package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.itserv.BaseActivity;
import com.app.itserv.adapters.BelongGreenHoustAdapter;
import com.app.itserv.adapters.BelongRecorderAdapter;
import com.app.itserv.adapters.BelongTaskNameAdapter;
import com.app.itserv.adapters.DataDictionaryAdapter;
import com.app.itserv.adapters.FarmingRecordAdapter;
import com.app.itserv.jparser.JsonBelongRecorderObject;
import com.app.itserv.jparser.JsonBelongTaskNameObject;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonFarmingRecordObject;
import com.app.itserv.jparser.JsonFarmingRecordObject.AttributesBean;
import com.app.itserv.jparser.JsonFarmingRecordObject.ObjBean;
import com.app.itserv.jparser.JsonbelongGreenHouseOBject;
import com.app.itserv.views.LoadingFrameView;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.DateTimePickDialogUtil;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.StringUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.TAUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 农事记录
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: FarmingRecordActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-21 下午5:26:02
 */
public class FarmingRecordActivity extends BaseActivity implements OnClickListener,
        OnItemClickListener {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.far_record_lay_title)
    TextView titleTextView;
    @BindView(R.id.far_record_lay_greenhouse)
    Spinner greenhouse;
    @BindView(R.id.far_record_lay_task_name)
    Spinner taskname;
    @BindView(R.id.far_record_lay_far_type)
    Spinner fartype;
    @BindView(R.id.far_recor_recorder)
    Spinner recorder;
    @BindView(R.id.far_recor_recorder_ll)
    LinearLayout recorder_ll;
    @BindView(R.id.far_record_lay_start_distri_time)
    TextView startdistritime;
    @BindView(R.id.far_record_lay_stop_distri_time)
    TextView stopdistritime;
    @BindView(R.id.far_record_lay_btn_select)
    Button select;
    @BindView(R.id.far_record_lay_changepsw_reset)
    Button changepswreset;
    @BindView(R.id.far_record_lay_listview)
    ListView listview;
    @BindView(R.id.load_view)
    LoadingFrameView loadView;
    private String strecord;// 根据它判断是从哪个页面跳转过来的
    private DateTimePickDialogUtil dateTimePicKDialog;// 时间选择器的Dialog
    private Context mContext;
    private String ghouseId;// 大棚ID
    private String taskId;// 任务ID
    private String farmingCategory;// 农事分类
    private String createBy;// 记录人
    private String createDateStart;// 记录开始时间
    private String createDateEnd;// 记录结束时间
    private List<ObjBean> objBeans = null;
    private FarmingRecordAdapter recordAdapter;
    // 农事分类的数据字典
    private List<JsonDataDictionaryObject.ObjBean> datadicObjBeans = null;
    private DataDictionaryAdapter dataDictionaryAdapter;
    private JsonDataDictionaryObject.ObjBean datadictionaryBean;
    // 所属大棚
    private List<JsonbelongGreenHouseOBject.ObjBean> greenhouseBeans = null;
    private BelongGreenHoustAdapter belongGreenHoustAdapter;
    private JsonbelongGreenHouseOBject.ObjBean greenhouseBean;
    // 任务名称
    private List<JsonBelongTaskNameObject.ObjBean> tasknameBeans = null;
    private BelongTaskNameAdapter belongTaskNameAdapter;
    private JsonBelongTaskNameObject.ObjBean tasknameBean;
    // 记录人
    private List<JsonBelongRecorderObject.ObjBean> recorderBeans = null;
    private BelongRecorderAdapter belongRecorderAdapter;
    private JsonBelongRecorderObject.ObjBean recorderBean;


    @Override
    protected int layoutViewId() {
        return R.layout.far_record_lay;
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        initView();
        setBelongGreenhouse();
        setDataDictionary();
        setBelongTaskName();
        setFramingRecordUrl();
    }

    //所属大棚
    private void setBelongGreenhouse() {
        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext,
                "tenantId");// 商户id
        HttpManager.getInstance().usergreenhouselist(TAG, token, currTenantId, new HttpCallBack<JsonbelongGreenHouseOBject>() {
            @Override
            public void onSuccess(JsonbelongGreenHouseOBject jsonHouseObject) {
                if (jsonHouseObject != null) {
                    if (jsonHouseObject.isSuccess()) {// 成功
                        greenhouseBeans = jsonHouseObject.getObj();
                        if (null == greenhouseBeans)
                            greenhouseBeans = new ArrayList<>();
                        JsonbelongGreenHouseOBject.ObjBean bean = new JsonbelongGreenHouseOBject.ObjBean();
                        bean.setId("");
                        bean.setGhouseFullname("全部");
                        greenhouseBeans.add(0, bean);
                        belongGreenHoustAdapter = new BelongGreenHoustAdapter(
                                greenhouseBeans, mContext);
                        greenhouse.setAdapter(belongGreenHoustAdapter);
                        greenhouse.setSelection(0);// 设置默认值
                        // 获取对象
                        greenhouseBean = (JsonbelongGreenHouseOBject.ObjBean) belongGreenHoustAdapter
                                .getItem(0);
                        ghouseId = greenhouseBean.getId();
                        Log.i(TAG, ghouseId + "");
                        if (!TextUtils.isEmpty(ghouseId)) {
                            if (strecord.equals("record")) {

                            } else if (strecord.equals("manage")) {// 如果是从农事管理页面跳转过来的
                                setBelongRecorder(ghouseId);
                            }
                        }
                    } else {
                        ToastUtils.makeTextShort(jsonHouseObject.getMsg());
                    }
                } else
                    ToastUtils.makeTextShort(R.string.network_error);
            }

            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort(R.string.network_error);
            }
        });
    }

    // 农事分类数据字典表
    public void setDataDictionary() {
        String key = "BP_FARMING_CATEGORY";
        HttpManager.getInstance().apiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>() {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort(R.string.network_error);
            }

            @Override
            public void onSuccess(JsonDataDictionaryObject jsonDataDictionaryObject) {
                if (jsonDataDictionaryObject != null) {
                    if (jsonDataDictionaryObject.isSuccess()) {// 成功
                        datadicObjBeans = jsonDataDictionaryObject.getObj();
                        if (null == datadicObjBeans)
                            datadicObjBeans = new ArrayList<>();
                        JsonDataDictionaryObject.ObjBean bean = new JsonDataDictionaryObject.ObjBean();
                        bean.setTypecode("");
                        bean.setTypename("全部");
                        datadicObjBeans.add(0, bean);
                        dataDictionaryAdapter = new DataDictionaryAdapter(mContext,
                                datadicObjBeans);
                        fartype.setAdapter(dataDictionaryAdapter);
                        fartype.setSelection(0);// 设置默认值
                        // 获取对象
                        datadictionaryBean = (JsonDataDictionaryObject.ObjBean) dataDictionaryAdapter
                                .getItem(0);
                        farmingCategory = TextUtils.isEmpty(datadictionaryBean
                                .getTypecode()) ? "" : datadictionaryBean.getTypecode();
                    } else
                        ToastUtils.makeTextShort(jsonDataDictionaryObject.getMsg());
                } else {
                    ToastUtils.makeTextShort(R.string.network_error);
                }
            }
        });
    }

    // 所属任务名称
    public void setBelongTaskName() {
        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext,
                "tenantId");// 商户id
        HttpManager.getInstance().fartaskList(TAG, token, currTenantId, new HttpCallBack<JsonBelongTaskNameObject>() {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort(R.string.network_error);
            }

            @Override
            public void onSuccess(JsonBelongTaskNameObject jsonBelongTaskNameObject) {
                if (jsonBelongTaskNameObject != null) {
                    if (jsonBelongTaskNameObject.isSuccess()) {// 成功
                        tasknameBeans = jsonBelongTaskNameObject.getObj();
                        if (null == tasknameBeans)
                            tasknameBeans = new ArrayList<>();
                        JsonBelongTaskNameObject.ObjBean bean = new JsonBelongTaskNameObject.ObjBean();
                        bean.setId("");
                        bean.setTaskName("全部");
                        tasknameBeans.add(0, bean);
                        belongTaskNameAdapter = new BelongTaskNameAdapter(mContext,
                                tasknameBeans);
                        taskname.setAdapter(belongTaskNameAdapter);
                        taskname.setSelection(0);// 设置默认值
                        // 获取对象
                        tasknameBean = (JsonBelongTaskNameObject.ObjBean) belongTaskNameAdapter
                                .getItem(0);
                        taskId = TextUtils.isEmpty(tasknameBean.getId()) ? ""
                                : tasknameBean.getId();
                    } else
                        ToastUtils.makeTextShort(jsonBelongTaskNameObject.getMsg());

                } else
                    ToastUtils.makeTextShort(R.string.network_error);
            }
        });
    }

    //记录人
    public void setBelongRecorder(String ghouseId) {
        if (!StringUtils.isEmpty(ghouseId)) {
            recorder_ll.setVisibility(View.VISIBLE);// 显示记录人
            String token = PreferencesUtils.getString(mContext, "token");// token
            String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id
            HttpManager.getInstance().ghouseemp(TAG, token, currTenantId, ghouseId, new HttpCallBack<JsonBelongRecorderObject>() {
                @Override
                public void onSuccess(JsonBelongRecorderObject jsonBelongRecorderObject) {
                    if (jsonBelongRecorderObject != null) {
                        if (jsonBelongRecorderObject.isSuccess()) {// 成功
                            recorderBeans = jsonBelongRecorderObject.getObj();
                            if (null == recorderBeans)
                                recorderBeans = new ArrayList<>();
                            JsonBelongRecorderObject.ObjBean bean = new JsonBelongRecorderObject.ObjBean();
                            bean.setCreateBy("");
                            bean.setUserName("全部");
                            recorderBeans.add(0, bean);
                            belongRecorderAdapter = new BelongRecorderAdapter(mContext,
                                    recorderBeans);
                            recorder.setAdapter(belongRecorderAdapter);
                            recorder.setSelection(0);// 设置默认值
                            // 获取对象
                            recorderBean = (JsonBelongRecorderObject.ObjBean) belongRecorderAdapter
                                    .getItem(0);
                            createBy = TextUtils.isEmpty(recorderBean.getCreateBy()) ? ""
                                    : recorderBean.getCreateBy();
                        } else {
                            ToastUtils.makeTextShort(jsonBelongRecorderObject.getMsg());
                        }
                    } else
                        ToastUtils.makeTextShort(R.string.network_error);
                }

                @Override
                public void onError(Throwable throwable) {
                    ToastUtils.makeTextShort(R.string.network_error);
                }
            });
        } else {
            recorder_ll.setVisibility(View.GONE);// 隐藏记录人
        }
    }

    // 农事记录列表
    private void setFramingRecordUrl() {
        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id
        HttpManager.getInstance().apiFramingRecordGetList(TAG, token, currTenantId, createBy, ghouseId, taskId, farmingCategory, createBy, createDateStart + " 00:00:00", createDateEnd + " 23:59:59", new HttpCallBack<JsonFarmingRecordObject>() {
            @Override
            public void onSuccess(final JsonFarmingRecordObject jsonFarmingRecordObject) {
                if (jsonFarmingRecordObject != null) {
                    if (jsonFarmingRecordObject.isSuccess()) {// 成功
                        AttributesBean attributesbean = jsonFarmingRecordObject.getAttributes();
                        attributesbean.getCurrUserId();
                        attributesbean.getCurrTenantId();
                        objBeans = jsonFarmingRecordObject.getObj();
                        if (objBeans.size() > 0) {
                            recordAdapter = new FarmingRecordAdapter(mContext, objBeans);
                            listview.setAdapter(recordAdapter);
                            loadView.delayShowContainer(true);
                        } else {
                            recordAdapter = new FarmingRecordAdapter(mContext, objBeans);
                            listview.setAdapter(recordAdapter);
                            loadView.setNoShown(true);
                        }
                    } else {
                        loadView.setErrorShown(true, new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.makeTextShort(jsonFarmingRecordObject.getMsg());
                            }
                        });
                    }
                } else {
                    loadView.setErrorShown(true, new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.makeTextShort(R.string.network_error);
                        }
                    });
                }
            }

            @Override
            public void onError(Throwable throwable) {
                loadView.setErrorShown(true, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.makeTextShort(R.string.network_error);
                    }
                });
            }
        });
    }

    private void initView() {
        // TODO Auto-generated method stub
        imgBack.setOnClickListener(this);
        // 创建时间选择器
        dateTimePicKDialog = new DateTimePickDialogUtil(this, DateTimePickDialogUtil.formatDate());
        startdistritime.setOnClickListener(this);
        stopdistritime.setOnClickListener(this);
        listview.setOnItemClickListener(this);
        strecord = getIntent().getExtras().getString("record");
        if (strecord.equals("record")) {
            recorder_ll.setVisibility(View.GONE);// 隐藏记录人
        } else if (strecord.equals("manage")) {// 如果是从农事管理页面跳转过来的
            titleTextView.setText("农事记录查看");
        }

        select.setOnClickListener(this);
        changepswreset.setOnClickListener(this);

        // 所属大棚 选择监听
        greenhouse.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                ghouseId = greenhouseBeans.get(position).getId();
                String ghouseIdname = greenhouseBeans.get(position)
                        .getGhouseFullname();
                Log.i(TAG, "所属大棚 名字：" + ghouseIdname + "编号：" + ghouseId);
                if (!TextUtils.isEmpty(ghouseId)) {
                    setBelongRecorder(ghouseId);
                } else {
                    recorder_ll.setVisibility(View.GONE);// 隐藏记录人
                }
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
                taskId = tasknameBeans.get(position).getId();
                String tasknameString = tasknameBeans.get(position)
                        .getTaskName();
                Log.i(TAG, "任务名称 选择监听  名字：" + tasknameString
                        + "任务ID：" + taskId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        // 农事分类 数据字典 选择监听
        fartype.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String strnameString = datadicObjBeans.get(position)
                        .getTypename();
                farmingCategory = datadicObjBeans.get(position).getTypecode();
                Log.i(TAG, "农事分类 选择监听 名字：" + strnameString
                        + "        农事分类ID：" + farmingCategory);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        // 记录人 选择监听
        recorder.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                createBy = recorderBeans.get(position).getCreateBy();
                Log.i(TAG, "记录人  选择监听：" + createBy);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        setReset();
    }

    // 重置  周作威  2017/9/20 19:37
    public void setReset() {
        startdistritime.setText(DateTimePickDialogUtil.formatDate((System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 7), "yyyy-MM-dd"));
        stopdistritime.setText(DateTimePickDialogUtil.formatDate());
        createDateStart = startdistritime.getText().toString().trim();// 记录开始时间
        createDateEnd = stopdistritime.getText().toString().trim();// 记录结束时间
        ghouseId = "";// 大棚ID
        taskId = "";// 任务ID
        farmingCategory = "";// 农事分类
        createBy = "";// 记录人
//        greenhouseBeans  datadicObjBeans  tasknameBeans  recorderBeans
        if (null != greenhouseBeans)
            greenhouse.setSelection(0);// 设置默认值
        if (null != datadicObjBeans)
            fartype.setSelection(0);// 设置默认值
        if (null != tasknameBeans)
            taskname.setSelection(0);// 设置默认值
        if (null != recorderBeans)
            recorder.setSelection(0);// 设置默认值
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
//            case R.id.add_supply:// 农事填报
//                startActivity(new Intent(mContext, FarmingExamingActivity.class)
//                        .putExtra("farrecordsupply", "farsupply"));
//                break;
            case R.id.far_record_lay_btn_select:// 查询
                frarecordgetinfo();
                break;
            case R.id.far_record_lay_changepsw_reset:// 重置
                setReset();
                setFramingRecordUrl();
                break;
            case R.id.far_record_lay_start_distri_time:// 开始时间
                dateTimePicKDialog.dateTimePicKDialog(startdistritime);
                break;
            case R.id.far_record_lay_stop_distri_time:// 结束时间
                dateTimePicKDialog.dateTimePicKDialog(stopdistritime);
                break;

            default:
                break;
        }
    }

    // 条件查询
    private void frarecordgetinfo() {
        createDateStart = startdistritime.getText().toString().trim();// 记录开始时间
        createDateEnd = stopdistritime.getText().toString().trim();// 记录结束时间

        if (strecord.equals("record")) {// 表示从普通的农事记录页面跳转过来
            createBy = "";
            if (ghouseId.isEmpty() && taskId.isEmpty()
                    && farmingCategory.isEmpty() && createDateStart.isEmpty()
                    && createDateEnd.isEmpty()) {
                TAUtils.toastMessage((Activity) mContext, "请输入查询条件");
                return;
            }
        } else if (strecord.equals("manage")) {// 如果是从农事管理页面跳转过来的
            if (ghouseId.isEmpty() && taskId.isEmpty()
                    && farmingCategory.isEmpty() && createBy.isEmpty()
                    && createDateStart.isEmpty() && createDateEnd.isEmpty()) {
                TAUtils.toastMessage((Activity) mContext, "请输入查询条件");
                return;
            }
        }

        if (!createDateStart.isEmpty() && createDateEnd.isEmpty()) {
            TAUtils.toastMessage((Activity) mContext, "请输入记录结束时间");
            return;
        }
        if (createDateStart.isEmpty() && !createDateEnd.isEmpty()) {
            TAUtils.toastMessage((Activity) mContext, "请输入记录开始时间");
            return;
        }

        if (!createDateStart.isEmpty() && !createDateEnd.isEmpty()) {
            if (isDateStringValid(createDateStart)) {

                if (createDateStart.length() == 10) {// 日期格式必须为10位 例：2014-02-24

                } else {
                    ToastUtils.makeTextShort("记录开始时间格式不对,应为：2017-05-05格式");
                    return;
                }
            } else {
                ToastUtils.makeTextShort("记录开始时间格式不对,应为：2017-05-05格式");
                return;
            }

            if (isDateStringValid(createDateEnd)) {

                if (createDateEnd.length() == 10) {// 日期格式必须为10位 例：2014-02-24

                } else {
                    ToastUtils.makeTextShort("记录结束时间格式不对,应为：2017-05-05格式");
                    return;
                }
            } else {
                ToastUtils.makeTextShort("记录结束时间格式不对,应为：2017-05-05格式");
                return;
            }
        }
        setFramingRecordUrl();
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
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        Log.i("wwwwwwwwww", "position:" + position);
        // 点击条目，查看详情
        String framingRecordId = objBeans.get(position).getId();
        Intent intent = new Intent(mContext, FarmingExamingActivity.class);
        intent.putExtra("framingRecordId", framingRecordId);// 农事记录ID
        intent.putExtra("farrecordsupply", "farrecord");
        mContext.startActivity(intent);
    }
}
