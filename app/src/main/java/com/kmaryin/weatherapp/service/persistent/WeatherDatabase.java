package com.kmaryin.weatherapp.service.persistent;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kmaryin.weatherapp.service.persistent.model.CurrentWeatherEntity;

@Database(entities = { CurrentWeatherEntity.class }, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
