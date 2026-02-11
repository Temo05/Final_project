package com.automationexercise.pages;

import com.automationexercise.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupLoginPage extends BasePage {

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    private WebElement signupNameInput;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signupEmailInput;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signupButton;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement loginEmailInput;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement loginPasswordInput;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h2[contains(text(),'Login to your account')]")
    private WebElement loginToYourAccountText;

    @FindBy(xpath = "//h2[contains(text(),'New User Signup!')]")
    private WebElement newUserSignupText;

    @FindBy(xpath = "//p[contains(text(),'Your email or password is incorrect!')]")
    private WebElement incorrectLoginErrorMessage;

    @FindBy(xpath = "//p[contains(text(),'Email Address already exist!')]")
    private WebElement emailAlreadyExistsErrorMessage;

    public SignupLoginPage(WebDriver driver) {
        super(driver);
    }

    public void signup(String name, String email) {
        WebElementUtils.sendKeys(driver, signupNameInput, name);
        WebElementUtils.sendKeys(driver, signupEmailInput, email);
        WebElementUtils.click(driver, signupButton);
    }

    public void login(String email, String password) {
        WebElementUtils.sendKeys(driver, loginEmailInput, email);
        WebElementUtils.sendKeys(driver, loginPasswordInput, password);
        WebElementUtils.click(driver, loginButton);
    }

    public boolean isLoginToYourAccountVisible() {
        return WebElementUtils.isElementDisplayed(driver, loginToYourAccountText);
    }

    public boolean isNewUserSignupVisible() {
        return WebElementUtils.isElementDisplayed(driver, newUserSignupText);
    }

    public boolean isIncorrectLoginErrorVisible() {
        return WebElementUtils.isElementDisplayed(driver, incorrectLoginErrorMessage);
    }

    public boolean isEmailAlreadyExistsErrorVisible() {
        return WebElementUtils.isElementDisplayed(driver, emailAlreadyExistsErrorMessage);
    }
}
