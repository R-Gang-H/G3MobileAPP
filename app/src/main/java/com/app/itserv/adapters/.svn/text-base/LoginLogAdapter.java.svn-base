package com.app.itserv.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.itserv.jparser.JsonLoginLogObject;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/23.
 * haoruigang
 * 2017-9-23 19:56:19
 * 登录日志适配器
 */

public class LoginLogAdapter extends BaseAdapter {

    private Context mContext;
    private List<JsonLoginLogObject.ObjBean> loginLog;

    public LoginLogAdapter(Context mContext, List<JsonLoginLogObject.ObjBean> loginLog) {
        this.mContext = mContext;
        this.loginLog = loginLog;
    }


    @Override
    public int getCount() {
        return loginLog.size();
    }

    @Override
    public Object getItem(int position) {
        return loginLog.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.login_log_items, null);
            vHolder = new ViewHolder();
            vHolder.tvLoginTime = (TextView) convertView.findViewById(R.id.tv_login_time);//登录时间
            vHolder.tvLoginUser = (TextView) convertView.findViewById(R.id.tv_login_user);//登录用户
            vHolder.tvLoginStatus = (TextView) convertView.findViewById(R.id.tv_login_status);//登录状态
            vHolder.tvDevice = (TextView) convertView.findViewById(R.id.tv_device);//设备
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        JsonLoginLogObject.ObjBean loginLog = (JsonLoginLogObject.ObjBean) getItem(position);
        // 时间格式转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datecreatedate = new Date(loginLog.getOperatetime()); // 根据long类型的毫秒数生命一个date类型的时间
        String operatetime = simpleDateFormat.format(datecreatedate); // 把date类型的时间转换为string
        vHolder.tvLoginTime.setText(TextUtils.isEmpty(operatetime) ? "" : operatetime);
        vHolder.tvLoginUser.setText(PreferencesUtils.getString(mContext, "userName"));
        String loginType = "";//1登录2登出
        if (String.valueOf(loginLog.getLoglevel()).equals("1")) {
            loginType = "登录";
        } else {
            loginType = "登出";
        }
        vHolder.tvLoginStatus.setText(loginType);
        vHolder.tvDevice.setText(TextUtils.isEmpty(loginLog.getBroswer()) ? "未知设备" : loginLog.getBroswer());
        return convertView;
    }


    static final class ViewHolder {
        TextView tvLoginTime, tvLoginUser, tvLoginStatus, tvDevice;
    }
}
