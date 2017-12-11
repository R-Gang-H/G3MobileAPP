package com.app.itserv.adapters;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.itserv.jparser.JsonUserGreenHouseObject.ObjBean;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;

/**
 * @project name：yyshed
 * @type name：UserGreenHouseAdapter
 * @description：用户管理---大棚查看
 * @author：gang
 * @date time：2017-6-13 上午9:53:44
 */
public class UserGreenHouseAdapter extends BaseAdapter {

    private Context mContext;

    private ViewHolder vHolder;
    private List<ObjBean> objbeanUserGreenHouse;
    private List<com.app.itserv.jparser.JsonDataDictionaryObject.ObjBean> userStatukList;

    public UserGreenHouseAdapter(
            Context mContext,
            List<ObjBean> objbeanUserGreenHouse,
            List<com.app.itserv.jparser.JsonDataDictionaryObject.ObjBean> userStatukList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.objbeanUserGreenHouse = objbeanUserGreenHouse;
        this.userStatukList = userStatukList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objbeanUserGreenHouse.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return objbeanUserGreenHouse.get(position);
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
                    R.layout.user_green_house_items, null);
            vHolder = new ViewHolder();
            vHolder.tvGreHouName = (TextView) convertView
                    .findViewById(R.id.gre_hou_name);// 大棚名称
            vHolder.tvGreHouCode = (TextView) convertView
                    .findViewById(R.id.ghouse_code);// 大棚编号
            vHolder.tvGreHouType = (TextView) convertView
                    .findViewById(R.id.gre_hou_type);// 大棚类型
            vHolder.tvBaseName = (TextView) convertView.findViewById(R.id.tv_base_name);// 所属基地
            vHolder.tvActivaDate = (TextView) convertView
                    .findViewById(R.id.tv_activa_date);// 启用日期
            vHolder.tvPlanArea = (TextView) convertView
                    .findViewById(R.id.tv_plan_area);// 种植面积
            vHolder.tvUserState = (TextView) convertView
                    .findViewById(R.id.tv_user_state);// 使用状态

            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        ObjBean objbeanugh = (ObjBean) getItem(position);
        vHolder.tvGreHouName.setText(TextUtils.isEmpty(objbeanugh
                .getGhouseFullname()) ? "" : objbeanugh.getGhouseFullname());
        vHolder.tvGreHouCode.setText(TextUtils.isEmpty(objbeanugh
                .getGhouseCode()) ? "" : objbeanugh.getGhouseCode());
        vHolder.tvGreHouType.setText(TextUtils.isEmpty(objbeanugh
                .getDescription()) ? "" : objbeanugh.getDescription());
        vHolder.tvBaseName
                .setText(TextUtils.isEmpty(objbeanugh.getBaseFullname()) ? ""
                        : objbeanugh.getBaseFullname());
        String activaDate = DateLocalUtil.getDate(String.valueOf(objbeanugh
                .getOpenDateOpen()));
        vHolder.tvActivaDate.setText(TextUtils.isEmpty(activaDate) ? ""
                : activaDate);
        vHolder.tvPlanArea
                .setText(TextUtils.isEmpty(objbeanugh.getUsedArea()) ? "(平米)"
                        : objbeanugh.getUsedArea() + "(平米)");
        String userStatus = objbeanugh.getStatus();// 使用状态
        for (int i = 0; i < userStatukList.size(); i++) {
            String userStatuId = userStatukList.get(i).getTypecode();
            if (userStatus.equals(userStatuId)) {
                String userStatuName = userStatukList.get(i).getTypename();
                vHolder.tvUserState
                        .setText(TextUtils.isEmpty(userStatuName) ? ""
                                : userStatuName);// 使用状态
            }
        }
        return convertView;
    }

    private final static class ViewHolder {
        private TextView tvGreHouName, tvGreHouCode, tvGreHouType, tvBaseName,
                tvActivaDate, tvPlanArea, tvUserState;

    }

}
