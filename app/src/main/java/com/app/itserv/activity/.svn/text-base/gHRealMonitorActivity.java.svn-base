package com.app.itserv.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.app.itserv.MineApplication;
import com.app.itserv.adapters.GreenHouseGatMoSmartSensorAdapter;
import com.app.itserv.adapters.gHouseGatSensorAdapter;
import com.app.itserv.fragments.WebCameraFragment;
import com.app.itserv.jparser.JsonGreenHouseGatMoSmartSensorObject;
import com.app.itserv.jparser.JsongHouseAllRelayObject;
import com.app.itserv.views.LoadingFrameView;
import com.google.gson.Gson;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WAPI;
import com.yycloud.app.utils.WapiUtil;
import com.yycloud.core.beans.Components;
import com.yycloud.core.beans.RelayBoxSocketBean;
import com.yycloud.core.beans.ShedInfo;
import com.yycloud.core.beans.SocketIobeans;
import com.yycloud.core.config.Constants;
import com.yycloud.core.config.DeviceType;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;

import hsl.p2pipcam.manager.Device;
import hsl.p2pipcam.manager.DeviceManager;
import hsl.p2pipcam.manager.listener.DeviceStatusListener;
import hsl.p2pipcam.service.BridgeService;
import io.socket.emitter.Emitter;

/**
 * 实时监控
 * 功能  权限 管理  只有管理员可以控制   员工只能看
 * @author Administrator
 */
public class gHRealMonitorActivity extends FragmentActivity implements OnClickListener, AdapterView.OnItemClickListener, DeviceStatusListener {

    private static final String TAG = "RealMonitorActivity";
    private Context mContext;
    private ImageView imgBack;

    private LoadingFrameView ldDeviceControl;
    private LinearLayout llCamerasDeviceControl, llErelayDeviceControl,
            llErelaySwitchDeviceControl, llErelay2DeviceControl, llErealyBoxDeviceControl, llRelayBoxDialog, llDeviceControl;
    private TextView tvMosmartName;//实时监控名称
    private ListView lvDataSensorMonitor; //传感器设备控制
    // 填充器
    private LayoutInflater inflater;

    private String ghouseFullName, sgateSN, gHouseId;

    private Button greenHousesDetails;

    private List<JsonGreenHouseGatMoSmartSensorObject.ObjBean> gHouseObjSensorList;
    private GreenHouseGatMoSmartSensorAdapter greenHouseGatMoSmartSensorAdapter;


