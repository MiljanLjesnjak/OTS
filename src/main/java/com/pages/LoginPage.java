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



    public void clearUsernameField() {
        log.info("Clearing username field");

        WebElement usernameFieldElement = driver.findElement(usernameField);

        // Brisemo ovako jer .clear() ne radi?
        String usernameValue = usernameFieldElement.getAttribute("value");
        for (int i = 0; i < usernameValue.length(); i++) {
            usernameFieldElement.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void clearPasswordField() {
        log.info("Clearing password field");

        WebElement passwordFieldElement = driver.findElement(passwordField);

        // Brisemo ovako jer .clear() ne radi?
        String usernameValue = passwordFieldElement.getAttribute("value");
        for (int i = 0; i < usernameValue.length(); i++) {
            passwordFieldElement.sendKeys(Keys.BACK_SPACE);
        }


    }

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
        try {
            return driver.findElement(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
