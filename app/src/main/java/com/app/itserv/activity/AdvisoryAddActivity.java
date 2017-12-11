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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.app.itserv.adapters.DataDictionaryAdapter;
import com.app.itserv.jparser.JsonAddAdvisoryObject;
import com.app.itserv.jparser.JsonAddAdvisoryObject.AttributesBean;
import com.app.itserv.jparser.JsonAddAdvisoryObject.ObjBean;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.EmptyUtils;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新增咨询建议
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: AdvisoryAddActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-6-30 下午3:55:58
 */
public class AdvisoryAddActivity extends Activity implements OnClickListener {

    public static final String TAG = "AdvisoryAddActivity";

    protected static final int ADVISORY_ERROR = 1;
    protected static final int ADVISORY_SUCCESS = 2;
    // 农事分类的数据字典
    private List<JsonDataDictionaryObject.ObjBean> datadicObjBeans = null;
    private DataDictionaryAdapter dataDictionaryAdapter;

    private Context mContext;
    private ObjBean objBean;

    private ImageView imgBack;
    private Button btnSubmite, changepswReset;

    private Spinner tvissueClass;
    private EditText edtitleName, eddescExplain;

    private String issueType, titleName, descExplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisory_add_lay);
        mContext = this;
        initView();

        DataDictionaryRequest();//问题分类 数据字典表请求线程
    }

    private void init() {
        // TODO Auto-generated method stub
        new Thread(new AdvisoryAddRequest()).start();
    }

    private void initView() {
        // TODO Auto-generated method stub
        imgBack = (ImageView) findViewById(R.id.img_back);// 返回
        imgBack.setOnClickListener(this);
        tvissueClass = (Spinner) findViewById(R.id.issue_class);// 问题类型
        edtitleName = (EditText) findViewById(R.id.title_name);// 标题
        eddescExplain = (EditText) findViewById(R.id.desc_explain);// 详细内容
        btnSubmite = (Button) findViewById(R.id.btn_submite);// 提交
        btnSubmite.setOnClickListener(this);
        changepswReset = (Button) findViewById(R.id.changepsw_reset);// 重置
        changepswReset.setOnClickListener(this);

        tvissueClass.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                issueType = datadicObjBeans.get(position).getTypecode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.btn_submite:// 提交
                if (validat()) {
                    init();
                }
                break;
            case R.id.changepsw_reset:// 重置
                reset();
                break;
            default:
                break;
        }
    }

    private void reset() {
        // TODO Auto-generated method stub
        //tvissueClass.setText("");
        edtitleName.setText("");
        eddescExplain.setText("");
    }

    private Boolean validat() {
        //issueType = tvissueClass.getText().toString().trim();
        titleName = edtitleName.getText().toString().trim();
        descExplain = eddescExplain.getText().toString().trim();
        if (TextUtils.isEmpty(issueType)) {
            ToastUtils.makeTextShort("请输入问题分类");
            return false;
        }

        if (TextUtils.isEmpty(titleName)) {
            ToastUtils.makeTextShort("请输入标题");
            return false;
        }

        if (TextUtils.isEmpty(descExplain)) {
            ToastUtils.makeTextShort("请输入详细说明");
            return false;
        }

        return true;
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ADVISORY_ERROR:// 咨询建议请求失败
                    ToastUtils.makeTextShort(msg.obj.toString());
                    break;
                case ADVISORY_SUCCESS:
                    String advisory_json = msg.obj.toString();
                    AdvisoryaddAction(advisory_json);// 投诉建议报文解析线程
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 新增咨询建议json解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: AdvisoryaddAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-6-30 下午4:55:48
     */
    public void AdvisoryaddAction(String advisory_json) {
        if (TextUtils.isEmpty(advisory_json)) {
            ToastUtils.makeTextShort("服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonAddAdvisoryObject advisory_add = gson.fromJson(
                advisory_json, JsonAddAdvisoryObject.class);

        if (!advisory_add.equals("")) {
            if (advisory_add.isSuccess()) {// 成功
                objBean = advisory_add.getObj();
                if (objBean != null) {
                    finish();
                }
            } else
                ToastUtils.makeTextShort(advisory_add.getMsg());
        }
    }

    /**
     * 新增咨询建议请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: AdvisoryAddRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-6-30 下午4:04:21
     */
    class AdvisoryAddRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();
                String token = PreferencesUtils.getString(mContext, "token");// 用户token
                String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id

                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("adviceSendCategory", issueType);
                map.put("noticeTitle", titleName);
                map.put("noticeContent", descExplain);
                map.put("tenantId", currTenantId);
                Log.i(TAG, TAG + "新增咨询建议请求");

                WapiUtilEx.addAdvisory(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = ADVISORY_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = ADVISORY_SUCCESS;
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

//-------------------问题分类数据字典--------------------------------------	

    /**
     * 问题分类数据字典请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: DataDictionaryRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-22 下午4:04:21
     */
    public void DataDictionaryRequest() {

        String key = "BP_ADVICE_SEND_CATEGORY";
        Log.i(TAG, TAG + "问题分类数据字典请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(AdvisoryAddActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }

            @Override
            public void onSuccess(JsonDataDictionaryObject date) {
                Log.i(TAG, "response 问题分类数据字典请求-> " + date);
                if (EmptyUtils.isEmpty(date)) {
                    ToastUtils.makeTextShort("服务器异常请联系管理员!");
                    return;
                }
                if (date.isSuccess()) {// 成功
                    datadicObjBeans = date.getObj();
                    if (datadicObjBeans != null) {
                        dataDictionaryAdapter = new DataDictionaryAdapter(mContext, datadicObjBeans);
                        tvissueClass.setAdapter(dataDictionaryAdapter);
                    }
                } else
                    ToastUtils.makeTextShort(date.getMsg());
            }
        });
    }
}
