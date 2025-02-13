package org.pages.events;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class EventsSteps extends Events{

    public EventsSteps(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим на страницу \"Мероприятия\" и проверяем главный текст")
    public EventsSteps goToPageAndCheckMainText() {
        goToPage();
        checkMainText("TAGES МЕРОПРИЯТИЯ");
        return this;
    }
}
