Feature: Bing search function
  Scenario: Doing a Bing search on a string, Google
    Given I am at "http://www.bing.com" search page
    When I search for "google"
    Then The first result is google

  Scenario: Do a new search for Yahoo
    Given I am at "http://www.bing.com" search page
    When I search for "yahoo"
    Then The first result is yahoo