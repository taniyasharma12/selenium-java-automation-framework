package com.selenium.java.automation.framework;

import annotations.Author;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertsPage;
import pages.FileUploadPage;
import pages.LoginPage;
import utils.ConfigReader;

import java.io.IOException;

public class FileUploadTest extends BaseTest {

    FileUploadPage uploadPage;

    LoginPage loginPage;
    ConfigReader config;

    @BeforeMethod(alwaysRun = true)
    public void login() throws IOException {

        loginPage = new LoginPage();
        config = new ConfigReader();

        loginPage.login(config.getUsername(), config.getPassword());

        Assert.assertTrue(loginPage.isLogoutButtonVisible(),
                "Login failed in setup");

        getDriver().get("https://the-internet.herokuapp.com/upload");

        uploadPage = new FileUploadPage();
    }
    @Author(name = "Taniya Sharma")
    @Test(groups = {"smoke"})
    public void verifyFileUpload() {

        String filePath = System.getProperty("user.dir") + "/test-data/sample.txt";

        uploadPage.uploadFile(filePath);
        uploadPage.clickUpload();

        Assert.assertEquals(uploadPage.getUploadedFileName(), "sample.txt");
    }
}