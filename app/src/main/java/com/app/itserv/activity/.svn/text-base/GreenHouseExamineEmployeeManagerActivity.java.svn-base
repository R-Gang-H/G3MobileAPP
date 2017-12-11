package com.app.itserv.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.jparser.JsonGreHouExaEmployeObject.ObjBean;
import com.app.itserv.jparser.JsonOrganizationObject;
import com.app.itserv.jparser.JsonRoleGetListObject;
import com.app.itserv.jparser.JsonUserEditObject;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 大棚管理---员工管理---查看员工
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: GreenHouseExamineEmployeeManagerActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-14 上午11:18:39
 */
public class GreenHouseExamineEmployeeManagerActivity extends Activity
        implements OnClickListener {

    private static final String TAG = "GreenHouseExamineEmployeeManagerActivity";

    protected static final int GHOUSEEMPEXA_ERROR = 1;
    protected static final int GHOUSEEMPEXA_SUCCESS = 2;
    protected static final int GHOUSEEMPEXA_VALUES = 3;

    private Context mContext;

    private ImageView ImgBack;
    private TextView tvUserName, tvRealName, tvSex, tvBirthday, tvPhone,
            tvEmail, tvOrganization, tvRole;

    private String ghouseUserId;
    private String UserId;
    private ObjBean gHouExaEmpBean;

    private JsonUserEditObject.ObjBean conversationObj;
    private String userName;
    private String realName;
    private String phone;
    private String email;
    private String roleId;
    private String orgId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gre_hou_exa_emp_manager_lay);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        ghouseUserId = (String) bundle.get("ghouseUserId");
        UserId = (String) bundle.get("UserId");
        initView();
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        new Thread(new ConverRequest()).start();// 账户信息
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);// 返回
        ImgBack.setOnClickListener(this);
        tvUserName = (TextView) findViewById(R.id.tv_user_name);// 用户名
        tvRealName = (TextView) findViewById(R.id.tv_real_name);// 真实姓名
        tvSex = (TextView) findViewById(R.id.tv_sex);// 性别
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);// 生日
        tvPhone = (TextView) findViewById(R.id.tv_phone);// 手机号
        tvEmail = (TextView) findViewById(R.id.tv_email);// 邮箱
        tvOrganization = (TextView) findViewById(R.id.tv_organization);// 组织机构
        tvRole = (TextView) findViewById(R.id.tv_role);// 角色
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            default:
                break;
        }
    }


    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GHOUSEEMPEXA_ERROR:// 大棚用户列表请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case GHOUSEEMPEXA_SUCCESS:
                    String conver_sation = (String) msg.obj;
                    new Thread(new ConversaAction(conver_sation)).start();
                    break;
                case GHOUSEEMPEXA_VALUES:
                    userName = conversationObj.getUser().getUserName();
                    realName = conversationObj.getUser().getRealName();
                    phone = conversationObj.getUser().getMobilePhone();
                    email = conversationObj.getUser().getEmail();
                    String departName = conversationObj.getDepartname();
                    tvOrganization.setText(departName.substring(0, departName.length() - 1));
                    String roleName = conversationObj.getUser().getUserKey();
                    tvRole.setText(roleName);
                    tvUserName.setText(TextUtils.isEmpty(userName) ? "" : userName);
                    tvRealName.setText(TextUtils.isEmpty(realName) ? "" : realName);
                    tvPhone.setText(TextUtils.isEmpty(phone) ? "" : phone);
                    tvEmail.setText(TextUtils.isEmpty(email) ? "" : email);
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * @project name：yyshed
     * @type name：ConverRequest
     * @description：账户明细请求线程
     * @author：gang
     * @date time：2017-6-4 下午4:01:27
     */
    class ConverRequest extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userId", UserId);

                Log.v(TAG, TAG + "账户明细请求");

                WapiUtilEx.canverSation(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEEMPEXA_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = GHOUSEEMPEXA_SUCCESS;
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
     * @project name：yyshed
     * @type name：ConversaAction
     * @description：账户明细解析线程
     * @author：gang
     * @date time：2017-6-4 下午4:29:57
     */
    class ConversaAction extends Thread {

        private String conversa_req;

        public ConversaAction(String conver_sation) {
            // TODO Auto-generated constructor stub
            this.conversa_req = conver_sation;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();

                if (TextUtils.isEmpty(conversa_req)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonUserEditObject jsonuseredit = gson.fromJson(conversa_req,
                        JsonUserEditObject.class);
                if (!jsonuseredit.equals("") && jsonuseredit != null) {
                    if (jsonuseredit.isSuccess()) {// 成功

                        conversationObj = jsonuseredit.getObj();

                        Message msg = Message.obtain();
                        msg.what = GHOUSEEMPEXA_VALUES;
                        mHandler.sendMessage(msg);

                    }
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }
}
