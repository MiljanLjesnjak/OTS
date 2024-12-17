package com.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage extends BasePage {

    private static final Logger log = LogManager.getLogger(ProductsPage.class.getName());

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public static final String INVENTORY_URL = "inventory.html";
    public static final String CART_URL = "cart.html";
    public static final String CHECKOUT_URL_1 = "checkout-step-one.html";
    public static final String CHECKOUT_URL_2 = "checkout-step-two.html";
    public static final String CHECKOUT_URL_3 = "checkout-complete.html";


    public static final String PAGE_TITLE = "Swag Labs";


    By sortDropdown = By.cssSelector("select[data-test='product-sort-container']");

    By checkoutButton = By.id("checkout");

    By addToCartButton = By.id("add-to-cart-sauce-labs-fleece-jacket");

    By goToCartButton = By.cssSelector("a.shopping_cart_link[data-test='shopping-cart-link']");

    // Checkout
    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By postalCodeField = By.id("postal-code");
    By continueButton = By.id("continue");
    By finishCheckoutButton = By.id("finish");

    public void clickFinishCheckoutButton() {
        driver.findElement(finishCheckoutButton).click();
    }

    public boolean isProductInCart() {
        try {
            return driver.findElement(By.id("item_5_title_link")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void fillInCheckoutForm() {
        driver.findElement(firstNameField).sendKeys("Miljan");

        // Find the last name field and enter the value
        driver.findElement(lastNameField).sendKeys("Ljesnjak");

        // Find the postal code field and enter the value
        driver.findElement(postalCodeField).sendKeys("26101");

        // Click the continue button
        driver.findElement(continueButton).click();
    }

    public void selectSortByPriceLowToHigh() {
        log.info("Selected 'Price (low to high)' from the sort dropdown.");

        Select dropdown = new Select(driver.findElement(sortDropdown));
        dropdown.selectByValue("lohi");
    }

    public void clickCheckoutButton() {
        log.info("Clicked on the Checkout button.");
        driver.findElement(checkoutButton).click();
    }

    public void clickAddToCartButton() {
        log.info("Clicked on the Add to cart button.");
        driver.findElement(addToCartButton).click();
    }

    public void clickGoToCartButton() {
        log.info("Clicked on the cart button.");
        driver.findElement(goToCartButton).click();
    }

}
