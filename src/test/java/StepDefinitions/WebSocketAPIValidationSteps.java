package StepDefinitions;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import automation.Client;
import automation.MessageQueue;
import automation.SocketData;
import extensions.ScenarioContext;
import extensions.Utility;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.ErrorMessage;
import pojo.JsonRequest;
import pojo.Subscription;
import pojo.SubscriptionStatus;
import pojo.SystemStatus;

public class WebSocketAPIValidationSteps {
	
	@Given("^I have connected to the Websocket API$")
    public void i_have_connected_to_the_websocket_api() throws Throwable {

		SocketData context=new SocketData(Utility.getPropertyValue("api", "BASE_URL"), 180);
        Client client = new Client();
        client = client.connectToHost(context);

        // To ensure messages are received.
        while (client.getSocketClient().connectionActiveTime() < 2) {}
        
		ObjectMapper objectMapper = new ObjectMapper();
		SystemStatus systemStatus = objectMapper.readValue(client.getSocketClient().dataContext.getMessage(0).getReceivedMessage(), SystemStatus.class);
		
		Assert.assertEquals("Verify the status", "online",systemStatus.getStatus());
		client.getSocketClient().dataContext.setStatus(systemStatus.getStatus());
		
		Assert.assertEquals("Verify the API version", Utility.getPropertyValue("api", "API_VERSION"),systemStatus.getVersion());
		client.getSocketClient().dataContext.setVersion(systemStatus.getVersion());
        
        ScenarioContext.setContext("Client", client);   
    }
	
	@When("^I create a unsubscription request for a public-data feed$")
    @When("^I create a subscription request for a public-data feed$")
    public void i_create_a_subscription_request_for_a_publicdata_feed(DataTable dataTable) throws Throwable {
    	
    	List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		Map<String, String> row = rows.get(0);
		
		String sub_event  = row.get("event").trim();
		String sub_pair  = row.get("pair").trim();
		String sub_name  = row.get("name").trim();


    	Subscription subscription = new Subscription();
    	subscription.setName(sub_name);
    	
    	List<String> pair = new ArrayList<String> ();
    	pair.add(sub_pair);
    	
    	JsonRequest jsonRequest = new JsonRequest();
    	jsonRequest.setEvent(sub_event);
    	jsonRequest.setPair(pair);
    	jsonRequest.setSubscription(subscription);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonString = mapper.writeValueAsString(jsonRequest);
    	    	
    	JSONObject requestJSONObject = new JSONObject(jsonString);
    	JSONObject subscriptionJSONObject = (JSONObject) requestJSONObject.get("subscription");
		
    	if(row.get("reqid") != null && row.get("reqid").trim().length() > 0)
    	{
    		String sub_reqid  = row.get("reqid").trim();
    		requestJSONObject.put("reqid", Integer.parseInt(sub_reqid));
    	}
    	
    	if(row.get("depth") != null && row.get("depth").trim().length() > 0)
    	{
    		String sub_depth  = row.get("depth").trim();
    		subscriptionJSONObject.put("depth", Integer.parseInt(sub_depth));
    	}
    	
    	if(row.get("interval") != null && row.get("interval").trim().length() > 0)
    	{
    		String sub_interval  = row.get("interval").trim();
    		subscriptionJSONObject.put("interval", Integer.parseInt(sub_interval));
    	}
    	
    	if(row.get("ratecounter") != null && row.get("ratecounter").trim().length() > 0)
    	{
    		String sub_ratecounter  = row.get("ratecounter").trim();
    		subscriptionJSONObject.put("ratecounter", Boolean.parseBoolean(sub_ratecounter));
    	}
    	
    	if(row.get("snapshot") != null && row.get("snapshot").trim().length() > 0)
    	{
    		String sub_snapshot  = row.get("snapshot").trim();
    		subscriptionJSONObject.put("snapshot", Boolean.parseBoolean(sub_snapshot));
    	}
    	
    	if(row.get("token") != null && row.get("token").trim().length() > 0)
    	{
    		String sub_token  = row.get("token").trim();
    		subscriptionJSONObject.put("token", sub_token);
    	}
 
    	ScenarioContext.setContext("SubRequest", requestJSONObject.toString());   
    }

