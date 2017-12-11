package com.app.itserv.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.app.itserv.BaseActivity;
import com.app.itserv.adapters.FollowAdapter;
import com.app.itserv.jparser.JsonGreenHouseObject;
import com.app.itserv.jparser.JsonGreenHouseObject.ObjBean;
import com.itserv.app.bean.Column;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.mobeta.android.dslv.DragSortListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 基地中大棚排序
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: GreenHouseSortActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-8 上午10:46:10
 */
public class GreenHouseSortActivity extends BaseActivity implements OnClickListener {
    @BindView(R.id.img_back)
    ImageView ImgBack;
    @BindView(R.id.follow_list)
    DragSortListView followListView;
    @BindView(R.id.changepsw_submite)
    Button changPsdsbm;
    // 关注列表相关
    private ArrayList<Column> followList = new ArrayList<>();
    private FollowAdapter mFollowAdapter;

    private String baseId;
    private List<ObjBean> gHouseList;
    private StringBuffer greenbaseIds;

    // 增加一个临时变量 保存所有的值 因为在后面列表为空时存在问题
    private ArrayList<Column> allList = new ArrayList<Column>();

    @Override
    protected int layoutViewId() {
        return R.layout.gre_hou_sort_lay;
    }

    @Override
    protected void init() {
        super.init();
        baseId = (String) getIntent().getExtras().get("baseId");
        followListView.setDropListener(onDrop);
        mFollowAdapter = new FollowAdapter(GreenHouseSortActivity.this, followList);
        followListView.setAdapter(mFollowAdapter);
        followListView.setDragEnabled(true); // 设置是否可拖动。
        ImgBack.setOnClickListener(this);
        changPsdsbm.setOnClickListener(this);
        setGreenHouseRequest();
    }

    public void setGreenHouseRequest() {
        String token = PreferencesUtils.getString(GreenHouseSortActivity.this, "token");// token
        String currTenantId = PreferencesUtils.getString(GreenHouseSortActivity.this,
                "tenantId");// 商户id
        HttpManager.getInstance().basegreenHouse(TAG, token, currTenantId, baseId, new HttpCallBack<JsonGreenHouseObject>() {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort(R.string.network_error);
            }

            @Override
            public void onSuccess(JsonGreenHouseObject greenHouse) {
                if (greenHouse != null) {
                    if (greenHouse.isSuccess()) {// 成功
                        gHouseList = greenHouse.getObj();
                        initData();
                        mFollowAdapter.notifyDataSetInvalidated();
                    } else {
                        ToastUtils.makeTextShort(greenHouse.getMsg());
                    }
                } else
                    ToastUtils.makeTextShort(R.string.network_error);
            }
        });
    }

    private void initData() {
        greenbaseIds = new StringBuffer();
        for (int i = 0; i < gHouseList.size(); i++) {
            Column column1 = new Column();
            column1.id = i;
            column1.name = gHouseList.get(i).getGhouseFullname();
            followList.add(column1);
            allList.add(column1);
            greenbaseIds.append(gHouseList.get(i).getId() + ",");
        }
    }

    // 监听器在手机拖动停下的时候触发
    private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
        @Override
        public void drop(int from, int to) {// from to 分别表示 被拖动控件原位置 和目标位置
            if (from != to) {
                Column column = (Column) mFollowAdapter.getItem(from);// 得到listview的适配器
                mFollowAdapter.remove(from);// 在适配器中”原位置“的数据。
                mFollowAdapter.insert(column, to);// 在目标位置中插入被拖动的控件。
                for (int i = 0; i < followList.size(); i++) {
                    if (followList.size() == gHouseList.size()) {
                        if ((followList.get(i).id) < gHouseList.size()) {
                            greenbaseIds.append(gHouseList.get(followList.get(i).id).getId() + ",");
                        }
                    }
                }
            }
        }
    };

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.changepsw_submite:// 提交
                if (null != gHouseList && gHouseList.size() > 0)
                    setGreenHouseSortRequest();// 基地中大鹏请求线程
                else
                    ToastUtils.makeTextShort("暂无大棚");
                break;
            default:
                break;
        }
    }

    public void setGreenHouseSortRequest() {
        String token = PreferencesUtils.getString(GreenHouseSortActivity.this, "token");// token
        String currTenantId = PreferencesUtils.getString(GreenHouseSortActivity.this,
                "tenantId");// 商户id

        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("greenbaseId", baseId);
        map.put("greenhouseIds", greenbaseIds.toString().trim());
        HttpManager.getInstance().greenHouseSort(TAG, token, currTenantId, baseId, greenbaseIds.toString().trim(), new HttpCallBack<JsonGreenHouseObject>(GreenHouseSortActivity.this) {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort(R.string.network_error);
            }

            @Override
            public void onSuccess(JsonGreenHouseObject greenHouse) {
                if (greenHouse != null) {
                    if (greenHouse.isSuccess()) {// 成功
                        ToastUtils.makeTextShort("排序成功");
                        finish();
                    } else
                        ToastUtils.makeTextShort(greenHouse.getMsg());
                } else
                    ToastUtils.makeTextShort(R.string.network_error);
            }
        });
    }
}
