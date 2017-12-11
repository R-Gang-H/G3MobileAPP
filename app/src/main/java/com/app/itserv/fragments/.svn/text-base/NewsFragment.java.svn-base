package com.app.itserv.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.itserv.MineApplication;
import com.app.itserv.activity.WebViewActivity;
import com.app.itserv.adapters.Bee_PageAdapter;
import com.app.itserv.adapters.NewsAdapter;
import com.app.itserv.views.ChildViewPager;
import com.app.itserv.views.ChildViewPager.onSimpleClickListener;
import com.app.itserv.views.LoadingFrameView;
import com.app.itserv.views.PullToRefreshListView;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.viewpagerindicator.CirclePageIndicator;
import com.yycloud.app.utils.WAPI;
import com.yycloud.core.beans.ECMSAdvertisement;
import com.yycloud.core.beans.ECMSColumn;
import com.yycloud.core.beans.ECMSColumnContentList;
import com.yycloud.core.config.ECMSConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 农事咨询详情
 */
public class NewsFragment extends BaseFragment implements onSimpleClickListener,
        OnPageChangeListener, OnScrollListener {
    @BindView(R.id.column_list)
    PullToRefreshListView xlistView;
    @BindView(R.id.load_view)
    LoadingFrameView loadView;
    private ECMSColumn curCategory;
    private View headView;
    private ChildViewPager viewpager;
    private CirclePageIndicator indicator;
    private Bee_PageAdapter adapter;
    private List<View> bannerListView;
    private float xDistance, yDistance;
    /**
     * 记录按下的X坐标
     **/
    private float mLastMotionX, mLastMotionY;
    private boolean isScrolled = false;
    private boolean mIsBeingDragged = true;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private NewsAdapter mNewsAdapter;
    private int start = 0;
    private final static int limit = 10;
    private ArrayList<ECMSColumnContentList> mContentList = new ArrayList<>();
    private String pic_base_url = "http://newsapi.yunyangdata.com:8080/uatcms/";
    private static final int mAdverColumnId = 2439;
    private ArrayList<ECMSAdvertisement> mAdvers = new ArrayList<ECMSAdvertisement>();
    private View loadingView;
    private boolean isLoading = false;// 是否正在加载的标识

    @Override
    protected int layoutViewId() {
        return R.layout.news_fragment_content;
    }

    @Override
    public void onUpdateUI() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle mBundle = getArguments();
        curCategory = (ECMSColumn) mBundle.getSerializable("arg");
        ImageLoaderConfiguration config = MineApplication.getConfig(getActivity());
        imageLoader.init(config);
        this.loadingView = View.inflate(getActivity(), R.layout.message_foot_more, null);// 加载视图布局
        initMainViews();
        start = 0;
        isLast = false;
        mContentList.clear();
        new LoadContent().execute();
    }

    /**
     * 载入目录
     */
    private class LoadContent extends AsyncTask<Object, Void, Void> {
        @Override
        protected Void doInBackground(Object... params) {
            isLoading = true;
            String url = ECMSConfig.CONFIG_GET_CONTENT_LIST_URL + "&columnid="
                    + curCategory.getId() + "&start=" + start + "&limit="
                    + limit;
            //System.out.println("============url："+url);
            Log.e("url", url);
            String content = WAPI.get_content_from_remote_url(url);
            parseContents(content);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            xlistView.removeFooterView(loadingView);
            isLoading = false;
            mNewsAdapter.setDatas(mContentList);
            xlistView.onRefreshComplete(getString(R.string.xlistview_header_last_time)
                    + new Date().toLocaleString());
            loadView.delayShowContainer(true);
        }
    }

    private void parseContents(String content) {
        if (content == null || content.equals("")) {
            return;
        } else {
            ArrayList<ECMSColumnContentList> mCotent = new ArrayList<ECMSColumnContentList>();
            Log.e("content", content);
            try {
                JSONObject object = new JSONObject(content);
                JSONObject funcdata = object.getJSONObject("funcdata");
                JSONArray list = funcdata.getJSONArray("list");
                for (int i = 0; i < list.length(); i++) {
                    JSONObject column = list.getJSONObject(i);
                    ECMSColumnContentList contentList = new ECMSColumnContentList();
                    String id = column.getString("id");
                    String name = column.getString("title");
                    String smallpic = column.getString("smallpic");
                    String content1 = column.getString("content");
                    contentList.setId(id);
                    contentList.setTitle(name);
                    contentList.setSmallpic(pic_base_url + smallpic);
                    contentList.setContent(content1);
                    mCotent.add(contentList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mContentList.addAll(mCotent);
            if (mCotent.size() < limit) {
                isLast = true;
            }
            start = mContentList.size();
        }
    }

    private boolean isLast = false;

    /**
     * 载入目录
     */
    private class LoadAdver extends AsyncTask<Object, Void, Void> {
        @Override
        protected Void doInBackground(Object... params) {
            String url = ECMSConfig.CONFIG_GET_CONTENT_LIST_URL + "&columnid="
                    + mAdverColumnId + "&start=" + start + "&limit=" + 10;
            Log.e("url", url);
            String content = WAPI.get_content_from_remote_url(url);
            parseAders(content);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            addBannerView();
        }
    }

    private void parseAders(String content) {
        if (content == null || content.equals("")) {
            return;
        } else {
            mAdvers.clear();
            Log.e("content", content);
            try {
                JSONObject object = new JSONObject(content);
                JSONObject funcdata = object.getJSONObject("funcdata");
                JSONArray list = funcdata.getJSONArray("list");
                for (int i = 0; i < list.length(); i++) {
                    JSONObject column = list.getJSONObject(i);
                    ECMSAdvertisement contentList = new ECMSAdvertisement();
                    String name = column.getString("title");
                    String outerurl = column.getString("outerurl");
                    String smallpic = column.getString("smallpic");
                    contentList.setTitle(name);
                    contentList.setSmallpic(pic_base_url + smallpic);
                    contentList.setOuterurl(outerurl);
                    mAdvers.add(contentList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化界面
     */
    private void initMainViews() {
        mNewsAdapter = new NewsAdapter(getActivity());
        headView = View.inflate(getActivity(), R.layout.news_fragment_content_head, null);
        xlistView.addHeaderView(headView);
        xlistView.setAdapter(mNewsAdapter);
        xlistView.setScrollListener(this);
        xlistView.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            public void onRefresh() {
                new LoadAdver().execute();
                isLast = false;
                start = 0;
                mContentList.clear();
                new LoadContent().execute();
            }
        });
        new LoadAdver().execute();
    }

    public void addBannerView() {
        bannerListView = new ArrayList<>();
        bannerListView.clear();

        for (int i = 0; i < mAdvers.size(); i++) {
            ImageView viewOne = (ImageView) View.inflate(getActivity(), R.layout.b0_index_banner_cell, null);
            imageLoader.displayImage(mAdvers.get(i).getSmallpic(), viewOne,
                    MineApplication.default_img);
            bannerListView.add(viewOne);
        }
        /*************** 顶部广告部分开始 ****************/
        viewpager = (ChildViewPager) headView
                .findViewById(R.id.banner_viewpager);
        viewpager.setOnSimpleClickListener(this);//大图点击
        viewpager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                viewpager.getGestureDetector().onTouchEvent(event);
                final float x = event.getRawX();
                final float y = event.getRawY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        xDistance = yDistance = 0f;
                        mLastMotionX = x;
                        mLastMotionY = y;
                    case MotionEvent.ACTION_MOVE:
                        final float xDiff = Math.abs(x - mLastMotionX);
                        final float yDiff = Math.abs(y - mLastMotionY);
                        xDistance += xDiff;
                        yDistance += yDiff;

                        /** 左右滑动避免和下拉刷新冲突 **/
                        if (xDistance > yDistance
                                || Math.abs(xDistance - yDistance) < 0.00001f) {
                            mIsBeingDragged = true;
                            mLastMotionX = x;
                            mLastMotionY = y;
                            v.getParent()
                                    .requestDisallowInterceptTouchEvent(true);
                        } else {
                            mIsBeingDragged = false;
                            v.getParent()
                                    .requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        if (mIsBeingDragged) {
                            v.getParent()
                                    .requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        indicator = (CirclePageIndicator) headView.findViewById(R.id.indicator);
        adapter = new Bee_PageAdapter(bannerListView);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        indicator.setViewPager(viewpager);
        indicator.setOnPageChangeListener(this);
        /*************** 广告设置部分结束 ****************/
    }

    @Override
    public void setOnSimpleClickListenr(int position) {
        // 大图点击事件
        ECMSAdvertisement adver = mAdvers.get(position);
        Intent intent = new Intent();
        intent.setClass(getActivity(), WebViewActivity.class);
        Log.e("url", adver.getOuterurl());
        intent.putExtra("url", adver.getOuterurl());
        getActivity().startActivity(intent);
    }

    @Override
    public void onPageScrollStateChanged(int status) {
        switch (status) {
            case 1:// 手势滑动
                isScrolled = false;
                break;
            case 2:// 界面切换
                isScrolled = true;
                break;
            case 0:// 滑动结束

                // 当前为最后一张，此时从右向左滑，则切换到第一张
                if (viewpager.getCurrentItem() == viewpager.getAdapter().getCount() - 1
                        && !isScrolled) {
                    indicator.setCurrentItem(0);
                }
                // 当前为第一张，此时从左向右滑，则切换到最后一张
                else if (viewpager.getCurrentItem() == 0 && !isScrolled) {
                    indicator.setCurrentItem(viewpager.getAdapter().getCount() - 1);
                }
                break;
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (view.getLastVisiblePosition() == -1 + view.getCount()
                && scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            if (isLast) {
                ToastUtils.makeTextShort("没有更多");
                return;
            }
            if (!isLoading) {
                if (xlistView.getFooterViewsCount() == 0) {
                    xlistView.addFooterView(loadingView);
                }
                new LoadContent().execute();
            }
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

    }
}