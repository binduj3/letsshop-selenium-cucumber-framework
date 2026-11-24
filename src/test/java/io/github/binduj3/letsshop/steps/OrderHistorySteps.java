package io.github.binduj3.letsshop.steps;

import io.cucumber.java.en.Then;
import io.github.binduj3.letsshop.context.TestContext;
import io.github.binduj3.letsshop.pages.PageManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderHistorySteps {

    private final PageManager pages;
    private final TestContext context;

    public OrderHistorySteps(PageManager pages, TestContext context) {
        this.pages = pages;
        this.context =context;
    }

    @Then("the orders appear in the order history")
    public void theOrdersAppearInTheOrderHistory() {
        assertTrue(pages.orderHistoryPage().isDisplayed());

      List<String> ids=  pages.orderHistoryPage().getOrderIds();
      assertTrue(ids.containsAll(context.getOrderIds()));

    }
}
