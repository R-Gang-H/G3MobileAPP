package com.app.itserv.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.itserv.adapters.TabPageIndicatorAdapter;
import com.app.itserv.fragments.ItemFragment;
import com.app.itserv.fragments.MyTodayTaskFragment;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: AdvisoryproposalActivity
 * @Description: 咨询建议
 * @date 2017-6-24 下午3:19:08
 */
public class AdvisoryproposalActivity extends FragmentActivity implements
        OnClickListener {

    private Context mContext;

    private static final String TAG = "AdvisoryproposalActivity";

    /**
     * Tab标题
     */
    private static final String[] TITLE = new String[]{"未回复", "已回复|待关闭",
            "已关闭"};

    private Button btnAddAdvisory;
    private ImageView imgBack;
    /* 内部消息导航标题start */
    private ViewPager pager;
    private FragmentPagerAdapter adapter;
    private TabPageIndicator indicator;

	/* 内部消息导航标题end */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisory_proposal_lay);
        mContext = this;
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        imgBack = (ImageView) findViewById(R.id.img_back);// 返回
        imgBack.setOnClickListener(this);
        btnAddAdvisory = (Button) findViewById(R.id.btn_add_advisory);// 新增
        btnAddAdvisory.setOnClickListener(this);
        getViewPager();
        /* 内部消息导航标题end */
    }

    private void getViewPager() {
        // ViewPager的adapter
        pager = (ViewPager) findViewById(R.id.pager);
        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        adapter = new TabPageIndicatorAdapter(getSupportFragmentManager(), TITLE);
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);

        // 如果我们要对ViewPager设置监听，用indicator设置就行了
        indicator.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

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
            case R.id.btn_add_advisory:// 新增咨询
                startActivity(new Intent(mContext, AdvisoryAddActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // 刷新界面
        getViewPager();
    }

}
