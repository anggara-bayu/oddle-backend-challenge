package com.oddle.app.weather.pojo.request;

import java.util.List;

import com.oddle.app.weather.pojo.request.weather.*;

public class WeatherRequest {
	
	private int cityId;
	private String cityName;
	private int cod;
	private int timeZone;
	private Coordinate coordinate;
	private String base;
	private SystemData systemData;
	private long calculationDateTime;
	private int visibility;
	private MainData mainData;
	private Wind wind;
	private Rain rain;
	private Snow snow;
	private Clouds clouds;
	private List<Weather> weather;
	
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
	
	public SystemData getSystemData() {
		return systemData;
	}
	
	public void setSystemData(SystemData systemData) {
		this.systemData = systemData;
	}
	
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
