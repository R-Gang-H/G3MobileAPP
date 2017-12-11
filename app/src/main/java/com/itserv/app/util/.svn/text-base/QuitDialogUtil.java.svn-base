package com.itserv.app.util;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.itserv.ActivityCollector;
import com.app.itserv.MineApplication;
import com.app.itserv.activity.LoginActivity;
import com.app.itserv.jparser.JsonExitObject;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.http.HttpUtils;
import com.itserv.shed.R;

/**
 * @project name：yyshed
 * @type name：QuitDialogUtil
 * @description：退出App弹框工具类
 * @author：gang
 * @date time：2017-6-9 下午12:32:51
 * @update 周作威 2017年9月21日 11:00:24
 */
public class QuitDialogUtil {
    private static QuitDialogUtil quitDialogUtil;
    Activity activity;
    private AlertDialog dialog;
    private Button quitEnsure;
    private Button quitCancel;

    private static Thread thread = null;

    private QuitDialogUtil() {
    }

    public static QuitDialogUtil getInstance(Activity activity) {
        if (quitDialogUtil == null) {
            quitDialogUtil = new QuitDialogUtil();
        }
        quitDialogUtil.activity = activity;
        return quitDialogUtil;
    }

    public void exit() {
        String token = PreferencesUtils.getString(activity, "token");// token
        String currTenantId = PreferencesUtils.getString(activity,
                "tenantId");// 商户id
        String userName = PreferencesUtils.getString(activity, "userName");// 用户名
        HttpManager.getInstance().exit(token, currTenantId, userName, new HttpCallBack<JsonExitObject>(activity, true) {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort(R.string.network_error);
            }

            @Override
            public void onSuccess(JsonExitObject jsonexit) {
                if (jsonexit != null) {
                    if (jsonexit.isSuccess()) {// 成功
                        ActivityCollector.finishAll();
                        Intent intent = new Intent(MineApplication.getInstance(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        MineApplication.getInstance().startActivity(intent);
                    } else {
                        ToastUtils.makeTextShort(jsonexit.getMsg());
                    }
                } else
                    ToastUtils.makeTextShort(R.string.network_error);
            }
        });
    }

    /**
     *  * @Description:退出弹框  * @param  * void  * @exception:  * @author: axin  *
     *
     * @time:2016-10-26 下午1:42:35
     */
    public void showQuitDialog() {
        dialog = new AlertDialog.Builder(activity).create();
        dialog.show();
        View quitDialog = LayoutInflater.from(activity).inflate(
                R.layout.quit_dialog, null);
        dialog.addContentView(quitDialog,
                new ViewGroup.LayoutParams((int) (activity.getResources()
                        .getDisplayMetrics().widthPixels * 0.9),
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(true);
        quitEnsure = (Button) quitDialog.findViewById(R.id.btn_ensurequit);
        quitCancel = (Button) quitDialog.findViewById(R.id.btn_classquit);
        // 关闭当前账号
        quitEnsure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HttpUtils.token = null;
                dialog.dismiss();
                exit();
            }
        });
        // 关闭当前进程
        quitCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HttpUtils.token = null;
                dialog.dismiss();
                ActivityCollector.finishAll();
//                System.exit(0);
//                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
}
