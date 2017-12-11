package com.app.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.TimeZone;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;

public class UtilityClass {
    private final static String TAG = "UtilityClass";

    /////////////////////////////////////////////////////////////////音频、震动相关
    private static MediaPlayer m_mediaPlayer = null;
    private static Vibrator m_vibrator = null;

    //播放指定路径的音频
    public static void play_voice_path(String voice_file_path) {
        try {
            m_mediaPlayer.stop();
            m_mediaPlayer.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
        m_mediaPlayer = new MediaPlayer();
        try {
            File file = new File(voice_file_path);
            FileInputStream fis = new FileInputStream(file);
            m_mediaPlayer.setDataSource(fis.getFD());
            m_mediaPlayer.prepare();
            m_mediaPlayer.start();

        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //播放资源文件 /raw中
    public static void play_voice_res(Context context, int id, boolean looping) {
        try {
            m_mediaPlayer.stop();
            m_mediaPlayer.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
        m_mediaPlayer = MediaPlayer.create(context, id);
        try {
            //m_mediaPlayer.prepare();
            m_mediaPlayer.setLooping(looping);
            m_mediaPlayer.start();

        } catch (Exception e1) {
            Log.v(TAG, e1.getMessage());
        }
    }

    //暂停播放
    public static void pause_player() {
        try {
            m_mediaPlayer.pause();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    //停止播放音频
    public static void stop_player() {
        try {
            m_mediaPlayer.stop();
            m_mediaPlayer.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /* 开启震动
     * pattern： 静止时常、震动时常、静止时常、震动时常。。。。    单位为ms
     * repeat_time 反复震动   -1为否
     */
    public static void start_vibrate(Application application, long[] pattern, int repeat_index) {
        m_vibrator = (Vibrator) (application.getSystemService(Service.VIBRATOR_SERVICE));
        m_vibrator.vibrate(pattern, repeat_index);
    }

    //停止震动
    public static void cancel_vibrate() {
        if (null != m_vibrator) {
            m_vibrator.cancel();
        }
    }

    ///////////////////////////////////////////////////////////////////////////时间相关

    /**
     * 获取更改时区后的日期
     *
     * @param date    日期
     * @param oldZone 旧时区对象
     * @param newZone 新时区对象
     * @return 日期
     */
    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {
        Date dateTmp = null;
        if (date != null) {
            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
            dateTmp = new Date(date.getTime() - timeOffset);
        }
        return dateTmp;
    }

}