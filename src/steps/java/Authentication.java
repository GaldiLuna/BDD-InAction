package steps.java;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@authentication", // Executa apenas cenários com esta tag.
        plugin = {"pretty", "html:target/cucumber-html-report"},
        features = "src/test/resources/features",
        glue = {"steps.java"})
public class Authentication {
}
