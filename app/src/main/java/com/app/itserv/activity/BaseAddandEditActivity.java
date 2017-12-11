package com.app.itserv.activity;

import java.util.HashMap;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.commons.ToUIEvent;
import com.app.itserv.jparser.JsonBaseAddObject;
import com.app.itserv.jparser.JsonBaseAddObject.AttributesBean;
import com.app.itserv.jparser.JsonBaseAddObject.ObjBean;
import com.app.itserv.jparser.JsonBaseExaObject;
import com.app.itserv.jparser.JsonBaseExaObject.ObjBean.GreenbaseBean;
import com.itserv.app.util.DateTimePickDialogUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.RegexChk;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import de.greenrobot.event.EventBus;

/**
 * 新增、编辑基地
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: BaseAddandEditActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-6 下午3:19:55
 */
public class BaseAddandEditActivity extends Activity implements OnClickListener {

    private Context mContext;

    private static final String TAG = "BaseAddandEditActivity";
    protected static final int BASEADD_ERROR = 1;
    protected static final int BASEADD_SUCCESS = 2;
    protected static final int BASEADD_VALUES = 3;
    protected static final int BASEEXA_SUCCESS = 4;
    protected static final int BASEEXA_VALUES = 5;
    protected static final int REQUEST_BUSINESS = 6;

    private ImageView ImgBack;
    private TextView tvAddTextTitle, tvBusinessAdd, tvActivaDate, tvPicture;
    private EditText edBasename, edBasecode, etTotalArea, etPlanArea, etAbout,
            tvBusinessDetailed;
    private Button btChoose, changepswSubmite, changepswReset;

    private String mCurrentCountry = "中国";
    private String mCurrentProviceName;
    private String mCurrentCityName;
    private String mCurrentDistrictName;

    private ObjBean baseAddObj;
    private GreenbaseBean baseExa;
    private String baseName, baseCode, ativaDate, totalArea, planArea, about,
            businessAdd, businessDetailed, baseaddandedit = null;
    private String baseId;

    /* 启用日期start */
    private String initDateTime;

