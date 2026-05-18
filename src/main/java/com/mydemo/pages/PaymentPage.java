package com.mydemo.pages;

import com.mydemo.drivers.GUIDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Locale;

public class PaymentPage {

    // Variables
    private final GUIDriver driver;

    // Constructor
    public PaymentPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By paymentMethodHeader = By.xpath("//android.widget.TextView[@text='Enter a payment method']");
    private final By fullNameField = AppiumBy.accessibilityId("Full Name* input field");
    private final By cardNumberField = AppiumBy.accessibilityId("Card Number* input field");
    private final By expirationDateField = AppiumBy.accessibilityId("Expiration Date* input field");
    private final By securityCodeField = AppiumBy.accessibilityId("Security Code* input field");
    private final By reviewOrderButton = AppiumBy.accessibilityId("Review Order button");
    private final By fullNameError = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Full Name*-error-message']/android.widget.TextView");
    private final By cardNumberError = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Card Number*-error-message']/android.widget.TextView");
    private final By expirationDateError = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Expiration Date*-error-message']/android.widget.TextView");
    private final By securityCodeError = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Security Code*-error-message']/android.widget.TextView");
    private final By totalAmountOfProducts = AppiumBy.accessibilityId("total number");
    private final By totalPrice = AppiumBy.accessibilityId("total price");
    private final By deliveryFee = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='checkout delivery details']/android.widget.TextView)[2]");
    private final By placeOrderButton = AppiumBy.accessibilityId("Place Order button");

    // Dynamic Locators
    private By shippingData(String field) {
        return AppiumBy.xpath("//android.view.ViewGroup[@content-desc='checkout delivery address']/android.widget.TextView[@text='" + field + "']");
    }

    private By paymentData(String field) {
        return AppiumBy.xpath("//android.view.ViewGroup[@content-desc='checkout payment info']/android.widget.TextView[contains(@text,'" + field + "')]");
    }

    // Actions
    public ShippingPage shippingPage() {
        return new ShippingPage(driver);
    }

    private String getDeliveryFee() {
        return driver.element().getText(deliveryFee).replace("$", "");
    }

    private String getTotalAmountOfProducts() {
        return driver.element().getText(totalAmountOfProducts).split(" ")[0];
    }

    private String getTotalPrice() {
        return driver.element().getText(totalPrice).replace("$", "");
    }

    @Step("Fill payment details with full name: {fullName}, card number: {cardNumber}, expiration date: {expirationDate}, security code: {securityCode}")
    public PaymentPage fillPaymentDetails(String fullName, String cardNumber, String expirationDate, String securityCode) {
        driver.element().type(fullNameField, fullName);
        driver.element().type(cardNumberField, cardNumber);
        driver.element().type(expirationDateField, expirationDate);
        driver.element().type(securityCodeField, securityCode);
        return this;
    }

    @Step("Click on Review Order button")
    public PaymentPage clickReviewOrder() {
        driver.element().click(reviewOrderButton);
        return this;
    }

    @Step("Click on Place Order button")
    public OrderSuccessPage clickPlaceOrder() {
        driver.element().click(placeOrderButton);
        return new OrderSuccessPage(driver);
    }

    // Assertions
    @Step("Verify payment method header is visible")
    public PaymentPage verifyPaymentMethodHeader() {
        driver.softAssert().isElementVisible(paymentMethodHeader);
        return this;
    }

    @Step("Verify full name error message is: {expectedMessage}")
    public PaymentPage verifyFullNameError(String expectedMessage) {
        String actualMessage = driver.element().getText(fullNameError);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "Full Name error message does not match expected");
        return this;
    }

    @Step("Verify card number error message is: {expectedMessage}")
    public PaymentPage verifyCardNumberError(String expectedMessage) {
        String actualMessage = driver.element().getText(cardNumberError);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "Card Number error message does not match expected");
        return this;
    }

    @Step("Verify expiration date error message is: {expectedMessage}")
    public PaymentPage verifyExpirationDateError(String expectedMessage) {
        String actualMessage = driver.element().getText(expirationDateError);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "Expiration Date error message does not match expected");
        return this;
    }

    @Step("Verify security code error message is: {expectedMessage}")
    public PaymentPage verifySecurityCodeError(String expectedMessage) {
        String actualMessage = driver.element().getText(securityCodeError);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "Security Code error message does not match expected");
        return this;
    }

    @Step("Verify total amount of products is: {expectedAmount}")
    public PaymentPage verifyTotalAmountOfProducts(String expectedAmount) {
        driver.softAssert().assertEquals(getTotalAmountOfProducts(), expectedAmount, "Total amount of products does not match expected");
        return this;
    }

    @Step("Verify total price is calculated correctly including delivery fee in review order page")
    public PaymentPage verifyTotalPrice(String productsTotalPrice) {
        double deliveryFeeValue = Double.parseDouble(getDeliveryFee());
        double productsTotalPriceValue = Double.parseDouble(productsTotalPrice);
        double expectedTotalPrice = deliveryFeeValue + productsTotalPriceValue;
        driver.softAssert().assertEquals(getTotalPrice(), String.format(Locale.US, "%.2f", expectedTotalPrice), "Total price format does not match expected");
        return this;
    }

    @Step("Verify shipping data is displayed correctly in review order page")
    public PaymentPage verifyShippingDetailsIsCorrect(String fullName, String address, String city, String country, String zipCode) {
        driver.softAssert().isElementVisible(shippingData(fullName));
        driver.softAssert().isElementVisible(shippingData(address));
        driver.softAssert().isElementVisible(shippingData(city));
        driver.softAssert().isElementVisible(shippingData(country + ", " + zipCode));
        return this;
    }

    @Step("Verify payment data is displayed correctly in review order page")
    public PaymentPage verifyPaymentDetailsIsCorrect(String cardFullName, String cardNumber, String expirationDate) {
        driver.softAssert().isElementVisible(paymentData(cardFullName));
        driver.softAssert().isElementVisible(paymentData(cardNumber.substring(0, 4)));
        driver.softAssert().isElementVisible(paymentData("Exp: " + expirationDate));
        return this;
    }
}