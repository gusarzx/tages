package org.pages.events;

import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Events {

    private final Actions actions;

    public Events(WebDriver driver) {
        this.actions = new Actions(driver);
    }

    private final By pageButton = By.xpath("(//li[@class='nav-menu__item'])[3]");
    private final By mainText = By.xpath("//div[@class='events container']//h1[1]");

    protected void goToPage() {
        actions.click(pageButton);
    }

    protected void checkMainText(String text) {
        Assert.assertEquals(actions.getText(mainText), text, "Ожидался: " + text + ", но имеем: "
                + actions.getText(mainText));
    }
}
