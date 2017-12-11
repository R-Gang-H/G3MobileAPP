package com.app.itserv.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.app.itserv.BaseActivity;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAPreferenceUtil;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 修改密码
 *
 * @author Administrator
 */
public class ResetPasswordActivity extends BaseActivity implements
        OnClickListener {

    private Context mContext;
    private String phone = "";
    private BroadcastReceiver smsReceiver;
    private IntentFilter filter2;
    private Handler handler;
    private EditText mResetPasswordPhone, mResetPsdSmsCode, mResetPsdPsd;
    private String strContent = "";
    private String patternCoder = "(?<!\\d)\\d{6}(?!\\d)";
    private Button mResetPsdSmsRequest, reset_psd_ok;
    private TimeCount time;
    private boolean isResetPsdSmsCode = false, isResetPsdPsd = false;
    private View mBackView;
    private TextView mTitleTv;
    private static String TAG = "ResetPasswordSmsActivity";
    private CheckBox mckShowPwd;

    @Override
    protected int layoutViewId() {
        return R.layout.reset_password_lay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initViews();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                mResetPsdSmsCode.setText(strContent);
            }

        };
        filter2 = new IntentFilter();
        filter2.addAction("android.provider.Telephony.SMS_RECEIVED");
        filter2.setPriority(Integer.MAX_VALUE);
        smsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Object[] objs = (Object[]) intent.getExtras().get("pdus");
                for (Object obj : objs) {
                    byte[] pdu = (byte[]) obj;
                    SmsMessage sms = SmsMessage.createFromPdu(pdu);
                    // 短信的内容
                    String message = sms.getMessageBody();
                    Log.d("logo", "message     " + message);
                    // 短息的手机号。。+86开头？
                    String from = sms.getOriginatingAddress();
                    Log.d("logo", "from     " + from);
                    if (!TextUtils.isEmpty(from)) {
                        String code = patternCode(message);
                        if (!TextUtils.isEmpty(code)) {
                            strContent = code;
                            handler.sendEmptyMessage(1);
                        }
                    }
                }
            }
        };
        registerReceiver(smsReceiver, filter2);
    }

    private void initViews() {
        mBackView = findViewById(R.id.btn_back);
        mBackView.setOnClickListener(this);
        mTitleTv = (TextView) findViewById(R.id.title_txt);
        mTitleTv.setText(this.getResources().getString(R.string.resetpsd_title));
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
        mResetPasswordPhone = (EditText) findViewById(R.id.reset_password_phone);
        // String str =
        // this.getResources().getString(R.string.reset_password_tip);
        // String temp = String.format(str, phone);
        mResetPasswordPhone.addTextChangedListener(new ResetPsdPhone());
        mResetPsdSmsCode = (EditText) findViewById(R.id.reset_psd_sms_code);
        mResetPsdSmsCode.addTextChangedListener(new ResetPsdSmsCode());
        mResetPsdSmsRequest = (Button) findViewById(R.id.reset_psd_sms_code_request);
        mResetPsdSmsRequest.setOnClickListener(this);
        mResetPsdSmsRequest.setEnabled(false);
        mResetPsdPsd = (EditText) findViewById(R.id.reset_psd_psd);
        mResetPsdPsd.addTextChangedListener(new ResetPsdPsd());
        mckShowPwd = (CheckBox) findViewById(R.id.ck_show_pwd);
        reset_psd_ok = (Button) findViewById(R.id.reset_psd_ok);
        reset_psd_ok.setEnabled(false);
        reset_psd_ok.setOnClickListener(this);

        TAPreferenceUtil preferenceUtil = TAPreferenceUtil
                .getInstance(mContext);
        /* 显示明文密码start */
        Boolean isshowpwd = preferenceUtil.getBoolean(
                TAPreferenceUtil.IS_SHOW_PWD, false);
        mckShowPwd.setChecked(isshowpwd);
        if (isshowpwd) {
            mResetPsdPsd
                    .setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        mckShowPwd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    mResetPsdPsd
                            .setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mResetPsdPsd.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                mckShowPwd.setChecked(isChecked);
            }
        });
		/* 显示明文密码end */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsReceiver);
    }

    /**
     * 匹配短信中间的6个数字（验证码等）
     *
     * @param patternContent
     * @return
     */
    private String patternCode(String patternContent) {
        if (TextUtils.isEmpty(patternContent)) {
            return null;
        }
        Pattern p = Pattern.compile(patternCoder);
        Matcher matcher = p.matcher(patternContent);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_psd_sms_code_request:
                SmsCode();
                time.start();// 开始计时
                break;
            case R.id.reset_psd_ok:

                Log.i(TAG, "修改按钮被点击");
                String psdPhone = mResetPasswordPhone.getText().toString().trim();// 手机号
                String smscode = mResetPsdSmsCode.getText().toString().trim();// 验证码
                String restpsd = mResetPsdPsd.getText().toString().trim();// 修改的密码

                break;
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // SmsCode();
        // time.start();// 开始计时
    }

    /**
     * 获取验证码
     */
    private void SmsCode() {
        WapiUtilEx.SmsCode(phone, new MYCallBack() {
            @Override
            public void onError(int code, String message) {

            }

            @Override
            public void onSuccess(String msg) {
                strContent = msg;
            }
        });
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            mResetPsdSmsRequest.setText("获取验证码");
            mResetPsdSmsRequest.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            mResetPsdSmsRequest.setClickable(false);
            mResetPsdSmsRequest.setText(millisUntilFinished / 1000 + "秒后重发");
        }
    }

    /**
     * 修改密码手机号
     *
     * @author Administrator
     */
    class ResetPsdPhone implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub
            String phone = mResetPasswordPhone.getText().toString();
            boolean isPhone = TAUtils.isMobileNO(phone);
            if (isPhone) {
                mResetPsdSmsRequest.setEnabled(true);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub

        }
    }

    /**
     * 修改密码验证码
     *
     * @author Administrator
     */
    class ResetPsdSmsCode implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = mResetPsdSmsCode.getText().toString();
            if (text != null && !text.equals(""))
                isResetPsdSmsCode = true;
            if (isResetPsdSmsCode && isResetPsdPsd) {
                reset_psd_ok.setEnabled(true);
            }
        }

    }

    /**
     * 修改密码
     *
     * @author Administrator
     */
    class ResetPsdPsd implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = mResetPsdPsd.getText().toString();
            if (text != null && !text.equals(""))
                isResetPsdPsd = true;
            if (isResetPsdSmsCode && isResetPsdPsd) {
                reset_psd_ok.setEnabled(true);
            }
        }
    }

}
