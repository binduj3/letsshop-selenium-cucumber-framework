package io.github.binduj3.letsshop.pages;

import io.github.binduj3.letsshop.utils.ConfigReader;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ConfirmationPage extends BasePage {
    @FindBy(xpath="//td//label[@class='ng-star-inserted']")
    List<WebElement> orderIds;

    @FindBy(xpath = "//label[text()=' Orders History Page ']")
    WebElement ordersHistoryLink;

        public boolean isDisplayed() {
            try {
                waitForUrl(ConfigReader.read("confirmationUrl"));
                return true;
            } catch (TimeoutException e) {
                return false;
            }
        }

        public List<String> getOrderIds() {
            List<String> ids = new ArrayList<>();
            for (var element: orderIds) {
                ids.add(((element.getText()).replace("|","")).trim());
            }
         return ids;
        }

        public void proceedToOrderHistory() {
            click(ordersHistoryLink);
        }
    }

