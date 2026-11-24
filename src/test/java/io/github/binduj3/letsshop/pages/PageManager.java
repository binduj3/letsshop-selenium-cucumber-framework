package io.github.binduj3.letsshop.pages;

/**
 * Holds the page objects for ONE scenario.
 * PicoContainer creates a new PageManager for every scenario and gives the same
 * instance to every step class and hook in that scenario, so no static and no ThreadLocal.
 *
 * Pages are created lazily (on first use), which is after the @Before hook has opened
 * the browser, as PageFactory.initElements needs a live driver.
 */
public class PageManager {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private ConfirmationPage confirmationPage;
    private OrderHistoryPage orderHistoryPage;

    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public DashboardPage dashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage();
        }

        return dashboardPage;
    }

    public CartPage cartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }

        return cartPage;
    }
    public CheckoutPage checkoutPage() {
        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage();
        }
        return checkoutPage;
    }

    public ConfirmationPage confirmationPage() {
        if (confirmationPage == null) {
            confirmationPage = new ConfirmationPage();
        }
        return confirmationPage;
    }

    public OrderHistoryPage orderHistoryPage() {
        if (orderHistoryPage == null) {
            orderHistoryPage = new OrderHistoryPage();
        }
        return orderHistoryPage;
    }
}