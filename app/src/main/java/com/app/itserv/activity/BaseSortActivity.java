package com.app.itserv.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.app.commons.ToUIEvent;
import com.app.itserv.BaseActivity;
import com.app.itserv.adapters.FollowAdapter;
import com.app.itserv.jparser.JsonBaseDelObject;
import com.app.itserv.jparser.JsonBaseManagerObject.ObjBean;
import com.itserv.app.bean.Column;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.mobeta.android.dslv.DragSortListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


/**
 * 基地排序
 *
 * @author Administrator
 * @update 周作威 2017年9月22日 14:38:47
 */
public class BaseSortActivity extends BaseActivity implements OnClickListener {
    @BindView(R.id.follow_list)
    DragSortListView followListView;
    // 关注列表相关
    private ArrayList<Column> followList = new ArrayList<>();
    private FollowAdapter mFollowAdapter;
    private List<ObjBean> baseObjList;
    private StringBuffer greenbaseIds;

    @Override
    protected int layoutViewId() {
        return R.layout.base_sort_lay;
    }

    @Override
    protected void init() {
        super.init();
        initData();
        followListView.setDropListener(onDrop);
        mFollowAdapter = new FollowAdapter(this, followList);
        followListView.setAdapter(mFollowAdapter);
        followListView.setDragEnabled(true); // 设置是否可拖动。
    }

    private void initData() {
        Bundle budle = getIntent().getExtras();
        baseObjList = (List<ObjBean>) budle.get("baseObjList");
        for (int i = 0; i < baseObjList.size(); i++) {
            Column column1 = new Column();
            column1.id = i;
            column1.name = baseObjList.get(i).getBaseFullname();
            followList.add(column1);
        }
    }

    public void setBaseSortRequest() {
        greenbaseIds = new StringBuffer();
        for (int i = 0; i < followList.size(); i++) {
            if (followList.size() == baseObjList.size()) {
                if ((followList.get(i).id) < baseObjList.size()) {
                    greenbaseIds.append(baseObjList.get(followList.get(i).id).getId() + ",");
                }
            }
        }
        greenbaseIds.deleteCharAt(greenbaseIds.length() - 1);
        String token = PreferencesUtils.getString(BaseSortActivity.this, "token");// token
        String currTenantId = PreferencesUtils.getString(BaseSortActivity.this, "tenantId");// 商户id
        HttpManager.getInstance().basesort(TAG, token, currTenantId, greenbaseIds.toString(), new HttpCallBack<JsonBaseDelObject>(BaseSortActivity.this) {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort("排序失败");
            }

            @Override
            public void onSuccess(JsonBaseDelObject date) {
                if (null != date) {
                    if (date.isSuccess()) {
                        ToUIEvent toUIEvent = new ToUIEvent(ToUIEvent.UPDATE_BASEMANAGER, null);
                        EventBus.getDefault().post(toUIEvent);
                        ToastUtils.makeTextShort("排序成功");
                    }
                } else
                    ToastUtils.makeTextShort("排序失败");
            }
        });
    }

    // 监听器在手机拖动停下的时候触发
    private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
        @Override
        public void drop(int from, int to) {// from to 分别表示 被拖动控件原位置 和目标位置
            if (from != to) {
                Column column = (Column) mFollowAdapter.getItem(from);// 得到listview的适配器
                mFollowAdapter.remove(from);// 在适配器中”原位置“的数据。
                mFollowAdapter.insert(column, to);// 在目标位置中插入被拖动的控件。
            }
        }
    };

    @OnClick({R.id.img_back, R.id.changepsw_submite, R.id.changepsw_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.changepsw_submite:
                setBaseSortRequest();
                break;
            case R.id.changepsw_reset:
                break;
        }
    }
}
