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

import com.app.itserv.jparser.JsonFarTaskDistrObject;
import com.app.itserv.jparser.JsonFarTaskDistrObject.AttributesBean;
import com.app.itserv.jparser.JsonFarTaskEditObject.ObjBean.PlantingTaskBean;
import com.app.itserv.jparser.JsonTaskExecDetailObject.ObjBean.YyWorktaskEntityBean;
import com.app.itserv.jparser.JsonTodayTaskDetailObject;
import com.app.itserv.jparser.JsonTodayTaskObject;
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
 * 取消农事任务
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: FarmingCancelActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-26 上午10:47:43
 */
public class FarmingCancelActivity extends Activity implements OnClickListener {

    private static final String TAG = "FarmingCancelActivity";

    protected static final int FARTASKCANCEL_ERROR = 1;
    protected static final int FARTASKCANCEL_SUCCESS = 2;
    protected static final int FARTASKCANCEL_VALUES = 3;

    private Context mContext;

    private ImageView ImgBack;
    private TextView tvTaskName, tvTaskDesc, tvFarType, tvExecutor;
    private EditText etCanExpl;
    private Button btnSubmite, btnChanReset;

    private PlantingTaskBean plantaskBean;
    private YyWorktaskEntityBean taskExecBean;
    private String plantingTaskId;
    private String executeTaskId;
    private String taskName, taskDesc, farType, exeCutor, canExpl;
    private String farexecute;

