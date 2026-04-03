package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static driver.DriverManager.getDriver;

public class AlertsPage extends BasePage {

    // Locators
    private By jsAlertBtn = By.xpath("//button[text()='Click for JS Alert']");
    private By jsConfirmBtn = By.xpath("//button[text()='Click for JS Confirm']");
    private By jsPromptBtn = By.xpath("//button[text()='Click for JS Prompt']");
    private By resultText = By.id("result");

    // Click actions
    public void clickJsAlert() {
        click(jsAlertBtn);
    }

    public void clickJsConfirm() {
        click(jsConfirmBtn);
    }

    public void clickJsPrompt() {
        click(jsPromptBtn);
    }

    // Alert actions (generic - reusable)
    private Alert switchToAlert() {
        return getDriver().switchTo().alert();
    }

    public void acceptAlert() {
        switchToAlert().accept();
    }

    public void dismissAlert() {
        switchToAlert().dismiss();
    }

    public void sendTextToAlert(String text) {
        switchToAlert().sendKeys(text);
    }

    public String getAlertText() {
        return switchToAlert().getText();
    }

    // Result validation
    public String getResult() {
        return getDriver().findElement(resultText).getText();
    }
}

