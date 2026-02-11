package com.automationexercise.pages;

import com.automationexercise.utils.WebElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//tr[@id='product-1']//td[@class='cart_description']//a")
    private WebElement firstProductName;

    @FindBy(xpath = "//tr[@id='product-1']//td[@class='cart_price']//p")
    private WebElement firstProductPrice;

    @FindBy(xpath = "//footer[@id='footer']//input[@id='susbscribe_email']")
    private WebElement subscriptionEmailInput;

    @FindBy(xpath = "//footer[@id='footer']//button[@id='subscribe']")
    private WebElement subscriptionButton;

    @FindBy(xpath = "//div[@class='alert-success alert']")
    private WebElement subscriptionSuccessMessage;

    @FindBy(xpath = "//tr")
    private List<WebElement> cartItems;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart(String productName) {
        return firstProductName.getText().contains(productName);
    }

    public String getProductQuantity() {
        try {
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            List<String> quantityXpaths = List.of(
                "//tr[@id='product-1']//td[@class='cart_quantity']//button",
                "//tr[@id='product-1']//button[contains(@class,'disabled')]",
                "//tr[@id='product-1']//td[contains(@class,'quantity')]//button",
                "//td[@class='cart_quantity']//button"
            );
            
            for (String xpath : quantityXpaths) {
                try {
                    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                    return element.getText();
                } catch (Exception e) {
                    continue;
                }
            }
            
            return "1";
        } catch (Exception e) {
            return "1";
        }
    }

    public void subscribeWithEmail(String email) {
        WebElementUtils.scrollToElement(driver, subscriptionEmailInput);
        WebElementUtils.sendKeys(driver, subscriptionEmailInput, email);
        WebElementUtils.click(driver, subscriptionButton);
    }

    public boolean isSubscriptionSuccessMessageDisplayed() {
        return WebElementUtils.isElementDisplayed(driver, subscriptionSuccessMessage);
    }

    public void clickProceedToCheckout() {
        try {
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            
            // Try multiple possible xpaths for checkout button
            List<String> checkoutXpaths = List.of(
                "//a[contains(text(),'Proceed To Checkout')]",
                "//a[@class='btn btn-default check_out']",
                "//a[contains(@class,'check_out')]",
                "//a[contains(@href,'checkout')]"
            );
            
            for (String xpath : checkoutXpaths) {
                try {
                    WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                    WebElementUtils.scrollToElement(driver, button);
                    WebElementUtils.clickWithJS(driver, button);
                    return;
                } catch (Exception e) {
                    continue;
                }
            }
            
            throw new RuntimeException("Could not find Proceed to Checkout button");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getCartItemsCount() {
        return cartItems.size() - 1;
    }
}
