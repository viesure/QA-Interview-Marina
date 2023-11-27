package stepdefinitions;

import org.testng.Assert;

import com.google.gson.JsonObject;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MVPWeatherHelper {
    
    public void sendPutRequest(RequestSpecification request, String endpoint, String key, String value) {
        JsonObject params = new JsonObject();
        params.addProperty(key, value);
        request.header(APIFields.CONTENT_TYPE_HEADER, APIFields.APPLICATION_JSON)
                .body(params.toString())
                .put(endpoint)
                .then()
                .statusCode(200);
    }

    // Method to get condition value based on condition ID
    public String getConditionValueBasedOnID(String conditionID) {
        int conditionIDAsInt = Integer.parseInt(conditionID);
        String conditionValue = "";
        switch(conditionIDAsInt){
            case 1:
                conditionValue = APIFields.Condition.CLEAR.getConditionValue();
                break;
            case 2:
                conditionValue = APIFields.Condition.WINDY.getConditionValue();
                break;
            case 3:
                conditionValue = APIFields.Condition.MIST.getConditionValue();
                break;
            case 4:
                conditionValue = APIFields.Condition.DRIZZLE.getConditionValue();
                break;
            case 5:
                conditionValue = APIFields.Condition.DUST.getConditionValue();
                break;
        }
        return conditionValue;
    }

    // Method to calculate temperature in Celsius based on temperature in Fahrenheit
    public int getCelsiusBasedOnFahrenheit(String tempInFahrenheit) {
        return (int) Math.round((Double.parseDouble(tempInFahrenheit) - 32) * 5 / 9);

    }

    // Method to get description based on temperature in Celsius
    public String getDescriptionBasedOnCelsius(int tempInCelsius) {
        String description = "The weather is ";
        if (tempInCelsius <= 0) {
            description += APIFields.Description.FREEZING.getDescriptionValue();
        } else if (tempInCelsius < 10) {
            description +=  APIFields.Description.COLD.getDescriptionValue();
        } else if (tempInCelsius < 20) {
            description +=  APIFields.Description.MILD.getDescriptionValue();
        } else if (tempInCelsius < 25) {
            description +=  APIFields.Description.WARM.getDescriptionValue();
        } else if(tempInCelsius >= 25){
            description +=  APIFields.Description.HOT.getDescriptionValue();
        }
        return description;
    }

    public void assertFieldStringValue(Response response, String fieldName, String expectedValue, String errorMessage) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String fieldValue = jsonPathEvaluator.getString(fieldName);
        Assert.assertEquals(fieldValue, expectedValue, errorMessage);
    }
    
}
