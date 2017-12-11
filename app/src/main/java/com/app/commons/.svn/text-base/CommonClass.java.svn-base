package com.app.commons;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.List;

public class CommonClass {
    // app name
    public final static String APP_NAME = "com.itserv.shed";
    public final static String APP_LAUNCH_ACTIVITY_NAME = "com.app.itserv.activity.SplashActivity";
    // save the avi installationid failed
    public final static int AVI_INSTALLATION_SAVE_FAIL = 4500;
    // upload the user's installationId to the server success
    public final static int SERVER_INSTALLATION_SUCCESS = 4501;

    public static final String AVI_ID = "tWEMecKAyPC3JI0k9otHx5TM";
    public static final String AVI_KEY = "uR1ODjKJRmD5RFUQA1ty9onl";

    /**
     * judge whether the app is alive
     *
     * @param context
     * @param appName
     * @return
     */
    public static boolean isAppAlive(Context context, String appName) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        // 获取正在运行的应用
        List<RunningAppProcessInfo> run = am.getRunningAppProcesses();
        // 获取包管理器，在这里主要通过包名获取程序的图标和程序名
        PackageManager pm = context.getPackageManager();

        for (RunningAppProcessInfo ra : run) {
            // 这里主要是过滤系统的应用和电话应用，当然你也可以把它注释掉。
            if (ra.processName.equals("system")
                    || ra.processName.equals("com.android.phone")) {
                continue;
            }

            if (ra.processName.equals(appName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 重启应用
     *
     * @param context
     */
    public static void restartApp(Context context) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage((CommonClass.APP_NAME));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void finishProgram() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
