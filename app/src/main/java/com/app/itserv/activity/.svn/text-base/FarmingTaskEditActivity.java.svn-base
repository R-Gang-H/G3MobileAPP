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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.itserv.adapters.BaseBelAdapter;
import com.app.itserv.adapters.DataDictionaryAdapter;
import com.app.itserv.adapters.DistriPersonAdapter;
import com.app.itserv.adapters.FarTaskHistoryAdapter;
import com.app.itserv.adapters.FarTaskRecordAdapter;
import com.app.itserv.adapters.GhosueBelAdapter;
import com.app.itserv.adapters.PlantBelAdapter;
import com.app.itserv.adapters.TaskExecuteRecordAdapter;
import com.app.itserv.jparser.JsonBaseManagerObject;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonFarTaskEditObject;
import com.app.itserv.jparser.JsonFarTaskEditObject.ObjBean;
import com.app.itserv.jparser.JsonFarTaskEditObject.ObjBean.PlantingTaskBean;
import com.app.itserv.jparser.JsonFarTaskSaveObject;
import com.app.itserv.jparser.JsonGreHouEmpObject;
import com.app.itserv.jparser.JsonGreenhouseManagerObject;
import com.app.itserv.jparser.JsonPlanSchemeObject;
import com.app.itserv.jparser.JsonTodayTaskObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import org.apache.http.impl.cookie.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编辑农事任务 （1.创建人可以保存、分配、取消、复制、关闭） （2.其他人只能查看）
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: FarmingTaskEditActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-24 下午8:05:02
 */
public class FarmingTaskEditActivity extends Activity implements
        OnClickListener, OnItemSelectedListener {
    private static final String TAG = "FarmingTaskEditActivity";

    protected static final int FARTASKEDIT_ERROR = 1;
    protected static final int FARTASKEDIT_SUCCESS = 2;
    protected static final int FARTASKEDIT_VALUES = 3;
    protected static final int FARTASKSAVE_SUCCESS = 4;

    private Context mContext;

    private ImageView ImgBack;

    private TextView tvFarTaskTitle, tvFarRecordSize, startDistriTime, stopDistriTime;
    private EditText etTaskName, taskDescribe;
    private Button btnDraft, btnReset, btnDistribu, btnCancel, btnClose,
            btnCopy;
    private LinearLayout llExecutePeople;
    private ListView farTaskHistory;

    private FarTaskHistoryAdapter farTaskHistoryAdapter;
    private ListView farTaskRecor;
    private FarTaskRecordAdapter farTaskRecorAdapter;
    private TaskExecuteRecordAdapter taskExecRecorAdapter;
    private Intent intents;
    private ObjBean farTaskEditBeanList;// 农事任务编辑列表

    private PlantingTaskBean plantaskBean;
    private String plantingTaskId;

    private String farexecute;//判断跳转页面

    // 所属基地
    protected static final int BASELIST_ERROR = 7;
    protected static final int BASELIST_SUCCESS = 8;
    protected static final int BASELIST_VALUES = 9;
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

    private Spinner spExecuFre;// 执行频率

    private List<JsonDataDictionaryObject.ObjBean> executFarObjBeans;
    private DataDictionaryAdapter executFarAdapter;
    // 农事分类数据字典
    private Spinner spFarType;

    private List<JsonDataDictionaryObject.ObjBean> datadicObjBeans;
    private DataDictionaryAdapter dataDictionaryAdapter;
    // 任务状态
    private TextView tvTaskStatus;// 任务状态

    private List<JsonDataDictionaryObject.ObjBean> taskStatusList;
    // 执行人
    protected static final int GHOUSEEMP_ERROR = 24;

    protected static final int GHOUSEEMP_SUCCESS = 25;
    protected static final int GHOUSEEMP_VALUES = 26;
    private Spinner spExecutor;// 执行人

    private List<JsonGreHouEmpObject.ObjBean> gHouseUserEmpList;
    private DistriPersonAdapter distriPersonAdapter;

    private String baseBel; //获得所属基地ID
    private String baseName;
    private String greHouBel; //获得所属大棚ID
    private String ghouseName;
    private String planBel;//获得所属种植计划ID
    private String planName;
    private String taskName;//任务名称
    private String execuFre;//执行频率

    private String farType; //农事分类
    private String taskDesc;//任务描述
    private String farCategory;//任务执行状态

    private String execFrequency;//执行频率（周期（一次性、每周、每月、每天））
    private String planDateStart;//开始日期
    private String planDateEnd;  //结束日期
    private String taskStatic;//分配状态
    private String planDays; //指定日
    private String plantimeStart; //开始时间
    private String plantimeEnd; //结束时间
    private String principal; //获得执行人 负责人ID
    private String gradeDesc;//评分描述

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_task_edit_lay);
        mContext = this;
        initView();
        Bundle intent = getIntent().getExtras();
        farexecute = intent.getString("farexecute");//判断是从 我的农事任务条目跳转过来还是 从农事任务设置的条目跳转过来
        plantingTaskId = intent.getString("plantingTaskId");
        JsonTodayTaskObject.ObjBean todayTaskBean = (JsonTodayTaskObject.ObjBean) intent.getSerializable("todayTaskBean");//当日任务对象

        if (farexecute.equals("far_edit")) {// 编辑农事任务
            tvFarTaskTitle.setText("编辑农事任务");
            llExecutePeople.setVisibility(View.GONE);
            new Thread(new FarTaskEditRequest(plantingTaskId)).start();// 编辑农事任务
        }
        btnDraft.setVisibility(View.GONE);//保存
        btnReset.setVisibility(View.GONE);//重置
        btnDistribu.setVisibility(View.GONE);//分配
        btnCancel.setVisibility(View.GONE);//取消
        btnCopy.setVisibility(View.GONE); //复制
        btnClose.setVisibility(View.GONE);//关闭


