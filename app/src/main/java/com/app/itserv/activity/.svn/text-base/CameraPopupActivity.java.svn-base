package com.app.itserv.activity;

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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alicloud.oss.callback.ProgressCallback;
import com.alicloud.oss.model.Paramet;
import com.alicloud.oss.token.Initialicloud;
import com.alicloud.oss.token.StsToken;
import com.app.itserv.BaseActivity;
import com.app.itserv.jparser.JsonFarTaskDistrObject;
import com.app.itserv.jparser.JsonFarmingRecordCreateObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 拍照弹窗界面
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/9/8 09:51
 */
public class CameraPopupActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "CameraPopupActivity";
    private static final int UPLOAD_IMAGE_URL_SUC = 1000;

    private static final String tag = "MainActivity";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    private ImageView imageView;//拍照预览
    private Button btnCamera, btnCancelquit, btnCancel, btnComfirm;//取消
    private LinearLayout llCameraip;//拍照
    private LinearLayout llYulan;//预览

    private String toadyTaskId;
    private String myTaskDist;
    private String attachment1;
    private String attachment2;

    private ProgressBar mUploadPb;

    @Override
    protected int layoutViewId() {
        return R.layout.camera_poput_lay;
    }

    @Override
    protected void init() {
        super.init();
        Bundle bundle = getIntent().getExtras();
        toadyTaskId = (String) bundle.get("toadyTaskId");//当日任务id
        myTaskDist = bundle.getString("myTaskDist");//判断是开始还是关闭当日任务打开的拍照
        initView();
        //please init local sts server firstly. please check python/*.py for more info.
        initStsData();
    }

    private void initStsData() {
        StsToken.getInstance();//生成token及初始化
    }

    private void initView() {
        btnCamera = (Button) findViewById(R.id.btn_camera);//拍照
        btnCamera.setOnClickListener(this);
        btnCancelquit = (Button) findViewById(R.id.btn_cancelquit);//取消按钮
        btnCancelquit.setOnClickListener(this);
        llCameraip = (LinearLayout) findViewById(R.id.ll_cameraip);//拍照
        llYulan = (LinearLayout) findViewById(R.id.ll_yulan);//预览
        imageView = (ImageView) findViewById(R.id.img_camera);//拍照预览
        btnCancel = (Button) findViewById(R.id.btn_cancel);//取消后重新拍照
        btnCancel.setOnClickListener(this);
        btnComfirm = (Button) findViewById(R.id.btn_comfirm);//确定按钮
        btnComfirm.setOnClickListener(this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//获取当前日期
        String date = sdf.format(new Date());
        mUploadPb = (ProgressBar) findViewById(R.id.upload_progress);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera://拍照
                takePicture();
                break;
            case R.id.btn_cancelquit://取消
                finish();
                break;
            case R.id.btn_cancel://取消
                takePicture();
                break;
            case R.id.btn_comfirm://确定
                btnComfirm.setEnabled(false);
                if (myTaskDist.equals("myTaskStart")) {//开始任务拍照
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
                                        mUploadPb.setProgress((int) ((currentSize * 100) / totalSize));
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
                                        attachment2 = request.getObjectKey();
                                        Log.e("onSuccess", "--------->request:---" + request + "--------------->result:----body：" + body + ",StatusCode：" + StatusCode + ",Etag：" + Etag + ",responseHeader" + responseHeader + ",id：" + id);
                                        if (StatusCode == 200) {
                                            new Thread(new FramingRecordCreateInfoRequest()).start();// 农事填报请求线程
                                        }
                                    }

                                    @Override
                                    public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                                        OSSLog.logDebug("stsResponseCode", Boolean.parseBoolean(String.valueOf(clientException)));
                                        Log.e("onFailure", "---request:----" + request + ",---clientException:---" + clientException + "--serviceException:--" + serviceException + "-----------------");
                                        ToastUtils.makeTextShort("failure");
                                        mUploadPb.setProgress(0);
                                    }
                                });
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (myTaskDist.equals("myTaskStop")) {//关闭任务拍照
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
                                        mUploadPb.setProgress((int) ((currentSize * 100) / totalSize));
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
                                        attachment2 = request.getObjectKey();
                                        Log.e("onSuccess", "--------->request:---" + request.getObjectKey() + "--------------->result:----body：" + body + ",StatusCode：" + StatusCode + ",Etag：" + Etag + ",responseHeader" + responseHeader + ",id：" + id);
                                        if (StatusCode == 200) {
                                            new Thread(new FarCloseRequest()).start();// 员工关闭事任务请求线程
                                        }
                                    }

                                    @Override
                                    public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                                        OSSLog.logDebug("stsResponseCode", Boolean.parseBoolean(String.valueOf(clientException)));
                                        Log.e("onFailure", "---request:----" + request + ",---clientException:---" + clientException + "--serviceException:--" + serviceException + "-----------------");
                                        ToastUtils.makeTextShort("failure");
                                        mUploadPb.setProgress(0);
                                    }
                                });
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }


    private boolean checkNotNull(Object obj) {
        if (obj != null) {
            return true;
        }
        return false;
    }

    private String picPath;

    //拍照
    public void takePicture() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } else {
            Log.e(tag, "请确认已经插入SD卡");
        }
    }

    private Bitmap photo;
    private String filename;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                llCameraip.setVisibility(View.GONE);
                llYulan.setVisibility(View.VISIBLE);
                Uri uri = data.getData();
                if (uri != null) {
                    this.photo = BitmapFactory.decodeFile(uri.getPath());
                }
                if (this.photo == null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        this.photo = (Bitmap) bundle.get("data");
                    } else {
                        Log.e(tag, "---------拍照失败------------");
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
                    SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");
                    String strRand = String.valueOf((int) (Math.random() * 5));//5位随机数
                    filename = "Far" + (t.format(new Date())) + strRand + ".jpg";
                    // 新建文件
                    File file = new File(saveDir, filename);
                    // 打开文件输出流
                    fileOutputStream = new FileOutputStream(file);
                    // 生成图片文件
                    this.photo.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    // 相片的完整路径
                    this.picPath = file.getPath();
                    imageView.setImageBitmap(this.photo);
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
        }
    }

    // -------------------------我的农事记录新增-请求--------------------------------------------------

    // 农事记录新增
    protected static final int FARMING_CREATEINFO_ERROR = 130;
    protected static final int FARMING_CREATEINFO_SUCCESS = 14;

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
//				String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(CameraPopupActivity.this, "token");// token
                String currTenantId = PreferencesUtils.getString(CameraPopupActivity.this,
                        "tenantId");// 商户id
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//获取当前日期
                String date = sdf.format(new Date());
                sdf = new SimpleDateFormat("hh");//获取当前日期
                String time = sdf.format(new Date());

                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("taskdayId", toadyTaskId);// 农事任务id
                map.put("planDateStart", date);// 开始日期
                map.put("planTimeStart", time);// 开始时间
                map.put("planDateEnd", date);// 结束日期
                map.put("planTimeEnd", time);// 结束时间
                map.put("attachment1", attachment1);// 附件1
                map.put("workDesc", null);// 文字描述
                map.put("hasProblem", null);// 是否发现问题
                map.put("attachment2", attachment2);// 附件2
                map.put("problemDesc", null);// 问题描述
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

    // 农事新增后的对象
    private JsonFarmingRecordCreateObject.ObjBean createBean;

    private void createinfoJson(String createinfojson) {

        if (TextUtils.isEmpty(createinfojson)) {
            TAUtils.toastMessage(CameraPopupActivity.this, "服务器异常请联系管理员!");
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
                    btnComfirm.setEnabled(true);
                    finish();
                }
            }
        }
    }

    // -------------------------我的农事记录新增-请求--------------------------------------------------

    public Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPLOAD_IMAGE_URL_SUC:// 获取上传图片的路径---------------------------------
                    TAUtils.toastMessage(CameraPopupActivity.this, msg.obj.toString());
                    break;
                case FARMING_CREATEINFO_ERROR:// 农事记录新增---------------------------------
                    TAUtils.toastMessage(CameraPopupActivity.this, msg.obj.toString());
                    break;
                case FARMING_CREATEINFO_SUCCESS:
                    String createinfo_json = msg.obj.toString();// 农事记录新增后的json解析
                    createinfoJson(createinfo_json);
                    break;
                case FARTASKCLOSE_ERROR:// 农事任务关闭请求失败
                    TAUtils.toastMessage(CameraPopupActivity.this, msg.obj.toString());
                    break;
                case FARTASKCLOSE_SUCCESS:
                    String fartaskclose_json = msg.obj.toString();
                    new Thread(new FarTaskCloseAction(fartaskclose_json)).start();// 员工农事任务关闭请求解析线程
                    break;
                default:
                    break;
            }
        }
    };

    /************
     * 员工关闭农事任务
     **************/

    protected static final int FARTASKCLOSE_ERROR = 1;
    protected static final int FARTASKCLOSE_SUCCESS = 2;

    /**
     * 任务关闭请求线程
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

                String token = PreferencesUtils.getString(CameraPopupActivity.this, "token");// token
                String currTenantId = PreferencesUtils.getString(CameraPopupActivity.this,
                        "tenantId");// 商户id

                Map<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("workTaskDayId", toadyTaskId);//每日农事任务Id
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

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
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
    class FarTaskCloseAction extends Thread {

        private String fartaskCloseJson;

        public FarTaskCloseAction(String fartaskclose_json) {
            // TODO Auto-generated constructor stub
            this.fartaskCloseJson = fartaskclose_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(fartaskCloseJson)) {
                    TAUtils.toastMessage(CameraPopupActivity.this, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonFarTaskDistrObject far_task_close = gson.fromJson(
                        fartaskCloseJson, JsonFarTaskDistrObject.class);

                if (!far_task_close.equals("") && far_task_close != null) {
                    TAUtils.toastMessage(CameraPopupActivity.this,
                            far_task_close.getMsg());
                    if (far_task_close.isSuccess()) {// 成功

                        JsonFarTaskDistrObject.ObjBean farTaskCloseBean = far_task_close.getObj();
                        if (farTaskCloseBean != null) {
                            btnComfirm.setEnabled(true);
                            finish();
                        }
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
}
