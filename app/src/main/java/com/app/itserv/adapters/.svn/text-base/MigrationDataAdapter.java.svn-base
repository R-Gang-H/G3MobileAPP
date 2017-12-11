package com.app.itserv.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.app.itserv.jparser.JsonBaseDelObject;
import com.app.itserv.jparser.JsonMigrationDataObject;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/8/30 11:29
 */
public class MigrationDataAdapter extends BaseAdapter {

    private static final String TAG = "MigrationDataAdapter";

    protected static final int GATEBIND_ERROR = 1;
    protected static final int GATEBIND_SUCCESS = 2;
    protected static final int GATEBIND_VALUES = 3;

    private Context mContext;
    private List<JsonMigrationDataObject.ObjBean> migraDataList;
    private JsonMigrationDataObject.ObjBean migraBase;
    private String gHouseId;


    public MigrationDataAdapter(Context mContext, List<JsonMigrationDataObject.ObjBean> migraDataList, String greenhouseId) {
        this.mContext = mContext;
        this.migraDataList = migraDataList;
        this.gHouseId = greenhouseId;
    }

    @Override
    public int getCount() {
        return migraDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return migraDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.migra_date_items, null);
            vHolder.tvDeviceName = (TextView) convertView.findViewById(R.id.tv_device_name);//设备名称
            vHolder.tvSnId = (TextView) convertView.findViewById(R.id.tv_sn_id);//sn号
            vHolder.btBind = (Button) convertView.findViewById(R.id.bt_bind);//绑定
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        migraBase = (JsonMigrationDataObject.ObjBean) getItem(position);
        vHolder.tvDeviceName.setText(TextUtils.isEmpty(migraBase.getDev_name()) ? "" : migraBase.getDev_name());
        vHolder.tvSnId.setText(TextUtils.isEmpty(migraBase.getSn()) ? "" : migraBase.getSn());
        vHolder.btBind.setOnClickListener(new OnMigraDataClick(migraBase));
        return convertView;
    }


    private final class ViewHolder {
        private TextView tvDeviceName, tvSnId;
        private Button btBind;
    }


    class OnMigraDataClick implements View.OnClickListener {

        private JsonMigrationDataObject.ObjBean migraBase;

        public OnMigraDataClick(JsonMigrationDataObject.ObjBean migraBase) {
            this.migraBase = migraBase;
        }

        @Override
        public void onClick(View v) {
            new Thread(new GHouseGeteBindRequest(migraBase)).start();
        }
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GATEBIND_ERROR:// 大棚网关绑定请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GATEBIND_SUCCESS:
                    String gHouseGatebind_json = msg.obj.toString();
                    new Thread(new GHouseGateBindAction(gHouseGatebind_json))
                            .start();// 大棚网关绑定报文解析线程
                    break;
                case GATEBIND_VALUES:
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 大棚网关绑定请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: GHouseGeteBindRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-12 下午4:53:21
     */
    class GHouseGeteBindRequest extends Thread {

        private JsonMigrationDataObject.ObjBean migraBase;

        public GHouseGeteBindRequest(JsonMigrationDataObject.ObjBean migraBase) {
            this.migraBase = migraBase;
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
                map.put("ghouseId", gHouseId);
                map.put("sgateSN", migraBase.getSn());

                Log.v(TAG, TAG + "大棚网关绑定请求");
                WapiUtilEx.ghousegatebind(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GATEBIND_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GATEBIND_SUCCESS;
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
     * 大棚网关绑定解析
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: GHouseGateBindAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-12 下午5:03:02
     */
    class GHouseGateBindAction extends Thread {

        private String gHouGateBindJson;

        public GHouseGateBindAction(String gHouseGatebind_json) {
            // TODO Auto-generated constructor stub
            this.gHouGateBindJson = gHouseGatebind_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            if (TextUtils.isEmpty(gHouGateBindJson)) {
                TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                return;
            }
            Gson gson = new Gson();
            JsonBaseDelObject jsonGHouseGateObject = gson.fromJson(
                    gHouGateBindJson, JsonBaseDelObject.class);
            if (!jsonGHouseGateObject.equals("")
                    && jsonGHouseGateObject != null) {
                TAUtils.toastMessage((Activity) mContext,
                        jsonGHouseGateObject.getMsg());
                if (jsonGHouseGateObject.isSuccess()) {// 成功
                    Message msg = Message.obtain();
                    msg.what = GATEBIND_VALUES;
                    mHandler.sendMessage(msg);
                }
            }
        }
    }
}
