package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.app.commons.ToUIEvent;
import com.app.itserv.jparser.JsonBaseDelObject;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.RegexChk;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * 大棚管理---网关管理---绑定
 *
 * @author Administrator
 */
public class GreenHouseGateBindActivity extends Activity implements
        OnClickListener {

    private static final String TAG = "GreenHouseGateBindActivity";

    protected static final int GATEBIND_ERROR = 1;
    protected static final int GATEBIND_SUCCESS = 2;
    private final static int SCANNIN_GREQUEST_CODE = 4;

    private Context mContext;

    private ImageView ImgBack;
    private TextView tvSnUnlawful, tvLawful;
    private Button btnComBind, btValedate, btnDatamigra, btnGateReset;
    private EditText etSgateCode;

    private String greenhouseId, gateCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gre_hou_gate_bind_lay);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        greenhouseId = bundle.getString("greenhouseId");
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);// 返回
        ImgBack.setOnClickListener(this);
        etSgateCode = (EditText) findViewById(R.id.et_sgate_code);// 网关编号
        btValedate = (Button) findViewById(R.id.bt_valedate);// 网关校验
        btValedate.setOnClickListener(this);
        findViewById(R.id.zxing).setOnClickListener(this);
        tvSnUnlawful = (TextView) findViewById(R.id.tv_sn_unlawful);// sn非法
        tvLawful = (TextView) findViewById(R.id.tv_lawful);// sn合法
        btnDatamigra = (Button) findViewById(R.id.btn_datamigra);//二代数据迁移
        btnDatamigra.setOnClickListener(this);
        btnComBind = (Button) findViewById(R.id.btn_comfirm_bind);// 确定绑定
        btnComBind.setOnClickListener(this);
        btnGateReset = (Button) findViewById(R.id.btn_gate_reset);// 重置
        btnGateReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.bt_valedate:// 校验
                if (valedate()) {
                    if (RegexChk.checkGateCode(gateCode)) {
                        tvSnUnlawful.setText("校验失败:SN号非法");
                        tvSnUnlawful.setVisibility(View.VISIBLE);
                        tvLawful.setVisibility(View.GONE);
                    } else {
                        tvLawful.setText("校验成功:SN号合法");
                        tvSnUnlawful.setVisibility(View.GONE);
                        tvLawful.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.zxing:
                Intent intent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                break;
            case R.id.btn_datamigra:
                startActivity(new Intent(mContext, MigrationDataActivity.class).putExtra("greenhouseId", greenhouseId));//二代数据迁移界面
                break;
            case R.id.btn_comfirm_bind:// 确认绑定
                if (valedate()) {
                    new Thread(new GHouseGeteBindRequest()).start();
                }
                break;
            case R.id.btn_gate_reset:// 重置
                etSgateCode.setText("");
                break;
            default:
                break;
        }
    }

    private boolean valedate() {
        gateCode = etSgateCode.getText().toString().trim();
        if (TextUtils.isEmpty(gateCode)) {
            TAUtils.toastMessage((Activity) mContext, "网关号不能为空!");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String result = bundle.getString("result");
                    etSgateCode.setText(result);
                }
                break;
        }
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GATEBIND_ERROR:// 大棚网关绑定请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GATEBIND_SUCCESS:
                    String gHouseGatebind_json = msg.obj.toString();
                    GHouseGateBindAction(gHouseGatebind_json);// 大棚网关绑定报文解析线程
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 大棚网关绑定请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: GHouseGeteBindRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-12 下午4:53:21
     */
    class GHouseGeteBindRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

//                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("ghouseId", greenhouseId);
                map.put("sgateSN", gateCode);

                WapiUtilEx.ghousegatebind(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GATEBIND_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GATEBIND_SUCCESS;
                        msg.obj = response;
                        mHandler.sendMessage(msg);

                        EventBus.getDefault().post(new ToUIEvent(ToUIEvent.UPDATE_MONITORING_INTERFACE, null));
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

    /**
     * 大棚网关绑定解析
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: GHouseGateBindAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-12 下午5:03:02
     */
    public void GHouseGateBindAction(String gHouseGatebind_json) {


        if (TextUtils.isEmpty(gHouseGatebind_json)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonBaseDelObject jsonGHouseGateObject = gson.fromJson(
                gHouseGatebind_json, JsonBaseDelObject.class);
        if (!jsonGHouseGateObject.equals("")
                && jsonGHouseGateObject != null) {
            if (jsonGHouseGateObject.isSuccess()) {// 成功
                tvSnUnlawful.setVisibility(View.GONE);
                tvLawful.setVisibility(View.VISIBLE);
            }
            ToastUtils.makeTextShort(jsonGHouseGateObject.getMsg());
        }
    }
}
