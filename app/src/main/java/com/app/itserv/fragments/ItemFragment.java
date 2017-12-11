package com.app.itserv.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.app.itserv.activity.ExamineProblemActivity;
import com.app.itserv.adapters.AdvisoryProposalAdapter;
import com.app.itserv.jparser.JsonAdvisoryProposalObject;
import com.app.itserv.jparser.JsonAdvisoryProposalObject.ObjBean;
import com.app.itserv.views.LoadingFrameView;
import com.app.itserv.views.PullToRefreshListView;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.TAUtils;

import java.util.List;

import butterknife.BindView;

/**
 * 咨询建议
 *
 * @author 作者 E-mail:haoruigang
 * @version 1.0
 * @date 创建时间：2017-7-3 下午3:48:32
 * @parameter
 * @return
 */

public class ItemFragment extends BaseFragment implements PullToRefreshListView.OnRefreshListener, AdapterView.OnItemClickListener {

    @BindView(R.id.advisory_items)
    PullToRefreshListView advisoryItems;
    @BindView(R.id.load_view)
    LoadingFrameView loadView;

    private static final String TAG = "ItemFragment";

    private AdvisoryProposalAdapter advisoryadapter;

    private String title;
    private List<ObjBean> objBeanList;
    public int currentItem = 0;

    @Override
    protected int layoutViewId() {
        return R.layout.internal_message_items;
    }

    @Override
    public void onUpdateUI() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        initView();
        init();
    }

    private void initView() {
        // 获取Activity传递过来的参数
        Bundle mBundle = getArguments();
        title = mBundle.getString("arg");
        currentItem = mBundle.getInt("position");
        advisoryItems.setOnRefreshListener(this);
        advisoryItems.setOnItemClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void init() {
        // TODO Auto-generated method stub
        ProposalRequest();// 咨询建议请求接口
    }

    @Override
    public void onRefresh() {
        init();
    }


    /**
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: ProposalRequest
     * @Description: 咨询建议请求线程
     * @date 2017-6-24 下午3:34:00
     */
    private void ProposalRequest() {

        String token = PreferencesUtils.getString(getActivity(), "token");// token
        String currTenantId = PreferencesUtils.getString(getActivity(), "tenantId");// 商户id

        String replyStatus = null;
        switch (currentItem) {
            case 0:// 未回复
                replyStatus = "WAITINGREPLY";
                break;
            case 1:// 已答复（待关闭）
                replyStatus = "REPLYED";
                break;
            case 2:// 已关闭
                replyStatus = "CLOSED";
                break;
            default:
                break;
        }
        Log.i(TAG, TAG + "咨询建议请求");
        HttpManager.getInstance().doProposal(TAG, token, currTenantId, replyStatus, new HttpCallBack<JsonAdvisoryProposalObject>() {
            @Override
            public void onError(Throwable throwable) {
                advisoryItems.onRefreshComplete();
                loadView.setErrorShown(true, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProposalRequest();
                    }
                });
            }

            @Override
            public void onSuccess(JsonAdvisoryProposalObject date) {
                if (!date.equals("") && date != null) {

                    if (date.isSuccess()) {// 成功

                        objBeanList = date.getObj();
                        if (objBeanList != null) {
                            advisoryadapter = new AdvisoryProposalAdapter(getActivity(),
                                    objBeanList);
                            advisoryItems.setAdapter(advisoryadapter);
                            advisoryItems.onRefreshComplete();
                            loadView.delayShowContainer(true);
                        } else {
                            loadView.setNoShown(true);
                        }
                    } else {
                        loadView.setErrorShown(true, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ProposalRequest();
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ObjBean objBean = objBeanList.get(position - 1);
        startActivity(new Intent(getActivity(),
                ExamineProblemActivity.class).putExtra("adviceId",
                objBean.getId()));
    }
}
