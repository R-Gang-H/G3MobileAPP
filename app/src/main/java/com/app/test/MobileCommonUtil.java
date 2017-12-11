package com.app.test;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 *  * @Description:安卓手机通用工具类:
 *  * @author:axin
 *  * @time:2016-10-8 下午4:47:02
 */
public class MobileCommonUtil {
    private static MobileCommonUtil mobileCommonUtil = null;
    public Context context;
    private TelephonyManager telephonyManager = null;
    private ConnectivityManager connectivityManager = null;
    private String IMSI = null;

    private MobileCommonUtil(Context context) {
        super();
        this.context = context;
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public MobileCommonUtil getInstance(Context context) {
        if (mobileCommonUtil == null) {
            mobileCommonUtil = new MobileCommonUtil(context);
        }
        return mobileCommonUtil;
    }

    /**
     *  * @Description:获取电话号码
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-9 下午5:48:34
     */
    public String getPhoneNumber() {
        String phoneNumber = telephonyManager.getLine1Number();
        return phoneNumber;
    }

    /**
     *  * @Description:获取运营商信息
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-9 下午5:56:05
     */
    public String getPhoneOperetorName() {
        String phoneOperetorName = "N/A";
        IMSI = telephonyManager.getSubscriberId();
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
            phoneOperetorName = "中国移动";
        } else if (IMSI.startsWith("46001")) {
            phoneOperetorName = "中国联通";
        } else if (IMSI.startsWith("46003")) {
            phoneOperetorName = "中国电信";
        } else {
            phoneOperetorName = "未知";
        }
        return phoneOperetorName;
    }

    /**
     *  * @Description:获取SIM卡序列号
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-9 下午5:58:52
     */
    public String getSimSerialNumber() {
        String simSesialNumber = telephonyManager.getSimSerialNumber();
        return simSesialNumber;
    }

    /**
     *  * @Description:判断网络是否连接
     *  * @param @return
     *  * Boolean
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-9 下午6:08:53
     */
    public Boolean isConnectInternet() {
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            return netInfo != null;
        } else {
            return false;
        }
    }

    /**
     *  * @Description:判断wifi是否连接
     *  * @param @return
     *  * boolean
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午2:32:00
     */
    public boolean isWIFI() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        return connectivityManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     *  * @Description:打开网络设置页面
     *  * @param @param activity
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午2:34:49
     */
    public void callInternetSet(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    /**
     *  * @Description:进入拨号页面但不呼叫
     *  * @param @param phoneNumber
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-9 下午6:21:22
     */
    public void enterDialNotCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     *  * @Description:进入拨号页面并呼叫(权限:CALL_PHONE)
     *  * @param @param phoneNumber
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-9 下午6:21:42
     */
    public void enterDialAndCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    /**
     *  * @Description:发送短信(权限SEND_SMS)
     *  * @param @param smsContent 短信内容
     *  * @param @param phoneNumber 电话号码
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-9 下午6:50:32
     */
    public void sendSms(String smsContent, String phoneNumber) {
        SmsManager smsManager = SmsManager.getDefault();
        //切割整个短信内容，每隔七十字切割一次，并存储到list集合中
        List<String> list = smsManager.divideMessage(smsContent);
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i);
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);
        }
    }

    /**
     *  * @Description:调用系统相机
     *  * @param
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-9 下午6:59:42
     */
    public void callCamera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        context.startActivity(intent);
    }

    /**
     *  * @Description:调用系统相册
     *  * @param
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午2:26:35
     */
    public void callAlbum(int requestcode, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, requestcode);
    }
}
