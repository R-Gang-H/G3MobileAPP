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
import android.widget.TextView;

import com.app.itserv.adapters.BaseBelAdapter;
import com.app.itserv.adapters.DataDictionaryAdapter;
import com.app.itserv.adapters.FarTaskAssignerAdapter;
import com.app.itserv.adapters.FarTaskItemsAdapter;
import com.app.itserv.adapters.GhosueBelAdapter;
import com.app.itserv.adapters.PlantBelAdapter;
import com.app.itserv.jparser.JsonBaseManagerObject;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonGreenhouseManagerObject;
import com.app.itserv.jparser.JsonMyFarTaskObject;
import com.app.itserv.jparser.JsonMyFarTaskObject.ObjBean;
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
 * 农事管理---农事任务设置
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: FarmingTaskSettingActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-21 下午2:12:49
 */

public class FarmingTaskSettingActivity extends Activity implements
        OnClickListener, OnItemClickListener, OnItemSelectedListener {

    private Context mContext;

    public static final String TAG = "FarmingTaskSettingActivity";
    private DateTimePickDialogUtil dateTimePicKDialog;// 时间选择器的Dialog
    private String initDateTime;// 时间选择器的Dialog
    private ImageView ImgBack;

    // 农事任务
    protected static final int MYFARTASK_ERROR = 1;
    protected static final int MYFARTASK_SUCCESS = 2;
    protected static final int MYFARTASK_VALUES = 3;

    private EditText etTaskName;// 任务名称
    // private TaskNameAdapter taskNameAdapter;
    private ListView farTaskItems;
    private List<ObjBean> myFarTaskBeanList;
    private FarTaskItemsAdapter farTaskAdapter;

    // 所属基地
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

    private Spinner spTaskStatus;// 任务状态
    private List<JsonDataDictionaryObject.ObjBean> datadicList;
    private DataDictionaryAdapter dataDictionaryAdapter;

    // 分配人
//    private Spinner spDistriPerson;// 分配人
//    private List<JsonUserGetListObject.ObjBean> objbeanUserList;// 用户列表集合
//    private FarTaskAssignerAdapter fartTaskAssignerAdapter;

    private Button btnFarTaskAdd, btnSelect, btnReset;
    private TextView etStartDistriTime, etStopDistriTime;
    private Boolean bleSelect = false;

    private String baseId;// 所属基地id
    private String ghouseId;// 所属大棚id
    private String planId;// 所属计划id
    private String taskName;// 任务名称
    private String assignStatus;// 任务状态
    private String allotBy;// 分配人
    private String startTime;// 开始时间
    private String stopTime;// 结束时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_task_setting_lay);
        mContext = this;
        initView();
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        BaseManagerRequest();// 基地列表请求线程
        GreenHouseListRequest();// 大棚列表请求线程
        new Thread(new MyFarTaskRequest()).start();// 农事任务列表
        PlanSchemeRequest();// 种植计划请求线程
        TaskStaticListRequest();// 任务分配状态数据字典
        UserManagerListRequest();// 分配人
    }

    private void initView() {
        // TODO Auto-generated method stub
        // 初始化控件
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        // 创建时间选择器
        initDateTime = DateTimePickDialogUtil.formatDate();
        dateTimePicKDialog = new DateTimePickDialogUtil(this, initDateTime);
        farTaskItems = (ListView) findViewById(R.id.far_task_items);
        farTaskItems.setOnItemClickListener(this);

        spBaseBel = (Spinner) findViewById(R.id.sp_base_bel);// 所属基地
        spBaseBel.setOnItemSelectedListener(this);
        spGhouseBel = (Spinner) findViewById(R.id.sp_ghouse_bel);// 所属大棚
        spGhouseBel.setOnItemSelectedListener(this);
        spPlantBel = (Spinner) findViewById(R.id.sp_plan_bel);// 所属计划
        spPlantBel.setOnItemSelectedListener(this);
        etTaskName = (EditText) findViewById(R.id.et_task_name);// 任务名称
        spTaskStatus = (Spinner) findViewById(R.id.sp_task_status);// 任务状态
        spTaskStatus.setOnItemSelectedListener(this);
//        spDistriPerson = (Spinner) findViewById(R.id.sp_distri_person);// 分配人
//        spDistriPerson.setOnItemSelectedListener(this);
        etStartDistriTime = (TextView) findViewById(R.id.start_distri_time);// 开始时间
        etStartDistriTime.setOnClickListener(this);
        etStopDistriTime = (TextView) findViewById(R.id.stop_distri_time);// 结束时间
        etStopDistriTime.setOnClickListener(this);
        btnFarTaskAdd = (Button) findViewById(R.id.btn_far_task_add);// 农事添加
        btnFarTaskAdd.setOnClickListener(this);
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
            case R.id.btn_far_task_add:// 新增农事任务
                startActivity(new Intent(mContext, FarmingTaskAddActivity.class)
                        .putExtra("faraddcopy", "faradd"));
                break;
            case R.id.btn_select:// 查询
                bleSelect = true;
                taskName = etTaskName.getText().toString().trim();
                startTime = etStartDistriTime.getText().toString().trim();
                stopTime = etStopDistriTime.getText().toString().trim();
                new Thread(new MyFarTaskRequest()).start();// 农事任务列表
                break;
            case R.id.btn_reset:// 重置
                bleSelect = false;
                etTaskName.setText("");
                etStartDistriTime.setText("");
                etStopDistriTime.setText("");
                etStartDistriTime.setText("");
                etStopDistriTime.setText("");
                new Thread(new MyFarTaskRequest()).start();// 农事任务列表
                break;
            case R.id.start_distri_time:// 开始时间
                dateTimePicKDialog.dateTimePicKDialog(etStartDistriTime);
                break;
            case R.id.stop_distri_time:// 结束时间
                dateTimePicKDialog.dateTimePicKDialog(etStopDistriTime);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        init();
    }

    // --------------------------------------------------------------
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        String plantingTaskId = myFarTaskBeanList.get(position).getId();
        startActivity(new Intent(mContext, FarmingTaskEditActivity.class)
                .putExtra("farexecute", "far_edit").putExtra("plantingTaskId",
                        plantingTaskId));// 编辑农事任务
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        switch (parent.getId()) {
            case R.id.sp_base_bel:
                baseId = baseObjList.get(position).getId();// 基地id
                break;
            case R.id.sp_ghouse_bel:
                ghouseId = gHouseObjList.get(position).getId();// 大棚id
                break;
            case R.id.sp_plan_bel:
                planId = planschemeList.get(position).getId();// 计划id
                break;
            case R.id.sp_task_status:
                assignStatus = datadicList.get(position).getTypecode();// 任务状态
                break;
            case R.id.sp_distri_person:
//                allotBy = objbeanUserList.get(position).getUserName();// 分配人
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MYFARTASK_ERROR:// 农事任务列表请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case MYFARTASK_SUCCESS:
                    String myfartask_json = msg.obj.toString();
                    new Thread(new MyFarTaskAction(myfartask_json)).start();// 农事任务列表请求解析线程
                    break;
                case MYFARTASK_VALUES:
                    // 设置适配器
                    farTaskAdapter = new FarTaskItemsAdapter(mContext,
                            myFarTaskBeanList);
                    farTaskItems.setAdapter(farTaskAdapter);
                    break;
                default:
                    break;
            }
        }
    };

    // --------------------农事任务列表-----------------------------

    /**
     * 农事任务列表请求线程
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

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                if (bleSelect) {
                    map.put("baseId", baseId);
                    map.put("ghouseId", ghouseId);
                    map.put("planId", planId);
                    map.put("taskName", "*" + taskName + "*");//支持模糊查询
                    map.put("assignStatus", assignStatus);
                    map.put("allotBy", allotBy);
                    map.put("allotDateBegin", startTime);
                    map.put("allotDateEnd", stopTime);
                }
                WapiUtilEx.fartaskList(map, new MYCallBack() {

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
     * 农事任务列表json解析线程
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
                JsonMyFarTaskObject myfar_task = gson.fromJson(myfartask_json,
                        JsonMyFarTaskObject.class);

                if (!myfar_task.equals("") && myfar_task != null) {
                    if (myfar_task.isSuccess()) {// 成功

                        myFarTaskBeanList = myfar_task.getObj();
                        Message msg = Message.obtain();
                        msg.what = MYFARTASK_VALUES;
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

    // --------------------所属基地-----------------------------

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
    public void BaseManagerRequest() {

        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext,
                "tenantId");// 商户id
        HttpManager.getInstance().basemanagerlist(TAG, token, currTenantId, new HttpCallBack<JsonBaseManagerObject>() {
            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onSuccess(JsonBaseManagerObject date) {
                if (!date.equals("") && date != null) {
                    if (date.isSuccess()) {// 成功
                        baseObjList = date.getObj();
                        if (baseObjList != null) {
                            JsonBaseManagerObject.ObjBean bean = new JsonBaseManagerObject.ObjBean();
                            bean.setId("");
                            bean.setBaseFullname("全部");
                            baseObjList.add(0, bean);
                            baseBleAdapter = new BaseBelAdapter(mContext, baseObjList);
                            // 绑定 Adapter到控件
                            spBaseBel.setAdapter(baseBleAdapter);
                        }
                    }
                }
            }
        });
    }


    // --------------------所属大棚-----------------------------

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
                            spGhouseBel.setSelection(0);
                        }
                    }
                }
            }
        });
    }


    // --------------------所属计划-----------------------------

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
                            JsonPlanSchemeObject.ObjBean bean = new JsonPlanSchemeObject.ObjBean();
                            bean.setId("");
                            bean.setPlanFullname("全部");
                            planschemeList.add(0, bean);
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

    // -------------------任务分配状态start-------------------

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

        String key = "SP_WORKTASK_ASS_STATUS";// 任务分配状态
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(FarmingTaskSettingActivity.this, true) {
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
                        JsonDataDictionaryObject.ObjBean bean = new JsonDataDictionaryObject.ObjBean();
                        bean.setId("");
                        bean.setTypename("全部");
                        datadicList = date.getObj();
                        datadicList.add(0, bean);
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

        String token = PreferencesUtils.getString(FarmingTaskSettingActivity.this, "token");// token
        String currTenantId = PreferencesUtils.getString(FarmingTaskSettingActivity.this,
                "tenantId");// 商户id
        HttpManager.getInstance().doUserGetList(TAG, TAG, token, new HttpCallBack<JsonUserGetListObject>(FarmingTaskSettingActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onSuccess(JsonUserGetListObject date) {
                if (TextUtils.isEmpty(date.toString())) {
                    ToastUtils.makeTextShort("用户列表为空!");
                    return;
                }

                if (!date.equals("") && date != null) {
                    String msg = date.getMsg();// 提示消息
                    if (!date.isSuccess()) {// 请求用户列表失败
                        return;
                    }
//                    objbeanUserList = date.getObj();// 用户列表集合
//                    JsonUserGetListObject.ObjBean objBean = new JsonUserGetListObject.ObjBean();
//                    objBean.setId("");
//                    objBean.setUserName("全部");
//                    objbeanUserList.add(0, objBean);
//                    if (objbeanUserList != null) {
//                        // 设置适配器
//                        fartTaskAssignerAdapter = new FarTaskAssignerAdapter(mContext,
//                                objbeanUserList);
//                        spDistriPerson.setAdapter(fartTaskAssignerAdapter);
//                    }
                }
            }
        });
    }
}
