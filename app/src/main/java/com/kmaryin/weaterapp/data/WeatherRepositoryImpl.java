package com.kmaryin.weaterapp.data;

import com.kmaryin.weaterapp.model.CurrentWeatherModel;

public class WeatherRepositoryImpl implements WeatherRepository {


    public CurrentWeatherModel getCurrentWeather() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {

        }
        return new CurrentWeatherModel();
    }
}
