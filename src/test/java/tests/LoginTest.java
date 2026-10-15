package tests;

import base.BaseTest;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DataDriven;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private InventoryPage inventoryPage;
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private LoginPage loginPage;

    @BeforeMethod
    public void resetLoginPage() {
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "testData", dataProviderClass = DataDriven.class)
    public void testValidLogin(String username, String password, ITestContext context){
        inventoryPage = loginPage.login(username, password);
        context.setAttribute("inventoryPage", inventoryPage);
        assertTrue(inventoryPage.getURL().contains("/inventory.html"),"Incorrect URL");
    }

    @Test(dataProvider = "testData", dataProviderClass = DataDriven.class)
    public void testInvalidLogin(String username, String password){
        loginPage.login(username, password);
        assertTrue(loginPage.getErrorText().contains("Username and password do not match"),
                "Expected error message for Username and Password is not displayed");
    }

    @Test(dataProvider = "testData", dataProviderClass = DataDriven.class)
    public void testLoginWithoutPassword(String username, String password){
        loginPage.login(username, password);
        assertTrue(loginPage.getErrorText().contains("Password is required"),
                "Password required error message is not displayed");
    }
}