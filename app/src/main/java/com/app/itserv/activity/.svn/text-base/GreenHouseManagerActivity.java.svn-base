package com.app.itserv.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.BaseActivity;
import com.app.itserv.adapters.GreenHouseManagerAdapter;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonGreenhouseManagerObject;
import com.app.itserv.jparser.JsonGreenhouseManagerObject.AttributesBean;
import com.app.itserv.jparser.JsonGreenhouseManagerObject.ObjBean;
import com.app.itserv.views.PullToRefreshListView;
import com.app.itserv.views.PullToRefreshListView.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 大棚管理
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: GreenHouseManagerActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-10 下午6:14:48
 */
public class GreenHouseManagerActivity extends BaseActivity implements OnClickListener, OnItemClickListener {
    @BindView(R.id.img_back)
    ImageView ImgBack;
    @BindView(R.id.tv_greenhouse_size)
    TextView tvGhouseSize;
    @BindView(R.id.add_green_house)
    Button btAddGreHou;
    @BindView(R.id.gre_hou_manager_items)
    PullToRefreshListView greHouManagerList;
    public GreenHouseManagerAdapter greHouAdapter;
    public int curItem;
    private List<ObjBean> gHouseObjList;
    // 状态
    private List<JsonDataDictionaryObject.ObjBean> datadicList;

    @Override
    protected int layoutViewId() {
        return R.layout.gre_hou_manager_lay;
    }

    @Override
    protected void init() {
        super.init();
        ImgBack.setOnClickListener(this);
        greHouManagerList.setOnItemClickListener(this);
        btAddGreHou.setOnClickListener(this);
        greHouManagerList.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 刷新  周作威  2017/9/25 9:50
                GreenHouseListRequest();// 大棚列表请求线程
            }
        });
    }

    public void setUrl() {
        // TODO Auto-generated method stub
        if (null == datadicList)
            StaticListRequest();// 状态数据字典
        else
            GreenHouseListRequest();// 大棚列表请求线程
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        setUrl();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.add_green_house:// 新增大棚
                startActivity(new Intent(GreenHouseManagerActivity.this, GreenHouseAddEditActivity.class)
                        .putExtra("grehouaddedit", "grehouadd"));
                break;
            // case R.id.tv_sin_pro:// 单品管理
            // startActivity(new Intent(mContext,
            // GreenHouseGateManagerActivity.class));
            // break;
            default:
                break;
        }
    }


    /**
     * 大棚列表请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-10 下午3:10:29
     */
    public void GreenHouseListRequest() {


        String token = PreferencesUtils.getString(GreenHouseManagerActivity.this, "token");// token
        String currTenantId = PreferencesUtils.getString(GreenHouseManagerActivity.this,
                "tenantId");// 商户id
        Log.v(TAG, TAG + "大棚列表请求");
        HttpManager.getInstance().

                ghousemanagerlist(TAG, token, currTenantId, new HttpCallBack<JsonGreenhouseManagerObject>() {
                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(JsonGreenhouseManagerObject date) {
                        if (TextUtils.isEmpty(date.toString())) {
                            TAUtils.toastMessage(GreenHouseManagerActivity.this, "服务器异常请联系管理员!");
                            return;
                        }
                        if (!date.equals("") && date != null) {
                            if (date.isSuccess()) {// 成功
                                gHouseObjList = date.getObj();
                                if (gHouseObjList != null) {
                                    JsonGreenhouseManagerObject.ObjBean bean = new JsonGreenhouseManagerObject.ObjBean();
                                    bean.setId("");
                                    bean.setGhouseFullname("全部");
                                    gHouseObjList.add(0, bean);
                                    tvGhouseSize.setText("大棚管理("
                                            + String.valueOf(gHouseObjList.size()) + ")个");
                                    greHouAdapter = new GreenHouseManagerAdapter(GreenHouseManagerActivity.this,
                                            gHouseObjList, datadicList);
                                    greHouManagerList.setAdapter(greHouAdapter);
                                    greHouManagerList.onRefreshComplete();
                                }
                            }
                        }
                    }
                });
    }

    // -------------------状态start-------------------

    /**
     * 状态数据字典请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: TaskNameListRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-29 下午3:30:58
     */
    public void StaticListRequest() {
        String key = "SP_COMMON_STATE";
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(GreenHouseManagerActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onSuccess(JsonDataDictionaryObject date) {
                if (TextUtils.isEmpty(date.toString())) {
                    TAUtils.toastMessage(GreenHouseManagerActivity.this, "服务器异常请联系管理员!");
                    return;
                }
                if (date != null) {
                    if (date.isSuccess()) {// 成功
                        datadicList = date.getObj();
                        GreenHouseListRequest();// 大棚列表请求线程
                    }
                }
            }
        });
    }


    // -------------------状态end-------------------

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        startActivity(new Intent(GreenHouseManagerActivity.this, GreenHouseAddEditActivity.class)
                .putExtra("grehouaddedit", "grehouedit")
                .putExtra("greenhouseId",
                        gHouseObjList.get(position - 1).getId()));
    }
}
