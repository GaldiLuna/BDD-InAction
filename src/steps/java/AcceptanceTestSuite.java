package steps.java;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import net.thucydides.jbehave.ThucydidesJUnitStories;
import org.junit.runner.RunWith;
import cucumber.api.java.en.Given;

@RunWith(Cucumber.class)
@Cucumber.Options(format = {"html:target/cucumber-html-report"})
public class AcceptanceTestSuite extends ThucydidesJUnitStories {
}
