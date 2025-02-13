package org.pages.vacancies;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class VacanciesSteps extends Vacancies{

    public VacanciesSteps(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим на страницу \"Вакансии\" и проверяем главный текст")
    public VacanciesSteps goToPageAndCheckMainText() {
        goToPage();
        checkMainText("#ДАВАЙСРАБОТАЕМСЯ");
        return this;
    }
}
