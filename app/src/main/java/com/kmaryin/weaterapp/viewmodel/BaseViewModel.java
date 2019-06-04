package com.kmaryin.weaterapp.viewmodel;

import com.kmaryin.weaterapp.service.AsyncExecutor;
import com.kmaryin.weaterapp.service.AsyncTaskExecutor;

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
