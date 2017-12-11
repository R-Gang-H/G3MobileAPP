package com.app.itserv.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.jparser.JsonTodayTaskDetailObject;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;

import java.util.List;

/**
 * 每日任务农事记录
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyShed
 * @ClassName: TaskExecuteRecordAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-27 下午6:25:47
 */

public class TaskExecuteRecordAdapter extends BaseAdapter {
    private Context mContext;

    private List<JsonTodayTaskDetailObject.ObjBean.FramingRecordListBean> taskexeRecorList;
    private JsonTodayTaskDetailObject.ObjBean.FramingRecordListBean taskExecOpelag;
    private ViewHolder vHolder;

    public TaskExecuteRecordAdapter(Context mContext, List<JsonTodayTaskDetailObject.ObjBean.FramingRecordListBean> taskexeRecorList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.taskexeRecorList = taskexeRecorList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return taskexeRecorList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return taskexeRecorList.get(position);
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
            convertView = View.inflate(mContext, R.layout.far_task_record,
                    null);
            vHolder = new ViewHolder();
            vHolder.tvOperaTime = (TextView) convertView
                    .findViewById(R.id.tv_record_date);// 操作时间
            vHolder.tvOperator = (TextView) convertView
                    .findViewById(R.id.tv_recorder);// 操作人
            vHolder.tvOperaAction = (TextView) convertView
                    .findViewById(R.id.tv_record_action);// 操作动作
            vHolder.tvDistExplain = (TextView) convertView
                    .findViewById(R.id.tv_record_explain);// 分配说明
            vHolder.imgRecord = (ImageView) convertView.findViewById(R.id.img_record);//图片
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        taskExecOpelag = (JsonTodayTaskDetailObject.ObjBean.FramingRecordListBean) getItem(position);

        vHolder.tvOperaTime.setText(DateLocalUtil.getDate(TextUtils.isEmpty(String
                .valueOf(taskExecOpelag.getCreateDate())) ? "" : String
                .valueOf(taskExecOpelag.getCreateDate())));// 操作时间
        vHolder.tvOperator.setText(TextUtils.isEmpty(taskExecOpelag
                .getCreateName()) ? "" : taskExecOpelag.getCreateName());// 操作人
        vHolder.tvOperaAction
                .setText("填报记录");// 操作类型
        vHolder.tvDistExplain.setText(TextUtils.isEmpty(taskExecOpelag
                .getWorkDesc()) ? "" : taskExecOpelag.getWorkDesc());// 说明
//        vHolder.imgRecord.setImageDrawable(taskExecOpelag.getAttachment1());
        return convertView;
    }

    static final class ViewHolder {
        private TextView tvOperaTime, tvOperator, tvOperaAction, tvDistExplain;
        private ImageView imgRecord;
    }
}
