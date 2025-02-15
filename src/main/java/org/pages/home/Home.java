package org.pages.home;

import io.qameta.allure.Step;
import org.actions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.utils.ConfigReader;

import java.util.List;

public class Home {

    private final WebDriver driver;
    private final Actions actions;
    private final SoftAssert softAssert;

    public Home(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.softAssert = new SoftAssert();
    }

    private static final By PAGE_BUTTON = By.xpath("//header[1]/div[1]/div[1]/div[1]/a[1]");
    private static final By MAIN_TEXT = By.xpath("//section[@class='section section-promo']//h2[1]");
    private static final By WHAT_WE_DO_HEADER = By.xpath("//section[@class='section section-what-we-do']//h3[1]");
    private static final By WHAT_WE_DO_ITEMS = By.xpath("//li[@class='what-we-do__item']");
    private static final By PARTNERS_LIST_HEADER = By.xpath("//section[@class='section section-clients']//h3[1]");
    private static final By PARTNERS_LIST_ITEMS = By.xpath("//ul[@class='partners__list']//li");
    private static final By CONTACTS = By.xpath("//div[contains(@class,'footer__item_contacts')]//a");
    private static final By PRESS_CONTACTS = By.xpath("//span[text()='Пресс-служба (контакты для СМИ):']/following-sibling::a");
    private static final By HR_CONTACT = By.xpath("//div[contains(@class,'footer__item_press')]//a[contains(@href,'HR@tages.ru')]");
    private static final By REQUISITES = By.xpath("//div[contains(@class,'footer__item_requisites')]//a");
    private static final By SOCIAL_LINKS = By.cssSelector(".footer__item_social .social-list__item-link");
    private static final By PRIVACY_POLICY = By.cssSelector(".footer__item_policy a");
    private static final By COPYRIGHT = By.cssSelector(".footer__item_copyright");
    private static final By BUTTON_FORM = By.xpath("//p[@class='section-promo__call-to-action']//a[1]");
    private static final By CONTACT_INFORMATION_HEADER = By.xpath("//section[@class='section section-contact']//h3");
    private static final By CONTACT_INFORMATION_TEXT = By.xpath("//section[@class='section section-contact']//h2");
    private static final By CONTACT_INFORMATION_INPUT = By.xpath("//div[@class='form__form-component form__form-component_input']//input");
    private static final By CONTACT_INFORMATION_INPUT_COMMENT = By.xpath("//div[contains(@class,'form__form-component form__form-component_input')]//textarea[1]");
    private static final By CONTACT_INFORMATION_SEND_BUTTON = By.xpath("//div[@class='form__button-container']//button[1]");

    @Step("Возвращаемся назад, через кнопку браузера")
    public Home goToPreviousPage() {
        driver.navigate().back();
        return this;
    }

    @Step("Переходим на главную страницу tages.ru")
    public Home open() {
        driver.get(ConfigReader.getBaseUrl());
        return this;
    }

    @Step("Кликаем на элемент \"TAGES\"")
    public Home clickButtonTages() {
        actions.click(PAGE_BUTTON);
        return this;
    }

    @Step("Кликаем на текст-ссылку \"форму\"")
    public Home goToForm() {
        actions.click(BUTTON_FORM);
        return this;
    }

    @Step("Проверяем заголовок главной страницы")
    public Home checkHeaderText(HomeEnum expectedText) {
        String actualText = actions.getText(MAIN_TEXT).trim();
        Assert.assertEquals(actualText, expectedText.getText(), "Ожидался: " + expectedText.getText() + ", но имеем: " + actualText);
        return this;
    }

    @Step("Проверяем кликабельность кнопки \"Отправить\"")
    public Home checkSendButtonClickable() {
        Assert.assertTrue(actions.isButtonClickable(CONTACT_INFORMATION_SEND_BUTTON), "Кнопка отправки не кликабельна!");
        return this;
    }

