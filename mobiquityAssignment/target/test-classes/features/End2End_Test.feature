@ApiAutomation
Feature: End to End test mobiquity assignment

  @ApiAutomation
  Scenario: the Authorized user can Add and Remove a book.
    Given Search for the user with username "Delphine" and validate user email id "Chaim_McDermott@dana.io"
    When Fetch posts written by the user
    Then Fetch the comments and validate if the emails in the comment section are in the proper format