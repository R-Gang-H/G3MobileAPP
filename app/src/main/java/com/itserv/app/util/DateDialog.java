package com.itserv.app.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.TextUtils;

import java.util.Calendar;

/**
 * 作者： 周作威 on 2017/10/9 16:02.
 * 类描述：日期日期选择器
 */
public class DateDialog extends DatePickerDialog {

    public DateDialog(Context context, long date, OnDateSetListener callBack) {
        this(context, callBack, 0, 0, 0);
        Calendar calendar = Calendar.getInstance();
        try {
            if (date > 0)
                calendar.setTimeInMillis(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        onDateChanged(null, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public DateDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }
}
