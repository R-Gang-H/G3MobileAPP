package com.app.itserv.views;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Shader;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.itserv.shed.R;

/**
 * 线框图
 *
 * @author Administrator
 */
public class Chartengine extends View {

    private List<Float> milliliter;
    private float tb;
    private float interval_left_right;// 横向每一等份的宽度
    private float margin_bottom;
    private float margin_left;
    private Paint paint_date, paint_brokenLine, paint_dottedline,
            paint_brokenline_big, framPanint, paintxytext;

    private int time_index;
    private Bitmap bitmap_point;
    private Path path;
    private float dotted_text;
    private int line = 5;// 多少行
    private int linesum = 20;// 一行表示多少
    private float startx = 0;
    private float starty = 0;
    private float width = 0.0f;
    private List<String> mTexts;

    public float getDotted_text() {
        return dotted_text;
    }

    public void setDotted_text(float dotted_text) {
        this.dotted_text = dotted_text;
    }

    private int fineLineColor = 0x5faaaaaa; // 灰色
    private int blueLineColor = 0xff00ffff; // 蓝色
    private int orangeLineColor = 0xffd56f2b; // 橙色
    private int black = 0xff000000;

    public Chartengine(Context context, List<Float> milliliter,
                       List<String> mstr) {
        super(context);
        mTexts = mstr;
        init(milliliter);
    }

    public void init(List<Float> milliliter) {
        if (null == milliliter || milliliter.size() == 0)
            return;
        this.milliliter = milliliter;
        Resources res = getResources();
        tb = res.getDimension(R.dimen.historyscore_tb);
        margin_bottom = 8 * tb * 0.2f;// 16
        margin_left = 8 * tb * 0.2f + 10;// 16
        paint_date = new Paint();// 画日期
        paint_date.setStrokeWidth(tb * 0.1f);
        paint_date.setTextSize(tb * 1.2f);
        paint_date.setColor(fineLineColor);

        paintxytext = new Paint();// 画x,y轴的字
        paintxytext.setStrokeWidth(tb * 0.1f);
        paintxytext.setTextSize(tb * 1.2f);
        paintxytext.setColor(black);

        paint_brokenLine = new Paint();// 画抛物线
        paint_brokenLine.setStrokeWidth(tb * 0.1f);
        paint_brokenLine.setColor(blueLineColor);
        paint_brokenLine.setAntiAlias(true);

        paint_dottedline = new Paint();
        paint_dottedline.setStyle(Paint.Style.STROKE);
        paint_dottedline.setColor(fineLineColor);

        paint_brokenline_big = new Paint();
        paint_brokenline_big.setStrokeWidth(tb * 0.2f);
        paint_brokenline_big.setColor(fineLineColor);
        paint_brokenline_big.setAntiAlias(true);

        framPanint = new Paint();
        framPanint.setAntiAlias(true);
        framPanint.setStrokeWidth(2f);

        path = new Path();
        bitmap_point = BitmapFactory.decodeResource(getResources(),
                R.drawable.icon_point_blue);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
    }

    protected void onDraw(Canvas c) {
        if (null == milliliter || milliliter.size() == 0)
            return;
        interval_left_right = (getWidth() - 20 - margin_left) / 5.0f;
        width = interval_left_right * 5;
        drawStraightLine(c);
        drawBrokenLine(c);
        drawDate(c);
        drawxText(c);
    }

    /**
     * 绘制竖线
     *
     * @param c
     */
    public void drawStraightLine(Canvas c) {
        for (int i = 0; i < milliliter.size(); i++) {
            if (i == 0) {// 绘制Y轴
                c.drawLine(interval_left_right * i + margin_left, 0,
                        interval_left_right * i + margin_left, getHeight()
                                - margin_bottom, paint_date);
                int lineTotal = line;
                // 绘制横线
                for (int j = 0; j < lineTotal; j++) {
                    paint_dottedline.setColor(orangeLineColor);
                    if (j == lineTotal - 1) {
                        // 绘制横线的顶端一根为实线
                        c.drawLine(margin_left, 0, width - 8, 0, paint_date);
                    } else {
                        // 绘制虚线
                        Path path = new Path();
                        path.moveTo(margin_left, (getHeight() - margin_bottom)
                                / line * (line - (j + 1)));
                        path.lineTo(width, (getHeight() - margin_bottom) / line
                                * (line - (j + 1)));
                        PathEffect effects = new DashPathEffect(new float[]{
                                tb * 0.3f, tb * 0.3f, tb * 0.3f, tb * 0.3f},
                                tb * 0.1f);
                        paint_dottedline.setPathEffect(effects);
                        c.drawPath(path, paint_dottedline);
                    }
                }
                continue;
            }
            if (i == milliliter.size() - 1) {
                // 绘制竖线最后一根线为实线
                c.drawLine(interval_left_right * i + margin_left, 0,
                        interval_left_right * i + margin_left, getHeight()
                                - margin_bottom, paint_date);
            } else {
                // 绘制竖线
                Path path = new Path();
                path.moveTo(interval_left_right * i + margin_left, 0);
                path.lineTo(interval_left_right * i + margin_left, getHeight()
                        - margin_bottom);
                PathEffect effects = new DashPathEffect(new float[]{
                        tb * 0.3f, tb * 0.3f, tb * 0.3f, tb * 0.3f}, tb * 0.1f);
                paint_dottedline.setPathEffect(effects);
                c.drawPath(path, paint_dottedline);
            }
        }
        // 绘制X轴
        c.drawLine(margin_left, getHeight() - margin_bottom, width - 8,
                getHeight() - margin_bottom, paint_date);
    }

