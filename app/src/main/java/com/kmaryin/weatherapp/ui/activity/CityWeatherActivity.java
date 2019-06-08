package com.kmaryin.weatherapp.ui.activity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.kmaryin.weatherapp.R;
import com.kmaryin.weatherapp.databinding.ActivityCityWeatherBinding;
import com.kmaryin.weatherapp.service.permission.Permission;
import com.kmaryin.weatherapp.viewmodel.CityWeatherViewModel;

public class CityWeatherActivity extends BaseActivity {
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 0;

    private CityWeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_city_weather);

        viewModel = ViewModelProviders
                .of(this, getViewModelFactory())
                .get(CityWeatherViewModel.class);

        viewModel.permissionRequester().observe(this, this::onRequestPermission);

        binding.setCityWeather(viewModel);
    }

    private void onRequestPermission(Permission permission) {
        if (isPermissionGranted(permission)) {
            notifyViewModelAboutLocationPermissionGranted();
        } else {
            requestPermission(permission, REQUEST_CODE_LOCATION_PERMISSION);
            // TODO: check is user disabled request dialog
        }
    }

    @Override
    protected void onRequestPermissionsResult(Permission permission, boolean granted, int code) {
        if (code == REQUEST_CODE_LOCATION_PERMISSION) {
            if (granted) {
                notifyViewModelAboutLocationPermissionGranted();
            } else {
                onRequestPermission(permission);
            }
        }
    }

    private void notifyViewModelAboutLocationPermissionGranted() {
        viewModel.locationPermissionGranted();
    }
}
