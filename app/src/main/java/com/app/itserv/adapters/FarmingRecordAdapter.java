package com.app.itserv.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.itserv.jparser.JsonFarmingRecordObject.ObjBean;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;

import java.util.List;

/**
 * 农事记录列表显示适配器
 *
 * @author 作者 E-mail:cyq
 * @version 1.0
 * @date 创建时间：2017-7-24 下午4:52:22
 * @parameter
 * @return
 */

public class FarmingRecordAdapter extends BaseAdapter {
    private Context mContext;
    private List<ObjBean> objList;
    private ViewHolder mHolder;


    public FarmingRecordAdapter(Context mContext,
                                List<ObjBean> objList) {
        super();
        this.mContext = mContext;
        this.objList = objList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return objList.get(position);
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
        ObjBean objBean = objList.get(position);

        long lcreatedate = objBean.getCreateDate();// 发送信息时间
        if (lcreatedate != 0) {
            mHolder.recordtime.setText(DateLocalUtil.getDate(String.valueOf(lcreatedate)));// 记录时间
        } else {
            mHolder.recordtime.setText("未知");// 记录时间
        }
        mHolder.taskname.setText(TextUtils.isEmpty(objBean.getTaskName()) ? "未知" : objBean.getTaskName());
        mHolder.description.setText(TextUtils.isEmpty(objBean.getWorkDesc()) ? "未知" : objBean.getWorkDesc());
        return convertView;
    }

    private final static class ViewHolder {
        public TextView taskname;//任务名称
        public TextView recordtime;//记录时间
        public TextView description;//描述
    }


}
