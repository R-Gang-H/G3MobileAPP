package com.app.itserv;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.app.commons.ToUIEvent;
import com.yycloud.app.utils.MyVolley;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

public abstract class BaseActivity extends FragmentActivity {
    public String TAG = getClass().getSimpleName();
    private Unbinder unbinder;

    protected abstract int layoutViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutViewId() > 0) {
            setContentView(layoutViewId());
            unbinder = ButterKnife.bind(this);//注册注解
        }
        ActivityCollector.addActivity(this);
        EventBus.getDefault().register(this);//注册EventBus
        init();
    }

    protected void init() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityCollector.removeActivity(this);
        MyVolley.getRequestQueue().cancelAll(TAG);
        if (null != unbinder)
            unbinder.unbind();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    public void onEvent(ToUIEvent event) {
    }
}
