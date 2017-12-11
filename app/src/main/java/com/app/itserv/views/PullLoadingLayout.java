
package com.app.itserv.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

import com.itserv.shed.R;

/**
 * 下拉刷新或上拉加载的等待控件
 * axin
 */
public class PullLoadingLayout extends LinearLayout {

    static final String LOG_TAG = "PullLoadingLayout";

    private Context context;

    private FrameLayout mInnerLayout;

    protected ImageView mHeaderImage;
    protected ProgressBar mHeaderProgress;

    private TextView mHeaderText;
    private TextView mSubHeaderText;

    private RotateAnimation animation;
    private RotateAnimation reverseAnimation;

    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;
    private CharSequence mLastUpdatedLabel;

    private long lastTimeMillis;

    //是否已measured
    private boolean isMeasured;
    private int measuredSize;

    public PullLoadingLayout(Context context) {
        super(context);
        init(context);
    }

    public PullLoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    @SuppressLint("NewApi")
    public PullLoadingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_loading_vertical, this);

        mInnerLayout = (FrameLayout) findViewById(R.id.fl_inner);
        mHeaderText = (TextView) mInnerLayout.findViewById(R.id.pull_to_refresh_text);
        mHeaderProgress = (ProgressBar) mInnerLayout.findViewById(R.id.pull_to_refresh_progress);
        mSubHeaderText = (TextView) mInnerLayout.findViewById(R.id.pull_to_refresh_sub_text);
        mHeaderImage = (ImageView) mInnerLayout.findViewById(R.id.pull_to_refresh_image);

        // 设置旋转动画事件
        animation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(150);
        animation.setFillAfter(true);

        reverseAnimation = new RotateAnimation(-180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setInterpolator(new LinearInterpolator());
        reverseAnimation.setDuration(2000);
        reverseAnimation.setFillAfter(true);

        reset();
    }

    /**
     * 下拉刷新
     *
     * @param isBackFromRelease 是由releaseToRefresh状态回退回来的
     */
    public final void pullToRefresh(boolean isBackFromRelease) {
        if (null != mInnerLayout) {
            mInnerLayout.setVisibility(View.VISIBLE);
        }
        if (null != mHeaderImage) {
            mHeaderImage.setVisibility(View.VISIBLE);
            mHeaderImage.clearAnimation();
            if (isBackFromRelease) {
                mHeaderImage.startAnimation(reverseAnimation);
            }
        }

        if (null != mHeaderProgress) {
            mHeaderProgress.setVisibility(View.GONE);
        }

        if (null != mHeaderText) {
            mHeaderText.setText(mPullLabel);
        }

        if (null != mSubHeaderText) {
            mSubHeaderText.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 松开刷新
     */
    public final void releaseToRefresh() {
        if (null != mHeaderImage) {
            mHeaderImage.setVisibility(View.VISIBLE);
            mHeaderImage.clearAnimation();
            mHeaderImage.startAnimation(animation);
        }

        if (null != mHeaderProgress) {
            mHeaderProgress.setVisibility(View.GONE);
        }

        if (null != mHeaderText) {
            mHeaderText.setVisibility(View.VISIBLE);
            mHeaderText.setText(mReleaseLabel);
        }

        if (null != mSubHeaderText) {
            mSubHeaderText.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 正在刷新
     */
    public final void refreshing() {
        if (null != mHeaderImage) {
            mHeaderImage.setVisibility(View.GONE);
            mHeaderImage.clearAnimation();
        }

        if (null != mHeaderProgress) {
            mHeaderProgress.setVisibility(View.VISIBLE);
        }


        if (null != mHeaderText) {
            mHeaderText.setText(mRefreshingLabel);
        }
        if (null != mSubHeaderText) {
            mSubHeaderText.setVisibility(View.GONE);
        }
    }

    /**
     * 状态重置
     */
    public final void reset() {
        if (null != mInnerLayout) {
            mInnerLayout.setVisibility(View.INVISIBLE);
        }
        if (null != mHeaderImage) {
            mHeaderImage.setVisibility(View.VISIBLE);
            mHeaderImage.clearAnimation();
        }

        if (null != mHeaderProgress) {
            mHeaderProgress.setVisibility(View.GONE);
        }

        if (null != mHeaderText) {
            mHeaderText.setText(mPullLabel);
        }

        if (null != mSubHeaderText) {
            mSubHeaderText.setVisibility(View.VISIBLE);
        }

        refreshComplete();
    }

    /**
     * 刷新完成
     */
    public final void refreshComplete() {
        if (null != mSubHeaderText) {
            mSubHeaderText.setText(mLastUpdatedLabel + getFriendlyTime());
        }
    }

    /**
     * 设置上次刷新显示
     *
     * @param label
     */
    public void setLastUpdatedLabel(CharSequence label) {
        mLastUpdatedLabel = label;
        //setSubHeaderText(label);
    }

    /**
     * 设置拉动图标
     *
     * @param imageDrawable
     */
    public final void setLoadingDrawable(Drawable imageDrawable) {
        // Set Drawable
        mHeaderImage.setImageDrawable(imageDrawable);
    }

    /**
     * 设置刷新等待图标
     *
     * @param imageDrawable
     */
    public final void setRefreshingDrawable(Drawable imageDrawable) {
        // Set Drawable
        mHeaderProgress.setIndeterminateDrawable(imageDrawable);
    }

    /**
     * 设置下拉显示
     *
     * @param pullLabel
     */
    public void setPullLabel(CharSequence pullLabel) {
        mPullLabel = pullLabel;
    }

    /**
     * 设置正在刷新显示
     *
     * @param refreshingLabel
     */
    public void setRefreshingLabel(CharSequence refreshingLabel) {
        mRefreshingLabel = refreshingLabel;
    }

    /**
     * 设置松开显示
     *
     * @param releaseLabel
     */
    public void setReleaseLabel(CharSequence releaseLabel) {
        mReleaseLabel = releaseLabel;
    }

    public void setTextTypeface(Typeface tf) {
        mHeaderText.setTypeface(tf);
    }

    private void setSubHeaderText(CharSequence label) {
        if (null != mSubHeaderText) {
            if (TextUtils.isEmpty(label)) {
                mSubHeaderText.setVisibility(View.GONE);
            } else {
                mSubHeaderText.setText(label);

                // Only set it to Visible if we're GONE, otherwise VISIBLE will
                // be set soon
                if (View.GONE == mSubHeaderText.getVisibility()) {
                    mSubHeaderText.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void setSubTextAppearance(int value) {
        if (null != mSubHeaderText) {
            mSubHeaderText.setTextAppearance(getContext(), value);
        }
    }

    private void setSubTextColor(ColorStateList color) {
        if (null != mSubHeaderText) {
            mSubHeaderText.setTextColor(color);
        }
    }

    private void setTextAppearance(int value) {
        if (null != mHeaderText) {
            mHeaderText.setTextAppearance(getContext(), value);
        }
        if (null != mSubHeaderText) {
            mSubHeaderText.setTextAppearance(getContext(), value);
        }
    }

    private void setTextColor(ColorStateList color) {
        if (null != mHeaderText) {
            mHeaderText.setTextColor(color);
        }
        if (null != mSubHeaderText) {
            mSubHeaderText.setTextColor(color);
        }
    }

    /**
     * 获取控件的高度（或宽度）
     *
     * @return
     */
    public int getContentSize() {
        if (!isMeasured) {
            measureView(mInnerLayout);
        }
        measuredSize = mInnerLayout.getMeasuredHeight();
        return measuredSize;
    }

    /**
     * 估算view的width以及height
     *
     * @param view
     */
    private void measureView(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0,
                params.width);
        int lpHeight = params.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * 获取显示时间
     *
     * @return
     */
    private String getFriendlyTime() {
        Date date = new Date();
        return date.toLocaleString();
    }

}
