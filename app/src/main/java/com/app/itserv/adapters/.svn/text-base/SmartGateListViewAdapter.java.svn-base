package com.app.itserv.adapters;

import java.util.List;

import com.itserv.shed.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 *  * @Description:所有访客adapter
 *  * @author:axin
 *  * @time:2016-8-20 下午4:47:30
 */
public class SmartGateListViewAdapter extends BaseAdapter implements OnClickListener {
    public Context context;
    public List list;

    public SmartGateListViewAdapter(Context context, List list) {
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
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.visitor_layout, null);
            holder.visitornamenew = (TextView) convertView.findViewById(R.id.visitornamenew);
            holder.visitornamenewtag = (TextView) convertView.findViewById(R.id.visitornamenewtag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.visitornamenewtag.setText("访客" + position + " : ");
        holder.visitornamenew.setText((String) list.get(position));
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }

    class ViewHolder {
        public TextView visitornamenew;
        public TextView visitornamenewtag;
    }
}