    @Then("^I verify that subscription is successful$")
    public void i_verify_that_subscription_is_successful(DataTable dataTable) throws Throwable {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		Map<String, String> row = rows.get(0);
		
	    String sub_pair  = row.get("pair").trim();
		String sub_name  = row.get("name").trim();
		
		Client client = (Client) ScenarioContext.getContext("Client");

		List<MessageQueue> subscriptionStatusList = client.getSocketClient().dataContext.getMessageList()
		.stream()
		.filter(c ->  c.getReceivedMessage().contains("subscriptionStatus")) 
		.collect(Collectors.toList());
		
		Assert.assertEquals("Verify the number of subscriptionStatus message", 1,subscriptionStatusList.size());
		
		ObjectMapper objectMapper = new ObjectMapper();
		SubscriptionStatus subscriptionStatus = objectMapper.readValue(subscriptionStatusList.get(0).getReceivedMessage(), SubscriptionStatus.class);
		
		Assert.assertTrue("Verify that channel name contains feed name", subscriptionStatus.getChannelName().contains(sub_name));
		Assert.assertEquals("Verify the currency pairs", sub_pair ,subscriptionStatus.getPair());
		Assert.assertEquals("Verify the status", "subscribed" ,subscriptionStatus.getStatus());
		Assert.assertEquals("Verify the name", sub_name ,subscriptionStatus.getSubscription().getName());
		
		if(row.get("depth") != null && row.get("depth").trim().length() > 0)
    	{
    		String sub_depth  = row.get("depth").trim();
    		Assert.assertTrue("Verify that channel name contains depth", subscriptionStatus.getChannelName().contains(sub_depth));
    	}
    	
    	if(row.get("interval") != null && row.get("interval").trim().length() > 0)
    	{
    		String sub_interval  = row.get("interval").trim();
    		Assert.assertTrue("Verify that channel name contains interval", subscriptionStatus.getChannelName().contains(sub_interval));
    	}
		
		ScenarioContext.setContext("ChannelID", subscriptionStatus.getChannelID());   

    }
    
    @Then("^I verify that subscription is not successful$")
    public void i_verify_that_subscription_is_not_successful(DataTable dataTable) throws Throwable {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		Map<String, String> row = rows.get(0);
		
	    String sub_pair  = row.get("pair").trim();
		String sub_name  = row.get("name").trim();
		String sub_error  = row.get("error").trim();
		
		Client client = (Client) ScenarioContext.getContext("Client");

		List<MessageQueue> subscriptionStatusList = client.getSocketClient().dataContext.getMessageList()
		.stream()
		.filter(c ->  c.getReceivedMessage().contains("subscriptionStatus")) 
		.collect(Collectors.toList());
		
		Assert.assertEquals("Verify the number of subscriptionStatus message", 1,subscriptionStatusList.size());
		
		System.out.println(subscriptionStatusList.get(0).getReceivedMessage());
		
		ObjectMapper objectMapper = new ObjectMapper();
		ErrorMessage errorMessage = objectMapper.readValue(subscriptionStatusList.get(0).getReceivedMessage(), ErrorMessage.class);
				
		Assert.assertEquals("Verify the currency pairs", sub_pair ,errorMessage.getPair());
		Assert.assertEquals("Verify the status", "error" ,errorMessage.getStatus());
		Assert.assertEquals("Verify the name", sub_name ,errorMessage.getSubscription().getName());
		Assert.assertEquals("Verify the error message", sub_error ,errorMessage.getErrorMessage());

    }

    @Then("^I verify that unsubscription is successful$")
    public void i_verify_that_unsubscription_is_successful(DataTable dataTable) throws Throwable {
    	List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		Map<String, String> row = rows.get(0);
		
		String sub_pair  = row.get("pair").trim();
		String sub_name  = row.get("name").trim();
		
		Client client = (Client) ScenarioContext.getContext("Client");
		int channelID = (int) ScenarioContext.getContext("ChannelID");

		List<MessageQueue> subscriptionStatusList = client.getSocketClient().dataContext.getMessageList()
		.stream()
		.filter(c ->  c.getReceivedMessage().contains("subscriptionStatus") &&
				c.getReceivedMessage().contains(String.valueOf(channelID))) 
		.collect(Collectors.toList());
		
		Assert.assertEquals("Verify the number of subscriptionStatus messages", 2,subscriptionStatusList.size());
		
		ObjectMapper objectMapper = new ObjectMapper();
		SubscriptionStatus subscriptionStatus = objectMapper.readValue(subscriptionStatusList.get(1).getReceivedMessage(), SubscriptionStatus.class);
		
		Assert.assertTrue("Verify that channel name contains feed name", subscriptionStatus.getChannelName().contains(sub_name));
		Assert.assertEquals("Verify the currency pairs", sub_pair ,subscriptionStatus.getPair());
		Assert.assertEquals("Verify the status", "unsubscribed" ,subscriptionStatus.getStatus());
		Assert.assertEquals("Verify the name", sub_name ,subscriptionStatus.getSubscription().getName());
    }

