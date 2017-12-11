package com.app.itserv.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/***
 * 自定义九宫格类
 *
 * @author Administrator
 *
 */
public class PagerGridView extends GridView {

    private boolean haveScrollbar = true;

    public PagerGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public PagerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public PagerGridView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    /***
     * 设置是否有ScrollBar，当要在ScollView中显示是，应当设置false。 默认为true
     *
     * @param haveScrollbar
     */
    public void setHaveScrollbar(boolean haveScrollbar) {
        this.haveScrollbar = haveScrollbar;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        if (haveScrollbar == false) {
            int expandSpec = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
