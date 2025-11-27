package steps.java;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@authentication"},
        format = {"json:target/cucumber/authentication.json"},
        plugin = {"pretty", "html:target/cucumber-html-report"},
        features = "src/test/resources/features",
        glue = {"steps.java"})
public class Authentication {
}
