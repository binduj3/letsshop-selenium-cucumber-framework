package io.github.binduj3.letsshop.steps;

import io.cucumber.java.en.When;
import io.github.binduj3.letsshop.context.TestContext;
import io.github.binduj3.letsshop.pages.PageManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutSteps {

    private final PageManager pages;
    private final TestContext context;

    public CheckoutSteps(PageManager pages, TestContext context) {
        this.pages = pages;
        this.context = context;
    }

    @When("the user checks out to {string} and places the order")
    public void theUserChecksOutAndPlacesTheOrder(String country) {
        assertTrue(pages.checkoutPage().isDisplayed(), "Checkout page is not displayed");

        pages.checkoutPage().enterEmail(context.getRegisteredEmail());
        pages.checkoutPage().selectCountry(country);

        pages.checkoutPage().placeOrder();
    }
}
