package org.pages.blog;

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

    protected void goToPage() {
        actions.click(pageButton);
    }

    protected void checkMainText(String text) {
        Assert.assertEquals(actions.getText(mainText), text, "Ожидался: " + text + ", но имеем: "
                + actions.getText(mainText));
    }
}
