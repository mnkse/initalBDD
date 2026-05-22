package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (driver.get() == null) {

            String browser = System.getProperty(
                    "browser",
                    ConfigReader.getProperty("browser")
            );

            String headless = System.getProperty(
                    "headless",
                    ConfigReader.getProperty("headless")
            );

            switch (browser.toLowerCase()) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();

                    ChromeOptions chromeOptions = new ChromeOptions();
                    addCommonChromeOptions(chromeOptions, headless);

                    driver.set(new ChromeDriver(chromeOptions));
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();

                    EdgeOptions edgeOptions = new EdgeOptions();
                    addCommonEdgeOptions(edgeOptions, headless);

                    driver.set(new EdgeDriver(edgeOptions));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();

                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    if (headless.equalsIgnoreCase("true")) {
                        firefoxOptions.addArguments("-headless");
                    }

                    driver.set(new FirefoxDriver(firefoxOptions));

                    if (!headless.equalsIgnoreCase("true")) {
                        driver.get().manage().window().maximize();
                    }

                    break;

                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
        }

        return driver.get();
    }

    private static void addCommonChromeOptions(
            ChromeOptions options,
            String headless
    ) {

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");

        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }
    }

    private static void addCommonEdgeOptions(
            EdgeOptions options,
            String headless
    ) {

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");

        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();
            driver.remove();
        }
    }
}