package com.app.itserv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.BaseActivity;
import com.app.itserv.adapters.UserManagerAdapter;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonUserGetListObject;
import com.app.itserv.jparser.JsonUserGetListObject.ObjBean;
import com.app.itserv.views.PullToRefreshListView;
import com.app.itserv.views.PullToRefreshListView.OnRefreshListener;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.TAUtils;

import java.util.List;

/**
 * @project name：yyshed
 * @type name：UserManagerActivity
 * @description：员工管理---用户管理
 * @author：gang
 * @date time：2017-6-13 上午9:46:52
 */
public class UserManagerActivity extends BaseActivity implements OnRefreshListener, OnClickListener, OnItemClickListener {

    public static final String TAG = "UserManagerActivity";

    // 使用状态
    private List<JsonDataDictionaryObject.ObjBean> userStatukList;

    private ImageView ImgBack;

    public PullToRefreshListView userManagerList;
    public UserManagerAdapter userAdapter;
    private Button btAddUser;
    private TextView tvUserMng;

    private List<ObjBean> objbeanUserList;// 用户列表集合

    public int curItem;

    @Override
    protected int layoutViewId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_manager_lay);
        init();
        initView();
    }

    public void init() {
        // TODO Auto-generated method stub
        UserManagerListRequest();// 用户列表请求线程
        UserStateListRequest();// 使用状态数据字典
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        userManagerList = (PullToRefreshListView) findViewById(R.id.user_manager_items);
        userManagerList.setOnItemClickListener(this);// 刷新控件项的点击事件
        userManagerList.setOnRefreshListener(this);
        btAddUser = (Button) findViewById(R.id.add_user);
        btAddUser.setOnClickListener(this);
        tvUserMng = (TextView) findViewById(R.id.tv_user_mng);
    }

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        UserManagerListRequest();// 用户列表请求线程
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.add_user:// 新增用户
                startActivity(new Intent(UserManagerActivity.this, UserAddandEditActivity.class)
                        .putExtra("useraddandedit", "useradd"));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i(TAG, TAG + "onResume");
        init();
    }


    /**
     * @project name：yyshed
     * @type name：UserManagerRequest
     * @description：用户列表请求线程
     * @author：gang
     * @date time：2017-6-9 下午7:51:03
     */
    public void UserManagerListRequest() {

        String token = PreferencesUtils.getString(UserManagerActivity.this, "token");// token
        String currTenantId = PreferencesUtils.getString(UserManagerActivity.this,
                "tenantId");// 商户id
        HttpManager.getInstance().doUserGetList(TAG, token, currTenantId, new HttpCallBack<JsonUserGetListObject>(UserManagerActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort("用户列表请求失败!");
                userManagerList.onRefreshComplete();
            }

            @Override
            public void onSuccess(JsonUserGetListObject date) {
                userManagerList.onRefreshComplete();
                if (TextUtils.isEmpty(date.toString())) {
                    ToastUtils.makeTextShort("用户列表为空!");
                    return;
                }

                if (!date.equals("") && date != null) {
                    String msg = date.getMsg();// 提示消息
                    if (!date.isSuccess()) {// 请求用户列表失败
                        return;
                    }
                    objbeanUserList = date.getObj();// 用户列表集合

                    if (objbeanUserList != null) {
                        // 加载适配器
                        tvUserMng.setText(String.format("用户管理（%d）人",
                                objbeanUserList.size()));
                        userAdapter = new UserManagerAdapter(UserManagerActivity.this, objbeanUserList,
                                userStatukList);
                        userManagerList.setAdapter(userAdapter);
                    }
                }
            }
        });
    }

    // -------------------使用状态-------------------

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
        HttpManager.getInstance().doApiTypeGetList(TAG, key, new HttpCallBack<JsonDataDictionaryObject>(UserManagerActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }

            @Override
            public void onSuccess(JsonDataDictionaryObject date) {
                Log.i(TAG, "response 使用状态数据字典请求-> " + date);
                if (TextUtils.isEmpty(date.toString())) {
                    TAUtils.toastMessage(UserManagerActivity.this, "服务器异常请联系管理员!");
                    return;
                }
                if (!date.equals("")
                        && date != null) {

                    if (date.isSuccess()) {// 成功
                        userStatukList = date.getObj();
                    }else
                        TAUtils.toastMessage(UserManagerActivity.this,
                                date.getMsg());
                }
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        String userid = objbeanUserList.get(position - 1).getId();
        String tenantid = objbeanUserList.get(position - 1).getTenantId();
        startActivity(new Intent(UserManagerActivity.this, UserAddandEditActivity.class)
                .putExtra("useraddandedit", "useredit")
                .putExtra("userid", userid).putExtra("tenantid", tenantid));// 编辑用户(用户明细)
    }
}
