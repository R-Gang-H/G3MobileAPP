package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
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

import com.app.itserv.adapters.GreenHouseDistrAdapter;
import com.app.itserv.adapters.GreenHouseDistrAdapter.ViewHolder;
import com.app.itserv.jparser.JsonBaseDelObject;
import com.app.itserv.jparser.JsonGreHouEmpObject;
import com.app.itserv.jparser.JsonUserGetListObject;
import com.app.itserv.jparser.JsonUserGetListObject.ObjBean;
import com.app.itserv.views.PullToRefreshListView;
import com.app.itserv.views.PullToRefreshListView.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大棚管理---员工管理---分配员工
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: GreenHouseDistriManagerActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-14 下午3:11:41
 */
public class GreenHouseDistriManagerActivity extends Activity implements OnClickListener, OnItemClickListener {

    private Context mContext;

    public static final String TAG = "GreenHouseDistriManagerActivity";

    protected static final int USERBIND_SUCCESS = 5;

    private ImageView ImgBack;
    private Button btnSave, btnReset;
    public PullToRefreshListView greHouDistrList;
    public GreenHouseDistrAdapter greHouDistrAdapter;
    private List<ObjBean> objbeanUserList;// 用户列表集合

    private List<JsonGreHouEmpObject.ObjBean> gHouseUserEmpList;
    private String greenhouseId;

    private int checkNum; // 记录选中的条目数量
    public static List<String> isBindUserId = new ArrayList<>();
    private StringBuffer userIds = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gre_hou_distri_lay);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        greenhouseId = (String) bundle.get("greenhouseId");
        // 初始化视图
        initView();
        // 初始化控件
        init();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        greHouDistrList = (PullToRefreshListView) findViewById(R.id.gre_hou_distr_items);
        // greHouDistrAdapter = new GreenHouseDistrAdapter();
        // greHouDistrList.setAdapter(greHouDistrAdapter);
        greHouDistrList.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                init();
            }
        });
        greHouDistrList.setOnItemClickListener(this);
        btnSave = (Button) findViewById(R.id.btn_save);// 保存
        btnSave.setOnClickListener(this);
        btnReset = (Button) findViewById(R.id.btn_reset);// 重置
        btnReset.setOnClickListener(this);
    }

    public void init() {
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
            case R.id.btn_save:// 保存
                for (String userId : isBindUserId) {
                    userIds.append(userId + ",");
                }
                new Thread(new GHouseUserBindRequest()).start();// 大棚员工绑定请求线程
                break;
            case R.id.btn_reset:// 重置

                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        if (view.getTag() instanceof ViewHolder) {

            // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
            ViewHolder holder = (ViewHolder) view.getTag();
            // 会自动出发CheckBox的checked事件，改变CheckBox的状态
            holder.ckIsDis.toggle();

            // 将CheckBox的选中状况记录下来
            objbeanUserList.get(position).isSelect = holder.ckIsDis.isChecked();
            // 调整选定条目
            if (holder.ckIsDis.isChecked() == true) {
                isBindUserId.add(objbeanUserList.get(position).getId());
                checkNum++;
            } else {
                isBindUserId.remove(objbeanUserList.get(position).getId());
                checkNum--;
            }
            // 用TextView显示
            // tv_show.setText("已选中" + checkNum + "项");
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case USERBIND_SUCCESS:
                    String userbind_json = (String) msg.obj;
                    new Thread(new GHouseUserBindAction(userbind_json)).start();
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * @project name：yyshed
     * @type name：UserManagerRequest
     * @description：用户列表请求线程
     * @author：gang
     * @date time：2017-6-9 下午7:51:03
     */
    /**
     * @project name：yyshed
     * @type name：UserManagerRequest
     * @description：用户列表请求线程
     * @author：gang
     * @date time：2017-6-9 下午7:51:03
     */
    public void UserManagerListRequest() {

        String token = PreferencesUtils.getString(GreenHouseDistriManagerActivity.this, "token");// token
        String currTenantId = PreferencesUtils.getString(GreenHouseDistriManagerActivity.this,
                "tenantId");// 商户id

        HttpManager.getInstance().doUserGetList(TAG, token, currTenantId, new HttpCallBack<JsonUserGetListObject>(GreenHouseDistriManagerActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort("请求失败!");
            }

            @Override
            public void onSuccess(JsonUserGetListObject date) {
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
                        GreHouEmpRequest();// 大棚员工列表请求线程
                    }
                }
            }
        });
    }


    /**
     * 大棚员工列表
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreHouEmpRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午5:04:13
     */
    public void GreHouEmpRequest() {

        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext,
                "tenantId");// 商户id

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("greenhouseId", greenhouseId);
        HttpManager.getInstance().ghouseemp(TAG, token, currTenantId, greenhouseId, new HttpCallBack<JsonGreHouEmpObject>(GreenHouseDistriManagerActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort("请求失败!");
            }

            @Override
            public void onSuccess(JsonGreHouEmpObject date) {
                if (!date.equals("") && date != null) {
                    if (date.isSuccess()) {// 成功
                        gHouseUserEmpList = date.getObj();
                        if (objbeanUserList != null && gHouseUserEmpList != null) {
                            isBindUserId.clear();
                            for (int i = 0; i < objbeanUserList.size(); i++) {
                                for (JsonGreHouEmpObject.ObjBean gHouseBean : gHouseUserEmpList) {
                                    if (gHouseBean.getUserId().equals(objbeanUserList.get(i).getId())) {
                                        objbeanUserList.get(i).isSelect = true;
                                        isBindUserId.add(objbeanUserList.get(i).getId());
                                        break;
                                    }
                                }
                            }
                            // 加载适配器
                            greHouDistrAdapter = new GreenHouseDistrAdapter(mContext,
                                    objbeanUserList, gHouseUserEmpList);
                            greHouDistrList.setAdapter(greHouDistrAdapter);
                            greHouDistrList.onRefreshComplete();
                        }
                    }
                }
            }
        });
    }

    /**
     * 大棚员工绑定请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyShed
     * @ClassName: GHouseUserDisRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-8-8 下午5:10:56
     */
    class GHouseUserBindRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

//				String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id

                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("tenantId", currTenantId);
                map.put("ghouseId", greenhouseId);
                map.put("userIds", userIds.toString().trim());
                map.put("status", 1 + "");

                Log.v(TAG, TAG + "大棚员工绑定请求");
                WapiUtilEx.ghouseUserBind(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        ToastUtils.makeTextShort(message + "请求失败");
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = USERBIND_SUCCESS;
                        msg.obj = response;
                        mHandler.sendMessage(msg);
                    }

                });

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }

    /**
     * 大棚员工绑定json解析
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseEmpAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午5:08:08
     */
    class GHouseUserBindAction extends Thread {

        private String userBindJson;

        public GHouseUserBindAction(String userBind_json) {
            // TODO Auto-generated constructor stub
            this.userBindJson = userBind_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(userBindJson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonBaseDelObject gHouse_user_emp = gson.fromJson(userBindJson,
                        JsonBaseDelObject.class);

                if (!gHouse_user_emp.equals("") && gHouse_user_emp != null) {
                    TAUtils.toastMessage((Activity) mContext,
                            gHouse_user_emp.getMsg());
                    if (gHouse_user_emp.isSuccess()) {// 成功
                        finish();
                    }
                }
            } catch (JsonSyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }
}
