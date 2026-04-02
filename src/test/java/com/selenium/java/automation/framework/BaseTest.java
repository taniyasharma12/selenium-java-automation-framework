package com.selenium.java.automation.framework;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {

    // to read values from config.properties like browser and URL
    protected ConfigReader configReader;

    // runs before each test method
    @BeforeMethod(alwaysRun = true)
    public void baseSetup() throws Exception {

        // initialize config reader to get browser and URL
        configReader = new ConfigReader();

        // set up driver based on browser from config
        DriverManager.setDriver(configReader.getBrowser());

        // open the application URL in browser
        DriverManager.getDriver().get(configReader.getURL());
    }

    // runs after each test method
    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        // close the driver and clean up
        DriverManager.quitDriver();
    }

    // helper method to get driver easily from test classes
    protected WebDriver getDriver() {

        return DriverManager.getDriver();
    }
}