//        if (farexecute.equals("far_task")) {//当是从我的执行任务条目点击跳转过来时，设为不可点击
//        tvTaskStatus.setEnabled(false);
//        spBaseBel.setEnabled(false);
//        spGhouseBel.setEnabled(false);
//        spPlantBel.setEnabled(false);
//        spExecuFre.setEnabled(false);
//        spFarType.setEnabled(false);
//        spExecutor.setEnabled(false);
//        etTaskName.setEnabled(false);
//        taskDescribe.setEnabled(false);
//        } else {
        new Thread(new BaseManagerRequest()).start();// 基地列表请求线程
        GreenHouseListRequest();// 大棚列表请求线程
        PlanSchemeRequest();// 种植计划请求线程
        ExecuFreRequest();// 执行频率数据字典表请求线程
        DataDictionaryRequest();// 农事分类数据字典表请求线程
        TaskStaticListRequest();// 任务状态数据字典
//        }
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        tvFarTaskTitle = (TextView) findViewById(R.id.tv_far_task_title);// 标题
        tvFarRecordSize = (TextView) findViewById(R.id.tv_far_record_size);// 农事记录数
        llExecutePeople = (LinearLayout) findViewById(R.id.ll_execute_people);// 执行人

        spBaseBel = (Spinner) findViewById(R.id.sp_base_bel);// 所属基地
        spBaseBel.setOnItemSelectedListener(this);
        spGhouseBel = (Spinner) findViewById(R.id.sp_ghouse_bel);// 所属大棚
        spGhouseBel.setOnItemSelectedListener(this);
        spPlantBel = (Spinner) findViewById(R.id.sp_plan_bel);// 所属计划
        spPlantBel.setOnItemSelectedListener(this);
        etTaskName = (EditText) findViewById(R.id.ed_task_name);// 任务名称
        spExecuFre = (Spinner) findViewById(R.id.sp_execu_fre);// 执行频率
        spExecuFre.setOnItemSelectedListener(this);
        startDistriTime = (TextView) findViewById(R.id.start_distri_time);// 执行开始时间
        stopDistriTime = (TextView) findViewById(R.id.stop_distri_time);// 执行结束时间
        spFarType = (Spinner) findViewById(R.id.sp_far_type);// 农事分类
        spFarType.setOnItemSelectedListener(this);
        taskDescribe = (EditText) findViewById(R.id.task_describe);// 任务描述
        spExecutor = (Spinner) findViewById(R.id.sp_executor);// 执行人
        spExecutor.setOnItemSelectedListener(this);
        tvTaskStatus = (TextView) findViewById(R.id.tv_task_status);// 任务状态
        // spTaskStatus = (Spinner) findViewById(R.id.sp_task_status);// 任务状态
        // spTaskStatus.setOnItemSelectedListener(this);
        btnDraft = (Button) findViewById(R.id.btn_draft);// 保存按钮
        btnDraft.setOnClickListener(this);
        btnReset = (Button) findViewById(R.id.btn_reset);// 重置按钮
        btnReset.setOnClickListener(this);
        btnDistribu = (Button) findViewById(R.id.btn_distribu);// 分配按钮
        btnDistribu.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btn_cancel);// 取消按钮
        btnCancel.setOnClickListener(this);
        btnClose = (Button) findViewById(R.id.btn_close);// 关闭按钮
        btnClose.setOnClickListener(this);
        btnCopy = (Button) findViewById(R.id.btn_copy);// 复制按钮
        btnCopy.setOnClickListener(this);
        farTaskHistory = (ListView) findViewById(R.id.far_task_history);// 任务操作历史
        farTaskRecor = (ListView) findViewById(R.id.far_task_record);// 农事记录
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_draft:// 保存
                if (validator()) {
                    new Thread(new FarTaskSaveRequest()).start();// 农事任务编辑
                }
                break;
            case R.id.btn_distribu:// 分配按钮
                startActivity(new Intent(mContext, FarmingDistribuActivity.class)
                        .putExtra("plantaskBean", plantaskBean));
                finish();
                break;
            case R.id.btn_cancel:// 取消按钮
                intents = new Intent(mContext, FarmingCancelActivity.class);
                if (farexecute.equals("far_task")) {// 我的农事任务执行人查看
                } else if (farexecute.equals("far_edit")) {// 编辑农事任务
                    intents.putExtra("plantaskBean", plantaskBean).putExtra(
                            "farexecute", "far_edit");
                }
                startActivity(intents);
                finish();
                break;
            case R.id.btn_close:// 关闭按钮
                intents = new Intent(mContext, FarmingCloseActivit.class);
                if (farexecute.equals("far_task")) {// 我的农事任务执行人查看
                } else if (farexecute.equals("far_edit")) {// 编辑农事任务
                    intents.putExtra("plantaskBean", plantaskBean).putExtra(
                            "farexecute", "far_edit");
                }
                startActivity(intents);
                finish();
                break;
            case R.id.btn_copy:// 复制按钮
                startActivity(new Intent(mContext, FarmingTaskAddActivity.class)
                        .putExtra("faraddcopy", "farcopy").putExtra("plantaskBean",
                                plantaskBean));
                finish();
                break;
            case R.id.btn_collecting:// 领用
                startActivity(new Intent(mContext, FarmingCollectingActivity.class));
                break;
            case R.id.btn_record:// 农事填报
                startActivity(new Intent(mContext, FarmingExamingActivity.class)
                        .putExtra("farrecordsupply", "farsupply"));
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
     * @date 创建时间：2017-7-25 下午6:54:51
     */
    private boolean validator() {
        // TODO Auto-generated method stub
        return true;
    }


    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BASELIST_ERROR:// 请求失败----------所属基地---------------------
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

                    // 判断是1：我的执行任务查看 2：执行任务管理分配人查看 3： 编辑农事任务
                    if (farexecute.equals("far_task")) {// 我的执行任务查看

                        for (int i = 0; i < baseObjList.size(); i++) {
                            String strbaseId = baseObjList.get(i).getId();
                            if (baseBel.equals(strbaseId)) {
                                spBaseBel.setSelection(i);
                            }
                        }
                    }
                    break;
                case GHOUSEEMP_ERROR:// 大棚用户列表请求失败//----------执行人---------------------
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
                    // 判断是1：我的执行任务查看 2：执行任务管理分配人查看 3： 编辑农事任务
