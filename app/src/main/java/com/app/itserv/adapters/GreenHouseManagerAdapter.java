package com.app.itserv.adapters;

import java.io.Serializable;
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

import com.app.itserv.activity.GreenHouseEmployeeManagerActivity;
import com.app.itserv.activity.GreenHouseGateManagerActivity;
import com.app.itserv.activity.GreenHouseImgManagerActivity;
import com.app.itserv.activity.GreenHouseManagerActivity;
import com.app.itserv.activity.GreenHouseViewActivity;
import com.app.itserv.jparser.JsonBaseDelObject;
import com.app.itserv.jparser.JsonBaseDelObject.AttributesBean;
import com.app.itserv.jparser.JsonGreenhouseManagerObject.ObjBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 大棚管理适配器
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyshed
 * @ClassName: GreenHouseManagerAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-12 下午3:45:37
 */
public class GreenHouseManagerAdapter extends BaseAdapter {

    private Context mContext;

    public static final String TAG = "GreenHouseManagerAdapter";
    protected static final int GHOUSEMAG_ERROR = 1;
    protected static final int GHOUSEMAG_SUCCESS = 2;
    protected static final int GHOUSEMAG_VALUES = 3;

    private List<ObjBean> gHouseObjList;
    private ViewHolder vHolder;
    private List<com.app.itserv.jparser.JsonDataDictionaryObject.ObjBean> datadicList;

    /* 弹出dialog框start */
    private AlertDialog dialog;
    private Button quitEnsure;
    private Button quitCancel;
    private Boolean isCanActiva = false;

	/* 弹出dialog框end */

    public GreenHouseManagerAdapter(
            Context mContext,
            List<ObjBean> gHouseObjList,
            List<com.app.itserv.jparser.JsonDataDictionaryObject.ObjBean> datadicList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.gHouseObjList = gHouseObjList;
        this.datadicList = datadicList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return gHouseObjList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return gHouseObjList.get(position);
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
                    R.layout.gre_hou_manager_items, null);
            vHolder = new ViewHolder();
            vHolder.tvGreHouName = (TextView) convertView
                    .findViewById(R.id.gre_hou_name);// 大棚名称
            vHolder.tvGhouseCode = (TextView) convertView
                    .findViewById(R.id.ghousecode);// 大棚编号
            // vHolder.tvGreHouType = (TextView) convertView
            // .findViewById(R.id.gre_hou_type);// 大棚类型
            vHolder.tvBaseStation = (TextView) convertView
                    .findViewById(R.id.tv_base_station);// 所属基地
            vHolder.tvActivaDate = (TextView) convertView
                    .findViewById(R.id.tv_activa_date);// 启用日期
            vHolder.tvPlanArea = (TextView) convertView
                    .findViewById(R.id.tv_plan_area);// 种植面积
            vHolder.tvUseState = (TextView) convertView
                    .findViewById(R.id.tv_use_state);// 使用状态
            vHolder.btExamine = (Button) convertView
                    .findViewById(R.id.bt_examine);// 查看
            vHolder.btDel = (Button) convertView.findViewById(R.id.bt_del);// 删除
            vHolder.btImgManager = (Button) convertView
                    .findViewById(R.id.bt_image_manager);// 图片
            vHolder.btGateway = (Button) convertView
                    .findViewById(R.id.bt_gateway);// 网关
            vHolder.btEmployee = (Button) convertView
                    .findViewById(R.id.bt_employee);// 员工
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        ObjBean gHouBean = (ObjBean) getItem(position);
        String gHouName = TextUtils.isEmpty(gHouBean.getGhouseFullname()) ? ""
                : gHouBean.getGhouseFullname();
        String gHouseId = TextUtils.isEmpty(gHouBean.getGhouseCode()) ? ""
                : gHouBean.getGhouseCode();
        // String gHouType = "";
        String baseStation = TextUtils.isEmpty(gHouBean.getBaseFullname()) ? ""
                : gHouBean.getBaseFullname();
        String activaDate = TextUtils.isEmpty(String.valueOf(gHouBean
                .getOpenDateOpen())) ? "" : String.valueOf(gHouBean
                .getOpenDateOpen());
        String PlanArea = TextUtils.isEmpty(gHouBean.getUsedArea()) ? ""
                : gHouBean.getUsedArea();
        String UseState = TextUtils.isEmpty(gHouBean.getStatus()) ? ""
                : gHouBean.getStatus();

        vHolder.tvGreHouName.setText(gHouName);
        vHolder.tvGhouseCode.setText(gHouseId);
        // vHolder.tvGreHouType.setText(gHouType);
        vHolder.tvBaseStation.setText(baseStation);
        vHolder.tvActivaDate.setText(DateLocalUtil.getDate(activaDate));
        vHolder.tvPlanArea.setText(PlanArea + "(平米)");
        for (int i = 0; i < datadicList.size(); i++) {
            String strm = datadicList.get(i).getTypecode();
            if (UseState.equals(strm)) {
                vHolder.tvUseState.setText(datadicList.get(i).getTypename());
            }
        }
        vHolder.btExamine
                .setOnClickListener(new OnGreenHouseExaClick(gHouBean));// 查看
        vHolder.btDel.setOnClickListener(new OnGreenHouseDelClick(gHouBean));// 删除
        vHolder.btImgManager.setOnClickListener(new OnGreenHouseImgClick(
                gHouBean));// 大棚图片
        vHolder.btGateway.setOnClickListener(new OnGatewayClick(gHouBean));// 网关
        vHolder.btEmployee.setOnClickListener(new OnGreenHouseUserClick(
                gHouBean));// 大棚员工

