package com.kmaryin.weatherapp.service.repository;

import com.kmaryin.weatherapp.model.CurrentWeatherModel;

public interface WeatherRepository {
    CurrentWeatherModel getCurrentWeatherForLocation(double latitude, double longitude);
    CurrentWeatherModel getLastCurrentWeather();
}
