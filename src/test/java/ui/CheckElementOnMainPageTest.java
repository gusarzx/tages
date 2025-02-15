package ui;

import org.openqa.selenium.WebDriver;

import org.pages.aboutCompany.AboutCompany;
import org.pages.aboutCompany.AboutCompanyEnum;
import org.pages.academy.Academy;
import org.pages.academy.AcademyEnum;
import org.pages.blog.Blog;
import org.pages.blog.BlogEnum;
import org.pages.events.Events;
import org.pages.events.EventsEnum;
import org.pages.home.Home;
import org.pages.home.HomeEnum;
import org.pages.vacancies.Vacancies;
import org.pages.vacancies.VacanciesEnum;
import org.pages.сontacts.Contacts;
import org.pages.сontacts.ContactsEnum;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
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

    @BeforeMethod
    public void setUp() {
        WebDriver driver = WebDriverManager.getDriver();
        home = new Home(driver);
        aboutCompany = new AboutCompany(driver);
        academy = new Academy(driver);
        events = new Events(driver);
        blog = new Blog(driver);
        vacancies = new Vacancies(driver);
        contacts = new Contacts(driver);
        home.open();
    }

    @Test(description = "Возврат на главную страницу через кнопку \"TAGES\"")
    public void test1() {
        aboutCompany
                .goToPage()
                .checkHeaderText(AboutCompanyEnum.HEADER);
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.HEADER);

        academy
                .goToPage()
                .checkHeaderText(AcademyEnum.HEADER);
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.HEADER);

        events
                .goToPage()
                .checkHeaderText(EventsEnum.HEADER);
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.HEADER);

        blog
                .goToPage()
                .checkHeaderText(BlogEnum.HEADER); //страница долго прогружает контент
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.HEADER);

        vacancies
                .goToPage()
                .checkHeaderText(VacanciesEnum.HEADER);
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.HEADER);

        contacts .goToPage()
                .checkHeaderText(ContactsEnum.HEADER);
        home
                .clickButtonTages()
                .checkHeaderText(HomeEnum.HEADER);
    }

    @Test(description = "Возврат на главную страницу через кнопку \"Назад\" в бразуере")
    public void test2() {
        aboutCompany
                .goToPage()
                .checkHeaderText(AboutCompanyEnum.HEADER);
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.HEADER);

        academy
                .goToPage()
                .checkHeaderText(AcademyEnum.HEADER);
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.HEADER);

        events
                .goToPage()
                .checkHeaderText(EventsEnum.HEADER); //тут дефект, с этого экрана не работает возврат через кнопку назад (ручками проверял)
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.HEADER);

        blog
                .goToPage()
                .checkHeaderText(BlogEnum.HEADER);
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.HEADER);

        vacancies.
                goToPage()
                .checkHeaderText(VacanciesEnum.HEADER);
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.HEADER);

        contacts .goToPage()
                .checkHeaderText(ContactsEnum.HEADER);
        home
                .goToPreviousPage()
                .checkHeaderText(HomeEnum.HEADER);
    }

    @Test(description = "Проверка текста в таблице \"Чем мы занимаемся\"")
    public void test3() {
        List<String> expectedText = Arrays.stream(HomeEnum.WhatWeDo.values())
                .map(HomeEnum.WhatWeDo::getText)
                .toList();
        home.checkWhatWeDoTexts("Чем мы занимаемся", expectedText);
    }

    @Test(description = "Проверка текста в таблице \"Партнеры\"")
    public void test4() {
        List<String> expectedText = Arrays.stream(HomeEnum.Partner.values())
                .map(HomeEnum.Partner::getText)
                .toList();
        home.checkPartnerListTexts("Наши партнеры", expectedText);
    }

    @Test(description = "Проверка текста в нижнем колонтитуле")
    public void test5() {
        List<HomeEnum> expectedContacts = List.of(HomeEnum.NUMBER, HomeEnum.MAIL);
        List<HomeEnum> expectedPressContacts = List.of(HomeEnum.PRESS_NUMBER, HomeEnum.PRESS_MAIL);
        List<String> expectedSocialLinks = Arrays.stream(HomeEnum.LinkSocialNetwork.values())
                .map(HomeEnum.LinkSocialNetwork::getText)
                .toList();

        home.checkFooterElements(expectedContacts, expectedPressContacts, "HR@tages.ru", "Реквизиты", expectedSocialLinks, "Политика конфиденциальности", "© 2012-2025 TAGES");
    }

    @Test(description = "Проверка блока \"Контактная информация\"")
    public void test6() {
        List<String> expectedText = Arrays.stream(HomeEnum.ContactPlaceHolder.values())
                .map(HomeEnum.ContactPlaceHolder::getText)
                .toList();

        home.goToForm()
                .checkContactInformationTexts(HomeEnum.HEADER_CONTACT, HomeEnum.SUB_HEADER_CONTACT)
                .checkContactInformationPlaceholders(expectedText, Collections.singletonList(HomeEnum.COMMENT))
                .checkSendButtonClickable();

    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}
