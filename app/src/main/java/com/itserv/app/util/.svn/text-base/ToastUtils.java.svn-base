package com.itserv.app.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.app.itserv.MineApplication;

import static android.widget.Toast.makeText;


/**
 * Toast工具类
 *
 * @author 周作威
 * @Date: 2015年1月28日
 * @Time: 下午2:54:05
 */
public class ToastUtils {
    public static long LAST_CLOCK_TIME;
    private static Toast toast;
    public static void makeTextShort(int resId, Object... params) {
        if (toast != null)
            toast.cancel();
        toast = makeText(MineApplication.getInstance(), ResourcesUtils.getString(resId, params), Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void makeTextShort(String text) {
        if (TextUtils.isEmpty(text))
            return;
        if (toast != null)
            toast.cancel();
        toast = makeText(MineApplication.getInstance(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void makeTextShort(int resId) {
        if (toast != null)
            toast.cancel();
        toast = makeText(MineApplication.getInstance(), resId, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void makeTextLong(int resId, Object... params) {
        if (toast != null)
            toast.cancel();
        toast = makeText(MineApplication.getInstance(), ResourcesUtils.getString(resId, params), Toast.LENGTH_LONG);
        toast.show();
    }

    public static void makeTextLong(String text) {
        if (toast != null)
            toast.cancel();
        toast = makeText(MineApplication.getInstance(), text, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void makeTextLong(int resId) {
        if (toast != null)
            toast.cancel();
        toast = makeText(MineApplication.getInstance(), resId, Toast.LENGTH_LONG);
        toast.show();
    }

    // 防误点
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - LAST_CLOCK_TIME < 500) {
            return true;
        }
        LAST_CLOCK_TIME = time;
        return false;
    }

}
