package com.oddle.app.weather.pojo.request.weather;

public class Wind {
	
	private float speed;
	private float degree;
	private float gust;
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getDegree() {
		return degree;
	}
	
	public void setDegree(float degree) {
		this.degree = degree;
	}
	
	public float getGust() {
		return gust;
	}
	
	public void setGust(float gust) {
		this.gust = gust;
	}
}
