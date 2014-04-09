package fr.soat.selenium;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withText;

/**
 * User: david
 * Date: 10/04/2014
 * Time: 00:12
 */
public class FluentLeniumTest extends FluentTest {

    @Override
    public WebDriver getDefaultDriver() {
        return new ChromeDriver();
    }

    @Test
    public void title_of_bing_should_contain_search_query_name() {
        goTo("http://www.soat.fr");
        FluentWebElement formation = findFirst("a", withText("Toutes les formations"));
        formation.click();

        assertThat(getDriver().getCurrentUrl()).isEqualTo("http://www.soat.fr/expertise/formations");

        FluentWebElement formationSelenium = findFirst("a", withText("Formation Selenium : Automatiser les tests de vos applications web"));
        formationSelenium.click();


        FluentWebElement eventCode = findFirst("#ref").findFirst(".event_meta_value");
        assertThat(eventCode.getText()).isEqualTo("SEL01");
    }

}
