package fr.soat.selenium.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by formation on 13/08/14.
 */
public class Admin {
    private final WebDriver driver;

    @FindBy(linkText = "New entry")
    private WebElement newBlogPost;

    public Admin(WebDriver driver) {
        this.driver = driver;
    }

    public BlogEntry newBlogPost() {
        newBlogPost.click();
        return PageFactory.initElements(driver, BlogEntry.class);
    }
}
