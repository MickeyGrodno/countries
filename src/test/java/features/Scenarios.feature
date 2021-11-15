Feature: Test.

  Scenario Outline: Checking countries that border the selected country
    Given a request for a URL "getCurrentCountryInfo" with parameter "<countryCode>" has been sent. List of bordering country codes saved to variable "countriesVar"
    And check that the "<countryCode>" value is present in the borders of the list countries "countriesVar"
    Examples:
      | countryCode |
      | RUS         |
#      | BEL         |
#      | UKR         |