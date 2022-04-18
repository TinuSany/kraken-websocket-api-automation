Feature: Verify that all the public-data feeds are up and running
  As a user I would like to connect to Kraken Websocket API and retrieve public-data feeds.

  @Regression @Smoke
  Scenario Outline: Smoke test public-data feeds
    Given I have connected to the Websocket API
    When I create a subscription request for a public-data feed
      | event   | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | <event> | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    And I submit the rquest to subscribe public-data feed
    Then I verify that subscription is successful
      | event   | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | <event> | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    And I verify that subscription feed is received for 30 seconds
    When I create a unsubscription request for a public-data feed
      | event       | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | unsubscribe | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    And I submit the rquest to unsubscribe public-data feed
    Then I verify that unsubscription is successful
      | event       | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | unsubscribe | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    And I verify that subscription feed is not received for 30 seconds
    And I close the connection

    Examples: 
      | event     | reqid | pair    | name   | depth | interval | ratecounter | snapshot | token |
      | subscribe |       | XBT/USD | ticker |       |          |             |          |       |
      | subscribe |       | XBT/USD | ohlc   |       |          |             |          |       |
      | subscribe |       | XBT/USD | trade  |       |          |             |          |       |
      | subscribe |       | XBT/USD | spread |       |          |             |          |       |
      | subscribe |       | XBT/USD | book   |       |          |             |          |       |
