package cn.louguanyang.carbon.util;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.louguanyang.carbon.exception.DateParseException;
import cn.louguanyang.carbon.exception.DateStringNullException;

import static cn.louguanyang.carbon.spec.Constans.DATE_PATTERN_LONG;
import static cn.louguanyang.carbon.spec.Constans.DATE_PATTERN_DEFAULT;

/**
 * Created by louguanyang on 15/12/28.
 */
public class DateUtils {

    /**
     * 当前的时间戳
     */
    @CheckResult
    public static Timestamp getNowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    @CheckResult
    public static String getNowTime() {
        return format(getNowTimestamp(), DATE_PATTERN_LONG);
    }

    @CheckResult
    public static String today() {
        return format(getNowTimestamp(), DATE_PATTERN_DEFAULT);
    }

    /**
     * 日期转换成时间戳
     *
     * @param date
     * @return
     */
    @CheckResult
    public static Timestamp toTimestamp(@NonNull Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 字串转换日期
     * 默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param dateString
     * @return
     */
    @CheckResult
    public static Date parseDate(String dateString) throws DateStringNullException, DateParseException {
        return parseDate(dateString, null);
    }

    /**
     * 字串转换日期
     * 默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param dateString
     * @param pattern
     * @return
     */
    @CheckResult
    public static Date parseDate(@NonNull String dateString, @Nullable String pattern) throws DateStringNullException, DateParseException {
        if (StringUtils.isEmpty(dateString)) {
            throw new DateStringNullException();
        }
        if (StringUtils.isEmpty(pattern)) {
            pattern = DATE_PATTERN_DEFAULT;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new DateParseException();
    }

    /**
     * 字串转为时间戳
     *
     * @param dateString
     * @return
     */
    @CheckResult
    public static Timestamp toTimestamp(String dateString) throws DateStringNullException, DateParseException {
        return toTimestamp(dateString, null);
    }

    /**
     * 字串转为时间戳
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    @CheckResult
    public static Timestamp toTimestamp(String dateStr, String pattern) throws DateStringNullException, DateParseException {
        Date date = parseDate(dateStr, pattern);
        return toTimestamp(date);
    }

    /**
     * 日期转字串
     * 默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date 时间
     * @return
     */
    @CheckResult
    public static String format(Date date) {
        return format(date, null);
    }

    /**
     * 日期转字串
     *
     * @param date    时间
     * @param pattern 格式
     * @return
     */
    @CheckResult
    public static String format(@NonNull Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DATE_PATTERN_DEFAULT;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(calendar.getTime());
    }

    /**
     * 在指定的日期上增加N秒并返回
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Timestamp addSeconds(@NonNull Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return toTimestamp(calendar.getTime());
    }

    /**
     * 在指定的日期上增加N分钟并返回
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Timestamp addMinutes(@NonNull Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return toTimestamp(calendar.getTime());
    }

    /**
     * 在指定的日期上增加N小时并返回
     *
     * @param date
     * @param hours
     * @return
     */
    public static Timestamp addHours(@NonNull Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return toTimestamp(calendar.getTime());
    }

    /**
     * 在指定的日期上增加N天并返回
     *
     * @param date
     * @param days
     * @return
     */
    public static Timestamp addDays(@NonNull Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return toTimestamp(calendar.getTime());
    }

    private final static int ZERO = 0;
    private final static int ONE = 1;
    private final static int TWO = 2;

    /**
     * 在指定的日期上增加N天(不包含周末)并返回
     * @param date
     * @param days
     * @return
     */
    public static Date addDaysNotIncludingWeekend(@NonNull Date date, int days) {
        if(days <= ZERO) {
            return date;
        }
        Date resultDate = date;
        for (int index = ZERO; index < days; index++) {
            Date nextDay = addDays(resultDate, ONE);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nextDay);
            int dayOfWeekend = calendar.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeekend == Calendar.SATURDAY) {
                resultDate = addDays(nextDay, TWO);
            }else if(dayOfWeekend == Calendar.SUNDAY) {
                resultDate = addDays(nextDay, ONE);
            }else {
                resultDate = nextDay;
            }
        }
        return resultDate;
    }

    /**
     * 在指定的日期上增加N月并返回
     *
     * @param date
     * @param months
     * @return
     */
    public static Timestamp addMonths(@NonNull Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return toTimestamp(calendar.getTime());
    }

    /**
     * 在指定的日期上增加N年并返回
     *
     * @param date
     * @param years
     * @return
     */
    public static Timestamp addYears(@NonNull Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        return toTimestamp(calendar.getTime());
    }

    /**
     * 获取当前日期的年份
     *
     * @return
     */
    public static int getYear() {
        return getYear(new Date());
    }

    /**
     * 获取指定日期的年份
     *
     * @param date
     * @return
     */
    public static int getYear(@NonNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前日期的月份
     *
     * @return
     */
    public static int getMonth() {
        return getMonth(new Date());
    }

    /**
     * 获取指定日期的月份
     *
     * @param date
     * @return
     */
    public static int getMonth(@NonNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当然日期的日期
     *
     * @return
     */
    public static int getDay() {
        return getDay(new Date());
    }

    /**
     * 获取指定日期的日期
     *
     * @param date
     * @return
     */
    public static int getDay(@NonNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}
