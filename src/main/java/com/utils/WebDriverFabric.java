package com.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverFabric {

    private static final Logger log = LogManager.getLogger(WebDriverFabric.class.getName());

    private static final String URL = "https://www.saucedemo.com/";

    public static WebDriver startBrowser(String browser){

        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")){

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")){

            WebDriverManager.firefoxdriver().setup();
            driver =  new FirefoxDriver();

        } else {

            throw new RuntimeException("NO SUCH BROWSER!");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        log.info("Starting browser - {}", browser);

        driver.get(URL);

        log.info("Opening page - {}", URL);

        return driver;
    }

    public static  void closeBrowser(WebDriver driver) {

        if (driver != null) {

            log.info("Closing browser.");

            driver.quit();
        }
    }
}
