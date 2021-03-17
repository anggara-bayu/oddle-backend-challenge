package com.oddle.app.weather.parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.pojo.request.WeatherRequest;
import com.oddle.app.weather.pojo.request.weather.Clouds;
import com.oddle.app.weather.pojo.request.weather.Rain;
import com.oddle.app.weather.pojo.request.weather.Snow;
import com.oddle.app.weather.pojo.request.weather.SystemData;
import com.oddle.app.weather.pojo.request.weather.Weather;
import com.oddle.app.weather.pojo.request.weather.Wind;
import com.oddle.app.weather.pojo.response.WeatherHistory;

public class WeatherConditionParser implements BaseParser<WeatherRequest, WeatherHistory> {
	
	@Override
	public String objectToJson(WeatherRequest obj) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("main_temperature", obj.getMainData().getTemperature());
		data.put("main_pressure", obj.getMainData().getPressure());
		data.put("main_humidity", obj.getMainData().getHumidity());
		data.put("main_temperature_min", obj.getMainData().getMinTemperature());
		data.put("main_temperature_max", obj.getMainData().getMaxTemperature());
		data.put("main_sea_level", obj.getMainData().getSeaLevelPressure());
		data.put("main_ground_level", obj.getMainData().getGroundLevelPressure());
		
		Wind wind = obj.getWind();
		if(wind != null) {
			data.put("wind_speed", wind.getSpeed());
			data.put("wind_degree", wind.getDegree());
			data.put("wind_gust", wind.getGust());
		}
		
		Clouds clouds = obj.getClouds();
		if(clouds != null) {
			data.put("clouds", clouds.getCloudiness());
		}
		
		Rain rain = obj.getRain();
		if(rain != null) {
			data.put("rain_1h", rain.getVolumeInLast1h());
			data.put("rain_3h",rain.getVolumeInLast3h());
		}
		
		Snow snow = obj.getSnow();
		if(snow != null) {
			data.put("snow_1h", snow.getVolumeInLast1h());
			data.put("snow_3h", snow.getVolumeInLast3h());
		}
		
		data.put("visibility", obj.getVisibility());
		
		SystemData systemData = obj.getSystemData();
		if(systemData != null) {
			data.put("sys_message", systemData.getMessage());
			data.put("sys_sunrise", systemData.getSunriseTime());
			data.put("sys_sunset", systemData.getSunsetTime());
		}
		
		LinkedList<HashMap<String, Object>> weatherListMap = new LinkedList<HashMap<String, Object>>(); 
		List<Weather> weatherList = obj.getWeather();
		if(weatherList != null && !weatherList.isEmpty()) {
			for(Weather weather : weatherList) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				map.put("id", weather.getId());
				map.put("group", weather.getGroup());
				map.put("description", weather.getDescription());
				map.put("icon", weather.getIcon());
				
				weatherListMap.add(map);
			}
			
			data.put("weather", weatherListMap);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(data);
	}

	@Override
	public void jsonToObject(String json, WeatherHistory response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode jsonObj = mapper.readTree(json);
		
		com.oddle.app.weather.pojo.response.weather.MainData mainData = new com.oddle.app.weather.pojo.response.weather.MainData();
		mainData.setTemperature(jsonObj.get("main_temperature").floatValue());
		mainData.setPressure(jsonObj.get("main_pressure").asInt());
		mainData.setHumidity(jsonObj.get("main_humidity").shortValue());
		mainData.setMinTemperature(jsonObj.get("main_temperature_min").floatValue());
		mainData.setMaxTemperature(jsonObj.get("main_temperature_max").floatValue());
		mainData.setSeaLevelPressure(jsonObj.get("main_sea_level").asInt());
		mainData.setGroundLevelPressure(jsonObj.get("main_ground_level").asInt());
		response.setMainData(mainData);
		
		if(jsonObj.has("wind_speed")) {
			com.oddle.app.weather.pojo.response.weather.Wind wind = new com.oddle.app.weather.pojo.response.weather.Wind();
			
			wind.setSpeed(jsonObj.get("wind_speed").floatValue());
			wind.setDegree(jsonObj.get("wind_degree").floatValue());
			wind.setGust(jsonObj.get("wind_gust").floatValue());
			
			response.setWind(wind);
		}
		
		if(jsonObj.has("clouds")) {
			com.oddle.app.weather.pojo.response.weather.Clouds clouds = new com.oddle.app.weather.pojo.response.weather.Clouds();
			clouds.setCloudiness(jsonObj.get("clouds").shortValue());
			
			response.setClouds(clouds);
		}
		
		
		if(jsonObj.has("rain_1h")) {
			com.oddle.app.weather.pojo.response.weather.Rain rain = new com.oddle.app.weather.pojo.response.weather.Rain();
			
			rain.setVolumeInLast1h(jsonObj.get("rain_1h").asInt());
			rain.setVolumeInLast3h(jsonObj.get("rain_3h").asInt());
			
			response.setRain(rain);
		}
		
		if(jsonObj.has("snow_1h")) {
			com.oddle.app.weather.pojo.response.weather.Snow snow = new com.oddle.app.weather.pojo.response.weather.Snow();
			
			snow.setVolumeInLast1h(jsonObj.get("snow_1h").asInt());
			snow.setVolumeInLast3h(jsonObj.get("snow_1h").asInt());
			
			response.setSnow(snow);
		}
		
		response.setVisibility(jsonObj.get("visibility").asInt());
		
		if(jsonObj.has("sys_sunrise")) {
			response.setSysMessage(jsonObj.get("sys_message").asText());
			response.setSysSunriseTime(jsonObj.get("sys_sunrise").asLong());
			response.setSysSunsetTime(jsonObj.get("sys_sunset").asLong());
		}
		
		if(jsonObj.has("weather")) {
			
			JsonNode weatherJsonArray = jsonObj.get("weather");
			if (weatherJsonArray.isArray()) {
				LinkedList<com.oddle.app.weather.pojo.response.weather.Weather> weatherList = new LinkedList<com.oddle.app.weather.pojo.response.weather.Weather>();
		          
				for (JsonNode node : weatherJsonArray) {
					com.oddle.app.weather.pojo.response.weather.Weather weather = new com.oddle.app.weather.pojo.response.weather.Weather();
					
					weather.setId(node.get("id").asInt());
					weather.setGroup(node.get("group").asText());
					weather.setDescription(node.get("description").asText());
					weather.setIcon(node.get("icon").asText());
					
					weatherList.add(weather);
				}
				
				response.setWeather(weatherList);
			}
			
		}
		
	}
	
	
	
}
