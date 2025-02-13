package ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.pages.mainPages.MainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.utils.WebDriverManager;

@Feature("Проверка элементов на главной странице")
public class CheckElementOnMainPageTest {

    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        WebDriver driver = WebDriverManager.getDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Story("Возврат на главную страницу через кнопку браузера \"назад\"")
    @Test(description = "Проверяем возврат на главный экран через кнопку браузера \"назад\" с каждой вкладки")
    public void testGoogleSearch() {

        mainPage
                .goToCompanyPage()
                .checkText("Время – ценный ресурс")
                .returnToMainPage()
                .checkTexts("Скорее всего, вам порекомендовали нас. Если вы здесь, то вам нужно качественное технологическое решение.");

    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}
