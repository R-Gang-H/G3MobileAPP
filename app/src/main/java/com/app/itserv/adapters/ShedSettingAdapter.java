package com.app.itserv.adapters;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.MineApplication;
import com.itserv.shed.R;
import com.yycloud.app.MyUser;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.WapiUtil;
import com.yycloud.core.beans.Components;
import com.yycloud.core.beans.ShedInfo;
import com.yycloud.core.config.Constants;

public class ShedSettingAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<Components> mDatas = new ArrayList<Components>();
	public Handler pHandler;
	private Handler paHandler;
	private String devid;

	public ShedSettingAdapter(Context context, String id, Handler handler) {
		this.devid = id;// 大棚的id
		this.paHandler = handler;
		this.mContext = context;
	}

	private String mAlias, mDevName;

	public void setDatas(List<Components> list, String[] keys1,
			String[] values1, String devName, String alias) {
		mAlias = alias;
		mDevName = devName;
		//r this.keys = keys1;
		//r this.values = values1;
		this.mDatas = (ArrayList<Components>) list;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 定义一个ImageView,显示在GridView里
		final int pos = position;
		final Components co = mDatas.get(pos);
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// 使用View的对象itemView与R.layout.item关联
			convertView = inflater
					.inflate(R.layout.shed_setting_item_lay, null);

			holder.component_name = (TextView) convertView
					.findViewById(R.id.component_name);
//			holder.component_sn = (TextView) convertView.findViewById(R.id.component_sn);
			holder.component_et = (EditText) convertView
					.findViewById(R.id.component_et);
			holder.component_icon = (ImageView) convertView
					.findViewById(R.id.component_icon);
			holder.component_update = (Button) convertView
					.findViewById(R.id.component_update);
			holder.seconed_name_setting = (TextView) convertView
					.findViewById(R.id.seconed_name_setting);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (pos == 0) {
			holder.seconed_name_setting.setVisibility(View.VISIBLE);
			holder.component_icon.setImageResource(R.drawable.house);
			String defaultAlias = co.getDev_alias();
			String alias = defaultAlias;
			if (mAlias != null && !mAlias.equals("") && !mAlias.equals(Constants.UNDEFINED)) {
				alias = mAlias;
			}

			String display_alias_name = alias;
			if (display_alias_name == null || "".equals(display_alias_name))
				display_alias_name = alias;
			holder.component_name.setText(mDevName);
//			holder.component_sn.setText(co.getSn());
			holder.component_et.setText(display_alias_name);
			holder.component_update.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String text = holder.component_et.getText().toString();
					new updateDevAlias().execute(text);
				}
			});
		} else {
			int resid = MineApplication.getComponentIcon(co.getDev_type(),co.getDev_extend_type(),
					co.getOnline_state(),co.getDev_name());
			holder.component_icon.setImageResource(resid);
			holder.seconed_name_setting.setVisibility(View.GONE);
			String defaultAlias = co.getDev_alias();
			String alias = defaultAlias;
			holder.component_name.setText(co.getDev_name());
			if (null == alias || alias.equals(Constants.UNDEFINED))
				alias = co.getDev_name();
			
			holder.component_et.setText(alias);
			holder.component_update.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String text = holder.component_et.getText().toString();
					String id = co.getSn();
					new updateDevComponentAlias().execute(id, text);
				}
			});
		}
		return convertView;
	}

	class ViewHolder {
		private TextView component_name;
		private Button component_update;
		private EditText component_et;
		private ImageView component_icon;
		private TextView seconed_name_setting;		
	}

	private class updateDevComponentAlias extends
			AsyncTask<Object, Void, String> {

		@Override
		protected String doInBackground(Object... arg0) {
			String id = (String) arg0[0];
			String alias = (String) arg0[1];
			//final String retMsg = "修改失败！";
			String result = MyUser.getInstance().updateDevComponentAlias(devid, id, alias, 
					new MYCallBack() {
				@Override
				public void onError(int code, String message) {
				}

				@Override
				public void onSuccess(String response) {
					ArrayList<Components> components = new ArrayList<Components>();
					ShedInfo shedInfo = MyUser.getInstance().getShedInfo(devid);
					components.addAll(shedInfo.getComponents());
					setDatas(components, null, null,
							shedInfo.getSmartgate().getDev_name(),
							shedInfo.getSmartgate().getDev_alias());
				};
			});
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			Message msg = new Message();
			msg.what = 1;
			msg.obj = result;
			paHandler.sendMessage(msg);
		}
	}

	private class updateDevAlias extends AsyncTask<Object, Void, String> {

		@Override
		protected String doInBackground(Object... arg0) {
			String alias = (String) arg0[0];
			String retMsg = "修改失败！";
			try {
				String result = WapiUtil.updateDevAlias(devid, alias);
				if (result != null && !"".equals(result)) {
					JSONObject object = new JSONObject(result);
					String ret = object.getString("alias");
					if (alias.equals(ret)) {
						retMsg = "修改成功！";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return retMsg;
		}

		@Override
		protected void onPostExecute(String result) {
			Message msg = new Message();
			msg.what = 1;
			msg.obj = result;
			paHandler.sendMessage(msg);
		}
	}

}
