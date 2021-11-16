@API
Feature: https://restcountries.com/ test

  Scenario Outline: Checking countries that border the selected country
    Given a request for a URL "getCurrentCountryInfo" with parameter "<countryCode>" has been sent. List of bordering country codes saved to variable "countriesVar"
    Then check that the "<countryCode>" value is present in the borders of the list countries "countriesVar"

    Examples:
      | countryCode |
      | RUS         |
      | BEL         |
      | UKR         |
      | ARM         |
      | GEO         |
      | IRN         |

  Scenario Outline: Checking the response body.
    Given a request for a URL "getCurrentCountryInfo" with parameter "<countryCode>" has been sent. The response body is stored in a variable "response"
    Then JSON response schema from variable "response" must match "countriesResponseSchema"

    Examples:
      | countryCode |
      | RUS         |
      | BLR         |
      | UKR         |
      | ARM         |
      | GEO         |
      | IRN         |