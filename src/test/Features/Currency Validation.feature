Feature: Verify the list of currencies supported
  As a user I would like to ensure that user is able to retrieve public-data feeds for all the supported currencies.

  @Regression
  Scenario Outline: Verify currencies supported
    Given I have connected to the Websocket API
    When I create a subscription request for a public-data feed
      | event   | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | <event> | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    And I submit the rquest to subscribe public-data feed
    Then I verify that subscription is successful
      | event   | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | <event> | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    When I create a unsubscription request for a public-data feed
      | event       | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | unsubscribe | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    And I submit the rquest to unsubscribe public-data feed
    Then I verify that unsubscription is successful
      | event       | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | unsubscribe | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    And I close the connection

    Examples: 
      | event     | reqid | pair    | name   | depth | interval | ratecounter | snapshot | token |
      | subscribe |       | ETH/USD | ticker |       |          |             |          |       |
      | subscribe |       | ETH/EUR | ticker |       |          |             |          |       |
      | subscribe |       | ETH/CAD | ticker |       |          |             |          |       |
      | subscribe |       | ETH/JPY | ticker |       |          |             |          |       |
      | subscribe |       | ETH/GBP | ticker |       |          |             |          |       |
      | subscribe |       | ETH/CHF | ticker |       |          |             |          |       |
      | subscribe |       | ETH/AUD | ticker |       |          |             |          |       |
