package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.id("login-button");
    private By error = By.tagName("h3");


    public LoginPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public void setUsername(String name){
        var usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        usernameField.clear();
        usernameField.sendKeys(name);
    }
    public void setPassword(String pass){
        var passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        passwordField.clear();
        passwordField.sendKeys(pass);
    }
    public void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    public InventoryPage login(String username, String password){
        setUsername(username);
        setPassword(password);
        clickLoginButton();
        return new InventoryPage(driver);
    }
    public String getErrorText(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(error)).getText();
    }
}
