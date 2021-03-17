package com.oddle.app.weather.pojo.request.weather;

public class SystemData {
	
	private int id;
	private int type;
	private String message;
	private String countryCode;
	private long sunriseTime;
	private long sunsetTime;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public long getSunriseTime() {
		return sunriseTime;
	}
	
	public void setSunriseTime(long sunriseTime) {
		this.sunriseTime = sunriseTime;
	}
	
	public long getSunsetTime() {
		return sunsetTime;
	}
	
	public void setSunsetTime(long sunsetTime) {
		this.sunsetTime = sunsetTime;
	}
	
}
