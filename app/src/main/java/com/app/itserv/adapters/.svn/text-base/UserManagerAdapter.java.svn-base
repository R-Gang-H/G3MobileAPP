package com.app.itserv.adapters;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.app.itserv.activity.UserGreenHouseActivity;
import com.app.itserv.activity.UserManagerActivity;
import com.app.itserv.jparser.JsonUserCancellActivatObject;
import com.app.itserv.jparser.JsonUserGetListObject.ObjBean;
import com.app.itserv.jparser.JsonUserGetListObject.ObjBean.CurrentDepartBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * @project name：yyshed
 * @type name：UserManagerAdapter
 * @description：员工管理---用户管理适配器
 * @author：gang
 * @date time：2017-6-10 上午9:28:38
 */
public class UserManagerAdapter extends BaseAdapter {

    public static final String TAG = "UserManagerAdapter";

    /**
     * 注销、激活
     */
    protected static final int USERMANAG_ERROR = 1;
    protected static final int USERMANAG_SUCCESS = 2;
    public static final int USERMANAG_STATUS = 3;

    private Context mContext;
    private List<ObjBean> objbeanUserList;// 用户列表
    private List<com.app.itserv.jparser.JsonDataDictionaryObject.ObjBean> userStatukList;// 使用状态

    /* 弹出dialog框start */
    private AlertDialog dialog;
    private Button quitEnsure;
    private Button quitCancel;
    private String loginUser = " ";

	/* 弹出dialog框end */

    ViewHolder vHolder = null;
    private Boolean isCanActiva = false;
    private String cancell_text;
    private String activation_text;

    public UserManagerAdapter(
            Context mContext,
            List<ObjBean> objbeanUserList,
            List<com.app.itserv.jparser.JsonDataDictionaryObject.ObjBean> userStatukList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.objbeanUserList = objbeanUserList;
        this.userStatukList = userStatukList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objbeanUserList.size();
    }

    @Override
    public ObjBean getItem(int position) {
        // TODO Auto-generated method stub
        return objbeanUserList.get(position);
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
            convertView = View.inflate(mContext, R.layout.user_manager_items,
                    null);
            vHolder = new ViewHolder();
            vHolder.username = (TextView) convertView
                    .findViewById(R.id.username);// 用户名
            vHolder.organization = (TextView) convertView
                    .findViewById(R.id.organization);// 组织结构
            vHolder.real_name = (TextView) convertView
                    .findViewById(R.id.real_name);// 真实姓名
            vHolder.et_role = (TextView) convertView.findViewById(R.id.et_role);// 角色
            vHolder.tvCancell = (Button) convertView
                    .findViewById(R.id.tv_cancell);// 注销
            vHolder.tvActivation = (Button) convertView
                    .findViewById(R.id.tv_activation);// 激活
            vHolder.tvGreenhouse = (Button) convertView
                    .findViewById(R.id.tv_greenhouse);// 大棚
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        ObjBean objbean = (ObjBean) getItem(position);

        vHolder.username.setText(TextUtils.isEmpty(objbean.getUserName()) ? ""
                : objbean.getUserName());// 用户名
        CurrentDepartBean currdepart = objbean.getCurrentDepart();
//        String orgCode = (String) currdepart.getOrgCode();
//        vHolder.organization.setText(TextUtils.isEmpty(orgCode) ? "" : orgCode);// 组织机构
        String orgCode = (String) currdepart.getDepartname();
        vHolder.organization.setText(TextUtils.isEmpty(orgCode) ? "" : orgCode);// 组织机构
        vHolder.real_name.setText(TextUtils.isEmpty(objbean.getRealName()) ? ""
                : objbean.getRealName());// 真实姓名
        vHolder.et_role.setText(TextUtils.isEmpty(objbean.getUserKey()) ? ""
                : objbean.getUserKey());// 角色
        cancell_text = vHolder.tvCancell.getText().toString().trim();// 注销
        vHolder.tvCancell.setOnClickListener(new CancellonClick(position));
        activation_text = vHolder.tvActivation.getText().toString().trim();// 激活
        vHolder.tvActivation.setOnClickListener(new ActivationClick(position));

        String cancell_activat = String.valueOf(objbean.getStatus());
        if (TextUtils.equals(cancell_activat, "0")) {
            vHolder.tvCancell.setVisibility(View.GONE);
            vHolder.tvActivation.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(cancell_activat, "1")) {
            vHolder.tvCancell.setVisibility(View.VISIBLE);
            vHolder.tvActivation.setVisibility(View.GONE);
        }
        vHolder.tvGreenhouse
                .setOnClickListener(new GreenHouseonClick(position));
        return convertView;
    }

    private static final class ViewHolder {
        private TextView username, organization, real_name, et_role;
        private Button tvCancell, tvActivation, tvGreenhouse;
    }

    /**
     * @project name：yyshed
     * @type name：CancellonClick
     * @description：注销点击事件
     * @author：gang
     * @date time：2017-6-15 下午7:33:12
     */
    class CancellonClick implements OnClickListener {

        private int position;

