package org.pages.aboutCompany;

import io.qameta.allure.Step;
import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AboutCompany {

    private final Actions actions;

    public AboutCompany(WebDriver driver) {
        this.actions = new Actions(driver);
    }

    private final By pageButton = By.xpath("(//li[@class='nav-menu__item'])[1]");
    private final By mainText = By.xpath("//section[@class='section section-about-lead']//p[1]");

    @Step("Переходим на вкладку \"О компании\"")
    public AboutCompany goToPage() {
        actions.click(pageButton);
        return this;
    }

    @Step("Проверяем заголовок вкладки \"О компании\"")
    public AboutCompany checkHeaderText(AboutCompanyEnum expectedText) {
        String actualText = actions.getText(mainText).trim();
        Assert.assertEquals(actualText, expectedText.getText(), "Ожидался: " + expectedText.getText() + ", но имеем: " + actualText);
        return this;
    }
}
