package org.actions;

import org.interfaces.HasText;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для выполнения часто используемых действий с элементами веб-страницы.
 * Обеспечивает удобный интерфейс для работы с элементами DOM через WebDriver.
 */
public class Actions {
    private final WebDriver driver;
    private final SoftAssert softAssert;

    /**
     * Конструктор класса Actions.
     *
     * @param driver экземпляр WebDriver, используемый для взаимодействия с браузером.
     */
    public Actions(WebDriver driver) {
        this.driver = driver;
        this.softAssert = new SoftAssert();
    }

    /**
     * Проверяет отсутствие элемента в DOM по заданному XPath.
     *
     * @param xpath XPath локатор элемента (например, "//input[@type='email']/following-sibling::p[1]").
     */
    public void checkElementNotPresent(By xpath) {
        List<WebElement> elements = driver.findElements(xpath);
        Assert.assertTrue(elements.isEmpty(), "Элемент по XPath '" + xpath + "' присутствует, но ожидалось его отсутствие!");
    }

    /**
     * Кликает по элементу, найденному по указанному локатору.
     *
     * @param locator локатор элемента, который нужно кликнуть.
     */
    public void click(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    /**
     * Вводит текст в элемент, найденный по указанному локатору.
     * Предварительно очищает поле перед вводом текста.
     *
     * @param locator локатор элемента, в который нужно ввести текст.
     * @param text    текст, который нужно ввести.
     */
    public void enterText(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Очищает значения только указанных полей ввода на странице.
     *
     * @param locator локатор поля ввода, который нужно очистить.
     */
    public void clearInput(By locator) {
        WebElement inputField = waitForElementToBeVisible(locator);
        try {
            inputField.clear();

            if (!inputField.getAttribute("value").isEmpty()) {
                inputField.sendKeys(Keys.CONTROL + "a");
                inputField.sendKeys(Keys.DELETE);
            }
        } catch (Exception e) {
            System.err.println("Не удалось очистить поле ввода: " + locator + ". Ошибка: " + e.getMessage());
        }
    }

    /**
     * Получает текст из элемента, найденного по указанному локатору.
     *
     * @param locator локатор элемента, из которого нужно получить текст.
     * @return текст элемента (обрезанный пробелы по краям).
     */
    public String getText(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        return element.getText().trim();
    }

    /**
     * Получает значение заданного атрибута из элемента, найденного по указанному локатору.
     *
     * @param locator   локатор элемента.
     * @param attribute название атрибута, значение которого нужно получить.
     * @return значение атрибута.
     */
    public String getAttribute(By locator, String attribute) {
        WebElement element = waitForElementToBeVisible(locator);
        return element.getAttribute(attribute).trim();
    }

    /**
     * Проверяет, что указанный текст присутствует на странице.
     *
     * @param expectedText ожидаемый текст, который должен быть на странице.
     * @return true, если текст найден на странице, иначе false.
     */
    public boolean verifyTextIsPresent(String expectedText) {
        waitForPageToLoad();
        try {
            String pageText = driver.findElement(By.tagName("body")).getText();
            return pageText.contains(expectedText);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет текущий URL, заголовок страницы и текст элемента на соответствие ожидаемым значениям.
     *
     * @param <T>           тип ожидаемых значений, который должен предоставлять метод getText().
     * @param expectedUrl   ожидаемый URL страницы.
     * @param expectedTitle ожидаемый заголовок страницы.
     * @param expectedText  ожидаемый текст элемента.
     */
    public <T extends HasText> void checkUrlAndTitle(T expectedUrl, T expectedTitle, T expectedText) {
        waitForPageToLoad();
        String actualUrl = driver.getCurrentUrl();
        String actualTitle = driver.getTitle();

        softAssert.assertEquals(actualUrl, expectedUrl.getText(), "Ожидали адрес: " + expectedUrl.getText() + ", но имеем: " + actualUrl);
        softAssert.assertEquals(actualTitle, expectedTitle.getText(), "Ожидали заголовок: " + expectedTitle.getText() + ", но имеем: " + actualTitle);
        softAssert.assertTrue(verifyTextIsPresent(expectedText.getText()), "Ожидаемый текст '" + expectedText.getText() + "' отсутствует на странице!");
        softAssert.assertAll();
    }

    /**
     * Получает тексты из всех элементов, найденных по указанному локатору.
     *
     * @param locator локатор элементов, из которых нужно получить тексты.
     * @return список текстов всех найденных элементов.
     */
    public List<String> getTextsFromElements(By locator) {
        List<WebElement> elements = waitForElementsToBeVisible(locator);
        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText().trim());
        }
        return texts;
    }

    /**
     * Получает значения заданного атрибута из всех элементов, найденных по указанному локатору.
     *
     * @param locator   локатор элементов, из которых нужно получить атрибуты.
     * @param attribute название атрибута, значение которого нужно получить.
     * @return список значений атрибута для всех найденных элементов.
     */
    public List<String> getAttributesFromElements(By locator, String attribute) {
        List<WebElement> elements = waitForElementsToBeVisible(locator);
        List<String> attributes = new ArrayList<>();
        for (WebElement element : elements) {
            attributes.add(element.getAttribute(attribute).trim());
        }
        return attributes;
    }

    /**
     * Получает значения заданного атрибута из дочерних элементов, найденных по указанному тегу и локатору.
     *
     * @param locator   локатор родительских элементов.
     * @param tagName   имя тега дочернего элемента.
     * @param attribute название атрибута, значение которого нужно получить.
     * @return список значений атрибута для всех найденных дочерних элементов.
     */
    public List<String> getAttributesFromElements(By locator, String tagName, String attribute) {
        List<WebElement> elements = waitForElementsToBeVisible(locator);
        List<String> attributes = new ArrayList<>();
        for (WebElement element : elements) {
            WebElement child = element.findElement(By.tagName(tagName));
            attributes.add(child.getAttribute(attribute).trim());
        }
        return attributes;
    }

    /**
     * Проверяет, является ли кнопка кликабельной.
     *
     * @param locator локатор кнопки, которую нужно проверить.
     * @return true, если кнопка кликабельна, иначе false.
     */
    public boolean isButtonClickable(By locator) {
        try {
            waitForElementToBeClickable(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Переключается на новую вкладку браузера.
     * Предполагается, что новая вкладка уже открыта.
     */
    public void goToTab() {
        String currentTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }

    /**
     * Форматирует номер телефона в зависимости от количества цифр.
     *
     * @param number номер телефона в формате строки (например, "1", "12", "1234567890").
     * @return отформатированный номер (например, "+7 1", "+7 12", "+7 123 456 78 90").
     */
    public String formatPhoneNumber(String number) {
        if (number == null || number.isEmpty()) {
            throw new IllegalArgumentException("Номер телефона не может быть пустым!");
        }

        StringBuilder formattedNumber = new StringBuilder("+7");

        switch (number.length()) {
            case 1 -> formattedNumber.append(" ").append(number);
            case 2 -> formattedNumber.append(" ").append(number);
            case 3 -> formattedNumber.append(" ").append(number);
            case 4 -> formattedNumber.append(" ").append(number.substring(0, 3))
                    .append(" ").append(number.charAt(3));
            case 5 -> formattedNumber.append(" ").append(number.substring(0, 3))
                    .append(" ").append(number.substring(3, 5));
            case 6 -> formattedNumber.append(" ").append(number.substring(0, 3))
                    .append(" ").append(number.substring(3, 6));
            case 7 -> formattedNumber.append(" ").append(number.substring(0, 3))
                    .append(" ").append(number.substring(3, 6))
                    .append(" ").append(number.charAt(6));
            case 8 -> formattedNumber.append(" ").append(number.substring(0, 3))
                    .append(" ").append(number.substring(3, 6))
                    .append(" ").append(number.substring(6, 8));
            case 9 -> formattedNumber.append(" ").append(number.substring(0, 3))
                    .append(" ").append(number.substring(3, 6))
                    .append(" ").append(number.substring(6, 8))
                    .append(" ").append(number.charAt(8));
            case 10 -> formattedNumber.append(" ").append(number.substring(0, 3))
                    .append(" ").append(number.substring(3, 6))
                    .append(" ").append(number.substring(6, 8))
                    .append(" ").append(number.substring(8, 10));
            default -> throw new IllegalArgumentException("Номер телефона должен содержать от 1 до 10 цифр!");
        }

        return formattedNumber.toString();
    }

    /**
     * Ожидает, пока элемент станет кликабельным.
     *
     * @param locator локатор элемента, который нужно ждать.
     * @return WebElement, который стал кликабельным.
     */
    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Ожидает, пока элемент станет видимым.
     *
     * @param locator локатор элемента, который нужно ждать.
     * @return WebElement, который стал видимым.
     */
    private WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Ожидает, пока все элементы, найденные по локатору, станут видимыми.
     *
     * @param locator локатор элементов, которые нужно ждать.
     * @return список WebElement, которые стали видимыми.
     */
    private List<WebElement> waitForElementsToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Ожидает, пока страница полностью загрузится (document.readyState = "complete").
     */
    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
