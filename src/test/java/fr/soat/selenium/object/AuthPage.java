package fr.soat.selenium.object;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;

/**
 * Created by formation on 13/08/14.
 */
public class AuthPage {

    private final Screenshoot record;
    private final WebDriver driver;
    @FindBy(id = "user_id")
    private WebElement user;
    @FindBy(id = "user_pwd")
    private WebElement password;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        record = new Screenshoot(driver);
    }

    public void login(String user, String password) {
        this.user.sendKeys(user);
        this.password.sendKeys(password);

    }

    public void perform() {
        this.password.submit();
    }

    public void screenshot(String filename) {
        record.saveAt(filename);
    }

    public static class Screenshoot {
        private WebDriver driver;

        public Screenshoot(WebDriver driver) {
            this.driver = driver;
        }

        public void saveAt(String filename) {
            File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotAs, new File(filename));
            } catch (IOException e) {
                ; // do nothing
            }
        }
    }
}
