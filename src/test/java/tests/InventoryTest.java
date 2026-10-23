package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import utils.DataDriven;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InventoryTest extends BaseTest {
    private InventoryPage inventoryPage;
    private By linkedin = By.xpath("//a[contains(@href, 'linkedin')]");
    private By facebook = By.xpath("//a[contains(@href,'facebook')]");
    private By x = By.xpath("//a[contains(@href,'x.com')]");

    @BeforeMethod
    public void setUpInventoryPage() {
        inventoryPage = loginAsValidUser();
        assertTrue(inventoryPage.getURL().contains("/inventory.html"),
                "Incorrect URL");
    }
    @Test
    public void testPageTitle()
    {
        assertEquals(inventoryPage.getTitle(), "Swag Labs",
                "The page title is not correct");
    }
    @Test
    public void testCartIcon()
    {
        assertTrue(inventoryPage.cartIsDisplayed(),
                "The cart is not displayed");
    }
    @Test
    public void testProductsCount()
    {
        assertEquals(inventoryPage.getProductsCount(),6,
                "Products count displayed on the Inventory page not correct");
    }
    @Test
    public void testLinkedinIcon(){
        assertTrue(inventoryPage.verifyIcon(linkedin).contains("linkedin"));
    }
    @Test
    public void testFacebookIcon(){
        assertTrue(inventoryPage.verifyIcon(facebook).contains("facebook"));
    }
    @Test
    public void testXIcon(){
        assertTrue(inventoryPage.verifyIcon(x).contains("x.com"));
    }

}