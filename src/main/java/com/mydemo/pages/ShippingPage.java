package com.mydemo.pages;

import com.mydemo.drivers.GUIDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ShippingPage {

    // Variables
    private final GUIDriver driver;

    // Constructor
    public ShippingPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By fullNameField = AppiumBy.accessibilityId("Full Name* input field");
    private final By addressField = AppiumBy.accessibilityId("Address Line 1* input field");
    private final By cityField = AppiumBy.accessibilityId("City* input field");
    private final By countryField = AppiumBy.accessibilityId("Country* input field");
    private final By zipCodeField = AppiumBy.accessibilityId("Zip Code* input field");
    private final By toPaymentButton = AppiumBy.accessibilityId("To Payment button");
    private final By fullNameError = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Full Name*-error-message']/android.widget.TextView");
    private final By addressError = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Address Line 1*-error-message']/android.widget.TextView");
    private final By cityError = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='City*-error-message']/android.widget.TextView");
    private final By countryError = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Country*-error-message']/android.widget.TextView");
    private final By zipCodeError = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Zip Code*-error-message']/android.widget.TextView");

    // Dynamic Locators

    // Actions
    public LoginPage loginPage() {
        return new LoginPage(driver);
    }

    @Step("Fill the shipping form with full name: {fullName}, address: {address}, city: {city}, country: {country}, and zip code: {zipCode}")
    public ShippingPage fillShippingForm(String fullName, String address, String city, String country, String zipCode) {
        driver.element().type(fullNameField, fullName);
        driver.element().type(addressField, address);
        driver.element().type(cityField, city);
        driver.element().type(zipCodeField, zipCode);
        driver.element().type(countryField, country);
        return this;
    }

    @Step("Click on To Payment button")
    public PaymentPage clickToPaymentButton() {
        driver.element().click(toPaymentButton);
        return new PaymentPage(driver);
    }

    // Assertions
    @Step("Verify full name error message is: {expectedMessage}")
    public ShippingPage verifyFullNameErrorMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(fullNameError);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "Full name error message is incorrect");
        return this;
    }

    @Step("Verify address error message is: {expectedMessage}")
    public ShippingPage verifyAddressErrorMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(addressError);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "Address error message is incorrect");
        return this;
    }

    @Step("Verify city error message is: {expectedMessage}")
    public ShippingPage verifyCityErrorMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(cityError);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "City error message is incorrect");
        return this;
    }

    @Step("Verify country error message is: {expectedMessage}")
    public ShippingPage verifyCountryErrorMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(countryError);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "Country error message is incorrect");
        return this;
    }

    @Step("Verify zip code error message is: {expectedMessage}")
    public ShippingPage verifyZipCodeErrorMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(zipCodeError);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "Zip code error message is incorrect");
        return this;
    }
}
