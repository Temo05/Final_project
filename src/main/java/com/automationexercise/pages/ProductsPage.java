package com.automationexercise.pages;

import com.automationexercise.utils.WebElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(),'All Products')]")
    private WebElement allProductsTitle;

    @FindBy(xpath = "//div[@class='features_items']//div[@class='product-image-wrapper']")
    private List<WebElement> allProducts;

    @FindBy(xpath = "//input[@id='search_product']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@id='submit_search']")
    private WebElement searchButton;

    @FindBy(xpath = "//h2[contains(text(),'Searched Products')]")
    private WebElement searchedProductsTitle;

    @FindBy(xpath = "//a[@class='btn btn-success add-to-cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[contains(text(),'Continue Shopping')]")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//div[@class='modal-content']//a[@href='/view_cart']")
    private WebElement viewCartLink;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAllProductsPageVisible() {
        try {
            Thread.sleep(3000);
            return allProducts.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public int getProductsCount() {
        return allProducts.size();
    }

    public void clickOnFirstProduct() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        WebElement viewProduct = allProducts.get(0).findElement(By.xpath(".//a[contains(text(),'View Product')]"));
        WebElementUtils.scrollToElement(driver, viewProduct);
        WebElementUtils.clickWithJS(driver, viewProduct);
    }

    public void searchProduct(String productName) {
        try {
            Thread.sleep(2000);
            if (driver.findElements(By.xpath("//input[@id='search_product']")).size() == 0) {
                return;
            }
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            try {
                List<WebElement> closeButtons = driver.findElements(By.xpath("//button[contains(@class,'close') or @aria-label='Close']"));
                for (WebElement closeBtn : closeButtons) {
                    try {
                        if (closeBtn.isDisplayed()) {
                            closeBtn.click();
                            Thread.sleep(500);
                        }
                    } catch (Exception ignored) {
                    }
                }
            } catch (Exception ignored) {
            }
            
            try {
                js.executeScript(
                    "var ads = document.querySelectorAll('iframe[id^=\"aswift\"], iframe[id*=\"google_ads\"], iframe[title=\"Advertisement\"]');" +
                    "ads.forEach(function(ad) { " +
                    "  ad.style.display = 'none !important'; " +
                    "  ad.style.visibility = 'hidden !important'; " +
                    "  ad.style.opacity = '0 !important'; " +
                    "  ad.style.zIndex = '-9999 !important'; " +
                    "  ad.remove(); " +
                    "});"
                );
                Thread.sleep(500);
            } catch (Exception ignored) {
            }
            
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            
            WebElementUtils.scrollToElement(driver, searchInput);
            Thread.sleep(500);
            WebElementUtils.sendKeys(driver, searchInput, productName);
            Thread.sleep(500);
            
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", searchButton);
            Thread.sleep(300);
            js.executeScript("arguments[0].click();", searchButton);
            
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isSearchedProductsTitleVisible() {
        try {
            Thread.sleep(5000);
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            
            List<String> titleVariations = List.of(
                "//h2[contains(text(),'Searched Products')]",
                "//h2[text()='Searched Products']",
                "//h2[contains(text(),'SEARCHED PRODUCTS')]",
                "//*[contains(text(),'Searched Products')]"
            );
            
            for (String xpath : titleVariations) {
                try {
                    WebElement title = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))
                    );
                    if (title.isDisplayed()) {
                        return true;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            
            System.out.println("=== SEARCH RESULTS PAGE SOURCE ===");
            String pageSource = driver.getPageSource();
            if (pageSource.contains("Searched Products") || pageSource.contains("SEARCHED PRODUCTS")) {
                System.out.println("Title text exists in page source but element not visible!");
            } else {
                System.out.println("Title text NOT found in page source - search may have failed");
            }
            
            return false;
        } catch (Exception e) {
            System.out.println("Exception in isSearchedProductsTitleVisible: " + e.getMessage());
            return false;
        }
    }

    public void clickAddToCartForFirstProduct() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        if (addToCartButtons.size() == 0) {
            return;
        }
        
        WebElementUtils.scrollToElement(driver, addToCartButtons.get(0));
        WebElementUtils.clickWithJS(driver, addToCartButtons.get(0));
    }

    public void clickContinueShopping() {
        WebElementUtils.click(driver, continueShoppingButton);
    }

    public void clickViewCart() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        WebElementUtils.clickWithJS(driver, viewCartLink);
    }
}
