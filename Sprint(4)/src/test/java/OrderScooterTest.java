import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object.AboutRentPage;
import page_object.MainPage;
import page_object.PersonalDataOrderPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class OrderScooterTest {

    private WebDriver driver;

    private final By buttonCheckOrder = By.xpath(".//button[text() = 'Посмотреть статус']");

    private final int indexButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String mobileNumber;
    private final String dateOrder;
    private final String period;
    private final String comment;

    public OrderScooterTest(int indexButton, String name, String surname, String address, String metro,
                            String mobileNumber, String dateOrder, String period, String comment) {
        this.indexButton = indexButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.mobileNumber = mobileNumber;
        this.dateOrder = dateOrder;
        this.period = period;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {1, "Олег", "Иванов", "Москва", "Сокольники", "89996566633", "04.11.2022", "сутки", "До подъезда"},
                {0, "Петр", "Клиш", "Москва", "Красносельская", "89996566644", "04.12.2022", "двое суток", "До квартиры"}
        };
    }

    @Test
    public void orderingScooterWithButtonHead() {
        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoadMainPage();
        mainPage.clickOrder(indexButton);

        PersonalDataOrderPage personalDataOrderPage = new PersonalDataOrderPage(driver);
        personalDataOrderPage.waitForLoadPersonalDataOrderPage();
        personalDataOrderPage.setDataFieldsAndClickButtonNext(name, surname, address,
                metro, mobileNumber);

        AboutRentPage aboutRentPage = new AboutRentPage(driver);
        aboutRentPage.waitForLoadAboutRentPage();
        aboutRentPage.orderScooter(dateOrder, period, comment);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(buttonCheckOrder).isDisplayed()));

        Assert.assertTrue(driver.findElement(buttonCheckOrder).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}