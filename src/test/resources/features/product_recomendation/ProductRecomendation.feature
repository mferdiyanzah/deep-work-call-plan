@ProductRecomendation
Feature: Product Recomendation

  Background:
    Given I am on the ifocus page
    Then I should see the login page
    When I input the credentials
    And I click the login button
    Then I should see the dashboard
    When I am on the change rayon page
    Then I should see the change rayon page
    And I should see the rayon code input
    When I input the rayon code "PMVIC01"
    When I click the change rayon code session button
    Then I should see the change rayon code success message

  Scenario: I can see the product recomendation page
    Given I am on the product recomendation page
    Then I should see the product recomendation page

  Scenario: I can see the list of product recomendation
    Given I am on the product recomendation page
    Then I should see the product recomendation page
    When I click the search button of product recomendation
    Then I should see the list of product recomendation

  Scenario: I can filter all progress
    Given I am on the product recomendation page
    Then I should see the list of product recomendation
    When I click the filter "progress" button with "All" value
    And I click the search button of product recomendation
    Then I should see the list of product recomendation with "progress" filter of "All" value

  Scenario: I can filter upcoming progress
    Given I am on the product recomendation page
    Then I should see the list of product recomendation
    When I click the filter "progress" button with "Upcoming" value
    And I click the search button of product recomendation
    Then I should see the list of product recomendation with "progress" filter of "Upcoming" value

  Scenario: I can filter finished progress
    Given I am on the product recomendation page
    Then I should see the list of product recomendation
    When I click the filter "progress" button with "Finished" value
    And I click the search button of product recomendation
    Then I should see the list of product recomendation with "progress" filter of "Finished" value

  Scenario: I can reset the filter
    Given I am on the product recomendation page
    Then I should see the list of product recomendation
    When I click the filter "progress" button with "Finished" value
    And I click the reset filter button of product recomendation
    Then I should see the filter reset

  Scenario: I can delete the product recomendation
    Given I am on the product recomendation page
    Then I should see the list of product recomendation
    When I click the filter "progress" button with "All" value
    And I click the search button of product recomendation
    Then I should see the list of product recomendation with "progress" filter of "All" value
    When I delete a product recomendation
    Then I see the product recomendation deleted

  Scenario: I can go to the add product recomendation page
    Given I am on the product recomendation page
    When I click the add new product recomendation button
    Then I should see the create product recomendation page

  Scenario: I can see error message when add new product recomendation without input the required field
    Given I am on the product recomendation page
    When I click the add new product recomendation button
    Then I should see the create product recomendation page
    When I submit the add new product recomendation form
    Then I should see the input error message

  Scenario: I can see error message when add new product recomendation with past date
    Given I am on the product recomendation page
    When I click the add new product recomendation button
    Then I should see the create product recomendation page
    When I input valid specialist value of "Drg"
    And I input valid area
    And I input valid product value
    And I input valid period value of "2024-01-01" to "2024-01-12"
    Then I should see the input error message

  Scenario: I can create new product recomendation
    Given I am on the product recomendation page
    When I click the add new product recomendation button
    Then I should see the create product recomendation page
    When I input valid specialist value of "Drg"
    And I input valid area
    And I input valid product value
    And I input valid period value of "2025-01-01" to "2025-01-12"
    Then I should see confirmation modal of product recomendation
    And I click the confirm button of confirmation modal of product recomendation
    Then I should see the success message

  Scenario: I can not input a long note on add new product recomendation
    Given I am on the product recomendation page
    When I click the add new product recomendation button
    Then I should see the create product recomendation page
    When I input a long note
    Then I should see the note error message

  Scenario: I can add new product recomendation without select line base
    Given I am on the product recomendation page
    When I click the add new product recomendation button
    Then I should see the create product recomendation page
    When I input valid specialist value of "Drg"
    And I input valid area
    And I input valid product value
    And I input valid period value of "2025-01-01" to "2025-01-12"
    Then I should see confirmation modal of product recomendation
    And I click the confirm button of confirmation modal of product recomendation
    Then I should see the success message

  Scenario: I can not see the approval product recomendation page
    Given I am on the approval product recomendation page
    Then I click the search button of approval product recomendation page
    Then I should see unauthenticated message
