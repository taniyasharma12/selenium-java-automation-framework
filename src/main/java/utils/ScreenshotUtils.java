package utils;

import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class ScreenshotUtils {


    public static String takeScreenshot(ITestResult result) throws IOException {

        WebDriver driver = DriverManager.getDriver();
        String screenshotDir = "extent-output/screenshots/";
        new File(screenshotDir).mkdirs(); // Ensure directory exists

        String fileName = result.getName() + "_status" + result.getStatus() + "_" + result.getStartMillis() + ".png";
        String fullPath = screenshotDir + fileName;

        if (driver != null) {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file, new File(fullPath));
            } catch (IOException e) {
                throw new IOException(e);
            }
        }
        return fullPath; //return full path to screenshot file
    }
}

