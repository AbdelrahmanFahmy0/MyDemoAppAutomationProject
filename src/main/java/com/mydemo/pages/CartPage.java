package com.mydemo.pages;

import com.mydemo.drivers.GUIDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;

public class CartPage extends AppBarPage {

    // Variables
    private final GUIDriver driver;

    // Constructor
    public CartPage(GUIDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators
    private final By noProductsMessage = AppiumBy.xpath("//android.widget.TextView[@text='No Items']");
    private final By totalItems = AppiumBy.xpath("//android.widget.TextView[@content-desc='total number']");
    private final By totalPrice = AppiumBy.xpath("//android.widget.TextView[@content-desc='total price']");
    private final By productItem = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='product row']");
    private final By checkoutButton = AppiumBy.accessibilityId("Proceed To Checkout button");

    // Dynamic Locators
    private By product(String productName) {
        return AppiumBy.xpath("//android.view.ViewGroup[@content-desc='product row']/android.widget.TextView[@text='" + productName + "']");
    }

    private By productPrice(String productName) {
        String productNameLocator = product(productName).toString().replace("By.xpath: ", "");
        return AppiumBy.xpath(productNameLocator + "/following-sibling::android.widget.TextView[@content-desc='product price']");
    }

    private By productCount(String productName) {
        String productNameLocator = product(productName).toString().replace("By.xpath: ", "");
        return AppiumBy.xpath(productNameLocator + "/following-sibling::android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView");
    }

    private By increaseProductCountButton(String productName) {
        String productNameLocator = product(productName).toString().replace("By.xpath: ", "");
        return AppiumBy.xpath(productNameLocator + "/following-sibling::android.view.ViewGroup[@content-desc='counter plus button']");
    }

    private By decreaseProductCountButton(String productName) {
        String productNameLocator = product(productName).toString().replace("By.xpath: ", "");
        return AppiumBy.xpath(productNameLocator + "/following-sibling::android.view.ViewGroup[@content-desc='counter minus button']");
    }

    private By removeProductButton(String productName) {
        String productNameLocator = product(productName).toString().replace("By.xpath: ", "");
        return AppiumBy.xpath(productNameLocator + "/following-sibling::android.view.ViewGroup[@content-desc='remove item']");
    }

    // Actions
    private String getProductPrice(String productName) {
        return driver.element().getText(productPrice(productName)).replace("$", "");
    }

    private String getProductCount(String productName) {
        return driver.element().getText(productCount(productName));
    }

    private String getTotalItemsCount() {
        return driver.element().getText(totalItems).split(" ")[0];
    }

    private String getTotalPrice() {
        return driver.element().getText(totalPrice).replace("$", "");
    }

    @Step("Calculate total price based on products in cart")
    private String calculateTotalPrice() {
        List<WebElement> products = driver.element().findElements(productItem);
        double expectedTotalPrice = 0.0;
        for (WebElement product : products) {
            String productName = product.findElement(AppiumBy.accessibilityId("product label")).getText();
            double price = Double.parseDouble(getProductPrice(productName));
            int count = Integer.parseInt(getProductCount(productName));
            expectedTotalPrice += price * count;
        }
        return String.format(Locale.US, "%.2f", expectedTotalPrice);
    }

    @Step("Calculate total items count based on products in cart")
    private String calculateTotalItems() {
        List<WebElement> products = driver.element().findElements(productItem);
        int totalItemsCount = 0;
        for (WebElement product : products) {
            String productName = product.findElement(AppiumBy.accessibilityId("product label")).getText();
            int count = Integer.parseInt(getProductCount(productName));
            totalItemsCount += count;
        }
        return String.valueOf(totalItemsCount);
    }

    @Step("Increase product count for product: {productName}")
    public CartPage increaseProductCount(String productName) {
        driver.element().click(increaseProductCountButton(productName));
        return this;
    }

    @Step("Decrease product count for product: {productName}")
    public CartPage decreaseProductCount(String productName) {
        driver.element().click(decreaseProductCountButton(productName));
        return this;
    }

    @Step("Remove product: {productName} from cart")
    public CartPage removeProductFromCart(String productName) {
        driver.element().click(removeProductButton(productName));
        return this;
    }

    @Step("Proceed to checkout")
    public ShippingPage proceedToCheckout() {
        driver.element().click(checkoutButton);
        return new ShippingPage(driver);
    }

    // Assertions
    @Step("Verify product details in cart for product: {productName}")
    public CartPage verifyProductInCart(String productName, String productPrice, String productCount) {
        driver.softAssert().isElementVisible(product(productName));
        String actualPrice = getProductPrice(productName);
        String actualCount = getProductCount(productName);
        driver.softAssert().assertEquals(actualPrice, productPrice, "Product price in cart is incorrect");
        driver.softAssert().assertEquals(actualCount, productCount, "Product count in cart is incorrect");
        return this;
    }

    @Step("Verify cart is empty")
    public CartPage verifyCartIsEmpty() {
        driver.softAssert().isElementVisible(noProductsMessage);
        return this;
    }

    @Step("Verify product: {productName} is removed from cart")
    public CartPage verifyProductIsRemoved(String productName) {
        driver.softAssert().isElementHidden(product(productName));
        return this;
    }

    @Step("Verify total price in cart is correct")
    public CartPage verifyTotalPriceIsCorrect() {
        String actualTotalPrice = getTotalPrice();
        String expectedTotalPrice = calculateTotalPrice();
        driver.softAssert().assertEquals(actualTotalPrice, expectedTotalPrice, "Total price in cart is incorrect");
        return this;
    }

    @Step("Verify total items count in cart is correct")
    public CartPage verifyTotalItemsCountIsCorrect() {
        String actualTotalItemsCount = getTotalItemsCount();
        String expectedTotalItemsCount = calculateTotalItems();
        driver.softAssert().assertEquals(actualTotalItemsCount, expectedTotalItemsCount, "Total items count in cart is incorrect");
        return this;
    }
}
