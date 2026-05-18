package com.mydemo.Tests;

import com.mydemo.BaseTest;
import com.mydemo.drivers.GUIDriver;
import com.mydemo.drivers.UITest;
import com.mydemo.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Locale;

@Owner("Abdelrahman Fahmy")
@Epic("UI Tests")
@Feature("End-to-End Tests")
@UITest
public class E2ETest extends BaseTest {

    // Variables

    // Tests
    @BeforeClass
    public void setup() {
        driver = new GUIDriver();
    }

    @BeforeMethod
    public void preCondition() {
        driver.androidDevice().openApp(appPackage);
    }

    @Test(description = "E2E test for purchasing a single product")
    @Description("This test covers the entire flow of purchasing a single product, from login to order confirmation.")
    @Severity(SeverityLevel.BLOCKER)
    public void E2ESingleProductPurchaseTest() {
        new ProductsPage(driver)
                .openSideBarMenu()
                .clickLoginButton()
                .login(username, password)
                .sortProductsByPriceAscending()
                .verifyProductsAreSortedByPriceAscending()
                .verifyProductPrice(product1Name, product1Price)
                .viewProduct(product1Name)
                .verifyProductName(product1Name)
                .verifyProductPrice(product1Price)
                .verifyProductCount("1")
                .clickAddToCart()
                .verifyCartBadgeCountIsCorrect("1")
                .navigateToCart()
                .verifyProductInCart(product1Name, product1Price, "1")
                .verifyTotalItemsCountIsCorrect()
                .verifyTotalPriceIsCorrect()
                .proceedToCheckout()
                .fillShippingForm(fullName, address, city, country, zipCode)
                .clickToPaymentButton()
                .fillPaymentDetails(cardFullName, cardNumber, expirationDate, securityCode)
                .clickReviewOrder()
                .verifyTotalPrice(product1Price)
                .verifyTotalAmountOfProducts("1")
                .verifyShippingDetailsIsCorrect(fullName, address, city, country, zipCode)
                .verifyPaymentDetailsIsCorrect(cardFullName, cardNumber, expirationDate)
                .clickPlaceOrder()
                .verifyOrderSuccess()
                .openSideBarMenu()
                .clickLogoutButton()
                .clickConfirmLogoutButton()
                .verifyLogoutSuccessMessageIsDisplayed();
    }

    @Test(description = "E2E test for purchasing a single product with amount")
    @Description("This test covers the entire flow of purchasing a single product with a specific amount, from login to order confirmation.")
    @Severity(SeverityLevel.CRITICAL)
    public void E2ESingleProductWithAmountPurchaseTest() {
        String totalPrice = String.valueOf(Double.parseDouble(product2Price) * 2);
        new ProductsPage(driver)
                .openSideBarMenu()
                .clickLoginButton()
                .login(username, password)
                .sortProductsByPriceDescending()
                .verifyProductsAreSortedByPriceDescending()
                .verifyProductPrice(product2Name, product2Price)
                .viewProduct(product2Name)
                .verifyProductName(product2Name)
                .verifyProductPrice(product2Price)
                .increaseProductCount()
                .increaseProductCount()
                .verifyProductCount("3")
                .clickAddToCart()
                .verifyCartBadgeCountIsCorrect("3")
                .navigateToCart()
                .verifyProductInCart(product2Name, product2Price, "3")
                .decreaseProductCount(product2Name)
                .verifyProductInCart(product2Name, product2Price, "2")
                .verifyTotalItemsCountIsCorrect()
                .verifyTotalPriceIsCorrect()
                .proceedToCheckout()
                .fillShippingForm(fullName, address, city, country, zipCode)
                .clickToPaymentButton()
                .fillPaymentDetails(cardFullName, cardNumber, expirationDate, securityCode)
                .clickReviewOrder()
                .verifyTotalPrice(totalPrice)
                .verifyTotalAmountOfProducts("2")
                .verifyShippingDetailsIsCorrect(fullName, address, city, country, zipCode)
                .verifyPaymentDetailsIsCorrect(cardFullName, cardNumber, expirationDate)
                .clickPlaceOrder()
                .verifyOrderSuccess()
                .openSideBarMenu()
                .clickLogoutButton()
                .clickConfirmLogoutButton()
                .verifyLogoutSuccessMessageIsDisplayed();
    }

