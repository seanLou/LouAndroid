package cn.louguanyang.carbon.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

import static cn.louguanyang.carbon.spec.Constans.DEFAULT_BOOLEAN_VALUE;
import static cn.louguanyang.carbon.spec.Constans.DEFAULT_FLOAT_VALUE;
import static cn.louguanyang.carbon.spec.Constans.DEFAULT_INT_VALUE;
import static cn.louguanyang.carbon.spec.Constans.DEFAULT_LONG_VALUE;
import static cn.louguanyang.carbon.spec.Constans.DEFAULT_STRING_VALUE;

/**
 * Created by louguanyang on 15/12/3.
 */
public class PreferencesUtils {
    private final static String PREFERENCE_NAME = UUID.randomUUID().toString();

    private PreferencesUtils() {
        throw new AssertionError();
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static boolean putString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getString(Context context, String key) {
        return getString(context, key, DEFAULT_STRING_VALUE);
    }

    private static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(key, defaultValue);
    }

    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getInt(Context context, String key) {
        return getInt(context, key, DEFAULT_INT_VALUE);
    }

    private static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putLong(key, value);
        return editor.commit();
    }

    public static long getLong(Context context, String key) {
        return getLong(context, key, DEFAULT_LONG_VALUE);
    }

    private static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static float getFloat(Context context, String key) {
        return getFloat(context, key, DEFAULT_FLOAT_VALUE);
    }

    private static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, DEFAULT_BOOLEAN_VALUE);
    }

    private static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getBoolean(key, defaultValue);
    }
}
