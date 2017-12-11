package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.itserv.shed.R;

/**
 * 员工管理类
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: StaffManageActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-23 下午12:51:30
 */
public class StaffManageActivity extends Activity implements OnClickListener {

    private Context mContext;
    private ImageView ImgBack;

    // private LinearLayout lLUserManager, lLRoleManager, lLOrganization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_manage_lay);
        mContext = this;
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        // lLUserManager = (LinearLayout) findViewById(R.id.user_manager);
        // lLUserManager.setOnClickListener(this);
        // lLRoleManager = (LinearLayout) findViewById(R.id.role_manager);
        // lLRoleManager.setOnClickListener(this);
        // lLOrganization = (LinearLayout) findViewById(R.id.organization);
        // lLOrganization.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            // case R.id.user_manager:// 用户管理
            // startActivity(new Intent(mContext, UserManagerActivity.class));
            // break;
            // case R.id.role_manager:// 角色管理
            // startActivity(new Intent(mContext, RoleManagerActivity.class));
            // break;
            // case R.id.organization:// 组织机构
            // startActivity(new Intent(mContext, OrganizationActivity.class));
            // break;
            default:
                break;
        }
    }

}
