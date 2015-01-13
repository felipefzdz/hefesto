Feature: Update User
  As a user
  I want to update a user
  
  Scenario: Update User Name
    Given A user exists
    When someone updates that user with different name
    Then the user has the new name
    
    