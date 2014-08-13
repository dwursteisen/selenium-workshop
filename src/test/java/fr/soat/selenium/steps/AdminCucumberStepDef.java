package fr.soat.selenium.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.fr.*;
import fr.soat.selenium.object.Admin;
import fr.soat.selenium.object.AuthPage;
import fr.soat.selenium.object.BlogEntry;
import fr.soat.selenium.utils.Screenshot;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

/**
 * Created by formation on 13/08/14.
 */
public class AdminCucumberStepDef {

    private WebDriver driver;
    private AuthPage authPage;
    private Admin admin;
    private BlogEntry blogEntry;
    private Screenshot screenshot;

    @Before
    public void setUpSteps() throws MalformedURLException {

        driver = new PhantomJSDriver();
        screenshot = new Screenshot(driver, this);
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
        screenshot.saveAt("login.png");
    }

    @Alors("^l'accès à l'administration est autorisé$")
    public void l_accès_à_l_administration_est_autorisé() throws Throwable {
        screenshot.saveAt("admin.png");
    }

    @Quand("^l'utilisateur se loggue sur la page d'authentification$")
    public void l_utilisateur_se_loggue_sur_la_page_d_authentification() throws Throwable {
        admin = authPage.perform();
        screenshot.saveAt("adminMenu.png");
    }

    @Lorsque("^l'utilisateur choisi d'écrire un nouveau bilet de blog$")
    public void l_utilisateur_choisi_d_écrire_un_nouveau_bilet_de_blog() throws Throwable {
        blogEntry = admin.newBlogPost();
        screenshot.saveAt("blogEntry.png");
    }

    @Et("^que le bilet de blog a pour titre \"([^\"]*)\"$")
    public void que_le_bilet_de_blog_a_pour_titre(String title) throws Throwable {
        blogEntry.fillTitle(title);
    }

    @Et("^que le billet de blog a pour contenu \"([^\"]*)\"$")
    public void que_le_billet_de_blog_a_pour_contenu(String content) throws Throwable {
        blogEntry.fillContent(content);
    }

    @Et("^que le billet est publié$")
    public void que_le_billet_est_publié() throws Throwable {
        blogEntry.publish();
        screenshot.saveAt("publish.png");
    }

    @Alors("^le billet de blog est lisible sur le blog avec le titre \"([^\"]*)\"$")
    public void le_billet_de_blog_est_lisible_sur_le_blog_avec_le_titre(String title) throws Throwable {
        Assert.assertEquals(title, blogEntry.goToBlogPost().getPostTitle());
        screenshot.saveAt("blogPublish.png");
    }
}
