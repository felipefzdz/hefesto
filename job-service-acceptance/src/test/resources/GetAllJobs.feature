Feature: Get all jobs
  As a user 
  I want to get all the jobs in the system
  
  Scenario: Get all jobs
    Given User creates several jobs
    When User get all jobs
    Then all jobs are retrieved