package com.app.itserv.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.itserv.jparser.JsonGHouseGateObject.ObjBean;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 大棚管理---网管管理---解绑
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: GreenHouseGateUnbindActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-11 下午3:09:21
 */
public class GreenHouseGateUnbindActivity extends Activity implements
        OnClickListener {

    public static final String TAG = "GreenHouseGateUnbindActivity";

    protected static final int GATEUNBIND_ERROR = 1;
    protected static final int GATEUNBIND_SUCCESS = 2;
    protected static final int GATEUNBIND_VALUES = 3;

    private Context mContext;

    private ImageView ImgBack;
    private EditText etPicture;
    private Button btnComUnBind;

    private ObjBean gHouGateBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gre_hou_gate_unbind_lay);
        mContext = this;
        initView();
        initData();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);// 返回
        ImgBack.setOnClickListener(this);
        etPicture = (EditText) findViewById(R.id.et_picture);// sn号
        etPicture.setText(gHouGateBean.getSn().toString().trim());
        btnComUnBind = (Button) findViewById(R.id.btn_confirm_unbind);// 确定解绑
        btnComUnBind.setOnClickListener(this);
    }

    private void initData() {
        // TODO Auto-generated method stub
        Bundle bundle = getIntent().getExtras();
        gHouGateBean = (ObjBean) bundle.getSerializable("gHouGateBean");
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_confirm_unbind:// 解绑
                String gate_SN = etPicture.getText().toString().trim();
                if (TextUtils.isEmpty(gate_SN)) {
                    TAUtils.toastMessage((Activity) mContext, "请输入SN号");
                    break;
                }
                new Thread(new GHouGateUnbindRequest()).start();
                break;
            default:
                break;
        }
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GATEUNBIND_ERROR:// 大棚删除请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GATEUNBIND_SUCCESS:
                    String gHouseGate_json = msg.obj.toString();
                    // new Thread(new
                    // GHouseGateUbindAction(gHouseGate_json)).start();//
                    // 大棚网关解绑报文解析线程
                    break;
                case GATEUNBIND_VALUES:
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 解绑网关请求
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: GHouGateUnbindRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-11 下午3:59:34
     */
    class GHouGateUnbindRequest extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

//				String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("ghouseId", gHouGateBean.getGhouseid());
                map.put("sgateId", gHouGateBean.getId());

                Log.v(TAG, TAG + "大棚网关解绑请求");
                WapiUtilEx.ghousegatelist(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GATEUNBIND_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GATEUNBIND_SUCCESS;
                        msg.obj = response;
                        mHandler.sendMessage(msg);
                    }

                });

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }

}
