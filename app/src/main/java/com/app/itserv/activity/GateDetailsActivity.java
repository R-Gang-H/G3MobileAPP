package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.app.itserv.jparser.JsonGHouseGateObject;
import com.app.itserv.jparser.JsonGateDetailObject;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.Map;

/**
 * 大棚网关详情
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/8/30 15:02
 */
public class GateDetailsActivity extends Activity {

    private Context mContext;

    private static final int GATEDETAIL_ERROR = 1;
    private static final int GATEDETAIL_SUCCESS = 2;
    private static final int GATEDETAIL_VALUES = 3;

    private static final String TAG = "GateDetailsActivity";

    private JsonGateDetailObject.ObjBean gateDetailList;//大棚网关详情
    private JsonGHouseGateObject.ObjBean gHousegateBean;//大棚网关列表

    private TextView tvMerchantName, tvGhouseName, tvGateName, tvSnCode, tvUserOwned, tvRegion, tvGateDefault,
            tvLatitude, tvLongitude, tvPlantName, tvArea, tvPlantTrme, tvHarvestTime, tvHarvestWeight,
            tvExpectation, tvContactName, tvContactNumber, tvVersion, tvSimName, tvSimLinenumber, tvSimDevid,
            tvSimImsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gate_detail_lay);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        gHousegateBean = (JsonGHouseGateObject.ObjBean) bundle.getSerializable("gHousegateBean");
        initView();
        init();
    }

    private void initView() {
        tvMerchantName = (TextView) findViewById(R.id.merchant_name);//商户名称
        tvGhouseName = (TextView) findViewById(R.id.ghouse_name);//大棚名称
        tvGateName = (TextView) findViewById(R.id.gate_name);//网关名称
        tvSnCode = (TextView) findViewById(R.id.sn_code);//sn号
        tvUserOwned = (TextView) findViewById(R.id.user_owned);//所属用户
        tvRegion = (TextView) findViewById(R.id.region);//地区
        tvGateDefault = (TextView) findViewById(R.id.gate_default);//网关地址
        tvLatitude = (TextView) findViewById(R.id.latitude);//纬度
        tvLongitude = (TextView) findViewById(R.id.longitude);//经度
        tvPlantName = (TextView) findViewById(R.id.plant_name);//种植作物
        tvArea = (TextView) findViewById(R.id.area);//面积
        tvPlantTrme = (TextView) findViewById(R.id.plant_trme);//种植时间
        tvHarvestTime = (TextView) findViewById(R.id.harvest_time);//收货时间
        tvHarvestWeight = (TextView) findViewById(R.id.harvest_weight);//预估产量
        tvExpectation = (TextView) findViewById(R.id.expectation);//其他
        tvContactName = (TextView) findViewById(R.id.contact_name);//联系人
        tvContactNumber = (TextView) findViewById(R.id.contact_number);//联系人电话
        tvVersion = (TextView) findViewById(R.id.version);//版本号
        tvSimName = (TextView) findViewById(R.id.sim_name);//sim卡名称
        tvSimLinenumber = (TextView) findViewById(R.id.sim_linenumber);//sim卡号码
        tvSimDevid = (TextView) findViewById(R.id.sim_devid);//sim卡用户id
        tvSimImsi = (TextView) findViewById(R.id.sim_imsi);//sim卡序列号
    }

    private void init() {
        new Thread(new GeteDetailsRequest()).start();
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GATEDETAIL_ERROR:// 大棚网关详情请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GATEDETAIL_SUCCESS:
                    String gHouseGate_json = msg.obj.toString();
                    new Thread(new GeteDetailsAction(gHouseGate_json)).start();// 大棚网关详情报文解析线程
                    break;
                case GATEDETAIL_VALUES:
                    tvMerchantName.setText(TextUtils.isEmpty(gateDetailList.getTnantName()) ? "" : gateDetailList.getTnantName());//商户名称
                    tvGhouseName.setText(TextUtils.isEmpty(gateDetailList.getGhouseName()) ? "" : gateDetailList.getGhouseName());//大棚名称
                    tvGateName.setText(TextUtils.isEmpty(gateDetailList.getDev_name()) ? "" : gateDetailList.getDev_name());//网关名称
                    tvSnCode.setText(TextUtils.isEmpty(gateDetailList.getSn()) ? "" : gateDetailList.getSn());//sn号
                    tvUserOwned.setText(TextUtils.isEmpty(gateDetailList.getOwner()) ? "" : gateDetailList.getOwner());//所属用户
                    tvRegion.setText(TextUtils.isEmpty(gateDetailList.getGeogroup()) ? "" : gateDetailList.getGeogroup());//地区
                    tvGateDefault.setText(TextUtils.isEmpty(gateDetailList.getDev_location()) ? "" : gateDetailList.getDev_location());//网关地址
                    tvLatitude.setText(TextUtils.isEmpty(gateDetailList.getLat()) ? "" : gateDetailList.getLat());//纬度
                    tvLongitude.setText(TextUtils.isEmpty(gateDetailList.getLng()) ? "" : gateDetailList.getLng());//经度
                    tvPlantName.setText(TextUtils.isEmpty(gateDetailList.getPlant_name()) ? "" : gateDetailList.getPlant_name());//种植作物
                    tvArea.setText(TextUtils.isEmpty(gateDetailList.getArea()) ? "" : gateDetailList.getArea());//面积
                    tvPlantTrme.setText(TextUtils.isEmpty(String.valueOf(gateDetailList.getPlant_trme())) ? "" : String.valueOf(gateDetailList.getPlant_trme()));//种植时间
                    tvHarvestTime.setText(TextUtils.isEmpty(gateDetailList.getHarvest_time()) ? "" : gateDetailList.getHarvest_time());//收货时间
                    tvHarvestWeight.setText(TextUtils.isEmpty(gateDetailList.getHarvest_weight()) ? "" : gateDetailList.getHarvest_weight());//预估产量
                    tvExpectation.setText(TextUtils.isEmpty(gateDetailList.getExpectation()) ? "" : gateDetailList.getExpectation());//其他
                    tvContactName.setText(TextUtils.isEmpty(gateDetailList.getContact_name()) ? "" : gateDetailList.getContact_name());//联系人
                    tvContactNumber.setText(TextUtils.isEmpty(gateDetailList.getContact_number()) ? "" : gateDetailList.getContact_number());//联系人电话
                    tvVersion.setText(TextUtils.isEmpty(gateDetailList.getVersion()) ? "" : gateDetailList.getVersion());//版本号
                    tvSimName.setText(TextUtils.isEmpty(gateDetailList.getSim_name()) ? "" : gateDetailList.getSim_name());//sim卡名称
                    tvSimLinenumber.setText(TextUtils.isEmpty(String.valueOf(gateDetailList.getSim_linenumber())) ? "" : String.valueOf(gateDetailList.getSim_linenumber()));//sim号码
                    tvSimDevid.setText(TextUtils.isEmpty(String.valueOf(gateDetailList.getSim_devid())) ? "" : String.valueOf(gateDetailList.getSim_devid()));//sim卡用户id
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 大棚网关详情
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: GHouseGeteRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017年08月31日12:38:13
     */
    class GeteDetailsRequest extends Thread {
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
                map.put("smartgateSN", gHousegateBean.getSn());

                Log.v(TAG, TAG + "大棚网关详情请求");
                WapiUtilEx.gatedetails(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GATEDETAIL_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GATEDETAIL_SUCCESS;
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
     * 大棚网关详情json解析
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: GHouseGateAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-11 上午10:35:54
     */
    class GeteDetailsAction extends Thread {

        private String gateDetailsJson;

        public GeteDetailsAction(String gateDetailsJson) {
            // TODO Auto-generated constructor stub
            this.gateDetailsJson = gateDetailsJson;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            if (TextUtils.isEmpty(gateDetailsJson)) {
                TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                return;
            }
            Gson gson = new Gson();
            JsonGateDetailObject jsonGateDetailObject = gson.fromJson(
                    gateDetailsJson, JsonGateDetailObject.class);
            if (!jsonGateDetailObject.equals("")
                    && jsonGateDetailObject != null) {
                if (jsonGateDetailObject.isSuccess()) {// 成功
                    gateDetailList = jsonGateDetailObject.getObj();
                    Message msg = Message.obtain();
                    msg.what = GATEDETAIL_VALUES;
                    mHandler.sendMessage(msg);
                }
            }
        }
    }
}
