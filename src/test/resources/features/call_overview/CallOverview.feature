@CallOverview
Feature: Call Overview

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

  Scenario: I can see the error message when search without the month
    Given I am on the call overview page
    When I click the search button
    Then I should see the input error message

  Scenario: I can see the search result
    Given I am on the call overview page
    When I input the month "2" of "2024" year
    And I click the search button
    Then I should see the result of overview

  Scenario: I can switch to weekly tab
    Given I am on the call overview page
    When I switch to weekly tab
    Then I should see the weekly tab

  Scenario: I can use sort by name ascending
    Given I am on the call overview page
    When I input the month "2" of "2024" year
    And I click the search button
    Then I should see the result of overview
    When I click the sort by "Person Name" button "ascending"
    Then I can see the sorted "Person Name" by "ascending"

  Scenario: I can use sort by name descending
    Given I am on the call overview page
    When I input the month "2" of "2024" year
    And I click the search button
    Then I should see the result of overview
    When I click the sort by "Person Name" button "descending"
    Then I can see the sorted "Person Name" by "descending"

  Scenario: I can filter the doctor by name
    Given I am on the call overview page
    When I input the month "2" of "2024" year
    And I click the search button
    Then I should see the result of overview
    When I can filter the doctor by "Person Name" with value "AD"
    Then I can see the filtered doctor names "AD"

  Scenario: I can reset the filter of doctor name
    Given I am on the call overview page
    When I input the month "2" of "2024" year
    And I click the search button
    Then I should see the result of overview
    When I reset the filter by "Person Name"
    Then I can see the input filter of "Person Name" is empty

  Scenario: I can use sort by class descending
    Given I am on the call overview page
    When I input the month "2" of "2024" year
    And I click the search button
    Then I should see the result of overview
    When I click the sort by "Class" button "descending"