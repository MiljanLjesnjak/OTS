package com.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopMenu extends BasePage {

    private static final Logger log = LogManager.getLogger(TopMenu.class.getName());


    public TopMenu(WebDriver driver){

        super(driver);
    }

    By topMenuButton = By.id("react-burger-menu-btn");

    By logOutButton = By.id("logout_sidebar_link");

    By resetAppStateButton = By.id("reset_sidebar_link");

    public void clickTopMenu(){

        log.info("Clicking on top menu button.");

        driver.findElement(topMenuButton).click();
    }


    public void clickLogoutButton(){

        log.info("Clicking on log out option.");

        driver.findElement(logOutButton).click();
    }

    public void clickResetAppStateButton() {
        log.info("Clicking on reset app state button.");

        driver.findElement(resetAppStateButton).click();
    }
}
