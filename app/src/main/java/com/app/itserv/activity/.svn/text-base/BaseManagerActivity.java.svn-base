package com.app.itserv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.app.commons.ToUIEvent;
import com.app.itserv.BaseActivity;
import com.app.itserv.adapters.BaseManagerAdapter;
import com.app.itserv.jparser.JsonBaseManagerObject;
import com.app.itserv.jparser.JsonBaseManagerObject.ObjBean;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.views.LoadingFrameView;
import com.app.itserv.views.PullToRefreshListView;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 基地管理
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: BaseManagerActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-5 下午6:52:12
 * @update 周作威 2017年9月22日 14:38:47
 */
public class BaseManagerActivity extends BaseActivity {
    @BindView(R.id.tv_baseSize)
    TextView tvBaseSize;
    @BindView(R.id.base_manager_items)
    PullToRefreshListView baseManagerItems;
    @BindView(R.id.load_view)
    LoadingFrameView loadView;
    private List<JsonDataDictionaryObject.ObjBean> userStatukList;
    public static List<ObjBean> baseObjList;
    public BaseManagerAdapter baseAdapter;

    @Override
    protected int layoutViewId() {
        return R.layout.base_manager_lay;
    }

    @Override
    protected void init() {
        super.init();
        baseManagerItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String baseId = baseObjList.get(position - 1).getId();
                startActivity(new Intent(BaseManagerActivity.this, BaseAddandEditActivity.class)
                        .putExtra("baseaddandedit", "baseedit").putExtra("baseId", baseId));// 编辑基地
            }
        });
        baseManagerItems.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (null == userStatukList)
                    UserStateListRequest();// 使用状态数据字典
                else
                    setBaseManagerRequest();
            }
        });
        setUrl();
    }

    // 如果数据字典为空则先请求数据字典  周作威  2017/9/22 14:07
    public void setUrl() {
        loadView.setProgressShown(true);
        if (null == userStatukList)
            UserStateListRequest();// 使用状态数据字典
        else
            setBaseManagerRequest();
    }

    private void setBaseManagerRequest() {
        String token = PreferencesUtils.getString(BaseManagerActivity.this, "token");// token
        String currTenantId = PreferencesUtils.getString(BaseManagerActivity.this,
                "tenantId");// 商户id
        HttpManager.getInstance().basemanagerlist(TAG, token, currTenantId, new HttpCallBack<JsonBaseManagerObject>(BaseManagerActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                baseManagerItems.onRefreshComplete();
                loadView.setErrorShown(true, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setUrl();
                    }
                });
            }

            @Override
            public void onSuccess(JsonBaseManagerObject base_manager) {
                baseManagerItems.onRefreshComplete();
                if (base_manager != null) {
                    if (base_manager.isSuccess()) {// 成功
                        baseObjList = base_manager.getObj();
                        tvBaseSize.setText("基地管理(" + String.valueOf(baseObjList.size()) + ")个");
                        baseAdapter = new BaseManagerAdapter(BaseManagerActivity.this, baseObjList,
                                userStatukList);
                        baseManagerItems.setAdapter(baseAdapter);
                        loadView.delayShowContainer(true);
                    } else {
                        loadView.setErrorShown(true, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setUrl();
                            }
                        });
                    }
                } else {
                    loadView.setErrorShown(true, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setUrl();
                        }
                    });
                }
            }
        });
    }

    /**
     * 使用状态数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: SalesAreaListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-22 下午4:53:44
     */
    public void UserStateListRequest() {
        String key = "SP_COMMON_STATE";
        Log.v(TAG, TAG + "使用状态数据字典列表请求");
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(BaseManagerActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                baseManagerItems.onRefreshComplete();
                loadView.setErrorShown(true, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setUrl();
                    }
                });
            }

            @Override
            public void onSuccess(JsonDataDictionaryObject date) {
                if (date != null) {
                    if (date.isSuccess()) {// 成功
                        userStatukList = date.getObj();
                        setBaseManagerRequest();
                    } else
                        loadView.setErrorShown(true, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setUrl();
                            }
                        });
                } else
                    loadView.setErrorShown(true, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setUrl();
                        }
                    });
            }
        });
    }

    @OnClick({R.id.img_back, R.id.add_base, R.id.base_sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.add_base:
                startActivity(new Intent(BaseManagerActivity.this, BaseAddandEditActivity.class).putExtra("baseaddandedit", "baseadd"));
                break;
            case R.id.base_sort:
                if (null == baseObjList || baseObjList.size() <= 0) {
                    ToastUtils.makeTextShort("基地为空，请查看您是否有添加基地");
                    return;
                }
                Intent intent = new Intent(BaseManagerActivity.this, BaseSortActivity.class);
                Bundle budle = new Bundle();
                budle.putSerializable("baseObjList", (Serializable) baseObjList);
                intent.putExtras(budle);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onEvent(ToUIEvent event) {
        super.onEvent(event);
        if (event.getWhat() == ToUIEvent.UPDATE_BASEMANAGER) {
            setUrl();
        }
    }
}
