package ru.qa_scooter.importantDropDownMenu;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.qa_scooter.homePage.HomePageDropDown;
import static ru.qa_scooter.usefulData.UsefulData.*;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)

public class ImportantDropDownMenuTest {

    private WebDriver driver;

    //Переменные для параметризации
    //Номер вопроса и ответа
    private int questionIdNumber;
    //Какой должен быть текст ответа
    private String expectedAnswerText;

    public ImportantDropDownMenuTest(int questionIdNumber, String expectedAnswerText){
        this.questionIdNumber = questionIdNumber;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Parameterized.Parameters
    public static Object[][] getAnswerText(){
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void importantDropDownMenuTest() {

        driver = getWebDriver(BROWSER_NAME);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(URL_QA_SCOOTER);

        //Экземпляр класса page object
        HomePageDropDown objHomePageDropDown = new HomePageDropDown(driver);
        //Скролл до блока Вопросы о важном
        objHomePageDropDown.scrollToImportantQuestions();
        //Кликнуть вопрос, получить текст ответа
        String actualAnswerText = objHomePageDropDown.clickQuestionAndGetAnswerText(questionIdNumber);
        //Проверить, что полученный текст ответа совпадает с ожидаемым текстом
        Assert.assertEquals("Текст ответа со страницы должен совпадать с ожидаемым", expectedAnswerText, actualAnswerText);
        System.out.println("Тест importantDropDownMenuTest в браузере " + BROWSER_NAME + " успешно пройден: при нажатии на вопрос открывается ответ");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}