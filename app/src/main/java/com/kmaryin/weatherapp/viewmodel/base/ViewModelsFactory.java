package com.kmaryin.weatherapp.viewmodel.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kmaryin.weatherapp.service.dependency.DependencyContainer;
import com.kmaryin.weatherapp.viewmodel.CityWeatherViewModel;

public class ViewModelsFactory implements ViewModelProvider.Factory {
    private DependencyContainer dependencyContainer;

    public ViewModelsFactory(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(CityWeatherViewModel.class)) {
            return (T) new CityWeatherViewModel(
                    dependencyContainer.getLocationProvider(),
                    dependencyContainer.getWeatherRepository());
        }

        return null;
    }
}
