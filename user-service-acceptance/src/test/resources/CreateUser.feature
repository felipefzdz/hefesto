Feature: Create User
  As a user
  I want to create users
  So I can manage jobs and jobs applications

  Scenario: Create Employer
    Given User fills employer data
    When the user creates a user
    Then an employer is created