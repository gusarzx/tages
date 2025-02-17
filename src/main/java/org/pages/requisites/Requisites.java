package org.pages.requisites;

import io.qameta.allure.Step;
import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class Requisites {

    private final Actions actions;
    private final WebDriver driver;
    private final SoftAssert softAssert;

    public Requisites(WebDriver driver) {
        this.actions = new Actions(driver);
        this.driver = driver;
        this.softAssert = new SoftAssert();
    }

    private static final By BUTTON_REQUISITES = By.xpath("//span[@class='footer__info-title']//a[1]");
    private static final By TITLE_REQUISITES = By.xpath("//div[@class='requisites__container container']//h1[1]");
    private static final By SUB_TITLE_REQUISITES = By.xpath("//div[@class='requisites__container container']//h2[1]");

    @Step("Проверяем кликабельность текст-ссылки \"Реквизиты\"")
    public Requisites checkButtonRequisites(RequisitesEnum expectedUrl) {
        softAssert.assertTrue(actions.isButtonClickable(BUTTON_REQUISITES), "Кнопка отправки не кликабельна!");
        actions.click(BUTTON_REQUISITES);

        String actualUrl = driver.getCurrentUrl();
        softAssert.assertEquals(actualUrl, expectedUrl.getText(), "Ожидали адрес: " + expectedUrl.getText() + ", но имеем: " + actualUrl);

        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем текст страницы \"Реквизиты\"")
    public Requisites checkTitleTextRequisites(RequisitesEnum expectedTitle, RequisitesEnum expectedSubTitle) {
        String actualTitle = actions.getText(TITLE_REQUISITES).trim();
        String actualSubTitle = actions.getText(SUB_TITLE_REQUISITES).trim();

        softAssert.assertEquals(actualTitle, expectedTitle.getText(), "Ожидался: " + expectedTitle + ", но имеем: " + actualTitle);
        softAssert.assertEquals(actualSubTitle, expectedSubTitle.getText(), "Ожидался: " + expectedSubTitle + ", но имеем: " + expectedSubTitle.getText());
        softAssert.assertAll();
        return this;
    }
}
