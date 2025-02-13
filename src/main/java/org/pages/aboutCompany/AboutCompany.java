package org.pages.aboutCompany;

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

    protected void goToPage() {
        actions.click(pageButton);
    }

    protected void checkMainText(String text) {
        Assert.assertEquals(actions.getText(mainText), text, "Ожидался: " + text + ", но имеем: "
                + actions.getText(mainText));
    }
}
