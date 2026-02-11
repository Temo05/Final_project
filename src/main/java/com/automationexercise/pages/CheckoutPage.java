package com.automationexercise.pages;

import com.automationexercise.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//ul[@id='address_delivery']")
    private WebElement deliveryAddress;

    @FindBy(xpath = "//textarea[@name='message']")
    private WebElement commentTextarea;

    @FindBy(xpath = "//a[@href='/payment']")
    private WebElement placeOrderButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDeliveryAddressDisplayed() {
        return WebElementUtils.isElementDisplayed(driver, deliveryAddress);
    }

    public void fillComment(String comment) {
        WebElementUtils.sendKeys(driver, commentTextarea, comment);
    }

    public void clickPlaceOrder() {
        WebElementUtils.scrollToElement(driver, placeOrderButton);
        WebElementUtils.clickWithJS(driver, placeOrderButton);
    }
}
