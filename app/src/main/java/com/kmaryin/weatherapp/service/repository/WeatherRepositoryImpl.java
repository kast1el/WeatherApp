package com.kmaryin.weatherapp.service.repository;

import com.google.gson.Gson;
import com.kmaryin.weatherapp.model.CurrentWeatherModel;
import com.kmaryin.weatherapp.service.adapter.ModelDataAdapter;
import com.kmaryin.weatherapp.service.network.RequestResult;
import com.kmaryin.weatherapp.service.network.WeatherNetworkService;
import com.kmaryin.weatherapp.service.network.model.CurrentWeatherRemoteModel;
import com.kmaryin.weatherapp.service.persistent.WeatherDao;
import com.kmaryin.weatherapp.service.persistent.model.CurrentWeatherEntity;

import java.io.IOException;

public class WeatherRepositoryImpl implements WeatherRepository {
    private ModelDataAdapter adapter;
    private WeatherNetworkService networkService;
    private WeatherDao databaseManager;

    public WeatherRepositoryImpl(WeatherNetworkService networkService, WeatherDao databaseManager) {
        this.networkService = networkService;
        this.databaseManager = databaseManager;

        adapter = new ModelDataAdapter();
    }

    @Override
    public CurrentWeatherModel getCurrentWeatherForLocation(double latitude, double longitude) {
        CurrentWeatherModel currentWeatherModel = getWeatherForLocationFromRemoteService(latitude, longitude);

        saveCurrentWeatherInPersistentStorage(currentWeatherModel);

        return currentWeatherModel;
    }

    @Override
    public CurrentWeatherModel getLastCurrentWeather() {
        return getLastCurrentWeatherFromDatabase();
    }

    private CurrentWeatherModel getWeatherForLocationFromRemoteService(double latitude, double longitude) {
        try {
            RequestResult response = networkService.getCurrentWeather(latitude, longitude);
            if (response.body != null) {
                return convertRemoteResponseToCurrentWeatherModel(response.body);
            }
            // TODO: show error
        } catch (IOException e) {
            // TODO: log error and show exception
        }

        return null;
    }

    private CurrentWeatherModel convertRemoteResponseToCurrentWeatherModel(String response) {
        // TODO: provide json serializer instead of using GSON
        Gson gson = new Gson();
        CurrentWeatherRemoteModel model = gson.fromJson(response, CurrentWeatherRemoteModel.class);
        return adapter.currentWeatherFromRemote(model);
    }

    private void saveCurrentWeatherInPersistentStorage(CurrentWeatherModel currentWeatherModel) {
        databaseManager.insert(adapter.currentWeatherToEntity(currentWeatherModel));
    }

    private CurrentWeatherModel getLastCurrentWeatherFromDatabase() {
        CurrentWeatherEntity entity = databaseManager.getLastCurrentWeather();

        return adapter.currentWeatherFromEntity(entity);
    }
}
