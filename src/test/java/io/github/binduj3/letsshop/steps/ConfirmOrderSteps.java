package io.github.binduj3.letsshop.steps;

import io.cucumber.java.en.Then;
import io.github.binduj3.letsshop.context.TestContext;
import io.github.binduj3.letsshop.pages.PageManager;
import io.restassured.internal.http.ContentTypeExtractor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfirmOrderSteps {
    private final PageManager pages;
    private final TestContext context;

    public ConfirmOrderSteps(PageManager pages, TestContext context) {
        this.pages = pages;
        this.context = context;
    }

    @Then("the order confirmation shows one order ID per product")
    public void theOrderConfirmationShowsOneOrderIdPerProduct() {
     assertTrue(pages.confirmationPage().isDisplayed());
     var orderIds = pages.confirmationPage().getOrderIds();
     context.addOrderIds(orderIds);

     assertEquals(context.getOrderIds().size(),context.getProducts().size());
     pages.confirmationPage(). proceedToOrderHistory();
    }
}
