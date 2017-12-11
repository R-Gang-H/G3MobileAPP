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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.app.itserv.adapters.BaseBelAdapter;
import com.app.itserv.adapters.DataDictionaryAdapter;
import com.app.itserv.adapters.FarTaskAssignerAdapter;
import com.app.itserv.adapters.FarTaskExecuteAdapter;
import com.app.itserv.adapters.GhosueBelAdapter;
import com.app.itserv.adapters.PlantBelAdapter;
import com.app.itserv.jparser.JsonBaseManagerObject;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonFarExecuteTaskObject;
import com.app.itserv.jparser.JsonFarExecuteTaskObject.AttributesBean;
import com.app.itserv.jparser.JsonFarExecuteTaskObject.ObjBean;
import com.app.itserv.jparser.JsonGreenhouseManagerObject;
import com.app.itserv.jparser.JsonPlanSchemeObject;
import com.app.itserv.jparser.JsonUserGetListObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.DateTimePickDialogUtil;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的农事任务
 *
 * @author Administrator
 */
public class FarmingTaskActivity extends Activity implements OnClickListener,
        OnItemClickListener, OnItemSelectedListener {

    private Context mContext;

    public static final String TAG = "MyFarmingTaskActivity";

    // 我的农事任务类表
    protected static final int MYFARTASK_ERROR = 1;
    protected static final int MYFARTASK_SUCCESS = 2;
    protected static final int MYFARTASK_VALUES = 3;

    private ListView farTaskItems;
    private FarTaskExecuteAdapter farTaskAdapter;
    private List<ObjBean> myFarTaskBeanList;

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

    private Spinner spTaskStatus;// 任务执行状态
    private List<JsonDataDictionaryObject.ObjBean> datadicList;
    private DataDictionaryAdapter dataDictionaryAdapter;

    // 分配人
    private Spinner spDistriPerson;// 分配人
    private List<JsonUserGetListObject.ObjBean> objbeanUserList;// 用户列表集合
    private FarTaskAssignerAdapter fartTaskAssignerAdapter;

    private ImageView ImgBack;

    private DateTimePickDialogUtil dateTimePicKDialog;// 时间选择器的Dialog
    private String initDateTime;// 时间选择器的Dialog

    // 查询
    private Boolean bleSelect = false;
    // 查询条件
    private String strbaseID;// 所属基地Id
    private String strhouseID;// 所属大棚Id
    private String strplanID;// 所属计划Id
    private String strcreateBy;// 分配人
    private String strworktaskDoStatus;// 执行状态
    private String strtaskname;// 任务名称
    private String strstartdistritime;// 分配开始时间
    private String strstopdistritime;// 分配结束时间

    private EditText executestatus;
    // private EditText executor;
    private EditText startdistritime;
    private EditText stopdistritime;
    private EditText taskname;
    private Button btnselect;
    private Button btnreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_task_lay);
        mContext = this;
        initView();
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        new Thread(new MyFarTaskRequest()).start();// 农事任务列表
        new Thread(new BaseManagerRequest()).start();// 基地列表请求线程
        GreenHouseListRequest();// 大棚列表请求线程
        PlanSchemeRequest();// 种植计划请求线程
        TaskStaticListRequest();// 任务状态数据字典
        UserManagerListRequest();// 分配人
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);// 返回
        ImgBack.setOnClickListener(this);
        // 创建时间选择器
        initDateTime = DateTimePickDialogUtil.formatDate();
        dateTimePicKDialog = new DateTimePickDialogUtil(this, initDateTime);
        farTaskItems = (ListView) findViewById(R.id.far_task_items);// 刷新列表
        farTaskItems.setOnItemClickListener(this);
        spBaseBel = (Spinner) findViewById(R.id.far_task_base_bel);// 基地
        spBaseBel.setOnItemSelectedListener(this);
        spDistriPerson = (Spinner) findViewById(R.id.far_task_distri_person);// 分配人
        spDistriPerson.setOnItemSelectedListener(this);
        spTaskStatus = (Spinner) findViewById(R.id.far_task_execute_status);// 执行状态
        spTaskStatus.setOnItemSelectedListener(this);
        // executor = (EditText) findViewById(R.id.et_far_task_executor);// 执行人
        spGhouseBel = (Spinner) findViewById(R.id.far_task_greenhouse);// 大棚
        spGhouseBel.setOnItemSelectedListener(this);
        spPlantBel = (Spinner) findViewById(R.id.far_task_plan);// 所属计划
        spPlantBel.setOnItemSelectedListener(this);
        startdistritime = (EditText) findViewById(R.id.et_far_task_start_distri_time);// 分配开始时间
        startdistritime.setOnClickListener(this);
        stopdistritime = (EditText) findViewById(R.id.et_far_task_stop_distri_time);// 分配结束时间
        stopdistritime.setOnClickListener(this);
        taskname = (EditText) findViewById(R.id.et_far_task_task_name);// 任务名称
        btnselect = (Button) findViewById(R.id.btn_far_task_select);// 查询按钮
        btnselect.setOnClickListener(this);
        btnreset = (Button) findViewById(R.id.btn_far_task_changepsw_reset);// 重置按钮
        btnreset.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_far_task_changepsw_reset:// 重置按钮
                bleSelect = false;
                // 查询条件
                strbaseID = "";// 所属基地Id
                strhouseID = "";// 所属大棚Id
                strplanID = "";// 所属计划Id
                strcreateBy = "";// 分配人
                strworktaskDoStatus = "";// 执行状态
                strtaskname = "";// 任务名称
                strstartdistritime = "";// 分配开始时间
                strstopdistritime = "";// 分配结束时间

                taskname.getText().clear();// 任务名称
                startdistritime.getText().clear();// 分配开始时间
                stopdistritime.getText().clear();// 分配结束时间
                // 查询
                new Thread(new MyFarTaskRequest()).start();// 农事任务列表
                break;
            case R.id.btn_far_task_select:// 查询按钮
                bleSelect = true;
                strtaskname = taskname.getText().toString().trim();// 任务名称
                strstartdistritime = startdistritime.getText().toString().trim();// 分配开始时间
                strstopdistritime = stopdistritime.getText().toString().trim();// 分配结束时间
                // 查询
                new Thread(new MyFarTaskRequest()).start();// 农事任务列表
                break;
            case R.id.et_far_task_start_distri_time:// 分配开始时间
                dateTimePicKDialog.dateTimePicKDialog(startdistritime);
                break;
            case R.id.et_far_task_stop_distri_time:// 分配结束时间
                dateTimePicKDialog.dateTimePicKDialog(stopdistritime);
                break;
            default:
                break;
        }
    }

    /**
     * 所属基地、大棚、计划、分配人、执行状态的 选择监听
     */
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        switch (arg0.getId()) {
            case R.id.far_task_base_bel:// 所属基地
                strbaseID = baseObjList.get(arg2).getId();
                break;
            case R.id.far_task_greenhouse:// 所属大棚
                strhouseID = gHouseObjList.get(arg2).getId();
                break;
            case R.id.far_task_plan:// 所属计划
                strplanID = planschemeList.get(arg2).getId();
                break;
            case R.id.far_task_distri_person:// 分配人
                strcreateBy = objbeanUserList.get(arg2).getUserName();
                break;
            case R.id.far_task_execute_status:// 执行状态
                strworktaskDoStatus = datadicList.get(arg2).getTypecode();
                break;
            default:
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    /*
     * 我的农事任务条目点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        String execuTaskId = myFarTaskBeanList.get(position).getId();
        // 查看我的农事任务
//        startActivity(new Intent(mContext, FarmingTaskDetailsActivity.class)
//                .putExtra("farexecute", "far_task").putExtra("execuTaskId",
//                        execuTaskId));
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i(TAG, "onResume");
        init();
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MYFARTASK_ERROR:// 我的农事任务列表请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case MYFARTASK_SUCCESS:
                    String myfartask_json = msg.obj.toString();
                    new Thread(new MyFarTaskAction(myfartask_json)).start();// 我的农事任务列表请求解析线程
                    break;
                case MYFARTASK_VALUES:
                    // 设置适配器
                    farTaskAdapter = new FarTaskExecuteAdapter(mContext,
                            myFarTaskBeanList);
                    farTaskItems.setAdapter(farTaskAdapter);
                    break;

                case BASELIST_ERROR:// 请求失败--------------所属基地------------------
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
                default:
                    break;
            }
        }

    };

    // --------------------------我的农事任务列表查询--------执行任务列表--------------------------------------------

    /**
     * 我的农事任务列表请求线程 执行任务列表
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: MyFarTaskRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-21 上午9:22:41
     */
    class MyFarTaskRequest extends Thread {
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
                String headBy = PreferencesUtils.getString(mContext, "id");
                Log.i("bbbbbb", "headBy:" + headBy);// 402883fd5dbb8a9c015dbbc69aa40093
                // 对应yg1账号
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                if (bleSelect) {
                    map.put("baseId", strbaseID);// 所属基地Id
                    map.put("ghouseId", strhouseID);// 所属大棚Id
                    map.put("planId", strplanID);// 所属计划Id
                    map.put("taskName", strtaskname);// //任务名称
                    map.put("worktaskDoStatus", strworktaskDoStatus);// 执行状态
                    map.put("createBy", strcreateBy);// 分配人
                    map.put("allotdateStart", strstartdistritime);// 分配开始时间
                    map.put("allotdateEnd", strstopdistritime);// 分配结束时间
                }
                map.put("headBy", headBy);
                Log.v(TAG, TAG + "农事任务列表请求");
                WapiUtilEx.taskExecuteList(map, new MYCallBack() {
                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = MYFARTASK_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = MYFARTASK_SUCCESS;
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
     * 我的农事任务列表json解析线程 ---- 解析执行任务列表-----
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: MyFarTaskAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-21 上午9:32:56
     */
    class MyFarTaskAction extends Thread {
        private String myfartask_json;

        public MyFarTaskAction(String myfartask_json) {
            // TODO Auto-generated constructor stub
            this.myfartask_json = myfartask_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();
                if (TextUtils.isEmpty(myfartask_json)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }
                Gson gson = new Gson();
                JsonFarExecuteTaskObject myfar_task = gson.fromJson(
                        myfartask_json, JsonFarExecuteTaskObject.class);
                if (!myfar_task.equals("") && myfar_task != null) {

                    if (myfar_task.isSuccess()) {// 成功

                        myFarTaskBeanList = myfar_task.getObj();
                        Message msg = Message.obtain();
                        msg.what = MYFARTASK_VALUES;
                        mHandler.sendMessage(msg);
                    } else
                        TAUtils.toastMessage((Activity) mContext,
                                myfar_task.getMsg());
                }

            } catch (JsonSyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }

    // ---------------------所属基地--------------------------------------------

    /**
     * 基地列表请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BaseManagerRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-17 下午6:55:14
     */
    public class BaseManagerRequest extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();
                String token = PreferencesUtils.getString(mContext, "token");// token
//                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
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
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BaseManagerAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-17 下午7:01:12
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

    // ----------------------所属大棚------------------------------------------

    /**
     * 大棚列表请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-17 下午3:10:29
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
                                }
                            }
                        }
                    }
                });
    }

    // --------------------所属种植计划------------------------------

    /**
     * 种植计划请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: PlanSchemeRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-17 上午11:18:14
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
    // -------------------任务执行状态-------------------

    /**
     * 任务执行状态数据字典请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: TaskNameListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-29 下午3:30:58
     */
    public void TaskStaticListRequest() {

        String key = "SP_WORKTASK_STATUS";// 任务执行状态

        Log.v(TAG, TAG + "任务状态数据字典列表请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(FarmingTaskActivity.this, true) {
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
                        datadicList = date.getObj();
                        // 设置适配器
                        dataDictionaryAdapter = new DataDictionaryAdapter(mContext,
                                datadicList);
                        // 绑定 Adapter到控件
                        spTaskStatus.setAdapter(dataDictionaryAdapter);
                    }
                }
            }
        });
    }

    // ------------------------分配人--------------------------------------

    /**
     * @project name：yyshed
     * @type name：UserManagerRequest
     * @description：用户列表请求线程
     * @author：changyiqiang
     * @date time：2017-8-17 下午7:51:03
     */
    public void UserManagerListRequest() {

        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext,
                "tenantId");// 商户id
        Log.v(TAG, TAG + "商户下用户列表请求");
        HttpManager.getInstance().doUserGetList(TAG, TAG, token, new HttpCallBack<JsonUserGetListObject>(FarmingTaskActivity.this, true) {
                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtils.makeTextShort("请求失败!");
                        Log.e(TAG, throwable.toString());
                    }

                    @Override
                    public void onSuccess(JsonUserGetListObject date) {
                        Log.i(TAG, "date 用户列表-> " + date);

                        if (TextUtils.isEmpty(date.toString())) {
                            ToastUtils.makeTextShort("用户列表为空!");
                            return;
                        }

                        if (!date.equals("") && date != null) {
                            String msg = date.getMsg();// 提示消息
                            if (!date.isSuccess()) {// 请求用户列表失败
                                return;
                            }
                            objbeanUserList = date.getObj();// 用户列表集合

                            if (objbeanUserList != null) {
                                // 加载适配器
                                // 设置适配器
                                fartTaskAssignerAdapter = new FarTaskAssignerAdapter(mContext,
                                        objbeanUserList);
                                spDistriPerson.setAdapter(fartTaskAssignerAdapter);
                            }
                        }
                    }
                }
        );
    }
}
