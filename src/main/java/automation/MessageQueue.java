package automation;

import java.time.LocalDateTime;

public class MessageQueue {
	
	private String receivedMessage;
	private LocalDateTime receivedDateTime;
	
	public MessageQueue(String receivedMessage, LocalDateTime receivedDateTime)
	{
		this.receivedMessage = receivedMessage;
		this.receivedDateTime = receivedDateTime;
	}
	
	public String getReceivedMessage() {
		return receivedMessage;
	}
	public void setReceivedMessage(String receivedMessage) {
		this.receivedMessage = receivedMessage;
	}
	public LocalDateTime getReceivedDateTime() {
		return receivedDateTime;
	}
	public void setReceivedDateTime(LocalDateTime receivedDateTime) {
		this.receivedDateTime = receivedDateTime;
	}
}
