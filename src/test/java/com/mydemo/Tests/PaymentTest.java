package com.mydemo.Tests;

import com.mydemo.BaseTest;
import com.mydemo.drivers.GUIDriver;
import com.mydemo.drivers.UITest;
import com.mydemo.pages.ProductsPage;
import com.mydemo.pages.ShippingPage;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Owner("Abdelrahman Fahmy")
@Epic("UI Tests")
@Feature("Payment")
@UITest
public class PaymentTest extends BaseTest {

    // Variables
    private final String invalidValueMessage = paymentData.getJsonData("messages.invalidValue");

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
                .navigateToCart()
                .proceedToCheckout()
                .fillShippingForm(fullName, address, city, country, zipCode);
    }

    @Test(description = "Place an order with valid payment details")
    @Description("Verify that the user can place an order successfully when valid payment details are provided.")
    @Severity(SeverityLevel.BLOCKER)
    public void placeOrderWithValidPaymentDetailsTest() {
        new ShippingPage(driver)
                .clickToPaymentButton()
                .fillPaymentDetails(cardFullName, cardNumber, expirationDate, securityCode)
                .clickReviewOrder()
                .clickPlaceOrder()
                .verifyOrderSuccess();
    }

    @Test(description = "Verify products details on review page")
    @Description("Verify that the products details are correctly displayed on the review page.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductsDetailsOnReviewPageTest() {
        new ShippingPage(driver)
                .clickToPaymentButton()
                .fillPaymentDetails(cardFullName, cardNumber, expirationDate, securityCode)
                .clickReviewOrder()
                .verifyTotalAmountOfProducts("1")
                .verifyTotalPrice(product1Price);
    }

    @Test(description = "Verify shipping details on review page")
    @Description("Verify that the shipping details are correctly displayed on the review page.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyShippingDetailsOnReviewPageTest() {
        new ShippingPage(driver)
                .clickToPaymentButton()
                .fillPaymentDetails(cardFullName, cardNumber, expirationDate, securityCode)
                .clickReviewOrder()
                .verifyShippingDetailsIsCorrect(fullName, address, city, country, zipCode);
    }

    @Test(description = "Verify payment details on review page")
    @Description("Verify that the payment details are correctly displayed on the review page.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyPaymentDetailsOnReviewPageTest() {
        new ShippingPage(driver)
                .clickToPaymentButton()
                .fillPaymentDetails(cardFullName, cardNumber, expirationDate, securityCode)
                .clickReviewOrder()
                .verifyPaymentDetailsIsCorrect(cardFullName, cardNumber, expirationDate);
    }

    @Test(description = "Place an order with empty payment details")
    @Description("Verify that appropriate error messages are displayed when trying to place an order with empty payment details.")
    @Severity(SeverityLevel.CRITICAL)
    public void placeOrderWithEmptyPaymentDetailsTest() {
        new ShippingPage(driver)
                .clickToPaymentButton()
                .clickReviewOrder()
                .verifyFullNameError(invalidValueMessage)
                .verifyCardNumberError(invalidValueMessage)
                .verifyExpirationDateError(invalidValueMessage)
                .verifySecurityCodeError(invalidValueMessage);
    }

    @Test(description = "Place an order with empty full name")
    @Description("Verify that an appropriate error message is displayed when trying to place an order with an empty full name in the payment details.")
    @Severity(SeverityLevel.NORMAL)
    public void placeOrderWithEmptyFullNameTest() {
        new ShippingPage(driver)
                .clickToPaymentButton()
                .fillPaymentDetails("", cardNumber, expirationDate, securityCode)
                .clickReviewOrder()
                .verifyFullNameError(invalidValueMessage);
    }

    @Test(description = "Place an order with empty card number")
    @Description("Verify that an appropriate error message is displayed when trying to place an order with an empty card number in the payment details.")
    @Severity(SeverityLevel.CRITICAL)
    public void placeOrderWithEmptyCardNumberTest() {
        new ShippingPage(driver)
                .clickToPaymentButton()
                .fillPaymentDetails(cardFullName, "", expirationDate, securityCode)
                .clickReviewOrder()
                .verifyCardNumberError(invalidValueMessage);
    }

    @Test(description = "Place an order with empty expiration date")
    @Description("Verify that an appropriate error message is displayed when trying to place an order with an empty expiration date in the payment details.")
    @Severity(SeverityLevel.CRITICAL)
    public void placeOrderWithEmptyExpirationDateTest() {
        new ShippingPage(driver)
                .clickToPaymentButton()
                .fillPaymentDetails(cardFullName, cardNumber, "", securityCode)
                .clickReviewOrder()
                .verifyExpirationDateError(invalidValueMessage);
    }

    @Test(description = "Place an order with empty security code")
    @Description("Verify that an appropriate error message is displayed when trying to place an order with an empty security code in the payment details.")
    @Severity(SeverityLevel.CRITICAL)
    public void placeOrderWithEmptySecurityCodeTest() {
        new ShippingPage(driver)
                .clickToPaymentButton()
                .fillPaymentDetails(cardFullName, cardNumber, expirationDate, "")
                .clickReviewOrder()
                .verifySecurityCodeError(invalidValueMessage);
    }
}
