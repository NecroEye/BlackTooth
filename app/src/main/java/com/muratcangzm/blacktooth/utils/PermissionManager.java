package com.muratcangzm.blacktooth.utils;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {

    private final int PERMISSION_REQUEST_CODE = 666;
    private final Activity activity;

    public PermissionManager(Activity _activity) {

        this.activity = _activity;

    }


    private boolean checkPermission(String permission) {

        int result = ContextCompat.checkSelfPermission(activity, permission);
        return result == PackageManager.PERMISSION_GRANTED;

    }

    public void requestPermissions(PermissionCallBack callBackString, String... permissions) {

        boolean allPermissionsGranted = true;
        for (String permission : permissions) {

            if (!checkPermission(permission)) {
                allPermissionsGranted = false;
                break;
            }
        }

        if (!allPermissionsGranted) {
            ActivityCompat.requestPermissions(activity, permissions, PERMISSION_REQUEST_CODE);
        } else {
            callBackString.onPermissionsGranted();
        }
    }

    public interface PermissionCallBack {
        void onPermissionsGranted();
    }

}
