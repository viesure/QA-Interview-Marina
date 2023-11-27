Feature: OpenWeatherMap Tests

  Scenario: Verify the main page's search field placeholder text
    Given User navigates to the OpenWeatherMap website
    When Cookies are accepted
    Then The main page's search field contains "Weather in your city" placeholder text

  Scenario: Search for Sydney, AU and verify details
    Given User navigates to the OpenWeatherMap website
    When Cookies are accepted
    And User searches for "Sydney" in the search field
    And User selects "Sydney, AU" from the suggested list
    Then Verify the selected city's title is "Sydney, AU"
    And Verify that the date shown is current date in "Australia/Sydney" timezone
    And Verify that the time shown is current time in "Australia/Sydney" timezone