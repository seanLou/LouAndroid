package cn.louguanyang.louandroid.biz.impl;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import cn.louguanyang.carbon.exception.NoCameraPermissionException;
import cn.louguanyang.carbon.exception.NoPermissionException;
import cn.louguanyang.carbon.exception.NoStoragePermissionException;
import cn.louguanyang.carbon.spec.PermissionRequestCode;
import cn.louguanyang.louandroid.biz.IPermissionBiz;

/**
 *
 * Created by louguanyang on 16/1/14.
 */
public class PermissionBizImpl implements IPermissionBiz {

    /**
     *
     * @param context
     * @param permission
     * @throws NoPermissionException
     */
    private void checkPermission(Context context, String permission) throws NoPermissionException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                throw new NoPermissionException();
            }
        }
    }

    /**
     *
     * @param activity
     * @param permissions
     * @param permission_request_code
     */
    private void requestPermission(Activity activity, String[] permissions, int permission_request_code) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(permissions, permission_request_code);
        }
    }

    @Override
    public void checkPermissionOfCamera(Context context) throws NoCameraPermissionException {
        String camera_permission = Manifest.permission.CAMERA;
        try {
            checkPermission(context, camera_permission);
        }catch (NoPermissionException e) {
            throw new NoCameraPermissionException();
        }
    }



    @Override
    public void requestPermissionOfCamera(Activity activity) {
        String[] camera_permission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        int camera_permission_request_code = PermissionRequestCode.CAMERA_PERMISSION;
        requestPermission(activity, camera_permission, camera_permission_request_code);
    }

    @Override
    public void checkPermissionOfStorage(Context context) throws NoStoragePermissionException {
        String storage_permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        try {
            checkPermission(context, storage_permission);
        } catch (NoPermissionException e) {
            throw new NoStoragePermissionException();
        }
    }

    @Override
    public void requestPermissionOfStorage(Activity activity) {
        String[] storage_permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
        int storage_permission_request_code = PermissionRequestCode.STORAGE_PERMISSION;
        requestPermission(activity, storage_permission, storage_permission_request_code);
    }

}
