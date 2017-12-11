package com.app.itserv.adapters;

import java.util.List;

import com.app.itserv.adapters.PlantBelAdapter.ViewHolder;
import com.app.itserv.jparser.JsonUserGetListObject.ObjBean;

import com.itserv.shed.R;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 农事任务中的分配人
 *
 * @author yiqiang
 */
public class FarTaskAssignerAdapter extends BaseAdapter {
    private Context mContext;
    private List<ObjBean> objBeans;
    private ViewHolder holder;

    public FarTaskAssignerAdapter(Context mContext, List<ObjBean> objBeans) {
        super();
        this.mContext = mContext;
        this.objBeans = objBeans;
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
        holder.tvname.setText(TextUtils.isEmpty(objBeans.get(position).getUserName()) ? "" : objBeans.get(position).getUserName());
        return convertView;
    }

    class ViewHolder {
        public TextView tvname;
    }

}
