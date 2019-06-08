package com.kmaryin.weatherapp.service.network.model;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    private float temperature;
    private int pressure;
    private int humidity;
    @SerializedName("temp_min")
    private float temperatureMin;
    @SerializedName("temp_max")
    private float temperatureMax;
    @SerializedName("sea_level")
    private int seaPressure;
    @SerializedName("grnd_level")
    private int groundPressure;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public int getSeaPressure() {
        return seaPressure;
    }

    public void setSeaPressure(int seaPressure) {
        this.seaPressure = seaPressure;
    }

    public int getGroundPressure() {
        return groundPressure;
    }

    public void setGroundPressure(int groundPressure) {
        this.groundPressure = groundPressure;
    }
}
