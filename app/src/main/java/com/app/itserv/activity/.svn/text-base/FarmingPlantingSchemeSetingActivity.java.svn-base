package com.app.itserv.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.itserv.adapters.BaseBelAdapter;
import com.app.itserv.adapters.GhosueBelAdapter;
import com.app.itserv.adapters.PlanSchemeAdapter;
import com.app.itserv.jparser.JsonBaseManagerObject;
import com.app.itserv.jparser.JsonGreenhouseManagerObject;
import com.app.itserv.jparser.JsonPlanSchemeObject;
import com.app.itserv.jparser.JsonPlanSchemeObject.AttributesBean;
import com.app.itserv.jparser.JsonPlanSchemeObject.ObjBean;
import com.app.itserv.views.LoadingFrameView;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.DateTimePickDialogUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 农事种植计划设置
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: FarmingPlantingSchemeSetingActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-5 上午11:00:08
 */
public class FarmingPlantingSchemeSetingActivity extends Activity implements
        OnClickListener {

    private Context mContext;

    private static final String TAG = "FarmingPlantingScheme";
    private List<ObjBean> planschemeList;

    private LoadingFrameView loadView;

    // 所属基地
    protected static final int BASELIST_ERROR = 4;
    protected static final int BASELIST_SUCCESS = 5;
    protected static final int BASELIST_VALUES = 6;
    private List<JsonBaseManagerObject.ObjBean> baseObjList = new ArrayList<>();
    private BaseBelAdapter baseBleAdapter;

    // 所属大棚
    private List<JsonGreenhouseManagerObject.ObjBean> gHouseObjList = new ArrayList<>();
    private GhosueBelAdapter gHouseBleAdapter;

    private DateTimePickDialogUtil dateTimePicKDialog;// 时间选择器的Dialog
    private String initDateTime;// 时间选择器的Dialog

    private ImageView ImgBack;// 返回按钮
    private Button btnAdd;// 新增按钮
    private Button btnselect;// 查询按钮
    private Button changepswreset;// 重置按钮
    private Spinner chemebase;
    private Spinner greenhouse;
    private EditText planname;
    private EditText quicode;
    private TextView startplantime;
    private TextView stopplantime;

    private String strchemebase;// 基地
    private String strgreenhouse;// 大棚
    private String strplanname;// 计划名称
    private String strquicode;// 种植作物
    private String strstartplantime;// 开始时间
    private String strstopplantime;// 结束时间

    private ListView plan_schem_items;
    private PlanSchemeAdapter planschemeadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farming_plan_scheme_setting_lay);
        mContext = this;
        initView();
        init();

        new Thread(new BaseManagerRequest()).start();// 基地列表请求线程
        GreenHouseListRequest();// 大棚列表请求线程
    }

    private void init() {
        // TODO Auto-generated method stub
        PlanSchemeRequest();// 种植计划请求线程
    }

    private void initView() {
        // 创建时间选择器
        initDateTime = DateTimePickDialogUtil.formatDate();
        dateTimePicKDialog = new DateTimePickDialogUtil(this, initDateTime);
        chemebase = (Spinner) findViewById(R.id.farming_plan_scheme_base_bel);// 所属基地
        greenhouse = (Spinner) findViewById(R.id.farming_plan_scheme_tv_greenhouse);// 所属大棚
        planname = (EditText) findViewById(R.id.farming_plan_scheme_plan_name);// 计划名称
        quicode = (EditText) findViewById(R.id.farming_plan_scheme_equi_code);// 种植作物
        startplantime = (TextView) findViewById(R.id.farming_plan_scheme_start_plan_time);// 种植开始时间
        startplantime.setOnClickListener(this);
        stopplantime = (TextView) findViewById(R.id.farming_plan_scheme_stop_plan_time);// 种植结束时间
        stopplantime.setOnClickListener(this);
        ImgBack = (ImageView) findViewById(R.id.img_back);// 返回按钮
        ImgBack.setOnClickListener(this);
        btnAdd = (Button) findViewById(R.id.farming_plan_scheme_btn_add);// 新增按钮
        btnAdd.setOnClickListener(this);
        btnselect = (Button) findViewById(R.id.farming_plan_scheme_btn_select);// 查询按钮
        btnselect.setOnClickListener(this);
        changepswreset = (Button) findViewById(R.id.farming_plan_scheme_changepsw_reset);// 重置按钮
        changepswreset.setOnClickListener(this);
        loadView = (LoadingFrameView) findViewById(R.id.load_view);
        // listview条目
        plan_schem_items = (ListView) findViewById(R.id.plan_schem_items);
//		plan_schem_items.setOnItemClickListener(this);

        // 所属基地选择监听
        chemebase.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                strchemebase = baseObjList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        // 所属大棚选择监听
        greenhouse.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                strgreenhouse = gHouseObjList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回按钮
                finish();
                break;
            case R.id.farming_plan_scheme_btn_add:// 农事种植计划新增
                startActivity(new Intent(mContext, FarmingPlanAddActivity.class)
                        .putExtra("planaddedit", "planadd"));
                break;
            case R.id.farming_plan_scheme_btn_select:// 查询按钮
                farmingplangetinfo();
                break;
            case R.id.farming_plan_scheme_changepsw_reset:// 重置按钮
                textReset();
                init();
                break;
            case R.id.farming_plan_scheme_start_plan_time:// 种植开始时间
                dateTimePicKDialog.dateTimePicKDialog(startplantime);
                break;
            case R.id.farming_plan_scheme_stop_plan_time:// 种植结束时间
                dateTimePicKDialog.dateTimePicKDialog(stopplantime);
                break;
            default:
                break;
        }
    }

    private void textReset() {
        planname.getText().clear();// 计划名称
        quicode.getText().clear();// 种植作物
        startplantime.setText("");// 种植开始时间
        stopplantime.setText("");// 种植结束时间
        strchemebase = "";// 基地
        strgreenhouse = "";// 大棚
        strplanname = "";// 计划名称
        strquicode = "";// 种植作物
        strstartplantime = "";// 开始时间
        strstopplantime = "";// 结束时间
    }

    private void farmingplangetinfo() {
        strplanname = planname.getText().toString().trim();
        strquicode = quicode.getText().toString().trim();
        strstartplantime = startplantime.getText().toString().trim();
        strstopplantime = stopplantime.getText().toString().trim();
        // ToastUtils.makeTextShort("时间格式,应为：2017-05-05格式");
        if (!DateTimePickDialogUtil.compare_date(strstartplantime, strstopplantime)) {
            ToastUtils.makeTextShort("开始时间大于结束时间");
            return;
        }
        // 查询请求
        init();
    }


    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BASELIST_ERROR:// 请求失败 所属基地----------
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case BASELIST_SUCCESS:
                    String baseManager_json = msg.obj.toString();
                    new Thread(new BaseManagerAction(baseManager_json)).start();// 基地列表报文解析线程
                    break;
                case BASELIST_VALUES:
                    baseBleAdapter = new BaseBelAdapter(mContext, baseObjList);
                    // 绑定 Adapter到控件
                    chemebase.setAdapter(baseBleAdapter);
                    chemebase.setSelection(0);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 种植计划请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: PlanSchemeRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-5 上午11:18:14
     */
    public void PlanSchemeRequest() {

        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext,
                "tenantId");// 商户id
        Log.v(TAG, TAG + "种植计划列表请求");
        HttpManager.getInstance().planschemelist(TAG, token, currTenantId, strchemebase, strgreenhouse, "*" + strplanname + "*", "*" + strquicode + "*", strstartplantime, strstopplantime, new HttpCallBack<JsonPlanSchemeObject>(FarmingPlantingSchemeSetingActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                loadView.setErrorShown(true, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        init();
                    }
                });
            }

            @Override
            public void onSuccess(JsonPlanSchemeObject date) {
                if (TextUtils.isEmpty(date.toString())) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }
                if (!date.equals("") && date != null) {

                    if (date.isSuccess()) {// 成功
                        planschemeList = date.getObj();
                        if (planschemeList != null) {
                            // 设置适配器
                            planschemeadapter = new PlanSchemeAdapter(mContext,
                                    planschemeList);
                            plan_schem_items.setAdapter(planschemeadapter);
                            loadView.delayShowContainer(true);
                        } else {
                            loadView.setNoShown(true);
                        }
                    } else
                        loadView.setErrorShown(true, new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                init();
                            }
                        });
                }
            }
        });

    }


    /**
     * 重新获取焦点，重新请求数据
     */
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        init();
    }

    /**
     * 基地列表请求线程
     *
     * @author cyq
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BaseManagerRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-31 下午6:55:14
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

                Log.v(TAG, TAG + "基地列表请求");
                WapiUtilEx.basemanagerlist(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = BASELIST_ERROR;
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
     * @author cyq
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BaseManagerAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-31下午7:01:12
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
                        JsonBaseManagerObject.ObjBean bean = new JsonBaseManagerObject.ObjBean();
                        bean.setBaseFullname("全部");
                        bean.setId("");
                        baseObjList.add(0, bean);
                        Message msg = Message.obtain();
                        msg.what = BASELIST_VALUES;
                        mHandler.sendMessage(msg);
                    } else
                        TAUtils.toastMessage((Activity) mContext,
                                base_manager.getMsg());
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
     * 大棚列表请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-10 下午3:10:29
     */
    public void GreenHouseListRequest() {


        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext,
                "tenantId");// 商户id
        Log.v(TAG, TAG + "大棚列表请求");
        HttpManager.getInstance().ghousemanagerlist(TAG, token, currTenantId, new HttpCallBack<JsonGreenhouseManagerObject>() {
            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onSuccess(JsonGreenhouseManagerObject date) {
                if (TextUtils.isEmpty(date.toString())) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }
                if (!date.equals("") && date != null) {
                    if (date.isSuccess()) {// 成功
                        gHouseObjList = date.getObj();
                        if (gHouseObjList != null) {
                            JsonGreenhouseManagerObject.ObjBean bean = new JsonGreenhouseManagerObject.ObjBean();
                            bean.setId("");
                            bean.setGhouseFullname("全部");
                            gHouseObjList.add(0, bean);
                            gHouseBleAdapter = new GhosueBelAdapter(mContext, gHouseObjList);
                            // 绑定 Adapter到控件
                            greenhouse.setAdapter(gHouseBleAdapter);
                            greenhouse.setSelection(0);
                        }
                    }
                }
            }
        });
    }
}
