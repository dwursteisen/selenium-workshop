package fr.soat.selenium.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by formation on 13/08/14.
 */
public class Screenshot {
    private final Class<? extends Object> clazz;
    private WebDriver driver;

    public Screenshot(WebDriver driver, Object testClass) {
        this.driver = driver;
        this.clazz = testClass.getClass();
    }

    public void saveAt(String filename) {
        filename = this.clazz.getSimpleName() + "-" + filename;
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotAs, new File(filename));
        } catch (IOException e) {
            ; // do nothing
        }
    }
}
