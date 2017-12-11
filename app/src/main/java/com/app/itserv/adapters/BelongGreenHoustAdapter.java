package com.app.itserv.adapters;

import java.util.List;


import com.app.itserv.jparser.JsonbelongGreenHouseOBject.ObjBean;
import com.itserv.shed.R;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BelongGreenHoustAdapter extends BaseAdapter {
    private List<ObjBean> objBeans;
    private Context mContext;
    private ViewHolder holder;


    public BelongGreenHoustAdapter(List<ObjBean> objBeans, Context mContext) {
        super();
        this.objBeans = objBeans;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objBeans.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return objBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.data_dictionary_item, null);
            holder = new ViewHolder();
            holder.tvname = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(TextUtils.isEmpty(objBeans.get(position).getGhouseFullname()) ? "" : objBeans.get(position).getGhouseFullname());
        return convertView;
    }

    class ViewHolder {
        public TextView tvname;
    }

}
