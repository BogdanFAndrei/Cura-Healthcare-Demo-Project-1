import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ButtonVisibilityTest {

    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(ButtonVisibilityTest.class);

    // Initialize the WebDriver before each test
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver(); // Initialize ChromeDriver
        driver.get("https://katalon-demo-cura.herokuapp.com/"); // Open the page to test
        logger.info("Navigating to URL: https://katalon-demo-cura.herokuapp.com/");
    }

    // Close the WebDriver after each test
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser after the test
            logger.info("Browser closed successfully.");
        }
    }



    @Test
    public void testAppButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Increased timeout to 10 seconds
        WebElement cookieButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btn-make-appointment")));

        Assertions.assertTrue(cookieButton.isDisplayed(),  "Make Appointment Button visible.");
    }

}
