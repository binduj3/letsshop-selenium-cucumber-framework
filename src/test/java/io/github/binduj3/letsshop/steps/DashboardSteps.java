package io.github.binduj3.letsshop.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.binduj3.letsshop.context.TestContext;
import io.github.binduj3.letsshop.pages.PageManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

public class DashboardSteps {
    private final PageManager pages;
    private final TestContext context;

    public DashboardSteps(PageManager pages, TestContext context) {
        this.pages = pages;
        this.context = context;
    }

    @When("the user adds the following products to the cart")
    public void theUserAddsTheFollowingProductsToTheCart(io.cucumber.datatable.DataTable dataTable) {
            List<String> list = dataTable.asList();

            for (String productName: list) {
                BigDecimal price = pages.dashboardPage().getPrice(productName);
                pages.dashboardPage().addToCart(productName);
                context.addProduct(productName, price);
            }
    }

    @Then("the cart badge shows the number of products added")
    public void theCartBadgeShowsTheNumberOfProductsAdded() {
        int expected = context.getProducts().size();
        assertTrue(pages.dashboardPage().cartCountIs(expected),
                "Cart badge should show " + expected);

    }

    @When("the user opens the cart")
    public void theUserOpensTheCart() {
        pages.dashboardPage().proceedToCartPage();
    }
}
