package steps.java;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.thucydides.jbehave.ThucydidesJUnitStories;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@earning_points", // Executa apenas cenários com esta tag.
                plugin = {"pretty", "html:target/cucumber-html-report"},
                features = "src/test/resources/features",
                glue = {"steps.java"}) // Armazena os relatórios em formato JSON neste arquivo.
public class EarningPoints extends ThucydidesJUnitStories { // Isso executará todas as histórias no diretório earning_points.
    public EarningPoints() {
        this.findStoriesCalled("**/earning_points/*.story"); // Encontra todas as histórias no diretório.
    }
}
