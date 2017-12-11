package com.app.itserv.activity;

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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.itserv.adapters.BaseBelAdapter;
import com.app.itserv.adapters.DataDictionaryAdapter;
import com.app.itserv.jparser.JsonBaseManagerObject;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonGreenHouseViewObject;
import com.app.itserv.jparser.JsonGreenHouseViewObject.ObjBean.GreenhouseBean;
import com.app.itserv.jparser.JsonGreenhouseAEObject;
import com.app.itserv.jparser.JsonGreenhouseAEObject.ObjBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.DateTimePickDialogUtil;
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
 * 新增、编辑大棚管理
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: GreenHouseAddEditActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-10 下午6:15:28
 */
public class GreenHouseAddEditActivity extends Activity implements
        OnClickListener, OnItemSelectedListener {

    private Context mContext;
    private static final String TAG = "GreenHouseAddEditActivity";
    protected static final int GHOUSEAE_ERROR = 1;
    protected static final int GHOUSEAE_SUCCESS = 2;
    protected static final int GHOUSEAE_VALUES = 3;
    protected static final int REQUEST_BUSINESS = 4;

    protected static final int GHOUSEVIEW_SUCCESS = 5;
    protected static final int GHOUSEVIEW_VALUES = 6;

    private ImageView ImgBack, ImgLacation;
    private TextView tvAddEditTitle, tvActivaDate, tvCancelledDate, tvPicture,
            tvBusinessAdd, tvMapLocation;
    private Button chapswSubmite, btChoose, btChangreset;
    private EditText edGreHouName, edGreHouCode, etTotalArea, etPlanArea,
            etLength, etWidth, etAbout, etBusinessDetailed;
    private DateTimePickDialogUtil dateTimePicKDialog;

    private String grehouaddedit = null, greHouName, greHouCode, activaDate,
            totalArea, planArea, length, width, about, useState, cancelledDate,
            picture, businessAdd, businessDetailed, location;
    private ObjBean gHouseObj;

    private String greenhouseId;
    private String baseName, baseCode, baseId;
    private JsonGreenHouseViewObject.ObjBean gHouseViewBean;
    /* 启用日期start */
    private String mCurrentCountry = "中国";
    private String mCurrentProviceName;
    private String mCurrentCityName;
    private String mCurrentDistrictName;
    private String initDateTime;
    /* 启用日期end */

    // 所属基地
    protected static final int BASELIST_SUCCESS = 7;
    protected static final int BASELIST_VALUES = 8;

    private Spinner spBaseBel;// 所属基地
    private List<JsonBaseManagerObject.ObjBean> baseObjList;
    private BaseBelAdapter baseBleAdapter;

    private Spinner spUseState;// 任务状态
    private List<JsonDataDictionaryObject.ObjBean> datadicList;
    private DataDictionaryAdapter dataDictionaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gre_hou_add_edit_lay);
        mContext = this;
        initView();
        Bundle intentExt = getIntent().getExtras();
        grehouaddedit = (String) intentExt.get("grehouaddedit");
        if (grehouaddedit.equals("grehouadd")) {
            tvAddEditTitle.setText("新增大棚");
        } else {
            tvAddEditTitle.setText("编辑大棚");
            greenhouseId = intentExt.getString("greenhouseId");
            new Thread(new GreenHouseExaRequest(greenhouseId)).start();// 大棚查看请求线程
        }
        new Thread(new BaseManagerRequest()).start();// 基地列表请求线程
        StaticListRequest();// 状态数据字典
    }

    private void initView() {
        // TODO Auto-generated method stub
        // 初始化控件
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvAddEditTitle = (TextView) findViewById(R.id.tv_add_edit_gre_hou);
        chapswSubmite = (Button) findViewById(R.id.changepsw_submite);// 提交
        chapswSubmite.setOnClickListener(this);
        btChangreset = (Button) findViewById(R.id.changepsw_reset);//重置
        btChangreset.setOnClickListener(this);
        edGreHouName = (EditText) findViewById(R.id.gre_hou_name);// 大棚名称
        edGreHouCode = (EditText) findViewById(R.id.gre_hou_code);// 大棚编号
        tvActivaDate = (TextView) findViewById(R.id.tv_activa_date);// 启动日期
        tvActivaDate.setOnClickListener(this);
        initDateTime = DateTimePickDialogUtil.formatDate();
        spBaseBel = (Spinner) findViewById(R.id.sp_base_bel);// 所属基地
        spBaseBel.setOnItemSelectedListener(this);
        etTotalArea = (EditText) findViewById(R.id.et_total_area);// 总面积
        etPlanArea = (EditText) findViewById(R.id.et_plan_area);// 种植面积
        etLength = (EditText) findViewById(R.id.et_length);// 长度
        etWidth = (EditText) findViewById(R.id.et_width);// 宽度
        etAbout = (EditText) findViewById(R.id.et_about);// 简介
        spUseState = (Spinner) findViewById(R.id.sp_use_state);// 使用状态
        spUseState.setOnItemSelectedListener(this);
        tvCancelledDate = (TextView) findViewById(R.id.tv_cancelled_date);// 停用/注销时间
        tvCancelledDate.setOnClickListener(this);
        tvPicture = (TextView) findViewById(R.id.tv_picture);// 图片
        btChoose = (Button) findViewById(R.id.bt_choose);// 选择图片路径按钮
        btChoose.setOnClickListener(this);
        tvBusinessAdd = (TextView) findViewById(R.id.tv_business_add);// 详细地址
        tvBusinessAdd.setOnClickListener(this);
        etBusinessDetailed = (EditText) findViewById(R.id.et_business_detailed);// 详细地址
        tvMapLocation = (TextView) findViewById(R.id.tv_map_location);// 地图定位
        ImgLacation = (ImageView) findViewById(R.id.btn_img_lacation);// 地图图标
        ImgLacation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.tv_activa_date:// 启动日期
                dateTimePicKDialog = new DateTimePickDialogUtil(this, initDateTime);
                dateTimePicKDialog.dateTimePicKDialog(tvActivaDate);
                break;
            case R.id.tv_cancelled_date:// 停用/注销时间
                dateTimePicKDialog = new DateTimePickDialogUtil(this, initDateTime);
                dateTimePicKDialog.dateTimePicKDialog(tvCancelledDate);
                break;
            case R.id.bt_choose:// 选择
                //上传图片
                Intent intent = new Intent(GreenHouseAddEditActivity.this, CameraActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(CameraActivity.ISUPDATE, true);
                intent.putExtras(bundle);
                startActivityForResult(intent, CameraActivity.ACTIVITY_RESULT);
                break;
            case R.id.btn_img_lacation:// 定位图标
                tvMapLocation.setText(String.format("纬度%s,经度%s", "118.0000001", "64.00001"));
                break;
            case R.id.tv_business_add:// 地址
                startActivityForResult(new Intent(mContext,
                        AddressLinkageActivity.class), REQUEST_BUSINESS);
                break;
            case R.id.changepsw_submite:// 提交
                if (validtor()) {
                    new Thread(new GhouseAddEditRequest()).start();// 新增大棚请求线程
                }
                break;
            case R.id.changepsw_reset://重置
                getResetText();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == RESULT_OK) {
            // 所属区域
            if (requestCode == REQUEST_BUSINESS) {
                if (data != null) {
                    mCurrentProviceName = data
                            .getStringExtra("mCurrentProviceName");// 省
                    mCurrentCityName = data.getStringExtra("mCurrentCityName");// 市
                    mCurrentDistrictName = data
                            .getStringExtra("mCurrentDistrictName");// 县
                    String mCurrentXipCode = data
                            .getStringExtra("mCurrentXipCode");// 邮编
                    tvBusinessAdd.setText(mCurrentCountry + mCurrentProviceName
                            + mCurrentCityName + mCurrentDistrictName);
                }

            } else if (requestCode == CameraActivity.ACTIVITY_RESULT) {
                picture = data.getData().toString();
                tvPicture.setText(data.getData().toString());
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 非空验证
     *
     * @return boolean
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-10 下午7:09:27
     */
    private boolean validtor() {
        // TODO Auto-generated method stub
        getgHouseText();

        if (TextUtils.isEmpty(greHouName)) {
            TAUtils.toastMessage((Activity) mContext, "请输入大棚名称!");
            return false;
        }
        if (TextUtils.isEmpty(greHouCode)) {
            TAUtils.toastMessage((Activity) mContext, "请输入大棚编号");
            return false;
        }
        if (TextUtils.isEmpty(activaDate)) {
            TAUtils.toastMessage((Activity) mContext, "请选择启动日期");
            return false;
        }
        if (TextUtils.isEmpty(baseName)) {
            TAUtils.toastMessage((Activity) mContext, "请选择所属基地!");
            return false;
        }
        if (TextUtils.isEmpty(totalArea)) {
            TAUtils.toastMessage((Activity) mContext, "请输入总面积!");
            return false;
        }
        if (TextUtils.isEmpty(planArea)) {
            TAUtils.toastMessage((Activity) mContext, "请输入种植面积!");
            return false;
        }
        if (TextUtils.isEmpty(length)) {
            TAUtils.toastMessage((Activity) mContext, "请输入长度!");
            return false;
        }
        if (TextUtils.isEmpty(width)) {
            TAUtils.toastMessage((Activity) mContext, "请输入宽度!");
            return false;
        }
        if (TextUtils.isEmpty(about)) {
            TAUtils.toastMessage((Activity) mContext, "请输入简介!");
            return false;
        }
        if (TextUtils.isEmpty(useState)) {
            TAUtils.toastMessage((Activity) mContext, "请选择使用状态!");
            return false;
        }
        if (TextUtils.isEmpty(cancelledDate)) {
            TAUtils.toastMessage((Activity) mContext, "请选择停用/注销时间!");
            return false;
        }
        if (TextUtils.isEmpty(picture)) {
            TAUtils.toastMessage((Activity) mContext, "请选择大棚图片!");
            return false;
        }
        if (TextUtils.isEmpty(businessAdd)) {
            TAUtils.toastMessage((Activity) mContext, "请选择地址!");
            return false;
        }
        if (TextUtils.isEmpty(businessDetailed)) {
            TAUtils.toastMessage((Activity) mContext, "请输入详细信息!");
            return false;
        }
        if (TextUtils.isEmpty(location)) {
            TAUtils.toastMessage((Activity) mContext, "定位失败!");
            return false;
        }

        return true;
    }

    /**
     * 获取大棚信息
     *
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-10 下午7:10:10
     */
    private void getgHouseText() {
        greHouName = edGreHouName.getText().toString().trim();
        greHouCode = edGreHouCode.getText().toString().trim();
        activaDate = tvActivaDate.getText().toString().trim();
        // baseName = spBaseBel.getText().toString().trim();
        totalArea = etTotalArea.getText().toString().trim();
        planArea = etPlanArea.getText().toString().trim();
        length = etLength.getText().toString().trim();
        width = etWidth.getText().toString().trim();
        about = etAbout.getText().toString().trim();
        // useState = spUseState.getText().toString().trim();
        cancelledDate = tvCancelledDate.getText().toString().trim();
        picture = tvPicture.getText().toString().trim();
        businessAdd = tvBusinessAdd.getText().toString().trim();
        businessDetailed = etBusinessDetailed.getText().toString().trim();
        location = tvMapLocation.getText().toString().trim();
    }

    private void getResetText() {
        edGreHouName.setText("");
        edGreHouCode.setText("");
        tvActivaDate.setText("");
        // spBaseBel.setText("");
        etTotalArea.setText("");
        etPlanArea.setText("");
        etLength.setText("");
        etWidth.setText("");
        etAbout.setText("");
        // tvUseState.setText("");
        tvCancelledDate.setText("");
        tvPicture.setText("");
        tvBusinessAdd.setText("");
        etBusinessDetailed.setText("");
        tvMapLocation.setText("");
    }

    /**
     * 大棚新增、编辑json解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GhouseAddEditAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-10 下午8:18:23
     */
    class GhouseAddEditAction extends Thread {

        private String gHouseAEJson;

        public GhouseAddEditAction(String gHouseAE_json) {
            // TODO Auto-generated constructor stub
            this.gHouseAEJson = gHouseAE_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(gHouseAEJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonGreenhouseAEObject gHouse_AE = gson.fromJson(gHouseAEJson,
                        JsonGreenhouseAEObject.class);

                if (!gHouse_AE.equals("") && gHouse_AE != null) {
                    TAUtils.toastMessage((Activity) mContext,
                            gHouse_AE.getMsg());
                    if (gHouse_AE.isSuccess()) {// 成功
                        gHouseObj = gHouse_AE.getObj();
                        Message msg = Message.obtain();
                        msg.what = GHOUSEAE_VALUES;
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
                case GHOUSEAE_SUCCESS:
                    String gHouseAE_json = msg.obj.toString();
                    new Thread(new GhouseAddEditAction(gHouseAE_json)).start();// 大棚新增、编辑报文解析线程
                    break;
                case GHOUSEAE_VALUES:
                    greHouName = TextUtils.isEmpty(gHouseObj.getGhouseFullname()) ? ""
                            : gHouseObj.getGhouseFullname();// 大棚名称
                    greHouCode = TextUtils.isEmpty(gHouseObj.getGhouseCode()) ? ""
                            : gHouseObj.getGhouseCode();// 大棚编号
                    activaDate = TextUtils.isEmpty(String.valueOf(gHouseObj
                            .getOpenDateOpen())) ? "" : String.valueOf(gHouseObj
                            .getOpenDateOpen());// 启动日期
                    baseName = TextUtils.isEmpty(String.valueOf(gHouseObj
                            .getBaseFullname())) ? "" : String.valueOf(gHouseObj
                            .getBaseFullname());// 所属基地
                    baseId = TextUtils
                            .isEmpty(String.valueOf(gHouseObj.getBaseId())) ? ""
                            : String.valueOf(gHouseObj.getBaseId());// 所属基地id
                    totalArea = TextUtils.isEmpty(gHouseObj.getCoveredArea()) ? ""
                            : gHouseObj.getCoveredArea();// 总面积
                    planArea = TextUtils.isEmpty(gHouseObj.getUsedArea()) ? ""
                            : gHouseObj.getUsedArea();// 种植面积
                    length = TextUtils.isEmpty(gHouseObj.getAreaLength()) ? ""
                            : gHouseObj.getAreaLength();// 长度
                    width = TextUtils.isEmpty(gHouseObj.getAreaWidth()) ? ""
                            : gHouseObj.getAreaWidth();// 宽度
                    about = TextUtils.isEmpty(gHouseObj.getContent()) ? ""
                            : gHouseObj.getContent();// 简介
                    useState = TextUtils.isEmpty(gHouseObj.getStatus()) ? ""
                            : gHouseObj.getStatus();// 使用状态
                    cancelledDate = TextUtils.isEmpty(String.valueOf(gHouseObj
                            .getOpenDateStop())) ? "" : String.valueOf(gHouseObj
                            .getOpenDateStop());// 停用/注销时间
                    picture = TextUtils.isEmpty(String.valueOf(gHouseObj
                            .getAttachment1())) ? "" : String.valueOf(gHouseObj
                            .getAttachment1());// 图片路径
                    mCurrentCountry = TextUtils.isEmpty(gHouseObj
                            .getRegionIdCountry()) ? "" : gHouseObj
                            .getRegionIdCountry();// 国家
                    mCurrentProviceName = TextUtils.isEmpty(gHouseObj
                            .getRegionIdProvince()) ? "" : gHouseObj
                            .getRegionIdProvince();// 省
                    mCurrentCityName = TextUtils.isEmpty(gHouseObj
                            .getRegionIdCity()) ? "" : gHouseObj.getRegionIdCity();// 市
                    mCurrentDistrictName = TextUtils.isEmpty(gHouseObj
                            .getRegionIdDistrict()) ? "" : gHouseObj
                            .getRegionIdDistrict();// 区
                    businessAdd = mCurrentCountry + mCurrentProviceName
                            + mCurrentCityName + mCurrentDistrictName;// 地址
                    businessDetailed = TextUtils.isEmpty(gHouseObj.getRegionAddr()) ? ""
                            : gHouseObj.getRegionAddr();// 详细地址
                    String lat = gHouseObj.getLatitude();// 纬度
                    String lot = gHouseObj.getLongitude();// 经度
                    edGreHouName.setText(greHouName);
                    edGreHouCode.setText(greHouCode);
                    tvActivaDate.setText(DateLocalUtil.getDate(String
                            .valueOf(activaDate)));
                    for (int i = 0; i < baseObjList.size(); i++) {
                        String strm = baseObjList.get(i).getId();
                        if (baseId.equals(strm)) {
                            spBaseBel.setSelection(i);
                        }
                    }
                    etTotalArea.setText(totalArea);
                    etPlanArea.setText(planArea);
                    etLength.setText(length);
                    etWidth.setText(width);
                    etAbout.setText(about);
                    for (int i = 0; i < baseObjList.size(); i++) {
                        String strm = baseObjList.get(i).getId();
                        if (useState.equals(strm)) {
                            spUseState.setSelection(i);
                        }
                    }
                    tvCancelledDate.setText(DateLocalUtil.getDate(String
                            .valueOf(cancelledDate)));
                    tvPicture.setText(picture);
                    tvBusinessAdd.setText(businessAdd);
                    etBusinessDetailed.setText(businessDetailed);
                    tvMapLocation.setText(String.format("纬度%s,经度%s", lat, lot));
                    finish();
                    break;
                case GHOUSEVIEW_SUCCESS:
                    String gHouseExa_json = msg.obj.toString();
                    new Thread(new GreenHouseExaAction(gHouseExa_json)).start();// 大棚查看报文解析线程
                    break;
                case GHOUSEVIEW_VALUES:
                    GreenhouseBean houseBean = gHouseViewBean.getGreenhouse();

                    edGreHouName.setText(houseBean.getGhouseFullname());
                    edGreHouCode.setText(houseBean.getGhouseCode());
                    tvActivaDate.setText(DateLocalUtil.getDate(String
                            .valueOf(houseBean.getOpenDateOpen())));
                    baseId = String.valueOf(houseBean.getBaseId());
                    for (int i = 0; i < baseObjList.size(); i++) {
                        String strm = baseObjList.get(i).getId();
                        if (baseId.equals(strm)) {
                            spBaseBel.setSelection(i);
                        }
                    }
                    etTotalArea.setText(houseBean.getCoveredArea());
                    etPlanArea.setText(houseBean.getUsedArea());
                    etLength.setText(houseBean.getAreaLength());
                    etWidth.setText(houseBean.getAreaWidth());
                    etAbout.setText(houseBean.getContent());
                    useState = houseBean.getStatus();
                    for (int i = 0; i < baseObjList.size(); i++) {
                        String strm = baseObjList.get(i).getId();
                        if (useState.equals(strm)) {
                            spUseState.setSelection(i);
                        }
                    }
                    tvCancelledDate.setText(DateLocalUtil.getDate(String.valueOf(houseBean.getOpenDateStop())));
                    tvPicture.setText(String.valueOf(houseBean.getAttachment1()));
                    mCurrentCountry = houseBean.getRegionIdCountry();
                    mCurrentProviceName = houseBean.getRegionIdProvince();
                    mCurrentCityName = houseBean.getRegionIdCity();
                    mCurrentDistrictName = houseBean.getRegionIdDistrict();
                    tvBusinessAdd.setText(mCurrentCountry + mCurrentProviceName
                            + mCurrentCityName + mCurrentDistrictName);
                    etBusinessDetailed.setText(houseBean.getRegionAddr().toString()
                            .trim());
                    tvMapLocation.setText(String.format("纬度%s,经度%s",
                            houseBean.getLatitude(), houseBean.getLongitude()));
                    break;
                case BASELIST_SUCCESS:
                    String baseManager_json = msg.obj.toString();
                    new Thread(new BaseManagerAction(baseManager_json)).start();// 基地列表报文解析线程
                    break;
                case BASELIST_VALUES:
                    baseBleAdapter = new BaseBelAdapter(mContext, baseObjList);
                    // 绑定 Adapter到控件
                    spBaseBel.setAdapter(baseBleAdapter);
                    spBaseBel.setSelection(0);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 大棚新增、编辑请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GhouseAddEditRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-10 下午8:12:19
     */
    class GhouseAddEditRequest extends Thread {

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
                map.put("tenantId", currTenantId);
                map.put("baseFullname", baseName);
                map.put("baseCode", baseCode);
                map.put("baseId", baseId);
                map.put("openDateOpen", activaDate);
                map.put("coveredArea", totalArea);
                map.put("coveredAreaMu", "");
                map.put("content", about);
                map.put("regionIdCountry", mCurrentCountry);
                map.put("regionIdProvince", mCurrentProviceName);
                map.put("regionIdCity", mCurrentCityName);
                map.put("regionIdDistrict", mCurrentDistrictName);
                map.put("regionAddr", businessDetailed);
                map.put("ghouseFullname", greHouName);
                map.put("ghouseCode", greHouCode);
                map.put("usedArea", planArea);
                map.put("latitude", "116.395954");
                map.put("longitude", "39.52676");
                map.put("areaLength", length);
                map.put("status", useState);
                map.put("openDateStop", cancelledDate);
                map.put("areaWidth", width);
                map.put("attachment1", picture);

                if (grehouaddedit.equals("grehouedit")) {
                    map.put("greenhouseId", greenhouseId);
                }

                WapiUtilEx.ghouseAddEdit(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEAE_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEAE_SUCCESS;
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
                WapiUtilEx.ghouseexaview(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEAE_ERROR;
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
                    TAUtils.toastMessage((Activity) mContext,
                            gHouse_View.getMsg());
                    if (gHouse_View.isSuccess()) {// 成功

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

    /**
     * 基地列表请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BaseManagerRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-5 下午6:55:14
     */
    public class BaseManagerRequest extends Thread {

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
                WapiUtilEx.basemanagerlist(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEAE_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = BASELIST_SUCCESS;
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
     * 基地列表json报文解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BaseManagerAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-5 下午7:01:12
     */
    class BaseManagerAction extends Thread {

        private String baseMagJson;

        public BaseManagerAction(String baseManager_json) {
            // TODO Auto-generated constructor stub
            this.baseMagJson = baseManager_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();

                if (TextUtils.isEmpty(baseMagJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonBaseManagerObject base_manager = gson.fromJson(baseMagJson,
                        JsonBaseManagerObject.class);

                if (!base_manager.equals("") && base_manager != null) {
                    if (base_manager.isSuccess()) {// 成功

                        baseObjList = base_manager.getObj();

                        Message msg = Message.obtain();
                        msg.what = BASELIST_VALUES;
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

    // -------------------状态start-------------------

    /**
     * 状态数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: TaskNameListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-29 下午3:30:58
     */
    public void StaticListRequest() {

        String key = "SP_COMMON_STATE";
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(GreenHouseAddEditActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onSuccess(JsonDataDictionaryObject date) {
                if (TextUtils.isEmpty(date.toString())) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }
                if (!date.equals("")
                        && date != null) {
                    if (date.isSuccess()) {// 成功
                        datadicList = date.getObj();
                        // 设置适配器
                        dataDictionaryAdapter = new DataDictionaryAdapter(mContext,
                                datadicList);
                        // 绑定 Adapter到控件
                        spUseState.setAdapter(dataDictionaryAdapter);
                        spUseState.setSelection(0);
                    }

                }
            }
        });
    }


    // -------------------状态end-------------------

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // TODO Auto-generated method stub
        switch (parent.getId()) {
            case R.id.sp_base_bel:// 所属基地
                baseId = baseObjList.get(position).getId();// 基地id
                baseName = baseObjList.get(position).getBaseFullname();// 基地名称
                baseCode = baseObjList.get(position).getBaseCode();// 基地编号
                break;
            case R.id.sp_use_state:// 使用状态
                useState = datadicList.get(position).getTypecode();// 使用状态
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }


}
