package com.selenium.java.automation.framework;

import annotations.Author;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DropdownPage;
import pages.LoginPage;
import utils.ConfigReader;

import java.io.IOException;

public class DropdownTest extends BaseTest {

    DropdownPage dropdownPage;
    LoginPage loginPage;
    ConfigReader config;

    @BeforeMethod(alwaysRun = true)
    public void login() throws IOException {

        loginPage = new LoginPage();
        config = new ConfigReader();

        loginPage.login(config.getUsername(), config.getPassword());

        Assert.assertTrue(loginPage.isLogoutButtonVisible(),
                "Login failed in setup");

        getDriver().get("https://the-internet.herokuapp.com/dropdown");

        dropdownPage = new DropdownPage();
    }

    @Author(name = "Taniya Sharma")
    @Test(dataProvider = "dropdownData", groups = {"smoke"})
    public void verifyDropdownUsingSelect(String option) {

        dropdownPage.selectByVisibleText(option);

        Assert.assertEquals(dropdownPage.getSelectedOption(), option,
                "Selected option mismatch (Select method)");
    }

    @Author(name = "Taniya Sharma")
    @Test(dataProvider = "dropdownData", groups = {"regression"})
    public void verifyDropdownUsingDynamic(String option) {

        dropdownPage.selectOptionDynamic(option);

        String selectedOption = dropdownPage.getSelectedOptionDynamic();

        Assert.assertEquals(selectedOption, option,
                "Selected option mismatch (Dynamic method)");
    }

    @DataProvider(name = "dropdownData")
    public Object[][] dropdownData() {
        return new Object[][]{
                {"Option 1"},
                {"Option 2"}
        };
    }
}