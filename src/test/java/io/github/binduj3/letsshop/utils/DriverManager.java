package io.github.binduj3.letsshop.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;

/**
 * The only class that creates, holds and quits browsers.
 * One browser per thread, so parallel scenarios never share one.
 */
public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final Duration GRID_STARTUP_TIMEOUT = Duration.ofSeconds(90);

    private DriverManager() {
    }

    /** This thread's browser. Fails clearly if @Before didn't open one. */
    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("No browser on thread " + Thread.currentThread().getName()
                    + " - did the @Before hook call DriverManager.openBrowser()?");
        }
        return driver;
    }

    public static boolean hasDriver() {
        return DRIVER.get() != null;
    }

    /** Starts the configured browser (local or Grid) and opens the app URL. */
    public static void openBrowser() {
        String browser = ConfigReader.readOrDefault("browser", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfigReader.readOrDefault("headless", "false"));
        String gridUrl = ConfigReader.readOrDefault("gridUrl", "");

        MutableCapabilities options = buildOptions(browser, headless);
        WebDriver driver = gridUrl.isBlank() ? startLocal(browser, options) : startOnGrid(gridUrl, options);
        DRIVER.set(driver);

        // Headless and Grid browsers have no screen to maximize to, so give them a fixed size
        if (headless || !gridUrl.isBlank()) {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } else {
            driver.manage().window().maximize();
        }
        driver.get(ConfigReader.read("url"));
    }

    /** Quits the browser and always frees this thread's slot (threads are reused). */
    public static void closeBrowser() {
        try {
            if (DRIVER.get() != null) {
                DRIVER.get().quit();
            }
        } finally {
            DRIVER.remove();
        }
    }

    private static MutableCapabilities buildOptions(String browser, boolean headless) {
        return switch (browser) {
            case "chrome" -> chromium(new ChromeOptions(), headless);
            case "edge" -> chromium(new EdgeOptions(), headless);
            case "firefox" -> headless ? new FirefoxOptions().addArguments("-headless") : new FirefoxOptions();
            default -> throw new IllegalArgumentException("Unsupported browser '" + browser + "'. Use chrome, edge or firefox.");
        };
    }

    /** Chrome and Edge are both Chromium, so they share the same settings. */
    private static <T extends ChromiumOptions<T>> T chromium(T options, boolean headless) {
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage"); // needed in Docker/CI
        options.setExperimentalOption("prefs", Map.of(                    // no save-password popups
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false));
        if (headless) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private static WebDriver startLocal(String browser, MutableCapabilities options) {
        return switch (browser) {
            case "chrome" -> new ChromeDriver((ChromeOptions) options);
            case "edge" -> new EdgeDriver((EdgeOptions) options);
            case "firefox" -> new FirefoxDriver((FirefoxOptions) options);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    /** Grid containers take a few seconds to start, so retry for up to 90 seconds. */
    private static WebDriver startOnGrid(String gridUrl, MutableCapabilities options) {
        long deadline = System.nanoTime() + GRID_STARTUP_TIMEOUT.toNanos();
        while (true) {
            try {
                RemoteWebDriver driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), options);
                driver.setFileDetector(new LocalFileDetector()); // lets file uploads work on Grid
                return driver;
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Bad gridUrl: " + gridUrl, e);
            } catch (WebDriverException e) {
                if (System.nanoTime() > deadline) {
                    throw new WebDriverException("Selenium Grid at " + gridUrl + " not ready after "
                            + GRID_STARTUP_TIMEOUT.toSeconds() + "s", e);
                }
                sleepTwoSeconds();
            }
        }
    }

    private static void sleepTwoSeconds() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for Selenium Grid", e);
        }
    }
}