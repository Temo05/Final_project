package com.automationexercise.utils;

import com.automationexercise.config.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebElementUtils {

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBePresent(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void click(WebDriver driver, WebElement element) {
        waitForElementToBeClickable(driver, element);
        element.click();
    }

    public static void clickWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }

    public static void sendKeys(WebDriver driver, WebElement element, String text) {
        waitForElementToBeVisible(driver, element);
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(WebDriver driver, WebElement element) {
        waitForElementToBeVisible(driver, element);
        return element.getText();
    }

    public static boolean isElementDisplayed(WebDriver driver, WebElement element) {
        try {
            waitForElementToBeVisible(driver, element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        waitForElementToBePresent(driver, By.xpath("//body"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitForAllElementsToBeVisible(WebDriver driver, List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static void waitForUrlToContain(WebDriver driver, String urlFragment) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        wait.until(ExpectedConditions.urlContains(urlFragment));
    }

    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
