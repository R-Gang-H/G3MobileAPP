package com.app.itserv.activity;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.app.itserv.adapters.BaseBelAdapter;
import com.app.itserv.adapters.DataDictionaryAdapter;
import com.app.itserv.adapters.DistriPersonAdapter;
import com.app.itserv.adapters.FarTaskExecuteAdapter;
import com.app.itserv.adapters.GhosueBelAdapter;
import com.app.itserv.adapters.PlantBelAdapter;
import com.app.itserv.jparser.JsonBaseManagerObject;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonFarExecuteTaskObject;
import com.app.itserv.jparser.JsonFarExecuteTaskObject.AttributesBean;
import com.app.itserv.jparser.JsonFarExecuteTaskObject.ObjBean;
import com.app.itserv.jparser.JsonGreHouEmpObject;
import com.app.itserv.jparser.JsonGreenhouseManagerObject;
import com.app.itserv.jparser.JsonPlanSchemeObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 执行任务管理
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: FarmingExecuteManagerActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-26 下午6:16:46
 */
public class FarmingExecuteManagerActivity extends Activity implements
        OnClickListener, OnItemClickListener, OnItemSelectedListener {

    private static final String TAG = "FarmingExecuteManagerActivity";

    protected static final int EXECUTETASK_ERROR = 1;
    protected static final int EXECUTETASK_SUCCESS = 2;
    protected static final int EXECUTETASK_VALUES = 3;

    private Context mContext;

    private ImageView ImgBack;
    private Button btnSelect, btnReset;
    private EditText etTaskName, etStartDistriTime, etStopDistriTime;
    private Boolean bleSelect = false;
    private String taskName;// 任务名称
    private String personName;// 分配人
    private String executorName;// 执行人
    private String startTime;// 开始时间
    private String stopTime;// 结束时间

    private ListView farExecTaskItems;
    private FarTaskExecuteAdapter farTaskExeAdapter;
    private List<ObjBean> execuTaskBeanList;

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

    // 执行人
    protected static final int GHOUSEEMP_ERROR = 10;
    protected static final int GHOUSEEMP_SUCCESS = 11;
    protected static final int GHOUSEEMP_VALUES = 12;

    private Spinner spExecutor;// 执行人
    private List<JsonGreHouEmpObject.ObjBean> executorList;
    private DistriPersonAdapter executorAdapter;

    // 所属计划
    private Spinner spPlantBel;// 所属计划
    private List<JsonPlanSchemeObject.ObjBean> planschemeList;
    private PlantBelAdapter plantBleAdapter;

    private Spinner spTaskStatus;// 任务状态
    private List<JsonDataDictionaryObject.ObjBean> taskStatusList;
    private DataDictionaryAdapter taskStatusAdapter;

    // 分配人
    protected static final int DistriPerson_ERROR = 19;
    protected static final int DistriPerson_SUCCESS = 20;
    protected static final int DistriPerson_VALUES = 21;

    private Spinner spDistriPerson;// 分配人
    private List<JsonGreHouEmpObject.ObjBean> distriPersonList;
    private DistriPersonAdapter distriPersonAdapter;

    private String baseBel, greHouBel, planBel, execuFre, startDistr,
            stopDistr, farType, taskDesc;
    private String assignStatus;// 任务状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_execute_lay);
        mContext = this;
        initView();
        init();

    }

    private void init() {
        // TODO Auto-generated method stub
        new Thread(new FarExecuteTaskRequest()).start();// 执行任务请求线程
        new Thread(new BaseManagerRequest()).start();// 基地列表请求线程
        GreenHouseListRequest();// 大棚列表请求线程
        PlanSchemeRequest();// 种植计划请求线程
        TaskStaticListRequest();// 任务状态数据字典
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);

        farExecTaskItems = (ListView) findViewById(R.id.far_execute_items);
        farExecTaskItems.setOnItemClickListener(this);

        spBaseBel = (Spinner) findViewById(R.id.sp_base_bel);// 所属基地
        spBaseBel.setOnItemSelectedListener(this);
        spGhouseBel = (Spinner) findViewById(R.id.sp_ghouse_bel);// 所属大棚
        spGhouseBel.setOnItemSelectedListener(this);
        spExecutor = (Spinner) findViewById(R.id.sp_executor);// 执行人
        spExecutor.setOnItemSelectedListener(this);
        spPlantBel = (Spinner) findViewById(R.id.sp_plant_bel);// 所属计划
        spPlantBel.setOnItemSelectedListener(this);
        etTaskName = (EditText) findViewById(R.id.task_name);// 任务名称
        etStartDistriTime = (EditText) findViewById(R.id.start_distri_time);// 开始时间
        etStopDistriTime = (EditText) findViewById(R.id.stop_distri_time);// 结束时间
        spTaskStatus = (Spinner) findViewById(R.id.sp_task_status);// 执行状态
        spTaskStatus.setOnItemSelectedListener(this);
        spDistriPerson = (Spinner) findViewById(R.id.sp_distri_person);// 分配人
        spDistriPerson.setOnItemSelectedListener(this);

        btnSelect = (Button) findViewById(R.id.btn_select);// 查询
        btnSelect.setOnClickListener(this);
        btnReset = (Button) findViewById(R.id.btn_reset);// 重置
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_select:// 查询
                bleSelect = true;
                taskName = etTaskName.getText().toString().trim();
                startTime = etStartDistriTime.getText().toString().trim();
                stopTime = etStopDistriTime.getText().toString().trim();

                new Thread(new FarExecuteTaskRequest()).start();// 执行任务请求线程
                break;
            case R.id.btn_reset:// 重置

                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i(TAG, "onResume");
        init();
    }

    /**
     * 执行任务json解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarExecuteTaskAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26 下午7:11:44
     */
    class FarExecuteTaskAction extends Thread {

        private String executeTaskJson;

        public FarExecuteTaskAction(String executetask_json) {
            // TODO Auto-generated constructor stub
            this.executeTaskJson = executetask_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(executeTaskJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonFarExecuteTaskObject execute_task = gson.fromJson(
                        executeTaskJson, JsonFarExecuteTaskObject.class);

                if (!execute_task.equals("") && execute_task != null) {

                    if (execute_task.isSuccess()) {// 成功
                        execuTaskBeanList = execute_task.getObj();
                        Message msg = Message.obtain();
                        msg.what = EXECUTETASK_VALUES;
                        mHandler.sendMessage(msg);
                    } else
                        TAUtils.toastMessage((Activity) mContext,
                                execute_task.getMsg());
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
                case EXECUTETASK_ERROR:// 执行任务列表请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case EXECUTETASK_SUCCESS:
                    String executetask_json = msg.obj.toString();
                    new Thread(new FarExecuteTaskAction(executetask_json)).start();// 农事任务列表请求解析线程
                    break;
                case EXECUTETASK_VALUES:
                    // 设置适配器
                    farTaskExeAdapter = new FarTaskExecuteAdapter(mContext,
                            execuTaskBeanList);
                    farExecTaskItems.setAdapter(farTaskExeAdapter);
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
                    executorAdapter = new DistriPersonAdapter(mContext,
                            executorList);
                    // 绑定 Adapter到控件
                    spExecutor.setAdapter(executorAdapter);
                    distriPersonAdapter = new DistriPersonAdapter(mContext,
                            distriPersonList);
                    // 绑定 Adapter到控件
                    spDistriPerson.setAdapter(distriPersonAdapter);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 执行任务列表请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarExecuteRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26 下午7:01:10
     */
    class FarExecuteTaskRequest extends Thread {

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
                if (bleSelect) {
                    map.put("baseId", baseBel);
                    map.put("ghouseId", greHouBel);
                    map.put("planId", planBel);
                    map.put("taskName", taskName);
                    map.put("worktaskDoStatus", assignStatus);
                    map.put("createBy", personName);
                    map.put("allotdateStart", startTime);
                    map.put("allotdateEnd", stopTime);
                    map.put("headBy", executorName);
                }
                WapiUtilEx.taskExecuteList(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = EXECUTETASK_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = EXECUTETASK_SUCCESS;
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
                            JsonGreenhouseManagerObject.ObjBean bean = new JsonGreenhouseManagerObject.ObjBean();
                            bean.setId("");
                            bean.setGhouseFullname("全部");
                            gHouseObjList.add(0, bean);
                            gHouseBleAdapter = new GhosueBelAdapter(mContext, gHouseObjList);
                            // 绑定 Adapter到控件
                            spGhouseBel.setAdapter(gHouseBleAdapter);
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

    // ----------------------------所属计划end------------------------------

    // -------------------任务状态start-------------------

    /**
     * 任务状态数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: TaskNameListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-29 下午3:30:58
     */
    public void TaskStaticListRequest() {

        String key = "SP_WORKTASK_STATUS";

        Log.v(TAG, TAG + "任务状态数据字典列表请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(FarmingExecuteManagerActivity.this, true) {
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
                        taskStatusList = date.getObj();
                        // 设置适配器
                        taskStatusAdapter = new DataDictionaryAdapter(mContext,
                                taskStatusList);
                        // 绑定 Adapter到控件
                        spTaskStatus.setAdapter(taskStatusAdapter);
                    }

                }
            }
        });
    }

    // -------------------任务状态end-------------------

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

                        executorList = gHouse_user_emp.getObj();// 执行人
                        distriPersonList = gHouse_user_emp.getObj();// 分配人

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        Log.i(TAG, "-------->" + position);
        String execuTaskId = execuTaskBeanList.get(position).getId();
        startActivity(new Intent(mContext, FarmingTaskEditActivity.class)
                .putExtra("farexecute", "execute_task").putExtra("execuTaskId",
                        execuTaskId));
    }

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
                new Thread(new GreHouEmpRequest(greHouBel)).start();// 大棚员工列表请求线程
                break;
            case R.id.sp_plant_bel:
                planBel = planschemeList.get(position).getId();// 计划id
                break;
            // case R.id.sp_execu_fre:
            // execuFre = executFarObjBeans.get(position).getTypecode();// 执行频率
            // break;
            // case R.id.sp_far_type:
            // farType = datadicObjBeans.get(position).getTypecode();// 农事分类
            // break;
            case R.id.sp_executor:
                executorName = executorList.get(position).getUserName();// 执行人
                break;
            case R.id.sp_distri_person:
                personName = distriPersonList.get(position).getUserName();// 分配人
                break;
            case R.id.sp_task_status:
                assignStatus = taskStatusList.get(position).getTypecode();// 任务状态
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
