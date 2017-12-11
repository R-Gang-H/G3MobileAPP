package com.app.itserv.views;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

/***
 * 手势滑动
 *
 * @author Administrator
 *
 */
public class YScrollDetector extends SimpleOnGestureListener {

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return Math.abs(distanceY) >= Math.abs(distanceX);
    }

}
