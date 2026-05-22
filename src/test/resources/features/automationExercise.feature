@ui
Feature: Automation Exercise Product Search

  @smoke
  Scenario Outline: Search product successfully

    Given user is on automation exercise home page
    When user navigates to products page
    And user searches for "<product>"
    Then searched products should be displayed

    Examples:
      | product |
      | dress   |
      | top     |
      | jeans   |