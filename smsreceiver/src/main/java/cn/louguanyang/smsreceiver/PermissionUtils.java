package cn.louguanyang.smsreceiver;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by louguanyang on 16/3/23.
 */
public class PermissionUtils {
    /**
     *
     * @param context
     * @param permission
     * @throws NoPermissionException
     */
    public static void checkPermission(Context context, String permission) throws NoPermissionException {
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
    public static void requestPermission(Activity activity, String[] permissions, int permission_request_code) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(permissions, permission_request_code);
        }
    }


    public static void checkReceiveSMSPermission(Activity activity) throws NoPermissionException {
        checkPermission(activity, Manifest.permission.RECEIVE_SMS);
    }

    public static void requestReceiveSMSPermission(Activity activity, int permission_request_code) {
        requestPermission(activity, new String[]{Manifest.permission.RECEIVE_SMS}, permission_request_code);
    }

    public static void checkSendSMSPermission(Activity activity) throws NoPermissionException {
        checkPermission(activity, Manifest.permission.SEND_SMS);
    }

    public static void requestSendSMSPermission(Activity activity, int permission_request_code) {
        requestPermission(activity, new String[]{Manifest.permission.SEND_SMS}, permission_request_code);
    }


}
