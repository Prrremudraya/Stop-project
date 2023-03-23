package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalDataOrderPage {

    private final WebDriver driver;

    private final By name = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Имя']");

    private final By surname = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Фамилия']");

    private final By address = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Адрес: куда привезти заказ']");

    private final By metro = By.xpath(".//input[@class = 'select-search__input' and @placeholder = '* Станция метро']");

    private final By mobileNumber = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Телефон: на него позвонит курьер']");

    private final By buttonNext = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    public PersonalDataOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadPersonalDataOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(buttonNext).isDisplayed()));
    }

    public void setName(String value) {
        driver.findElement(name).sendKeys(value);
    }

    public void setSurname(String value) {
        driver.findElement(surname).sendKeys(value);
    }

    public void setAddress(String value) {
        driver.findElement(address).sendKeys(value);
    }

    public void setMetro(String value) {
        driver.findElement(metro).sendKeys(value, Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void setMobileNumber(String value) {
        driver.findElement(mobileNumber).sendKeys(value);
    }

    public void setDataFieldsAndClickButtonNext(String valueName, String valueSurname,
                                                String valueAddress, String valueMetro, String valueMobileNumber) {
        setName(valueName);
        setSurname(valueSurname);
        setAddress(valueAddress);
        setMetro(valueMetro);
        setMobileNumber(valueMobileNumber);
        driver.findElement(buttonNext).click();
    }
}