package org.pages.aboutCompany;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class AboutCompanySteps extends AboutCompany{

    public AboutCompanySteps(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим на страницу \"О компании\" и проверяем главный текст")
    public AboutCompanySteps goToPageAndCheckMainText() {
        goToPage();
        checkMainText("Проектирование, разработка и поддержка индивидуальных решений для цифровизации бизнеса.");
        return this;
    }
}
