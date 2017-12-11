package com.app.itserv.activity;

import java.util.HashMap;
import java.util.Map;

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

import com.app.itserv.jparser.JsonGreenhouseImgAddObject;
import com.app.itserv.jparser.JsonGreenhouseImgAddObject.AttributesBean;
import com.app.itserv.jparser.JsonGreenhouseImgAddObject.ObjBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.RegexChk;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 上传、编辑大棚图片
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: GreenHouseImgEditActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-12 下午6:29:35
 */
public class GreenHouseImgEditActivity extends Activity implements
        OnClickListener {

    private Context mContext;

    public static final String TAG = "GreenHouseImgEditActivity";
    protected static final int GHOUSEIMGADD_ERROR = 1;
    protected static final int GHOUSEIMGADD_SUCCESS = 2;
    protected static final int GHOUSEIMGADD_VALUES = 3;
    protected static final int GHOUSEIMGEXA_SUCCESS = 4;

    private ObjBean gHouImgAdd;
    private String gHouseImgAddEdit, greenhouseId, ghouseDocId, greHouseName,
            explain, picture;

    private ImageView ImgBack, ImgPreview;
    private EditText edGreHouName, edExplain;
    private TextView tvGrehouImgTitle, tvPicture;
    private Button btChoose, btnUpload, btChanPswReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gre_hou_img_edit_lay);
        mContext = this;
        initView();
        Bundle bundle = getIntent().getExtras();
        gHouseImgAddEdit = (String) bundle.get("gHouseImgaddedit");
        greenhouseId = (String) bundle.get("greenhouseId");
        if (TextUtils.equals(gHouseImgAddEdit, "gHouseImgadd")) {// 大棚图片新增
            tvGrehouImgTitle.setText("大棚图片上传");
        } else {
            ghouseDocId = (String) getIntent().getExtras().get("ghouseDocId");
            tvGrehouImgTitle.setText("大棚图片编辑");
            new Thread(new GreenHouseImgExaRequest(ghouseDocId)).start();
        }
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);// 返回
        ImgBack.setOnClickListener(this);
        tvGrehouImgTitle = (TextView) findViewById(R.id.tv_grehou_img_title);// 标题
        edGreHouName = (EditText) findViewById(R.id.gre_hou_name);// 大棚图片名称
        edExplain = (EditText) findViewById(R.id.et_explain);// 大棚图片说明
        tvPicture = (TextView) findViewById(R.id.tv_picture);// 大棚图片路径
        btChoose = (Button) findViewById(R.id.bt_choose);// 大棚图片选择按钮
        btChoose.setOnClickListener(this);
        ImgPreview = (ImageView) findViewById(R.id.img_preview);// 大棚图片预览
        btnUpload = (Button) findViewById(R.id.btn_upload);// 上传按钮
        btnUpload.setOnClickListener(this);
        btChanPswReset = (Button) findViewById(R.id.changepsw_reset);// 重置
        btChanPswReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.bt_choose:// 选择大棚图片
                tvPicture
                        .setText("http://img01.taopic.com/160514/240378-16051410240336.jpg");
                break;
            case R.id.btn_upload:// 上传图片
                if (validator()) {
                    new Thread(new GhouseImgAddRequest(greenhouseId)).start();// 大棚图片上传请求方法
                }
                break;
            case R.id.changepsw_reset:// 重置
                resetGhouseImgText();
                break;
            default:
                break;
        }
    }

    /**
     * 非空验证
     *
     * @return boolean
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-13 上午10:04:22
     */
    private boolean validator() {
        // TODO Auto-generated method stub
        getGhouseImgText();
        if (TextUtils.isEmpty(greHouseName)) {
            TAUtils.toastMessage((Activity) mContext, "请输入大棚图片名称!");
            return false;
        }
        if (RegexChk.checkUserName(greHouseName)) {
            TAUtils.toastMessage((Activity) mContext, "大棚名称不能为特殊字符!");
            return false;
        }
        if (TextUtils.isEmpty(explain)) {
            TAUtils.toastMessage((Activity) mContext, "请输入图片说明!");
            return false;
        }
        if (RegexChk.checkUserName(explain)) {
            TAUtils.toastMessage((Activity) mContext, "大棚名称不能为特殊字符!");
            return false;
        }
        if (TextUtils.isEmpty(picture)) {
            TAUtils.toastMessage((Activity) mContext, "请选择图片路径!");
            return false;
        }
        return true;
    }

    private void getGhouseImgText() {
        greHouseName = edGreHouName.getText().toString().trim();
        explain = edExplain.getText().toString().trim();
        picture = tvPicture.getText().toString().trim();
    }

    private void resetGhouseImgText() {
        // TODO Auto-generated method stub
        edGreHouName.setText("");
        edExplain.setText("");
        tvPicture.setText("");
    }

    /**
     * 大棚图片查看json报文解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseImgExaAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午3:09:08
     */
    class GreenHouseImgExaAction extends Thread {

        private String gHouseImgExaJson;

        public GreenHouseImgExaAction(String gHouseImgExa_json) {
            // TODO Auto-generated constructor stub
            this.gHouseImgExaJson = gHouseImgExa_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(gHouseImgExaJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonGreenhouseImgAddObject gHouse_ImgAdd = gson.fromJson(
                        gHouseImgExaJson, JsonGreenhouseImgAddObject.class);

                if (!gHouse_ImgAdd.equals("") && gHouse_ImgAdd != null) {
                    TAUtils.toastMessage((Activity) mContext,
                            gHouse_ImgAdd.getMsg());
                    if (gHouse_ImgAdd.isSuccess()) {// 成功

                        AttributesBean attributesbean = gHouse_ImgAdd
                                .getAttributes();
                        attributesbean.getCurrUserId();
                        attributesbean.getCurrTenantId();

                        gHouImgAdd = gHouse_ImgAdd.getObj();

                        Message msg = Message.obtain();
                        msg.what = GHOUSEIMGADD_VALUES;
                        mHandler.sendMessage(msg);
                    }
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
     * 大棚图片新增、编辑json报文解析线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseImgAddAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12 下午6:38:55
     */
    class GreenHouseImgAddAction extends Thread {

        private String gHouseImgAddString;

        public GreenHouseImgAddAction(String gHouseImgAdd_json) {
            // TODO Auto-generated constructor stub
            this.gHouseImgAddString = gHouseImgAdd_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(gHouseImgAddString)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonGreenhouseImgAddObject gHouse_ImgAdd = gson.fromJson(
                        gHouseImgAddString, JsonGreenhouseImgAddObject.class);

                if (!gHouse_ImgAdd.equals("") && gHouse_ImgAdd != null) {
                    if (gHouse_ImgAdd.isSuccess()) {// 成功

                        AttributesBean attributesbean = gHouse_ImgAdd
                                .getAttributes();
                        attributesbean.getCurrUserId();
                        attributesbean.getCurrTenantId();

                        gHouImgAdd = gHouse_ImgAdd.getObj();

                        Message msg = Message.obtain();
                        msg.what = GHOUSEIMGADD_VALUES;
                        mHandler.sendMessage(msg);
                    }
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
                case GHOUSEIMGADD_ERROR:// 大棚图片新增请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GHOUSEIMGADD_SUCCESS:
                    String gHouseImgAdd_json = msg.obj.toString();
                    new Thread(new GreenHouseImgAddAction(gHouseImgAdd_json))
                            .start();// 大棚图片新增报文解析线程
                    break;
                case GHOUSEIMGADD_VALUES:
                    greHouseName = TextUtils.isEmpty(gHouImgAdd.getDocName()) ? ""
                            : gHouImgAdd.getDocName();
                    explain = TextUtils.isEmpty(gHouImgAdd.getContent()) ? ""
                            : gHouImgAdd.getContent();
                    picture = TextUtils.isEmpty(gHouImgAdd.getAttachment1()) ? ""
                            : gHouImgAdd.getAttachment1();
                    edGreHouName.setText(greHouseName);
                    edExplain.setText(explain);
                    tvPicture.setText(picture);
                    break;
                case GHOUSEIMGEXA_SUCCESS:
                    String gHouseImgExa_json = msg.obj.toString();
                    new Thread(new GreenHouseImgExaAction(gHouseImgExa_json))
                            .start();// 大棚图片查看报文解析线程
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 大棚图片新增、编辑json报文请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GhouseImgRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12 下午6:29:29
     */
    class GhouseImgAddRequest extends Thread {

        private String gHouseId;

        public GhouseImgAddRequest(String greenhouseId) {
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
                map.put("tenantId", currTenantId);
                map.put("ghouseId", gHouseId);
                map.put("docName", greHouseName);
                map.put("content", explain);
                map.put("attachment1", picture);
                map.put("description", explain);

                if (TextUtils.equals(gHouseImgAddEdit, "gHouseImgedit")) {// 大棚图片编辑
                    Log.v(TAG, TAG + "大棚图片编辑请求");
                    map.put("ghouseDocId", ghouseDocId);
                } else {
                    Log.v(TAG, TAG + "大棚图片新增请求");
                }

                WapiUtilEx.ghouseaddimage(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEIMGADD_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEIMGADD_SUCCESS;
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
     * 大棚图片查看请求方法
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseImgExaAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午2:59:13
     */
    class GreenHouseImgExaRequest extends Thread {

        private String ghouseDocId;

        public GreenHouseImgExaRequest(String ghouseDocId) {
            // TODO Auto-generated constructor stub
            this.ghouseDocId = ghouseDocId;
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
                map.put("ghouseDocId", ghouseDocId);

                Log.v(TAG, TAG + "大棚图片查看请求");
                WapiUtilEx.ghouseimgexa(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEIMGADD_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEIMGEXA_SUCCESS;
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

}
