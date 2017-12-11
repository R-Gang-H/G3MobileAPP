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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.itserv.adapters.BaseBelAdapter;
import com.app.itserv.adapters.BelongRecorderAdapter;
import com.app.itserv.adapters.DataDictionaryAdapter;
import com.app.itserv.adapters.GhosueBelAdapter;
import com.app.itserv.jparser.JsonBaseManagerObject;
import com.app.itserv.jparser.JsonBelongRecorderObject;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonGreenhouseManagerObject;
import com.app.itserv.jparser.JsonPlanSchemeParticularsObject;
import com.app.itserv.jparser.JsonPlanSchemeParticularsObject.AttributesBean;
import com.app.itserv.jparser.JsonPlanSchemeParticularsObject.ObjBean;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 农事种植计划管理  详情、编辑、修改、新增
 *
 * @author Administrator
 */
public class FarmingPlanAddActivity extends Activity implements OnClickListener {

    public static final String TAG = "FarmingPlanAddActivity";
    protected static final int FARMINGPLANGETINFO_ERROR = 1;//种植计划详情查看失败
    protected static final int FARMINGPLANGETINFO_SUCCESS = 2;//种植计划详情查看成功
    protected static final int FARMINGPLANGETINFO_VALUES = 3;//种植计划详情查看具体数据

    protected static final int FARMINGPLANCREATEINFO_ERROR = 4;//种植计划新增失败
    protected static final int FARMINGPLANCREATEINFO_SUCCESS = 5;//种植计划新增成功

    protected static final int FARMINGPLANUPDATEINFO_ERROR = 7;//种植计划编辑修改失败
    protected static final int FARMINGPLANUPDATEINFO_SUCCESS = 8;//种植计划编辑修改成功
    protected static final int FARMINGPLANUPDATEINFO_VALUES = 9;//种植计划编辑修改具体数据

    // 所属基地
    protected static final int BASELIST_ERROR = 10;
    protected static final int BASELIST_SUCCESS = 11;
    protected static final int BASELIST_VALUES = 12;
    private List<JsonBaseManagerObject.ObjBean> baseObjList = new ArrayList<JsonBaseManagerObject.ObjBean>();
    private BaseBelAdapter baseBleAdapter;

    // 所属大棚
    private List<JsonGreenhouseManagerObject.ObjBean> gHouseObjList = new ArrayList<JsonGreenhouseManagerObject.ObjBean>();
    private GhosueBelAdapter gHouseBleAdapter;

    //数据字典  执行阶段
    private List<JsonDataDictionaryObject.ObjBean> datadicObjBeans = new ArrayList<JsonDataDictionaryObject.ObjBean>();
    private DataDictionaryAdapter dataDictionaryAdapter;

    // 负责人
    protected static final int BELONG_RECORDER_ERROR = 19;
    protected static final int BELONG_RECORDER_SUCCESS = 20;
    protected static final int BELONG_RECORDER_VALUES = 21;
    private List<JsonBelongRecorderObject.ObjBean> recorderBeans = new ArrayList<JsonBelongRecorderObject.ObjBean>();
    private BelongRecorderAdapter belongRecorderAdapter;
    private JsonBelongRecorderObject.ObjBean recorderBean;

    private DateTimePickDialogUtil dateTimePicKDialog;//时间选择器的Dialog
    private String initDateTime;//时间选择器的Dialog

    private Context mContext;
    private ImageView ImgBack;//返回按钮
    private Button btnCopy;//复制按钮
    private TextView tvAddPlan;//新增种植计划的标题
    private Spinner basebel;//所属基地
    private Spinner greenhouse;//所属大棚
    private EditText planname;//计划名称
    private EditText plancropcode;//计划茬口编码
    private EditText plantingcrops;//种植作物
    private EditText plantingarea;//种植面积
    private TextView startplantime;//计划种植开始时间
    private TextView stopplantime;//计划种植结束时间
    private TextView startharvesttime;//预计收获开始时间
    private TextView stopharvesttime;//预计收获结束时间
    private EditText predictyield;//预计产量
    private EditText remarks;//计划内容
    private Spinner personcharge;//负责人
    private Spinner execuphase;//执行阶段

    private LinearLayout realityplantime;//实际种植时间的布局
    private LinearLayout realitypredict;//实际收获时间的布局
    private LinearLayout realityyield;//实际产量的布局
    private View realityview;//横条
    private TextView realitystartplantime;//实际种植开始时间
    private TextView realitystopplantime;//实际种植结束时间
    private TextView realitystartharvesttime;//实际收获开始时间
    private TextView realitystopharvesttime;//实际收获结束时间
    private TextView realitypredictyield;//实际产量

    private String strbasebel;//所属基地
    private String strbasebelId;//所属基地ID
    private String strgreenhouse;//所属大棚
    private String strgreenhouseId;//所属大棚ID
    private String strplanname;//计划名称
    private String strplancropcode;//计划编码
    private String strplantingcrops;//种植作物
    private String strplantingarea;//种植面积
    private String strstartplantime;//计划种植开始时间
    private String strstopplantime;//计划种植结束时间
    private String strstartharvesttime;//预计收获时间
    private String strstopharvesttime;//预计收获时间
    private String strpredictyield;//预计产量
    private String strremarks;//计划内容
    private String strpersoncharge;//负责人
    private String strpersonchargeId;//负责人Id
    private String strexecuphase;//执行阶段Code

    private String actualPlantdateStart;//实际开始种植日期
    private String actualPlantdateEnd;//实际结束种植日期
    private String actualPickdateStart;//实际开始采摘日期
    private String actualPickdateEnd;//实际结束采摘日期
    private String actualYield;//实际产量公斤


