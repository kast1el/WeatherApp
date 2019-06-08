package com.kmaryin.weatherapp.service.network.model;

import com.google.gson.annotations.SerializedName;

public class Rain {
    @SerializedName("1h")
    private float oneHour;
    @SerializedName("3h")
    private float threeHours;

    public float getOneHour() {
        return oneHour;
    }

    public void setOneHour(float oneHour) {
        this.oneHour = oneHour;
    }

    public float getThreeHours() {
        return threeHours;
    }

    public void setThreeHours(float threeHours) {
        this.threeHours = threeHours;
    }
}
