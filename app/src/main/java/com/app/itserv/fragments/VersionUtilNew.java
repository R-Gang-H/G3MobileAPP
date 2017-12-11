package com.app.itserv.fragments;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.http.Header;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itserv.app.http.HttpManager;
import com.itserv.app.util.FileUtils;
import com.itserv.app.util.LogUtils;
import com.itserv.app.util.PackageUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.oneapm.agent.android.OneApmAgent.log;

/**
 *  * @Description:版本更新  * @author:axin  * @time:2016-8-14 下午12:43:28
 */
public class VersionUtilNew {
    public Activity activity;

    private static VersionUtilNew versionUtil = null;

    private TextView versionUpdateContent;

    private TextView versionUpdateDevide;

    private LinearLayout versionUpdateButton;

    private Button versionUpdateEntrue;

    private Button versionUpdateCancel;

    private TextView versionUpdateTitle;

    private TextView versionNowVersion;

    public AlertDialog dialog;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                progressBar.setMax(100);
                progressBar.setProgress(msg.arg1 >= 100 ? 98 : 99);
            } else if (msg.what == 2) {
                progressBar.setProgress(100);
                progressDialog.dismiss();
                PackageUtils.installNormal(activity, "/sdcard/download/yyshed.apk");
            } else {
                progressDialog.dismiss();
                ToastUtils.makeTextShort("下载失败");
            }
        }
    };
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;

    public static VersionUtilNew getInstance(Activity activity) {
        if (versionUtil == null) {
            versionUtil = new VersionUtilNew();
        }
        versionUtil.activity = activity;
        return versionUtil;
    }

    /**
     *  * @Description:版本更新dialog  * @param @param nativeVersionCode 本地版本号  * @param @param
     * serviceVersionCode 服务端版本号  * @param @param serviceVersionName 服务端版本名  * @param @param
     * updateVersionUrl 更新地址  * void  * @exception:  * @author: axin  *
     *
     * @time:2016-8-14 下午5:00:57
     */
    public void showVersionUpdateDialog(int nativeVersionCode,
                                        String serviceVersionCode, String serviceVersionName,
                                        final String downLoadUrl, String updateContent) {
        if (null == activity || activity.isFinishing())
            return;
        dialog = new AlertDialog.Builder(activity).create();
        dialog.show();
        View versionDialog = LayoutInflater.from(activity).inflate(
                R.layout.version_update_dialog, null);
        dialog.addContentView(versionDialog,
                new ViewGroup.LayoutParams((int) (activity.getResources()
                        .getDisplayMetrics().widthPixels * 0.9),
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(true);
        // 版本更新
        versionUpdateTitle = (TextView) versionDialog
                .findViewById(R.id.tv_versiondialog_title);
        // 当前版本
        versionNowVersion = (TextView) versionDialog
                .findViewById(R.id.nowversion);
        // 版本更新提醒内容
        versionUpdateContent = (TextView) versionDialog
                .findViewById(R.id.tv_versiondialog_content);
        // 版本更新分割线
        versionUpdateDevide = (TextView) versionDialog
                .findViewById(R.id.tv_devide_two);
        // 版本更新按钮布局
        versionUpdateButton = (LinearLayout) versionDialog
                .findViewById(R.id.ll_versionlayout);
        // 版本更新确定
        versionUpdateEntrue = (Button) versionDialog
                .findViewById(R.id.btn_ensure);
        // 版本更新取消
        versionUpdateCancel = (Button) versionDialog
                .findViewById(R.id.btn_cancel);
        String nowversion = getVersion();
        versionNowVersion.setText("当前版本：V" + nowversion);
        // 更新
        versionUpdateButton.setVisibility(View.VISIBLE);
        versionUpdateDevide.setVisibility(View.VISIBLE);
        versionUpdateTitle.setText("新版		V" + serviceVersionName
                + "		发布啦！主要更新:");
        versionUpdateContent.setText(updateContent);
        // 确定更新
        versionUpdateEntrue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 隐藏版本更新弹框
                dialog.dismiss();
                // 弹框
                progressDialog = new ProgressDialog(
                        activity);
                progressDialog.show();
                View progressLayout = LayoutInflater.from(activity).inflate(
                        R.layout.version_update_progress, null);
                // 更新进度条
                progressBar = (ProgressBar) progressLayout
                        .findViewById(R.id.bar_progress);
                progressDialog
                        .addContentView(
                                progressLayout,
                                new ViewGroup.LayoutParams(
                                        (int) (activity.getResources()
                                                .getDisplayMetrics().widthPixels * 0.9),
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                progressDialog.setCanceledOnTouchOutside(false);
                Request request = new Request.Builder().url(downLoadUrl).addHeader("Accept-Encoding", "identity").build();
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.sslSocketFactory(createSSLSocketFactory());
                builder.build().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message message = new Message();
                        message.what = 3;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            InputStream is;
                            byte[] buf = new byte[2048];
                            int len = 0;
                            FileOutputStream fos = null;
                            // 设置字符编码
                            File file = new File("/sdcard/download/yyshed.apk");
                            if (!file.getParentFile().exists()) {
                                file.getParentFile().mkdirs();
                            }
                            is = response.body().byteStream();
                            long total = response.body().contentLength();
                            if (total < 0)// 如果未获取到文件大小，则定义为10M  周作威  2017/9/22 20:42
                                total = 1024l * 1024l * 10l;
                            fos = new FileOutputStream(file);
                            long sum = 0;
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                                sum += len;
                                long progress = (long) (sum * 1.0f / total * 100L);
                                // 下载中
                                Message message = new Message();
                                message.what = 1;
                                message.arg1 = (int) progress;
                                handler.sendMessage(message);
                            }
                            fos.flush(); // 下载完成
                            Message message = new Message();
                            message.what = 2;
                            handler.sendMessage(message);
                        } catch (Exception e) {
                            Message message = new Message();
                            message.what = 3;
                            handler.sendMessage(message);
                        }
                    }
                });
//                AsyncHttpClient progressClient = new AsyncHttpClient(true, 80, 443);
//                progressClient.get(activity, downLoadUrl,
//                        new FileAsyncHttpResponseHandler(activity) {
//
//                            @Override
//                            public void onFailure(int statusCode,
//                                                  Header[] headers, Throwable e,
//                                                  File response) {
//                                progressDialog.dismiss();
//                                super.onFailure(statusCode, headers, e,
//                                        response);
//                            }
//
//                            @Override
//                            public void onSuccess(int statusCode, File file) {
//                                progressDialog.dismiss();
//                                // 复制asykhttp响应中的file到目标地址
//                                FileUtils.copyFile(file.getAbsolutePath(),
//                                        "/sdcard/download/yyshed.apk");
//                                // 启动系统安装程序intent
//                                PackageUtils.installNormal(activity,
//                                        "/sdcard/download/yyshed.apk");
//                                super.onSuccess(statusCode, file);
//                            }
//
//                            @Override
//                            public void onProgress(int bytesWritten,
//                                                   int totalSize) {
//                                progressBar.setMax(totalSize);
//                                progressBar.setProgress(bytesWritten);
//                                super.onProgress(bytesWritten, totalSize);
//                            }
//                        });
            }
        });
        // 取消更新
        versionUpdateCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()}, new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /**
     * 获取系统当前版本
     *
     * @return
     */
    public String getVersion() {
        // TODO Auto-generated method stub
        try {
            PackageManager manager = activity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(),
                    PackageManager.GET_META_DATA);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
