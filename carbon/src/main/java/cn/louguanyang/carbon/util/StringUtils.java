package cn.louguanyang.carbon.util;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import static cn.louguanyang.carbon.spec.Constans.ZERO;

/**
 * Created by louguanyang on 15/12/4.
 */
public class StringUtils {
    private StringUtils() {
        throw new AssertionError();
    }

    @CheckResult
    public static boolean isBlank(@NonNull String str) {
        return (str == null || str.trim().length() == ZERO);
    }

    @CheckResult
    public static boolean isEmpty(@NonNull CharSequence str) {
        return (str == null || str.length() == ZERO);
    }

    /**
     * 10进制转换成16进制字符
     *
     * @param value
     * @return
     */
    @CheckResult
    public static String toHexString(@NonNull int value) {
        return Integer.toHexString(value);
    }
}
