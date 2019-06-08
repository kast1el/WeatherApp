package com.kmaryin.weatherapp.service.network;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitWeatherNetworkServiceImpl implements WeatherNetworkService {
    private static final String API_KEY = "a283fc234a098fe176c5241c809f453d";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    // TODO: move units to app settings
    private static final String UNITS = "metric";

    private RetrofitWeatherNetworkService weatherService;

    public RetrofitWeatherNetworkServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        weatherService = retrofit.create(RetrofitWeatherNetworkService.class);
    }

    @Override
    public RequestResult getCurrentWeather(double latitude, double longitude) throws IOException {
        Call<String> currentWeather = weatherService.getCurrentWeather(latitude, longitude, API_KEY, UNITS);
        Response<String> response = currentWeather.execute();

        RequestResult result = new RequestResult();
        if (response.isSuccessful()) {
            result.body = response.body();
        } else if (response.errorBody() != null){
            result.error = new Exception(response.errorBody().string());
        }

        return result;
    }
}
