package com.app.itserv.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.MineApplication;
import com.app.itserv.activity.NewsContentActivity;
import com.itserv.shed.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.yycloud.core.beans.ECMSColumnContentList;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ECMSColumnContentList> datas = new ArrayList<ECMSColumnContentList>();
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public NewsAdapter(Context context) {

        this.mContext = context;
        ImageLoaderConfiguration config = MineApplication.getConfig(mContext);
        imageLoader.init(config);
    }

    public void setDatas(ArrayList<ECMSColumnContentList> contents) {
        this.datas = contents;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
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
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 使用View的对象itemView与R.layout.item关联
            convertView = inflater.inflate(R.layout.news_item_lay, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.img = (ImageView) convertView.findViewById(R.id.news_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(datas.get(pos).getTitle());
        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsContentActivity.class);
                intent.putExtra("content", datas.get(pos));
                mContext.startActivity(intent);
            }
        });
        imageLoader.displayImage(datas.get(pos).getSmallpic(), holder.img,
                MineApplication.default_img);
        return convertView;
    }

    class ViewHolder {
        private TextView title;
        private ImageView img;
    }

}
