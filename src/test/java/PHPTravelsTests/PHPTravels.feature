Feature: Using the www.phptravels.net
  Scenario: Search for a hotel in London for 2 nights and 3 adults
    Given I am at "http://www.phptravels.net" home page
    When I give location london and duration for two days with 3 adults
    Then I am on the correct page if and only if the url is correct
#  Scenario: Book a hotel in London for 2 nights for 3 adults as a guest user to the website
#    Given I have already searched for a hotel in London for 2 nights and 3 adults
#    When I click on the first option for both the hotel and the room with the necessary details
#    Then I have recieved the invoice for my booking
#  Scenario: