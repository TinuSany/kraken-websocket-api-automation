Feature: Verify error messages are displayed when invalid inputs are provided
  As a user I would like to ensure that appropriate error messages are displayed when invlid inputs entered.

  @Regression @Smoke
  Scenario Outline: Smoke test public-data feeds
    Given I have connected to the Websocket API
    When I create a subscription request for a public-data feed
      | event   | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   |
      | <event> | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> |
    And I submit the rquest to subscribe public-data feed
    Then I verify that subscription is not successful
      | event   | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   | error   |
      | <event> | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> | <error> |
    And I verify that subscription feed is not received when subscription is unsuccessful
    And I close the connection

    Examples: 
      | event      | reqid | pair    | name   | depth | interval | ratecounter | snapshot | token | error                                      |
      | subscribed |       | XBT/USD | ticker |       |          |             |          |       | Unsupported event                          |
      | subscribe  |       | XBT/TST | ohlc   |       |          |             |          |       | Currency pair not supported XBT/TST        |
      | subscribe  |       | XBT/INR | trade  |       |          |             |          |       | Currency pair not supported XBT/INR        |
      | subscribe  |       | XBT/USD | test   |       |          |             |          |       | Subscription name invalid                  |
      | subscribe  |       | XBT/USD | spread |    10 |          |             |          |       | Subscription spread doesn't require depth  |
      | subscribe  |       | XBT/USD | book   |    20 |          |             |          |       | Subscription depth not supported           |
      | subscribe  |       | XBT/USD | book   |       |       30 |             |          |       | Subscription book doesn't require interval |
      | subscribe  |       | XBT/USD | ohlc   |       |       20 |             |          |       | Subscription ohlc interval not supported   |
