package com.flyhigh.common.utils;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 *
 * @author flyhigh
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";

    public static String HH_MM = "HH:mm";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYY__MM__DD = "yyyy/MM/dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";

    public static final String FORMAT_YEAR_MONTH = "yyyy-MM";

    public static final String HOUR_MINUTE_SECOND = "HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        if (null == date) {
            return StringUtils.EMPTY;
        }
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    public static final String toDate() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMM");
    }


    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获取给定时间的月份的第一天的 00:00:00 时间日期
     *
     * @param localDate
     * @return
     */
    public static String getMonthFirstDayDateTime(LocalDate localDate) {
        LocalDate firstDay = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
        LocalDateTime startTime = LocalDateTime.of(firstDay, LocalTime.MIN);
        return startTime.format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 获取当前月份的第一天的 00:00:00 时间日期
     *
     * @return
     */
    public static Date getMonthFirstDayDateTime() {
        LocalDate localDate = LocalDate.now();
        LocalDate firstDay = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
        LocalDateTime startTime = LocalDateTime.of(firstDay, LocalTime.MIN);
        return Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前月份的第一天的 00:00:00 时间日期
     *
     * @return
     */
    public static Date getMonthFirstDayDate(LocalDateTime localDate) {
        LocalDate firstDay = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
        LocalDateTime startTime = LocalDateTime.of(firstDay, LocalTime.MIN);
        return Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当月的最后一天且时间格式 23:59:59
     *
     * @param localDate
     * @return
     */
    public static String getMonthLastDayDateTime(LocalDate localDate) {
        LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime endTime = LocalDateTime.of(lastDay, LocalTime.MAX);
        return endTime.format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 获取当月的最后一天且时间格式 23:59:59
     *
     * @param localDate
     * @return
     */
    public static String getMonthLastDayDateTime(LocalDate localDate, String format) {
        LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime endTime = LocalDateTime.of(lastDay, LocalTime.MAX);
        return endTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取当月的最后一天且时间格式 23:59:59
     *
     * @return
     */
    public static Date getMonthLastDayDateTime() {
        LocalDate localDate = LocalDate.now();
        LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime endTime = LocalDateTime.of(lastDay, LocalTime.MAX);
        return Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取月最后一天
     *
     * @param localDate
     * @return
     */
    public static LocalDate getLastDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取两个日期相差的天数
     *
     * @param one
     * @param two
     * @return
     */
    public static Long betweenDays(LocalDate one, LocalDate two) {
        return ChronoUnit.DAYS.between(one, two);
    }

    /**
     * 获取两个日期之间的所有日期 (年月)
     *
     * @return
     */
    public static List<String> getBetweenYearAndMouth(Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M");
        // 声明保存日期集合
        List<String> list = new ArrayList<>();
        //用Calendar 进行日期比较判断
        Calendar calendar = Calendar.getInstance();
        while (startDate.getTime() <= endDate.getTime()) {
            // 把日期添加到集合
            list.add(sdf.format(startDate));
            // 设置日期
            calendar.setTime(startDate);
            //把日期增加一天
            calendar.add(Calendar.MONTH, 1);
            // 获取增加后的日期
            startDate = calendar.getTime();
        }
        return list;
    }

    /**
     * 格式化
     *
     * @param date
     * @return
     */
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
    }

    public static String formatDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 当前年第一天的 00:00:00 时间日期
     *
     * @return
     */
    public static Date getYearFirstDayTime() {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN);
        return Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 当前年最后一天的 23:59:59 时间日期
     *
     * @return
     */
    public static Date getYearLastDayTime() {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX);
        return Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取本季度的开始时间
     *
     * @return
     */
    public static Date getStartDayOfQuarter() {
        LocalDate now = LocalDate.now();
        Month beginMonthOfQuarter = now.getMonth().firstMonthOfQuarter();
        LocalDate localDate = LocalDate.of(now.getYear(), beginMonthOfQuarter, 1);
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

    }

    /**
     * 获取本季度的结束时间
     *
     * @return
     */
    public static Date getEndDayOfQuarter() {
        LocalDate now = LocalDate.now();
        Month endMonthOfQuarter = Month.of(now.getMonth().firstMonthOfQuarter().getValue() + 2);
        return Date.from(LocalDateTime.of(LocalDate.of(now.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(now.isLeapYear())), LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取两个日期之间的所有月份
     *
     * @param minDate 开始日期
     * @param maxDate 结束日期
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthBetween(String minDate, String maxDate) {
        List<String> result = Lists.newArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        try {
            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                result.add(sdf.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 获取两个日期之间的所有月份
     *
     * @param minDate 开始日期
     * @param maxDate 结束日期
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthBetween(Date minDate, Date maxDate) {
        List<String> result = Lists.newArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        try {
            min.setTime(minDate);
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(maxDate);
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                result.add(sdf.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 获取两个日期之间的所有日期
     *
     * @param minDate 开始日期
     * @param maxDate 结束日期
     * @return
     * @throws ParseException
     */
    public static List<String> getDayBetween(Date minDate, Date maxDate) {
        List<String> result = Lists.newArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);

        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(minDate);
            while (calendar.getTime().before(maxDate) || calendar.getTime().equals(maxDate)) {
                result.add(sdf.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 获取两个日期之间的所有年
     *
     * @param startTime
     * @param endTime
     * @return：list
     */
    public static List<String> getYearBetweenDate(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        // 声明保存日期集合
        List<String> list = new ArrayList<>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {

                // 把日期添加到集合
                list.add(sdf.format(startDate));

                // 设置日期
                calendar.setTime(startDate);

                //把年数增加 1
                calendar.add(Calendar.YEAR, 1);

                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取两个日期之间的所有年
     *
     * @param startDate
     * @param endDate
     * @return：list
     */
    public static List<String> getYearBetweenDate(Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        // 声明保存日期集合
        List<String> list = new ArrayList<>();
        try {
            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {

                // 把日期添加到集合
                list.add(sdf.format(startDate));

                // 设置日期
                calendar.setTime(startDate);

                //把年数增加 1
                calendar.add(Calendar.YEAR, 1);

                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取上周一日期
     *
     * @param date
     * @return
     */
    public static Date getLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    /**
     * 获取上周一日期
     *
     * @param date   当前时间
     * @param number 数值
     * @return
     */
    public static Date getLastWeekMonday(Date date, Integer number) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7 * number);
        return cal.getTime();
    }

    /**
     * 获取本周一日期
     *
     * @param date
     * @return
     */
    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取指定日期的下个月 yyyy-MM
     *
     * @param month 时间
     * @return yyyy-MM
     */
    public static String getNextMonth(String month) {
        return DateUtils.getNextMonth(DateUtils.dateTime(DateUtils.YYYY_MM_DD, month));
    }

    /**
     * 获取指定日期的下个月 yyyy-MM
     *
     * @param month 时间
     * @return yyyy-MM
     */
    public static String getNextMonth(String month, String format) {
        return DateUtils.getNextMonth(DateUtils.dateTime(month, format), format);
    }

    /**
     * 获取指定日期的下个月 yyyy-MM
     *
     * @param month 时间
     * @return yyyy-MM
     */
    public static String getNextMonth(Date month) {
        return DateUtils.getNextMonth(month, DateUtils.YYYY_MM);
    }

    /**
     * 获取指定日期的下个月 yyyy-MM
     *
     * @param month 时间
     * @return yyyy-MM
     */
    public static String getNextMonth(Date month, String format) {
        if (null == month) {
            return StringUtils.EMPTY;
        }
        return DateUtils.parseDateToStr(format, DateUtil.offsetMonth(month, 1));
    }

}
