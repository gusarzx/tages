package org.pages.anotherWebSite;

import io.qameta.allure.Step;
import org.actions.Actions;
import org.openqa.selenium.WebDriver;

public class AnotherWebSite {

    private final Actions actions;

    public AnotherWebSite(WebDriver driver) {
        this.actions = new Actions(driver);
    }

    @Step("Проверяем title и url у сайта: {expectedUrl}")
    public AnotherWebSite checkUrlAndTitleAnotherWebSite(AnotherWebSiteEnum expectedUrl, AnotherWebSiteEnum expectedTitle, AnotherWebSiteEnum expectedText) {
        actions.checkUrlAndTitle(expectedUrl, expectedTitle, expectedText);
        return this;
    }
}
