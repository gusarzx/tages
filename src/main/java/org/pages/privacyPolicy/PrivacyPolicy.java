package org.pages.privacyPolicy;

import io.qameta.allure.Step;
import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class PrivacyPolicy {

    private final Actions actions;
    private final WebDriver driver;
    private final SoftAssert softAssert;

    public PrivacyPolicy(WebDriver driver) {
        this.actions = new Actions(driver);
        this.driver = driver;
        this.softAssert = new SoftAssert();
    }

    private static final By BUTTON_PRIVACY_POLICY = By.xpath("//div[@class='footer__item footer__item_policy']//a[1]");
    private static final By TITLE_PRIVACY_POLICY = By.xpath("//h3[normalize-space(text())='Политика в отношении обработки персональных данных']");


    @Step("Проверяем кликабельность текст-ссылки \"Реквизиты\"")
    public PrivacyPolicy checkButtonPolicy(PrivacyPolicyEnum expectedUrl) {
        softAssert.assertTrue(actions.isButtonClickable(BUTTON_PRIVACY_POLICY), "Кнопка отправки не кликабельна!");
        actions.click(BUTTON_PRIVACY_POLICY);

        String actualUrl = driver.getCurrentUrl();
        softAssert.assertEquals(actualUrl, expectedUrl.getText(), "Ожидали адрес: " + expectedUrl.getText() + ", но имеем: " + actualUrl);

        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем текст страницы \"Реквизиты\"")
    public PrivacyPolicy checkTitleTextPolicy(PrivacyPolicyEnum expectedTitle) {
        String actualTitle = actions.getText(TITLE_PRIVACY_POLICY).trim();

        Assert.assertEquals(actualTitle, expectedTitle.getText(), "Ожидался: " + expectedTitle.getText() + ", но имеем: " + actualTitle);
        return this;
    }

}
