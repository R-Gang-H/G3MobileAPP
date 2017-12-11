package com.app.itserv.activity;

import java.util.Date;
import java.util.TimeZone;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.commons.UtilityClass;
import com.app.itserv.BaseActivity;
import com.itserv.shed.R;

public class ShowEventDetailActivity extends BaseActivity {
    private TextView snTextView;
    private TextView event_idTextView;
    private TextView event_nameTextView;
    private TextView event_typeTextView;
    private TextView event_levelTextView;
    private TextView event_dateTextView;
    private TextView user_nameTextView;
    private TextView smartgate_snTextView;
    private TextView smartgate_nameTextView;
    private TextView detailTextView;
    private LinearLayout layout;
    private TextView mTitle;

    @Override
    protected int layoutViewId() {
        return R.layout.eventdetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        mTitle = (TextView) findViewById(R.id.title_txt);
        mTitle.setText("事件详细");
        snTextView = (TextView) this
                .findViewById(R.id.snDetail);
        /*
		 * event_idTextView = (TextView) this
		 * .findViewById(com.itserv.shed.R.id.event_idDetail);
		 */
        event_nameTextView = (TextView) this
                .findViewById(R.id.event_nameDetail);
        event_typeTextView = (TextView) this
                .findViewById(R.id.event_typeDetail);
        event_levelTextView = (TextView) this
                .findViewById(R.id.event_levelDetail);
        event_dateTextView = (TextView) this
                .findViewById(R.id.event_dateDetail);
		/*
		 * user_nameTextView = (TextView) this
		 * .findViewById(com.itserv.shed.R.id.user_nameDetail);
		 */
        smartgate_snTextView = (TextView) this
                .findViewById(R.id.smartgate_snDetail);
        smartgate_nameTextView = (TextView) this.findViewById(R.id.smartgate_nameDetail);
        detailTextView = (TextView) this
                .findViewById(R.id.detailDetail);

        this.findViewById(R.id.btn_back).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        finish();
                    }
                });

        String sn = getIntent().getStringExtra("sn");
        snTextView.setText(sn);
		/*
		 * String ev_id = getIntent().getStringExtra("event_id");
		 * event_idTextView.setText(ev_id);
		 */
        String ev_name = getIntent().getStringExtra("event_name");
        event_nameTextView.setText(ev_name);
        String ev_type = getIntent().getStringExtra("event_type");
        event_typeTextView.setText(ev_type);
        String ev_level = getIntent().getStringExtra("event_level");
        event_levelTextView.setText(ev_level);
        String ev_data = getIntent().getStringExtra("event_date");
        event_dateTextView.setText(convertDateFormat(ev_data));
		/*
		 * String us_name = getIntent().getStringExtra("user_name");
		 * user_nameTextView.setText(us_name);
		 */
        String sm_sn = getIntent().getStringExtra("smartgate_sn");
        smartgate_snTextView.setText(sm_sn);
        String sm_name = getIntent().getStringExtra("smartgate_name");
        smartgate_nameTextView.setText(sm_name);
        String detailString = getIntent().getStringExtra("detail").trim();
        detailTextView.setText(detailString);

    }

    /**
     * 将GMT 0类型的时间字符串转为本地时间
     *
     * @param oldDateStr
     * @return
     */
    private String convertDateFormat(String oldDateStr) {
        Date date = new Date(oldDateStr);
        date = UtilityClass.changeTimeZone(date, TimeZone.getTimeZone("GMT"),
                TimeZone.getDefault());
        return date.toLocaleString();
    }
}
