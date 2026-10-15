package tests;

import base.BaseTest;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InventoryTest extends BaseTest {
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setUpInventoryPage(ITestContext context) {
        inventoryPage = (InventoryPage) context.getAttribute("inventoryPage");
        assertTrue(inventoryPage != null, "InventoryPage was not initialized after login");
    }

    @Test(dependsOnMethods = "tests.LoginTest.testValidLogin")
    public void testPageTitle()
    {
        assertEquals(inventoryPage.getTitle(),"Swag Labs","The page title is not correct");
    }

    @Test(dependsOnMethods = "tests.LoginTest.testValidLogin")
    public void testCartIcon()
    {
        assertTrue(inventoryPage.cartIsDisplayed(),"The cart is not displayed");
    }

    @Test(dependsOnMethods = "tests.LoginTest.testValidLogin")
    public void testProductsCount()
    {
        assertEquals(inventoryPage.getProductsCount(),6,"Products count displayed on the Inventory page not correct");
    }
    
}
