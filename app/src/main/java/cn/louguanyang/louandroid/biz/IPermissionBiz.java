package cn.louguanyang.louandroid.biz;

import android.app.Activity;
import android.content.Context;

import cn.louguanyang.carbon.exception.NoCameraPermissionException;
import cn.louguanyang.carbon.exception.NoStoragePermissionException;

/**
 * Created by louguanyang on 16/1/13.
 */
public interface IPermissionBiz {



    /**
     * Android 6.0 检查相机权限
     * @param context
     * @throws
     */
    void checkPermissionOfCamera(Context context) throws NoCameraPermissionException;

    /**
     * Android 6.0 运行时请求相机权限
     * @param activity
     */
    void requestPermissionOfCamera(Activity activity);

    void checkPermissionOfStorage(Context context) throws NoStoragePermissionException;

    void requestPermissionOfStorage(Activity activity);

}
