package com.app.itserv.activity;

import android.view.View;
import android.widget.ImageView;

import com.app.itserv.BaseActivity;
import com.app.itserv.adapters.LoginLogAdapter;
import com.app.itserv.jparser.JsonLoginLogObject;
import com.app.itserv.views.LoadingFrameView;
import com.app.itserv.views.PullToRefreshListView;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录日志
 *
 * @author Administrator
 */
public class LoginLogActivity extends BaseActivity implements PullToRefreshListView.OnRefreshListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.load_view)
    LoadingFrameView loadView;
    @BindView(R.id.list_login_log_items)
    PullToRefreshListView listLoginLogItems;

    LoginLogAdapter loginLogAdapter;

    @Override
    protected int layoutViewId() {
        return R.layout.login_log_lay;
    }

    @Override
    protected void init() {
        super.init();
        listLoginLogItems.setOnRefreshListener(this);
        InitRquest();

    }

    private void InitRquest() {
        LoginLogRequest();//登录日志请求
    }

    /**
     * hoaruigang
     * 2017-9-23 19:58:54
     * 登录日志
     */
    private void LoginLogRequest() {

        String token = PreferencesUtils.getString(this, "token");// token
        String currTenantId = PreferencesUtils.getString(this, "tenantId");// 商户id
        HttpManager.getInstance().doLoginLog(TAG, token, currTenantId, new HttpCallBack<JsonLoginLogObject>(LoginLogActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                loadView.setErrorShown(true, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InitRquest();
                        listLoginLogItems.onRefreshComplete();
                    }
                });
            }

            @Override
            public void onSuccess(JsonLoginLogObject date) {
                if (date != null && !date.equals("")) {
                    if (date.isSuccess()) {//成功
                        List<JsonLoginLogObject.ObjBean> loginLog = date.getObj();
                        if (loginLog != null) {
                            //登录日志适配器
                            loginLogAdapter = new LoginLogAdapter(LoginLogActivity.this, loginLog);
                            listLoginLogItems.setAdapter(loginLogAdapter);
                            listLoginLogItems.onRefreshComplete();
                            loadView.delayShowContainer(true);
                        }
                    } else {
                        loadView.setErrorShown(true, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                InitRquest();
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        InitRquest();
    }

    @OnClick(R.id.img_back)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
