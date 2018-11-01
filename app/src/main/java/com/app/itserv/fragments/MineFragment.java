package com.app.itserv.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.itserv.activity.AlarmNumberActivity;
import com.app.itserv.activity.BaseManagerActivity;
import com.app.itserv.activity.ConverSationsActivity;
import com.app.itserv.activity.EquipmentManagerActivity;
import com.app.itserv.activity.FarmingManageActivity;
import com.app.itserv.activity.FarmingTaskActivity;
import com.app.itserv.activity.FeedbackActivity;
import com.app.itserv.activity.GetLimitActivity;
import com.app.itserv.activity.GreenHouseManagerActivity;
import com.app.itserv.activity.MerchantInfoActivity;
import com.app.itserv.activity.MyFarmingRecordActivity;
import com.app.itserv.activity.ServerUrlActivity;
import com.app.itserv.activity.SettingUserActivity;
import com.app.itserv.activity.ShareActivity;
import com.app.itserv.activity.UserManagerActivity;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.QuitDialogUtil;
import com.itserv.shed.R;

/**
 * 我的
 *
 * @author haoruigang
 * @Package com.app.itserv.fragments
 * @project yyShed
 * @ClassName: MineFragment
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-1 上午10:11:13
 */
public class MineFragment extends BaseFragment implements OnClickListener {

    /* 修改前代码start */
    private RelativeLayout mFeedback, mContact, mGetLimitRl, mChangeLimitRl;
    private Context mContext;
    private AlertDialog dialog;
    private Button quitEnsure;
    private Button quitCancel;
    private Button settingUser;

	/* 修改后代码end */

    private TextView tvUserNicheng, tvCorpora;

    private LinearLayout lLMyAccount, lLMerchantInfo, lLUserManager, lLStaffManage, lLBaseManage,
            lLGreenhouseManage, lLEquipManage, lLFarmingManage, farmingContact,
            lLFarmingMission, lLFarmingRecord, llServerUrl;

    @Override
    protected int layoutViewId() {
        return R.layout.more_page_lay;
    }

