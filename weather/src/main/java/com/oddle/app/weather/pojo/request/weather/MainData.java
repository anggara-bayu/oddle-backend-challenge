package com.oddle.app.weather.pojo.request.weather;

public class MainData {
	
	private float temperature;
	private float feelsLike;
	private int pressure;
	private short humidity;
	private float minTemperature;
	private float maxTemperature;
	private int seaLevelPressure;
	private int groundLevelPressure;
	
	public float getTemperature() {
		return temperature;
	}
	
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	
	public float getFeelsLike() {
		return feelsLike;
	}
	
	public void setFeelsLike(float feelsLike) {
		this.feelsLike = feelsLike;
	}
	
	public int getPressure() {
		return pressure;
	}
	
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	
	public short getHumidity() {
		return humidity;
	}
	
	public void setHumidity(short humidity) {
		this.humidity = humidity;
	}
	
	public float getMinTemperature() {
		return minTemperature;
	}
	
	public void setMinTemperature(float minTemperature) {
		this.minTemperature = minTemperature;
	}
	
	public float getMaxTemperature() {
		return maxTemperature;
	}
	
	public void setMaxTemperature(float maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	
	public int getSeaLevelPressure() {
		return seaLevelPressure;
	}
	
	public void setSeaLevelPressure(int seaLevelPressure) {
		this.seaLevelPressure = seaLevelPressure;
	}
	
	public int getGroundLevelPressure() {
		return groundLevelPressure;
	}
	
	public void setGroundLevelPressure(int groundLevelPressure) {
		this.groundLevelPressure = groundLevelPressure;
	}
}
