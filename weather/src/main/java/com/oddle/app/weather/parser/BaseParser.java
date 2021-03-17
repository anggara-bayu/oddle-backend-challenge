package com.oddle.app.weather.parser;

public interface BaseParser<T, R> {
	
	String objectToJson(T obj) throws Exception;
	
	void jsonToObject(String json, R response) throws Exception;
	
}