    @Override
    public void onUpdateUI() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        initViews();
        init();
    }

    private void init() {

    }

    private void initViews() {
        /* 改造前代码start */
        mChangeLimitRl = (RelativeLayout) this.getView().findViewById(
                R.id.changeLimit);
        // 授权网关
        mGetLimitRl = (RelativeLayout) this.getView().findViewById(
                R.id.getLimit);

//        mLogout = (LinearLayout) this.getView().findViewById(R.id.loginout);
//        mLogout.setOnClickListener(this);
        mFeedback = (RelativeLayout) this.getView().findViewById(R.id.feekback);
        mFeedback.setOnClickListener(this);

        mContact = (RelativeLayout) this.getView()
                .findViewById(R.id.rl_contact);
        mContact.setOnClickListener(this);
        mChangeLimitRl.setOnClickListener(this);
        // 授权网关
        mGetLimitRl.setOnClickListener(this);
        /* 改造前代码end */
        settingUser = (Button) this.getView().findViewById(R.id.setting_user);// 设置
        settingUser.setOnClickListener(this);
        tvUserNicheng = (TextView) this.getView().findViewById(R.id.tv_user_nicheng);// 姓名(真实姓名)
        tvUserNicheng.setText(PreferencesUtils.getString(mContext, "realName", "King"));
        tvCorpora = (TextView) this.getView().findViewById(R.id.tv_corpora);// 企业
        tvCorpora.setText(PreferencesUtils.getString(mContext, "tenantName", "北京云洋数据科技有限公司"));
        llServerUrl = (LinearLayout) this.getView().findViewById(
                R.id.ll_server_url);// 修改服务器地址
        llServerUrl.setOnClickListener(this);
        lLMyAccount = (LinearLayout) this.getView().findViewById(
                R.id.my_account);// 我的账户
        lLMyAccount.setOnClickListener(this);
        lLMerchantInfo = (LinearLayout) this.getView().findViewById(
                R.id.merchant_info);
        lLMerchantInfo.setOnClickListener(this);// 商户信息
        // lLStaffManage = (LinearLayout)  this.getView().findViewById(R.id.staff_manage);//员工管理
        // lLStaffManage.setOnClickListener(this);
        lLUserManager = (LinearLayout) this.getView().findViewById(R.id.user_manager);// 用户管理
        lLUserManager.setOnClickListener(this);
        lLBaseManage = (LinearLayout) this.getView().findViewById(R.id.base_manage);// 基地管理
        lLBaseManage.setOnClickListener(this);
        lLGreenhouseManage = (LinearLayout) this.getView().findViewById(R.id.greenhouse_manage);// 大棚管理
        lLGreenhouseManage.setOnClickListener(this);
        lLEquipManage = (LinearLayout) this.getView().findViewById(R.id.equip_manage);// 设备管理
        lLEquipManage.setOnClickListener(this);
        lLFarmingManage = (LinearLayout) this.getView().findViewById(R.id.farming_manage);// 农事管理
        lLFarmingManage.setOnClickListener(this);
        farmingContact = (LinearLayout) this.getView().findViewById(R.id.farming_contact);// 告警电话
        farmingContact.setOnClickListener(this);
//        lLShare = (LinearLayout) this.getView().findViewById(R.id.share);
//        lLShare.setOnClickListener(this);// 分享
        lLFarmingMission = (LinearLayout) this.getView().findViewById(
                R.id.farming_mission);// 农事任务
        lLFarmingMission.setOnClickListener(this);
        lLFarmingRecord = (LinearLayout) this.getView().findViewById(
                R.id.farming_record);// 农事记录
        lLFarmingRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeLimit:
//                Intent changeLimitIntent = new Intent(mContext,
//                        ChangeLimitActivity.class);
//                mContext.startActivity(changeLimitIntent);
                break;
            // 授权网关
            case R.id.getLimit:
                Intent getLimitIntent = new Intent(mContext, GetLimitActivity.class);
                mContext.startActivity(getLimitIntent);
                break;
            case R.id.loginout:// 登出
                QuitDialogUtil.getInstance(getActivity()).showQuitDialog();
                break;
            case R.id.feekback:
            /*
             * Intent intent1 = new Intent(mContext, ShedActivity.class);
			 * mContext.startActivity(intent1);
			 */
                Intent intent1 = new Intent(mContext, FeedbackActivity.class);
                mContext.startActivity(intent1);
                break;
            case R.id.rl_contact:
                Intent intent2 = new Intent(mContext, AlarmNumberActivity.class);
                mContext.startActivity(intent2);
                break;
            case R.id.setting_user:// 设置
                startActivity(new Intent(getActivity(), SettingUserActivity.class));
                break;
            case R.id.ll_server_url:// 连接服务器地址
                startActivity(new Intent(getActivity(), ServerUrlActivity.class));
                break;
            case R.id.my_account:// 我的账户
                startActivity(new Intent(getActivity(), ConverSationsActivity.class));
                break;
            // case R.id.staff_manage:// 员工管理
            // startActivity(new Intent(mContext, StaffManageActivity.class));
            // break;
            case R.id.user_manager:// 用户管理
                startActivity(new Intent(mContext, UserManagerActivity.class));
                break;
            case R.id.base_manage:// 基地管理
                startActivity(new Intent(mContext, BaseManagerActivity.class));
                break;
            case R.id.greenhouse_manage:// 大棚管理
                startActivity(new Intent(mContext, GreenHouseManagerActivity.class));
                break;
            case R.id.equip_manage:// 设备管理
                startActivity(new Intent(mContext, EquipmentManagerActivity.class));
                break;
            case R.id.farming_manage:// 农事管理
                startActivity(new Intent(mContext, FarmingManageActivity.class));
                break;
            case R.id.farming_contact:// 告警电话
                startActivity(new Intent(mContext, AlarmNumberActivity.class));
                break;
            case R.id.share:// 分享
                Intent intent3 = new Intent(getActivity(), ShareActivity.class);
                intent3.putExtra("type", 1);
                startActivity(intent3);
                break;
            case R.id.merchant_info:// 商户信息
                startActivity(new Intent(getActivity(), MerchantInfoActivity.class));
                break;
            case R.id.farming_mission:// 农事任务
                startActivity(new Intent(mContext, FarmingTaskActivity.class));
                break;
            case R.id.farming_record:// 农事记录
                startActivity(new Intent(mContext, MyFarmingRecordActivity.class)
                        .putExtra("record", "record"));
                break;

            default:
                break;
        }
    }
}
