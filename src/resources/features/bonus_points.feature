Feature: Earning extra points from Frequent Flyer status

  Scenario: A standard Frequent Flyer earns the base point value of a trip
    Given I am a Standard Frequent Flyer member
    When I fly on a flight that is worth 439 base points
    Then I should earn a total of 439 points

  Scenario: A Silver Frequent Flyer will earn a 50% status bonus
    Given I am a Silver Frequent Flyer member
    When I fly on a flight that is worth 439 base points
    Then I should earn a status bonus of 220 points
    And I should earn a total of 659 points

  Scenario: A Gold Frequent Flyer will earn a 75% status bonus
    Given I am a Gold Frequent Flyer member
    When I fly on a flight that is worth 2040 base points
    Then I should earn a status bonus of 1530 points
    And I should earn a total of 3570 points

  Scenario: A Gold Frequent Flyer benefits from a guaranteed minimum of 1000 points per trip
    Given I am a Gold Frequent Flyer member
    When I fly on a flight that is worth 439 base points
    Then I should have a guaranteed minimum of 1000 earned points per trip
    And I should earn a total of 1000 points

  Scenario Outline: Earning points on flights by Frequent Flyer status
    Given I am a <status> Frequent Flyer member
    When I fly on a flight that is worth <base> base points
    Then I should earn a status bonus of <bonus>
    And I should have guaranteed minimum earned points of <minimum>
    And I should earn <total> points in all
    Examples:
      | status   | base | bonus | minimum | total |
      | Standard | 439  | 0     | 0       | 439   |
      | Silver   | 148  | 74    | 500     | 500   |
      // ...