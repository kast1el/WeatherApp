package com.kmaryin.weatherapp.service.location;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationProvidingManager implements LocationProvider, LocationListener {
    private static final int LOCATION_ACCURACY = 100;

    private LocationManager locationManager;
    private OnLocationObtainedListener listener;

    public LocationProvidingManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void obtain() {
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
        onLocationChanged(locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER));
    }

    @Override
    public void setOnLocationObtainedListener(OnLocationObtainedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null && location.getAccuracy() < LOCATION_ACCURACY) {
            if (listener != null) {
                listener.locationObtained(location.getLatitude(), location.getLongitude());
            }

            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // do nothing
    }

    @Override
    public void onProviderEnabled(String provider) {
        // do nothing
    }

    @Override
    public void onProviderDisabled(String provider) {
        // do nothing
    }
}