    @And("^I submit the rquest to unsubscribe public-data feed$")
    @And("^I submit the rquest to subscribe public-data feed$")
    public void i_submit_the_rquest_to_subscribe_publicdata_feed() throws Throwable {
    	
    	Client client = (Client) ScenarioContext.getContext("Client");
    	String jsonString = (String) ScenarioContext.getContext("SubRequest");
    	client = client.subscribeToMessage(jsonString);
    	Thread.sleep(1000);
    	ScenarioContext.setContext("Client", client); 
    }

    @And("I verify that subscription feed is received for {int} seconds")
    public void i_verify_that_subscription_feed_is_received_for_something_seconds(int elapsedTime) throws Throwable {
    	Client client = (Client) ScenarioContext.getContext("Client");
    	int channelID = (int) ScenarioContext.getContext("ChannelID");
    	
    	//int elapsedTime = Integer.parseInt(timeOut);
        LocalDateTime start_time = LocalDateTime.now();
        LocalDateTime end_time = start_time.plusSeconds(elapsedTime + 2); 
        Thread.sleep(elapsedTime * 1000);
        
        List<MessageQueue> subscribedMessageList = client.getSocketClient().dataContext.getMessageList()
    		.stream()
    		.filter(c ->  c.getReceivedMessage().contains(String.valueOf(channelID)) && 
    				c.getReceivedDateTime().isAfter(start_time) && 
    				c.getReceivedDateTime().isBefore(end_time)   ) 
    		.collect(Collectors.toList());
        
        Assert.assertTrue("Verify that atleast one subscribed message is received", subscribedMessageList.size() >= 1);
        
        subscribedMessageList = client.getSocketClient().dataContext.getMessageList()
        		.stream()
        		.filter(c -> c.getReceivedDateTime().isAfter(start_time) && 
        				c.getReceivedDateTime().isBefore(end_time)   ) 
        		.collect(Collectors.toList());
        
        Assert.assertTrue("Verify that at least one message received in every second", subscribedMessageList.size() >= elapsedTime); // including 'heartbeat'
        
    }

    @And("I verify that subscription feed is not received for {int} seconds")
    public void i_verify_that_subscription_feed_is_not_received_for_something_seconds(int elapsedTime) throws Throwable {
    	Client client = (Client) ScenarioContext.getContext("Client");
    	int channelID = (int) ScenarioContext.getContext("ChannelID");
        LocalDateTime start_time = LocalDateTime.now();
        LocalDateTime end_time = start_time.plusSeconds(elapsedTime + 2); 
        Thread.sleep(elapsedTime * 1000);
        
        List<MessageQueue> subscribedMessageList = client.getSocketClient().dataContext.getMessageList()
    		.stream()
    		.filter(c -> c.getReceivedMessage().contains(String.valueOf(channelID)) && 
    				c.getReceivedDateTime().isAfter(start_time) && 
    				c.getReceivedDateTime().isBefore(end_time)   ) 
    		.collect(Collectors.toList());
        
        Assert.assertTrue("Verify that no subscribed message is received after the unsubscribe", subscribedMessageList.size() == 0);
        
        subscribedMessageList = client.getSocketClient().dataContext.getMessageList()
        		.stream()
        		.filter(c -> c.getReceivedDateTime().isAfter(start_time) && 
        				c.getReceivedDateTime().isBefore(end_time)   ) 
        		.collect(Collectors.toList());
        
        Assert.assertTrue("Verify that at least one message received in every second", subscribedMessageList.size() >= elapsedTime); // 'heartbeat' received after unsubscription
    }

    @And("^I close the connection$")
    public void i_close_the_connection() throws Throwable {
    	Client client = (Client) ScenarioContext.getContext("Client");
    	client.closeConnection();
    }
    
