package com.app.itserv.fragments;

import com.app.itserv.views.LoadingFrameView;
import com.app.itserv.views.PullToRefreshListView;
import com.itserv.shed.R;

import butterknife.BindView;

/**
 * 作者： 周作威 on 2017/10/9 13:56.
 * 类描述：农事记录
 */
public class FarmingRecordFragment extends BaseFragment {
    @BindView(R.id.column_list)
    PullToRefreshListView columnList;
    @BindView(R.id.load_view)
    LoadingFrameView loadView;

    @Override
    protected int layoutViewId() {
        return R.layout.news_fragment_content;
    }

    @Override
    public void onUpdateUI() {

    }
}
