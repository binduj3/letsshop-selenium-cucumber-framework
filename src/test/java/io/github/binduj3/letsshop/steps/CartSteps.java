package io.github.binduj3.letsshop.steps;

import io.cucumber.java.en.Then;
import io.github.binduj3.letsshop.context.TestContext;
import io.github.binduj3.letsshop.pages.PageManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartSteps {

    private final PageManager pages;
    private final TestContext context;

    public CartSteps(PageManager pages, TestContext context) {
        this.pages = pages;
        this.context = context;
    }

    @Then("the cart shows the selected products with the correct total")
    public void theCartShowsTheSelectedProductsWithTheCorrectTotal() {
        assertTrue(pages.cartPage().isDisplayed(), "Cart page is not displayed");

        assertEquals(pages.cartPage().getItems(), context.productPrices,"Cart items don't match the products added");
        assertEquals(pages.cartPage().totalPrice(), context.getExpectedTotal(),"Cart total is wrong");

        pages.cartPage().proceedToCheckOutPage();
    }
}
