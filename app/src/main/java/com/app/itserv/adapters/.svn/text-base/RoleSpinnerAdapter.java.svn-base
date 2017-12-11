package com.app.itserv.adapters;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.itserv.jparser.JsonRoleGetListObject.ObjBean;
import com.itserv.shed.R;

/**
 * 角色下拉列表
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyShed
 * @ClassName: RoleSpinnerAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-4 下午4:07:58
 */
public class RoleSpinnerAdapter extends BaseAdapter {

    private Context mContext;
    private List<ObjBean> objbeanRoleList;

    ViewHolder holder = null;

    public RoleSpinnerAdapter(Context mContext, List<ObjBean> objbeanRoleList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.objbeanRoleList = objbeanRoleList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objbeanRoleList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return objbeanRoleList.get(position);
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
        holder.tvname.setText(TextUtils.isEmpty(objBeans.getRoleName()) ? ""
                : objBeans.getRoleName());
        return convertView;
    }

    class ViewHolder {
        private TextView tvname;
    }
}
