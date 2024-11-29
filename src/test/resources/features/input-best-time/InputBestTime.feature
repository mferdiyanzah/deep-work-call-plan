@InputBestTime
Feature: Input Best Time

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

  Scenario: I can visit the input best time page
    Given I am on the input best time page
    Then I should see the input best time page

  Scenario: I can use the day filter correctly
    Given I am on the input best time page
    When I click the "monday" day
    Then I should see the total doctors on the "monday" day

  Scenario: I can select the doctor from the list
    Given I am on the input best time page
    When I select the doctor from the list
    Then I should see the selected doctor

  Scenario: I can switch tab between best time and leave
    Given I am on the input best time page
    When I select the doctor from the list
    Then I should see the selected doctor
    When I switch the tab to leave
    Then I should see the leave tab
    When I switch the tab to best time
    Then I should see the best time tab

  Scenario: I can see the error message when submitting visit time of multiple location doctor with same time
    Given I am on the input best time page
    When I select the doctor from the list
    Then I should see the selected doctor
    When I submit the visit time
    Then I should see the error message when submitting visit time of multiple location doctor with same time

  Scenario: Toggle between Day and Night shifts
    Given I am on the input best time page
    When I select the doctor from the list
    Then I should see the selected doctor
    When I select "Day" shift for "Monday" at "42 BALARAJA, RSUD"
    Then "Day" shift for "Monday" at "42 BALARAJA, RSUD" should be changed

  Scenario: Submit without selecting any shifts
    Given I am on the input best time page
    When I select the doctor from the list
    Then I should see the selected doctor
    When I switch all shift to off
    And I submit the visit time
    Then I should see confirmation modal
    When I click the Confirm button
    Then I can see success modal of "Doctor's best time visit successfully updated"

  Scenario: Switch between tab day and night
    Given I am on the input best time page
    When I select the doctor from the list
    Then I should see the selected doctor
    When I switch all shift to off
    And I submit the visit time
    Then I should see confirmation modal
    When I click the Confirm button
    Then I can see success modal of "Doctor's best time visit successfully updated"

  Scenario: I can't input the leave date that already used
    Given I am on the input best time page
    When I select the doctor from the list
    Then I should see the selected doctor
    When I switch the tab to leave
    Then I should see the leave tab
    When I input the leave date of "2024-11-21" to "2024-11-29" and "enter"
    Then I should see confirmation modal
    When I click the Confirm button
    Then I can see error message of "The leave period overlaps with an existing leave record. Please choose a different date range."

  Scenario: I can see the history of doctor's leave
    Given I am on the input best time page
    When I select the doctor from the list
    Then I should see the selected doctor
    When I switch the tab to leave
    Then I should see the leave tab
    When I click the history button
    Then I can see the history of doctor leave

  Scenario: I can close the history of doctor leave
    Given I am on the input best time page
    When I select the doctor from the list
    Then I should see the selected doctor
    When I switch the tab to leave
    Then I should see the leave tab
    When I click the history button
    Then I can see the history of doctor leave
    When I click the close of history of doctor leave
    Then I can not see the history of doctor leave

  Scenario: I can see the error message when input invalid date on leave tab
    Given I am on the input best time page
    When I select the doctor from the list
    Then I should see the selected doctor
    When I switch the tab to leave
    Then I should see the leave tab
    When I input invalid date on leave tab
    Then I should see the input error message

  Scenario: I can submit the leave with a long note
    Given I am on the input best time page
    When I select the doctor from the list
    Then I should see the selected doctor
    When I switch the tab to leave
    Then I should see the leave tab
    When I input a long leave note
    And I input the leave date of "2024-11-21" to "2024-11-29" and "enter"
    Then I should see confirmation modal
    When I click the Confirm button
    Then I can see success modal of "Doctor's leave successfully created"

  Scenario: I can use the filter upcountry correctly
    Given I am on the input best time page
    When I click the "upcountry" filter button
    Then I can see the "upcountry" filter active

  Scenario: I can use the filter unscheduled correctly
    Given I am on the input best time page
    When I click the "unscheduled" filter button
    Then I can see the "unscheduled" filter active

  Scenario: I can use the filter visitable correctly
    Given I am on the input best time page
    When I click the "visitable" filter button
    Then I can see the "visitable" filter active

  Scenario: I can search the doctor by name
    Given I am on the input best time page
    When I input the doctor name "ABD"
    Then I can see the doctor "ABD" in the list