    @And("^I verify that subscription feed is not received when subscription is unsuccessful$")
    public void i_verify_that_subscription_feed_is_not_received_when_subscription_is_unsuccessful() throws Throwable {
    	Client client = (Client) ScenarioContext.getContext("Client");
    	int elapsedTime = 10;
        LocalDateTime start_time = LocalDateTime.now().plusSeconds(1);
        LocalDateTime end_time = start_time.plusSeconds(elapsedTime + 1); 
        Thread.sleep(elapsedTime * 1000);
        
        List<MessageQueue> subscribedMessageList = client.getSocketClient().dataContext.getMessageList()
    		.stream()
    		.filter(c -> c.getReceivedDateTime().isAfter(start_time) && 
    				c.getReceivedDateTime().isBefore(end_time)   ) 
    		.collect(Collectors.toList());
        
        Assert.assertTrue("Verify that no subscribed message is received when subscription unsuccessful", subscribedMessageList.size() == 0);
    }
    
    @Then("I perform the schema validation on {string}")
    public void i_perform_the_schema_validation_on_something(String schema) throws Throwable {

		Client client = (Client) ScenarioContext.getContext("Client");
		
		//default is systemStatus
		String schemaResourceName = "systemstatusschema.json";
		String actualMessage = client.getSocketClient().dataContext.getMessage(0).getReceivedMessage();
		
		switch(schema)
		{
			case "subscriptionStatus" :
				schemaResourceName = "subscriptionstatusschema.json";
				actualMessage = client.getSocketClient().dataContext.getMessage(1).getReceivedMessage();
				break;
			case "spread" :
				schemaResourceName = "spreadschema.json";
				int channelID = (int) ScenarioContext.getContext("ChannelID");
		        List<MessageQueue> subscribedMessageList = client.getSocketClient().dataContext.getMessageList()
		    		.stream()
		    		.filter(c ->  c.getReceivedMessage().contains(String.valueOf(channelID))) 
		    		.collect(Collectors.toList());
		        actualMessage = subscribedMessageList.get(1).getReceivedMessage();
				break;
		}

		JSONObject jsonSchema = new JSONObject(
		       new JSONTokener(getClass().getClassLoader().getResourceAsStream(schemaResourceName)));
		   
		JSONObject jsonSubject = new JSONObject(actualMessage);
		   
		Schema expectedSchema = SchemaLoader.load(jsonSchema);
		expectedSchema.validate(jsonSubject);
		System.out.println(String.format("Schema of %s is successfully tested.", schema));
        
    }
    
    @And("^I verify that timestamp increases over a time$")
	public void i_verify_that_timestamp_increases_over_a_time() throws Throwable {
		Client client = (Client) ScenarioContext.getContext("Client");
		int channelID = (int) ScenarioContext.getContext("ChannelID");

		List<MessageQueue> subscribedMessageList = client.getSocketClient().dataContext.getMessageList().stream()
				.filter(c -> c.getReceivedMessage().contains("["+String.valueOf(channelID)))
				.collect(Collectors.toList());

		for (int i = 0; i < subscribedMessageList.size() - 1; i++) 
		{

			JSONArray jsonMessage1 = new JSONArray(subscribedMessageList.get(i).getReceivedMessage());
			JSONArray jsonSpread1 = new JSONArray(jsonMessage1.get(1).toString());
			BigDecimal timeStamp1 = jsonSpread1.getBigDecimal(2);
			BigDecimal timeStamp2 = timeStamp1.multiply(new BigDecimal(1000));
			long epoch = timeStamp2.longValue();
			LocalDateTime ldt1 = Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();

			String europeanDatePattern = "dd.MM.yyyy hh:mm:ss:SSSSSS";
			DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);

			jsonMessage1 = new JSONArray(subscribedMessageList.get(i + 1).getReceivedMessage());
			jsonSpread1 = new JSONArray(jsonMessage1.get(1).toString());
			timeStamp1 = jsonSpread1.getBigDecimal(2);
			timeStamp2 = timeStamp1.multiply(new BigDecimal(1000));
			epoch = timeStamp2.longValue();
			LocalDateTime ldt2 = Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();

			Duration duration = Duration.between(ldt1, ldt2);
			long nano_seconds = duration.getNano();
			System.out.println(String.format("Timestamp %s is greater than %s by %s nano seconds", europeanDateFormatter.format(ldt2),
					europeanDateFormatter.format(ldt1),nano_seconds));
			
			Assert.assertTrue("Timestamp increases over a time", nano_seconds >= 0);

		}
	}

}
