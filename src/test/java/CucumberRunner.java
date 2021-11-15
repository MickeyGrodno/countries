import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"html:target/cucumber-report/runner", "json:target/cucumber.json"},
        features = "src/test/resources/features/api",
        tags = "@API")

public class CucumberRunner
{
}
