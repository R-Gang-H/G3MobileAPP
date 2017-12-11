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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.adapters.OrganizationAdapter;
import com.app.itserv.jparser.JsonOrganizationObject;
import com.app.itserv.jparser.JsonOrganizationObject.ObjBean;
import com.app.itserv.views.PullToRefreshListView;
import com.app.itserv.views.PullToRefreshListView.OnRefreshListener;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * @project name：yyshed
 * @type name：OrganizationActivity
 * @description：员工管理---组织机构
 * @author：gang
 * @date time：2017-6-13 下午7:15:57
 */
public class OrganizationActivity extends Activity implements OnRefreshListener, OnClickListener, OnItemClickListener {

    public static final String TAG = "OrganizationActivity";

    private Context mContext;

    protected static final int ORGANIZALIST_ERROR = 1;
    protected static final int ORGANIZALIST_SUCCESS = 2;
    public static final int ORGANIZA_VALUES = 3;

    private ImageView ImgBack;
    private TextView tvOrganiza;
    private Button btAddOrgan;

    public PullToRefreshListView organManagerList;
    public OrganizationAdapter organAdapter;

    private List<ObjBean> organiObjbeanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organization_lay);
        mContext = this;
        init();
        initView();
    }

    public void init() {
        // TODO Auto-generated method stub
        new Thread(new OrganizationRequest()).start();// 组织机构请求线程
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        organManagerList = (PullToRefreshListView) findViewById(R.id.organ_items);
        organManagerList.setOnItemClickListener(this);
        organManagerList.setOnRefreshListener(this);
        btAddOrgan = (Button) findViewById(R.id.add_organiza);
        btAddOrgan.setOnClickListener(this);
        tvOrganiza = (TextView) findViewById(R.id.tv_organiza);// 组织机构数
    }


    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        init();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.add_organiza:// 新增机构
                startActivity(new Intent(mContext, OrganizaAddandEditActivity.class)
                        .putExtra("organaddandedit", "organadd"));
                break;
            default:
                break;
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ORGANIZALIST_ERROR:
                    organManagerList.onRefreshComplete();
                    TAUtils.toastMessage((Activity) mContext, (String) msg.obj);
                    break;
                case ORGANIZALIST_SUCCESS:
                    String organiza_json = (String) msg.obj;
                    new Thread(new OrganizaAction(organiza_json)).start();// 组织机构json解析线程
                    break;
                case ORGANIZA_VALUES:
                    tvOrganiza.setText(String.format("组织机构（%d）",
                            organiObjbeanList.size()));// 组织机构数
                    organAdapter = new OrganizationAdapter(mContext,
                            organiObjbeanList);
                    organManagerList.setAdapter(organAdapter);
                    organManagerList.onRefreshComplete();
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i(TAG, TAG + "onResume");
        init();
    }

    /**
     * @project name：yyshed
     * @type name：OrganizaAction
     * @description：组织机构json解析线程
     * @author：gang
     * @date time：2017-6-13 下午7:49:43
     */
    class OrganizaAction extends Thread {

        private String organizajson;

        public OrganizaAction(String organiza_json) {
            // TODO Auto-generated constructor stub
            this.organizajson = organiza_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            if (TextUtils.isEmpty(organizajson)) {
                TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                return;
            }

            Gson gson = new Gson();
            JsonOrganizationObject jsonOrgani = gson.fromJson(organizajson,
                    JsonOrganizationObject.class);
            if (!jsonOrgani.equals("") && jsonOrgani != null) {
                String msg = jsonOrgani.getMsg();// 消息提醒

                if (jsonOrgani.isSuccess()) {// 成功
                    organiObjbeanList = jsonOrgani.getObj();

                    Message mege = Message.obtain();
                    mege.what = ORGANIZA_VALUES;
                    mHandler.sendMessage(mege);
                }else
                    TAUtils.toastMessage((Activity) mContext, msg);
            }
        }
    }

    /**
     * @project name：yyshed
     * @type name：OrganizationRequest
     * @description：组织机构请求线程
     * @author：gang
     * @date time：2017-6-13 下午7:33:18
     */
    class OrganizationRequest extends Thread {

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

                Log.v(TAG, TAG + "组织机构列表请求");
                WapiUtilEx.doorganiza(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = ORGANIZALIST_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = ORGANIZALIST_SUCCESS;
                        msg.obj = response;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        String departId = organiObjbeanList.get(position - 1).getId();
        mContext.startActivity(new Intent(mContext,
                OrganizaAddandEditActivity.class).putExtra("organaddandedit",
                "organedit").putExtra("departId", departId));// 编辑修改机构
    }

}
