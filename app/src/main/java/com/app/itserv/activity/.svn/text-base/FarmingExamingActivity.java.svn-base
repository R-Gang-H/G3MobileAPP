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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.itserv.adapters.BelongExecuteTaskNameAdapter;
import com.app.itserv.adapters.BelongGreenHoustAdapter;
import com.app.itserv.adapters.BelongTaskNameAdapter;
import com.app.itserv.adapters.DataDictionaryAdapter;
import com.app.itserv.jparser.JsonBelongExecuteTaskNameObject;
import com.app.itserv.jparser.JsonBelongTaskNameObject;
import com.app.itserv.jparser.JsonBelongfarCreateobject;
import com.app.itserv.jparser.JsonBelongfarCreateobject.ObjBean.PlantingTaskBean;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonFarmingExamingObject;
import com.app.itserv.jparser.JsonFarmingExamingObject.ObjBean;
import com.app.itserv.jparser.JsonFarmingRecordCreateObject;
import com.app.itserv.jparser.JsonbelongGreenHouseOBject;
import com.google.gson.Gson;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.DateTimePickDialogUtil;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 农事查看
 *
 * @author Administrator
 */
public class FarmingExamingActivity extends Activity implements OnClickListener {

    // 农事查看
    public static final String TAG = "FarmingExamingActivity";
    protected static final int FARMING_EXAMING_ERROR = 1;
    protected static final int FARMING_EXAMING_SUCCESS = 2;
    protected static final int FARMING_EXAMING_VALUES = 3;
    // 所属大棚
    protected static final int BELONG_GREENHOUSE_ERROR = 7;
    protected static final int BELONG_GREENHOUSE_SUCCESS = 8;
    protected static final int BELONG_GREENHOUSE_VALUES = 9;
    // 任务名称 用于查看详情
    protected static final int BELONG_TASKNAME_ERROR = 10;
    protected static final int BELONG_TASKNAME_SUCCESS = 11;
    protected static final int BELONG_TASKNAME_VALUES = 12;
    // 任务名称 执行 用于填报农事
    protected static final int BELONG_EXECUTE_TASKNAME_ERROR = 16;
    protected static final int BELONG_EXECUTE_TASKNAME_SUCCESS = 17;
    protected static final int BELONG_EXECUTE_TASKNAME_VALUES = 18;
    // 根据执行任务ID 确定对应的大棚和农事分类
    protected static final int BELONG_FAR_CATEATE_ERROR = 19;
    protected static final int BELONG_FAR_CATEATE_SUCCESS = 20;
    protected static final int BELONG_FAR_CATEATE_VALUES = 21;

    private String ghouseId;// 大棚ID
    private String ghouseIdname;// 大棚名字
    private String taskId;// 任务IisNumericD
    private String tasknameString;// 任务名称
    private String farmingCategory;// 农事Id
    private String strdatadic;// 农事分类
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

    private String plantingTaskId;// 任务ID 根据任务ID请求数据 确定对应的大棚和分类
    private String farcreateGhouse;// 根据执行任务ID得到的对应的大棚ID
    private String farcreateFarmingCategory;// 根据执行任务ID得到的对应的农事分类ID

    private DateTimePickDialogUtil dateTimePicKDialog;// 时间选择器的Dialog
    private String initDateTime;// 时间选择器的Dialog
    private Context mContext;
    private ImageView ImgBack;
    private Button btnAutoBuild, btnManualEdit;
    private LinearLayout llSupply;
    private ObjBean objBean;
    private String framingRecordId;// 农事记录ID

    private CheckBox ckissueBox;
    private Button submite;
    private Button reset;
    private TextView startdistritime;// 执行开始时间
    private TextView stopdistritime;// 执行结束时间
    private TextView starthour;
    private TextView stophour;
    private Spinner greenhouse;// 所属大棚
    private TextView classify;// 农事分类
    private Spinner taskname;// 任务名称
    private TextView uploadimg;
    private ImageView preview1;
    private EditText taskdescribe;
    private EditText issueexplain;
    private TextView uploadissueimg;
    private ImageView preview2;
    private TextView tvFarTaskTitle;
    private TextView tvPrompt, tvIsProblem;

