package org.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WebDriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", ConfigReader.getWebDriverPath());

            if ("chrome".equalsIgnoreCase(ConfigReader.getBrowser())) {
                driver = new ChromeDriver();
            } else {
                throw new RuntimeException("Поддерживается только браузер Chrome");
            }

            // Настраиваем драйвер
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
