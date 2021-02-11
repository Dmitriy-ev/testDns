package ru.appline.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import ru.appline.managers.*;

import static ru.appline.utils.PropConst.IMPLICITLY_WAIT;
import static ru.appline.managers.DriverManager.getDriver;

import java.util.concurrent.TimeUnit;

public class BasePage {

	 /**
     * Менеджер страничек
     */
    protected PageManager app = PageManager.getPageManager();

    /**
     * Объект для имитации реального поведения мыши или клавиатуры
     */
    protected Actions action = new Actions(getDriver());

    /**
     * Объект для выполнения любого js кода
     */
    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    /**
     * Объект явного ожидания
     * При применении будет ожидать заданного состояния 10 секунд с интервалом в 1 секунду
     */
    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    /**
     * Менеджер properties
     */
    private final TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * Конструктор позволяющий инициализировать все странички и их элементы помеченные аннотацией {FindBy}
     * Подробнее можно просмотреть в класс {org.openqa.selenium.support.PageFactory}
     */
    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    /**
     * Функция позволяющая производить scroll до любого элемента с помощью js
     */
    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Функция позволяющая производить scroll до любого элемента с помощью js со смещение
     * Смещение задается количеством пикселей по вертикали и горизонтали, т.е. смещение до точки (x, y)
     */
    public void scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) getDriver()).executeScript(code, element, x, y);
    }

    /**
     * Явное ожидание состояния clickable элемента
     */
    protected WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    protected WebElement elementToBeVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

    protected WebElement elementToBeVisible(WebElement element) {
    	return wait.until(ExpectedConditions.visibilityOf(element));
	}
    /**
     * Проверям есть ли элемент или нет на страничке
     */
    public boolean isElementExist(By by) {
        boolean flag = false;
        try {
            getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            getDriver().findElement(by);
            flag = true;
        } catch (NoSuchElementException ignore) {
        } finally {
            getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        }
        return flag;
    }
	
}
