package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static void takeScreenshot(String screenshotName) {

        File sourceFile =
                ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.FILE);

        File destinationFile =
                new File("target/screenshots/" + screenshotName + ".png");

        try {

            FileUtils.copyFile(sourceFile, destinationFile);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}