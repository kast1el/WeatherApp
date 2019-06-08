package com.kmaryin.weatherapp.service.adapter;

import com.kmaryin.weatherapp.model.CurrentWeatherModel;
import com.kmaryin.weatherapp.service.network.model.CurrentWeatherRemoteModel;
import com.kmaryin.weatherapp.service.network.model.Weather;
import com.kmaryin.weatherapp.service.persistent.model.CurrentWeatherEntity;

import java.util.Date;

public class ModelDataAdapter {
    public CurrentWeatherModel currentWeatherFromRemote(CurrentWeatherRemoteModel model) {
        if (model == null) return null;

        CurrentWeatherModel resultModel = new CurrentWeatherModel();
        resultModel.setCityName(model.getName());

        resultModel.setTemperature(model.getMain().getTemperature());
        resultModel.setPressure(model.getMain().getPressure());
        resultModel.setHumidity(model.getMain().getHumidity());
        resultModel.setWindSpeed(model.getWind().getSpeed());
        resultModel.setSunrise(new Date(model.getSystem().getSunrise()));
        resultModel.setSunset(new Date(model.getSystem().getSunset()));

        if (model.getWeather() != null && model.getWeather().size() > 0) {
            Weather weather = model.getWeather().get(0);
            resultModel.setStatusDescription(weather.getDescription());
            resultModel.setStatusIcon(weather.getIcon());
        }

        return resultModel;
    }

    public CurrentWeatherEntity currentWeatherToEntity(CurrentWeatherModel model) {
        if (model == null) return null;

        CurrentWeatherEntity entity = new CurrentWeatherEntity();
        entity.setIdentifier(model.getCityName().toLowerCase());
        entity.setCityName(model.getCityName());
        entity.setStatusDescription(model.getStatusDescription());
        entity.setStatusIcon(model.getStatusIcon());
        entity.setTemperature(model.getTemperature());
        entity.setPressure(model.getPressure());
        entity.setHumidity(model.getHumidity());
        entity.setWindSpeed(model.getWindSpeed());
        entity.setSunrise(model.getSunrise().getTime());
        entity.setSunset(model.getSunset().getTime());

        return entity;
    }

    public CurrentWeatherModel currentWeatherFromEntity(CurrentWeatherEntity entity) {
        if (entity == null) return null;

        CurrentWeatherModel currentWeatherModel = new CurrentWeatherModel();
        currentWeatherModel.setCityName(entity.getCityName());
        currentWeatherModel.setStatusDescription(entity.getStatusDescription());
        currentWeatherModel.setStatusIcon(entity.getStatusIcon());
        currentWeatherModel.setTemperature(entity.getTemperature());
        currentWeatherModel.setPressure(entity.getPressure());
        currentWeatherModel.setHumidity(entity.getHumidity());
        currentWeatherModel.setWindSpeed(entity.getWindSpeed());
        currentWeatherModel.setSunrise(new Date(entity.getSunrise()));
        currentWeatherModel.setSunset(new Date(entity.getSunset()));

        return currentWeatherModel;
    }
}
