package org.pages.vacancies;

import io.qameta.allure.Step;
import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Vacancies {

    private final Actions actions;

    public Vacancies(WebDriver driver) {
        this.actions = new Actions(driver);
    }

    private final By pageButton = By.xpath("//a[@aria-label='Вакансии']");
    private final By mainText = By.xpath("//div[@class='vacancies container']//h1[1]");

    @Step("Переходим на вкладку \"Вакансии\"")
    public Vacancies goToPage() {
        actions.click(pageButton);
        return this;
    }

    @Step("Проверяем заголовок вкладки \"Вакансии\"")
    public Vacancies checkHeaderText(VacanciesEnum expectedText) {
        String actualText = actions.getText(mainText).trim();
        Assert.assertEquals(actualText, expectedText.getText(), "Ожидался: " + expectedText.getText() + ", но имеем: " + actualText);
        return this;
    }
}
