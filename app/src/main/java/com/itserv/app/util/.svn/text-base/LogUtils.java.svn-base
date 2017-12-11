package com.itserv.app.util;/**
 * 作者： 周作威 on 2017/9/19 09:02.
 * 类描述：
 */

import android.util.Log;

import com.itserv.shed.BuildConfig;

/**
 * 作者： 周作威 on 2017/9/19 09:02.
 * 类描述：普通log日志打印
 */
public class LogUtils {
    public final static boolean DEBUG = BuildConfig.LOG_DEBUG;
    public final static String tag = "com.itserv.shed";

    public static void e(String tag, Object msg) {
        if (DEBUG) {
            if (null == msg) {
                msg = "LOG信息为空";
            }
            if (msg instanceof Throwable)
                Log.e(LogUtils.tag, ((Throwable)msg).getMessage(), (Throwable)msg);
            else
                Log.e(LogUtils.tag, tag+":"+msg.toString());
        }
    }

    public static void i(String tag, Object msg) {
        if (DEBUG) {
            if (null == msg) {
                msg = "LOG信息为空";
            }
            Log.i(LogUtils.tag, tag+":"+msg.toString());
        }
    }
    public static void d(String tag, Object msg) {
        if (DEBUG) {
            if (null == msg) {
                msg = "LOG信息为空";
            }
            Log.d(LogUtils.tag, tag+":"+msg.toString());
        }
    }
    public static void v(String tag, Object msg) {
        if (DEBUG) {
            if (null == msg) {
                msg = "LOG信息为空";
            }
            Log.v(LogUtils.tag, tag+":"+msg.toString());
        }
    }
}
