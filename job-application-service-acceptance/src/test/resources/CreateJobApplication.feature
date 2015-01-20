  Feature: Create Job Application
    Scenario: Create Job Application
      Given A jobseeker exists
      And a job exists
      When the jobseeker apply to the job
      Then a job application is created
      
      