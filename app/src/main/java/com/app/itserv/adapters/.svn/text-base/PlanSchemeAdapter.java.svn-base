package com.app.itserv.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.itserv.activity.FarmingPlanAddActivity;
import com.app.itserv.jparser.JsonPlanSchemeObject.ObjBean;
import com.itserv.shed.R;

/**
 * 农事种植计划列表适配器
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyshed
 * @ClassName: PlanSchemeAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-5 下午3:32:24
 */
public class PlanSchemeAdapter extends BaseAdapter {

    private Context mContext;
    private List<ObjBean> planschemeList;
    private ViewHolder vHolder;
    private ObjBean planObj;

    public PlanSchemeAdapter(Context mContext, List<ObjBean> planschemeList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.planschemeList = planschemeList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return planschemeList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return planschemeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = View.inflate(mContext,
                    R.layout.far_plan_scheme_manager_items, null);
            vHolder = new ViewHolder();
            vHolder.tvGhouseName = (TextView) convertView
                    .findViewById(R.id.tv_ghouse_name);// 大鹏名称
            vHolder.tvPlanName = (TextView) convertView
                    .findViewById(R.id.tv_plan_name);// 计划名称
            vHolder.tvCategoryDefine = (TextView) convertView
                    .findViewById(R.id.tv_crop_category_define);// 种植作物
            vHolder.tvPlantdate = (TextView) convertView
                    .findViewById(R.id.tv_plan_plantdate);// 种植时间
            vHolder.lLDetails = (LinearLayout) convertView
                    .findViewById(R.id.ll_details);

            convertView.setTag(vHolder);
            vHolder.lLDetails.setTag(position);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        // planObj = getItem(position);
        planObj = planschemeList.get(position);
        String ghouseName = TextUtils.isEmpty(planObj.getGhouseFullname()) ? ""
                : planObj.getGhouseFullname();
        String planName = TextUtils.isEmpty(planObj.getPlanFullname()) ? ""
                : planObj.getPlanFullname();
        String categoryDefine = TextUtils.isEmpty(planObj
                .getCropCategoryDefine()) ? "" : planObj
                .getCropCategoryDefine();

        // 时间格式转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long plandateStart = planObj.getPlanPlantdateStart();// 发送信息时间
        long plandateEnd = planObj.getPlanPlantdateEnd();

        if (plandateStart != 0 && plandateEnd != 0) {
            Date dateplandateStart = new Date(plandateStart); // 根据long类型的毫秒数生命一个date类型的时间
            String SplandateStart = simpleDateFormat.format(dateplandateStart); // 把date类型的时间转换为string

            Date dateplandateEnd = new Date(plandateEnd); // 根据long类型的毫秒数生命一个date类型的时间
            String SplandateEnd = simpleDateFormat.format(dateplandateEnd); // 把date类型的时间转换为string

            vHolder.tvPlantdate.setText("种植时间："
                    + String.valueOf(SplandateStart) + "~"
                    + String.valueOf(SplandateEnd));
        } else {
            vHolder.tvPlantdate.setText("种植时间：" + String.valueOf("未知") + "~"
                    + String.valueOf("未知"));
        }

        vHolder.tvGhouseName.setText("大棚名称：" + ghouseName);// 大棚名称
        vHolder.tvPlanName.setText("计划名称：" + planName);// 计划名称
        vHolder.tvCategoryDefine.setText("种植作物：" + categoryDefine);// 种植作物

        // 设置点击监听
        vHolder.lLDetails.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String planId = planschemeList.get(position).getId();// 种植计划ID
                Log.i("xxxxxxxxxplanId", planId + "");
                Intent intent = new Intent(mContext,
                        FarmingPlanAddActivity.class);
                intent.putExtra("planId", planId);// 种植计划ID
                intent.putExtra("planaddedit", "planedit");// 编辑标识符
                mContext.startActivity(intent);

            }
        });
        return convertView;
    }

    static final class ViewHolder {
        private TextView tvGhouseName, tvPlanName, tvCategoryDefine,
                tvPlantdate;
        private LinearLayout lLDetails;
    }

}
