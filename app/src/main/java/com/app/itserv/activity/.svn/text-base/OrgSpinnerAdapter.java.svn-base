package com.app.itserv.activity;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.itserv.jparser.JsonOrganizationObject.ObjBean;
import com.itserv.shed.R;

/**
 * 组织结构列表
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: OrgSpinnerAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-4 下午4:49:03
 */

public class OrgSpinnerAdapter extends BaseAdapter {

    private Context mContext;
    private List<ObjBean> objbeanOrgList;

    ViewHolder holder = null;

    public OrgSpinnerAdapter(Context mContext, List<ObjBean> organiObjbeanList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.objbeanOrgList = organiObjbeanList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objbeanOrgList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return objbeanOrgList.get(position);
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
            convertView = View.inflate(mContext, R.layout.data_dictionary_item,
                    null);
            holder = new ViewHolder();
            holder.tvname = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ObjBean objBeans = (ObjBean) getItem(position);
        holder.tvname.setText(TextUtils.isEmpty(objBeans.getDepartname()) ? ""
                : objBeans.getDepartname());
        return convertView;
    }

    class ViewHolder {
        private TextView tvname;
    }
}
