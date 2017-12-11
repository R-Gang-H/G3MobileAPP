package com.app.itserv.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.commons.ToUIEvent;
import com.app.itserv.activity.RealMonitorActivity;
import com.app.itserv.activity.gHRealMonitorActivity;
import com.app.itserv.adapters.gHGateAdapter;
import com.app.itserv.jparser.JsonGreenhouseManagerObject;
import com.app.itserv.views.LoadingFrameView;
import com.app.itserv.views.PullToRefreshListView;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 监控主界面
 * 2017年9月13日16:40:02
 *
 * @author jcy
 */
public class GHMonitoringFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    @BindView(R.id.column_list)
    PullToRefreshListView columnList;
    @BindView(R.id.ld_monitor_view)
    LoadingFrameView ldMonitorView;
    @BindView(R.id.tv_nbiot_btn)
    TextView tvNbiotBtn;
    @BindView(R.id.tv_nbiot_number)
    TextView tvNbiotNumber;
    @BindView(R.id.mainFragmentAlarmJump)
    RelativeLayout mainFragmentAlarmJump;

    private String gHouseId,ghouseFullName;

    private gHGateAdapter gHGateAdapter;
    Unbinder unbinder;
    private List<JsonGreenhouseManagerObject.ObjBean> obj;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        initView();
        init();
    }

    private void init() {
        greenHouseGetList();
    }

    @Override
    protected int layoutViewId() {
        return R.layout.monitor_layout;
    }

    @Override
    public void onUpdateUI() {

    }


    private void initView() {
        // TODO Auto-generated method stub
        columnList.setOnItemClickListener(this);
        columnList.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                greenHouseGetList();
            }
        });
    }

    /**
     * @author jcy
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseAction
     * @Description: TODO(大棚列表数据请求)
     * @date 2017-9-20 20:00:58
     */

    private void greenHouseGetList() {
        String token = PreferencesUtils.getString(getActivity(), "token");// 用户token
        String currTenantId = PreferencesUtils.getString(getActivity(), "tenantId");// 商户id
        HttpManager.getInstance().gHouseGetList(TAG, token, currTenantId, new HttpCallBack<JsonGreenhouseManagerObject>() {
            @Override
            public void onError(Throwable throwable) {
                columnList
                        .onRefreshComplete(getString(R.string.xlistview_header_last_time)
                                + new Date().toLocaleString());
                ldMonitorView.setErrorShown(true, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //
                        ldMonitorView.setProgressShown(true);
                        greenHouseGetList();
                    }
                });
            }

            @Override
            public void onSuccess(JsonGreenhouseManagerObject date) {
              //  greenhousesEnvironmentAverage.removeAllViews();
                columnList
                        .onRefreshComplete(getString(R.string.xlistview_header_last_time)
                                + new Date().toLocaleString());

                if (date != null) {
                    String message = date.getMsg();// 提示消息
                    if (date.isSuccess()) {
                        obj = date.getObj();
                        if ( obj== null  || obj.size()<=0){
                            ldMonitorView.setNoShown(true);
                            return;
                        }

                        if (gHGateAdapter == null ) {

                            gHGateAdapter = new gHGateAdapter(getActivity(), obj);
                            columnList.setAdapter(gHGateAdapter);

                        }else{

                            gHGateAdapter.setObjList( date.getObj());
                            gHGateAdapter.notifyDataSetChanged();

                        }
                        ldMonitorView.delayShowContainer(true);
                    } else { // 失败
                        if (!TextUtils.isEmpty(message)) {
                            ToastUtils.makeTextShort(message);
                        } else {
                            ToastUtils.makeTextShort("网络不可用!");
                        }
                        ldMonitorView.setNoShown(true);
                    }
                } else {
                    ldMonitorView.setNoShown(true);
                }

                ldMonitorView.delayShowContainer(true);

            }
        });
    }


    @Override
    public void onEvent(ToUIEvent toUIEvent) {
        super.onEvent(toUIEvent);
        if (ToUIEvent.UPDATE_MONITORING_INTERFACE == toUIEvent.getWhat()) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        gHouseId =   obj.get(position -1).getId();//自定义控件从1开始
        ghouseFullName =  obj.get(position - 1).getGhouseFullname();
        if (gHouseId == null  && ghouseFullName == null) {
            return;
        }
        startActivity(new Intent(getActivity(), gHRealMonitorActivity.class).putExtra("gHouseId", gHouseId).putExtra("ghouseFullName",ghouseFullName));
    }

}
