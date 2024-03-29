package com.kmaryin.weatherapp.service.async;

import androidx.annotation.NonNull;

public interface AsyncExecutor {
    <T> void execute(@NonNull ResultSupplier<T> resultSupplier);
    <T> void execute(@NonNull ResultSupplier<T> resultSupplier, ResultAction<T> resultAction);
    <T> void execute(@NonNull ResultSupplier<T> resultSupplier, ResultAction<T> resultAction, ResultAction<Throwable> errorHandler);
}
