@CallRecomendation
Feature: Call Recommendation

  Background:
    Given I am on the ifocus page
    Then I should see the login page
    When I input the credentials
    And I click the login button
    Then I should see the dashboard
    When I am on the change rayon page
    Then I should see the change rayon page
    And I should see the rayon code input
    When I input the rayon code "TGRVIC0103"
    When I click the change rayon code session button
    Then I should see the change rayon code success message
  Scenario: I can see the call recomendation page
    Given I am on the call recomendation page
    Then I should see the call recomendation page

  Scenario: I can simulate the call recomendation
    Given I am on the call recomendation page
    Then I should see the call recomendation page
    When I input the "45" week of "2024" year
    And I click the simulate call button
    Then I should see the docter card

  Scenario: I can see the error message when simulate without the date
    Given I am on the call recomendation page
    Then I should see the call recomendation page
    When I click the simulate call button
    Then I should see the input error message

  Scenario: I can reset the recomendation after simulate
    Given I am on the call recomendation page
    Then I should see the call recomendation page
    When I input the "45" week of "2024" year
    And I click the simulate call button
    Then I should see the docter card
    When I click the reset button
    Then I should see the empty datepicker and doctor card

  Scenario: I can see the error message when simulate with invalid date
    Given I am on the call recomendation page
    Then I should see the call recomendation page
    When I input invalid date
    And I click the simulate call button
    Then I should see the input error message

  Scenario: I can see warning when simulate old date
    Given I am on the call recomendation page
    Then I should see the call recomendation page
    When I input the "45" week of "2020" year
    And I click the simulate call button
    Then I should see the warning message

  Scenario: I can see the tooltip of the doctor card
    Given I am on the call recomendation page
    Then I should see the call recomendation page
    When I input the "45" week of "2024" year
    And I click the simulate call button
    Then I should see the docter card
    When I click the doctor card
    Then I should see the tooltip of the doctor card
