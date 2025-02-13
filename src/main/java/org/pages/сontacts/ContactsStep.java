package org.pages.сontacts;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ContactsStep extends Contacts{

    public ContactsStep(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим на страницу \"Контакты\" и проверяем главный текст")
    public ContactsStep goToPageAndCheckMainText() {
        goToPage();
        checkMainText("Контакты");
        return this;
    }
}
