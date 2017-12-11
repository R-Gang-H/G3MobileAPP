package com.app.itserv.adapters;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.jparser.JsonFarTaskEditObject.ObjBean.WorktaskListBean;
import com.app.itserv.manager.DataWordQuery;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;

/**
 * 编辑农事任务中-农事记录
 *
 * @author haoruigang
 * @Package com.app.itserv.adapters
 * @project yyShed
 * @ClassName: FarTaskRecordAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-25 下午4:57:02
 */

public class FarTaskRecordAdapter extends BaseAdapter {

    private Context mContext;

    private List<WorktaskListBean> worktaskList;
    private ViewHolder vHolder;

    private WorktaskListBean farTaskRecordBean;

    public FarTaskRecordAdapter(Context mContext,
                                List<WorktaskListBean> worktaskList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.worktaskList = worktaskList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return worktaskList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return worktaskList.get(position);
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
            convertView = View
                    .inflate(mContext, R.layout.far_task_record, null);
            vHolder = new ViewHolder();
            vHolder.tvRecordDate = (TextView) convertView
                    .findViewById(R.id.tv_record_date);// 记录时间
            vHolder.tvRecorder = (TextView) convertView
                    .findViewById(R.id.tv_recorder);// 记录人
            vHolder.tvRecordAction = (TextView) convertView
                    .findViewById(R.id.tv_record_action);// 记录动作
            vHolder.tvRecordExplain = (TextView) convertView
                    .findViewById(R.id.tv_record_explain);// 记录说明
            vHolder.imgRecord = (ImageView) convertView
                    .findViewById(R.id.img_record);// 记录图片
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        farTaskRecordBean = (WorktaskListBean) getItem(position);

        vHolder.tvRecordDate.setText(DateLocalUtil.getDate(TextUtils.isEmpty(String
                .valueOf(farTaskRecordBean.getCreateDate())) ? "" : String
                .valueOf(farTaskRecordBean.getCreateDate())));// 记录时间
        vHolder.tvRecorder.setText(TextUtils.isEmpty(farTaskRecordBean
                .getCreateName()) ? "" : farTaskRecordBean.getHeadName());// 记录人
        vHolder.tvRecordAction.setText(DataWordQuery.getInstance().gettastExecuStatus(TextUtils.isEmpty(farTaskRecordBean
                .getWorktaskDoStatus()) ? "" : farTaskRecordBean
                .getWorktaskDoStatus()));// 记录动作
        vHolder.tvRecordExplain.setText(TextUtils.isEmpty(String
                .valueOf(farTaskRecordBean.getDescription())) ? "" : String
                .valueOf(farTaskRecordBean.getDescription()));// 记录描述
        vHolder.imgRecord.setBackgroundResource(R.drawable.img_base);// 记录图片

        return convertView;
    }

    static final class ViewHolder {
        private TextView tvRecordDate, tvRecorder, tvRecordAction,
                tvRecordExplain;
        private ImageView imgRecord;
    }

}
