package com.app.itserv.activity;

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
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.itserv.adapters.RoleSpinnerAdapter;
import com.app.itserv.jparser.JsonOrganizationObject;
import com.app.itserv.jparser.JsonRoleGetListObject;
import com.app.itserv.jparser.JsonUserEditObject;
import com.app.itserv.jparser.JsonUserEditObject.ObjBean;
import com.app.itserv.jparser.JsonUserEditObject.ObjBean.UserBean;
import com.app.itserv.jparser.JsonUserEditObject.ObjBean.UserBean.CurrentDepartBean;
import com.itserv.app.util.DateTimePickDialogUtil;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.RegexChk;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAPreferenceUtil;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * @project name：yyshed
 * @type name：UserAddandEditActivity
 * @description：用户管理------新增、编辑
 * @author：gang
 * @date time：2017-6-10 上午9:28:55
 */
public class UserAddandEditActivity extends Activity implements
        OnCheckedChangeListener, OnClickListener, OnItemSelectedListener {

    public static final String TAG = "UserAddandEditActivity";

    protected static final int USEREDIT_ERROR = 1;
    protected static final int USEREDIT_SUCCESS = 2;
    public static final int USEREDIT_VALUE = 3;

    private Context mContext;
    private ImageView ImgBack;

    private TextView tvAddEditTitle;
    private CheckBox ckShowPwd, ckComfirmPwd, ckChangePwd;
    private TAPreferenceUtil preferenceUtil;
    private EditText eduserName, edPwd, edComfirmPwd, edrealName, edPhone,
            OfficePhone, edEmail;
    // private EditText edRemarks;//备注
    private Button changepswSubmite, changepswReset;

    private String isEdited;// 是否修改密码
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
    private String useraddandedit;
    private LinearLayout lLChangePwd, llPwd, llComfirmPwd;
    /* 生日end */

    private String userName, pwd, comFirmPwd, realName, phone, offPhone, email,
            institu, roleId, remarks;
    private String userid, tenantid;
    private ObjBean jsonusereditobjbean;

    // 组织机构
    private static final int ORGTYPE_SUCCESS = 4;
    private static final int ORGTYPE_VALUES = 5;

    private Spinner spInstitu;// 组织机构
    private List<JsonOrganizationObject.ObjBean> organiObjbeanList;
    private OrgSpinnerAdapter organAdapter;

    // 角色
    private static final int ROLELIST_SUCCESS = 6;
    private static final int ROLELIST_VALUES = 7;

    private Spinner spRole;// 角色
    private List<JsonRoleGetListObject.ObjBean> objbeanRoleList;
    private RoleSpinnerAdapter roleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_add_edit_lay);
        mContext = this;
        preferenceUtil = TAPreferenceUtil.getInstance(mContext);
        sp = getSharedPreferences("personal_text", MODE_PRIVATE);
        editor = sp.edit();
        initView();
        new Thread(new OrganizationRequest()).start();// 组织机构请求线程
        new Thread(new RoleManagerRequest()).start();// 角色管理列表请求
        useraddandedit = (String) this.getIntent().getExtras()
                .get("useraddandedit");
        if (useraddandedit.equals("useradd")) {
            lLChangePwd.setVisibility(View.GONE);
            tvAddEditTitle.setText("新增用户");
            isEdited = "Y";
            llPwd.setVisibility(View.VISIBLE);
            llComfirmPwd.setVisibility(View.VISIBLE);
        } else {
            lLChangePwd.setVisibility(View.VISIBLE);
            tvAddEditTitle.setText("编辑用户");
            isEdited = "N";
            eduserName.setEnabled(false);
            llPwd.setVisibility(View.GONE);
            llComfirmPwd.setVisibility(View.GONE);
            userid = (String) getIntent().getExtras().get("userid");
            tenantid = (String) getIntent().getExtras().get("tenantid");
            new Thread(new UserEditRequest()).start();// 用户管理---编辑请求线程
        }
    }

    private void initView() {
        // TODO Auto-generated method stub
        // 初始化控件
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvAddEditTitle = (TextView) findViewById(R.id.tv_add_edit_user);
        lLChangePwd = (LinearLayout) findViewById(R.id.ll_change_pwd);
        ckChangePwd = (CheckBox) findViewById(R.id.ck_change_pwd);
        Boolean isChangePwd = preferenceUtil.getBoolean(
                TAPreferenceUtil.IS_CHANGE_PWD, false);
        if (isChangePwd) {
            llPwd.setVisibility(View.VISIBLE);
            llComfirmPwd.setVisibility(View.VISIBLE);
        }
        ckChangePwd.setOnCheckedChangeListener(this);
        llPwd = (LinearLayout) findViewById(R.id.ll_pwd);
        llComfirmPwd = (LinearLayout) findViewById(R.id.ll_comfirm);
        mSex = (TextView) findViewById(R.id.tv_sex);
        mSex.setText(sex[sp.getInt("userSex", index)]);
        mSex.setOnClickListener(this);
        mBirthday = (EditText) findViewById(R.id.tv_birthday);
        mBirthday.setOnClickListener(this);
        initDateTime = DateTimePickDialogUtil.formatDate();
        /* 密码明文显示start */
        edPwd = (EditText) findViewById(R.id.ed_pwd);// 密码
        ckShowPwd = (CheckBox) findViewById(R.id.ck_show_pwd);
        Boolean isShowPwd = preferenceUtil.getBoolean(
                TAPreferenceUtil.IS_SHOW_PWD, false);
        if (isShowPwd) {
            edPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        ckShowPwd.setOnCheckedChangeListener(this);
        edComfirmPwd = (EditText) findViewById(R.id.ed_comfirm);
        ckComfirmPwd = (CheckBox) findViewById(R.id.ck_comfirm_pwd);
        Boolean isComfirmPwd = preferenceUtil.getBoolean(
                TAPreferenceUtil.IS_COMFIRM_PWD, false);
        if (isComfirmPwd) {
            edComfirmPwd
                    .setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        ckComfirmPwd.setOnCheckedChangeListener(this);

		/* 密码明文显示end */

        eduserName = (EditText) findViewById(R.id.username);// 用户名
        edrealName = (EditText) findViewById(R.id.real_name);// 真实姓名
        edPhone = (EditText) findViewById(R.id.ed_phone);// 手机号
        OfficePhone = (EditText) findViewById(R.id.ed_office_phone);// 办公电话
        edEmail = (EditText) findViewById(R.id.ed_email);// 邮箱
        spInstitu = (Spinner) findViewById(R.id.sp_Institu);// 组织机构
        spInstitu.setOnItemSelectedListener(this);
        spRole = (Spinner) findViewById(R.id.sp_role);// 角色
        spRole.setOnItemSelectedListener(this);
        // edRemarks = (EditText) findViewById(R.id.et_remarks);// 备注
        changepswSubmite = (Button) findViewById(R.id.changepsw_submite);// 提交按钮
        changepswSubmite.setOnClickListener(this);
        changepswReset = (Button) findViewById(R.id.changepsw_reset);// 重置按钮
        changepswReset.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ck_show_pwd:// 密码
                if (isChecked) {
                    edPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    edPwd.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.ck_comfirm_pwd:// 确认密码
                if (isChecked) {
                    edComfirmPwd
                            .setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    edComfirmPwd.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.ck_change_pwd:// 修改密码
                if (isChecked) {
                    isEdited = "Y";
                    llPwd.setVisibility(View.VISIBLE);
                    llComfirmPwd.setVisibility(View.VISIBLE);
                } else {
                    isEdited = "N";
                    llPwd.setVisibility(View.GONE);
                    llComfirmPwd.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    /**
     * @description：员工管理---用户管理---修改用户信息
     * @author：gang
     * @date time：2017-6-15 上午9:49:04
     */
    private Boolean validator() {
        getUserTextValues();// 获取text值

        if (TextUtils.isEmpty(userName)) {
            TAUtils.toastMessage((Activity) mContext, "请输入用户名");
            return false;
        }

        if (RegexChk.checkUserName(userName)) {
            TAUtils.toastMessage((Activity) mContext, "用户名称由数字、字母或者下划线组成");
            return false;
        }

        if (TextUtils.equals(isEdited, "Y")) {
            if (TextUtils.isEmpty(pwd)) {
                TAUtils.toastMessage((Activity) mContext, "请输入密码");
                return false;
            }
            if (RegexChk.checkPassword(pwd)) {
                TAUtils.toastMessage((Activity) mContext,
                        "密码 长度为 8-20位，必须包括字母、数字、特殊符号中的2种");
                return false;
            }

            if (!TextUtils.equals(pwd, comFirmPwd)) {
                TAUtils.toastMessage((Activity) mContext, "密码不一致");
                return false;
            }
        }

        if (TextUtils.isEmpty(realName)) {
            TAUtils.toastMessage((Activity) mContext, "请输入真实姓名");
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            TAUtils.toastMessage((Activity) mContext, "请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(offPhone)) {
            TAUtils.toastMessage((Activity) mContext, "请输入办公电话");
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            TAUtils.toastMessage((Activity) mContext, "请输入电子邮箱");
            return false;
        }
        // if (TextUtils.isEmpty(institu)) {
        // TAUtils.toastMessage((Activity) mContext, "请选择组织机构");
        // return false;
        // }
        // if (TextUtils.isEmpty(role)) {
        // TAUtils.toastMessage((Activity) mContext, "请选择角色");
        // return false;
        // }
        return true;
    }

    /**
     * 获取getText值
     *
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-8-23 下午4:58:39
     */
    private void getUserTextValues() {
        userName = eduserName.getText().toString().trim();// 用户名
        pwd = edPwd.getText().toString().trim();// 密码
        comFirmPwd = edComfirmPwd.getText().toString().trim();// 确认密码
        realName = edrealName.getText().toString().trim();// 真实姓名
        phone = edPhone.getText().toString().trim();// 手机号
        offPhone = OfficePhone.getText().toString().trim();// 办公电话
        email = edEmail.getText().toString().trim();// 电子邮箱
        // institu = tvInstitu.getText().toString().trim();//组织机构
        // role = tvRole.getText().toString().trim();//角色
        // remarks = edRemarks.getText().toString().trim();// 备注
    }

    /**
     * 清空getText值,重置
     *
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-8-23 下午4:58:52
     */
    private void resetUserTextValues() {
        eduserName.setText("");// 用户名
        edPwd.setText("");// 密码
        edComfirmPwd.setText("");// 确认密码
        edrealName.setText("");// 真实姓名
        edPhone.setText("");// 手机号
        OfficePhone.setText("");// 办公电话
        edEmail.setText("");// 电子邮箱
        // institu = tvInstitu.getText().toString().trim();//组织机构
        // role = tvRole.getText().toString().trim();//角色
        // edRemarks.setText("");// 备注
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
            case R.id.changepsw_submite:// 提交按钮
                if (validator()) {// 非空验证
                    if (useraddandedit.equals("useradd")) {
                        new Thread(new UserAddRequest()).start();// 用户管理---新增请求线程
                    } else {
                        new Thread(new UserUpdateRequest()).start();// 用户管理---修改请求线程
                    }
                }
                break;
            case R.id.changepsw_reset:// 重置按钮
                resetUserTextValues();
                break;
            default:
                break;
        }
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

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case USEREDIT_ERROR:
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case USEREDIT_SUCCESS:
                    String user_edit = (String) msg.obj;
                    new Thread(new UserEditAction(user_edit)).start();
                    break;
                case USEREDIT_VALUE:
                    UserBean userBean = jsonusereditobjbean.getUser();
                    String userName = userBean.getUserName();// 用户名
                    String Psd = userBean.getPassword();// 密码
                    String realName = userBean.getRealName();// 真实姓名
                    String mobilePhone = userBean.getMobilePhone();// 手机
                    String offPhone = userBean.getOfficePhone();// 办公电话
                    String email = userBean.getEmail();// 邮箱

                    eduserName.setText(TextUtils.isEmpty(userName) ? "" : userName);
//				    edPwd.setText(TextUtils.isEmpty(Psd) ? "" : Psd);
                    edrealName.setText(TextUtils.isEmpty(realName) ? "" : realName);
                    edPhone.setText(TextUtils.isEmpty(mobilePhone) ? ""
                            : mobilePhone);
                    OfficePhone
                            .setText(TextUtils.isEmpty(offPhone) ? "" : offPhone);
                    edEmail.setText(TextUtils.isEmpty(email) ? "" : email);
//                    CurrentDepartBean currentdepartbean = userBean
//                            .getCurrentDepart();
                    String orgCode = jsonusereditobjbean.getOrgIds();// 组织机构id
                    String roleId = jsonusereditobjbean.getRoleId();// 角色id
                    if (organiObjbeanList != null) {
                        for (int i = 0; i < organiObjbeanList.size(); i++) {
                            String strm = organiObjbeanList.get(i).getId();
                            if (orgCode.substring(0, orgCode.length() - 1).equals(strm)) {
                                spInstitu.setSelection(i);
                            }
                        }
                    }
                    if (objbeanRoleList != null) {
                        for (int i = 0; i < objbeanRoleList.size(); i++) {
                            String strm = objbeanRoleList.get(i).getId();
                            if (roleId.substring(0, roleId.length() - 1).equals(strm)) {
                                spRole.setSelection(i);
                            }
                        }
                    }
                    // String remarks = String.valueOf(currentdepartbean
                    // .getDescription());// 描述
                    // edRemarks.setText(TextUtils.isEmpty(remarks) ? "" :
                    // remarks);//备注
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
                    ToastUtils.makeTextShort(jsonuseredit.getMsg());
                    if (jsonuseredit.isSuccess()) {// 成功
                        jsonusereditobjbean = jsonuseredit.getObj();
                        if (jsonusereditobjbean != null) {
                            Message mege = Message.obtain();
                            mege.what = USEREDIT_VALUE;
                            mHandler.sendMessage(mege);
                        } else {
                            finish();
                        }
                    }
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
     * @type name：UserEditRequest
     * @description：用户管理---编辑请求线程
     * @author：gang
     * @date time：2017-6-12 下午8:26:32
     */
    class UserEditRequest extends Thread {

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
                String userId = userid;

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userId", userId);

                Log.v(TAG, TAG + "用户编辑明细请求");
                WapiUtilEx.usergetedit(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = USEREDIT_ERROR;
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
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
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

//                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id
                String userId = userid;// 用户id
                String tenantId = tenantid;// 商户id

                Map<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userId", userId);
                map.put("tenantId", tenantId);
                map.put("userName", userName);
                map.put("isEdited", isEdited);
                if (TextUtils.equals(isEdited, "Y")) {
                    map.put("password", pwd);
                    map.put("passwords", comFirmPwd);
                }
                map.put("realName", realName);
                map.put("mobilePhone", phone);
                map.put("officePhone", offPhone);
                map.put("email", email);
                map.put("orgIds", institu);
                map.put("roleIds", roleId);

                Log.v(TAG, TAG + "用户修改编辑请求");
                WapiUtilEx.usergetupdate(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = USEREDIT_ERROR;
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
     * @type name：UserAddRequest
     * @description：用户管理---新增用户请求线程
     * @author：gang
     * @date time：2017-6-15 下午2:46:58
     */
    class UserAddRequest extends Thread {
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
                map.put("userId", token);
                map.put("tenantId", currTenantId);
                map.put("userName", userName);
                map.put("password", pwd);
                map.put("passwords", comFirmPwd);
                map.put("realName", realName);
                map.put("mobilePhone", phone);
                map.put("officePhone", offPhone);
                map.put("email", email);
                map.put("orgIds", institu);
                // map.put("orgIds", "402883fd5c8fd7be015c8fdeed4b0006");
                map.put("roleIds", roleId);
                // map.put("roleIds", "402883fd5c8fd7be015c8fdfa8380008 ");

                Log.v(TAG, TAG + "用户新增请求");
                WapiUtilEx.usergetadd(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = USEREDIT_ERROR;
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
                        msg.what = USEREDIT_ERROR;
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // TODO Auto-generated method stub
        switch (parent.getId()) {
            case R.id.sp_Institu:// 组织机构
                institu = organiObjbeanList.get(position).getId();// 机构id
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
                        msg.what = USEREDIT_ERROR;
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
}
