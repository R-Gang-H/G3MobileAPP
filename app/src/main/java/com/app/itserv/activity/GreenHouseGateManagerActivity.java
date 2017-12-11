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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.app.itserv.adapters.GreenHouseGateAdapter;
import com.app.itserv.jparser.JsonGHouseGateObject;
import com.app.itserv.jparser.JsonGHouseGateObject.ObjBean;
import com.app.itserv.views.PullToRefreshListView;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大棚管理---网关管理
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: GreenHouseGateManagerActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-11 下午4:44:05
 */
public class GreenHouseGateManagerActivity extends Activity implements
        OnClickListener, AdapterView.OnItemClickListener {

    public static final String TAG = "GreenHouseGateManagerActivity";

    protected static final int GATE_ERROR = 1;
    protected static final int GATE_SUCCESS = 2;
    protected static final int GATE_VALUES = 3;

    private Context mContext;

    private ImageView ImgBack;
    private PullToRefreshListView gHouGateMngItems;
    public GreenHouseGateAdapter greHouGateAdapter;
    private Button btnGateBind;

    private String greenhouseId;

    private List<ObjBean> gHouseGateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gre_hou_gate_manager_lay);
        mContext = this;
        greenhouseId = (String) getIntent().getExtras().get("greenhouseId");
        initView();
        init();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        gHouGateMngItems = (PullToRefreshListView) findViewById(R.id.gre_hou_gate_manager_items);
        gHouGateMngItems.setOnItemClickListener(this);
        btnGateBind = (Button) findViewById(R.id.btn_gate_bind);
        btnGateBind.setOnClickListener(this);
        gHouGateMngItems.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                init();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (greHouGateAdapter != null) {
            init();
            greHouGateAdapter.notifyDataSetChanged();
        }
    }

    public void init() {
        new Thread(new GHouseGeteRequest()).start();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_gate_bind:// 绑定
                startActivity(new Intent(mContext, GreenHouseGateBindActivity.class)
                        .putExtra("greenhouseId", greenhouseId));
                break;
            // case R.id.tv_disable:// 停用
            // startActivity(new Intent(mContext,
            // GreenHouseGateDisableActivity.class));
            // break;
            // case R.id.tv_enable:// 启用
            // startActivity(new Intent(mContext,
            // GreenHouseGateEnableActivity.class));
            // break;
            default:
                break;
        }
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GATE_ERROR:// 大棚删除请求失败
                    gHouGateMngItems.onRefreshComplete();
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GATE_SUCCESS:
                    String gHouseGate_json = msg.obj.toString();
                    new Thread(new GHouseGateAction(gHouseGate_json)).start();// 大棚网关报文解析线程
                    break;
                case GATE_VALUES:
                    greHouGateAdapter = new GreenHouseGateAdapter(mContext,
                            gHouseGateList);
                    gHouGateMngItems.setAdapter(greHouGateAdapter);
                    gHouGateMngItems.onRefreshComplete();
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, position + "");
        ObjBean gHousegateBean = gHouseGateList.get(position - 1);
        startActivity(new Intent(mContext, GateDetailsActivity.class).putExtra("gHousegateBean", gHousegateBean));
    }

    /**
     * 大棚网关列表
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: GHouseGeteRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-11 上午10:22:22
     */
    class GHouseGeteRequest extends Thread {
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
                map.put("tenantId", currTenantId);

                Log.v(TAG, TAG + "大棚网关请求");
                WapiUtilEx.ghousegatelist(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GATE_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GATE_SUCCESS;
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

    /**
     * 大棚网关json解析
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: GHouseGateAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-11 上午10:35:54
     */
    class GHouseGateAction extends Thread {

        private String gHouseGateJson;

        public GHouseGateAction(String gHouseGate_json) {
            // TODO Auto-generated constructor stub
            this.gHouseGateJson = gHouseGate_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            if (TextUtils.isEmpty(gHouseGateJson)) {
                TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                return;
            }
            Gson gson = new Gson();
            JsonGHouseGateObject jsonGHouseGateObject = gson.fromJson(
                    gHouseGateJson, JsonGHouseGateObject.class);
            if (!jsonGHouseGateObject.equals("")
                    && jsonGHouseGateObject != null) {
                if (jsonGHouseGateObject.isSuccess()) {// 成功
                    gHouseGateList = jsonGHouseGateObject.getObj();
                    Message msg = Message.obtain();
                    msg.what = GATE_VALUES;
                    mHandler.sendMessage(msg);
                }
            }
        }
    }

}
