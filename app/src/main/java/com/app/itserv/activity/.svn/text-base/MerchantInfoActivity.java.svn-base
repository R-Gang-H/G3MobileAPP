package com.app.itserv.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.app.itserv.adapters.DataDictionaryAdapter;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonMerchantInfoObject;
import com.app.itserv.jparser.JsonMerchantInfoObject.ObjBean;
import com.app.itserv.jparser.JsonMerchantInfoObject.ObjBean.TenantBean;
import com.app.itserv.jparser.JsonMerchantInfoObject.ObjBean.TenantDetailBean;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.DateTimePickDialogUtil;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project name：yyshed
 * @type name：MerchantInfoActivity
 * @description：商户信息
 * @author：gang
 * @date time：2017-6-14 下午7:56:10
 */
public class MerchantInfoActivity extends Activity implements OnClickListener,
        OnItemSelectedListener {

    private Context mContext;

    private ImageView ImgBack;
    private LinearLayout lLenterprise;
    private EditText edmerchantName, edenglishName, edabbreName,
            etBusinessLicense, etStartPeriod, etStopPeriod, etOrganization,
            etTaxRegistration, etRegisterDetailed;
    private Button btnSubmite;// 商户信息提交

    protected static final int MERCHANT_INFO_ERROR = 4;
    protected static final int MERCHANT_INFO_SUCCESS = 5;
    public static final int MERCHANT_INFO_VALUE = 6;

    // 商户分类
    private Spinner mTenantType;// 商户分类
    private List<JsonDataDictionaryObject.ObjBean> tenantTypeList;
    private DataDictionaryAdapter tenantTypeAdapter;

    // 市场定位
    private Spinner mMarketPosi;// 市场定位
    private List<JsonDataDictionaryObject.ObjBean> marposiList;
    private DataDictionaryAdapter marketPosiAdapter;

    // 销售区域
    private Spinner mSalesArea;// 销售区域
    private List<JsonDataDictionaryObject.ObjBean> salesAreaList;
    private DataDictionaryAdapter salesAreaAdapter;

    private Spinner mOperaStatus;// 经营状态
    private List<JsonDataDictionaryObject.ObjBean> operaStatusList;
    private DataDictionaryAdapter operaStatusAdapter;

    private Spinner mLicenseMark;// 执照合格标志
    private List<JsonDataDictionaryObject.ObjBean> licenseMarkList;
    private DataDictionaryAdapter licenseMarkAdapter;

    private SharedPreferences sp = null;
    private Editor editor;

    /* 省市级三级联动start */
    private EditText etArea, etSetupTime, etRegisterAdd, etRegisterPostcode,
            etBusinessAdd, etBusinessPostcode;
    private static final int REQUEST_CODE = 1;
    private static final int REQUEST_REGISTER = 2;
    private static final int REQUEST_BUSINESS = 3;

    public static final String TAG = null;
    private String initDateTime;
    private Dialog dialog = null;
    private DateTimePickDialogUtil dateTimePicKDialog;
    /* 省市级三级联动end */

    private ObjBean objbeanmerchant;
    private String merchantName;
    private String englishName;
    private String abbreName;
    private String ficatType;// 商户分类
    private String Area;
    private String marketName;// 市场定位名称

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merchant_info_lay);
        mContext = this;
        init();
        initView();
    }

    private void init() {
        // TODO Auto-generated method stub
        TenantTypeListRequest();// 商户分类数据字典
        MarketPosiListRequest();// 市场定位数据字典
        SalesAreaListRequest();// 销售区域数据字典
        OperaStatusListRequest();// 经营状态数据字典
        LicenseMarkListRequest();// 执照合格标志数据字典
        MerchantInfoRequest();// 商户明细信息
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        sp = getSharedPreferences("personal_text", MODE_PRIVATE);
        editor = sp.edit();
        initDateTime = DateTimePickDialogUtil.formatDate();
        dateTimePicKDialog = new DateTimePickDialogUtil(this, initDateTime);
        lLenterprise = (LinearLayout) findViewById(R.id.enterprise_show);
        lLenterprise.setVisibility(View.GONE);
        mTenantType = (Spinner) findViewById(R.id.tenant_type);// 商户分类
        mTenantType.setOnItemSelectedListener(this);
        etArea = (EditText) findViewById(R.id.area);// 所属地区
        etArea.setOnClickListener(this);
        mMarketPosi = (Spinner) findViewById(R.id.market_posi);// 市场定位
        mMarketPosi.setOnItemSelectedListener(this);
        mSalesArea = (Spinner) findViewById(R.id.sales_area);// 销售区域
        mSalesArea.setOnItemSelectedListener(this);
        etSetupTime = (EditText) findViewById(R.id.setup_time);// 成立时间
        etSetupTime.setOnClickListener(this);
        mOperaStatus = (Spinner) findViewById(R.id.opera_status);// 经营状态
        mOperaStatus.setOnItemSelectedListener(this);
        mLicenseMark = (Spinner) findViewById(R.id.license_mark);// 执照合格标志
        mLicenseMark.setOnItemSelectedListener(this);
        etBusinessLicense = (EditText) findViewById(R.id.business_license);// 营业执照号
        etStartPeriod = (EditText) findViewById(R.id.start_period_validity);// 营业有效期开始
        etStartPeriod.setOnClickListener(this);
        etStopPeriod = (EditText) findViewById(R.id.stop_period_validity);// 营业有效期结束
        etStopPeriod.setOnClickListener(this);
        etOrganization = (EditText) findViewById(R.id.organization);// 组织机构证号
        etTaxRegistration = (EditText) findViewById(R.id.tax_registration);// 税务登记证号
        etRegisterAdd = (EditText) findViewById(R.id.register_add);// 注册地址
        etRegisterAdd.setOnClickListener(this);
        etRegisterDetailed = (EditText) findViewById(R.id.register_detailed);// 注册详细地址
        etRegisterPostcode = (EditText) findViewById(R.id.register_postcode);// 注册地址邮政编码
        etBusinessAdd = (EditText) findViewById(R.id.business_add);// 经营地址
        etBusinessAdd.setOnClickListener(this);
        etBusinessPostcode = (EditText) findViewById(R.id.business_postcode);// 经营地址邮政编码
        edmerchantName = (EditText) findViewById(R.id.merchant_name);// 商户名称
        edenglishName = (EditText) findViewById(R.id.english_name);// 商户英文名称
        edabbreName = (EditText) findViewById(R.id.abbre_name);// 商户简称
        btnSubmite = (Button) findViewById(R.id.btn_submite);// 提交按钮
        btnSubmite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.area:// 省市县级三级联动
                startActivityForResult(new Intent(mContext,
                        AddressLinkageActivity.class), REQUEST_CODE);
                break;
            case R.id.setup_time:// 成立时间
                dateTimePicKDialog.dateTimePicKDialog(etSetupTime);
                break;
            case R.id.start_period_validity:// 经营有效开始时间
                dateTimePicKDialog.dateTimePicKDialog(etStartPeriod);
                break;
            case R.id.stop_period_validity:// 经营有效结束时间
                dateTimePicKDialog.dateTimePicKDialog(etStopPeriod);
                break;
            case R.id.register_add:// 注册地址
                startActivityForResult(new Intent(mContext,
                        AddressLinkageActivity.class), REQUEST_REGISTER);
                break;
            case R.id.business_add:// 经营地址
                startActivityForResult(new Intent(mContext,
                        AddressLinkageActivity.class), REQUEST_BUSINESS);
                break;
            case R.id.btn_submite:// 提交按钮
                if (Verifica()) {
                    new Thread(new MerchantSubmitRequest()).start();
                }
                break;
            default:
                break;
        }
    }

    private boolean Verifica() {
        // TODO Auto-generated method stub
        merchantName = edmerchantName.getText().toString();// 商户名称
        englishName = edenglishName.getText().toString();// 英文简称
        abbreName = edabbreName.getText().toString();// 商户简称
        Area = etArea.getText().toString();// 所属地区

        if (TextUtils.isEmpty(merchantName)) {
            TAUtils.toastMessage((Activity) mContext, "请输入商户名称");
            return false;
        }

        if (TextUtils.isEmpty(abbreName)) {
            TAUtils.toastMessage((Activity) mContext, "请输入商户简称");
            return false;
        }

        if (TextUtils.isEmpty(Area)) {
            TAUtils.toastMessage((Activity) mContext, "请选择所属地区");
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == RESULT_OK) {
            // 所属区域
            if (requestCode == REQUEST_CODE) {
                if (data != null) {
                    String mCurrentProviceName = data
                            .getStringExtra("mCurrentProviceName");// 省
                    String mCurrentCityName = data
                            .getStringExtra("mCurrentCityName");// 市
                    String mCurrentDistrictName = data
                            .getStringExtra("mCurrentDistrictName");// 县
                    etArea.setText(mCurrentProviceName + "," + mCurrentCityName
                            + "," + mCurrentDistrictName);
                }
                // 注册地址
            } else if (requestCode == REQUEST_REGISTER) {
                if (data != null) {
                    String mCurrentProviceName = data
                            .getStringExtra("mCurrentProviceName");// 省
                    String mCurrentCityName = data
                            .getStringExtra("mCurrentCityName");// 市
                    String mCurrentDistrictName = data
                            .getStringExtra("mCurrentDistrictName");// 县
                    String mCurrentXipCode = data
                            .getStringExtra("mCurrentXipCode");// 邮编
                    etRegisterAdd.setText(mCurrentProviceName
                            + mCurrentCityName + mCurrentDistrictName);
                    etRegisterPostcode.setText(mCurrentXipCode);
                }
                // 经营地址
            } else if (requestCode == REQUEST_BUSINESS) {
                if (data != null) {
                    String mCurrentProviceName = data
                            .getStringExtra("mCurrentProviceName");// 省
                    String mCurrentCityName = data
                            .getStringExtra("mCurrentCityName");// 市
                    String mCurrentDistrictName = data
                            .getStringExtra("mCurrentDistrictName");// 县
                    String mCurrentXipCode = data
                            .getStringExtra("mCurrentXipCode");// 邮编
                    etBusinessAdd.setText(mCurrentProviceName
                            + mCurrentCityName + mCurrentDistrictName);
                    etBusinessPostcode.setText(mCurrentXipCode);
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 商户分类
     */
    private void getmerChantInfo() {
        // TODO Auto-generated method stub
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MERCHANT_INFO_ERROR:
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case MERCHANT_INFO_VALUE:// 赋值
                    TenantBean tenantbean = objbeanmerchant.getTenant();
                    List<TenantDetailBean> tenantdetailbean = objbeanmerchant
                            .getTenantDetail();
                    String tenantTypeCode = tenantbean.getTenantType().toString();// 商户类型编号
                    //不让选择
//                    if (!TextUtils.isEmpty(tenantbean.getTenantFullname())
//                            && !TextUtils.isEmpty(tenantbean.getTenantShortname())
//                            && !TextUtils.isEmpty(tenantTypeCode)
//                            && !TextUtils.isEmpty(tenantdetailbean.get(0)
//                            .getRegionIdProvince())
//                            && !TextUtils.isEmpty(tenantdetailbean.get(0)
//                            .getRegionIdCity())
//                            && !TextUtils.isEmpty(tenantdetailbean.get(0)
//                            .getRegionIdDistrict())) {
                    edmerchantName.setEnabled(false);
                    edenglishName.setEnabled(false);
                    mTenantType.setEnabled(false);
                    edabbreName.setEnabled(false);
                    etArea.setEnabled(false);
                    mMarketPosi.setEnabled(false);
                    mOperaStatus.setEnabled(false);
                    btnSubmite.setVisibility(View.GONE);
//                    }
                    edmerchantName.setText(TextUtils.isEmpty(tenantbean
                            .getTenantFullname()) ? "" : tenantbean
                            .getTenantFullname());// 商户名称
                    edenglishName.setText(TextUtils.isEmpty(tenantbean
                            .getTenantEnglishName()) ? "" : tenantbean
                            .getTenantEnglishName());// 商户英文名称
                    edabbreName.setText(TextUtils.isEmpty(tenantbean
                            .getTenantShortname()) ? "" : tenantbean
                            .getTenantShortname());// 商户简称
                    if (tenantTypeList != null && tenantTypeList.size() >= 0) {//防止null
                        for (int i = 0; i < tenantTypeList.size(); i++) {
                            String tenantTypeiId = tenantTypeList.get(i).getTypecode();// 商户编号
                            if (tenantTypeCode.equals(tenantTypeiId)) {
                                mTenantType.setSelection(i);// 商户类型
                                if (tenantTypeiId.toString().equals("PERSON")) {// 农户
                                    lLenterprise.setVisibility(View.GONE);
                                } else {
                                    lLenterprise.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                    etArea.setText((TextUtils.isEmpty(tenantdetailbean.get(0)
                            .getRegionIdProvince()) ? "" : tenantdetailbean.get(0)
                            .getRegionIdProvince())
                            + (TextUtils.isEmpty(tenantdetailbean.get(0)
                            .getRegionIdCity()) ? "" : tenantdetailbean
                            .get(0).getRegionIdCity())
                            + (TextUtils.isEmpty(tenantdetailbean.get(0)
                            .getRegionIdDistrict()) ? "" : tenantdetailbean
                            .get(0).getRegionIdDistrict()));// 所属地区
                    String marposiCode = tenantdetailbean.get(0).getMarketLevel();// 市场定位编码
                    if (marposiList != null) {
                        for (int i = 0; i < marposiList.size(); i++) {
                            String marposiId = marposiList.get(i).getTypecode();
                            if (marposiCode.equals(marposiId)) {
                                mMarketPosi.setSelection(i);// 市场定位
                            }
                        }
                    }
                    String saleReginoCode = tenantdetailbean.get(0).getSaleRegion();// 销售区域编码
                    if (salesAreaList != null) {
                        for (int i = 0; i < salesAreaList.size(); i++) {
                            String saleReginoId = salesAreaList.get(i).getTypecode();
                            if (saleReginoCode.equals(saleReginoId)) {
                                mSalesArea.setSelection(i);// 销售区域
                            }
                        }
                    }
                    String openStatusCode = tenantdetailbean.get(0).getOpenStatus();// 经营状态编码
                    for (int i = 0; i < operaStatusList.size(); i++) {
                        String openStautId = operaStatusList.get(i).getTypecode();
                        if (openStatusCode.equals(openStautId)) {
                            mOperaStatus.setSelection(i);// 经营状态
                        }
                    }
                    String licenceMarkCode = tenantdetailbean.get(0)
                            .getLicenceMerged();// 执照合格标志编码
                    for (int i = 0; i < licenseMarkList.size(); i++) {
                        String licenceMarktId = licenseMarkList.get(i)
                                .getTypecode();
                        if (licenceMarkCode.equals(licenceMarktId)) {
                            mLicenseMark.setSelection(i);// 执照合格标志
                        }
                    }
                    etBusinessLicense.setText(TextUtils.isEmpty(tenantdetailbean
                            .get(0).getBusiLicenceCode()) ? "" : tenantdetailbean
                            .get(0).getBusiLicenceCode());// 营业执照号
                    String startPeriod = String
                            .valueOf(tenantdetailbean.get(0).getBusiTimeBegin());
                    if (!startPeriod.equals("0")) {
                        etStartPeriod.setText(DateLocalUtil.getDate(TextUtils.isEmpty(startPeriod) ? ""
                                : startPeriod));// 经营有效期开始
                    }
                    String stopPeriod = String
                            .valueOf(tenantdetailbean.get(0).getBusiTimeEnd());
                    if (!stopPeriod.equals("0")) {
                        etStopPeriod.setText(DateLocalUtil.getDate(TextUtils.isEmpty(stopPeriod) ? ""
                                : stopPeriod));// 经营有效期结束
                    }
                    etOrganization.setText(TextUtils.isEmpty(tenantdetailbean
                            .get(0).getOrgLicenceCode()) ? "" : tenantdetailbean
                            .get(0).getOrgLicenceCode());// 组织机构证号
                    etTaxRegistration.setText(TextUtils.isEmpty(tenantdetailbean
                            .get(0).getTaxLicenceCode()) ? "" : tenantdetailbean
                            .get(0).getTaxLicenceCode());// 税务登记证号

                    String registerAddrCountry = TextUtils.isEmpty(tenantdetailbean
                            .get(0).getRegisterAddrCountry()) ? ""
                            : tenantdetailbean.get(0).getRegisterAddrCountry();// 注册地址-国
                    String registerAddrProvince = TextUtils
                            .isEmpty(tenantdetailbean.get(0)
                                    .getRegisterAddrProvince()) ? ""
                            : tenantdetailbean.get(0).getRegisterAddrProvince();// 注册地址-省
                    String registerAddrCity = TextUtils.isEmpty(tenantdetailbean
                            .get(0).getRegisterAddrCity()) ? "" : tenantdetailbean
                            .get(0).getRegisterAddrCity();// 注册地址-市
                    String registerAddrDistrict = TextUtils
                            .isEmpty(tenantdetailbean.get(0)
                                    .getRegisterAddrDistrict()) ? ""
                            : tenantdetailbean.get(0).getRegisterAddrDistrict();// 注册地址-区
                    etRegisterAdd.setText(registerAddrCountry
                            + registerAddrProvince + registerAddrCity
                            + registerAddrDistrict);// 注册地址
                    etRegisterDetailed.setText(TextUtils.isEmpty(tenantdetailbean
                            .get(0).getRegisterAddrDetail()) ? ""
                            : tenantdetailbean.get(0).getRegisterAddrDetail());// 注册详细地址
                    etRegisterPostcode.setText(TextUtils.isEmpty(tenantdetailbean
                            .get(0).getPostcode()) ? "" : tenantdetailbean.get(0)
                            .getPostcode());// 注册地址邮政编码

                    String businessAddrCountry = TextUtils.isEmpty(tenantdetailbean
                            .get(0).getBusinessAddrCountry()) ? ""
                            : tenantdetailbean.get(0).getBusinessAddrCountry();// 经营地址-国
                    String businessAddrProvince = TextUtils
                            .isEmpty(tenantdetailbean.get(0)
                                    .getBusinessAddrProvince()) ? ""
                            : tenantdetailbean.get(0).getBusinessAddrProvince();// 经营地址-省
                    String businessAddrCity = TextUtils.isEmpty(tenantdetailbean
                            .get(0).getBusinessAddrCity()) ? "" : tenantdetailbean
                            .get(0).getBusinessAddrCity();// 经营地址-市
                    String businessAddrDistrict = TextUtils
                            .isEmpty(tenantdetailbean.get(0)
                                    .getBusinessAddrDistrict()) ? ""
                            : tenantdetailbean.get(0).getBusinessAddrDistrict();// 经营地址-区
                    etBusinessAdd.setText(businessAddrCountry
                            + businessAddrProvince + businessAddrCity
                            + businessAddrDistrict);// 经营地址
                    etBusinessPostcode.setText(TextUtils.isEmpty(tenantdetailbean
                            .get(0).getPostcode()) ? "" : tenantdetailbean.get(0)
                            .getPostcode());// 经营地址邮政编码

                    break;
                default:
                    break;
            }
        }

    };

    /**
     * @project name：yyshed
     * @type name：MerchantInfoRequest
     * @description：商户信息请求线程
     * @author：gang
     * @date time：2017-6-12 下午4:52:20
     */
    public void MerchantInfoRequest() {

        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext,
                "tenantId");// 商户id
        String tenantId = currTenantId;// 商户id

        HttpManager.getInstance().domerchantInfo(TAG, token, currTenantId, tenantId, new HttpCallBack<JsonMerchantInfoObject>(MerchantInfoActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
                ToastUtils.makeTextShort("请求失败......");
            }

            @Override
            public void onSuccess(JsonMerchantInfoObject date) {
                if (!date.equals("") && date != null) {
                    String msg = date.getMsg();// 提醒消息
                    if (date.isSuccess()) {// 成功

                        objbeanmerchant = date.getObj();

                        Message mesg = Message.obtain();
                        mesg.what = MERCHANT_INFO_VALUE;
                        mHandler.sendMessage(mesg);
                    }
                }
            }
        });
    }

    /**
     * @project name：yyshed
     * @type name：MerchantSubmitRequest
     * @description：商户信息修改提交请求线程
     * @author：gang
     * @date time：2017-6-14 下午8:05:48
     */
    class MerchantSubmitRequest extends Thread {
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
                String tenantId = currTenantId;// 商户id
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<>();
                map.put("token", token);// token
                map.put("currTenantId", currTenantId);// 商户id
                map.put("tenantId", tenantId);// 商户id
                map.put("tenantFullname", merchantName);// 商户名称
                map.put("tenantEnglishName", englishName);// 英文名称
                map.put("tenantShortname", abbreName);// 商户简称
                map.put("tenantType", ficatType);// 商户分类
                map.put("regionIds", Area);// 所示区域

                WapiUtilEx.domerchantUpdate(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = MERCHANT_INFO_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = MERCHANT_INFO_SUCCESS;
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

    // -------------------商户分类状态-------------------

    /**
     * 商户分类状态数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: TenantTypeListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-22 下午4:53:32
     */

    public void TenantTypeListRequest() {

        String key = "SP_TENANT_TYPE";// 商户分类状态
        Log.v(TAG, TAG + "商户分类状态数据字典列表请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(MerchantInfoActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }

            @Override
            public void onSuccess(JsonDataDictionaryObject date) {
                Log.i(TAG, "response 任务状态数据字典请求-> " + date);
                if (TextUtils.isEmpty(date.toString())) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }
                if (!date.equals("")
                        && date != null) {
                    if (date.isSuccess()) {// 成功
                        tenantTypeList = date.getObj();
                        JsonDataDictionaryObject.ObjBean bean = new JsonDataDictionaryObject.ObjBean();
                        bean.setTypecode("");
                        bean.setTypename("请选择");
                        tenantTypeList.add(0, bean);
                        // 设置适配器
                        tenantTypeAdapter = new DataDictionaryAdapter(mContext,
                                tenantTypeList);
                        // 绑定 Adapter到控件
                        mTenantType.setAdapter(tenantTypeAdapter);
                    }
                }
            }
        });
    }

    // -------------------市场定位状态-------------------

    /**
     * 市场定位状态数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: MarketPosiListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-22 下午4:53:44
     */
    public void MarketPosiListRequest() {

        String key = "BP_MARKET_LEVEL";// 市场定位状态
        Log.v(TAG, TAG + "市场定位状态数据字典列表请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(MerchantInfoActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
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
                        marposiList = date.getObj();
                        JsonDataDictionaryObject.ObjBean bean = new JsonDataDictionaryObject.ObjBean();
                        bean.setTypecode("");
                        bean.setTypename("请选择");
                        marposiList.add(0, bean);
                        // 设置适配器
                        marketPosiAdapter = new DataDictionaryAdapter(mContext,
                                marposiList);
                        // 绑定 Adapter到控件
                        mMarketPosi.setAdapter(marketPosiAdapter);
                    }
                }
            }
        });
    }


    // -------------------销售区域状态-------------------

    /**
     * 销售区域状态数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: SalesAreaListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-22 下午4:53:44
     */
    public void SalesAreaListRequest() {

        String key = "BP_SALE_REGION";// 销售区域状态
        Log.v(TAG, TAG + "销售区域状态数据字典列表请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(MerchantInfoActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
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
                        salesAreaList = date.getObj();
                        JsonDataDictionaryObject.ObjBean bean = new JsonDataDictionaryObject.ObjBean();
                        bean.setTypecode("");
                        bean.setTypename("请选择");
                        salesAreaList.add(0, bean);
                        // 设置适配器
                        salesAreaAdapter = new DataDictionaryAdapter(mContext,
                                salesAreaList);
                        // 绑定 Adapter到控件
                        mSalesArea.setAdapter(salesAreaAdapter);
                    }
                }
            }
        });
    }


    // -------------------经营状态-------------------

    /**
     * 经营状态数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: SalesAreaListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-22 下午4:53:44
     */
    public void OperaStatusListRequest() {

        String key = "BP_OPEN_STATUS";// 经营状态
        Log.v(TAG, TAG + "经营状态数据字典列表请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(MerchantInfoActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
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
                        operaStatusList = date.getObj();
                        JsonDataDictionaryObject.ObjBean bean = new JsonDataDictionaryObject.ObjBean();
                        bean.setTypecode("");
                        bean.setTypename("请选择");
                        operaStatusList.add(0, bean);
                        // 设置适配器
                        operaStatusAdapter = new DataDictionaryAdapter(mContext,
                                operaStatusList);
                        // 绑定 Adapter到控件
                        mOperaStatus.setAdapter(operaStatusAdapter);
                    }
                }
            }
        });
    }


    // -------------------执照合格标志-------------------

    /**
     * 执照合格标志数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: SalesAreaListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-22 下午4:53:44
     */
    public void LicenseMarkListRequest() {

        String key = "sf_yn";// 执照合格标志
        Log.v(TAG, TAG + "执照合格标志数据字典列表请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(MerchantInfoActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
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
                        licenseMarkList = date.getObj();
                        JsonDataDictionaryObject.ObjBean bean = new JsonDataDictionaryObject.ObjBean();
                        bean.setTypecode("");
                        bean.setTypename("请选择");
                        licenseMarkList.add(0, bean);
                        // 设置适配器
                        licenseMarkAdapter = new DataDictionaryAdapter(mContext,
                                licenseMarkList);
                        // 绑定 Adapter到控件
                        mLicenseMark.setAdapter(licenseMarkAdapter);
                    }
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // TODO Auto-generated method stub
        switch (parent.getId()) {
            case R.id.tenant_type:// 商户分类
                ficatType = tenantTypeList.get(position).getTypecode();// 市场定位编号
                break;
            case R.id.market_posi:// 市场定位
                marketName = marposiList.get(position).getTypecode();// 市场定位编号
                break;
            case R.id.sales_area:// 销售区域
                salesAreaList.get(position).getTypecode();// 销售区域编号
                break;
            case R.id.opera_status:// 经营状态
                operaStatusList.get(position).getTypecode();// 经营状态编号
                break;
            case R.id.license_mark:// 执照合格标志
                licenseMarkList.get(position).getTypecode();// 执照合格标志编号
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