//                    if (farexecute.equals("far_task")) {// 我的执行任务查看

                    for (int i = 0; i < gHouseUserEmpList.size(); i++) {
                        String strusernameId = gHouseUserEmpList.get(i).getUserId();
                        Log.i("执行人循环：", "执行人ID：" + strusernameId + "执行人姓名：" + gHouseUserEmpList.get(i).getUserName());
                        Log.i("传递来的执行人ID：", principal);
                        if (principal.equals(strusernameId)) {
                            spExecutor.setSelection(i);
                        }
                    }
//                    }
                    break;
                case FARTASKEDIT_ERROR:// 农事任务编辑请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case FARTASKEDIT_SUCCESS://农事任务编辑请求成功
                    String fartaskedit_json = msg.obj.toString();
                    FarTaskEditAction(fartaskedit_json);// 农事任务编辑请求解析线程
                    break;
                case FARTASKEDIT_VALUES://农事任务编辑
                    plantaskBean = farTaskEditBeanList.getPlantingTask();// 农事任务
                    baseName = TextUtils.isEmpty(plantaskBean.getBaseFullname()) ? ""
                            : plantaskBean.getBaseFullname();
                    ghouseName = TextUtils
                            .isEmpty(plantaskBean.getGhouseFullname()) ? ""
                            : plantaskBean.getGhouseFullname();
                    planName = TextUtils.isEmpty(plantaskBean.getPlanFullname()) ? ""
                            : plantaskBean.getPlanFullname();
                    taskName = TextUtils.isEmpty(plantaskBean.getTaskName()) ? ""
                            : plantaskBean.getTaskName();
                    execFrequency = TextUtils.isEmpty(plantaskBean
                            .getWorktaskCircle()) ? "" : plantaskBean
                            .getWorktaskCircle();
                    planDateStart = TextUtils.isEmpty(String.valueOf(plantaskBean
                            .getPlanDateStart())) ? "" : String
                            .valueOf(plantaskBean.getPlanDateStart());
                    planDateEnd = TextUtils.isEmpty(String.valueOf(plantaskBean
                            .getPlanDateEnd())) ? "" : String.valueOf(plantaskBean
                            .getPlanDateEnd());
                    farCategory = TextUtils.isEmpty(String.valueOf(plantaskBean
                            .getFarmingCategory())) ? "" : String
                            .valueOf(plantaskBean.getFarmingCategory());
                    taskDesc = TextUtils.isEmpty(plantaskBean.getContent()) ? ""
                            : plantaskBean.getContent();
                    taskStatic = TextUtils.isEmpty(plantaskBean.getAssignStatus()) ? ""
                            : plantaskBean.getAssignStatus();
                    if (taskStatusList != null) {
                        for (int i = 0; i < taskStatusList.size(); i++) {
                            String strtaskstatusId = taskStatusList.get(i).getTypecode();
                            if (taskStatic.equals(strtaskstatusId)) {
                                tvTaskStatus.setText(taskStatusList.get(i).getTypename());
                            }
                        }
                    }
                    setTaskDetailAdapter(baseName, ghouseName, planName, taskName,
                            execFrequency, planDateStart, planDateEnd, farCategory,
                            taskDesc, taskStatic);
                    // 任务操作历史设置适配器
                    farTaskHistoryAdapter = new FarTaskHistoryAdapter(mContext,
                            farTaskEditBeanList.getPlantingTaskOpelogList());
                    farTaskHistory.setAdapter(farTaskHistoryAdapter);
                    tvFarRecordSize
                            .setText(String.format("农事记录(%s)", String
                                    .valueOf(farTaskEditBeanList.getWorktaskList()
                                            .size())));// 农事记录条数
                    // 农事记录设置适配器
                    farTaskRecorAdapter = new FarTaskRecordAdapter(mContext,
                            farTaskEditBeanList.getWorktaskList());
                    farTaskRecor.setAdapter(farTaskRecorAdapter);
                    break;
                case FARTASKSAVE_SUCCESS:// 保存成功
                    String fartasksave_json = msg.obj.toString();
                    new Thread(new FarTaskSaveAction(fartasksave_json)).start();// 农事任务保存请求解析线程
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 任务明细适配器赋值
     *
     * @param baseName
     * @param ghouseName
     * @param planName
     * @param taskName
     * @param execFrequency
     * @param planDateStart
     * @param planDateEnd
     * @param farCategory
     * @param taskDesc
     * @param taskStatic
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-27 下午5:09:11
     */
    private void setTaskDetailAdapter(String baseName, String ghouseName,
                                      String planName, String taskName, String execFrequency,
                                      String planDateStart, String planDateEnd, String farCategory,
                                      String taskDesc, String taskStatic) {
        // tvBaseBel.setText(baseName);// 所属基地
        // tvRoleBel.setText(ghouseName);// 所属大棚
        // tvPlanBel.setText(planName);// 所属计划
        etTaskName.setText(taskName);// 任务名称
        // tvFrequency.setText(execFrequency);// 执行频率
        startDistriTime.setText(DateLocalUtil.getDate(planDateStart));// 执行开始时间段
        stopDistriTime.setText(DateLocalUtil.getDate(planDateEnd));// 执行结束时间段
        // tvFarType.setText(farCategory);// 农事分类
        taskDescribe.setText(taskDesc);// 任务描述
//        tvTaskStatus.setText(taskStatic);// 任务状态
        if (farexecute.equals("far_edit")) {// 编辑农事任务
            String tatus = tvTaskStatus.getText().toString().trim();
            if (tatus.equals("未分配")) {
                btnDraft.setVisibility(View.VISIBLE);//保存
                btnReset.setVisibility(View.VISIBLE);//重置
                btnDistribu.setVisibility(View.VISIBLE);//分配
                btnCopy.setVisibility(View.VISIBLE);//复制
            } else if (tatus.equals("已分配")) {
                btnCancel.setVisibility(View.VISIBLE);//取消
                btnCopy.setVisibility(View.VISIBLE); //复制
                btnClose.setVisibility(View.VISIBLE);//关闭
            } else if (tatus.equals("已关闭")) {
                btnCopy.setVisibility(View.VISIBLE); //复制
            } else if (tatus.equals("已取消")) {
                btnCopy.setVisibility(View.VISIBLE); //复制
            }
        }
    }

