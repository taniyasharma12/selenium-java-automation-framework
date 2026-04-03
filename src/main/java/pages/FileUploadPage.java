package pages;

import org.openqa.selenium.By;

import static driver.DriverManager.getDriver;

public class FileUploadPage extends BasePage {

    // 🔹 XPath locators
    private By fileInput = By.xpath("//input[@id='file-upload']");
    private By uploadBtn = By.xpath("//input[@id='file-submit']");
    private By uploadedFile = By.xpath("//div[@id='uploaded-files']");

    public void uploadFile(String filePath) {
        sendKeys(fileInput, filePath);
    }

    public void clickUpload() {
        click(uploadBtn);
    }

    public String getUploadedFileName() {
        return getText(uploadedFile);
    }
}