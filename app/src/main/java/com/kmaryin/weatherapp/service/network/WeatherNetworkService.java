package com.kmaryin.weatherapp.service.network;

import java.io.IOException;

public interface WeatherNetworkService {
    RequestResult getCurrentWeather(double latitude, double longitude) throws IOException;
}
