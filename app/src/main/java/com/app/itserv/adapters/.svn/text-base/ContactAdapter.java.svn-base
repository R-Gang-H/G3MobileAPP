package com.app.itserv.adapters;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itserv.shed.R;
import com.yycloud.core.beans.YYContact;

public class ContactAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<YYContact> datas = new ArrayList<YYContact>();
    private Handler prHandler;

    public ContactAdapter(Context context, Handler handler) {
        this.mContext = context;
        prHandler = handler;
    }

    public void setDatas(ArrayList<YYContact> data) {
        this.datas = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 定义一个ImageView,显示在GridView里
        final int pos = position;
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 使用View的对象itemView与R.layout.item关联
            convertView = inflater.inflate(R.layout.alarm_num_item_lay, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.phone = (TextView) convertView.findViewById(R.id.phone);
            holder.del = (ImageView) convertView.findViewById(R.id.del_contact);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        YYContact contact = datas.get(pos);
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getPhone());
        final String phone = contact.getPhone();
        holder.del.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                final Dialog dialog = new AlertDialog.Builder(
                        mContext).create();
                // 设置点击范围外 无效果
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                // 填充自己想要的布局
                View ararmDialog = LayoutInflater.from(
                        mContext).inflate(
                        R.layout.alarm_num_delete_dialog, null);
                // 设置显示位置
                dialog.addContentView(
                        ararmDialog,
                        new ViewGroup.LayoutParams(
                                (int) (mContext.getResources()
                                        .getDisplayMetrics().widthPixels * 0.9),
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                TextView evAlarmDelete = (TextView) ararmDialog.findViewById(R.id.ev_alarm_delete_dialog_content);
                Button btnAlarmDelete_en = (Button) ararmDialog.findViewById(R.id.btn_alarm_delete_ensurequit);
                Button btnAlarmDelete_ca = (Button) ararmDialog.findViewById(R.id.btn_alarm_delete_cancelquit);
                evAlarmDelete.setText(phone);
                btnAlarmDelete_en.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        Message msg = new Message();
                        msg.what = 5;
                        msg.obj = phone;
                        prHandler.sendMessage(msg);
                        //datas.remove(phone);
                        dialog.dismiss();
                    }
                });
                btnAlarmDelete_ca.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        dialog.dismiss();

                    }
                });
            }
        });
        return convertView;

    }

    class ViewHolder {
        private TextView name;
        private ImageView del;
        private TextView phone;
    }

}
