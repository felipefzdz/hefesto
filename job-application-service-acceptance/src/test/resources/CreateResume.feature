Feature: Create Resume
  Scenario: Create Resume
    Given A jobseeker exists
    When the jobseeker creates a resume
    Then the resume is created
    