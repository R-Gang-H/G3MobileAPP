package com.app.itserv.adapters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.app.itserv.activity.RoleManagerActivity;
import com.app.itserv.activity.UserRoleOrganizaActivity;
import com.app.itserv.jparser.JsonDelRoleObject;
import com.app.itserv.jparser.JsonRoleGetListObject.ObjBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * @project name：yyshed
 * @type name：RoleManagerAdapter
 * @description：角色管理---角色管理适配器
 * @author：gang
 * @date time：2017-6-13 上午9:47:35
 */
public class RoleManagerAdapter extends BaseAdapter implements OnClickListener {

    public static final String TAG = "RoleManagerAdapter";

    protected static final int ROLEDEL_ERROR = 1;
    protected static final int ROLEDEL_SUCCESS = 2;
    private static final int ROLEDEL_VALUES = 3;

    private Context mContext;
    ViewHolder vHolder = null;

    private List<ObjBean> objbeanRoleList;

    /* 弹出dialog框start */
    private AlertDialog dialog;
    private Button quitEnsure;
    private Button quitCancel;
    private Boolean isCancell = false;
    private Boolean isActivat = false;
    private Boolean isDelete = false;
    private String greHouManager = "大棚管理员";

	/* 弹出dialog框end */

    public RoleManagerAdapter(Context mContext, List<ObjBean> objbeanRoleList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.objbeanRoleList = objbeanRoleList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objbeanRoleList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return objbeanRoleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.role_manager_items,
                    null);
            vHolder = new ViewHolder();
            vHolder.role_name = (TextView) convertView
                    .findViewById(R.id.role_name);// 角色名称
            vHolder.role_code = (TextView) convertView
                    .findViewById(R.id.role_code);// 角色编号
            vHolder.role_status = (TextView) convertView
                    .findViewById(R.id.role_status);// 角色状态
            vHolder.tvDel = (Button) convertView.findViewById(R.id.tv_del);// 删除
            vHolder.tvCancell = (Button) convertView
                    .findViewById(R.id.tv_cancell);// 注销
            vHolder.tvActivation = (Button) convertView
                    .findViewById(R.id.tv_activation);// 激活
            vHolder.tvUser = (Button) convertView.findViewById(R.id.tv_user);// 用户
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        ObjBean objbean = (ObjBean) getItem(position);

