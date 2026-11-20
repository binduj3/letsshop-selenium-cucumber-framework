package io.github.binduj3.letsshop.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.binduj3.letsshop.utils.ConfigReader;
import io.github.binduj3.letsshop.utils.PageManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private final PageManager pages;

    public LoginSteps(PageManager pages) {
        this.pages = pages;
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        assertTrue(pages.loginPage().isDisplayed(), "Login page is not displayed");
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        pages.loginPage().login(ConfigReader.read("email"), ConfigReader.read("LETSSHOP_PASSWORD"));
    }

    @Then("the user is taken to dashboard page")
    public void theUserIsTakenToDashboardPage() {
        assertTrue(pages.dashboardPage().isDisplayed(), "Dashboard page is not displayed");
    }
}
