package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static driver.DriverManager.getDriver;

public class LoginPage extends BasePage {

    protected WebDriver driver;

    // constructor to assign driver from DriverManager
    public LoginPage() {
        this.driver = getDriver();
    }

    // locators for login page elements
    public static By userNameInputBox = By.id("username");
    public static By passwordInputBox = By.id("password");
    public static By loginButton = By.xpath("//button[@type='submit']");
    public static By homePageMessage = By.xpath("//div[@id='flash']");
    public static By logoutButton = By.xpath("//a[@href='/logout']"); // only visible after successful login

    // perform login action
    public void login(String userName, String password) {
        sendKeys(userNameInputBox, userName);
        sendKeys(passwordInputBox, password);
        click(loginButton);
    }

    // get flash message text after login attempt
    public String getFlashMessage() {
        return getText(homePageMessage);
    }

    // check if logout button is visible (indicates successful login)
    public boolean isLogoutButtonVisible() {
        return isDisplayed(logoutButton);
    }

    // check if login form is still visible (indicates failed login)
    public boolean isLoginFormVisible() {
        return isDisplayed(userNameInputBox) && isDisplayed(passwordInputBox) && isDisplayed(loginButton);
    }
}