        return convertView;
    }

    static final class ViewHolder {
        private TextView tvGreHouName, tvGhouseCode, tvGreHouType,
                tvBaseStation, tvActivaDate, tvPlanArea, tvUseState;
        private Button btExamine, btDel, btImgManager, btGateway, tvSinPro,
                btEmployee;

    }

    /**
     * 大棚查看
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: OnGreenHouseExaClick
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12 上午10:45:07
     */
    class OnGreenHouseExaClick implements OnClickListener {

        private ObjBean gHouBean;

        public OnGreenHouseExaClick(ObjBean gHouBean) {
            // TODO Auto-generated constructor stub
            this.gHouBean = gHouBean;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mContext.startActivity(new Intent(mContext,
                    GreenHouseViewActivity.class).putExtra("greenhouseId",
                    gHouBean.getId()).putExtra("datadicList", (Serializable) datadicList));
        }

    }

    /**
     * 大棚删除
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: OnGreenHouseDelClick
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12 上午9:56:15
     */
    class OnGreenHouseDelClick implements OnClickListener {

        private ObjBean gHouBean;

        public OnGreenHouseDelClick(ObjBean gHouBean) {
            // TODO Auto-generated constructor stub
            this.gHouBean = gHouBean;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            isCanActiva = true;
            getCanActivaUser(gHouBean);
        }

    }

    /**
     * 大棚员工
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: OnGreenHouseUserClick
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午4:40:46
     */
    class OnGreenHouseUserClick implements OnClickListener {

        private ObjBean gHouBean;

        public OnGreenHouseUserClick(ObjBean gHouBean) {
            // TODO Auto-generated constructor stub
            this.gHouBean = gHouBean;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mContext.startActivity(new Intent(mContext,
                    GreenHouseEmployeeManagerActivity.class).putExtra(
                    "greenhouseId", gHouBean.getId()));
        }

    }

    /**
     * 大棚图片查看
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: OnGreenHouseDelClick
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12 上午9:56:15
     */
    class OnGreenHouseImgClick implements OnClickListener {

        private ObjBean gHouBean;

        public OnGreenHouseImgClick(ObjBean gHouBean) {
            // TODO Auto-generated constructor stub
            this.gHouBean = gHouBean;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mContext.startActivity(new Intent(mContext,
                    GreenHouseImgManagerActivity.class).putExtra(
                    "greenhouseId", gHouBean.getId()).putExtra("datadicList", (Serializable) datadicList));
        }

    }

    /**
     * 大棚网关列表
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyShed
     * @ClassName: OnGatewayClick
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-10 上午10:47:58
     */
    class OnGatewayClick implements OnClickListener {

        private ObjBean gHouBean;

        public OnGatewayClick(ObjBean gHouBean) {
            // TODO Auto-generated constructor stub
            this.gHouBean = gHouBean;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            // 网关管理
            mContext.startActivity(new Intent(mContext,
                    GreenHouseGateManagerActivity.class).putExtra(
                    "greenhouseId", gHouBean.getId()));
        }

    }

    /**
     * 大棚删除
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
        if (isCanActiva) {// 删除
            comfirmContent.setText("你确定要删除该大棚(" + gHouBean.getGhouseFullname()
                    + ")吗?");
        }
        // 确认操作
        quitEnsure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isCanActiva) {// 删除
                    new Thread(new GreenHouseDelRequest(gHouBean.getId()))
                            .start();
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
     * 大棚删除json解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: GreenHouseDelAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12 上午10:12:49
     */
    class GreenHouseDelAction extends Thread {

        private String gHosueDelJson;

        public GreenHouseDelAction(String gHouseDel_json) {
            // TODO Auto-generated constructor stub
            this.gHosueDelJson = gHouseDel_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(gHosueDelJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonBaseDelObject gHouse_del = gson.fromJson(gHosueDelJson,
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
                        msg.what = GHOUSEMAG_VALUES;
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

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GHOUSEMAG_ERROR:// 大棚删除请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GHOUSEMAG_SUCCESS:
                    String gHouseDel_json = msg.obj.toString();
                    new Thread(new GreenHouseDelAction(gHouseDel_json)).start();// 大棚删除报文解析线程
                    break;
                case GHOUSEMAG_VALUES:
                    ((GreenHouseManagerActivity) mContext).setUrl();
                    ((GreenHouseManagerActivity) mContext).greHouAdapter
                            .notifyDataSetChanged();
                    int curItem = ((GreenHouseManagerActivity) mContext).curItem;
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 删除大棚请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: OnGreenHouseClick
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12 上午9:17:02
     */
    class GreenHouseDelRequest extends Thread {

        private String gHouseId;

        public GreenHouseDelRequest(String id) {
            // TODO Auto-generated constructor stub
            this.gHouseId = id;
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
                map.put("greenhouseId", gHouseId);
                WapiUtilEx.ghousedelete(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEMAG_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEMAG_SUCCESS;
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

}