    protected final static int MSG_ERELAY_BOX = 4008;// 电器柜
    protected final static int MSG_ERELAY_CTRL = 5000;// 单机电器
    protected final static int MSG_ERELAY2_CTRL = 4000;// 双继电器
    protected final static int MSG_ERELAY2_CALLBRATION = 4200;// 校准
    protected static final int SOCKET_ECHO = 10;
    protected static final int ERROR_ECHO = 11;
    private List<JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean> mCameras = new ArrayList<JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean>();//摄像头设备集合
    private ArrayList<FrameLayout> resIds = new ArrayList<FrameLayout>();//摄像头布局FrameLayout
    private DeviceManager deviceManager;// 摄像头监听
    private ArrayList<WebCameraFragment> webCameraFragments = new ArrayList<WebCameraFragment>();
    private List<Components> components;
    private Components mComponents;// 每个设备
    private ShedInfo shedInfo;
    String method = null;
    Gson gson = new Gson();
    private TimerTask task;// 任务
    private Timer timer = new Timer();// 定时器
    private boolean isTast = true;// 管理任务执行
    private  gHouseGatSensorAdapter gHouseGatSensorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//解决 Fragment 加载闪屏问题
        setContentView(R.layout.device_control_layout);
        mContext = this;
        deviceManager = DeviceManager.getDeviceManager(this);
        deviceManager.setDeviceStatusListener(this);
        initView();
        init();
        initSocket();
    }


    private void initSocket() {
        HomeActivity.socket.on("dev_push_to_" + PreferencesUtils.getString(gHRealMonitorActivity.this, "userName"),// 监听根据返回的数据更新界面
                new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        JSONObject jsonObject = (JSONObject) args[0];
                        Gson gson = new Gson();
                        SocketIobeans fromJson;
                        try {
                            fromJson = gson.fromJson(jsonObject.toString(),
                                    SocketIobeans.class);
                            Log.v("Socket", "==========控制器监听===========" + jsonObject.toString());
                            String dev_type = fromJson.getData().getInfo()
                                    .getDev_type();

                            if ("relaybox".equals(dev_type)) {
                                Message message = new Message();
                                message.obj = jsonObject;
                                message.what = MSG_ERELAY_BOX;
                                message.arg1 = SOCKET_ECHO;
                                mHandler.sendMessage(message);
                                // Log.v("------==========------",
                                // jsonObject.toString());

                            } else if ("erelay".equals(dev_type)) {
                                Message message = new Message();
                                message.obj = fromJson;
                                message.arg1 = SOCKET_ECHO;
                                // Log.v("------==========------",
                                // jsonObject.toString());
                                message.what = MSG_ERELAY_CTRL;
                                mHandler.sendMessage(message);

                            } else if ("erelay2".equals(dev_type)) {
                                Message message = new Message();
                                message.obj = fromJson;
                                message.what = MSG_ERELAY2_CTRL;
                                message.arg1 = SOCKET_ECHO;
                                mHandler.sendMessage(message);

                                if (DeviceType.erelay_ventilation
                                        .equals(fromJson.getData().getInfo()
                                                .getDev_extend_type())) {

                                    String result = fromJson.getData()
                                            .getInfo().getConfig_info()
                                            .getResult();
                                    String version = fromJson.getData()
                                            .getInfo().getConfig_info()
                                            .getVersion();
                                    if ("0".equals(result)
                                            && "2".equals(version)) {
                                        Message messages = new Message();
                                        messages.obj = fromJson;
                                        messages.what = MSG_ERELAY2_CALLBRATION;
                                        mHandler.sendMessage(messages);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    private void init() {
        Intent intent = getIntent();

        gHouseId = (String) intent.getExtras().get("gHouseId");
        ghouseFullName = (String) intent.getExtras().get("ghouseFullName");
        if (gHouseId != null) {
            getgHouseAllRelayList(gHouseId);

            timeTest();
        } else {
            ldDeviceControl.setNoShown(true);
        }
        if (ghouseFullName != null) {
            tvMosmartName.setText(ghouseFullName);
        }
    }

    private void timeTest() {
        try {

            task = new TimerTask() {
                @Override
                public void run() {
                    if (isTast) {

                    }
                }
            };
            timer.schedule(task, 0, 10000);
        } catch (Exception e) {
        }

    }

    private void initView() {
        // TODO Auto-generated method stub
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imgBack = (ImageView) findViewById(R.id.img_back);// 退出
        imgBack.setOnClickListener(this);
        ldDeviceControl = (LoadingFrameView) findViewById(R.id.ld_device_control);// 设备控制
        tvMosmartName = (TextView) findViewById(R.id.tv_mosmart_name);// 网关名称
        lvDataSensorMonitor = (ListView) findViewById(R.id.lv_data_sensor_monitor);// 传感器设备控制
        lvDataSensorMonitor.setOnItemClickListener(this);//传感器设备控制项点击事件
        llCamerasDeviceControl = (LinearLayout) findViewById(R.id.ll_cameras_device_control);// 摄像头
        llErelayDeviceControl = (LinearLayout) findViewById(R.id.ll_erelay_device_control);// 单继电器
        llErelaySwitchDeviceControl = (LinearLayout) findViewById(R.id.ll_erelay_switch_device_control);// 单继电器开关
        llErelay2DeviceControl = (LinearLayout) findViewById(R.id.ll_erelay2_device_control);// 双继电器
        llErealyBoxDeviceControl = (LinearLayout) findViewById(R.id.ll_erealy_box_device_control);// 电器柜
        llDeviceControl = (LinearLayout) findViewById(R.id.ll_device_control);//网关
        greenHousesDetails = (Button) findViewById(R.id.greenhouses_details);//大棚详情
        greenHousesDetails.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.greenhouses_details:

                startActivity(new Intent(mContext, ShedSetingActivity.class).putExtra("shedInfo", shedInfo));//所有
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        JsonGreenHouseGatMoSmartSensorObject.ObjBean objBean = gHouseObjSensorList.get(position);
        startActivity(new Intent(mContext, EnvironmentalCurveActivity.class).putExtra("devSN", objBean.getSn()).putExtra("devType", objBean.getDeviceType()).putExtra("DevName", objBean.getDeviceName()));
    }

    @Override
    public void receiveDeviceStatus(long userid, int status) {
// TODO Auto-generated method stub
        for (int i = 0; i < mCameras.size(); i++) {
            Log.v("mCameras.get(i)", mCameras.get(i).getSn());
            // 解决获取空值
            if (mCameras.equals(null) || mCameras.equals("")) {
                // Log.d("mCameras.get(i)", mCameras.get(i).getSn() + "等于空了");
                continue;

            }
            Device device = null;
            device = webCameraFragments.get(i).getDevice();
            if (device == null)
                continue;
            if (device.getUserid() == userid) {
                Handler hdl = webCameraFragments.get(i).getHandler();
                Message msg = hdl.obtainMessage(0, status, 0);
                hdl.sendMessage(msg);
            }
        }
    }

    @Override
    public void receiveDeviceImage(long userid, Bitmap bitmap) {

    }

    @Override
    public void loadListData(List<Device> data) {

    }

    @Override
    public void receiveDeviceZoneArming(long userid, int zone_arming) {

    }

    /*---------------------------------------------大棚网关传感器列表Start--------------------------------------------------------*/

    /**
     * @author jcy
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseAction
     * @Description: TODO(大棚网关传感器列表数据请求)
     * @date 2017-7-10 下午4:20:50
     */
    private void getMoSmartSensorList(final String sgateSN) {
        String token = PreferencesUtils.getString(mContext, "token");// 用户token
        String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id
        HttpManager.getInstance().gAllSensorInfo(TAG, token, currTenantId, sgateSN, new HttpCallBack<JsonGreenHouseGatMoSmartSensorObject>() {

            @Override
            public void onSuccess(JsonGreenHouseGatMoSmartSensorObject date) {
                if (date != null) {
                    String message = date.getMsg();// 提示消息
                    if (date.isSuccess()) {
                        gHouseObjSensorList = date.getObj();
                        if (gHouseObjSensorList == null || gHouseObjSensorList.size() <= 0) {

                            return;
                        }
                        if (greenHouseGatMoSmartSensorAdapter == null) {
                            greenHouseGatMoSmartSensorAdapter = new GreenHouseGatMoSmartSensorAdapter(
                                    mContext, gHouseObjSensorList);
                            lvDataSensorMonitor
                                    .setAdapter(greenHouseGatMoSmartSensorAdapter);
                        } else {
                            greenHouseGatMoSmartSensorAdapter
                                    .setList(gHouseObjSensorList);
                            greenHouseGatMoSmartSensorAdapter.notifyDataSetChangeds();
                        }

                    } else {// 失败
                        if (!TextUtils.isEmpty(message)) {

                            ToastUtils.makeTextShort(message);
                        } else {

                            ToastUtils.makeTextShort("网络不可用!");
                        }
                    }
                } else {

                }
            }

            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort("网络不可用!");

            }
        });

    }

    /*---------------------------------------------大棚网关传感器列表End--------------------------------------------------------*/

    /**
     * *@Description: TODO(获取当前大棚的设备)
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    private void getgHouseAllRelayList(final String gHouseId) {
        String token = PreferencesUtils.getString(mContext, "token");// 用户token
        String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id
        HttpManager.getInstance().gHouseGetAllRelayList(TAG, token, currTenantId, gHouseId, new HttpCallBack<JsongHouseAllRelayObject>() {

            @Override
            public void onSuccess(JsongHouseAllRelayObject date) {
                if (date != null) {
                    String message = date.getMsg();// 提示消息
                    if (date.isSuccess()) {
                        JsongHouseAllRelayObject.ObjBean obj = date.getObj();
                        if ( obj== null  || obj.getReControllerEntitySize()==0 &&obj.getReSensorDeviceSize() == 0 && obj.getReSmartgateInfosSize() == 0 ){
                            ldDeviceControl.setNoShown(true);
                            return;
                        }
                        showController(obj.getReControllerEntityList());

                        showSensorDevice(obj.getReSensorDeviceList());

                        showreSmartgateInfos(obj.getReSmartgateInfosList());
                        ldDeviceControl.delayShowContainer(true);
                    } else { // 失败
                        if (!TextUtils.isEmpty(message)) {
                            ToastUtils.makeTextShort(message);
                        } else {
                            ToastUtils.makeTextShort("网络不可用!");
                        }
                        ldDeviceControl.setNoShown(true);
                    }
                } else {
                    ldDeviceControl.setNoShown(true);
                }

            }

            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort("网络不可用!");
                ldDeviceControl.setErrorShown(true, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ldDeviceControl.setProgressShown(true);
                        getgHouseAllRelayList(gHouseId);
                    }
                });
            }
        });
    }

    private void showreSmartgateInfos(List<JsongHouseAllRelayObject.ObjBean.ReSmartgateInfosListBean> reSmartgateInfosList) {//网关
        llDeviceControl.removeAllViews();


    }

    private void showSensorDevice(List<JsongHouseAllRelayObject.ObjBean.ReSensorDeviceListBean> reSensorDeviceList) {//传感器
        if (gHouseGatSensorAdapter == null ) {
            gHouseGatSensorAdapter = new gHouseGatSensorAdapter(this, reSensorDeviceList);
            lvDataSensorMonitor.setAdapter(gHouseGatSensorAdapter);
        }else{
            gHouseGatSensorAdapter.setList(reSensorDeviceList);
            gHouseGatSensorAdapter.notifyDataSetChanged();
        }
    }

    private void showController(List<JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean> reControllerEntityList) {//控制器

            llErelayDeviceControl.removeAllViews();
            llErelaySwitchDeviceControl.removeAllViews();
            llErelay2DeviceControl.removeAllViews();
            llErealyBoxDeviceControl.removeAllViews();

            for (int i = 0; i < reControllerEntityList.size(); i++) {
                final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean co = reControllerEntityList.get(i);
                if (null == co) {
                    continue;
                }

                // 单向继电器
                if (DeviceType.erelay.equals(co.getDevType())) {

                    if (co.getSwitchs() == null
                            || co.getSwitchs().equalsIgnoreCase(
                            Constants.UNDEFINED)) {

                        co.setSwitchs(Constants.HAND_CONTROL);
                    }

                    // 开关
                    if (DeviceType.erelay_switch.equals(co
                            .getDevExtendType())) {
                        initErelaySwitch(co);
                        continue;
                    }
                    // 水阀,灯
                    initErelay(co);
                    // 双向继电器
                } else if (DeviceType.erelay2.equals(co.getDevType())) {

                    if (co.getSwitchs() == null
                            || "undefined".equalsIgnoreCase(co
                            .getSwitchs())) {

                        co.setSwitchs(Constants.HAND_CONTROL);
                    }

                    initErelay2(co);

                    // 电气柜
                } else if (DeviceType.relaybox.equals(co.getDevType())) {
                    initRelayBox(co);

                    // 摄像头
                } else if (co.getDevType().equals(DeviceType.cameraip)) {
                    mCameras.add(co);
                } else {
                    ldDeviceControl.setNoShown(true);
                }
            }

        initWifiCameraData();
        // 加载摄像头fragment
        loadFragment();

    }
    /*---------------------------------------------获取当前网关的设备列表Start---------------------------------------------------*/


     /*---------------------------------------------获取当前网关的设备列表End---------------------------------------------------*/

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case MSG_ERELAY_BOX:
                    if (msg.arg1 == SOCKET_ECHO) {
                        mHandler.removeMessages(WAPI.MSG_ERELAY_BOX);
                        SocketIobeans relayBoxJson = (SocketIobeans) msg.obj;
                        RelayBoxSocketBean relayBox = gson.fromJson(relayBoxJson.toString(), RelayBoxSocketBean.class);
                        View relayBoxView = ViewMap.get(relayBox.getData().getInfo().getSn());
                        View relayBoxDialog = ViewMap.get(relayBox.getData().getInfo().getSn() + "box");
                        JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean mComponents = relayMap.get(relayBox.getData().getInfo().getSn());
                        if (relayBox != null && relayBoxView != null && relayBoxDialog != null && mComponents != null) {
                            List<JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list> mPool_list = mComponents.getPool_list();
                            String remote = String.valueOf(relayBox.getData().getInfo().getRemote());
                            final TextView state = (TextView) relayBoxDialog.findViewById(R.id.relaybox_state);
                            final TextView control = (TextView) relayBoxDialog.findViewById(R.id.relaybox_control);
                            final TextView relayboxStatus = (TextView) relayBoxView.findViewById(R.id.relaybox_status);
                            if (relayBox.getData().getState().getOnline_state().equals(Constants.STATUS_ONLINE) && "1".equals(remote)) {

                                mComponents.setOnlineState(Constants.STATUS_ONLINE);
                                state.setText("在线");
                                state.setTextSize(12);
                                state.setTextColor(Color.BLACK);
                                relayboxStatus.setText("在线");
                                relayboxStatus.setTextSize(12);
                                relayboxStatus.setTextColor(Color.BLACK);

                                mComponents.setRemote("1");
                                control.setText("远程控制");
                                control.setTextColor(Color.BLACK);

                                List<RelayBoxSocketBean.DataBean.LastValueBean.PoolListBean> pool_list = relayBox.getData().getLast_value().getPool_list();
                                for (int i = 0; i < pool_list.size(); i++) {
                                    String dev_type = pool_list.get(i).getDev_type();
                                    if ("SINGLE".equals(dev_type)) {

                                        View relaySingle = ViewMap.get(relayBox.getData().getInfo().getSn() + pool_list.get(i).getPool_index());
                                        if (relaySingle != null) {
                                            final ImageView button_openRelay = (ImageView) relaySingle.findViewById(R.id.button_open);
                                            final TextView erelay_statusRelay = (TextView) relaySingle.findViewById(R.id.erelay_status);
                                            button_openRelay.setEnabled(true);
                                            if (pool_list.get(i).getStatus() == 0 || pool_list.get(i).getStatus() == 2) {
                                                button_openRelay.setImageResource(R.drawable.button_close);
                                                mPool_list.get(i).setStatus(0);
                                                erelay_statusRelay.setText("关闭");
                                            } else {
                                                button_openRelay.setImageResource(R.drawable.button_open);
                                                erelay_statusRelay.setText("打开");
                                                mPool_list.get(i).setStatus(1);
                                            }
                                        } else if ("DOUBLE".equals(dev_type)) {

                                            View relayDouble = ViewMap.get(relayBox.getData().getInfo().getSn() + pool_list.get(i).getPool_index());

                                            if (relayDouble != null) {

                                                final TextView backRelay = (TextView) relayDouble.findViewById(R.id.backRelay);
                                                final TextView forwardRelay = (TextView) relayDouble.findViewById(R.id.forwardRelay);
                                                final TextView stopRelay = (TextView) relayDouble.findViewById(R.id.stopRelay);

                                                if (backRelay != null && forwardRelay != null && stopRelay != null) {
                                                    setErelayButtonStatus(forwardRelay, stopRelay, backRelay, String.valueOf(pool_list.get(i).getStatus()), true);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {

                                if (relayBox.getData().getState().getOnline_state().equals(Constants.STATUS_OFFLINE)) {
                                    mComponents.setOnlineState(Constants.STATUS_OFFLINE);
                                    state.setText("离线");
                                    state.setTextSize(12);
                                    state.setTextColor(Color.RED);
                                    relayboxStatus.setText("离线");
                                    relayboxStatus.setTextSize(12);
                                    relayboxStatus.setTextColor(Color.RED);
                                }

                                if ("0".equals(remote)) {

                                    mComponents.setRemote("0");
                                    control.setText("本地控制");
                                    control.setTextColor(Color.RED);

                                }
                                List<RelayBoxSocketBean.DataBean.LastValueBean.PoolListBean> pool_list = relayBox.getData().getLast_value().getPool_list();
                                for (RelayBoxSocketBean.DataBean.LastValueBean.PoolListBean relaybox : pool_list) {
                                    String dev_type = relaybox.getDev_type();
                                    if ("SINGLE".equals(dev_type)) {

                                        View relaySingle = ViewMap.get(relayBox.getData().getInfo().getSn() + relaybox.getPool_index());
                                        if (relaySingle != null) {

                                            final ImageView button_openRelay = (ImageView) relaySingle.findViewById(R.id.button_open);
                                            final TextView erelay_statusRelay = (TextView) relaySingle.findViewById(R.id.erelay_status);
                                            if (button_openRelay != null && erelay_statusRelay != null) {
                                                button_openRelay.setEnabled(false);
                                                if (relaybox.getStatus() == 0 || relaybox.getStatus() == 2) {
                                                    button_openRelay.setImageResource(R.drawable.button_close);
                                                    erelay_statusRelay.setText("关闭");
                                                } else {
                                                    button_openRelay.setImageResource(R.drawable.button_open);
                                                    erelay_statusRelay.setText("打开");
                                                }
                                            }
                                        }
                                    } else if ("DOUBLE".equals(dev_type)) {

                                        View relayDouble = ViewMap.get(relayBox.getData().getInfo().getSn() + relaybox.getPool_index());

                                        if (relayDouble != null) {
                                            final TextView backRelay = (TextView) relayDouble.findViewById(R.id.backRelay);
                                            final TextView forwardRelay = (TextView) relayDouble.findViewById(R.id.forwardRelay);
                                            final TextView stopRelay = (TextView) relayDouble.findViewById(R.id.stopRelay);
                                            if (backRelay != null && forwardRelay != null && stopRelay != null) {
                                                setErelayButtonStatus(forwardRelay, stopRelay, backRelay, String.valueOf(relaybox.getStatus()), false);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {// 延时消息
                        final String sn = (String) msg.obj;
                        final View relayBoxView = ViewMap.get(sn);
                        final View relayBoxDialog = ViewMap.get(sn + "box");

                        MineApplication.executorService.submit(new Runnable() {
                            @Override
                            public void run() {
                                String deviceinfos = WapiUtil.getDeviceinfos(sn);
                                Gson gson = new Gson();
                                final Components mComponents = gson.fromJson(deviceinfos, Components.class);

                                JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean components = relayMap.get(sn);

                                if (mComponents != null && relayBoxView != null && relayBoxDialog != null && mComponents != null) {
                                    String remote = String.valueOf(mComponents.getRemote());
                                    final TextView state = (TextView) relayBoxDialog.findViewById(R.id.relaybox_state);
                                    final TextView control = (TextView) relayBoxDialog.findViewById(R.id.relaybox_control);
                                    final TextView relayboxStatus = (TextView) relayBoxView.findViewById(R.id.relaybox_status);
                                    if (mComponents.getOnline_state().equals(Constants.STATUS_ONLINE) && "1".equals(remote)) {

                                        state.setText("在线");
                                        state.setTextSize(12);
                                        state.setTextColor(Color.BLACK);
                                        relayboxStatus.setText("在线");
                                        relayboxStatus.setTextSize(12);
                                        relayboxStatus.setTextColor(Color.BLACK);

                                        control.setText("远程控制");
                                        control.setTextColor(Color.BLACK);

                                        List<Components.Pool_list> pool_list = mComponents.getPool_list();
                                        for (Components.Pool_list relaybox : pool_list) {
                                            String dev_type = relaybox.getDev_type();
                                            if ("SINGLE".equals(dev_type)) {

                                                View relaySingle = ViewMap.get(mComponents.getSn() + relaybox.getPool_index());
                                                if (relaySingle != null) {
                                                    final ImageView button_openRelay = (ImageView) relaySingle.findViewById(R.id.button_open);
                                                    final TextView erelay_statusRelay = (TextView) relaySingle.findViewById(R.id.erelay_status);
                                                    button_openRelay.setEnabled(true);
                                                    if (relaybox.getStatus() == 0 || relaybox.getStatus() == 2) {
                                                        button_openRelay.setImageResource(R.drawable.button_close);
                                                        erelay_statusRelay.setText("关闭");
                                                    } else {
                                                        button_openRelay.setImageResource(R.drawable.button_open);
                                                        erelay_statusRelay.setText("打开");
                                                    }
                                                } else if ("DOUBLE".equals(dev_type)) {

                                                    View relayDouble = ViewMap.get(mComponents.getSn() + relaybox.getPool_index());

                                                    if (relayDouble != null) {

                                                        final TextView backRelay = (TextView) relayDouble.findViewById(R.id.backRelay);
                                                        final TextView forwardRelay = (TextView) relayDouble.findViewById(R.id.forwardRelay);
                                                        final TextView stopRelay = (TextView) relayDouble.findViewById(R.id.stopRelay);

                                                        if (backRelay != null && forwardRelay != null && stopRelay != null) {
                                                            setErelayButtonStatus(forwardRelay, stopRelay, backRelay, String.valueOf(relaybox.getStatus()), true);

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {

                                        if (mComponents.getOnline_state().equals(Constants.STATUS_OFFLINE)) {
                                            state.setText("离线");
                                            state.setTextSize(12);
                                            state.setTextColor(Color.RED);
                                            relayboxStatus.setText("离线");
                                            relayboxStatus.setTextSize(12);
                                            relayboxStatus.setTextColor(Color.RED);
                                        }

                                        if ("0".equals(remote)) {

                                            control.setText("本地控制");
                                            control.setTextColor(Color.RED);

                                        }
                                        List<Components.Pool_list> pool_list = mComponents.getPool_list();
                                        for (Components.Pool_list relaybox : pool_list) {
                                            String dev_type = relaybox.getDev_type();
                                            if ("SINGLE".equals(dev_type)) {

                                                View relaySingle = ViewMap.get(mComponents.getSn() + relaybox.getPool_index());
                                                if (relaySingle != null) {

                                                    final ImageView button_openRelay = (ImageView) relaySingle.findViewById(R.id.button_open);
                                                    final TextView erelay_statusRelay = (TextView) relaySingle.findViewById(R.id.erelay_status);
                                                    if (button_openRelay != null && erelay_statusRelay != null) {
                                                        button_openRelay.setEnabled(false);
                                                        if (relaybox.getStatus() == 0 || relaybox.getStatus() == 2) {
                                                            button_openRelay.setImageResource(R.drawable.button_close);
                                                            erelay_statusRelay.setText("关闭");
                                                        } else {
                                                            button_openRelay.setImageResource(R.drawable.button_open);
                                                            erelay_statusRelay
                                                                    .setText("打开");
                                                        }
                                                    }
                                                }
                                            } else if ("DOUBLE".equals(dev_type)) {

                                                View relayDouble = ViewMap.get(mComponents.getSn() + relaybox.getPool_index());

                                                if (relayDouble != null) {
                                                    final TextView backRelay = (TextView) relayDouble.findViewById(R.id.backRelay);
                                                    final TextView forwardRelay = (TextView) relayDouble.findViewById(R.id.forwardRelay);
                                                    final TextView stopRelay = (TextView) relayDouble.findViewById(R.id.stopRelay);
                                                    if (backRelay != null && forwardRelay != null && stopRelay != null) {
                                                        setErelayButtonStatus(forwardRelay, stopRelay, backRelay, String.valueOf(relaybox.getStatus()), false);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                    break;
                case MSG_ERELAY_CTRL:
                    int tag = msg.arg1;
                    if (tag == 10) {
                        mHandler.removeMessages(MSG_ERELAY_CTRL);
                        SocketIobeans erelay = (SocketIobeans) msg.obj;
                        View relayView = ViewMap.get(erelay.getData().getInfo().getSn());
                        JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean mComponents = relayMap.get(erelay.getData().getInfo().getSn());

                        if (erelay != null && relayView != null
                                && mComponents != null) {
                            TextView erelayName = (TextView) relayView.findViewById(R.id.erelay_name);

                            final ImageView buttonOpen = (ImageView) relayView.findViewById(R.id.button_open);// 开关按钮

                            TextView erelayStatus = (TextView) relayView.findViewById(R.id.erelay_status);// 设备开关

                            TextView relayOneStatus = (TextView) relayView.findViewById(R.id.tv_relay_one_status);// 设备状态

                            TextView erelayHandStatus = (TextView) relayView.findViewById(R.id.erelay_handstatus);// 手
                            final ToggleButton erelayisauto = (ToggleButton) relayView.findViewById(R.id.erelay_tb_btn);// 模式切换
                            TextView erelayAutoStatus = (TextView) relayView.findViewById(R.id.erelay_autostatus);// 自

                            String status = erelay.getData().getLast_value().getStatus() + "";

                            if ("erelay-switch".equals(erelay.getData().getInfo().getDev_extend_type())) {

                                TextView erelayPower = (TextView) relayView.findViewById(R.id.erelay_power);

                                erelayPower.setText(erelay.getData().getInfo().getPower() + " W");

                                mComponents.setPower(erelay.getData().getInfo().getPower());

                                mComponents.setChargeTotal(erelay.getData().getInfo().getCharge_total());
                            }

                            mComponents.setOnlineState(erelay.getData().getState().getOnline_state());//设置本地状态

                            if (erelay.getData().getState().getOnline_state().equals("online")) {
                                buttonOpen.setEnabled(true);
                                erelayisauto.setChecked(true);
                                relayOneStatus.setText("在线");
                                relayOneStatus.setTextColor(Color.BLACK);

                            } else {
                                buttonOpen.setEnabled(false);
                                erelayisauto.setChecked(false);
                                relayOneStatus.setText("离线");
                                relayOneStatus.setTextColor(Color.RED);

                            }

                            mComponents.setSwitchs(erelay.getData().getInfo().getSwitchs());//设置本地自控状态
                            if ("11".equals(erelay.getData().getInfo().getSwitchs())) {
                                if (erelay.getData().getState().getOnline_state().equals("online")) {

                                    buttonOpen.setEnabled(true);
                                    erelayisauto.setChecked(true);

                                } else {
                                    buttonOpen.setEnabled(false);
                                    erelayisauto.setChecked(false);

                                }
                                // erelay_tvstras.setText("手动控制");
                                erelayHandStatus.setTextColor(Color.parseColor("#25C730"));

                                erelayAutoStatus.setTextColor(Color.GRAY);

                                erelayisauto.setBackgroundResource(R.drawable.button_open);

                            } else if ("12".equals(erelay.getData().getInfo().getSwitchs())) {
                                // erelay_tvstras.setText("自动控制");
                                erelayHandStatus.setTextColor(Color.GRAY);
                                erelayAutoStatus.setTextColor(Color
                                        .parseColor("#25C730"));
                                buttonOpen.setEnabled(false);
                                erelayisauto.setChecked(false);
                                erelayisauto.setBackgroundResource(R.drawable.button_close);
                            }

                            if (status.equals("1")) {
                                PreferencesUtils.putInt(mContext, erelay.getData().getInfo().getSn(), 1);
                                mComponents.setStatus("1");
                                buttonOpen.setImageResource(R.drawable.button_open);

                                erelayStatus.setText("打开");

                            } else if (status.equals("0") || status.equals("2")) {

                                PreferencesUtils.putInt(mContext, erelay.getData().getInfo().getSn(), 0);
                                mComponents.setStatus("0");
                                buttonOpen.setImageResource(R.drawable.button_close);

                                erelayStatus.setText("关闭");
                            }

                        }
                    } else {
                        final String sn = (String) msg.obj;

                        if (sn != null) {
                            final View erelay = ViewMap.get(sn);
                            MineApplication.executorService.submit(new Runnable() {
                                @Override
                                public void run() {
                                    String deviceinfos = WapiUtil.getDeviceinfos(sn);
                                    Log.v(TAG, deviceinfos);

                                    Gson gson = new Gson();
                                    final Components mComponents = gson.fromJson(
                                            deviceinfos, Components.class);
                                    final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean components = relayMap.get(sn);
                                    ((Activity) mContext).runOnUiThread(new Runnable() {
                                        public void run() {
                                            String status = mComponents.getStatus();
                                            String switchs = mComponents.getSwitchs();
                                            String dev_extend_type = mComponents.getDev_extend_type();
                                            if (erelay != null) {
                                                final ImageView buttonOpen = (ImageView) erelay.findViewById(R.id.button_open);// 开关按钮

                                                TextView erelayStatus = (TextView) erelay.findViewById(R.id.erelay_status);// 设备开关

                                                TextView relayOneStatus = (TextView) erelay.findViewById(R.id.tv_relay_one_status);// 设备状态

                                                TextView erelayHandStatus = (TextView) erelay.findViewById(R.id.erelay_handstatus);// 手
                                                final ToggleButton erelayisauto = (ToggleButton) erelay.findViewById(R.id.erelay_tb_btn);// 模式切换
                                                TextView erelayAutoStatus = (TextView) erelay.findViewById(R.id.erelay_autostatus);// 自

                                                if ("erelay-switch".equals(dev_extend_type)) {
                                                    TextView erelayPower = (TextView) erelay.findViewById(R.id.erelay_power);
                                                    erelayPower.setText(mComponents.getPower() + " mW");

                                                }
                                                components.setSwitchs(switchs);
                                                components.setOnlineState(mComponents.getOnline_state());
                                                if (mComponents.getOnline_state().equals("online")) {
                                                    buttonOpen.setEnabled(true);
                                                    erelayisauto.setChecked(true);
                                                    relayOneStatus.setText("在线");
                                                    relayOneStatus.setTextColor(Color.BLACK);

                                                } else {
                                                    buttonOpen.setEnabled(false);
                                                    erelayisauto.setChecked(false);
                                                    relayOneStatus.setText("离线");
                                                    relayOneStatus.setTextColor(Color.RED);

                                                }

                                                if ("11".equals(switchs)) {
                                                    if (mComponents.getOnline_state().equals("online")) {
                                                        buttonOpen.setEnabled(true);
                                                        erelayisauto.setChecked(true);

                                                    } else {
                                                        buttonOpen.setEnabled(false);
                                                        erelayisauto.setChecked(false);

                                                    }

                                                    // erelay_tvstras.setText("手动控制");
                                                    erelayHandStatus.setTextColor(Color.parseColor("#25C730"));

                                                    erelayAutoStatus.setTextColor(Color.GRAY);
                                                    erelayisauto.setBackgroundResource(R.drawable.button_open);
                                                } else if ("12".equals(switchs)) {
                                                    // erelay_tvstras.setText("自动控制");
                                                    erelayHandStatus.setTextColor(Color.GRAY);
                                                    erelayAutoStatus.setTextColor(Color.parseColor("#25C730"));
                                                    buttonOpen.setEnabled(false);
                                                    erelayisauto.setChecked(false);
                                                    erelayisauto.setBackgroundResource(R.drawable.button_close);
                                                }

                                                if ("1".equals(status)) {
                                                    PreferencesUtils.putInt(mContext, mComponents.getSn(), 1);
                                                    components.setStatus("1");
                                                    buttonOpen.setImageResource(R.drawable.button_open);

                                                    erelayStatus.setText("打开");

                                                } else if ("0".equals(status) || "2".equals(status)) {

                                                    PreferencesUtils.putInt(mContext, mComponents.getSn(), 0);
                                                    components.setStatus("0");
                                                    buttonOpen.setImageResource(R.drawable.button_close);

                                                    erelayStatus.setText("关闭");
                                                }
                                            }
                                        }
                                    });

                                }
                            });

                        }
                    }
                    break;
                case MSG_ERELAY2_CTRL:
                    if (msg.arg1 == 10) {
                        mHandler.removeMessages(MSG_ERELAY2_CTRL);
                        SocketIobeans relay2 = (SocketIobeans) msg.obj;
                        JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean ErelayDouble = relayMap.get(relay2.getData().getInfo().getSn());
                        View viewVentilation = ViewMap.get(relay2.getData().getInfo().getSn());

                        if (relay2 != null && ErelayDouble != null && viewVentilation != null) {
                            String switchs = relay2.getData().getInfo().getSwitchs();
                            String online_state = relay2.getData().getState().getOnline_state();
                            String status = relay2.getData().getLast_value().getStatus() + "";
                            TextView tvErelayTwoStatus = (TextView) viewVentilation.findViewById(R.id.tv_erelay_two_status);

                            final TextView erelay2HandStatus = (TextView) viewVentilation.findViewById(R.id.erelay2_handstatus);// 手
                            final ToggleButton ventilationIsControl = (ToggleButton) viewVentilation.findViewById(R.id.erelay2_is_control);// 模式切换
                            final TextView erelay2AutoStatus = (TextView) viewVentilation.findViewById(R.id.erelay2_autostatus);// 自

                            TextView forward = (TextView) viewVentilation.findViewById(R.id.erelay2_forward);// 上
                            TextView stop = (TextView) viewVentilation.findViewById(R.id.erelay2_stop);// 停
                            TextView back = (TextView) viewVentilation.findViewById(R.id.erelay2_back);// 下

                            ErelayDouble.setStatus(status);

                            ErelayDouble.setOnlineState(online_state);
                            if (DeviceType.erelay_ventilation.equals(relay2.getData().getInfo().getDev_extend_type())) {

                                ProgressBar percentageProgressBar = (ProgressBar) viewVentilation.findViewById(R.id.percentage_progress_bar);
                                TextView currentProgress = (TextView) viewVentilation.findViewById(R.id.current_progress_numeral);

                                if (null != relay2.getData().getInfo().getConfig_info()) {

                                    if (!("2".equals(relay2.getData().getInfo().getConfig_info().getVersion())) || relay2.getData().getInfo().getConfig_info().isIstemcontrol()) {

                                        String mt2 = relay2.getData().getInfo().getConfig_info().getMt();
                                        String cp2 = relay2.getData().getInfo().getConfig_info().getCp();
                                        if (mt2 != null && cp2 != null) {
                                            int mt = Integer.parseInt(mt2);
                                            int cp = Integer.parseInt(cp2);

                                            int result;
                                            if (cp <= 0) {
                                                result = 0;
                                            } else if (cp >= mt) {
                                                result = 100;
                                            } else {
                                                float num = (float) cp / mt;

                                                DecimalFormat df = new DecimalFormat(
                                                        "0.00");

                                                String current = df.format(num);

                                                result = (int) (Float.parseFloat(current) * 100);
                                            }

                                            percentageProgressBar.setSecondaryProgress(result);
                                            currentProgress.setText(result + "");
                                        }

                                    } else {// 位置控制
                                        percentageProgressBar.setSecondaryProgress(Integer.parseInt(relay2.getData().getInfo().getConfig_info().getPt() == null ? "0"
                                                : relay2.getData().getInfo().getConfig_info().getPt()));
                                        currentProgress.setText(relay2.getData().getInfo().getConfig_info().getPt());
                                    }

                                    ErelayDouble.setSwitchs(switchs);
                                    if ("online".equals(online_state)) {
                                        tvErelayTwoStatus.setText("在线");
                                        tvErelayTwoStatus.setTextColor(Color.BLACK);

                                        setErelayButtonStatus(forward, stop, back, relay2.getData().getLast_value().getStatus() + "", true);
                                    } else {
                                        tvErelayTwoStatus.setText("离线");
                                        tvErelayTwoStatus.setTextColor(Color.RED);
                                        setErelayButtonStatus(forward, stop, back,
                                                status, false);
                                    }
                                    if ("11".equals(switchs)) {

                                        ventilationIsControl.setBackgroundResource(R.drawable.button_open);
                                        erelay2HandStatus.setTextColor(Color.parseColor("#25C730"));
                                        erelay2AutoStatus.setTextColor(Color.GRAY);
                                        if ("online".equals(online_state)) {
                                            tvErelayTwoStatus.setText("在线");
                                            tvErelayTwoStatus.setTextColor(Color.BLACK);

                                            setErelayButtonStatus(forward, stop, back, status, true);
                                        } else {
                                            tvErelayTwoStatus.setText("离线");
                                            tvErelayTwoStatus.setTextColor(Color.RED);
                                            setErelayButtonStatus(forward, stop, back, status, false);
                                        }

                                    } else if ("12".equals(switchs)) {
                                        ventilationIsControl.setBackgroundResource(R.drawable.button_close);
                                        erelay2AutoStatus.setTextColor(Color.GRAY);
                                        erelay2HandStatus.setTextColor(Color.parseColor("#25C730"));
                                        setErelayButtonStatus(forward, stop, back, status, false);

                                    }
                                }
                            } else {// 其他
                                if ("online".equals(online_state)) {
                                    tvErelayTwoStatus.setText("在线");
                                    tvErelayTwoStatus.setTextColor(Color.BLACK);

                                    setErelayButtonStatus(forward, stop, back, status, true);
                                } else {
                                    tvErelayTwoStatus.setText("离线");
                                    tvErelayTwoStatus.setTextColor(Color.RED);
                                    setErelayButtonStatus(forward, stop, back, status, false);
                                }
                            }

                        }
                    } else {
                        final String sn = (String) msg.obj;
                        final View erelay2 = ViewMap.get(sn);
                        if (erelay2 != null) {
                            MineApplication.executorService.submit(new Runnable() {
                                @Override
                                public void run() {
                                    String deviceinfos = WapiUtil.getDeviceinfos(sn);
                                    Gson gson = new Gson();
                                    final Components ErelayDouble = gson.fromJson(
                                            deviceinfos,  Components.class);
                                    final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean components = relayMap.get(sn);
                                    ((Activity) mContext).runOnUiThread(new Runnable() {
                                        public void run() {
                                            if (ErelayDouble != null) {
                                                TextView tvErelayTwoStatus = (TextView) erelay2.findViewById(R.id.tv_erelay_two_status);
                                                final TextView erelay2HandStatus = (TextView) erelay2.findViewById(R.id.erelay2_handstatus);// 手
                                                final ToggleButton ventilationIsControl = (ToggleButton) erelay2.findViewById(R.id.erelay2_is_control);// 模式切换
                                                final TextView erelay2AutoStatus = (TextView) erelay2.findViewById(R.id.erelay2_autostatus);// 自

                                                TextView forward = (TextView) erelay2.findViewById(R.id.erelay2_forward);// 上
                                                TextView stop = (TextView) erelay2.findViewById(R.id.erelay2_stop);// 停
                                                TextView back = (TextView) erelay2.findViewById(R.id.erelay2_back);// 下
                                                String switchs = ErelayDouble.getSwitchs();
                                                String online_state = ErelayDouble.getOnline_state();
                                                components.setOnlineState(online_state);
                                                String status = ErelayDouble.getStatus() + "";
                                                components.setStatus(status);
                                                if (DeviceType.erelay_ventilation.equals(ErelayDouble.getDev_extend_type())) {
                                                    components.setSwitchs(switchs);
                                                    ProgressBar percentageProgressBar = (ProgressBar) erelay2.findViewById(R.id.percentage_progress_bar);
                                                    TextView currentProgress = (TextView) erelay2.findViewById(R.id.current_progress_numeral);

                                                    if (null != ErelayDouble.getConfig_info()) {

                                                        if (!("2".equals(ErelayDouble.getConfig_info().getVersion())) || ErelayDouble.getConfig_info().isIstemcontrol()) {

                                                            String mt2 = ErelayDouble.getConfig_info().getMt();
                                                            String cp2 = ErelayDouble.getConfig_info().getCp();
                                                            if (mt2 != null && cp2 != null) {
                                                                int mt = Integer.parseInt(mt2);
                                                                int cp = Integer.parseInt(cp2);

                                                                int result;
                                                                if (cp <= 0) {
                                                                    result = 0;
                                                                } else if (cp >= mt) {
                                                                    result = 100;
                                                                } else {
                                                                    float num = (float) cp / mt;

                                                                    DecimalFormat df = new DecimalFormat("0.00");

                                                                    String current = df.format(num);

                                                                    result = (int) (Float.parseFloat(current) * 100);
                                                                }

                                                                percentageProgressBar.setSecondaryProgress(result);
                                                                currentProgress.setText(result + "");
                                                            }

                                                        } else {// 位置控制
                                                            percentageProgressBar.setSecondaryProgress(Integer.parseInt(ErelayDouble.getConfig_info().getPt() == null ? "0"
                                                                    : ErelayDouble.getConfig_info().getPt()));
                                                            currentProgress.setText(ErelayDouble.getConfig_info().getPt());
                                                        }

                                                        if ("online".equals(online_state)) {
                                                            tvErelayTwoStatus.setText("在线");
                                                            tvErelayTwoStatus.setTextColor(Color.BLACK);
                                                            ErelayDouble.setOnline_state("online");

                                                            setErelayButtonStatus(forward, stop, back, status, true);
                                                        } else {
                                                            tvErelayTwoStatus.setText("离线");
                                                            tvErelayTwoStatus.setTextColor(Color.RED);
                                                            ErelayDouble.setOnline_state("offline");
                                                            setErelayButtonStatus(forward, stop, back, status, false);
                                                        }

                                                        if ("11".equals(switchs)) {
                                                            ErelayDouble.setSwitchs("11");
                                                            ventilationIsControl.setBackgroundResource(R.drawable.button_open);
                                                            erelay2HandStatus.setTextColor(Color.parseColor("#25C730"));
                                                            erelay2AutoStatus.setTextColor(Color.GRAY);
                                                            if ("online".equals(online_state)) {
                                                                tvErelayTwoStatus.setText("在线");
                                                                tvErelayTwoStatus.setTextColor(Color.BLACK);
                                                                ErelayDouble.setOnline_state("online");

                                                                setErelayButtonStatus(forward, stop, back, status, true);
                                                            } else {
                                                                tvErelayTwoStatus.setText("离线");
                                                                tvErelayTwoStatus.setTextColor(Color.RED);
                                                                ErelayDouble.setOnline_state("offline");
                                                                setErelayButtonStatus(forward, stop, back, status, false);
                                                            }

                                                        } else if ("12".equals(switchs)) {
                                                            ventilationIsControl.setBackgroundResource(R.drawable.button_close);
                                                            erelay2AutoStatus.setTextColor(Color.GRAY);
                                                            erelay2HandStatus.setTextColor(Color.parseColor("#25C730"));
                                                            ErelayDouble.setSwitchs("12");
                                                            setErelayButtonStatus(forward, stop, back, status, false);

                                                        }
                                                    }
                                                } else {// 其他
                                                    if ("online".equals(online_state)) {
                                                        tvErelayTwoStatus.setText("在线");
                                                        tvErelayTwoStatus.setTextColor(Color.BLACK);

                                                        setErelayButtonStatus(forward, stop, back, status, true);
                                                    } else {
                                                        tvErelayTwoStatus.setText("离线");
                                                        tvErelayTwoStatus.setTextColor(Color.RED);
                                                        setErelayButtonStatus(forward, stop, back, status, false);
                                                    }
                                                }
                                            }
                                        }
                                    });
                                }
                            });
                        }

                    }

                    break;
                case MSG_ERELAY2_CALLBRATION:
                    SocketIobeans erelaydata = (SocketIobeans) msg.obj;
                    JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean ErelayDouble = relayMap.get(erelaydata.getData().getInfo().getSn());
                    View ventilationDialog = ViewMap.get(erelaydata.getData().getInfo().getSn() + "ventilationDialog");
                    if (erelaydata != null && ventilationDialog != null && ErelayDouble != null) {
                        Button callbration_btn_on = (Button) ventilationDialog.findViewById(R.id.callbration_btn_on);
                        Button callbration_btn_off = (Button) ventilationDialog.findViewById(R.id.callbration_btn_off);
                        TextView callbration_tv_vcc_max = (TextView) ventilationDialog.findViewById(R.id.callbration_tv_vcc_max);
                        TextView callbration_tv_vcc_current = (TextView) ventilationDialog.findViewById(R.id.callbration_tv_vcc_current);
                        TextView callbration_tv_vcc_min = (TextView) ventilationDialog.findViewById(R.id.callbration_tv_vcc_min);
                        Button callbration_btn_switch1 = (Button) ventilationDialog.findViewById(R.id.callbration_btn_switch1);
                        Button callbration_btn_switch2 = (Button) ventilationDialog.findViewById(R.id.callbration_btn_switch2);
                        Button callbration_btn_switch3 = (Button) ventilationDialog.findViewById(R.id.callbration_btn_switch3);
                        if (erelaydata.getData().getInfo().getConfig_info().getVcc_max() != null) {
                            callbration_tv_vcc_max.setText("最大值 :" + erelaydata.getData().getInfo().getConfig_info().getVcc_max());
                            ErelayDouble.getConfigInfo().setVcc_max(erelaydata.getData().getInfo().getConfig_info().getVcc_max());
                        }
                        if (erelaydata.getData().getInfo().getConfig_info().getVcc_current() != null) {
                            callbration_tv_vcc_current.setText("当前值 :" + erelaydata.getData().getInfo().getConfig_info().getVcc_current());
                            ErelayDouble.getConfigInfo().setVcc_current(erelaydata.getData().getInfo().getConfig_info().getVcc_current());
                        }

                        if (erelaydata.getData().getInfo().getConfig_info().getVcc_min() != null) {
                            callbration_tv_vcc_min.setText("最小值 :" + erelaydata.getData().getInfo().getConfig_info().getVcc_min());
                            ErelayDouble.getConfigInfo().setVcc_min(erelaydata.getData().getInfo().getConfig_info().getVcc_min());
                        }

                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 单继电器 开关
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    Map<String, JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean> relayMap = new HashMap<String, JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean>();
    Map<String, View> ViewMap = new HashMap<String, View>();

    private void initErelaySwitch(final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean co) {
        // TODO Auto-generated method stub

        final View view = inflater.inflate(R.layout.erelay_lay, null);

        relayMap.put(co.getSn(), co);
        ViewMap.put(co.getSn(), view);
        TextView erelayName = (TextView) view.findViewById(R.id.erelay_name);

        TextView erelayIeee = (TextView) view.findViewById(R.id.erelay_ieee);// sn
        TextView erelayControlsWarm = (TextView) view
                .findViewById(R.id.erelay_controls_warm);// 策略按钮

        final ImageView buttonOpen = (ImageView) view
                .findViewById(R.id.button_open);// 开关按钮

        TextView erelayStatus = (TextView) view
                .findViewById(R.id.erelay_status);// 设备开关
        ImageView erelayIco = (ImageView) view.findViewById(R.id.water_valve);// 设备图片

        TextView relayOneStatus = (TextView) view
                .findViewById(R.id.tv_relay_one_status);// 设备状态

        RelativeLayout erelayControlBtnLay = (RelativeLayout) view
                .findViewById(R.id.erelay_control_btn_lay);

        TextView erelayHandStatus = (TextView) view// 手
                .findViewById(R.id.erelay_handstatus);
        final ToggleButton erelayisauto = (ToggleButton) view// 模式切换
                .findViewById(R.id.erelay_tb_btn);
        TextView erelayAutoStatus = (TextView) view// 自
                .findViewById(R.id.erelay_autostatus);

        TextView erelayTvpower = (TextView) view
                .findViewById(R.id.erelay_tvpower);
        TextView erelayPower = (TextView) view.findViewById(R.id.erelay_power);
        erelayControlBtnLay.setVisibility(View.VISIBLE);
        erelayTvpower.setVisibility(View.VISIBLE);
        erelayPower.setVisibility(View.VISIBLE);
        erelayName.setText(TextUtils.isEmpty(co.getDevName()) ? "" : co
                .getDevName());

        erelayPower.setText(TextUtils.isEmpty(co.getPower()) ? "" : co
                .getPower() + " W");

        erelayIeee.setText(TextUtils.isEmpty(co.getSn()) ? "" : co.getSn());

        erelayIco.setImageResource(R.drawable.switch1);
        String status = co.getStatus();
        if (status == null) {
            status = Constants.ACTION_RELAY_CLOSE;
        }

        PreferencesUtils.putInt(mContext, co.getSn(), Integer.parseInt(status));
        // 获取保存的单继电器状态
        int ereStatus = PreferencesUtils.getInt(mContext, co.getSn(),
                Integer.parseInt(status));
        // 单继电器设置
        if (null != status) {
            buttonOpen.setVisibility(View.VISIBLE);
            buttonOpen.setEnabled(true);
            erelayisauto.setEnabled(true);
            if (ereStatus == Integer.parseInt(Constants.ACTION_RELAY_CLOSE)
                    || ereStatus == Integer
                    .parseInt(Constants.ERELAY_STATUS_CLOSE2)) {// 后者条件为适配双向应用单向
                buttonOpen.setImageResource(R.drawable.button_close);
                erelayStatus.setText("关闭");

            } else {
                buttonOpen.setImageResource(R.drawable.button_open);
                erelayStatus.setText("打开");
            }

        } else {
            erelayisauto.setEnabled(false);
            buttonOpen.setEnabled(false);
            buttonOpen.setVisibility(View.GONE);
        }
        if (Constants.STATUS_ONLINE.equals(co.getOnlineState())) {
            relayOneStatus.setText("在线");
            relayOneStatus.setTextSize(10);
            erelayisauto.setEnabled(true);
            buttonOpen.setEnabled(true);
            relayOneStatus.setTextColor(getResources().getColor(R.color.black));
        } else if (Constants.STATUS_OFFLINE.equals(co.getOnlineState())
                || null == co.getOnlineState()) {
            relayOneStatus.setText("离线");
            relayOneStatus.setTextSize(10);
            buttonOpen.setEnabled(false);
            erelayisauto.setEnabled(false);
            relayOneStatus.setTextColor(getResources().getColor(R.color.red));
        }

        PreferencesUtils.putString(mContext, "method" + co.getSn(),
                co.getSwitchs());

        method = ReturnControlMethod(erelayisauto, co);// 返回状态
        String method = PreferencesUtils.getString(mContext,
                "method" + co.getSn());
        setElelayControlButton(erelayisauto, method, buttonOpen,
                erelayHandStatus, erelayAutoStatus);// 设置状态

        erelayisauto.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean isOpenHand = erelayisauto.isChecked();
                if (isOpenHand) {
                    erelayisauto.setBackgroundResource(R.drawable.button_pitch);
                    buttonOpen.setEnabled(true);
                    new opeErelay().execute(erelayisauto, co,
                            Constants.HAND_CONTROL);

                } else {

                    if (co.getConfigInfo().getTlh() == null
                            || "".equals(co.getConfigInfo().getSlh())
                            || "-1".equals(co.getConfigInfo().getSlh())
                            || "0".equals(co.getConfigInfo().getSlh())) {
                        erelayisauto
                                .setBackgroundResource(R.drawable.button_open);
                        TAUtils.toastMessage((Activity) mContext, "请设置自动策略");
                        erelayisauto.setChecked(true);
                        return;
                    }

                    erelayisauto.setBackgroundResource(R.drawable.button_pitch);
                    buttonOpen.setEnabled(false);
                    new opeErelay().execute(erelayisauto, co,
                            Constants.AUTO_CONTROL);
                }
            }
        });

        erelayIco.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                TAUtils.toastMessage((Activity) mContext,
                        "使用电量为" + co.getChargeTotal() + "kW·h");
            }
        });
        // 单继电器控制
        buttonOpen.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                /* 0 is close,1 is open */

                buttonOpen.setImageResource(R.drawable.button_pitch);

                if (co.getStatus().equals(Constants.ERELAY_STATUS_CLOSE)) {// 0,2

                    new opeErelay().execute(buttonOpen, co,
                            Constants.ACTION_RELAY_OPEN);// "0");

                } else if (co.getStatus().equals(Constants.ERELAY_STATUS_OPEN)) {// 1
                    new opeErelay().execute(buttonOpen, co,
                            Constants.ACTION_RELAY_CLOSE);// "1");
                }

            }
        });

        erelayControlsWarm.setOnClickListener(new OnClickListener() {// 自控策略

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showErelayDialogControls(co);
            }
        });

        llErelaySwitchDeviceControl.addView(view);

    }

    /**
     * 返回当前设备策略模式状态
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     * @pram String 状态
     */
    public String ReturnControlMethod(final ToggleButton erelayisauto,
                                      final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean  co) {
        // TODO Auto-generated method stub

        method = PreferencesUtils.getString(mContext, "method" + co.getSn(),
                null);

        erelayisauto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // TODO Auto-generated method stub
                if (arg1) {
                    method = Constants.HAND_CONTROL;// 手动
                    erelayisauto.setBackgroundResource(R.drawable.button_open);
                    // 保存控制方式
                    PreferencesUtils.putString(mContext, "method" + co.getSn(), method);

                } else {

                    method = Constants.AUTO_CONTROL;// 自动

                    erelayisauto.setBackgroundResource(R.drawable.button_close);
                    // 保存控制方式
                    PreferencesUtils.putString(mContext, "method" + co.getSn(), method);

                }
            }
        });
        return method;
    }

    /**
     * 初始化当前设备策略模式状态
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    private void setElelayControlButton(final ToggleButton erelayopen,
                                        String method, ImageView buttonOpen, TextView erelayHandStatus,
                                        TextView erelayAutoStatus) {
        if (Constants.HAND_CONTROL.equals(method)) {
            erelayHandStatus.setTextColor(Color.parseColor("#25C730"));
            erelayAutoStatus.setTextColor(Color.GRAY);
            buttonOpen.setEnabled(true);
            erelayopen.setChecked(true);
        } else if (Constants.AUTO_CONTROL.equals(method)) {
            erelayHandStatus.setTextColor(Color.GRAY);
            erelayAutoStatus.setTextColor(Color.parseColor("#25C730"));
            erelayopen.setChecked(false);
            buttonOpen.setEnabled(false);
        }
    }

    /**
     * 操作单继电器，如水阀等
     *
     * @author shuyi
     */
    private class opeErelay extends AsyncTask<Object, Void, String> {
        JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean mComponents = null;
        String mComponId = "";
        View view = null;

        @Override
        protected String doInBackground(Object... arg0) {
            String str = "远程控制失败";
            view = (View) arg0[0];
            mComponents = (JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean) arg0[1];
            String componentId = mComponents.getSn();
            String oldState = mComponents.getStatus();
            mComponId = componentId;
            String action = (String) arg0[2];
            String result = WapiUtil.opeErelay(mComponents.getOwner(),
                    componentId, action);

            if (result != null && !"".equals(result)) {
                try {
                    JSONObject object = new JSONObject(result);
                    String ret = object.getString("ret");
                    if ("success".equals(ret)) {
                        if (oldState.equals("1")) {

                            // msg.arg1 = 1;
                            // 保存单继电器打开状态
                            PreferencesUtils.putInt(mContext,
                                    mComponents.getSn(), 1);
                        } else {

                            // msg.arg1 = 0;
                            // 保存单继电器关闭状态
                            PreferencesUtils.putInt(mContext,
                                    mComponents.getSn(), 0);
                        }
                        Message msg = new Message();
                        msg.what = MSG_ERELAY_CTRL;
                        msg.obj = componentId;
                        msg.arg1 = ERROR_ECHO;
                        mHandler.sendMessageDelayed(msg, 3000);

                        if ((Constants.HAND_CONTROL).equals(action)) {
                            action = "手动控制";
                            str = "模式切换：" + action;
                        } else if ((Constants.AUTO_CONTROL).equals(action)) {
                            action = "自动控制";
                            str = "模式切换：" + action;
                        } else if ("1".equals(action)) {
                            str = "远程控制:打开";
                        } else if ("0".equals(action)) {
                            str = "远程控制:关闭";
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return str;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            TAUtils.toastMessage((Activity) mContext, result);
        }
    }


    /**
     * 自控设置界面
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    String slh, tlh, sth1, sth2, sth3, sth4, sth5, stm1, stm2, stm3, stm4,
            stm5, eth1, eth2, eth3, eth4, eth5, etm1, etm2, etm3, etm4, etm5;

    protected void showErelayDialogControls(final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean co) {
        // TODO Auto-generated method stub

        final Dialog dialog = new AlertDialog.Builder(mContext).create();
        // 设置点击范围外 无效果
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
        // 填充自己想要的布局
        View ventilationDialog = LayoutInflater.from(mContext).inflate(
                R.layout.erelay_control_dialog, null);
        // 设置显示位置
        dialog.addContentView(ventilationDialog,
                new ViewGroup.LayoutParams((int) (mContext.getResources()
                        .getDisplayMetrics().widthPixels * 0.9),
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        ImageView erelayIv = (ImageView) ventilationDialog
                .findViewById(R.id.erelay_iv);
        Button btnErelayEnsure = (Button) ventilationDialog
                .findViewById(R.id.btn_erelay_ensure);
        Button btnErelayCancel = (Button) ventilationDialog
                .findViewById(R.id.btn_erelay_cancel);

        final EditText erelayOpenTime = (EditText) ventilationDialog
                .findViewById(R.id.erelay_open_time);
        final EditText erelayWaitingTime = (EditText) ventilationDialog
                .findViewById(R.id.erelay_waiting_time);

        final TimePicker erelayTimepickerStart1 = (TimePicker) ventilationDialog
                .findViewById(R.id.erelay_timepicker_start1);
        final TimePicker erelayTimepickerStart2 = (TimePicker) ventilationDialog
                .findViewById(R.id.erelay_timepicker_start2);
        final TimePicker erelayTimepickerStart3 = (TimePicker) ventilationDialog
                .findViewById(R.id.erelay_timepicker_start3);
        final TimePicker erelayTimepickerStart4 = (TimePicker) ventilationDialog
                .findViewById(R.id.erelay_timepicker_start4);
        final TimePicker erelayTimepickerStart5 = (TimePicker) ventilationDialog
                .findViewById(R.id.erelay_timepicker_start5);

        final TimePicker erelayTimepickerEnd1 = (TimePicker) ventilationDialog
                .findViewById(R.id.erelay_timepicker_end1);
        final TimePicker erelayTimepickerEnd2 = (TimePicker) ventilationDialog
                .findViewById(R.id.erelay_timepicker_end2);
        final TimePicker erelayTimepickerEnd3 = (TimePicker) ventilationDialog
                .findViewById(R.id.erelay_timepicker_end3);
        final TimePicker erelayTimepickerEnd4 = (TimePicker) ventilationDialog
                .findViewById(R.id.erelay_timepicker_end4);
        final TimePicker erelayTimepickerEnd5 = (TimePicker) ventilationDialog
                .findViewById(R.id.erelay_timepicker_end5);

        // 把时间控件设置成显示24小时
        erelayTimepickerStart1.setIs24HourView(true);
        erelayTimepickerStart2.setIs24HourView(true);
        erelayTimepickerStart3.setIs24HourView(true);
        erelayTimepickerStart4.setIs24HourView(true);
        erelayTimepickerStart5.setIs24HourView(true);

        erelayTimepickerEnd1.setIs24HourView(true);
        erelayTimepickerEnd2.setIs24HourView(true);
        erelayTimepickerEnd3.setIs24HourView(true);
        erelayTimepickerEnd4.setIs24HourView(true);
        erelayTimepickerEnd5.setIs24HourView(true);


        MineApplication.executorService.submit(new Runnable() {
            @Override
            public void run() {
                // 请求网络
                String deviceinfos = WapiUtil.getDeviceinfos(co.getSn());
                Gson gson = new Gson();
                final Components ErelayDouble = gson.fromJson(deviceinfos,
                        Components.class);

                mComponents = ErelayDouble;
                Components.ConfigInfoBean config_info = ErelayDouble.getConfig_info();
                if (config_info != null) {
                    slh = config_info.getSlh();
                    tlh = config_info.getTlh();

                    sth1 = config_info.getSth1();
                    stm1 = config_info.getStm1();

                    sth2 = config_info.getSth2();
                    stm2 = config_info.getStm2();

                    sth3 = config_info.getSth3();
                    stm3 = config_info.getStm3();

                    sth4 = config_info.getSth4();
                    stm4 = config_info.getStm4();

                    sth5 = config_info.getSth5();
                    stm5 = config_info.getStm5();

                    eth1 = config_info.getEth1();
                    etm1 = config_info.getEtm1();

                    eth2 = config_info.getEth2();
                    etm2 = config_info.getEtm2();

                    eth3 = config_info.getEth3();
                    etm3 = config_info.getEtm3();

                    eth4 = config_info.getEth4();
                    etm4 = config_info.getEtm4();

                    eth5 = config_info.getEth5();
                    etm5 = config_info.getEtm5();
                }

                // ui线程
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    public void run() {

                        if (ErelayDouble != null && !"".equals(ErelayDouble)) {

                            erelayWaitingTime.setText(SetValueIsNull(tlh));

                            erelayOpenTime.setText(SetValueIsNull(slh));

                            erelayTimepickerStart1.setCurrentHour(Integer
                                    .parseInt(SetValueIsNull(sth1)));
                            erelayTimepickerStart1.setCurrentMinute(Integer
                                    .parseInt(SetValueIsNull(stm1)));

                            erelayTimepickerStart2.setCurrentHour(Integer
                                    .parseInt(SetValueIsNull(sth2)));
                            erelayTimepickerStart2.setCurrentMinute(Integer
                                    .parseInt(SetValueIsNull(stm2)));
                            erelayTimepickerStart3.setCurrentHour(Integer
                                    .parseInt(SetValueIsNull(sth3)));
                            erelayTimepickerStart3.setCurrentMinute(Integer
                                    .parseInt(SetValueIsNull(stm3)));
                            erelayTimepickerStart4.setCurrentHour(Integer
                                    .parseInt(SetValueIsNull(sth4)));
                            erelayTimepickerStart4.setCurrentMinute(Integer
                                    .parseInt(SetValueIsNull(stm4)));
                            erelayTimepickerStart5.setCurrentHour(Integer
                                    .parseInt(SetValueIsNull(sth5)));
                            erelayTimepickerStart5.setCurrentMinute(Integer
                                    .parseInt(SetValueIsNull(stm5)));

                            erelayTimepickerEnd1.setCurrentHour(Integer
                                    .parseInt(SetValueIsNull(eth1)));
                            erelayTimepickerEnd1.setCurrentMinute(Integer
                                    .parseInt(SetValueIsNull(etm1)));
                            erelayTimepickerEnd2.setCurrentHour(Integer
                                    .parseInt(SetValueIsNull(eth2)));
                            erelayTimepickerEnd2.setCurrentMinute(Integer
                                    .parseInt(SetValueIsNull(etm2)));
                            erelayTimepickerEnd3.setCurrentHour(Integer
                                    .parseInt(SetValueIsNull(eth3)));
                            erelayTimepickerEnd3.setCurrentMinute(Integer
                                    .parseInt(SetValueIsNull(etm3)));
                            erelayTimepickerEnd4.setCurrentHour(Integer
                                    .parseInt(SetValueIsNull(eth4)));
                            erelayTimepickerEnd4.setCurrentMinute(Integer
                                    .parseInt(SetValueIsNull(etm4)));
                            erelayTimepickerEnd5.setCurrentHour(Integer
                                    .parseInt(SetValueIsNull(eth5)));
                            erelayTimepickerEnd5.setCurrentMinute(Integer
                                    .parseInt(SetValueIsNull(etm5)));
                        }
                    }
                });
            }
        });

        // 提交按钮
        btnErelayEnsure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Integer currentHour_start1 = erelayTimepickerStart1
                        .getCurrentHour();
                Integer currentMinute_start1 = erelayTimepickerStart1
                        .getCurrentMinute();

                Integer currentHour_start2 = erelayTimepickerStart2
                        .getCurrentHour();
                Integer currentMinute_start2 = erelayTimepickerStart2
                        .getCurrentMinute();
                Integer currentHour_start3 = erelayTimepickerStart3
                        .getCurrentHour();
                Integer currentMinute_start3 = erelayTimepickerStart3
                        .getCurrentMinute();
                Integer currentHour_start4 = erelayTimepickerStart4
                        .getCurrentHour();
                Integer currentMinute_start4 = erelayTimepickerStart4
                        .getCurrentMinute();
                Integer currentHour_start5 = erelayTimepickerStart5
                        .getCurrentHour();
                Integer currentMinute_start5 = erelayTimepickerStart5
                        .getCurrentMinute();

                Integer currentHour_end1 = erelayTimepickerEnd1
                        .getCurrentHour();
                Integer currentMinute_end1 = erelayTimepickerEnd1
                        .getCurrentMinute();
                Integer currentHour_end2 = erelayTimepickerEnd2
                        .getCurrentHour();
                Integer currentMinute_end2 = erelayTimepickerEnd2
                        .getCurrentMinute();
                Integer currentHour_end3 = erelayTimepickerEnd3
                        .getCurrentHour();
                Integer currentMinute_end3 = erelayTimepickerEnd3
                        .getCurrentMinute();
                Integer currentHour_end4 = erelayTimepickerEnd4
                        .getCurrentHour();
                Integer currentMinute_end4 = erelayTimepickerEnd4
                        .getCurrentMinute();
                Integer currentHour_end5 = erelayTimepickerEnd5
                        .getCurrentHour();
                Integer currentMinute_end5 = erelayTimepickerEnd5
                        .getCurrentMinute();
                String startTime = currentHour_start1 + ":"
                        + currentMinute_start1;
                Date strToDateLong = strToDateLong(startTime);
                String endTime = currentHour_end1 + ":" + currentMinute_end1;
                Date strToDateLong2 = strToDateLong(endTime);
                if (strToDateLong.getTime() >= strToDateLong2.getTime()) {
                    TAUtils.toastMessage((Activity) mContext,
                            "一组结束时间不能小于开始时间,请从新设置");
                    return;
                } else {
                    String setStartTimeShow2 = setStartTimeShow(
                            currentHour_start2, currentMinute_start2,
                            currentHour_end2, currentMinute_end2,
                            currentHour_end1, currentMinute_end1, "二组");
                    if ("NO".endsWith(setStartTimeShow2)) {
                        return;
                    }
                    String setStartTimeShow3 = setStartTimeShow(
                            currentHour_start3, currentMinute_start3,
                            currentHour_end3, currentMinute_end3,
                            currentHour_end2, currentMinute_end2, "三组");
                    if ("NO".endsWith(setStartTimeShow3)) {
                        return;
                    }
                    String setStartTimeShow4 = setStartTimeShow(
                            currentHour_start4, currentMinute_start4,
                            currentHour_end4, currentMinute_end4,
                            currentHour_end3, currentMinute_end3, "四组");
                    if ("NO".endsWith(setStartTimeShow4)) {
                        return;
                    }
                    String setStartTimeShow5 = setStartTimeShow(
                            currentHour_start5, currentMinute_start5,
                            currentHour_end5, currentMinute_end5,
                            currentHour_end4, currentMinute_end4, "五组");
                    if ("NO".endsWith(setStartTimeShow5)) {
                        return;
                    }
                }
                String open_time = erelayOpenTime.getText().toString();

                if (open_time == null || "0".equals(open_time)
                        || "".equals(open_time)) {
                    TAUtils.toastMessage((Activity) mContext, "启时长不能小于0");
                    return;
                }
                co.getConfigInfo().setSlh(open_time);
                mComponents.getConfig_info().setSlh(open_time);
                String waiting_time = erelayWaitingTime.getText().toString();
                if (waiting_time != null && !"".equals(waiting_time)) {
                    mComponents.getConfig_info().setTlh(waiting_time);
                }

                Integer currentHourstart1 = erelayTimepickerStart1
                        .getCurrentHour();
                if (currentHourstart1 != null) {
                    mComponents.getConfig_info()
                            .setSth1(currentHourstart1 + "");
                }
                Integer currentMinutestart1 = erelayTimepickerStart1
                        .getCurrentMinute();
                if (currentMinutestart1 != null) {
                    mComponents.getConfig_info().setStm1(
                            currentMinutestart1 + "");
                }

                Integer currentHourstart2 = erelayTimepickerStart2
                        .getCurrentHour();
                if (currentHourstart2 != null) {
                    mComponents.getConfig_info()
                            .setSth2(currentHourstart2 + "");
                }
                Integer currentMinutestart2 = erelayTimepickerStart2
                        .getCurrentMinute();
                if (currentMinutestart2 != null) {
                    mComponents.getConfig_info().setStm2(
                            currentMinutestart2 + "");
                }

                Integer currentHourstart3 = erelayTimepickerStart3
                        .getCurrentHour();
                if (currentHourstart3 != null) {
                    mComponents.getConfig_info()
                            .setSth3(currentHourstart3 + "");
                }
                Integer currentMinutestart3 = erelayTimepickerStart3
                        .getCurrentMinute();
                if (currentMinutestart3 != null) {
                    mComponents.getConfig_info().setStm3(
                            currentMinutestart3 + "");
                }

                Integer currentHourstart4 = erelayTimepickerStart4
                        .getCurrentHour();
                if (currentHourstart4 != null) {
                    mComponents.getConfig_info()
                            .setSth4(currentHourstart4 + "");
                }
                Integer currentMinutestart4 = erelayTimepickerStart4
                        .getCurrentMinute();
                if (currentMinutestart4 != null) {
                    mComponents.getConfig_info().setStm4(
                            currentMinutestart4 + "");
                }

                Integer currentHourstart5 = erelayTimepickerStart5
                        .getCurrentHour();
                if (currentHourstart5 != null) {
                    mComponents.getConfig_info()
                            .setSth5(currentHourstart5 + "");
                }
                Integer currentMinutestart5 = erelayTimepickerStart5
                        .getCurrentMinute();
                if (currentMinutestart5 != null) {
                    mComponents.getConfig_info().setStm5(
                            currentMinutestart5 + "");
                }

                Integer currentHourend1 = erelayTimepickerEnd1.getCurrentHour();
                if (currentHourend1 != null) {
                    mComponents.getConfig_info().setEth1(currentHourend1 + "");
                }
                Integer currentMinuteend1 = erelayTimepickerEnd1
                        .getCurrentMinute();
                if (currentMinuteend1 != null) {
                    mComponents.getConfig_info()
                            .setEtm1(currentMinuteend1 + "");
                }
                Integer currentHourend2 = erelayTimepickerEnd2.getCurrentHour();
                if (currentHourend2 != null) {
                    mComponents.getConfig_info().setEth2(currentHourend2 + "");
                }
                Integer currentMinuteend2 = erelayTimepickerEnd2
                        .getCurrentMinute();
                if (currentMinuteend2 != null) {
                    mComponents.getConfig_info()
                            .setEtm2(currentMinuteend2 + "");
                }
                Integer currentHourend3 = erelayTimepickerEnd3.getCurrentHour();
                if (currentHourend3 != null) {
                    mComponents.getConfig_info().setEth3(currentHourend3 + "");
                }
                Integer currentMinuteend3 = erelayTimepickerEnd3
                        .getCurrentMinute();
                if (currentMinuteend3 != null) {
                    mComponents.getConfig_info()
                            .setEtm3(currentMinuteend3 + "");
                }
                Integer currentHourend4 = erelayTimepickerEnd4.getCurrentHour();
                if (currentHourend4 != null) {
                    mComponents.getConfig_info().setEth4(currentHourend4 + "");
                }
                Integer currentMinuteend4 = erelayTimepickerEnd4
                        .getCurrentMinute();
                if (currentMinuteend4 != null) {
                    mComponents.getConfig_info()
                            .setEtm4(currentMinuteend4 + "");
                }
                Integer currentHourend5 = erelayTimepickerEnd5.getCurrentHour();
                if (currentHourend5 != null) {
                    mComponents.getConfig_info().setEth5(currentHourend5 + "");
                }
                Integer currentMinuteend5 = erelayTimepickerEnd5
                        .getCurrentMinute();
                if (currentMinuteend5 != null) {
                    mComponents.getConfig_info()
                            .setEtm5(currentMinuteend5 + "");
                }
                Gson gson = new Gson();
                final String json = gson.toJson(mComponents);
                if (Constants.STATUS_OFFLINE.equals(co.getOnlineState())) {
                    TAUtils.toastMessage((Activity) mContext, "设备离线不能提交策略");
                    return;
                }

                try {
                    final JSONObject jsonObject = new JSONObject(json);
                    Future<?> future = MineApplication.executorService
                            .submit(new Runnable() {

                                @Override
                                public void run() {
                                    WapiUtil.waterControlStrategy(
                                            sgateSN,
                                            mComponents.getSn(), jsonObject);
                                }
                            });
                    Object object = future.get();
                    if (object == null) {
                        ((Activity) mContext).runOnUiThread(new Runnable() {
                            public void run() {
                                dialog.dismiss();
                                TAUtils.toastMessage((Activity) mContext,
                                        "自动策略已发送");
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // 键盘
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        btnErelayCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();

            }
        });

        erelayIv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                dialog.dismiss();
            }
        });

    }

    private String SetValueIsNull(String value) {
        if (value == null || "".equals(value) || "-1".equals(value)) {
            return "0";
        }
        return value;
    }

    /**
     * 温控设置界面逻辑判断
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    private String setStartTimeShow(Integer currentHourStart,
                                    Integer currentMinuteStart, Integer currentHourEnd,
                                    Integer currentMinuteEnd, Integer currentHour_end1,
                                    Integer currentMinute_end1, String string) {
        if (currentHourStart == 0 && currentHourEnd == 0
                && currentMinuteStart == 0 && currentMinuteEnd == 0) {
            return "OK";
        }

        String startTime = currentHourStart + ":" + currentMinuteStart;
        Date strToDateLong = strToDateLong(startTime);

        String endTime = currentHourEnd + ":" + currentMinuteEnd;
        Date strToDateLong2 = strToDateLong(endTime);
        String upTime = currentHour_end1 + ":" + currentMinute_end1;
        Date strToDateLong3 = strToDateLong(upTime);

        if (strToDateLong.getTime() > strToDateLong2.getTime()) {

            TAUtils.toastMessage((Activity) mContext, string
                    + "结束时间不能小于开始时间,请从新设置");
            return "NO";
        }

        if ((currentMinute_end1 + currentHour_end1) == 0) {
            TAUtils.toastMessage((Activity) mContext, "请先设置"
                    + getUPGroup(string) + "的开始与结束时间,请从新设置");
            return "NO";
        }

        if (strToDateLong.getTime() == strToDateLong3.getTime()
                || strToDateLong.getTime() < strToDateLong3.getTime()) {
            TAUtils.toastMessage((Activity) mContext, string + "开始时间应该大于"
                    + getUPGroup(string) + "结束时间,请从新设置");
            return "NO";
        }
        return "OK";
    }

    /**
     * 时间转换
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 返回组数
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    private String getUPGroup(String string) {
        if ("二组".equals(string)) {
            return "一组";
        }
        if ("三组".equals(string)) {
            return "二组";
        }
        if ("四组".equals(string)) {
            return "三组";
        }
        if ("五组".equals(string)) {
            return "四组";
        }
        return string;
    }


    /**
     * 单继电器
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    private void initErelay(final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean co) {
        // TODO Auto-generated method stub

        final View view = inflater.inflate(R.layout.erelay_lay, null);
        view.setTag(co.getSn());
        TextView erelayName = (TextView) view.findViewById(R.id.erelay_name);
        relayMap.put(co.getSn(), co);
        ViewMap.put(co.getSn(), view);
        TextView erelayIeee = (TextView) view.findViewById(R.id.erelay_ieee);// sn
        TextView erelayControlsWarm = (TextView) view
                .findViewById(R.id.erelay_controls_warm);// 策略按钮

        final ImageView buttonOpen = (ImageView) view
                .findViewById(R.id.button_open);// 开关按钮

        TextView erelayStatus = (TextView) view
                .findViewById(R.id.erelay_status);// 设备开关
        ImageView erelayIco = (ImageView) view.findViewById(R.id.water_valve);// 设备图片

        TextView relayOneStatus = (TextView) view
                .findViewById(R.id.tv_relay_one_status);// 设备状态

        RelativeLayout erelayControlBtnLay = (RelativeLayout) view
                .findViewById(R.id.erelay_control_btn_lay);

        TextView erelayHandStatus = (TextView) view// 手
                .findViewById(R.id.erelay_handstatus);
        final ToggleButton erelayisauto = (ToggleButton) view// 模式切换
                .findViewById(R.id.erelay_tb_btn);
        TextView erelayAutoStatus = (TextView) view// 自
                .findViewById(R.id.erelay_autostatus);

        if (co.getDevExtendType().equals(DeviceType.erelay_water_valve)
                && co.getConfigInfo() != null) {
            erelayControlBtnLay.setVisibility(View.VISIBLE);
        }

        erelayName.setText(TextUtils.isEmpty(co.getDevName()) ? "" : co
                .getDevName());

        erelayIeee.setText(TextUtils.isEmpty(co.getSn()) ? "" : co.getSn());

        @SuppressWarnings("static-access")
        int imageId = MineApplication.getInstance().getComponentIcon(
                // 获取对应设备图片
                co.getDevType(), co.getDevExtendType(),
                co.getOnlineState(), co.getDevName());
        erelayIco.setImageResource(imageId);
        String status = co.getStatus();
        if (status == null) {
            status = Constants.ACTION_RELAY_CLOSE;
        }

        PreferencesUtils.putInt(mContext, co.getSn(), Integer.parseInt(status));
        // 获取保存的单继电器状态
        int ereStatus = PreferencesUtils.getInt(mContext, co.getSn(),
                Integer.parseInt(status));
        // 单继电器设置
        if (null != status) {
            buttonOpen.setVisibility(View.VISIBLE);
            buttonOpen.setEnabled(true);
            erelayisauto.setEnabled(true);
            if (ereStatus == Integer.parseInt(Constants.ACTION_RELAY_CLOSE)
                    || ereStatus == Integer
                    .parseInt(Constants.ERELAY_STATUS_CLOSE2)) {// 后者条件为适配双向应用单向
                buttonOpen.setImageResource(R.drawable.button_close);
                erelayStatus.setText("关闭");

            } else {
                buttonOpen.setImageResource(R.drawable.button_open);
                erelayStatus.setText("打开");
            }

        } else {
            erelayisauto.setEnabled(false);
            buttonOpen.setEnabled(false);
            buttonOpen.setVisibility(View.GONE);
        }
        if (Constants.STATUS_ONLINE.equals(co.getOnlineState())) {
            relayOneStatus.setText("在线");
            relayOneStatus.setTextSize(10);
            erelayisauto.setEnabled(true);
            buttonOpen.setEnabled(true);
            relayOneStatus.setTextColor(getResources().getColor(R.color.black));
        } else if (Constants.STATUS_OFFLINE.equals(co.getOnlineState())
                || null == co.getOnlineState()) {
            relayOneStatus.setText("离线");
            relayOneStatus.setTextSize(10);
            buttonOpen.setEnabled(false);
            erelayisauto.setEnabled(false);
            relayOneStatus.setTextColor(getResources().getColor(R.color.red));
        }

        PreferencesUtils.putString(mContext, "method" + co.getSn(),
                co.getSwitchs());

        method = ReturnControlMethod(erelayisauto, co);// 返回状态
        String method = PreferencesUtils.getString(mContext,
                "method" + co.getSn());
        setElelayControlButton(erelayisauto, method, buttonOpen,
                erelayHandStatus, erelayAutoStatus);// 设置状态

        erelayisauto.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean isOpenHand = erelayisauto.isChecked();
                if (isOpenHand) {
                    erelayisauto.setBackgroundResource(R.drawable.button_pitch);
                    buttonOpen.setEnabled(true);
                    new opeErelay().execute(erelayisauto, co,
                            Constants.HAND_CONTROL, view);

                } else {

                    if (co.getConfigInfo().getTlh() == null
                            || "".equals(co.getConfigInfo().getSlh())
                            || "-1".equals(co.getConfigInfo().getSlh())
                            || "0".equals(co.getConfigInfo().getSlh())) {
                        erelayisauto
                                .setBackgroundResource(R.drawable.button_open);
                        TAUtils.toastMessage((Activity) mContext, "请设置自动策略");
                        erelayisauto.setChecked(true);
                        return;
                    }

                    erelayisauto.setBackgroundResource(R.drawable.button_pitch);
                    buttonOpen.setEnabled(false);
                    new opeErelay().execute(erelayisauto, co,
                            Constants.AUTO_CONTROL, view);
                }
            }
        });
        // 单继电器控制
        buttonOpen.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                /* 0 is close,1 is open */

                buttonOpen.setImageResource(R.drawable.button_pitch);

                if (co.getStatus().equals(Constants.ERELAY_STATUS_CLOSE)) {// 0,2

                    new opeErelay().execute(buttonOpen, co,
                            Constants.ACTION_RELAY_OPEN, view);// "0");

                } else if (co.getStatus().equals(Constants.ERELAY_STATUS_OPEN)) {// 1
                    new opeErelay().execute(buttonOpen, co,
                            Constants.ACTION_RELAY_CLOSE, view);// "1");
                }

            }
        });

        erelayControlsWarm.setOnClickListener(new OnClickListener() {//自控策略

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showErelayDialogControls(co);
            }
        });

        llErelayDeviceControl.addView(view);

    }


    /**
     * 双继电器
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    private void initErelay2(final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean co) {
        // TODO Auto-generated method stub
        final View view = inflater.inflate(R.layout.erelay2_lay, null);
        view.setTag(co.getSn());
        relayMap.put(co.getSn(), co);
        ViewMap.put(co.getSn(), view);
        ImageView erelay2Ico = (ImageView) view.findViewById(R.id.water_valve);
        // 设备IEEE
        TextView erelay2Ieee = (TextView) view.findViewById(R.id.erelay2_ieee);
        // 设备名称
        TextView erelay2Name = (TextView) view.findViewById(R.id.erelay2_name);
        // 设备状态
        TextView tvErelayTwoStatus = (TextView) view
                .findViewById(R.id.tv_erelay_two_status);
        // 温控策略
        TextView ventilationControlsWarm = (TextView) view
                .findViewById(R.id.ventilation_controls_warm);
        // 温控策略布局
        RelativeLayout ventilationWarmControlLay = (RelativeLayout) view
                .findViewById(R.id.ventilation_warm_control_lay);
        // 控制布局
        LinearLayout ventilationControlButtonLay = (LinearLayout) view
                .findViewById(R.id.ventilation_control_button_lay);
        // 进度布局
        LinearLayout erelay2ProgressLay = (LinearLayout) view
                .findViewById(R.id.erelay2_progress_lay);
        final TextView erelay2HandStatus = (TextView) view// 手
                .findViewById(R.id.erelay2_handstatus);
        final ToggleButton ventilationIsControl = (ToggleButton) view// 模式切换
                .findViewById(R.id.erelay2_is_control);
        final TextView erelay2AutoStatus = (TextView) view// 自
                .findViewById(R.id.erelay2_autostatus);

        TextView forward = (TextView) view// 上
                .findViewById(R.id.erelay2_forward);
        TextView stop = (TextView) view// 停
                .findViewById(R.id.erelay2_stop);
        TextView back = (TextView) view// 下
                .findViewById(R.id.erelay2_back);

        // 设备类型
        String ErelayDoubleExtendType = DeviceType
                .getDefaultNameByExtendType(co.getDevExtendType());

        erelay2Name.setText(TextUtils.isEmpty(co.getDevName()) ? "" : co
                .getDevName());
        erelay2Ieee.setText(TextUtils.isEmpty(co.getSn()) ? "" : co.getSn());
        @SuppressWarnings("static-access")
        int imageId = MineApplication.getInstance().getComponentIcon(
                // 获取对应设备图片
                co.getDevType(), co.getDevExtendType(),
                co.getOnlineState(), co.getDevName());
        erelay2Ico.setImageResource(imageId);
        if (Constants.STATUS_ONLINE.equals(co.getOnlineState())) {
            tvErelayTwoStatus.setText("在线");
            tvErelayTwoStatus.setTextSize(10);
            ventilationControlsWarm.setEnabled(true);
            ventilationWarmControlLay.setEnabled(true);
            ventilationControlButtonLay.setEnabled(true);
            ventilationIsControl.setEnabled(true);
            setErelayButtonStatus(forward, stop, back, co.getStatus(), true);

            tvErelayTwoStatus.setTextColor(getResources().getColor(
                    R.color.black));
        } else if (Constants.STATUS_OFFLINE.equals(co.getOnlineState())
                || null == co.getOnlineState()) {
            tvErelayTwoStatus.setText("离线");
            tvErelayTwoStatus.setTextSize(10);
            ventilationWarmControlLay.setEnabled(false);
            ventilationControlsWarm.setEnabled(false);
            ventilationControlButtonLay.setEnabled(false);
            ventilationIsControl.setEnabled(false);
            tvErelayTwoStatus
                    .setTextColor(getResources().getColor(R.color.red));
            setErelayButtonStatus(forward, stop, back, co.getStatus(), false);
        }

        if (ErelayDoubleExtendType.equals("放风机控制器")) {
            erelay2ProgressLay.setVisibility(View.VISIBLE);
            ventilationWarmControlLay.setVisibility(View.VISIBLE);

            ProgressBar percentageProgressBar = (ProgressBar) view
                    .findViewById(R.id.percentage_progress_bar);
            TextView currentProgress = (TextView) view
                    .findViewById(R.id.current_progress_numeral);

            PreferencesUtils.putString(mContext, "method" + co.getSn(),
                    co.getSwitchs());

            method = ReturnControlMethod(ventilationIsControl, co);// 返回状态
            String method = PreferencesUtils.getString(mContext,
                    "method" + co.getSn());
            setElelay2ControlButton(ventilationIsControl, method,
                    erelay2HandStatus, erelay2AutoStatus);// 设置状态

            if (null != co.getConfigInfo()) {

                if (!("2".equals(co.getConfigInfo().getVersion()))
                        || "true".equals(co.getConfigInfo().getIstemcontrol())) {

                    String mt2 = co.getConfigInfo().getMt();
                    String cp2 = co.getConfigInfo().getCp();
                    if (mt2 != null && cp2 != null) {
                        int mt = Integer.parseInt(mt2);
                        int cp = Integer.parseInt(cp2);

                        int result;
                        if (cp <= 0) {
                            result = 0;
                        } else if (cp >= mt) {
                            result = 100;
                        } else {
                            float num = (float) cp / mt;

                            DecimalFormat df = new DecimalFormat("0.00");

                            String current = df.format(num);

                            result = (int) (Float.parseFloat(current) * 100);
                        }

                        percentageProgressBar.setSecondaryProgress(result);
                        currentProgress.setText(result + "");
                    }

                } else {// 位置控制
                    percentageProgressBar.setSecondaryProgress(Integer
                            .parseInt(co.getConfigInfo().getPt() == null ? "0"
                                    : co.getConfigInfo().getPt()));
                    currentProgress.setText(co.getConfigInfo().getPt());
                }
            }
        }
        ventilationControlsWarm
                .setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View arg0) {
                        // TODO Auto-generated method stub
                        if ("2".equals(co.getConfigInfo().getVersion())) {
                            showTowVentilationCheck(co);
                        }
                        return true;
                    }
                });

        ventilationIsControl
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton arg0,
                                                 boolean arg1) {
                        // TODO Auto-generated method stub
                        if (DeviceType.getDefaultNameByExtendType(
                                co.getDevExtendType()).equals("放风机控制器")) {
                            if (arg1) {


                                ventilationIsControl
                                        .setBackgroundResource(R.drawable.button_pitch);
                                erelay2HandStatus.setTextColor(Color
                                        .parseColor("#25C730"));
                                erelay2AutoStatus.setTextColor(Color.GRAY);
                                new opeErelayDouble().execute(
                                        ventilationIsControl, co,
                                        "ventilationone"
                                                + Constants.AUTO_CONTROL
                                                + "open");

                            } else {
                                if (Integer.parseInt(co.getConfigInfo()
                                        .getPd()) == 0
                                        || "".equals(co.getConfigInfo()
                                        .getPd())
                                        || co.getConfigInfo().getPd() == null) {
                                    ventilationIsControl
                                            .setBackgroundResource(R.drawable.button_open);
                                    TAUtils.toastMessage((Activity) mContext,
                                            "请设置温控策略");
                                    ventilationIsControl.setChecked(true);
                                    return;

                                }
                                ventilationIsControl
                                        .setBackgroundResource(R.drawable.button_pitch);
                                erelay2HandStatus.setTextColor(Color.GRAY);
                                erelay2AutoStatus.setTextColor(Color
                                        .parseColor("#25C730"));
                                new opeErelayDouble().execute(
                                        ventilationIsControl, co,
                                        "ventilationone"
                                                + Constants.HAND_CONTROL
                                                + "open");
                            }
                        }
                    }
                });

        ventilationControlsWarm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showErelay2WarmControlDialog(co);
            }
        });
        controlRelayTwoForward(forward, co, "");
        controlRelayTwoStop(stop, co, "");
        controlRelayTwoBack(back, co, "");

        llErelay2DeviceControl.addView(view);
    }

    /**
     * 初始化当前设备策略模式状态
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    private void setElelay2ControlButton(final ToggleButton erelayopen,
                                         String method, TextView erelayHandStatus, TextView erelayAutoStatus) {
        if (Constants.HAND_CONTROL.equals(method)) {
            erelayHandStatus.setTextColor(Color.parseColor("#25C730"));
            erelayAutoStatus.setTextColor(Color.GRAY);
            erelayopen.setChecked(true);
        } else if (Constants.AUTO_CONTROL.equals(method)) {
            erelayHandStatus.setTextColor(Color.GRAY);
            erelayAutoStatus.setTextColor(Color.parseColor("#25C730"));
            erelayopen.setChecked(false);
        }
    }

    /**
     * 双继电器状态显示
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    private void setErelayButtonStatus(TextView forward, TextView stop,
                                       TextView back, String status, boolean isEnabled) {

        if (isEnabled) {
            // TODO Auto-generated method stub
            forward.setEnabled(true);
            stop.setEnabled(true);
            back.setEnabled(true);
            if (Constants.ERELAY_STATUS_CLOSE.equals(status)) {
                forward.setBackgroundResource(R.drawable.forward1);
                forward.setTextColor(Color.RED);

                stop.setTextColor(Color.WHITE);
                stop.setBackgroundResource(R.drawable.forward2_focus);
                back.setTextColor(Color.WHITE);
                back.setBackgroundResource(R.drawable.forward3_focus);
            } else if (Constants.ERELAY_STATUS_OPEN.equals(status)) {
                back.setBackgroundResource(R.drawable.forward3);
                back.setTextColor(Color.RED);
                forward.setBackgroundResource(R.drawable.forward1_foucs);
                forward.setTextColor(Color.WHITE);
                stop.setTextColor(Color.WHITE);
                stop.setBackgroundResource(R.drawable.forward2_focus);
            } else if (Constants.ERELAY_STATUS_CLOSE2.equals(status)) {
                stop.setBackgroundResource(R.drawable.forward2);
                stop.setTextColor(Color.RED);
                forward.setBackgroundResource(R.drawable.forward1_foucs);
                forward.setTextColor(Color.WHITE);
                back.setTextColor(Color.WHITE);
                back.setBackgroundResource(R.drawable.forward3_focus);
            }
        } else {
            forward.setEnabled(false);
            stop.setEnabled(false);
            back.setEnabled(false);
            if (Constants.ERELAY_STATUS_CLOSE.equals(status)) {
                forward.setTextColor(Color.RED);
                stop.setTextColor(Color.WHITE);
                back.setTextColor(Color.WHITE);
            } else if (Constants.ERELAY_STATUS_OPEN.equals(status)) {
                back.setTextColor(Color.RED);
                forward.setTextColor(Color.WHITE);
                stop.setTextColor(Color.WHITE);
            } else if (Constants.ERELAY_STATUS_CLOSE2.equals(status)) {
                stop.setTextColor(Color.RED);
                forward.setTextColor(Color.WHITE);
                back.setTextColor(Color.WHITE);
            }

            // 不可点灰色背景
            forward.setBackgroundResource(R.drawable.forward1_foucs_pitch);
            stop.setBackgroundResource(R.drawable.forward2_focus_pitch);
            back.setBackgroundResource(R.drawable.forward3_focus_pitch);
        }

    }

    protected void showTowVentilationCheck(final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean co) {
        // TODO Auto-generated method stub
        final Dialog alertDialog = new AlertDialog.Builder(mContext).create();
        // 设置点击范围外 无效果
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();
        // 填充自己想要的布局
        View ventilationDialog = LayoutInflater.from(mContext).inflate(
                R.layout.calibration_dialog, null);
        ViewMap.put(co.getSn() + "ventilationDialog", ventilationDialog);
        // 设置显示位置
        alertDialog.addContentView(ventilationDialog,
                new ViewGroup.LayoutParams((int) (mContext.getResources()
                        .getDisplayMetrics().widthPixels * 0.9),
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        final ImageView callbration_img = (ImageView) ventilationDialog
                .findViewById(R.id.callbration_img);
        Button callbration_btn_on = (Button) ventilationDialog
                .findViewById(R.id.callbration_btn_on);
        Button callbration_btn_off = (Button) ventilationDialog
                .findViewById(R.id.callbration_btn_off);
        TextView callbration_tv_vcc_max = (TextView) ventilationDialog
                .findViewById(R.id.callbration_tv_vcc_max);
        TextView callbration_tv_vcc_current = (TextView) ventilationDialog
                .findViewById(R.id.callbration_tv_vcc_current);
        TextView callbration_tv_vcc_min = (TextView) ventilationDialog
                .findViewById(R.id.callbration_tv_vcc_min);
        Button callbration_btn_switch1 = (Button) ventilationDialog
                .findViewById(R.id.callbration_btn_switch1);
        Button callbration_btn_switch2 = (Button) ventilationDialog
                .findViewById(R.id.callbration_btn_switch2);
        Button callbration_btn_switch3 = (Button) ventilationDialog
                .findViewById(R.id.callbration_btn_switch3);

        callbration_tv_vcc_max.setText("最大值 :"
                + co.getConfigInfo().getVcc_max());
        callbration_tv_vcc_current.setText("当前值 :"
                + co.getConfigInfo().getVcc_current());
        callbration_tv_vcc_min.setText("最小值 :"
                + co.getConfigInfo().getVcc_min());

        callbration_img.setOnClickListener(new OnClickListener() {// 关闭界面

            @Override
            public void onClick(View arg0) {
                alertDialog.dismiss();
            }
        });
        callbration_btn_on.setOnClickListener(new OnClickListener() {// 打开校验

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated
                // method stub
                callbration_img.setEnabled(false);
                new opeErelayRelayCallbration().execute(

                        co, "ventilationone" + Constants.ACTION_CALLBRATION_ON);
            }
        });
        callbration_btn_off.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated
                // method stub
                callbration_img.setEnabled(true);
                new opeErelayRelayCallbration().execute(co, "ventilationone"
                        + Constants.ACTION_CALLBRATION_OFF);
            }
        });
        callbration_btn_switch1.setOnClickListener(new OnClickListener() {// 上行

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated
                // method stub

                new opeErelayRelayCallbration().execute(co,
                        "ventilationone" + Constants.HAND_CONTROL
                                + Constants.ACTION_FORWARD);

            }
        });
        callbration_btn_switch2.setOnClickListener(new OnClickListener() {// 停止

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated
                // method stub
                new opeErelayRelayCallbration().execute(co,
                        "ventilationone" + Constants.HAND_CONTROL
                                + Constants.ACTION_STOP);
            }
        });
        callbration_btn_switch3.setOnClickListener(new OnClickListener() {// 下行

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated
                // method stub

                new opeErelayRelayCallbration().execute(co,
                        "ventilationone" + Constants.HAND_CONTROL
                                + Constants.ACTION_BACK);
            }
        });

    }

    /**
     * 操作校准
     *
     * @author shuyi
     */
    private class opeErelayRelayCallbration extends
            AsyncTask<Object, Void, String> {
        JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean mComponents = null;
        String mComponId = "";

        @Override
        protected String doInBackground(Object... arg0) {
            String str = "远程控制失败";
            mComponents = (JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean) arg0[0];
            String componentId = mComponents.getSn();
            String oldState = mComponents.getConfigInfo().getResult();
            String action = (String) arg0[1];
            String result = WapiUtil.opeErelay2(sgateSN,
                    componentId, action);
            if (result != null && !"".equals(result)) {
                try {
                    JSONObject object = new JSONObject(result);
                    String ret = object.getString("ret");
                    if ("success".equals(ret)) {
                        if (("ventilationone00").equals(action)) {
                            action = "校准开始";
                            str = action;
                        } else if (("ventilationone01").equals(action)) {
                            action = "校准结束";
                            str = action;
                        } else if (("ventilationone" + Constants.HAND_CONTROL + Constants.ACTION_FORWARD)
                                .equals(action)) {
                            action = "上行";
                            str = action;
                        } else if (("ventilationone" + Constants.HAND_CONTROL + Constants.ACTION_STOP)
                                .equals(action)) {
                            action = "停止";
                            str = action;
                        } else if (("ventilationone" + Constants.HAND_CONTROL + Constants.ACTION_BACK)
                                .equals(action)) {
                            action = "下行";
                            str = action;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return str;
        }

        @Override
        protected void onPostExecute(String result) {
            ToastUtils.makeTextShort(result);
            super.onPostExecute(result);
        }
    }

    /**
     * 操作双继电器，如卷帘机等 发送post请求 del 20170217 jcy
     *
     * @author shuyi 参数依次为：(放风机)控制布局，(放风机)组件，(放风机)控制命令
     */
    private class opeErelayDouble extends AsyncTask<Object, Void, String> {
        JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean mComponents = null;
        String mComponId = "";
        String ventilationAction = "";
        View view = null;

        @Override
        protected String doInBackground(Object... arg0) {
            String str = "远程控制失败";
            mComponents = (JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean) arg0[1];
            String componentId = mComponents.getSn();
            String action = (String) arg0[2];
            view = (View) arg0[0];
            String result = WapiUtil.opeErelay2(mComponents.getOwner(),
                    componentId, action);
            mComponId = componentId;

            if (result != null && !"".equals(result)) {
                try {
                    JSONObject object = new JSONObject(result);
                    String ret = object.getString("ret");
                    if ("success".equals(ret)) {
                        if (DeviceType.getDefaultNameByExtendType(
                                mComponents.getDevExtendType()).equals(
                                "放风机控制器")) {
                            ventilationAction = (String) arg0[2];
                            if (ventilationAction.substring(0, 14).equals(
                                    "ventilationone")) {

                                Message msg = new Message();
                                msg.what = MSG_ERELAY2_CTRL; // 保存状态
                                msg.arg1 = ERROR_ECHO;
                                msg.obj = componentId;
                                mHandler.sendMessageDelayed(msg, 3000);

                            }
                        } else {

                            Message msg = new Message();
                            msg.what = MSG_ERELAY2_CTRL;
                            msg.arg1 = ERROR_ECHO;
                            msg.obj = componentId;
                            mHandler.sendMessageDelayed(msg, 3000);

                        }
                        if ("forward".equals(action)) {
                            if (mComponents.getDevName().equals("智能排风机")) {
                                action = "运行";
                                str = "远程控制：" + action;
                            } else {
                                action = "上行";
                                str = "远程控制：" + action;
                            }
                        } else if ("stop".equals(action)) {
                            action = "停止";
                            str = "远程控制：" + action;

                            // 模式切换
                        } else if (("ventilationone" + Constants.HAND_CONTROL + "open")
                                .equals(action)) {
                            action = "手动控制";
                            str = "模式切换：" + action;
                        } else if (("ventilationone" + Constants.AUTO_CONTROL + "open")
                                .equals(action)) {
                            action = "温度控制";
                            str = "模式切换：" + action;
                            // 一代放风机控制成功的提醒
                        } else if (("ventilationone" + Constants.HAND_CONTROL + Constants.ACTION_FORWARD)
                                .equals(action)) {
                            action = "上行";
                            str = "远程控制：" + action;
                        } else if (("ventilationone" + Constants.HAND_CONTROL + Constants.ACTION_STOP)
                                .equals(action)) {
                            action = "停止";
                            str = "远程控制：" + action;
                        } else if (("ventilationone" + Constants.HAND_CONTROL + Constants.ACTION_BACK)
                                .equals(action)) {
                            action = "下行";
                            str = "远程控制：" + action;
                        } else if ("back".equals(action)) {
                            action = "下行";
                            str = "远程控制：" + action;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return str;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            TAUtils.toastMessage((Activity) mContext, result);
        }
    }

    List<CheckBox> checkBoxList = new ArrayList<CheckBox>();

    private void showErelay2WarmControlDialog(final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean co) {

        // 创建dialog
        final Dialog dialog = new AlertDialog.Builder(mContext).create();
        // 设置点击范围外 无效果
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        // 填充自己想要的布局
        View ventilationDialog = LayoutInflater.from(mContext).inflate(
                R.layout.erelay2_ventilation_dialog, null);
        // 设置显示位置
        dialog.addContentView(ventilationDialog,
                new ViewGroup.LayoutParams((int) (mContext.getResources()
                        .getDisplayMetrics().widthPixels * 0.9),
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        // 初始化
        final LinearLayout TempImage_CheckBox = (LinearLayout) ventilationDialog
                .findViewById(R.id.TempImage_CheckBox);
        ImageView ventilation_iv = (ImageView) ventilationDialog
                .findViewById(R.id.ventilation_iv);
        final TimePicker timepickerStart = (TimePicker) ventilationDialog
                .findViewById(R.id.timepicker_start);
        final TimePicker timepickerEnd = (TimePicker) ventilationDialog
                .findViewById(R.id.timepicker_end);
        final CheckBox calModeChoose = (CheckBox) ventilationDialog
                .findViewById(R.id.cal_mode_choose);
        final CheckBox calModeChooseBig = (CheckBox) ventilationDialog
                .findViewById(R.id.cal_mode_choose_big);
        final EditText reserveValidationTimeEdit = (EditText) ventilationDialog
                .findViewById(R.id.reserve_validation_time_edit);
        final EditText adapterRelayEttemUp = (EditText) ventilationDialog
                .findViewById(R.id.adapter_relay_ettem_up);
        final EditText adapterRelayEttemDown = (EditText) ventilationDialog
                .findViewById(R.id.adapter_relay_ettem_down);
        final EditText oprateTimeLength = (EditText) ventilationDialog
                .findViewById(R.id.oprateTimeLength);
        final EditText oprateStepLength = (EditText) ventilationDialog
                .findViewById(R.id.oprateStepLength);
        final EditText oprateCycle = (EditText) ventilationDialog
                .findViewById(R.id.oprateCycle);

        final TextView opratePercentText = (TextView) ventilationDialog
                .findViewById(R.id.opratePercentText);
        final EditText opratepercent = (EditText) ventilationDialog
                .findViewById(R.id.opratepercent);

        final CheckBox ch_time_control = (CheckBox) ventilationDialog
                .findViewById(R.id.ch_time_control);
        final CheckBox ch_position_control = (CheckBox) ventilationDialog
                .findViewById(R.id.ch_position_control);

        final LinearLayout checkbox_control_lay = (LinearLayout) ventilationDialog
                .findViewById(R.id.checkbox_control_lay);
        final LinearLayout time_callbration_lay = (LinearLayout) ventilationDialog
                .findViewById(R.id.time_callbration_lay);
        final LinearLayout time_step_length_lay = (LinearLayout) ventilationDialog
                .findViewById(R.id.time_step_length_lay);

        final Button btn_visitorensure = (Button) ventilationDialog
                .findViewById(R.id.btn_visitorensure);
        final Button btn_visitorcancel = (Button) ventilationDialog
                .findViewById(R.id.btn_visitorcancel);
        // 把时间控件设置成显示24小时
        timepickerStart.setIs24HourView(true);
        timepickerEnd.setIs24HourView(true);
        if ("2".equals(co.getConfigInfo().getVersion())) {
            checkbox_control_lay.setVisibility(View.VISIBLE);

        } else {
            checkbox_control_lay.setVisibility(View.GONE);
        }

        ch_time_control
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton arg0,
                                                 boolean arg1) {
                        // TODO Auto-generated method stub
                        if (arg1) {
                            ch_position_control.setChecked(false);
                            time_callbration_lay.setVisibility(View.VISIBLE);
                            time_step_length_lay.setVisibility(View.VISIBLE);
                            opratePercentText.setVisibility(View.GONE);
                            opratepercent.setVisibility(View.GONE);
                        } else {
                            ch_position_control.setChecked(true);
                            time_callbration_lay.setVisibility(View.GONE);
                            time_step_length_lay.setVisibility(View.GONE);
                            opratePercentText.setVisibility(View.VISIBLE);
                            opratepercent.setVisibility(View.VISIBLE);
                        }

                    }
                });

        ch_position_control
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton arg0,
                                                 boolean arg1) {
                        // TODO Auto-generated method stub
                        if (arg1) {
                            ch_time_control.setChecked(false);
                            time_callbration_lay.setVisibility(View.GONE);
                            time_step_length_lay.setVisibility(View.GONE);
                            opratePercentText.setVisibility(View.VISIBLE);
                            opratepercent.setVisibility(View.VISIBLE);
                        } else {
                            ch_time_control.setChecked(true);
                            time_callbration_lay.setVisibility(View.VISIBLE);
                            time_step_length_lay.setVisibility(View.VISIBLE);
                            opratePercentText.setVisibility(View.GONE);
                            opratepercent.setVisibility(View.GONE);

                        }

                    }
                });
        // 异步请求网络
        MineApplication.executorService.submit(new Runnable() {
            @Override
            public void run() {
                // 请求网络
                String deviceinfos = WapiUtil.getDeviceinfos(co.getSn());
                Gson gson = new Gson();
                final Components ErelayDouble = gson.fromJson(deviceinfos,
                        Components.class);
                co.getConfigInfo() .setPd(ErelayDouble.getConfig_info().getPd());
                mComponents = ErelayDouble;
                // ui线程
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    public void run() {
                        if (ErelayDouble != null) {
                            String ot = ErelayDouble.getConfig_info().getOt();
                            String ct = ErelayDouble.getConfig_info().getCt();
                            String tl = ErelayDouble.getConfig_info().getTl();
                            String sl = ErelayDouble.getConfig_info().getSl();
                            String pd = ErelayDouble.getConfig_info().getPd();
                            String sth = ErelayDouble.getConfig_info().getSth();
                            String stm = ErelayDouble.getConfig_info().getStm();
                            String eth = ErelayDouble.getConfig_info().getEth();
                            String etm = ErelayDouble.getConfig_info().getEtm();
                            String rt = ErelayDouble.getConfig_info().getRt();
                            String percent = ErelayDouble.getConfig_info()
                                    .getPercent();

                            if (ErelayDouble.getConfig_info().isIstemcontrol()
                                    && "2".equals(ErelayDouble.getConfig_info() .getVersion())) {
                                ch_time_control.setChecked(true);
                                ch_position_control.setChecked(false);
                                time_callbration_lay
                                        .setVisibility(View.VISIBLE);
                                time_step_length_lay
                                        .setVisibility(View.VISIBLE);
                                opratePercentText.setVisibility(View.GONE);
                                opratepercent.setVisibility(View.GONE);
                            } else if ("2".equals(ErelayDouble.getConfig_info()
                                    .getVersion())) {
                                ch_time_control.setChecked(false);
                                ch_position_control.setChecked(true);
                                time_callbration_lay.setVisibility(View.GONE);
                                time_step_length_lay.setVisibility(View.GONE);
                                opratePercentText.setVisibility(View.VISIBLE);
                                opratepercent.setVisibility(View.VISIBLE);
                            }

                            if (ot != null) {
                                adapterRelayEttemUp.setText(ot);
                            }
                            if (ct != null) {
                                adapterRelayEttemDown.setText(ct);
                            }
                            if (tl != null) {
                                oprateTimeLength.setText(tl);
                            }
                            if (sl != null) {
                                oprateStepLength.setText(sl);
                            }
                            if (pd != null) {
                                oprateCycle.setText(pd);
                            }
                            if (sth != null) {
                                timepickerStart.setCurrentHour(Integer
                                        .parseInt(sth));
                            }
                            if (stm != null) {
                                timepickerStart.setCurrentMinute(Integer
                                        .parseInt(stm));
                            }
                            if (eth != null) {
                                timepickerEnd.setCurrentHour(Integer
                                        .parseInt(eth));
                            }
                            if (etm != null) {
                                timepickerEnd.setCurrentMinute(Integer
                                        .parseInt(etm));
                            }
                            if (rt != null) {
                                reserveValidationTimeEdit.setText(rt);
                            }

                            if (percent != null) {
                                opratepercent.setText(percent);
                            }
                            List<Components.ConfigInfoBean.ThBean> th = ErelayDouble.getConfig_info()
                                    .getTh();
                            if (th != null) {
                                TempImage_CheckBox.setVisibility(View.VISIBLE);
                                for (int i = 0; i < th.size(); i++) {
                                    String icd = th.get(i).getIcd();
                                    CheckBox checkBox = new CheckBox(mContext);
                                    checkBox.setText(th.get(i).getDn());
                                    if ("0".equals(icd)) {// 未选
                                        checkBox.setChecked(false);
                                    } else if ("1".equals(icd)) {// 选中
                                        checkBox.setChecked(true);
                                    }
                                    TempImage_CheckBox.addView(checkBox);
                                    checkBoxList.add(checkBox);
                                }
                            }
                        }
                    }
                });
            }
        });

        // 点击确定按钮上传数据
        btn_visitorensure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // mComponents.setSn(co.getSn());
                // 开始时间
                Integer currentHourStart = timepickerStart.getCurrentHour();
                Integer currentMinuteStart = timepickerStart.getCurrentMinute();
                // 结束时间
                Integer currentHourEnd = timepickerEnd.getCurrentHour();
                Integer currentMinuteEnd = timepickerEnd.getCurrentMinute();

                String startTime = currentHourStart + ":" + currentMinuteStart;
                Date strToDateLong = strToDateLong(startTime);
                String endTime = currentHourEnd + ":" + currentMinuteEnd;
                Date strToDateLong2 = strToDateLong(endTime);
                if (strToDateLong.getTime() >= strToDateLong2.getTime()) {
                    TAUtils.toastMessage((Activity) mContext,
                            "结束时间不能小于开始时间,请从新设置");
                    return;
                }
                mComponents.getConfig_info().setIstemcontrol(
                        ch_time_control.isChecked());// 提前存
                // 后面取
                // 开始
                mComponents.getConfig_info().setSth(currentHourStart + "");
                mComponents.getConfig_info().setStm(currentMinuteStart + "");
                // 结束
                mComponents.getConfig_info().setEth(currentHourEnd + "");
                mComponents.getConfig_info().setEtm(currentMinuteEnd + "");

                // 温度
                String adapterRelayEttemUptx = adapterRelayEttemUp.getText()
                        .toString();
                String adapterRelayEttemDowntx = adapterRelayEttemDown
                        .getText().toString();
                if (adapterRelayEttemUptx == null
                        || "".equals(adapterRelayEttemUptx)) {
                    TAUtils.toastMessage((Activity) mContext, "开始温度不能为空,请从新设置");
                    return;
                }
                if (adapterRelayEttemDowntx == null
                        || "".equals(adapterRelayEttemDowntx)) {
                    TAUtils.toastMessage((Activity) mContext, "结束温度不能为空,请从新设置");
                    return;
                }
                if (Float.parseFloat(adapterRelayEttemDowntx) > Float
                        .parseFloat(adapterRelayEttemUptx)) {
                    TAUtils.toastMessage((Activity) mContext,
                            "结束温度不能大于开始温度,请从新设置");
                    return;
                }
                mComponents.getConfig_info().setOt(adapterRelayEttemUptx);
                mComponents.getConfig_info().setCt(adapterRelayEttemDowntx);

                if (mComponents.getConfig_info().getVersion() == null || "1".equals(mComponents.getConfig_info().getVersion())
                        || mComponents.getConfig_info().isIstemcontrol()) {
                    // 时长
                    String oprateTimeLengthtx = oprateTimeLength.getText()
                            .toString();
                    String oprateStepLengthtx = oprateStepLength.getText()
                            .toString();

                    if (oprateTimeLengthtx == null
                            || "".equals(oprateTimeLengthtx)) {
                        TAUtils.toastMessage((Activity) mContext,
                                "时长不能为空,请从新设置");
                        return;
                    }
                    if (oprateStepLengthtx == null
                            || "".equals(oprateStepLengthtx)) {
                        TAUtils.toastMessage((Activity) mContext,
                                "步长不能为空,请从新设置");
                    }

                    mComponents.getConfig_info().setTl(oprateTimeLengthtx);
                    co.getConfigInfo().setTl(oprateTimeLengthtx);
                    mComponents.getConfig_info().setMt(oprateTimeLengthtx);
                    mComponents.getConfig_info().setSl(oprateStepLengthtx);

                    // 最小
                    boolean checked = calModeChoose.isChecked();
                    if (checked) {
                        mComponents.getConfig_info().setMinc("1");

                    } else {
                        mComponents.getConfig_info().setMinc("0");
                    }

                    // 最大
                    boolean checkedBig = calModeChooseBig.isChecked();
                    if (checkedBig) {
                        mComponents.getConfig_info().setMaxc("1");

                    } else {
                        mComponents.getConfig_info().setMaxc("0");
                    }

                    if (calModeChoose.isChecked()
                            && calModeChooseBig.isChecked()) {
                        TAUtils.toastMessage((Activity) mContext,
                                "校准真能勾选一个,请从新设置");
                        return;
                    }

                    // 保留时间
                    String reserveValidationTimetx = reserveValidationTimeEdit
                            .getText().toString();
                    if (reserveValidationTimetx == null
                            || reserveValidationTimetx == "") {
                        TAUtils.toastMessage((Activity) mContext,
                                "保留时间不能为空,请从新设置");
                        return;
                    }
                    mComponents.getConfig_info().setRt(reserveValidationTimetx);

                } else {
                    String opratePercent = opratepercent.getText().toString();
                    if (opratePercent == null || "".equals(opratePercent)) {
                        TAUtils.toastMessage((Activity) mContext,
                                "增量不能为空,请从新设置");
                        return;
                    }
                    mComponents.getConfig_info().setPercent(opratePercent);
                }
                String oprateCycletx = oprateCycle.getText().toString();
                if (oprateCycletx == null || "".equals(oprateCycletx)) {
                    TAUtils.toastMessage((Activity) mContext, "周期不能为空,请从新设置");
                    return;
                }
                mComponents.getConfig_info().setPd(oprateCycletx);

                for (int i = 0; i < TempImage_CheckBox.getChildCount(); i++) {
                    if (TempImage_CheckBox.getChildAt(i) instanceof CheckBox) {
                        final Components.ConfigInfoBean.ThBean thBean = mComponents.getConfig_info()
                                .getTh().get(i);
                        CheckBox childAt = (CheckBox) TempImage_CheckBox
                                .getChildAt(i);
                        boolean checked2 = childAt.isChecked();
                        if (checked2) {
                            thBean.setIcd("1");
                        } else {
                            thBean.setIcd("0");
                        }
                    }
                }
                Gson gson = new Gson();
                final String json = gson.toJson(mComponents);
                Log.v(TAG, json);
                if (Constants.STATUS_OFFLINE.equals(co.getOnlineState())) {
                    TAUtils.toastMessage((Activity) mContext, "设备离线不能提交策略");
                    return;
                }

                try {
                    final JSONObject jsonObject = new JSONObject(json);
                    Future<?> future = MineApplication.executorService
                            .submit(new Runnable() {

                                @Override
                                public void run() {
                                    WapiUtil.temperatureControlStrategy(
                                            sgateSN,
                                            mComponents.getSn(), jsonObject);
                                }
                            });
                    Object object = future.get();
                    if (object == null) {
                        ((Activity) mContext).runOnUiThread(new Runnable() {
                            public void run() {
                                dialog.dismiss();

                                TAUtils.toastMessage((Activity) mContext,
                                        "温控策略已发送");
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // 键盘
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        // 关闭dialog
        ventilation_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        // 关闭dialog
        btn_visitorcancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * @Description:双继电器上行  * @param @param stopTextview  * @param @param
     * relayTwoLayout  * @param @param relayTwoCo  * void  *
     * @exception:  * @author: axin  *
     * @time:2016-6-2 下午4:29:49
     */
    private void controlRelayTwoForward(final TextView forwardTextview,
                                        final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean relayTwoCo, final String poolIndex) {

        forwardTextview.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                forwardTextview
                        .setBackgroundResource(R.drawable.forward1_foucs_pitch);
                if (poolIndex == "" || poolIndex.isEmpty()) {

                    if (DeviceType.getDefaultNameByExtendType(
                            relayTwoCo.getDevExtendType()).equals("放风机控制器")) {

                        new opeErelayDouble().execute(forwardTextview,
                                relayTwoCo, "ventilationone"
                                        + Constants.HAND_CONTROL
                                        + Constants.ACTION_FORWARD);
                    } else {
                        new opeErelayDouble().execute(forwardTextview,
                                relayTwoCo, Constants.ACTION_FORWARD);
                    }

                } else {
                    new opeErelayDoubleRealy().execute(forwardTextview,
                            relayTwoCo, Constants.ACTION_FORWARD, poolIndex);

                }
            }
        });
    }

    /**
     *  * @Description:双继电器停止  * @param @param stopTextview  * @param @param
     * relayTwoLayout  * @param @param relayTwoCo  * void  * @exception:  *
     *
     * @author: axin  * @time:2016-6-2 下午4:29:49
     */
    private void controlRelayTwoStop(final TextView stopTextview,
                                     final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean relayTwoCo, final String poolIndex) {

        stopTextview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTextview
                        .setBackgroundResource(R.drawable.forward2_focus_pitch);
                if (poolIndex == "" || poolIndex == null) {
                    if (DeviceType.getDefaultNameByExtendType(
                            relayTwoCo.getDevExtendType()).equals("放风机控制器")) {

                        new opeErelayDouble().execute(stopTextview, relayTwoCo,
                                "ventilationone" + Constants.HAND_CONTROL
                                        + Constants.ACTION_STOP);
                    } else {
                        new opeErelayDouble().execute(stopTextview, relayTwoCo,
                                Constants.ACTION_STOP);
                    }

                } else {

                    new opeErelayDoubleRealy().execute(stopTextview,
                            relayTwoCo, Constants.ACTION_STOP, poolIndex);

                }
            }
        });
    }

    /**
     *  * @Description:双继电器下行  * @param @param backTextview   * @param @param
     * relayTwoLayout  * @param @param relayTwoCo  * void  * @exception:  *
     *
     * @author: axin  * @time:2016-6-2 下午4:30:06
     */
    private void controlRelayTwoBack(final TextView backTextview,
                                     final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean relayTwoCo, final String poolIndex) {

        backTextview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                backTextview
                        .setBackgroundResource(R.drawable.forward3_focus_pitch);
                if (poolIndex == "" || poolIndex == null) {

                    if (DeviceType.getDefaultNameByExtendType(
                            relayTwoCo.getDevExtendType()).equals("放风机控制器")) {
                        new opeErelayDouble().execute(backTextview, relayTwoCo,
                                "ventilationone" + Constants.HAND_CONTROL
                                        + Constants.ACTION_BACK);
                    } else {
                        new opeErelayDouble().execute(backTextview, relayTwoCo,
                                Constants.ACTION_BACK);
                    }

                } else {

                    new opeErelayDoubleRealy().execute(backTextview,
                            relayTwoCo, Constants.ACTION_BACK, poolIndex);

                }
            }
        });
    }

    /**
     * 操作电器柜双继电器
     *
     * @author EVA
     */
    private class opeErelayDoubleRealy extends AsyncTask<Object, Void, String> {
        JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean mComponents = null;
        String mComponId = "";
        String poolIndex = "";
        String ventilationAction = "";

        @Override
        protected String doInBackground(Object... arg0) {
            String str = "远程控制失败";
            mComponents = (JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean) arg0[0];
            String componentId = mComponents.getSn();
            String action = (String) arg0[1];
            poolIndex = (String) arg0[2];
            // 数据请求
            mComponId = componentId;
            if (null != poolIndex && !poolIndex.equals("")) {

                List<JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list> pool_list = mComponents.getPool_list();
                for (int i = 0; i < pool_list.size(); i++) {
                    JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list relay = pool_list.get(i);
                    String pool_index = relay.getPool_index() + "";
                    if (pool_index.equals(poolIndex)) {

                        String result = WapiUtil.opeErelay2Realy(
                                mComponents.getOwner(), componentId, action,
                                poolIndex);

                        if (result != null && !"".equals(result)) {
                            try {
                                JSONObject object = new JSONObject(result);
                                String ret = object.getString("ret");
                                if ("success".equals(ret)) {

                                    Message msg = new Message();
                                    msg.what = WAPI.MSG_ERELAY_BOX;
                                    msg.arg1 = ERROR_ECHO;
                                    msg.obj = componentId;
                                    mHandler.sendMessageDelayed(msg, 6000);

                                    if ("forward".equals(action)) {
                                        if (relay.getDev_name().equals("智能排风机")) {
                                            action = "运行";
                                            str = "远程控制：" + action;
                                        } else {
                                            action = "上行";
                                            str = "远程控制：" + action;
                                        }
                                    } else if ("stop".equals(action)) {
                                        action = "停止";
                                        str = "远程控制：" + action;
                                    } else if ("back".equals(action)) {
                                        action = "下行";
                                        str = "远程控制：" + action;
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            return str;
        }

        @Override
        protected void onPostExecute(String result) {
            TAUtils.toastMessage((Activity) mContext, result);
            super.onPostExecute(result);
        }
    }

    /**
     * 电器柜
     *
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-29 下午2:14:23
     */
    private void initRelayBox(final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean co) {
        // TODO Auto-generated method stub
        final View view = inflater.inflate(R.layout.realybox_lay, null);
        view.setTag(co.getSn());
        relayMap.put(co.getSn(), co);
        ViewMap.put(co.getSn(), view);
        TextView relayName = (TextView) view.findViewById(R.id.relaybox_name);
        TextView relayIeee = (TextView) view.findViewById(R.id.relaybox_ieee);
        TextView relayStatus = (TextView) view
                .findViewById(R.id.relaybox_status);
        TextView relayBoxCount = (TextView) view
                .findViewById(R.id.relaybox_count);
        ImageView waterValve = (ImageView) view.findViewById(R.id.water_valve);
        TextView relayboxBtn = (TextView) view.findViewById(R.id.relaybox_btn);

        relayIeee.setText(TextUtils.isEmpty(co.getSn()) ? "" : co.getSn());
        final String remote = co.getRemote();
        relayName.setText(TextUtils.isEmpty(co.getDevName()) ? "" : co
                .getDevName());
        if (co.getOnlineState().equals(Constants.STATUS_ONLINE)) {
            relayStatus.setText("在线");
            relayStatus.setTextSize(10);
            relayStatus.setTextColor(getResources().getColor(R.color.black));
        } else if (co.getOnlineState().equals(Constants.STATUS_OFFLINE)
                || co.getOnlineState() == null) {
            relayStatus.setText("离线");
            relayStatus.setTextSize(10);
            relayStatus.setTextColor(getResources().getColor(R.color.red));
        }

        List<JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list> pool_list = co.getPool_list();

        relayBoxCount.setText(String.valueOf(pool_list.size()));

        @SuppressWarnings("static-access")
        int imageId = MineApplication.getInstance().getComponentIcon(
                co.getDevType(), co.getDevExtendType(),
                co.getOnlineState(), co.getDevName());

        waterValve.setImageResource(imageId);
        // 跳转电气柜
        relayboxBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // 创建界面
                final AlertDialog dialog = new AlertDialog.Builder(mContext)
                        .create();
                // 设置点击范围外 无效果
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                View realyBoxDialog = LayoutInflater.from(mContext).inflate(
                        R.layout.realybox_dialog, null);
                ViewMap.put(co.getSn() + "box", view);
                dialog.addContentView(
                        realyBoxDialog,
                        new ViewGroup.LayoutParams(
                                (int) (mContext.getResources()
                                        .getDisplayMetrics().widthPixels * 0.9),
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                // 初始化
                TextView alias = (TextView) realyBoxDialog
                        .findViewById(R.id.relaybox_alias);
                TextView control = (TextView) realyBoxDialog
                        .findViewById(R.id.relaybox_control);
                TextView name = (TextView) realyBoxDialog
                        .findViewById(R.id.relay_name);
                TextView ieee = (TextView) realyBoxDialog
                        .findViewById(R.id.relaybox_ieee);
                TextView connected = (TextView) realyBoxDialog
                        .findViewById(R.id.relaybox_connected);
                TextView state = (TextView) realyBoxDialog
                        .findViewById(R.id.relaybox_state);
                ImageView ivbtm = (ImageView) realyBoxDialog
                        .findViewById(R.id.realy_iv);
                llRelayBoxDialog = (LinearLayout) realyBoxDialog
                        .findViewById(R.id.ll_relay_box_dialog);

                // 绑定数据
                alias.setText(TextUtils.isEmpty(co.getDevName()) ? "" : co
                        .getDevName());
                ieee.setText(TextUtils.isEmpty(co.getSn()) ? "" : co.getSn());

                name.setText(TextUtils.isEmpty(co.getDevName()) ? "" : co
                        .getDevName());

                if ("1".equals(remote)) {
                    control.setText("远程控制");
                    control.setTextColor(getResources().getColor(R.color.black));
                } else if ("0".equals(remote)) {
                    control.setText("本地控制");
                    control.setTextColor(getResources().getColor(R.color.red));
                }
                if (co.getOnlineState() != null) {
                    if (co.getOnlineState().equals("online")) {
                        state.setText("在线");
                        state.setTextSize(12);
                        state.setTextColor(getResources().getColor(
                                R.color.black));
                    } else if (co.getOnlineState().equals("offline")) {
                        state.setText("离线");
                        state.setTextSize(12);
                        state.setTextColor(getResources().getColor(R.color.red));

                    }
                }
                // 排序

                List<JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list> pool_list = co.getPool_list();
                if (pool_list != null) {

                    Collections.sort(pool_list, new Comparator<JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list>() {

                        @Override
                        public int compare(JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list lhs, JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list rhs) {
                            if (lhs.getPool_index() > rhs.getPool_index()) {
                                return 1;
                            }
                            if (lhs.getPool_index() == rhs.getPool_index()) {
                                return 0;
                            }
                            return -1;
                        }
                    });

                    // 通道数
                    connected.setText(String.valueOf(pool_list.size()));
                    llRelayBoxDialog.removeAllViews();
                    // 区分单双viewco.getusePoolList
                    for (int i = 0; i < pool_list.size(); i++) {
                        if (pool_list.get(i).getDev_type().equals("DOUBLE")) {
                            // 双继电器

                            relayBoxDoubleErelay(pool_list.get(i), co, remote);

                        } else if (pool_list.get(i).getDev_type()
                                .equals("SINGLE")) {
                            // 单继电器

                            relayBoxSinglErelay(pool_list.get(i), co, remote);
                        }
                    }
                }
                // 退出按钮
                ivbtm.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        llErealyBoxDeviceControl.addView(view);
    }

    // 电器柜单
    @SuppressWarnings("unused")
    protected void relayBoxSinglErelay(final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list poolList,
                                       final JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean co, String remote) {
        // TODO Auto-generated method stub

        final View view = inflater.inflate(R.layout.realyboxerelay_lay, null);
        TextView realyBoxName = (TextView) view
                .findViewById(R.id.realybox_name);
        TextView realyBoxIeee = (TextView) view
                .findViewById(R.id.realybox_ieee);
        TextView realyBoxStatus = (TextView) view
                .findViewById(R.id.erelay_status);
        TextView realyBoxType = (TextView) view
                .findViewById(R.id.realybox_type);
        final ImageView buttonOpen = (ImageView) view
                .findViewById(R.id.button_open);
        ImageView waterValve = (ImageView) view.findViewById(R.id.water_valve);

        final String poolIndex = String.valueOf(poolList.getPool_index());
        ViewMap.put(co.getSn() + poolIndex, view);
        realyBoxName.setText(TextUtils.isEmpty(poolList.getDev_name()) ? ""
                : poolList.getDev_name());
        realyBoxIeee.setText(poolIndex);
        waterValve.setImageResource(R.drawable.relay);

        String status = String.valueOf(poolList.getStatus());

        // 设备sn 加上通道号
        PreferencesUtils
                .putInt(mContext, co.getSn() + poolList.getPool_index(),
                        Integer.parseInt(status));
        // 获取保存的单继电器状态
        int realyBoxErelayStatus = PreferencesUtils.getInt(mContext, co.getSn()
                + poolList.getPool_index(), Integer.parseInt(status));

        // 单继电器设置
        if (status == null) {
            status = "0";
        }

        if (null != status) {
            buttonOpen.setVisibility(View.VISIBLE);
            if (realyBoxErelayStatus == 0 || realyBoxErelayStatus == 2) {
                buttonOpen.setImageResource(R.drawable.button_close);
                realyBoxStatus.setText("关闭");
            } else {
                buttonOpen.setImageResource(R.drawable.button_open);
                // 游客模式
                realyBoxStatus.setText("打开");
            }
        } else {
            buttonOpen.setVisibility(View.GONE);
            realyBoxStatus.setText("离线");
        }

        if ("1".equals(remote)) {
            buttonOpen.setEnabled(true);
        } else {// if ("0".equals(remote))

            buttonOpen.setEnabled(false);
            buttonOpen.setImageResource(R.drawable.button_close);

        }

        buttonOpen.setOnClickListener(new OnClickListener() {

            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                /* 0 is close,1 is open */
                // 非游客模式

                buttonOpen.setImageResource(R.drawable.button_pitch);
                if ((Integer.toString(poolList.getStatus()))
                        .equals(Constants.ERELAY_STATUS_OPEN)) {
                    new opeErelayRelay().execute(co,
                            Constants.ACTION_RELAY_CLOSE, poolIndex);// "0");
                } else {
                    new opeErelayRelay().execute(co,
                            Constants.ACTION_RELAY_OPEN, poolIndex);// "1");
                }

            }
        });
        llRelayBoxDialog.addView(view);
    }

    /**
     * 操作电器柜单继电器，如水阀等
     *
     * @author shuyi
     */
    private class opeErelayRelay extends AsyncTask<Object, Void, String> {
        JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean mComponents = null;
        String mComponId = "";
        String poolIndex = null;

        @Override
        protected String doInBackground(Object... arg0) {
            String str = "远程控制失败";
            mComponents = (JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean) arg0[0];
            String componentId = mComponents.getSn();
            String oldState = mComponents.getStatus();
            // 通道号del jcy 20170217
            poolIndex = (String) arg0[2];
            mComponId = componentId;
            String action = (String) arg0[1];
            String result;
            if (poolIndex != null || !poolIndex.equals("")) {

                List<JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list> pool_list = mComponents.getPool_list();

                for (int i = 0; i < pool_list.size(); i++) {
                    JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list PoolList = pool_list.get(i);
                    String pool_index = PoolList.getPool_index() + "";
                    if (poolIndex.equals(pool_index)) {
                        String oldStateRelay = PoolList.getStatus() + "";
                        if ("2".equals(oldStateRelay)) {
                            oldStateRelay = "0";
                        }
                        result = WapiUtil.opeErelayRealy(mComponents.getOwner(),
                                componentId, action, poolIndex);

                        if (result != null && !"".equals(result)
                                && poolIndex != null) {
                            try {
                                JSONObject object = new JSONObject(result);
                                String ret = object.getString("ret");
                                if ("success".equals(ret)) {

                                    Message msg = new Message();
                                    msg.arg1 = ERROR_ECHO;
                                    msg.obj = componentId;
                                    msg.arg2 = Integer.parseInt(poolIndex);
                                    msg.what = MSG_ERELAY_BOX;
                                    mHandler.sendMessageDelayed(msg, 6000);
                                    if (oldStateRelay.equals("0")) {
                                        str = "远程控制:打开";
                                        PreferencesUtils
                                                .putInt(mContext,
                                                        mComponents.getSn()
                                                                + poolIndex, 1);
                                    } else {
                                        str = "远程控制:关闭";
                                        PreferencesUtils
                                                .putInt(mContext,
                                                        mComponents.getSn()
                                                                + poolIndex, 0);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            return str;
        }

        @Override
        protected void onPostExecute(String result) {
            TAUtils.toastMessage((Activity) mContext, result);
            super.onPostExecute(result);
        }
    }


    // 电器柜双
    protected void relayBoxDoubleErelay(JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean.Pool_list poolList, JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean co,
                                        String remote) {
        // TODO Auto-generated method stub
        final View view = inflater.inflate(R.layout.realyboxerelay2_lay, null);

        TextView realyBoxName = (TextView) view
                .findViewById(R.id.realybox_name);
        TextView realyBoxIeee = (TextView) view
                .findViewById(R.id.realybox_ieee);
        ImageView waterValve = (ImageView) view.findViewById(R.id.water_valve);
        TextView realyBoxType = (TextView) view
                .findViewById(R.id.realybox_type);

        final TextView forward = (TextView) view
                .findViewById(R.id.forwardRelay);
        final TextView stop = (TextView) view.findViewById(R.id.stopRelay);
        final TextView back = (TextView) view.findViewById(R.id.backRelay);

        String poolIndex = String.valueOf(poolList.getPool_index());

        ViewMap.put(co.getSn() + poolIndex, view);
        waterValve.setImageResource(R.drawable.relay2);
        realyBoxName.setText(TextUtils.isEmpty(poolList.getDev_name()) ? ""
                : poolList.getDev_name());
        realyBoxIeee.setText(poolIndex);
        realyBoxType
                .setText(TextUtils.isEmpty(poolList.getDev_extend_type()) ? ""
                        : poolList.getDev_extend_type());
        String status = String.valueOf(poolList.getStatus());

        if ("1".equals(remote)) {
            setErelayButtonStatus(forward, stop, back, status, true);
            // 实际控制设备
        } else if ("0".equals(remote)) {
            setErelayButtonStatus(forward, stop, back, status, false);
        }
        controlRelayTwoForward(forward, co, poolIndex);
        controlRelayTwoStop(stop, co, poolIndex);
        controlRelayTwoBack(back, co, poolIndex);

        llRelayBoxDialog.addView(view);
    }


    /**
     * dip转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    protected void initWifiCameraData() {
        // TODO Auto-generated method stub

        llCamerasDeviceControl.removeAllViews();
        for (int i = 0; i < mCameras.size(); i++) {
            // 270 --> 470
            WindowManager wm = (WindowManager) mContext
                    .getSystemService(Context.WINDOW_SERVICE);
            int height = wm.getDefaultDisplay().getHeight();
            FrameLayout frameLayout = new FrameLayout(mContext);
            int px = dip2px(mContext, 270);// 270(int)(height*0.15)
            FrameLayout.LayoutParams fParams = new FrameLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT, px);
            frameLayout.setLayoutParams(fParams);
            frameLayout.setId(i + 1);
            llCamerasDeviceControl.addView(frameLayout);
            resIds.add(frameLayout);
        }
    }

    /**
     * 加载摄像头fragment
     */
    protected void loadFragment() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(mContext, BridgeService.class);
        this.startService(intent);
        webCameraFragments.clear();
        // 根据设备名称排序
        Collections.sort(mCameras, new Comparator<JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean>() {

            @Override
            public int compare(JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean o1, JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean o2) {
                return o1.getDevName().compareTo(o2.getDevName());
            }
        });
        for (int i = 0; i < mCameras.size(); i++) {
            // Log.v("sort", "第" + i + "个：" + mCameras.get(i).getDev_name());
            addFragment(i, mCameras.get(i));
        }

    }

    /**
     * 添加摄像头布局
     *
     * @param idx
     * @param co
     */
    private void addFragment(int idx, JsongHouseAllRelayObject.ObjBean.ReControllerEntityListBean co) {
        // TODO Auto-generated method stub
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        webCameraFragments.add(new WebCameraFragment());
        Bundle args = new Bundle();
        args.putLong("CAMERA_INDEX", idx);
        args.putSerializable("co", co);
        webCameraFragments.get(idx).setArguments(args);

        int resId = resIds.get(idx).getId();
        transaction.replace(resId, webCameraFragments.get(idx));
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    //Activity创建或者从被覆盖、后台重新回到前台时被调用
    @Override
    protected void onResume() {
        isTast = true;
        super.onResume();
    }

    //Activity被覆盖到下面或者锁屏时被调用
    @Override
    protected void onPause() {
        isTast = false;
        super.onPause();
        //有可能在执行完onPause或onStop后,系统资源紧张将Activity杀死,所以有必要在此保存持久数据
    }
}

