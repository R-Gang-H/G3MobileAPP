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

import com.app.itserv.activity.ExamineProblemActivity;
import com.app.itserv.jparser.JsonAdvisoryProposalObject.ObjBean;
import com.itserv.shed.R;

/**
 * 咨询建议状态列表显示适配器
 *
 * @author 作者 E-mail:haoruigang
 * @version 1.0
 * @date 创建时间：2017-6-29 下午4:52:22
 * @parameter
 * @return
 */

public class AdvisoryProposalAdapter extends BaseAdapter {

    private Context mContext;
    private List<ObjBean> objBeanList;
    private ViewHolder vHolder;

    public AdvisoryProposalAdapter(Context mContext, List<ObjBean> objBeanList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.objBeanList = objBeanList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return objBeanList.get(position);
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
            convertView = View.inflate(mContext, R.layout.advi_prop_item_lay,
                    null);
            vHolder = new ViewHolder();
            vHolder.ll_reply_message = (LinearLayout) convertView
                    .findViewById(R.id.ll_reply_message);// 回复信息
            vHolder.tv_sendtime = (TextView) convertView
                    .findViewById(R.id.tv_sendtime);// 发送信息时间
            vHolder.tv_rece_msg_type = (TextView) convertView
                    .findViewById(R.id.tv_rece_msg_type);// 发送信息类型
            vHolder.tv_content_msg = (TextView) convertView
                    .findViewById(R.id.tv_content_msg);// 发送信息内容
            vHolder.tv_replytime = (TextView) convertView
                    .findViewById(R.id.tv_replytime);// 回复信息时间
            vHolder.tv_content_reply = (TextView) convertView
                    .findViewById(R.id.tv_content_reply);// 回复信息内容
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        String id, send_data = null, send_type = null, send_content = null, replyid, reply_data = null, reply_content = null;

        ObjBean objBean = objBeanList.get(position);// 咨询建议消息
        id = TextUtils.isEmpty(objBean.getId()) ? "" : objBean.getId();// 发送人id
        //时间格式转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long senddate = objBean.getSendDate();// 发送信息时间
        if (senddate != 0) {
            Date datesenddate = new Date(senddate); // 根据long类型的毫秒数生命一个date类型的时间
            String Ssenddate = simpleDateFormat.format(datesenddate); // 把date类型的时间转换为string
            vHolder.tv_sendtime.setText(Ssenddate);// 发送信息时间
        } else {
            vHolder.tv_sendtime.setText("未知");// 发送信息时间
        }

        send_type = TextUtils.isEmpty(objBean.getAdviceSendCategory()) ? ""
                : objBean.getAdviceSendCategory();// 发送信息类型
        send_content = TextUtils.isEmpty(objBean.getNoticeTitle()) ? "没有标题"
                : objBean.getNoticeTitle();// 发送信息标题
        replyid = TextUtils.isEmpty(objBean.getApplyBy()) ? "" : objBean
                .getApplyBy();// 回复人id
        reply_content = TextUtils.isEmpty(String.valueOf(objBean.getDescription())) ? "没有回复内容" : String.valueOf(objBean.getDescription());// 暂apply_content回复消息内容
        long replydate = objBean.getApplyDate();//  回复信息时间
        if (replydate != 0) {
            Date datereplydate = new Date(replydate); // 根据long类型的毫秒数生命一个date类型的时间
            String Sreplydate = simpleDateFormat.format(datereplydate); // 把date类型的时间转换为string
            vHolder.tv_replytime.setText(Sreplydate);//  回复信息时间
            if (reply_content.equals("null")) {
                vHolder.tv_content_reply.setText("没有回复内容");// 回复信息内容
            } else {
                vHolder.tv_content_reply.setText(reply_content.toString().trim());// 回复信息内容
            }

            vHolder.ll_reply_message.setVisibility(View.VISIBLE);//回复的布局显示
        } else {
            //vHolder.tv_replytime.setVisibility(View.GONE);
            //vHolder.tv_replytime.setText("未知");// 发送信息时间
        }

        //vHolder.tv_content_reply.setVisibility(View.GONE);

//        if (!send_type.isEmpty()) {
//            if (send_type.equals("NOTIFY") || send_type.equals("告知")) {//告知
//                vHolder.tv_rece_msg_type.setText("告知");// 问题分类
//            } else if (send_type.equals("CONSULT") || send_type.equals("咨询")) {//咨询
//                vHolder.tv_rece_msg_type.setText("咨询");// 问题分类
//            } else if (send_type.equals("ADVICE") || send_type.equals("建议")) {//建议
//                vHolder.tv_rece_msg_type.setText("建议");// 问题分类
//            } else if (send_type.equals("COMPLAIN") || send_type.equals("投诉")) {//投诉
//                vHolder.tv_rece_msg_type.setText("投诉");// 问题分类
//            } else if (send_type.equals("OTHER") || send_type.equals("其它")) {//其它
//                vHolder.tv_rece_msg_type.setText("其它");// 问题分类
//            } else {
//                vHolder.tv_rece_msg_type.setText("未知");// 问题分类
//            }
//        }
        vHolder.tv_content_msg.setText(send_content.toString().trim());// 发送信息内容
        return convertView;
    }

    private final static class ViewHolder {
        private LinearLayout ll_reply_message;// 回复信息
        private TextView tv_sendtime;// 发送信息时间
        private TextView tv_rece_msg_type;// 发送信息类型
        private TextView tv_content_msg;// 发送信息内容
        private TextView tv_replytime;// 回复信息时间
        private TextView tv_content_reply;// 回复消息内容
    }
}
