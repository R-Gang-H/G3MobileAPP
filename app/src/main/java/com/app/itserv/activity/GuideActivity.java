package com.app.itserv.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.app.itserv.BaseActivity;
import com.itserv.shed.R;

import java.util.ArrayList;

public class GuideActivity extends BaseActivity {

    private ViewPager viewPager;
    private ArrayList<View> pageViews;
    private ImageView imageView;
    private Context mContext;

    @Override
    protected int layoutViewId() {
        return R.layout.guide;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        pageViews.add(inflater.inflate(R.layout.guide_item_one, null));
        pageViews.add(inflater.inflate(R.layout.guide_item_two, null));
        pageViews.add(inflater.inflate(R.layout.guide_item_three, null));


        // group是R.layou.main中的负责包裹小圆点的LinearLayout.

        viewPager = (ViewPager) findViewById(R.id.guidePages);


        viewPager.setAdapter(new GuidePageAdapter());
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());
    }

    private void setGuided() {
        SharedPreferences preferences = getSharedPreferences(
                SplashActivity.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        // 存入数据
        editor.putBoolean("isFirstIn", false);
        // 提交修改
        editor.commit();
    }

    /**
     * 指引页面Adapter
     */
    class GuidePageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(pageViews.get(arg1));
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(pageViews.get(arg1), 0);
            if (arg1 == pageViews.size() - 1) {
                ImageView mStartWeiboImageButton = (ImageView) arg0
                        .findViewById(R.id.guide_next);
                mStartWeiboImageButton
                        .setOnClickListener(new OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                setGuided();
                                // 设置已经引导
                                Intent intent = new Intent(mContext,
                                        LoginActivity.class);
                                mContext.startActivity(intent);
                                finish();
                            }

                        });
            }
            return pageViews.get(arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }

    /**
     * 指引页面改监听器
     */
    class GuidePageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {

        }
    }
}