package com.app.itserv.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.itserv.ActivityCollector;
import com.app.itserv.adapters.RoleSpinnerAdapter;
import com.app.itserv.jparser.JsonOrganizationObject;
import com.app.itserv.jparser.JsonRoleGetListObject;
import com.app.itserv.jparser.JsonUserEditObject;
import com.app.itserv.jparser.JsonUserEditObject.AttributesBean;
import com.app.itserv.jparser.JsonUserEditObject.ObjBean;
import com.itserv.app.util.DateTimePickDialogUtil;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * @project name：yyshed
 * @type name：ConverSationsActivity
 * @description：我的账户
 * @author：gang
 * @date time：2017-6-3 下午7:50:00
 */
public class ConverSationsActivity extends Activity implements OnClickListener,
        OnItemSelectedListener {

    private final String TAG = getClass().getName();

    private static final int CONVER_ERROR = 1;
    private static final int CONVER_SUCCESS = 2;
    private static final int CONVER_VALUES = 3;
    private static final int USEREDIT_SUCCESS = 4;

    private Context mContext;
    private Button mChangePsd, mSubmitUser;
    private ImageView ImgBack;

    /* 性别选择start */
    private TextView mSex;
    private final String[] sex = {"男", "女"};
    private SharedPreferences sp = null;
    private Editor editor;
    private int index = 0;
    /* 性别选择end */
    /* 生日start */
    private EditText mBirthday;
    private String initDateTime;
    /* 生日end */

    private String userName;
    private String realName;
    private String phone;
    private String email;
    private String roleId;
    private String orgId;

    private ObjBean conversationObj;

    // 组织机构
    private static final int ORGTYPE_SUCCESS = 6;
    private static final int ORGTYPE_VALUES = 7;

    private Spinner spInstitu;// 组织机构
    private List<JsonOrganizationObject.ObjBean> organiObjbeanList;
    private OrgSpinnerAdapter organAdapter;

    // 角色
    private static final int ROLELIST_SUCCESS = 9;
    private static final int ROLELIST_VALUES = 10;

    private Spinner spRole;// 角色
    private List<JsonRoleGetListObject.ObjBean> objbeanRoleList;
    private RoleSpinnerAdapter roleAdapter;

    private EditText edUsername, edRealName, edPhone, edEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.conver_sation);
        mContext = this;
        sp = getSharedPreferences("personal_text", MODE_PRIVATE);
        editor = sp.edit();
        initViews();
        init();

    }

    private void init() {
        // TODO Auto-generated method stub
        new Thread(new ConverRequest()).start();// 账户信息
        new Thread(new OrganizationRequest()).start();// 组织机构请求线程
        new Thread(new RoleManagerRequest()).start();// 角色管理列表请求
    }

    private void initViews() {
        // 初始化控件
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        mSex = (TextView) findViewById(R.id.tv_sex);
        mSex.setText(sex[sp.getInt("userSex", index)]);
        mSex.setOnClickListener(this);
        mBirthday = (EditText) findViewById(R.id.tv_birthday);
        mBirthday.setOnClickListener(this);
        initDateTime = DateTimePickDialogUtil.formatDate();
        mChangePsd = (Button) findViewById(R.id.change_psd);// 修改密码
        mChangePsd.setOnClickListener(this);
        mSubmitUser = (Button) findViewById(R.id.btn_submit);// 提交
        mSubmitUser.setOnClickListener(this);

        edUsername = (EditText) findViewById(R.id.username);// 用户名
        edRealName = (EditText) findViewById(R.id.real_name);// 真实姓名
        edPhone = (EditText) findViewById(R.id.ed_phone);// 手机
        edEmail = (EditText) findViewById(R.id.ed_email);// 邮箱
        spInstitu = (Spinner) findViewById(R.id.sp_Institu);// 组织机构
        spInstitu.setOnItemSelectedListener(this);
        spRole = (Spinner) findViewById(R.id.sp_role);// 角色
        spRole.setOnItemSelectedListener(this);
        // /---------------/
        // edUsername.setText(PreferencesUtils.getString(mContext, "userName",
        // "King"));
        // edRealName.setText(PreferencesUtils.getString(mContext, "realName"));
        // edPhone.setText(PreferencesUtils.getString(mContext, "mobilePhone"));
        // edEmail.setText(PreferencesUtils.getString(mContext, "email"));
        // /---------------/
    }

    /**
     * Handler消息处理
     */
    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CONVER_ERROR:
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case CONVER_SUCCESS:
                    String conver_sation = (String) msg.obj;
                    new Thread(new ConversaAction(conver_sation)).start();
                    break;
                case USEREDIT_SUCCESS:
                    String user_edit = (String) msg.obj;
                    new Thread(new UserEditAction(user_edit)).start();
                    break;
                case CONVER_VALUES:
                    userName = conversationObj.getUser().getUserName();
                    realName = conversationObj.getUser().getRealName();
                    phone = conversationObj.getUser().getMobilePhone();
                    email = conversationObj.getUser().getEmail();
                    String orgName = conversationObj.getOrgIds();
                    String roleName = conversationObj.getRoleId();

                    edUsername.setText(TextUtils.isEmpty(userName) ? "" : userName);
                    edRealName.setText(TextUtils.isEmpty(realName) ? "" : realName);
                    edPhone.setText(TextUtils.isEmpty(phone) ? "" : phone);
                    edEmail.setText(TextUtils.isEmpty(email) ? "" : email);
                    if (null == organiObjbeanList)
                        organiObjbeanList = new ArrayList<>();
                    for (int i = 0; i < organiObjbeanList.size(); i++) {
                        String strm = organiObjbeanList.get(i).getId();
                        if (orgName.equals(strm)) {
                            spInstitu.setSelection(i);
                        }
                    }
                    if (null == objbeanRoleList)
                        objbeanRoleList = new ArrayList<>();
                    for (int i = 0; i < objbeanRoleList.size(); i++) {
                        String strm = objbeanRoleList.get(i).getId();
                        if (roleName.equals(strm)) {
                            spRole.setSelection(i);
                        }
                    }
                    break;
                case ORGTYPE_SUCCESS:
                    String organiza_json = (String) msg.obj;
                    new Thread(new OrganizaAction(organiza_json)).start();// 组织机构json解析线程
                    break;
                case ORGTYPE_VALUES:
                    organAdapter = new OrgSpinnerAdapter(mContext,
                            organiObjbeanList);
                    // 绑定 Adapter到控件
                    spInstitu.setAdapter(organAdapter);
                    spInstitu.setSelection(0);
                    break;
                case ROLELIST_SUCCESS:
                    String rolelist_json = (String) msg.obj;
                    new Thread(new RoleManagerListAction(rolelist_json)).start();
                    break;
                case ROLELIST_VALUES:
                    // 设置适配器
                    roleAdapter = new RoleSpinnerAdapter(mContext, objbeanRoleList);
                    // 绑定 Adapter到控件
                    spRole.setAdapter(roleAdapter);
                    spRole.setSelection(0);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * @project name：yyshed
     * @type name：ConverRequest
     * @description：账户明细请求线程
     * @author：gang
     * @date time：2017-6-4 下午4:01:27
     */
    class ConverRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userId", currUserId);

                Log.v(TAG, TAG + "账户明细请求");

                WapiUtilEx.canverSation(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = CONVER_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = CONVER_SUCCESS;
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
     * @type name：ConversaAction
     * @description：账户明细解析线程
     * @author：gang
     * @date time：2017-6-4 下午4:29:57
     */
    class ConversaAction extends Thread {

        private String conversa_req;

        public ConversaAction(String conver_sation) {
            // TODO Auto-generated constructor stub
            this.conversa_req = conver_sation;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(conversa_req)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonUserEditObject jsonuseredit = gson.fromJson(conversa_req,
                        JsonUserEditObject.class);
                if (!jsonuseredit.equals("") && jsonuseredit != null) {
                    String msge = jsonuseredit.getMsg();// 消息提醒

                    if (jsonuseredit.isSuccess()) {// 成功

                        conversationObj = jsonuseredit.getObj();

                        Message msg = Message.obtain();
                        msg.what = CONVER_VALUES;
                        mHandler.sendMessage(msg);

                    } else
                        TAUtils.toastMessage((Activity) mContext, msge);
                }

                // JsonConverSationObject jsonconversation = gson.fromJson(
                // conversa_req, JsonConverSationObject.class);
                // if (!jsonconversation.equals("") && jsonconversation != null)
                // {
                // String msge = jsonconversation.getMsg();// 消息提醒
                // TAUtils.toastMessage((Activity) mContext, msge);
                // if (!jsonconversation.isSuccess()) {// 请求明细失败
                // return;
                // }
                //
                // }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tv_sex:
                getSex();// 选择性别选择框
                break;
            case R.id.tv_birthday:// 时间选择器
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        this, initDateTime);
                dateTimePicKDialog.dateTimePicKDialog(mBirthday);
                break;
            case R.id.change_psd:// 修改密码
                Intent changePsd = new Intent(mContext,
                        ChangePasswordActivity.class);
                mContext.startActivity(changePsd);
                break;
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_submit:// 提交
                if (validate()) {
                    new Thread(new UserUpdateRequest()).start();// 用户编辑请求线程
                }
                break;
            default:
                break;
        }
    }

    private boolean validate() {
        // TODO Auto-generated method stub
        userName = edUsername.getText().toString().trim();
        realName = edRealName.getText().toString().trim();
        phone = edPhone.getText().toString().trim();
        email = edEmail.getText().toString().trim();
        // institu = tvInstitu.getText().toString().trim();
        // role = tvRole.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            TAUtils.toastMessage((Activity) mContext, "请输入用户名");
        } else {
            edUsername.setEnabled(false);
        }
        if (TextUtils.isEmpty(realName)) {
            TAUtils.toastMessage((Activity) mContext, "请输入真实姓名");
        } else {
            edRealName.setEnabled(false);
        }
        if (TextUtils.isEmpty(phone)) {
            TAUtils.toastMessage((Activity) mContext, "请输入手机号");
        } else {
            edPhone.setEnabled(false);
        }
        if (TextUtils.isEmpty(email)) {
            TAUtils.toastMessage((Activity) mContext, "请输入电子邮箱");
        } else {
            edEmail.setEnabled(false);
        }
        // if (TextUtils.isEmpty(institu)) {
        // return false;
        // }
        // if (TextUtils.isEmpty(role)) {
        // return false;
        // }
        return true;
    }

    private void getSex() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        // 设置一个单项选择下拉框
        /**
         * 第一个参数指定我们要显示的一组下拉单选框的数据集合 第二个参数代表索引，指定默认哪一个单选框被勾选上，2表示默认'未知性别' 会被勾选上
         * 第三个参数给每一个单选项绑定一个监听器
         */

        builder.setSingleChoiceItems(sex, index,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        index = which;
                        dialog.dismiss();
                        mSex.setText(sex[which]);
                    }
                });
        AlertDialog dialog = builder.show();
    }

    /**
     * @project name：yyshed
     * @type name：UserUpdateRequest
     * @description：用户管理---修改编辑信息
     * @author：gang
     * @date time：2017-6-15 上午10:20:44
     */
    class UserUpdateRequest extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();

                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userId", currUserId);
                map.put("isEdited", "N");
                map.put("realName", realName);
                map.put("mobilePhone", phone);
                map.put("email", email);
                map.put("orgIds", orgId);
                // map.put("orgIds", "402883fd5c8fd7be015c8fdeed4b0006");
                map.put("roleIds", roleId);
                // map.put("roleIds", "402883fd5c8fd7be015c8fdfa8380008");
                map.put("tenantId", currTenantId);

                Log.v(TAG, TAG + "用户修改编辑请求");
                WapiUtilEx.usergetupdate(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = CONVER_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = USEREDIT_SUCCESS;
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

    /**
     * @project name：yyshed
     * @type name：UserEditAction
     * @description：用户编辑明细json解析线程
     * @author：gang
     * @date time：2017-6-12 下午8:40:20
     */
    class UserEditAction extends Thread {

        private String userEdit;

        public UserEditAction(String user_edit) {
            // TODO Auto-generated constructor stub
            this.userEdit = user_edit;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();

                if (TextUtils.isEmpty(userEdit)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonUserEditObject jsonuseredit = gson.fromJson(userEdit,
                        JsonUserEditObject.class);
                if (!jsonuseredit.equals("") && jsonuseredit != null) {
                    String msg = jsonuseredit.getMsg();// 消息提醒

                    if (jsonuseredit.isSuccess()) {// 成功
                        AttributesBean usereditattribute = jsonuseredit
                                .getAttributes();
                        usereditattribute.getCurrUserId();// 用户id
                        usereditattribute.getCurrTenantId();// 商户id

                        conversationObj = jsonuseredit.getObj();

                        Message mege = Message.obtain();
                        mege.what = CONVER_VALUES;
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

    // -------------------组织机构start-------------------

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

//                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
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
                        msg.what = CONVER_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = ORGTYPE_SUCCESS;
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
                    mege.what = ORGTYPE_VALUES;
                    mHandler.sendMessage(mege);
                }else
                    TAUtils.toastMessage((Activity) mContext, msg);
            }
        }
    }

    // -------------------组织机构end-------------------
    // -------------------角色start-------------------

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

//                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
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
                        msg.what = CONVER_ERROR;
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
                    msge.what = ROLELIST_VALUES;
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

    // -------------------角色end-------------------

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // TODO Auto-generated method stub
        switch (parent.getId()) {
            case R.id.sp_Institu:// 组织机构
                orgId = organiObjbeanList.get(position).getId();// 机构id
                break;
            case R.id.sp_role:// 角色
                roleId = objbeanRoleList.get(position).getId();// 角色id
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }

}
