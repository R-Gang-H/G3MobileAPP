package com.app.itserv.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itserv.app.bean.Column;
import com.itserv.shed.R;

public class FollowAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Column> followList;

    public FollowAdapter(Context context, List<Column> followList) {
        this.mContext = context;
        this.followList = followList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return followList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return followList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Column column = (Column) getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater
                    .inflate(R.layout.follow_listview_item, null);
            viewHolder.columnName = (TextView) convertView
                    .findViewById(R.id.columnName);
            viewHolder.drag_handle = (ImageView) convertView
                    .findViewById(R.id.drag_handle);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.columnName.setText(column.name);

        return convertView;
    }

    class ViewHolder {
        TextView columnName; // 列名
        ImageView drag_handle; // 滑动按钮
    }

    public void remove(int arg0) {
        followList.remove(arg0);
        this.notifyDataSetChanged();
    }

    public void insert(Column item, int arg0) {
        followList.add(arg0, item);
        this.notifyDataSetChanged();
    }

}
