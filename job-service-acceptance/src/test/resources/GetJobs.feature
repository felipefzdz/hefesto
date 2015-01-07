Feature: Get Jobs
  As an employer
  I want to retrieve the jobs that I created
  
  Scenario: Get Jobs
    Given An employer exists when get jobs
    And Employer creates several jobs
    When the employer gets the jobs
    Then jobs are retrieved
    
    