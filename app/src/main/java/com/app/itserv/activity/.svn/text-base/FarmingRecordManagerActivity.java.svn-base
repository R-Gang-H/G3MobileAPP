package com.app.itserv.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.itserv.adapters.FarmingRecordAdapter;
import com.app.itserv.jparser.JsonFarmingRecordObject;
import com.app.itserv.jparser.JsonFarmingRecordObject.AttributesBean;
import com.app.itserv.jparser.JsonFarmingRecordObject.ObjBean;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 农事管理---农事记录管理
 *
 * @author Administrator
 */
public class FarmingRecordManagerActivity extends Activity implements OnClickListener {

    public static final String TAG = "FarmingRecordManagerActivity";
    protected static final int FARMING_RECORD_MANAGER_ERROR = 1;
    protected static final int FARMING_RECORD_MANAGER_SUCCESS = 2;
    protected static final int FARMING_RECORD_MANAGER_VALUES = 3;
    private List<ObjBean> objBeans;
    private ListView listview;//listview条目

    private Context mContext;
    private ImageView ImgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_recor_mana_lay);
        mContext = this;
        initView();
        init();
    }

    private void init() {
        new Thread(new FramingRecordManagerGetListRequest()).start();//农事记录管理中的列表请求线程
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);//返回按钮
        ImgBack.setOnClickListener(this);

        listview = (ListView) findViewById(R.id.far_recor_manager_listview);
        select = (Button) findViewById(R.id.far_recor_manager_btn_select);//查询
        reset = (Button) findViewById(R.id.far_recor_manager_changepsw_reset);//重置
        select.setOnClickListener(this);
        reset.setOnClickListener(this);
        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String idString = objBeans.get(position).getId();
                Intent intent = new Intent(mContext, FarmingExamingActivity.class);
                intent.putExtra("farrecordsupply", "farrecord");
                intent.putExtra("framingRecordId", idString);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.far_recor_manager_btn_select:// 查询

                break;

            default:
                break;
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FARMING_RECORD_MANAGER_ERROR:// 农事记录管理列表请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case FARMING_RECORD_MANAGER_SUCCESS://农事记录管理列表请求成功
                    String farmingrecordmanager_json = msg.obj.toString();// 农事记录管理列表解析
                    farmingrecordmanagerJson(farmingrecordmanager_json);
                    break;
                case FARMING_RECORD_MANAGER_VALUES:
                    FarmingRecordAdapter adapter = new FarmingRecordAdapter(mContext, objBeans);
                    listview.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }


    };
    private Button select;
    private Button reset;


    /**
     * 农事记录管理列表请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: FramingRecordManagerGetListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-22 下午4:04:21
     */
    class FramingRecordManagerGetListRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();
                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id
                String headBy = currUserId;

                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("headBy", headBy);
                WapiUtilEx.apiFramingRecordGetList(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = FARMING_RECORD_MANAGER_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = FARMING_RECORD_MANAGER_SUCCESS;
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

    //我的农事列表数据解析
    private void farmingrecordmanagerJson(String farcordjson) {
        if (TextUtils.isEmpty(farcordjson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonFarmingRecordObject jsonFarmingRecordObject = gson.fromJson(farcordjson, JsonFarmingRecordObject.class);
        if (!jsonFarmingRecordObject.equals("") && jsonFarmingRecordObject != null) {

            if (jsonFarmingRecordObject.isSuccess()) {// 成功
                objBeans = jsonFarmingRecordObject.getObj();

                Message msg = Message.obtain();
                msg.what = FARMING_RECORD_MANAGER_VALUES;
                mHandler.sendMessage(msg);
            }else
                TAUtils.toastMessage((Activity) mContext, jsonFarmingRecordObject.getMsg());


        }
    }

}
