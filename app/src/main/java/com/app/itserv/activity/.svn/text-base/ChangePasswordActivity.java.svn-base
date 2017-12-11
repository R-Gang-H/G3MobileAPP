package com.app.itserv.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.itserv.ActivityCollector;
import com.app.itserv.BaseActivity;
import com.app.itserv.jparser.JsonChangePasswordObject;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.QuitDialogUtil;
import com.itserv.app.util.RegexChk;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * @project name：yyshed
 * @type name：ChangePasswordActivity
 * @description：修改密码
 * @author：gang
 * @date time：2017-6-3 下午3:28:51
 */
public class ChangePasswordActivity extends BaseActivity implements
        View.OnClickListener {
    // 旧密码
    private EditText originalPsw;
    // 新密码
    private EditText newPsw;
    // 确认密码
    private EditText changepsw_psw_et;
    // 提交按钮
    private Button submite;
    // 重置按钮
    private Button changepsw_reset;
    // 返回
    private ImageView back;

    private Context context;
    // 用户名
    private String currUserId, token, currTenantId, userName, password;
    private String original;
    private String newpsd;
    private String changepsd;

    private static final int CHANGE_PSD_ERROR = 1;
    protected static final int CHANGE_PSD_SUCCESS = 2;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_change_psw;
    }

    /**
     * activity创建
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        System.out.println("====================ChangePasswordActivity启动");
        initView();
    }

    /**
     * handler处理
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHANGE_PSD_ERROR:
                    TAUtils.toastMessage((Activity) context, msg.obj.toString());
                    break;
                case CHANGE_PSD_SUCCESS:
                    String change_psd = (String) msg.obj;
                    new Thread(new ChangePsdAction(change_psd)).start();
                    break;
                default:
                    break;
            }

        }

    };

    /**
     * @description：初始化
     * @author：gang
     * @date time：2017-6-3 下午4:18:26
     */
    private void initView() {
        back = (ImageView) findViewById(R.id.img_back);
        originalPsw = (EditText) findViewById(R.id.changepsw_originalpsw_et);
        newPsw = (EditText) findViewById(R.id.changepsw_newpsw_et);
        changepsw_psw_et = (EditText) findViewById(R.id.changepsw_psw_et);
        back.setOnClickListener(this);
        submite = (Button) findViewById(R.id.changepsw_submite);
        submite.setOnClickListener(this);
        changepsw_reset = (Button) findViewById(R.id.changepsw_reset);
        changepsw_reset.setOnClickListener(this);
    }

    /**
     * 获取修改密码信息
     */
    private void MygetText() {
//        currUserId = PreferencesUtils.getString(context, "id");// 用户id
        token = PreferencesUtils.getString(context, "token");// token
        currTenantId = PreferencesUtils.getString(context, "tenantId");// 商户id
        userName = PreferencesUtils.getString(context, "userName");// 用户名
        password = PreferencesUtils.getString(context, "password");// 旧密码
        original = originalPsw.getText().toString().trim();
        newpsd = newPsw.getText().toString().trim();
        changepsd = changepsw_psw_et.getText().toString().trim();
    }

    /**
     * 重置修改密码信息
     */
    private void MyReset() {
        originalPsw.setText("");
        newPsw.setText("");
        changepsw_psw_et.setText("");
    }

    /**
     * view点击事件处理
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changepsw_submite:// 提交
                MygetText();// 获取信息
                if (TextUtils.isEmpty(original)) {
                    ToastUtils.makeTextShort("请输入旧密码!");
                    return;
                }
                if (TextUtils.isEmpty(newpsd)) {
                    ToastUtils.makeTextShort("请输入新密码!");
                    return;
                }
                if (TextUtils.isEmpty(changepsd)) {
                    ToastUtils.makeTextShort("请输入确认密码!");
                    return;
                }

//                if (RegexChk.checkPassword(original)) {
//                    ToastUtils.makeTextShort("旧密码 长度为 8-20位，必须包括字母、数字、特殊符号的组合");
//                    return;
//                }

                if (RegexChk.checkPassword(newpsd)) {
                    ToastUtils.makeTextShort("新密码 长度为 8-20位，必须包括字母、数字、特殊符号的组合");
                    return;
                }

                if (RegexChk.checkPassword(changepsd)) {
                    ToastUtils.makeTextShort("确认密码 长度为 8-20位，必须包括字母、数字、特殊符号的组合");
                    return;
                }
                if (TextUtils.equals(original, newpsd)) {
                    ToastUtils.makeTextShort("新密码不能与旧密码相同!");
                    return;
                }
                if (TextUtils.equals(userName, newpsd)) {
                    ToastUtils.makeTextShort("新密码必须满足复杂性和安全性校验规则,不能与登录名相同!");
                    return;
                }

                if (!TextUtils.equals(newpsd, changepsd)) {
                    ToastUtils.makeTextShort("确认密码必须与新密码一致!");
                    return;
                }

                new Thread(new ChangePsdRequest()).start();// 请求修改密码线程

                break;
            case R.id.changepsw_reset:// 重置
                MyReset();
                break;
            case R.id.img_back:// 返回
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * @project name：yyshed
     * @type name：ChangePsdRequest
     * @description：修改密码请求线程
     * @author：gang
     * @date time：2017-6-3 下午4:18:49
     */
    class ChangePsdRequest extends Thread {

        public void run() {
            try {
                Looper.prepare();

                // 在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("username", userName);
                map.put("psd", original);
                map.put("newpsd", newpsd);

                Log.v("changepsd", "修改密码请求");

                WapiUtilEx.changePsd(map, new MYCallBack() {
                    @Override
                    public void onError(int code, String message) {
                        Message msg = Message.obtain();
                        msg.obj = message;
                        msg.what = CHANGE_PSD_ERROR;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        Message msg = Message.obtain();
                        msg.obj = response;
                        msg.what = CHANGE_PSD_SUCCESS;
                        mHandler.sendMessage(msg);
                    }

                });
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }

    }

    /**
     * @project name：yyshed
     * @type name：ChangePsdAction
     * @description：修改密码解析线程
     * @author：gang
     * @date time：2017-6-3 下午4:19:10
     */
    class ChangePsdAction extends Thread {

        private String changePsd = null;

        public ChangePsdAction(String change_psd) {
            // TODO Auto-generated constructor stub
            this.changePsd = change_psd;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (!TextUtils.isEmpty(changepsd)) {
                    Gson gson = new Gson();
                    JsonChangePasswordObject jsonchangepsd = gson.fromJson(
                            changePsd, JsonChangePasswordObject.class);

                    if (!jsonchangepsd.equals("") && jsonchangepsd != null) {
                        String msg = jsonchangepsd.getMsg();// 消息提醒
                        if (!jsonchangepsd.isSuccess()) {// 修改失败
                            TAUtils.toastMessage((Activity) context, msg);
                            return;
                        }
                        Builder builder = new Builder(
                                ChangePasswordActivity.this);
                        builder.setMessage("密码修改成功,请重新登录!");
                        builder.setTitle("提示");
                        builder.setCancelable(false);
                        builder.setPositiveButton(
                                "确认",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0,
                                                        int arg1) {
                                        // TODO Auto-generated method stub
                                        arg0.dismiss();
                                        QuitDialogUtil.getInstance(ChangePasswordActivity.this).exit();
                                    }
                                });
                        builder.create().show();

//						修改成功
                        ActivityCollector.finishAll();
                        context.startActivity(new Intent(context,
                                LoginActivity.class));
                        System.exit(0);
                    }
                } else {
                    TAUtils.toastMessage((Activity) context, "服务器异常请联系管理员!");
                }

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }

    /**
     * activity销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
