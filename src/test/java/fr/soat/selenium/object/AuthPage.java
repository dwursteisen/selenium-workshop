package fr.soat.selenium.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by formation on 13/08/14.
 */
public class AuthPage {

    private final WebDriver driver;
    @FindBy(id = "user_id")
    private WebElement user;
    @FindBy(id = "user_pwd")
    private WebElement password;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String password) {
        this.user.sendKeys(user);
        this.password.sendKeys(password);

    }

    public Admin perform() {
        this.password.submit();
        return PageFactory.initElements(driver, Admin.class);
    }


}
