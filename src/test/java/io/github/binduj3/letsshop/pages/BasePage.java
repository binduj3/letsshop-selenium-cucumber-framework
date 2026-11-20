package io.github.binduj3.letsshop.pages;

import io.github.binduj3.letsshop.utils.CommonMethods;
import io.github.binduj3.letsshop.utils.DriverManager;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends CommonMethods {

    BasePage() {
        PageFactory.initElements(DriverManager.getDriver(),this);
    }
}
