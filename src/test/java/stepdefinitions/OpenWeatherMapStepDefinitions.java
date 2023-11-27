package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import runners.OpenWeatherMapRunner;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class OpenWeatherMapStepDefinitions {

    private WebDriver driver;
    private final OpenWeatherMapHelper weatherMapHelper;
    
    public OpenWeatherMapStepDefinitions() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        OpenWeatherMapRunner.setDriver(driver);
        weatherMapHelper = new OpenWeatherMapHelper(driver);
    }

    @Given("User navigates to the OpenWeatherMap website")
    public void userNavigatesToOpenWeatherMapWebsite() throws IOException {
        weatherMapHelper.navigateToOpenWeatherMap();
    }

    @When("Cookies are accepted")
    public void acceptCookies() {
        weatherMapHelper.acceptCookies();
    }

    @Then("The main page's search field contains {string} placeholder text")
    public void verifySearchFieldPlaceholderText(String expectedPlaceholderText) {
        Assert.assertEquals(weatherMapHelper.getSearchFieldPlaceholderText(), expectedPlaceholderText);
    }

    @And("User searches for {string} in the search field")
    public void searchCity(String city) {
        weatherMapHelper.searchCity(city);
    }

    @And("User selects {string} from the suggested list")
    public void selectSuggestedCity(String suggestedCity) {
        weatherMapHelper.selectSuggestedCity(suggestedCity);
    }

    @Then("Verify the selected city's title is {string}")
    public void verifySelectedCityTitle(String city) {
        Assert.assertEquals(weatherMapHelper.getSelectedCityTitle(), city);
    }

    @And("Verify that the date shown is current date in {string} timezone")
    public void verifyDateShown(String cityWithCountry) {
        Assert.assertEquals(weatherMapHelper.getCurrentDate(cityWithCountry), weatherMapHelper.getExpectedCurrentDate(cityWithCountry));
    }

    @And("Verify that the time shown is current time in {string} timezone")
    public void verifyTimeShown(String cityWithCountry) {
        String currentTime = weatherMapHelper.getCurrentTime(cityWithCountry);
        String expectedCurrentTime = weatherMapHelper.getExpectedCurrentTime(cityWithCountry);
        //check 2 times to avoid false negatives due to seconds changing between calls
        if (!currentTime.equals(expectedCurrentTime)) {
            currentTime = weatherMapHelper.getCurrentTime(cityWithCountry);
            expectedCurrentTime = weatherMapHelper.getExpectedCurrentTime(cityWithCountry);
        }
        Assert.assertEquals(weatherMapHelper.getCurrentTime(cityWithCountry), weatherMapHelper.getExpectedCurrentTime(cityWithCountry));
    } 
}
