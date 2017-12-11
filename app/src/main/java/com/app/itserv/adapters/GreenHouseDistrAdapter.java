package com.app.itserv.adapters;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.app.itserv.activity.GreenHouseDistriManagerActivity;
import com.app.itserv.jparser.JsonGreHouEmpObject;
import com.app.itserv.jparser.JsonUserGetListObject.ObjBean;
import com.itserv.shed.R;

/**
 * 大棚分配员工列表adapter适配器
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyShed
 * @ClassName: GreenHouseDistrAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-8 下午12:17:55
 */

public class GreenHouseDistrAdapter extends BaseAdapter {

    private Context mContext;

    private ViewHolder vHolder;

    private List<ObjBean> objbeanUserList;
    private List<com.app.itserv.jparser.JsonGreHouEmpObject.ObjBean> gHouseUserEmpList;

    public GreenHouseDistrAdapter(
            Context mContext,
            List<ObjBean> objbeanUserList,
            List<com.app.itserv.jparser.JsonGreHouEmpObject.ObjBean> gHouseUserEmpList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.objbeanUserList = objbeanUserList;
        this.gHouseUserEmpList = gHouseUserEmpList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objbeanUserList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return objbeanUserList.get(position);
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
            convertView = View.inflate(mContext, R.layout.gre_hou_distri_items,
                    null);
            vHolder = new ViewHolder();
            vHolder.tvGhouDisId = (TextView) convertView
                    .findViewById(R.id.tv_ghou_dis_id);// 列表id
            vHolder.tvGhouDisName = (TextView) convertView
                    .findViewById(R.id.tv_ghou_dis_name);// 员工姓名
            vHolder.tvGhouDisSex = (TextView) convertView
                    .findViewById(R.id.tv_ghou_dis_sex);// 性别
            vHolder.ckIsDis = (CheckBox) convertView
                    .findViewById(R.id.ck_is_dis);// 是否分配
            vHolder.tvAge = (TextView) convertView.findViewById(R.id.tv_age);// 年龄
            vHolder.tvRole = (TextView) convertView.findViewById(R.id.tv_role);// 角色
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        ObjBean userBean = (ObjBean) getItem(position);
        vHolder.tvGhouDisId.setText(String.format("No.%d", position + 1));
        vHolder.tvGhouDisName.setText(userBean.getUserName());
        // vHolder.tvGhouDisSex.setText("");
        vHolder.ckIsDis.setChecked(userBean.isSelect);
        // vHolder.tvAge.setText("");
        // vHolder.tvRole.setText("");
        return convertView;
    }

    public final static class ViewHolder {
        private TextView tvGhouDisId, tvGhouDisName, tvGhouDisSex, tvAge,
                tvRole;
        public CheckBox ckIsDis;
    }
}
