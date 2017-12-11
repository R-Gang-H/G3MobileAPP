package com.app.itserv.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.app.itserv.BaseActivity;
import com.itserv.shed.R;
import com.oneapm.agent.android.OneApmAgent;

/***
 * 启动页
 *
 * @author Administrator
 */
public class SplashActivity extends BaseActivity implements OnClickListener {
    private final int SPLASH_DISPLAY_LENGHT = 2000;
    private Context mContext;
    public static final String SHAREDPREFERENCES_NAME = "first_pref";
    boolean isFirstIn = false;

    private TextView btnSkip;
    Handler handler = new Handler();

    @Override
    protected int layoutViewId() {
        return R.layout.splash_lay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // 启动one apm性能监测 e农TOKEN:DEC840FCE93A5068D5384BFCC1C086E524(弃用)
        OneApmAgent.init(this.getApplicationContext()).setToken("DEC840FCE93A5068D5384BFCC1C086E524").start();
        initView();
        SharedPreferences preferences = getSharedPreferences(
                SHAREDPREFERENCES_NAME, MODE_PRIVATE);

        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = preferences.getBoolean("isFirstIn", true);

        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        handler.postDelayed(runnable, SPLASH_DISPLAY_LENGHT);
    }

    private void initView() {
        // TODO Auto-generated method stub
        btnSkip = (TextView) findViewById(R.id.btn_skip);// 跳过
        btnSkip.setOnClickListener(this);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isFirstIn) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(intent);
            } else {
                Intent intent1 = new Intent(mContext, GuideActivity.class);
                mContext.startActivity(intent1);
            }
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_skip:// 跳过
                handler.removeCallbacks(runnable);
                if (!isFirstIn) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                } else {
                    Intent intent1 = new Intent(mContext, GuideActivity.class);
                    mContext.startActivity(intent1);
                }
                finish();
                break;
            default:
                break;
        }
    }
}
