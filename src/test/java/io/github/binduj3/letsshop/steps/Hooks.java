package io.github.binduj3.letsshop.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.binduj3.letsshop.utils.CommonMethods;
import io.github.binduj3.letsshop.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.github.binduj3.letsshop.driver.DriverManager.*;

/**
 * Every scenario gets its own browser on its own thread, opened here and always closed here.
 * Don't call openBrowser() from step definitions (e.g. the Background "navigates to" step),
 * or you'll open a second browser.
 */
public class Hooks extends CommonMethods {
    private static final Logger log = LogManager.getLogger(Hooks.class);
    @Before
    public void setUp(Scenario scenario) {
        log.info("Before Scenario,opening browser {}", scenario.getName());
        openBrowser();
        scenario.log("Browser: " + ConfigReader.readOrDefault("browser", "chrome")
                + " | thread: " + Thread.currentThread().getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed() && hasDriver()) {
                byte[] screenshot = takeScreenshot(scenario.getName());
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        } finally {
            log.info("After Scenario,closing browser {}", scenario.getName());
            closeBrowser();
        }
    }
}
