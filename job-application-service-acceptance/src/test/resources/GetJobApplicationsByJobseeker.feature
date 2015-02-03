Feature: Get job applications by jobseeker
  As a jobseeker
  I want to retrieve jobs that I've previously applied
  
  Scenario: Get job application by jobseeker
    Given A jobseeker exists
    And a job exists
    And the jobseeker apply to the job
    When the jobseeker retrieves job applications
    Then the jobseeker related job application is there