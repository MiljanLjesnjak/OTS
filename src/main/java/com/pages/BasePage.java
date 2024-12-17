package com.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private static final Logger log = LogManager.getLogger(BasePage.class.getName());


    public WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public String getCurrentUrl(){

        String currentURL =
                driver.getCurrentUrl();

        log.info("Current URL is {}.", currentURL);

        return currentURL;


    }

    public void waitUntilPageTitleIsCorrect(long seconds, String title){
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.titleIs(title));
    }

}
