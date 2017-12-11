package com.app.itserv.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.app.itserv.BaseActivity;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.WapiUtil;

public class AddAlarmNumberActivity extends BaseActivity implements
        OnClickListener {
    private Context mContext;
    private final static int SCANNIN_GREQUEST_CODE = 1;
    EditText deviceName;
    EditText deviceNickname;
    private static String TAG = "AddDeviceAcitivity";

    @Override
    protected int layoutViewId() {
        return R.layout.add_alarm_num_lay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        findViewById(R.id.add_ok).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        deviceName = (EditText) findViewById(R.id.device_name);
        deviceNickname = (EditText) findViewById(R.id.device_nickname);

    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String msgObj = (String) msg.obj;
                    try {
                        String result = "";
                        JSONObject jsonObject = new JSONObject(msgObj);
                        if (jsonObject.has("err"))
                            result = jsonObject.getString("err");
                        else if (jsonObject.has("message")) {
                            result = jsonObject.getString("message");
                            Intent data = new Intent();
                            setResult(1, data);
                            // 关闭掉这个Activity
                            finish();
                        }

                        ToastUtils.makeTextShort(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
        }

    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_ok:
                String devUuid = deviceName.getText().toString();
                String alias = deviceNickname.getText().toString();
                if (alias != null && !devUuid.equals("") && !alias.equals(""))
                    // addDevice();
                    if (checkPhoneNumber(alias)) {
                        new AddContact().execute();

                    } else {
                        ToastUtils.makeTextShort("请确认电话号码填写正确！");
                    }

                else {
                    ToastUtils.makeTextShort("请确认设备名和设备别名填写正确！");
                }
                break;
            case R.id.cancel:
                Intent data = new Intent();
                setResult(2, data);
                // 关闭掉这个Activity
                finish();
                break;
            default:
                break;
        }
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^1[0-9]{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    private class AddContact extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object... arg0) {
            String devUuid = deviceName.getText().toString();
            String alias = deviceNickname.getText().toString();
            String msg = WapiUtil.addContact(devUuid, alias);
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            if (msg != null) {
                Message msg1 = new Message();
                msg1.obj = msg;
                msg1.what = 1;
                mHandler.sendMessage(msg1);
            } else
                Log.e("add device", "null");
        }

    }

}
