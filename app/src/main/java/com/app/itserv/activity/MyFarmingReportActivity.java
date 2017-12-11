package com.app.itserv.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alicloud.oss.callback.ProgressCallback;
import com.alicloud.oss.model.Paramet;
import com.alicloud.oss.token.Initialicloud;
import com.alicloud.oss.token.StsToken;
import com.app.itserv.jparser.JsonFarTaskDistrObject;
import com.app.itserv.jparser.JsonFarmingRecordCreateObject;
import com.app.itserv.jparser.JsonTodayTaskDetailObject;
import com.app.itserv.jparser.JsonTodayTaskObject;
import com.app.itserv.manager.DataWordQuery;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.itserv.app.util.LogUtils.tag;

/**
 * 农事填报
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/9/8 18:51
 */
public class MyFarmingReportActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    // 农事查看
    public static final String TAG = "MyFarmingRecordActivity";
    private Context mContext;

    // 农事记录新增
    protected static final int FARMING_CREATEINFO_ERROR = 13;
    protected static final int FARMING_CREATEINFO_SUCCESS = 14;
    //当日任务对象
    private JsonTodayTaskObject.ObjBean todayTaskBean;
    private String todayTaskId;
    // 农事新增后的对象
    private JsonFarmingRecordCreateObject.ObjBean createBean;

    private ImageView ImgBack;

    private String ghouseIdname;// 大棚名字
    private String taskdayId;// 任务ID
    private String tasknameString;// 任务名称
    private String farmingCategory;// 农事Id
    private String farClass;// 农事分类
    private String createBy;// 记录人
    private String createDateStart;// 记录开始日期
    private String createDateEnd;// 记录结束日期
    private String workDesc;// 文字描述
    private String problemDesc;// 问题说明
    private String hasProblem;// 是否发现问题
    private String planTimeStart;// 开始时间
    private String planTimeEnd;// 结束时间
    private String attachment1;// 附件1
    private String attachment2;// 附件2

    private TextView startdistritime;// 执行开始时间
    private TextView stopdistritime;// 执行结束时间
    private TextView starthour;
    private TextView stophour;
    private TextView greenhouse;// 所属大棚
    private TextView classify;// 农事分类
    private TextView taskname;// 任务名称
    private TextView uploadimg;
    private ImageView preview1;
    private EditText taskdescribe;
    private EditText issueexplain;
    private TextView uploadissueimg;
    private ImageView preview2, preview3;
    private TextView tvFarTaskTitle;
    private Button btnAutoBuild, btnManualEdit;
    private LinearLayout llSupply;
    private CheckBox ckissueBox;
    private Button submite, farExaCannel;
    private LinearLayout llproblemdescription;// 问题说明布局
    private LinearLayout lluploadpictures;// 上传图片布局

    private LinearLayout llpreview;// 预览布局

    //每日任务
    private JsonTodayTaskDetailObject.ObjBean todayTaskDetailBeanList;//今日任务数据集合
    private JsonTodayTaskDetailObject.ObjBean.YyWorktaskBean yyWorktask;//工作任务对象
    private List<JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskOpelogListBean> yyPlantingTaskOpelogList;// 种植任务打开日志列表
    private JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskBean YyPlantingTask;//种植任务
    private List<JsonTodayTaskDetailObject.ObjBean.FramingRecordListBean> framingRecordList;//农事记录列表
    private JsonTodayTaskDetailObject.ObjBean.YyWorktaskDayBean yyWorktaskDay;//一天的工作任务

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_far_record);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        todayTaskId = bundle.getString("todayTaskId");// 获取当前当日任务Id

        initView();
        tvFarTaskTitle.setText("农事填报");
        btnAutoBuild.setVisibility(View.VISIBLE);
        btnManualEdit.setVisibility(View.VISIBLE);
        llSupply.setVisibility(View.VISIBLE);
        // 任务名称请求线程
        greenhouse.setClickable(false);// 工作大棚
        classify.setClickable(false);// 农事分类
        init();
        initStsData();
    }

    private void init() {
        TodayTaskDetailRequest(todayTaskId);// 当日任务
    }

    private void initStsData() {
        StsToken.getInstance();//生成token及初始化
    }

    /**
     * 公共请求接口
     */

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);// 返回按钮
        ImgBack.setOnClickListener(this);
        tvFarTaskTitle = (TextView) findViewById(R.id.tv_far_task_title);//标题
        btnAutoBuild = (Button) findViewById(R.id.btn_auto_build);// 自动生成按钮
        btnAutoBuild.setOnClickListener(this);
        btnManualEdit = (Button) findViewById(R.id.btn_manual_edit);// 手动编辑按钮
        btnManualEdit.setOnClickListener(this);
        llSupply = (LinearLayout) findViewById(R.id.far_exa_ll_supply);// 按钮隐藏的布局
        // 确定发送和重置按钮

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//获取当前日期
        String date = sdf.format(new java.util.Date());
        sdf = new SimpleDateFormat("hh");//获取当前时间
        String time = sdf.format(new java.util.Date());

        startdistritime = (TextView) findViewById(R.id.far_exa_start_distri_time);// 执行开始时间
        startdistritime.setText(String.valueOf(date));
        stopdistritime = (TextView) findViewById(R.id.far_exa_stop_distri_time);// 执行结束时间
        stopdistritime.setText(String.valueOf(date));
        starthour = (TextView) findViewById(R.id.far_exa_start_distri_time_hour);// 开始
        starthour.setText(String.valueOf(time));
        // 小时
        stophour = (TextView) findViewById(R.id.far_exa_stop_distri_time_hour);// 结束
        stophour.setText(String.valueOf(time));
        // 小时
        greenhouse = (TextView) findViewById(R.id.far_exa_greenhouse);// 工作大棚
        classify = (TextView) findViewById(R.id.far_exa_classify);// 农事分类
        taskname = (TextView) findViewById(R.id.far_exa_task_name);// 任务名称
        uploadimg = (TextView) findViewById(R.id.far_exa_upload_img);// 选择图片1
        preview1 = (ImageView) findViewById(R.id.far_exa_preview1);// 图片1
        taskdescribe = (EditText) findViewById(R.id.far_exa_task_describe);// 文字说明
        ckissueBox = (CheckBox) findViewById(R.id.far_exa_ck_issue);// 是否存在问题
        ckissueBox.setOnCheckedChangeListener(this);
        issueexplain = (EditText) findViewById(R.id.far_exa_issue_explain);// 问题说明
        uploadissueimg = (TextView) findViewById(R.id.far_exa_upload_issue_img);// 选择图片2
        preview2 = (ImageView) findViewById(R.id.far_exa_preview2);// 图片2
        preview3 = (ImageView) findViewById(R.id.far_exa_preview3);// 图片2

        submite = (Button) findViewById(R.id.far_exa_btn_submite);// 提交按钮
        farExaCannel = (Button) findViewById(R.id.far_exa_cannel);//取消按钮
        farExaCannel.setOnClickListener(this);
        llproblemdescription = (LinearLayout) findViewById(R.id.ll_far_ex_problem_description);// 问题说明布局
        lluploadpictures = (LinearLayout) findViewById(R.id.ll_far_ex_upload_pictures);// 上传图片布局
        llpreview = (LinearLayout) findViewById(R.id.ll_far_ex_preview);// 预览布局
        submite.setOnClickListener(this);
        uploadimg.setOnClickListener(this);
        uploadissueimg.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_auto_build:// 自动生成
                taskdescribe.setEnabled(false);
                String username = PreferencesUtils.getString(mContext, "userName");
                createDateStart = TextUtils.isEmpty(startdistritime.getText().toString().trim()) ? "" : startdistritime.getText().toString().trim();
                createDateEnd = TextUtils.isEmpty(stopdistritime.getText().toString().trim()) ? "" : stopdistritime.getText().toString().trim();
                planTimeStart = TextUtils.isEmpty(starthour.getText().toString().trim()) ? "" : starthour.getText().toString().trim();// 开始时间
                planTimeEnd = TextUtils.isEmpty(stophour.getText().toString().trim()) ? "" : stophour.getText().toString().trim();// 结束时间
                workDesc = "[" + createDateStart + "日" + planTimeStart + "时" + "~"
                        + createDateEnd + "日" + planTimeEnd + "时" + "]" + ",["
                        + username + "]在[" + ghouseIdname + "] 进行了 [" + classify.getText().toString() + "]工作";
                taskdescribe.setText(workDesc);
                break;
            case R.id.btn_manual_edit: // 手动编辑
                taskdescribe.setEnabled(true);
                taskdescribe.getText().clear();
                break;
            case R.id.far_exa_upload_img:// 选择图片1
                takePicture();
                break;
            case R.id.far_exa_upload_issue_img:// 选择图片2
                startActivityForResult(new Intent(mContext, CameraActivity.class).putExtra("isupdate", false), CAPTURE_IMAGE_ACTIVITY_REQUEST);
                break;
            case R.id.far_exa_btn_submite:// 提交按钮
                submite.setEnabled(true);
                submit();
                break;
            case R.id.far_exa_cannel:// 取消按钮
