package automation;

import java.util.ArrayList;
import java.util.List;

public class SocketData {
	
	private String URI;
	private List<MessageQueue> messageList=new ArrayList<>();
	private int statusCode;
	private int timeOut;
	private int timeTaken;
	private String status;
	private String version;
	
	public SocketData(String URI, int timeOut)
	{
		this.URI = URI;
		this.timeOut = timeOut;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public List<MessageQueue> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<MessageQueue> messageList) {
		this.messageList = messageList;
	}
	
	public void addMessage(MessageQueue messageListItem) {
		this.messageList.add(messageListItem);
	}
	
	public MessageQueue getMessage(int itemNo) {
		return this.messageList.get(itemNo);
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public int getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}
}
