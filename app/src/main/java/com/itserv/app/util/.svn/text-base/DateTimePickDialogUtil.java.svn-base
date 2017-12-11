package com.itserv.app.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.itserv.shed.R;

/* 
 *  日期时间选择控件 使用方法：
 *  @author
 */
public class DateTimePickDialogUtil implements OnDateChangedListener,
        OnTimeChangedListener {
    private DatePicker datePicker;
    private AlertDialog ad;
    private String dateTime;
    private String initDateTime;
    private Activity activity;

    /**
     * 日期时间弹出选择框构造函数
     *
     * @param activity     ：调用的父activity
     * @param initDateTime 初始日期时间值，作为弹出窗口的标题和日期时间初始值
     */
    public DateTimePickDialogUtil(Activity activity, String initDateTime) {
        this.activity = activity;
        this.initDateTime = initDateTime;

    }

    public void init(DatePicker datePicker) {
        Calendar calendar = Calendar.getInstance();
        if (!(null == initDateTime || "".equals(initDateTime))) {
            calendar = this.getCalendarByInintData(initDateTime);
        } else {
            initDateTime = calendar.get(Calendar.YEAR) + "-"
                    + calendar.get(Calendar.MONTH) + "-"
                    + calendar.get(Calendar.DAY_OF_MONTH) + " "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                    + calendar.get(Calendar.MINUTE);
        }

        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);
    }

    /**
     * 弹出日期时间选择框方法
     *
     * @param inputDate :为需要设置的日期时间文本编辑框
     * @return
     */
    public AlertDialog dateTimePicKDialog(final TextView inputDate) {
        ScrollView dateTimeLayout = (ScrollView) activity.getLayoutInflater()
                .inflate(R.layout.common_datetime, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        init(datePicker);

        ad = new AlertDialog.Builder(activity)
                .setTitle(initDateTime)
                .setView(dateTimeLayout)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inputDate.setText(dateTime);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //inputDate.setText("");
                    }
                }).show();

        onDateChanged(null, 0, 0, 0);
        return ad;
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();

        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        dateTime = sdf.format(calendar.getTime());
        ad.setTitle(dateTime);
    }

    /**
     * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
     *
     * @param initDateTime 初始日期时间值 字符串型
     * @return Calendar
     */
    private Calendar getCalendarByInintData(String initDateTime) {
        Calendar calendar = Calendar.getInstance();

        // 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒

        String yearStr = spliteString(initDateTime, "-", "index", "front"); // 年份
        String monthAndDay = spliteString(initDateTime, "-", "index", "back"); // 月日

        String monthStr = spliteString(monthAndDay, "-", "index", "front"); // 月
        String dayStr = spliteString(monthAndDay, "-", "index", "back"); // 日

        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
        int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
        int currentDay = Integer.valueOf(dayStr.trim()).intValue();

        calendar.set(currentYear, currentMonth, currentDay);
        return calendar;
    }

    /**
     * 截取子串
     *
     * @param srcStr      源串
     * @param pattern     匹配模式
     * @param indexOrLast
     * @param frontOrBack
     * @return
     */
    public static String spliteString(String srcStr, String pattern,
                                      String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
        } else {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy年MM月dd日
     */
    public static String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * 获取时间
     *
     * @return 返回短时间字符串格式yyyy年MM月dd日
     */
    public static String formatDate(long time, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(date);
        return sdf.format(new Date(time));
    }

    /**
     * 判断日期大小
     *
     * @param DATE1 当前日期
     * @param DATE2 选择日期
     * @return Boolean
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-19 上午9:39:42
     */
    public static Boolean compare_date(String DATE1, String DATE2) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return true;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 判断字符串是否为日期格式
     *
     * @param date
     * @return
     */
    public boolean isDateStringValid(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        // 输入对象不为空
        try {
            sdf.parse(date);
            return true;
        } catch (java.text.ParseException e) {
            return false;
        }
    }

}