    private String farrecordsupply;// 识别是农事填报还是农事查看详情
    private LinearLayout llproblemdescription;// 问题说明布局
    private LinearLayout llUploadImage, lluploadpictures;// 上传图片布局
    private LinearLayout llpreview;// 预览布局
    // 所属大棚
    private List<JsonbelongGreenHouseOBject.ObjBean> greenhouseBeans = null;
    private BelongGreenHoustAdapter belongGreenHoustAdapter;
    private JsonbelongGreenHouseOBject.ObjBean greenhouseBean;
    // 任务名称
    private List<JsonBelongTaskNameObject.ObjBean> tasknameBeans = null;
    private BelongTaskNameAdapter belongTaskNameAdapter;
    private JsonBelongTaskNameObject.ObjBean tasknameBean;
    // 执行任务名称 新增中任务名称用的这个
    private List<JsonBelongExecuteTaskNameObject.ObjBean> executetasknameBeans = null;
    private BelongExecuteTaskNameAdapter belongExecuteTaskNameAdapter;
    // 根据执行任务ID获得的对象 ，根据这个对象取对应的大棚和农事分类
    private PlantingTaskBean plantingTaskBean;

    // 农事新增后的对象
    private JsonFarmingRecordCreateObject.ObjBean createBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_examing_lay);
        mContext = this;
        initView();
        farrecordsupply = getIntent().getExtras().getString("farrecordsupply");// 识别是农事填报还是农事查看详情
        framingRecordId = getIntent().getExtras().getString("framingRecordId");// 农事ID
        if (farrecordsupply.equals("farrecord")) {// 农事（记录）查看
            tvFarTaskTitle.setText("农事记录");
            btnAutoBuild.setVisibility(View.GONE);
            btnManualEdit.setVisibility(View.GONE);
            llSupply.setVisibility(View.GONE);
            llUploadImage.setVisibility(View.GONE);

            startdistritime.setClickable(false);// 执行开始时间
            startdistritime.setEnabled(false);
            stopdistritime.setClickable(false);// 执行结束时间
            stopdistritime.setEnabled(false);
            greenhouse.setClickable(false);// 工作大棚
            classify.setClickable(false);// 农事分类
            taskname.setClickable(false);// 任务名称
            uploadimg.setClickable(false);// 上传图片
            preview1.setClickable(false);// 图片1
            taskdescribe.setClickable(false);// 文字说明
            ckissueBox.setClickable(false);// 是否存在问题
            issueexplain.setClickable(false);// 问题说明
            uploadissueimg.setClickable(false);// 选择图片2
            preview2.setClickable(false);// 图片2
            init();
        }
        initRequest();
    }

    private void init() {
        new Thread(new FramingExamingGetInfoRequest()).start();// 我的农事记录详情请求线程
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);// 返回按钮
        ImgBack.setOnClickListener(this);
        tvFarTaskTitle = (TextView) findViewById(R.id.tv_far_task_title);//标题
        // 创建时间选择器
        initDateTime = DateTimePickDialogUtil.formatDate();
        dateTimePicKDialog = new DateTimePickDialogUtil(this, initDateTime);
        btnAutoBuild = (Button) findViewById(R.id.btn_auto_build);// 自动生成按钮
        btnAutoBuild.setOnClickListener(this);
        btnManualEdit = (Button) findViewById(R.id.btn_manual_edit);// 手动编辑按钮
        btnManualEdit.setOnClickListener(this);
        llSupply = (LinearLayout) findViewById(R.id.far_exa_ll_supply);// 按钮隐藏的布局
        // 确定发送和重置按钮

        startdistritime = (TextView) findViewById(R.id.far_exa_start_distri_time);// 执行开始时间
        startdistritime.setOnClickListener(this);
        stopdistritime = (TextView) findViewById(R.id.far_exa_stop_distri_time);// 执行结束时间
        stopdistritime.setOnClickListener(this);
        starthour = (TextView) findViewById(R.id.far_exa_start_distri_time_hour);// 开始
        // 小时
        stophour = (TextView) findViewById(R.id.far_exa_stop_distri_time_hour);// 结束
        // 小时
        greenhouse = (Spinner) findViewById(R.id.far_exa_greenhouse);// 工作大棚
        classify = (TextView) findViewById(R.id.far_exa_classify);// 农事分类
        taskname = (Spinner) findViewById(R.id.far_exa_task_name);// 任务名称
        uploadimg = (TextView) findViewById(R.id.far_exa_upload_img);// 上传图片
        preview1 = (ImageView) findViewById(R.id.far_exa_preview1);// 图片1
        taskdescribe = (EditText) findViewById(R.id.far_exa_task_describe);// 文字说明
        tvPrompt = (TextView) findViewById(R.id.tv_prompt);//农事记录存在问题标题
        ckissueBox = (CheckBox) findViewById(R.id.far_exa_ck_issue);// 是否存在问题
        tvIsProblem = (TextView) findViewById(R.id.tv_is_problem);//是否存在问题
        issueexplain = (EditText) findViewById(R.id.far_exa_issue_explain);// 问题说明
        uploadissueimg = (TextView) findViewById(R.id.far_exa_upload_issue_img);// 选择图片2
        preview2 = (ImageView) findViewById(R.id.far_exa_preview2);// 图片2

        submite = (Button) findViewById(R.id.far_exa_btn_submite);// 提交按钮
        reset = (Button) findViewById(R.id.far_exa_changepsw_reset);// 重置按钮
        llproblemdescription = (LinearLayout) findViewById(R.id.ll_far_ex_problem_description);// 问题说明布局
        llUploadImage = (LinearLayout) findViewById(R.id.upload_image);// 上传图片布局
        lluploadpictures = (LinearLayout) findViewById(R.id.ll_far_ex_upload_pictures);// 上传图片布局
        llpreview = (LinearLayout) findViewById(R.id.ll_far_ex_preview);// 预览布局
        submite.setOnClickListener(this);
        reset.setOnClickListener(this);
        uploadimg.setOnClickListener(this);
        uploadissueimg.setOnClickListener(this);

        initSpinner();
        // 是否选中监听
        ckissueBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (ckissueBox.isChecked()) {
                    llproblemdescription.setVisibility(View.VISIBLE);// 问题说明布局
                    lluploadpictures.setVisibility(View.GONE);// 上传图片布局
                    llpreview.setVisibility(View.VISIBLE);// 预览布局
                } else {
                    llproblemdescription.setVisibility(View.GONE);// 问题说明布局
                    lluploadpictures.setVisibility(View.GONE);// 上传图片布局
                    llpreview.setVisibility(View.GONE);// 预览布局
                }

            }
        });
        // 所属大棚 选择监听
        greenhouse.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                ghouseId = greenhouseBeans.get(position).getId();
                ghouseIdname = greenhouseBeans.get(position)
                        .getGhouseFullname();
                Log.i("qqqq点击了qqqqqq", "所属大棚  选择监听  名字：" + ghouseIdname
                        + "        编号：" + ghouseId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // 任务名称 选择监听
        taskname.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (farrecordsupply.equals("farsupply")) {// 农事填报
//                    taskId = TextUtils.isEmpty(executetasknameBeans.get(position).getId()) ? "" : executetasknameBeans.get(position).getId();
//                    tasknameString = executetasknameBeans.get(position)
//                            .getTaskName();
                    plantingTaskId = executetasknameBeans.get(position)
                            .getTaskId();// 获取TaskID 请求对应的大棚和农事分类
                    new Thread(new BelongfarCreateRequest()).start();// 2、所属执行任务名称请求线程必须得有计划任务Id
                } else if (farrecordsupply.equals("farrecord")) {// 农事（记录）查看
                    taskId = tasknameBeans.get(position).getId();
                    tasknameString = tasknameBeans.get(position).getTaskName();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void initSpinner() {

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_auto_build:// 自动生成
                String username = PreferencesUtils.getString(mContext, "userName");
                createDateStart = TextUtils.isEmpty(startdistritime.getText()
                        .toString().trim()) ? "" : startdistritime.getText()
                        .toString().trim();
                createDateEnd = TextUtils.isEmpty(stopdistritime.getText()
                        .toString().trim()) ? "" : stopdistritime.getText()
                        .toString().trim();
                planTimeStart = TextUtils.isEmpty(starthour.getText().toString()
                        .trim()) ? "" : starthour.getText().toString().trim();// 开始时间
                planTimeEnd = TextUtils.isEmpty(stophour.getText().toString()
                        .trim()) ? "" : stophour.getText().toString().trim();// 结束时间
                workDesc = "[" + createDateStart + "日" + planTimeStart + "时" + "~"
                        + createDateEnd + "日" + planTimeEnd + "时" + "]" + ",["
                        + username + "]在[" + ghouseIdname + "] 进行了 [" + strdatadic
                        + "]工作";
                taskdescribe.setText(workDesc);
                break;
            case R.id.btn_manual_edit: // 手动编辑
                taskdescribe.getText().clear();
                break;
            case R.id.far_exa_upload_img:// 选择图片1

                break;
            case R.id.far_exa_upload_issue_img:// 选择图片2

                break;
            case R.id.far_exa_btn_submite:// 提交按钮
//                submit();
                break;
            case R.id.far_exa_changepsw_reset:// 重置按钮

                startdistritime.setText("");// 执行开始日期
                stopdistritime.setText("");// 执行结束日期
                starthour.setText("");// 执行开始时间
                stophour.setText("");// 执行结束时间
                // greenhouse.getText().clear();//工作大棚
                // classify.getText().clear();//农事分类
                // taskname.getText().clear();//任务名称
                taskdescribe.getText().clear();// 文字说明
                ckissueBox.setChecked(false);// 是否存在问题
                issueexplain.getText().clear();// 问题说明

                break;
            case R.id.far_exa_start_distri_time:// 执行开始时间
                dateTimePicKDialog.dateTimePicKDialog(startdistritime);
                break;
            case R.id.far_exa_stop_distri_time:// 执行结束时间
                dateTimePicKDialog.dateTimePicKDialog(stopdistritime);
                break;
            default:
                break;
        }
    }


    public boolean isNumeric(String str) {
        int a = Integer.parseInt(str);
        return a >= 0 && a <= 23;
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FARMING_EXAMING_ERROR:// 我的农事记录详情查看请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case FARMING_EXAMING_SUCCESS:// 我的农事记录详情查看请求成功
                    String farmingexString_json = msg.obj.toString();// 我的农事记录详情查看解析
                    farmingexamingJson(farmingexString_json);
                    break;
                case FARMING_EXAMING_VALUES:// 我的农事记录详情查看
                    // 时间格式转换
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    long lstartdistritime = objBean.getPlanDateStart();// 执行开始时间
                    if (lstartdistritime != 0) {
                        Date datestartdistritime = new Date(lstartdistritime); // 根据long类型的毫秒数生命一个date类型的时间
                        String Sstartdistritime = simpleDateFormat
                                .format(datestartdistritime); // 把date类型的时间转换为string
                        startdistritime.setText(Sstartdistritime);// 执行开始时间
                    } else {
                        startdistritime.setText("未知");// 执行开始时间
                    }

                    long lstopdistritime = objBean.getPlanDateEnd();// 执行结束时间
                    if (lstopdistritime != 0) {
                        Date datestopdistritime = new Date(lstopdistritime); // 根据long类型的毫秒数生命一个date类型的时间
                        String Sstopdistritime = simpleDateFormat
                                .format(datestopdistritime); // 把date类型的时间转换为string
                        stopdistritime.setText(Sstopdistritime);// 执行结束时间
                    } else {
                        stopdistritime.setText("未知");// 执行结束时间
                    }
                    objBean.getTaskName();
                    String farClass = null;
                    //农事分类
                    String farType = TextUtils.isEmpty(objBean.getFarmingCategory()) ? "" : objBean.getFarmingCategory();
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
                    classify.setText(farClass);
                    String strhasPr = objBean.getHasProblem();
                    if (!TextUtils.isEmpty(strhasPr)) {
                        llUploadImage.setVisibility(View.GONE);
                        lluploadpictures.setVisibility(View.GONE);
                        if (strhasPr.equals("N")) {// 是否存在问题
                            ckissueBox.setChecked(false);
                            llproblemdescription.setVisibility(View.GONE);// 问题说明布局
                            lluploadpictures.setVisibility(View.GONE);// 上传图片布局
                            llpreview.setVisibility(View.GONE);// 预览布局
                        } else if (strhasPr.equals("Y")) {
                            ckissueBox.setChecked(true);
                            llproblemdescription.setVisibility(View.VISIBLE);// 问题说明布局
                            lluploadpictures.setVisibility(View.GONE);// 上传图片布局
                            llpreview.setVisibility(View.VISIBLE);// 预览布局
                            tvPrompt.setText("该任务存在如下问题:");
                            tvIsProblem.setText("存在问题");
                        }
                    }

                    taskdescribe.setText(objBean.getWorkDesc());// 文字说明
                    taskdescribe.setEnabled(false);// 不可编辑
                    issueexplain.setText(objBean.getProblemDesc());// 问题说明
                    issueexplain.setEnabled(false);// 不可编辑
                    starthour.setText(objBean.getPlanTimeStart());// 开始小时
                    starthour.setEnabled(false);// 不可编辑
                    stophour.setText(objBean.getPlanTimeEnd());// 结束小时小时
                    stophour.setEnabled(false);// 不可编辑
                    break;
                case BELONG_GREENHOUSE_ERROR:// 所属大棚-----------------------------
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case BELONG_GREENHOUSE_SUCCESS:
                    String belonggreenhouse_json = msg.obj.toString();// 我的农事记录列表
                    // 所属大棚解析
                    belonggreenhouseJson(belonggreenhouse_json);
                    break;
                case BELONG_GREENHOUSE_VALUES:
                    // 设置适配器
                    belongGreenHoustAdapter = new BelongGreenHoustAdapter(
                            greenhouseBeans, mContext);
                    greenhouse.setAdapter(belongGreenHoustAdapter);
                    // 判断是 农事填报 还是 查看详情
                    if (farrecordsupply.equals("farsupply")) {// 农事填报
                        // farcreateGhouse;//根据执行任务ID得到的对应的大棚ID
                        for (int i = 0; i < greenhouseBeans.size(); i++) {
                            String strm = TextUtils.isEmpty(greenhouseBeans.get(i).getId()) ? "" : greenhouseBeans.get(i).getId();
                            if (farcreateGhouse != null) {
                                if (farcreateGhouse.equals(strm)) {
                                    greenhouse.setSelection(i);
                                }
                            }
                        }
                    } else if (farrecordsupply.equals("farrecord")) {// 农事（记录）查看
                        if (objBean != null) {
                            String strgreenhouse = objBean.getGhouseId();
                            for (int i = 0; i < greenhouseBeans.size(); i++) {
                                String strm = greenhouseBeans.get(i).getId();
                                if (strgreenhouse.equals(strm)) {
                                    greenhouse.setSelection(i);
                                }
                            }
                        }
                    }
                    break;
                case BELONG_TASKNAME_ERROR:// 任务名称--------------------------------
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case BELONG_TASKNAME_SUCCESS:
                    String belongtaskname_json = msg.obj.toString();// 我的农事记录列表
                    // 任务名称解析
                    belongtasknameJson(belongtaskname_json);
                    break;
                case BELONG_TASKNAME_VALUES:
                    // 设置适配器
                    belongTaskNameAdapter = new BelongTaskNameAdapter(mContext,
                            tasknameBeans);
                    taskname.setAdapter(belongTaskNameAdapter);
                    // 判断是 农事填报 还是 查看详情
                    if (farrecordsupply.equals("farsupply")) {// 农事填报
                        taskname.setSelection(0);// 设置默认值
                    } else if (farrecordsupply.equals("farrecord")) {// 农事（记录）查看
                        if (objBean != null) {
                            String strtaskID = TextUtils.isEmpty(objBean.getTaskid()) ? "" : objBean.getTaskid();
                            for (int i = 0; i < tasknameBeans.size(); i++) {
                                String strm = tasknameBeans.get(i).getId();
                                if (strtaskID != null) {
                                    if (strtaskID.equals(strm)) {
                                        taskname.setSelection(i);
                                    }
                                }
                            }
                        }
                    }
                    break;
                case BELONG_EXECUTE_TASKNAME_ERROR:// 执行
                    // 农事任务名称---------------------------------
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case BELONG_EXECUTE_TASKNAME_SUCCESS:
                    String executetaskname_json = msg.obj.toString();// 农事记录中执行
                    // 农事任务名称json解析
                    belongexecutetasknameJson(executetaskname_json);
                    break;
                case BELONG_EXECUTE_TASKNAME_VALUES:
                    belongExecuteTaskNameAdapter = new BelongExecuteTaskNameAdapter(
                            mContext, executetasknameBeans);
                    taskname.setAdapter(belongExecuteTaskNameAdapter);
                    taskname.setSelection(0);

                    break;
                case BELONG_FAR_CATEATE_ERROR:// 根据执行任务ID
                    // 确定对应的大棚和农事分类-------------------------------
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case BELONG_FAR_CATEATE_SUCCESS:
                    String farcreate_json = msg.obj.toString();// 根据执行任务ID
                    // 确定对应的大棚和农事分类json解析
                    belongfarcreateJson(farcreate_json);
                    break;
                case BELONG_FAR_CATEATE_VALUES:
                    farcreateGhouse = plantingTaskBean.getGhouseId();
                    farcreateFarmingCategory = plantingTaskBean
                            .getFarmingCategory();
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 公共请求接口
     */
    private void initRequest() {
        new Thread(new BelongGreenhouseRequest()).start();// 0、所属大棚请求线程
        new Thread(new BelongTaskNameRequest()).start();// 1、所属任务名称请求线程
    }

    // -------------------------我的农事记录详情查看---------------------------------------------------

    /**
     * 我的农事记录详情查看请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: FramingExamingGetInfoRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-22 下午4:04:21
     */
    class FramingExamingGetInfoRequest extends Thread {

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
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("framingRecordId", framingRecordId);
                Log.i(TAG, TAG + "我的农事记录详情查看请求");
                WapiUtilEx.apiFramingRecordGetInfo(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = FARMING_EXAMING_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = FARMING_EXAMING_SUCCESS;
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

    // ----------------我的农事记录详情查看 json解析-----------------------------

    private void farmingexamingJson(String examingjson) {
        if (TextUtils.isEmpty(examingjson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonFarmingExamingObject jsonFarmingExamingObject = gson.fromJson(
                examingjson, JsonFarmingExamingObject.class);
        if (!jsonFarmingExamingObject.equals("")
                && jsonFarmingExamingObject != null) {
            if (jsonFarmingExamingObject.isSuccess()) {// 成功
                objBean = jsonFarmingExamingObject.getObj();
                if (objBean != null) {
                    Message msg = Message.obtain();
                    msg.what = FARMING_EXAMING_VALUES;
                    mHandler.sendMessage(msg);
                }
            }
        }

    }


    // -------------------农事记录查询的---所属大棚-----------------------------------

    /**
     * 所属大棚请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BelongGreenhouseRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26下午4:04:21
     */
    class BelongGreenhouseRequest extends Thread {

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
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                Log.i(TAG, TAG + "农事记录中的所属大棚的请求");
                WapiUtilEx.usergreenhouselist(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = BELONG_GREENHOUSE_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = BELONG_GREENHOUSE_SUCCESS;
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

    // 农事记录中的所属大棚的解析
    private void belonggreenhouseJson(String belonggreenjson) {
        if (TextUtils.isEmpty(belonggreenjson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonbelongGreenHouseOBject jsonHouseObject = gson.fromJson(
                belonggreenjson, JsonbelongGreenHouseOBject.class);
        if (!jsonHouseObject.equals("") && jsonHouseObject != null) {

            if (jsonHouseObject.isSuccess()) {// 成功
                greenhouseBeans = jsonHouseObject.getObj();
                Message msg = Message.obtain();
                msg.what = BELONG_GREENHOUSE_VALUES;
                mHandler.sendMessage(msg);
            }else
                TAUtils.toastMessage((Activity) mContext, jsonHouseObject.getMsg());

        }
    }

    // -------------------农事记录查询的---所属任务名称-----------------------------------

    /**
     * 所属任务名称请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BelongTaskNameRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26下午4:04:21
     */
    class BelongTaskNameRequest extends Thread {

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
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("assignStatus", "ALLOCATED");
                Log.i(TAG, TAG + "农事记录中的任务名称的请求");
                WapiUtilEx.fartaskList(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = BELONG_TASKNAME_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = BELONG_TASKNAME_SUCCESS;
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

    // 农事记录中的任务名称的解析
    private void belongtasknameJson(String belongtasknamejson) {
        if (TextUtils.isEmpty(belongtasknamejson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonBelongTaskNameObject jsonBelongTaskNameObject = gson.fromJson(
                belongtasknamejson, JsonBelongTaskNameObject.class);
        if (!jsonBelongTaskNameObject.equals("")
                && jsonBelongTaskNameObject != null) {
            if (jsonBelongTaskNameObject.isSuccess()) {// 成功
                tasknameBeans = jsonBelongTaskNameObject.getObj();
                Message msg = Message.obtain();
                msg.what = BELONG_TASKNAME_VALUES;
                mHandler.sendMessage(msg);
            }else
                TAUtils.toastMessage((Activity) mContext,
                        jsonBelongTaskNameObject.getMsg());

        }
    }

    // -------------------农事记录新增的---所属任务名称---用的是执行任务列表--------------------------------

    /**
     * 所属任务名称请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BelongExecuteTaskNameRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26下午4:04:21
     */
    class BelongExecuteTaskNameRequest extends Thread {

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
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("worktaskDoStatus", "IN_EXECUTION");// 执行中
                Log.i(TAG, TAG + "农事记录中的 执行 任务名称的请求");
                WapiUtilEx.taskExecuteList(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = BELONG_EXECUTE_TASKNAME_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = BELONG_EXECUTE_TASKNAME_SUCCESS;
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

    // 农事填报中的执行任务名称的解析
    private void belongexecutetasknameJson(String belongexecutetasknamejson) {
        if (TextUtils.isEmpty(belongexecutetasknamejson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonBelongExecuteTaskNameObject jsonBelongExecuteTaskNameObject = gson
                .fromJson(belongexecutetasknamejson,
                        JsonBelongExecuteTaskNameObject.class);
        if (!jsonBelongExecuteTaskNameObject.equals("")
                && jsonBelongExecuteTaskNameObject != null) {

            if (jsonBelongExecuteTaskNameObject.isSuccess()) {// 成功
                executetasknameBeans = jsonBelongExecuteTaskNameObject.getObj();
                Message msg = Message.obtain();
                msg.what = BELONG_EXECUTE_TASKNAME_VALUES;
                mHandler.sendMessage(msg);
            }else
                TAUtils.toastMessage((Activity) mContext,
                        jsonBelongExecuteTaskNameObject.getMsg());

        }
    }


    // -------------------农事记录新增的---根据任务名称的ID请求数据--确定工作大棚和农事分类--------------------------------

    /**
     * 根据所属执行任务名称请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BelongfarCreateRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26下午4:04:21
     */
    class BelongfarCreateRequest extends Thread {

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
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("plantingTaskId", plantingTaskId);
                Log.i(TAG, TAG + "农事记录中的 根据执行 任务ID 确定大棚和农事分类的请求");
                WapiUtilEx.fartaskDetail(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = BELONG_FAR_CATEATE_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = BELONG_FAR_CATEATE_SUCCESS;
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

    // 农事记录中的 执行 任务名称的解析
    private void belongfarcreateJson(String belongfarcreatejson) {
        if (TextUtils.isEmpty(belongfarcreatejson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonBelongfarCreateobject jsonBelongfarCreateobject = gson.fromJson(
                belongfarcreatejson, JsonBelongfarCreateobject.class);
        if (!jsonBelongfarCreateobject.equals("")
                && jsonBelongfarCreateobject != null) {

            if (jsonBelongfarCreateobject.isSuccess()) {// 成功
                JsonBelongfarCreateobject.ObjBean farCreateBean = jsonBelongfarCreateobject
                        .getObj();
                plantingTaskBean = farCreateBean.getPlantingTask();
                Message msg = Message.obtain();
                msg.what = BELONG_FAR_CATEATE_VALUES;
                mHandler.sendMessage(msg);
            }else
                TAUtils.toastMessage((Activity) mContext,
                        jsonBelongfarCreateobject.getMsg());

        }
    }

}
