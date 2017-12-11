
package com.app.itserv.views;

import com.itserv.shed.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * 下拉或上拉刷新控件
 * axin
 */
public class MyPullToRefreshListView extends ListView {
    protected Context context;
    //拉动状态 完成(reset)/pull/release/refreshing/loading
    protected final static int STATE_RESET = 0;
    protected final static int STATE_PULL_TO_REFRESH = 1;
    protected final static int STATE_RELEASE_TO_REFRESH = 2;
    protected final static int STATE_REFRESHING = 3;
    protected final static int STATE_LOADING = 4;

    protected final static int PULL_MODE_FROM_START = 1;
    protected final static int PULL_MODE_FROM_END = 2;

    // 实际的padding的距离与界面上偏移距离的比例
    private final static int RATIO = 3;

    private LayoutInflater inflater;

    // 头部和底部的等待布局
    private PullLoadingLayout headerView;
    private PullLoadingLayout footerView;

    private int headerContentHeight;
    private int footerContentHeight;

    private int currentPullMode;

    private int startY;
    private int pullState;
    private Boolean isBackFromRelease = true;
    View mTitle;

    // 用于保证startY的值在一个完整的touch事件中只被记录一次
    private boolean isRecored;

    private OnRefreshListener startRefreshListener;
    private OnRefreshListener endRefreshListener;

    //是否可以上拉加载
    private boolean isPullUpable;
    //是否可以下拉刷新
    private boolean isPullDownable;


    public MyPullToRefreshListView(Context context) {
        super(context);
        init(context);
    }

