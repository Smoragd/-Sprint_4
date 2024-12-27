package ru.qa_scooter.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageMakeOrder {

    private WebDriver driver;

    //Поля и локаторы:
    //Верхняя кнопка Заказать
    private By cookieButton = By.xpath(".//button[@id='rcc-confirm-button']");
    private By upperOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    //Нижняя кнопка Заказать
    private By lowerOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Форма для заполнения "Для кого самокат" - заголовок
    private By fillInFormForWhom = By.xpath(".//div[@class='Order_Header__BZXOb']");
    //Имя Тест
    private By firstName = By.xpath(".//input[@placeholder= '* Имя']");
    //Фамилия Тестов
    private By lastName = By.xpath(".//input[@placeholder= '* Фамилия']");
    //Адрес Тестовый адрес, д.1, кв.1
    private By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Станция метро (поле)
    private By subwayStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Станция метро (выпадающий список) - нужно дождаться
    private By subwayStationList = By.xpath(".//*[@id='root']//ul");
    //Станция метро (выбор конкретной 1ой станции из списка = 0 или 1)
    private By subwayStationChosen = By.xpath(".//*[@id='root']//li[@data-index='0']");
    //Телефон 89009009000 или +79009009000
    private By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private By furtherButton = By.xpath(
            ".//button[(@class='Button_Button__ra12g Button_Middle__1CSJM') and (text()='Далее')]");

    //Форма для заполнения "Про аренду"
    private By fillInFormAboutRental = By.xpath(".//div[@class='Order_Header__BZXOb']");
    //Когда привезти самокат
    private By whenDeliver = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле календаря - нужно дождаться
    private By calendarField = By.xpath(".//*[@id='root']//div[@class='react-datepicker']");
    //Дата - сегодня
    private By dateToday = By.xpath(".//*[@id='root']//div[contains(@class, 'react-datepicker__day--today')]");
    //Срок аренды
    private By forHowLong = By.xpath(".//div[text()='* Срок аренды']");
    //Срок аренды (выпадающий список) - дождаться
    private By forHowLongDropDown = By.xpath(".//*[@id='root']//div[@class='Dropdown-menu']");
    //Срок аренды (выбор конкретного срока = сутки или пятеро суток)
    private By forHowLongChosen = By.xpath(".//*[@id='root']//div[text()='сутки']");
    //Цвет самоката = black или grey
    private By scooterColor = By.xpath(".//*[@id='root']//input[@id='black']");
    //Комментарий для курьера - Тестовый комментарий
    private By comment = By.xpath(".//*[@id='root']//input[@placeholder='Комментарий для курьера']");
    //Кнопка Заказать
    private By makeOrderButton = By.xpath(
            ".//*[@id='root']//button[(@class='Button_Button__ra12g Button_Middle__1CSJM') and (text()='Заказать')]");

    //Окно "Хотите оформить заказ?" - дождаться
    private By confirmOrder = By.xpath(
            "//*[@id='root']//div[@class='Order_ModalHeader__3FDaJ']");
    //Кнопка Да - в Chrome не нажимается
    private By confirmOrderYes = By.xpath(
            ".//*[@id='root']//button[(@class='Button_Button__ra12g Button_Middle__1CSJM') and (text()='Да')]");

    //Окно "Заказ оформлен" - дождаться
    private By orderDone = By.xpath(".//*[@id='root']//div[@class='Order_ModalHeader__3FDaJ']");
    //Кнопка Посмотреть статус
    private By seeOrderStatus = By.xpath(
            ".//*[@id='root']//button[(@class='Button_Button__ra12g Button_Middle__1CSJM') and (text()='Посмотреть статус')]");

    public HomePageMakeOrder(WebDriver driver) {
        this.driver = driver;
    }

    //Методы:

    //Закрыть куки
    public void cookieButtonClick(){driver.findElement(cookieButton).click();
    }
    //Кликнуть верхнюю кнопку Заказать
    public void upperOrderButtonClick(){driver.findElement(upperOrderButton).click();
    }
    //Скролл до нижней кнопки Заказать
    public void scrollToLowerOrderButton() {
        WebElement element = driver.findElement(lowerOrderButton);
        ((JavascriptExecutor) driver).
                executeScript("arguments[0].scrollIntoView();", element);
    }
    //Подождать видимость кнопки Заказать (тк без ожидания тест периодически падает)
    public void waitForLowerOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(lowerOrderButton));
    }
    //Кликнуть нижнюю кнопку Заказать
    public void lowerOrderButtonClick(){driver.findElement(lowerOrderButton).click();}

    //Подождать окно Для кого самокат
    public void waitForFillInFormForWhom(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(fillInFormForWhom));
    }

    //Заполнить поля 1 страницы формы заказа
    public void FillInFormForWhom
    (String firstNameIndex, String lastNameIndex, String addressIndex, int stationIndex, String phoneNumberIndex){
    //Заполнить имя
    driver.findElement(firstName).sendKeys(firstNameIndex);
    //Заполнить фамилию
    driver.findElement(lastName).sendKeys(lastNameIndex);
    //Заполнить адрес
    driver.findElement(address).sendKeys(addressIndex);
    //Кликнуть в поле Станция метро
    driver.findElement(subwayStationField).click();
    //Дождаться отображения выпадающего списка станций метро
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(subwayStationList));
    //Выбрать станцию метро из списка
    driver.findElement(By.xpath(".//*[@id='root']//li[@data-index='" + stationIndex + "']")).click();
    //Заполнить телефон
    driver.findElement(phoneNumber).sendKeys(phoneNumberIndex);
    }

    //Кликнуть кнопку Далее
    public void furtherButtonClick(){
        driver.findElement(furtherButton).click();
    }

    //Подождать окно Про аренду
    public void waitForFillInFormAboutRental(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(fillInFormAboutRental));
    }

    //Заполнить поля 2 страницы формы заказа
    public void setFillInFormAboutRental(String forHowLongIndex, String scooterColorIndex, String commentIndex){
    //Кликнуть Когда привезти самокат
        driver.findElement(whenDeliver).click();
    //Подождать открытия календаря
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(calendarField));
    //Кликнуть дату
        driver.findElement(dateToday).click();
    //Кликнуть срок аренды
        driver.findElement(forHowLong).click();
    //Подождать открытия выпадающего списка
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(forHowLongDropDown));
    //Кликнуть срок
        driver.findElement(By.xpath(".//*[@id='root']//div[text()='" + forHowLongIndex + "']")).click();
    //Кликнуть цвет самоката
        driver.findElement(By.xpath(".//*[@id='root']//input[@id='" + scooterColorIndex + "']")).click();
    //Внести комментарий для курьера
        driver.findElement(comment).sendKeys(commentIndex);
    }
    //Кликнуть кнопку Заказать
    public void makeOrderButtonClick(){
        driver.findElement(makeOrderButton).click();
    }

    //Подождать окно Хотите оформить заказ?
    public void waitForConfirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(confirmOrder));
    }

    //Кликнуть кнопку Да
    public void confirmOrderYesButtonClick(){
        driver.findElement(confirmOrderYes).click();
    }

    //Подождать окно Заказ оформлен
    public void waitForOrderDone() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(orderDone));
    }

    //Проверить наличие кнопки Посмотреть статус в окне Заказ оформлен
    public boolean seeOrderStatusIsDisplayed(){
        return driver.findElement(seeOrderStatus).isDisplayed();
    }


}
