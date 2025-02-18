package org.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WebDriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            boolean isGridEnabled = ConfigReader.isGridEnabled();
            WebDriver newDriver;

            if (isGridEnabled) {
                try {
                    ChromeOptions options = new ChromeOptions();
                    options.setBrowserVersion(ConfigReader.getBrowserVersion());
                    options.setCapability("browserName", ConfigReader.getBrowser());
                    newDriver = new RemoteWebDriver(new URL(ConfigReader.getGridUrl()), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Некорректный URL для Selenium Grid: " + e.getMessage());
                }
            } else {
                System.setProperty("webdriver.chrome.driver", ConfigReader.getWebDriverPath());
                if ("chrome".equalsIgnoreCase(ConfigReader.getBrowser())) {
                    newDriver = new ChromeDriver();
                } else {
                    throw new RuntimeException("Поддерживается только браузер Chrome");
                }
            }

            newDriver.manage().window().maximize();
            newDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
            driver.set(newDriver);
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
