package com.app.itserv.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itserv.shed.R;

/**
 * 内部消息
 *
 * @author Administrator
 */
public class InternalMessageActivity extends Activity implements
        OnClickListener {

    private Context mContext;

    private Button btnAddSend;

    /* 内部消息导航标题start */
    private ViewPager pager;
    private ImageView imageView;
    private List<View> lists = new ArrayList<View>();
    private InternalPagerAdapter adapter;
    private Bitmap cursor;
    private int offSet;
    private int currentItem;
    private Matrix matrix = new Matrix();//矩阵
    private int bmWidth;
    private Animation animation;
    private TextView textView1, textView2, textView3, textView4;

	/* 内部消息导航标题end */

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        setContentView(R.layout.internal_message_lay);
        mContext = this;
        initView();
        initViewPager();
    }

    private void initView() {
        // TODO Auto-generated method stub
        btnAddSend = (Button) findViewById(R.id.btn_add_send);
        btnAddSend.setOnClickListener(this);

		/* 内部消息导航标题start */
        imageView = (ImageView) findViewById(R.id.cursor);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);

        lists.add(getLayoutInflater().inflate(R.layout.internal_message_items,
                null));
        lists.add(getLayoutInflater().inflate(R.layout.internal_message_items,
                null));
        lists.add(getLayoutInflater().inflate(R.layout.internal_message_items,
                null));
        lists.add(getLayoutInflater().inflate(R.layout.internal_message_items,
                null));

        pager = (ViewPager) findViewById(R.id.pager);
        initeCursor();

		/* 内部消息导航标题end */
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);

    }

    private void initeCursor() {
        cursor = BitmapFactory.decodeResource(getResources(), R.drawable.gb);

        DisplayMetrics dm;
        dm = getResources().getDisplayMetrics();
        bmWidth = dm.widthPixels / 4;

        // offSet = (dm.widthPixels - 4 * bmWidth) / 8;
        matrix.setTranslate(offSet, 0);
        /* 设置游标的宽度start */
        imageView.setImageResource(R.drawable.gb);
        LayoutParams params = imageView.getLayoutParams();
        params.width = bmWidth;
        imageView.setLayoutParams(params);
		/* 设置游标的宽度end */
        imageView.setImageMatrix(matrix); // 需要imageView的scaleType为matrix
        currentItem = 0;
    }

    private void initViewPager() {
        // ViewPager的adapter
        adapter = new InternalPagerAdapter(lists);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // 当滑动式，顶部的imageView是通过animation缓慢的滑动
            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        if (currentItem == 1) {
                            animation = new TranslateAnimation(
                                    offSet * 2 + bmWidth, 0, 0, 0);
                        } else if (currentItem == 2) {
                            animation = new TranslateAnimation(offSet * 4 + 2
                                    * bmWidth, 0, 0, 0);
                        }
                        break;
                    case 1:
                        if (currentItem == 0) {
                            animation = new TranslateAnimation(0, offSet * 2
                                    + bmWidth, 0, 0);
                        } else if (currentItem == 2) {
                            animation = new TranslateAnimation(4 * offSet + 2
                                    * bmWidth, offSet * 2 + bmWidth, 0, 0);
                        }
                        break;
                    case 2:
                        if (currentItem == 0) {
                            animation = new TranslateAnimation(0, 4 * offSet + 2
                                    * bmWidth, 0, 0);
                        } else if (currentItem == 1) {
                            animation = new TranslateAnimation(
                                    offSet * 2 + bmWidth, 4 * offSet + 2 * bmWidth,
                                    0, 0);
                        }
                    case 3:
                        if (currentItem == 3) {
                            animation = new TranslateAnimation(4 * offSet + 3
                                    * bmWidth, 4 * offSet + 2 * bmWidth, 0, 0);
                        } else if (currentItem == 2) {
                            animation = new TranslateAnimation(offSet * 2 + 2
                                    * bmWidth, 4 * offSet + 3 * bmWidth, 0, 0);
                        }
                }
                currentItem = arg0;
                animation.setDuration(150); // 光标滑动速度
                animation.setFillAfter(true);
                imageView.startAnimation(animation);
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
            case R.id.btn_add_send:// 发送
                startActivity(new Intent(mContext,
                        SendInternalMessageActivity.class));
                break;
            case R.id.textView1:
                pager.setCurrentItem(0);
                break;
            case R.id.textView2:
                pager.setCurrentItem(1);
                break;
            case R.id.textView3:
                pager.setCurrentItem(2);
                break;
            case R.id.textView4:
                pager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    class InternalPagerAdapter extends PagerAdapter implements OnClickListener {

        List<View> viewLists;

        public InternalPagerAdapter(List<View> lists) {
            viewLists = lists;
        }

        // 获得size
        @Override
        public int getCount() {
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        // 销毁Item
        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            //((ViewPager) view).removeView(viewLists.get(position));
            view.removeView((View) object);
        }

        // 实例化Item
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
			/*((ViewPager) view).addView(viewLists.get(position), 0);
			LinearLayout viewlist = (LinearLayout) viewLists.get(position);
		   
			TextView tvExamine = (TextView) viewlist
					.findViewById(R.id.tv_examine);
			// tvExamine.setOnClickListener(this);
			return viewlist;*/

            View view = viewLists.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.tv_examine:// 查看
                    startActivity(new Intent(mContext,
                            InternalMessageExamineActivity.class));
                    break;

                default:
                    break;
            }
        }
    }

}