    /**
     * 绘制y轴上的字
     *
     * @param c
     */
    public void drawxText(Canvas c) {
        int lineTotal = line;
        // 绘制横线
        for (int j = 0; j < lineTotal; j++) {
            int flag = linesum * (j + 1);
            if (j == (lineTotal - 1))
                c.drawText(String.valueOf(flag), 0,
                        (getHeight() - margin_bottom) / line * (line - (j + 1))
                                + 30, paintxytext);
            else
                c.drawText(String.valueOf(flag), 0,
                        (getHeight() - margin_bottom) / line * (line - (j + 1))
                                + 20, paintxytext);

        }
    }

    /**
     * 绘制折线
     *
     * @param c
     */
    public void drawBrokenLine(Canvas c) {
        float base = (getHeight() - margin_bottom) / 100;// 画布高度分成100份

        Shader mShader = new LinearGradient(0, 0, 0, getHeight(), new int[]{
                Color.argb(100, 0, 255, 255), Color.argb(45, 0, 255, 255),
                Color.argb(10, 0, 255, 255)}, null, Shader.TileMode.CLAMP);
        framPanint.setShader(mShader);

        for (int i = 0; i < milliliter.size() - 1; i++) {
            Log.e("i", "" + milliliter.get(i));
            float x1 = interval_left_right * i + margin_left;
            float y1 = getHeight() - margin_bottom - (base * milliliter.get(i));
            float Y1 = milliliter.get(i);
            float x2 = interval_left_right * (i + 1) + margin_left;
            float y2 = getHeight() - margin_bottom
                    - (base * milliliter.get(i + 1));
            float Y2 = milliliter.get(i + 1);

            paint_date.setColor(orangeLineColor);
            if (i == 0) {
                if (Y1 > 96)
                    c.drawText(String.valueOf(Y1), x1, y1 + 5 * base,
                            paint_date);// 绘出第一个值的大小
                else
                    c.drawText(String.valueOf(Y1), x1, y1, paint_date);// 绘出第一个值的大小
            }
            if (Y2 > 96) {
                if (i == milliliter.size() - 2) {
                    c.drawText(String.valueOf(Y2), x2 - 70, y2 + 5 * base,
                            paint_date);// 绘出第i+1个值的大小
                } else
                    c.drawText(String.valueOf(Y2), x2, y2 + 5 * base,
                            paint_date);// 绘出第i+1个值的大小
            } else {
                if (i == milliliter.size() - 2)
                    c.drawText(String.valueOf(Y2), x2 - 70, y2, paint_date);// 绘出第i+1个值的大小
                else
                    c.drawText(String.valueOf(Y2), x2, y2, paint_date);// 绘出第i+1个值的大小

            }
            c.drawLine(x1, y1, x2, y2, paint_brokenLine);
            path.lineTo(x1, y1);
            if (i == 0) {
                startx = x1;
                starty = y1;
            }
            if (i != 0)
                c.drawBitmap(bitmap_point, x1 - bitmap_point.getWidth() / 2, y1
                        - bitmap_point.getHeight() / 2, null);
            if (i == milliliter.size() - 2) {// 填充区域颜色
                path.lineTo(x2, y2);
                path.lineTo(x2, getHeight() - margin_bottom);
                path.lineTo(margin_left, getHeight() - margin_bottom);
                path.lineTo(startx, starty);
                path.close();
                c.drawPath(path, framPanint);// 多个点形成一条path的多边形
                c.drawBitmap(bitmap_point, x2 - bitmap_point.getWidth() / 2, y2
                        - bitmap_point.getHeight() / 2, null);
            }
        }
        paint_date.setColor(fineLineColor);

    }

    /**
     * 绘制时间
     *
     * @param c
     */
    public void drawDate(Canvas c) {
        for (int i = 0; i < milliliter.size(); i += 1) {
            paintxytext.setStrokeWidth(tb * 2.8f);
            if (i == 0) {
                c.drawText(mTexts.get(i), 0, getHeight() - 4, paintxytext);
            } else if (i == milliliter.size() - 1) {
                c.drawText(mTexts.get(i), interval_left_right * i - 30,
                        getHeight(), paintxytext);
            } else {
                c.drawText(mTexts.get(i), interval_left_right * i, getHeight(),
                        paintxytext);
            }
        }
    }

}