        greHouManager = objbean.getRoleName();
        vHolder.role_name.setText(TextUtils.isEmpty(greHouManager) ? ""
                : greHouManager);// 角色名称
        vHolder.role_code.setText(TextUtils.isEmpty(objbean.getRoleCode()) ? ""
                : objbean.getRoleCode());// 角色编号
        vHolder.role_status.setText(TextUtils.isEmpty(String.valueOf(objbean
                .getCreateName())) ? "" : String.valueOf(objbean
                .getCreateName()));// 角色状态
        vHolder.tvDel.setOnClickListener(new DelonClick(position));// 删除
        vHolder.tvCancell.setOnClickListener(this);
        vHolder.tvActivation.setOnClickListener(this);
        vHolder.tvUser.setOnClickListener(this);
        return convertView;
    }

    private static final class ViewHolder {
        private TextView role_name, role_code, role_status;
        private Button tvCancell, tvActivation, tvUser, tvDel;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tv_cancell:// 注销
                isCancell = true;
                getCanActivaUser(vHolder.tvCancell.getText().toString().trim(), 0);
                break;
            case R.id.tv_activation:// 激活
                isActivat = true;
                getCanActivaUser(vHolder.tvActivation.getText().toString().trim(),
                        0);
                break;
            case R.id.tv_user:// 查看用户
                mContext.startActivity(new Intent(mContext,
                        UserRoleOrganizaActivity.class).putExtra(
                        "RoleOrganizaUser", "RoleUser"));
                break;
            default:
                break;
        }
    }

    /**
     * @project name：yyshed
     * @type name：DelonClick
     * @description：删除角色
     * @author：gang
     * @date time：2017-6-17 上午8:30:11
     */
    class DelonClick implements OnClickListener {

        private int position;

        public DelonClick(int position) {
            // TODO Auto-generated constructor stub
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            isDelete = true;
            getCanActivaUser(vHolder.tvDel.getText().toString().trim(),
                    position);
        }

    }

    // 注销、激活用户
    private void getCanActivaUser(String content, int position) {

        final ObjBean objBean = (ObjBean) getItem(position);
        greHouManager = objBean.getRoleName();

        dialog = new AlertDialog.Builder(mContext).create();
        // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        View comfirmDialog = LayoutInflater.from(mContext).inflate(
                R.layout.comfirm_dialog, null);
        dialog.addContentView(comfirmDialog,
                new ViewGroup.LayoutParams((int) (mContext.getResources()
                        .getDisplayMetrics().widthPixels * 0.9),
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(true);
        TextView comfirmContent = (TextView) comfirmDialog
                .findViewById(R.id.tv_comfirmdialog_content);
        quitEnsure = (Button) comfirmDialog.findViewById(R.id.btn_ensurequit);
        quitCancel = (Button) comfirmDialog.findViewById(R.id.btn_cancelquit);
        comfirmContent.setText("你确定要" + content + "该角色(" + greHouManager
                + ")吗?");
        // 确认操作
        quitEnsure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isCancell) {// 激活
                    vHolder.tvCancell.setVisibility(View.VISIBLE);
                    vHolder.tvActivation.setVisibility(View.GONE);
                }
                if (isActivat) {// 注销
                    vHolder.tvCancell.setVisibility(View.GONE);
                    vHolder.tvActivation.setVisibility(View.VISIBLE);
                }
                if (isDelete) {// 删除
                    new Thread(new DelRoleRequest(objBean)).start();// 删除角色
                }
                dialog.dismiss();
            }
        });
        // 取消操作
        quitCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ROLEDEL_ERROR:
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case ROLEDEL_SUCCESS:
                    String roledel_json = (String) msg.obj;
                    new Thread(new DelRoleAction(roledel_json)).start();
                    break;
                case ROLEDEL_VALUES:
                    ((RoleManagerActivity) mContext).init();
                    ((RoleManagerActivity) mContext).roleAdapter
                            .notifyDataSetChanged();
//                    int curItem = ((RoleManagerActivity) mContext).curItem;
//                    ((RoleManagerActivity) mContext).roleManagerList
//                            .setSelection(curItem);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * @project name：yyshed
     * @type name：DelRoleRequest
     * @description：角色删除请求线程
     * @author：gang
     * @date time：2017-6-17 上午8:56:36
     */
    class DelRoleRequest extends Thread {

        private ObjBean objBean;

        public DelRoleRequest(ObjBean objBean) {
            // TODO Auto-generated constructor stub
            this.objBean = objBean;
        }

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
                String roleId = objBean.getId().toString();// 角色id

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("roleId", roleId);

                Log.v(TAG, TAG + "角色删除请求");
                WapiUtilEx.roledelete(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = ROLEDEL_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = ROLEDEL_SUCCESS;
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
     * @type name：DelRoleAction
     * @description：角色删除解析线程
     * @author：gang
     * @date time：2017-6-17 上午9:07:03
     */
    class DelRoleAction extends Thread {

        private String roledelJson;

        public DelRoleAction(String roledel_json) {
            // TODO Auto-generated constructor stub
            this.roledelJson = roledel_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();

                if (TextUtils.isEmpty(roledelJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonDelRoleObject delroleObject = gson.fromJson(roledelJson,
                        JsonDelRoleObject.class);
                if (!delroleObject.equals("") && delroleObject != null) {
                    String msg = delroleObject.getMsg();// 消息提醒
                    TAUtils.toastMessage((Activity) mContext, msg);
                    if (delroleObject.isSuccess()) {// 成功
                        // com.app.itserv.jparser.JsonDelRoleObject.AttributesBean
                        // delAttrbute = delroleObject
                        // .getAttributes();
                        // delAttrbute.getCurrUserId();
                        // delAttrbute.getCurrTenantId();
                        // delroleObject.getObj();

                        Message mege = Message.obtain();
                        mege.what = ROLEDEL_VALUES;
                        mHandler.sendMessage(mege);
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

}
