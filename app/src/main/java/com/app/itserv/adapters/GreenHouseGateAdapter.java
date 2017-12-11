package com.app.itserv.adapters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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

import com.app.itserv.activity.GreenHouseGateManagerActivity;
import com.app.itserv.jparser.JsonBaseDelObject;
import com.app.itserv.jparser.JsonBaseDelObject.AttributesBean;
import com.app.itserv.jparser.JsonGHouseGateObject.ObjBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 大棚网关列表适配器
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: GreenHouseGateAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-11 上午11:15:12
 */

public class GreenHouseGateAdapter extends BaseAdapter {

    private Context mContext;

    private List<ObjBean> gHouseGateList;
    private ViewHolder vHolder;

    /* 弹出dialog框start */
    private AlertDialog dialog;
    private Button quitEnsure;
    private Button quitCancel;
    private Boolean isCanActiva = false;
    /* 弹出dialog框end */

    protected static final int GATEUNBIND_ERROR = 1;
    protected static final int GATEUNBIND_SUCCESS = 2;
    protected static final int GATEUNBIND_VALUES = 3;

    public static final String TAG = "GreenHouseGateAdapter";

    public GreenHouseGateAdapter(Context mContext, List<ObjBean> gHouseGateList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.gHouseGateList = gHouseGateList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return gHouseGateList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return gHouseGateList.get(position);
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
            convertView = View.inflate(mContext,
                    R.layout.gre_hou_gate_manager_items, null);
            vHolder = new ViewHolder();
            vHolder.tvGateName = (TextView) convertView
                    .findViewById(R.id.tv_gate_name);// 网关名称
            vHolder.tvStatus = (TextView) convertView
                    .findViewById(R.id.tv_status);// 状态
            vHolder.tvSnId = (TextView) convertView.findViewById(R.id.tv_sn_id);// sn号
            vHolder.btUnbind = (Button) convertView
                    .findViewById(R.id.bt_unbind);// 解绑
            vHolder.btEnable = (Button) convertView
                    .findViewById(R.id.bt_enable);// 启用
            vHolder.btDisible = (Button) convertView
                    .findViewById(R.id.bt_disable);// 停用
            vHolder.btSetting = (Button) convertView
                    .findViewById(R.id.bt_setting);// 设置
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        ObjBean gHouGateBean = (ObjBean) getItem(position);
        String gateName = TextUtils.isEmpty(gHouGateBean.getDev_name()) ? ""
                : gHouGateBean.getDev_name();// 网关名称
//		String gateStatus = TextUtils.isEmpty(gHouGateBean.getStatus()) ? ""
//				: gHouGateBean.getStatus();// 网关状态
        String gateSN = TextUtils.isEmpty(gHouGateBean.getSn()) ? ""
                : gHouGateBean.getSn();// 网关sn
        vHolder.tvGateName.setText(gateName);
//		vHolder.tvStatus.setText(gateStatus);
        vHolder.tvSnId.setText(gateSN);
        vHolder.btUnbind.setOnClickListener(new OnUnbindClick(gHouGateBean));
        return convertView;
    }

    final static class ViewHolder {
        private TextView tvGateName, tvStatus, tvSnId;
        private Button btUnbind, btDisible, btEnable, btSetting;
    }

    /**
     * 网关解绑点击事件
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyShed
     * @ClassName: OnUnbindClick
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-11 下午2:47:19
     */
    class OnUnbindClick implements OnClickListener {

        private ObjBean gHouGateBean;

        public OnUnbindClick(ObjBean gHouGateBean) {
            // TODO Auto-generated constructor stub
            this.gHouGateBean = gHouGateBean;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            // mContext.startActivity(new Intent(mContext,
            // GreenHouseGateUnbindActivity.class).putExtra(
            // "gHouGateBean", gHouGateBean));// 解绑
            isCanActiva = true;
            getCanActivaUser(gHouGateBean);
        }

    }

    /**
     * 大棚网关解绑
     *
     * @param gHouBean
     */
    private void getCanActivaUser(final ObjBean gHouBean) {

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
        if (isCanActiva) {// 解绑
            comfirmContent.setText("你确定要解绑该网关(" + gHouBean.getDev_name()
                    + ")吗?");
        }
        // 确认操作
        quitEnsure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isCanActiva) {// 解绑
                    new Thread(new GHouGateUnbindRequest(gHouBean)).start();
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

    /**
     * 大棚解绑网关请求
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: GHouGateUnbindRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-11 下午3:59:34
     */
    class GHouGateUnbindRequest extends Thread {

        private ObjBean gHouBean;

        public GHouGateUnbindRequest(ObjBean gHouBean) {
            // TODO Auto-generated constructor stub
            this.gHouBean = gHouBean;
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

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("ghouseId", gHouBean.getGhouseid());
                map.put("sgateSN", gHouBean.getSn());

                Log.v(TAG, TAG + "大棚网关解绑请求");
                WapiUtilEx.ghousegateunbind(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GATEUNBIND_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GATEUNBIND_SUCCESS;
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

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GATEUNBIND_ERROR:// 大棚网关解绑请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GATEUNBIND_SUCCESS:
                    String gHouseGate_json = msg.obj.toString();
                    new Thread(new GHouseGateUbindAction(gHouseGate_json)).start();// 大棚网关解绑报文解析线程
                    break;
                case GATEUNBIND_VALUES:
                    ((GreenHouseGateManagerActivity) mContext).init();
                    ((GreenHouseGateManagerActivity) mContext).greHouGateAdapter
                            .notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 大棚网关解绑json解析
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyShed
     * @ClassName: GHouseGateUbindAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-11 下午4:31:21
     */
    class GHouseGateUbindAction extends Thread {

        private String gHouGateJson;

        public GHouseGateUbindAction(String gHouseGate_json) {
            // TODO Auto-generated constructor stub
            this.gHouGateJson = gHouseGate_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(gHouGateJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonBaseDelObject gHouse_del = gson.fromJson(gHouGateJson,
                        JsonBaseDelObject.class);

                if (!gHouse_del.equals("") && gHouse_del != null) {
                    TAUtils.toastMessage((Activity) mContext,
                            gHouse_del.getMsg());
                    if (gHouse_del.isSuccess()) {// 成功

                        AttributesBean attributesbean = gHouse_del
                                .getAttributes();
                        attributesbean.getCurrUserId();
                        attributesbean.getCurrTenantId();

                        Message msg = Message.obtain();
                        msg.what = GATEUNBIND_VALUES;
                        mHandler.sendMessage(msg);
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
