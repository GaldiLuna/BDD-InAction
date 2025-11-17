Feature: Displaying flight status
  Scenario: Provide a positive visual queue for on-time flights
    Given that flight FH-101 has no reported delays
    When I check the flight status
    Then I should see that it is on time
    And I should see its scheduled arrival time

Feature: Retrieve information about a given flight
  Scenario: Find flight details by flight number
    Given I need to know the details of flight number FH-101
    When I request the details about this flight
    Then I should receive the following:
      | flightNumber | departure | destination | time  |
      | FH-101       | MEL       | SYD         | 06:00 |