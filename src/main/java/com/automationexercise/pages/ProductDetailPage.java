package com.automationexercise.pages;

import com.automationexercise.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends BasePage {

    @FindBy(xpath = "//div[@class='product-information']//h2")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='product-information']//p[contains(text(),'Category:')]")
    private WebElement category;

    @FindBy(xpath = "//div[@class='product-information']//span/span")
    private WebElement price;

    @FindBy(xpath = "//div[@class='product-information']//p[contains(text(),'Availability:')]")
    private WebElement availability;

    @FindBy(xpath = "//div[@class='product-information']//p[contains(text(),'Condition:')]")
    private WebElement condition;

    @FindBy(xpath = "//div[@class='product-information']//p[contains(text(),'Brand:')]")
    private WebElement brand;

    @FindBy(id = "quantity")
    private WebElement quantityInput;

    @FindBy(xpath = "//button[contains(@class,'cart')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[@href='#reviews']")
    private WebElement writeReviewTab;

    @FindBy(id = "name")
    private WebElement reviewNameInput;

    @FindBy(id = "email")
    private WebElement reviewEmailInput;

    @FindBy(id = "review")
    private WebElement reviewTextInput;

    @FindBy(id = "button-review")
    private WebElement submitReviewButton;

    @FindBy(xpath = "//div[@class='alert-success alert']")
    private WebElement reviewSuccessMessage;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductNameVisible() {
        return WebElementUtils.isElementDisplayed(driver, productName);
    }

    public boolean isCategoryVisible() {
        return WebElementUtils.isElementDisplayed(driver, category);
    }

    public boolean isPriceVisible() {
        return WebElementUtils.isElementDisplayed(driver, price);
    }

    public boolean isAvailabilityVisible() {
        return WebElementUtils.isElementDisplayed(driver, availability);
    }

    public boolean isConditionVisible() {
        return WebElementUtils.isElementDisplayed(driver, condition);
    }

    public boolean isBrandVisible() {
        return WebElementUtils.isElementDisplayed(driver, brand);
    }

    public void setQuantity(String quantity) {
        quantityInput.clear();
        WebElementUtils.sendKeys(driver, quantityInput, quantity);
    }

    public void clickAddToCart() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        WebElementUtils.scrollToElement(driver, addToCartButton);
        WebElementUtils.clickWithJS(driver, addToCartButton);
    }

    public void writeReview(String name, String email, String review) {
        WebElementUtils.scrollToElement(driver, writeReviewTab);
        WebElementUtils.click(driver, writeReviewTab);
        
        WebElementUtils.sendKeys(driver, reviewNameInput, name);
        WebElementUtils.sendKeys(driver, reviewEmailInput, email);
        WebElementUtils.sendKeys(driver, reviewTextInput, review);
        
        WebElementUtils.click(driver, submitReviewButton);
    }

    public boolean isReviewSuccessMessageDisplayed() {
        return WebElementUtils.isElementDisplayed(driver, reviewSuccessMessage);
    }
}
