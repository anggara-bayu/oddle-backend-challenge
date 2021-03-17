package com.oddle.app.weather.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oddle.app.weather.pojo.response.weather.Clouds;
import com.oddle.app.weather.pojo.response.weather.MainData;
import com.oddle.app.weather.pojo.response.weather.Rain;
import com.oddle.app.weather.pojo.response.weather.Snow;
import com.oddle.app.weather.pojo.response.weather.Weather;
import com.oddle.app.weather.pojo.response.weather.Wind;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WeatherHistory {
	
	private long calculationDateTime;
	private int visibility;
	private String sysMessage;
	private long sysSunriseTime;
	private long sysSunsetTime;
	private MainData mainData;
	private Wind wind;
	private Rain rain;
	private Snow snow;
	private Clouds clouds;
	private List<Weather> weather;
	
	public long getCalculationDateTime() {
		return calculationDateTime;
	}
	
	public void setCalculationDateTime(long calculationDateTime) {
		this.calculationDateTime = calculationDateTime;
	}
	
	public int getVisibility() {
		return visibility;
	}
	
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	
	public String getSysMessage() {
		return sysMessage;
	}
	
	public void setSysMessage(String sysMessage) {
		this.sysMessage = sysMessage;
	}
	
	public long getSysSunriseTime() {
		return sysSunriseTime;
	}
	
	public void setSysSunriseTime(long sysSunriseTime) {
		this.sysSunriseTime = sysSunriseTime;
	}
	
	public long getSysSunsetTime() {
		return sysSunsetTime;
	}
	
	public void setSysSunsetTime(long sysSunsetTime) {
		this.sysSunsetTime = sysSunsetTime;
	}
	
	public MainData getMainData() {
		return mainData;
	}
	
	public void setMainData(MainData mainData) {
		this.mainData = mainData;
	}
	
	public Wind getWind() {
		return wind;
	}
	
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	
	public Rain getRain() {
		return rain;
	}
	
	public void setRain(Rain rain) {
		this.rain = rain;
	}
	
	public Snow getSnow() {
		return snow;
	}
	
	public void setSnow(Snow snow) {
		this.snow = snow;
	}
	
	public Clouds getClouds() {
		return clouds;
	}
	
	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}
	
	public List<Weather> getWeather() {
		return weather;
	}
	
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}
}
