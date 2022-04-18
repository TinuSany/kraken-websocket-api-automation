package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription {
	
	private String name;
	@JsonIgnore
	private int depth;
	@JsonIgnore
	private int interval;
	@JsonIgnore
	private Boolean ratecounter;
	@JsonIgnore
	private Boolean snapshot;
	@JsonIgnore
	private String token;
	

	public Boolean getRatecounter() {
		return ratecounter;
	}
	public void setRatecounter(Boolean ratecounter) {
		this.ratecounter = ratecounter;
	}
	public Boolean getSnapshot() {
		return snapshot;
	}
	public void setSnapshot(Boolean snapshot) {
		this.snapshot = snapshot;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}

}
