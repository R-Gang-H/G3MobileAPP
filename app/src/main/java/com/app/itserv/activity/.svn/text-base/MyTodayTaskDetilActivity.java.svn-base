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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.itserv.adapters.TaskExecuteHistoryAdapter;
import com.app.itserv.adapters.TaskExecuteRecordAdapter;
import com.app.itserv.jparser.JsonTodayTaskDetailObject;
import com.app.itserv.jparser.JsonTodayTaskObject;
import com.app.itserv.manager.DataWordQuery;
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

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 每日任务明细窗口
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/9/6 11:11
 */
public class MyTodayTaskDetilActivity extends Activity implements View.OnClickListener {

    private Context mContext;

    private static final String TAG = "MyTodayTaskDetilActivity";

    private String farexecute;//判断跳转的界面


    //每日任务
    private JsonTodayTaskDetailObject.ObjBean todayTaskDetailBeanList;//今日任务数据集合
    private JsonTodayTaskDetailObject.ObjBean.YyWorktaskBean yyWorktask;//工作任务对象
    private List<JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskOpelogListBean> yyPlantingTaskOpelogList;// 种植任务打开日志列表
    private JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskBean YyPlantingTask;//种植任务
    private List<JsonTodayTaskDetailObject.ObjBean.FramingRecordListBean> framingRecordList;//农事记录列表
    private JsonTodayTaskDetailObject.ObjBean.YyWorktaskDayBean yyWorktaskDay;//一天的工作任务

    private ImageView ImgBack;
    private TextView tvFarTaskTitle, tvBaseBel, tvGhouseBel, tvPlanBel, tvTaskName, tvExecuCycle,

    tvStartDistriDate, tvStopDistriDate, tvPlanDays, tvStartDistriTime, tvStopDistriTime, tvExecutor,
            tvFarType, tvAssignStatus, tvTaskDescribe, tvTaskStatus, tvFarRecordSize;
    private Button btnCollecting, btnScore;//领用任务,评分

    private ListView farTaskHistory;//任务操作日志
    private TaskExecuteHistoryAdapter taskExecHistoryAdapter;

