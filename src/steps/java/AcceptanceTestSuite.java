package steps.java;

import net.thucydides.jbehave.ThucydidesJUnitStories;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-html-report"},
        features = "src/test/resources/features",
        glue = {"steps.java"}
)
public class AcceptanceTestSuite extends ThucydidesJUnitStories {
}
