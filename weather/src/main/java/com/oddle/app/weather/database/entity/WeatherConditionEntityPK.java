package com.oddle.app.weather.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WeatherConditionEntityPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "city_id")
	private Integer cityId;
	
	private Long datetime;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Long getDatetime() {
		return datetime;
	}

	public void setDatetime(Long datetime) {
		this.datetime = datetime;
	}
}
