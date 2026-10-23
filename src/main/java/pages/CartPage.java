package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By cartItem = By.className("cart_item");
    private By itemName = By.className("inventory_item_name");
    private By continueShoppingBtn = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getURL(){
        return driver.getCurrentUrl();
    }
    public int getProductsCount(){
        return driver.findElements(cartItem).size();
    }
    public List<String> getProductsName(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemName));
        List<String> names = new ArrayList<>();
        for (WebElement product: driver.findElements(itemName)){
            names.add(product.getText());
        }
        return names;
    }
    public void removeProductFromCart(String productName) {
        By removeButton = By.id("remove-"+productName.toLowerCase().replace(" ", "-"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton)).click();
    }
    public InventoryPage goToInventoryPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingBtn)).click();
        return new InventoryPage(driver);
    }

}
