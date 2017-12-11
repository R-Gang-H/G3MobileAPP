package com.app.itserv.adapters;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.itserv.jparser.JsonFarTaskEditObject.ObjBean.PlantingTaskOpelogListBean;
import com.app.itserv.manager.DataWordQuery;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;

/**
 * 编辑农事任务-任务操作历史适配器
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyShed
 * @ClassName: FarTaskHistoryAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-25 下午2:28:13
 */

public class FarTaskHistoryAdapter extends BaseAdapter {

    private Context mContext;

    private ViewHolder vHolder;
    private List<PlantingTaskOpelogListBean> plantingTaskOpelogList;
    private PlantingTaskOpelogListBean planTaskOpelag;

    public FarTaskHistoryAdapter(Context mContext,
                                 List<PlantingTaskOpelogListBean> plantingTaskOpelogList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.plantingTaskOpelogList = plantingTaskOpelogList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return plantingTaskOpelogList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return plantingTaskOpelogList.get(position);
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
            convertView = View.inflate(mContext, R.layout.far_task_history,
                    null);
            vHolder = new ViewHolder();
            vHolder.tvOperaTime = (TextView) convertView
                    .findViewById(R.id.tv_opera_time);// 操作时间
            vHolder.tvOperator = (TextView) convertView
                    .findViewById(R.id.tv_operator);// 操作人
            vHolder.tvOperaAction = (TextView) convertView
                    .findViewById(R.id.tv_opera_action);// 操作动作
            vHolder.tvDistExplain = (TextView) convertView
                    .findViewById(R.id.tv_dist_explain);// 分配说明
            vHolder.tvSendee = (TextView) convertView
                    .findViewById(R.id.tv_sendee);// 接收人
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        planTaskOpelag = (PlantingTaskOpelogListBean) getItem(position);

        vHolder.tvOperaTime.setText(DateLocalUtil.getDate(TextUtils.isEmpty(String
                .valueOf(planTaskOpelag.getCreateDate())) ? "" : String
                .valueOf(planTaskOpelag.getCreateDate())));// 操作时间
        vHolder.tvOperator.setText(TextUtils.isEmpty(planTaskOpelag
                .getCreateName()) ? "" : planTaskOpelag.getCreateName());// 操作人
        String getoperaType = TextUtils.isEmpty(planTaskOpelag.getWorktaskStatus()) ? ""
                : planTaskOpelag.getWorktaskStatus();
        vHolder.tvOperaAction.setText(DataWordQuery.getInstance().getoperaType(getoperaType));// 操作类型
        vHolder.tvDistExplain.setText(TextUtils.isEmpty(planTaskOpelag
                .getOpeDesc()) ? "" : planTaskOpelag.getOpeDesc());// 说明
        vHolder.tvSendee.setText(PreferencesUtils.getString(mContext, "userName").toString());// 接收人
//        vHolder.tvSendee.setText(TextUtils.isEmpty(String
//                .valueOf(planTaskOpelag.getUpdateName())) ? "" : String
//                .valueOf(planTaskOpelag.getUpdateName()));// 接收人
        return convertView;
    }

    static final class ViewHolder {
        private TextView tvOperaTime, tvOperator, tvOperaAction, tvDistExplain,
                tvSendee;
    }

}
