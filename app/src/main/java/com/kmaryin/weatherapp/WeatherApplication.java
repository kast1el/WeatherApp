package com.kmaryin.weatherapp;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import androidx.room.Room;

import com.kmaryin.weatherapp.service.dependency.DependencyContainer;
import com.kmaryin.weatherapp.service.location.LocationProvider;
import com.kmaryin.weatherapp.service.location.LocationProvidingManager;
import com.kmaryin.weatherapp.service.network.RetrofitWeatherNetworkServiceImpl;
import com.kmaryin.weatherapp.service.network.WeatherNetworkService;
import com.kmaryin.weatherapp.service.persistent.WeatherDao;
import com.kmaryin.weatherapp.service.persistent.WeatherDatabase;
import com.kmaryin.weatherapp.service.repository.WeatherRepository;
import com.kmaryin.weatherapp.service.repository.WeatherRepositoryImpl;

public class WeatherApplication extends Application implements DependencyContainer {
    private WeatherRepository weatherRepository;
    private LocationProvider locationProvider;

    @Override
    public void onCreate() {
        super.onCreate();

        initServices();
    }

    private void initServices() {
        WeatherNetworkService networkService = new RetrofitWeatherNetworkServiceImpl();
        WeatherDao databaseAccessObject = createWeatherDao();

        this.weatherRepository = new WeatherRepositoryImpl(networkService, databaseAccessObject);

        locationProvider = new LocationProvidingManager((LocationManager) getSystemService(Context.LOCATION_SERVICE));
    }

    private WeatherDao createWeatherDao() {
        WeatherDatabase db = Room.databaseBuilder(getApplicationContext(), WeatherDatabase.class,
                "weather_database").build();

        return db.weatherDao();
    }

    @Override
    public WeatherRepository getWeatherRepository() {
        return weatherRepository;
    }

    @Override
    public LocationProvider getLocationProvider() {
        return locationProvider;
    }
}
