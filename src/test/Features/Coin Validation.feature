Feature: Verify the list of coins supported
  As a user I would like to ensure that user is able to retrieve public-data feeds for all the supported coins.

  @Regression
  Scenario Outline: Verify coins supported
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
      | event     | reqid | pair       | name   | depth | interval | ratecounter | snapshot | token |
      | subscribe |       | 1INCH/USD  | spread |       |          |             |          |       |
      | subscribe |       | AAVE/USD   | spread |       |          |             |          |       |
      | subscribe |       | GHST/USD   | spread |       |          |             |          |       |
      | subscribe |       | ACA/USD    | spread |       |          |             |          |       |
      | subscribe |       | AKT/USD    | spread |       |          |             |          |       |
      | subscribe |       | ALCX/USD   | spread |       |          |             |          |       |
      | subscribe |       | ALGO/USD   | spread |       |          |             |          |       |
      | subscribe |       | AIR/USD    | spread |       |          |             |          |       |
      | subscribe |       | ANKR/USD   | spread |       |          |             |          |       |
      | subscribe |       | APE/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ANT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ASTR/USD   | spread |       |          |             |          |       |
      #| subscribe |       | AUDIO/USD  | spread |       |          |             |          |       |
      #| subscribe |       | REP/USD    | spread |       |          |             |          |       |
      #| subscribe |       | REPV2/USD  | spread |       |          |             |          |       |
      #| subscribe |       | AVAX/USD   | spread |       |          |             |          |       |
      #| subscribe |       | AXS/USD    | spread |       |          |             |          |       |
      #| subscribe |       | BADGER/USD | spread |       |          |             |          |       |
      #| subscribe |       | BAL/USD    | spread |       |          |             |          |       |
      #| subscribe |       | BNT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | BAND/USD   | spread |       |          |             |          |       |
      #| subscribe |       | BOND/USD   | spread |       |          |             |          |       |
      #| subscribe |       | BAT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | BICO/USD   | spread |       |          |             |          |       |
      #| subscribe |       | BNC/USD    | spread |       |          |             |          |       |
      #| subscribe |       | XBT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | BCH/USD    | spread |       |          |             |          |       |
      #| subscribe |       | FIDA/USD   | spread |       |          |             |          |       |
      #| subscribe |       | ADA/USD    | spread |       |          |             |          |       |
      #| subscribe |       | CTSI/USD   | spread |       |          |             |          |       |
      #| subscribe |       | LINK/USD   | spread |       |          |             |          |       |
      #| subscribe |       | CHZ/USD    | spread |       |          |             |          |       |
      #| subscribe |       | COMP/USD   | spread |       |          |             |          |       |
      #| subscribe |       | CVX/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ATOM/USD   | spread |       |          |             |          |       |
      #| subscribe |       | CQT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | CRV/USD    | spread |       |          |             |          |       |
      #| subscribe |       | DAI/USD    | spread |       |          |             |          |       |
      #| subscribe |       | DASH/USD   | spread |       |          |             |          |       |
      #| subscribe |       | MANA/USD   | spread |       |          |             |          |       |
      #| subscribe |       | XDG/USD    | spread |       |          |             |          |       |
      #| subscribe |       | DYDX/USD   | spread |       |          |             |          |       |
      #| subscribe |       | EWT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ENJ/USD    | spread |       |          |             |          |       |
      #| subscribe |       | MLN/USD    | spread |       |          |             |          |       |
      #| subscribe |       | EOS/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ETH/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ETC/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ENS/USD    | spread |       |          |             |          |       |
      #| subscribe |       | FIL/USD    | spread |       |          |             |          |       |
      #| subscribe |       | FLOW/USD   | spread |       |          |             |          |       |
      #| subscribe |       | FXS/USD    | spread |       |          |             |          |       |
      #| subscribe |       | GALA/USD   | spread |       |          |             |          |       |
      #| subscribe |       | GNO/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ICX/USD    | spread |       |          |             |          |       |
      #| subscribe |       | IMX/USD    | spread |       |          |             |          |       |
      #| subscribe |       | INJ/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ICP/USD    | spread |       |          |             |          |       |
      #| subscribe |       | JASMY/USD  | spread |       |          |             |          |       |
      #| subscribe |       | KAR/USD    | spread |       |          |             |          |       |
      #| subscribe |       | KAVA/USD   | spread |       |          |             |          |       |
      #| subscribe |       | KEEP/USD   | spread |       |          |             |          |       |
      #| subscribe |       | KP3R/USD   | spread |       |          |             |          |       |
      #| subscribe |       | KILT/USD   | spread |       |          |             |          |       |
      #| subscribe |       | KIN/USD    | spread |       |          |             |          |       |
      #| subscribe |       | KINT/USD   | spread |       |          |             |          |       |
      #| subscribe |       | KSM/USD    | spread |       |          |             |          |       |
      #| subscribe |       | KNC/USD    | spread |       |          |             |          |       |
      #| subscribe |       | LSK/USD    | spread |       |          |             |          |       |
      #| subscribe |       | LTC/USD    | spread |       |          |             |          |       |
      #| subscribe |       | LPT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | LRC/USD    | spread |       |          |             |          |       |
      #| subscribe |       | MKR/USD    | spread |       |          |             |          |       |
      #| subscribe |       | MNGO/USD   | spread |       |          |             |          |       |
      #| subscribe |       | MASK/USD   | spread |       |          |             |          |       |
      #| subscribe |       | MC/USD     | spread |       |          |             |          |       |
      #| subscribe |       | MINA/USD   | spread |       |          |             |          |       |
      #| subscribe |       | MIR/USD    | spread |       |          |             |          |       |
      #| subscribe |       | XMR/USD    | spread |       |          |             |          |       |
      #| subscribe |       | GLMR/USD   | spread |       |          |             |          |       |
      #| subscribe |       | MOVR/USD   | spread |       |          |             |          |       |
      #| subscribe |       | MULTI/USD  | spread |       |          |             |          |       |
      #| subscribe |       | ALICE/USD  | spread |       |          |             |          |       |
      #| subscribe |       | NANO/USD   | spread |       |          |             |          |       |
      #| subscribe |       | OCEAN/USD  | spread |       |          |             |          |       |
      #| subscribe |       | OMG/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ORCA/USD   | spread |       |          |             |          |       |
      #| subscribe |       | OXT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | OGN/USD    | spread |       |          |             |          |       |
      #| subscribe |       | OXY/USD    | spread |       |          |             |          |       |
      #| subscribe |       | PAXG/USD   | spread |       |          |             |          |       |
      #| subscribe |       | PERP/USD   | spread |       |          |             |          |       |
      #| subscribe |       | PHA/USD    | spread |       |          |             |          |       |
      #| subscribe |       | DOT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | MATIC/USD  | spread |       |          |             |          |       |
      #| subscribe |       | POWR/USD   | spread |       |          |             |          |       |
      #| subscribe |       | PLA/USD    | spread |       |          |             |          |       |
      #| subscribe |       | PSTAKE/USD | spread |       |          |             |          |       |
      #| subscribe |       | QTUM/USD   | spread |       |          |             |          |       |
      #| subscribe |       | QNT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | RARI/USD   | spread |       |          |             |          |       |
      #| subscribe |       | RAY/USD    | spread |       |          |             |          |       |
      #| subscribe |       | REN/USD    | spread |       |          |             |          |       |
      #| subscribe |       | RNDR/USD   | spread |       |          |             |          |       |
      #| subscribe |       | XRP/USD    | spread |       |          |             |          |       |
      #| subscribe |       | XRT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | RBC/USD    | spread |       |          |             |          |       |
      #| subscribe |       | SBR/USD    | spread |       |          |             |          |       |
      #| subscribe |       | SCRT/USD   | spread |       |          |             |          |       |
      #| subscribe |       | SRM/USD    | spread |       |          |             |          |       |
      #| subscribe |       | SHIB/USD   | spread |       |          |             |          |       |
      #| subscribe |       | SDN/USD    | spread |       |          |             |          |       |
      #| subscribe |       | SC/USD     | spread |       |          |             |          |       |
      #| subscribe |       | SOL/USD    | spread |       |          |             |          |       |
      #| subscribe |       | SGB/USD    | spread |       |          |             |          |       |
      #| subscribe |       | SPELL/USD  | spread |       |          |             |          |       |
      #| subscribe |       | ATLAS/USD  | spread |       |          |             |          |       |
      #| subscribe |       | POLIS/USD  | spread |       |          |             |          |       |
      #| subscribe |       | XLM/USD    | spread |       |          |             |          |       |
      #| subscribe |       | STEP/USD   | spread |       |          |             |          |       |
      #| subscribe |       | STORJ/USD  | spread |       |          |             |          |       |
      #| subscribe |       | SUSHI/USD  | spread |       |          |             |          |       |
      #| subscribe |       | RARE/USD   | spread |       |          |             |          |       |
      #| subscribe |       | SNX/USD    | spread |       |          |             |          |       |
      #| subscribe |       | TBTC/USD   | spread |       |          |             |          |       |
      #| subscribe |       | LUNA/USD   | spread |       |          |             |          |       |
      #| subscribe |       | UST/USD    | spread |       |          |             |          |       |
      #| subscribe |       | USDT/USD   | spread |       |          |             |          |       |
      #| subscribe |       | XTZ/USD    | spread |       |          |             |          |       |
      #| subscribe |       | TOKE/USD   | spread |       |          |             |          |       |
      #| subscribe |       | GRT/USD    | spread |       |          |             |          |       |
      #| subscribe |       | SAND/USD   | spread |       |          |             |          |       |
      #| subscribe |       | TRX/USD    | spread |       |          |             |          |       |
      #| subscribe |       | TRIBE/USD  | spread |       |          |             |          |       |
      #| subscribe |       | UNI/USD    | spread |       |          |             |          |       |
      #| subscribe |       | UMA/USD    | spread |       |          |             |          |       |
      #| subscribe |       | USDC/USD   | spread |       |          |             |          |       |
      #| subscribe |       | WAVES/USD  | spread |       |          |             |          |       |
      #| subscribe |       | WOO/USD    | spread |       |          |             |          |       |
      #| subscribe |       | WBTC/USD   | spread |       |          |             |          |       |
      #| subscribe |       | YFI/USD    | spread |       |          |             |          |       |
      #| subscribe |       | YGG/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ZEC/USD    | spread |       |          |             |          |       |
      #| subscribe |       | ZRX/USD    | spread |       |          |             |          |       |