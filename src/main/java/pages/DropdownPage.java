package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static driver.DriverManager.getDriver;

public class DropdownPage {

    private By dropdown = By.id("dropdown");

    // STATIC DROPDOWN (using Select class)
    public void selectByVisibleText(String value) {
        WebElement element = getDriver().findElement(dropdown);
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    // Get selected option
    public String getSelectedOption() {
        WebElement element = getDriver().findElement(dropdown);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }


    //dropdown with dynamic values
    public void selectOptionDynamic(String value) {

        // locate all dropdown options directly
        List<WebElement> options = getDriver()
                .findElements(By.xpath("//select[@id='dropdown']/option"));

        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(value)) {
                option.click();
                break;
            }
        }
    }

    public String getSelectedOptionDynamic() {
        List<WebElement> options = getDriver().findElements(By.xpath("//select[@id='dropdown']/option"));
        for (WebElement option : options) {
            if (option.isSelected()) {
                return option.getText().trim();
            }
        }
        return null;
    }
}