package com.mydemo.pages;

import com.mydemo.drivers.GUIDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductPage extends AppBarPage {

    // Variables
    private final GUIDriver driver;

    // Constructor
    public ProductPage(GUIDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators
    private final By productName = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='container header']/android.widget.TextView");
    private final By productPrice = AppiumBy.accessibilityId("product price");
    private final By plusButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='counter plus button']");
    private final By minusButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='counter minus button']");
    private final By productCount = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView");
    private final By addToCartButton = AppiumBy.accessibilityId("Add To Cart button");

    // Dynamic Locators

    // Actions
    private String getProductName() {
        return driver.element().getText(productName);
    }

    private String getProductPrice() {
        return driver.element().getText(productPrice).replace("$", "");
    }

    private String getProductCount() {
        return driver.element().getText(productCount);
    }

    @Step("Increase product count")
    public ProductPage increaseProductCount() {
        driver.element().click(plusButton);
        return this;
    }

    @Step("Decrease product count")
    public ProductPage decreaseProductCount() {
        driver.element().click(minusButton);
        return this;
    }

    @Step("Click Add To Cart button")
    public ProductPage clickAddToCart() {
        driver.element().click(addToCartButton);
        return this;
    }

    @Step("Navigate back to products page")
    public ProductsPage navigateBack() {
        driver.androidDevice().pressBack();
        return new ProductsPage(driver);
    }

    // Assertions
    @Step("Verify product name is: {expectedName}")
    public ProductPage verifyProductName(String expectedName) {
        driver.softAssert().assertEquals(getProductName(), expectedName, "Product name does not match");
        return this;
    }

    @Step("Verify product price is: {expectedPrice}")
    public ProductPage verifyProductPrice(String expectedPrice) {
        driver.softAssert().assertEquals(getProductPrice(), expectedPrice, "Product price does not match");
        return this;
    }

    @Step("Verify product count is: {expectedCount}")
    public ProductPage verifyProductCount(String expectedCount) {
        driver.softAssert().assertEquals(getProductCount(), expectedCount, "Product count does not match");
        return this;
    }

    @Step("Verify Add To Cart button is disabled")
    public ProductPage verifyAddToCartButtonIsDisabled() {
        boolean isEnabled = driver.element().isElementEnabled(addToCartButton);
        driver.softAssert().assertFalse(isEnabled, "Add To Cart button is enabled, but it should be disabled");
        return this;
    }
}