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
@Feature("Logout")
@UITest
public class LogoutTest extends BaseTest {

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

    @Test(description = "Logout from the application")
    @Description("Verify that the user can successfully log out from the application and is redirected to the login page.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyUserCanLogoutSuccessfullyTest() {
        new ProductsPage(driver)
                .openSideBarMenu()
                .clickLoginButton()
                .login(username, password)
                .openSideBarMenu()
                .clickLogoutButton()
                .clickConfirmLogoutButton()
                .verifyLogoutSuccessMessageIsDisplayed();
    }

    @Test(description = "Cancel logout from the application")
    @Description("Verify that the user can cancel the logout process and remain on the current page.")
    @Severity(SeverityLevel.MINOR)
    public void verifyUserCanCancelLogoutSuccessfullyTest() {
        new ProductsPage(driver)
                .openSideBarMenu()
                .clickLoginButton()
                .login(username, password)
                .openSideBarMenu()
                .clickLogoutButton()
                .clickCancelLogoutButton()
                .verifyLogoutButtonIsDisplayed();
    }

    @Test(description = "Checkout without login")
    @Description("Verify that the user is prompted to log in when trying to proceed to checkout without being logged in.")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyUserCannotCheckoutWithoutLoginTest() {
        new ProductsPage(driver)
                .viewProduct(product1Name)
                .clickAddToCart()
                .navigateToCart()
                .proceedToCheckout()
                .loginPage()
                .verifyLoginPageIsDisplayed();
    }
}
