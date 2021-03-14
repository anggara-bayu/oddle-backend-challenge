package com.oddle.app.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oddle.app.weather.config.ConfigProperties;

import java.util.Collections;
import java.util.Map;

@RestController
public class WeatherController {
	
	private ConfigProperties config;
	private RestTemplate restTemplate;
	private StringBuilder sb;

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
    public String getWeather(
    		@RequestParam(value = "city", defaultValue = "London") String cityName
	) {
    	sb.setLength(0);
    	sb.append("https://api.openweathermap.org/data/2.5/weather?q=");
    	sb.append(cityName);
    	sb.append("&appid=");
    	sb.append(config.getApikey());
    	
    	try {
    		return restTemplate.getForObject(sb.toString(), String.class);
    	} catch(Exception e) {
    		return Collections.singletonMap("message", "Sorry we cannot find the city that you looking for").toString();
    	}
    }
}