package ui;

import org.openqa.selenium.WebDriver;
import org.pages.aboutCompany.AboutCompanySteps;

import org.pages.academy.AcademySteps;
import org.pages.blog.BlogSteps;
import org.pages.events.EventsSteps;
import org.pages.home.HomeSteps;
import org.pages.vacancies.VacanciesSteps;
import org.pages.сontacts.ContactsStep;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.utils.WebDriverManager;

import java.util.List;

public class CheckElementOnMainPageTest {

    private HomeSteps home;
    private AboutCompanySteps aboutCompany;
    private AcademySteps academy;
    private EventsSteps events;
    private BlogSteps blog;
    private VacanciesSteps vacancies;
    private ContactsStep contacts;

    @BeforeMethod
    public void setUp() {
        WebDriver driver = WebDriverManager.getDriver();
        home = new HomeSteps(driver);
        aboutCompany = new AboutCompanySteps(driver);
        academy = new AcademySteps(driver);
        events = new EventsSteps(driver);
        blog = new BlogSteps(driver);
        vacancies = new VacanciesSteps(driver);
        contacts = new ContactsStep(driver);
        home.open();
    }

    @Test(description = "Возврат на главную страницу через кнопку \"TAGES\"")
    public void test1() {
        aboutCompany.goToPageAndCheckMainText();
        home.goToPageAndCheckMainText();
        academy.goToPageAndCheckMainText();
        home.goToPageAndCheckMainText();
        events.goToPageAndCheckMainText();
        home.goToPageAndCheckMainText();
        blog.goToPageAndCheckMainText(); //страница долго прогружает контент
        home.goToPageAndCheckMainText();
        vacancies.goToPageAndCheckMainText();
        home.goToPageAndCheckMainText();
        contacts.goToPageAndCheckMainText();
        home.goToPageAndCheckMainText();
    }

    @Test(description = "Возврат на главную страницу через кнопку \"Назад\" в бразуере")
    public void test2() {
        aboutCompany.goToPageAndCheckMainText();
        home.goToPageAndCheckMainTextWithButtonText();
        academy.goToPageAndCheckMainText();
        home.goToPageAndCheckMainTextWithButtonText();
        events.goToPageAndCheckMainText(); //тут дефект, с этого экрана не работает возврат через кнопку назад (ручками проверял)
        home.goToPageAndCheckMainTextWithButtonText();
        blog.goToPageAndCheckMainText();
        home.goToPageAndCheckMainTextWithButtonText();
        vacancies.goToPageAndCheckMainText();
        home.goToPageAndCheckMainTextWithButtonText();
        contacts.goToPageAndCheckMainText();
        home.goToPageAndCheckMainTextWithButtonText();
    }

    @Test(description = "Проверка текста в таблице \"Чем мы занимаемся\"")
    public void test3() {
        List<String> expectedText = List.of(
                "Разработка программного обеспечения",
                "Разработка пользовательских интерфейсов",
                "Построение процессов PIM / PXM",
                "API management",
                "DevOps",
                "Разработка SuperApp",
                "Интеграционные решения",
                "Разработка Enterprise-решений на микросервисной архитектуре",
                "Разработка мобильных сервисов",
                "Quality Assurance",
                "Композитная low code платформа",
                "Проектирование архитектуры",
                "Обеспечение бесперебойной работы приложений и инфраструктуры",
                "Разработка и внедрение решений на базе распределенных реестров",
                "Автоматизация жизненно важных бизнес-процессов"
        );
        home.checkWhatWeDo(expectedText);
    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}
