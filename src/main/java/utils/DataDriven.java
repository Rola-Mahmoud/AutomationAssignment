package utils;

import org.testng.annotations.DataProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

public class DataDriven {
    @DataProvider(name = "testData")
    public static Object[][] getData(Method method) throws IOException {
        String testName = switch (method.getName()) {
            case "testValidLogin" -> "validLogin";
            case "testInvalidLogin" -> "invalidLogin";
            case "testLoginWithoutPassword" -> "emptyPassword";
            default -> throw new RuntimeException("Unknown test: " + method.getName());
        };

        return new Object[][] { getCredentials(testName) };
    }

    // Returns {username, password} of the given TestcaseName
    public static String[] getCredentials(String testName) {
        JsonNode item = findTestCase(testName);
        return new String[] {
                item.get("Username").asText(),
                item.get("Password").asText()
        };
    }

    // Returns the "Products" list of the "cartProducts" entry
    public static List<String> getCartProducts() {
        List<String> products = new ArrayList<>();
        for (JsonNode product : findTestCase("cartProducts").get("Products")) {
            products.add(product.asText());
        }
        return products;
    }

    // Finds the entry with the given TestcaseName
    private static JsonNode findTestCase(String testName) {
        try (InputStream input = new FileInputStream("testData/testData.json")) {
            JsonNode data = new ObjectMapper().readTree(input);

            for (JsonNode item : data) {
                if (item.get("TestcaseName").asText().equals(testName)) {
                    return item;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read testData.json", e);
        }

        throw new RuntimeException("Test case not found: " + testName);
    }
}
