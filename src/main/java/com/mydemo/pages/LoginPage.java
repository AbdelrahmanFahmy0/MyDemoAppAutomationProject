package com.mydemo.pages;

import com.mydemo.drivers.GUIDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage {

    // Variables
    private final GUIDriver driver;

    // Constructor
    public LoginPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By loginPageTitle = AppiumBy.xpath("(//android.widget.TextView[@text='Login'])[1]");
    private final By usernameField = AppiumBy.accessibilityId("Username input field");
    private final By passwordField = AppiumBy.accessibilityId("Password input field");
    private final By loginButton = AppiumBy.accessibilityId("Login button");
    private final By usernameError = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Username-error-message']/android.widget.TextView");
    private final By passwordError = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Password-error-message']/android.widget.TextView");
    private final By errorMessage = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='generic-error-message']/android.widget.TextView");
    private final By logoutSuccessMessage = AppiumBy.id("android:id/alertTitle");

    // Dynamic Locators

    // Actions
    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        driver.element().type(usernameField, username);
        return this;
    }

    @Step("Enter password: {password}")
    public LoginPage enterPassword(String password) {
        driver.element().type(passwordField, password);
        return this;
    }

    @Step("Click on Login Button")
    public ProductsPage clickLoginButton() {
        driver.element().click(loginButton);
        return new ProductsPage(driver);
    }

    @Step("Login with username: {username} and password: {password}")
    public ProductsPage login(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
    }

    // Assertions
    @Step("Verify Login Page is displayed")
    public LoginPage verifyLoginPageIsDisplayed() {
        driver.softAssert().isElementVisible(loginPageTitle);
        return this;
    }

    @Step("Verify logout success message is displayed")
    public LoginPage verifyLogoutSuccessMessageIsDisplayed() {
        driver.softAssert().isElementVisible(logoutSuccessMessage);
        return this;
    }

    @Step("Verify error message is correct: {expectedMessage}")
    public LoginPage verifyErrorMessageIsCorrect(String expectedMessage) {
        String actualMessage = driver.element().getText(errorMessage);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "Error message is incorrect");
        return this;
    }

    @Step("Verify username error message is correct: {expectedMessage}")
    public LoginPage verifyUsernameErrorIsCorrect(String expectedMessage) {
        String actualMessage = driver.element().getText(usernameError);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "Username error message is incorrect");
        return this;
    }

    @Step("Verify password error message is correct: {expectedMessage}")
    public LoginPage verifyPasswordErrorIsCorrect(String expectedMessage) {
        String actualMessage = driver.element().getText(passwordError);
        driver.softAssert().assertEquals(actualMessage, expectedMessage, "Password error message is incorrect");
        return this;
    }
}
