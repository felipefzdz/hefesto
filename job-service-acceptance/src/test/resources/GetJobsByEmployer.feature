Feature: Get Jobs by employer
  As an employer
  I want to retrieve the jobs that I created
  
  Scenario: Get Jobs by employer
    Given An employer exists
    And Employer creates several jobs
    When the employer gets the jobs
    Then jobs are retrieved
    
    