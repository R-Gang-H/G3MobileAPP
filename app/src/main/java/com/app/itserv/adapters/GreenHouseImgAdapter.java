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

import com.app.itserv.activity.GreenHouseImgEditActivity;
import com.app.itserv.activity.GreenHouseImgManagerActivity;
import com.app.itserv.jparser.JsonBaseDelObject;
import com.app.itserv.jparser.JsonBaseDelObject.AttributesBean;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonGreenhouseImgObject.ObjBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 大棚图片适配器
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyshed
 * @ClassName: GreenHouseImgAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-12 下午4:20:58
 */
public class GreenHouseImgAdapter extends BaseAdapter {

    private Context mContext;

    private static final String TAG = "GreenHouseImgAdapter";

    protected static final int GHOUSEIMGDEL_ERROR = 1;
    protected static final int GHOUSEIMGDEL_SUCCESS = 2;
    private static final int GHOUSEIMGDEL_VALUES = 3;

    private List<ObjBean> gHouseImgObj;
    private ViewHolder vHolder;
    private ObjBean gHouseImgBean;
    private List<JsonDataDictionaryObject.ObjBean> datadicList;

    /* 弹出dialog框start */
    private AlertDialog dialog;
    private Button quitEnsure;
    private Button quitCancel;
    private Boolean isCanActiva = false;

	/* 弹出dialog框end */

    public GreenHouseImgAdapter(Context mContext, List<ObjBean> gHouseImgObj, List<JsonDataDictionaryObject.ObjBean> datadicList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.gHouseImgObj = gHouseImgObj;
        this.datadicList = datadicList;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return gHouseImgObj.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return gHouseImgObj.get(position);
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
                    R.layout.gre_hou_img_manager_items, null);
            vHolder = new ViewHolder();
            vHolder.tvImgName = (TextView) convertView
                    .findViewById(R.id.tv_img_name);// 图片名称
            vHolder.tvImgDes = (TextView) convertView
                    .findViewById(R.id.tv_img_des);// 图片说明
            vHolder.tvImgStatic = (TextView) convertView
                    .findViewById(R.id.tv_img_static);// 图片状态
            vHolder.tvImgAddress = (TextView) convertView
                    .findViewById(R.id.tv_img_address);// 图片地址
            vHolder.btImgEdite = (Button) convertView
                    .findViewById(R.id.bt_img_edite);// 编辑
            vHolder.btImgDel = (Button) convertView
                    .findViewById(R.id.bt_del_img);// 删除

            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        gHouseImgBean = gHouseImgObj.get(position);// 图片对象

        vHolder.tvImgName
                .setText(TextUtils.isEmpty(gHouseImgBean.getDocName()) ? ""
                        : gHouseImgBean.getDocName());
        vHolder.tvImgDes.setText(TextUtils.isEmpty(gHouseImgBean
                .getDescription()) ? "" : gHouseImgBean.getDescription());
        String ImgStatic = TextUtils.isEmpty(gHouseImgBean.getStatus()) ? ""
                : gHouseImgBean.getStatus();
        for (int i = 0; i < datadicList.size(); i++) {
            String strm = datadicList.get(i).getTypecode();
            if (ImgStatic.equals(strm)) {
                vHolder.tvImgStatic
                        .setText(datadicList.get(i).getTypename());
            }
        }
        vHolder.tvImgAddress.setText(TextUtils.isEmpty(gHouseImgBean
                .getAttachment1()) ? "" : gHouseImgBean.getAttachment1());
        vHolder.btImgEdite.setOnClickListener(new OnGreHouImgEditRequest(
                gHouseImgBean));
        vHolder.btImgDel.setOnClickListener(new OnGreHouImgDelRequest(
                gHouseImgBean));

        return convertView;
    }

    final static class ViewHolder {
        private TextView tvImgName, tvImgDes, tvImgStatic, tvImgAddress;
        private Button btImgEdite, btImgDel;
    }

    /**
     * 大棚图片编辑
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: OnGreHouImgEditRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午1:35:10
     */
    class OnGreHouImgEditRequest implements OnClickListener {

        private ObjBean gHouseImgBean;

        public OnGreHouImgEditRequest(ObjBean gHouseImgBean) {
            // TODO Auto-generated constructor stub
            this.gHouseImgBean = gHouseImgBean;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mContext.startActivity(new Intent(mContext,
                    GreenHouseImgEditActivity.class)
                    .putExtra("gHouseImgaddedit", "gHouseImgedit")
                    .putExtra("greenhouseId", gHouseImgBean.getGhouseId())
                    .putExtra("ghouseDocId", gHouseImgBean.getId()));
        }
    }

    /**
     * 大棚图片删除请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: OnGreHouImgDelRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午4:02:47
     */
    class OnGreHouImgDelRequest implements OnClickListener {

        private ObjBean gHouseImgBean;

        public OnGreHouImgDelRequest(ObjBean gHouseImgBean) {
            // TODO Auto-generated constructor stub
            this.gHouseImgBean = gHouseImgBean;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            isCanActiva = true;
            getCanActivaUser(gHouseImgBean);
        }
    }

    /**
     * 删除图片
     *
     * @param gHouseImgBean
     * @param content
     */
    private void getCanActivaUser(final ObjBean gHouseImgBean) {

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
            comfirmContent.setText("你确定要删除该图片(" + gHouseImgBean.getDocName()
                    + ")吗?");
        }
        // 确认操作
        quitEnsure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isCanActiva) {// 删除
                    new Thread(new GreenHouseImgDelRequest(gHouseImgBean))
                            .start();// 大棚图片删除请求线程
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
     * 大棚图片删除json报文解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: GreenHouseImgDelAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午4:16:52
     */
    class GreenHouseImgDelAction extends Thread {

        private String gHouseImgJson;

        public GreenHouseImgDelAction(String gHouseImg_json) {
            // TODO Auto-generated constructor stub
            this.gHouseImgJson = gHouseImg_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(gHouseImgJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonBaseDelObject gHouse_del = gson.fromJson(gHouseImgJson,
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
                        msg.what = GHOUSEIMGDEL_VALUES;
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
                case GHOUSEIMGDEL_ERROR:// 大棚删除请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GHOUSEIMGDEL_SUCCESS:
                    String gHouseImg_json = msg.obj.toString();
                    new Thread(new GreenHouseImgDelAction(gHouseImg_json)).start();// 大棚图片报文解析线程
                    break;
                case GHOUSEIMGDEL_VALUES:
                    ((GreenHouseImgManagerActivity) mContext).init();
                    ((GreenHouseImgManagerActivity) mContext).greHouImgAdapter
                            .notifyDataSetChanged();
                    int curItem = ((GreenHouseImgManagerActivity) mContext).curItem;
                    ((GreenHouseImgManagerActivity) mContext).greHouImgList
                            .setSelection(curItem);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 大棚图片删除请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: GreenHouseImgDelRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午4:09:37
     */
    class GreenHouseImgDelRequest extends Thread {

        private ObjBean gHouseImgBean;

        public GreenHouseImgDelRequest(ObjBean gHouseImgBean) {
            // TODO Auto-generated constructor stub
            this.gHouseImgBean = gHouseImgBean;
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
                map.put("ghouseDocId", gHouseImgBean.getId());

                Log.v(TAG, TAG + "大棚图片删除请求");
                WapiUtilEx.ghouseimagedel(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEIMGDEL_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEIMGDEL_SUCCESS;
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
