package com.oddle.app.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oddle.app.weather.config.ConfigProperties;
import com.oddle.app.weather.exception.DataAlreadyExistsException;
import com.oddle.app.weather.exception.DataNotFoundException;
import com.oddle.app.weather.pojo.request.WeatherRequest;
import com.oddle.app.weather.pojo.response.WeatherHistoryResponse;
import com.oddle.app.weather.pojo.response.WeatherResponse;
import com.oddle.app.weather.service.WeatherService;
import com.oddle.app.weather.status.Status;
import com.oddle.app.weather.status.StatusBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {
	
	private ConfigProperties config;
	private RestTemplate restTemplate;
	private StringBuilder sb;
	
	@Autowired
	private WeatherService weatherService;

	@Autowired
	public WeatherController(RestTemplateBuilder builder, ConfigProperties config) {
	    this.restTemplate = builder.build();
	    this.config = config;
	    this.sb = new StringBuilder();
	}
	
    @GetMapping("")
    public Map<String, Object> greetings() {
        return Collections.singletonMap("message", "Welcome to Weather Forecasting API!");
    }
    
    @GetMapping("/weather")
    public HashMap<String, Object> getWeather(
    		@RequestParam(value = "city", defaultValue = "London") String cityName
	) {
    	sb.setLength(0);
    	sb.append("https://api.openweathermap.org/data/2.5/weather?q=");
    	sb.append(cityName);
    	sb.append("&appid=");
    	sb.append(config.getApikey());
    	
    	HashMap<String, Object> response = new HashMap<String, Object>();
    	
    	try {
    		WeatherResponse weatherData = restTemplate.getForObject(sb.toString(), WeatherResponse.class);
    		
    		response.put("status", StatusBuilder.getStatus(Status.SUCCESS.name()));
    		response.put("response", weatherData);
    	} catch(Exception e) {
    		response.put("status", StatusBuilder.getStatus(Status.CITY_NOT_FOUND.name()));
    	}
    	
    	return response;
    }
    
    @PostMapping(value = "/save-weather", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> saveWeatherData(@RequestBody WeatherRequest request) {
    	HashMap<String, Object> response = new HashMap<String, Object>();
  	
	  	try {
	  		weatherService.saveWeatherData(request);
	  		response.put("status", StatusBuilder.getStatus(Status.SUCCESS.name()));
	  	} catch(DataAlreadyExistsException d) {
	  		response.put("status", StatusBuilder.getStatus(Status.DATA_ALREADY_EXISTS.name()));
	  	} catch(Exception e) {
	  		//e.printStackTrace();
	  		response.put("status", StatusBuilder.getStatus(Status.FAILED_TO_SAVE_DATA.name()));
	  	}
	  	
	  	return response;
    }
    
    @PostMapping(value = "/update-weather", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> updateWeatherData(@RequestBody WeatherRequest request) {
    	HashMap<String, Object> response = new HashMap<String, Object>();
  	
	  	try {
	  		weatherService.updateWeatherData(request);
	  		response.put("status", StatusBuilder.getStatus(Status.SUCCESS.name()));
	  	} catch(DataNotFoundException d) {
	  		response.put("status", StatusBuilder.getStatus(Status.DATA_NOT_FOUND.name()));
	  	} catch(Exception e) {
	  		response.put("status", StatusBuilder.getStatus(Status.FAILED_TO_UPDATE_DATA.name()));
	  	}
	  	
	  	return response;
    }
    
    @PostMapping(value = "/delete-weather", produces = "application/json")
    public HashMap<String, Object> deleteWeatherData(
    		@RequestParam(value = "cityId") int cityId,
    		@RequestParam(value = "calculationDatetime") long calculationDatetime
	) {
    	HashMap<String, Object> response = new HashMap<String, Object>();
  	
	  	try {
	  		weatherService.deleteWeatherData(cityId, calculationDatetime);
	  		response.put("status", StatusBuilder.getStatus(Status.SUCCESS.name()));
	  	} catch(DataNotFoundException d) {
	  		response.put("status", StatusBuilder.getStatus(Status.DATA_NOT_FOUND.name()));
	  	} catch(Exception e) {
	  		response.put("status", StatusBuilder.getStatus(Status.FAILED_TO_DELETE_DATA.name()));
	  	}
	  	
	  	return response;
    }

    @GetMapping("/historical-weather-data")
    public HashMap<String, Object> getHistoricalWeatherData(
    		@RequestParam(value = "city", defaultValue = "London") String cityName,
    		@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
    		@RequestParam(value = "pageSize", defaultValue = "5") int pageSize
    ) {
    	HashMap<String, Object> response = new HashMap<String, Object>();
    	WeatherHistoryResponse weatherHistory = weatherService.getHistoricalWeatherData(cityName, currentPage, pageSize);
    	
    	if(weatherHistory == null) {
    		response.put("status", StatusBuilder.getStatus(Status.NO_HISTORICAL_DATA.name()));
    	} else {
    		response.put("status", StatusBuilder.getStatus(Status.SUCCESS.name()));
    		response.put("response", weatherHistory);
    	}
    	
    	return response;
    }
}