package com.kmaryin.weatherapp.service.async;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

public class AsyncTaskExecutor implements AsyncExecutor {

    public <T> void execute(@NonNull ResultSupplier<T> resultSupplier) {
        execute(resultSupplier, null);
    }

    public <T> void execute(@NonNull ResultSupplier<T> resultSupplier, ResultAction<T> resultAction) {
        execute(resultSupplier, resultAction, null);
    }

    public <T> void execute(@NonNull ResultSupplier<T> resultSupplier, ResultAction<T> resultAction, ResultAction<Throwable> errorHandler) {
        new ExecutionAsyncTask<>(resultSupplier, resultAction, errorHandler).execute();
    }

    private class ExecutionAsyncTask<T> extends AsyncTask<Void, Void, ResultHolder<T>> {
        private ResultSupplier<T> backgroundAction;
        private ResultAction<T> resultAction;
        private ResultAction<Throwable> errorHandler;

        public ExecutionAsyncTask(@NonNull ResultSupplier<T> backgroundAction,
                                  ResultAction<T> resultAction,
                                  ResultAction<Throwable> errorHandler) {
            this.backgroundAction = backgroundAction;
            this.resultAction = resultAction;
            this.errorHandler = errorHandler;
        }

        @Override
        protected ResultHolder<T> doInBackground(Void[] objects) {
            ResultHolder<T> resultHolder = new ResultHolder<>();
            try {
                resultHolder.result =  backgroundAction.get();
            } catch (Exception e) {
                resultHolder.error = e;
            }

            return resultHolder;
        }

        @Override
        protected void onPostExecute(ResultHolder<T> resultHolder) {
            super.onPostExecute(resultHolder);

            if (resultHolder.result != null && resultAction != null) {
                resultAction.run(resultHolder.result);
            } else if (resultHolder.error != null && errorHandler != null) {
                errorHandler.run(resultHolder.error);
            }
        }
    }

    private class ResultHolder<T> {
        T result;
        Throwable error;
    }
}
