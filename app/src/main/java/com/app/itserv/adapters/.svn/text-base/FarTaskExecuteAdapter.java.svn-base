package com.app.itserv.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.itserv.jparser.JsonFarExecuteTaskObject.ObjBean;
import com.itserv.shed.R;

/**
 * 执行任务适配器类
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyShed
 * @ClassName: FarTaskExecuteAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-26 下午7:26:32
 */

public class FarTaskExecuteAdapter extends BaseAdapter {

    private Context mContext;
    private List<ObjBean> execuTaskBeanList;
    private ViewHolder vHolder;

    public FarTaskExecuteAdapter(Context mContext,
                                 List<ObjBean> execuTaskBeanList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.execuTaskBeanList = execuTaskBeanList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return execuTaskBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return execuTaskBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.far_execute_items,
                    null);
            vHolder = new ViewHolder();
            vHolder.tvExeTaskName = (TextView) convertView
                    .findViewById(R.id.tv_exe_task_name);// 执行任务名称
            vHolder.tvAssigner = (TextView) convertView
                    .findViewById(R.id.tv_assigner);// 分配人
            vHolder.tvAllocatTime = (TextView) convertView
                    .findViewById(R.id.tv_allocat_time);// 分配时间
            vHolder.tvExecutor = (TextView) convertView
                    .findViewById(R.id.tv_executor);// 执行人
            vHolder.tvStatic = (TextView) convertView
                    .findViewById(R.id.tv_static);// 状态
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        ObjBean farTaskExecute = (ObjBean) getItem(position);// 执行任务对象

        vHolder.tvExeTaskName.setText("任务名称：" + (TextUtils.isEmpty(farTaskExecute
                .getTaskName()) ? "" : farTaskExecute.getTaskName()));// 执行任务名称
        vHolder.tvAssigner.setText("分配人：" + (TextUtils.isEmpty(farTaskExecute
                .getCreateName()) ? "" : farTaskExecute.getCreateName()));// 分配人


        // 时间格式转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long ldistime = farTaskExecute.getCreateDate();// 分配时间
        if (ldistime != 0) {
            Date datedistime = new Date(ldistime); // 根据long类型的毫秒数生命一个date类型的时间
            String Sdistime = simpleDateFormat.format(datedistime); // 把date类型的时间转换为string
            vHolder.tvAllocatTime.setText("分配时间：" + Sdistime);// 分配时间
        } else {
            vHolder.tvAllocatTime.setText("分配时间：" + "未知");// 分配时间
        }
        /*vHolder.tvAllocatTime.setText("分配时间："+(TextUtils.isEmpty(String
				.valueOf(farTaskExecute.getCreateDate())) ? "" : String
				.valueOf(farTaskExecute.getCreateDate())));// 执行时间*/

        vHolder.tvExecutor.setText("执行人：" + (TextUtils.isEmpty(farTaskExecute
                .getHeadName()) ? "" : farTaskExecute.getHeadName()));// 执行人
        String strstatus = TextUtils.isEmpty(farTaskExecute.getWorktaskDoStatus()) ? "" : farTaskExecute.getWorktaskDoStatus();//获取执行状态
        if (strstatus.equals("ASSIGNED")) {
            vHolder.tvStatic.setText("执行状态：" + "已分配");// 状态
        } else if (strstatus.equals("IN_EXECUTION")) {
            vHolder.tvStatic.setText("执行状态：" + "执行中");// 状态
        } else if (strstatus.equals("CLOSED")) {
            vHolder.tvStatic.setText("执行状态：" + "已关闭");// 状态
        } else if (strstatus.equals("CANCELED")) {
            vHolder.tvStatic.setText("执行状态：" + "已取消");// 状态
        } else {
            vHolder.tvStatic.setText("执行状态：" + "未知");// 状态
        }


        return convertView;
    }

    final static class ViewHolder {
        private TextView tvExeTaskName, tvAssigner, tvAllocatTime, tvExecutor,
                tvStatic;
    }

}
