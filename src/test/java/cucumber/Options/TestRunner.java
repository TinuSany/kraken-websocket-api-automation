package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/Features",
plugin="json:target/jsonReports/jsonReport.json", 
glue={"StepDefinitions","hooks"}
//use the tag parameter for restricting the test to a specific tag. ~ can be used for except one tag
//,tags="@Smoke"
)

//Test Runner class to run the Cucumber Test
public class TestRunner {

}
