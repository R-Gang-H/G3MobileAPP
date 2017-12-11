package com.app.itserv.activity;

import java.util.HashMap;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.jparser.JsonFarTaskDistrObject;
import com.app.itserv.jparser.JsonFarTaskDistrObject.AttributesBean;
import com.app.itserv.jparser.JsonFarTaskDistrObject.ObjBean;
import com.app.itserv.jparser.JsonFarTaskEditObject.ObjBean.PlantingTaskBean;
import com.app.itserv.jparser.JsonTaskExecDetailObject.ObjBean.YyWorktaskEntityBean;
import com.app.itserv.jparser.JsonTodayTaskDetailObject;
import com.app.itserv.jparser.JsonTodayTaskObject;
import com.app.itserv.manager.DataWordQuery;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 关闭农事任务
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: FarmingCloseActivit
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-27 上午10:18:47
 */
public class FarmingCloseActivit extends Activity implements OnClickListener {

    private static final String TAG = "FarmingCloseActivit";

    protected static final int FARTASKCLOSE_ERROR = 1;
    protected static final int FARTASKCLOSE_SUCCESS = 2;
    protected static final int FARTASKCLOSE_VALUES = 3;

    private Context mContext;

    private ImageView ImgBack;
    private TextView tvTaskName, tvTaskDesc, tvFarType, tvScore;
    private EditText etCloseExplain, etEvaExp;
    private Button btnSubmite, btnPswReset;

    private PlantingTaskBean plantaskBean;
    private YyWorktaskEntityBean taskExecBean;
    private String plantingTaskId;
    private String executeTaskId;
    private String taskName, taskDesc, farType, closeExp, score, evaExp;
    private String farexecute;

    private JsonTodayTaskObject.ObjBean todayTaskBean;//每日任务对象
    private JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskBean YyPlantingTask;//种植任务

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_close_lay);
        mContext = this;

        Bundle bundle = getIntent().getExtras();
        farexecute = (String) bundle.get("farexecute");
        if (farexecute.equals("execute_task")) {// 执行任务管理分配人查看
            taskExecBean = (YyWorktaskEntityBean) bundle.get("taskExecBean");
            executeTaskId = taskExecBean.getId();
        } else if (farexecute.equals("my_today_far_close")) {// 我的每日农事任务关闭
            todayTaskBean = (JsonTodayTaskObject.ObjBean) bundle.getSerializable("todayTaskBean");//每日任务对象
            YyPlantingTask = (JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskBean) bundle.getSerializable("YyPlantingTask");//种植任务
        } else if (farexecute.equals("far_edit")) {// 编辑农事任务
            plantaskBean = (PlantingTaskBean) bundle.get("plantaskBean");
            plantingTaskId = plantaskBean.getId();
        }

        initView();
    }

    private String farClass;

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
            farClass = DataWordQuery.getInstance().getfarType(farType);
        } else if (farexecute.equals("my_today_far_close")) {// 我的每日农事任务关闭
            taskName = TextUtils.isEmpty(YyPlantingTask.getTaskName()) ? ""
                    : YyPlantingTask.getTaskName();// 任务名称
            taskDesc = TextUtils.isEmpty(YyPlantingTask.getContent()) ? ""
                    : YyPlantingTask.getContent();
            farType = TextUtils.isEmpty(YyPlantingTask.getFarmingCategory()) ? ""
                    : YyPlantingTask.getFarmingCategory();
            farClass = DataWordQuery.getInstance().getfarType(farType);
        } else if (farexecute.equals("far_edit")) {// 编辑农事任务
            taskName = TextUtils.isEmpty(plantaskBean.getTaskName()) ? ""
                    : plantaskBean.getTaskName();// 任务名称
            taskDesc = TextUtils.isEmpty(plantaskBean.getContent()) ? ""
                    : plantaskBean.getContent();
            farType = TextUtils.isEmpty(plantaskBean.getFarmingCategory()) ? ""
                    : plantaskBean.getFarmingCategory();
            farClass = DataWordQuery.getInstance().getfarType(farType);
        }

        tvTaskName = (TextView) findViewById(R.id.tv_task_name);// 任务名称
        tvTaskName.setText(taskName);
        tvTaskDesc = (TextView) findViewById(R.id.tv_task_desc);// 任务描述
        tvTaskDesc.setText(taskDesc);
        tvFarType = (TextView) findViewById(R.id.tv_far_type);// 农事分类
        tvFarType.setText(farClass);
        etCloseExplain = (EditText) findViewById(R.id.et_close_explain);// 关闭说明
        tvScore = (TextView) findViewById(R.id.tv_score);// 评分
        etEvaExp = (EditText) findViewById(R.id.et_eva_exp);// 评价说明

        btnSubmite = (Button) findViewById(R.id.btn_submite);
        btnSubmite.setOnClickListener(this);
        btnPswReset = (Button) findViewById(R.id.btn_chan_reset);
        btnPswReset.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_submite:// 提交
                if (validator()) {
                    new Thread(new FarCloseRequest()).start();// 创建人关闭事任务请求线程
                }
                break;
            case R.id.btn_chan_reset:// 重置
                setCloseResetChan();
                break;
            default:
                break;
        }
    }

    /**
     * 任务关闭非空验证
     *
     * @return boolean
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-26 下午4:11:42
     */
    private boolean validator() {
        getCloseTextView();
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * 获取任务关闭控件
     *
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-26 下午4:14:00
     */
    private void getCloseTextView() {
        taskName = tvTaskName.getText().toString().trim();
        taskDesc = tvTaskDesc.getText().toString().trim();
        farType = tvFarType.getText().toString().trim();
        closeExp = etCloseExplain.getText().toString().trim();
        score = tvScore.getText().toString().trim();
        evaExp = etEvaExp.getText().toString().trim();
    }

    /**
     * 重置任务关闭控件
     *
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-26 下午4:13:26
     */
    private void setCloseResetChan() {
        // TODO Auto-generated method stub
//		tvTaskName.setText("");
//		tvTaskDesc.setText("");
//		tvFarType.setText("");
        etCloseExplain.setText("");
        tvScore.setText("");
        etEvaExp.setText("");
    }

    /**
     * 任务关闭json解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskCloseAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26 下午4:26:10
     */
    public void FarTaskCloseAction(String fartaskCloseJson) {

        if (TextUtils.isEmpty(fartaskCloseJson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }

        Gson gson = new Gson();
        JsonFarTaskDistrObject far_task_close = gson.fromJson(
                fartaskCloseJson, JsonFarTaskDistrObject.class);

        if (!far_task_close.equals("") && far_task_close != null) {
            TAUtils.toastMessage((Activity) mContext,
                    far_task_close.getMsg());
            if (far_task_close.isSuccess()) {// 成功

                ObjBean farTaskCloseBean = far_task_close.getObj();

                Message msg = Message.obtain();
                msg.what = FARTASKCLOSE_VALUES;
                mHandler.sendMessage(msg);
            } else
                TAUtils.toastMessage((Activity) mContext,
                        far_task_close.getMsg());
        }
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FARTASKCLOSE_ERROR:// 农事任务关闭请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case FARTASKCLOSE_SUCCESS:
                    String fartaskclose_json = msg.obj.toString();
                    FarTaskCloseAction(fartaskclose_json);// 农事任务关闭请求解析线程
                    break;
                case FARTASKCLOSE_VALUES:
                    if (farexecute.equals("my_today_far_close")) {// 我的每日农事任务关闭
                        startActivity(new Intent(mContext, MyFarmingTaskActivity.class));
                    }
                    finish();
                    break;
                default:
                    break;
            }
        }

    };