    @Step("Проверяем наличие заголовка и списка в блоке \"Что мы делаем\"")
    public Home checkWhatWeDoTexts(String expectedHeader, List<String> expectedTexts) {
        String actualHeader= actions.getText(WHAT_WE_DO_HEADER).trim();
        List<String> actualTexts = actions.getTextsFromElements(WHAT_WE_DO_ITEMS);

        softAssert.assertEquals(actualHeader, expectedHeader, "Ожидался заголовок: " + expectedHeader + ", но имеем: " + actualHeader);
        softAssert.assertEquals(actualTexts, expectedTexts, "Ожидался список: " + expectedTexts + ", но имеем: " + actualTexts);
        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем наличие заголовка и списка в блоке \"Наши партнеры\"")
    public Home checkPartnerListTexts(String expectedHeader, List<String> expectedTexts) {
        String actualHeader= actions.getText(PARTNERS_LIST_HEADER).trim();
        List<String> actualTexts = actions.getAttributesFromElements(PARTNERS_LIST_ITEMS, "a", "title");

        softAssert.assertEquals(actualHeader, expectedHeader, "Ожидался заголовок: " + expectedHeader + ", но имеем: " + actualHeader);
        softAssert.assertEquals(actualTexts, expectedTexts, "Список партнёров не совпадает!");
        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем наличие заголовка и подзаголовка в блоке \"Контактная информация\"")
    public Home checkContactInformationTexts(HomeEnum expectedHeader, HomeEnum expectedText) {
        String actualHeader = actions.getText(CONTACT_INFORMATION_HEADER).trim();
        String actualText = actions.getText(CONTACT_INFORMATION_TEXT).trim();

        softAssert.assertEquals(actualHeader, expectedHeader.getText(), "Ожидался заголовок: " + expectedHeader.getText() + ", но имеем: " + actualHeader);
        softAssert.assertEquals(actualText, expectedText.getText(), "Ожидался текст: " + expectedText.getText() + ", но имеем: " + actualText);
        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем поля ввода в блоке \"Контактная информация\"")
    public Home checkContactInformationPlaceholders(List<String> expectedPlaceholders, List<HomeEnum> expectedPlaceholdersComment) {
        List<String> actualPlaceholders = actions.getAttributesFromElements(CONTACT_INFORMATION_INPUT, "placeholder");
        List<String> actualPlaceholdersComment = actions.getAttributesFromElements(CONTACT_INFORMATION_INPUT_COMMENT, "placeholder");

        softAssert.assertEquals(actualPlaceholders, expectedPlaceholders, "Плейсхолдеры в полях ввода не совпадают!");
        softAssert.assertEquals(actualPlaceholdersComment, expectedPlaceholdersComment.stream().map(HomeEnum::getText).toList(), "Плейсхолдеры в полях ввода не совпадают!");
        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем заголовки и подзаголовки в нижнем колонтитуле")
    public Home checkFooterElements(List<HomeEnum> expectedContacts, List<HomeEnum> expectedPressContacts, String hrContact, String requisites, List<String> expectedSocialLinks, String privacyPolicy, String copyright) {
        List<String> actualContacts = actions.getTextsFromElements(CONTACTS);
        softAssert.assertEquals(actualContacts, expectedContacts.stream().map(HomeEnum::getText).toList(), "Контакты не совпадают!");

        List<String> actualPressContacts = actions.getTextsFromElements(PRESS_CONTACTS);
        softAssert.assertEquals(actualPressContacts, expectedPressContacts.stream().map(HomeEnum::getText).toList(), "Контакты пресс-службы не совпадают!");

        String actualHrContact = actions.getText(HR_CONTACT).trim();
        softAssert.assertEquals(actualHrContact, hrContact, "HR-контакт не совпадает!");

        String actualRequisites = actions.getText(REQUISITES).trim();
        softAssert.assertEquals(actualRequisites, requisites, "Реквизиты не совпадают!");

        List<String> actualSocialLinks = actions.getAttributesFromElements(SOCIAL_LINKS, "href");
        softAssert.assertEquals(actualSocialLinks, expectedSocialLinks, "Социальные ссылки не совпадают!");

        String actualPrivacyPolicy = actions.getText(PRIVACY_POLICY).trim();
        softAssert.assertEquals(actualPrivacyPolicy, privacyPolicy, "Политика конфиденциальности не совпадает!");

        String actualCopyright = actions.getText(COPYRIGHT).trim();
        softAssert.assertEquals(actualCopyright, copyright, "Копирайт не совпадает!");

        softAssert.assertAll();
        return this;
    }
}
