# Project: MVP Weather Application API Tests & OpenWeatherMap Tests
This project involves automated testing of the MVP Weather Application's API and the OpenWeatherMap website using Java, Selenium, RestAssured, Cucumber, and TestNG.

## Project Structure

- **SuiteListener.java**: TestNG listener implements the ISuiteListener interface to listen for suite completion events and it generates cucumber report.
- **MVPWeatherRunner.java**: TestNG runner class runs Cucumber tests for the MVPWeatherAPI feature. It specifies feature file paths, glue code package, and report generation.
- **OpenWeatherMapRunner.java**:  TestNG runner class that executes Cucumber tests for OpenWeatherMapTests.feature. It defines feature file paths, glue code package, and report generation.
- **APIFields.java**: Holds constants and enums for API-related field names and values.
- **MVPWeatherHelper.java**: Helper methods for API testing of the MVP Weather Application.
- **MVPWeatherStepDefinitions.java**: Step definitions for the Cucumber scenarios related to MVP Weather Application API testing.
- **OpenWeatherMapHelper.java**: Helper methods for UI testing of the OpenWeatherMap website using Selenium WebDriver.
- **OpenWeatherMapStepDefinitions.java**: Step definitions for the Cucumber scenarios related to OpenWeatherMap website testing.
- **MVPWeatherAPI.feature**: Cucumber feature file defining scenarios for API testing of the MVP Weather Application.
- **OpenWeatherMapTests.feature**: Cucumber feature file defining scenarios for UI testing of the OpenWeatherMap website.
- **config.properties**: Configuration file containing base URLs for the MVP Weather Application and OpenWeatherMap.
- **pom.xml**: Maven project file listing dependencies and build configurations.
- **testng.xml**: TestNG suite configuration file.

# Running Tests

1. Pre-requisites:

- Java and Maven should be installed on the machine.
- Chromedriver executable should be present in the 'drivers' folder.

2. Execution:

- To run all tests, execute the command:
```mvn clean test```
- TestNG XML file (testng.xml) controls the test execution.

## Test Reports
- Test reports are generated using Cucumber reporting (net.masterthought.cucumber.ReportBuilder).
- Reports can be found in the target/cucumber-reports/cucumber-html-reports/overview-features.html directory after test execution.

You can view the [Cucumber Test Report here](/target/cucumber-reports/cucumber-html-reports/overview-features.html).

# Test Report Summary

## Test Results
The test suite executed a total of 42 tests. Unfortunately, there were 7 test failures reported during the execution.

**Failures**
- Please review 4 unique failures: 
1. Verify icon field based on provided ConditionID = 4
Actual Result: Icon value mismatch. Expected [drizzle.png] but found [drizzle.jpeg].

2. Verify temperature in Celsius based on provided temperature in Fahrenheits = 75
Actual Result: Temp in Celsius value is not as expected. Expected [24] but found [23].

3. Verify Description value based on Celsius Temperature == 10
Actual Result: Description value mismatch. Expected [The weather is mild] but found [The weather is cold].

4. Verify Description value based on Celsius Temperature = 20
Actual Result: Description value mismatch. Expected [The weather is warm] but found [The weather is ].