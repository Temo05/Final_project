package com.automationexercise.pages;

import com.automationexercise.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountDeletedPage extends BasePage {

    @FindBy(xpath = "//h2[@data-qa='account-deleted']")
    private WebElement accountDeletedMessage;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountDeletedMessageDisplayed() {
        try {
            Thread.sleep(1000);
            return WebElementUtils.isElementDisplayed(driver, accountDeletedMessage);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinue() {
        WebElementUtils.click(driver, continueButton);
    }
}
