package io.github.binduj3.letsshop.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static io.github.binduj3.letsshop.driver.DriverManager.getDriver;

public class CommonMethods {

    public void sendText(String text, WebElement element){
        element.clear();
        element.sendKeys(text);
    }

    public void clearAndSendText(String text, WebElement element) {
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        if(text!=null && !text.isBlank()){
            element.sendKeys(text);
        }
    }

    public WebDriverWait getwait(){
        WebDriverWait wait= new WebDriverWait(getDriver(), Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return  wait;
    }


    public void waitForElementToBeClickAble(WebElement element){
        getwait().until(ExpectedConditions.elementToBeClickable(element));
    }


    public void click(WebElement element) {
        waitForSpinnerToDisappear();
        waitForElementToBeClickAble(element);
        int seconds = Integer.parseInt(ConfigReader.readOrDefault("spinnerWait", "40"));
        new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
                .withMessage("Click kept being intercepted for " + seconds + "s")
                .ignoring(ElementClickInterceptedException.class)
                .until(driver -> { element.click(); return true; });
    }
    public void click(WebElement element, boolean clickableAndOptionReady){
        if(clickableAndOptionReady){
            waitForOptionReadyAndClickable(element);
        }
        else {
            waitForElementToBeClickAble(element);
        }

        element.click();
    }

    public void waitForOptionReadyAndClickable(WebElement element) {
        getwait().until(
                ExpectedConditions.and(
                        ExpectedConditions.elementToBeClickable(element),
                        ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, "Searching..."))
                )
        );
    }

    public void waitForTextToBe(WebElement element, String expectedText){
        getwait().until(driver -> element.getText().trim().equalsIgnoreCase(expectedText));
    }

    public void waitForUrl(String url){
        getwait().until(ExpectedConditions.urlContains(url));
    }

    public void waitForElementToBeVisible(WebElement element){
        getwait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public void waitForValueToBePopulated(WebElement element) {
        getwait().until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
    }

    // Generates random number between 1 and 1000
    public int generateRandomNumber() {
        return (int) (Math.random() * 1000) + 1;
    }

    public void selectOptionByText(List<WebElement> options, String optionToSelect)
    {
        List<WebElement> elements = options;
        for(var element : elements){
            if(element.getText().equals(optionToSelect)){
                click(element);
                break;
            }
        }
    }

    public byte[] takeScreenshot(String fileName){

        TakesScreenshot ts = (TakesScreenshot) getDriver();
        byte[] picByte = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile,
                    new File(Constants.SCREENSHOT_FILEPATH +
                            fileName+" "+
                            getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picByte;
    }


    public String getTimeStamp(String pattern){

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public BigDecimal parsePrice(String price)
    {
        return  new BigDecimal(price.replaceAll("[^0-9.]", ""));
    }

       public void waitForSpinnerToDisappear() {
        int seconds = Integer.parseInt(ConfigReader.readOrDefault("spinnerWait", "40"));
        new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
                .withMessage("Loading spinner still visible after " + seconds + "s")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
    }

    public void waitForValueToBe(WebElement input, String expected) {
        getwait()
                .withMessage("Input value never became '" + expected + "'")
                .until(driver -> expected.equalsIgnoreCase(input.getDomProperty("value").trim()));
    }

    public void waitForToast(String expectedText) {
        getwait()
                .withMessage("Toast '" + expectedText + "' did not appear")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#toast-container .toast-message"), expectedText));
    }
 }



