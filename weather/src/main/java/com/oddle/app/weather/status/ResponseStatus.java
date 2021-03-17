package com.oddle.app.weather.status;

public class ResponseStatus {
	private String responseCode;
	private String message;
	private String description;

	public ResponseStatus() {
		responseCode = "";
		message = "";
		description = "";
	}

	public ResponseStatus(String rc, String msg, String desc) {
		this.setResponseCode(rc);
		this.setMessage(msg);
		this.setDescription(desc);
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "[Response Code : " + responseCode + ", Message : " + message + ", Description : " + description + "]";
	}
}
