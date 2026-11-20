package io.github.binduj3.letsshop.utils;

import io.github.binduj3.letsshop.pages.DashboardPage;
import io.github.binduj3.letsshop.pages.LoginPage;

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
}