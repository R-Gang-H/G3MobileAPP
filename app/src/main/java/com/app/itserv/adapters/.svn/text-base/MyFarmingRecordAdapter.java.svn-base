package com.app.itserv.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.itserv.jparser.JsonTodayTaskObject;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;

import java.util.List;

/**
 * 我的农事记录列表显示适配器
 *
 * @author 作者 E-mail:cyq
 * @version 1.0
 * @date 创建时间：2017-7-24 下午4:52:22
 * @parameter
 * @return
 */

public class MyFarmingRecordAdapter extends BaseAdapter {
    private Context mContext;
    List<JsonTodayTaskObject.ObjBean> todayTaskObjList;
    private ViewHolder mHolder;

    public MyFarmingRecordAdapter(Context mContext, List<JsonTodayTaskObject.ObjBean> todayTaskObjList) {
        super();
        this.mContext = mContext;
        this.todayTaskObjList = todayTaskObjList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return todayTaskObjList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return todayTaskObjList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.far_record_items, null);
            mHolder = new ViewHolder();
            mHolder.taskname = (TextView) convertView.findViewById(R.id.far_record_taskname);
            mHolder.recordtime = (TextView) convertView.findViewById(R.id.far_record_recordtime);
            mHolder.description = (TextView) convertView.findViewById(R.id.far_record_description);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        JsonTodayTaskObject.ObjBean objBean = todayTaskObjList.get(position);

        long lcreatedate = objBean.getCreateDate();// 发送信息时间getWorktaskDate
        if (lcreatedate != 0) {
            mHolder.recordtime.setText(DateLocalUtil.getDate(String.valueOf(lcreatedate)));// 记录时间
        } else {
            mHolder.recordtime.setText("未知");// 记录时间
        }
        mHolder.taskname.setText(TextUtils.isEmpty(objBean.getTaskName()) ? "未知" : objBean.getTaskName());
        mHolder.description.setText(TextUtils.isEmpty(objBean.getDescription()) ? "未知" : objBean.getDescription());
        return convertView;
    }

    private final static class ViewHolder {
        public TextView taskname;//任务名称
        public TextView recordtime;//记录时间
        public TextView description;//描述
    }


}
