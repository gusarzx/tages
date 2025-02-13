package org.pages.home;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class HomeSteps extends Home{

    public HomeSteps(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим на домашнюю страницу и проверяем главный текст")
    public HomeSteps goToPageAndCheckMainText() {
        goToPage();
        checkMainText("Скорее всего, вам порекомендовали нас. Если вы здесь, то вам нужно качественное " +
                "технологическое решение.");
        return this;
    }

    @Step("Возвращаемся на домашнюю страницу с помощью кнопки \"назад\"")
    public HomeSteps goToPageAndCheckMainTextWithButtonText() {
        goToPageWithButtonBrowser();
        checkMainText("Скорее всего, вам порекомендовали нас. Если вы здесь, то вам нужно качественное " +
                "технологическое решение.");
        return this;
    }
    @Step("Проверяем таблицу \"Чем мы занимаемся\"")
    public HomeSteps checkWhatWeDo(List<String> expectedTexts) {
        checkText(expectedTexts);
        return this;
    }
}
