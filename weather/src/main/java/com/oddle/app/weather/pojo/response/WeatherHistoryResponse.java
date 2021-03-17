package com.oddle.app.weather.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oddle.app.weather.pojo.response.weather.Coordinate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WeatherHistoryResponse {
	
	private int cityId;
	private String cityName;
	private int cod;
	private int timeZone;
	private Coordinate coordinate;
	private String base;
	private int sysId;
	private int sysType;
	private String sysCountryCode;
	private List<WeatherHistory> history;
	
	public int getCityId() {
		return cityId;
	}
	
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public int getCod() {
		return cod;
	}
	
	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public int getTimeZone() {
		return timeZone;
	}
	
	public void setTimeZone(int timeZone) {
		this.timeZone = timeZone;
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public String getBase() {
		return base;
	}
	
	public void setBase(String base) {
		this.base = base;
	}
	
	public int getSysId() {
		return sysId;
	}
	
	public void setSysId(int sysId) {
		this.sysId = sysId;
	}
	
	public int getSysType() {
		return sysType;
	}
	
	public void setSysType(int sysType) {
		this.sysType = sysType;
	}
	
	public String getSysCountryCode() {
		return sysCountryCode;
	}
	
	public void setSysCountryCode(String sysCountryCode) {
		this.sysCountryCode = sysCountryCode;
	}
	
	public List<WeatherHistory> getHistory() {
		return history;
	}
	
	public void setHistory(List<WeatherHistory> history) {
		this.history = history;
	}
}
