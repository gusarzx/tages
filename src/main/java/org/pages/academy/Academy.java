package org.pages.academy;

import io.qameta.allure.Step;
import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Academy {
    private final Actions actions;

    public Academy(WebDriver driver) {
        this.actions = new Actions(driver);
    }

    private final By pageButton = By.xpath("(//li[@class='nav-menu__item']//a)[2]");
    private final By mainText = By.xpath("//div[@class='academy container']//h1[1]");

    @Step("Переходим на вкладку \"О компании\"")
    public Academy goToPage() {
        actions.click(pageButton);
        return this;
    }

    @Step("Проверяем заголовок вкладки \"О компании\"")
    public Academy checkHeaderText(AcademyEnum expectedText) {
        String actualText = actions.getText(mainText).trim();
        Assert.assertEquals(actualText, expectedText.getText(), "Ожидался: " + expectedText.getText() + ", но имеем: " + actualText);
        return this;
    }
}
