package fr.soat.selenium;

import fr.soat.selenium.utils.Screenshot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * User: david
 * Date: 10/04/2014
 * Time: 00:07
 */
public class WebDriverTest {

    private WebDriver driver;
    private String baseUrl;

    private Screenshot screenshot;

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

        screenshot = new Screenshot(driver, this);

        baseUrl = "http://localhost/dotclear/admin/auth.php";
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Test
    public void testLogging() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("user_id")).clear();
        driver.findElement(By.id("user_id")).sendKeys("azerty");
        driver.findElement(By.id("user_pwd")).clear();
        driver.findElement(By.id("user_pwd")).sendKeys("azerty");
        driver.findElement(By.id("user_remember")).click();

        screenshot.saveAt("login.png");

        driver.findElement(By.cssSelector("input.login")).click();


    }


    // drapg & drop
    @Test
    public void testDragAndDrop() throws Exception {
        testLogging();

        driver.findElement(By.linkText("Presentation widgets")).click();
        List<WebElement> li = driver.findElements(By.xpath("//li[@class='ui-draggable']"));
        WebElement gotIt = null;
        for (WebElement webElement : li) {
            if (webElement.getText().contains("Simple menu")) {
                gotIt = webElement;
            }
        }

        new Actions(driver).dragAndDrop(gotIt, driver.findElement(By.id("dndnav"))).perform();
        screenshot.saveAt("menuUpdate.png");
        driver.findElement(By.name("wup")).click();

        driver.findElement(By.linkText("Go to site")).click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        iterator.next();
        driver.switchTo().window(iterator.next());
        screenshot.saveAt("BlogMenuUpdate.png");

    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
    }


}
