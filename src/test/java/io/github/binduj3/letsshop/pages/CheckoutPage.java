package io.github.binduj3.letsshop.pages;

import io.github.binduj3.letsshop.utils.ConfigReader;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage extends BasePage {
    @FindBy(xpath = " //a[contains(text(),'Place Order')]")
    WebElement placeOrderBtn;

    @FindBy(xpath = "//div[contains(@class,'user__name')]/input")
    WebElement email;

    @FindBy(xpath = "//div[contains(@class,'user__name')]//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(css = "section.ta-results button")
    List<WebElement> countryOptions;

    public boolean isDisplayed() {
        try {
            waitForUrl(ConfigReader.read("checkoutUrl"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void enterEmail(String registeredEmail) {
        sendText(registeredEmail, email);
    }

    public void selectCountry(String desiredCountry) {
        sendText(desiredCountry.substring(0, 3), country);
        getwait().until(driver -> !countryOptions.isEmpty());
        selectOptionByText(countryOptions, desiredCountry);
        waitForValueToBe(country, desiredCountry);
    }


    public void placeOrder() {
        click(placeOrderBtn);
    }
}
