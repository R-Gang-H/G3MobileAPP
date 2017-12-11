package com.app.itserv.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.itserv.BaseActivity;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

@SuppressLint("HandlerLeak")
public class RegisterActivity extends BaseActivity implements
        View.OnClickListener, TextWatcher {

    /* 原Register注册公共变量start */
    private Context mContext;// 上下文
    private View mBackView;// 返回
    private TextView mTitleTv;// 标题
    /* 原Register注册功能变量end */

    private String registerMsg, userName, phoneNum, sms_code, psdOne, psdTwo;// 注册用户所需变量
    private EditText mUserName, mPhone, mRegAddress, mPsdOne, mPsdTwo,
            mSmsCode;
    private Button mRegister;
    private static String TAG = "RegisterSettingPsdActivity";

    private BroadcastReceiver smsReceiver;
    private IntentFilter filter2;
    private Handler handler;
    private String patternCoder;// 短息验证正则表达式
    private Button mSmsCodeRequest;
    private TimeCount time;// 倒计时

    /* 省市县三级联动start */
    protected static final int REQUEST_CODE = 1;
    protected static final int PER_EXCUTE = 2;

	/* 省市县三级联动end */

    @Override
    protected int layoutViewId() {
        return R.layout.register_lay;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initViews();
        // String paramstr = String.format("token=%s&sublist=%s", imei,
        // curSublist);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                mSmsCode.setText(sms_code);
            }
        };
        filter2 = new IntentFilter();
        filter2.addAction("android.provider.Telephony.SMS_RECEIVED");
        filter2.setPriority(Integer.MAX_VALUE);
        smsReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                // TODO Auto-generated method stub
                Object[] objs = (Object[]) intent.getExtras().get("pdus");
                for (Object obj : objs) {
                    byte[] pdu = (byte[]) obj;
                    SmsMessage sms = SmsMessage.createFromPdu(pdu);
                    // 短息的内容
                    String message = sms.getMessageBody();
                    Log.d(TAG, "message   " + message);
                    // 短息的手机号。。。+86开头？
                    String from = sms.getOriginatingAddress();
                    Log.d(TAG, "from	" + from);
                    if (!TextUtils.isEmpty(from)) {
                        String code = patternCode(message);
                        if (!TextUtils.isEmpty(code)) {
                            sms_code = code;// 验证码
                            handler.sendEmptyMessage(1);
                        }
                    }
                }
            }
        };
        registerReceiver(smsReceiver, filter2);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(smsReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                if (data != null) {
                    String mCurrentProviceName = data
                            .getStringExtra("mCurrentProviceName");// 省
                    String mCurrentCityName = data
                            .getStringExtra("mCurrentCityName");// 市
                    String mCurrentDistrictName = data
                            .getStringExtra("mCurrentDistrictName");// 县
                    String mCurrentXipCode = data
                            .getStringExtra("mCurrentXipCode");// 邮编
                    mRegAddress.setText(mCurrentProviceName + mCurrentCityName
                            + mCurrentDistrictName);
                }
            }
        }
    }

    /**
     * 匹配短息中间的6个数字（验证码等）
     *
     * @param message
     * @return
     */
    protected String patternCode(String message) {
        // TODO Auto-generated method stub
        if (TextUtils.isEmpty(message)) {
            return null;
        }
        Pattern p = Pattern.compile(patternCoder);
        Matcher matcher = p.matcher(message);
        if (matcher.find()) {// 尝试查找与该模式匹配的输入序列的下一个子序列
            return matcher.group();// 返回匹配到的子字符串
        }
        return null;
    }

    private void initViews() {
        mBackView = findViewById(R.id.btn_back);
        mBackView.setOnClickListener(this);
        mTitleTv = (TextView) findViewById(R.id.title_txt);
        mTitleTv.setText(this.getResources().getString(R.string.register_title));
        mUserName = (EditText) findViewById(R.id.register_username);
        mPhone = (EditText) findViewById(R.id.phone);
        mPhone.addTextChangedListener(this);
        mRegAddress = (EditText) findViewById(R.id.register_address);
        mRegAddress.setOnClickListener(this);
        mPsdOne = (EditText) findViewById(R.id.register_psd_one);
        mPsdTwo = (EditText) findViewById(R.id.register_psd_two);
        mSmsCode = (EditText) findViewById(R.id.sms_code);
        mSmsCodeRequest = (Button) findViewById(R.id.sms_code_request);
        mSmsCodeRequest.setOnClickListener(this);
        mSmsCodeRequest.setEnabled(false);
        time = new TimeCount(60000, 1000);// 开始倒计总时间,1秒执行一次
        mRegister = (Button) findViewById(R.id.register_ok);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:// 返回
                finish();
                break;
            case R.id.sms_code_request:
                SmsCode();
                time.start();// 开始计时
                break;
            case R.id.register_address:// 选择地址
                startActivityForResult(new Intent(mContext,
                        AddressLinkageActivity.class), REQUEST_CODE);
                break;
            case R.id.register_ok:// 注册
                mGetTextValue();
                if (userName == null || userName.equals("")) {
                    ToastUtils.makeTextShort("请输入用户名");
                } else if (phoneNum == null || phoneNum.equals("")) {
                    ToastUtils.makeTextShort("电话号码为空或者有误");
                } else if (psdOne == null || psdTwo == null || psdOne.equals("")
                        || psdTwo.equals("")) {
                    ToastUtils.makeTextShort("密码不能为空");
                } else if (sms_code == null || sms_code.equals("")) {
                    ToastUtils.makeTextShort("请输入验证码");
                } else {
                    if (psdOne.equals(psdTwo)) {
                        Register();
                    } else {
                        ToastUtils.makeTextShort("两次密码不一致");
                    }
                }
                break;
            default:
                break;
        }
    }

    private void SmsCode() {
        // TODO Auto-generated method stub
        WapiUtilEx.SmsCode(phoneNum, new MYCallBack() {

            @Override
            public void onError(int code, String message) {
                // TODO Auto-generated method stub
                super.onError(code, message);
            }

            @Override
            public void onSuccess(String response) {
                // TODO Auto-generated method stub
                super.onSuccess(response);
            }

        });
    }

    private void mGetTextValue() {
        phoneNum = mPhone.getText().toString().trim();
        userName = mUserName.getText().toString().trim();
        psdOne = mPsdOne.getText().toString().trim();
        psdTwo = mPsdTwo.getText().toString().trim();
        sms_code = mSmsCode.getText().toString();
    }

    private void Register() {

        mGetTextValue();

        WapiUtilEx.Register(userName, psdTwo, phoneNum, sms_code,
                new MYCallBack() {
                    @Override
                    public void onError(int code, String message) {
                        Log.e("onError " + code, message);
                    }

                    @Override
                    public void onSuccess(String msg) {
                        if (parseRegisterCallback(msg)) {
                            mHandler.sendEmptyMessage(1);
                        } else {
                            mHandler.sendEmptyMessage(2);
                        }
                    }
                });
    }

    private boolean parseRegisterCallback(String content) {
        boolean successed = false;
        try {
            if (content != null && !content.equals("")) {
                JSONObject jsonObject = new JSONObject(content);
                if (jsonObject.getString("result").equals("OK")) {
                    mLoginAndRegister.username = jsonObject
                            .getString("username");
                    mLoginAndRegister.session_token = jsonObject
                            .getString("session_token");
                    Log.v("sessiontoken", mLoginAndRegister.session_token);
                    successed = true;
                } else {
                    registerMsg = jsonObject.getString("msg");
                    successed = false;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return successed;
    }

    private LoginAndRegister mLoginAndRegister = new LoginAndRegister();
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (!mLoginAndRegister.session_token.equals("")
                            && mLoginAndRegister.session_token != null) {
                        ToastUtils.makeTextShort("注册成功，请登录！");
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent);
                        finish();
                    }
                    break;
                case 2:
                    ToastUtils.makeTextShort(registerMsg);
                    break;
                default:
                    break;
            }
        }

    };

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            mSmsCodeRequest.setClickable(false);
            mSmsCodeRequest.setText(millisUntilFinished / 1000 + "秒后重发");
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            mSmsCodeRequest.setText("重新验证");
            mSmsCodeRequest.setClickable(true);
        }

    }

    @Override
    public void afterTextChanged(Editable arg0) {
        // TODO Auto-generated method stub
        String phone = mPhone.getText().toString().trim();
        boolean isPhone = TAUtils.isMobileNO(phone);
        if (isPhone) {
            mSmsCodeRequest.setEnabled(true);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // TODO Auto-generated method stub

    }
}
