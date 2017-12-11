package com.app.itserv.activity;

import java.util.HashMap;
import java.util.List;
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
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.adapters.OrganizaUserAdapter;
import com.app.itserv.adapters.RoleUserOrgAdapter;
import com.app.itserv.jparser.JsonOrganizaUserRoleObject;
import com.app.itserv.jparser.JsonUserRoleOrgObject;
import com.app.itserv.jparser.JsonUserRoleOrgObject.AttributesBean;
import com.app.itserv.jparser.JsonUserRoleOrgObject.ObjBean;
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
 * @project name：yyshed
 * @type name：UserRoleOrganizaActivity
 * @description：员工管理---组织机构---查看用户
 * @author：gang
 * @date time：2017-6-14 上午9:24:52
 */
public class UserRoleOrganizaActivity extends Activity implements OnClickListener, OnRefreshListener {

    public static final String TAG = "UserRoleOrganizaActivity";

    protected static final int USERORGLIST_ERROR = 1;
    protected static final int USERORGLIST_SUCCESS = 2;
    public static final int USERORGLIST_VALUES = 3;
    protected static final int ORGANIZA_VALUES = 4;

    private Context mContext;
    private ImageView ImgBack;
    private TextView tvGreenhouseNum;

    private PullToRefreshListView userManagerItem;
    private RoleUserOrgAdapter roleUserOrgAdapter;// 角色管理---用户查看
    private OrganizaUserAdapter organizaUserAdapter;// 组织机构---用户查看

    private List<ObjBean> userorgObjBean;// 角色列表---查看用户
    private List<JsonOrganizaUserRoleObject.ObjBean> orguserObjBean;// 组织机构---查看用户

    private String roleUserorganiza = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_role_organiza_lay);
        mContext = this;
        roleUserorganiza = (String) this.getIntent().getExtras()
                .get("RoleOrganizaUser");
        init();
        initView();
    }

    private void init() {
        // TODO Auto-generated method stub
        new Thread(new UserRoleOrgRequest()).start();// 角色管理---查看用户请求线程|机构管理---查看用户请求线程
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvGreenhouseNum = (TextView) findViewById(R.id.tv_greenhouse_num);
        userManagerItem = (PullToRefreshListView) findViewById(R.id.user_manager_items);
        userManagerItem.setOnRefreshListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            default:
                break;
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case USERORGLIST_ERROR:
                    userManagerItem.onRefreshComplete();
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case USERORGLIST_SUCCESS:
                    String userroleorg_json = (String) msg.obj;
                    new Thread(new UserRoleOrgAction(userroleorg_json)).start();// 查看用户json解析线程
                    break;
                case USERORGLIST_VALUES:// 角色管理---用户查看
                    tvGreenhouseNum.setText(String.format("用户:%d个",
                            userorgObjBean.size()));// 用户大小
                    roleUserOrgAdapter = new RoleUserOrgAdapter(mContext,
                            userorgObjBean);
                    userManagerItem.setAdapter(roleUserOrgAdapter);
                    userManagerItem.onRefreshComplete();
                    break;
                case ORGANIZA_VALUES:// 组织机构---用户查看
                    tvGreenhouseNum.setText(String.format("用户:%d个",
                            orguserObjBean.size()));// 用户大小
                    organizaUserAdapter = new OrganizaUserAdapter(mContext,
                            orguserObjBean);
                    userManagerItem.setAdapter(organizaUserAdapter);
                    userManagerItem.onRefreshComplete();
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * @project name：yyshed
     * @type name：UserRoleOrgAction
     * @description：查看用户json解析线程
     * @author：gang
     * @date time：2017-6-13 下午12:18:38
     */
    class UserRoleOrgAction extends Thread {

        private String userorgjson = "";

        public UserRoleOrgAction(String userroleorg_json) {
            // TODO Auto-generated constructor stub
            this.userorgjson = userroleorg_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();
                if (TextUtils.isEmpty(userorgjson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();

                if (roleUserorganiza.equals("RoleUser")) {// 角色管理---查看用户

                    JsonUserRoleOrgObject jsonuserroleorg = gson.fromJson(
                            userorgjson, JsonUserRoleOrgObject.class);
                    if (!jsonuserroleorg.equals("") && jsonuserroleorg != null) {
                        String msg = jsonuserroleorg.getMsg();// 消息提醒

                        if (jsonuserroleorg.isSuccess()) {// 成功
                            AttributesBean userorgattribute = jsonuserroleorg
                                    .getAttributes();
                            userorgattribute.getCurrUserId();
                            userorgattribute.getCurrTenantId();
                            userorgattribute.getUserSize();

                            userorgObjBean = jsonuserroleorg.getObj();// 角色管理---用户列表

                            Message mege = Message.obtain();
                            mege.what = USERORGLIST_VALUES;
                            mHandler.sendMessage(mege);
                        }else
                            TAUtils.toastMessage((Activity) mContext, msg);
                    }
                } else {
                    JsonOrganizaUserRoleObject jsonorguserObj = gson.fromJson(
                            userorgjson, JsonOrganizaUserRoleObject.class);
                    if (!jsonorguserObj.equals("") && jsonorguserObj != null) {
                        String msg = jsonorguserObj.getMsg();// 消息提醒

                        if (jsonorguserObj.isSuccess()) {// 成功
                            JsonOrganizaUserRoleObject.AttributesBean orguserattribute = jsonorguserObj
                                    .getAttributes();
                            orguserattribute.getCurrUserId();
                            orguserattribute.getCurrTenantId();
                            orguserattribute.getUserSize();

                            orguserObjBean = jsonorguserObj.getObj();// 组织机构---用户列表

                            Message mege = Message.obtain();
                            mege.what = ORGANIZA_VALUES;
                            mHandler.sendMessage(mege);
                        }else
                            TAUtils.toastMessage((Activity) mContext, msg);
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

    /**
     * @project name：yyshed
     * @type name：UserRoleOrgRequest
     * @description：查看用户请求线程
     * @author：gang
     * @date time：2017-6-13 上午11:58:06
     */
    class UserRoleOrgRequest extends Thread {

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

                if (roleUserorganiza.equals("RoleUser")) {
                    String roleId = "402883fd5c8fd7be015c8fdfa8380008";
                    map.put("roleId", roleId);
                } else {
                    String departId = "402883fd5c8fd7be015c8fdeed4b0006";
                    map.put("departId", departId);
                }

                WapiUtilEx.roleManagOrguser(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = USERORGLIST_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = USERORGLIST_SUCCESS;
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

}
