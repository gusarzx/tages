package org.pages.home;

import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.utils.ConfigReader;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Home {

    private final WebDriver driver;
    private final Actions actions;

    public Home(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    private final By pageButton = By.xpath("//header[1]/div[1]/div[1]/div[1]/a[1]");
    private final By mainText = By.xpath("//section[@class='section section-promo']//h2[1]");
    private final By tableWhatWeDo = By.xpath("//li[@class='what-we-do__item']");

    public void open() {
        driver.get(ConfigReader.getBaseUrl());
    }

    protected void goToPage() {
        actions.click(pageButton);
    }

    protected void checkMainText(String expectedText) {
        String actualText = actions.getText(mainText); // Сохраняем текст в переменную
        Assert.assertEquals(actualText, expectedText, "Ожидался: " + expectedText + ", но имеем: " + actualText);
    }

    protected void goToPageWithButtonBrowser() {
        driver.navigate().back();
    }

    protected void checkText(List<String> expectedTexts) {
        List<String> actualTexts = getTextFromTable(); // Получаем список текстов из элементов
        Assert.assertEquals(actualTexts, expectedTexts, "Ожидался: " + expectedTexts + ", но имеем: " + actualTexts);
    }

    private List<String> getTextFromTable() {
        List<String> actualTexts = new ArrayList<>();

        // Предположим, что количество элементов известно или его можно определить
        int totalItems = actions.find(tableWhatWeDo).size();

        // Перебираем элементы с использованием индекса
        for (int i = 1; i <= totalItems; i++) {
            // Динамически формируем XPath для каждого элемента
            By dynamicLocator = By.xpath("(//li[@class='what-we-do__item'])[" + i + "]");

            // Получаем текст элемента и добавляем его в список
            String text = actions.getText(dynamicLocator).trim();
            actualTexts.add(text);
        }

        return actualTexts; // Возвращаем список текстов
    }


}
