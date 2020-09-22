package com.ifeng.fhh.zmt.tools;


import com.ifeng.fhh.zmt.tools.constant.DateFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具
 * <p>
 * Created by licheng1 on 2016/12/22.
 */
public class DateUtils {


    public static final String DEFAULT_FORMAT = DateFormatter.UTC_FULL.format();

    public static final String LOCAL_FORMAT = DateFormatter.LOCAL_SIMPLE.format();

    public static final TimeZone LOCAL_TIMEZONE = TimeZone.getDefault();

    public static final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("UTC");


    public static Date parse(String source) throws Exception {
        return parse(source, UTC_TIMEZONE);
    }

    public static Date parse(String source, TimeZone timeZone) throws Exception {
        return parse(source, matchFormat(source), timeZone);
    }

    public static Date parse(String source, String format, TimeZone timeZone) throws Exception {
        Objects.requireNonNull(source, "date parse source string cannot be null");
        Objects.requireNonNull(format, "date parse format string cannot be null");
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.parse(source);
    }

    public static String format(Date date) {
        return format(date, DEFAULT_FORMAT);
    }

    public static String format(Date date, String format) {
        return format(date, format, UTC_TIMEZONE);
    }

    public static String format(Date date, String format, TimeZone timeZone) {
        Objects.requireNonNull(date, "date format source date cannot be null");
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (Objects.nonNull(timeZone))
            dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

    public static String matchFormat(String date) {

        Objects.requireNonNull(date, "matchFormat date string is null");

        for (DateFormatter format : DateFormatter.values()) {
            String regex = format.pattern();
            if (date.matches(regex)) {
                return format.format();
            }
        }
        return null;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param sDate
     * @param eDate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String sDate, String eDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(sDate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(eDate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return days
     */
    public static long millisBetween(Date date1, Date date2) {
        long time = date2.getTime() - date1.getTime();
        return time;
    }


    //获取当前时间的n天前的日期
    public static String DateBeforeNDays(Date sDate, Integer n) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sDate);
        c.add(Calendar.DATE, -n);
        Date date = c.getTime();
        return format.format(date);
    }

    /**
     * 获取当前时间的n年前的日期
     *
     * @param date
     * @param n
     * @return days
     */
    public static String yearBeforeNDays(Date date, Integer n) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, n);
        return format.format(c.getTime());
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return days
     */
    public static int differentDays(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    public static List<String> gainDayStr(Long startTime, Long endTime){
        int days=differentDays(new Date(startTime),new Date(endTime));
        List<String> dayStr=new ArrayList<>();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String strStartTime=simpleDateFormat.format(new Date(startTime));
        dayStr.add(strStartTime);
        for (int i = 0; i < days; i++) {
            startTime=startTime+(1000 * 24 * 60 * 60);
            String strTime=simpleDateFormat.format(new Date(startTime));
            dayStr.add(strTime);
        }
        return dayStr;
    }

    /**
     * 将读取到的mongo数据库 yyyy-MM-dd'T'HH:mm:ss.SSS'Z' 格式的时间字符串，转成 yyyy-MM-dd HH:mm:ss 格式的时间字符串
     * 2019-01-18T03:06:07.761Z  转成  2019-01-18 11:06:07
     * @param source
     * @return
     * @throws ParseException
     */
    public static String parseMongoDateDefault(String source) throws ParseException {
        Objects.requireNonNull(source, "date parse source string cannot be null");
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.DEFAULT_FORMAT);
        dateFormat.setTimeZone(DateUtils.UTC_TIMEZONE);
        Date sourceDate = dateFormat.parse(source);
        return format(sourceDate, DateUtils.LOCAL_FORMAT, DateUtils.LOCAL_TIMEZONE);
    }

    public static Date getOneMonthAgo(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    public static Date getSomeMonthLater(Date date,int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }
}
