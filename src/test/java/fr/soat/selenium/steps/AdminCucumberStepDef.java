package fr.soat.selenium.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Etantdonné;
import cucumber.api.java.fr.Quand;
import fr.soat.selenium.object.AuthPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by formation on 13/08/14.
 */
public class AdminCucumberStepDef {

    private WebDriver driver;
    private AuthPage authPage;

    @Before
    public void setUpSteps() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.phantomjs();

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        driver = new Augmenter().augment(driver);
        driver.manage().deleteAllCookies();

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Etantdonné("^un utilisateur avec le login \"([^\"]*)\" et le mot de passe \"([^\"]*)\"$")
    public void un_utilisateur_avec_le_login_et_le_mot_de_passe(String user, String pwd) throws Throwable {
        driver.get("http://localhost/dotclear/admin");
        authPage = PageFactory.initElements(driver, AuthPage.class);
        authPage.login(user, pwd);
    }

    @Alors("^l'accès à l'administration est autorisé$")
    public void l_accès_à_l_administration_est_autorisé() throws Throwable {
        authPage.screenshot("./admin.png");
    }

    @Quand("^l'utilisateur se loggue sur la page d'authentification$")
    public void l_utilisateur_se_loggue_sur_la_page_d_authentification() throws Throwable {
        authPage.perform();
    }
}
