Feature: Verify different depth and intevals on the feeds
  As a user I would like to connect to Kraken Websocket API and retrieve public-data feed (book and ohlc) with different depth and intervals.

  @Regression
  Scenario Outline: Verify depth and intervals
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
      | event     | reqid | pair     | name | depth | interval | ratecounter | snapshot | token |
      | subscribe |       | XDG/USD  | book |    10 |          |             |          |       |
      | subscribe |       | XDG/USD  | book |    25 |          |             |          |       |
      | subscribe |       | XDG/USD  | book |   100 |          |             |          |       |
      | subscribe |       | XDG/USD  | book |   500 |          |             |          |       |
      | subscribe |       | XDG/USD  | book |  1000 |          |             |          |       |
      | subscribe |       | USDT/USD | ohlc |       |        1 |             |          |       |
      | subscribe |       | USDT/USD | ohlc |       |        5 |             |          |       |
      | subscribe |       | USDT/USD | ohlc |       |       15 |             |          |       |
      | subscribe |       | USDT/USD | ohlc |       |       30 |             |          |       |
      | subscribe |       | USDT/USD | ohlc |       |       60 |             |          |       |
      | subscribe |       | USDT/USD | ohlc |       |      240 |             |          |       |
      | subscribe |       | USDT/USD | ohlc |       |     1440 |             |          |       |
      | subscribe |       | USDT/USD | ohlc |       |    10080 |             |          |       |
      | subscribe |       | USDT/USD | ohlc |       |    21600 |             |          |       |
