package io.github.binduj3.letsshop.pages;

import io.github.binduj3.letsshop.utils.ConfigReader;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    @FindBy(id = "userEmail")
    public WebElement emailField;

    @FindBy(id = "userPassword")
    public WebElement passwordField;

    @FindBy(id = "login")
    public WebElement loginButton;

    public void login(String email, String password) {
        sendText(email, emailField);
        sendText(password, passwordField);
        click(loginButton);
    }

    public boolean isDisplayed() {
        try {
            waitForUrl(ConfigReader.read("url"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
