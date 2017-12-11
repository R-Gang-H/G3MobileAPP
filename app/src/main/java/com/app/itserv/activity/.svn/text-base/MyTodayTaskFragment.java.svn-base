package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.app.itserv.adapters.MyTodayTaskAdapter;
import com.app.itserv.jparser.JsonTodayTaskObject;
import com.app.itserv.views.PullToRefreshListView;
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
 * 今日未领用任务
 *
 * @author haoruigang
 * @Package com.app.itserv.fragments
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/8/31 17:07
 */
public class MyTodayTaskFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String TAG = "MyTodayTaskFragment";
    private static final int TODAYTASK_ERROR = 1;
    private static final int TODAYTASK_SUCCESS = 2;
    private static final int TODAYTASK_VALUES = 3;

    private Context mContext;

    private PullToRefreshListView myTodayTaskItems;
    private MyTodayTaskAdapter myTodayTaskAdapter;
    private List<JsonTodayTaskObject.ObjBean> todayTaskObjList;

    private String title;
    public int currentItem = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_today_task_lay, null, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        initView();
        init();
    }

    private void initView() {
        // 获取Activity传递过来的参数
        Bundle mBundle = getArguments();
        title = mBundle.getString("arg");
        currentItem = mBundle.getInt("position");
        myTodayTaskItems = (PullToRefreshListView) getView().findViewById(R.id.my_today_task_items);//当日任务列表
        myTodayTaskItems.setOnItemClickListener(this);
        myTodayTaskItems.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                init();
            }
        });
    }

    public void init() {
        new Thread(new MyTodayTaskRequest()).start();// 当日任务请求接口
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TODAYTASK_ERROR:// 请求失败
                    myTodayTaskItems.onRefreshComplete();
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case TODAYTASK_SUCCESS:
                    String today_task_json = msg.obj.toString();
                    new Thread(new MyTodayTaskAction(today_task_json)).start();// 当日任务报文解析线程
                    break;
                case TODAYTASK_VALUES:
                    myTodayTaskAdapter = new MyTodayTaskAdapter(mContext, todayTaskObjList);
                    myTodayTaskItems.setAdapter(myTodayTaskAdapter);
                    myTodayTaskItems.onRefreshComplete();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, position + "");
        JsonTodayTaskObject.ObjBean todayTaskBean = todayTaskObjList.get(position - 1);//当日任务对象
        startActivity(new Intent(mContext, MyTodayTaskDetilActivity.class)
                .putExtra("farexecute", "today").putExtra("todayTaskBean",
                        todayTaskBean));// 当日任务
    }

    /**
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: ProposalRequest
     * @Description: 我的当日任务请求线程
     * @date 2017-6-24 下午3:34:00
     */
    class MyTodayTaskRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {

                Looper.prepare();

                String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id

                String businessType = null;
                businessType = "0";

                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("businessType", businessType);
                map.put("headBy", currUserId);


                WapiUtilEx.myTodayTaskList(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = TODAYTASK_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = TODAYTASK_SUCCESS;
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

    class MyTodayTaskAction extends Thread {

        private String todayTaskJson;

        public MyTodayTaskAction(String today_task_json) {
            // TODO Auto-generated constructor stub
            this.todayTaskJson = today_task_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();

                if (TextUtils.isEmpty(todayTaskJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonTodayTaskObject todayTaskObj = gson.fromJson(todayTaskJson, JsonTodayTaskObject.class);

                if (!todayTaskObj.equals("") && todayTaskObj != null) {

                    if (todayTaskObj.isSuccess()) {// 成功
                        JsonTodayTaskObject.AttributesBean attributesbean = todayTaskObj
                                .getAttributes();

                        todayTaskObjList = todayTaskObj.getObj();

                        Message msg = Message.obtain();
                        msg.what = TODAYTASK_VALUES;
                        mHandler.sendMessage(msg);
                    }else
                        TAUtils.toastMessage((Activity) mContext,
                                todayTaskObj.getMsg());
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
