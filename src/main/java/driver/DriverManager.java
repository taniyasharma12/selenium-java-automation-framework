package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {


    public static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver() { //Return the current driver instance.

        return driverThread.get();

    }

    public static WebDriver setDriver(String browser) throws Exception {

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup(); //Downloads the correct ChromeDriver version.
                driverThread.set(new ChromeDriver());    //Launches the Chrome browser.
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driverThread.set(new FirefoxDriver());
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driverThread.set(new EdgeDriver());
                break;

            default:
                throw new Exception("browser is not supported : " + browser);
        }

        return driverThread.get();
    }

    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}
