package io.github.binduj3.letsshop.pages;

import io.github.binduj3.letsshop.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;

public class DashboardPage extends BasePage {

    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
    public WebElement cartLink;

    public boolean isDisplayed() {
        try {
            waitForUrl(ConfigReader.read("dashboardUrl"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void addToCart(String productName)
    {
        WebElement cardBody = getCardBody(productName);
        WebElement addToCart = cardBody.findElement(By.xpath(".//button[normalize-space()='Add To Cart']"));
        click(addToCart);
        waitForToast("Product Added To Cart");
        waitForSpinnerToDisappear();
    }

    public BigDecimal getPrice(String productName)
    {
      WebElement cardBody = getCardBody(productName);
      WebElement priceElement=  cardBody.findElement(By.xpath(".//div[contains(@class,'text-muted')]"));
      return parsePrice(priceElement.getText());
    }

    public WebElement getCardBody (String productName)
    {
      return getwait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[normalize-space()='"+productName+"']/ancestor::div[contains(@class,'card-body')]")));
    }


    public boolean cartCountIs(int expected) {
        try {
            waitForTextToBe(cartLink.findElement(By.xpath(".//label")), String.valueOf(expected));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

  public void proceedToCartPage()
  {
      click(cartLink);
  }



}
