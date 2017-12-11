package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.app.itserv.adapters.TaskExecuteHistoryAdapter;
import com.app.itserv.jparser.JsonTaskExecDetailObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.Map;

/**
 * 农事任务详情
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/9/9 17:37
 */
public class FarmingTaskDetailsActivity extends Activity {

    private static final String TAG = "FarmingTaskDetailsActivity";
    private Context mContext;

    private ImageView ImgBack;

    private String farexecute;//判断跳转页面

    protected static final int FARTASKEDIT_ERROR = 1;
    protected static final int EXECUDETAIL_SUCCESS = 2;
    protected static final int EXECUDETAIL_VALUES = 3;

    private String baseName;
    private String baseBel; //获得所属基地ID
    private String ghouseName;
    private String greHouBel; //获得所属大棚ID
    private String planName;
    private String planBel;//获得所属种植计划ID
    private String taskName;//任务名称
    private String execuFre;//执行频率
    private String planDays; //指定日
    private String plantimeStart; //开始时间
    private String plantimeEnd; //结束时间
    private String principal; //获得执行人 负责人ID
    private String farCategory;//任务执行状态
    private String gradeDesc;//评分描述

    private JsonTaskExecDetailObject.ObjBean taskExecDetailBeanList;// 执行任务明细列表
    private JsonTaskExecDetailObject.ObjBean.YyWorktaskEntityBean taskExecBean;//任务执行对象
    private JsonTaskExecDetailObject.ObjBean.YyPlantingTaskEntityBean plantingTaskEntityBean;//执行任务明细
    private TaskExecuteHistoryAdapter taskExecHistoryAdapter;//任务操作记录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_task_edit_lay);
        mContext = this;
        initView();
        Bundle intent = getIntent().getExtras();
        farexecute = intent.getString("farexecute");//判断是从 我的农事任务条目跳转过来还是 从农事任务设置的条目跳转过来

