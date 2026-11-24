package io.github.binduj3.letsshop.pages;

import io.github.binduj3.letsshop.utils.ConfigReader;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.latest.runtime.Runtime;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryPage extends BasePage {

    @FindBy(xpath ="//tr/th[@scope='row']")
    List<WebElement> orderIds;

    public boolean isDisplayed() {
        try {
            waitForUrl(ConfigReader.read("orderHistoryUrl"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public List<String> getOrderIds() {

        getwait().withMessage("No orders shown in Order History")
                .until(driver -> !orderIds.isEmpty());

        List<String> orderIdsList = new ArrayList<>();
        for (var el:  orderIds) {
            orderIdsList.add(el.getText().trim());
        }
        return orderIdsList;
    }

}