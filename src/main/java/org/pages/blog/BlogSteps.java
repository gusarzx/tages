package org.pages.blog;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BlogSteps extends Blog{

    public BlogSteps(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим на страницу \"Блог\" и проверяем главный текст")
    public BlogSteps goToPageAndCheckMainText() {
        goToPage();
        checkMainText("Блог");
        return this;
    }
}
