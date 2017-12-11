package com.app.itserv.adapters;

import java.util.List;

import com.app.itserv.jparser.JsonBelongRecorderObject.ObjBean;
import com.itserv.shed.R;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 农事记录中的 记录人的下拉选择列表
 *
 * @author yiqiang
 */
public class BelongRecorderAdapter extends BaseAdapter {
    private Context mContext;
    private List<ObjBean> objBeans;
    private ViewHolder holder;


    public BelongRecorderAdapter(Context mContext, List<ObjBean> objBeans) {
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
        Log.i("22222222222", objBeans.get(position).getUserName());
        holder.tvname.setText(TextUtils.isEmpty(objBeans.get(position).getUserName()) ? "" : objBeans.get(position).getUserName());
        return convertView;
    }

    class ViewHolder {
        public TextView tvname;
    }

}
