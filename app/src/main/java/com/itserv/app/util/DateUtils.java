package com.itserv.app.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static android.R.attr.duration;
import static android.R.attr.format;

/**
 * 时间工具类
 *
 * @author 周作威
 * @Date 2015年5月11日
 * @Time 下午12:07:52
 */
public class DateUtils {
    public static final int MINUTES = 60 * 1000;// 分毫秒值
    public static final int HOUR = 60 * MINUTES;// 小时毫秒值
    public static final int DAY = 24 * HOUR;// 天毫秒值

    /**
     * 根据日期获得星期
     * <p>
     * Date date = new Date(); date.setDate(1);//设置日期 传入1-31
     *
     * @return
     */
    public static String getWeekOfDate(int day) {
        Date date = new Date();
        date.setDate(day);
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysCode[intWeek];
    }

    /**
     * 获取指定月份的日历信息
     *
     * @param year  年
     * @param month 月
     * @return
     */
    public static int[] getMonthCalendar(int year, int month) {
        Calendar cl = Calendar.getInstance();
        cl.set(year, month, 1);
        int firstDay = cl.getMinimum(Calendar.DAY_OF_MONTH);
        int lastDay = cl.getMaximum(Calendar.DAY_OF_MONTH);

        int[] day = new int[lastDay];

        for (int i = 0; i < lastDay; i++) {
            day[i] = i + firstDay;
        }
        return day;
    }

    /**
     * 测试时间类
     */
//    public static void DateTest() {
//        Calendar c = Calendar.getInstance();
//        Date curDate = new Date();
//        c.setTime(curDate);
//        int year = c.get(Calendar.YEAR); // 获取年
//        int month = c.get(Calendar.MONTH) + 1; // 获取月份，0表示1月份
//        int day = c.get(Calendar.DAY_OF_MONTH); // 获取当前天数
//        int first = c.getActualMinimum(c.DAY_OF_MONTH); // 获取本月最小天数
//        int last = c.getActualMaximum(c.DAY_OF_MONTH); // 获取本月最大天数
//        int time = c.get(Calendar.HOUR_OF_DAY); // 获取当前小时
//        int min = c.get(Calendar.MINUTE); // 获取当前分钟
//        int xx = c.get(Calendar.SECOND); // 获取当前秒
//        // // 一周第一天是否为星期天
//        // boolean isFirstSunday = (c.getFirstDayOfWeek() == Calendar.SUNDAY);
//        // // 获取周几
//        // int weekDay = c.get(Calendar.DAY_OF_WEEK);
//        // // 若一周第一天为星期天，则-1
//        // if (isFirstSunday) {
//        // weekDay = weekDay - 1;
//        // if (weekDay == 0) {
//        // weekDay = 7;
//        // }
//        // }
//        // // 打印周几
//        // Log.e("weekDay", weekDay + "");
//    }

    /**
     * 格式化时间值
     *
     * @param format   格化
     * @param duration 时间值
     * @return
     */
    public static String getFromat(String format, long duration) {
        SimpleDateFormat format2 = null;
        try {
            format2 = new SimpleDateFormat(format);
            format2.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format2.format(duration);
    }

    public static long getStringTime(String format, String date) throws Exception {
        SimpleDateFormat format2 = new SimpleDateFormat(format);
        return format2.parse(date).getTime();
    }

    /**
     * 给定时间是否为今天时间值
     *
     * @param startThrntableTime
     * @return
     */
    public static boolean isToday(long startThrntableTime) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), day, 0, 0, 0);
        return startThrntableTime > calendar.getTimeInMillis();
    }

    /**
     * 返回当前时间描述
     *
     * @param currentTime 当前时间
     * @return
     */
    public static String getTimeSummary(long currentTime) {
        long timeMillis = System.currentTimeMillis() - currentTime;
        timeMillis = (0 > timeMillis) ? 0 : timeMillis;
        int scale = 60, i = 0;
        for (timeMillis /= 1000 * scale; timeMillis >= 0; timeMillis /= scale, i++) {
            switch (i) {
                case 0:
                    // 分
                    if (timeMillis < 3) {
                        return "刚刚";
                    } else if (timeMillis >= 3 && timeMillis < 60) {
                        return timeMillis + "分钟前";
                    }
                    break;
                case 1:
                    // 时
                    if (timeMillis < 24) {
                        return timeMillis + "小时前";
                    }
                    scale = 24;
                    break;
                case 2:
                    // 天
                    if (timeMillis < 28) {
                        return timeMillis < 7 ? timeMillis + "天前" : timeMillis / 7 == 0 ? "1周前" : timeMillis / 7 + "周前";
                    }
                    scale = 30;
                    break;
                case 3:
                    if (timeMillis < 12) {
                        return timeMillis + "月前";
                    }
                    scale = 12;
                    break;
                case 4:
                    return timeMillis + "年前";
            }
        }
        return null;
    }

    public static String getNumber(int number) {
        String s = "";
        if (number >= 10000)
            s = getObjToString(number * 1.0000d / 10000.0000d, "#0.00") + "万";
        else
            s = number+"";
        return s;
    }


    /**
     * 将数字类型转换为字符串，指定格式
     */
    public static String getObjToString(Object d, String string) {
        DecimalFormat format = new DecimalFormat(string);
        return format.format(d);
    }
}
