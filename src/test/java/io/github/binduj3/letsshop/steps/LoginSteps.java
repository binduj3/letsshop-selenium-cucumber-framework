package io.github.binduj3.letsshop.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.binduj3.letsshop.context.TestContext;
import io.github.binduj3.letsshop.utils.ConfigReader;
import io.github.binduj3.letsshop.pages.PageManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private final PageManager pages;
    private final TestContext context;

    public LoginSteps(PageManager pages, TestContext context) {
        this.pages = pages;
        this.context =context;
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        assertTrue(pages.loginPage().isDisplayed(), "Login page is not displayed");
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        String email = ConfigReader.read("email");
        context.setRegisteredEmail(email);
        pages.loginPage().login(email, ConfigReader.read("LETSSHOP_PASSWORD"));
    }

    @Then("the user is taken to dashboard page")
    public void theUserIsTakenToDashboardPage() {
        assertTrue(pages.dashboardPage().isDisplayed(), "Dashboard page is not displayed");
    }

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        String email = ConfigReader.read("email");
        context.setRegisteredEmail(email);
        pages.loginPage().login(email, ConfigReader.read("LETSSHOP_PASSWORD"));
    }
}
