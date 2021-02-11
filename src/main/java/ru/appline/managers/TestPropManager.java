package ru.appline.managers;

import java.io.*;
import java.util.Properties;


/**
 * 
 * @author dmitr
 * Класс отвечающий за проперти
 */
public class TestPropManager {

	/**
	 * системный класс Java, хранит properties. Основан на Hashtable (ключ-значение)
	 */
    private final Properties properties = new Properties();

    /**
     * Переменна для хранения объекта TestPropManager
     */
    private static TestPropManager INSTANCE = null;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see TestPropManager#getTestPropManager()
     */
    private TestPropManager() {
        loadApplicationProperties();
        loadCustomProperties();
    }

    public static TestPropManager getTestPropManager() {
        if (INSTANCE == null) {
            INSTANCE = new TestPropManager();
        }
        return INSTANCE;
    }
    /**
     * загрузка из файла. Если propFile = null, берем данные из application
     */
    private void loadApplicationProperties() {
        try {
            properties.load(new FileInputStream(
                    new File("src/main/resources/" +
                            System.getProperty("propFile", "application") + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Загрузка из maven. Заменяет дефолтные на кастомные значения
     */
    private void loadCustomProperties() {
        properties.forEach((key, value) -> System.getProperties()
                .forEach((customUserKey, customUserValue) -> {
                    if (key.toString().equals(customUserKey.toString()) &&
                            !value.toString().equals(customUserValue.toString())) {
                        properties.setProperty(key.toString(), customUserValue.toString());
                    }
                }));
    }


    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }
	
}
