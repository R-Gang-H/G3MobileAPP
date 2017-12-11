package com.app.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *  * @Description:日期操作工具类
 *  * @author:axin
 *  * @time:2016-10-25 下午4:27:53
 */
public class DateLocalUtil {
    /**
     * 英文简写如：2016
     */
    public static String FORMAT_Y = "yyyy";

    /**
     * 英文简写如：12:01
     */
    public static String FORMAT_HM = "HH:mm";

    /**
     * 英文简写如：1-12 12:01
     */
    public static String FORMAT_MDHM = "MM-dd HH:mm";

    /**
     * 英文简写（默认）如：2016-12-01
     */
    public static String FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 英文全称  如：2016-12-01 23:15
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 英文全称  如：2016-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL_SN = "yyyyMMddHHmmssS";

    /**
     * 中文简写  如：2016年12月01日
     */
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";

    /**
     * 中文简写  如：2016年12月01日  12时
     */
    public static String FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时";

    /**
     * 中文简写  如：2016年12月01日  12时12分
     */
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";

    /**
     * 中文全称  如：2016年12月01日  23时15分06秒
     */
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    public static Calendar calendar = null;
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     *  * @Description:string转 date
     *  * @param @param str
     *  * @param @return
     *  * Date
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:49:46
     */
    public static Date stringToDate(String str) {
        return stringToDate(str, null);
    }

    /**
     *  * @Description:按格式把string转date
     *  * @param @param str
     *  * @param @param format
     *  * @param @return
     *  * Date
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:51:06
     */
    public static Date stringToDate(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     *  * @Description:string 转 Calendar
     *  * @param @param str
     *  * @param @return
     *  * Calendar
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:52:23
     */
    public static Calendar stringToCalendar(String str) {
        return stringToCalendar(str, null);
    }

    /**
     *  * @Description:按格式 string转Calendar
     *  * @param @param str
     *  * @param @param format
     *  * @param @return
     *  * Calendar
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:53:15
     */
    public static Calendar stringToCalendar(String str, String format) {

        Date date = stringToDate(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;
    }

    /**
     *  * @Description:Calendar 转 string
     *  * @param @param c
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:54:19
     */
    public static String dateToString(Calendar c) {
        // yyyy-MM-dd HH:mm:ss
        return dateToString(c, null);
    }

    /**
     *  * @Description:按格式 Calendar 转 string
     *  * @param @param c
     *  * @param @param format
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:17:57
     */
    public static String dateToString(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return dateToString(c.getTime(), format);
    }

    /**
     *  * @Description:date 转 string
     *  * @param @param d
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:20:34
     */
    public static String dateToString(Date d) {
        // yyyy-MM-dd HH:mm:ss
        return dateToString(d, null);
    }

    /**
     *  * @Description:按格式 date 转 string
     *  * @param @param d
     *  * @param @param format
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:20:50
     */
    public static String dateToString(Date d, String format) {
        // yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    /**
     *  * @Description:获取当前日期
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:21:55
     */
    public static String getCurDateString() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) + "-" +
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
                ":" + c.get(Calendar.SECOND);
    }

    /**
     *  * @Description:获取当前日期的字符串格式
     *  * @param @param format 格式
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:22:20
     */
    public static String getCurDateString(String format) {
        Calendar c = Calendar.getInstance();
        return dateToString(c, format);

    }

    /**
     *  * @Description:获取当前时间
     *  * @param @param time
     *  * @param @return 格式到秒
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:23:43
     */
    public static String getMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }

    /**
     *  * @Description:获取当前时间
     *  * @param @param time
     *  * @param @return 格式到天
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:24:05
     */
    public static String getDay(long time) {

        return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }

    /**
     *  * @Description:获取当前时间
     *  * @param @param time
     *  * @param @return 格式到毫秒
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:24:32
     */
    public static String getSMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }

    /**
     *  * @Description:在当前日期上增加数个整月
     *  * @param @param date 当前日期
     *  * @param @param n 增加的月数
     *  * @param @return 增加后的日期
     *  * Date
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:25:11
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();

    }

    /**
     *  * @Description:在当前日期上增加数个天数
     *  * @param @param date 当前日期
     *  * @param @param n 天数
     *  * @param @return 增加后的日期
     *  * Date
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:26:38
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();

    }

    /**
     *  * @Description:获取距现在某一小时的时刻
     *  * @param @param format 格式化时间的格式
     *  * @param @param h 距现在的小时 例如：h=-1为上一个小时，h=1为下一个小时
     *  * @param @return 获取距现在某一小时的时刻
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:39:11
     */
    public static String getNextHour(String format, int h) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(date.getTime() + h * 60 * 60 * 1000);
        return sdf.format(date);

    }

    /**
     *  * @Description:获取时间戳
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:27:52
     */
    public static String getTimeTag() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());

    }

    /**
     *  * @Description:返回月
     *  * @param @param date 当前日期
     *  * @param @return 月
     *  * int
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:28:19
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     *  * @Description:返回日
     *  * @param @param date 当前日期
     *  * @param @return 日
     *  * int
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:28:46
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     *  * @Description:返回小时
     *  * @param @param date 当前日期
     *  * @param @return 小时
     *  * int
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:29:16
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     *  * @Description:返回分钟
     *  * @param @param date 当前日期
     *  * @param @return 分钟
     *  * int
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:29:42
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     *  * @Description: 获取默认的日期格式
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:30:47
     */
    public static String getDefaultDateFormat() {

        return FORMAT_YMDHMS;
    }

    /**
     *  * @Description:返回秒钟
     *  * @param @param date 当前日期
     *  * @param @return 秒种
     *  * int
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:31:13
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();

        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }


    /**
     *  * @Description:使用预设格式提取字符串日期
     *  * @param @param strDate 日期字符串
     *  * @param @return 提取字符串的日期
     *  * Date
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:40:04
     */
    public static Date parseByDefault(String strDate) {
        return parseByUser(strDate, getDefaultDateFormat());

    }

    /**
     *  * @Description:返回毫秒
     *  * @param @param date 当前日期
     *  * @param @return 毫秒数
     *  * long
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:32:01
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     *  * @Description:按默认格式的字符串距离今天的天数
     *  * @param @param date 日期字符串
     *  * @param @return 按默认格式的字符串距离今天的天数
     *  * int
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:41:11
     */
    public static int countDayByDefault(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parseByDefault(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }

    /**
     *  * @Description:使用用户格式提取字符串日期
     *  * @param @param strDate 日期字符串
     *  * @param @param pattern 日期格式
     *  * @param @return 提取字符串日期
     *  * Date
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:41:45
     */
    public static Date parseByUser(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     *  * @Description:按用户格式字符串距离今天的天数
     *  * @param @param date 日期字符串
     *  * @param @param format 日期格式
     *  * @param @return 按用户格式字符串距离今天的天数
     *  * int
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午6:42:25
     */
    public static int countDayByUser(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parseByUser(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }
}
