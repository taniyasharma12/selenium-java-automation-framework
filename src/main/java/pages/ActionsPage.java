package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static driver.DriverManager.getDriver;


public class ActionsPage extends BasePage {

    Actions actions = new Actions(getDriver());

    // HOVER
    private By userImages = By.cssSelector(".figure");
    private By userCaptions = By.cssSelector(".figcaption h5");

    public void hoverOverUser(int index) {
        WebElement element = getDriver().findElements(userImages).get(index - 1);
        actions.moveToElement(element).perform();
    }

    public String getUserCaption(int index) {
        return getDriver().findElements(userCaptions).get(index - 1).getText();
    }

    public boolean isCaptionVisible(int index) {
        return getDriver().findElements(userCaptions).get(index - 1).isDisplayed();
    }

    //CONTEXT CLICK
    private By contextBox = By.id("hot-spot");

    public void rightClickOnBox() {
        WebElement element = getDriver().findElement(contextBox);
        actions.contextClick(element).perform();
    }

    //DRAG AND DROP
    private By source = By.id("column-a");
    private By target = By.id("column-b");

    public void dragAndDrop() {
        WebElement src = getDriver().findElement(source);
        WebElement tgt = getDriver().findElement(target);

        actions.dragAndDrop(src, tgt).perform();
    }

    public void dragAndDropUsingClickHold() {
        WebElement src = getDriver().findElement(source);
        WebElement tgt = getDriver().findElement(target);

        actions.clickAndHold(src)
                .moveToElement(tgt)
                .release()
                .build()
                .perform();
    }

    public String getColumnAText() {
        return getDriver().findElement(source).getText();
    }

    public String getColumnBText() {
        return getDriver().findElement(target).getText();
    }
}