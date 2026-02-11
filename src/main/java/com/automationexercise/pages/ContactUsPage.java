package com.automationexercise.pages;

import com.automationexercise.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage {

    @FindBy(xpath = "//input[@data-qa='name']")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@data-qa='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@data-qa='subject']")
    private WebElement subjectInput;

    @FindBy(id = "message")
    private WebElement messageTextarea;

    @FindBy(xpath = "//input[@data-qa='submit-button']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='status alert alert-success']")
    private WebElement successMessage;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void fillContactForm(String name, String email, String subject, String message) {
        WebElementUtils.sendKeys(driver, nameInput, name);
        WebElementUtils.sendKeys(driver, emailInput, email);
        WebElementUtils.sendKeys(driver, subjectInput, subject);
        WebElementUtils.sendKeys(driver, messageTextarea, message);
    }

    public void clickSubmit() {
        WebElementUtils.clickWithJS(driver, submitButton);
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // No alert present
        }
    }

    public boolean isSuccessMessageDisplayed() {
        return WebElementUtils.isElementDisplayed(driver, successMessage);
    }
}
