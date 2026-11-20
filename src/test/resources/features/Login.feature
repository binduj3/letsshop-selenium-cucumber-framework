Feature: Login page related functionality

  @smoke @LOG-02
  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user logs in with valid credentials
    Then the user is taken to dashboard page