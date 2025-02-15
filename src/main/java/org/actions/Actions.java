package org.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Actions {
    private final WebDriver driver;

    public Actions(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    public void enterText(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        return element.getText().trim();
    }

    public List<String> getTextsFromElements(By locator) {
        List<WebElement> elements = waitForElementsToBeVisible(locator);
        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText().trim());
        }
        return texts;
    }

    public List<String> getAttributesFromElements(By locator, String attribute) {
        List<WebElement> elements = waitForElementsToBeVisible(locator);
        List<String> attributes = new ArrayList<>();
        for (WebElement element : elements) {
            attributes.add(element.getAttribute(attribute).trim());
        }
        return attributes;
    }

    public List<String> getAttributesFromElements(By locator, String tagName, String attribute) {
        List<WebElement> elements = waitForElementsToBeVisible(locator);
        List<String> attributes = new ArrayList<>();
        for (WebElement element : elements) {
            WebElement child = element.findElement(By.tagName(tagName));
            attributes.add(child.getAttribute(attribute).trim());
        }
        return attributes;
    }

    public boolean isButtonClickable(By locator) {
        try {
            waitForElementToBeClickable(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private List<WebElement> waitForElementsToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}

