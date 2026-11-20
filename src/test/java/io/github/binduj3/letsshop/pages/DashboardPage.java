package io.github.binduj3.letsshop.pages;

import io.github.binduj3.letsshop.utils.ConfigReader;
import org.openqa.selenium.TimeoutException;

public class DashboardPage extends BasePage {
    public boolean isDisplayed() {
        try {
            waitForUrl(ConfigReader.read("dashboardUrl"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
