package com.app.itserv.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itserv.app.util.ToastUtils;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtil;
import com.itserv.shed.R;

/**
 * 实现软件更新的管理类
 */
public class UpdateManager {
    private final static int MSG_NEWVERSION = 5;// 有版本更新
    private Handler outHander = null; // 用于回调发消息的handler
    private int outMsg;// 用于回调发消息的内容

    // 下载中...
    private static final int DOWNLOAD = 1;
    // 下载完成
    private static final int DOWNLOAD_FINISH = 2;
    private static final int NEEDUPDATE = 3;
    // 已经是最新版本
    private static final int ISNEWVERSION = 4;
    // 保存解析的XML信息
    HashMap<String, String> mHashMap;
    // 下载保存路径
    private String mSavePath;
    // 记录进度条数量
    private int progress;
    // 是否取消更新
    private boolean cancelUpdate = false;
    // 上下文对象
    private Context mContext;
    // 进度条
    private ProgressBar mProgressBar;
    // 更新进度条的对话框
    private Dialog mDownloadDialog;
    public final static String MYPACKAGENAME = "com.itserv.shed";
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == DOWNLOAD) {
                // 更新进度条
                System.out.println(progress);
                mProgressBar.setProgress(progress);
            } else if (msg.what == DOWNLOAD_FINISH) {
                // 安装文件
                installApk();
            } else if (msg.what == NEEDUPDATE) {
                showNoticeDialog();
            } else if (msg.what == ISNEWVERSION) {
                ToastUtils.makeTextShort(R.string.soft_update_no);
            }
        }

    };

    public UpdateManager(Context context) {
        super();
        this.mContext = context;
        mHashMap = new HashMap<String, String>();
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate() {
        if (isUpdate()) {
            mHandler.sendEmptyMessage(NEEDUPDATE);
            // 显示提示对话框
            // showNoticeDialog();
        } else {
            mHandler.sendEmptyMessage(ISNEWVERSION);
            // Toast.makeText(mContext, R.string.soft_update_no,
            // Toast.LENGTH_SHORT).show();
        }

    }

    private void showNoticeDialog() {
        // TODO Auto-generated method stub
        // 构造对话框
        Builder builder = new Builder(mContext);
        builder.setTitle(R.string.soft_update_title);
        builder.setMessage(R.string.soft_update_info);
        // 更新
        builder.setPositiveButton(R.string.soft_update_updatebtn,
                new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        // 显示下载对话框
                        showDownloadDialog();
                    }
                });
        // 稍后更新
        builder.setNegativeButton(R.string.soft_update_later,
                new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        sendOutMsg();
                    }
                });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }

    private void showDownloadDialog() {
        // 构造软件下载对话框
        Builder builder = new Builder(mContext);
        builder.setTitle(R.string.soft_updating);
        // 给下载对话框增加进度条
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.progress, null);
        mProgressBar = (ProgressBar) view.findViewById(R.id.update_progress);
        builder.setView(view);
        builder.setNegativeButton(R.string.soft_update_cancel,
                new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        // 设置取消状态
                        cancelUpdate = true;
                        sendOutMsg();
                    }
                });
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
        // 下载文件
        downloadApk();
    }

    /**
     * 下载APK文件
     */
    private void downloadApk() {
        // TODO Auto-generated method stub
        // 启动新线程下载软件
        new DownloadApkThread().start();
    }

    /**
     * 检查软件是否有更新版本
     *
     * @return
     */
    public boolean isUpdate() {
        // 获取当前软件版本
        int versionCode = getVersionCode(mContext);

        try {
            String content = WapiUtil.getVersion();
            Log.v("updateshed", "==================WapiUtil.getVersion()=========" + content);

            if (content != null) {
                WapiUtil.parse_client_version_info(content, mHashMap);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        if (null != mHashMap) {
            int serviceCode = TAUtils.parseStrToInt(mHashMap.get("version"), 0);
            Log.v("updateshed", "==================service版本=========" + String.valueOf(serviceCode));
            // 版本判断
            if (serviceCode > versionCode) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private int getVersionCode(Context context) {
        int versionCode = 0;

        // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
        try {
            versionCode = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 下载文件线程
     *
     * @author Administrator
     */
    private class DownloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    // 获取SDCard的路径
                    String sdpath = Environment.getExternalStorageDirectory()
                            + "/";
                    mSavePath = sdpath + "download";
                    URL url = new URL(mHashMap.get("url"));
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(mSavePath);
                    // 如果文件不存在，新建目录
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, MYPACKAGENAME + ".apk");
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条的位置
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0) {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);// 点击取消就停止下载
                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 取消下载对话框显示
            mDownloadDialog.dismiss();
        }
    }

    /**
     * 安装APK文件
     */
    private void installApk() {
        File apkfile = new File(mSavePath, MYPACKAGENAME + ".apk");
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        // xia 20140115 应用程序安装更新后不自动开升级后的应用的bug
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }

    /**
     * 检测软件更新(无最新提示)
     */
    public void checkUpdateNoMsg() {
        // this.outHander = handler;
        // this.outMsg = msg;
        if (isUpdate()) {
            mHandler.sendEmptyMessage(NEEDUPDATE);
            // 显示提示对话框
            // showNoticeDialog();
        }
    }

    private void sendOutMsg() {
        if (outHander != null) {
            // 给主线程发消息
            Message msg = new Message();
            msg.what = outMsg;
            outHander.sendMessage(msg);
        }
    }
}