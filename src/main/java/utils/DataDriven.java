package utils;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.openqa.selenium.json.Json;
import org.testng.annotations.DataProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import java.lang.reflect.Method;

public class DataDriven {
    @DataProvider(name = "testData")
    public static Object[][] getData(Method method) throws IOException {
        String testName = switch (method.getName()) {
            case "testValidLogin" -> "validLogin";
            case "testInvalidLogin" -> "invalidLogin";
            case "testLoginWithoutPassword" -> "emptyPassword";
            default -> throw new RuntimeException("Unknown test: " + method.getName());
        };

        InputStream input = new FileInputStream("testData/testData.json");
        JsonNode data = new ObjectMapper().readTree(input);

        for (JsonNode item : data) {
            if (item.get("TestcaseName").asText().equals(testName)) {
                return new Object[][] {{
                        item.get("Username").asText(),
                        item.get("Password").asText()
                }};
            }
        }

        throw new RuntimeException("Test case not found: " + testName);
    }
}

