package com.kmaryin.weatherapp.service.persistent;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kmaryin.weatherapp.service.persistent.model.CurrentWeatherEntity;

@Dao
public interface WeatherDao {
    @Query("SELECT * " +
            "FROM CurrentWeatherEntity " +
            "ORDER BY timestamp DESC " +
            "LIMIT 1")
    CurrentWeatherEntity getLastCurrentWeather();

    @Insert
    void insert(CurrentWeatherEntity entity);
}
