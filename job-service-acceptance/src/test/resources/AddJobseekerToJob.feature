Feature: Add jobseeker to job
  As a jobseeker
  I want to add myself as interested in a job
  So I can view it later
  
  Scenario: Add jobseeker to job
    Given A jobseeker exists
    And a job exists
    When the jobseeker save the job
    Then the jobseeker get added into that job