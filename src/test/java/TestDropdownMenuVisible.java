import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.plaf.basic.ComboPopup;
import java.time.Duration;
import java.util.List;
import java.util.Objects;


public class TestDropdownMenuVisible {

    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(TestDropdownMenuVisible.class);

    // Initialize the WebDriver before each test
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver(); // Initialize ChromeDriver
        driver.get("https://katalon-demo-cura.herokuapp.com/"); // Open the page to test
        logger.info("Navigating to URL: https://katalon-demo-cura.herokuapp.com/");
    }

    // Close the WebDriver after each test
   // @AfterEach
   // public void tearDown() {
   //     if (driver != null) {
   //         driver.quit(); // Close the browser after the test
   //         logger.info("Browser closed successfully.");
   //     }
   // }


    @Test
    public void testSidebarMenu() {
        // Wait for the menu toggle button to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement menuToggleButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#menu-toggle")));

        // Assert that the menu toggle button is displayed
        Assertions.assertTrue(menuToggleButton.isDisplayed(), "Menu toggle button is visible.");

        // Click the menu toggle button
        menuToggleButton.click();

        // Wait for the sidebar menu to be visible
        WebElement sidebarMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#sidebar-wrapper")));

        // Assert that the sidebar menu is displayed
        Assertions.assertTrue(sidebarMenu.isDisplayed(), "Sidebar menu is visible.");

        // Locate the list items inside the sidebar menu
        List<WebElement> menuItems = sidebarMenu.findElements(By.tagName("a"));

        // Assert that there are menu items
        Assertions.assertFalse(menuItems.isEmpty(), "Sidebar menu contains items.");

        // Print the text of each menu item and interact if needed
        for (WebElement item : menuItems) {
            System.out.println("Menu item: " + item.getText());

            if (item.getText().equals("Login")) {
                System.out.println("All items found");
                item.click(); // Example: Click the Login item
                break; // Exit the loop after clicking Login

            }
        }

        WebElement loginPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login")));

        // Assert that the login Page is displayed
        Assertions.assertTrue(loginPage.isDisplayed(), "loginPage page is visible.");







        // Wait for the element containing the text to be visible
        WebElement sourceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login > div > div > div.col-sm-offset-3.col-sm-6 > form > div.alert.alert-info > div:nth-child(1) > div > div > input"))); // Replace with the correct locator
        String textToCopy = sourceElement.getAttribute("value"); // Get the text from the element


        // Locate the textbox where the text will be written
        WebElement targetTextbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#txt-username")));


        // Write the retrieved text into the textbox
        targetTextbox1.clear(); // Clear any existing text (optional)
        targetTextbox1.sendKeys(textToCopy); // Send the retrieved text







        // Wait for the element containing the text to be visible
        WebElement SourcePassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login > div > div > div.col-sm-offset-3.col-sm-6 > form > div.alert.alert-info > div:nth-child(2) > div > div > input")));
        String textToCopy2 = SourcePassword.getAttribute("value"); // Get the text from the element


        // Locate the textbox where the text will be written
        WebElement passwordBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txt-password")));

        // Write the retrieved text into the textbox
        passwordBox.clear(); // Clear any existing text (optional)

        passwordBox.sendKeys(textToCopy2); // Send the retrieved text

        Assertions.assertEquals(textToCopy2, passwordBox.getAttribute("value"), "Password was not copied correctly.");






        //Check if it's visible login button
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btn-login")));
        loginButton.click();




        WebElement MakeAppointmentPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#appointment")));

        // Assert that the login Page is displayed
        Assertions.assertTrue(MakeAppointmentPage.isDisplayed(), " Appointment page is visible.");



        WebElement combo_facility = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#combo_facility")));//click on that combo
        // Create a Select object for interacting with the dropdown
        Select select = new Select(combo_facility);

        // Get all options from the combo box
        List<WebElement> options = select.getOptions();

        // Iterate through options and print or return the text of each option
        for (WebElement option : options) {
           // System.out.println(option.getAttribute("value")); // Print the value attribute
            if (Objects.equals(option.getAttribute("value"), "Hongkong CURA Healthcare Center")) { // Match by value
                option.click(); // Select the option by value
                break; // Exit the loop after selecting the desired option
            }
        }


        // Wait until the radio button is visible and present
        WebElement radioButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#chk_hospotal_readmission")));

        // Check if the radio button is enabled and displayed
        if (radioButton != null && radioButton.isDisplayed() && radioButton.isEnabled()) {
            // Click on the radio button
            radioButton.click();
            System.out.println("Radio button is present and clicked.");
        } else {
            System.out.println("Radio button is not available or clickable.");
        }

       // //Switch to the iframe containing the Datepicker
       // driver.switchTo().frame(driver.findElement(By.cssSelector("#appointment > div > div > form > div:nth-child(4) > div > div")));
       // // Wait until the Datepicker element is visible and click it
       // WebElement datepicker = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div > div.datepicker-days"))
       // );
       // datepicker.click();
       // System.out.println("Datepicker opened");
       // // Select a specific date, e.g., 27th of the current month
       // WebElement dateToSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='25']")));
       // dateToSelect.click();
       // System.out.println("Date '27' selected");


    }
}
