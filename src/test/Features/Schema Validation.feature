Feature: Perform schema validation on the data feeds
  As a user I would like to ensure that response shema matches with the API contract.
  # Due to time constraints schema for the spread feed alone validated.

  @Regression @Schema
  Scenario Outline: Schema validation
    Given I have connected to the Websocket API
    When I create a subscription request for a public-data feed
      | event   | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | <event> | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    Then I perform the schema validation on "systemStatus"
    And I submit the rquest to subscribe public-data feed
    Then I perform the schema validation on "subscriptionStatus"
    Then I verify that subscription is successful
      | event   | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | <event> | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    And I verify that subscription feed is received for 10 seconds
    When I create a unsubscription request for a public-data feed
      | event       | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | unsubscribe | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    And I submit the rquest to unsubscribe public-data feed
    And I close the connection

    Examples: 
      | event     | reqid | pair    | name   | depth | interval | ratecounter | snapshot | token |
      | subscribe |       | ETH/USD | spread |       |          |             |          |       |
