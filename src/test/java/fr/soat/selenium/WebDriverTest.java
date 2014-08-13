package fr.soat.selenium;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * User: david
 * Date: 10/04/2014
 * Time: 00:07
 */
public class WebDriverTest {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
//        DesiredCapabilities capability = DesiredCapabilities.phantomjs();
//
//
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);

//
//
//        System.out.println(capability.getCapability("takesScreenshot"));


        driver = new ChromeDriver();

        baseUrl = "http://localhost/dotclear/admin/auth.php";
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Test
    public void testExportWebDriver() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("user_id")).clear();
        driver.findElement(By.id("user_id")).sendKeys("azerty");
        driver.findElement(By.id("user_pwd")).clear();
        driver.findElement(By.id("user_pwd")).sendKeys("azerty");
        driver.findElement(By.id("user_remember")).click();

        screenshot("./login.png");

        driver.findElement(By.cssSelector("input.login")).click();


    }

    private void screenshot(String fileName) throws IOException {
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotAs, new File(fileName));
    }

    // drapg & drop
    @Test
    public void testName() throws Exception {
        testExportWebDriver();

        driver.findElement(By.linkText("Presentation widgets")).click();
        List<WebElement> li = driver.findElements(By.xpath("//li[@class='ui-draggable']"));
        WebElement gotchat = null;
        for (WebElement webElement : li) {
            if (webElement.getText().contains("Simple menu")) {
                gotchat = webElement;
            }
        }

        new Actions(driver).dragAndDrop(gotchat, driver.findElement(By.id("dndnav"))).perform();
        screenshot("./menuUpdate.png");
        driver.findElement(By.name("wup")).click();

        driver.findElement(By.linkText("Go to site")).click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        iterator.next();
        driver.switchTo().window(iterator.next());
        screenshot("./BlogMenuUpdate.png");

    }


    @Test
    public void testNameCommentaire() throws Exception {
        testExportWebDriver();
        driver.findElement(By.linkText("New entry")).click();
        driver.findElement(By.id("post_title")).sendKeys("Hello tout le monde LE RETOUR");
        driver.findElement(By.id("post_content")).sendKeys("Ceci est un long texte...");
        new Select(driver.findElement(By.id("post_status"))).selectByValue("1");

        screenshot("./beforeSave.png");
        new Actions(driver).sendKeys(Keys.chord(Keys.ALT, "s")).perform();
        screenshot("./afterSave.png");
        String windowHandle = driver.getWindowHandle();

        driver.findElement(By.className("onblog_link")).click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        iterator.next();
        driver.switchTo().window(iterator.next());
        assertEquals("Hello tout le monde LE RETOUR", driver.findElement(By.className("post-title")).getText());
        Commentaire commentaire = PageFactory.initElements(driver, Commentaire.class);
        commentaire.fillCommentaire("name", "email@gmail.com", "blablabla");
        screenshot("./commentaire.png");
        commentaire.deleteCommentaire("Hello tout le monde LE RETOUR");
    }

    @Test
    public void testNamebackup() throws Exception {
        testExportWebDriver();
        driver.findElement(By.linkText("New entry")).click();
        driver.findElement(By.id("post_title")).sendKeys("Hello tout le monde ");
        driver.findElement(By.id("post_content")).sendKeys("Ceci est un long texte...");
        new Select(driver.findElement(By.id("post_status"))).selectByValue("1");

        screenshot("./beforeSave.png");
        new Actions(driver).sendKeys(Keys.chord(Keys.ALT, "s")).perform();
        screenshot("./afterSave.png");
        String windowHandle = driver.getWindowHandle();

        driver.findElement(By.className("onblog_link")).click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        iterator.next();
        driver.switchTo().window(iterator.next());
        assertEquals("Hello tout le monde", driver.findElement(By.className("post-title")).getText());
        screenshot("./blogEntry.png");

        driver.switchTo().window(windowHandle);
        driver.findElement(By.linkText("Presentation widgets")).click();
        List<WebElement> li = driver.findElements(By.xpath("//li[@class='ui-draggable']"));
        WebElement gotchat = null;
        for (WebElement webElement : li) {
            if (webElement.getText().contains("Simple menu")) {
                gotchat = webElement;
            }
        }

        new Actions(driver).dragAndDrop(gotchat, driver.findElement(By.id("dndnav"))).perform();
        screenshot("./menuUpdate.png");

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public static class Commentaire {
        private final WebDriver driver;

        @CacheLookup
        @FindBy(id = "c_name")
        private WebElement name;

        @CacheLookup
        @FindBy(id = "c_mail")
        private WebElement mail;


        @CacheLookup
        @FindBy(id = "c_content")
        private WebElement content;

        private String baseUrl = "http://localhost/dotclear/admin/auth.php";

        public Commentaire(WebDriver driver) {
            this.driver = driver;
        }


        public void fillCommentaire(String name, String email, String content) {
            this.name.sendKeys(name);
            this.mail.sendKeys(email);
            this.content.sendKeys(content);
            this.content.submit();
//            this.driver.findElement(By.xpath("//input[@value='send']")).click();
        }

        public void deleteCommentaire(String name) {
            driver.get(baseUrl);
            driver.findElement(By.linkText("Comments")).click();

            List<WebElement> tr = driver.findElement(By.id("form-comments")).findElements(By.xpath("//tr[@class='line']"));
            WebElement found = tr.iterator().next();
            found.findElement(By.xpath("//input[@type='checkbox']")).click();
            WebElement action = driver.findElement(By.id("action"));
            new Select(action).selectByValue("delete");
            action.submit();
            driver.switchTo().alert().accept();


        }

    }


}
