package ru.qa_scooter.makeOrder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.qa_scooter.homePage.HomePageMakeOrder;
import java.util.concurrent.TimeUnit;
import static ru.qa_scooter.usefulData.UsefulData.*;

@RunWith(Parameterized.class)

public class MakeOrderLowerButtonTest {

    private WebDriver driver;

    //Переменные для параметризации
    private String firstNameIndex;
    private String lastNameIndex;
    private String addressIndex;
    private int stationIndex;
    private String phoneNumberIndex;
    private String forHowLongIndex;
    private String scooterColorIndex;
    private String commentIndex;

    //Конструктор
    public MakeOrderLowerButtonTest(String firstNameIndex, String lastNameIndex, String addressIndex,
                                    int stationIndex, String phoneNumberIndex, String forHowLongIndex,
                                    String scooterColorIndex, String commentIndex){
        this.firstNameIndex=firstNameIndex;
        this.lastNameIndex=lastNameIndex;
        this.addressIndex=addressIndex;
        this.stationIndex=stationIndex;
        this.phoneNumberIndex=phoneNumberIndex;
        this.forHowLongIndex=forHowLongIndex;
        this.scooterColorIndex=scooterColorIndex;
        this.commentIndex=commentIndex;
    }

    //Значения параметров для теста
    @Parameterized.Parameters
    public static Object[][] makeOrderLowerButtonTestParameters(){
        return new Object[][] {
                {"Тест", "Тестов", "Тест, 1, 2", 0, "89009009090", "сутки", "black", "commentEng"},
                {"имяРус", "фамилияРус", "Москва, 1, 2", 1, "+79139009090", "пятеро суток", "grey", "комментарийРус"},
        };
    }

    @Test
    public void makeOrderLowerButtonTest(){
        driver = getWebDriver(BROWSER_NAME);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(URL_QA_SCOOTER);
        //Экземпляр класса page object
        HomePageMakeOrder homePageMakeOrder = new HomePageMakeOrder(driver);

        //Закрыть куки
        homePageMakeOrder.cookieButtonClick();
        //Скролл до нижней кнопки Заказать
        homePageMakeOrder.scrollToLowerOrderButton();
        //Подождать видимость кнопки Заказать (тк без ожидания тест периодически падает)
        homePageMakeOrder.waitForLowerOrderButton();
        //Кликнуть нижнюю кнопку Заказать
        homePageMakeOrder.lowerOrderButtonClick();
        //Подождать окно Для кого самокат
        homePageMakeOrder.waitForFillInFormForWhom();
        //Заполнить поля 1 страницы формы заказа
        homePageMakeOrder.FillInFormForWhom (firstNameIndex, lastNameIndex, addressIndex, stationIndex, phoneNumberIndex);
        //Кликнуть кнопку Далее
        homePageMakeOrder.furtherButtonClick();
        //Подождать окно Про аренду
        homePageMakeOrder.waitForFillInFormAboutRental();
        //Заполнить поля 2 страницы формы заказа
        homePageMakeOrder.setFillInFormAboutRental(forHowLongIndex, scooterColorIndex, commentIndex);
        //Кликнуть кнопку Заказать
        homePageMakeOrder.makeOrderButtonClick();
        //Подождать окно Хотите оформить заказ?
        homePageMakeOrder.waitForConfirmOrder();
        //Кликнуть кнопку Да
        homePageMakeOrder.confirmOrderYesButtonClick();
        //Подождать окно Заказ оформлен
        homePageMakeOrder.waitForOrderDone();
        //Проверить наличие кнопки Посмотреть статус в окне Заказ оформлен
        boolean actualStatus = homePageMakeOrder.seeOrderStatusIsDisplayed();
        Assert.assertTrue("Кнопка Посмотреть статус должна отображаться в окне Заказ оформлен", actualStatus);
        System.out.println("Тест makeOrderLowerButtonTest в браузере " + BROWSER_NAME + " успешно пройден: заказ оформлен");

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