//---------------------------编辑农事任务请求------------------------------------------------

    /**
     * 编辑农事任务请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskEditRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-24 下午7:56:59
     */
    class FarTaskEditRequest extends Thread {

        private String plantingTaskId;

        public FarTaskEditRequest(String plantingTaskId) {
            // TODO Auto-generated constructor stub
            this.plantingTaskId = plantingTaskId;
        }

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

                Log.v(TAG, TAG + "农事任务编辑请求");
                WapiUtilEx.fartaskDetail(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = FARTASKEDIT_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = FARTASKEDIT_SUCCESS;
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
     * 编辑农事任务json解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskEditAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-24 下午8:01:50
     */
    public void FarTaskEditAction(String fartaskEditJson) {

        if (TextUtils.isEmpty(fartaskEditJson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }

        Gson gson = new Gson();
        JsonFarTaskEditObject far_task_edit = gson.fromJson(
                fartaskEditJson, JsonFarTaskEditObject.class);

        if (!far_task_edit.equals("") && far_task_edit != null) {

            if (far_task_edit.isSuccess()) {// 成功

                farTaskEditBeanList = far_task_edit.getObj();

                Message msg = Message.obtain();
                msg.what = FARTASKEDIT_VALUES;
                mHandler.sendMessage(msg);
            } else
                TAUtils.toastMessage((Activity) mContext,
                        far_task_edit.getMsg());
        }
    }


    /**
     * 保存农事任务
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskSaveRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-25 下午6:57:33
     */
    class FarTaskSaveRequest extends Thread {

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
                String tenantId = currTenantId;

                Map<String, String> map = new HashMap<String, String>();

                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("plantingTaskId", plantingTaskId);
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

                Log.v(TAG, TAG + "农事任务保存请求");
                WapiUtilEx.fartaskSave(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = FARTASKEDIT_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = FARTASKSAVE_SUCCESS;
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
     * 农事任务保存(编辑)
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskSaveAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-25 下午7:35:41
     */
    class FarTaskSaveAction extends Thread {

        private String fartaskSaveJson;

        public FarTaskSaveAction(String fartasksave_json) {
            // TODO Auto-generated constructor stub
            this.fartaskSaveJson = fartasksave_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(fartaskSaveJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonFarTaskSaveObject far_task_save = gson
                        .fromJson(fartaskSaveJson, JsonFarTaskSaveObject.class);

                if (!far_task_save.equals("") && far_task_save != null) {

                    if (far_task_save.isSuccess()) {// 成功

                        JsonFarTaskSaveObject.AttributesBean taskattributsave = far_task_save
                                .getAttributes();
                        taskattributsave.getCurrUserId();
                        taskattributsave.getCurrTenantId();

                        // com.app.itserv.jparser.JsonFarTaskSaveObject.ObjBean
                        // farTaskSaveBeanList = far_task_save
                        // .getObj();

                        Message msg = Message.obtain();
                        msg.what = FARTASKEDIT_VALUES;
                        mHandler.sendMessage(msg);
                    } else
                        TAUtils.toastMessage((Activity) mContext,
                                far_task_save.getMsg());
                }
            } catch (JsonSyntaxException e) {
                // TODO Auto-generated catch block
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
        HttpManager.getInstance().

                ghousemanagerlist(TAG, token, currTenantId, new HttpCallBack<JsonGreenhouseManagerObject>() {
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
                                    // 判断是1：我的执行任务查看 2：执行任务管理分配人查看 3： 编辑农事任务
                                    if (farexecute.equals("far_task")) {// 我的执行任务查看

                                        for (int i = 0; i < gHouseObjList.size(); i++) {
                                            String strhousId = gHouseObjList.get(i).getId();
                                            if (greHouBel.equals(strhousId)) {
                                                spGhouseBel.setSelection(i);
                                            }
                                        }
                                    }
                                    //new Thread(new GreHouEmpRequest(greHouBel)).start();// 大棚员工列表请求线程
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
                            // 判断是1：我的执行任务查看 2：执行任务管理分配人查看 3： 编辑农事任务
                            if (farexecute.equals("far_task")) {// 我的执行任务查看
                                for (int i = 0; i < planschemeList.size(); i++) {
                                    String strplanId = planschemeList.get(i).getId();
                                    if (planBel.equals(strplanId)) {
                                        spPlantBel.setSelection(i);
                                    }
                                }
                            }
                        }
                    } else
                        TAUtils.toastMessage((Activity) mContext,
                                date.getMsg());
                }
            }
        });
    }

    // ----------------------------所属计划end------------------------------

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
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(FarmingTaskEditActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }

            @Override
            public void onSuccess(JsonDataDictionaryObject date) {
                Log.i(TAG, "response 执行频率数据字典请求-> " + date);
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
                        // 判断是1：我的执行任务查看 2：执行任务管理分配人查看 3： 编辑农事任务
                        if (farexecute.equals("far_task")) {// 我的执行任务查看

                            for (int i = 0; i < executFarObjBeans.size(); i++) {
                                String strfrequencyId = executFarObjBeans.get(i).getTypecode();
                                Log.i("频率循环：", strfrequencyId);
                                if (execuFre != null) {
                                    if (execuFre.equals(strfrequencyId)) {
                                        spExecuFre.setSelection(i);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    // ---------------------执行频率end--------------------------

    // -------------------农事分类start数据字典--------------------------------------

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
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(FarmingTaskEditActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }

            @Override
            public void onSuccess(JsonDataDictionaryObject date) {
                Log.i(TAG, "response 农事分类数据字典请求-> " + date);
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
                        // 判断是1：我的执行任务查看 2：执行任务管理分配人查看 3： 编辑农事任务
                        if (farexecute.equals("far_task")) {// 我的执行任务查看

                            for (int i = 0; i < datadicObjBeans.size(); i++) {
                                String strdicId = datadicObjBeans.get(i).getTypecode();
                                if (farType.equals(strdicId)) {
                                    spFarType.setSelection(i);
                                }
                            }
                        }
                    }
                }
            }
        });
    }


    // -------------------农事分类end数据字典--------------------------------------

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

        String key = "SP_WORKTASK_ASS_STATUS";//分配状态
        Log.v(TAG, TAG + "任务状态数据字典列表请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(FarmingTaskEditActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }

            @Override
            public void onSuccess(JsonDataDictionaryObject date) {
                Log.i(TAG, "response 农事分类数据字典请求-> " + date);
                if (TextUtils.isEmpty(date.toString())) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }
                if (!date.equals("")
                        && date != null) {
                    if (date.isSuccess()) {// 成功
                        taskStatusList = date.getObj();
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

//                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
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
//                new Thread(new GreHouEmpRequest(greHouBel)).start();// 大棚员工列表请求线程
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
                principal = gHouseUserEmpList.get(position).getUserId();// 执行人
                break;
            case R.id.sp_task_status:
//                taskStatic = taskStatusList.get(position).getTypecode();// 任务执行状态
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
