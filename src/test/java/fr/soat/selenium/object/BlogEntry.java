package fr.soat.selenium.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by formation on 13/08/14.
 */
public class BlogEntry {
    private final WebDriver driver;

    @FindBy(id = "post_title")
    private WebElement title;

    @FindBy(id = "post_content")
    private WebElement content;

    @FindBy(id = "post_status")
    private WebElement status;

    @CacheLookup
    @FindBy(name = "save")
    private WebElement save;

    @FindBy(linkText = "Go to this entry on the site")
    private WebElement goOut;

    public BlogEntry(WebDriver driver) {
        this.driver = driver;
    }

    public void fillBlog(String title, String content) {
        this.title.sendKeys(title);
        this.content.sendKeys(content);
    }

    public void publish() {
        new Select(status).selectByValue("1");
        save.click();
        // alternative :  (marche pas sous Firefox)
        // new Actions(driver).sendKeys(Keys.chord(Keys.ALT, "s")).perform();
    }

    public String getPublishedUrl() {
        return goOut.getAttribute("href");
    }

    public BlogPost goToBlogPost() {
        driver.get(getPublishedUrl());
        return PageFactory.initElements(driver, BlogPost.class);
    }

    public static class BlogPost {
        private final WebDriver driver;

        @CacheLookup
        @FindBy(className = "post-title")
        private WebElement postTitle;

        public BlogPost(WebDriver driver) {
            this.driver = driver;
        }

        public String getPostTitle() {
            return postTitle.getText();
        }
    }
}
