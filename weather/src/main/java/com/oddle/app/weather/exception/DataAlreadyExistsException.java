package com.oddle.app.weather.exception;

public class DataAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DataAlreadyExistsException() {
		super("The data is exists. Try input another data");
	}
	
	public DataAlreadyExistsException(String message) {
		super(message);
	}
	
}
