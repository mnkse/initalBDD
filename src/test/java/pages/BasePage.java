package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {

        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public WebElement waitForVisible(By locator) {

        return waitUtils.waitForVisible(locator);
    }

    public WebElement waitForClickable(By locator) {

        return waitUtils.waitForClickable(locator);
    }

    public WebElement waitForPresence(By locator) {

        return waitUtils.waitForPresence(locator);
    }

    public WebElement find(By locator) {

        return waitForVisible(locator);
    }

    public void click(By locator) {

        waitForClickable(locator).click();
    }

    public void jsClick(By locator) {

        WebElement element = waitForVisible(locator);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    public void type(By locator, String text) {

        waitForVisible(locator).sendKeys(text);
    }

    public void clearAndType(By locator, String text) {

        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void pressEnter(By locator) {

        waitForVisible(locator).sendKeys(Keys.ENTER);
    }

    public String getText(By locator) {

        return waitForVisible(locator).getText();
    }

    public boolean isDisplayed(By locator) {

        return waitForVisible(locator).isDisplayed();
    }

    public boolean isElementPresent(By locator) {

        try {

            waitForVisible(locator);
            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public void scrollToElement(By locator) {

        WebElement element = waitForVisible(locator);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getPageTitle() {

        return driver.getTitle();
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }

    public void refreshPage() {

        driver.navigate().refresh();
    }

    public void waitForSeconds(int seconds) {

        try {

            Thread.sleep(seconds * 1000L);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}