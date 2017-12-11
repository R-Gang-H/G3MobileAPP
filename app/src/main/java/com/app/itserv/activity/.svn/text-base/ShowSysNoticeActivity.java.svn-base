package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.app.itserv.jparser.JsonMySysNoticeDetailObject;
import com.app.itserv.jparser.JsonMySysNoticeDetailObject.ObjBean;
import com.app.itserv.jparser.JsonMySysNoticeSetObject;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统公告详情
 */
public class ShowSysNoticeActivity extends Activity implements OnClickListener {

    public static final String TAG = "ShowSysNoticeActivity";
    protected static final int SYSNOTICEDETAILS_ERROR = 1;
    protected static final int SYSNOTICEDETAILS_SUCCESS = 2;
    protected static final int SYSNOTICEDETAILS_VALUES = 3;
    protected static final int READSYSNOTICESET_ERROR = 4;
    protected static final int READSYSNOTICESET_SUCCESS = 5;
    protected static final int READSYSNOTICESET_VALUES = 6;
    private TextView sysnoticetitle, sysnoticetype, sysnoticecontent,
            readdeadline, creator, creattime;
    private String strId;
    private Context mContext;
    private ObjBean objBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_show_sys_notice);
        mContext = this;
        initView();
        Intent intent = getIntent();
        strId = intent.getExtras().getString("strId");// ID
        String name = intent.getExtras().getString("name");
        Log.i("strIdqqqqqqqqqqqq", strId);
        init(name);
    }

    private void init(String name) {
        if (name.equals("unread")) {
            new Thread(new NoticeControllerGetInfo()).start();// 系统公告详情请求
            new Thread(new MySysReadNoticeSetRequest()).start();// 系统公告设为已读请求
        } else if (name.equals("read")) {
            new Thread(new NoticeControllerGetInfo()).start();// 系统公告详情请求
        }

    }

    private void initView() {
        View backView = findViewById(R.id.btn_back);// 返回
        TextView titleTextView = (TextView) findViewById(R.id.title_txt);// 标题
        sysnoticetitle = (TextView) findViewById(R.id.tv_sysnotice_title);// 公告标题
        sysnoticetype = (TextView) findViewById(R.id.tv_sysnotice_type);// 公告类型
        sysnoticecontent = (TextView) findViewById(R.id.tv_sysnotice_content);// 公告内容
        readdeadline = (TextView) findViewById(R.id.tv_sysnotice_readdeadline);// 阅读期限
        creator = (TextView) findViewById(R.id.tv_sysnotice_creator);// 创建者
        creattime = (TextView) findViewById(R.id.tv_sysnotice_creattime);// 创建时间
        titleTextView.setText("公告详情");

        backView.setOnClickListener(this);

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SYSNOTICEDETAILS_ERROR:// 系统公告详情请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case SYSNOTICEDETAILS_SUCCESS:// 系统公告详情请求成功
                    String details_json = msg.obj.toString();
                    sysnoticedetailsJson(details_json);// 系统公告详情解析json
                    break;
                case SYSNOTICEDETAILS_VALUES:// 解析成功赋值

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    long createtime = objBean.getCreateTime();
                    Log.i("sssssssss", createtime + "");
                    if (createtime != 0) {
                        Date datecreatetime = new Date(createtime); // 根据long类型的毫秒数生命一个date类型的时间
                        String Screatetime = simpleDateFormat
                                .format(datecreatetime); // 把date类型的时间转换为string
                        creattime.setText(Screatetime + "");// 创建时间
                    } else {
                        creattime.setText("未知");// 创建时间
                    }
                    long noticeterm = objBean.getNoticeTerm();
                    Log.i("sssssssss", noticeterm + "");
                    if (noticeterm != 0) {
                        Date datenoticeterm = new Date(noticeterm); // 根据long类型的毫秒数生命一个date类型的时间
                        String Snoticeterm = simpleDateFormat
                                .format(datenoticeterm); // 把date类型的时间转换为string
                        readdeadline.setText(Snoticeterm + "");// 阅读期限
                    } else {
                        readdeadline.setText("未知");// 阅读期限
                    }

                    String strnoticetitle = objBean.getNoticeTitle();
                    if (strnoticetitle.isEmpty() || strnoticetitle == null
                            || strnoticetitle.equals("") || strnoticetitle == "") {
                        sysnoticetitle.setText("未知");// 公告标题
                    } else {

                        sysnoticetitle.setText(strnoticetitle);// 公告标题
                    }

                    String Snoticetype = objBean.getNoticeType();
                    if (Snoticetype.equals("1")) {
                        sysnoticetype.setText("通知");// 公告类型
                    } else if (Snoticetype.equals("2")) {
                        sysnoticetype.setText("公告");// 公告类型
                    } else {
                        sysnoticetype.setText("其它");// 公告类型
                    }


                    if (!TextUtils.isEmpty(objBean.getNoticeContent())) {
                        String string = objBean.getNoticeContent();
                        sysnoticecontent.setText(Html.fromHtml(string));// 公告内容
                        Log.i("bbbbbbb", string + "wwwwwww");
                    } else {
                        sysnoticecontent.setText("未知");// 公告内容
                        //Log.i("vvvvvvvvvvv", string+"wwwwwww");
                    }

                    String createnameString = null;
                    createnameString = objBean.getCreateUser() + "";
                    if (createnameString.isEmpty()
                            || createnameString.equals("null")
                            || createnameString == null
                            || createnameString.equals("")) {
                        creator.setText("未知");// 创建者
                    } else {
                        creator.setText("" + createnameString);// 创建者
                    }

                    break;
                case READSYSNOTICESET_ERROR:// 系统公告设为已读请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case READSYSNOTICESET_SUCCESS:// 系统公告设置已读请求成功
                    String sysnoticeset_json = msg.obj.toString();
                    myreadsysnoticesetjson(sysnoticeset_json);
                    break;
                case READSYSNOTICESET_VALUES:

                    break;
                default:
                    break;
            }
        }

    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;

            default:
                break;
        }

    }

    /**
     * 系统公告详情请求线程
     *
     * @author cyq
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: NoticeControllerGetInfo
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-6-30 下午4:04:21
     */
    class NoticeControllerGetInfo extends Thread {
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
                map.put("userAdviceId", strId);
                Log.i(TAG, TAG + "系统公告详情请求");
                WapiUtilEx.apiNoticeControllerGetInfo(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = SYSNOTICEDETAILS_ERROR;// details
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = SYSNOTICEDETAILS_SUCCESS;
                        msg.obj = response;
                        Log.i("aaaaaaaaaaa", response.toString());
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
     * 解析json串
     *
     * @param detailsjson
     */
    private void sysnoticedetailsJson(String detailsjson) {
        if (TextUtils.isEmpty(detailsjson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }
        Gson gson = new Gson();
        JsonMySysNoticeDetailObject detailsobj = gson.fromJson(detailsjson,
                JsonMySysNoticeDetailObject.class);
        if (!detailsobj.equals("") && detailsobj != null) {

            if (detailsobj.isSuccess()) {// 成功
                objBean = detailsobj.getObj();

                Message msg = Message.obtain();
                msg.what = SYSNOTICEDETAILS_VALUES;
                mHandler.sendMessage(msg);
            }else
                TAUtils.toastMessage((Activity) mContext, detailsobj.getMsg());


        }

    }

    // -------------------系统公告 设为已读----------------------------

    /**
     * 系统公告设为已读请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: MySysReadNoticeSetRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-19 下午4:04:21
     */
    class MySysReadNoticeSetRequest extends Thread {
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
                map.put("userAdviceIds", strId);// userAdviceIds
                // 公告Id（格式：公告id1,公告id2）公告Id以为逗号隔开
                Log.i(TAG, TAG + "系统公告设为已读列表请求");
                WapiUtilEx.apiNoticeControllerSet(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = READSYSNOTICESET_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = READSYSNOTICESET_SUCCESS;
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
     * 系统公告设为已读json解析
     *
     * @param sysnoticesetjson
     */
    private void myreadsysnoticesetjson(String sysnoticesetjson) {
        if (TextUtils.isEmpty(sysnoticesetjson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }

        Gson gson = new Gson();
        JsonMySysNoticeSetObject jsonMySysNoticesetObject = gson.fromJson(
                sysnoticesetjson, JsonMySysNoticeSetObject.class);

        if (!jsonMySysNoticesetObject.equals("")
                && jsonMySysNoticesetObject != null) {

            if (jsonMySysNoticesetObject.isSuccess()) {// 成功
                Message msg = Message.obtain();
                msg.what = READSYSNOTICESET_VALUES;
                mHandler.sendMessage(msg);
            }else
                TAUtils.toastMessage((Activity) mContext,
                        jsonMySysNoticesetObject.getMsg());


        }

    }

}
