package com.Vcidex.StoryboardSystems.Purchase;

import com.Vcidex.StoryboardSystems.Common.Base.BasePage;
import org.openqa.selenium.WebDriver;

public class PurchaseBasePage extends BasePage {

    // Constructor
    public PurchaseBasePage(WebDriver driver) {
        super(driver);
    }

    // Methods for common interactions using BasePage methods

    public void selectBranchName(String branch) {
        selectDropdownUsingVisibleText(null, "Branch Name", branch); // Assuming the label is "Branch Name"
    }

    public void selectVendorName(String vendor) {
        selectDropdownUsingVisibleText(null, "Vendor Name", vendor); // Assuming the label is "Vendor Name"
    }

    public void enterDeliveryTerms(String terms) {
        enterTextUsingFollowingSibling(null, "Delivery Terms", terms); // Assuming the label is "Delivery Terms"
    }

    public void enterPaymentTerms(String terms) {
        enterTextUsingFollowingSibling(null, "Payment Terms", terms); // Assuming the label is "Payment Terms"
    }

    public void selectCurrency(String currency) {
        selectDropdownUsingVisibleText(null, "Currency", currency); // Assuming the label is "Currency"
    }

    public void enterQuantity(String quantity) {
        enterTextUsingFollowingSibling(null, "Quantity", quantity); // Assuming the label is "Quantity"
    }

    public void enterPrice(String price) {
        enterTextUsingFollowingSibling(null, "Price", price); // Assuming the label is "Price"
    }

    public void enterDiscount(String discount) {
        enterTextUsingFollowingSibling(null, "Discount", discount); // Assuming the label is "Discount"
    }

    public void enterAddOnCharges(String charges) {
        enterTextUsingFollowingSibling(null, "Add-On Charges", charges); // Assuming the label is "Add-On Charges"
    }

    public void enterAdditionalDiscount(String discount) {
        enterTextUsingFollowingSibling(null, "Additional Discount", discount); // Assuming the label is "Additional Discount"
    }

    public void enterFreightCharges(String charges) {
        enterTextUsingFollowingSibling(null, "Freight Charges", charges); // Assuming the label is "Freight Charges"
    }

    public void selectAdditionalTax(String tax) {
        selectDropdownUsingVisibleText(null, "Additional Tax", tax); // Assuming the label is "Additional Tax"
    }

    public void enterRoundOff(String roundOff) {
        enterTextUsingFollowingSibling(null, "Round Off", roundOff); // Assuming the label is "Round Off"
    }

    // Methods for viewing data
    public String getVendorDetails() {
        return getTextFromElementByLabel("Vendor Details"); // Assuming the label is "Vendor Details"
    }

    public String getExchangeRate() {
        return getTextFromElementByLabel("Exchange Rate"); // Assuming the label is "Exchange Rate"
    }

    public String getTotalAmount() {
        return getTextFromElementByLabel("Total Amount"); // Assuming the label is "Total Amount"
    }

    public String getGrandTotal() {
        return getTextFromElementByLabel("Grand Total"); // Assuming the label is "Grand Total"
    }
}