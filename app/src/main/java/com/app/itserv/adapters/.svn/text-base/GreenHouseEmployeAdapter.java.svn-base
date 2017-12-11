package com.app.itserv.adapters;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.itserv.activity.GreenHouseExamineEmployeeManagerActivity;
import com.app.itserv.jparser.JsonGreHouEmpObject.ObjBean;
import com.itserv.shed.R;

/**
 * 大棚员工管理列表json报文解析类
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyshed
 * @ClassName: GreenHouseEmployeAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-13 下午5:41:43
 */
public class GreenHouseEmployeAdapter extends BaseAdapter {

    private Context mContext;

    private ViewHolder vHolder;
    private List<ObjBean> gHouseUserEmpList;

    public GreenHouseEmployeAdapter(Context mContext,
                                    List<ObjBean> gHouseUserEmpList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.gHouseUserEmpList = gHouseUserEmpList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return gHouseUserEmpList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return gHouseUserEmpList.get(position);
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
            convertView = View.inflate(mContext,
                    R.layout.gre_hou_emp_manager_items, null);
            vHolder = new ViewHolder();
            vHolder.tvGrehouUserName = (TextView) convertView
                    .findViewById(R.id.tv_grehou_user_name);// 大棚人员名称
            vHolder.tvGrehouRealName = (TextView) convertView
                    .findViewById(R.id.tv_grehou_real_name);// 大棚人员真实姓名
            vHolder.tvGrehouRole = (TextView) convertView
                    .findViewById(R.id.tv_grehou_role);// 大棚人员角色
            vHolder.tvGreHouStatic = (TextView) convertView
                    .findViewById(R.id.tv_green_house_static);// 大棚人员状态
            vHolder.btExaEmp = (TextView) convertView
                    .findViewById(R.id.bt_exa_emp);// 大棚人员查看
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        ObjBean grehouEmpBean = (ObjBean) getItem(position);
        vHolder.tvGrehouUserName.setText(TextUtils.isEmpty(grehouEmpBean
                .getUserName()) ? "" : grehouEmpBean.getUserName());
        vHolder.tvGrehouRealName.setText(TextUtils.isEmpty(grehouEmpBean
                .getCreateName()) ? "" : grehouEmpBean.getCreateName());
        vHolder.tvGrehouRole.setText(TextUtils.isEmpty(String
                .valueOf(grehouEmpBean.getCreateBy())) ? "" : String
                .valueOf(grehouEmpBean.getCreateBy()));
        String empStatus = TextUtils.isEmpty(grehouEmpBean
                .getStatus()) ? "" : grehouEmpBean.getStatus();
        String exaEmpStatus = null;
        if (empStatus.equals(0)) {//未激活
            exaEmpStatus = "未激活";
        } else {//已激活
            exaEmpStatus = "已激活";
        }
        vHolder.tvGreHouStatic.setText(exaEmpStatus);
        vHolder.btExaEmp.setOnClickListener(new OcGreHouEmployClick(
                grehouEmpBean));
        return convertView;
    }

    final static class ViewHolder {
        private TextView tvGrehouUserName, tvGrehouRealName, tvGrehouRole,
                tvGreHouStatic, btExaEmp;
    }

    /**
     * 大棚人员查看
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: OcGreHouEmployClick
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-13 下午6:20:06
     */
    class OcGreHouEmployClick implements OnClickListener {

        private ObjBean grehouEmpBean;

        public OcGreHouEmployClick(ObjBean grehouEmpBean) {
            // TODO Auto-generated constructor stub
            this.grehouEmpBean = grehouEmpBean;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mContext.startActivity(new Intent(mContext,
                    GreenHouseExamineEmployeeManagerActivity.class).putExtra(
                    "ghouseUserId", grehouEmpBean.getGhouseId()).putExtra(
                    "UserId", grehouEmpBean.getUserId()));
        }
    }

}
