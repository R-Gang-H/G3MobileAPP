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
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonGreenHouseViewObject;
import com.app.itserv.jparser.JsonGreenHouseViewObject.AttributesBean;
import com.app.itserv.jparser.JsonGreenHouseViewObject.ObjBean;
import com.app.itserv.jparser.JsonGreenHouseViewObject.ObjBean.GreenhouseBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大棚管理---查看大棚
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: GreenHouseViewActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-12 下午4:29:39
 */

public class GreenHouseViewActivity extends Activity implements OnClickListener {

    private Context mContext;

    public static final String TAG = "GreenHouseViewActivity";
    protected static final int GHOUSEVIEW_ERROR = 1;
    protected static final int GHOUSEVIEW_SUCCESS = 2;
    protected static final int GHOUSEVIEW_VALUES = 3;

    private ImageView ImgBack;
    private TextView tvGreHouName, tvGreHouCode, tvActivaDate, tvBaseStation,
            tvTotalArea, tvPlanArea, tvLength, tvWidth, tvAbout, tvBusinessAdd,
            tvBusinessDetailed, tvMapLocation, tvUseState;

    private ObjBean gHouseViewBean;
    private List<JsonDataDictionaryObject.ObjBean> datadicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gre_hou_view_lay);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        datadicList = (List<JsonDataDictionaryObject.ObjBean>) bundle.getSerializable("datadicList");
        initView();
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        String greenhouseId = (String) getIntent().getExtras().get(
                "greenhouseId");
        new Thread(new GreenHouseExaRequest(greenhouseId)).start();// 大棚查看请求线程
    }

    private void initView() {
        // TODO Auto-generated method stub
        // 初始化控件
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvGreHouName = (TextView) findViewById(R.id.gre_hou_name);// 大棚名称
        tvGreHouCode = (TextView) findViewById(R.id.gre_hou_code);// 大棚编号
        tvActivaDate = (TextView) findViewById(R.id.tv_activa_date);// 启动日期
        tvBaseStation = (TextView) findViewById(R.id.tv_base_station);// 所属基地
        tvTotalArea = (TextView) findViewById(R.id.tv_total_area);// 总面积
        tvPlanArea = (TextView) findViewById(R.id.tv_plan_area);// 种植面积
        tvLength = (TextView) findViewById(R.id.tv_length);// 长度
        tvWidth = (TextView) findViewById(R.id.tv_width);// 宽度
        tvAbout = (TextView) findViewById(R.id.tv_about);// 简介
        tvBusinessAdd = (TextView) findViewById(R.id.tv_business_add);// 地址
        tvBusinessDetailed = (TextView) findViewById(R.id.tv_business_detailed);// 详细地址
        tvMapLocation = (TextView) findViewById(R.id.tv_map_location);// 坐标
        tvUseState = (TextView) findViewById(R.id.tv_use_state);// 使用状态
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

    private void setgHouseViewText() {
        // TODO Auto-generated method stub
        GreenhouseBean Greenhouse = gHouseViewBean.getGreenhouse();
        tvGreHouName
                .setText(TextUtils.isEmpty(Greenhouse.getGhouseFullname()) ? ""
                        : Greenhouse.getGhouseFullname());
        tvGreHouCode.setText(TextUtils.isEmpty(Greenhouse.getGhouseCode()) ? ""
                : Greenhouse.getGhouseCode());
        tvActivaDate.setText(DateLocalUtil.getDate(TextUtils.isEmpty(String
                .valueOf(Greenhouse.getOpenDateOpen())) ? "" : String
                .valueOf(Greenhouse.getOpenDateOpen())));
        tvBaseStation.setText(TextUtils.isEmpty(String.valueOf(Greenhouse
                .getBaseFullname())) ? "" : String.valueOf(Greenhouse
                .getBaseFullname()));
        tvTotalArea.setText(TextUtils.isEmpty(Greenhouse.getCoveredArea()) ? ""
                : Greenhouse.getCoveredArea());
        tvPlanArea.setText(TextUtils.isEmpty(Greenhouse.getUsedArea()) ? ""
                : Greenhouse.getUsedArea());
        tvLength.setText(TextUtils.isEmpty(Greenhouse.getAreaLength()) ? ""
                : Greenhouse.getAreaLength());
        tvWidth.setText(TextUtils.isEmpty(Greenhouse.getAreaWidth()) ? ""
                : Greenhouse.getAreaWidth());
        tvAbout.setText(TextUtils.isEmpty(Greenhouse.getContent()) ? ""
                : Greenhouse.getContent());
        tvBusinessAdd.setText(Greenhouse.getRegionIdCountry()
                + Greenhouse.getRegionIdProvince()
                + Greenhouse.getRegionIdCity()
                + Greenhouse.getRegionIdDistrict());
        tvBusinessDetailed
                .setText(TextUtils.isEmpty(Greenhouse.getRegionAddr()) ? ""
                        : Greenhouse.getRegionAddr());
        tvMapLocation.setText(String.format("纬度%s,经度%s",
                Greenhouse.getLatitude(), Greenhouse.getLongitude()));
        String UseState = TextUtils.isEmpty(Greenhouse.getStatus()) ? "" : Greenhouse.getStatus();
        for (int i = 0; i < datadicList.size(); i++) {
            String strm = datadicList.get(i).getTypecode();
            if (UseState.equals(strm)) {
                tvUseState.setText(datadicList.get(i).getTypename());
            }
        }
    }

    /**
     * 大棚查看json解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseExaAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12 上午11:02:27
     */
    class GreenHouseExaAction extends Thread {

        private String gHouseExaJson;

        public GreenHouseExaAction(String gHouseExa_json) {
            // TODO Auto-generated constructor stub
            this.gHouseExaJson = gHouseExa_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(gHouseExaJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonGreenHouseViewObject gHouse_View = gson.fromJson(
                        gHouseExaJson, JsonGreenHouseViewObject.class);

                if (!gHouse_View.equals("") && gHouse_View != null) {
                    if (gHouse_View.isSuccess()) {// 成功

                        AttributesBean attributesbean = gHouse_View
                                .getAttributes();
                        attributesbean.getYyGreenhouseDocList();
                        attributesbean.getYyGreenhouseUserEntityList();
                        attributesbean.getSmartgateSize();
                        attributesbean.getSingleproSize();
                        attributesbean.getCurrUserId();
                        attributesbean.getCurrTenantId();

                        gHouseViewBean = gHouse_View.getObj();

                        Message msg = Message.obtain();
                        msg.what = GHOUSEVIEW_VALUES;
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
                case GHOUSEVIEW_ERROR:// 大棚删除请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GHOUSEVIEW_SUCCESS:
                    String gHouseExa_json = msg.obj.toString();
                    new Thread(new GreenHouseExaAction(gHouseExa_json)).start();// 大棚查看报文解析线程
                    break;
                case GHOUSEVIEW_VALUES:
                    setgHouseViewText();// 赋值
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 大棚查看请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseVeRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12 上午10:55:51
     */
    class GreenHouseExaRequest extends Thread {

        private String greenhouseId;

        public GreenHouseExaRequest(String greenhouseId) {
            // TODO Auto-generated constructor stub
            this.greenhouseId = greenhouseId;
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
                map.put("greenhouseId", greenhouseId);

                Log.v(TAG, TAG + "大棚查看请求");
                WapiUtilEx.ghouseexaview(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEVIEW_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEVIEW_SUCCESS;
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
