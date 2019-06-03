package com.kmaryin.weaterapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.kmaryin.weaterapp.R;
import com.kmaryin.weaterapp.databinding.ActivityCityWeatherBinding;
import com.kmaryin.weaterapp.viewmodel.CityWeatherViewModel;

public class CityWeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_city_weather);

        CityWeatherViewModel viewModel = ViewModelProviders
                .of(this)
                .get(CityWeatherViewModel.class);

        binding.setCityWeather(viewModel);
    }
}
