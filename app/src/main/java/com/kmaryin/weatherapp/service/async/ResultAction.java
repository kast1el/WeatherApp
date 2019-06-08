package com.kmaryin.weatherapp.service.async;

public interface ResultAction<T> {
    void run(T result);
}