    @Test(description = "E2E test for purchasing multiple products with different amounts")
    @Description("This test covers the entire flow of purchasing multiple products with different amounts, from login to order confirmation.")
    @Severity(SeverityLevel.BLOCKER)
    public void E2EMultipleProductsPurchaseTest() {
        String totalPrice = String.format(Locale.US, "%.2f", (Double.parseDouble(product2Price) * 2) + (Double.parseDouble(product3Price) * 2));
        new ProductsPage(driver)
                .openSideBarMenu()
                .clickLoginButton()
                .login(username, password)
                .sortProductsByNameDescending()
                .verifyProductsAreSortedByNameDescending()
                .verifyProductPrice(product2Name, product2Price)
                .verifyProductPrice(product3Name, product3Price)
                .viewProduct(product2Name)
                .verifyProductName(product2Name)
                .verifyProductPrice(product2Price)
                .increaseProductCount()
                .increaseProductCount()
                .verifyProductCount("3")
                .clickAddToCart()
                .verifyCartBadgeCountIsCorrect("3")
                .navigateBack()
                .viewProduct(product3Name)
                .verifyProductName(product3Name)
                .verifyProductPrice(product3Price)
                .increaseProductCount()
                .verifyProductCount("2")
                .clickAddToCart()
                .verifyCartBadgeCountIsCorrect("5")
                .navigateToCart()
                .verifyProductInCart(product2Name, product2Price, "3")
                .decreaseProductCount(product2Name)
                .verifyProductInCart(product2Name, product2Price, "2")
                .verifyProductInCart(product3Name, product3Price, "2")
                .verifyTotalItemsCountIsCorrect()
                .verifyTotalPriceIsCorrect()
                .proceedToCheckout()
                .fillShippingForm(fullName, address, city, country, zipCode)
                .clickToPaymentButton()
                .fillPaymentDetails(cardFullName, cardNumber, expirationDate, securityCode)
                .clickReviewOrder()
                .verifyTotalPrice(totalPrice)
                .verifyTotalAmountOfProducts("4")
                .verifyShippingDetailsIsCorrect(fullName, address, city, country, zipCode)
                .verifyPaymentDetailsIsCorrect(cardFullName, cardNumber, expirationDate)
                .clickPlaceOrder()
                .verifyOrderSuccess()
                .openSideBarMenu()
                .clickLogoutButton()
                .clickConfirmLogoutButton()
                .verifyLogoutSuccessMessageIsDisplayed();
    }

    @Test(description = "E2E test for purchasing a single product with login during checkout")
    @Description("This test covers the entire flow of purchasing a single product with login during checkout, from adding the product to the cart to order confirmation.")
    @Severity(SeverityLevel.CRITICAL)
    public void E2ECompletePurchaseFlow_LoginDuringCheckoutTest() {
        new ProductsPage(driver)
                .verifyProductsAreSortedByNameAscending()
                .verifyProductPrice(product1Name, product1Price)
                .viewProduct(product1Name)
                .verifyProductName(product1Name)
                .verifyProductPrice(product1Price)
                .verifyProductCount("1")
                .clickAddToCart()
                .verifyCartBadgeCountIsCorrect("1")
                .navigateToCart()
                .verifyProductInCart(product1Name, product1Price, "1")
                .verifyTotalItemsCountIsCorrect()
                .verifyTotalPriceIsCorrect()
                .proceedToCheckout()
                .loginPage()
                .login(username, password)
                .shippingPage()
                .fillShippingForm(fullName, address, city, country, zipCode)
                .clickToPaymentButton()
                .fillPaymentDetails(cardFullName, cardNumber, expirationDate, securityCode)
                .clickReviewOrder()
                .verifyTotalPrice(product1Price)
                .verifyTotalAmountOfProducts("1")
                .verifyShippingDetailsIsCorrect(fullName, address, city, country, zipCode)
                .verifyPaymentDetailsIsCorrect(cardFullName, cardNumber, expirationDate)
                .clickPlaceOrder()
                .verifyOrderSuccess()
                .openSideBarMenu()
                .clickLogoutButton()
                .clickConfirmLogoutButton()
                .verifyLogoutSuccessMessageIsDisplayed();
    }
}