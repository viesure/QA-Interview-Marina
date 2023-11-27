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
                .statusCode(APIFields.HTTP_STATUS_OK);
    }

    // Method to get condition value based on condition ID
    public String getConditionValueBasedOnID(String conditionID) {
        int conditionIDAsInt = Integer.parseInt(conditionID);
        String conditionValue = "";
        switch(conditionIDAsInt){
            case 1:
                conditionValue = APIFields.Condition.clear.toString();
                break;
            case 2:
                conditionValue = APIFields.Condition.windy.toString();
                break;
            case 3:
                conditionValue = APIFields.Condition.mist.toString();
                break;
            case 4:
                conditionValue = APIFields.Condition.drizzle.toString();
                break;
            case 5:
                conditionValue = APIFields.Condition.dust.toString();
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
        StringBuilder description = new StringBuilder("The weather is ");
    
        if (tempInCelsius <= 0) {
            description.append(APIFields.Description.freezing.toString());
        } else if (tempInCelsius < 10) {
            description.append(APIFields.Description.cold.toString());
        } else if (tempInCelsius < 20) {
            description.append(APIFields.Description.mild.toString());
        } else if (tempInCelsius < 25) {
            description.append(APIFields.Description.warm.toString());
        } else if (tempInCelsius >= 25) {
            description.append(APIFields.Description.hot.toString());
        }
    
        return description.toString();
    }

    public void assertFieldStringValue(Response response, String fieldName, String expectedValue, String errorMessage) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String fieldValue = jsonPathEvaluator.getString(fieldName);
        Assert.assertEquals(fieldValue, expectedValue, errorMessage);
    }
    
}
