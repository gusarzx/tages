package org.pages.blog;

import io.qameta.allure.Step;
import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Blog {

    private final Actions actions;

    public Blog(WebDriver driver) {
        this.actions = new Actions(driver);
    }

    private final By pageButton = By.xpath("(//li[@class='nav-menu__item']/following-sibling::li)[3]");
    private final By mainText = By.xpath("//div[@class='container container_no-padding']//h1[1]");

    @Step("Переходим на вкладку \"Блог\"")
    public Blog goToPage() {
        actions.click(pageButton);
        return this;
    }

    @Step("Проверяем заголовок вкладки \"Блог\"")
    public Blog checkHeaderText(BlogEnum expectedText) {
        String actualText = actions.getText(mainText).trim();
        Assert.assertEquals(actualText, expectedText.getText(), "Ожидался: " + expectedText.getText() + ", но имеем: " + actualText);
        return this;
    }
}
