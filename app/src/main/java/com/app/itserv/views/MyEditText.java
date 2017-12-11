package com.app.itserv.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 自定义文本框下划线
 *
 * @author Administrator
 */
public class MyEditText extends EditText {

    private Paint mPaint;

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    public MyEditText(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    private void init() {
        // TODO Auto-generated method stub
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        // 根据具体需要在此处对画笔做更多个性化设置
        mPaint.setColor(Color.GRAY);
        mPaint.setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        // 画下划线
        // canvas.drawLine((this.getWidth()/4), this.getHeight()-1,
        // this.getWidth(),
        // this.getHeight()-1, mPaint);
    }

}
