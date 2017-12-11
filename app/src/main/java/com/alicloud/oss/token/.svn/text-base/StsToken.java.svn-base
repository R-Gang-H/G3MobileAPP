package com.alicloud.oss.token;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.IOUtils;
import com.alicloud.oss.model.Paramet;
import com.alicloud.oss.model.StsModel;
import com.google.gson.Gson;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.alicloud.oss.model.Paramet.FAIL;

/**
 * Created by jingdan on 2017/8/31.
 */
public class StsToken {

    //oss对象存储
    private StsToken stsTokenSamples;

    private static volatile StsToken instance = null;

    public StsToken() {
        //get sts token
        stsTokenSamples = new StsToken(mHandler);
        stsTokenSamples.getStsTokenAndSet();
    }

    public static StsToken getInstance() {
        synchronized (StsToken.class) {
            if (instance == null) {
                instance = new StsToken();
            }
        }
        return instance;
    }

    private WeakReference<Handler> handler;

    public StsToken(Handler handler) {
        this.handler = new WeakReference<>(handler);
    }


    //建议sts的token获取等放在服务器端进行获取对提高安全性
    public void getStsTokenAndSet() {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL stsUrl = new URL(Paramet.STS_SERVER_API);
                    HttpURLConnection conn = (HttpURLConnection) stsUrl.openConnection();
                    conn.setConnectTimeout(3000);
                    conn.connect();
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String result = IOUtils.readStreamAsString(inputStream, "utf-8");
                        Gson gson = new Gson();
                        StsModel stsModel = gson.fromJson(result, StsModel.class);

                        Message msg = Message.obtain();
                        msg.obj = stsModel;
                        msg.what = Paramet.STS_TOKEN_SUC;
                        StsToken.this.mHandler.sendMessage(msg);
                    } else {
                        OSSLog.logDebug("stsResponseCode", Boolean.parseBoolean(String.valueOf(responseCode)));
                        StsToken.this.mHandler.sendEmptyMessage(FAIL);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    StsToken.this.mHandler.sendEmptyMessage(FAIL);
                }
            }
        }.start();
    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            boolean handled = false;
            switch (msg.what) {
                case Paramet.STS_TOKEN_SUC://获取token
                    Log.v("STS_TOKEN_SUC", "-------------token获取成功sts_token_suc-------------");
                    StsModel response = (StsModel) msg.obj;
                    Initialicloud.getInstance().setOssClient(response.getAccessKeyId(), response.getAccessKeySecret(), response.getSecurityToken());
                    handled = true;
                    break;
                case Paramet.FAIL:
                    Log.v("STS_TOKEN_SUC", "-------------token获取失败sts_token_fail------------");
                    handled = true;
                    break;
            }
            return handled;
        }
    });
}
