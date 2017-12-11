package com.app.itserv.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.app.itserv.fragments.ShedReadEventFragment;
import com.app.itserv.fragments.ShedUnReadEventFragment;
import com.app.itserv.views.MineViewpager;
import com.itserv.shed.R;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

/**
 * 系统告警
 */
public class MyEventActivity extends FragmentActivity implements OnClickListener {
    private static String TAG = "MyEventActivity";

    private Context mContext;
    private View mBackView;
    private TextView mTitle;
    private ArrayList<String> categoryList = new ArrayList<String>();// 分类列表
    private TabPageIndicatorAdapter adapter;
    MineViewpager pager;
    TabPageIndicator indicator;
    private ArrayList<Fragment> fragments;
    // tab标签中可切换的fragment
    ShedReadEventFragment readEventFragment;
    ShedUnReadEventFragment unReadEventFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        mContext = this;
        // 设置标题不显示
        setContentView(R.layout.myevent_layout);
        initViews();
        initTabFragments();
        initViewPager();
    }

    private void initViews() {
        mBackView = findViewById(R.id.btn_back);
        mBackView.setOnClickListener(this);
        pager = (MineViewpager) findViewById(R.id.pager_myevent);
        pager.setOffscreenPageLimit(0);
        indicator = (TabPageIndicator) findViewById(R.id.indicator_myevent);
        mTitle = (TextView) findViewById(R.id.title_txt);
        mTitle.setText("系统告警");
    }

    /**
     * init the tab-fragments
     */
    private void initTabFragments() {
        categoryList.add("未读事件");
        categoryList.add("已读事件");
        fragments = new ArrayList<>();
        unReadEventFragment = new ShedUnReadEventFragment();//未读
        fragments.add(unReadEventFragment);
        readEventFragment = new ShedReadEventFragment();//已读
        fragments.add(readEventFragment);
    }

    /**
     * init the view pager
     */
    private void initViewPager() {
        // ViewPager的adapter
        FragmentManager fragmentManager = ((FragmentActivity) mContext)
                .getSupportFragmentManager();
        adapter = new TabPageIndicatorAdapter(fragmentManager, categoryList);

        pager.setAdapter(adapter);

        // 实例化TabPageIndicator然后设置ViewPager与之关联

        indicator.setViewPager(pager);
        indicator.setVisibility(View.VISIBLE);
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
    public void onClick(View view) {
        // TODO Auto-generated method stub
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * ViewPager适配器
     *
     * @author len
     */
    private class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        private ArrayList<String> infos = new ArrayList<>();

        public TabPageIndicatorAdapter(FragmentManager fm,
                                       ArrayList<String> datas) {
            super(fm);
            infos = datas;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position % fragments.size());
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return infos.get(position % infos.size());
        }

        @Override
        public int getCount() {
            return infos.size();
        }
    }
}
