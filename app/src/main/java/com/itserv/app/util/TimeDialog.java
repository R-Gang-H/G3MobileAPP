package com.itserv.app.util;

import android.app.TimePickerDialog;
import android.content.Context;

import java.util.Calendar;

/**
 * 作者： 周作威 on 2017/10/9 16:03.
 * 类描述：时间选择器
 */
public class TimeDialog extends TimePickerDialog {
    public TimeDialog(Context context, long time, OnTimeSetListener callBack) {
        super(context, callBack, 0, 0, true);
        Calendar calendar = Calendar.getInstance();
        try {
            if (time > 0)
                calendar.setTimeInMillis(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }

    public TimeDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView) {
        super(context, callBack, hourOfDay, minute, is24HourView);
    }
}
