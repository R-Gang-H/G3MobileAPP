package com.app.itserv.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import com.app.itserv.activity.FarmingTaskEditActivity;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.viewpagerindicator.TabPageIndicator;
import com.yycloud.core.beans.ECMSColumn;
import com.yycloud.core.beans.NewBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 农事资讯
 *
 * @author Administrator
 */
public class NewFrament extends BaseFragment {
    @BindView(R.id.indicator)
    TabPageIndicator indicator;
    @BindView(R.id.news_viewpager)
    ViewPager pager;
    private TabPageIndicatorAdapter adapter;
    private List<ECMSColumn> categoryList = new ArrayList<>();// 分类列表
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int layoutViewId() {
        return R.layout.news_frament_lay1;
    }

    @Override
    public void onUpdateUI() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setLoadCategory();
    }

    public void setLoadCategory() {
        HttpManager.getInstance().getLoadCategory(TAG, new HttpCallBack<NewBean>(getActivity(), true) {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort(R.string.network_error);
            }

            @Override
            public void onSuccess(NewBean date) {
                if (null != date && null != date.funcdata && date.funcdata.size() > 0) {
                    categoryList = date.funcdata;
                    initViewPager();
                } else
                    ToastUtils.makeTextShort(R.string.network_error);
            }
        });
    }

    private void initViewPager() {
        // ViewPager的adapter
        FragmentManager fragmentManager = getChildFragmentManager();
        for (int i = 0; i < categoryList.size(); i++) {
            Fragment fragment = new NewsFragment();
            Bundle args = new Bundle();
            args.putSerializable("arg", categoryList.get(i));
            fragment.setArguments(args);
            fragmentList.add(fragment);
        }
        adapter = new TabPageIndicatorAdapter(fragmentManager);

        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(categoryList.size());
        // 实例化TabPageIndicator然后设置ViewPager与之关联

        indicator.setViewPager(pager);
        indicator.setVisibility(View.VISIBLE);
        // 如果我们要对ViewPager设置监听，用indicator设置就行了
//        indicator.setOnPageChangeListener(new OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int arg0) {
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//
//            }
//        });
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
            return categoryList.get(position).getName();
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