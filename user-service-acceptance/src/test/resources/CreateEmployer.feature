@ignore
Feature: Create Employer
  As a user
  I want to create an employer
  So I can manage jobs and jobs applications

  Scenario: Create Employer
    Given User fills employer data
    When the user create an employer
    Then an employer is created