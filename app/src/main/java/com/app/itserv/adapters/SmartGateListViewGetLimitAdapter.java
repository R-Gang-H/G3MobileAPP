package com.app.itserv.adapters;

import java.util.List;

import com.app.itserv.adapters.SmartGateListViewAdapter.ViewHolder;
import com.itserv.app.bean.SmartLimit;
import com.itserv.shed.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 *  * @Description:已授权网关名称
 *  * @author:axin
 *  * @time:2016-11-25 下午3:40:29
 */
public class SmartGateListViewGetLimitAdapter extends BaseAdapter {

    public Context context;
    public List<SmartLimit> list;

    public SmartGateListViewGetLimitAdapter(Context context, List<SmartLimit> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.i("wwwwwwwwwwwwwwwwwww", "" + position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.smart_getlimit_layout, null);
            holder.smartname = (TextView) convertView.findViewById(R.id.smartname);
            holder.smartsn = (TextView) convertView.findViewById(R.id.smartsn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.smartname.setText(list.get(position).getSmartname());
        holder.smartsn.setText(list.get(position).getSmartsn());
        return convertView;
    }

    class ViewHolder {
        public TextView smartname;
        public TextView smartsn;
    }
}
