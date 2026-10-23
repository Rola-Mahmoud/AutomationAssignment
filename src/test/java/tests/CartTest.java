package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.CartPage;
import utils.DataDriven;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {
    private CartPage cartPage;
    private List<String> products;
    private String removedProduct;

    @Test
    public void testSpecificProductsAdded(){
        inventoryPage = loginAsValidUser();
        products = inventoryPage.addSpecificList();
        cartPage = inventoryPage.goToCart();
        assertTrue(cartPage.getURL().contains("/cart.html"),"Incorrect URL");
        assertEquals(cartPage.getProductsCount(),products.size(),"Products count should be "+products.size());
        assertEquals(cartPage.getProductsName(),products,"Product name mismatch");
    }
    @Test(dependsOnMethods = "testSpecificProductsAdded")
    public void testRemoveProduct(){
        removedProduct = products.get(1);
        cartPage.removeProductFromCart(removedProduct);
        inventoryPage = cartPage.goToInventoryPage();
        assertTrue(inventoryPage.getURL().contains("/inventory.html"),"Incorrect URL");
        assertEquals(inventoryPage.checkButton(removedProduct),"Add to cart",
                "Expected Add to cart button for the product: "+removedProduct);
        for (var product : products) {
            if (product.equals(removedProduct)) {
                continue;
            }else{
                assertEquals(inventoryPage.checkButton(product),"Remove",
                        "Expected Remove button for the product: "+product);
            }
        }

    }

}
