package com.app.itserv.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.app.itserv.fragments.MyCancelTaskFragment;
import com.app.itserv.fragments.MyCloseTaskFragment;
import com.app.itserv.fragments.MyExecuTaskFragment;
import com.app.itserv.fragments.MyTodayTaskFragment;
import com.itserv.shed.R;
import com.viewpagerindicator.TabPageIndicator;

/**
 * 当日任务
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: MyFarmingTaskActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-22 上午10:52:03
 */
public class MyFarmingTaskActivity extends FragmentActivity implements View.OnClickListener {

    private Context mContext;

    public static final String TAG = "MyFarmingTaskActivity";

    /**
     * Tab标题
     */
    private static final String[] TITLE = new String[]{"未领用", "执行中", "已关闭", "已取消"};

    private ImageView mImgBack;

    /* 内部消息导航标题start */
    public ViewPager pager;
    private FragmentPagerAdapter adapter;
    private TabPageIndicator indicator;

	/* 内部消息导航标题end */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_far_task_lay);
        mContext = this;
        // 初始化控件
        initView();
    }


    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.img_back);// 返回
        mImgBack.setOnClickListener(this);
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
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        if (myTodayTaskfragment == null) {
                            myTodayTaskfragment = new MyTodayTaskFragment();
                        }
                        myTodayTaskfragment.init();
                        break;
                    case 1:
                        if (myExecuTaskfragment == null) {
                            myExecuTaskfragment = new MyExecuTaskFragment();
                        }
                        myExecuTaskfragment.init();
                        break;
                    case 2:
                        if (myCloseTaskfragment == null) {
                            myCloseTaskfragment = new MyCloseTaskFragment();
                        }
                        myCloseTaskfragment.init();
                        break;
                    case 3:
                        if (myCancelTaskfragment == null) {
                            myCancelTaskfragment = new MyCancelTaskFragment();
                        }
                        myCancelTaskfragment.init();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            default:
                break;
        }
    }

    MyTodayTaskFragment myTodayTaskfragment;
    MyExecuTaskFragment myExecuTaskfragment;
    MyCloseTaskFragment myCloseTaskfragment;
    MyCancelTaskFragment myCancelTaskfragment;
    Bundle args;

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i(TAG, TAG + "onResume");
        // 刷新界面
        pager.getAdapter().notifyDataSetChanged();
    }

    public class TabPageIndicatorAdapter extends FragmentPagerAdapter {

        private String[] TITLE = null;

        public TabPageIndicatorAdapter(FragmentManager fm, String[] title) {
            super(fm);
            this.TITLE = title;
        }

        @Override
        public Fragment getItem(int position) {
            // 新建一个Fragment来展示ViewPager item的内容，并传递参数
            switch (position) {
                case 0:
                    myTodayTaskfragment = new MyTodayTaskFragment();
                    args = new Bundle();
                    args.putString("arg", TITLE[position]);
                    args.putInt("position", position);
                    myTodayTaskfragment.setArguments(args);
                    return myTodayTaskfragment;
                case 1:
                    myExecuTaskfragment = new MyExecuTaskFragment();
                    args = new Bundle();
                    args.putString("arg", TITLE[position]);
                    args.putInt("position", position);
                    myExecuTaskfragment.setArguments(args);
                    return myExecuTaskfragment;
                case 2:
                    myCloseTaskfragment = new MyCloseTaskFragment();
                    args = new Bundle();
                    args.putString("arg", TITLE[position]);
                    args.putInt("position", position);
                    myCloseTaskfragment.setArguments(args);
                    return myCloseTaskfragment;
                case 3:
                    myCancelTaskfragment = new MyCancelTaskFragment();
                    args = new Bundle();
                    args.putString("arg", TITLE[position]);
                    args.putInt("position", position);
                    myCancelTaskfragment.setArguments(args);
                    return myCancelTaskfragment;
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position % TITLE.length];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }
    }
}
