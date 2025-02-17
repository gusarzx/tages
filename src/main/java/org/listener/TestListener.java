package org.listener;


import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.utils.ScreenshotUtil;
import org.utils.WebDriverManager;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = WebDriverManager.getDriver();
        if (driver != null) {
            try {
                ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
                screenshotUtil.takeScreenshot();
            } catch (Exception e) {
                System.err.println("Не удалось сделать скриншот: " + e.getMessage());
            }
        } else {
            System.err.println("WebDriver не инициализирован. Скриншот не сделан.");
        }
    }
}
