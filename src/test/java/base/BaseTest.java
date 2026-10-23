package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DataDriven;

import java.lang.reflect.Method;

public class BaseTest {
    private WebDriver driver;
    private final String BASE_URL = "https://www.saucedemo.com/";
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;

    @BeforeMethod
    public void setup() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(BASE_URL);
            loginPage = new LoginPage(driver);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(Method method) {
        if (method.getName().equals("testSpecificProductsAdded")) {
            return;
        }
        driver.quit();
        driver = null;
    }
    protected InventoryPage loginAsValidUser() {
        String [] user = DataDriven.getCredentials("validLogin");
        return loginPage.login(user[0], user[1]);
    }

}
