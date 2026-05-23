package utils;

import io.cucumber.java.Scenario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AiFailureAnalyzer {

    public static void generateFailureSummary(Scenario scenario, Throwable error) {

        if (!scenario.isFailed()) {
            return;
        }

        File directory = new File("target/ai-analysis");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, "failure-summary.txt");

        try (FileWriter writer = new FileWriter(file, true)) {

            writer.write("AI-Ready Failure Analysis\n");
            writer.write("=========================\n");
            writer.write("Time: " + LocalDateTime.now() + "\n");
            writer.write("Scenario: " + scenario.getName() + "\n");
            writer.write("Status: FAILED\n");

            if (error != null) {
                writer.write("Error Type: " + error.getClass().getSimpleName() + "\n");
                writer.write("Error Message: " + error.getMessage() + "\n");
            }

            writer.write("\nPossible Root Causes:\n");
            writer.write("- Element locator may be unstable\n");
            writer.write("- Page may not be fully loaded\n");
            writer.write("- Headless browser behavior may differ from local execution\n");
            writer.write("- Test data or environment may have changed\n");

            writer.write("\nSuggested Actions:\n");
            writer.write("- Check screenshot attachment\n");
            writer.write("- Review locator strategy\n");
            writer.write("- Add explicit wait or JS click if needed\n");
            writer.write("- Compare local vs CI execution logs\n");

            writer.write("\n--------------------------------------\n\n");

        } catch (IOException e) {
            System.out.println("AI failure summary could not be generated.");
        }
    }
}