package zyCucumber.testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@CucumberOptions(
        plugin = {"json:target/cucumber/cucumber.json","html:target/cucumber","pretty"},
        features = "src/test/resources/zyCucumber/testCaseFeatures",
        glue = {"zyCucumber"},
        monochrome = true,
        tags = {"@smoke"}
)
@ContextConfiguration("classpath:cucumber.xml")
@RunWith(Cucumber.class)
public class TestRunner extends AbstractTestNGCucumberTests {

}
