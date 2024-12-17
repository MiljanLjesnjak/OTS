package ui;

import com.pages.LoginPage;
import com.pages.ProductsPage;
import com.pages.TopMenu;
import com.utils.WebDriverFabric;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestWebPages {

    private static final Logger log = LogManager.getLogger(TestWebPages.class);

    private WebDriver driver = null;
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";
    public static final String INVALID_USERNAME = "invalid_user";
    public static final String INVALID_PASSWORD = "invalid_password";

    @Parameters({"browser"})
    @BeforeMethod
    public void startBrowser(String browser){

        driver = WebDriverFabric.startBrowser(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){

        WebDriverFabric.closeBrowser(driver);
    }



    @Test
    public void testValidLogin(){

        log.info("************* Test Started *************");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeOnUsernameFieldName(USERNAME);

        loginPage.typeOnPasswordField(PASSWORD);

        loginPage.clickOnLoginButton();

        assertTrue(loginPage.getCurrentUrl().contains(ProductsPage.INVENTORY_URL),
                "URL is not changed. User is not logged in!");

        TopMenu topMenu = new TopMenu(driver);

        topMenu.clickTopMenu();

        topMenu.clickLogoutButton();

        assertTrue(loginPage.isLoginButtonVisible(),
                "Login button is not visible on a page");

        log.info("************* Test Ended *************");
    }

    @Test
    public void testInvalidLogin() {

        log.info("************* Test Started: Invalid Login *************");

        LoginPage loginPage = new LoginPage(driver);

        // 1) INVALID USERNAME - VALID PASSWORD
        loginPage.typeOnUsernameFieldName(INVALID_USERNAME);
        loginPage.typeOnPasswordField(PASSWORD);
        loginPage.clickOnLoginButton();

        assertTrue(loginPage.getErrorMessage()
                .contains("Epic sadface: Username and password do not match any user in this service"));
        //-------------------------------------


        // 2) VALID USERNAME - INVALID PASSWORD
        loginPage.typeOnUsernameFieldName(USERNAME);
        loginPage.typeOnPasswordField(INVALID_PASSWORD);
        loginPage.clickOnLoginButton();

        assertTrue(loginPage.getErrorMessage()
                .contains("Epic sadface: Username and password do not match any user in this service"));
        //-------------------------------------


        // 3) INVALID USERNAME - INVALID PASSWORD
        loginPage.typeOnUsernameFieldName(INVALID_USERNAME);
        loginPage.typeOnPasswordField(INVALID_PASSWORD);
        loginPage.clickOnLoginButton();

        assertTrue(loginPage.getErrorMessage()
                .contains("Epic sadface: Username and password do not match any user in this service"));
        //-------------------------------------


        // 4) NO USERNAME
        loginPage.clearUsernameField();
        loginPage.typeOnPasswordField(INVALID_PASSWORD);
        loginPage.clickOnLoginButton();

        log.error(loginPage.getErrorMessage());
        assertTrue(loginPage.getErrorMessage().contains("Epic sadface: Username is required"));
        //-------------------------------------

        // 4) NO PASSWORD
        loginPage.typeOnUsernameFieldName(INVALID_USERNAME);
        loginPage.clearPasswordField();
        loginPage.clickOnLoginButton();

        assertTrue(loginPage.getErrorMessage().contains("Epic sadface: Password is required"));
        //-------------------------------------

        // 4) NO USERNAME, NO PASSWORD
        loginPage.clearUsernameField();
        loginPage.clearPasswordField();
        loginPage.clickOnLoginButton();

        assertTrue(loginPage.getErrorMessage().contains("Epic sadface: Username is required"));
        //-------------------------------------




        log.info("************* Test Ended: Invalid Login *************");
    }

    @Test
    public void testPurchase() {
        log.info("************* Test Started: Purchase *************");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeOnUsernameFieldName(USERNAME);
        loginPage.typeOnPasswordField(PASSWORD);
        loginPage.clickOnLoginButton();
        assertTrue(loginPage.getCurrentUrl().contains(ProductsPage.INVENTORY_URL),
                "URL is not changed. User is not logged in!");

        // Adding to and going to cart
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.clickAddToCartButton();
        productsPage.clickGoToCartButton();
        assertTrue(productsPage.getCurrentUrl().contains(ProductsPage.CART_URL),  "Failed going to cart.");
        assertTrue(productsPage.isProductInCart(), "Product is not in cart.");

        // Checking out
        productsPage.clickCheckoutButton();
        assertTrue(productsPage.getCurrentUrl().contains(ProductsPage.CHECKOUT_URL_1), "Failed going to checkout.");

        productsPage.fillInCheckoutForm();
        assertTrue(productsPage.getCurrentUrl().contains(ProductsPage.CHECKOUT_URL_2), "Failed filling checkout info.");
        assertTrue(productsPage.isProductInCart(), "Product is not in cart.");

        productsPage.clickFinishCheckoutButton();
        assertTrue(productsPage.getCurrentUrl().contains(ProductsPage.CHECKOUT_URL_3), "Failed checkout finish.");


        log.info("************* Test Ended: Purchase Process *************");
    }
}
