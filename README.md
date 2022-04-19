"# kraken-websocket-api-automation" 

Overview
--------
Kraken Websocket API automation is developed using Apache Maven 3.8.5 and Java (TM) SE Runtime Environment 1.8. Please use respective versions or above when you run the test.

Tools/Packages Used
-------------------
Below are the tools/packages used in the framework.

1.	Cucumber: test designed in BDD framework
2.	Jackson databind: for Json serialization/deserialization
3.	Java Websocket for connecting to websocket API
4.	Json and everit for schema validation and json manipulation

Test Report
-----------
‘cucumber-reporting’ is used for reporting and run ‘mvn verify’ so cucumber reports will be generated in target/cucumber-html-reports. Please refer the below link for more details on ‘cucumber-reporting’.
https://github.com/damianszczepanik/cucumber-reporting

How to run
----------
Navigate to the root folder of the cloned library in the command prompt before execute the below maven commands.

•	Maven command to run the entire automation suite.

mvn test verify

•	Maven command to run the smoke automation alone.

mvn test verify -Dcucumber.filter.tags="@Smoke"

Test Report
-----------
Cucumber Report will be generated and will be available in target folder. Relative report path is given below.  
/target/cucumber-html-reports/overview-features.html

Logs
-----------
Simple logs will be generated and will be available in target folder. Relative log path is given below.  
/target/_log/Logging.txt

Dockerfile
-----------
Docker file is available in the parent folder with ENTRYPOINT as mvn test verify. 
