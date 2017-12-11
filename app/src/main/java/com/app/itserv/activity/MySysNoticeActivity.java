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

import com.app.itserv.fragments.MySysReadNoticeFragment;
import com.app.itserv.fragments.MySysUnReadNoticeFragment;
import com.app.itserv.views.MineViewpager;
import com.itserv.shed.R;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

/**
 * 系统公告 by 20170718
 *
 * @author yiqiang
 */
public class MySysNoticeActivity extends FragmentActivity implements
        OnClickListener {
    private static String TAG = "MySysNoticeActivity";

    private Context mContext;
    private View mBackView;
    private TextView mTitle;
    private ArrayList<String> categoryList = new ArrayList<>();// 分类列表
    private TabPageIndicatorAdapter adapter;
    MineViewpager pager;
    TabPageIndicator indicator;
    // tab标签中可切换的fragment
    MySysReadNoticeFragment readNoticeFragment;// 已读Fragment
    MySysUnReadNoticeFragment unReadNoticeFragment;// 未读Fragment

    // Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 使窗口支持透明度
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        mContext = this;
        // 设置标题不显示
        setContentView(R.layout.activity_my_sys_notice);
        // 初始化控件
        initViews();

        initTabFragments();
        initViewPager();
    }

    // 初始化控件
    private void initViews() {
        mBackView = findViewById(R.id.btn_back);// 返回
        mBackView.setOnClickListener(this);
        pager = (MineViewpager) findViewById(R.id.pager_mysysnotice);// Viewpager
        pager.setOffscreenPageLimit(0);// 显示第一个页面
        indicator = (TabPageIndicator) findViewById(R.id.indicator_mysysnotice);// Indicator
        mTitle = (TextView) findViewById(R.id.title_txt);
        mTitle.setText("系统公告");
    }

    /**
     * init the tab-fragments
     */
    private void initTabFragments() {
        categoryList.add("未读事件");
        categoryList.add("已读事件");
    }

    /**
     * init the view pager
     */
    private void initViewPager() {
        // ViewPager的adapter 创建Fragment管理者
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
                switch (arg0) {
                    case 0:
                        if (unReadNoticeFragment == null) {
                            unReadNoticeFragment = new MySysUnReadNoticeFragment();
                        }
                        unReadNoticeFragment.init();
                        break;
                    case 1:
                        if (readNoticeFragment == null) {
                            readNoticeFragment = new MySysReadNoticeFragment();
                        }
                        readNoticeFragment.init();
                        break;
                    default:
                        break;
                }
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
        private ArrayList<String> infos = new ArrayList<String>();

        public TabPageIndicatorAdapter(FragmentManager fm,
                                       ArrayList<String> datas) {
            super(fm);
            infos = datas;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    unReadNoticeFragment = new MySysUnReadNoticeFragment();// 未读
                    return unReadNoticeFragment;
                case 1:
                    readNoticeFragment = new MySysReadNoticeFragment();// 已读
                    return readNoticeFragment;
            }
            return null;
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
