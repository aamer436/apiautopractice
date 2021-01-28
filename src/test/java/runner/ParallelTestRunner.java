package runner;

import org.testng.annotations.DataProvider;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/java/features"},
glue = {"runner","stepDef"},
monochrome=true,
// strict=true,
// dryRun=true,
//tags = {" not @Testabc"},
// format = {
//                "pretty",
//                "html:target/cucumber-reports/cucumber-pretty",
//                "json:target/cucumber-reports/CucumberTestReport.json",
//                "rerun:target/cucumber-reports/rerun.txt"},
///facebookoauth2setup.feature
plugin = {"json:target/cucumber-reports/CucumberTestReport.json",
"rerun:target/cucumber-reports/rerun.txt",
"html:target/cucumber-reports/cucumber.html",
// "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
"timeline:test-output-thread/"})

public class ParallelTestRunner extends AbstractTestNGCucumberTests {
// private TestNGCucumberRunner testNGCucumberRunner;
@Override
@DataProvider(parallel=true)
public Object[][] scenarios() {
return super.scenarios();
}
}