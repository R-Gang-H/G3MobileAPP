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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.itserv.adapters.BaseBelAdapter;
import com.app.itserv.adapters.DataDictionaryAdapter;
import com.app.itserv.adapters.DistriPersonAdapter;
import com.app.itserv.adapters.GhosueBelAdapter;
import com.app.itserv.adapters.PlantBelAdapter;
import com.app.itserv.jparser.JsonBaseManagerObject;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonFarTaskAddObject;
import com.app.itserv.jparser.JsonFarTaskAddObject.ObjBean;
import com.app.itserv.jparser.JsonFarTaskEditObject.ObjBean.PlantingTaskBean;
import com.app.itserv.jparser.JsonGreHouEmpObject;
import com.app.itserv.jparser.JsonGreenhouseManagerObject;
import com.app.itserv.jparser.JsonPlanSchemeObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.DateTimePickDialogUtil;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.RegexChk;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新增、复制农事任务设置
 *
 * @author Administrator
 */
public class FarmingTaskAddActivity extends Activity implements
        OnClickListener, OnItemSelectedListener {

    private Context mContext;

    public static final String TAG = "FarmingTaskAddActivity";

    // 农事任务添加
    protected static final int FARTASKADD_ERROR = 1;
    protected static final int FARTASKADD_SUCCESS = 2;

    private ImageView ImgBack;
    private TextView tvAddcopyTitle, startDistriTime, stopDistriTime;

    private EditText etTaskName, etTaskDesc,
            etDistExplain, etExecutor;
    private Button btnSaveDraft, btnChanPswReset, btnDirectAll;

    private String baseBel, greHouBel, planBel, taskName, execuFre, startDistr,
            stopDistr, farType, taskDesc, distExp, executor;

    private ObjBean objAddBean;
    private PlantingTaskBean plantaskBean;
    private String plantingTaskId;

    // 所属基地
    protected static final int BASELIST_ERROR = 4;
    protected static final int BASELIST_SUCCESS = 5;
    protected static final int BASELIST_VALUES = 6;

    private Spinner spBaseBel;// 所属基地
    private List<JsonBaseManagerObject.ObjBean> baseObjList;
    private BaseBelAdapter baseBleAdapter;

    // 所属大棚
    private Spinner spGhouseBel;// 所属大棚
    private List<JsonGreenhouseManagerObject.ObjBean> gHouseObjList;
    private GhosueBelAdapter gHouseBleAdapter;

    // 所属计划
    private Spinner spPlantBel;// 所属计划
    private List<JsonPlanSchemeObject.ObjBean> planschemeList;
    private PlantBelAdapter plantBleAdapter;

    // 执行频率数据字典
    private Spinner spExecuFre;// 执行频率
    private List<JsonDataDictionaryObject.ObjBean> executFarObjBeans;
    private DataDictionaryAdapter executFarAdapter;

    // 农事分类数据字典
    private Spinner spFarType;
    private List<JsonDataDictionaryObject.ObjBean> datadicObjBeans;
    private DataDictionaryAdapter dataDictionaryAdapter;

    // 执行人
    protected static final int GHOUSEEMP_ERROR = 16;
    protected static final int GHOUSEEMP_SUCCESS = 17;
    protected static final int GHOUSEEMP_VALUES = 18;

    private Spinner spExecutor;// 执行人
    private List<JsonGreHouEmpObject.ObjBean> gHouseUserEmpList;
    private DistriPersonAdapter distriPersonAdapter;

    private DateTimePickDialogUtil dateTimePicKDialog;// 时间选择器的Dialog
    private String initDateTime;// 时间选择器的Dialog
    String faraddcopy;
    boolean isCoopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_task_add_lay);
        mContext = this;
        initView();

        Bundle bundle = getIntent().getExtras();
        faraddcopy = bundle.getString("faraddcopy");

        if (faraddcopy.equals("faradd")) {
            tvAddcopyTitle.setText("新增农事任务");
        } else {
            plantaskBean = (PlantingTaskBean) bundle.get("plantaskBean");
            plantingTaskId = plantaskBean.getId();
            tvAddcopyTitle.setText("复制农事任务");
            isCoopy = true;
            new Thread(new FarTaskCopyRequest()).start();// 复制农事任务请求线程
        }

        new Thread(new BaseManagerRequest()).start();// 基地列表请求线程
        GreenHouseListRequest();// 大棚列表请求线程
        PlanSchemeRequest();// 种植计划请求线程
        ExecuFreRequest();// 执行频率数据字典表请求线程
        DataDictionaryRequest();// 农事分类数据字典表请求线程

    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvAddcopyTitle = (TextView) findViewById(R.id.tvaddcopy_title);
        // 创建时间选择器
        initDateTime = DateTimePickDialogUtil.formatDate();
        dateTimePicKDialog = new DateTimePickDialogUtil(this, initDateTime);
        spBaseBel = (Spinner) findViewById(R.id.sp_base_bel);// 所属基地
        spBaseBel.setOnItemSelectedListener(this);
        spGhouseBel = (Spinner) findViewById(R.id.sp_ghouse_bel);// 所属大棚
        spGhouseBel.setOnItemSelectedListener(this);
        spPlantBel = (Spinner) findViewById(R.id.sp_plan_bel);// 所属计划
        spPlantBel.setOnItemSelectedListener(this);
        etTaskName = (EditText) findViewById(R.id.et_task_name);// 任务名称
        spExecuFre = (Spinner) findViewById(R.id.sp_execu_fre);// 执行频率
        spExecuFre.setOnItemSelectedListener(this);
        startDistriTime = (TextView) findViewById(R.id.start_distri_time);// 执行时间段开始时间
        startDistriTime.setOnClickListener(this);
        stopDistriTime = (TextView) findViewById(R.id.stop_distri_time);// 执行时间段结束时间
        stopDistriTime.setOnClickListener(this);
        spFarType = (Spinner) findViewById(R.id.sp_far_type);// 农事分类
        spFarType.setOnItemSelectedListener(this);
        etTaskDesc = (EditText) findViewById(R.id.et_task_desc);// 任务描述
        etDistExplain = (EditText) findViewById(R.id.et_dist_explain);// 分配说明
        etExecutor = (EditText) findViewById(R.id.et_executor);// 执行人
        spExecutor = (Spinner) findViewById(R.id.sp_executor);// 执行人
        spExecutor.setOnItemSelectedListener(this);

        btnSaveDraft = (Button) findViewById(R.id.btn_save_draft);// 保存草稿
        btnSaveDraft.setOnClickListener(this);
        btnChanPswReset = (Button) findViewById(R.id.btn_changepsw_reset);// 重置
        btnChanPswReset.setOnClickListener(this);
        btnDirectAll = (Button) findViewById(R.id.btn_direct_all);// 直接分配
        btnDirectAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_save_draft:// 保存草稿
                if (validator()) {
                    new Thread(new FarTaskAddRequest()).start();
                }
                break;
            case R.id.btn_changepsw_reset:// 重置
                farTaskResetText();
                break;
            case R.id.btn_direct_all:// 直接分配
                if (validator()) {
                    new Thread(new FarTaskAddRequest()).start();
                }
                break;
            case R.id.start_distri_time://开始时间
                dateTimePicKDialog.dateTimePicKDialog(startDistriTime);
                break;
            case R.id.stop_distri_time://结束时间
                dateTimePicKDialog.dateTimePicKDialog(stopDistriTime);
                break;
            default:
                break;
        }

    }

    /**
     * 非空验证
     *
     * @return boolean
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-21 下午3:27:02
     */
    private boolean validator() {
        // TODO Auto-generated method stub
        getFarTaskAddText();
        // if (TextUtils.isEmpty(baseBel)) {
        // TAUtils.toastMessage((Activity) mContext, "所属基地不能为空!");
        // return false;
        // }
        // if (RegexChk.compileExChar(baseBel)) {
        // TAUtils.toastMessage((Activity) mContext, "所属大棚不能包含特殊字符!");
        // return false;
        // }
        // if (TextUtils.isEmpty(greHouBel)) {
        // TAUtils.toastMessage((Activity) mContext, "所属大棚不能为空!");
        // return false;
        // }
        // if (RegexChk.compileExChar(greHouBel)) {
        // TAUtils.toastMessage((Activity) mContext, "所属大棚不能包含特殊字符!");
        // return false;
        // }
        // if (TextUtils.isEmpty(planBel)) {
        // TAUtils.toastMessage((Activity) mContext, "所属计划不能为空!");
        // return false;
        // }
        // if (RegexChk.compileExChar(planBel)) {
        // TAUtils.toastMessage((Activity) mContext, "所属计划不能包含特殊字符!");
        // return false;
        // }
        if (TextUtils.isEmpty(taskName)) {
            TAUtils.toastMessage((Activity) mContext, "任务名称不能为空!");
            return false;
        }
        if (RegexChk.compileExChar(taskName)) {
            TAUtils.toastMessage((Activity) mContext, "任务名称不能包含特殊字符!");
            return false;
        }
        // if (TextUtils.isEmpty(execuFre)) {
        // TAUtils.toastMessage((Activity) mContext, "请选择执行频率!");
        // return false;
        // }
        // if (TextUtils.isEmpty(startDistr) || TextUtils.isEmpty(stopDistr)) {
        // TAUtils.toastMessage((Activity) mContext, "请选择执行时间段!");
        // return false;
        // }
        // if (TextUtils.isEmpty(farType)) {
        // TAUtils.toastMessage((Activity) mContext, "请选择农事分类!");
        // return false;
        // }
        if (TextUtils.isEmpty(taskDesc)) {
            TAUtils.toastMessage((Activity) mContext, "任务描述不能为空!");
            return false;
        }
        if (TextUtils.isEmpty(distExp)) {
            TAUtils.toastMessage((Activity) mContext, "分配说明不能为空!");
            return false;
        }
        if (TextUtils.isEmpty(executor)) {
            TAUtils.toastMessage((Activity) mContext, "执行人不能为空!");
            return false;
        }
        return true;
    }

    /**
     * 获取新增农事任务文本
     *
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-21 下午3:27:48
     */
    private void getFarTaskAddText() {
        // baseBel = spBaseBel.getText().toString().trim();
        // greHouBel = tvGrehouBel.getText().toString().trim();
        // planBel = tvPlanBel.getText().toString().trim();
        taskName = etTaskName.getText().toString().trim();
        // execuFre = tvExecuFre.getText().toString().trim();
        startDistr = startDistriTime.getText().toString().trim();
        stopDistr = stopDistriTime.getText().toString().trim();
        // farType = tvFarType.getText().toString().trim();
        taskDesc = etTaskDesc.getText().toString().trim();
        distExp = etDistExplain.getText().toString().trim();
        executor = etExecutor.getText().toString().trim();
    }

    /**
     * 重置农事任务文本
     *
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-22 下午3:12:02
     */
    private void farTaskResetText() {
        // TODO Auto-generated method stub
        // tvBaseBel.setText("");
        // tvGrehouBel.setText("");
        // tvPlanBel.setText("");
        etTaskName.setText("");
        // tvExecuFre.setText("");
        // startDistriTime.setText(DateTimePickDialogUtil.formatDate());
        // stopDistriTime.setText(DateTimePickDialogUtil.formatDate());
        // tvFarType.setText("");
        etTaskDesc.setText("");
        etDistExplain.setText("");
        etExecutor.setText("");
    }

    /**
     * 新增农事任务json解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskAddAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-22 下午3:37:26
     */
    public void FarTaskAddAction(String farTaskaddJson) {
        if (TextUtils.isEmpty(farTaskaddJson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonFarTaskAddObject far_task_add = gson.fromJson(
                farTaskaddJson, JsonFarTaskAddObject.class);
        if (!far_task_add.equals("") && far_task_add != null) {
            TAUtils.toastMessage((Activity) mContext,
                    far_task_add.getMsg());
            if (far_task_add.isSuccess()) {// 成功
                objAddBean = far_task_add.getObj();
                if (objAddBean != null) {
                    if (faraddcopy.equals("faradd")) {
                        finish();
                    } else {
                        if (isCoopy) {
                            String baseName = TextUtils.isEmpty(objAddBean.getBaseFullname()) ? ""
                                    : objAddBean.getBaseFullname();
                            String ghouseName = TextUtils
                                    .isEmpty(objAddBean.getGhouseFullname()) ? ""
                                    : objAddBean.getGhouseFullname();
                            String planName = TextUtils.isEmpty(objAddBean.getPlanFullname()) ? ""
                                    : objAddBean.getPlanFullname();
                            taskName = TextUtils.isEmpty(objAddBean.getTaskName()) ? ""
                                    : objAddBean.getTaskName();
                            String execFrequency = TextUtils.isEmpty(objAddBean
                                    .getWorktaskCircle()) ? "" : objAddBean
                                    .getWorktaskCircle();
                            String planDateStart = TextUtils.isEmpty(String.valueOf(objAddBean
                                    .getPlanDateStart())) ? "" : String
                                    .valueOf(objAddBean.getPlanDateStart());
                            String planDateEnd = TextUtils.isEmpty(String.valueOf(objAddBean
                                    .getPlanDateEnd())) ? "" : String.valueOf(objAddBean
                                    .getPlanDateEnd());
                            String farCategory = TextUtils.isEmpty(String.valueOf(objAddBean
                                    .getFarmingCategory())) ? "" : String
                                    .valueOf(objAddBean.getFarmingCategory());
                            taskDesc = TextUtils.isEmpty(objAddBean.getContent()) ? ""
                                    : objAddBean.getContent();
                            setTaskDetailAdapter(baseName, ghouseName, planName, taskName,
                                    execFrequency, planDateStart, planDateEnd, farCategory,
                                    taskDesc);
                            isCoopy = false;
                        } else {
                            finish();
                        }
                    }
                }
            } else {
                TAUtils.toastMessage((Activity) mContext,
                        far_task_add.getMsg());
            }
        }
    }

    private void setTaskDetailAdapter(String baseName, String ghouseName,
                                      String planName, String taskName, String execFrequency,
                                      String planDateStart, String planDateEnd, String farCategory,
                                      String taskDesc) {
        etTaskName.setText(taskName);// 任务名称
        startDistriTime.setText(planDateStart);// 执行开始时间段
        stopDistriTime.setText(planDateEnd);// 执行结束时间段
        etTaskDesc.setText(taskDesc);// 任务描述
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FARTASKADD_ERROR:// 农事任务新增请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case FARTASKADD_SUCCESS:
                    String fartaskadd_json = msg.obj.toString();
                    FarTaskAddAction(fartaskadd_json);// 农事任务新增请求解析线程
                    break;
                case BASELIST_ERROR:// 请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case BASELIST_SUCCESS:
                    String baseManager_json = msg.obj.toString();
                    new Thread(new BaseManagerAction(baseManager_json)).start();// 基地列表报文解析线程
                    break;
                case BASELIST_VALUES:
                    baseBleAdapter = new BaseBelAdapter(mContext, baseObjList);
                    // 绑定 Adapter到控件
                    spBaseBel.setAdapter(baseBleAdapter);
                    break;
                case GHOUSEEMP_ERROR:// 大棚用户列表请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GHOUSEEMP_SUCCESS:
                    String gHouseEmp_json = msg.obj.toString();
                    new Thread(new GreenHouseEmpAction(gHouseEmp_json)).start();// 大棚员工报文解析线程
                    break;
                case GHOUSEEMP_VALUES:
                    // 设置适配器
                    distriPersonAdapter = new DistriPersonAdapter(mContext,
                            gHouseUserEmpList);
                    // 绑定 Adapter到控件
                    spExecutor.setAdapter(distriPersonAdapter);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 新增农事任务请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskAddRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-22 下午3:18:55
     */
    class FarTaskAddRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id
                String tenantId = currTenantId;

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("tenantId", tenantId);
                map.put("gbaseId", baseBel);
                map.put("ghouseId", greHouBel);
                map.put("planId", planBel);
                map.put("taskName", taskName);
                map.put("farmingCategory", farType);
                map.put("worktaskCircle", execuFre);
                map.put("planDateStart", "2017-07-18");
                map.put("planTimeStart", "8");
                map.put("planDateEnd", "2017-07-18");
                map.put("planTimeEnd", "12");
                map.put("content", taskDesc);
                map.put("allotContent", distExp);
                map.put("userIds", executorBuilder.toString().trim());
                map.put("content", taskDesc);
                map.put("planDays", "8");

                Log.v(TAG, TAG + "新增农事任务请求");
                WapiUtilEx.fartaskaddedit(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = FARTASKADD_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = FARTASKADD_SUCCESS;
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
     * 农事任务复制请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskCopyRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26 下午5:22:07
     */
    class FarTaskCopyRequest extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("plantingTaskId", plantingTaskId);

                Log.v(TAG, TAG + "复制农事任务请求");
                WapiUtilEx.fartaskCopy(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = FARTASKADD_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = FARTASKADD_SUCCESS;
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

    // -------------------------------所属基地start-------------------------

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

//				String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
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

    // -------------------------------所属基地end-------------------------

    // ------------------------所属大棚start-----------------------------

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
                            gHouseBleAdapter = new GhosueBelAdapter(mContext, gHouseObjList);
                            // 绑定 Adapter到控件
                            spGhouseBel.setAdapter(gHouseBleAdapter);
                            spGhouseBel.setSelection(0);
                        }
                    }
                }
            }
        });
    }


    // ----------------------------所属大棚end------------------------------

    // ----------------------------所属计划start------------------------------

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

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);

        Log.v(TAG, TAG + "种植计划列表请求");
        HttpManager.getInstance().planschemelist(TAG, token, currTenantId, null, null, null, null, null, null, new HttpCallBack<JsonPlanSchemeObject>() {
            @Override
            public void onError(Throwable throwable) {

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
                            plantBleAdapter = new PlantBelAdapter(mContext, planschemeList);
                            // 绑定 Adapter到控件
                            spPlantBel.setAdapter(plantBleAdapter);
                        }
                    } else
                        TAUtils.toastMessage((Activity) mContext,
                                date.getMsg());
                }
            }
        });
    }

    // ----------------------所属计划end-------------------------

    // -------------------农事分类数据字典--------------------------------------

    /**
     * 农事分类数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: DataDictionaryRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-31 下午5:37:24
     */
    public void DataDictionaryRequest() {

        String key = "BP_FARMING_CATEGORY";
        Log.i(TAG, TAG + "农事分类数据字典请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(FarmingTaskAddActivity.this, true) {
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
                        datadicObjBeans = date.getObj();
                        dataDictionaryAdapter = new DataDictionaryAdapter(mContext,
                                datadicObjBeans);
                        spFarType.setAdapter(dataDictionaryAdapter);
                    }
                }
            }
        });
    }
    // -------------------农事分类end数据字典--------------------------------------

    // -------------------执行频率start数据字典--------------------------------------

    /**
     * 执行频率数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: ExecuFreRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-31 下午5:37:04
     */
    public void ExecuFreRequest() {

        String key = "SP_WORKTASK_CIRCLE";
        Log.i(TAG, TAG + "执行频率数据字典请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(FarmingTaskAddActivity.this, true) {
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
                        executFarObjBeans = date.getObj();
                        executFarAdapter = new DataDictionaryAdapter(mContext,
                                executFarObjBeans);
                        spExecuFre.setAdapter(executFarAdapter);
                    }
                }
            }
        });
    }

    // ------------------执行频率end--------------------

    // ------------------执行人start--------------------

    /**
     * 大棚员工列表
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreHouEmpRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午5:04:13
     */
    class GreHouEmpRequest extends Thread {

        private String gHouseId;

        public GreHouEmpRequest(String greenhouseId) {
            // TODO Auto-generated constructor stub
            this.gHouseId = greenhouseId;
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
                map.put("greenhouseId", gHouseId);

                Log.v(TAG, TAG + "大棚员工请求");
                WapiUtilEx.ghouseemp(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEEMP_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEEMP_SUCCESS;
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
     * 大棚员工请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseEmpAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午5:08:08
     */
    class GreenHouseEmpAction extends Thread {

        private String gHouseEmpJson;

        public GreenHouseEmpAction(String gHouseEmp_json) {
            // TODO Auto-generated constructor stub
            this.gHouseEmpJson = gHouseEmp_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(gHouseEmpJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonGreHouEmpObject gHouse_user_emp = gson.fromJson(
                        gHouseEmpJson, JsonGreHouEmpObject.class);

                if (!gHouse_user_emp.equals("") && gHouse_user_emp != null) {

                    if (gHouse_user_emp.isSuccess()) {// 成功

                        gHouseUserEmpList = gHouse_user_emp.getObj();

                        Message msg = Message.obtain();
                        msg.what = GHOUSEEMP_VALUES;
                        mHandler.sendMessage(msg);
                    } else
                        TAUtils.toastMessage((Activity) mContext,
                                gHouse_user_emp.getMsg());
                }
            } catch (JsonSyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }

    // ------------------执行人end--------------------
    StringBuilder executorBuilder = new StringBuilder();

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // TODO Auto-generated method stub
        switch (parent.getId()) {
            case R.id.sp_base_bel:
                baseBel = baseObjList.get(position).getId();// 基地id
                break;
            case R.id.sp_ghouse_bel:
                greHouBel = gHouseObjList.get(position).getId();// 大棚id
                if (greHouBel != null) {
                    new Thread(new GreHouEmpRequest(greHouBel)).start();// 大棚员工列表请求线程
                }
                break;
            case R.id.sp_plan_bel:
                planBel = planschemeList.get(position).getId();// 计划id
                break;
            case R.id.sp_execu_fre:
                execuFre = executFarObjBeans.get(position).getTypecode();// 执行频率
                break;
            case R.id.sp_far_type:
                farType = datadicObjBeans.get(position).getTypecode();// 农事分类
                break;
            case R.id.sp_executor:
                if (executorBuilder.indexOf(gHouseUserEmpList.get(position).getUserId()) >= 0) {//这里查找str中是否存在"xxx"字符串，如果存在则会返回大于等于0的数，如果不存在，则返回-1
                    TAUtils.toastMessage((Activity) mContext, "已存在");
                    break;
                }
                String userName = gHouseUserEmpList.get(position).getUserName();
                executorBuilder.append(gHouseUserEmpList.get(position).getUserId() + ",");
                etExecutor.setText(etExecutor.getText().toString().trim() + ","
                        + userName);// 执行人
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