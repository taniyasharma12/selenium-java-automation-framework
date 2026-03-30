package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static driver.DriverManager.getDriver;

public class BasePage {

    // driver instance for all page actions
    protected WebDriver driver;

    // constructor to get driver from DriverManager
    public BasePage() {
        this.driver = getDriver();
    }

    // get the current page title
    public String getTitle() {
        return driver.getTitle();
    }

    // get the current page URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // navigate one step forward in browser
    public void navigateForward() {
        driver.navigate().forward();
    }

    // navigate one step back in browser
    public void navigateBack() {
        driver.navigate().back();
    }

    // click on element using locator
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    // type text into input box
    public void sendKeys(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    // get text from element
    protected String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    // check if element is visible on page
    protected boolean isDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    // refresh the current page
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // get any attribute value from an element
    protected String getAttribute(By locator, String attribute) {
        return driver.findElement(locator).getAttribute(attribute);
    }
}