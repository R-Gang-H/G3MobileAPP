package com.app.itserv.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.itserv.adapters.MyTodayTaskAdapter;
import com.app.itserv.jparser.JsonTodayTaskObject;
import com.app.itserv.views.LoadingFrameView;
import com.app.itserv.views.PullToRefreshListView;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;

import java.util.List;

/**
 * 今日执行中任务
 *
 * @author haoruigang
 * @Package com.app.itserv.fragments
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/8/31 17:07
 */
public class MyExecuTaskFragment extends BaseFragment implements PullToRefreshListView.OnRefreshListener {

    private static final String TAG = "MyTodayTaskFragment";

    private Context mContext;

    private LoadingFrameView loadView;
    public static PullToRefreshListView myTodayTaskItems;
    public static MyTodayTaskAdapter myTodayTaskAdapter;
    private List<JsonTodayTaskObject.ObjBean> todayTaskObjList;

    @Override
    protected int layoutViewId() {
        return R.layout.my_today_task_lay;
    }


    @Override
    public void onUpdateUI() {

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
        loadView = (LoadingFrameView) getView().findViewById(R.id.load_view);
        myTodayTaskItems = (PullToRefreshListView) getView().findViewById(R.id.my_today_task_items);//当日任务列表
        myTodayTaskItems.setOnRefreshListener(this);
    }

    public void init() {
        MyTodayTaskRequest();// 当日任务请求接口
    }


    @Override
    public void onRefresh() {
        init();
    }

    /**
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: ProposalRequest
     * @Description: 我的当日任务请求线程
     * @date 2017-6-24 下午3:34:00
     */
    public void MyTodayTaskRequest() {

        String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id

        String businessType = null;
        businessType = "1";


        HttpManager.getInstance().myTodayTaskList(TAG, token, currTenantId, businessType, currUserId, new HttpCallBack<JsonTodayTaskObject>(getActivity(), true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
                myTodayTaskItems.onRefreshComplete();
                loadView.setErrorShown(true, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        init();
                    }
                });
            }

            @Override
            public void onSuccess(JsonTodayTaskObject date) {
                myTodayTaskItems.onRefreshComplete();
                if (date != null) {
                    if (date.isSuccess()) {// 成功

                        todayTaskObjList = date.getObj();

                        if (todayTaskObjList != null) {
                            myTodayTaskAdapter = new MyTodayTaskAdapter(mContext, todayTaskObjList);
                            myTodayTaskItems.setAdapter(myTodayTaskAdapter);
                            loadView.delayShowContainer(true);
                        }
                    } else {
                        loadView.setErrorShown(true, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                init();
                            }
                        });
                    }
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        MyTodayTaskRequest();
        myTodayTaskItems.deferNotifyDataSetChanged();
    }
}
