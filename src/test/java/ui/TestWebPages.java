package ui;

import com.pages.LoginPage;
import com.pages.ProductsPage;
import com.pages.TopMenu;
import com.utils.WebDriverFabric;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

public class TestWebPages {

    private static final Logger log = LogManager.getLogger(TestWebPages.class);

    private WebDriver driver = null;
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";
    public static final String INVALID_USERNAME = "invalid_user";
    public static final String INVALID_PASSWORD = "invalid_password";

    public static final String CHECKOUT_NAME = "Miljan";
    public static final String CHECKOUT_LASTNAME = "Ljesnjak";
    public static final String CHECKOUT_POSTAL_CODE = "26101";

    @DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData() {
        return new Object[][]{
                {INVALID_USERNAME, PASSWORD, "Epic sadface: Username and password do not match any user in this service"},
                {USERNAME, INVALID_PASSWORD, "Epic sadface: Username and password do not match any user in this service"},
                {INVALID_USERNAME, INVALID_PASSWORD, "Epic sadface: Username and password do not match any user in this service"},
                {"", INVALID_PASSWORD, "Epic sadface: Username is required"},
                {USERNAME, "", "Epic sadface: Password is required"}
        };
    }

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

    @Test(dataProvider = "invalidLoginData")
    public void testInvalidLogin(String username, String password, String errorMessage) {

        log.info("************* Test Started: Invalid Login *************");

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeOnUsernameFieldName(username);
        loginPage.typeOnPasswordField(password);
        loginPage.clickOnLoginButton();

        assertTrue(loginPage.isErrorVisible(), "Error not visible.");
        assertTrue(loginPage.getErrorMessage().contains(errorMessage), "Error not containing expected error message");

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

        productsPage.fillInCheckoutForm(CHECKOUT_NAME, CHECKOUT_LASTNAME, CHECKOUT_POSTAL_CODE);
        
        assertTrue(productsPage.getCurrentUrl().contains(ProductsPage.CHECKOUT_URL_2), "Failed filling checkout info.");
        assertTrue(productsPage.isProductInCart(), "Product is not in cart.");

        productsPage.clickFinishCheckoutButton();
        assertTrue(productsPage.getCurrentUrl().contains(ProductsPage.CHECKOUT_URL_3), "Failed checkout finish.");


        log.info("************* Test Ended: Purchase Process *************");
    }
}
