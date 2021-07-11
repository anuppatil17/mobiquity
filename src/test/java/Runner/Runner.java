package Runner;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"StepsFunctions"},
        plugin = {"pretty","html:target/cucumber-reports/cucumber.html"}


)
public class Runner {
}






























