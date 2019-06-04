package com.kmaryin.weaterapp.viewmodel;

import android.os.Looper;
import android.util.Log;

import androidx.databinding.Bindable;

import com.kmaryin.weaterapp.BR;
import com.kmaryin.weaterapp.model.CurrentWeatherModel;

public class CityWeatherViewModel extends BaseViewModel {
    private String degrees = "36.6";

    public CityWeatherViewModel() {
        reloadWeatherData();
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

    private void reloadWeatherData() {
        async().execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            return new CurrentWeatherModel();
        }, (model) -> {
            boolean main = Looper.getMainLooper() == Looper.myLooper();
            Log.d("ssdf", "dsfdf");
        });
    }
}
