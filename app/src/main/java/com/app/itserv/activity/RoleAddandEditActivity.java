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
import android.widget.TextView;

import com.app.itserv.jparser.JsonRoleEditObject;
import com.app.itserv.jparser.JsonRoleEditObject.ObjBean;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * @project name：yyshed
 * @type name：RoleAddandEditActivity
 * @description：员工管理---角色管理 新增 编辑
 * @author：gang
 * @date time：2017-6-13 上午9:48:07
 */
public class RoleAddandEditActivity extends Activity implements OnClickListener {

    public static final String TAG = "RoleAddandEditActivity";

    protected static final int ROLEEDIT_ERROR = 1;
    protected static final int ROLEEDIT_SUCCESS = 2;
    public static final int ROLE_VALUE = 3;

    private Context mContext;
    private TextView tvAddEditTitle;
    private ImageView ImgBack;
    private EditText edRoleName, edRoleCode, edRemarks;
    private Button changePswSubmite;

    private String roleaddandedit = null;
    private String roleId, roleName, roleCode;

    private ObjBean objbean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.role_add_edit_lay);
        mContext = this;
        initView();
        roleaddandedit = (String) this.getIntent().getExtras()
                .get("roleaddandedit");
        if (roleaddandedit.equals("roleadd")) {
            tvAddEditTitle.setText("新增角色");
        } else {
            tvAddEditTitle.setText("编辑角色");
            roleId = (String) getIntent().getExtras().get("roleId");
            new Thread(new RoleEditRequest()).start();// 角色管理编辑明细
        }
    }

    private void initView() {
        // TODO Auto-generated method stub
        // 初始化控件
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvAddEditTitle = (TextView) findViewById(R.id.tv_add_edit_role);
        edRoleName = (EditText) findViewById(R.id.role_name);// 角色名称
        edRoleCode = (EditText) findViewById(R.id.role_code);// 角色编码
        edRemarks = (EditText) findViewById(R.id.et_remarks);// 备注
        changePswSubmite = (Button) findViewById(R.id.changepsw_submite);
        changePswSubmite.setOnClickListener(this);
    }

    private boolean Validation() {
        roleName = edRoleName.getText().toString().trim();
        roleCode = edRoleCode.getText().toString().trim();

        if (TextUtils.isEmpty(roleName)) {
            TAUtils.toastMessage((Activity) mContext, "请输入角色名称");
            return false;
        }

        if (TextUtils.isEmpty(roleCode)) {
            TAUtils.toastMessage((Activity) mContext, "请输入角色编码");
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.changepsw_submite:// 提交
                if (Validation()) {
                    new Thread(new RoleAddRequest()).start();
                }
                break;
            default:
                break;
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ROLEEDIT_ERROR:
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case ROLEEDIT_SUCCESS:
                    String role_edit = (String) msg.obj;
                    new Thread(new RoleEditAction(role_edit)).start();// 角色编辑明细解析
                    break;
                case ROLE_VALUE:
                    edRoleName.setText(objbean.getRoleName());// 角色名称
                    edRoleCode.setText(objbean.getRoleCode());// 角色编码
                    edRemarks.setText("");// 备注
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * @project name：yyshed
     * @type name：RoleEditAction
     * @description：角色编辑明细json解析
     * @author：gang
     * @date time：2017-6-13 上午10:17:11
     */
    class RoleEditAction extends Thread {

        private String roleEdit = "";

        public RoleEditAction(String role_edit) {
            // TODO Auto-generated constructor stub
            this.roleEdit = role_edit;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {

                Looper.prepare();

                if (TextUtils.isEmpty(roleEdit)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonRoleEditObject jsonroleedit = gson.fromJson(roleEdit,
                        JsonRoleEditObject.class);
                if (!jsonroleedit.equals("") && jsonroleedit != null) {
                    String msg = jsonroleedit.getMsg();// 消息提醒

                    if (jsonroleedit.isSuccess()) {// 成功
                        objbean = jsonroleedit.getObj();

                        Message mege = Message.obtain();
                        mege.what = ROLE_VALUE;
                        mHandler.sendMessage(mege);
                    }else
                        TAUtils.toastMessage((Activity) mContext, msg);
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
     * @type name：RoleEditRequest
     * @description：角色管理 --- 角色编辑明细
     * @author：gang
     * @date time：2017-6-13 上午10:03:55
     */
    class RoleEditRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();

                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id

                Map<String, String> map = new HashMap<String, String>();
                map.put("currUserId", currUserId);
                map.put("currTenantId", currTenantId);
                map.put("roleId", roleId);

                Log.v(TAG, TAG + "角色编辑明细请求");
                WapiUtilEx.rolegetedit(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = ROLEEDIT_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = ROLEEDIT_SUCCESS;
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
     * @project name：yyshed
     * @type name：RoleAddRequest
     * @description：角色管理---新增
     * @author：gang
     * @date time：2017-6-16 上午8:57:44
     */
    class RoleAddRequest extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

//			String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
            String token = PreferencesUtils.getString(mContext, "token");// token
            String currTenantId = PreferencesUtils.getString(mContext,
                    "tenantId");// 商户id
            String tenantId = currTenantId;

            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("currTenantId", currTenantId);
            map.put("roleName", roleName);
            map.put("roleCode", roleCode);
            map.put("tenantId", tenantId);

            if (roleaddandedit.equals("roleedit")) {// 角色修改
                map.put("roleId", roleId);
            }

            Log.v(TAG, TAG + "角色新增请求");
            WapiUtilEx.roleAdd(map, new MYCallBack() {

                @Override
                public void onError(int code, String message) {
                    // TODO Auto-generated method stub
                    super.onError(code, message);
                    Message msg = Message.obtain();
                    msg.what = ROLEEDIT_ERROR;
                    msg.obj = message;
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onSuccess(String response) {
                    // TODO Auto-generated method stub
                    super.onSuccess(response);
                    Message msg = Message.obtain();
                    msg.what = ROLEEDIT_SUCCESS;
                    msg.obj = response;
                    mHandler.sendMessage(msg);
                }
            });

        }
    }

}
