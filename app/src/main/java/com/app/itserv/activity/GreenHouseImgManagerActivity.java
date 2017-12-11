package com.app.itserv.activity;

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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.adapters.GreenHouseImgAdapter;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonGreenhouseImgObject;
import com.app.itserv.jparser.JsonGreenhouseImgObject.AttributesBean;
import com.app.itserv.jparser.JsonGreenhouseImgObject.ObjBean;
import com.app.itserv.views.PullToRefreshListView;
import com.app.itserv.views.PullToRefreshListView.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 大棚管理---图片管理
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: GreenHouseImgManagerActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-12 下午3:45:50
 */
public class GreenHouseImgManagerActivity extends Activity implements
        OnClickListener, OnRefreshListener,
        OnItemClickListener {

    private Context mContext;

    private static final String TAG = "GreenHouseImgManagerActivity";
    protected static final int GHOUSEIMG_ERROR = 1;
    protected static final int GHOUSEIMG_SUCCESS = 2;
    protected static final int GHOUSEIMG_VALUES = 3;

    private String greenhouseId;
    private List<ObjBean> gHouseImgObj;
    private List<JsonDataDictionaryObject.ObjBean> datadicList;
    public int curItem;

    private ImageView ImgBack;
    private TextView tvGreHouImgmagTitle;
    public PullToRefreshListView greHouImgList;
    public GreenHouseImgAdapter greHouImgAdapter;
    private Button btnGreenUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gre_hou_img_manager_lay);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        datadicList = (List<JsonDataDictionaryObject.ObjBean>) bundle.getSerializable("datadicList");
        initView();
        init();
    }

    public void init() {
        greenhouseId = (String) getIntent().getExtras().get("greenhouseId");
        new Thread(new GreenHouseImgRequest(greenhouseId)).start();// 大棚图片请求线程
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvGreHouImgmagTitle = (TextView) findViewById(R.id.tv_grehouimgmagtitle);
        btnGreenUpload = (Button) findViewById(R.id.btn_green_upload);
        btnGreenUpload.setOnClickListener(this);
        greHouImgList = (PullToRefreshListView) findViewById(R.id.gre_hou_img_manager_items);
        greHouImgList.setOnRefreshListener(this);
        greHouImgList.setOnItemClickListener(this);
        // greHouImgAdapter = new GreenHouseImgAdapter();
        // greHouImgList.setAdapter(greHouImgAdapter);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_green_upload:// 上传
                startActivity(new Intent(mContext, GreenHouseImgEditActivity.class)
                        .putExtra("gHouseImgaddedit", "gHouseImgadd").putExtra(
                                "greenhouseId", greenhouseId));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        init();
    }

    /**
     * 大棚图片解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseImgAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12 下午4:01:21
     */
    class GreenHouseImgAction extends Thread {

        private String gHosueImgJson;

        public GreenHouseImgAction(String gHouseImg_json) {
            // TODO Auto-generated constructor stub
            this.gHosueImgJson = gHouseImg_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(gHosueImgJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonGreenhouseImgObject gHouse_Img = gson.fromJson(
                        gHosueImgJson, JsonGreenhouseImgObject.class);

                if (!gHouse_Img.equals("") && gHouse_Img != null) {
                    TAUtils.toastMessage((Activity) mContext,
                            gHouse_Img.getMsg());
                    if (gHouse_Img.isSuccess()) {// 成功

                        AttributesBean attributesbean = gHouse_Img
                                .getAttributes();
                        attributesbean.getCurrUserId();
                        attributesbean.getCurrTenantId();
                        attributesbean.getGreenhouseDocSize();

                        gHouseImgObj = gHouse_Img.getObj();

                        Message msg = Message.obtain();
                        msg.what = GHOUSEIMG_VALUES;
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
                case GHOUSEIMG_ERROR:// 大棚删除请求失败
                    greHouImgList.onRefreshComplete();
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GHOUSEIMG_SUCCESS:
                    String gHouseImg_json = msg.obj.toString();
                    new Thread(new GreenHouseImgAction(gHouseImg_json)).start();// 大棚图片报文解析线程
                    break;
                case GHOUSEIMG_VALUES:
                    tvGreHouImgmagTitle.setText(String.format("大棚图片(%s)",
                            gHouseImgObj.size()));
                    greHouImgAdapter = new GreenHouseImgAdapter(mContext,
                            gHouseImgObj, datadicList);
                    greHouImgList.setAdapter(greHouImgAdapter);
                    greHouImgList.onRefreshComplete();
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 大棚图片请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseImgRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12 下午3:49:42
     */
    class GreenHouseImgRequest extends Thread {

        private String gHouseId;

        public GreenHouseImgRequest(String greenhouseId) {
            // TODO Auto-generated constructor stub
            this.gHouseId = greenhouseId;
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
                WapiUtilEx.ghouseimage(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEIMG_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEIMG_SUCCESS;
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


    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        init();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub

    }

}
