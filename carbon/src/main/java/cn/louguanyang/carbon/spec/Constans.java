package cn.louguanyang.carbon.spec;

import java.math.BigDecimal;

/**
 * 常量表
 * Created by louguanyang on 16/5/28.
 */

public class Constans {
    /**
     * 默认String类型值
     */
    public final static String DEFAULT_STRING_VALUE = "";
    /**
     * 默认int类型值
     */
    public final static int DEFAULT_INT_VALUE = -1;
    /**
     * 默认Long类型值
     */
    public final static Long DEFAULT_LONG_VALUE = -1L;
    /**
     * 默认float类型值
     */
    public final static float DEFAULT_FLOAT_VALUE = -1F;
    /**
     * 默认boolean类型值
     */
    public final static boolean DEFAULT_BOOLEAN_VALUE = false;
    /**
     * 随机数字串
     */
    public static final String NUMBERS = "0123456789";
    /**
     * 随机小写字母串
     */
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    /**
     * 随机大写字母串
     */
    public static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 随机字母串
     */
    public static final String LETTERS = LOWER_CASE_LETTERS + UPPER_CASE_LETTERS;
    /**
     * 随机数字,字母串
     */
    public static final String NUMBERS_AND_LETTERS = NUMBERS + LETTERS;

    public final static int DEFAULT_LENGTH = 16;
    public final static int DEFAULT_PART_1_LENGTH = 5;
    public final static int DEFAULT_PART_2_LENGTH = 7;
    public final static int MAX_VALUE = 65536;
    /**
     * 数字0
     */
    public static final int ZERO = BigDecimal.ZERO.intValue();
    /**
     * 字符 "0"
     */
    public static final String PAD_CHAR_ZERO = String.valueOf(ZERO);
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public final static String DATE_PATTERN_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd
     */
    public final static String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";
    /**
     * yyMMdd
     */
    public final static String DATE_PATTERN_YYMMDD = "yyMMdd";
    /**
     * HHmmssSSS
     */
    public static final String DATE_PATTERN_HOUR_SECOND = "HHmmssSSS";
    /**
     * UTF-8
     */
    public static final String UTF_8 = "UTF-8";
}
