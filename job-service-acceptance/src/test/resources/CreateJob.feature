Feature: Create Job
  As an employer
  I want to create jobs
  So the jobseekers can apply to them

  Scenario: Create Job
    Given An employer exists
    And Employer fills job data
    When the employer creates a job
    Then a job is created