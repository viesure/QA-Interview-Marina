package runners;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import report.SuiteListener;

import org.openqa.selenium.WebDriver;
import org.testng.ITestNGListener;

@CucumberOptions(
	features = {"src/test/resources/features/OpenWeatherMapTests.feature"},
	glue = {"stepdefinitions"},
	plugin = "json:target/cucumber-reports/OpenWeatherMapTest.json")

@Listeners(SuiteListener.class)
public final class OpenWeatherMapRunner extends AbstractTestNGCucumberTests implements ITestNGListener {
	
	private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver newDriver) {
        driver = newDriver;
    } 

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterTest
    public void afterTest() {
        SuiteListener.incrementCompletedFeatures();
    } 
}
