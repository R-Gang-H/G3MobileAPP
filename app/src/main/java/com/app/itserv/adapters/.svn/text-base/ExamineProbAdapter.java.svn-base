package com.app.itserv.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.app.itserv.adapters.BaseManagerAdapter.ViewHolder;
import com.app.itserv.jparser.JsonExamineProblemObject.ObjBean.UserAdviceReadReplyListBean;


import com.itserv.shed.R;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExamineProbAdapter extends BaseAdapter {
    private List<UserAdviceReadReplyListBean> list;
    private Context context;
    private ViewHolder holder;


    public ExamineProbAdapter(List<UserAdviceReadReplyListBean> list, Context context) {
        super();
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.examine_prob_lay_list, null);
            holder = new ViewHolder();
            holder.ll_examine_prob_lay_list_reply = (LinearLayout) convertView.findViewById(R.id.ll_examine_prob_lay_list_reply);
            holder.tv_examine_prob_lay_list_reply = (TextView) convertView.findViewById(R.id.tv_examine_prob_lay_list_reply);//答复还是追加
            holder.tv_examine_prob_lay_list_replytime = (TextView) convertView.findViewById(R.id.tv_examine_prob_lay_list_replytime);
            holder.reply_content = (TextView) convertView.findViewById(R.id.reply_content);
            holder.reply_data = (TextView) convertView.findViewById(R.id.reply_data);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String replydata = null;
        String replycontent = null;


        //集合
        UserAdviceReadReplyListBean userAdviceReadReplyListBean = list.get(position);
        String isAppendQuestion = TextUtils.isEmpty(userAdviceReadReplyListBean.getIsAppendQuestion()) ? "" : userAdviceReadReplyListBean.getIsAppendQuestion();

        if (isAppendQuestion.equals("Y")) {//是否是追加的
            holder.tv_examine_prob_lay_list_reply.setText("追加提问：");
            holder.tv_examine_prob_lay_list_reply.setTextColor(Color.WHITE);
            holder.tv_examine_prob_lay_list_replytime.setText("提问时间：");
        } else if (isAppendQuestion.equals("N")) {
            holder.tv_examine_prob_lay_list_reply.setText("答复：");
            holder.tv_examine_prob_lay_list_reply.setTextColor(Color.YELLOW);
            holder.tv_examine_prob_lay_list_replytime.setText("答复时间：");
        }
        replycontent = TextUtils.isEmpty(userAdviceReadReplyListBean.getApplyContent()) ? ""
                : userAdviceReadReplyListBean.getApplyContent();// 发送信息内容
        holder.reply_content.setText(replycontent.toString().trim());

        //时间格式转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lreplydata = userAdviceReadReplyListBean.getApplyDate();//// 回复信息时间
        if (lreplydata != 0) {
            Date datereplydata = new Date(lreplydata); // 根据long类型的毫秒数生命一个date类型的时间
            String Sreplydata = simpleDateFormat.format(datereplydata); // 把date类型的时间转换为string
            holder.reply_data.setText(Sreplydata);//// 回复信息时间
        } else {
            holder.reply_data.setText("未知");//提问时间
        }

        //holder.reply_content.setText(userAdviceReadReplyListBean.getApplyDate()+"");//// 回复信息时间

        return convertView;
    }

    private final static class ViewHolder {
        private LinearLayout ll_examine_prob_lay_list_reply;
        private TextView tv_examine_prob_lay_list_reply;// 答复
        private TextView tv_examine_prob_lay_list_replytime;// 答复时间
        private TextView reply_data;// 时间
        private TextView reply_content;// 内容
    }


}
