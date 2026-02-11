package com.automationexercise.base;

import com.automationexercise.utils.DriverManager;
import com.automationexercise.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.ByteArrayInputStream;

public class BaseUITest {
    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        System.out.println("Test Suite Started: " + this.getClass().getSimpleName());
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = DriverManager.getDriver();
        driver.get("https://automationexercise.com");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot on Failure", "image/png",
                    new ByteArrayInputStream(screenshot), "png");

                ScreenshotUtils.captureScreenshot(driver, result.getName());
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }

        DriverManager.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        System.out.println("Test Suite Finished: " + this.getClass().getSimpleName());
    }
}
