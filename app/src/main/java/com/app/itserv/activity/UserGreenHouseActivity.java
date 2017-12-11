package com.app.itserv.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.adapters.UserGreenHouseAdapter;
import com.app.itserv.jparser.JsonUserGreenHouseObject;
import com.app.itserv.jparser.JsonUserGreenHouseObject.AttributesBean;
import com.app.itserv.jparser.JsonUserGreenHouseObject.ObjBean;
import com.app.itserv.views.PullToRefreshListView;
import com.app.itserv.views.PullToRefreshListView.OnRefreshListener;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * @project name：yyshed
 * @type name：UserGreenHouseActivity
 * @description：用户管理---查看大棚
 * @author：gang
 * @date time：2017-6-12 下午12:26:59
 */
public class UserGreenHouseActivity extends Activity implements OnRefreshListener, OnClickListener {

    public static final String TAG = "UserGreenHouseActivity";

    protected static final int USERGREENHOUSELIST_ERROR = 0;
    protected static final int USERGREENHOUSELIST_SUCCESS = 1;
    private static final int USERGRRENHOUSELIST_ADAPTER = 2;

    private List<ObjBean> objbeanUserGreenHouseList;

    private Context mContext;
    private ImageView ImgBack;

    private PullToRefreshListView greenListView;
    private UserGreenHouseAdapter greenAdapter;

    private TextView tvGreenHouseNum;

    private List<com.app.itserv.jparser.JsonDataDictionaryObject.ObjBean> userStatukList;// 使用状态
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_green_house_lay);
        mContext = this;

        Bundle bundle = getIntent().getExtras();
        userStatukList = (List<com.app.itserv.jparser.JsonDataDictionaryObject.ObjBean>) bundle
                .get("userStatukList");// 使用状态数据字典列表
        userId = bundle.getString("userId");

        init();
        initView();
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case USERGREENHOUSELIST_ERROR:// 失败
                    greenListView.onRefreshComplete();
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case USERGREENHOUSELIST_SUCCESS:// 成功
                    String user_green_house_json = (String) msg.obj;
                    new Thread(new UserGreenHouseAction(user_green_house_json))
                            .start();// json解析线程
                    break;
                case USERGRRENHOUSELIST_ADAPTER:// 用户管理---查看大棚适配器
                    tvGreenHouseNum.setText(String.format("大棚:%d个",
                            objbeanUserGreenHouseList.size()));
                    greenAdapter = new UserGreenHouseAdapter(mContext,
                            objbeanUserGreenHouseList, userStatukList);
                    greenListView.setAdapter(greenAdapter);
                    greenListView.onRefreshComplete();
                    break;
                default:
                    break;
            }

        }

    };

    private void init() {
        // TODO Auto-generated method stub
        new Thread(new UserGreenHouseRequest()).start();// 用户管理-查看大棚请求线程
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvGreenHouseNum = (TextView) findViewById(R.id.tv_greenhouse_num);
        greenListView = (PullToRefreshListView) findViewById(R.id.green_items);
        greenListView.setOnRefreshListener(this);

    }


    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        init();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * @project name：yyshed
     * @type name：UserGreenHouseRequest
     * @description：用户管理--查看大棚列表请求线程
     * @author：gang
     * @date time：2017-6-12 下午12:31:01
     */
    class UserGreenHouseRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {

                Looper.prepare();

                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userId", userId);

                Log.v(TAG, TAG + "用户管理---查看大棚列表请求");
                WapiUtilEx.usergreenhouselist(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = USERGREENHOUSELIST_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = USERGREENHOUSELIST_SUCCESS;
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
     * @project name：yyshed
     * @type name：UserGreenHouseAction
     * @description：用户管理---查看大棚json解析线程
     * @author：gang
     * @date time：2017-6-12 下午12:47:11
     */
    class UserGreenHouseAction extends Thread {

        private String user_green_house_json;

        public UserGreenHouseAction(String user_green_house_json) {
            // TODO Auto-generated constructor stub
            this.user_green_house_json = user_green_house_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {

                Looper.prepare();

                if (TextUtils.isEmpty(user_green_house_json)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonUserGreenHouseObject jsonuserghobj = gson.fromJson(
                        user_green_house_json, JsonUserGreenHouseObject.class);
                if (!jsonuserghobj.equals("") && jsonuserghobj != null) {
                    String msg = jsonuserghobj.getMsg();// 消息提醒

                    if (jsonuserghobj.isSuccess()) {// 成功
                        AttributesBean attributeugh = jsonuserghobj
                                .getAttributes();
                        int greenhousesize = attributeugh
                                .getGreenhouseUserSize();// 大棚数量
                        attributeugh.getCurrUserId();// 用户id
                        attributeugh.getCurrTenantId();// 商户id
                        objbeanUserGreenHouseList = jsonuserghobj.getObj();// 查看大棚集合

                        // 加载适配器
                        Message msge = Message.obtain();
                        msge.what = USERGRRENHOUSELIST_ADAPTER;
                        mHandler.sendMessage(msge);
                    }else
                        TAUtils.toastMessage((Activity) mContext, msg);
                }

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                Looper.loop();
            }

        }
    }

}
