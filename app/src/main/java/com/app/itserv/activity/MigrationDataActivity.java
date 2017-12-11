package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.itserv.adapters.MigrationDataAdapter;
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
 * 二代数据迁移
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/8/29 16:23
 */
public class MigrationDataActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MigrationDataActivity";
    private static final int MIGRA_ERROR = 1;
    private static final int MIGRA_SUCCESS = 2;
    private static final int MIGRA_VALUES = 3;

    private Context mContext;

    private ImageView imgClose;
    private ListView migraDateItems;
    private MigrationDataAdapter migradataAdapter;
    private List<JsonMigrationDataObject.ObjBean> migraDataList;
    private JsonMigrationDataObject.ObjBean migraDataBean;

    private String greenhouseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_migra_lay);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        greenhouseId = bundle.getString("greenhouseId");
        initView();
        init();
    }

    private void init() {
        new Thread(new MigrationDataRequest()).start();
    }

    private void initView() {
        imgClose = (ImageView) findViewById(R.id.img_close);//关闭
        imgClose.setOnClickListener(this);
        migraDateItems = (ListView) findViewById(R.id.migra_date_items);//二代数据迁移列表
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close://关闭
                finish();
                break;
            default:
                break;
        }
    }


    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MIGRA_ERROR:// 数据迁移请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case MIGRA_SUCCESS:
                    String migraTion_json = msg.obj.toString();
                    new Thread(new MigrationDataAction(migraTion_json)).start();// 数据迁移报文解析线程
                    break;
                case MIGRA_VALUES:
                    migradataAdapter = new MigrationDataAdapter(mContext,
                            migraDataList, greenhouseId);
                    migraDateItems.setAdapter(migradataAdapter);
                    break;
                default:
                    break;
            }
        }

    };


    /**
     * 二代数据迁移请求接口
     */
    class MigrationDataRequest extends Thread {

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
                String userName = PreferencesUtils.getString(mContext,
                        "userName");// 登录用户

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userBy", userName);

                Log.v(TAG, TAG + "二代数据迁移请求");
                WapiUtilEx.migrationdata(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = MIGRA_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = MIGRA_SUCCESS;
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
     * 二代数据迁移json解析线程
     */
    class MigrationDataAction extends Thread {

        private String migrationJson;

        public MigrationDataAction(String migration_json) {
            // TODO Auto-generated constructor stub
            this.migrationJson = migration_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            if (TextUtils.isEmpty(migrationJson)) {
                TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                return;
            }
            Gson gson = new Gson();
            JsonMigrationDataObject jsonMigraObject = gson.fromJson(
                    migrationJson, JsonMigrationDataObject.class);
            if (!jsonMigraObject.equals("")
                    && jsonMigraObject != null) {

                if (jsonMigraObject.isSuccess()) {// 成功
                    migraDataList = jsonMigraObject.getObj();
                    Message msg = Message.obtain();
                    msg.what = MIGRA_VALUES;
                    mHandler.sendMessage(msg);
                }else
                    TAUtils.toastMessage((Activity) mContext,
                            jsonMigraObject.getMsg());
            }
        }
    }
}