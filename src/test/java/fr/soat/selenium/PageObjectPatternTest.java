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
 * Time: 00:11
 */
public class PageObjectPatternTest {

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

        HomePage homePage = new HomePage(driver).go();

        TrainingPage trainingPage = homePage.followTrainings();
        SeleniumPage seleniumPage = trainingPage.followSeleniumTraining();
        assertThat(seleniumPage.getReference()).isEqualTo("SEL01");
    }

    private static class HomePage {

        private final WebDriver driver;


        private HomePage(WebDriver driver) {
            this.driver = driver;
        }

        public HomePage go() {
            driver.get("http://www.soat.fr");
            return this;
        }

        public TrainingPage followTrainings() {
            WebElement formation = driver.findElement(By.linkText("Toutes les formations"));
            formation.click();
            return new TrainingPage(driver);
        }
    }

    private static class TrainingPage {

        private final WebDriver driver;

        private TrainingPage(WebDriver driver) {
            this.driver = driver;
        }

        public SeleniumPage followSeleniumTraining() {
            WebElement formationSelenium = driver.findElement(By.linkText("Formation Selenium : Automatiser les tests de vos applications web"));
            formationSelenium.click();
            return new SeleniumPage(driver);
        }
    }

    private static class SeleniumPage {

        private final WebDriver driver;

        private SeleniumPage(WebDriver driver) {
            this.driver = driver;
        }

        public String getReference() {
            WebElement eventCode = driver.findElement(By.id("ref")).findElement(By.className("event_meta_value"));
            return eventCode.getText();
        }
    }
}
