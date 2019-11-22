@maven
Feature: Maven repository search

  Scenario: User can search for serenity core libraries on maven repository
    Given User is on the maven repository page
    When User enters to search for "serenity core"
    Then Search result displays "serenity core" libraries