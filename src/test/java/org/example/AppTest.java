package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AppTest extends TestCase {
    private WebDriver driver;
    private static final String BASE_URL = "https://www.selenium.dev/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 5L;
    private static final String TEXT_LOCATOR = "#selenium_logo title";
    private static final String EXPECTED_RESULT = "Selenium";

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();        // download the latest webDriver
        ChromeOptions options = new ChromeOptions();    // initialize driver instance
        options.addArguments("--window-size=1920,1080");// set window size
        options.addArguments("--start-maximized");      // start maximized screen
        //options.addArguments("--no-sandbox");
        //options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");             // run in headless mode
        options.addArguments("--no-proxy-server");      // cno proxy server
        options.addArguments("--ignore-certificate-errors");    // ignore-certificate-errors
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.navigate().to(BASE_URL);
    }

    @Test
    public void testMethod() {
        String actual = driver.findElement(By.cssSelector(TEXT_LOCATOR)).getText();
        Assert.assertTrue(actual.contains(EXPECTED_RESULT));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
