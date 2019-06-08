package com.kmaryin.weatherapp.service.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitWeatherNetworkService {
    @GET("weather")
    Call<String> getCurrentWeather(@Query("lat") double latitude, @Query("lon") double longitude,
                                   @Query("appid") String apiKey, @Query("units") String units);
}
