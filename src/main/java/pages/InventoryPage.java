package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private WebDriver driver;
    private By pageTitle = By.className("app_logo");
    private By cart = By.id("shopping_cart_container");
    private By products = By.className("inventory_item");
    private WebDriverWait wait;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public String getURL() {
        return driver.getCurrentUrl();
    }
    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();
    }
    public boolean cartIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cart));
        return driver.findElement(cart).isDisplayed();
    }
    public int getProductsCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(products));
        return driver.findElements(products).size();
    }

}
