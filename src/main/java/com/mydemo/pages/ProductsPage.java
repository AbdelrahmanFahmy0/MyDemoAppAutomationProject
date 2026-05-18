package com.mydemo.pages;

import com.mydemo.drivers.GUIDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductsPage extends AppBarPage {

    // Variables
    private final GUIDriver driver;

    // Constructor
    public ProductsPage(GUIDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators
    private final By productsTitle = AppiumBy.accessibilityId("container header");

    // Dynamic Locators
    private By product(String productName) {
        return AppiumBy.xpath("//android.widget.TextView[@content-desc='store item text' and @text='" + productName + "']");
    }

    private By productPrice(String productName) {
        return AppiumBy.xpath("//android.widget.TextView[@content-desc='store item text' and @text='" + productName + "']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView[@content-desc='store item price']");
    }

    private By productNameByIndex(int index) {
        return AppiumBy.xpath("(//android.widget.TextView[@content-desc='store item text'])[" + index + "]");
    }

    private By productPriceByIndex(int index) {
        return AppiumBy.xpath("(//android.widget.TextView[@content-desc='store item price'])[" + index + "]");
    }

    // Actions
    public LoginPage loginPage() {
        return new LoginPage(driver);
    }

    public ShippingPage shippingPage() {
        return new ShippingPage(driver);
    }

    @Step("Get product name by index: {index}")
    private String getProductNameByIndex(int index) {
        return driver.element().getText(productNameByIndex(index));
    }

    @Step("Get product price by index: {index}")
    private double getProductPriceByIndex(int index) {
        String priceText = driver.element().getText(productPriceByIndex(index)).replace("$", "");
        return Double.parseDouble(priceText);
    }

    @Step("View product: {productName}")
    public ProductPage viewProduct(String productName) {
        driver.element().click(product(productName));
        return new ProductPage(driver);
    }

    // Assertions
    @Step("Verify Products Page is displayed")
    public ProductsPage verifyProductsPageIsDisplayed() {
        driver.softAssert().isElementVisible(productsTitle);
        return this;
    }

    @Step("Verify products are sorted by name in ascending order")
    public ProductsPage verifyProductsAreSortedByNameAscending() {
        String firstProductName = getProductNameByIndex(1);
        String secondProductName = getProductNameByIndex(2);
        boolean isSorted = firstProductName.compareTo(secondProductName) <= 0;
        driver.softAssert().assertTrue(isSorted, "Expected products to be sorted by name in ascending order, but found: " + firstProductName + " and " + secondProductName);
        return this;
    }

    @Step("Verify products are sorted by name in descending order")
    public ProductsPage verifyProductsAreSortedByNameDescending() {
        String firstProductName = getProductNameByIndex(1);
        String secondProductName = getProductNameByIndex(2);
        boolean isSorted = firstProductName.compareTo(secondProductName) >= 0;
        driver.softAssert().assertTrue(isSorted, "Expected products to be sorted by name in descending order, but found: " + firstProductName + " and " + secondProductName);
        return this;
    }

    @Step("Verify products are sorted by price in ascending order")
    public ProductsPage verifyProductsAreSortedByPriceAscending() {
        double firstProductPrice = getProductPriceByIndex(1);
        double secondProductPrice = getProductPriceByIndex(2);
        boolean isSorted = firstProductPrice <= secondProductPrice;
        driver.softAssert().assertTrue(isSorted, "Expected products to be sorted by price in ascending order, but found: " + firstProductPrice + " and " + secondProductPrice);
        return this;
    }

    @Step("Verify products are sorted by price in descending order")
    public ProductsPage verifyProductsAreSortedByPriceDescending() {
        double firstProductPrice = getProductPriceByIndex(1);
        double secondProductPrice = getProductPriceByIndex(2);
        boolean isSorted = firstProductPrice >= secondProductPrice;
        driver.softAssert().assertTrue(isSorted, "Expected products to be sorted by price in descending order, but found: " + firstProductPrice + " and " + secondProductPrice);
        return this;
    }

    @Step("Verify price of product: {productName}")
    public ProductsPage verifyProductPrice(String productName, String expectedPrice) {
        String priceText = driver.element().getText(productPrice(productName)).replace("$", "");
        boolean isPriceCorrect = Double.parseDouble(priceText) == Double.parseDouble(expectedPrice);
        driver.softAssert().assertTrue(isPriceCorrect, "Expected price for product '" + productName + "' to be: " + expectedPrice + ", but found: " + priceText);
        return this;
    }
}