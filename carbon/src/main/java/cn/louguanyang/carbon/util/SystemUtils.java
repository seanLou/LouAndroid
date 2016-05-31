package cn.louguanyang.carbon.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.TypedValue;

import java.util.List;

/**
 * Created by louguanyang on 15/12/19.
 */
public class SystemUtils {

    /**
     * Dp转换成Px
     * @param dp
     * @param resources
     * @return px
     */
    public static int dpToPx(float dp,@NonNull Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    public static int getTargetSDKVersion(@NonNull Context context) {
        int targetSdkVersion = Build.VERSION_CODES.BASE;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            targetSdkVersion = packageInfo.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return targetSdkVersion;
    }

    public static void sendSMS(@NonNull String phoneNumber,@NonNull String message){
        //获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(message);
        for (String msg : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, msg, null, null);
        }
    }
}
