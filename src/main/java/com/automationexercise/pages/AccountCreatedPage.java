package com.automationexercise.pages;

import com.automationexercise.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends BasePage {

    @FindBy(xpath = "//h2[@data-qa='account-created']")
    private WebElement accountCreatedMessage;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountCreatedMessageDisplayed() {
        return WebElementUtils.isElementDisplayed(driver, accountCreatedMessage);
    }

    public void clickContinueButton() {
        WebElementUtils.click(driver, continueButton);
    }
}
