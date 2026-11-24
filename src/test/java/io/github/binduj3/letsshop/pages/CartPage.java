package io.github.binduj3.letsshop.pages;

import io.github.binduj3.letsshop.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CartPage extends BasePage{
    @FindBy(xpath="//button[normalize-space()='Checkout']")
    WebElement checkout;

    @FindBy(css ="div.infoWrap")
    List<WebElement> addedItemsWrappers;

    @FindBy(xpath = "//li[contains(@class,'totalRow')]/span[text()='Total']/following-sibling::span")
    WebElement totalPrice;

    public boolean isDisplayed() {
        try {
            waitForUrl(ConfigReader.read("cartUrl"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void proceedToCheckOutPage()
    {
     click(checkout);
    }

    public Map<String, BigDecimal> getItems()
    {   getwait().until(driver -> !addedItemsWrappers.isEmpty());
        Map<String, BigDecimal> items = new LinkedHashMap<>();
        for (var wrapper: addedItemsWrappers)
        {
       BigDecimal price= parsePrice(wrapper.findElement(By.cssSelector(".prodTotal ")).getText());
       String productName = wrapper.findElement(By.xpath("./div[contains(@class,'cartSection')]/h3")).getText();

       items.put(productName, price);
        }
        System.out.println(items);
        return items;
    }

    public BigDecimal totalPrice()
    {
        return parsePrice(totalPrice.getText());
    }
}
