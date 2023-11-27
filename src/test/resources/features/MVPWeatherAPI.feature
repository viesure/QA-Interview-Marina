Feature: MVP Weather API Testing
  
  Scenario Outline: Verify Weather Information Response Structure
    Given the API endpoint is available
    And Today in Vienna "<conditionID>" conditionID
    When the MVP app requests weather information for Vienna
    Then City should be a fixed name Vienna
    
    Examples:
      | conditionID |
      | 1           |
      | 2           |
      | 3           |
      | 4           |
      | 5           |

  Scenario Outline: Verify condition value based on provided ConditionID
    Given the API endpoint is available
    And Today in Vienna "<conditionID>" conditionID
    When the MVP app requests weather information for Vienna
    Then the response should contain correct condition value based on the provided "<conditionID>"
    
    Examples:
      | conditionID |
      | 1           |
      | 2           |
      | 3           |
      | 4           |
      | 5           |

  Scenario Outline: Verify icon field based on provided ConditionID
    Given the API endpoint is available
    And Today in Vienna "<conditionID>" conditionID
    When the MVP app requests weather information for Vienna
    Then the response should contain correct icon data based on the provided "<conditionID>"
    
    Examples:
      | conditionID |
      | 1           |
      | 2           |
      | 3           |
      | 4           |
      | 5           |

  Scenario Outline: Verify temperature in Celsius based on provided temperature in Fahrenheits
    Given the API endpoint is available
    And Today in Vienna "<tempInFahrenheit>" temperature in Fahrenheits
    When the MVP app requests weather information for Vienna
    Then the response should contain correct tempInCelsius data based on the provided "<tempInFahrenheit>"
    
    Examples:
    | tempInFahrenheit |
    | 32               |
    | 25               |
    | 45               |
    | 50               |
    | 67               |
    | 75               |
    | 77               |
    | 95               |

  Scenario Outline: Verify Description value based on Celsius Temperature
    Given the API endpoint is available
    And Today in Vienna temperature is "<tempInCelsius>" in Celsius
    When the MVP app requests weather information for Vienna
    Then the description value should be as per the calculation rules based on "<tempInCelsius>" Celsius Temperature
    
    Examples:
      | tempInCelsius |
      | -4            |
      | 0             |
      | 8             |
      | 10            |
      | 15            |
      | 20            |
      | 23            |
      | 25            |
      | 32            |

  Scenario Outline: Regression - Verify weather information for Vienna with specific condition ID and temperature
    Given the API endpoint is available
    And Today in Vienna "<conditionID>" condition and temperature is "<tempInFahrenheit>" Fahrenheit
    When the MVP app requests weather information for Vienna
    Then the response should contain correct data based on "<conditionID>", "<tempInFahrenheit>"
    
    Examples:
      | conditionID | tempInFahrenheit |
      | 1           | 32               |
      | 3           | 25               |
      | 2           | 45               |
      | 3           | 50               |
      | 1           | 67               |
      | 4           | 75               |
      | 5           | 77               |
      | 5           | 95               |