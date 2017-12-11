package com.app.itserv.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.itserv.jparser.JsonTaskExecDetailObject;
import com.app.itserv.jparser.JsonTodayTaskDetailObject;
import com.itserv.shed.R;

import java.util.List;

/**
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/9/9 17:12
 */
public class TaskDetailHistoryAdapter extends TaskExecuteHistoryAdapter {
    private Context mContext;

    private List<JsonTaskExecDetailObject.ObjBean.YyPlantingTaskOpelogListBean> yyPlantingTaskOpelogList;
    private JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskOpelogListBean taskExecOpelag;
    private ViewHolder vHolder;


    public TaskDetailHistoryAdapter(Context mContext, List<JsonTaskExecDetailObject.ObjBean.YyPlantingTaskOpelogListBean> yyPlantingTaskOpelogList) {
        super();
        this.mContext = mContext;
        this.yyPlantingTaskOpelogList = yyPlantingTaskOpelogList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return yyPlantingTaskOpelogList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return yyPlantingTaskOpelogList.get(position);
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

        taskExecOpelag = (JsonTodayTaskDetailObject.ObjBean.YyPlantingTaskOpelogListBean) getItem(position);

        vHolder.tvOperaTime.setText(TextUtils.isEmpty(String
                .valueOf(taskExecOpelag.getCreateDate())) ? "" : String
                .valueOf(taskExecOpelag.getCreateDate()));// 操作时间
        vHolder.tvOperator.setText(TextUtils.isEmpty(taskExecOpelag
                .getCreateName()) ? "" : taskExecOpelag.getCreateName());// 操作人
        vHolder.tvOperaAction
                .setText(TextUtils.isEmpty(taskExecOpelag.getWorktaskStatus()) ? ""
                        : taskExecOpelag.getWorktaskStatus());// 操作类型
        vHolder.tvDistExplain.setText(TextUtils.isEmpty(taskExecOpelag
                .getOpeDesc()) ? "" : taskExecOpelag.getOpeDesc());// 说明
        vHolder.tvSendee.setText(TextUtils.isEmpty(String
                .valueOf(taskExecOpelag.getHeadName())) ? "" : String
                .valueOf(taskExecOpelag.getHeadName()));// 接收人
        return convertView;
    }

    static final class ViewHolder {
        private TextView tvOperaTime, tvOperator, tvOperaAction, tvDistExplain,
                tvSendee;
    }
}
