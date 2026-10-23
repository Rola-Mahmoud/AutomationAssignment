package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.InventoryPage;
import utils.DataDriven;
import static org.testng.Assert.assertTrue;


@Test(dataProvider = "testData", dataProviderClass = DataDriven.class)
public class LoginTest extends BaseTest {

    public void testValidLogin(String username, String password){
        InventoryPage inventoryPage = loginPage.login(username, password);
        assertTrue(inventoryPage.getURL().contains("/inventory.html"),
                "Incorrect URL");
    }
    public void testInvalidLogin(String username, String password){
        loginPage.login(username, password);
        assertTrue(loginPage.getErrorText().contains("Username and password do not match"),
                "Expected error message for Username and Password is not displayed");
    }
    public void testLoginWithoutPassword(String username, String password){
        loginPage.login(username, password);
        assertTrue(loginPage.getErrorText().contains("Password is required"),
                "Password required error message is not displayed");
    }
}