    public MyPullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public MyPullToRefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    private void init(Context context) {
        this.context = context;
        setCacheColorHint(context.getResources().getColor(R.color.transparent));
        inflater = LayoutInflater.from(context);

        pullState = STATE_RESET;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //performActivityTouchEvent(ev);
        //if (!refreshLocked && isRefreshable && this.getFirstVisiblePosition() == 0) {
        if (isPullDownable || isPullUpable) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    System.out.println("===down");
                    if (!isRecored) {
                        isRecored = true;
                        // 手指按下时记录当前位置
                        startY = (int) ev.getY();
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    System.out.println("===up");
                    if (pullState == STATE_PULL_TO_REFRESH) {
                        setPullState(STATE_RESET);
                    } else if (pullState == STATE_RELEASE_TO_REFRESH) {
                        setPullState(STATE_REFRESHING);
                        onRefresh();
                    }
                    isRecored = false;
                    isBackFromRelease = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    int tempY = (int) ev.getY();
                    if (!isRecored) {
                        isRecored = true;
                        startY = tempY;
                    }

                    if (pullState == STATE_REFRESHING) {
                        break;
                    }

                    int diff = (tempY - startY) / RATIO;
                    currentPullMode = diff > 0 ? PULL_MODE_FROM_START : PULL_MODE_FROM_END;
                    int absDiff = Math.abs(diff);
                    //超过最小拉动刷新距离
                    boolean outOfTouchSlop = absDiff > getTouchSlop();

                    if (!isReadyForPull()) {
                        System.out.println("===move:not ready");
                        break;
                    }

                    if (pullState == STATE_RESET) {
                        setPullState(STATE_PULL_TO_REFRESH);
                    } else if (pullState == STATE_PULL_TO_REFRESH) {
                        autoScroll();
                        // 下拉到可以进入RELEASE_TO_REFRESH的状态
                        if (outOfTouchSlop) {// 由done或者下拉刷新状态转变到松开刷新
                            setPullState(STATE_RELEASE_TO_REFRESH);
                        }
                    } else if (pullState == STATE_RELEASE_TO_REFRESH) {
                        autoScroll();
                        if (!outOfTouchSlop) {
                            isBackFromRelease = true;
                            setPullState(STATE_PULL_TO_REFRESH);
                        }
                    }
                    onPullDiffChanged(diff);
                    break;

                default:
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 设置拉动状态
     *
     * @param pullState
     */
    private void setPullState(int pullState) {
        System.out.println("===pullState:" + pullState);
        this.pullState = pullState;
        onPullStateChanged();
    }

    /*将touch事件传递到Activity中，暂弃用
    private void performActivityTouchEvent(MotionEvent ev) {
        Handler handler = FactoryFragment.getInstacne().getHandler();
        if (handler != null) {
            Message msg = handler.obtainMessage();
            msg.what = MessageWhat.ACTIVITY_TOUCH_EVENT;
            msg.obj = ev;
            handler.sendMessage(msg);
        }
    }*/

    /**
     * 拉动状态变化回调
     */
    private void onPullStateChanged() {
        System.out.println("===onPullStateChanged");
        switch (currentPullMode) {
            case PULL_MODE_FROM_START:
                changeHeaderViewByState();
                break;
            case PULL_MODE_FROM_END:
                changeFooterViewByState();
                break;
        }
    }

    /**
     * 更改顶部等待控件
     */
    private void changeHeaderViewByState() {
        if (!isPullDownable) {
            return;
        }
        switch (pullState) {
            case STATE_PULL_TO_REFRESH:
                headerView.pullToRefresh(isBackFromRelease);
                isBackFromRelease = false;
                break;
            case STATE_RELEASE_TO_REFRESH:
                headerView.releaseToRefresh();
                break;
            case STATE_REFRESHING:
                headerView.setPadding(0, 0, 0, 0);
                headerView.refreshing();
                break;
            case STATE_RESET:
                headerView.setPadding(0, -headerContentHeight, 0, 0);
                headerView.reset();
                break;
        }
    }

    /**
     * 更改底部等待控件
     */
    private void changeFooterViewByState() {
        if (!isPullUpable) {
            return;
        }
        switch (pullState) {
            case STATE_PULL_TO_REFRESH:
                footerView.pullToRefresh(isBackFromRelease);
                isBackFromRelease = false;
                break;
            case STATE_RELEASE_TO_REFRESH:
                footerView.releaseToRefresh();
                break;
            case STATE_REFRESHING:
                footerView.setPadding(0, 0, 0, 0);
                footerView.refreshing();
                break;
            case STATE_RESET:
                footerView.setPadding(0, 0, 0, -footerContentHeight);
                footerView.reset();
                break;
        }
    }

    /**
     * 拉动距离发生变化
     */
    private void onPullDiffChanged(int diff) {
        System.out.println("===onPullDiffChanged:" + diff);
        switch (currentPullMode) {
            case PULL_MODE_FROM_START:
                if (isPullDownable) {
                    headerView.setPadding(0, diff - headerContentHeight, 0, 0);
                }
                break;
            case PULL_MODE_FROM_END:
                if (isPullUpable) {
                    footerView.setPadding(0, 0, 0, -diff - footerContentHeight);
                }
                break;
        }
    }

    /**
     * 设置下拉刷新回调
     *
     * @param refreshListener
     */
    public void setOnStartRefreshListener(OnRefreshListener refreshListener) {
        this.startRefreshListener = refreshListener;
    }

    /**
     * 设置上拉刷新回调
     *
     * @param refreshListener
     */
    public void setOnEndRefreshListener(OnRefreshListener refreshListener) {
        this.endRefreshListener = refreshListener;
    }


    public interface OnRefreshListener {
        void onRefresh();
    }

    /**
     * 刷新完成回调
     */
    public void onRefreshComplete() {
        setPullState(STATE_RESET);
        autoScroll();
    }

    /**
     * ListView刷新回调
     */
    private void onRefresh() {
        switch (currentPullMode) {
            case PULL_MODE_FROM_START:
                if (startRefreshListener != null) {
                    startRefreshListener.onRefresh();
                }
                break;
            case PULL_MODE_FROM_END:
                if (endRefreshListener != null) {
                    endRefreshListener.onRefresh();
                }
                break;
        }

    }

    /**
     * listView悬浮处理：(放弃)
     */
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if (pullState == STATE_RESET) {
//
//            if (mTitle != null) {
//                measureChild(mTitle, widthMeasureSpec, heightMeasureSpec);
//            }
//        }
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//        if (pullState == STATE_RESET) {
//
//            if (mTitle != null) {
//                mTitle.layout(0, 0, mTitle.getMeasuredWidth(), mTitle.getMeasuredHeight());
//            }
//        }
//    }

//    @Override
//    protected void dispatchDraw(Canvas canvas) {
//        super.dispatchDraw(canvas);
//        if (pullState == STATE_RESET) {
//            if (mTitle != null) {
//                drawChild(canvas, mTitle, getDrawingTime());
//            }
//        }
//    }
    @Override
    public void setAdapter(ListAdapter adapter) {
        setAdapter(adapter, true);
    }

    public void setAdapter(ListAdapter adapter, boolean needTitle) {
        super.setAdapter(adapter);
//        if (needTitle) {
//            //mTitle = inflater.inflate(R.layout.matchitem_one, this, false);
//        }
    }

//    public void moveTitle(String title) {
//        if (mTitle == null) {
//            return;
//        }
//        updateTitleText(title);
//        if (pullState != STATE_RESET) {
//            return;
//        }
//        View bottomChild = getChildAt(0);
//        if (bottomChild != null) {
//            int bottom = bottomChild.getBottom();
//            int height = mTitle.getMeasuredHeight();
//            int y = 0;
//            if (bottom < height) {
//                y = bottom - height;
//            }
//            mTitle.layout(0, y, mTitle.getMeasuredWidth(), mTitle.getMeasuredHeight() + y);
//        }
//    }

//    public void updateTitle(String title) {
//        if (mTitle == null) {
//            return;
//        }
//        updateTitleText(title);
//        if (pullState == STATE_RESET) {
//            mTitle.layout(0, 0, mTitle.getMeasuredWidth(), mTitle.getMeasuredHeight());
//        }
//    }
//
//    private void updateTitleText(String title) {
//        if (mTitle == null) {
//            return;
//        }
//        if (title != null) {
//            //TextView title_text = (TextView) mTitle.findViewById(R.id.matchitem_one_tv01);
//            //title_text.setText(title);
//        }
//    }

    /**
     * 设置下拉是否可用
     *
     * @param isPullDownable
     */
    public void setPullDownable(boolean isPullDownable) {
        if (this.isPullDownable == isPullDownable) {
            return;
        }
        this.isPullDownable = isPullDownable;
        if (isPullDownable) {
            initHeaderView();
        } else {
            removeHeaderView(headerView);
            headerView = null;
        }
    }

    /**
     * 设置上拉是否可用
     *
     * @param isPullUpable
     */
    public void setPullUpable(boolean isPullUpable) {
        if (this.isPullUpable == isPullUpable) {
            return;
        }
        this.isPullUpable = isPullUpable;
        if (isPullUpable) {
            initFooterView();
        } else {
            removeFooterView(footerView);
            footerView = null;
        }
    }

    /**
     * 初始化顶部等待控件
     */
    private void initHeaderView() {
        headerView = new PullLoadingLayout(context);
        headerView.setPullLabel(context.getString(R.string.pulltorefresh_pull));
        headerView.setReleaseLabel(context.getString(R.string.pulltorefresh_release));
        headerView.setRefreshingLabel(context.getString(R.string.pulltorefresh_refreshing));
        headerView.setLastUpdatedLabel(context.getString(R.string.pulltorefresh_last_updated));
        headerView.setLoadingDrawable(
                context.getResources().getDrawable(R.drawable.shuaxinshangjiantou));
        headerView.setRefreshingDrawable(
                context.getResources().getDrawable(R.drawable.progressbar));
        headerContentHeight = headerView.getContentSize();
        System.out.println("----headerContentHeight:" + headerContentHeight);
        // 设置内边距，正好距离顶部为一个负的整个布局的高度，正好把头部隐藏
        headerView.setPadding(0, -headerContentHeight, 0, 0);
        // 重绘一下
        headerView.invalidate();
        // 将下拉刷新的布局加入ListView的顶部
        addHeaderView(headerView, null, false);
        headerView.reset();
    }

    /**
     * 初始化底部等待控件
     */
    private void initFooterView() {
        footerView = new PullLoadingLayout(context);
        footerView.setPullLabel(context.getString(R.string.pulltorefresh_pull_up));
        footerView.setReleaseLabel(context.getString(R.string.pulltorefresh_release_up));
        footerView.setRefreshingLabel(context.getString(R.string.pulltorefresh_refreshing_up));
        footerView.setLastUpdatedLabel(context.getString(R.string.pulltorefresh_last_updated_up));
        footerView.setLoadingDrawable(
                context.getResources().getDrawable(R.drawable.shuaxinxiajiantou));
        footerView.setRefreshingDrawable(
                context.getResources().getDrawable(R.drawable.progressbar));
        footerContentHeight = footerView.getContentSize();
        // 设置内边距，正好距离底部部为一个负的整个布局的高度，正好把头部隐藏
        footerView.setPadding(0, 0, 0, -footerContentHeight);
        // 重绘一下
        footerView.invalidate();
        // 将下拉刷新的布局加入ListView的顶部
        addFooterView(footerView, null, false);
        footerView.reset();
    }

    /**
     * 是否可以开始拉动
     */
    private void autoScroll() {
        switch (currentPullMode) {
            case PULL_MODE_FROM_START:
                if (isPullDownable) {
                    setSelection(0);
                }
                break;
            case PULL_MODE_FROM_END:
                if (isPullUpable) {
                    setSelection(getCount());
                }
                break;
        }
    }

    /**
     * 是否可以开始拉动
     */
    public boolean isReadyForPull() {
        switch (currentPullMode) {
            case PULL_MODE_FROM_START:
                if (isPullDownable) {
                    return isFirstItemVisible();
                }
            case PULL_MODE_FROM_END:
                if (isPullUpable) {
                    return isLastItemVisible();
                }
        }
        return false;
    }

    /**
     * 第一个项目是否可见
     *
     * @return
     */
    private boolean isFirstItemVisible() {
        final Adapter adapter = getAdapter();

        if (null == adapter || adapter.isEmpty()) {
            return true;
        } else {
            System.out.println("====getFirstVisiblePosition():" + getFirstVisiblePosition());
            if (getFirstVisiblePosition() <= 1) {
                final View firstVisibleChild = getChildAt(0);
                if (firstVisibleChild != null) {
                    return true;//firstVisibleChild.getTop() >= getTop();
                }
            }
        }
        return false;
    }

    /**
     * 最后一个项目是否可见
     *
     * @return
     */
    private boolean isLastItemVisible() {
        final Adapter adapter = getAdapter();
        if (null == adapter || adapter.isEmpty()) {
            return true;
        } else {
            final int lastItemPosition = getCount() - 1;
            final int lastVisiblePosition = getLastVisiblePosition();
            if (lastVisiblePosition >= lastItemPosition - 1) {
                final int childIndex = lastVisiblePosition - getFirstVisiblePosition();
                final View lastVisibleChild = getChildAt(childIndex);
                if (lastVisibleChild != null) {
                    return lastVisibleChild.getBottom() <= getBottom();
                }
            }
        }
        return false;
    }

    /**
     * 获取最小的拉动刷新距离
     *
     * @return
     */
    private int getTouchSlop() {
        switch (currentPullMode) {
            case PULL_MODE_FROM_START:
                if (isPullDownable) {
                    return headerContentHeight;
                }
            case PULL_MODE_FROM_END:
                if (isPullUpable) {
                    return footerContentHeight;
                }
        }
        //一个比较大的数，防止刷新
        return 100000;
    }
}
