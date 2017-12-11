package com.app.itserv.adapters;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.itserv.jparser.JsonPlanSchemeObject.ObjBean;
import com.itserv.shed.R;

/**
 * 所属计划spinner列表
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyShed
 * @ClassName: PlantBelAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-29 下午2:50:55
 */

public class PlantBelAdapter extends BaseAdapter {

    private List<ObjBean> objBeans;
    private Context mContext;
    private ViewHolder holder;

    public PlantBelAdapter(Context mContext, List<ObjBean> planschemeList) {
        super();
        this.mContext = mContext;
        this.objBeans = planschemeList;
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
            convertView = View.inflate(mContext, R.layout.data_dictionary_item,
                    null);
            holder = new ViewHolder();
            holder.tvname = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(TextUtils.isEmpty(objBeans.get(position)
                .getPlanFullname()) ? "" : objBeans.get(position)
                .getPlanFullname());
        return convertView;
    }

    class ViewHolder {
        public TextView tvname;
    }

}
