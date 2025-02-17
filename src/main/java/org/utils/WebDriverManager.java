package org.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WebDriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            boolean isGridEnabled = ConfigReader.isGridEnabled();

            if (isGridEnabled) {
                try {
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName(ConfigReader.getBrowser());
                    capabilities.setVersion(ConfigReader.getBrowserVersion());
                    driver = new RemoteWebDriver(new URL(ConfigReader.getGridUrl()), capabilities);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Некорректный URL для Selenium Grid: " + e.getMessage());
                }
            } else {
                System.setProperty("webdriver.chrome.driver", ConfigReader.getWebDriverPath());
                if ("chrome".equalsIgnoreCase(ConfigReader.getBrowser())) {
                    driver = new ChromeDriver();
                } else {
                    throw new RuntimeException("Поддерживается только браузер Chrome");
                }
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
