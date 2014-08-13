package fr.soat.selenium;

import fr.soat.selenium.object.Admin;
import fr.soat.selenium.object.AuthPage;
import fr.soat.selenium.object.BlogEntry;
import fr.soat.selenium.utils.Screenshot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by formation on 13/08/14.
 */
public class PageObjectTest {


    private WebDriver driver;
    private Screenshot screenshot;

    private String baseUrl = "http://localhost/dotclear/";

    @Before
    public void setUp() {
        driver = new PhantomJSDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(new Dimension(800, 600));
        screenshot = new Screenshot(driver, this);
    }

    @After
    public void tearDown() {
        screenshot.saveAt("end.png");
        driver.quit();
    }


    @Test
    public void shouldCreateBlogPost() {
        driver.get(baseUrl + "admin/auth.php");
        AuthPage authPage = PageFactory.initElements(driver, AuthPage.class);
        authPage.login("azerty", "azerty");
        screenshot.saveAt("beforeLogin.png");

        Admin admin = authPage.perform();

        BlogEntry blogEntry = admin.newBlogPost();

        blogEntry.fillBlog("Ceci est un titre", "Ceci est un contenu");
        screenshot.saveAt("blogFilled.png");
        blogEntry.publish();
        screenshot.saveAt("blogPublished.png");
        BlogEntry.BlogPost post = blogEntry.goToBlogPost();
        screenshot.saveAt("blogPost.png");
        Assert.assertEquals("Ceci est un titre", post.getPostTitle());

    }

}
