package com.automationexercise.pages;

import com.automationexercise.utils.WebElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productsLink;

    @FindBy(xpath = "//a[@href='/contact_us']")
    private WebElement contactUsLink;

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement signupLoginLink;

    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement logoutLink;

    @FindBy(xpath = "//a[@href='/delete_account']")
    private WebElement deleteAccountLink;

    @FindBy(xpath = "//a[contains(text(),' Logged in as ')]")
    private WebElement loggedInAsText;

    @FindBy(xpath = "//footer[@id='footer']//input[@id='susbscribe_email']")
    private WebElement subscriptionEmailInput;

    @FindBy(xpath = "//footer[@id='footer']//button[@id='subscribe']")
    private WebElement subscriptionButton;

    @FindBy(xpath = "//div[@class='alert-success alert']")
    private WebElement subscriptionSuccessMessage;

    @FindBy(xpath = "//h2[contains(text(),'Subscription')]")
    private WebElement subscriptionText;

    @FindBy(id = "scrollUp")
    private WebElement scrollUpArrow;

    @FindBy(xpath = "//div[@class='carousel-inner']//h2[contains(text(),'Full-Fledged practice website for Automation Engineers')]")
    private WebElement fullFledgedText;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToProductsPage() {
        WebElementUtils.click(driver, productsLink);
    }

    public void navigateToContactUsPage() {
        WebElementUtils.click(driver, contactUsLink);
    }

    public void navigateToSignupLoginPage() {
        WebElementUtils.click(driver, signupLoginLink);
    }

    public void clickLogout() {
        WebElementUtils.click(driver, logoutLink);
    }


    public boolean isLoggedInAsVisible(String username) {
        try {
            return loggedInAsText.getText().contains(username);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHomePageVisible() {
        try {
            return driver.getCurrentUrl().contains("automationexercise.com");
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollToBottom() {
        WebElementUtils.scrollToBottom(driver);
    }

    public boolean isSubscriptionVisible() {
        try {
            Thread.sleep(1000);
            return WebElementUtils.isElementDisplayed(driver, subscriptionText);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickScrollUpArrow() {
        try {
            Thread.sleep(2000);
            WebElementUtils.scrollToElement(driver, scrollUpArrow);
            WebElementUtils.clickWithJS(driver, scrollUpArrow);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Scroll up arrow click failed: " + e.getMessage());
        }
    }

    public boolean isFullFledgedTextVisible() {
        try {
            Thread.sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Long scrollPosition = (Long) js.executeScript("return window.pageYOffset;");
            
            if (scrollPosition < 500) {
                return driver.findElements(
                    By.xpath("//h2[contains(text(),'Full-Fledged practice website')]")
                ).size() > 0;
            }
            return false;
        } catch (Exception e) {
            return false;
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

    public boolean clickAddToCartOnRecommendedProduct() {
        WebElementUtils.scrollToBottom(driver);
        
        try {
            Thread.sleep(4000);
            List<WebElement> buttons = driver.findElements(
                By.xpath("//div[@class='recommended_items']//a[@class='btn btn-success add-to-cart']"));
            
            if (buttons.size() > 0) {
                WebElementUtils.scrollToElement(driver, buttons.get(0));
                WebElementUtils.clickWithJS(driver, buttons.get(0));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Recommended items not found: " + e.getMessage());
        }
        return false;
    }
}