    private ObjBean objBeans;
    private String planId;//计划ID
    private String planaddedit;//判断是新增页面还是编辑页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.far_plan_add_lay);
        mContext = this;
        initView();
        //获取页面跳转 传递来的值
        planaddedit = (String) getIntent().getExtras().get("planaddedit");
        //设置标题
        if (planaddedit.equals("planadd")) {//新增
            tvAddPlan.setText("新增种植计划");
            new Thread(new BaseManagerRequest()).start();// 基地列表请求线程
            GreenHouseListRequest();// 大棚列表请求线程
            DataDictionaryRequest();//执行阶段数据字典表请求线程
        } else if (planaddedit.equals("planedit")) {//编辑
            tvAddPlan.setText("编辑种植计划");
            btnCopy.setVisibility(View.VISIBLE);
            //获取 传递过来的种植计划ID 和标题的分类
            planId = (String) getIntent().getExtras().get("planId");
            //种植计划详情查看请求
            init(planId);
        } else if (planaddedit.equals("plancopy")) {//复制
            tvAddPlan.setText("复制种植计划");
            btnCopy.setVisibility(View.GONE);
            //获取 传递过来的种植计划ID 和标题的分类
            planId = (String) getIntent().getExtras().get("planId");
            //种植计划详情查看请求
            init(planId);
        }


    }

    private void initView() {
        // 创建时间选择器
        initDateTime = DateTimePickDialogUtil.formatDate();
        dateTimePicKDialog = new DateTimePickDialogUtil(this, initDateTime);
        basebel = (Spinner) findViewById(R.id.farplanadd_base_bel);//所属基地
        greenhouse = (Spinner) findViewById(R.id.farplanadd_et_greenhouse);//所属大棚
        planname = (EditText) findViewById(R.id.farplanadd_et_plan_name);//计划名称
        plancropcode = (EditText) findViewById(R.id.farplanadd_et_plan_crop_code);//计划茬口编码
        plantingcrops = (EditText) findViewById(R.id.farplanadd_et_planting_crops);//种植作物
        plantingarea = (EditText) findViewById(R.id.farplanadd_et_planting_area);//种植面积
        startplantime = (TextView) findViewById(R.id.farplanadd_et_start_plan_time);//计划种植开始时间
        startplantime.setOnClickListener(this);
        stopplantime = (TextView) findViewById(R.id.farplanadd_et_stop_plan_time);//计划种植结束时间
        stopplantime.setOnClickListener(this);
        startharvesttime = (TextView) findViewById(R.id.farplanadd_et_start_harvest_time);//预计收获开始时间
        startharvesttime.setOnClickListener(this);
        stopharvesttime = (TextView) findViewById(R.id.farplanadd_et_stop_harvest_time);//预计收获结束时间
        stopharvesttime.setOnClickListener(this);
        predictyield = (EditText) findViewById(R.id.farplanadd_et_predict_yield);//预计产量
        remarks = (EditText) findViewById(R.id.farplanadd_et_remarks);//计划内容
        personcharge = (Spinner) findViewById(R.id.farplanadd_et_person_charge);//负责人
        execuphase = (Spinner) findViewById(R.id.farplanadd_et_execu_phase);//执行阶段

        realityplantime = (LinearLayout) findViewById(R.id.ll_reality_plan_time);//实际种植时间的布局
        realitypredict = (LinearLayout) findViewById(R.id.ll_reality_predict);//实际收获时间的布局
        realityyield = (LinearLayout) findViewById(R.id.ll_reality_yield);//实际产量的布局
        realityview = findViewById(R.id.ll_reality_view);//横条
        realitystartplantime = (TextView) findViewById(R.id.farplanadd_reality_start_plan_time);//实际种植开始时间
        realitystartplantime.setOnClickListener(this);
        realitystopplantime = (TextView) findViewById(R.id.farplanadd_reality_stop_plan_time);//实际种植结束时间
        realitystopplantime.setOnClickListener(this);
        realitystartharvesttime = (TextView) findViewById(R.id.farplanadd_reality_start_harvest_time);//实际收获开始时间
        realitystartharvesttime.setOnClickListener(this);
        realitystopharvesttime = (TextView) findViewById(R.id.farplanadd_reality_stop_harvest_time);//实际收获结束时间
        realitystopharvesttime.setOnClickListener(this);
        realitypredictyield = (TextView) findViewById(R.id.farplanadd_reality_predict_yield);//实际产量


        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);//返回按钮
        ImgBack.setOnClickListener(this);
        btnCopy = (Button) findViewById(R.id.btn_copy);//复制按钮
        btnCopy.setOnClickListener(this);
        Button btnsave = (Button) findViewById(R.id.farplanadd_btn_save);//保存按钮
        btnsave.setOnClickListener(this);
        Button changepswreset = (Button) findViewById(R.id.farplanadd_changepsw_reset);//重置按钮
        changepswreset.setOnClickListener(this);
        tvAddPlan = (TextView) findViewById(R.id.tv_add_plan);//新增种植计划的标题

        //所属基地选择监听
        basebel.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                strbasebelId = baseObjList.get(position).getId();
                Log.i("wwwwwwwwwwwwww111111111基地ID：", strbasebelId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        // 所属大棚选择监听
        greenhouse.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                strgreenhouseId = gHouseObjList.get(position).getId();
                Log.i("wwwwwwwwwwwwww111111111大棚ID：", strgreenhouseId);
                if (!TextUtils.isEmpty(strgreenhouseId)) {
                    new Thread(new BelongRecorderRequest()).start();// 负责人请求线程
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        //负责人选择器
        personcharge.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                strpersonchargeId = recorderBeans.get(position).getUserId();
                Log.i("wwwwwwwwwwwwww111111111负责人ID：", strpersonchargeId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        //执行阶段选择器
        execuphase.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                strexecuphase = datadicObjBeans.get(position).getTypecode();
                Log.i("wwwwwwwwwwwwww111111111执行阶段code：", strexecuphase);
                if (strexecuphase.equals("HARVESTED")) {
                    realityplantime.setVisibility(View.VISIBLE);//实际种植时间的布局
                    realitypredict.setVisibility(View.VISIBLE);//实际收获时间的布局
                    realityyield.setVisibility(View.VISIBLE);//实际产量的布局
                    realityview.setVisibility(View.VISIBLE);
                } else {
                    realityplantime.setVisibility(View.GONE);//实际种植时间的布局
                    realitypredict.setVisibility(View.GONE);//实际收获时间的布局
                    realityyield.setVisibility(View.GONE);//实际产量的布局
                    realityview.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void init(String planId) {
        // TODO Auto-generated method stub
        new Thread(new FarmingPlanGetInfoRequest(planId)).start();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_copy:// 复制
                ToastUtils.makeTextShort("正在复制");
                finish();
                planaddedit = "plancopy";
                Intent copyintent = new Intent(mContext, FarmingPlanAddActivity.class);
                copyintent.putExtra("planaddedit", "plancopy");
                copyintent.putExtra("planId", planId);
                startActivity(copyintent);
                break;
            case R.id.farplanadd_btn_save://保存按钮
                farmingplanadd();
                break;
            case R.id.farplanadd_changepsw_reset://重置按钮
                //basebel.getText().clear();//所属基地
                //greenhouse.getText().clear();//所属大棚
                planname.getText().clear();//计划名称
                plancropcode.getText().clear();//计划茬口编码
                plantingcrops.getText().clear();//种植作物
                plantingarea.getText().clear();//种植面积
                startplantime.setText("");//计划种植开始时间
                stopplantime.setText("");//计划种植结束时间
                startharvesttime.setText("");//预计收获开始时间
                stopharvesttime.setText("");//预计收获结束时间
                predictyield.getText().clear();//预计产量
                remarks.getText().clear();//计划内容
                //personcharge.getText().clear();//负责人
                //execuphase.getText().clear();//执行阶段
                break;
            case R.id.farplanadd_et_start_plan_time://计划种植开始时间
                dateTimePicKDialog.dateTimePicKDialog(startplantime);
                break;
            case R.id.farplanadd_et_stop_plan_time://计划种植结束时间
                dateTimePicKDialog.dateTimePicKDialog(stopplantime);
                break;
            case R.id.farplanadd_et_start_harvest_time://预计收获开始时间
                dateTimePicKDialog.dateTimePicKDialog(startharvesttime);
                break;
            case R.id.farplanadd_et_stop_harvest_time://预计收获结束时间
                dateTimePicKDialog.dateTimePicKDialog(stopharvesttime);
                break;

            case R.id.farplanadd_reality_start_plan_time://实际种植开始时间
                dateTimePicKDialog.dateTimePicKDialog(realitystartplantime);
                break;
            case R.id.farplanadd_reality_stop_plan_time://实际种植结束时间
                dateTimePicKDialog.dateTimePicKDialog(realitystopplantime);
                break;
            case R.id.farplanadd_reality_start_harvest_time://实际收获开始时间
                dateTimePicKDialog.dateTimePicKDialog(realitystartharvesttime);
                break;
            case R.id.farplanadd_reality_stop_harvest_time://实际收获结束时间
                dateTimePicKDialog.dateTimePicKDialog(realitystopharvesttime);
                break;

            default:
                break;
        }
    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FARMINGPLANGETINFO_ERROR://种植计划详情查看失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case FARMINGPLANGETINFO_SUCCESS://种植计划详情查看成功
                    String farmingplangetinfo = msg.obj.toString();
                    // 种植计划详情报文解析线程
                    FarmingPlanGetInfoJson(farmingplangetinfo);
                    break;
                case FARMINGPLANGETINFO_VALUES://种植计划详情查看具体数据
                    strbasebel = TextUtils.isEmpty(objBeans.getBaseFullname()) ? "" : objBeans.getBaseFullname();// 基地名称
                    strbasebelId = TextUtils.isEmpty(objBeans.getGbaseId()) ? "" : objBeans.getGbaseId();// 基地名称Id
                    strgreenhouse = TextUtils.isEmpty(objBeans.getGhouseFullname()) ? "" : objBeans.getGhouseFullname();// 大棚名称
                    strgreenhouseId = TextUtils.isEmpty(objBeans.getGhouseId()) ? "" : objBeans.getGhouseId();// 大棚名称Id
                    strplanname = TextUtils.isEmpty(objBeans.getPlanFullname()) ? "" : objBeans.getPlanFullname();// 计划名称
                    strplancropcode = TextUtils.isEmpty(objBeans.getPlanCode()) ? "" : objBeans.getPlanCode();// 计划茬口编码
                    strplantingcrops = TextUtils.isEmpty(objBeans.getCropCategoryDefine()) ? "" : objBeans.getCropCategoryDefine();// 种植作物
                    strplantingarea = TextUtils.isEmpty(objBeans.getPlanArea()) ? "" : objBeans.getPlanArea();// 种植面积(平米)


                    //时间格式转换
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    long lstartplantime = objBeans.getPlanPlantdateStart();//计划种植开始时间
                    if (lstartplantime != 0) {
                        Date datestartplantime = new Date(lstartplantime); // 根据long类型的毫秒数生命一个date类型的时间
                        String Sstartplantime = simpleDateFormat.format(datestartplantime); // 把date类型的时间转换为string
                        startplantime.setText(Sstartplantime);//计划种植开始时间
                    } else {
                        startplantime.setText("未知");// 计划种植开始时间
                    }

                    long lstopplantime = objBeans.getPlanPlantdateEnd();//计划种植结束时间
                    if (lstopplantime != 0) {
                        Date datestopplantime = new Date(lstopplantime); // 根据long类型的毫秒数生命一个date类型的时间
                        String Sstopplantime = simpleDateFormat.format(datestopplantime); // 把date类型的时间转换为string
                        stopplantime.setText(Sstopplantime);//计划种植结束时间
                    } else {
                        stopplantime.setText("未知");// 计划种植结束时间
                    }

                    long lstartharvesttime = objBeans.getPlanPickdateStart();// 预计种植开始时间
                    if (lstartharvesttime != 0) {
                        Date datestartharvesttime = new Date(lstartharvesttime); // 根据long类型的毫秒数生命一个date类型的时间
                        String Sstartharvesttime = simpleDateFormat.format(datestartharvesttime); // 把date类型的时间转换为string
                        startharvesttime.setText(Sstartharvesttime);// 预计种植开始时间
                    } else {
                        startharvesttime.setText("未知");// 预计种植开始时间
                    }

                    long lstopharvesttime = objBeans.getPlanPickdateEnd();// 预计种植结束时间
                    if (lstopharvesttime != 0) {
                        Date datestopharvesttime = new Date(lstopharvesttime); // 根据long类型的毫秒数生命一个date类型的时间
                        String Sstopharvesttime = simpleDateFormat.format(datestopharvesttime); // 把date类型的时间转换为string
                        stopharvesttime.setText(Sstopharvesttime);// 预计种植结束时间
                    } else {
                        stopharvesttime.setText("未知");// 预计种植结束时间
                    }

                    //strstartplantime = TextUtils.isEmpty(String.valueOf(objBeans.getPlanPlantdateStart())) ? "": String.valueOf(objBeans.getPlanPlantdateStart());// 计划种植开始时间
                    //strstopplantime = TextUtils.isEmpty(String.valueOf(objBeans.getPlanPlantdateEnd())) ? "": String.valueOf(objBeans.getActualPlantdateEnd());// 计划种植结束时间
                    //strstartharvesttime = TextUtils.isEmpty(String.valueOf(objBeans.getPlanPickdateStart())) ? "": String.valueOf(objBeans.getPlanPickdateStart());// 预计种植开始时间
                    //strstopharvesttime = TextUtils.isEmpty(String.valueOf(objBeans.getPlanPickdateEnd())) ? "": String.valueOf(objBeans.getPlanPickdateEnd());// 预计种植结束时间

                    strpredictyield = TextUtils.isEmpty(objBeans.getPlanYield()) ? "" : objBeans.getPlanYield();// 预计产量(公斤)
                    strremarks = TextUtils.isEmpty(objBeans.getContent()) ? "" : objBeans.getContent();// 种植内容
                    strpersoncharge = TextUtils.isEmpty(objBeans.getHeadName()) ? "" : objBeans.getHeadName();// 负责人
                    strpersonchargeId = TextUtils.isEmpty(objBeans.getHeadBy()) ? "" : objBeans.getHeadBy();// 负责人Id
                    strexecuphase = TextUtils.isEmpty(objBeans.getPlanStep()) ? "" : objBeans.getPlanStep();// 执行阶段
                    Log.i("222222222222", strpersoncharge + "");
                    //如果执行阶段等于收获完毕，就显示实际种植时间
                    if (strexecuphase.equals("HARVESTED")) {
                        long lacstartplantime = objBeans.getActualPlantdateStart();//实际计划种植开始时间
                        if (lacstartplantime != 0) {
                            Date dateacstartplantime = new Date(lacstartplantime); // 根据long类型的毫秒数生命一个date类型的时间
                            String Sacstartplantime = simpleDateFormat.format(dateacstartplantime); // 把date类型的时间转换为string
                            realitystartplantime.setText(Sacstartplantime);//实际计划种植开始时间
                        } else {
                            realitystartplantime.setText("未知");// 实际计划种植开始时间
                        }

                        long lacstopplantime = objBeans.getActualPlantdateEnd();//实际计划种植结束时间
                        if (lacstopplantime != 0) {
                            Date dateacstopplantime = new Date(lacstopplantime); // 根据long类型的毫秒数生命一个date类型的时间
                            String Sacstopplantime = simpleDateFormat.format(dateacstopplantime); // 把date类型的时间转换为string
                            realitystopplantime.setText(Sacstopplantime);//实际计划种植结束时间
                        } else {
                            realitystopplantime.setText("未知");// 实际计划种植结束时间
                        }

                        long lacstartharvesttime = objBeans.getActualPickdateStart();// 实际收获开始时间
                        if (lacstartharvesttime != 0) {
                            Date dateacstartharvesttime = new Date(lacstartharvesttime); // 根据long类型的毫秒数生命一个date类型的时间
                            String Sacstartharvesttime = simpleDateFormat.format(dateacstartharvesttime); // 把date类型的时间转换为string
                            realitystartharvesttime.setText(Sacstartharvesttime);// 实际收获开始时间
                        } else {
                            realitystartharvesttime.setText("未知");// 实际收获开始时间
                        }

                        long lacstopharvesttime = objBeans.getActualPickdateEnd();// 实际收获结束时间
                        if (lacstopharvesttime != 0) {
                            Date dateacstopharvesttime = new Date(lacstopharvesttime); // 根据long类型的毫秒数生命一个date类型的时间
                            String Sacstopharvesttime = simpleDateFormat.format(dateacstopharvesttime); // 把date类型的时间转换为string
                            realitystopharvesttime.setText(Sacstopharvesttime);// 实际收获结束时间
                        } else {
                            realitystopharvesttime.setText("未知");// 实际收获结束时间
                        }
                    }

                    //basebel.setText(strbasebel);//所属基地
                    //greenhouse.setText(strgreenhouse.toString().trim());//所属大棚
                    planname.setText(strplanname.toString().trim());//计划名称
                    plancropcode.setText(strplancropcode.toString().trim());//计划茬口编码
                    plantingcrops.setText(strplantingcrops.toString().trim());//种植作物
                    plantingarea.setText(strplantingarea.toString().trim());//种植面积
                    predictyield.setText(strpredictyield.toString().trim());//预计产量
                    remarks.setText(strremarks.toString().trim());//计划内容
                    for (int i = 0; i < recorderBeans.size(); i++) {//遍历基地集合，获取负责人与详情中的ID对比
                        String stuserid = recorderBeans.get(i).getUserId();//负责人ID
                        if (stuserid.equals(strpersonchargeId)) {
                            personcharge.setSelection(i);
                        }
                    }
                    for (int i = 0; i < datadicObjBeans.size(); i++) {//遍历基地集合，获取执行阶段Code与详情中的ID对比
                        String sttypecode = datadicObjBeans.get(i).getTypecode();
                        if (sttypecode.equals(strexecuphase)) {
                            execuphase.setSelection(i);
                        }
                    }

                    new Thread(new BaseManagerRequest()).start();// 基地列表请求线程
                    GreenHouseListRequest();// 大棚列表请求线程
                    DataDictionaryRequest();//执行阶段数据字典表请求线程
                    break;
                case FARMINGPLANCREATEINFO_ERROR://种植计划新增失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case FARMINGPLANCREATEINFO_SUCCESS://种植计划新增成功
                    String farmingplancreateinfo = msg.obj.toString();
                    FarmingPlanCreateInfoJson(farmingplancreateinfo);
                    break;
                case FARMINGPLANUPDATEINFO_ERROR://种植计划编辑修改失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case FARMINGPLANUPDATEINFO_SUCCESS://种植计划编辑修改成功
                    String farmingplanupdateinfo = msg.obj.toString();
                    FarmingPlanUpdateInfoJson(farmingplanupdateinfo);
                    break;
                case FARMINGPLANUPDATEINFO_VALUES://种植计划编辑修改成功后

                    break;
                case BASELIST_ERROR:// 请求失败 所属基地----------
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case BASELIST_SUCCESS:
                    String baseManager_json = msg.obj.toString();
                    new Thread(new BaseManagerAction(baseManager_json)).start();// 基地列表报文解析线程
                    break;
                case BASELIST_VALUES:
                    baseBleAdapter = new BaseBelAdapter(mContext, baseObjList);
                    // 绑定 Adapter到控件
                    basebel.setAdapter(baseBleAdapter);
                    //判断是编辑、新增、复制
                    if (planaddedit.equals("planadd")) {//新增
                        basebel.setSelection(0);//设置默认
                    } else if (planaddedit.equals("planedit")) {//编辑
                        for (int i = 0; i < baseObjList.size(); i++) {//遍历基地集合，获取基地ID与详情中的ID对比
                            String stbaseId = baseObjList.get(i).getId();
                            if (stbaseId.equals(strbasebelId)) {
                                basebel.setSelection(i);
                            }
                        }
                    } else if (planaddedit.equals("plancopy")) {//复制
                        for (int i = 0; i < baseObjList.size(); i++) {//遍历基地集合，获取基地ID与详情中的ID对比
                            String stbaseId = baseObjList.get(i).getId();
                            if (stbaseId.equals(strbasebelId)) {
                                basebel.setSelection(i);
                            }
                        }
                    }
                    break;
                case BELONG_RECORDER_ERROR:// 负责人---------------------------------------
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case BELONG_RECORDER_SUCCESS:
                    String belongrecorder_json = msg.obj.toString();// 种植计划负责人解析
                    belongrecorderJson(belongrecorder_json);
                    break;
                case BELONG_RECORDER_VALUES:
                    //设置适配器
                    belongRecorderAdapter = new BelongRecorderAdapter(mContext, recorderBeans);
                    personcharge.setAdapter(belongRecorderAdapter);
                    //判断是编辑、新增、复制
                    if (planaddedit.equals("planadd")) {//新增
                        personcharge.setSelection(0);//设置默认
                    } else if (planaddedit.equals("planedit")) {//编辑
                        for (int i = 0; i < recorderBeans.size(); i++) {//遍历基地集合，获取负责人与详情中的ID对比
                            String stuserid = recorderBeans.get(i).getUserId();//负责人ID
                            if (stuserid.equals(strpersonchargeId)) {
                                personcharge.setSelection(i);
                            }
                        }
                    } else if (planaddedit.equals("plancopy")) {//复制
                        for (int i = 0; i < recorderBeans.size(); i++) {//遍历基地集合，获取负责人与详情中的ID对比
                            String stuserid = recorderBeans.get(i).getUserId();//负责人ID
                            if (stuserid.equals(strpersonchargeId)) {
                                personcharge.setSelection(i);
                            }
                        }
                    }

                    break;
                default:
                    break;
            }
        }


    };

    //种植计划  新增 和 编辑修改 判断数据 的方法
    private void farmingplanadd() {


        if (TextUtils.isEmpty(strbasebelId)) {//所属基地ID
            TAUtils.toastMessage((Activity) mContext, "所属基地不能为空");
            return;
        }
        if (TextUtils.isEmpty(strgreenhouseId)) {//所属大棚ID
            TAUtils.toastMessage((Activity) mContext, "所属大棚不能为空");
            return;
        }
        if (TextUtils.isEmpty(planname.getText().toString().trim())) {//计划名称
            TAUtils.toastMessage((Activity) mContext, "计划名称不能为空");
            return;
        }
        if (TextUtils.isEmpty(plancropcode.getText().toString().trim())) {//计划茬口编码
            TAUtils.toastMessage((Activity) mContext, "计划茬口编码不能为空");
            return;
        }
        if (TextUtils.isEmpty(plantingcrops.getText().toString().trim())) {//种植作物
            TAUtils.toastMessage((Activity) mContext, "种植作物不能为空");
            return;
        }
        if (TextUtils.isEmpty(plantingarea.getText().toString().trim())) {//种植面积
            TAUtils.toastMessage((Activity) mContext, "种植面积不能为空");
            return;
        }
        if (TextUtils.isEmpty(startplantime.getText().toString().trim())) {//计划种植开始时间
            TAUtils.toastMessage((Activity) mContext, "计划种植开始时间不能为空");
            return;
        } else {
            if (isDateStringValid(startplantime.getText().toString().trim())) {

                if (startplantime.getText().toString().trim().length() == 10) {//日期格式必须为10位  例：2014-02-24

                } else {
                    ToastUtils.makeTextShort("计划种植开始时间格式不对,应为：2017-05-05格式");
                    return;
                }
            } else {
                ToastUtils.makeTextShort("计划种植开始时间格式不对,应为：2017-05-05格式");
                return;
            }

        }
        if (TextUtils.isEmpty(stopplantime.getText().toString().trim())) {//计划种植结束时间
            TAUtils.toastMessage((Activity) mContext, "计划种植结束时间不能为空");
            return;
        } else {
            if (isDateStringValid(stopplantime.getText().toString().trim())) {

                if (stopplantime.getText().toString().trim().length() == 10) {//日期格式必须为10位  例：2014-02-24

                } else {
                    ToastUtils.makeTextShort("计划种植结束时间格式不对,应为：2017-05-05格式");
                    return;
                }
            } else {
                ToastUtils.makeTextShort("计划种植结束时间格式不对,应为：2017-05-05格式");
                return;
            }

        }

        if (TextUtils.isEmpty(startharvesttime.getText().toString().trim())) {//预计收获开始时间
            TAUtils.toastMessage((Activity) mContext, "预计收获开始时间不能为空");
            return;
        } else {
            if (isDateStringValid(startharvesttime.getText().toString().trim())) {

                if (startharvesttime.getText().toString().trim().length() == 10) {//日期格式必须为10位  例：2014-02-24

                } else {
                    ToastUtils.makeTextShort("预计收获开始时间格式不对,应为：2017-05-05格式");
                    return;
                }
            } else {
                ToastUtils.makeTextShort("预计收获开始时间格式不对,应为：2017-05-05格式");
                return;
            }

        }
        if (TextUtils.isEmpty(stopharvesttime.getText().toString().trim())) {//预计收获结束时间
            TAUtils.toastMessage((Activity) mContext, "预计收获结束时间不能为空");
            return;
        } else {
            if (isDateStringValid(stopharvesttime.getText().toString().trim())) {

                if (stopharvesttime.getText().toString().trim().length() == 10) {//日期格式必须为10位  例：2014-02-24

                } else {
                    ToastUtils.makeTextShort("预计收获结束时间格式不对,应为：2017-05-05格式");
                    return;
                }
            } else {
                ToastUtils.makeTextShort("预计收获开始结束格式不对,应为：2017-05-05格式");
                return;
            }

        }
        if (TextUtils.isEmpty(predictyield.getText().toString().trim())) {//预计产量
            TAUtils.toastMessage((Activity) mContext, "预计产量不能为空");
            return;
        }
        if (TextUtils.isEmpty(remarks.getText().toString().trim())) {//计划内容
            TAUtils.toastMessage((Activity) mContext, "计划内容不能为空");
            return;
        }
        if (TextUtils.isEmpty(strpersonchargeId)) {//负责人
            TAUtils.toastMessage((Activity) mContext, "负责人不能为空");
            return;
        }
        if (TextUtils.isEmpty(strexecuphase)) {//执行阶段
            TAUtils.toastMessage((Activity) mContext, "执行阶段不能为空");
            return;
        }
        if (strexecuphase.equals("HARVESTED")) {
            actualPlantdateStart = realitystartplantime.getText().toString().trim();//实际开始种植日期
            actualPlantdateEnd = realitystopplantime.getText().toString().trim();//实际结束种植日期
            actualPickdateStart = realitystartharvesttime.getText().toString().trim();//实际开始采摘日期
            actualPickdateEnd = realitystopharvesttime.getText().toString().trim();//实际结束采摘日期
            actualYield = realitypredictyield.getText().toString().trim();//实际产量公斤
        } else {
            actualPlantdateStart = "";//实际开始种植日期
            actualPlantdateEnd = "";//实际结束种植日期
            actualPickdateStart = "";//实际开始采摘日期
            actualPickdateEnd = "";//实际结束采摘日期
            actualYield = "";//实际产量公斤
        }


        if (planaddedit.equals("planadd")) {//新增种植计划
            //创建线程，新增请求
            new Thread(new FarmingPlanCreateInfoRequest()).start();
        } else if (planaddedit.equals("planedit")) {//编辑种植计划
            //创建线程，编辑修改请求
            new Thread(new FarmingPlanUpdateInfoRequest()).start();
        } else if (planaddedit.equals("plancopy")) {//复制种植计划
            //创建线程，新增请求
            new Thread(new FarmingPlanCreateInfoRequest()).start();
        }

    }

    /**
     * 判断字符串是否为日期格式
     *
     * @param date
     * @return
     */
    public boolean isDateStringValid(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        // 输入对象不为空
        try {
            sdf.parse(date);
            return true;
        } catch (java.text.ParseException e) {
            return false;
        }
    }

// --------------------种植计划 编辑  修改 请求线程--和解析json-----------------------------------------------

    /**
     * 种植计划 编辑  修改请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: FarmingPlanGetInfoRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午4:04:21
     */
    class FarmingPlanUpdateInfoRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();
//                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);// 用户id（通过登录接口获取）
                map.put("currTenantId", currTenantId);// 商户id（通过登录接口获取）
                map.put("tenantId", currTenantId);// 商户id

                map.put("planId", planId);// 计划id	编辑要用
                map.put("gbaseId", strbasebelId);// 基地id
                map.put("ghouseId", strgreenhouseId);// 大棚id

                map.put("planFullname", planname.getText().toString().trim());// 计划名称
                map.put("planCode", plancropcode.getText().toString().trim());// 计划编号
                map.put("cropCategoryDefine", plantingcrops.getText().toString().trim());// 种植作物
                map.put("planArea", plantingarea.getText().toString().trim());// 种植面积平米
                map.put("planPlantdateStart", startplantime.getText().toString().trim());// 计划开始种植日期
                map.put("planPlantdateEnd", stopplantime.getText().toString().trim());// 计划结束种植日期
                map.put("planPickdateStart", startharvesttime.getText().toString().trim());// 计划开始采摘日期
                map.put("planPickdateEnd", stopharvesttime.getText().toString().trim());// 计划结束采摘日期
                map.put("planYield", predictyield.getText().toString().trim());// 预计产量公斤
                map.put("content", remarks.getText().toString().trim());// 计划描述
                map.put("headBy", strpersonchargeId);// 负责人id
                map.put("planStep", strexecuphase);// 执行阶段
                map.put("actualPlantdateStart", actualPlantdateStart);//实际开始种植日期
                map.put("actualPlantdateEnd", actualPlantdateEnd);//实际结束种植日期
                map.put("actualPickdateStart", actualPickdateStart);//实际开始采摘日期
                map.put("actualPickdateEnd", actualPickdateEnd);//实际结束采摘日期
                map.put("actualYield", actualYield);//实际产量公斤
                Log.i(TAG, TAG + "种植编辑修改请求");
                WapiUtilEx.planschemeUpdateInfo(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = FARMINGPLANUPDATEINFO_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = FARMINGPLANUPDATEINFO_SUCCESS;
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

    // 种植计划 编辑修改 成功后的json解析
    private void FarmingPlanUpdateInfoJson(String farmingplanupdateinfo) {
        if (TextUtils.isEmpty(farmingplanupdateinfo)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonPlanSchemeParticularsObject jsonPlanSchemeParticularsObject = gson
                .fromJson(farmingplanupdateinfo, JsonPlanSchemeParticularsObject.class);
        if (!jsonPlanSchemeParticularsObject.equals("")
                && jsonPlanSchemeParticularsObject != null) {

            if (jsonPlanSchemeParticularsObject.isSuccess()) {// 成功
                finish();
            } else
                TAUtils.toastMessage((Activity) mContext,
                        jsonPlanSchemeParticularsObject.getMsg());
        }

    }

// --------------------种植计划 新增请求线程--和 解析json-----------------------------------------------

    /**
     * 种植计划 新增 请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: FarmingPlanGetInfoRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午4:04:21
     */
    class FarmingPlanCreateInfoRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();
//                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);//用户id（通过登录接口获取）
                map.put("currTenantId", currTenantId);//商户id（通过登录接口获取）
                map.put("tenantId", currTenantId);//商户id

                map.put("gbaseId", strbasebelId);//基地id
                map.put("ghouseId", strgreenhouseId);//大棚id
                map.put("planFullname", planname.getText().toString().trim());//计划名称
                map.put("planCode", plancropcode.getText().toString().trim());//计划编号
                map.put("cropCategoryDefine", plantingcrops.getText().toString().trim());//种植作物
                map.put("planArea", plantingarea.getText().toString().trim());//种植面积平米
                map.put("planPlantdateStart", startplantime.getText().toString().trim());//计划开始种植日期
                map.put("planPlantdateEnd", stopplantime.getText().toString().trim());//计划结束种植日期
                map.put("planPickdateStart", startharvesttime.getText().toString().trim());//计划开始采摘日期
                map.put("planPickdateEnd", stopharvesttime.getText().toString().trim());//计划结束采摘日期
                map.put("planYield", predictyield.getText().toString().trim());//预计产量公斤
                map.put("content", remarks.getText().toString().trim());//计划描述
                map.put("headBy", strpersonchargeId);//负责人id
                map.put("planStep", strexecuphase);//执行阶段

                map.put("actualPlantdateStart", actualPlantdateStart);//实际开始种植日期
                map.put("actualPlantdateEnd", actualPlantdateEnd);//实际结束种植日期
                map.put("actualPickdateStart", actualPickdateStart);//实际开始采摘日期
                map.put("actualPickdateEnd", actualPickdateEnd);//实际结束采摘日期
                map.put("actualYield", actualYield);//实际产量公斤
                Log.i(TAG, TAG + "种植计划新增请求");
                WapiUtilEx.planschemeCreateInfo(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = FARMINGPLANCREATEINFO_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = FARMINGPLANCREATEINFO_SUCCESS;
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


    //种植计划新增成功后的json解析
    private void FarmingPlanCreateInfoJson(String farmingplancreateinfo) {
        if (TextUtils.isEmpty(farmingplancreateinfo)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonPlanSchemeParticularsObject jsonPlanSchemeParticularsObject = gson
                .fromJson(farmingplancreateinfo,
                        JsonPlanSchemeParticularsObject.class);
        if (!jsonPlanSchemeParticularsObject.equals("")
                && jsonPlanSchemeParticularsObject != null) {

            if (jsonPlanSchemeParticularsObject.isSuccess()) {// 成功
                finish();
            } else
                TAUtils.toastMessage((Activity) mContext,
                        jsonPlanSchemeParticularsObject.getMsg());
            objBeans = jsonPlanSchemeParticularsObject.getObj();
        }

    }


//--------------------种植计划 详情 请求线程--和 解析json-----------------------------------------------	

    /**
     * 种植计划 详情 请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: FarmingPlanGetInfoRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午4:04:21
     */
    class FarmingPlanGetInfoRequest extends Thread {
        private String planId;

        public FarmingPlanGetInfoRequest(String planId) {
            super();
            this.planId = planId;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();
//                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("planId", planId);
                Log.i(TAG, TAG + "种植计划详情请求");
                WapiUtilEx.planschemeGetInfo(map, new MYCallBack() {
                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = FARMINGPLANGETINFO_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = FARMINGPLANGETINFO_SUCCESS;
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

    /**
     * 解析种植计划详情json串
     *
     * @param farmingplangetinfo
     */
    private void FarmingPlanGetInfoJson(String farmingplangetinfo) {
        // TODO Auto-generated method stub
        if (TextUtils.isEmpty(farmingplangetinfo)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }

        Gson gson = new Gson();
        JsonPlanSchemeParticularsObject jsonPlanSchemeParticularsObject = gson.fromJson(farmingplangetinfo, JsonPlanSchemeParticularsObject.class);

        if (!jsonPlanSchemeParticularsObject.equals("") && jsonPlanSchemeParticularsObject != null) {

            if (jsonPlanSchemeParticularsObject.isSuccess()) {// 成功
                objBeans = jsonPlanSchemeParticularsObject.getObj();
                Message msg = Message.obtain();
                msg.what = FARMINGPLANGETINFO_VALUES;
                mHandler.sendMessage(msg);
            } else
                TAUtils.toastMessage((Activity) mContext, jsonPlanSchemeParticularsObject.getMsg());

        }
    }

    /**
     * 基地列表请求线程
     *
     * @author cyq
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BaseManagerRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-31 下午6:55:14
     */
    public class BaseManagerRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();
//                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id
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
     * @author cyq
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BaseManagerAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-31下午7:01:12
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
        Log.v(TAG, TAG + "大棚列表请求");
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

                            //设置大棚适配器
                            gHouseBleAdapter = new GhosueBelAdapter(mContext, gHouseObjList);
                            // 绑定 Adapter到控件
                            greenhouse.setAdapter(gHouseBleAdapter);
                            //判断是编辑、新增、复制
                            if (planaddedit.equals("planadd")) {//新增
                                greenhouse.setSelection(0);//设置默认
                            } else if (planaddedit.equals("planedit")) {//编辑
                                for (int i = 0; i < gHouseObjList.size(); i++) {//遍历基地集合，获取大棚ID与详情中的ID对比
                                    String stgreenhouseId = gHouseObjList.get(i).getId();
                                    if (stgreenhouseId.equals(strgreenhouseId)) {
                                        greenhouse.setSelection(i);
                                    }
                                }
                            } else if (planaddedit.equals("plancopy")) {//复制
                                for (int i = 0; i < gHouseObjList.size(); i++) {//遍历基地集合，获取大棚ID与详情中的ID对比
                                    String stgreenhouseId = gHouseObjList.get(i).getId();
                                    if (stgreenhouseId.equals(strgreenhouseId)) {
                                        greenhouse.setSelection(i);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

//-------------------种植计划数据字典---------执行阶段 种植阶段-----------------------------

    /**
     * 执行阶段数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: DataDictionaryRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-22 下午4:04:21
     */
    public void DataDictionaryRequest() {

        String key = "SP_PLANTING_STEP";
        Log.i(TAG, TAG + "种植计划中的执行阶段数据字典请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(FarmingPlanAddActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }

            @Override
            public void onSuccess(JsonDataDictionaryObject date) {
                Log.i(TAG, "response 执行阶段数据字典请求-> " + date);
                if (TextUtils.isEmpty(date.toString())) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }
                if (!date.equals("") && date != null) {
                    if (date.isSuccess()) {// 成功
                        datadicObjBeans = date.getObj();
                        //设置适配器
                        dataDictionaryAdapter = new DataDictionaryAdapter(mContext, datadicObjBeans);
                        execuphase.setAdapter(dataDictionaryAdapter);
                        //判断是编辑、新增、复制
                        if (planaddedit.equals("planadd")) {//新增
                            execuphase.setSelection(0);//设置默认
                        } else if (planaddedit.equals("planedit")) {//编辑
                            for (int i = 0; i < datadicObjBeans.size(); i++) {//遍历基地集合，获取执行阶段Code与详情中的ID对比
                                String sttypecode = datadicObjBeans.get(i).getTypecode();
                                if (sttypecode.equals(strexecuphase)) {
                                    execuphase.setSelection(i);
                                }
                            }
                        } else if (planaddedit.equals("plancopy")) {//复制
                            for (int i = 0; i < datadicObjBeans.size(); i++) {//遍历基地集合，获取执行阶段Code与详情中的ID对比
                                String sttypecode = datadicObjBeans.get(i).getTypecode();
                                if (sttypecode.equals(strexecuphase)) {
                                    execuphase.setSelection(i);
                                }
                            }
                        }
                    }
                }
            }
        });

    }


    //-------------------种植计划 编辑 新增 复制---负责人-----------------------------------

    /**
     * 负责人请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: BelongRecorderRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-26下午4:04:21
     */
    class BelongRecorderRequest extends Thread {

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
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                Log.i("kkkk大棚ID：", strgreenhouseId);
                map.put("greenhouseId", strgreenhouseId);//大棚ID
                Log.i(TAG, TAG + "种植计划编辑、新增、复制中的负责人的请求");
                WapiUtilEx.ghouseemp(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = BELONG_RECORDER_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = BELONG_RECORDER_SUCCESS;
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

    // 负责人的解析
    private void belongrecorderJson(String belongrecorderjson) {
        if (TextUtils.isEmpty(belongrecorderjson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonBelongRecorderObject jsonBelongRecorderObject = gson.fromJson(
                belongrecorderjson, JsonBelongRecorderObject.class);
        if (!jsonBelongRecorderObject.equals("")
                && jsonBelongRecorderObject != null) {

            if (jsonBelongRecorderObject.isSuccess()) {// 成功
                recorderBeans = jsonBelongRecorderObject.getObj();
                Message msg = Message.obtain();
                msg.what = BELONG_RECORDER_VALUES;
                mHandler.sendMessage(msg);
            } else
                TAUtils.toastMessage((Activity) mContext,
                        jsonBelongRecorderObject.getMsg());

        }
    }

}
