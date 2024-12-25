package ru.qa_scooter.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageDropDown {

    private WebDriver driver;

    //Вопросы о важном
    private By importantQuestions = By.xpath(".//div[text()='Вопросы о важном']");

    public HomePageDropDown(WebDriver driver) {
        this.driver = driver;
    }

    // скролл до блока Вопросы о важном
    public void scrollToImportantQuestions() {
        WebElement element = driver.findElement(importantQuestions);
        ((JavascriptExecutor) driver).
                    executeScript("arguments[0].scrollIntoView();", element);
        }

    //Шаг: кликнуть вопрос, подождать разворачивания ответа, получить текст ответа
    public String clickQuestionAndGetAnswerText(int questionIdNumber){

        //Ожидание вопроса
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        (".//div[@id='accordion__heading-" + questionIdNumber + "']")));
        //Кликнуть вопрос
        driver.findElement(By.xpath
                (".//div[@id='accordion__heading-" + questionIdNumber + "']")).click();

        //Ожидание разворачивания ответа
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                    until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                            (".//div[@id='accordion__panel-" + questionIdNumber + "']//p")));
        //Получить текст ответа
        return driver.findElement(By.xpath
                (".//div[@id='accordion__panel-" + questionIdNumber + "']//p")).getText();
    }

}
