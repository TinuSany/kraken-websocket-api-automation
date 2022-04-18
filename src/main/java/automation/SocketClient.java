package automation;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import extensions.Utility;

public class SocketClient extends WebSocketClient {
	
	public SocketData dataContext;
	public LocalDateTime openedTime, closedTime;
	
    public SocketClient(SocketData dataContext) throws URISyntaxException {
        super(new URI(dataContext.getURI()));
        this.dataContext=dataContext;
	}
    
	@Override
	public void onOpen(ServerHandshake handshakedata) {
        this.openedTime = LocalDateTime.now();
        String logMessage = String.format("Connection to '%s' opened at %s", dataContext.getURI(),this.openedTime.toString());
        System.out.println(logMessage);
        try {
			Utility.AppendLogger(logMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessage(String message) {
        
		LocalDateTime dt = LocalDateTime.now();
		String logMessage = String.format("Message '%s' received at %s", message, dt.toString());
        MessageQueue messageQueue = new MessageQueue(message, dt);
        dataContext.addMessage(messageQueue);
        try {
			Utility.AppendLogger(logMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onClose(int code, String reason, boolean remote) {
        this.closedTime = LocalDateTime.now();
		int connectionActiveTime = connectionActiveTime();
        System.out.println("Connection Active Time in Seconds : " + connectionActiveTime);
        
        String logMessage = String.format("Connection to the host is closed at %s", this.closedTime.toString());
        
        if (remote)
        {
        	logMessage = String.format("Connection is forcefully closed by host due to '%s' at %s", reason, this.closedTime.toString());
        }
        
        System.out.println(logMessage);
        try {
			Utility.AppendLogger(logMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
        dataContext.setStatusCode(code);
	}
	
	@Override
	public void onError(Exception ex) {
		
		String logMessage = String.format("Error in connection %s", ex.getMessage());
		System.out.println(logMessage);
        try {
			Utility.AppendLogger(logMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
//	To find the connection elapsed time (since the connection established)
    public int connectionActiveTime(){
    	LocalDateTime dt = LocalDateTime.now(); 
    	if (this.isClosed())
    	{
    		dt = this.closedTime;
    	}
    	int timeInSeconds = (int) Duration.between(openedTime, dt).getSeconds();
        dataContext.setTimeTaken(timeInSeconds);
//        System.out.println("Elapsed time : " + timeInSeconds);
        return timeInSeconds;
    }
}