//                startActivity(new Intent(mContext, FarmingCancelActivity.class).putExtra("todayTaskBean", todayTaskBean).
//                        putExtra("YyPlantingTask", YyPlantingTask).putExtra("farexecute", "my_today_far_cannel"));//每日任务Id
                FarCancelRequest(todayTaskId);// 取消农事任务请求线程
                break;
            default:
                break;
        }
    }

    //拍照
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST = 200;

    public void takePicture() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } else {
            Log.e(TAG, "请确认已经插入SD卡");
        }
    }

    private Bitmap photo, photo1;
    private String filename;
    private String picPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    this.photo = BitmapFactory.decodeFile(uri.getPath());
                }
                if (this.photo == null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        this.photo = (Bitmap) bundle.get("data");
                    } else {
                        Log.e(TAG, "---------拍照失败------------");
                        return;
                    }
                }

                FileOutputStream fileOutputStream = null;
                try {
                    // 获取 SD 卡根目录
                    String saveDir = Environment.getExternalStorageDirectory() + "/meitian_photos";
                    // 新建目录
                    File dir = new File(saveDir);
                    if (!dir.exists()) dir.mkdir();
                    // 生成文件名
                    String id = PreferencesUtils.getString(MyFarmingReportActivity.this, "id");
                    filename = id + "_" + System.currentTimeMillis() + ".jpg";
                    // 生成文件名
                    // SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");
                    // String strRand = String.valueOf((int) (Math.random() * 5));
                    // filename = "Far" + (t.format(new Date())) + strRand + ".jpg";
                    // 新建文件
                    File file = new File(saveDir, filename);
                    // 打开文件输出流
                    fileOutputStream = new FileOutputStream(file);
                    // 生成图片文件
                    this.photo.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    // 相片的完整路径
                    this.picPath = file.getPath();
                    preview2.setImageBitmap(this.photo);
                    Log.e(tag, "---------获取图片成功------------，path=" + picPath);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消了图像捕获
                Log.e(tag, "---------用户取消了图像捕获------------，path=" + Paramet.uploadFilePath);
            } else {
                // 图像捕获失败，提示用户
                Log.e(tag, "---------------拍照失败--------------");
            }
        } else if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                attachment2 = data.getAction();
                if (uri != null) {
                    this.photo1 = BitmapFactory.decodeFile(uri.getPath());
                }
                if (this.photo1 == null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        this.photo1 = (Bitmap) bundle.get("data");
                    } else {
                        Log.e(TAG, "---------拍照失败------------");
                        return;
                    }
                }
                preview3.setImageBitmap(this.photo1);
            }
        }
    }

    /* 弹出dialog框start */
    private AlertDialog dialog;
    private Button quitEnsure;
    private Button quitCancel;
    private String loginUser = " ";

    /* 弹出dialog框end */
    // 关闭任务提示

    private void getCanActivaUser(final String toadyTaskId, String content) {
        dialog = new AlertDialog.Builder(mContext).create();
        // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        View comfirmDialog = LayoutInflater.from(mContext).inflate(
                R.layout.comfirm_dialog1, null);
        dialog.addContentView(comfirmDialog,
                new ViewGroup.LayoutParams((int) (mContext.getResources()
                        .getDisplayMetrics().widthPixels * 0.9),
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(true);
        TextView comfirmContent = (TextView) comfirmDialog
                .findViewById(R.id.tv_comfirmdialog_content);
        quitEnsure = (Button) comfirmDialog.findViewById(R.id.btn_ensurequit);
        quitCancel = (Button) comfirmDialog.findViewById(R.id.btn_cancelquit);
        comfirmContent.setText(content);
        // 确认操作
        quitEnsure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
        // 取消操作
        quitCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 提交 农事填报
     */
    private void submit() {
        if (picPath == null) {
            TAUtils.toastMessage((Activity) mContext, "提交前，请对填报内容进行拍照");
            return;
        }
        if (TextUtils.isEmpty(workDesc)) {
            TAUtils.toastMessage((Activity) mContext, "文字说明不能为空");
            return;
        }
        if (ckissueBox.isChecked()) {// 如果选择框选中，那么问题说明不能为空
            problemDesc = issueexplain.getText().toString().trim();
            if (TextUtils.isEmpty(problemDesc)) {
                TAUtils.toastMessage((Activity) mContext, "问题说明不能为空");
                return;
            }
            hasProblem = "Y";
        } else {
            hasProblem = "N";
        }
        try {
            Paramet.uploadObject = "farming/" + Initialicloud.getInstance().getDate() + "/" + filename;//目录结构
            if (picPath != null) {
                Paramet.uploadFilePath = picPath;
                Initialicloud.getInstance().initSamples();
                if (checkNotNull(Initialicloud.getInstance().putObjectSamples)) {
                    Initialicloud.getInstance().putObjectSamples.asyncPutObjectFromLocalFile(new ProgressCallback<PutObjectRequest, PutObjectResult>() {
                        @Override
                        public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                            OSSLog.logDebug("stsResponseCode", Boolean.parseBoolean(String.valueOf(request)));
                            Log.e("onProgress", "---request:----" + request + ",---currentSize:---" + currentSize + "---totalSize:----------" + totalSize + "------");
                        }

                        @Override
                        public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                            String body = result.getServerCallbackReturnBody();
                            int StatusCode = result.getStatusCode();
                            String Etag = result.getETag();
                            Map<String, String> responseHeader = result.getResponseHeader();
                            String id = result.getRequestId();
                            OSSLog.logDebug("stsResponseCode", Boolean.parseBoolean(String.valueOf(result)));
                            attachment1 = request.getObjectKey();
                            Log.e("onSuccess", "--------->request:---" + request + "--------------->result:----body：" + body + ",StatusCode：" + StatusCode + ",Etag：" + Etag + ",responseHeader" + responseHeader + ",id：" + id);
                            if (StatusCode == 200) {
                                new Thread(new FramingRecordCreateInfoRequest()).start();// 农事填报请求线程
                            }
                        }

                        @Override
                        public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                            OSSLog.logDebug("stsResponseCode", Boolean.parseBoolean(String.valueOf(clientException)));
                            Log.e("onFailure", "---request:----" + request + ",---clientException:---" + clientException + "--serviceException:--" + serviceException + "-----------------");
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkNotNull(Object obj) {
        if (obj != null) {
            return true;
        }
        ToastUtils.makeTextShort("---init Samples fail---失败---");
        return false;
    }

    // -------------------------我的农事记录新增-请求--------------------------------------------------

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FARMING_CREATEINFO_ERROR:// 农事记录新增---------------------------------
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case FARMING_CREATEINFO_SUCCESS:
                    String createinfo_json = msg.obj.toString();// 农事记录新增后的json解析
                    createinfoJson(createinfo_json);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.far_exa_ck_issue://是否存在问题
                if (ckissueBox.isChecked()) {
                    llproblemdescription.setVisibility(View.VISIBLE);// 问题说明布局
                    lluploadpictures.setVisibility(View.VISIBLE);// 上传图片布局
                    llpreview.setVisibility(View.VISIBLE);// 预览布局
                } else {
                    llproblemdescription.setVisibility(View.GONE);// 问题说明布局
                    lluploadpictures.setVisibility(View.GONE);// 上传图片布局
                    llpreview.setVisibility(View.GONE);// 预览布局
                }
                break;
        }
    }


    // -------------------------我的农事记录新增-请求--------------------------------------------------

    /**
     * 我的农事记录新增请求线程(农事填报)
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: FramingRecordCreateInfoRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-22 下午4:04:21
     */
    class FramingRecordCreateInfoRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id

                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("taskdayId", todayTaskId);// 农事任务id
                map.put("planDateStart", createDateStart);// 开始日期
                map.put("planTimeStart", planTimeStart);// 开始时间
                map.put("planDateEnd", createDateEnd);// 结束日期
                map.put("planTimeEnd", planTimeEnd);// 结束时间
                map.put("attachment1", attachment1);// 附件1
                map.put("workDesc", workDesc);// 文字描述
                map.put("hasProblem", hasProblem);// 是否发现问题
                map.put("attachment2", attachment2);// 附件2
                map.put("problemDesc", problemDesc);// 问题描述
                Log.i(TAG, TAG + "我的农事记录填报请求");
                WapiUtilEx.apiFramingRecordCreateInfo(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = FARMING_CREATEINFO_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = FARMING_CREATEINFO_SUCCESS;
                        msg.obj = response;
                        mHandler.sendMessage(msg);
                    }
                });
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }

        }
    }

    private void createinfoJson(String createinfojson) {

        if (TextUtils.isEmpty(createinfojson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonFarmingRecordCreateObject jsonFarmingRecordCreateObject = gson
                .fromJson(createinfojson, JsonFarmingRecordCreateObject.class);
        if (!jsonFarmingRecordCreateObject.equals("")
                && jsonFarmingRecordCreateObject != null) {
            if (jsonFarmingRecordCreateObject.isSuccess()) {// 成功
                createBean = jsonFarmingRecordCreateObject.getObj();
                if (createBean != null) {
                    submite.setEnabled(false);
                    ToastUtils.makeTextShort("填报成功......");
                    finish();
                }
            } else {
                ToastUtils.makeTextShort(jsonFarmingRecordCreateObject.getMsg());
            }
        }
    }

    //---------------------------每日任务明细请求start------------------------------------------------

    /**
     * 每日任务明细请求线程
     *
     * @param todayTaskId
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarTaskEditRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017年09月05日15:29:52
     */
    public void TodayTaskDetailRequest(String todayTaskId) {

        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext,
                "tenantId");// 商户id
        Log.v(TAG, TAG + "每日任务明细请求");
        HttpManager.getInstance().todaytaskDetail(TAG, token, currTenantId, todayTaskId, new HttpCallBack<JsonTodayTaskDetailObject>(MyFarmingReportActivity.this, true) {
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

                            //执行开始日期
                            String planDateStart = TextUtils.isEmpty(DateLocalUtil.getDate(String.valueOf(YyPlantingTask.getPlanDateStart()))) ? ""
                                    : DateLocalUtil.getDate(String.valueOf(YyPlantingTask.getPlanDateStart()));//执行频率开始日期
                            //执行结束日期
                            String planDateEnd = TextUtils.isEmpty(DateLocalUtil.getDate(String.valueOf(YyPlantingTask.getPlanDateEnd()))) ? ""
                                    : DateLocalUtil.getDate(String.valueOf(YyPlantingTask.getPlanDateEnd()));//执行频率结束日期
                            startdistritime.setText(planDateStart);//执行开始日期
                            stopdistritime.setText(planDateEnd);//执行结束日期
                            //开始时间
                            String plantimeStart = TextUtils.isEmpty(String.valueOf(YyPlantingTask
                                    .getPlanTimeStart())) ? "" : String.valueOf(YyPlantingTask
                                    .getPlanTimeStart());
                            //结束时间
                            String plantimeEnd = TextUtils.isEmpty(String.valueOf(YyPlantingTask
                                    .getPlanTimeEnd())) ? "" : String.valueOf(YyPlantingTask
                                    .getPlanTimeEnd());
                            starthour.setText(plantimeStart);
                            stophour.setText(plantimeEnd);
                            //获得所属大棚
                            ghouseIdname = TextUtils
                                    .isEmpty(YyPlantingTask.getGhouseFullname()) ? ""
                                    : YyPlantingTask.getGhouseFullname();//获得大棚名称
                            greenhouse.setText(ghouseIdname);
                            //农事分类
                            String farType = TextUtils.isEmpty(YyPlantingTask.getFarmingCategory()) ? "" : YyPlantingTask.getFarmingCategory();
                            classify.setText(DataWordQuery.getInstance().getfarType(farType));
                            //获得任务名称
                            String taskName = TextUtils.isEmpty(YyPlantingTask.getTaskName()) ? ""
                                    : YyPlantingTask.getTaskName();//获得任务名称
                            taskname.setText(taskName);
                        }
                    }
                }
            }
        });
    }


    /*-----------------------每日任务明细end------------------------*/


    /**
     * 任务取消请求线程
     *
     * @param todayTaskId
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: FarCancelRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26 上午11:26:16
     */
    public void FarCancelRequest(String todayTaskId) {

        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id

        Log.v(TAG, TAG + "每日农事任务取消请求");
        HttpManager.getInstance().mytodayfartaskCancel(TAG, token, currTenantId, todayTaskId, new HttpCallBack<JsonFarTaskDistrObject>(MyFarmingReportActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort("取消失败...");
            }

            @Override
            public void onSuccess(JsonFarTaskDistrObject date) {
                if (!date.equals("") && date != null) {
                    if (date.isSuccess()) {// 成功
                        if (date.getObj() != null)
                            finish();
                    }
                } else {
                    ToastUtils.makeTextShort("取消失败!");
                }
            }
        });
    }

}