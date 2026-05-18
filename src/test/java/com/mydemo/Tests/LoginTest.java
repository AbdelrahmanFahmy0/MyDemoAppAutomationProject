package com.mydemo.Tests;

import com.mydemo.BaseTest;
import com.mydemo.drivers.GUIDriver;
import com.mydemo.drivers.UITest;
import com.mydemo.pages.LoginPage;
import com.mydemo.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Owner("Abdelrahman Fahmy")
@Epic("UI Tests")
@Feature("Login")
@UITest
public class LoginTest extends BaseTest {

    // Variables
    private final String lockedOutUsername = authData.getJsonData("lockedUsername");
    private final String invalidCredentialsErrorMessage = authData.getJsonData("messages.invalidCredentialsErrorMessage");
    private final String emptyUsernameErrorMessage = authData.getJsonData("messages.emptyUsernameErrorMessage");
    private final String emptyPasswordErrorMessage = authData.getJsonData("messages.emptyPasswordErrorMessage");
    private final String accountLockedErrorMessage = authData.getJsonData("messages.accountLockedErrorMessage");

    // Tests
    @BeforeClass
    public void setup() {
        driver = new GUIDriver();
    }

    @BeforeMethod
    public void preCondition() {
        driver.androidDevice().openApp(appPackage);
        new ProductsPage(driver).openSideBarMenu().clickLoginButton();
    }

    @Test(description = "Login with valid credentials")
    @Description("Verify that a user can successfully log in using valid credentials.")
    @Severity(SeverityLevel.BLOCKER)
    public void loginWithValidCredentialsTest() {
        new LoginPage(driver)
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton()
                .verifyProductsPageIsDisplayed();
    }

    @Test(description = "Login with invalid password")
    @Description("Verify that an error message is displayed when logging in with an invalid password.")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithInvalidPasswordTest() {
        new LoginPage(driver)
                .enterUsername(username)
                .enterPassword("wrongpassword")
                .clickLoginButton()
                .loginPage()
                .verifyErrorMessageIsCorrect(invalidCredentialsErrorMessage);
    }

    @Test(description = "Login with invalid username")
    @Description("Verify that an error message is displayed when logging in with an invalid username.")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithInvalidUsernameTest() {
        new LoginPage(driver)
                .enterUsername("wrongusername")
                .enterPassword(password)
                .clickLoginButton()
                .loginPage()
                .verifyErrorMessageIsCorrect(invalidCredentialsErrorMessage);
    }

    @Test(description = "Login with empty credentials")
    @Description("Verify that a username validation message is displayed when credentials are empty.")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithEmptyCredentialsTest() {
        new LoginPage(driver)
                .clickLoginButton()
                .loginPage()
                .verifyUsernameErrorIsCorrect(emptyUsernameErrorMessage);
    }

    @Test(description = "Login with empty username")
    @Description("Verify that a username validation message is displayed when the username field is empty.")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithEmptyUsernameTest() {
        new LoginPage(driver)
                .enterPassword(password)
                .clickLoginButton()
                .loginPage()
                .verifyUsernameErrorIsCorrect(emptyUsernameErrorMessage);
    }

    @Test(description = "Login with empty password")
    @Description("Verify that a password validation message is displayed when the password field is empty.")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithEmptyPasswordTest() {
        new LoginPage(driver)
                .enterUsername(username)
                .clickLoginButton()
                .loginPage()
                .verifyPasswordErrorIsCorrect(emptyPasswordErrorMessage);
    }

    @Test(description = "Login with locked out user")
    @Description("Verify that an error message is displayed when logging in with a locked out account.")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithLockedOutUserTest() {
        new LoginPage(driver)
                .enterUsername(lockedOutUsername)
                .enterPassword(password)
                .clickLoginButton()
                .loginPage()
                .verifyErrorMessageIsCorrect(accountLockedErrorMessage);
    }
}