package com.yycloud.core.config;

import android.os.Environment;

import java.io.File;

public class Constants {
    public final static String STATUS_ONLINE = "online";
    public final static String STATUS_OFFLINE = "offline";
    public final static String UNDEFINED = "undefined";

    public final static String STRATEGYTYPE_DEFAULT = "default";
    public final static String STRATEGYTYPE_CUSTOM = "customer";
    public final static String STRATEGYTYPE_TEMP = "temp";

    public final static String ACTION_BACK = "back";
    public final static String ACTION_STOP = "stop";
    public final static String ACTION_FORWARD = "forward";

    public final static String EERLAY2_STATUS_BACK = "back";
    public final static String EERLAY2_STATUS_STOP = "stop";
    public final static String EERLAY2_STATUS_FORWARD = "forward";

    public final static String HAND_CONTROL = "11";//控制模式
    public final static String AUTO_CONTROL = "12";

    public final static String ACTION_CALLBRATION_ON = "00";
    public final static String ACTION_CALLBRATION_OFF = "01";

    public final static String ACTION_RELAY_OPEN = "1";
    public final static String ACTION_RELAY_CLOSE = "0";

    public final static String ERELAY_STATUS_OPEN = "1";
    public final static String ERELAY_STATUS_CLOSE = "0";
    public final static String ERELAY_STATUS_CLOSE2 = "2"; // 单继电器 0,2都 为关闭

    public final static int RESULT_SUCCESS = 0;
    public final static int RESULT_FAILED = 1;

    public static String APPLICATION_DIR = Environment.getExternalStorageDirectory() + File.separator + "yyshed";//项目根目录
    public static String LOG_PATH = APPLICATION_DIR + File.separator + "logs" + File.separator;//项目日志目录
    public static String CAMERA_PATH = APPLICATION_DIR + File.separator + "img" + File.separator;//项目日志目录
    public static String LOG_FILE_NAME = "yyshedLog.txt";

}
