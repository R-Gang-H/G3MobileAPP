package com.app.itserv.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;

import com.app.itserv.adapters.GreenHouseEmployeAdapter;
import com.app.itserv.jparser.JsonGreHouEmpObject;
import com.app.itserv.jparser.JsonGreHouEmpObject.ObjBean;
import com.app.itserv.views.PullToRefreshListView;
import com.app.itserv.views.PullToRefreshListView.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 大棚管理---员工管理
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: GreenHouseEmployeeManagerActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017年08月28日14:04:36
 */
public class GreenHouseEmployeeManagerActivity extends Activity implements
        OnClickListener, OnRefreshListener {

    private Context mContext;

    public static final String TAG = "GreenHouseEmployeeManagerActivity";

    protected static final int GHOUSEEMP_ERROR = 1;
    protected static final int GHOUSEEMP_SUCCESS = 2;
    protected static final int GHOUSEEMP_VALUES = 3;

    private ImageView ImgBack;
    private Button btnDistri;
    public PullToRefreshListView greHouEmplayeList;
    public GreenHouseEmployeAdapter greHouEmpAdapter;

    private List<ObjBean> gHouseUserEmpList;
    private String greenhouseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gre_hou_emp_manager_lay);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        greenhouseId = (String) bundle.get("greenhouseId");
        initView();
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        new Thread(new GreHouEmpRequest()).start();// 大棚员工列表请求线程
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        btnDistri = (Button) findViewById(R.id.btn_distri);
        btnDistri.setOnClickListener(this);
        greHouEmplayeList = (PullToRefreshListView) findViewById(R.id.gre_hou_emp_manager_items);
        // greHouEmpAdapter = new
        // GreenHouseEmployeAdapter(mContext,gHouseUserEmpList);
        // greHouEmplayeList.setAdapter(greHouEmpAdapter);
        greHouEmplayeList.setOnRefreshListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_distri:// 分配
                GreenHouseDistriManagerActivity.isBindUserId.clear();
                startActivity(new Intent(mContext,
                        GreenHouseDistriManagerActivity.class).putExtra(
                        "greenhouseId", greenhouseId));
                break;
            default:
                break;
        }
    }

    /**
     * 大棚员工请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseEmpAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午5:08:08
     */
    class GreenHouseEmpAction extends Thread {

        private String gHouseEmpJson;

        public GreenHouseEmpAction(String gHouseEmp_json) {
            // TODO Auto-generated constructor stub
            this.gHouseEmpJson = gHouseEmp_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(gHouseEmpJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonGreHouEmpObject gHouse_user_emp = gson.fromJson(
                        gHouseEmpJson, JsonGreHouEmpObject.class);

                if (!gHouse_user_emp.equals("") && gHouse_user_emp != null) {
                    if (gHouse_user_emp.isSuccess()) {// 成功

                        gHouseUserEmpList = gHouse_user_emp.getObj();

                        Message msg = Message.obtain();
                        msg.what = GHOUSEEMP_VALUES;
                        mHandler.sendMessage(msg);
                    }
                }
            } catch (JsonSyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GHOUSEEMP_ERROR:// 大棚用户列表请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    greHouEmplayeList.onRefreshComplete();
                    break;
                case GHOUSEEMP_SUCCESS:
                    String gHouseEmp_json = msg.obj.toString();
                    new Thread(new GreenHouseEmpAction(gHouseEmp_json)).start();// 大棚员工报文解析线程
                    break;
                case GHOUSEEMP_VALUES:
                    greHouEmpAdapter = new GreenHouseEmployeAdapter(mContext,
                            gHouseUserEmpList);
                    greHouEmplayeList.setAdapter(greHouEmpAdapter);
                    greHouEmpAdapter.notifyDataSetChanged();
                    greHouEmplayeList.onRefreshComplete();
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 大棚员工列表
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreHouEmpRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午5:04:13
     */
    class GreHouEmpRequest extends Thread {

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
                map.put("greenhouseId", greenhouseId);

                Log.v(TAG, TAG + "大棚员工请求");
                WapiUtilEx.ghouseemp(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEEMP_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEEMP_SUCCESS;
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

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        init();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        init();
    }
}
