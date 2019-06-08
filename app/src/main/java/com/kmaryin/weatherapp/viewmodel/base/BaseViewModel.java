package com.kmaryin.weatherapp.viewmodel.base;

import com.kmaryin.weatherapp.service.async.AsyncExecutor;
import com.kmaryin.weatherapp.service.async.AsyncTaskExecutor;

public abstract class BaseViewModel extends ObservableViewModel {
    private AsyncExecutor asyncExecutor;

    public BaseViewModel() {
        // TODO: get from dependency provider
        asyncExecutor = new AsyncTaskExecutor();
    }

    protected AsyncExecutor async() {
        return asyncExecutor;
    }
}
