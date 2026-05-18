package com.mydemo.pages;

import com.mydemo.drivers.GUIDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AppBarPage {

    // Variables
    private final GUIDriver driver;

    // Constructor
    public AppBarPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By sidebarMenuButton = AppiumBy.accessibilityId("open menu");
    private final By sortButton = AppiumBy.accessibilityId("sort button");
    private final By activeSortOption = AppiumBy.accessibilityId("active option");
    private final By ascendingNameSortOption = AppiumBy.accessibilityId("nameAsc");
    private final By descendingNameSortOption = AppiumBy.accessibilityId("nameDesc");
    private final By ascendingPriceSortOption = AppiumBy.accessibilityId("priceAsc");
    private final By descendingPriceSortOption = AppiumBy.accessibilityId("priceDesc");
    private final By cartButton = AppiumBy.accessibilityId("cart badge");
    private final By cartBadgeCount = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='cart badge']/android.widget.TextView");

    // Dynamic Locators

    // Actions
    @Step("Open the side bar menu")
    public SideBarPage openSideBarMenu() {
        driver.element().click(sidebarMenuButton);
        return new SideBarPage(driver);
    }

    @Step("Navigate to the cart page")
    public CartPage navigateToCart() {
        driver.element().click(cartButton);
        return new CartPage(driver);
    }

    @Step("Click on the sort button")
    public AppBarPage clickSortButton() {
        driver.element().click(sortButton);
        return this;
    }

    @Step("Sort products by name in ascending order")
    public ProductsPage sortProductsByNameAscending() {
        driver.element().click(sortButton);
        driver.element().click(ascendingNameSortOption);
        return new ProductsPage(driver);
    }

    @Step("Sort products by name in descending order")
    public ProductsPage sortProductsByNameDescending() {
        driver.element().click(sortButton);
        driver.element().click(descendingNameSortOption);
        return new ProductsPage(driver);
    }

    @Step("Sort products by price in ascending order")
    public ProductsPage sortProductsByPriceAscending() {
        driver.element().click(sortButton);
        driver.element().click(ascendingPriceSortOption);
        return new ProductsPage(driver);
    }

    @Step("Sort products by price in descending order")
    public ProductsPage sortProductsByPriceDescending() {
        driver.element().click(sortButton);
        driver.element().click(descendingPriceSortOption);
        return new ProductsPage(driver);
    }

    private String getCartBadgeCount() {
        return driver.element().getText(cartBadgeCount);
    }

    // Assertions
    @Step("Verify default sort option is selected")
    public AppBarPage verifyDefaultSortOptionIsSelected() {
        driver.softAssert().isElementVisible(ascendingNameSortOption);
        WebElement activeSort = driver.element().findElement(ascendingNameSortOption);
        // Check if the active icon is displayed for the default sort option
        boolean isActive = activeSort.findElement(activeSortOption).isDisplayed();
        driver.softAssert().assertTrue(isActive, "Default sort option is not selected");
        return this;
    }

    @Step("Verify cart badge count is: {expectedCount}")
    public ProductPage verifyCartBadgeCountIsCorrect(String expectedCount) {
        driver.softAssert().assertEquals(getCartBadgeCount(), expectedCount, "Cart badge count does not match");
        return new ProductPage(driver);
    }
}
