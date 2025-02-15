package org.pages.events;

import io.qameta.allure.Step;
import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Events {

    private final Actions actions;

    public Events(WebDriver driver) {
        this.actions = new Actions(driver);
    }

    private final By pageButton = By.xpath("(//li[@class='nav-menu__item'])[3]");
    private final By mainText = By.xpath("//div[@class='events container']//h1[1]");

    @Step("Переходим на вкладку \"Мероприятия\"")
    public Events goToPage() {
        actions.click(pageButton);
        return this;
    }

    @Step("Проверяем заголовок вкладки \"Мероприятия\"")
    public Events checkHeaderText(EventsEnum expectedText) {
        String actualText = actions.getText(mainText).trim();
        Assert.assertEquals(actualText, expectedText.getText(), "Ожидался: " + expectedText.getText() + ", но имеем: " + actualText);
        return this;
    }
}
