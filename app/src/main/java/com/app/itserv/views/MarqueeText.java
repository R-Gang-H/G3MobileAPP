package com.app.itserv.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自定义跑马灯
 *
 * @author Administrator
 */
public class MarqueeText extends TextView implements Runnable {

    private int currentScrollX;// 当滚动的位置
    private int textWidth;
    private boolean isMeasure = false;

    public MarqueeText(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if (!isMeasure) {
            getTextWidth();
            isMeasure = true;
        }
    }

    private void getTextWidth() {
        // TODO Auto-generated method stub
        Paint paint = this.getPaint();
        String str = this.getText().toString();
        textWidth = (int) paint.measureText(str);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        // TODO Auto-generated method stub
        super.setText(text, type);
        this.isMeasure = false;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        currentScrollX += 2;// 滚动速度
        scrollTo(currentScrollX, 0);
        if (getScrollX() >= textWidth) {
            scrollTo(-this.getWidth(), 0);
            currentScrollX = -this.getWidth();
        }
        postDelayed(this, 20);
    }

    public void startScroll() {
        this.removeCallbacks(this);
        post(this);
    }

}
