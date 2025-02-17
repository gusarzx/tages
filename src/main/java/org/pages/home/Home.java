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
    private static final By TITLE_TEXT = By.xpath("//section[@class='section section-promo']//h2[1]");

    //Что мы делаем?
    private static final By WHAT_WE_DO_HEADER = By.xpath("//section[@class='section section-what-we-do']//h3[1]");
    private static final By WHAT_WE_DO_ITEMS = By.xpath("//li[@class='what-we-do__item']");

    //Партнеры
    private static final By PARTNERS_LIST_HEADER = By.xpath("//section[@class='section section-clients']//h3[1]");
    private static final By PARTNERS_LIST_ITEMS = By.xpath("//ul[@class='partners__list']//li");

    //Нижний колонтитул
    private static final By CONTACT_INFORMATION_HEADER = By.xpath("//section[@class='section section-contact']//h3");
    private static final By CONTACT_INFORMATION_TEXT = By.xpath("//section[@class='section section-contact']//h2");
    private static final By CONTACT_INFORMATION_INPUT = By.xpath("//div[@class='form__form-component form__form-component_input']//input");
    private static final By CONTACT_INFORMATION_INPUT_COMMENT = By.xpath("//div[contains(@class,'form__form-component form__form-component_input')]//textarea[1]");
    private static final By CONTACT_INFORMATION_SEND_BUTTON = By.xpath("//div[@class='form__button-container']//button[1]");

    //Контактная информация
    private static final By CONTACTS = By.xpath("//div[contains(@class,'footer__item_contacts')]//a");
    private static final By PRESS_CONTACTS = By.xpath("//span[text()='Пресс-служба (контакты для СМИ):']/following-sibling::a");
    private static final By HR_CONTACT = By.xpath("//div[contains(@class,'footer__item_press')]//a[contains(@href,'HR@tages.ru')]");
    private static final By REQUISITES = By.xpath("//div[contains(@class,'footer__item_requisites')]//a");
    private static final By SOCIAL_LINKS = By.cssSelector(".footer__item_social .social-list__item-link");
    private static final By PRIVACY_POLICY = By.cssSelector(".footer__item_policy a");
    private static final By COPYRIGHT = By.cssSelector(".footer__item_copyright");

    //Текст-ссылки
    private static final By BUTTON_FORM = By.xpath("//p[@class='section-promo__call-to-action']//a[1]");
    private static final By BUTTON_ROLF = By.xpath("(//a[@class='partners__link'])[1]");
    private static final By BUTTON_MEDSI = By.xpath("(//a[@class='partners__link'])[2]");
    private static final By BUTTON_OMNI = By.xpath("(//a[@class='partners__link'])[3]");
    private static final By BUTTON_INGO = By.xpath("(//a[@class='partners__link'])[4]");
    private static final By BUTTON_MVIDEO = By.xpath("(//a[@class='partners__link'])[5]");
    private static final By BUTTON_LEMANA = By.xpath("(//a[@class='partners__link'])[6]");
    private static final By BUTTON_TECHNO = By.xpath("(//a[@class='partners__link'])[7]");
    private static final By BUTTON_PHONE_NUMBER = By.xpath("//div[@class='footer__item footer__item_contacts']//a[1]");
    private static final By BUTTON_PHONE_NUMBER_TEXT = By.xpath("(//p[@class='section-promo__call-to-action']//a)[2]");
    private static final By BUTTON_MAIL = By.xpath("(//div[@class='footer__item footer__item_contacts']//a)[2]");
    private static final By BUTTON_PHONE_NUMBER_PRESS = By.xpath("(//div[@class='footer__item footer__item_press']//a)[1]");
    private static final By BUTTON_MAIL_PRESS = By.xpath("(//div[@class='footer__item footer__item_press']//a)[2]");
    private static final By BUTTON_MAIL_HR = By.xpath("(//div[@class='footer__item footer__item_press']//a)[3]");
    private static final By BUTTON_TG = By.xpath("//ul[contains(@class,'footer__item footer__item_social')]//li[1]");
    private static final By BUTTON_VK = By.xpath("//ul[contains(@class,'footer__item footer__item_social')]//li[2]");
    private static final By BUTTON_YOUTUBE = By.xpath("//ul[contains(@class,'footer__item footer__item_social')]//li[3]");

    //Не валидные значения
    private static final By NAME_INVALID_VALUE = By.xpath("//input[@placeholder='Имя*']/following-sibling::p[1]");
    private static final By NUMBER_INVALID_VALUE = By.xpath("//input[@type='phone']/following-sibling::p[1]");
    private static final By MAIL_INVALID_VALUE = By.xpath("//input[@type='email']/following-sibling::p[1]");

    //Отправка сообщения
    private static final By SUCCESS_SEND = By.xpath("//div[@class='form__success-badge']//h4[1]");
    private static final By WAIT_ANSWER = By.xpath("//div[@class='form__success-badge']//p[1]");

    //Текст-ссылки
    private static final By INPUT_NAME = By.xpath("(//div[@class='form__form-component form__form-component_input']//input)[1]");
    private static final By INPUT_NUMBER = By.xpath("(//div[@class='form__form-component form__form-component_input']//input)[2]");
    private static final By INPUT_COMPANY = By.xpath("(//div[@class='form__form-component form__form-component_input']//input)[3]");
    private static final By INPUT_MAIL = By.xpath("(//div[@class='form__form-component form__form-component_input']//input)[4]");
    private static final By INPUT_COMMENT = By.xpath("//div[contains(@class,'form__form-component form__form-component_input')]//textarea[1]");
    private static final By NEW_TASK = By.xpath("//div[@class='form__success-badge']//button[1]");


    @Step("Проверяем title и url у сайта Tages")
    public Home checkUrlAndTitleTages(HomeEnum expectedUrl, HomeEnum expectedTitle, HomeEnum expectedText) {
        actions.checkUrlAndTitle(expectedUrl, expectedTitle, expectedText);
        return this;
    }

    @Step("Возвращаемся назад, через кнопку браузера")
    public Home goToPreviousPage() {
        driver.navigate().back();
        return this;
    }

    @Step("Обновляем страницу")
    public Home refreshPage() {
        driver.navigate().refresh();
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

    @Step("Кликаем на текст-ссылку \"форму\" и проверяем url формы")
    public Home goToForm(HomeEnum expectedUrl) {
        actions.click(BUTTON_FORM);
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl.getText(), "Ожидали адрес: " + expectedUrl.getText() + ", но имеем: " + actualUrl);
        return this;
    }

    @Step("Возвращаемся на предыдущую вкладку бразуера")
    public Home returnToPreviousTab() {
        actions.goToTab();
        return this;
    }

    @Step("Кликаем на текст-ссылку \"Рольф\"")
    public Home goToRolf() {
        actions.click(BUTTON_ROLF);
        actions.goToTab();
        return this;
    }

    @Step("Кликаем на текст-ссылку \"Медси\"")
    public Home goToMedsi() {
        actions.click(BUTTON_MEDSI);
        actions.goToTab();
        return this;
    }

    @Step("Кликаем на текст-ссылку \"OmniBoard360\"")
    public Home goToOmni() {
        actions.click(BUTTON_OMNI);
        actions.goToTab();
        return this;
    }

    @Step("Кликаем на текст-ссылку \"Ингосстрах\"")
    public Home goToIngo() {
        actions.click(BUTTON_INGO);
        actions.goToTab();
        return this;
    }

    @Step("Кликаем на текст-ссылку \"М.ВидеоЭльдорадо\"")
    public Home goToMVideo() {
        actions.click(BUTTON_MVIDEO);
        actions.goToTab();
        return this;
    }

    @Step("Кликаем на текст-ссылку \"ЛеманаПро\"")
    public Home goToLemana() {
        actions.click(BUTTON_LEMANA);
        actions.goToTab();
        return this;
    }

    @Step("Кликаем на текст-ссылку \"ТЕХНОНИКОЛЬ\"")
    public Home goToTechno() {
        actions.click(BUTTON_TECHNO);
        actions.goToTab();
        return this;
    }

    @Step("Кликаем на текст-ссылку \"tg\"")
    public Home goToTg() {
        actions.click(BUTTON_TG);
        actions.goToTab();
        return this;
    }

    @Step("Кликаем на текст-ссылку \"vk\"")
    public Home goToVk() {
        actions.click(BUTTON_VK);
        actions.goToTab();
        return this;
    }

    @Step("Кликаем на текст-ссылку \"YouTube\"")
    public Home goToYouTube() {
        actions.click(BUTTON_YOUTUBE);
        actions.goToTab();
        return this;
    }

    @Step("Кликаем на текст-ссылку \"Заполнить форму заново\"")
    public Home goToNewTask() {
        actions.click(NEW_TASK);
        return this;
    }

    @Step("Вводим текст в поле Имя")
    public Home inputName(String name) {
        actions.enterText(INPUT_NAME, name);
        String actualValue = actions.getAttribute(INPUT_NAME, "value");

        Assert.assertEquals(actualValue, name, "Ожидали в поле: " + name + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Отчищаем поле Имя")
    public Home clearName() {
        actions.clearInput(INPUT_NAME);
        String actualValue = actions.getAttribute(INPUT_NAME, "value");

        Assert.assertEquals(actualValue, "", "Ожидали пустое поле: " + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Вводим номер в поле Телефон")
    public Home inputNumber(String number) {
        actions.enterText(INPUT_NUMBER, number);
        String actualValue = actions.getAttribute(INPUT_NUMBER, "value");

        String formatedNumber = actions.formatPhoneNumber(number);
        Assert.assertEquals(actualValue, formatedNumber, "Ожидали в поле: " + number + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Вводим номер в поле Телефон")
    public Home inputNumber(String number, String expectedNumber) {
        actions.enterText(INPUT_NUMBER, number);
        String actualValue = actions.getAttribute(INPUT_NUMBER, "value");

        Assert.assertEquals(actualValue, expectedNumber, "Ожидали: " + expectedNumber + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Отчищаем поле Телефон")
    public Home clearNumber() {
        actions.clearInput(INPUT_NUMBER);
        String actualValue = actions.getAttribute(INPUT_NUMBER, "value");

        Assert.assertEquals(actualValue, "", "Ожидали пустое поле: " + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Вводим текст в поле Компания")
    public Home inputCompany(String company) {
        actions.enterText(INPUT_COMPANY, company);
        String actualValue = actions.getAttribute(INPUT_COMPANY, "value");

        Assert.assertEquals(actualValue, company, "Ожидали в поле: " + company + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Отчищаем поле Компания")
    public Home clearCompany() {
        actions.clearInput(INPUT_COMPANY);
        String actualValue = actions.getAttribute(INPUT_COMPANY, "value");

        Assert.assertEquals(actualValue, "", "Ожидали пустое поле: " + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Вводим текст в поле Почта")
    public Home inputMail(String mail) {
        actions.enterText(INPUT_MAIL, mail);
        String actualValue = actions.getAttribute(INPUT_MAIL, "value");

        Assert.assertEquals(actualValue, mail, "Ожидали в поле: " + mail + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Вводим текст в поле Почта")
    public Home inputMail(String mail, String expectedMail) {
        actions.enterText(INPUT_MAIL, mail);
        String actualValue = actions.getAttribute(INPUT_MAIL, "value");

        Assert.assertEquals(actualValue, expectedMail, "Ожидали в поле: " + expectedMail + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Отчищаем поле Почта")
    public Home clearMail() {
        actions.clearInput(INPUT_MAIL);
        String actualValue = actions.getAttribute(INPUT_MAIL, "value");

        Assert.assertEquals(actualValue, "", "Ожидали пустое поле: " + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Вводим текст в поле Комментарий")
    public Home inputComment(String comment) {
        actions.enterText(INPUT_COMMENT, comment);
        String actualValue = actions.getAttribute(INPUT_COMMENT, "value");

        Assert.assertEquals(actualValue, comment, "Ожидали в поле: " + comment + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Отчищаем поле Комментарий")
    public Home clearComment() {
        actions.clearInput(INPUT_COMMENT);
        String actualValue = actions.getAttribute(INPUT_COMMENT, "value");

        Assert.assertEquals(actualValue, "", "Ожидали пустое поле: " + ",а имеем: " + actualValue);
        return this;
    }

    @Step("Кликаем на кнопку \"Отправить\"")
    public Home clickSend() {
        checkSendButtonClickable();
        actions.click(CONTACT_INFORMATION_SEND_BUTTON);
        return this;
    }

    @Step("Проверяем заголовок главной страницы")
    public Home checkHeaderText(HomeEnum expectedText) {
        String actualText = actions.getText(TITLE_TEXT).trim();
        Assert.assertEquals(actualText, expectedText.getText(), "Ожидался: " + expectedText.getText() + ", но имеем: " + actualText);
        return this;
    }

    @Step("Проверка отсутствия уведомления \"Невалидное значение\" для поля \"Имя\"")
    public Home checkNotInvalidValueName() {
        actions.checkElementNotPresent(NAME_INVALID_VALUE);
        return this;
    }
    @Step("Проверка уведомления \"Невалидное значение\" для поля \"Имя\"")
    public Home checkInvalidValueName() {
        String s = actions.getText(NAME_INVALID_VALUE);
        Assert.assertEquals(s, "Невалидное значение");
        return this;
    }

    @Step("Проверка отсутствия уведомления \"Невалидное значение\" для поля \"Телефон\"")
    public Home checkNotInvalidValueNumber() {
        actions.checkElementNotPresent(NUMBER_INVALID_VALUE);
        return this;
    }
    @Step("Проверка уведомления \"Невалидное значение\" для поля \"Телефон\"")
    public Home checkInvalidValueNumber() {
        String s = actions.getText(NUMBER_INVALID_VALUE);
        Assert.assertEquals(s, "Невалидное значение");
        return this;
    }

    @Step("Проверка отсутствия уведомления \"Невалидное значение\" для поля \"Телефон\"")
    public Home checkNotInvalidValueMail() {
        actions.checkElementNotPresent(MAIL_INVALID_VALUE);
        return this;
    }
    @Step("Проверка уведомления \"Невалидное значение\" для поля \"Телефон\"")
    public Home checkInvalidValueMail() {
        String s = actions.getText(MAIL_INVALID_VALUE);
        Assert.assertEquals(s, "Невалидное значение");
        return this;
    }

    @Step("Проверка успешной отправки запроса")
    public Home checkSuccessSend() {
        String successSend = actions.getText(SUCCESS_SEND);
        String waitAnswer = actions.getText(WAIT_ANSWER);
        String newTask = actions.getText(NEW_TASK);

        softAssert.assertEquals(successSend, "Ваше обращение получено", "Запрос не был отправлен");
        softAssert.assertEquals(waitAnswer, "Ожидайте ответа");
        softAssert.assertEquals(newTask, "Заполнить форму заново");
        softAssert.assertTrue(actions.isButtonClickable(NEW_TASK), "Кнопка \"Заполнить форму заново\" не кликабельна!");
        softAssert.assertAll();
        return this;
    }

    @Step("Проверка успешной отправки запроса")
    public Home checkFailSend() {
        actions.checkElementNotPresent(SUCCESS_SEND);
        actions.checkElementNotPresent(WAIT_ANSWER);
        actions.checkElementNotPresent(NEW_TASK);
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

    @Step("Проверяем кликабельность текст-ссылки \"Номер телефона\" и типа ссылки")
    public Home checkPhoneNumber() {
        String href = actions.getAttribute(BUTTON_PHONE_NUMBER, "href");

        softAssert.assertTrue(href.startsWith("tel:"), "Номер телефона не содержит корректный формат для перехода в приложение телефона!");
        softAssert.assertTrue(actions.isButtonClickable(BUTTON_PHONE_NUMBER), "Кнопка отправки не кликабельна!");
        actions.click(BUTTON_PHONE_NUMBER);

        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем кликабельность текст-ссылки \"Почта\" и типа ссылки")
    public Home checkMail() {
        String href = actions.getAttribute(BUTTON_MAIL, "href");

        softAssert.assertTrue(href.startsWith("mailto:"), "Номер телефона не содержит корректный формат для перехода в приложение телефона!");
        softAssert.assertTrue(actions.isButtonClickable(BUTTON_MAIL), "Кнопка отправки не кликабельна!");
        actions.click(BUTTON_MAIL);

        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем кликабельность текст-ссылки \"Номер телефона Прессы\" и типа ссылки")
    public Home checkPhoneNumberPress() {
        String href = actions.getAttribute(BUTTON_PHONE_NUMBER_PRESS, "href");

        softAssert.assertTrue(href.startsWith("tel:"), "Номер телефона не содержит корректный формат для перехода в приложение телефона!");
        softAssert.assertTrue(actions.isButtonClickable(BUTTON_PHONE_NUMBER_PRESS), "Кнопка отправки не кликабельна!");
        actions.click(BUTTON_PHONE_NUMBER_PRESS);

        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем кликабельность текст-ссылки \"Почта Прессы\" и типа ссылки")
    public Home checkMailPress() {
        String href = actions.getAttribute(BUTTON_MAIL_PRESS, "href");

        softAssert.assertTrue(href.startsWith("mailto:"), "Номер телефона не содержит корректный формат для перехода в приложение телефона!");
        softAssert.assertTrue(actions.isButtonClickable(BUTTON_MAIL_PRESS), "Кнопка отправки не кликабельна!");
        actions.click(BUTTON_MAIL_PRESS);

        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем кликабельность текст-ссылки \"Почта hr\" и типа ссылки")
    public Home checkMailHr() {
        String href = actions.getAttribute(BUTTON_MAIL_HR, "href");

        softAssert.assertTrue(href.startsWith("mailto:"), "Номер телефона не содержит корректный формат для перехода в приложение телефона!");
        softAssert.assertTrue(actions.isButtonClickable(BUTTON_MAIL_HR), "Кнопка отправки не кликабельна!");
        actions.click(BUTTON_MAIL_HR);

        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем кликабельность текст-ссылки \"Номер телефона в тексте\" и типа ссылки")
    public Home checkPhoneNumberInText() {
        String href = actions.getAttribute(BUTTON_PHONE_NUMBER_TEXT, "href");

        softAssert.assertTrue(href.startsWith("tel:"), "Номер телефона не содержит корректный формат для перехода в приложение телефона!");
        softAssert.assertTrue(actions.isButtonClickable(BUTTON_PHONE_NUMBER_TEXT), "Кнопка отправки не кликабельна!");
        actions.click(BUTTON_PHONE_NUMBER_TEXT);

        softAssert.assertAll();
        return this;
    }

    @Step("Проверяем кликабельность текст-ссылки \"Номер телефона\" и типа ссылки")
    public Home checkPhoneNumberInTextAndInFooter() {
        String actualNumberInText = actions.getText(BUTTON_PHONE_NUMBER_TEXT).trim();
        String actualNumberInFooter = actions.getText(BUTTON_PHONE_NUMBER).trim();

        Assert.assertEquals(actualNumberInText, actualNumberInFooter, "В тексте номер: " + actualNumberInText + ", а в контактах: " + actualNumberInFooter);

        return this;
    }
}
