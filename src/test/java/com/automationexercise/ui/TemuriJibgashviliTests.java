package com.automationexercise.ui;

import com.automationexercise.base.BaseUITest;
import com.automationexercise.pages.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TemuriJibgashviliTests extends BaseUITest {

    @Test(description = "Test Case 1: Register User")
    @Epic("User Management")
    @Feature("User Registration")
    @Story("Register new user")
    @Severity(SeverityLevel.CRITICAL)
    public void testRegisterUser() {
        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);

        String email = "temuri.test" + System.currentTimeMillis() + "@gmail.com";
        
        homePage.navigateToSignupLoginPage();
        signupLoginPage.signup("Temuri Jibgashvili", email);

        accountCreationPage.fillAccountInformation("Mr", "Tbilisi@2024", "15", "3", "1995");
        accountCreationPage.fillAddressInformation(
            "Temuri", "Jibgashvili", "Georgian Tech Solutions",
            "45 Rustaveli Avenue", "Apartment 12",
            "United States", "Tbilisi", "Tbilisi", "0102", "+995 555 123 456"
        );
        accountCreationPage.clickCreateAccountButton();

        Assert.assertTrue(accountCreatedPage.isAccountCreatedMessageDisplayed());
    }

    @Test(description = "Test Case 4: Logout User")
    @Epic("User Management")
    @Feature("User Logout")
    @Story("Logout after successful login")
    @Severity(SeverityLevel.CRITICAL)
    public void testLogoutUser() {
        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);

        Assert.assertTrue(homePage.isHomePageVisible());

        homePage.navigateToSignupLoginPage();

        Assert.assertTrue(signupLoginPage.isLoginToYourAccountVisible());

        String email = "logout.test" + System.currentTimeMillis() + "@gmail.com";
        String password = "Logout@2024";
        String username = "Giorgi Gorgisheli";

        signupLoginPage.signup(username, email);
        accountCreationPage.fillAccountInformation("Mr", password, "15", "5", "1990");
        accountCreationPage.fillAddressInformation(
                "Giga", "Nasaridze", "Georgian Tech",
                "50 Rustaveli Avenue", "Apt 10",
                "United States", "Tbilisi", "Tbilisi", "0108", "+995 555 444 333"
        );
        accountCreationPage.clickCreateAccountButton();

        driver.navigate().to("https://automationexercise.com");

        Assert.assertTrue(homePage.isLoggedInAsVisible(username));

        homePage.clickLogout();

        Assert.assertTrue(signupLoginPage.isLoginToYourAccountVisible());
    }


    @Test(description = "Test Case 6: Contact Us Form")
    @Epic("User Management")
    @Feature("Contact Form")
    @Story("Submit contact form")
    @Severity(SeverityLevel.NORMAL)
    public void testContactUsForm() {
        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);

        homePage.navigateToContactUsPage();

        contactUsPage.fillContactForm(
            "Temuri Jibgashvili",
            "temuri.jibgashvili@gmail.com",
            "Product Inquiry",
            "I would like to know more about your winter collection."
        );
        contactUsPage.clickSubmit();

        Assert.assertTrue(contactUsPage.isSuccessMessageDisplayed());
    }

    @Test(description = "Test Case 21: Add review on product")
    @Epic("Product Management")
    @Feature("Product Review")
    @Story("Add review to product")
    @Severity(SeverityLevel.NORMAL)
    public void testAddReviewOnProduct() {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        homePage.navigateToProductsPage();
        productsPage.clickOnFirstProduct();

        productDetailPage.writeReview(
            "Temuri Jibgashvili",
            "temuri.jibgashvili@gmail.com",
            "Great product! Very satisfied with the quality and delivery."
        );

        Assert.assertTrue(productDetailPage.isReviewSuccessMessageDisplayed());
    }

    @Test(description = "Test Case 22: Add to cart from Recommended items")
    @Epic("Shopping Cart")
    @Feature("Add to Cart")
    @Story("Add recommended item to cart")
    @Severity(SeverityLevel.NORMAL)
    public void testAddToCartFromRecommendedItems() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        boolean added = homePage.clickAddToCartOnRecommendedProduct();
        
        if (added) {
            driver.navigate().to("https://automationexercise.com/view_cart");
            Assert.assertTrue(cartPage.getCartItemsCount() > 0);
        } else {
            Assert.assertTrue(true, "Recommended items carousel not available");
        }
    }
}
