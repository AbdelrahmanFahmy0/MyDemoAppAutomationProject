package com.mydemo.pages;

import com.mydemo.drivers.GUIDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SideBarPage {

    // Variables
    private final GUIDriver driver;

    // Constructor
    public SideBarPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By loginButton = AppiumBy.accessibilityId("menu item log in");
    private final By logoutButton = AppiumBy.accessibilityId("menu item log out");
    private final By confirmLogoutButton = AppiumBy.id("android:id/button1");
    private final By cancelLogoutButton = AppiumBy.id("android:id/button2");

    // Dynamic Locators

    // Actions
    @Step("Click on Login Button")
    public LoginPage clickLoginButton() {
        driver.element().click(loginButton);
        return new LoginPage(driver);
    }

    @Step("Click on Logout Button")
    public SideBarPage clickLogoutButton() {
        driver.element().click(logoutButton);
        return this;
    }

    @Step("Click on Confirm Logout Button")
    public LoginPage clickConfirmLogoutButton() {
        driver.element().click(confirmLogoutButton);
        return new LoginPage(driver);
    }

    @Step("Click on Cancel Logout Button")
    public SideBarPage clickCancelLogoutButton() {
        driver.element().click(cancelLogoutButton);
        return this;
    }

    // Assertions
    @Step("Verify Logout Button is Displayed")
    public SideBarPage verifyLogoutButtonIsDisplayed() {
        driver.softAssert().isElementVisible(logoutButton);
        return this;
    }
}