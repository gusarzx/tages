package org.pages.academy;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class AcademySteps extends Academy {

    public AcademySteps(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим на страницу \"Академия\" и проверяем главный текст")
    public AcademySteps goToPageAndCheckMainText() {
        goToPage();
        checkMainText("TAGES ACADEMY");
        return this;
    }
}
