package com.kmaryin.weaterapp.viewmodel;

import androidx.databinding.Bindable;

import com.kmaryin.weaterapp.BR;

public class CityWeatherViewModel extends ObservableViewModel {
    private String degrees = "36.6";

    public CityWeatherViewModel() {
    }

    @Bindable
    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
        notifyPropertyChanged(BR.degrees);
    }

    @Bindable
    public String getDegreesSymbol() {
        // TODO: add setting for degrees system
        // Celsius
        return "\u2103";

        // Kelvin
        //return "\u2109";
    }
}
