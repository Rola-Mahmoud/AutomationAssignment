package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DataDriven;

import java.time.Duration;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;
    private By pageTitle = By.className("app_logo");
    private By cart = By.id("shopping_cart_container");
    private By products = By.className("inventory_item");
    private WebDriverWait wait;
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public String getURL() {
        return driver.getCurrentUrl();
    }
    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }
    public boolean cartIsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cart)).isDisplayed();
    }
    public int getProductsCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(products));
        return driver.findElements(products).size();
    }
    public void addProductToCart(String productName) {
        By addButton = By.id("add-to-cart-"+productName.toLowerCase().replace(" ", "-"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addButton)).click();
    }
    public List<String> addSpecificList() {
        List<String> productsData = DataDriven.getCartProducts();
        for (String product : productsData) {
            addProductToCart(product);
        }
        return productsData;
    }
    public String checkButton(String productName) {
        By buttonLocator = By.id("add-to-cart-"+productName.toLowerCase().replace(" ", "-"));
        if (!driver.findElements(buttonLocator).isEmpty()) {
            System.out.println();
            return "Add to cart";
        } else {
            return "Remove";
        }
    }
//    public By getAddButton(String productName) {
//        String name = productName.toLowerCase().replace(" ", "-");
//        return By.id(name);
//    }
    public CartPage goToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cart)).click();
        return new CartPage(driver);
    }
    public String verifyIcon(By icon){
        wait.until(ExpectedConditions.visibilityOfElementLocated(icon));
        String originalTab = driver.getWindowHandle();
        int oldTabCount = driver.getWindowHandles().size();
        driver.findElement(icon).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(oldTabCount + 1));
        return getNewTabURL(originalTab);
    }
    public String getNewTabURL(String originalTabURL){
        for (String tab :  driver.getWindowHandles()) {
            if (!tab.equals(originalTabURL)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        String newTabURL = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(originalTabURL);
        return newTabURL;
    }
}
