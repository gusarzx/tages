package org.pages.сontacts;

import io.qameta.allure.Step;
import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Contacts {

    private final Actions actions;

    public Contacts(WebDriver driver) {
        this.actions = new Actions(driver);
    }

    private final By pageButton = By.xpath("//a[@aria-label='Контакты']");
    private final By mainText = By.xpath("//div[@class='container']//h1[1]");

    @Step("Переходим на вкладку \"Контакты\"")
    public Contacts goToPage() {
        actions.click(pageButton);
        return this;
    }

    @Step("Проверяем заголовок вкладки \"Контакты\"")
    public Contacts checkHeaderText(ContactsEnum expectedText) {
        String actualText = actions.getText(mainText).trim();
        Assert.assertEquals(actualText, expectedText.getText(), "Ожидался: " + expectedText.getText() + ", но имеем: " + actualText);
        return this;
    }
}
