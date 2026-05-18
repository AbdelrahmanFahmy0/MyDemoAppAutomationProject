package com.mydemo.Tests;

import com.mydemo.BaseTest;
import com.mydemo.drivers.GUIDriver;
import com.mydemo.drivers.UITest;
import com.mydemo.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Owner("Abdelrahman Fahmy")
@Epic("UI Tests")
@Feature("Add To Cart")
@UITest
public class AddToCartTest extends BaseTest {

    // Variables

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
                .login(username, password);
    }

    @Test(description = "Add a product to the cart")
    @Description("Verify that a product can be added to the cart successfully.")
    @Severity(SeverityLevel.BLOCKER)
    public void addProductToCartTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .clickAddToCart()
                .verifyCartBadgeCountIsCorrect("1")
                .navigateToCart()
                .verifyProductInCart(product1Name, product1Price, "1");
    }

    @Test(description = "Add multiple quantities of a product to the cart")
    @Description("Verify that multiple quantities of a product can be added to the cart successfully.")
    @Severity(SeverityLevel.CRITICAL)
    public void addAmountOfProductToCartTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .increaseProductCount()
                .increaseProductCount()
                .clickAddToCart()
                .verifyCartBadgeCountIsCorrect("3")
                .navigateToCart()
                .verifyProductInCart(product1Name, product1Price, "3");
    }

    @Test(description = "Add multiple products to the cart")
    @Description("Verify that multiple products can be added to the cart successfully.")
    @Severity(SeverityLevel.CRITICAL)
    public void addMultipleProductsToCartTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .clickAddToCart()
                .navigateBack()
                .viewProduct(product2Name)
                .clickAddToCart()
                .verifyCartBadgeCountIsCorrect("2")
                .navigateToCart()
                .verifyProductInCart(product1Name, product1Price, "1")
                .verifyProductInCart(product2Name, product2Price, "1");
    }

    @Test(description = "Increase product count in the cart")
    @Description("Verify that the product count can be increased in the cart successfully.")
    @Severity(SeverityLevel.CRITICAL)
    public void increaseProductCountInCartTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .clickAddToCart()
                .navigateToCart()
                .increaseProductCount(product1Name)
                .verifyProductInCart(product1Name, product1Price, "2");
    }

    @Test(description = "Decrease product count in the cart")
    @Description("Verify that the product count can be decreased in the cart successfully.")
    @Severity(SeverityLevel.CRITICAL)
    public void decreaseProductCountInCartTest() {
        new ProductsPage(driver)
                .viewProduct(product2Name)
                .increaseProductCount()
                .clickAddToCart()
                .navigateToCart()
                .decreaseProductCount(product2Name)
                .verifyProductInCart(product2Name, product2Price, "1");
    }

    @Test(description = "Remove a product from the cart")
    @Description("Verify that a product can be removed from the cart successfully.")
    @Severity(SeverityLevel.CRITICAL)
    public void removeProductFromCartTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .clickAddToCart()
                .navigateBack()
                .viewProduct(product2Name)
                .clickAddToCart()
                .navigateToCart()
                .removeProductFromCart(product1Name)
                .verifyProductIsRemoved(product1Name);
    }

    @Test(description = "Remove all products from the cart")
    @Description("Verify that all products can be removed from the cart successfully.")
    @Severity(SeverityLevel.NORMAL)
    public void removeAllProductsFromCartTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .clickAddToCart()
                .navigateToCart()
                .removeProductFromCart(product1Name)
                .verifyCartIsEmpty();
    }

    @Test(description = "Remove all products from the cart by decrementing the count")
    @Description("Verify that all products can be removed from the cart by decrementing the count successfully.")
    @Severity(SeverityLevel.NORMAL)
    public void removeAllProductsByDecrementTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .clickAddToCart()
                .navigateToCart()
                .decreaseProductCount(product1Name)
                .verifyCartIsEmpty();
    }

    @Test(description = "Verify total price in the cart is correct")
    @Description("Verify that the total price in the cart is calculated correctly based on the products and their quantities.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyTotalPriceInCartTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .increaseProductCount()
                .clickAddToCart()
                .navigateBack()
                .viewProduct(product2Name)
                .clickAddToCart()
                .navigateToCart()
                .increaseProductCount(product2Name)
                .verifyTotalPriceIsCorrect();
    }

    @Test(description = "Verify total items count in the cart is correct")
    @Description("Verify that the total items count in the cart is calculated correctly based on the products and their quantities.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyTotalItemsCountInCartTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .increaseProductCount()
                .clickAddToCart()
                .navigateBack()
                .viewProduct(product2Name)
                .clickAddToCart()
                .navigateToCart()
                .increaseProductCount(product2Name)
                .verifyTotalItemsCountIsCorrect();
    }

    @Test(description = "Verify cart is empty by default")
    @Description("Verify that the cart is empty by default when the user opens it for the first time.")
    @Severity(SeverityLevel.MINOR)
    public void verifyCartIsEmptyByDefaultTest() {
        new ProductsPage(driver)
                .navigateToCart()
                .verifyCartIsEmpty();
    }
}