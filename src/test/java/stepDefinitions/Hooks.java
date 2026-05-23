package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.AiFailureAnalyzer;
import utils.DriverFactory;

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

                AiFailureAnalyzer.generateFailureSummary(
                        scenario,
                        new RuntimeException("Scenario failed")
                );
            }

        } catch (Exception e) {

            System.out.println("Screenshot could not be taken.");

        } finally {

            DriverFactory.quitDriver();
        }
    }
}