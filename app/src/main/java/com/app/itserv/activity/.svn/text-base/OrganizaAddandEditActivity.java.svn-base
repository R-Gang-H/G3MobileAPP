package com.app.itserv.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.jparser.JsonOrganizaEditObject;
import com.app.itserv.jparser.JsonOrganizaEditObject.AttributesBean;
import com.app.itserv.jparser.JsonOrganizaEditObject.ObjBean;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 员工管理---新增、编辑组织机构
 *
 * @project name：yyshed
 * @type name：OrganizaAddandEditActivity
 * @description：组织机构编辑明细,
 * @author：gang
 * @date time：2017-6-13 下午9:04:51
 */
public class OrganizaAddandEditActivity extends Activity implements
        OnClickListener {

    public static final String TAG = "OrganizaAddandEditActivity";

    private Context mContext;

    protected static final int ORGANIZAEDIT_ERROR = 1;
    protected static final int ORGANIZAEDIT_SUCCESS = 2;
    public static final int ORGANIZAEDIT_VALUES = 3;

    private ImageView ImgBack;
    private TextView tvAddEditTitle;
    private Button changepswSubmite;
    private EditText edOrganName, edOrganCode, edInstitu, edPhone, edRemarks;

    /* 机构类型start */
    private TextView mOrganType;
    private final String[] organ = {"公司", "部门", "岗位"};
    private SharedPreferences sp = null;
    private Editor editor;
    private int index = 0;
    private AlertDialog dialog;
    /* 机构类型end */

    private String organName, organCode, organType;

    private String departId, organaddandedit = null;
    private ObjBean orgObjBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organ_add_edit_lay);
        mContext = this;
		/* 类型start */
        sp = getSharedPreferences("personal_text", MODE_PRIVATE);
        editor = sp.edit();
		/* 类型end */
        initView();
        organaddandedit = (String) this.getIntent().getExtras()
                .get("organaddandedit");
        if (organaddandedit.equals("organadd")) {
            tvAddEditTitle.setText("新增组织机构");
        } else {
            tvAddEditTitle.setText("编辑组织机构");
            departId = (String) getIntent().getExtras().get("departId");
            new Thread(new OrganizaEditRequest()).start();// 编辑组织机构明细请求线程
        }
    }

    private void initView() {
        // TODO Auto-generated method stub
        // 初始化控件
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvAddEditTitle = (TextView) findViewById(R.id.tv_add_edit_organ);
        mOrganType = (TextView) findViewById(R.id.organ_type);// 机构类型
        mOrganType.setText(organ[sp.getInt("organization", index)]);
        mOrganType.setOnClickListener(this);
        edOrganName = (EditText) findViewById(R.id.organ_name);// 机构名称
        edOrganCode = (EditText) findViewById(R.id.organ_code);// 机构编号
        edInstitu = (EditText) findViewById(R.id.tv_Institu);// 上级组织机构
        edPhone = (EditText) findViewById(R.id.ed_phone);// 手机号
        edRemarks = (EditText) findViewById(R.id.et_remarks);// 备注
        changepswSubmite = (Button) findViewById(R.id.changepsw_submite);// 提交
        changepswSubmite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.organ_type:// 机构类型
                getOrganType();
                break;
            case R.id.changepsw_submite:// 提交
                if (Validation()) {
                    new Thread(new OrganizaAddEditRequest()).start();
                }
                break;
            default:
                break;
        }
    }

    private void getOrganType() {
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        // 设置一个单项选择下拉框
        /**
         * 第一个参数指定我们要显示的一组下拉单选框的数据集合 第二个参数代表索引，指定默认哪一个单选框被勾选上，2表示默认'未知性别' 会被勾选上
         * 第三个参数给每一个单选项绑定一个监听器
         */

        builder.setSingleChoiceItems(organ, index,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        index = which;
                        dialog.dismiss();
                        mOrganType.setText(organ[which]);
                    }
                });
        dialog = builder.show();
    }

    private boolean Validation() {
        organName = edOrganName.getText().toString().trim();
        organCode = edOrganCode.getText().toString().trim();
        organType = mOrganType.getText().toString().trim();

        if (TextUtils.isEmpty(organName)) {
            TAUtils.toastMessage((Activity) mContext, "请输入机构名称");
            return false;
        }

        if (TextUtils.isEmpty(organCode)) {
            TAUtils.toastMessage((Activity) mContext, "请输入机构编码");
            return false;
        }

        if (TextUtils.isEmpty(organType)) {
            TAUtils.toastMessage((Activity) mContext, "请输入机构类型");
            return false;
        }

        return true;
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ORGANIZAEDIT_ERROR:
                    TAUtils.toastMessage((Activity) mContext, (String) msg.obj);
                    break;
                case ORGANIZAEDIT_SUCCESS:
                    String organiza_json = (String) msg.obj;
                    new Thread(new OrganizaEditAction(organiza_json)).start();
                    break;
                case ORGANIZAEDIT_VALUES:
                    edOrganName.setText(orgObjBean.getDepartname());// 机构名称
                    edOrganCode.setText(orgObjBean.getOrgCode());// 机构编号
                    if (Integer.valueOf(orgObjBean.getOrgType()) < organ.length) {
                        mOrganType.setText(organ[Integer.valueOf(orgObjBean
                                .getOrgType())]);// 机构类型
                    }
                    edRemarks.setText(orgObjBean.getDescription());// 备注
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * @project name：yyshed
     * @type name：OrganizaEditAction
     * @description：组织机构---编辑明细json解析线程
     * @author：gang
     * @date time：2017-6-14 上午9:59:15
     */
    class OrganizaEditAction extends Thread {

        private String organizajson;

        public OrganizaEditAction(String organiza_json) {
            // TODO Auto-generated constructor stub
            this.organizajson = organiza_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            if (TextUtils.isEmpty(organizajson)) {
                TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员！");
                return;
            }

            Gson gson = new Gson();
            JsonOrganizaEditObject organizaedit = gson.fromJson(organizajson,
                    JsonOrganizaEditObject.class);
            if (!organizaedit.equals("") && organizaedit != null) {
                String msg = organizaedit.getMsg();// 消息提醒

                if (organizaedit.isSuccess()) {
                    AttributesBean organizaAttriButes = organizaedit
                            .getAttributes();
                    organizaAttriButes.getCurrUserId();
                    organizaAttriButes.getCurrTenantId();

                    orgObjBean = organizaedit.getObj();

                    Message mege = Message.obtain();
                    mege.what = ORGANIZAEDIT_VALUES;
                    mHandler.sendMessage(mege);
                }else
                    TAUtils.toastMessage((Activity) mContext, msg);
            }

        }
    }

    /**
     * @project name：yyshed
     * @type name：OrganizaEditRequest
     * @description：组织机构---编辑明细
     * @author：gang
     * @date time：2017-6-14 上午9:48:37
     */
    class OrganizaEditRequest extends Thread {

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
                map.put("departId", departId);

                Log.v(TAG, TAG + "组织机构编辑明细请求");
                WapiUtilEx.doOrganedit(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = ORGANIZAEDIT_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = ORGANIZAEDIT_SUCCESS;
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
     * @type name：OrganizaAddEditRequest
     * @description：组织机构---新增、编辑修改
     * @author：gang
     * @date time：2017-6-16 下午5:28:26
     */
    class OrganizaAddEditRequest extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

//			String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
            String token = PreferencesUtils.getString(mContext, "token");// token
            String currTenantId = PreferencesUtils.getString(mContext,
                    "tenantId");// 商户id

            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("currTenantId", currTenantId);
            map.put("departName", organName);
            map.put("orgCode", organCode);
            map.put("orgType", String.valueOf(index));
            map.put("description", edRemarks.getText().toString().trim());
            map.put("tenantId", currTenantId);

            if (organaddandedit.equals("organedit")) {
                map.put("departId", departId);
                Log.v(TAG, TAG + "组织机构---编辑修改请求");
            } else {
                Log.v(TAG, TAG + "组织机构---新增请求");
            }

            WapiUtilEx.doOrganAddUpdate(map, new MYCallBack() {

                @Override
                public void onError(int code, String message) {
                    // TODO Auto-generated method stub
                    super.onError(code, message);
                    Message msg = Message.obtain();
                    msg.what = ORGANIZAEDIT_ERROR;
                    msg.obj = message;
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onSuccess(String response) {
                    // TODO Auto-generated method stub
                    super.onSuccess(response);
                    Message msg = Message.obtain();
                    msg.what = ORGANIZAEDIT_SUCCESS;
                    msg.obj = response;
                    mHandler.sendMessage(msg);
                }

            });

        }
    }

}