	/* 启用日期end */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_add_edit_lay);
        mContext = this;
        initView();
        baseaddandedit = (String) this.getIntent().getExtras()
                .get("baseaddandedit");
        if (baseaddandedit.equals("baseadd")) {
            tvAddTextTitle.setText("新增基地");
        } else {
            baseId = (String) getIntent().getExtras().get("baseId");
            tvAddTextTitle.setText("编辑基地");
            new Thread(new ExaBaseRequest(baseId)).start();// 查看基地
        }
    }

    private void initView() {
        // TODO Auto-generated method stub
        // 初始化控件
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvAddTextTitle = (TextView) findViewById(R.id.tv_add_edit_base);
        edBasename = (EditText) findViewById(R.id.basename);// 基地名称
        edBasecode = (EditText) findViewById(R.id.basecode);// 基地编号
        tvActivaDate = (TextView) findViewById(R.id.tv_activa_date);// 启动日期
        tvActivaDate.setOnClickListener(this);
        initDateTime = DateTimePickDialogUtil.formatDate();
        etTotalArea = (EditText) findViewById(R.id.et_total_area);// 总面积
        etPlanArea = (EditText) findViewById(R.id.et_plan_area);// 种植面积
        etAbout = (EditText) findViewById(R.id.et_about);// 简介
        tvPicture = (TextView) findViewById(R.id.tv_picture);// 图片
        btChoose = (Button) findViewById(R.id.bt_choose);// 选择图片按钮
        btChoose.setOnClickListener(this);
        tvBusinessAdd = (TextView) findViewById(R.id.business_add);// 详细地址
        tvBusinessAdd.setOnClickListener(this);
        tvBusinessDetailed = (EditText) findViewById(R.id.business_detailed);// 详细地址
        changepswSubmite = (Button) findViewById(R.id.changepsw_submite);// 提交
        changepswSubmite.setOnClickListener(this);
        changepswReset = (Button) findViewById(R.id.changepsw_reset);// 重置
        changepswReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.tv_activa_date:// 启动日期
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        this, initDateTime);
                dateTimePicKDialog.dateTimePicKDialog(tvActivaDate);
                break;
            case R.id.bt_choose:// 图片选择
                tvPicture
                        .setText("http://img01.taopic.com/160514/240378-16051410104318.jpg");
                break;
            case R.id.business_add:// 详细地址
                startActivityForResult(new Intent(mContext,
                        AddressLinkageActivity.class), REQUEST_BUSINESS);
                break;
            case R.id.changepsw_submite:// 提交
                if (ToastUtils.isFastClick())
                    return;
                if (validator()) {
                    new Thread(new BaseAddRequest()).start();// 新增、编辑基地请求线程
                }
                break;
            case R.id.changepsw_reset:// 重置
                resetView();
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

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void resetView() {
        // TODO Auto-generated method stub
        getMyView();
        edBasename.setText("");
        edBasecode.setText("");
        tvActivaDate.setText("");
        etTotalArea.setText("");
        etPlanArea.setText("");
        etAbout.setText("");
        tvBusinessAdd.setText("");
        tvBusinessDetailed.setText("");
    }

    /**
     * 非空验证
     *
     * @return boolean
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-6 下午4:04:20
     */
    private boolean validator() {
        getMyView();
        if (TextUtils.isEmpty(baseName)) {
            TAUtils.toastMessage((Activity) mContext, "请输入基地名称!");
            return false;
        }
        if (RegexChk.checkUserName(baseName)) {
            TAUtils.toastMessage((Activity) mContext, "用户名称不能有特殊字符");
            return false;
        }
        if (TextUtils.isEmpty(baseCode)) {
            TAUtils.toastMessage((Activity) mContext, "请输入基地编号!");
            return false;
        }
        if (TextUtils.isEmpty(ativaDate)) {
            TAUtils.toastMessage((Activity) mContext, "请选择启动日期!");
            return false;
        }
        if (DateTimePickDialogUtil.compare_date(initDateTime, tvActivaDate
                .getText().toString().trim())) {
            TAUtils.toastMessage((Activity) mContext, "【启用日期】不能大于当前日期!");
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
        if (TextUtils.isEmpty(about)) {
            TAUtils.toastMessage((Activity) mContext, "请输入简介!");
            return false;
        }
        if (RegexChk.checkUserName(about)) {
            TAUtils.toastMessage((Activity) mContext, "简介不能有特殊字符!");
            return false;
        }
        if (TextUtils.isEmpty(businessAdd)) {
            TAUtils.toastMessage((Activity) mContext, "请选择地址!");
            return false;
        }
        if (TextUtils.isEmpty(businessDetailed)) {
            TAUtils.toastMessage((Activity) mContext, "请输入详细地址!");
            return false;
        }
        if (RegexChk.checkUserName(businessDetailed)) {
            TAUtils.toastMessage((Activity) mContext, "详细地址不能有特殊字符!");
            return false;
        }
        return true;
    }

    private void getMyView() {
        baseName = edBasename.getText().toString().trim();
        baseCode = edBasecode.getText().toString().trim();
        ativaDate = tvActivaDate.getText().toString().trim();
        totalArea = etTotalArea.getText().toString().trim();
        planArea = etPlanArea.getText().toString().trim();
        about = etAbout.getText().toString().trim();
        businessAdd = tvBusinessAdd.getText().toString().trim();
        businessDetailed = tvBusinessDetailed.getText().toString().trim();
    }

    /**
     * 查看基地json报文请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BaseExaAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-7 下午2:50:47
     */
    class BaseExaAction extends Thread {

        private String baseExaJson;

        public BaseExaAction(String baseExa_json) {
            // TODO Auto-generated constructor stub
            this.baseExaJson = baseExa_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(baseExaJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonBaseExaObject base_exa = gson.fromJson(baseExaJson,
                        JsonBaseExaObject.class);

                if (!base_exa.equals("")) {

                    if (base_exa.isSuccess()) {// 成功
                        JsonBaseExaObject.ObjBean baseExaObj = base_exa
                                .getObj();
                        baseExa = baseExaObj.getGreenbase();

                        Message msg = Message.obtain();
                        msg.what = BASEEXA_VALUES;
                        mHandler.sendMessage(msg);
                    }else
                        TAUtils.toastMessage((Activity) mContext, base_exa.getMsg());
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
     * 新增、编辑基地json报文请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BaseAddAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-6 下午4:36:15
     */
    class BaseAddAction extends Thread {

        private String baseAddJson;

        public BaseAddAction(String baseAdd_json) {
            // TODO Auto-generated constructor stub
            this.baseAddJson = baseAdd_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(baseAddJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonBaseAddObject base_add = gson.fromJson(baseAddJson,
                        JsonBaseAddObject.class);

                if (!base_add.equals("")) {
                    if (base_add.isSuccess()) {// 成功
                        baseAddObj = base_add.getObj();
                        Message msg = Message.obtain();
                        msg.what = BASEADD_VALUES;
                        mHandler.sendMessage(msg);
                    } else
                        ToastUtils.makeTextShort(base_add.getMsg());
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
                case BASEADD_ERROR:// 基地添加请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case BASEADD_SUCCESS:// 添加
                    String baseAdd_json = msg.obj.toString();
                    new Thread(new BaseAddAction(baseAdd_json)).start();// 新增基地报文解析线程
                    break;
                case BASEEXA_SUCCESS:// 查看
                    String baseExa_json = msg.obj.toString();
                    new Thread(new BaseExaAction(baseExa_json)).start();// 查看基地报文解析线程
                    break;
                case BASEADD_VALUES:// 添加
                    baseName = TextUtils.isEmpty(baseAddObj.getBaseFullname()) ? ""
                            : baseAddObj.getBaseFullname();
                    baseCode = TextUtils.isEmpty(baseAddObj.getBaseCode()) ? ""
                            : baseAddObj.getBaseCode();
                    ativaDate = TextUtils.isEmpty(String.valueOf(baseAddObj
                            .getOpenDateOpen())) ? "" : String.valueOf(baseAddObj
                            .getOpenDateOpen());
                    totalArea = TextUtils.isEmpty(String.valueOf(baseAddObj
                            .getCoveredArea())) ? "" : String.valueOf(baseAddObj
                            .getCoveredArea());
                    planArea = TextUtils.isEmpty(String.valueOf(baseAddObj
                            .getCoveredAreaMu())) ? "" : String.valueOf(baseAddObj
                            .getCoveredAreaMu());
                    about = TextUtils.isEmpty(baseAddObj.getContent()) ? ""
                            : String.valueOf(baseAddObj.getContent());
                    mCurrentCountry = TextUtils.isEmpty(baseAddObj
                            .getRegionIdCountry()) ? "" : baseAddObj
                            .getRegionIdCountry();
                    mCurrentProviceName = TextUtils.isEmpty(baseAddObj
                            .getRegionIdProvince()) ? "" : baseAddObj
                            .getRegionIdProvince();
                    mCurrentCityName = TextUtils.isEmpty(baseAddObj
                            .getRegionIdCity()) ? "" : baseAddObj.getRegionIdCity();
                    mCurrentDistrictName = TextUtils.isEmpty(baseAddObj
                            .getRegionIdDistrict()) ? "" : baseAddObj
                            .getRegionIdDistrict();
                    businessAdd = mCurrentCountry + mCurrentProviceName
                            + mCurrentCityName + mCurrentDistrictName;
                    businessDetailed = baseAddObj.getRegionAddr();

                    edBasename.setText(baseName);
                    edBasecode.setText(baseCode);
                    tvActivaDate.setText(DateLocalUtil.getDate(ativaDate));
                    etTotalArea.setText(totalArea);
                    etPlanArea.setText(planArea);
                    etAbout.setText(about);
                    tvBusinessAdd.setText(businessAdd);
                    tvBusinessDetailed.setText(businessDetailed);
                    ToUIEvent toUIEvent = new ToUIEvent(
                            ToUIEvent.UPDATE_BASEMANAGER, null);
                    EventBus.getDefault().post(toUIEvent);
                    BaseAddandEditActivity.this.finish();
                    break;
                case BASEEXA_VALUES:// 查看
                    baseName = TextUtils.isEmpty(baseExa.getBaseFullname()) ? ""
                            : baseExa.getBaseFullname();
                    baseCode = TextUtils.isEmpty(baseExa.getBaseCode()) ? ""
                            : baseExa.getBaseCode();
                    ativaDate = TextUtils.isEmpty(String.valueOf(baseExa
                            .getOpenDateOpen())) ? "" : String.valueOf(baseExa
                            .getOpenDateOpen());
                    totalArea = TextUtils.isEmpty(String.valueOf(baseExa
                            .getCoveredArea())) ? "" : String.valueOf(baseExa
                            .getCoveredArea());
                    planArea = TextUtils.isEmpty(String.valueOf(baseExa
                            .getCoveredAreaMu())) ? "" : String.valueOf(baseExa
                            .getCoveredAreaMu());
                    about = TextUtils.isEmpty(baseExa.getContent()) ? "" : String
                            .valueOf(baseExa.getContent());
                    mCurrentCountry = TextUtils.isEmpty(baseExa
                            .getRegionIdCountry()) ? "" : baseExa
                            .getRegionIdCountry();
                    mCurrentProviceName = TextUtils.isEmpty(baseExa
                            .getRegionIdProvince()) ? "" : baseExa
                            .getRegionIdProvince();
                    mCurrentCityName = TextUtils.isEmpty(baseExa.getRegionIdCity()) ? ""
                            : baseExa.getRegionIdCity();
                    mCurrentDistrictName = TextUtils.isEmpty(baseExa
                            .getRegionIdDistrict()) ? "" : baseExa
                            .getRegionIdDistrict();
                    businessAdd = mCurrentCountry + mCurrentProviceName
                            + mCurrentCityName + mCurrentDistrictName;
                    businessDetailed = baseExa.getRegionAddr();

                    edBasename.setText(baseName);
                    edBasecode.setText(baseCode);
                    tvActivaDate.setText(DateLocalUtil.getDate(ativaDate));
                    etTotalArea.setText(totalArea);
                    etPlanArea.setText(planArea);
                    etAbout.setText(about);
                    tvBusinessAdd.setText(businessAdd);
                    tvBusinessDetailed.setText(businessDetailed);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 查看基地请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: ExaBaseRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-7 下午2:30:33
     */
    class ExaBaseRequest extends Thread {

        private String baseId;

        public ExaBaseRequest(String baseId) {
            // TODO Auto-generated constructor stub
            this.baseId = baseId;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

//				String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("greenbaseId", baseId);

                Log.i(TAG, TAG + "基地查看请求");
                WapiUtilEx.baseexa(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = BASEADD_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = BASEEXA_SUCCESS;
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
     * 基地新增、编辑
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BaseAddRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-6 下午4:06:51
     */
    class BaseAddRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

//				String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("tenantId", currTenantId);
                map.put("baseFullname", baseName);
                map.put("baseCode", baseCode);
                map.put("openDateOpen", ativaDate);
                map.put("coveredArea", totalArea);
                map.put("coveredAreaMu", planArea);
                map.put("content", about);
                map.put("regionIdCountry", mCurrentCountry);
                map.put("regionIdProvince", mCurrentProviceName);
                map.put("regionIdCity", mCurrentCityName);
                map.put("regionIdDistrict", mCurrentDistrictName);
                map.put("regionAddr", businessDetailed);
                map.put("attachment1", null);

                if (baseaddandedit.equals("baseedit")) {
                    map.put("greenbaseId", baseId);
                    Log.v(TAG, TAG + "编辑基地请求");
                } else {
                    Log.v(TAG, TAG + "新增基地请求");
                }
                WapiUtilEx.baseaddupdate(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = BASEADD_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = BASEADD_SUCCESS;
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
