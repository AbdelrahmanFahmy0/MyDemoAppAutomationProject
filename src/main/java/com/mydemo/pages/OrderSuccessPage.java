package com.mydemo.pages;

import com.mydemo.drivers.GUIDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class OrderSuccessPage extends AppBarPage {

    // Variables
    private final GUIDriver driver;

    // Constructor
    public OrderSuccessPage(GUIDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators
    private final By checkoutConfirmationHeader = AppiumBy.xpath("//android.widget.TextView[@text='Checkout Complete']");
    private final By orderConfirmationMessage = AppiumBy.xpath("//android.widget.TextView[@text='Thank you for your order']");

    // Dynamic Locators

    // Actions

    // Assertions
    @Step("Verify order is placed successfully")
    public OrderSuccessPage verifyOrderSuccess() {
        driver.softAssert().isElementVisible(checkoutConfirmationHeader);
        driver.softAssert().isElementVisible(orderConfirmationMessage);
        return this;
    }
}
