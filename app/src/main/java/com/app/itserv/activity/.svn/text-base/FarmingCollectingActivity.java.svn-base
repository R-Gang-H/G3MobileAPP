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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.jparser.JsonTodayTaskCollectObject;
import com.app.itserv.jparser.JsonTodayTaskDetailObject;
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
 * 当日农事任务领用
 *
 * @author Administrator
 */
public class FarmingCollectingActivity extends Activity implements
        OnClickListener {

    private static final String TAG = "FarmingCollectingActivity";

    private Context mContext;

    //每日任务领用
    private static final int TASKCOLLECTEDIT_ERROR = 1;
    private static final int TASKCOLLECTEDIT_SUCCESS = 2;
    private static final int TASKCOLLECTEDIT_VALUES = 3;

    private ImageView ImgBack;
    private TextView tvTaskName, tvDesc, tvFarType, tvExecutor;
    private EditText edCollecDesc;
    private Button btnSubmite;

    private JsonTodayTaskDetailObject.ObjBean.YyWorktaskDayBean yyWorktaskDay;//每日任务对象
    private JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskBean yyPlantingTask;//每日种植任务对象
    private String collecDesc;//任务描述

    private JsonTodayTaskCollectObject.ObjBean todayTaskCollectBean;//每日任务领用对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_collec_lay);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        yyWorktaskDay = (JsonTodayTaskDetailObject.ObjBean.YyWorktaskDayBean) bundle.getSerializable("yyWorktaskDay");//每日任务对象
        yyPlantingTask = (JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskBean) bundle.getSerializable("YyPlantingTask");//每日种植任务对象

        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);//返回
        ImgBack.setOnClickListener(this);
        tvTaskName = (TextView) findViewById(R.id.tv_task_name);//任务名称
        tvTaskName.setText(yyWorktaskDay.getTaskName());
        tvDesc = (TextView) findViewById(R.id.tv_desc);//任务描述
        tvDesc.setText(yyWorktaskDay.getDescription());
        tvFarType = (TextView) findViewById(R.id.tv_far_type);//农事分类
        String farClass = "";
        String farType = yyPlantingTask.getFarmingCategory();
        if (farType.equals("seed")) {
            farClass = "播种";
        } else if (farType.equals("irrigate")) {
            farClass = "灌溉";
        } else if (farType.equals("spray_insecticide")) {
            farClass = "打药";
        } else if (farType.equals("pluck")) {
            farClass = "采摘";
        } else if (farType.equals("other")) {
            farClass = "其他";
        } else if (farType.equals("air")) {
            farClass = "放风";
        } else if (farType.equals("weeding")) {
            farClass = "除草";
        } else if (farType.equals("truck_loading")) {
            farClass = "装车";
        }
        tvFarType.setText(farClass);// 农事分类
        tvExecutor = (TextView) findViewById(R.id.tv_executor);//执行人
        tvExecutor.setText(yyWorktaskDay.getHeadName());
        edCollecDesc = (EditText) findViewById(R.id.ed_collec_descri);//领用说明
        btnSubmite = (Button) findViewById(R.id.btn_submite);//提交
        btnSubmite.setOnClickListener(this);
    }

    private void init() {
        new Thread(new TodayTaskCollectRequest(yyWorktaskDay)).start();// 当日任务
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_submite://提交
                String collecDesc = edCollecDesc.getText().toString().trim();
                if (TextUtils.isEmpty(collecDesc)) {
                    TAUtils.toastMessage((Activity) mContext, "请填写领用描述!");
                    break;
                }
                init();
                break;
            default:
                break;
        }
    }

    //---------------------------每日任务领用请求------------------------------------------------

    /**
     * 每日任务领用请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: TodayTaskCollectRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017年09月07日09:39:36
     */
    class TodayTaskCollectRequest extends Thread {

        private JsonTodayTaskDetailObject.ObjBean.YyWorktaskDayBean taskDayBean;

        public TodayTaskCollectRequest(JsonTodayTaskDetailObject.ObjBean.YyWorktaskDayBean taskDayBean) {
            this.taskDayBean = taskDayBean;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                // String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id
                String workTaskDayId = taskDayBean.getId();

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("workTaskDayId", workTaskDayId);
                map.put("allotContent", collecDesc);

                Log.v(TAG, TAG + "每日任务领用请求");
                WapiUtilEx.todaytaskCollect(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = TASKCOLLECTEDIT_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = TASKCOLLECTEDIT_SUCCESS;
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
     * 每日任务领用json解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskEditAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-24 下午8:01:50
     */
    class TodayTaskCollectlAction extends Thread {

        private String todayCollectJson;

        public TodayTaskCollectlAction(String todayCollect_json) {
            // TODO Auto-generated constructor stub
            this.todayCollectJson = todayCollect_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(todayCollectJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonTodayTaskCollectObject today_task_collect = gson.fromJson(
                        todayCollectJson, JsonTodayTaskCollectObject.class);

                if (!today_task_collect.equals("") && today_task_collect != null) {

                    if (today_task_collect.isSuccess()) {// 成功

                        todayTaskCollectBean = today_task_collect.getObj();//每日任务领用数据
                        Message msg = Message.obtain();
                        msg.what = TASKCOLLECTEDIT_VALUES;
                        mHandler.sendMessage(msg);
                    }else
                        TAUtils.toastMessage((Activity) mContext,
                                today_task_collect.getMsg());
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
                case TASKCOLLECTEDIT_ERROR://每日任务领用请求错误
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case TASKCOLLECTEDIT_SUCCESS://每日任务领用请求成功
                    String todayCollect_json = msg.obj.toString();
                    new Thread(new TodayTaskCollectlAction(todayCollect_json)).start();
                    break;
                case TASKCOLLECTEDIT_VALUES://每日任务领用资源处理
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
}