        if (farexecute.equals("far_task")) {// 农事任务执行人查看
            String execuTaskId = intent.getString("execuTaskId");
//            tvFarTaskTitle.setText("查看种植任务");
//            btnDraft.setVisibility(View.GONE);
//            btnReset.setVisibility(View.GONE);
//            btnDistribu.setVisibility(View.GONE);
//            btnCancel.setVisibility(View.GONE);
//            btnCopy.setVisibility(View.GONE);
//            btnClose.setVisibility(View.GONE);
//            llExecutePeople.setVisibility(View.VISIBLE);
            new Thread(new TaskExecDetailRequest(execuTaskId)).start();// 执行农事任务
        }
    }

    private void initView() {
    }


    //---------------------------查看执行任务明细-----------------------------------------

    /**
     * 查看执行任务明细请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskExecuteRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-27 下午3:51:16
     */
    class TaskExecDetailRequest extends Thread {

        private String execuTeskId;

        public TaskExecDetailRequest(String execuTaskId) {
            // TODO Auto-generated constructor stub
            this.execuTeskId = execuTaskId;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id

                Map<String, String> map = new HashMap<String, String>();
                map.put("currUserId", currUserId);
                map.put("currTenantId", currTenantId);
                map.put("workTaskId", execuTeskId);

                Log.v(TAG, TAG + "执行任务查看请求");
                WapiUtilEx.taskExecDetail(map, new MYCallBack() {

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
                        msg.what = EXECUDETAIL_SUCCESS;
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
     * 执行农事任务json报文解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: TaskExecDetailAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-27 下午4:36:49
     */
    class TaskExecDetailAction extends Thread {

        private String taskDetailJson;

        public TaskExecDetailAction(String taskdtail_json) {
            // TODO Auto-generated constructor stub
            this.taskDetailJson = taskdtail_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(taskDetailJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonTaskExecDetailObject execu_detail = gson
                        .fromJson(taskDetailJson,
                                JsonTaskExecDetailObject.class);

                if (!execu_detail.equals("") && execu_detail != null) {

                    if (execu_detail.isSuccess()) {// 成功

                        JsonTaskExecDetailObject.AttributesBean taskattributsave = execu_detail
                                .getAttributes();
                        taskattributsave.getCurrUserId();
                        taskattributsave.getCurrTenantId();

                        taskExecDetailBeanList = execu_detail.getObj();

                        Message msg = Message.obtain();
                        msg.what = EXECUDETAIL_VALUES;
                        mHandler.sendMessage(msg);
                    }else
                        TAUtils.toastMessage((Activity) mContext,
                                execu_detail.getMsg());
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

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EXECUDETAIL_SUCCESS:// 执行任务成功
                    String taskdetail_json = msg.obj.toString();
                    new Thread(new TaskExecDetailAction(taskdetail_json)).start();// 执行任务明细请求解析线程
                    break;
                case EXECUDETAIL_VALUES:// 执行任务明细-------------执行任务明细----------------
//                    taskExecBean = taskExecDetailBeanList.getYyWorktaskEntity();// 执行任务
//                    plantingTaskEntityBean = taskExecDetailBeanList.getYyPlantingTaskEntity();//执行任务明细
//                    //获得所属基地
//                    baseName = TextUtils.isEmpty(plantingTaskEntityBean.getBaseFullname()) ? ""
//                            : plantingTaskEntityBean.getBaseFullname();//获得基地名称
//                    baseBel = TextUtils.isEmpty(plantingTaskEntityBean.getBaseId()) ? "" : plantingTaskEntityBean.getBaseId();//获得基地ID
//                    //获得所属大棚
//                    ghouseName = TextUtils
//                            .isEmpty(plantingTaskEntityBean.getGhouseFullname()) ? ""
//                            : plantingTaskEntityBean.getGhouseFullname();//获得大棚名称
//                    greHouBel = TextUtils.isEmpty(plantingTaskEntityBean.getGhouseId()) ? "" : plantingTaskEntityBean.getGhouseId();//获得大棚ID
//                    //获得所属种植计划
//                    planName = TextUtils.isEmpty(plantingTaskEntityBean.getPlanFullname()) ? ""
//                            : plantingTaskEntityBean.getPlanFullname();//获得计划名称
//                    planBel = TextUtils.isEmpty(plantingTaskEntityBean.getPlanId()) ? "" : plantingTaskEntityBean.getPlanId();//获得计划ID
//                    //获得任务名称
//                    taskName = TextUtils.isEmpty(plantingTaskEntityBean.getTaskName()) ? ""
//                            : plantingTaskEntityBean.getTaskName();//获得任务名称
//                    //执行频率
//                    execuFre = TextUtils.isEmpty(plantingTaskEntityBean.getWorktaskCircle()) ? ""
//                            : plantingTaskEntityBean.getWorktaskCircle();//获得执行频率
//                    Log.i("执行频率：", execuFre + "执行频率");
//                    //指定日
//                    planDays = TextUtils.isEmpty((String) plantingTaskEntityBean.getPlanDays()) ? ""
//                            : (String) plantingTaskEntityBean.getPlanDays();//获得指定日
//                    execFrequency = "";
//                    //开始日期
//                    planDateStart = TextUtils.isEmpty(String.valueOf(plantingTaskEntityBean
//                            .getPlanDateStart())) ? "" : String.valueOf(plantingTaskEntityBean
//                            .getPlanDateStart());
//                    //结束日期
//                    planDateEnd = TextUtils.isEmpty(String.valueOf(plantingTaskEntityBean
//                            .getPlanDateEnd())) ? "" : String.valueOf(plantingTaskEntityBean
//                            .getPlanDateEnd());
//                    //开始时间
//                    plantimeStart = TextUtils.isEmpty(String.valueOf(plantingTaskEntityBean
//                            .getPlanTimeStart())) ? "" : String.valueOf(plantingTaskEntityBean
//                            .getPlanTimeStart());
//                    //结束时间
//                    plantimeEnd = TextUtils.isEmpty(String.valueOf(plantingTaskEntityBean
//                            .getPlanTimeEnd())) ? "" : String.valueOf(plantingTaskEntityBean
//                            .getPlanTimeEnd());
//                    //农事分类
//                    farType = TextUtils.isEmpty(plantingTaskEntityBean.getFarmingCategory()) ? "" : plantingTaskEntityBean.getFarmingCategory();//评分描述
//                    //任务描述
//                    taskDesc = TextUtils.isEmpty(plantingTaskEntityBean
//                            .getContent()) ? "" : plantingTaskEntityBean
//                            .getContent();//任务描述
//                    //获得执行人 负责人ID
//                    principal = TextUtils.isEmpty(String.valueOf(taskExecBean
//                            .getHeadBy())) ? "" : String
//                            .valueOf(taskExecBean.getHeadBy());//获得执行人
//
//                    //任务执行状态 assignStatus
//                    farCategory = TextUtils.isEmpty(String.valueOf(taskExecBean
//                            .getWorktaskDoStatus())) ? "" : String
//                            .valueOf(taskExecBean.getWorktaskDoStatus());//任务执行状态
//
//                    //评分描述
//                    gradeDesc = TextUtils.isEmpty(taskExecBean
//                            .getWorktaskCloseDesc()) ? "" : taskExecBean
//                            .getWorktaskCloseDesc();//评分描述


//                    setTaskDetailAdapter(baseName, ghouseName, planName, taskName,
//                            execFrequency, planDateStart, planDateEnd, farCategory,
//                            taskDesc, taskStatic);
//                    // 任务操作历史设置适配器
//                    taskExecHistoryAdapter = new TaskDetailHistoryAdapter(mContext,
//                            taskExecDetailBeanList.getYyPlantingTaskOpelogList());
//                    farTaskHistory.setAdapter(farTaskHistoryAdapter);
////                    String farRecordSize = TextUtils.isEmpty(String
////                            .valueOf(farTaskEditBeanList.getFramingRecordList()
////                                    .size())) ? "" : String
////                            .valueOf(farTaskEditBeanList.getFramingRecordList()
////                                    .size());
////                    tvFarRecordSize.setText(String
////                            .format("农事记录(%s)", farRecordSize));// 农事记录条数
////                    // 农事记录设置适配器
////                    taskExecRecorAdapter = new TaskExecuteRecordAdapter(mContext,
////                            farTaskEditBeanList.getFramingRecordList());
////                    farTaskRecor.setAdapter(taskExecRecorAdapter);
//
//                    if (farexecute.equals("far_task")) {
//                        new Thread(new BaseManagerRequest()).start();// 基地列表请求线程
//                        new Thread(new GreenHouseListRequest()).start();// 大棚列表请求线程
//                        new Thread(new PlanSchemeRequest()).start();// 种植计划请求线程
//                        new Thread(new ExecuFreRequest()).start();// 执行频率数据字典表请求线程
//                        new Thread(new DataDictionaryRequest()).start();// 农事分类数据字典表请求线程
//                        new Thread(new TaskStaticListRequest()).start();// 任务状态数据字典
//                    }

                    break;
            }
        }
    };
}
