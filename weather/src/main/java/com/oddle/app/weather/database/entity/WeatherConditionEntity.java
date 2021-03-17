package com.oddle.app.weather.database.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "weather_condition")
public class WeatherConditionEntity {
	
	@EmbeddedId
	private WeatherConditionEntityPK primaryKey;
	
	@Column(columnDefinition = "TEXT")
	private String weatherJson;
	
	@JsonBackReference
	@MapsId("city_id")
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
	private CityEntity city;

	public WeatherConditionEntityPK getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(WeatherConditionEntityPK primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getWeatherJson() {
		return weatherJson;
	}

	public void setWeatherJson(String weatherJson) {
		this.weatherJson = weatherJson;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}
}
