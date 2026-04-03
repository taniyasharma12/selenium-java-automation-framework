package com.selenium.java.automation.framework;

import annotations.Author;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Author(name = "Taniya Sharma")
    @Test(testName = "Validate Login flow using %s ", dataProvider = "loginData", groups = "smoke")
    public void loginTests(String type, String username, String password) {

        // initialize LoginPage object to access page actions
        LoginPage loginPage = new LoginPage();

        // perform login with provided credentials
        loginPage.login(username, password);

        // capture flash message for validation
        String message = loginPage.getFlashMessage();

        if (type.equalsIgnoreCase("correct credentials")) {

            // positive login validation
            Assert.assertTrue(message.contains("You logged into a secure area"));

            // verify page title after successful login
            Assert.assertEquals(getDriver().getTitle(), "The Internet", "Title mismatch after login");

            // verify URL contains '/secure'
            Assert.assertTrue(getDriver().getCurrentUrl().contains("/secure"), "URL mismatch after login");

            // check if logout button is visible after login
            Assert.assertTrue(loginPage.isLogoutButtonVisible(), "Logout button should be visible after successful login");

        } else if (type.equalsIgnoreCase("incorrect username") || type.equalsIgnoreCase("both incorrect")
                || type.equalsIgnoreCase("empty username")) {

            // validation for invalid username scenarios
            Assert.assertTrue(message.contains("Your username is invalid"));
        } else {

            // validation for invalid password scenario
            Assert.assertTrue(message.contains("Your password is invalid"));

        }

        // verify login form still visible for all failed login attempts
        Assert.assertTrue(loginPage.isLoginFormVisible(), "Login form should still be visible after failed login");
    }

    // test data provider with different login scenarios

    @DataProvider(name = "loginData", parallel = true)
    public Object[][] loginData() throws IOException {

        String excelPath = "\\src\\test\\resources\\testdata.xlsx";
        return ExcelUtils.getExcelData(excelPath, "sheet1");
    }
}