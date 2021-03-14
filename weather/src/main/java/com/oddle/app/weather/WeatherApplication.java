package com.oddle.app.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.oddle.app.weather.config")
public class WeatherApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}
}
