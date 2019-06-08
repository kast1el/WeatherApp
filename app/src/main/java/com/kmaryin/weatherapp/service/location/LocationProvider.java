package com.kmaryin.weatherapp.service.location;

public interface LocationProvider {
    void obtain();
    void setOnLocationObtainedListener(OnLocationObtainedListener listener);
}
