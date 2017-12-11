package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itserv.shed.R;

/**
 * 农事管理
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: FarmingManageActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-26 下午6:15:26
 */

public class FarmingManageActivity extends Activity implements OnClickListener {

    private Context mContext;
    private ImageView ImgBack;

    private LinearLayout lLFarTypeSetting, lLFarPlanSetting, lLFarTaskSetting,
            lLExecuteTaskManager, lLFarRecordManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farming_lay);
        mContext = this;
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        lLFarTypeSetting = (LinearLayout) findViewById(R.id.far_type_setting);
        lLFarTypeSetting.setOnClickListener(this);
        lLFarPlanSetting = (LinearLayout) findViewById(R.id.far_plan_setting);
        lLFarPlanSetting.setOnClickListener(this);
        lLFarTaskSetting = (LinearLayout) findViewById(R.id.far_task_setting);
        lLFarTaskSetting.setOnClickListener(this);
        lLExecuteTaskManager = (LinearLayout) findViewById(R.id.execute_task_manager);
        lLExecuteTaskManager.setOnClickListener(this);
        lLFarRecordManager = (LinearLayout) findViewById(R.id.far_record_manager);
        lLFarRecordManager.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.far_type_setting:// 农事分类设置
                startActivity(new Intent(mContext, FarmingclassSetingActivity.class));
                break;
            case R.id.far_plan_setting:// 种植计划设置
                startActivity(new Intent(mContext,
                        FarmingPlantingSchemeSetingActivity.class));
                break;
            case R.id.far_task_setting:// 农事任务设置
                startActivity(new Intent(mContext, FarmingTaskSettingActivity.class));
                break;
            case R.id.execute_task_manager:// 执行任务管理
                startActivity(new Intent(mContext, FarmingExecuteManagerActivity.class));
                break;
            case R.id.far_record_manager:// 农事记录查看
                startActivity(new Intent(mContext, FarmingRecordActivity.class)
                        .putExtra("record", "manage"));
                break;
            default:
                break;
        }
    }
}
