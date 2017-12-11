package com.app.itserv.fragments;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.itserv.MineApplication;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.core.beans.CameraStatus;
import com.yycloud.core.beans.Components;
import com.yycloud.core.config.DeviceType;

import hsl.p2pipcam.bean.ContantsModel;
import hsl.p2pipcam.bean.DeviceModel;
import hsl.p2pipcam.component.MyRender;
import hsl.p2pipcam.component.MyRender.RenderListener;
import hsl.p2pipcam.manager.DeviceManager;
import hsl.p2pipcam.manager.listener.PlayListener;

/**
 * NOTE: TODO 先运行第二个再运行行第一个，若相反则崩溃 （device ＝ null？？
 */
public class WebCameraFragment extends BaseFragment implements OnClickListener,
        RenderListener, PlayListener {

    private DeviceManager deviceManager;
    private hsl.p2pipcam.manager.Device device;
    private GLSurfaceView glSurfaceView;
    private MyRender myRender;

    private LinearLayout progressLayout;
    private ImageView ivStatus; // 摄像头的图片状态
    private TextView tvStatus;
    private TextView tvConnectingShow;

    private TextView etDevName;
    //上行
    private TextView tvUp;
    //	下行
    private TextView tvDown;
    //	停止
    private TextView tvStop;
    //	左行
    private TextView tvLeft;
    //	右行
    private TextView tvRight;

    private String mDevOriginalName = "";
    private String mInitDeviceId = "";
    private String mUserName = "";
    private String mPassword = "";

    private ImageView btnConnect;

    final String DEFAULT_USERNAME = "admin";

    private long mDevIdx;
    private int status = 0;// 0为播放，1为暂停
    private boolean isAudio;
    private WebCameraFragment iCameraFragment;
    Components mCameradata;
    private Context mContext;

    public hsl.p2pipcam.manager.Device getDevice() {
        return device;
    }

    public Handler getHandler() {
        return handler;
    }

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_web_camera;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iCameraFragment = this;
        mContext = this.getActivity();
        mCameradata = (Components) getArguments().getSerializable("co");
        mDevIdx = getArguments().getLong("CAMERA_INDEX");
        mInitDeviceId = mCameradata.getSn();
        mUserName = mCameradata.getUser();
        mPassword = mCameradata.getPassword();
        initViews();
        initEvents();
        deviceManager = DeviceManager.getDeviceManager(getActivity());
    }

    private void initViews() {
        //摄像头状态
        tvStatus = (TextView) findViewById(R.id.iw_tv_desc);

        ivStatus = (ImageView) findViewById(R.id.water_valve);
        etDevName = (TextView) findViewById(R.id.iw_et_device_name);
        glSurfaceView = (GLSurfaceView) findViewById(R.id.iw_glsurfaceview_gate);
        myRender = new MyRender(glSurfaceView);
        myRender.setListener(this);
        glSurfaceView.setRenderer(myRender);
        progressLayout = (LinearLayout) findViewById(R.id.progressLayout);
        tvConnectingShow = (TextView) findViewById(R.id.connecting_show);
        btnConnect = (ImageView) findViewById(R.id.iw_btn_connect);
        ImageView category1 = (ImageView) findViewById(R.id.water_valve);
        TextView iw_et_device_ieee = (TextView) findViewById(R.id.iw_et_device_ieee);
        iw_et_device_ieee.setText(mCameradata.getSn());

        int resId = MineApplication
                .getComponentIcon(mCameradata.getDev_type(),
                        mCameradata.getDev_extend_type(),
                        mCameradata.getOnline_state(), mCameradata.getDev_name());
        CameraStatus cameraStatus = MineApplication.getState(
                mCameradata.getOnline_state(), mCameradata.getStatus());
        category1.setImageResource(resId);
        setIPCViews();
    }

    private void setIPCViews() {
        //摄像头别名
        String mDevAliaName = null;
        //设备继承类型
        String mDevExtendName = null;
        mDevAliaName = mCameradata.getDev_alias();
        //摄像头原名
        mDevOriginalName = mCameradata.getDev_name();
        mDevExtendName = DeviceType.getDefaultNameByExtendType(mCameradata.getDev_extend_type());
        System.out.println("===============mDevOriginalName：" + mDevOriginalName);
        System.out.println("===============mDevExtendName：" + mDevExtendName);
        System.out.println("===============mDevAliaName：" + mDevAliaName);
        if ((mDevAliaName == null) || (mDevAliaName.equals("")) || (mDevAliaName.equals("undefined"))) {
            etDevName.setText(mDevOriginalName);
        } else {
            etDevName.setText(mDevAliaName);
        }
    }


    private void initEvents() {
//		初始化控件
        tvUp = (TextView) findViewById(R.id.tv_btn_camera_up);
        tvDown = (TextView) findViewById(R.id.tv_btn_camera_down);
        tvLeft = (TextView) findViewById(R.id.tv_btn_camera_left);
        tvRight = (TextView) findViewById(R.id.tv_btn_camera_right);
        tvStop = (TextView) findViewById(R.id.tv_btn_camera_stop);

//		监听控件点击事件
        tvUp.setOnClickListener(this);
        tvDown.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        tvStop.setOnClickListener(this);
        btnConnect.setOnClickListener(this);
    }

    @Override
    public void onUpdateUI() {
    }

    private void doUp() {
        device.ptzControl(ContantsModel.Cmd.CMD_PTZ_UP);
    }

    private void doDown() {
        device.ptzControl(ContantsModel.Cmd.CMD_PTZ_DOWN);
    }

    private void doLeft() {
        device.ptzControl(ContantsModel.Cmd.CMD_PTZ_LEFT);
    }

    private void doRight() {
        device.ptzControl(ContantsModel.Cmd.CMD_PTZ_RIGHT);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iw_btn_connect:
                String did = mInitDeviceId;
                DeviceModel model = new DeviceModel();
                if (did == null || did.equals("")) {
                    break;
                }
                Log.v("camera", "WebCameraFragment============did=====" + did);
                model.setDevID(did);
                model.setUsername(mUserName);
                model.setPassword(mPassword);
                device = new hsl.p2pipcam.manager.Device();
                device.setDeviceModel(model);

                device.createDevice();
                break;
            case R.id.tv_btn_camera_up:
                if (device == null)
                    ToastUtils.makeTextShort("请连接设备之后重试！");
                else
                    //上行
                    doUp();
                break;
            case R.id.tv_btn_camera_down:
                if (device == null)
                    ToastUtils.makeTextShort("请连接设备之后重试！");
                else
                    //下行
                    doDown();
                break;
            case R.id.tv_btn_camera_left:
                if (device == null)
                    ToastUtils.makeTextShort("请连接设备之后重试！");
                else
                    //左行
                    doLeft();
                break;
            case R.id.tv_btn_camera_right:
                if (device == null)
                    ToastUtils.makeTextShort("请连接设备之后重试！");
                else
                    //右行
                    doRight();
                break;
            case R.id.tv_btn_camera_stop:
                if (device == null)
                    ToastUtils.makeTextShort("请连接设备之后重试！");
                else {
                    if (status == 0) {
                        tvStatus.setText("暂停");
                        device.stopPlayStream();
                        status = 1;
                    } else {
                        status = 0;
                        tvStatus.setText("播放");
                        tvConnectingShow.setText("连接视频...");
                        progressLayout.setVisibility(View.VISIBLE);
                        deviceManager.setPlayListener(this);
                        device.initRecorder();
                        new LoadTask().execute();
                    }
                }
                break;
            default:
                break;
        }
    }

    private class LoadTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            if (device == null)
                return null;
            device.setRender(myRender);
            device.startPlayStream(ContantsModel.MidResolution);
            device.setCameraParam(13, 1024);//主码流(kbps)
            device.setCameraParam(6, 15);//主码流帧率(fps)
            return null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (device != null) {
            device.stopPlayAudio();
            device.stopPlayStream();
            device.releaseRecord();
            device.destoryDevice();
            device = null;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {

                progressLayout.setVisibility(View.INVISIBLE);
                if (msg.arg1 == 0) {
                    tvStatus.setText("连接中");
                    tvConnectingShow.setText("连接中...");
                    progressLayout.setVisibility(View.VISIBLE);
                } else if (msg.arg1 == 100) {
                    tvStatus.setText("在线");
                    ivStatus.setImageResource(R.drawable.camera_online);
                    deviceManager.setPlayListener(iCameraFragment);
                    if (device == null) {
                        Log.v("camera", "WebCameraFragment====摄像头设备为NULL===");
                        return;
                    }
                    device.initRecorder();
                    new LoadTask().execute();
                    btnConnect.setVisibility(View.GONE);
                } else if (msg.arg1 == 101) {
                    tvStatus.setText("连接错误");
                    ivStatus.setImageResource(R.drawable.camera_offline);
                } else if (msg.arg1 == 10) {
                    tvStatus.setText("连接超时");
                    ivStatus.setImageResource(R.drawable.camera_offline);
                } else if (msg.arg1 == 9) {
                    tvStatus.setText("不在线");
                    ivStatus.setImageResource(R.drawable.camera_offline);
                } else if (msg.arg1 == 5) {
                    Log.v("camera", "WebCameraFragment=======" + msg.arg1);
                    tvStatus.setText("无效ID");
                    ivStatus.setImageResource(R.drawable.camera_offline);
                } else if (msg.arg1 == 11) {
                    tvStatus.setText("断开");
                    ivStatus.setImageResource(R.drawable.camera_offline);
                } else if (msg.arg1 == 1) {
                    tvStatus.setText("用户名密码错误");
                    ivStatus.setImageResource(R.drawable.camera_offline);
                }
            } else if (msg.what == 1) {
                progressLayout.setVisibility(View.INVISIBLE);
            }
        }

    };

    @Override
    public void takePicture(byte[] imageBuffer, int width, int height) {
    }

    @Override
    public void initComplete(int size, int width, int height) {
        Message msg = handler.obtainMessage(1, 0, 0);
        handler.sendMessage(msg);
    }

    @Override
    public void cameraGetParamsResult(long userid, String cameraParams) {
    }

    @Override
    public void callBackAudioData(long userID, byte[] pcm, int size) {
    }

    @Override
    public void callBackVideoData(long userID, byte[] data, int type, int size) {
    }

    @Override
    public void smartAlarmCodeGetParamsResult(long userid, String params) {
    }

    @Override
    public void smartAlarmNotify(long userid, String message) {
    }

}
