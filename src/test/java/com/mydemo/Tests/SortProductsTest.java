package com.mydemo.Tests;

import com.mydemo.BaseTest;
import com.mydemo.drivers.GUIDriver;
import com.mydemo.drivers.UITest;
import com.mydemo.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Owner("Abdelrahman Fahmy")
@Epic("UI Tests")
@Feature("Sort Products")
@UITest
public class SortProductsTest extends BaseTest {

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

    @Test(description = "Verify default ascending product sorting")
    @Description("Verify that products are sorted by name in ascending order by default")
    @Severity(SeverityLevel.NORMAL)
    public void verifyProductsAreSortedByNameAscendingByDefaultTest() {
        new ProductsPage(driver)
                .clickSortButton()
                .verifyDefaultSortOptionIsSelected();
    }

    @Test(description = "Sort products by name in ascending order")
    @Description("Verify that products can be sorted by name in ascending order")
    @Severity(SeverityLevel.NORMAL)
    public void sortProductsByNameAscendingTest() {
        new ProductsPage(driver)
                .sortProductsByNameAscending()
                .verifyProductsAreSortedByNameAscending();
    }

    @Test(description = "Sort products by name in descending order")
    @Description("Verify that products can be sorted by name in descending order")
    @Severity(SeverityLevel.NORMAL)
    public void sortProductsByNameDescendingTest() {
        new ProductsPage(driver)
                .sortProductsByNameDescending()
                .verifyProductsAreSortedByNameDescending();
    }

    @Test(description = "Sort products by price in ascending order")
    @Description("Verify that products can be sorted by price in ascending order")
    @Severity(SeverityLevel.NORMAL)
    public void sortProductsByPriceAscendingTest() {
        new ProductsPage(driver)
                .sortProductsByPriceAscending()
                .verifyProductsAreSortedByPriceAscending();
    }

    @Test(description = "Sort products by price in descending order")
    @Description("Verify that products can be sorted by price in descending order")
    @Severity(SeverityLevel.NORMAL)
    public void sortProductsByPriceDescendingTest() {
        new ProductsPage(driver)
                .sortProductsByPriceDescending()
                .verifyProductsAreSortedByPriceDescending();
    }
}