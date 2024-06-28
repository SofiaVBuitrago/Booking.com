Feature: Filters on booking.com
  Scenario: the user search for a booking in chapinero with 5 stars
    Given The user is on the booking page
    And the user selects the city and Dates
    When the places are displayed the user marks the five stars and chapinero filters
    Then the results are displayed
    And the first option is opened
