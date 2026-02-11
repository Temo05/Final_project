package com.automationexercise.pages;

import com.automationexercise.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AccountCreationPage extends BasePage {

    @FindBy(id = "id_gender1")
    private WebElement mrRadioButton;

    @FindBy(id = "id_gender2")
    private WebElement mrsRadioButton;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "days")
    private WebElement dayDropdown;

    @FindBy(id = "months")
    private WebElement monthDropdown;

    @FindBy(id = "years")
    private WebElement yearDropdown;

    @FindBy(id = "first_name")
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    private WebElement lastNameInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "address1")
    private WebElement address1Input;

    @FindBy(id = "address2")
    private WebElement address2Input;

    @FindBy(id = "country")
    private WebElement countryDropdown;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "zipcode")
    private WebElement zipcodeInput;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberInput;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    private WebElement createAccountButton;

    public AccountCreationPage(WebDriver driver) {
        super(driver);
    }

    public void fillAccountInformation(String title, String password, String day, String month, String year) {
        if (title.equalsIgnoreCase("Mr")) {
            WebElementUtils.click(driver, mrRadioButton);
        } else {
            WebElementUtils.click(driver, mrsRadioButton);
        }

        WebElementUtils.sendKeys(driver, passwordInput, password);

        Select daySelect = new Select(dayDropdown);
        daySelect.selectByValue(day);

        Select monthSelect = new Select(monthDropdown);
        monthSelect.selectByValue(month);

        Select yearSelect = new Select(yearDropdown);
        yearSelect.selectByValue(year);
    }

    public void fillAddressInformation(String firstName, String lastName, String company,
                                        String address1, String address2, String country,
                                        String state, String city, String zipcode, String mobileNumber) {
        WebElementUtils.sendKeys(driver, firstNameInput, firstName);
        WebElementUtils.sendKeys(driver, lastNameInput, lastName);
        WebElementUtils.sendKeys(driver, companyInput, company);
        WebElementUtils.sendKeys(driver, address1Input, address1);
        WebElementUtils.sendKeys(driver, address2Input, address2);

        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText(country);

        WebElementUtils.sendKeys(driver, stateInput, state);
        WebElementUtils.sendKeys(driver, cityInput, city);
        WebElementUtils.sendKeys(driver, zipcodeInput, zipcode);
        WebElementUtils.sendKeys(driver, mobileNumberInput, mobileNumber);
    }

    public void clickCreateAccountButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        WebElementUtils.scrollToElement(driver, createAccountButton);
        WebElementUtils.clickWithJS(driver, createAccountButton);
    }
}
