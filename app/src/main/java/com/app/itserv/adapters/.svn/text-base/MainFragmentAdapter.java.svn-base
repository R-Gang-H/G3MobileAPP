package com.app.itserv.adapters;

import java.util.List;

import com.itserv.app.bean.SmartMain;
import com.itserv.shed.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *  * @Description:主页面listview适配器
 *  * @author:axin
 *  * @time:2017-1-6 下午3:04:52
 *  
 */
public class MainFragmentAdapter extends BaseAdapter {
    public Context context;
    public List<SmartMain> list;
    ViewHolder viewholder = null;

    public MainFragmentAdapter(Context context, List<SmartMain> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            viewholder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_main_item, null);
            viewholder.mainFragmentItemShedName = (TextView) convertView.findViewById(R.id.mainFragmentItemShedName);
            viewholder.mainFragmentItemCropName = (TextView) convertView.findViewById(R.id.mainFragmentItemCropName);
            viewholder.mainFragmentItemArrowArea = (LinearLayout) convertView.findViewById(R.id.mainFragmentItemArrowArea);
            viewholder.mainFragmentItemArrow = (ImageView) convertView.findViewById(R.id.mainFragmentItemArrow);
            viewholder.mainFragmentItemData = (RelativeLayout) convertView.findViewById(R.id.mainFragmentItemData);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.mainFragmentItemShedName.setText(list.get(position).getSmartgatemainname());
        viewholder.mainFragmentItemCropName.setText(list.get(position).getSmartgatemaincropname());
//		viewholder.mainFragmentItemArrowArea.setOnClickListener(new OnClickListener() {
//			
//			@SuppressLint("NewApi") @Override
//			public void onClick(View view) {
//				viewholder.mainFragmentItemArrow.setBackground(context.getResources().getDrawable(R.drawable.expand2));
//				viewholder.mainFragmentItemData.setVisibility(View.VISIBLE);
//			}
//		});
//		if (viewholder.mainFragmentItemArrowArea.isClickable()) {
//			viewholder.mainFragmentItemArrow.setBackground(context.getResources().getDrawable(R.drawable.lead2));
//			viewholder.mainFragmentItemData.setVisibility(View.GONE);
//		}
        return convertView;
    }

    class ViewHolder {
        public TextView mainFragmentItemShedName;
        public TextView mainFragmentItemCropName;
        public LinearLayout mainFragmentItemArrowArea;
        public ImageView mainFragmentItemArrow;
        public RelativeLayout mainFragmentItemData;
    }
}
