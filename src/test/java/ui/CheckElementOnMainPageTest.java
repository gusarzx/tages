package ui;

import org.openqa.selenium.WebDriver;
import org.pages.aboutCompany.AboutCompany;
import org.pages.aboutCompany.AboutCompanyEnum;
import org.pages.academy.Academy;
import org.pages.academy.AcademyEnum;
import org.pages.anotherWebSite.AnotherWebSite;
import org.pages.anotherWebSite.AnotherWebSiteEnum;
import org.pages.blog.Blog;
import org.pages.blog.BlogEnum;
import org.pages.events.Events;
import org.pages.events.EventsEnum;
import org.pages.home.Home;
import org.pages.home.HomeEnum;
import org.pages.privacyPolicy.PrivacyPolicy;
import org.pages.privacyPolicy.PrivacyPolicyEnum;
import org.pages.requisites.Requisites;
import org.pages.requisites.RequisitesEnum;
import org.pages.vacancies.Vacancies;
import org.pages.vacancies.VacanciesEnum;
import org.pages.сontacts.Contacts;
import org.pages.сontacts.ContactsEnum;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.utils.RandomGenerator;
import org.utils.WebDriverManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CheckElementOnMainPageTest {

    private Home home;
    private AboutCompany aboutCompany;
    private Academy academy;
    private Events events;
    private Blog blog;
    private Vacancies vacancies;
    private Contacts contacts;
    private AnotherWebSite anotherWebSite;
    private Requisites requisites;
    private PrivacyPolicy privacyPolicy;

    private final String NAME = "Тест" + RandomGenerator.generateRandomString(10);
    private final String NUMBER = RandomGenerator.generateRandomNumber(10);
    private final String COMPANY = "Тест" + RandomGenerator.generateRandomString(10);
    private final String MAIL = "test" + RandomGenerator.generateRandomString(10) + "@gmail.com";
    private final String MAIL_MAX = "test" + RandomGenerator.generateRandomString(50) + "@gmail.com";
    private final String COMMENT = RandomGenerator.generateRandomString(30) + " это тестовые, если что, извините!";
    private final String SYMBOL = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~8";
    private final String MAX_STRING = "test" + RandomGenerator.generateRandomString(100);

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = WebDriverManager.getDriver();
        home = new Home(driver);
        aboutCompany = new AboutCompany(driver);
        academy = new Academy(driver);
        events = new Events(driver);
        blog = new Blog(driver);
        vacancies = new Vacancies(driver);
        contacts = new Contacts(driver);
        anotherWebSite = new AnotherWebSite(driver);
        requisites = new Requisites(driver);
        privacyPolicy = new PrivacyPolicy(driver);
        home.open();
    }

    @Test(groups = {"regress", "navigation"}, description = "Возврат на главную страницу через кнопку \"TAGES\"")
    public void test1() {
        aboutCompany
                .goToPage()
                .checkHeaderText(AboutCompanyEnum.HEADER);
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.TITLE_TEXT);

        academy
                .goToPage()
                .checkHeaderText(AcademyEnum.HEADER);
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.TITLE_TEXT);

        events
                .goToPage()
                .checkHeaderText(EventsEnum.HEADER);
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.TITLE_TEXT);

        blog
                .goToPage()
                .checkHeaderText(BlogEnum.HEADER); //страница долго прогружает контент
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.TITLE_TEXT);

        vacancies
                .goToPage()
                .checkHeaderText(VacanciesEnum.HEADER);
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.TITLE_TEXT);

        contacts.goToPage()
                .checkHeaderText(ContactsEnum.HEADER);
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.TITLE_TEXT);
    }

    @Test(groups = {"regress", "navigation"}, description = "Возврат на главную страницу через кнопку \"Назад\" в бразуере")
    public void test2() {
        aboutCompany
                .goToPage()
                .checkHeaderText(AboutCompanyEnum.HEADER);
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.TITLE_TEXT);

        academy
                .goToPage()
                .checkHeaderText(AcademyEnum.HEADER);
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.TITLE_TEXT);

        events
                .goToPage()
                .checkHeaderText(EventsEnum.HEADER); //тут дефект, с этого экрана не работает возврат через кнопку назад (ручками проверял)
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.TITLE_TEXT);

        blog
                .goToPage()
                .checkHeaderText(BlogEnum.HEADER);
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.TITLE_TEXT);

        vacancies.
                goToPage()
                .checkHeaderText(VacanciesEnum.HEADER);
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.TITLE_TEXT);

        contacts.goToPage()
                .checkHeaderText(ContactsEnum.HEADER);
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.TITLE_TEXT);
    }

    @Test(groups = {"regress", "content"}, description = "Проверка текста в таблице \"Чем мы занимаемся\"")
    public void test3() {
        List<String> expectedText = Arrays.stream(HomeEnum.WhatWeDo.values())
                .map(HomeEnum.WhatWeDo::getText)
                .toList();
        home.checkWhatWeDoTexts("Чем мы занимаемся", expectedText);
    }

    @Test(groups = {"regress", "content"}, description = "Проверка текста в таблице \"Партнеры\"")
    public void test4() {
        List<String> expectedText = Arrays.stream(HomeEnum.Partner.values())
                .map(HomeEnum.Partner::getText)
                .toList();
        home.checkPartnerListTexts("Наши партнеры", expectedText);
    }

    @Test(groups = {"regress", "content"}, description = "Проверка текста в нижнем колонтитуле")
    public void test5() {
        List<HomeEnum> expectedContacts = List.of(HomeEnum.NUMBER, HomeEnum.MAIL);
        List<HomeEnum> expectedPressContacts = List.of(HomeEnum.PRESS_NUMBER, HomeEnum.PRESS_MAIL);
        List<String> expectedSocialLinks = Arrays.stream(HomeEnum.LinkSocialNetwork.values())
                .map(HomeEnum.LinkSocialNetwork::getText)
                .toList();

        home.checkFooterElements(expectedContacts, expectedPressContacts, "HR@tages.ru", "Реквизиты", expectedSocialLinks, "Политика конфиденциальности", "© 2012-2025 TAGES");
    }

    @Test(groups = {"regress", "content"}, description = "Проверка блока \"Контактная информация\"")
    public void test6() {
        List<String> expectedText = Arrays.stream(HomeEnum.ContactPlaceHolder.values())
                .map(HomeEnum.ContactPlaceHolder::getText)
                .toList();

        home
                .goToForm(HomeEnum.FORM_URL)
                .checkContactInformationTexts(HomeEnum.TITLE_CONTACT, HomeEnum.SUB_TITLE_CONTACT)
                .checkContactInformationPlaceholders(expectedText, Collections.singletonList(HomeEnum.COMMENT))
                .checkSendButtonClickable();

    }

    @Test(groups = {"regress", "strangerLink"}, description = "Проверка текст-ссылки \"Рольф\"")
    public void test7() {

        home.goToRolf();
        anotherWebSite.checkUrlAndTitleAnotherWebSite(AnotherWebSiteEnum.URL_ROLF, AnotherWebSiteEnum.TITLE_ROLF, AnotherWebSiteEnum.TEXT_ROLF);
        home.returnToPreviousTab();
        home.checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT);

    }

    @Test(groups = {"regress", "strangerLink"}, description = "Проверка текст-ссылки \"Медси\"")
    public void test8() {

        home.goToMedsi();
        anotherWebSite.checkUrlAndTitleAnotherWebSite(AnotherWebSiteEnum.URL_MEDSI, AnotherWebSiteEnum.TITLE_MEDSI, AnotherWebSiteEnum.TEXT_MEDSI);
        home.returnToPreviousTab();
        home.checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT);

    }

    @Test(groups = {"regress", "strangerLink"}, description = "Проверка текст-ссылки \"OmniBoard360\"")
    public void test9() {

        home.goToOmni();
        anotherWebSite.checkUrlAndTitleAnotherWebSite(AnotherWebSiteEnum.URL_OMNI, AnotherWebSiteEnum.TITLE_OMNI, AnotherWebSiteEnum.TEXT_OMNI);
        home.returnToPreviousTab();
        home.checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT);

    }

    @Test(groups = {"regress", "strangerLink"}, description = "Проверка текст-ссылки \"Ингосстрах\"")
    public void test10() {

        home.goToIngo();
        anotherWebSite.checkUrlAndTitleAnotherWebSite(AnotherWebSiteEnum.URL_INGO, AnotherWebSiteEnum.TITLE_INGO, AnotherWebSiteEnum.TEXT_INGO);
        home.returnToPreviousTab();
        home.checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT);

    }

    @Test(groups = {"regress", "strangerLink"}, description = "Проверка текст-ссылки \"М.ВидеоЭльдорадо\"")
    public void test11() {

        home.goToMVideo();
        anotherWebSite.checkUrlAndTitleAnotherWebSite(AnotherWebSiteEnum.URL_MVideo, AnotherWebSiteEnum.TITLE_MVideo, AnotherWebSiteEnum.TEXT_MVideo);
        home.returnToPreviousTab();
        home.checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT);

    }

    @Test(groups = {"regress", "strangerLink"}, description = "Проверка текст-ссылки \"ЛеманаПро\"")
    public void test12() {

        home.goToLemana();
        anotherWebSite.checkUrlAndTitleAnotherWebSite(AnotherWebSiteEnum.URL_LEMANA, AnotherWebSiteEnum.TITLE_LEMANA, AnotherWebSiteEnum.TEXT_LEMANA); //не находит тайтл
        home.returnToPreviousTab();
        home.checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT);

    }

    @Test(groups = {"regress", "strangerLink"}, description = "Проверка текст-ссылки \"ТЕХНОНИКОЛЬ\"")
    public void test13() {

        home.goToTechno();
        anotherWebSite.checkUrlAndTitleAnotherWebSite(AnotherWebSiteEnum.URL_TECHNO, AnotherWebSiteEnum.TITLE_TECHNO, AnotherWebSiteEnum.TEXT_TECHNO);
        home.returnToPreviousTab();
        home.checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT);

    }

    @Test(groups = {"regress", "ourLink"}, description = "Проверка типа и кликабельность текста-ссылки \"Номер телефона\"")
    public void test14() {

        home
                .checkPhoneNumber();
    }

    @Test(groups = {"regress", "ourLink"}, description = "Проверка типа и кликабельность текста-ссылки \"Почта\"")
    public void test15() {

        home
                .checkMail();
    }

    @Test(groups = {"regress", "ourLink"}, description = "Проверка типа и кликабельность текста-ссылки \"Номер телефона Прессы\"")
    public void test16() {

        home
                .checkPhoneNumberPress();
    }

    @Test(groups = {"regress", "ourLink"}, description = "Проверка типа и кликабельность текста-ссылки \"Почта Прессы\"")
    public void test17() {

        home
                .checkMailPress();
    }

    @Test(groups = {"regress", "ourLink"}, description = "Проверка типа и кликабельность текста-ссылки \"Почта hr\"")
    public void test18() {

        home
                .checkMailHr();
    }

    @Test(groups = {"regress", "ourLink"}, description = "Проверка типа и кликабельность текста-ссылки \"Реквизиты\"")
    public void test19() {

        requisites
                .checkButtonRequisites(RequisitesEnum.REQUISITES_URL)
                .checkTitleTextRequisites(RequisitesEnum.TITLE_REQUISITES, RequisitesEnum.SUB_TITLE_REQUISITES);
        home
                .clickButtonTages()
                .refreshPage()
                .checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT);
    }

    @Test(groups = {"regress", "strangerLink"}, description = "Проверка текст-ссылки \"tg\"")
    public void test20() {

        home.goToTg();
        anotherWebSite.checkUrlAndTitleAnotherWebSite(AnotherWebSiteEnum.URL_TG, AnotherWebSiteEnum.TITLE_TG, AnotherWebSiteEnum.TEXT_TG);
        home.returnToPreviousTab();
        home.checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT);

    }

    @Test(groups = {"regress", "strangerLink"}, description = "Проверка текст-ссылки \"vk\"")
    public void test22() {

        home.goToVk();
        anotherWebSite.checkUrlAndTitleAnotherWebSite(AnotherWebSiteEnum.URL_VK, AnotherWebSiteEnum.TITLE_VK, AnotherWebSiteEnum.TEXT_VK);
        home.returnToPreviousTab();
        home.checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT);

    }

    @Test(groups = {"regress", "strangerLink"}, description = "Проверка текст-ссылки \"YouTube\"")
    public void test23() {

        home.goToYouTube();
        anotherWebSite.checkUrlAndTitleAnotherWebSite(AnotherWebSiteEnum.URL_YOUTUBE, AnotherWebSiteEnum.TITLE_YOUTUBE, AnotherWebSiteEnum.TEXT_YOUTUBE);
        home.returnToPreviousTab();
        home.checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT);

    }

    @Test(groups = {"regress", "ourLink"}, description = "Проверка текст-ссылки \"Номер телефона в тексте\"")
    public void test24() {

        home
                .checkPhoneNumberInTextAndInFooter()
                .checkPhoneNumberInText();
    }

    @Test(groups = {"regress", "ourLink"}, description = "Проверка текст-ссылки \"Политика Конфиденциальности\"")
    public void test25() {

        privacyPolicy
                .checkButtonPolicy(PrivacyPolicyEnum.POLICY_URL)
                .checkTitleTextPolicy(PrivacyPolicyEnum.TITLE_POLICY);
        home
                .clickButtonTages()
                .refreshPage()
                .checkUrlAndTitleTages(HomeEnum.URL, HomeEnum.TITLE, HomeEnum.TITLE_TEXT); //почему-то в афт некорректно находит title
    }

    @Test(groups = {"regress", "validationSend"}, description = "Проверка отправки запроса со всеми полями - валидные значения")
    public void test26() {

        home
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputCompany(COMPANY)
                .inputMail(MAIL)
                .inputComment(COMMENT)
                .clickSend()
                .checkNotInvalidValueName()
                .checkNotInvalidValueNumber()
                .checkNotInvalidValueMail()
                .checkSuccessSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Проверка отправки запроса с незаполненным полем \"Компания\" - валидные значения")
    public void test27() {

        home
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .inputComment(COMMENT)
                .clickSend()
                .checkSuccessSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Проверка отправки запроса с незаполненным полем \"Комментарий\" - валидные значения")
    public void test28() {

        home
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputCompany(COMPANY)
                .inputMail(MAIL)
                .clickSend()
                .checkSuccessSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Проверка отправки запроса с незаполненным полем \"Имя\" - валидные значения")
    public void test30() {

        home
                .inputNumber(NUMBER)
                .inputCompany(COMPANY)
                .inputMail(MAIL)
                .inputComment(COMMENT)
                .clickSend()
                .checkInvalidValueName()
                .checkNotInvalidValueNumber()
                .checkNotInvalidValueMail()
                .checkFailSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Проверка отправки запроса с незаполненным полем \"Телефон\" - валидные значения")
    public void test31() {

        home
                .inputName(NAME)
                .inputCompany(COMPANY)
                .inputMail(MAIL)
                .inputComment(COMMENT)
                .clickSend()
                .checkNotInvalidValueName()
                .checkInvalidValueNumber()
                .checkNotInvalidValueMail()
                .checkFailSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Проверка отправки запроса с незаполненным полем \"Почта\" - валидные значения")
    public void test32() {

        home
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputCompany(COMPANY)
                .inputComment(COMMENT)
                .clickSend()
                .checkNotInvalidValueName()
                .checkNotInvalidValueNumber()
                .checkInvalidValueMail()
                .checkFailSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Проверка отправки запроса с незаполненным полями и повторным вводом")
    public void test33() {

        home
                .inputCompany(COMPANY)
                .inputComment(COMMENT)
                .clickSend()
                .checkInvalidValueName()
                .checkInvalidValueNumber()
                .checkInvalidValueMail()
                .checkFailSend()

                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .clickSend()
                .checkNotInvalidValueName()
                .checkNotInvalidValueNumber()
                .checkNotInvalidValueMail()
                .checkSuccessSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Проверка стирания значений в полях")
    public void test34() {

        home
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputCompany(COMPANY)
                .inputMail(MAIL)
                .inputComment(COMMENT)
                .clearName()
                .inputName(NAME + "second")
                .clearNumber()
                .inputNumber("1234567899")
                .clearCompany()
                .inputCompany(COMPANY+ "second")
                .clearMail()
                .inputMail("second" + MAIL)
                .clearComment()
                .inputComment(COMMENT+ "second")
                .clickSend()
                .checkSuccessSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Валидация поля Телефон - граничные значения и допустимые символы")
    public void test35() {

        home
                .inputName(NAME)
                .inputCompany(COMPANY)
                .inputMail(MAIL)
                .inputComment(COMMENT)

                .inputNumber(SYMBOL, "+7 8")
                .clickSend()
                .checkInvalidValueNumber()

                .inputNumber("1", "+7 81")
                .clickSend()
                .checkInvalidValueNumber()

                .inputNumber("2", "+7 812")
                .clickSend()
                .checkInvalidValueNumber()

                .inputNumber("3", "+7 812 3")
                .clickSend()
                .checkInvalidValueNumber()

                .inputNumber("4", "+7 812 34")
                .clickSend()
                .checkInvalidValueNumber()

                .inputNumber("5", "+7 812 345")
                .clickSend()
                .checkInvalidValueNumber()

                .inputNumber("6", "+7 812 345 6")
                .clickSend()
                .checkInvalidValueNumber()

                .inputNumber("7", "+7 812 345 67")
                .clickSend()
                .checkInvalidValueNumber()

                .inputNumber("8", "+7 812 345 67 8")
                .clickSend()
                .checkInvalidValueNumber()

                .inputNumber("9", "+7 812 345 67 89")
                .checkNotInvalidValueNumber()
                .clickSend()
                .checkSuccessSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Валидация поля Почта - граничные значения и обязательные/запрещенные символы")
    public void test36() {

        home
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputCompany(COMPANY)
                .inputComment(COMMENT)

                .inputMail("test")
                .clickSend()
                .checkInvalidValueMail()

                .inputMail("@test", "test@test")
                .clickSend()
                .checkInvalidValueMail()

                .inputMail("com", "test@testcom")
                .clickSend()
                .checkInvalidValueMail()

                .clearMail()
                .inputMail("test.com")
                .clickSend()
                .checkInvalidValueMail()

                .clearMail()
                .inputMail(SYMBOL + "@gmail.com")
                .clickSend()
                .checkInvalidValueMail()

                .clearMail()
                .inputMail("d@gmail.comm")
                .clickSend()
                .checkInvalidValueMail()

                .clearMail()
                .inputMail("d@gmail.c")
                .clickSend()
                .checkInvalidValueMail()

                .clearMail()
                .inputMail("егор@gmail.com")
                .clickSend()
                .checkInvalidValueMail()

                .clearMail()
                .inputMail("d@gmail.com")
                .clickSend()
                .checkNotInvalidValueMail()
                .checkSuccessSend()

                .clearMail()
                .inputMail("dd s@gmail.com")
                .clickSend()
                .checkNotInvalidValueMail()
                .checkSuccessSend()

                .goToNewTask()

                .inputName(NAME + "second")
                .inputNumber(NUMBER)
                .inputCompany(COMPANY + "second")
                .inputComment(COMMENT + "second")
                .inputMail(MAIL_MAX)
                .clickSend()
                .checkSuccessSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Проверка повторного заполнения запроса")
    public void test37() {
        List<String> expectedText = Arrays.stream(HomeEnum.ContactPlaceHolder.values())
                .map(HomeEnum.ContactPlaceHolder::getText)
                .toList();

        home
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputCompany(COMPANY)
                .inputMail(MAIL)
                .inputComment(COMMENT)
                .clickSend()
                .checkSuccessSend()

                .goToNewTask()

                .checkContactInformationTexts(HomeEnum.TITLE_CONTACT, HomeEnum.SUB_TITLE_CONTACT)
                .checkContactInformationPlaceholders(expectedText, Collections.singletonList(HomeEnum.COMMENT))

                .inputName("second" + NAME)
                .inputNumber(NUMBER)
                .inputCompany("second" + COMPANY)
                .inputMail("second" + MAIL)
                .inputComment("second" + COMMENT)
                .clickSend()
                .checkSuccessSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Валидация поля Имя - граничные значения и допустимые символы")
    public void test38() {

        home
                .inputName(MAX_STRING)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .checkNotInvalidValueName()
                .clickSend()
                .checkSuccessSend()

                .goToNewTask()

                .inputName("!")
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .checkNotInvalidValueName()
                .clickSend()
                .checkSuccessSend()

                .goToNewTask()

                .inputName(SYMBOL)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .checkNotInvalidValueName()
                .clickSend()
                .checkSuccessSend()

                .goToNewTask()

                .inputName(SYMBOL + NUMBER + NAME + MAIL + COMMENT + COMPANY)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .checkNotInvalidValueName()
                .clickSend()
                .checkSuccessSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Валидация поля Компания - граничные значения и допустимые символы")
    public void test39() {

        home
                .inputCompany(MAX_STRING)
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .clickSend()
                .checkSuccessSend()

                .goToNewTask()

                .inputCompany("!")
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .clickSend()
                .checkSuccessSend()

                .goToNewTask()

                .inputCompany(SYMBOL)
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .clickSend()
                .checkSuccessSend()

                .goToNewTask()

                .inputCompany(SYMBOL + NUMBER + NAME + MAIL + COMMENT + COMPANY)
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .clickSend()
                .checkSuccessSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Валидация поля Комментарий - граничные значения и допустимые символы")
    public void test40() {

        home
                .inputComment(MAX_STRING)
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .clickSend()
                .checkSuccessSend()

                .goToNewTask()

                .inputComment("!")
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .clickSend()
                .checkSuccessSend()

                .goToNewTask()

                .inputComment(SYMBOL)
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .clickSend()
                .checkSuccessSend()

                .goToNewTask()

                .inputComment(SYMBOL + NUMBER + NAME + MAIL + COMMENT + COMPANY)
                .inputName(NAME)
                .inputNumber(NUMBER)
                .inputMail(MAIL)
                .clickSend()
                .checkSuccessSend();
    }

    @Test(groups = {"regress", "validationSend"}, description = "Проверка отправки запроса со всеми полями - \"null\"")
    public void test41() {

        home
                .inputName("null")
                .inputNumber(NUMBER)
                .inputCompany("null")
                .inputMail("null@null.com")
                .inputComment("null")
                .clickSend()
                .checkNotInvalidValueName()
                .checkNotInvalidValueNumber()
                .checkNotInvalidValueMail()
                .checkSuccessSend();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}
