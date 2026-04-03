package com.selenium.java.automation.framework;

import annotations.Author;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ActionsPage;
import pages.LoginPage;
import utils.ConfigReader;

import java.io.IOException;

public class ActionsTest extends BaseTest {

    ActionsPage actionsPage;
    LoginPage loginPage;
    ConfigReader config;

    @BeforeMethod(alwaysRun = true)
    public void login() throws IOException {

        loginPage = new LoginPage();
        config = new ConfigReader();

        loginPage.login(config.getUsername(), config.getPassword());

        Assert.assertTrue(loginPage.isLogoutButtonVisible(),
                "Login failed in setup");

        actionsPage = new ActionsPage();
    }

    //Test 1
    @Author(name = "Taniya Sharma")
    @Test(groups = {"smoke"}, testName = "Hover on First User")
    public void verifyHoverOnFirstUser() {

        getDriver().get("https://the-internet.herokuapp.com/hovers");

        actionsPage.hoverOverUser(1);

        Assert.assertTrue(actionsPage.isCaptionVisible(1));
        Assert.assertTrue(actionsPage.getUserCaption(1).contains("user1"));
    }

    //Test 2
    @Author(name = "Taniya Sharma")
    @Test(groups = "smoke", testName = "Hover on All Users")
    public void verifyHoverOnAllUsers() {

        getDriver().get("https://the-internet.herokuapp.com/hovers");

        for (int i = 1; i <= 3; i++) {
            actionsPage.hoverOverUser(i);

            Assert.assertTrue(actionsPage.isCaptionVisible(i));
            Assert.assertTrue(actionsPage.getUserCaption(i).contains("user" + i));
        }
    }

    //Test 3
    @Author(name = "Taniya Sharma")
    @Test(groups = "regression", testName = "Right Click Context Menu Validation")
    public void verifyContextClick() {

        getDriver().get("https://the-internet.herokuapp.com/context_menu");

        actionsPage.rightClickOnBox();

        String alertText = getDriver().switchTo().alert().getText();
        Assert.assertEquals(alertText, "You selected a context menu");

        getDriver().switchTo().alert().accept();
    }

    //Test 4
    @Author(name = "Taniya Sharma")
    @Test(groups = "smoke", testName = "Drag and Drop Column Swap")
    public void verifyDragAndDrop() {

        getDriver().get("https://the-internet.herokuapp.com/drag_and_drop");

        try {
            actionsPage.dragAndDrop();
        } catch (Exception e) {
            actionsPage.dragAndDropUsingClickHold();
        }

        Assert.assertTrue(actionsPage.getColumnAText().contains("B"));
        Assert.assertTrue(actionsPage.getColumnBText().contains("A"));
    }
}