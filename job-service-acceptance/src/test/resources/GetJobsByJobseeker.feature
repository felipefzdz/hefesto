Feature: Get jobs by jobseeker
  As a jobseeker
  I want to retrieve jobs that I've previously saved
  
  Scenario: Get jobs by jobseeker
    Given A jobseeker exists
    And a job exists
    And the jobseeker save the job
    When the jobseeker retrieves jobs
    Then the jobseeker related job is there