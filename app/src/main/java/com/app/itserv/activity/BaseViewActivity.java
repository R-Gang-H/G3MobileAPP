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
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.jparser.JsonBaseExaObject;
import com.app.itserv.jparser.JsonBaseExaObject.AttributesBean;
import com.app.itserv.jparser.JsonBaseExaObject.ObjBean;
import com.app.itserv.jparser.JsonBaseExaObject.ObjBean.GreenbaseBean;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 查看基地
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: BaseViewActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-7 下午6:06:23
 */
public class BaseViewActivity extends Activity implements OnClickListener {

    private Context mContext;
    private static final String TAG = "BaseViewActivity";

    protected static final int BASEEXA_ERROR = 1;
    protected static final int BASEEXA_SUCCESS = 2;
    protected static final int BASEEXA_VALUES = 3;

    private ImageView ImgBack;
    private String baseId;
    private TextView tvAtivaDate, tvBusinessAdd, edBasename, edBasecode,
            etTotalArea, etPlanArea, etAbout, tvBusinessDetailed, tvUseState;

    private List<?> baseGreenList;
    private GreenbaseBean baseExa;
    private List<JsonDataDictionaryObject.ObjBean> userStatukList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_view_lay);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        userStatukList = (List<JsonDataDictionaryObject.ObjBean>) bundle.getSerializable("userStatukList");
        initView();
        init();
    }

    private void init() {
        baseId = (String) getIntent().getExtras().get("baseId");
        new Thread(new ExaBaseRequest(baseId)).start();// 查看基地
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        edBasename = (TextView) findViewById(R.id.basename);// 基地名称
        edBasecode = (TextView) findViewById(R.id.basecode);// 基地编号
        tvAtivaDate = (TextView) findViewById(R.id.tv_activa_date);// 启动日期
        etTotalArea = (TextView) findViewById(R.id.et_total_area);// 总面积
        etPlanArea = (TextView) findViewById(R.id.et_plan_area);// 种植面积
        etAbout = (TextView) findViewById(R.id.et_about);// 简介
        tvBusinessAdd = (TextView) findViewById(R.id.business_add);// 详细地址
        tvBusinessDetailed = (TextView) findViewById(R.id.business_detailed);// 详细地址
        tvUseState = (TextView) findViewById(R.id.tv_use_state);//使用状态
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

                if (!base_exa.equals("") && base_exa != null) {

                    if (base_exa.isSuccess()) {// 成功
                        ObjBean baseExaObj = base_exa.getObj();
                        baseGreenList = baseExaObj.getGreenhouseList();
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

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BASEEXA_ERROR:// 种植计划请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case BASEEXA_SUCCESS:// 查看
                    String baseExa_json = msg.obj.toString();
                    new Thread(new BaseExaAction(baseExa_json)).start();// 查看基地报文解析线程
                    break;
                case BASEEXA_VALUES:// 查看
                    String baseName = TextUtils.isEmpty(baseExa.getBaseFullname()) ? ""
                            : baseExa.getBaseFullname();
                    String baseCode = TextUtils.isEmpty(baseExa.getBaseCode()) ? ""
                            : baseExa.getBaseCode();
                    String ativaDate = TextUtils.isEmpty(String.valueOf(baseExa
                            .getOpenDateOpen())) ? "" : String.valueOf(baseExa
                            .getOpenDateOpen());
                    String totalArea = TextUtils.isEmpty(String.valueOf(baseExa
                            .getCoveredArea())) ? "" : String.valueOf(baseExa
                            .getCoveredArea());
                    String planArea = TextUtils.isEmpty(String.valueOf(baseExa
                            .getCoveredAreaMu())) ? "" : String.valueOf(baseExa
                            .getCoveredAreaMu());
                    String about = TextUtils.isEmpty(baseExa.getContent()) ? ""
                            : String.valueOf(baseExa.getContent());
                    String countryExa = TextUtils.isEmpty(baseExa
                            .getRegionIdCountry()) ? "" : baseExa
                            .getRegionIdCountry();
                    String provinceExa = TextUtils.isEmpty(baseExa
                            .getRegionIdProvince()) ? "" : baseExa
                            .getRegionIdProvince();
                    String cityExa = TextUtils.isEmpty(baseExa.getRegionIdCity()) ? ""
                            : baseExa.getRegionIdCity();
                    String districtExa = TextUtils.isEmpty(baseExa
                            .getRegionIdDistrict()) ? "" : baseExa
                            .getRegionIdDistrict();
                    String businessAdd = countryExa + provinceExa + cityExa
                            + districtExa;
                    String businessDetailed = TextUtils.isEmpty(baseExa.getRegionAddr()) ? "" : baseExa.getRegionAddr();
                    String useState = TextUtils.isEmpty(String.valueOf(baseExa.getStatus())) ? "" : String.valueOf(baseExa.getStatus());

                    edBasename.setText(baseName);
                    edBasecode.setText(baseCode);
                    tvAtivaDate.setText(DateLocalUtil.getDate(ativaDate));
                    etTotalArea.setText(totalArea);
                    etPlanArea.setText(planArea);
                    etAbout.setText(about);
                    tvBusinessAdd.setText(businessAdd);
                    tvBusinessDetailed.setText(businessDetailed);
                    for (int i = 0; i < userStatukList.size(); i++) {
                        String userStatuId = userStatukList.get(i).getTypecode();
                        if (useState.equals(userStatuId)) {
                            String userStatuName = userStatukList.get(i).getTypename();
                            tvUseState.setText(userStatuName);// 使用状态
                        }
                    }
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

//                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
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
                        msg.what = BASEEXA_ERROR;
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

}
