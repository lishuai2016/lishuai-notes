package com.lishuai.designPattern.observer;

public class Test {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		CurrentConditionDisplay c = new CurrentConditionDisplay(weatherData);
		weatherData.setMeasurements(80, 60, 30);
	}
}
