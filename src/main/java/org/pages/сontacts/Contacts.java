package org.pages.сontacts;

import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Contacts {

    private final Actions actions;

    public Contacts(WebDriver driver) {
        this.actions = new Actions(driver);
    }

    private final By pageButton = By.xpath("//a[@aria-label='Контакты']");
    private final By mainText = By.xpath("//div[@class='container']//h1[1]");

    protected void goToPage() {
        actions.click(pageButton);
    }

    protected void checkMainText(String text) {
        Assert.assertEquals(actions.getText(mainText), text, "Ожидался: " + text + ", но имеем: "
                + actions.getText(mainText));
    }
}
