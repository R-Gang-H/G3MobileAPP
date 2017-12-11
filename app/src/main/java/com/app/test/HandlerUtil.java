package com.app.test;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 *  * @Description:handler封装
 *  * @author:axin
 *  * @time:2016-10-8 下午3:26:29
 */
public class HandlerUtil {
    private static Handler mainHandler = null;
    private static HandlerUtil handlerUtil = null;

    private HandlerUtil() {
        super();
    }

    public static synchronized HandlerUtil getInstance(Handler handler) {
        if (handler.getLooper().myLooper() == Looper.getMainLooper()) {
            mainHandler = handler;
        } else {
            Log.e("error", "this handler not be in mainthread");
        }
        if (handlerUtil == null) {
            handlerUtil = new HandlerUtil();
        }
        return handlerUtil;
    }

    public Handler getMainHandler() {
        return mainHandler;
    }

    /**
     *  * @Description:发送空消息
     *  * @param @param what
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:45:02
     */
    public void sendEmptyMessage(int what) {
        Message emptyMessage = new Message();
        emptyMessage.what = what;
        mainHandler.sendEmptyMessage(what);
    }

    /**
     *  * @Description:发送延时空消息
     *  * @param @param what
     *  * @param @param delay
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:45:16
     */
    public void sendEmptyMessageDelay(int what, int delay) {
        Message delayMessage = new Message();
        delayMessage.what = what;
        mainHandler.sendEmptyMessageDelayed(what, delay);
    }

    /**
     *  * @Description:发送消息
     *  * @param @param msg
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:45:32
     */
    public void sendMessage(Message msg) {
        mainHandler.sendMessage(msg);
    }

    /**
     *  * @Description:发送延时消息
     *  * @param @param msg
     *  * @param @param delay
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:45:43
     */
    public void sendMessageDelay(Message msg, int delay) {
        mainHandler.sendMessageDelayed(msg, delay);
    }
}
