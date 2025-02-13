package org.pages.mainPages;

import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigReader;
import org.testng.Assert;

public class MainPage {

    private final WebDriver driver;
    private final Actions actions;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    private final By aboutCompanyButton = By.xpath("(//li[@class='nav-menu__item'])[1]");
    private final By aboutCompany = By.xpath("(//h3[@class='section__title'])[1]");
    private final By about = By.xpath("//section[@class='section section-promo']//h2[1]");
    private final By mainPageButton = By.xpath("//header[1]/div[1]/div[1]/div[1]/a[1]");


    public void open() {
        driver.get(ConfigReader.getBaseUrl());
    }

    public MainPage goToCompanyPage() {
        actions.click(aboutCompanyButton);
        return this;
    }

    public MainPage returnToMainPage() {
        actions.click(mainPageButton);
        return this;
    }

    public MainPage checkText(String text) {
        Assert.assertEquals(actions.getText(aboutCompany), text, "Ожидался: " + text + ", но имеем: "
                + actions.getText(aboutCompany));
        return this;
    }

    public MainPage checkTexts(String text) {
        Assert.assertEquals(actions.getText(about), text, "Ожидался: " + text + ", но имеем: "
                + actions.getText(about));
        return this;
    }



}
