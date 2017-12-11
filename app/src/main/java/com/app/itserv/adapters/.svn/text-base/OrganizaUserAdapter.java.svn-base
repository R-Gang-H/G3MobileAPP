package com.app.itserv.adapters;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.itserv.jparser.JsonOrganizaUserRoleObject.ObjBean;
import com.app.itserv.jparser.JsonOrganizaUserRoleObject.ObjBean.CurrentDepartBean;
import com.itserv.shed.R;

/**
 * @project name：yyshed
 * @type name：OrganizaUserAdapter
 * @description：组织机构---查看用户
 * @author：gang
 * @date time：2017-6-14 下午5:20:02
 */
public class OrganizaUserAdapter extends BaseAdapter {
    public static final String TAG = "OrganizaUserAdapter";

    private Context mContext;
    private List<ObjBean> orguserObjBeanItems;// 用户列表

    ViewHolder vHolder = null;

    public OrganizaUserAdapter(Context mContext, List<ObjBean> orguserObjBean) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.orguserObjBeanItems = orguserObjBean;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return orguserObjBeanItems.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return orguserObjBeanItems.get(position);
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
            convertView = View.inflate(mContext, R.layout.user_manager_items,
                    null);
            vHolder = new ViewHolder();
            vHolder.ll_details = (LinearLayout) convertView
                    .findViewById(R.id.ll_details);
            vHolder.username = (TextView) convertView
                    .findViewById(R.id.username);// 用户名
            vHolder.organization = (TextView) convertView
                    .findViewById(R.id.organization);// 组织结构
            vHolder.real_name = (TextView) convertView
                    .findViewById(R.id.real_name);// 真实姓名
            vHolder.et_role = (TextView) convertView.findViewById(R.id.et_role);// 角色
            vHolder.tvCancell = (Button) convertView
                    .findViewById(R.id.tv_cancell);// 注销
            vHolder.tvActivation = (Button) convertView
                    .findViewById(R.id.tv_activation);// 激活
            vHolder.tvGreenhouse = (Button) convertView
                    .findViewById(R.id.tv_greenhouse);// 大棚
            vHolder.tvCancell.setVisibility(View.GONE);
            vHolder.tvActivation.setVisibility(View.GONE);
            vHolder.tvGreenhouse.setVisibility(View.GONE);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        ObjBean objbean = (ObjBean) getItem(position);

        String loginUser = objbean.getUserName();
        vHolder.username.setText(TextUtils.isEmpty(loginUser) ? "" : loginUser);// 用户名
        CurrentDepartBean currdepart = objbean.getCurrentDepart();
        String orgCode = (String) currdepart.getOrgCode();
        vHolder.organization.setText(TextUtils.isEmpty(orgCode) ? "" : orgCode);// 组织机构
        vHolder.real_name.setText(TextUtils.isEmpty(objbean.getRealName()) ? ""
                : objbean.getRealName());// 真实姓名
        vHolder.et_role.setText(TextUtils.isEmpty(objbean.getUserKey()) ? ""
                : objbean.getUserKey());// 角色

        return convertView;
    }

    private static final class ViewHolder {
        private TextView username, organization, real_name, et_role;
        private Button tvCancell, tvActivation, tvGreenhouse;
        private LinearLayout ll_details;

    }
}
