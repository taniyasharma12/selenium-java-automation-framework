package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static driver.DriverManager.getDriver;

public class CustomWait {

    WebDriver driver;

    public CustomWait() {
        this.driver = getDriver();
    }

    public static void setImplicitWait(WebDriver driver, int timeInSeconds) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
    }

    public static void setExplicitWait(WebDriver driver, WebElement element, int timeInSeconds) {

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public static void setExplicitWait(WebDriver driver, ExpectedCondition expectedCondition, int timeInSeconds) {

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        wait.until(expectedCondition);

    }

    public static void setFluentWait(WebDriver driver, By element) {

        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5));

        fluentWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));


    }


}


