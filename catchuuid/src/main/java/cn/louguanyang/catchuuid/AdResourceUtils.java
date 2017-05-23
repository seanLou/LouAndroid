package cn.louguanyang.catchuuid;

import java.lang.reflect.Method;

/**
 * Created by louguanyang on 2016/9/5.
 */

public class AdResourceUtils {

    private static String getSystemProperties(Class<?> cls, String key) {
        try {
            Method hideMethod = cls.getMethod("get", String.class);
            Object object = cls.newInstance();
            return (String) hideMethod.invoke(object, key);
        } catch (Exception e) {
        }
        return null;
    }

    public static String getTvUUID() {
        try {
            final String key = "ro.aliyun.clouduuid";
            Class<?> cls = Class.forName("android.os.SystemProperties");
            final String valueString = getSystemProperties(cls, key);
            return valueString;
        } catch (Exception e) {
            return "";
        }
    }

}
