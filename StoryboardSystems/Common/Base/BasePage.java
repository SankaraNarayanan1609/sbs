package com.Vcidex.StoryboardSystems.Common.Base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(getTimeoutFromConfig()));
    }

    // Helper method to fetch timeout from properties
    private static int getTimeoutFromConfig() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);
            return Integer.parseInt(properties.getProperty("webdriver.timeout", "10"));
        } catch (Exception e) {
            logger.warn("Failed to fetch timeout from config.properties. Using default timeout.");
            return 10; // Default timeout if config fails
        }
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }


    // General WebDriver Methods
    public void click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on element: {}", locator);
        } catch (Exception e) {
            logger.error("Click failed for element: {}", locator, e);
            throw new RuntimeException(e);
        }
    }

    public void enterTextUsingFollowingSibling(By parentLocator, String labelText, String inputText) {
        try {
            // Construct XPath for the input field based on the label
            String xpath = String.format(".//*[text()='%s']/following-sibling::input", labelText);

            WebElement inputField = (parentLocator != null)
                    ? driver.findElement(parentLocator).findElement(By.xpath(xpath))
                    : driver.findElement(By.xpath(xpath));

            inputField.clear(); // Clear any existing text
            inputField.sendKeys(inputText); // Enter the new text
            logger.info("Entered text '{}' in input field with label '{}'", inputText, labelText);
        } catch (Exception e) {
            logger.error("Failed to enter text '{}' in input field for label '{}'", inputText, labelText, e);
            throw new RuntimeException(e);
        }
    }


    public void selectDropdownUsingVisibleText(By parentLocator, String labelText, String visibleText) {
        try {
            // Construct XPath for the dropdown based on the label
            String xpath = String.format(".//*[text()='%s']/following-sibling::select", labelText);
            WebElement dropdown = driver.findElement(By.xpath(xpath));
            Select select = new Select(dropdown);
            select.selectByVisibleText(visibleText);
            logger.info("Selected '{}' in dropdown with label '{}'", visibleText, labelText);
        } catch (Exception e) {
            logger.error("Failed to select '{}' in dropdown for label '{}'", visibleText, labelText, e);
            throw new RuntimeException(e);
        }
    }

    public String getTextFromElementByLabel(String labelText) {
        try {
            String xpath = String.format(".//*[text()='%s']/following-sibling::*", labelText);
            WebElement element = driver.findElement(By.xpath(xpath));
            String text = element.getText();
            logger.info("Retrieved text '{}' for label '{}'", text, labelText);
            return text;
        } catch (Exception e) {
            logger.error("Failed to retrieve text for label '{}'", labelText, e);
            throw new RuntimeException(e);
        }
    }


    public void sendKeys(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
            logger.info("Text entered into element: {}", locator);
        } catch (Exception e) {
            logger.error("Text entry failed for element: {}", locator, e);
            throw new RuntimeException(e);
        }
    }

    public String getElementText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = element.getText();
            logger.info("Retrieved text: '{}' from element: {}", text, locator);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: {}", locator, e);
            throw new RuntimeException(e);
        }
    }

    // Method to select a dropdown option using visible text, after clicking the field
    public void selectDropdownUsingVisibleText(String fieldXpath, String label, String value) {
        // Build XPath to find the dropdown field using the label
        String fieldXPath = "//label[contains(text(), '" + label + "')]//following-sibling::input";
        WebElement dropdownField = driver.findElement(By.xpath(fieldXPath));

        // Click the field to open the dropdown
        dropdownField.click();

        // Wait for the dropdown to appear (you may need to adjust the wait condition depending on your UI)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'dropdown-menu')]")));

        // Find the option by visible text
        WebElement option = dropdownOption.findElement(By.xpath("//li[contains(text(), '" + value + "')]"));

        // Scroll into view if the dropdown is scrollable
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);

        // Click the option
        option.click();
    }

    public void scrollToElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}", locator, e);
            throw new RuntimeException(e);
        }
    }

    // Advanced Waits
    public void waitForAttributeToBe(By locator, String attribute, String value) {
        try {
            wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
            logger.info("Attribute '{}' of element {} is now '{}'", attribute, locator, value);
        } catch (Exception e) {
            logger.error("Failed to wait for attribute '{}' of element {} to be '{}'", attribute, locator, value, e);
            throw new RuntimeException(e);
        }
    }
    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }


    public void waitForTextToBePresent(By locator, String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            logger.info("Text '{}' is now present in element: {}", text, locator);
        } catch (Exception e) {
            logger.error("Failed to wait for text '{}' in element: {}", text, locator, e);
            throw new RuntimeException(e);
        }
    }

    public void waitForElementToDisappear(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            logger.info("Element is no longer visible: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to wait for element to disappear: {}", locator, e);
            throw new RuntimeException(e);
        }
    }

    // Cross-Browser Full Page Screenshot
    public void captureFullPageScreenshot(String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("./screenshots/" + fileName + ".png");
            Files.copy(screenshot.toPath(), destination.toPath());
            logger.info("Full page screenshot saved at: {}", destination.getAbsolutePath());
        } catch (Exception e) {
            logger.error("Failed to capture full page screenshot", e);
        }
    }

    // General Utility Methods
    public void executeJavaScript(String script, Object... args) {
        try {
            ((JavascriptExecutor) driver).executeScript(script, args);
        } catch (Exception e) {
            logger.error("JavaScript execution failed", e);
            throw new RuntimeException(e);
        }
    }

    public void handleAlert(boolean accept) {
        try {
            Alert alert = driver.switchTo().alert();
            if (accept) {
                alert.accept();
                logger.info("Alert accepted");
            } else {
                alert.dismiss();
                logger.info("Alert dismissed");
            }
        } catch (Exception e) {
            logger.error("Failed to handle alert", e);
            throw new RuntimeException(e);
        }
    }

    public void waitForPageLoad() {
        try {
            // This will wait until the document.readyState is 'complete', meaning the page has fully loaded
            wait.until(driver -> "complete".equals(((JavascriptExecutor) driver).executeScript("return document.readyState")));
            logger.info("Page loaded successfully");
        } catch (Exception e) {
            logger.error("Page load wait failed", e);
            throw new RuntimeException(e);
        }
    }
}
