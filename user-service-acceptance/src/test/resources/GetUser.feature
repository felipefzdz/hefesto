Feature: Get User
  As a user
  I want to get a user by user id
  So I can get information about that user

  Scenario: Get User
    Given A user exists
    When the user gets a user by id
    Then a user is returned