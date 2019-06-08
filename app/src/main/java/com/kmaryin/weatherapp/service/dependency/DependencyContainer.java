package com.kmaryin.weatherapp.service.dependency;

import com.kmaryin.weatherapp.service.location.LocationProvider;
import com.kmaryin.weatherapp.service.repository.WeatherRepository;

public interface DependencyContainer {
    WeatherRepository getWeatherRepository();
    LocationProvider getLocationProvider();
}
