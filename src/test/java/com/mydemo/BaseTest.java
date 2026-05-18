package com.mydemo;

import com.mydemo.drivers.AppiumDriverProvider;
import com.mydemo.drivers.GUIDriver;
import com.mydemo.utils.dataReader.JsonReader;
import com.mydemo.utils.dataReader.PropertyReader;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class BaseTest implements AppiumDriverProvider {

    // Variables
    protected GUIDriver driver;
    protected JsonReader authData = new JsonReader("auth-data");
    protected JsonReader productData = new JsonReader("product-data");
    protected JsonReader shippingData = new JsonReader("shipping-data");
    protected JsonReader paymentData = new JsonReader("payment-data");
    protected String appPackage = PropertyReader.getProperty("android.app.package");
    protected final String username = authData.getJsonData("username");
    protected final String password = authData.getJsonData("password");
    protected final String product1Name = productData.getJsonData("products[0].name");
    protected final String product1Price = productData.getJsonData("products[0].price");
    protected final String product2Name = productData.getJsonData("products[1].name");
    protected final String product2Price = productData.getJsonData("products[1].price");
    protected final String product3Name = productData.getJsonData("products[2].name");
    protected final String product3Price = productData.getJsonData("products[2].price");
    protected final String fullName = shippingData.getJsonData("fullName");
    protected final String address = shippingData.getJsonData("address");
    protected final String city = shippingData.getJsonData("city");
    protected final String country = shippingData.getJsonData("country");
    protected final String zipCode = shippingData.getJsonData("zipCode");
    protected final String cardFullName = paymentData.getJsonData("fullName");
    protected final String cardNumber = paymentData.getJsonData("cardNumber");
    protected final String expirationDate = paymentData.getJsonData("expiryDate");
    protected final String securityCode = paymentData.getJsonData("securityCode");

    // Getting Active AppiumDriver instance
    @Override
    public AppiumDriver getAppiumDriver() {
        return driver.get();
    }

    // Hooks
    @AfterMethod
    public void postCondition() {
        driver.androidDevice().clearAppData(appPackage);
        driver.androidDevice().closeApp(appPackage);
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}