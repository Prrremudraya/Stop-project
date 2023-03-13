package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutRentPage {

    private final WebDriver driver;

    private final By dateScooter = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN']");

    private final By period = By.xpath(".//div[@class = 'Dropdown-placeholder']");

    private final By colorScooter = By.id("black");

    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private final By buttonOrder = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");

    private final By buttonOrderYes = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");

    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadAboutRentPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(buttonOrder).isDisplayed()));
    }

    public void setDateScooter(String value) {
        driver.findElement(dateScooter).sendKeys(value, Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void setPeriod(String value) {
        driver.findElement(period).click();
        driver.findElement(By.xpath(".//div[@class = 'Dropdown-menu']/div[text()='" + value + "']")).click();
    }

    public void setColorScooter() {
        driver.findElement(colorScooter).click();
    }

    public void setComment(String value) {
        driver.findElement(comment).sendKeys(value);
    }

    public void clickButtonOrder() {
        driver.findElement(buttonOrder).click();
    }

    public void clickButtonOrderYes() {
        driver.findElement(buttonOrderYes).click();
    }

    public void orderScooter(String dateOrder, String periodOrder, String commentOrder) {
        setDateScooter(dateOrder);
        setPeriod(periodOrder);
        setColorScooter();
        setComment(commentOrder);
        clickButtonOrder();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(buttonOrderYes).isDisplayed()));
        clickButtonOrderYes();
    }
}