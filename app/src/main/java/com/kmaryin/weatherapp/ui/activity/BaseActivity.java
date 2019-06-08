package com.kmaryin.weatherapp.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.kmaryin.weatherapp.service.dependency.DependencyContainer;
import com.kmaryin.weatherapp.service.permission.Permission;
import com.kmaryin.weatherapp.viewmodel.base.ViewModelsFactory;

public abstract class BaseActivity extends AppCompatActivity {

    protected ViewModelProvider.Factory getViewModelFactory() {
        DependencyContainer dependencyContainer = (DependencyContainer) getApplication();

        return new ViewModelsFactory(dependencyContainer);
    }

    protected boolean isPermissionGranted(Permission permission) {
        String[] stringPermissions = getPermissionsList(permission);
        boolean granted = true;
        for (int i = 0; i < stringPermissions.length; ++i) {
            int currentPermissionGrantResult = ContextCompat.checkSelfPermission(this, stringPermissions[i]);
            granted = granted && currentPermissionGrantResult == PackageManager.PERMISSION_GRANTED;
        }
        return granted;
    }

    protected void requestPermission(Permission permission, int requestCode) {
        ActivityCompat.requestPermissions(this,
                getPermissionsList(permission),
                requestCode);
    }

    protected void onRequestPermissionsResult(Permission permission, boolean result, int code) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int i = 0; i < permissions.length; ++i) {
            Permission permission = convertStringToPermission(permissions[i]);
            boolean result = grantResults.length > i
                    && grantResults[i] == PackageManager.PERMISSION_GRANTED;
            onRequestPermissionsResult(permission, result, requestCode);
        }
    }

    private String[] getPermissionsList(Permission permission) {
        if (permission == Permission.Location) {
            return new String[] { Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION };
        }

        return new String[0];
    }

    private Permission convertStringToPermission(String stringPermission) {
        if (Manifest.permission.ACCESS_FINE_LOCATION.equals(stringPermission)) {
            return Permission.Location;
        }

        return null;
    }
}
