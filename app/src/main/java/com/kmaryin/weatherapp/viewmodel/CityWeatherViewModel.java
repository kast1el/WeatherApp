package com.kmaryin.weatherapp.viewmodel;

import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.kmaryin.weatherapp.BR;
import com.kmaryin.weatherapp.model.CurrentWeatherModel;
import com.kmaryin.weatherapp.service.location.LocationProvider;
import com.kmaryin.weatherapp.service.location.OnLocationObtainedListener;
import com.kmaryin.weatherapp.service.permission.Permission;
import com.kmaryin.weatherapp.service.repository.WeatherRepository;
import com.kmaryin.weatherapp.viewmodel.base.BaseViewModel;

public class CityWeatherViewModel extends BaseViewModel implements OnLocationObtainedListener {
    private WeatherRepository weatherRepository;
    private LocationProvider locationProvider;

    private String degrees;
    private String city;
    private String description;
    private String pressure;
    private String humidity;
    private String windSpeed;

    private MutableLiveData<Permission> permissions;

    public CityWeatherViewModel(LocationProvider locationProvider,
                                WeatherRepository weatherRepository) {
        this.locationProvider = locationProvider;
        this.weatherRepository = weatherRepository;
        permissions = new MutableLiveData<>();

        requestLocationPermission();
        extractCachedWeather();
    }

    private void extractCachedWeather() {
        async().execute(
                weatherRepository::getLastCurrentWeather,
                this::displayCurrentWeather);
    }

    public void locationPermissionGranted() {
        startLocationObtaining();
    }

    private void startLocationObtaining() {
        locationProvider.setOnLocationObtainedListener(this);
        locationProvider.obtain();
    }

    @Override
    public void locationObtained(double latitude, double longitude) {
        reloadWeatherData(latitude, longitude);
    }

    private void requestLocationPermission() {
        permissions.postValue(Permission.Location);
    }

    public MutableLiveData<Permission> permissionRequester() {
        return permissions;
    }

    private void reloadWeatherData(double latitude, double longitude) {
        async().execute(
                () -> weatherRepository.getCurrentWeatherForLocation(latitude, longitude),
                this::displayCurrentWeather);
    }

    private void displayCurrentWeather(CurrentWeatherModel model) {
        if (model == null) {
            return;
        }

        setDegrees(Float.toString(model.getTemperature()));
        setCity(model.getCityName());
        setDescription(model.getStatusDescription());
        setPressure(Integer.toString(model.getPressure()));
        setHumidity(Integer.toString(model.getHumidity()));
        setWindSpeed(Float.toString(model.getWindSpeed()));
    }

    @Bindable
    public String getDegrees() {
        if (degrees == null) {
            return "--";
        }
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

    @Bindable
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        notifyPropertyChanged(BR.city);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
        notifyPropertyChanged(BR.pressure);
    }

    @Bindable
    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
        notifyPropertyChanged(BR.humidity);
    }

    @Bindable
    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
        notifyPropertyChanged(BR.windSpeed);
    }
}
