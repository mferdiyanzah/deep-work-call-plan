@ApprovalProductRecomendation
Feature: Approval Product Recomendation

  Background:
    Given I am on the ifocus page
    Then I should see the login page
    When I input the credentials
    And I click the login button
    Then I should see the dashboard
    When I am on the change rayon page
    Then I should see the change rayon page
    And I should see the rayon code input
    When I input the rayon code "GPMVIC02"
    When I click the change rayon code session button
    Then I should see the change rayon code success message

  Scenario: I can see the approval product recomendation page
    Given I am on the approval product recomendation page
    Then I should see the approval product recomendation page

  Scenario: I can see the list of approval product recomendation
    Given I am on the approval product recomendation page
    Then I should see the approval product recomendation page
    When I click the search button of approval product recomendation page
    Then I should see the list of approval product recomendation

  Scenario: I can see the modal of approval product recomendation
    Given I am on the approval product recomendation page
    Then I should see the approval product recomendation page
    When I click the search button of approval product recomendation page
    And I click one of the list of approval product recomendation
    Then I should see the modal of approval product recomendation

  Scenario: I can approve the product recomendation
    Given I am on the approval product recomendation page
    Then I should see the approval product recomendation page
    When I click the search button of approval product recomendation page
    And I click one of the list of approval product recomendation
    Then I should see the modal of approval product recomendation
    When I click the approve button of approval product recomendation
    And I click the yes button of approval product recomendation
    Then I should see the approval product recomendation success message

