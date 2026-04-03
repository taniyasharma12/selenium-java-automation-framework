package com.selenium.java.automation.framework;

import annotations.Author;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertsPage;
import pages.LoginPage;
import utils.ConfigReader;

import java.io.IOException;

public class AlertsTest extends BaseTest {

    AlertsPage alertsPage;
    LoginPage loginPage;
    ConfigReader config;

    @BeforeMethod(alwaysRun = true)
    public void login() throws IOException {

        loginPage = new LoginPage();
        config = new ConfigReader();

        loginPage.login(config.getUsername(), config.getPassword());

        Assert.assertTrue(loginPage.isLogoutButtonVisible(),
                "Login failed in setup");

        getDriver().get("https://the-internet.herokuapp.com/javascript_alerts");

        alertsPage = new AlertsPage();
    }

    //Test 1: Simple Alert (OK)
    @Author(name = "Taniya Sharma")
    @Test(groups = {"smoke"}, testName = "JS Alert Accept Validation")
    public void verifyJsAlertAccept() {

        alertsPage.clickJsAlert();

        Assert.assertEquals(alertsPage.getAlertText(), "I am a JS Alert");

        alertsPage.acceptAlert();

        Assert.assertTrue(alertsPage.getResult()
                .contains("You successfully clicked an alert"));
    }

    //Test 2: Confirm Alert (Cancel)
    @Author(name = "Taniya Sharma")
    @Test(groups = "smoke", testName = "JS Confirm Dismiss Validation")
    public void verifyJsConfirmDismiss() {

        alertsPage.clickJsConfirm();

        Assert.assertEquals(alertsPage.getAlertText(), "I am a JS Confirm");

        alertsPage.dismissAlert();

        Assert.assertTrue(alertsPage.getResult()
                .contains("You clicked: Cancel"));
    }

    // Test 3: Prompt Alert (Send Text)
    @Author(name = "Taniya Sharma")
    @Test(groups = "regression", testName = "JS Prompt Input Validation")
    public void verifyJsPromptInput() {

        alertsPage.clickJsPrompt();

        Assert.assertEquals(alertsPage.getAlertText(), "I am a JS prompt");

        alertsPage.sendTextToAlert("Taniya");
        alertsPage.acceptAlert();

        Assert.assertTrue(alertsPage.getResult()
                .contains("Taniya"));
    }
}