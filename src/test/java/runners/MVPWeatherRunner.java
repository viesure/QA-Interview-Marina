package runners;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import report.SuiteListener;

import org.testng.ITestNGListener;

@CucumberOptions(
	features = {"src/test/resources/features/MVPWeatherAPI.feature"},
	glue = {"stepdefinitions"},
	plugin = "json:target/cucumber-reports/MVPWeatherTest.json")

@Listeners(SuiteListener.class)
public final class MVPWeatherRunner extends AbstractTestNGCucumberTests implements ITestNGListener {
    @AfterTest
    public void afterTest() {
        SuiteListener.incrementCompletedFeatures();
    }
}
