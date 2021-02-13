package ru.appline.managers;

import org.apache.commons.exec.OS;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static ru.appline.utils.PropConst.*;

/**
 * 
 * @author dmitr
 *класс для управления веб драйвером
 */
public class DriverManager {

	/**
	 * Переменна для хранения объекта веб-драйвера
	 *
	 * @see WebDriver
	 */
	private static WebDriver driver;


	/**
	 * Переменна для хранения объекта DriverManager
	 */
	private static DriverManager INSTANCE = null;


	/**
	 * Менеджер properties
	 *
	 * @see TestPropManager#getTestPropManager()
	 */
	private final TestPropManager props = TestPropManager.getTestPropManager();


	/**
	 * Конструктор специально был объявлен как private (singleton паттерн)
	 *
	 * @see DriverManager#getDriver()
	 * @see DriverManager#initDriver()
	 */
	private DriverManager() {
		initDriver();
	}


	/**
	 * Метод ленивой инициализации веб драйвера
	 *
	 * @return WebDriver - возвращает веб драйвер
	 */
	public static WebDriver getDriver() {
		if (INSTANCE == null) {
			INSTANCE = new DriverManager();
		}
		return driver;
	}


	/**
	 * Метод для закрытия сессии драйвера и браузера
	 *
	 * @see WebDriver#quit()
	 */
	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}


	/**
	 * Метод инициализирующий веб драйвер
	 */
	private void initDriver() {
		if (OS.isFamilyWindows()) {
			initDriverAnyOsFamily(PATH_GECKO_DRIVER_WINDOWS, PATH_CHROME_DRIVER_WINDOWS);
		} else if (OS.isFamilyMac()) {
			initDriverAnyOsFamily(PATH_GECKO_DRIVER_MAC, PATH_CHROME_DRIVER_MAC);
		} else if (OS.isFamilyUnix()) {
			initDriverAnyOsFamily(PATH_GECKO_DRIVER_UNIX, PATH_CHROME_DRIVER_UNIX);

		}
	}

	/**
	 * Метод инициализирующий веб драйвер под ОС семейства Windows
	 */
	private void initDriverWindowsOsFamily() {

	}

	/**
	 * Метод инициализирующий веб драйвер под любую ОС
	 *
	 * gecko - переменная firefox из файла application.properties в классе 
	 * chrome - переменная chrome из файла application.properties в классе 
	 */
	private void initDriverAnyOsFamily(String gecko, String chrome) {
		switch (props.getProperty(TYPE_BROWSER)) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", props.getProperty(gecko));
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", props.getProperty(chrome));
			driver = new ChromeDriver();
			break;
		default:
			Assert.fail("Типа браузера '" + props.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
		}
	}

}
