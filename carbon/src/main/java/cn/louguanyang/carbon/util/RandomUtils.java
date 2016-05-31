package cn.louguanyang.carbon.util;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import cn.louguanyang.carbon.spec.Constans;

import static cn.louguanyang.carbon.spec.Constans.DATE_PATTERN_HOUR_SECOND;
import static cn.louguanyang.carbon.spec.Constans.DATE_PATTERN_YYMMDD;
import static cn.louguanyang.carbon.spec.Constans.LETTERS;
import static cn.louguanyang.carbon.spec.Constans.LOWER_CASE_LETTERS;
import static cn.louguanyang.carbon.spec.Constans.MAX_VALUE;
import static cn.louguanyang.carbon.spec.Constans.NUMBERS;
import static cn.louguanyang.carbon.spec.Constans.NUMBERS_AND_LETTERS;
import static cn.louguanyang.carbon.spec.Constans.UPPER_CASE_LETTERS;
import static cn.louguanyang.carbon.spec.Constans.ZERO;
import static cn.louguanyang.carbon.util.StringUtils.toHexString;

/**
 * Created by louguanyang on 15/12/7.
 */
public class RandomUtils {

    private RandomUtils() {
        throw new AssertionError();
    }

    /**
     * 随机一个长度为length的字符串(大写字母，小写字母和数字) {@link Constans#NUMBERS_AND_LETTERS}
     *
     * @param length 长度
     * @return 长度为length的随机(大写字母，小写字母和数字)串
     * @see RandomUtils#random(String, int)
     */
    public static String random_from_numbers_and_letters(int length) {
        return random(NUMBERS_AND_LETTERS, length);
    }

    /**
     * 随机一个长度为length的数字串，只包含：数字 {@link Constans#NUMBERS}
     *
     * @param length 长度
     * @return 长度为length的随机数字串
     * @see RandomUtils#random(String, int)
     */
    public static String randomNumbers(int length) {
        return random(NUMBERS, length);
    }

    /**
     * 随机一个长度为length的字母串(大写字母，小写字母) {@link Constans#LETTERS}
     *
     * @param length 随机字符串长度
     * @return 长度为length的随机字母串
     * @see RandomUtils#random(String, int)
     */
    public static String randomLetters(int length) {
        return random(LETTERS, length);
    }

    /**
     * 随机一个长度为length的大写字母串 {@link Constans#UPPER_CASE_LETTERS}
     *
     * @param length 随机字符串长度
     * @return 长度为length的大写字母串
     * @see RandomUtils#random(String, int)
     */
    public static String randomUpperCaseLetters(int length) {
        return random(UPPER_CASE_LETTERS, length);
    }

    /**
     * 随机一个长度为length的小写字母串，包含：小写字母 {@link Constans#LOWER_CASE_LETTERS}
     *
     * @param length 随机字符串长度
     * @return 长度为length的小写字母串
     * @see RandomUtils#random(String, int)
     */
    public static String randomLowerCaseLetters(int length) {
        return random(LOWER_CASE_LETTERS, length);
    }

    /**
     * 根据模版source,随机一个长度为length的字符串
     *
     * @param source 随机字符串模版
     * @param length 随机字符串长度
     * @return 长度为length的字符串
     * @see RandomUtils#random(char[], int)
     */
    private static String random(@NonNull String source, int length) {
        return random(source.toCharArray(), length);
    }

    /**
     * 根据模版字符集 sourceChar,随机一个长度为length的字符串
     *
     * @param sourceChar 随机字符串模版
     * @param length     随机字符串长度
     * @return 长度为length的字符串
     */
    private static String random(@NonNull char[] sourceChar, int length) {
        StringBuilder str = new StringBuilder(length);
        for (int i = ZERO; i < length; i++) {
            str.append(sourceChar[new Random().nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

    /**
     * random int between 0 and max
     *
     * @param max 随机数的上限
     * @return <ul>
     * <li>if max <= 0, return 0</li>
     * <li>else return random int between 0 and max</li>
     * </ul>
     */
    public static int random(int max) {
        return random(ZERO, max);
    }

    /**
     * random int between min and max
     *
     * @param min
     * @param max
     * @return <ul>
     * <li>if min > max, return 0</li>
     * <li>if min == max, return min</li>
     * <li>else return random int between min and max</li>
     * </ul>
     */
    public static int random(int min, int max) {
        if (min > max) {
            return ZERO;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }

    /**
     * 生成16进制的唯一编号。
     *
     * 共三部分 1、yyMMdd 2、HHmmssSSS 3、XXXX 四位随机数
     *
     * @return
     */
    private synchronized static String generateNo(int length) {
        try {
            Thread.sleep(BigDecimal.ONE.longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String datePart = toHexString(Integer.valueOf(DateUtils.format(new Date(), DATE_PATTERN_YYMMDD)));
        String timePart = toHexString(Integer.valueOf(DateUtils.format(new Date(), DATE_PATTERN_HOUR_SECOND)));
        String randomPart = toHexString(RandomUtils.random(MAX_VALUE));
        return (Constans.DEFAULT_STRING_VALUE + datePart + timePart + randomPart).toUpperCase();
    }


}
