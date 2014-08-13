package fr.soat.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;

/**
 * Created by formation on 12/08/14.
 */
public class FormulaireTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
//        DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);

        driver = new FirefoxDriver();
    }

    @Test
    public void testRemplirFormulaire() throws Exception {

        driver.get("http://localhost:9090");
        driver.findElement(By.id("name")).sendKeys("John Doe");
        driver.findElement(By.id("password")).sendKeys("Mot de pase");

        driver.findElement(By.className("btn-success")).click();
        Assert.assertTrue(driver.findElement(By.id("popup-ok")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.id("popup-ko")).isDisplayed());

        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Et là, c'est le drame");
        alert.accept();

        String formHandler = driver.getWindowHandle();
        driver.findElement(By.id("new_frame")).click();
        Set<String> handlers = driver.getWindowHandles();
        handlers.remove(formHandler);
        driver.switchTo().window(handlers.iterator().next());
        Assert.assertEquals("phpinfo()", driver.getTitle());
        driver.switchTo().window(formHandler);
        Assert.assertEquals("Workshop Selenium", driver.getTitle());
        driver.quit();
    }
}
