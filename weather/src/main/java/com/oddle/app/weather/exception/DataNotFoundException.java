package com.oddle.app.weather.exception;

public class DataNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DataNotFoundException() {
		super("Data not found");
	}
	
	public DataNotFoundException(String message) {
		super(message);
	}
	
}
