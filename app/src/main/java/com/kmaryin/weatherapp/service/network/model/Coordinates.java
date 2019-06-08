package com.kmaryin.weatherapp.service.network.model;

import com.google.gson.annotations.SerializedName;

public class Coordinates {
    @SerializedName("lat")
    private float latitude;
    @SerializedName("lon")
    private float longitude;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
