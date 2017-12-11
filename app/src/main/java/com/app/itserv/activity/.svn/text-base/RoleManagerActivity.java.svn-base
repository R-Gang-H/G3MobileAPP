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

import com.app.itserv.adapters.RoleManagerAdapter;
import com.app.itserv.jparser.JsonRoleGetListObject;
import com.app.itserv.jparser.JsonRoleGetListObject.AttributesBean;
import com.app.itserv.jparser.JsonRoleGetListObject.ObjBean;
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
 * @type name：RoleManagerActivity
 * @description：员工管理---角色管理
 * @author：gang
 * @date time：2017-6-10 上午10:29:03
 */
public class RoleManagerActivity extends Activity implements OnRefreshListener, OnClickListener, OnItemClickListener {

    private static final String TAG = "RoleManagerActivity";
    protected static final int ROLELIST_ERROR = 0;
    protected static final int ROLELIST_SUCCESS = 1;
    public static final int ROLELIST_ADAPTER = 2;

    private Context mContext;
    private ImageView ImgBack;

    public PullToRefreshListView roleManagerList;
    public RoleManagerAdapter roleAdapter;
    private Button btAddRole;
    private TextView tvRoleMng;

    private List<ObjBean> objbeanRoleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.role_manager_lay);
        mContext = this;
        init();
        initView();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ROLELIST_ERROR:
                    roleManagerList.onRefreshComplete();
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case ROLELIST_SUCCESS:
                    String rolelist_json = (String) msg.obj;
                    new Thread(new RoleManagerListAction(rolelist_json)).start();
                    break;
                case ROLELIST_ADAPTER:
                    tvRoleMng.setText(String.format("角色管理（%d）人",
                            objbeanRoleList.size()));
                    roleAdapter = new RoleManagerAdapter(mContext, objbeanRoleList);
                    roleManagerList.setAdapter(roleAdapter);
                    roleManagerList.onRefreshComplete();
                    break;
                default:
                    break;
            }
        }

    };

    public void init() {
        // TODO Auto-generated method stub
        new Thread(new RoleManagerRequest()).start();// 角色管理列表请求
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvRoleMng = (TextView) findViewById(R.id.tv_role_mng);
        roleManagerList = (PullToRefreshListView) findViewById(R.id.role_manager_items);
        roleManagerList.setOnItemClickListener(this);
        roleManagerList.setOnRefreshListener(this);
        btAddRole = (Button) findViewById(R.id.add_role);
        btAddRole.setOnClickListener(this);

    }

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        new Thread(new RoleManagerRequest()).start();// 角色管理列表请求
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.add_role:// 新增角色
                startActivity(new Intent(mContext, RoleAddandEditActivity.class)
                        .putExtra("roleaddandedit", "roleadd"));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i(TAG, TAG + "onResume");
        init();
    }

    /**
     * @project name：yyshed
     * @type name：RoleManagerListAction
     * @description：角色管理列表解析线程
     * @author：gang
     * @date time：2017-6-10 上午10:43:15
     */
    class RoleManagerListAction extends Thread {

        private String rolelistjson;

        public RoleManagerListAction(String rolelist_json) {
            // TODO Auto-generated constructor stub
            this.rolelistjson = rolelist_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();

                if (TextUtils.isEmpty(rolelistjson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonRoleGetListObject rolegetlistjson = gson.fromJson(
                        rolelistjson, JsonRoleGetListObject.class);

                if (!rolegetlistjson.equals("") && rolegetlistjson != null) {
                    String msg = rolegetlistjson.getMsg();// 消息提醒

                    if (!rolegetlistjson.isSuccess()) {// 请求失败
                        TAUtils.toastMessage((Activity) mContext, msg);
                        return;
                    }


                    objbeanRoleList = rolegetlistjson.getObj();

                    // 加载适配器
                    Message msge = Message.obtain();
                    msge.what = ROLELIST_ADAPTER;
                    mHandler.sendMessage(msge);
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
     * @project name：yyshed
     * @type name：RoleManagerRequest
     * @description：角色管理列表请求线程
     * @author：gang
     * @date time：2017-6-10 上午10:34:56
     */
    class RoleManagerRequest extends Thread {

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

                Log.v(TAG, TAG + "角色列表请求");
                WapiUtilEx.rolegetlist(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = ROLELIST_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = ROLELIST_SUCCESS;
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
    public void onItemClick(AdapterView<?> prent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        String roleid = objbeanRoleList.get(position - 1).getId();// 角色id
        startActivity(new Intent(mContext, RoleAddandEditActivity.class)
                .putExtra("roleaddandedit", "roleedit").putExtra("roleId",
                        roleid));// 角色修改

    }
}
