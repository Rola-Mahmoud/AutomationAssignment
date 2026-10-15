package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;

public class BaseTest {
    protected WebDriver driver;
//    private final String BASE_URL = "https://www.saucedemo.com/";
//    protected LoginPage loginPage;

    @BeforeSuite
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
//    @BeforeMethod
//    public void setupPage() {
//        driver.get(BASE_URL);
//        loginPage = new LoginPage(driver);
//    }
    @AfterSuite(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
