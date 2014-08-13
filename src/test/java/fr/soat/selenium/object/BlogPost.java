package fr.soat.selenium.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by formation on 13/08/14.
 */
public class BlogPost {
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

    public BlogComment getComment() {
        return PageFactory.initElements(driver, BlogComment.class);
    }
}
