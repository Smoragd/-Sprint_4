package ru.qa_scooter.usefulData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UsefulData {

    //url сайта заказа самокатов
    public static final String URL_QA_SCOOTER = "https://qa-scooter.praktikum-services.ru/";

    //Передать в константу нужный браузер: "FIREFOX" или "CHROME"
    public static final String BROWSER_NAME = "CHROME";

    //Метод возвращает нужный web driver
    public static WebDriver getWebDriver(String browserName) {
        if (browserName.equals("FIREFOX")) {
            return new FirefoxDriver();
        } else if (browserName.equals("CHROME")) {
            return new ChromeDriver();
        } else {
            throw new RuntimeException("Не распознан браузер: " + browserName);
        }
    }

}
