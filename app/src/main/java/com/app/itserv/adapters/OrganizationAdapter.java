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

import com.app.itserv.activity.OrganizationActivity;
import com.app.itserv.activity.UserRoleOrganizaActivity;
import com.app.itserv.jparser.JsonDelRoleObject;
import com.app.itserv.jparser.JsonOrganizationObject.ObjBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 组织机构适配器
 *
 * @author Administrator
 */
public class OrganizationAdapter extends BaseAdapter implements OnClickListener {

    private static final String TAG = "OrganizationAdapter";

    protected static final int ORGADEL_ERROR = 1;
    protected static final int ORGADEL_SUCCESS = 2;
    public static final int ORGADEL_VALUES = 3;

    private Context mContext;

    private List<ObjBean> organiObjbeanList;
    private ViewHolder vHolder;

    /* 弹出dialog框start */
    private AlertDialog dialog;
    private Button quitEnsure;
    private Button quitCancel;
    private Boolean isCancell = false;
    private Boolean isActivat = false;
    private Boolean isDelete = false;
    private String greOrganiza = "采购部";

	/* 弹出dialog框end */

    public OrganizationAdapter(Context mContext, List<ObjBean> organiObjbeanList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.organiObjbeanList = organiObjbeanList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return organiObjbeanList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return organiObjbeanList.get(position);
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
            vHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.organiza_items, null);
            vHolder.organiza_name = (TextView) convertView
                    .findViewById(R.id.organiza_name);
            vHolder.organiza_code = (TextView) convertView
                    .findViewById(R.id.organiza_code);
            vHolder.organiza_status = (TextView) convertView
                    .findViewById(R.id.organiza_status);
            vHolder.tvDel = (Button) convertView.findViewById(R.id.tv_del);// 删除
            vHolder.tvCancell = (Button) convertView
                    .findViewById(R.id.tv_cancell);
            vHolder.tvActivation = (Button) convertView
                    .findViewById(R.id.tv_activation);
            vHolder.tvUser = (Button) convertView.findViewById(R.id.tv_user);
            vHolder.tvCancell.setOnClickListener(this);
            vHolder.tvActivation.setOnClickListener(this);
            vHolder.tvUser.setOnClickListener(this);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        ObjBean objBeanitem = (ObjBean) getItem(position);

        vHolder.organiza_name.setText(TextUtils.isEmpty(objBeanitem
                .getDepartname()) ? "" : objBeanitem.getDepartname());// 机构名称
        vHolder.organiza_code.setText(TextUtils.isEmpty(String
                .valueOf(objBeanitem.getOrgCode())) ? "" : String
                .valueOf(objBeanitem.getOrgCode().toString()));// 机构编码
        vHolder.organiza_status.setText(TextUtils.isEmpty(String
                .valueOf(objBeanitem.getOrgType())) ? "" : String
                .valueOf(objBeanitem.getOrgType()));// 机构状态
        vHolder.tvDel.setOnClickListener(new DelonClick(position));// 删除
        return convertView;
    }

    private final static class ViewHolder {
        private TextView organiza_name, organiza_code, organiza_status;
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
                        "RoleOrganizaUser", "OrganizaUser"));
                break;
            default:
                break;
        }
    }

    /**
     * @project name：yyshed
     * @type name：DelonClick
     * @description：删除机构
     * @author：gang
     * @date time：2017-6-17 上午10:17:27
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

    // 注销、激活、删除用户
    private void getCanActivaUser(String content, int position) {
        final ObjBean objBean = (ObjBean) getItem(position);
        greOrganiza = objBean.getDepartname();

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
        comfirmContent.setText("你确定要" + content + "该机构(" + greOrganiza + ")吗?");
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
                    new Thread(new DelOrganizatRequest(objBean)).start();// 删除机构
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
                case ORGADEL_ERROR:
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case ORGADEL_SUCCESS:
                    String roledel_json = (String) msg.obj;
                    new Thread(new DelOrgaAction(roledel_json)).start();
                    break;
                case ORGADEL_VALUES:
                    ((OrganizationActivity) mContext).init();
                    ((OrganizationActivity) mContext).organAdapter
                            .notifyDataSetChanged();
//                    int curItem = ((OrganizationActivity) mContext).curItem;
//                    ((OrganizationActivity) mContext).organManagerList
//                            .setSelection(curItem);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * @project name：yyshed
     * @type name：DelOrganizatRequest
     * @description：删除机构请求线程
     * @author：gang
     * @date time：2017-6-17 上午10:24:11
     */
    class DelOrganizatRequest extends Thread {

        private ObjBean objBean;

        public DelOrganizatRequest(ObjBean objBean) {
            // TODO Auto-generated constructor stub
            this.objBean = objBean;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

//			String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
            String token = PreferencesUtils.getString(mContext, "token");// token
            String currTenantId = PreferencesUtils.getString(mContext,
                    "tenantId");// 商户id
            String departId = objBean.getId().toString();// 机构id

            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("currTenantId", currTenantId);
            map.put("departId", departId);

            Log.v(TAG, TAG + "机构删除请求");
            WapiUtilEx.orgadelete(map, new MYCallBack() {

                @Override
                public void onError(int code, String message) {
                    // TODO Auto-generated method stub
                    super.onError(code, message);
                    Message msg = Message.obtain();
                    msg.what = ORGADEL_ERROR;
                    msg.obj = message;
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onSuccess(String response) {
                    // TODO Auto-generated method stub
                    super.onSuccess(response);
                    Message msg = Message.obtain();
                    msg.what = ORGADEL_SUCCESS;
                    msg.obj = response;
                    mHandler.sendMessage(msg);
                }

            });

        }
    }

    /**
     * @project name：yyshed
     * @type name：DelOrgaAction
     * @description：删除机构解析线程
     * @author：gang
     * @date time：2017-6-17 上午10:29:22
     */
    class DelOrgaAction extends Thread {

        private String roledelJson;

        public DelOrgaAction(String roledel_json) {
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
                        mege.what = ORGADEL_VALUES;
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
