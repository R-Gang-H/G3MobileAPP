package com.app.itserv.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.app.itserv.MineApplication;
import com.app.itserv.activity.CameraPopupActivity;
import com.app.itserv.activity.MyFarmingReportActivity;
import com.app.itserv.jparser.JsonTodayTaskObject;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;

import java.util.List;

/**
 * 当日任务适配器
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/8/31 18:39
 */
public class MyTodayTaskAdapter extends BaseAdapter {

    private Context mContext;

    private List<JsonTodayTaskObject.ObjBean> todayTaskObjList;
    private JsonTodayTaskObject.ObjBean todayTaskBean;//当日任务对象

    public MyTodayTaskAdapter(Context mContext, List<JsonTodayTaskObject.ObjBean> todayTaskObjList) {
        this.mContext = mContext;
        this.todayTaskObjList = todayTaskObjList;
    }

    @Override
    public int getCount() {
        return todayTaskObjList.size();
    }

    @Override
    public Object getItem(int position) {
        return todayTaskObjList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.my_today_task_item, null);
            vHolder = new ViewHolder();
            vHolder.tvCreateName = (TextView) convertView.findViewById(R.id.tv_create_name);//创建人
            vHolder.tvChargePerson = (TextView) convertView.findViewById(R.id.tv_charge_person);//负责人
            vHolder.tvBaseName = (TextView) convertView.findViewById(R.id.tv_base_name);//基地名称
            vHolder.tvGhouseName = (TextView) convertView.findViewById(R.id.tv_ghouse_name);//大棚名称
            vHolder.tvTaskName = (TextView) convertView.findViewById(R.id.tv_task_name);//任务名称
            vHolder.tvTaskTime = (TextView) convertView.findViewById(R.id.tv_task_time);//任务时间
            vHolder.btnStartTask = (Button) convertView.findViewById(R.id.btn_start_task);//开始任务
            vHolder.btnRecord = (Button) convertView.findViewById(R.id.btn_record);//农事记录
            vHolder.btnFarExaClose = (Button) convertView.findViewById(R.id.far_exa_close);//关闭
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        todayTaskBean = (JsonTodayTaskObject.ObjBean) getItem(position);//当日任务对象
        vHolder.tvCreateName.setText(TextUtils.isEmpty(todayTaskBean.getCreateName()) ? "" : todayTaskBean.getCreateName());//创建人
        vHolder.tvChargePerson.setText(TextUtils.isEmpty(todayTaskBean.getHeadName()) ? "" : todayTaskBean.getHeadName());//负责人
        vHolder.tvBaseName.setText(TextUtils.isEmpty(todayTaskBean.getBaseFullname()) ? "" : todayTaskBean.getBaseFullname());//基地名称
        vHolder.tvGhouseName.setText(TextUtils.isEmpty(todayTaskBean.getGhouseFullname()) ? "" : todayTaskBean.getGhouseFullname());//大棚名称
        vHolder.tvTaskName.setText(TextUtils.isEmpty(todayTaskBean.getTaskName()) ? "" : todayTaskBean.getTaskName());//任务名称
        vHolder.tvTaskTime.setText(DateLocalUtil.getDate(TextUtils.isEmpty(String.valueOf(todayTaskBean.getWorktaskDate())) ? "" : String.valueOf(todayTaskBean.getWorktaskDate())));//任务时间
        String todayTaskId = todayTaskBean.getId();
        if (todayTaskBean.getWorktaskOperateStatus().toString().trim().equals("IN_ACCEPTD")) {//如果是执行中（已领用）
            vHolder.btnStartTask.setVisibility(View.VISIBLE);//显示开始任务
            vHolder.btnRecord.setVisibility(View.GONE);//隐藏农事填报
            vHolder.btnStartTask.setOnClickListener(new OnStartTaskClick(todayTaskId));//开始任务
            vHolder.btnFarExaClose.setVisibility(View.GONE);//隐藏关闭
        } else if (todayTaskBean.getWorktaskOperateStatus().toString().trim().equals("STARTD")) {//如果是（开始）
            vHolder.btnStartTask.setVisibility(View.GONE);//隐藏开始任务
            vHolder.btnRecord.setVisibility(View.VISIBLE);//显示农事填报
            vHolder.btnRecord.setOnClickListener(new OnStartRecordClick(todayTaskBean));//农事填报
            vHolder.btnFarExaClose.setVisibility(View.VISIBLE);
            vHolder.btnFarExaClose.setOnClickListener(new OnMyFarTaskClick(todayTaskBean));//关闭点击
        } else if (todayTaskBean.getWorktaskOperateStatus().toString().trim().equals("CANCELED")) {//如果是（取消）
            vHolder.btnStartTask.setVisibility(View.GONE);//隐藏开始任务
            vHolder.btnRecord.setVisibility(View.GONE);//隐藏农事填报
            vHolder.btnFarExaClose.setVisibility(View.GONE);//隐藏关闭任务
        }
        return convertView;
    }

    /**
     * 开始任务
     */
    class OnStartTaskClick implements View.OnClickListener {

        private String toadyTaskId;

        public OnStartTaskClick(String todayTaskId) {
            this.toadyTaskId = todayTaskId;
        }

        @Override
        public void onClick(View v) {
            getCanActivaUser(toadyTaskId, "开始工作前请对工作前的环境进行拍照!", "myTaskStart");
        }
    }

    /**
     * 农事填报
     */
    class OnStartRecordClick implements View.OnClickListener {

        private JsonTodayTaskObject.ObjBean todayTaskBean;

        public OnStartRecordClick(JsonTodayTaskObject.ObjBean todayTaskBean) {
            this.todayTaskBean = todayTaskBean;
        }

        @Override
        public void onClick(View v) {
            String todayTaskId = String.valueOf(todayTaskBean.getId());
            String taskId = todayTaskBean.getTaskId();
            PreferencesUtils.putString(MineApplication.getContext(), "todayTaskId", todayTaskId);
            mContext.startActivity(new Intent(mContext, MyFarmingReportActivity.class).putExtra("todayTaskId", todayTaskId));
        }
    }

    /**
     * 关闭任务
     */
    class OnMyFarTaskClick implements View.OnClickListener {

        private JsonTodayTaskObject.ObjBean todayTaskBean;

        public OnMyFarTaskClick(JsonTodayTaskObject.ObjBean todayTaskBean) {
            this.todayTaskBean = todayTaskBean;
        }

        @Override
        public void onClick(View v) {
            getCanActivaUser(todayTaskBean.getId(), "关闭工作前请对工作后的环境进行拍照!", "myTaskStop");
        }
    }


    private final class ViewHolder {
        private TextView tvCreateName, tvChargePerson, tvBaseName, tvGhouseName, tvTaskName, tvTaskTime;
        private Button btnStartTask, btnRecord, btnFarExaClose;
    }


    /* 弹出dialog框start */
    private AlertDialog dialog;
    private Button quitEnsure;
    private Button quitCancel;

    /* 弹出dialog框end */
    // 开始任务提示
    private void getCanActivaUser(final String toadyTaskId, String content, final String mytask) {
        dialog = new AlertDialog.Builder(mContext).create();
//        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        View comfirmDialog = LayoutInflater.from(mContext).inflate(
                R.layout.comfirm_dialog1, null);
        dialog.addContentView(comfirmDialog,
                new ViewGroup.LayoutParams((int) (mContext.getResources()
                        .getDisplayMetrics().widthPixels * 0.9),
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(true);
        TextView comfirmContent = (TextView) comfirmDialog
                .findViewById(R.id.tv_comfirmdialog_content);
        quitEnsure = (Button) comfirmDialog.findViewById(R.id.btn_ensurequit);
        quitCancel = (Button) comfirmDialog.findViewById(R.id.btn_cancelquit);
        comfirmContent.setText(content);
        // 确认操作
        quitEnsure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, CameraPopupActivity.class).putExtra("myTaskDist", mytask)
                        .putExtra("toadyTaskId", toadyTaskId));//拍照弹窗
                dialog.dismiss();
            }
        });
        // 取消操作
        quitCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
