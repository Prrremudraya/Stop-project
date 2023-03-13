package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    private final By orderButton = By.xpath(".//button[text() = 'Заказать']");

    private final By imageScooter = By.xpath(".//img[@alt = 'Scooter blueprint']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(imageScooter).isDisplayed()));
    }

    public void clickOrder(int indexButton) {
        switch (indexButton) {
            case 0:
                driver.findElements(orderButton).get(0).click();
                break;
            case 1:
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElements(orderButton).get(1).isDisplayed()));
                driver.findElements(orderButton).get(1).click();
                break;
        }
    }

    public void clickImportantQuestion(int indexFAQ) {
        WebElement element = driver.findElement(By.id("accordion__heading-" + indexFAQ));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        element.click();
    }
}