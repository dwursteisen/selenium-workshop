package fr.soat.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * User: david
 * Date: 10/04/2014
 * Time: 00:07
 */
public class WebDriverTest {


    private WebDriver driver;

    @After
    public void tearDown() throws Exception {
        driver.close();

    }

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @Test
    public void shoulCheckSeleniumTraining() {
        driver.get("http://www.soat.fr");

        WebElement formation = driver.findElement(By.linkText("Toutes les formations"));
        formation.click();

        assertThat(driver.getCurrentUrl()).isEqualTo("http://www.soat.fr/expertise/formations");

        WebElement formationSelenium = driver.findElement(By.linkText("Formation Selenium : Automatiser les tests de vos applications web"));
        formationSelenium.click();

        WebElement eventCode = driver.findElement(By.id("ref")).findElement(By.className("event_meta_value"));
        assertThat(eventCode.getText()).isEqualTo("SEL01");
    }

}
