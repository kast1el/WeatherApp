package com.kmaryin.weatherapp.service.adapter;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class BindingDataAdapters {
    @BindingAdapter({"app:refreshing"})
    public static void refreshing(SwipeRefreshLayout refreshLayout, boolean isRefreshing) {
        refreshLayout.setRefreshing(isRefreshing);
    }

    @BindingAdapter({"app:onRefreshListener"})
    public static void setOnRefreshListener(SwipeRefreshLayout refreshLayout,
                                            SwipeRefreshLayout.OnRefreshListener listener) {
        refreshLayout.setOnRefreshListener(listener);
    }
}
