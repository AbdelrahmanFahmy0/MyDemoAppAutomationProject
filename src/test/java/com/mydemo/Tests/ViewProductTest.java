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
@Feature("View Product")
@UITest
public class ViewProductTest extends BaseTest {

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

    @Test(description = "Verify product price in products page")
    @Description("Verify that the product price is displayed correctly in the products page.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyProductPriceInProductsPageTest() {
        new ProductsPage(driver)
                .verifyProductPrice(product1Name, product1Price);
    }

    @Test(description = "Verify product name in product details page")
    @Description("Verify that the product name is displayed correctly in the product details page.")
    @Severity(SeverityLevel.MINOR)
    public void verifyProductNameTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .verifyProductName(product1Name);
    }

    @Test(description = "Verify product price in product details page")
    @Description("Verify that the product price is displayed correctly in the product details page.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyProductPriceTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .verifyProductPrice(product1Price);
    }

    @Test(description = "Verify product count is 1 by default")
    @Description("Verify that the product count is 1 by default when viewing a product details page.")
    @Severity(SeverityLevel.MINOR)
    public void verifyProductCountIs1ByDefaultTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .verifyProductCount("1");
    }

    @Test(description = "Increase product count")
    @Description("Verify that the user can increase the product count and it is updated correctly.")
    @Severity(SeverityLevel.CRITICAL)
    public void increaseProductCountTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .increaseProductCount()
                .verifyProductCount("2");
    }

    @Test(description = "Decrease product count")
    @Description("Verify that the user can decrease the product count and it is updated correctly.")
    @Severity(SeverityLevel.CRITICAL)
    public void decreaseProductCountTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .decreaseProductCount()
                .verifyProductCount("0");
    }

    @Test(description = "Verify Add To Cart button is disabled when product count is 0")
    @Description("Verify that the Add To Cart button is disabled when the product count is decreased to 0 in the product details page.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyAddToCartButtonIsDisabledTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .decreaseProductCount()
                .verifyAddToCartButtonIsDisabled();
    }
}
