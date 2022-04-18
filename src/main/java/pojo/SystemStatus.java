package pojo;

import java.math.BigInteger;

public class SystemStatus {
	
	private BigInteger connectionID;
	private String event;
	private String status;
	private String version;
	
	public BigInteger getConnectionID() {
		return connectionID;
	}
	public void setConnectionID(BigInteger connectionID) {
		this.connectionID = connectionID;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
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

}
