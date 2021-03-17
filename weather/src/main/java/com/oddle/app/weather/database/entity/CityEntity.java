package com.oddle.app.weather.database.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "city")
public class CityEntity {
	
	@Id
	private Integer id;
	
	@Column(unique = true)
	private String name;
	
	private Integer cod;
	
	private Integer timezone;
	
	private Double coordLon;
	
	private Double coordLat;
	
	private String base;
	
	private Integer sysId;
	
	private Integer sysType;
	
	private String sysCountry;

	@JsonManagedReference
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="city")
	private List<WeatherConditionEntity> weatherConditions;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public Integer getTimezone() {
		return timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	public Double getCoordLon() {
		return coordLon;
	}

	public void setCoordLon(Double coordLon) {
		this.coordLon = coordLon;
	}

	public Double getCoordLat() {
		return coordLat;
	}

	public void setCoordLat(Double coordLat) {
		this.coordLat = coordLat;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public Integer getSysType() {
		return sysType;
	}

	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}

	public String getSysCountry() {
		return sysCountry;
	}

	public void setSysCountry(String sysCountry) {
		this.sysCountry = sysCountry;
	}

	public List<WeatherConditionEntity> getWeatherConditions() {
		return weatherConditions;
	}

	public void setWeatherConditions(List<WeatherConditionEntity> weatherConditions) {
		this.weatherConditions = weatherConditions;
	}
}
