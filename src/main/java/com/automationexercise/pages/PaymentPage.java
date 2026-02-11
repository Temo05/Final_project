package com.automationexercise.pages;

import com.automationexercise.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

    @FindBy(xpath = "//input[@data-qa='name-on-card']")
    private WebElement nameOnCardInput;

    @FindBy(xpath = "//input[@data-qa='card-number']")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//input[@data-qa='cvc']")
    private WebElement cvcInput;

    @FindBy(xpath = "//input[@data-qa='expiry-month']")
    private WebElement expiryMonthInput;

    @FindBy(xpath = "//input[@data-qa='expiry-year']")
    private WebElement expiryYearInput;

    @FindBy(xpath = "//button[@data-qa='pay-button']")
    private WebElement payButton;

    @FindBy(xpath = "//p[contains(text(),'Congratulations')]")
    private WebElement orderPlacedMessage;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void fillPaymentDetails(String nameOnCard, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        WebElementUtils.sendKeys(driver, nameOnCardInput, nameOnCard);
        WebElementUtils.sendKeys(driver, cardNumberInput, cardNumber);
        WebElementUtils.sendKeys(driver, cvcInput, cvc);
        WebElementUtils.sendKeys(driver, expiryMonthInput, expiryMonth);
        WebElementUtils.sendKeys(driver, expiryYearInput, expiryYear);
    }

    public void clickPayAndConfirmOrder() {
        WebElementUtils.click(driver, payButton);
    }

    public boolean isOrderPlacedMessageDisplayed() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return WebElementUtils.isElementDisplayed(driver, orderPlacedMessage);
    }
}
