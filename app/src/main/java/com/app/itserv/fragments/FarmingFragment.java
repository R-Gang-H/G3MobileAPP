package com.app.itserv.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.app.itserv.activity.AddFarmingRecordActivity;
import com.itserv.shed.R;
import com.viewpagerindicator.TabPageIndicator;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * 作者： 周作威 on 2017/10/9 11:03.
 * 类描述：农事
 */
public class FarmingFragment extends BaseFragment {
    @BindView(R.id.indicator)
    TabPageIndicator indicator;
    @BindView(R.id.news_viewpager)
    ViewPager pager;
    @BindView(R.id.tv_farming_right)
    TextView tvFarmingRight;
    private List<Fragment> fragmentList = new ArrayList<>();
    private TabPageIndicatorAdapter adapter;

    @Override
    protected int layoutViewId() {
        return R.layout.farming_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fragmentManager = getChildFragmentManager();
        FarmingRecordFragment recordFragment1 = new FarmingRecordFragment();
        FarmingRecordFragment recordFragment2 = new FarmingRecordFragment();
        fragmentList.add(recordFragment1);
        fragmentList.add(recordFragment2);
        adapter = new TabPageIndicatorAdapter(fragmentManager);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(fragmentList.size());
        indicator.setViewPager(pager);
        tvFarmingRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddFarmingRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onUpdateUI() {
    }

    /**
     * ViewPager适配器
     *
     * @author len
     */
    private class TabPageIndicatorAdapter extends FragmentPagerAdapter {

        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position == 0 ? "所有" : "我的";
        }

        @Override
        public Fragment getItem(int position) {
            // 新建一个Fragment来展示ViewPager item的内容，并传递参数

            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }
}
