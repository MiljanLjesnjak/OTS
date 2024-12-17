package com.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger(LoginPage.class.getName());

    public LoginPage(WebDriver driver){

        super(driver);

    }

    private final By usernameField  = By.id("user-name");

    private final By passwordField = By.cssSelector("input[type='password");

    private final By loginButton = By.name("login-button");

    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public void typeOnUsernameFieldName(String username){

        WebElement nameField =
                driver.findElement(this.usernameField);

        nameField.sendKeys(username);

        log.info("Typing username - {}", username );
    }


    public void typeOnPasswordField(String password){

        WebElement passField =
                driver.findElement(this.passwordField);

        passField.sendKeys(password);

        log.info("Typing password - {}", password );
    }

    public void clickOnLoginButton(){

        WebElement logInButtonCSS =
                driver.findElement(this.loginButton);

        log.info("Clicking on login button.");

        logInButtonCSS.click();
    }

    public boolean isLoginButtonVisible(){

        return driver.findElement(this.loginButton).isDisplayed();
    }

    public boolean isErrorVisible() {
        return driver.findElement(this.errorMessage).isDisplayed();
    }

    public String getErrorMessage() {
            return driver.findElement(errorMessage).getText();
    }
}