    private ListView farTaskRecor;//农事记录
    private TaskExecuteRecordAdapter taskExecRecorAdapter;
    private JsonTodayTaskObject.ObjBean todayTaskBean;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_today_task_detil_lay);
        mContext = this;
        Bundle intent = getIntent().getExtras();
        farexecute = intent.getString("farexecute");//判断是从 我的农事任务条目跳转过来还是 从农事任务设置的条目跳转过来
        if (farexecute.equals("today")) {//当日任务
            todayTaskBean = (JsonTodayTaskObject.ObjBean) intent.getSerializable("todayTaskBean");//当日任务对象
//            tvFarTaskTitle.setText("今日任务");
            TodayTaskDetailRequest(todayTaskBean);// 当日任务
        }
        initView();
        init();
    }

    private void initView() {
        ImgBack = (ImageView) findViewById(R.id.img_back);//返回
        ImgBack.setOnClickListener(this);//返回点击事件
        tvFarTaskTitle = (TextView) findViewById(R.id.tv_far_task_title);// 标题
        tvBaseBel = (TextView) findViewById(R.id.tv_base_bel);//基地名称
        tvGhouseBel = (TextView) findViewById(R.id.tv_ghouse_bel);//大棚名称
        tvPlanBel = (TextView) findViewById(R.id.tv_plan_bel);//计划名称
        tvTaskName = (TextView) findViewById(R.id.tv_task_name);//任务名称
        tvExecuCycle = (TextView) findViewById(R.id.tv_execu_cycle);//执行周期
        tvStartDistriDate = (TextView) findViewById(R.id.start_distri_date);//开始日期
        tvStopDistriDate = (TextView) findViewById(R.id.stop_distri_date);//结束日期
        tvPlanDays = (TextView) findViewById(R.id.tv_plan_days);//执行日
        tvStartDistriTime = (TextView) findViewById(R.id.start_distri_time);//开始时间
        tvStopDistriTime = (TextView) findViewById(R.id.stop_distri_time);//结束时间
        tvExecutor = (TextView) findViewById(R.id.tv_executor);//执行人
        tvFarType = (TextView) findViewById(R.id.tv_far_type);//农事分类
        tvAssignStatus = (TextView) findViewById(R.id.tv_assign_status);//分配状态
        tvTaskDescribe = (TextView) findViewById(R.id.tv_task_desc);//任务描述
        tvTaskStatus = (TextView) findViewById(R.id.tv_task_status);//任务状态
        btnCollecting = (Button) findViewById(R.id.btn_collecting);// 领用按钮
        btnScore = (Button) findViewById(R.id.btn_score);//评分
        if (todayTaskBean.getWorktaskOperateStatus().toString().trim().equals("UN_ACCEPTED")) {//如果是（未领用）
            btnCollecting.setVisibility(View.VISIBLE);//显示领用按钮
            btnCollecting.setOnClickListener(this);//领用点击事件
            btnScore.setVisibility(View.GONE);//隐藏评分按钮
        } else if (todayTaskBean.getWorktaskOperateStatus().toString().trim().equals("CLOSED")) {//如果是（已关闭）
            if (todayTaskBean.getCreateBy().equals(PreferencesUtils.getString(mContext, "id").toString())) {//如果当前登录用户为创建人可以关闭并进行评分
                btnScore.setVisibility(View.VISIBLE);//显示评分按钮
                btnScore.setOnClickListener(this);//领用点击事件
                btnCollecting.setVisibility(View.GONE);//隐藏领用按钮
            }
        } else if (todayTaskBean.getWorktaskOperateStatus().toString().trim().equals("CANCELED")) {//如果是（已取消）
            if (todayTaskBean.getCreateBy().equals(PreferencesUtils.getString(mContext, "id").toString())) {
                btnScore.setVisibility(View.GONE);//显示评分按钮
                btnCollecting.setVisibility(View.GONE);//隐藏领用按钮
            }
        }
        farTaskHistory = (ListView) findViewById(R.id.far_task_history);// 任务操作历史
        farTaskRecor = (ListView) findViewById(R.id.far_task_record);// 农事记录
        tvFarRecordSize = (TextView) findViewById(R.id.tv_far_record_size);// 农事记录数
    }

    private void init() {
    }


    /**
     * 赋值
     *
     * @param baseName
     * @param ghouseName
     * @param planName
     * @param taskName
     * @param execuCycle
     * @param planDateStart
     * @param planDateEnd
     * @param planDays
     * @param plantimeStart
     * @param plantimeEnd
     * @param farType
     * @param assignStatus
     * @param taskDesc
     * @param tastExecuStatus
     * @param executor
     */
    private void setTaskDetailAdapter(String baseName, String ghouseName, String planName, String taskName, String execuCycle, String planDateStart,
                                      String planDateEnd, String planDays, String plantimeStart, String plantimeEnd, String executor, String farType,
                                      String assignStatus, String taskDesc, String tastExecuStatus) {
        tvBaseBel.setText(baseName);// 基地名称
        tvGhouseBel.setText(ghouseName);// 大棚名称
        tvPlanBel.setText(planName);// 计划名称
        tvTaskName.setText(taskName);// 任务名称
        tvExecuCycle.setText(DataWordQuery.getInstance().getexecuCycle(execuCycle));//执行周期
        tvStartDistriDate.setText(DateLocalUtil.getDate(planDateStart));// 执行开始日期段
        tvStopDistriDate.setText(DateLocalUtil.getDate(planDateEnd));// 执行结束日期段

        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String planDaysby = sdf.format(new java.util.Date());
        tvPlanDays.setText(planDaysby);//执行日
        tvStartDistriTime.setText(plantimeStart + ":00");// 执行开始时间段
        tvStopDistriTime.setText(plantimeEnd + ":00");// 执行结束时间段
        tvExecutor.setText(executor);//执行人
        tvFarType.setText(DataWordQuery.getInstance().getfarType(farType));// 农事分类
        tvAssignStatus.setText(DataWordQuery.getInstance().getassignStatus(assignStatus));//分配状态
        tvTaskDescribe.setText(taskDesc);// 任务描述
        tvTaskStatus.setText(DataWordQuery.getInstance().gettastExecuStatus(tastExecuStatus));// 任务状态
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_collecting:// 领用
                startActivity(new Intent(mContext, FarmingCollectingActivity.class).putExtra("yyWorktaskDay", yyWorktaskDay)
                        .putExtra("YyPlantingTask", YyPlantingTask));
                finish();
                break;
            case R.id.btn_score://评分
                startActivity(new Intent(mContext, FarmingCloseActivit.class).putExtra("todayTaskBean", todayTaskBean).
                        putExtra("YyPlantingTask", YyPlantingTask).putExtra("farexecute", "my_today_far_close"));
                break;
            default:
                break;
        }
    }


    //---------------------------每日任务明细请求------------------------------------------------

    /**
     * 每日任务明细请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskEditRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017年09月05日15:29:52
     */
    public void TodayTaskDetailRequest(JsonTodayTaskObject.ObjBean todayTaskBean) {

        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext,
                "tenantId");// 商户id
        String todayTaskId = todayTaskBean.getId();
        HttpManager.getInstance().todaytaskDetail(TAG, token, currTenantId, todayTaskId, new HttpCallBack<JsonTodayTaskDetailObject>(MyTodayTaskDetilActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onSuccess(JsonTodayTaskDetailObject date) {
                if (!date.equals("") && date != null) {
                    if (date.isSuccess()) {// 成功

                        todayTaskDetailBeanList = date.getObj();//今日任务数据
                        if (todayTaskDetailBeanList != null) {
                            yyWorktask = todayTaskDetailBeanList.getYyWorktask();//今日任务
                            yyPlantingTaskOpelogList = todayTaskDetailBeanList.getYyPlantingTaskOpelogList();// 种植任务打开日志列表
                            YyPlantingTask = todayTaskDetailBeanList.getYyPlantingTask();//种植任务
                            framingRecordList = todayTaskDetailBeanList.getFramingRecordList();//农事记录列表
                            yyWorktaskDay = todayTaskDetailBeanList.getYyWorktaskDay();//一天的工作任务

                            //获得所属基地
                            String baseName = TextUtils.isEmpty(YyPlantingTask.getBaseFullname()) ? ""
                                    : YyPlantingTask.getBaseFullname();//获得基地名称
                            String baseIdBel = TextUtils.isEmpty(YyPlantingTask.getBaseId()) ? "" : YyPlantingTask.getBaseId();//获得基地ID
                            //获得所属大棚
                            String ghouseName = TextUtils
                                    .isEmpty(YyPlantingTask.getGhouseFullname()) ? ""
                                    : YyPlantingTask.getGhouseFullname();//获得大棚名称
                            String greHouIdBel = TextUtils.isEmpty(YyPlantingTask.getGhouseId()) ? "" : YyPlantingTask.getGhouseId();//获得大棚ID
                            //获得所属种植计划
                            String planName = TextUtils.isEmpty(YyPlantingTask.getPlanFullname()) ? ""
                                    : YyPlantingTask.getPlanFullname();//获得计划名称
                            String planBel = TextUtils.isEmpty(YyPlantingTask.getPlanId()) ? "" : YyPlantingTask.getPlanId();//获得计划ID
                            //获得任务名称
                            String taskName = TextUtils.isEmpty(YyPlantingTask.getTaskName()) ? ""
                                    : YyPlantingTask.getTaskName();//获得任务名称
                            //执行周期
                            String execucycle = TextUtils.isEmpty(YyPlantingTask.getWorktaskCircle()) ? "" : YyPlantingTask.getWorktaskCircle();//执行周期
                            String planDateStart = TextUtils.isEmpty(String.valueOf(YyPlantingTask.getPlanDateStart())) ? ""
                                    : String.valueOf(YyPlantingTask.getPlanDateStart());//执行频率开始日期
                            String planDateEnd = TextUtils.isEmpty(String.valueOf(YyPlantingTask.getPlanDateEnd())) ? ""
                                    : String.valueOf(YyPlantingTask.getPlanDateEnd());//执行频率结束日期
//                    //指定日
                            String planDays = TextUtils.isEmpty((String) YyPlantingTask.getPlanDays()) ? ""
                                    : (String) YyPlantingTask.getPlanDays();//获得指定日
                            //开始时间
                            String plantimeStart = TextUtils.isEmpty(String.valueOf(YyPlantingTask
                                    .getPlanTimeStart())) ? "" : String.valueOf(YyPlantingTask
                                    .getPlanTimeStart());
                            //结束时间
                            String plantimeEnd = TextUtils.isEmpty(String.valueOf(YyPlantingTask
                                    .getPlanTimeEnd())) ? "" : String.valueOf(YyPlantingTask
                                    .getPlanTimeEnd());
                            //执行人
                            String executor = TextUtils.isEmpty(yyWorktaskDay.getHeadName()) ? "" : yyWorktaskDay.getHeadName();
                            //农事分类
                            String farType = TextUtils.isEmpty(YyPlantingTask.getFarmingCategory()) ? "" : YyPlantingTask.getFarmingCategory();
                            //分配状态
                            String assignStatus = TextUtils.isEmpty(YyPlantingTask.getAssignStatus()) ? "" : YyPlantingTask.getAssignStatus();
                            //任务描述
                            String taskDesc = TextUtils.isEmpty(YyPlantingTask.getContent()) ? "暂未描述" : YyPlantingTask.getContent();//任务描述
                            //任务执行状态
                            String tastExecuStatus = TextUtils.isEmpty(String.valueOf(yyWorktaskDay.getWorktaskDoStatus())) ? "" :
                                    String.valueOf(yyWorktaskDay.getWorktaskDoStatus());//任务执行状态

                            setTaskDetailAdapter(baseName, ghouseName, planName, taskName, execucycle, planDateStart, planDateEnd, planDays, plantimeStart,
                                    plantimeEnd, executor, farType, assignStatus, taskDesc, tastExecuStatus);
                            // 任务操作历史设置适配器
                            taskExecHistoryAdapter = new TaskExecuteHistoryAdapter(mContext, yyPlantingTaskOpelogList);
                            farTaskHistory.setAdapter(taskExecHistoryAdapter);
                            String farRecordSize = TextUtils.isEmpty(String.valueOf(framingRecordList.size())) ? "" :
                                    String.valueOf(framingRecordList.size());
                            tvFarRecordSize.setText(String
                                    .format("农事记录(%s)", farRecordSize));// 农事记录条数
                            //农事记录设置适配器
                            taskExecRecorAdapter = new TaskExecuteRecordAdapter(mContext, framingRecordList);
                            farTaskRecor.setAdapter(taskExecRecorAdapter);
                        }
                    }
                }
            }
        });
    }
}
