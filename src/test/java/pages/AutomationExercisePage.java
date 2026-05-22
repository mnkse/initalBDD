package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class AutomationExercisePage extends BasePage {

    By productsButton = By.xpath("//a[@href='/products']");
    By searchBox = By.id("search_product");
    By searchedProductsTitle = By.xpath("//h2[contains(text(),'Searched Products')]");
    By allProductsTitle = By.xpath("//h2[text()='All Products']");
    public AutomationExercisePage(WebDriver driver) {

        super(driver);
    }

    public void openHomePage() {

        driver.get(ConfigReader.getProperty("baseUrl"));
    }


    public void clickProductsPage() {

        try {

            click(productsButton);

            find(allProductsTitle);

        } catch (Exception e) {

            driver.navigate().refresh();

            click(productsButton);

            find(allProductsTitle);
        }

        find(searchBox);
    }

    public void searchProduct(String productName) {

        type(searchBox, productName + Keys.ENTER);
    }

    public boolean isSearchedProductsDisplayed() {

        return isDisplayed(searchedProductsTitle);
    }
}