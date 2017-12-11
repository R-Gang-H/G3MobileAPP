package com.app.itserv.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.app.itserv.jparser.JsonGreenhouseManagerObject.ObjBean;
import com.itserv.shed.R;

import java.util.List;

/**
 * 大棚网关列表适配器
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: GreenHouseGateAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-11 上午11:15:12
 */

public class gHGateAdapter extends BaseAdapter {

    private Context mContext;

    private List<ObjBean> gHouseGateList;
    private ViewHolder vHolder;

    public static final String TAG = "gHGateAdapter";

    public gHGateAdapter(Context mContext, List<ObjBean> gHouseGateList) {
        super();
        this.mContext = mContext;
        this.gHouseGateList = gHouseGateList;
    }

    public void setObjList(List<ObjBean> gHouseGateList) {
        this.gHouseGateList = gHouseGateList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return gHouseGateList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return gHouseGateList.get(position);
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
            convertView = View.inflate(mContext,   R.layout.greenhouse_item, null);
            vHolder = new ViewHolder();
            vHolder.ghouseFullName = (TextView) convertView.findViewById(R.id.ghouse_full_name);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.ghouseFullName.setText(TextUtils.isEmpty(gHouseGateList.get(position).getGhouseFullname()) ? "" : gHouseGateList.get(position).getGhouseFullname());
        return convertView;
    }

    final static class ViewHolder {
        private TextView ghouseFullName;

    }

}
