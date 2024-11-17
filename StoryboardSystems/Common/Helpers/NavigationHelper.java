package com.Vcidex.StoryboardSystems.Common.Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NavigationHelper {
    private static final Logger logger = LoggerFactory.getLogger(NavigationHelper.class);
    private WebDriver driver;

    public NavigationHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToModuleAndMenu(String moduleName, String menuName, String subMenuName) {
        clickModuleByText(moduleName);
        clickMenuByText(menuName);
        clickSubMenuByText(subMenuName);
    }

    private void clickModuleByText(String text) {
        By locator = By.xpath("//div[text()='" + text + "']");
        clickElement(locator);
    }

    private void clickMenuByText(String text) {
        By locator = By.xpath("//div[contains(@class, 'menu') and text()='" + text + "']");
        clickElement(locator);
    }

    private void clickSubMenuByText(String text) {
        By locator = By.xpath("//div[contains(@class, 'submenu') and text()='" + text + "']");
        clickElement(locator);
    }

    private void clickElement(By locator) {
        try {
            driver.findElement(locator).click();
            logger.info("Clicked on element with locator: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to click element with locator: {}", locator, e);
            throw new RuntimeException(e);
        }
    }
}
