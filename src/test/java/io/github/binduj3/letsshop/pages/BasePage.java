package io.github.binduj3.letsshop.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.github.binduj3.letsshop.utils.CommonMethods;
import io.github.binduj3.letsshop.utils.DriverManager;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage extends CommonMethods {

    protected final Logger log = LogManager.getLogger(getClass());
    BasePage() {
        PageFactory.initElements(DriverManager.getDriver(),this);
    }
}