        public CancellonClick(int position) {
            // TODO Auto-generated constructor stub
            this.position = position;
        }

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            isCanActiva = false;
            getCanActivaUser(cancell_text, position);
        }

    }

    /**
     * @project name：yyshed
     * @type name：ActivationClick
     * @description：激活点击事件
     * @author：gang
     * @date time：2017-6-16 下午12:51:16
     */
    class ActivationClick implements OnClickListener {

        private int position;

        public ActivationClick(int position) {
            // TODO Auto-generated constructor stub
            this.position = position;
        }

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            isCanActiva = true;
            getCanActivaUser(activation_text, position);
        }

    }

    /**
     * @project name：yyshed
     * @type name：GreenHouseonClick
     * @description：大棚
     * @author：gang
     * @date time：2017-6-16 下午1:56:47
     */
    class GreenHouseonClick implements OnClickListener {

        private int position;

        public GreenHouseonClick(int position) {
            // TODO Auto-generated constructor stub
            this.position = position;
        }

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            String userId = getItem(position).getId();
            Bundle bundle = new Bundle();
            bundle.putSerializable("userStatukList",
                    (Serializable) userStatukList);
            bundle.putString("userId", userId);
            mContext.startActivity(new Intent(mContext,
                    UserGreenHouseActivity.class).putExtras(bundle));
        }

    }

    // 注销、激活用户
    private void getCanActivaUser(String content, final int position) {

        ObjBean userId = (ObjBean) getItem(position);
        loginUser = userId.getUserName();

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
        comfirmContent.setText("你确定要" + content + "该用户(" + loginUser + ")吗?");
        // 确认操作
        quitEnsure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isCanActiva) {// 激活
                    // vHolder.tvCancell.setVisibility(View.VISIBLE);
                    // vHolder.tvActivation.setVisibility(View.GONE);
                    new Thread(new UserActivatRequest(position)).start();// 用户管理---激活
                } else {// 注销
                    // vHolder.tvCancell.setVisibility(View.GONE);
                    // vHolder.tvActivation.setVisibility(View.VISIBLE);
                    new Thread(new UserCancellRequest(position)).start();// 用户管理---注销
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

                case USERMANAG_SUCCESS:
                    String userManager_json = (String) msg.obj;
                    new Thread(new UserCancellActivatAction(userManager_json))
                            .start();
                    break;
                case USERMANAG_ERROR:
                case USERMANAG_STATUS:
                    ((UserManagerActivity) mContext).init();
                    ((UserManagerActivity) mContext).userAdapter
                            .notifyDataSetChanged();
                    int curItem = ((UserManagerActivity) mContext).curItem;
                    ((UserManagerActivity) mContext).userManagerList
                            .setSelection(curItem);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * @project name：yyshed
     * @type name：UserCancellAction
     * @description：用户管理---注销、激活 解析线程
     * @author：gang
     * @date time：2017-6-15 下午5:02:29
     */
    class UserCancellActivatAction extends Thread {

        private String userManager_json;

        public UserCancellActivatAction(String userManager_json) {
            // TODO Auto-generated constructor stub
            this.userManager_json = userManager_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();

                if (TextUtils.isEmpty(userManager_json)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonUserCancellActivatObject jsonUserCancell = gson.fromJson(
                        userManager_json, JsonUserCancellActivatObject.class);
                if (!jsonUserCancell.equals("") && jsonUserCancell != null) {
                    String msg = jsonUserCancell.getMsg();// 消息提醒
                    TAUtils.toastMessage((Activity) mContext, msg);
                    if (jsonUserCancell.isSuccess()) {// 成功
                        // 处理json 报文
                        com.app.itserv.jparser.JsonUserCancellActivatObject.ObjBean
                                objCancell = jsonUserCancell.getObj();

                        Message msge = Message.obtain();
                        msge.what = USERMANAG_STATUS;
                        mHandler.sendMessage(msge);

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
     * @type name：UserCancellRequest
     * @description：用户管理---注销 请求线程
     * @author：gang
     * @date time：2017-6-15 下午3:45:18
     */
    class UserCancellRequest extends Thread {

        private int index;

        public UserCancellRequest(int position) {
            // TODO Auto-generated constructor stub
            this.index = position;
        }

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
                ObjBean userId = (ObjBean) getItem(index);

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userId", userId.getId());

                Log.v(TAG, TAG + "用户注销请求");
                WapiUtilEx.usergetCancell(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = USERMANAG_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = USERMANAG_SUCCESS;
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
     * @type UserActivatRequest
     * @description：用户管理---激活 请求线程
     * @author：gang
     * @date time：2017-6-15 下午3:45:18
     */
    class UserActivatRequest extends Thread {

        private int index;

        public UserActivatRequest(int position) {
            // TODO Auto-generated constructor stub
            this.index = position;
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
                ObjBean userId = (ObjBean) getItem(index);

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userId", userId.getId());

                Log.v(TAG, TAG + "用户激活请求");
                WapiUtilEx.usergetActivat(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = USERMANAG_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = USERMANAG_SUCCESS;
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
}
