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

import com.app.itserv.jparser.JsonMyFarTaskObject.ObjBean;
import com.itserv.shed.R;

/**
 * 我的农事任务列表适配器
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyShed
 * @ClassName: FarTaskItemsAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-21 上午9:50:13
 */
public class FarTaskItemsAdapter extends BaseAdapter {

    private Context mContext;
    private List<ObjBean> myFarTaskBeanList;
    private ViewHolder vHolder;

    public FarTaskItemsAdapter(Context mContext, List<ObjBean> myFarTaskBeanList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.myFarTaskBeanList = myFarTaskBeanList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return myFarTaskBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return myFarTaskBeanList.get(position);
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
            convertView = View.inflate(mContext,
                    R.layout.far_task_setting_items, null);
            vHolder = new ViewHolder();
            vHolder.tvFarTaskName = (TextView) convertView
                    .findViewById(R.id.tv_far_task_name);// 任务名称
            vHolder.tvFarAssigner = (TextView) convertView
                    .findViewById(R.id.tv_far_assigner);// 分配人
            vHolder.tvDisTime = (TextView) convertView
                    .findViewById(R.id.tv_dis_time);// 分配时间
            vHolder.tvFarExecutor = (TextView) convertView
                    .findViewById(R.id.tv_far_executor);// 执行人
            vHolder.tvFarStatic = (TextView) convertView
                    .findViewById(R.id.tv_far_static);// 状态
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        ObjBean objFarTaskBean = (ObjBean) getItem(position);
        vHolder.tvFarTaskName.setText("任务名称：" + (TextUtils.isEmpty(objFarTaskBean
                .getTaskName()) ? "" : objFarTaskBean.getTaskName()));//任务名称
        vHolder.tvFarAssigner.setText("创建人：" + (TextUtils.isEmpty(objFarTaskBean
                .getCreateName()) ? "" : objFarTaskBean.getCreateName()));//分配人

        // 时间格式转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long ldistime = objFarTaskBean.getCreateDate();// 分配时间
        if (ldistime != 0) {
            Date datedistime = new Date(ldistime); // 根据long类型的毫秒数生命一个date类型的时间
            String Sdistime = simpleDateFormat.format(datedistime); // 把date类型的时间转换为string
            vHolder.tvDisTime.setText("创建时间：" + Sdistime);// 分配时间
        } else {
            vHolder.tvDisTime.setText("创建时间：" + "未知");// 分配时间
        }

		/*vHolder.tvDisTime.setText("分配时间："+(TextUtils.isEmpty(String
				.valueOf(objFarTaskBean.getCreateDate())) ? "" : String
				.valueOf(objFarTaskBean.getCreateDate())));*/// 分配时间
        vHolder.tvFarExecutor.setText("执行人：" + (TextUtils.isEmpty(objFarTaskBean
                .getUpdateName()) ? "" : objFarTaskBean.getUpdateName()));//执行人

        String status = TextUtils.isEmpty(objFarTaskBean.getStatus()) ? "" : objFarTaskBean.getAssignStatus();
        if (status.equals("UNDISTRIBUTED")) {
            vHolder.tvFarStatic.setText("分配状态：" + "未分配");//分配状态
        } else if (status.equals("ALLOCATED")) {
            vHolder.tvFarStatic.setText("分配状态：" + "已分配");//分配状态
        } else if (status.equals("CANCELED")) {
            vHolder.tvFarStatic.setText("分配状态：" + "已取消");//分配状态
        } else if (status.equals("CLOSED")) {
            vHolder.tvFarStatic.setText("分配状态：" + "已关闭");//分配状态
        } else {
            vHolder.tvFarStatic.setText("分配状态：" + "未知");//分配状态
        }
		
		/*vHolder.tvFarStatic.setText("分配状态："+(TextUtils.isEmpty(objFarTaskBean
				.getStatus()) ? "" : objFarTaskBean.getAssignStatus()));//执行状态*/
        return convertView;
    }

    static final class ViewHolder {
        private TextView tvFarTaskName, tvFarAssigner, tvDisTime,
                tvFarExecutor, tvFarStatic;
    }

}
