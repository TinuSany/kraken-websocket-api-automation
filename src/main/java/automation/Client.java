package automation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import extensions.Utility;


public class Client {
	
	private SocketClient socketClient;
	
    public SocketClient getSocketClient() {
		return socketClient;
	}

    public Client() {
    }

	public Client connectToHost(SocketData socketContext) throws JsonMappingException, JsonProcessingException {
		try {
			
			this.socketClient = new SocketClient(socketContext);
			this.socketClient.connectBlocking();
			while (this.socketClient.dataContext.getMessageList().size() <= 0) {
				Thread.sleep(100);
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public Client subscribeToMessage(String subRequest) throws InterruptedException {
		
		if(this.socketClient.isOpen())
		{
			int currentMsqQueueSize = this.socketClient.dataContext.getMessageList().size();
//			System.out.println(currentMsqQueueSize);
			this.socketClient.send(subRequest);
			String logMessage = String.format("Subscription message '%s' is sent to the host", subRequest);
			System.out.println(logMessage);
	        try {
				Utility.AppendLogger(logMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
//	        System.out.println(this.socketClient.dataContext.getMessageList().size());
			while (this.socketClient.dataContext.getMessageList().size() <= currentMsqQueueSize) {
		        Thread.sleep(100);
			}
		}
		else
		{
			String logMessage = String.format("Connection to '%s' is already terminated", this.socketClient.dataContext.getURI());
	        System.out.println(logMessage);
	        try {
				Utility.AppendLogger(logMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return this;
	}
	
	public Client unsubscribeFromMessage(String subRequest) throws InterruptedException {
		
		if(this.socketClient.isOpen())
		{
			int currentMsqQueueSize = this.socketClient.dataContext.getMessageList().size();
			this.socketClient.send(subRequest);
			String logMessage = String.format("Unsubscription message '%s' is send to the host", subRequest);
			System.out.println(logMessage);
	        try {
				Utility.AppendLogger(logMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (this.socketClient.dataContext.getMessageList().size() <= currentMsqQueueSize) {
				Thread.sleep(100);
			}
		}
		else
		{
			String logMessage = String.format("Connection to '%s' is already terminated", this.socketClient.dataContext.getURI());
	        System.out.println(logMessage);
	        try {
				Utility.AppendLogger(logMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return this;
	}
	
	public void closeConnection() throws InterruptedException {
		
		if(this.socketClient.isOpen())
		{
			this.socketClient.closeBlocking();
		}
		else
		{
			String logMessage = String.format("Connection to '%s' is already terminated", this.socketClient.dataContext.getURI());
	        System.out.println(logMessage);
	        try {
				Utility.AppendLogger(logMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
