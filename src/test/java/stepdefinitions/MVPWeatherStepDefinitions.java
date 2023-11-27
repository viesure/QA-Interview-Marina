package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class MVPWeatherStepDefinitions {
    private static final String BASE_PATH = "/weather";
    private RequestSpecification request;    
    private Response response;
    private MVPWeatherHelper testHelpers = new MVPWeatherHelper();
    private static final String FIXED_CITY_NAME = "vienna";

    public MVPWeatherStepDefinitions() throws IOException {
        Properties prop = new Properties();
        try {
            FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAssured.baseURI = prop.getProperty("mvpWeatherBaseUrl");
    }

    @Given("the API endpoint is available")
    public void theApiEndpointAvailable() {
        request = RestAssured.given().basePath(BASE_PATH);
    }

    @When("the MVP app requests weather information for Vienna")
    public void getWeatherInfo() {
        response = request.when().get();
    } 

    @Then("City should be a fixed name Vienna")
    public void checkCityData() {
        testHelpers.assertFieldStringValue(response, "city", FIXED_CITY_NAME, "City value is not as expected");
    }

    @And("Today in Vienna {string} conditionID")
    public void setConditionID(String conditionID) {
        testHelpers.sendPutRequest(request, "/condition", "condition", conditionID);
    }  

    @And("Today in Vienna {string} temperature in Fahrenheits")
    public void setFahrenheit(String temp) {
        testHelpers.sendPutRequest(request, "/temp", "tempInFahrenheit", temp);
    }    

    @And("Today in Vienna temperature is {string} in Celsius")
    public void setFahrenheitBasedOnCelsius(String tempInCelsius) {
        //get temp in Fahrenheit based on temp in Celsius
        int tempInCelsiusAsInt = Integer.parseInt(tempInCelsius);
        int tempInFahrenheitExpected = (int) Math.round(tempInCelsiusAsInt * 9 / 5 + 32);
        testHelpers.sendPutRequest(request, "/temp", "tempInFahrenheit", String.valueOf(tempInFahrenheitExpected));
    }

    @Then("the response should contain correct condition value based on the provided {string}")
    public void checkConditoinValue(String conditionID) {
        String conditionValue = testHelpers.getConditionValueBasedOnID(conditionID);
        testHelpers.assertFieldStringValue(response, "condition", conditionValue, "Condition value is not as expected");
    }  

    @Then("the response should contain correct icon data based on the provided {string}")
    public void checkIconData(String conditionID) {
        String conditionValue = testHelpers.getConditionValueBasedOnID(conditionID);
        testHelpers.assertFieldStringValue(response, "icon", conditionValue + ".png", "Icon value is not as expected");
    }    

    @Then("the response should contain correct tempInCelsius data based on the provided {string}")
    public int checkTempInCelsius(String tempInFahrenheit) {
        testHelpers.assertFieldStringValue(response, "weather.tempInFahrenheit", tempInFahrenheit, "Temp in Fahrenheit value is not as expected");
        
        int tempInCelsiusExpected = testHelpers.getCelsiusBasedOnFahrenheit(tempInFahrenheit);
        testHelpers.assertFieldStringValue(response, "weather.tempInCelsius", String.valueOf(tempInCelsiusExpected), "Temp in Celsius value is not as expected");
        return tempInCelsiusExpected;
    }    

    @Then("the description value should be as per the calculation rules based on {string} Celsius Temperature")
    public void checkDescription(String tempInCelsius) {
        //get temp in Fahrenheit based on temp in Celsius
        int tempInCelsiusAsInt = Integer.parseInt(tempInCelsius);
        
        String description = testHelpers.getDescriptionBasedOnCelsius(tempInCelsiusAsInt);
        testHelpers.assertFieldStringValue(response, "description", description, "Description value is not as expected");
    }    

    @And("Today in Vienna {string} condition and temperature is {string} Fahrenheit")
    public void setConditionIDAndFahrenheit(String conditionID, String temp) {
        testHelpers.sendPutRequest(request, "/condition", "condition", conditionID);
        testHelpers.sendPutRequest(request, "/temp", "tempInFahrenheit", temp);
    }  

    @Then("the response should contain correct data based on {string}, {string}")
    public void checkResponse(String conditionID, String tempInFahrenheit) {
        checkCityData();
        checkConditoinValue(conditionID);
        checkIconData(conditionID);
        int tempInCelsiusExpected = checkTempInCelsius(tempInFahrenheit);
        checkDescription(String.valueOf(tempInCelsiusExpected));
    }   
}
