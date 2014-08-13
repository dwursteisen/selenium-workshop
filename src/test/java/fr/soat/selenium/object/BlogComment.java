package fr.soat.selenium.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

/**
 * Created by formation on 13/08/14.
 */
public class BlogComment {
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

    public BlogComment(WebDriver driver) {
        this.driver = driver;
    }


    public void fillCommentaire(String name, String email, String content) {
        this.name.sendKeys(name);
        this.mail.sendKeys(email);
        this.content.sendKeys(content);
    }

    public void perform() {
        this.content.submit();
    }

}
