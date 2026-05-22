package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.AutomationExercisePage;
import utils.DriverFactory;

public class AutomationExerciseSteps {

    WebDriver driver;
    AutomationExercisePage automationPage;

    @Given("user is on automation exercise home page")
    public void user_is_on_automation_exercise_home_page() {

        driver = DriverFactory.getDriver();

        automationPage = new AutomationExercisePage(driver);

        automationPage.openHomePage();
    }

    @When("user navigates to products page")
    public void user_navigates_to_products_page() {

        automationPage.clickProductsPage();
    }

    @When("user searches for {string}")
    public void user_searches_for(String productName) {

        automationPage.searchProduct(productName);
    }

    @Then("searched products should be displayed")
    public void searched_products_should_be_displayed() {

        String actualTitle = automationPage.getPageTitle();

        Assert.assertTrue(actualTitle.contains("Automation Exercise"));

        DriverFactory.quitDriver();
    }
}