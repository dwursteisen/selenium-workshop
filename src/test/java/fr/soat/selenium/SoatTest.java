package fr.soat.selenium;

import fr.soat.selenium.utils.Screenshot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * Created by formation on 13/08/14.
 */
public class SoatTest {


    private WebDriver driver;

    private Screenshot screenshot;

    @After
    public void tearDown() throws Exception {
        driver.quit();

    }

    @Before
    public void setUp() throws Exception {
        driver = new PhantomJSDriver();
        screenshot = new Screenshot(driver, this);
    }

    @Test
    public void shoulCheckSeleniumTraining() {
        driver.get("http://www.soat.fr");

        WebElement formation = driver.findElement(By.linkText("Toutes les formations"));
        formation.click();

        Assert.assertEquals("http://www.soat.fr/expertise/formations", driver.getCurrentUrl());

        screenshot.saveAt("formations.png");

        WebElement formationSelenium = driver.findElement(By.linkText("Formation Selenium : Automatiser les tests de vos applications web"));
        formationSelenium.click();

        WebElement eventCode = driver.findElement(By.id("ref")).findElement(By.className("event_meta_value"));
        Assert.assertEquals("SEL01", eventCode.getText());
        screenshot.saveAt("formation.png");

    }
}
