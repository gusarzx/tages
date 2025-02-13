package org.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Actions {
    private WebDriver driver;

    // Конструктор
    public Actions(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для клика по элементу
    public void click(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    // Явное ожидание: элемент кликабелен
    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Явное ожидание: элемент видим
    private WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Метод для ввода текста
    public void enterText(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Метод для получения текста элемента
    public String getText(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        return element.getText();
    }

    public List<WebElement> find(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        return element.findElements(locator);
    }


    // Метод для выполнения JavaScript-скрипта
    public void executeJavaScript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(script, args);
    }

    // Метод для скроллинга к элементу
    public void scrollToElement(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
    }

}
