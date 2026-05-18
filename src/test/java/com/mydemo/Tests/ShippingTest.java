package com.mydemo.Tests;

import com.mydemo.BaseTest;
import com.mydemo.drivers.GUIDriver;
import com.mydemo.drivers.UITest;
import com.mydemo.pages.CartPage;
import com.mydemo.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Owner("Abdelrahman Fahmy")
@Epic("UI Tests")
@Feature("Shipping")
@UITest
public class ShippingTest extends BaseTest {

    // Variables
    private final String emptyFullNameMessage = shippingData.getJsonData("messages.emptyFullName");
    private final String emptyAddressMessage = shippingData.getJsonData("messages.emptyAddress");
    private final String emptyCityMessage = shippingData.getJsonData("messages.emptyCity");
    private final String emptyZipCodeMessage = shippingData.getJsonData("messages.emptyZipCode");
    private final String emptyCountryMessage = shippingData.getJsonData("messages.emptyCountry");

    // Tests
    @BeforeClass
    public void setup() {
        driver = new GUIDriver();
    }

    @BeforeMethod
    public void preCondition() {
        driver.androidDevice().openApp(appPackage);
        new ProductsPage(driver)
                .openSideBarMenu()
                .clickLoginButton()
                .login(username, password)
                .viewProduct(product1Name)
                .clickAddToCart()
                .navigateToCart();
    }

    @Test(description = "Complete the shipping form with valid data")
    @Description("Verify that the shipping form can be completed successfully and the user can proceed to the payment page.")
    @Severity(SeverityLevel.BLOCKER)
    public void completeShippingFormTest() {
        new CartPage(driver)
                .proceedToCheckout()
                .fillShippingForm(fullName, address, city, country, zipCode)
                .clickToPaymentButton()
                .verifyPaymentMethodHeader();
    }

    @Test(description = "Complete the shipping form with empty fields")
    @Description("Verify that the appropriate error messages are displayed for each required field when they are left empty.")
    @Severity(SeverityLevel.CRITICAL)
    public void completeShippingFormWithEmptyFieldsTest() {
        new CartPage(driver)
                .proceedToCheckout()
                .clickToPaymentButton()
                .shippingPage()
                .verifyFullNameErrorMessage(emptyFullNameMessage)
                .verifyAddressErrorMessage(emptyAddressMessage)
                .verifyCityErrorMessage(emptyCityMessage)
                .verifyCountryErrorMessage(emptyCountryMessage)
                .verifyZipCodeErrorMessage(emptyZipCodeMessage);
    }

    @Test(description = "Complete the shipping form with empty full name")
    @Description("Verify that the full name field is required to continue to the payment step.")
    @Severity(SeverityLevel.CRITICAL)
    public void completeShippingFormWithEmptyFullNameTest() {
        new CartPage(driver)
                .proceedToCheckout()
                .fillShippingForm("", address, city, country, zipCode)
                .clickToPaymentButton()
                .shippingPage()
                .verifyFullNameErrorMessage(emptyFullNameMessage);
    }

    @Test(description = "Complete the shipping form with empty address")
    @Description("Verify that the address field is required to continue to the payment step.")
    @Severity(SeverityLevel.CRITICAL)
    public void completeShippingFormWithEmptyAddressTest() {
        new CartPage(driver)
                .proceedToCheckout()
                .fillShippingForm(fullName, "", city, country, zipCode)
                .clickToPaymentButton()
                .shippingPage()
                .verifyAddressErrorMessage(emptyAddressMessage);
    }

    @Test(description = "Complete the shipping form with empty city")
    @Description("Verify that the city field is required to continue to the payment step.")
    @Severity(SeverityLevel.CRITICAL)
    public void completeShippingFormWithEmptyCityTest() {
        new CartPage(driver)
                .proceedToCheckout()
                .fillShippingForm(fullName, address, "", country, zipCode)
                .clickToPaymentButton()
                .shippingPage()
                .verifyCityErrorMessage(emptyCityMessage);
    }

    @Test(description = "Complete the shipping form with empty country")
    @Description("Verify that the country field is required to continue to the payment step.")
    @Severity(SeverityLevel.CRITICAL)
    public void completeShippingFormWithEmptyCountryTest() {
        new CartPage(driver)
                .proceedToCheckout()
                .fillShippingForm(fullName, address, city, "", zipCode)
                .clickToPaymentButton()
                .shippingPage()
                .verifyCountryErrorMessage(emptyCountryMessage);
    }

    @Test(description = "Complete the shipping form with empty zip code")
    @Description("Verify that the zip code field is required to continue to the payment step.")
    @Severity(SeverityLevel.CRITICAL)
    public void completeShippingFormWithEmptyZipCodeTest() {
        new CartPage(driver)
                .proceedToCheckout()
                .fillShippingForm(fullName, address, city, country, "")
                .clickToPaymentButton()
                .shippingPage()
                .verifyZipCodeErrorMessage(emptyZipCodeMessage);
    }
}