    private JsonTodayTaskObject.ObjBean todayTaskBean;//每日任务对象
    private JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskBean YyPlantingTask;//种植任务

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_cancel_lay);
        mContext = this;

        Bundle bundle = getIntent().getExtras();
        farexecute = (String) bundle.get("farexecute");
        if (farexecute.equals("execute_task")) {// 执行任务管理分配人查看
            taskExecBean = (YyWorktaskEntityBean) bundle.get("taskExecBean");
            executeTaskId = taskExecBean.getId();
        } else if (farexecute.equals("my_today_far_cannel")) {// 我的农事任务执行人查看
            todayTaskBean = (JsonTodayTaskObject.ObjBean) bundle.getSerializable("todayTaskBean");//每日任务对象
            YyPlantingTask = (JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskBean) bundle.getSerializable("YyPlantingTask");//种植任务
        } else if (farexecute.equals("far_edit")) {// 编辑农事任务
            plantaskBean = (PlantingTaskBean) bundle.get("plantaskBean");
            plantingTaskId = plantaskBean.getId();
        }
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);

        if (farexecute.equals("execute_task")) {// 执行任务管理分配人查看
            taskName = TextUtils.isEmpty(taskExecBean.getTaskName()) ? ""
                    : taskExecBean.getTaskName();// 任务名称
            taskDesc = TextUtils.isEmpty(taskExecBean.getWorktaskCloseDesc()) ? ""
                    : taskExecBean.getWorktaskCloseDesc();
            farType = TextUtils.isEmpty(taskExecBean.getWorktaskDoStatus()) ? ""
                    : taskExecBean.getWorktaskDoStatus();
        } else if (farexecute.equals("my_today_far_cannel")) {// 我的每日农事任务对象
            taskName = TextUtils.isEmpty(todayTaskBean.getTaskName()) ? ""
                    : todayTaskBean.getTaskName();// 任务名称
            taskDesc = TextUtils.isEmpty(YyPlantingTask.getContent()) ? ""
                    : YyPlantingTask.getContent();
            farType = TextUtils.isEmpty(YyPlantingTask.getFarmingCategory()) ? ""
                    : YyPlantingTask.getFarmingCategory();
        } else if (farexecute.equals("far_edit")) {// 编辑农事任务
            taskName = TextUtils.isEmpty(plantaskBean.getTaskName()) ? ""
                    : plantaskBean.getTaskName();// 任务名称
            taskDesc = TextUtils.isEmpty(plantaskBean.getContent()) ? ""
                    : plantaskBean.getContent();
            farType = TextUtils.isEmpty(plantaskBean.getFarmingCategory()) ? ""
                    : plantaskBean.getFarmingCategory();
        }

        tvTaskName = (TextView) findViewById(R.id.tv_task_name);// 任务名称
        tvTaskName.setText(taskName);
        tvTaskDesc = (TextView) findViewById(R.id.tv_task_desc);// 任务描述
        tvTaskDesc.setText(taskDesc);
        tvFarType = (TextView) findViewById(R.id.tv_far_type);// 农事分类
        tvFarType.setText(farType);
        tvExecutor = (TextView) findViewById(R.id.tv_executor);// 执行人
        tvExecutor.setText(PreferencesUtils.getString(mContext, "userName"));
        etCanExpl = (EditText) findViewById(R.id.et_cancel_explain);// 取消说明

        btnSubmite = (Button) findViewById(R.id.btn_submite);
        btnSubmite.setOnClickListener(this);
        btnChanReset = (Button) findViewById(R.id.btn_chan_reset);
        btnChanReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_submite:// 提交取消
                if (validator()) {
                    new Thread(new FarCancelRequest()).start();// 取消农事任务请求线程
                }
                break;
            case R.id.btn_chan_reset:// 重置
                setCancelResetChan();
                break;
            default:
                break;
        }
    }

    /**
     * 任务取消非空验证
     *
     * @return boolean
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-26 上午11:16:49
     */
    private boolean validator() {
        // TODO Auto-generated method stub
        getCancelTextView();
        return true;
    }

    private void getCancelTextView() {
        taskName = tvTaskName.getText().toString().trim();
        taskDesc = tvTaskDesc.getText().toString().trim();
        farType = tvFarType.getText().toString().trim();
        exeCutor = tvExecutor.getText().toString().trim();
        canExpl = etCanExpl.getText().toString().trim();
    }

    private void setCancelResetChan() {
        // TODO Auto-generated method stub
//        tvTaskName.setText("");
//        tvTaskDesc.setText("");
//        tvFarType.setText("");
//        tvExecutor.setText("");
        etCanExpl.setText("");
    }

    /**
     * 农事任务取消json解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskCancelAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26 上午11:33:18
     */
    class FarTaskCancelAction extends Thread {

        private String fartaskCanJson;

        public FarTaskCancelAction(String fartaskcancel_json) {
            // TODO Auto-generated constructor stub
            this.fartaskCanJson = fartaskcancel_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(fartaskCanJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonFarTaskDistrObject far_task_distr = gson.fromJson(
                        fartaskCanJson, JsonFarTaskDistrObject.class);

                if (!far_task_distr.equals("") && far_task_distr != null) {

                    if (far_task_distr.isSuccess()) {// 成功
                        // farTaskDistrBean = far_task_distr.getObj();

                        Message msg = Message.obtain();
                        msg.what = FARTASKCANCEL_VALUES;
                        mHandler.sendMessage(msg);
                    }else
                        TAUtils.toastMessage((Activity) mContext,
                                far_task_distr.getMsg());
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
                case FARTASKCANCEL_ERROR:// 农事任务取消请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case FARTASKCANCEL_SUCCESS:
                    String fartaskcancel_json = msg.obj.toString();
                    new Thread(new FarTaskCancelAction(fartaskcancel_json)).start();// 农事任务取消请求解析线程
                    break;
                case FARTASKCANCEL_VALUES:
                    if (farexecute.equals("my_today_far_cannel")) {// 我的每日农事任务对象
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 任务取消请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarCancelRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26 上午11:26:16
     */
    class FarCancelRequest extends Thread {

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
                map.put("userIds", null);
                map.put("allotContent ", canExpl);
                if (farexecute.equals("execute_task")) {// 执行任务管理分配人查看
                    map.put("workTaskId", executeTaskId);
                    Log.v(TAG, TAG + "执行任务取消请求");
                    WapiUtilEx.taskExecuCancel(map, new MYCallBack() {

                        @Override
                        public void onError(int code, String message) {
                            // TODO Auto-generated method stub
                            super.onError(code, message);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCANCEL_ERROR;
                            msg.obj = message;
                            mHandler.sendMessage(msg);
                        }

                        @Override
                        public void onSuccess(String response) {
                            // TODO Auto-generated method stub
                            super.onSuccess(response);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCANCEL_SUCCESS;
                            msg.obj = response;
                            mHandler.sendMessage(msg);
                        }

                    });
                } else if (farexecute.equals("my_today_far_cannel")) {// 我的每日农事任务对象
                    map.put("workTaskDayId", todayTaskBean.getId());//每日农事任务Id
                    Log.v(TAG, TAG + "每日农事任务取消请求");
                    WapiUtilEx.mytodayfartaskCancel(map, new MYCallBack() {

                        @Override
                        public void onError(int code, String message) {
                            // TODO Auto-generated method stub
                            super.onError(code, message);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCANCEL_ERROR;
                            msg.obj = message;
                            mHandler.sendMessage(msg);
                        }

                        @Override
                        public void onSuccess(String response) {
                            // TODO Auto-generated method stub
                            super.onSuccess(response);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCANCEL_SUCCESS;
                            msg.obj = response;
                            mHandler.sendMessage(msg);
                        }

                    });
                } else if (farexecute.equals("far_edit")) {// 编辑农事任务
                    map.put("plantingTaskId", plantingTaskId);
                    Log.v(TAG, TAG + "农事任务取消请求");
                    WapiUtilEx.fartaskCancel(map, new MYCallBack() {

                        @Override
                        public void onError(int code, String message) {
                            // TODO Auto-generated method stub
                            super.onError(code, message);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCANCEL_ERROR;
                            msg.obj = message;
                            mHandler.sendMessage(msg);
                        }

                        @Override
                        public void onSuccess(String response) {
                            // TODO Auto-generated method stub
                            super.onSuccess(response);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCANCEL_SUCCESS;
                            msg.obj = response;
                            mHandler.sendMessage(msg);
                        }

                    });
                }

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }

}