/***************管理员任务关闭请求线程*****************/

    /**
     * 管理员任务关闭请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarCloseRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26 下午4:19:28
     */
    class FarCloseRequest extends Thread {

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
                map.put("allotContent ", closeExp);
                map.put("worktaskCloseScore", score);
                map.put("worktaskCloseDesc", evaExp);
                if (farexecute.equals("execute_task")) {// 执行任务管理分配人查看
                    map.put("workTaskId", executeTaskId);
                    Log.v(TAG, TAG + "执行任务关闭请求");
                    WapiUtilEx.taskExecuClose(map, new MYCallBack() {

                        @Override
                        public void onError(int code, String message) {
                            // TODO Auto-generated method stub
                            super.onError(code, message);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCLOSE_ERROR;
                            msg.obj = message;
                            mHandler.sendMessage(msg);
                        }

                        @Override
                        public void onSuccess(String response) {
                            // TODO Auto-generated method stub
                            super.onSuccess(response);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCLOSE_SUCCESS;
                            msg.obj = response;
                            mHandler.sendMessage(msg);
                        }

                    });
                } else if (farexecute.equals("my_today_far_close")) {// 我的每日农事任务关闭
                    map.put("workTaskDayId", todayTaskBean.getId());//每日农事任务Id
                    Log.v(TAG, TAG + "农事任务关闭请求");
                    WapiUtilEx.myfartaskClose(map, new MYCallBack() {

                        @Override
                        public void onError(int code, String message) {
                            // TODO Auto-generated method stub
                            super.onError(code, message);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCLOSE_ERROR;
                            msg.obj = message;
                            mHandler.sendMessage(msg);
                        }

                        @Override
                        public void onSuccess(String response) {
                            // TODO Auto-generated method stub
                            super.onSuccess(response);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCLOSE_SUCCESS;
                            msg.obj = response;
                            mHandler.sendMessage(msg);
                        }
                    });
                } else if (farexecute.equals("far_edit")) {// 编辑农事任务
                    map.put("plantingTaskId", plantingTaskId);

                    Log.v(TAG, TAG + "农事任务关闭请求");
                    WapiUtilEx.fartaskClose(map, new MYCallBack() {

                        @Override
                        public void onError(int code, String message) {
                            // TODO Auto-generated method stub
                            super.onError(code, message);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCLOSE_ERROR;
                            msg.obj = message;
                            mHandler.sendMessage(msg);
                        }

                        @Override
                        public void onSuccess(String response) {
                            // TODO Auto-generated method stub
                            super.onSuccess(response);
                            Message msg = Message.obtain();
                            msg.what = FARTASKCLOSE_SUCCESS;
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
