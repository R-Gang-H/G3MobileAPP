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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.itserv.adapters.DistriPersonAdapter;
import com.app.itserv.jparser.JsonFarTaskDistrObject;
import com.app.itserv.jparser.JsonFarTaskDistrObject.AttributesBean;
import com.app.itserv.jparser.JsonFarTaskDistrObject.ObjBean;
import com.app.itserv.jparser.JsonFarTaskEditObject.ObjBean.PlantingTaskBean;
import com.app.itserv.jparser.JsonGreHouEmpObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分配农事任务
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: FarmingDistribuActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-25 下午8:11:06
 */

public class FarmingDistribuActivity extends Activity implements
        OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = "FarmingDistribuActivity";
    protected static final int FARTASKDISTR_ERROR = 1;
    protected static final int FARTASKDISTR_SUCCESS = 2;
    protected static final int FARTASKDISTR_VALUES = 3;

    private Context mContext;

    private ImageView ImgBack;
    private Button btnSubmite, btnPswReset;
    private TextView tvTaskName, tvTaskDesc, tvFarType;
    private EditText etDistExplain, etExecutor;

    private PlantingTaskBean plantaskBean;// 农事任务
    private String taskName, taskDesc, farType, distExplain, exeCutor;
    private String plantingTaskId;//计划id
    private ObjBean farTaskDistrBean;
    private String gHouseId;//大棚id

    // 执行人
    protected static final int GHOUSEEMP_ERROR = 4;
    protected static final int GHOUSEEMP_SUCCESS = 5;
    protected static final int GHOUSEEMP_VALUES = 6;

    private Spinner spExecutor;// 执行人
    private List<JsonGreHouEmpObject.ObjBean> gHouseUserEmpList;
    private DistriPersonAdapter distriPersonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_distribu);
        mContext = this;

        Bundle bundle = getIntent().getExtras();
        plantaskBean = (PlantingTaskBean) bundle.getSerializable("plantaskBean");
        plantingTaskId = plantaskBean.getId();// 农事任务id
        gHouseId = plantaskBean.getGhouseId();

        new Thread(new GreHouEmpRequest(gHouseId)).start();//执行人列表
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);

        tvTaskName = (TextView) findViewById(R.id.tv_task_name);// 任务名称
        tvTaskName.setText(TextUtils.isEmpty(plantaskBean.getTaskName()) ? ""
                : plantaskBean.getTaskName());
        tvTaskDesc = (TextView) findViewById(R.id.tv_task_desc);// 任务描述
        tvTaskDesc.setText(TextUtils.isEmpty(plantaskBean.getContent()) ? ""
                : plantaskBean.getContent());
        tvFarType = (TextView) findViewById(R.id.tv_far_type);// 农事分类
        farType = TextUtils.isEmpty(plantaskBean.getFarmingCategory()) ? "无"
                : plantaskBean.getFarmingCategory();
        String farClass = null;
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
        tvFarType.setText(farClass);
        etDistExplain = (EditText) findViewById(R.id.et_dist_explain);// 分配说明
        etExecutor = (EditText) findViewById(R.id.et_executor);// 执行人
        spExecutor = (Spinner) findViewById(R.id.sp_executor);// 执行人
        spExecutor.setOnItemSelectedListener(this);

        btnSubmite = (Button) findViewById(R.id.btn_submite);// 提交
        btnSubmite.setOnClickListener(this);
        btnPswReset = (Button) findViewById(R.id.btn_chan_reset);// 重置
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
                    new Thread(new FarDistriRequest()).start();// 分配农事任务请求线程
                }
                break;
            case R.id.btn_chan_reset:// 重置
                setDistrResetChan();
                break;
            default:
                break;
        }
    }

    /**
     * 任务分配非空验证
     *
     * @return boolean
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-26 上午9:41:08
     */
    private boolean validator() {
        // TODO Auto-generated method stub
        getDistrTextView();
        if (TextUtils.isEmpty(taskName)) {
            TAUtils.toastMessage((Activity) mContext, "任务名称不能为空!");
            return false;
        }
        if (TextUtils.isEmpty(taskDesc)) {
            TAUtils.toastMessage((Activity) mContext, "任务描述不能为空!");
            return false;
        }
        if (TextUtils.isEmpty(farType)) {
            TAUtils.toastMessage((Activity) mContext, "农事分类不能为空!");
            return false;
        }
        if (TextUtils.isEmpty(distExplain)) {
            TAUtils.toastMessage((Activity) mContext, "分配说明不能为空!");
            return false;
        }
        if (TextUtils.isEmpty(exeCutor)) {
            TAUtils.toastMessage((Activity) mContext, "至少选择一个执行人!");
            return false;
        }

        return true;
    }

    /**
     * 获取农事记录控件
     *
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-26 上午9:41:35
     */
    private void getDistrTextView() {
        taskName = tvTaskName.getText().toString().trim();
        taskDesc = tvTaskDesc.getText().toString().trim();
        farType = tvFarType.getText().toString().trim();
        distExplain = etDistExplain.getText().toString().trim();
        exeCutor = etExecutor.getText().toString().trim();
    }

    /**
     * 重置控件
     *
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-26 上午9:49:22
     */
    private void setDistrResetChan() {
        // TODO Auto-generated method stub
        etDistExplain.setText("");
        etExecutor.setText("");
    }

    StringBuilder executorBuilder = new StringBuilder();

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
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

    }

    /**
     * 农事任务分配json报文解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskDistrAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26 上午10:21:16
     */
    class FarTaskDistrAction extends Thread {

        private String farTaskDistrJson;

        public FarTaskDistrAction(String fartaskdistr_json) {
            // TODO Auto-generated constructor stub
            this.farTaskDistrJson = fartaskdistr_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(farTaskDistrJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonFarTaskDistrObject far_task_distr = gson.fromJson(
                        farTaskDistrJson, JsonFarTaskDistrObject.class);

                if (!far_task_distr.equals("") && far_task_distr != null) {

                    if (far_task_distr.isSuccess()) {// 成功

                        AttributesBean attributesbean = far_task_distr
                                .getAttributes();
                        attributesbean.getCurrUserId();
                        attributesbean.getCurrTenantId();

                        farTaskDistrBean = far_task_distr.getObj();

                        Message msg = Message.obtain();
                        msg.what = FARTASKDISTR_VALUES;
                        mHandler.sendMessage(msg);
                    } else
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
                case FARTASKDISTR_ERROR:// 农事任务分配请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case FARTASKDISTR_SUCCESS:
                    String fartaskdistr_json = msg.obj.toString();
                    new Thread(new FarTaskDistrAction(fartaskdistr_json)).start();// 农事任务分配请求解析线程
                    break;
                case FARTASKDISTR_VALUES:

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
     * 分配农事任务请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarDistriRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26 上午9:56:28
     */
    class FarDistriRequest extends Thread {

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
                map.put("plantingTaskId", plantingTaskId);
                map.put("allotContent ", distExplain);
                map.put("userIds", executorBuilder.toString().trim());
                Log.v(TAG, TAG + "农事任务分配请求");
                WapiUtilEx.fartaskDistr(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = FARTASKDISTR_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = FARTASKDISTR_SUCCESS;
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
}
