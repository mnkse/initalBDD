package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverFactory;
import utils.OpenAiFailureAnalyzer;

public class Hooks {

    @Before
    public void setUp() {

        DriverFactory.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {

        try {

            if (scenario.isFailed()) {

                byte[] screenshot =
                        ((TakesScreenshot) DriverFactory.getDriver())
                                .getScreenshotAs(OutputType.BYTES);

                scenario.attach(
                        screenshot,
                        "image/png",
                        scenario.getName()
                );

                OpenAiFailureAnalyzer.analyzeFailure(
                        scenario,
                        "Scenario failed during execution"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Screenshot or AI analysis could not be completed."
            );

        } finally {

            DriverFactory.quitDriver();
        }
    }
}