package com.Vcidex.StoryboardSystems.Purchase.Pages.Purchase_Order;

import com.Vcidex.StoryboardSystems.Purchase.PurchaseBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Direct_PO extends PurchaseBasePage {

    // Constructor
    public Direct_PO(WebDriver driver) {
        super(driver);
    }

    // Unique Elements for Direct_PO_Add
    @FindBy(id = "UploadFile")
    private WebElement uploadFile;

    @FindBy(xpath = "//button[contains(text(), 'Delete')]")
    private WebElement productDeleteButton;

    @FindBy(id = "termsConditionsDropdown")
    private WebElement termsConditionsDropdown;

    @FindBy(id = "saveAsDraftButton")
    private WebElement saveAsDraftButton;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(id = "draftsButton")
    private WebElement draftsButton;

    // Locator for confirmation message (you need to adjust this based on your UI)
    private final By confirmationMessageLocator = By.id("confirmationMessage");

    // Methods for Direct_PO_Add
    public void uploadFile(String filePath) {
        uploadFile.sendKeys(filePath);
    }

    public void deleteProductFromSummary() {
        productDeleteButton.click();
    }

    public void selectTermsConditions(String terms) {
        termsConditionsDropdown.sendKeys(terms);
    }

    public void clickSaveAsDraft() {
        saveAsDraftButton.click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void openDrafts() {
        draftsButton.click();
    }

    // Wrapper method for creating Direct PO
    public void createDirectPO(String filePath, String terms) {
        // Upload file (Direct PO-specific)
        uploadFile(filePath);

        // Select terms and conditions (Direct PO-specific)
        selectTermsConditions(terms);

        // Click the Submit button (Direct PO-specific)
        clickSubmitButton();
    }

    // Method to get confirmation message after PO creation
    public String getConfirmationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmationMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessageLocator));
        return confirmationMessageElement.getText(); // Returns the confirmation message text
    }
}
