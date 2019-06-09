package com.kmaryin.weatherapp.service.location;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class LocationProvidingManager implements LocationProvider, LocationListener {
    private static final int LOCATION_ACCURACY = 100;

    private LocationManager locationManager;
    private WeakReference<OnLocationObtainedListener> listenerReference;

    public LocationProvidingManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void obtain() {
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
    }

    @Override
    public void setOnLocationObtainedListener(OnLocationObtainedListener listener) {
        this.listenerReference = new WeakReference<>(listener);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null && location.getAccuracy() < LOCATION_ACCURACY) {
            OnLocationObtainedListener listener = listenerReference != null ? listenerReference.get() : null;
            if (listener != null) {
                listener.locationObtained(location.getLatitude(), location.getLongitude());
            }

            listenerReference = null;
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
