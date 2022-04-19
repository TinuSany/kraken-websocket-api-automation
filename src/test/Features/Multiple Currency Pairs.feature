Feature: Verify that multiple currency pairs
  As a user I would like to verify that multiple currency pairs are supported in subscribe and unsubscribe request.

  @Regression
  Scenario Outline: Verify multiple pairs in single request
    Given I have connected to the Websocket API
    When I create a subscription request with multiple currencies for a public-data feed
      | event   | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   | pair1   | pair2   | pair3   | pair4   |
      | <event> | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> | <pair1> | <pair2> | <pair3> | <pair4> |
    And I submit the rquest to subscribe public-data feed
    Then I verify that subscription with multiple currencies is successful
      | event   | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   | pair1   | pair2   | pair3   | pair4   |
      | <event> | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> | <pair1> | <pair2> | <pair3> | <pair4> |
    And I verify that subscription feed for multiple currencies are received for 30 seconds
    When I create a unsubscription request  with multiple currencies for a public-data feed
      | event       | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   | pair1   | pair2   | pair3   | pair4   |
      | unsubscribe | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> | <pair1> | <pair2> | <pair3> | <pair4> |
    And I submit the rquest to unsubscribe public-data feed
    Then I verify that unsubscription with multiple currencies is successful
      | event       | reqid   | pair   | name   | depth   | interval   | ratecounter   | snapshot   | token   | pair1   | pair2   | pair3   | pair4   |
      | unsubscribe | <reqid> | <pair> | <name> | <depth> | <interval> | <ratecounter> | <snapshot> | <token> | <pair1> | <pair2> | <pair3> | <pair4> |
    And I verify that subscription feed for multiple currencies are not received for 30 seconds
    And I close the connection

    Examples: 
      | event     | reqid | pair    | name   | depth | interval | ratecounter | snapshot | token | pair1   | pair2   | pair3   | pair4 |
      | subscribe |       | XBT/USD | spread |       |          |             |          |       | ETH/USD | XBT/EUR | ETH/EUR |       |
