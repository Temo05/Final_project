package com.automationexercise.ui;

import com.automationexercise.base.BaseUITest;
import com.automationexercise.pages.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GiorgiGegenavaTests extends BaseUITest {

    @Test(description = "Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    @Epic("User Interface")
    @Feature("Page Scrolling")
    @Story("Verify scroll up and down functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testVerifyScrollUpAndDown() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isHomePageVisible());

        homePage.scrollToBottom();

        Assert.assertTrue(homePage.isSubscriptionVisible());

        homePage.clickScrollUpArrow();

        Assert.assertTrue(homePage.isFullFledgedTextVisible());
    }

    @Test(description = "Test Case 11: Verify Subscription in Cart page")
    @Epic("User Management")
    @Feature("Newsletter")
    @Story("Subscribe from cart page")
    @Severity(SeverityLevel.NORMAL)
    public void testVerifySubscriptionInCartPage() {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.navigateToProductsPage();
        productsPage.clickAddToCartForFirstProduct();
        productsPage.clickViewCart();

        cartPage.subscribeWithEmail("gegenava.test@gmail.com");

        Assert.assertTrue(cartPage.isSubscriptionSuccessMessageDisplayed());
    }

    @Test(description = "Test Case 9: Search Product")
    @Epic("Product Management")
    @Feature("Product Search")
    @Story("Search for products")
    @Severity(SeverityLevel.NORMAL)
    public void testSearchProduct() {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        homePage.navigateToProductsPage();
        productsPage.searchProduct("top");

        Assert.assertTrue(productsPage.isSearchedProductsTitleVisible());
    }

    @Test(description = "Test Case 10: Verify Subscription in home page")
    @Epic("User Management")
    @Feature("Newsletter Subscription")
    @Story("Subscribe to newsletter from home page")
    @Severity(SeverityLevel.NORMAL)
    public void testVerifySubscriptionInHomePage() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isHomePageVisible());

        homePage.scrollToBottom();

        Assert.assertTrue(homePage.isSubscriptionVisible());

        homePage.subscribeWithEmail("test.subscription" + System.currentTimeMillis() + "@gmail.com");

        Assert.assertTrue(homePage.isSubscriptionSuccessMessageDisplayed());
    }

    @Test(description = "Test Case 3: Login User with incorrect email and password")
    @Epic("User Management")
    @Feature("User Login")
    @Story("Login with incorrect credentials")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithIncorrectCredentials() {
        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);

        Assert.assertTrue(homePage.isHomePageVisible());

        homePage.navigateToSignupLoginPage();

        Assert.assertTrue(signupLoginPage.isLoginToYourAccountVisible());

        signupLoginPage.login("wrong" + System.currentTimeMillis() + "@test.com", "WrongPassword123!");

        Assert.assertTrue(signupLoginPage.isIncorrectLoginErrorVisible());
    }
}
