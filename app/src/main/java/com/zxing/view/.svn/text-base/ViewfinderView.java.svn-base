/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zxing.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.itserv.shed.R;
import com.yycloud.app.utils.TAUtils;
import com.zxing.camera.CameraManager;

import java.util.Collection;
import java.util.HashSet;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class ViewfinderView extends View {

//    private static final int[] SCANNER_ALPHA = { 0, 64, 128, 192, 255, 192,
//            128, 64 };
    /**
     * 扫描框中的中间线的与扫描框左右的间隙
     */
    private static final int MIDDLE_LINE_PADDING = 10;

    /**
     * 中间那条线每次刷新移动的距离
     */
    private static final int SPEEN_DISTANCE = 5;
    /**
     * 扫描框中的中间线的宽度
     */
    private static final int MIDDLE_LINE_WIDTH = 4;

    private static final long ANIMATION_DELAY = 20L;
    private static final int OPAQUE = 0xFF;

    private final Paint paint;
    private Bitmap resultBitmap;
    private final int maskColor;
    private final int resultColor;
    //    private final int frameColor;
    private final int laserColor;
    private final int resultPointColor;
    private int scannerAlpha;
    private Collection<ResultPoint> possibleResultPoints;
    private Collection<ResultPoint> lastPossibleResultPoints;
    private float density;
    private int ScreenRate;
    /**
     * 字体大小
     */
    private static final int TEXT_SIZE = 16;
    /**
     * 四个绿色边角对应的宽度
     */
    private static final int CORNER_WIDTH = 10;
    /**
     * 字体距离扫描框下面的距离
     */
    private static final int TEXT_PADDING_TOP = 30;
    /**
     * 中间滑动线的最顶端位置
     */
    private int slideTop;

    /**
     * 中间滑动线的最底端位置
     */
    private int slideBottom;
    boolean isFirst;
    private Context context;

    // This constructor is used when the class is built from an XML resource.
    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        // Initialize these once for performance rather than calling them every
        // time in onDraw().
        density = context.getResources().getDisplayMetrics().density;
        ScreenRate = (int) (20 * density);
        paint = new Paint();
        Resources resources = getResources();
        maskColor = resources.getColor(R.color.viewfinder_mask);
        resultColor = resources.getColor(R.color.result_view);
//        frameColor = resources.getColor(R.color.viewfinder_frame);
        laserColor = resources.getColor(R.color.green);
        resultPointColor = resources.getColor(R.color.possible_result_points);
        scannerAlpha = 0;
        possibleResultPoints = new HashSet<ResultPoint>(5);
    }

    @Override
    public void onDraw(Canvas canvas) {
        /**
         * 这个矩形就是中间显示的那个框框，在CameraManager中定义
         */
        Rect frame = CameraManager.get().getFramingRect();
        if (frame == null) {
            return;
        }
        if (!isFirst) {
            isFirst = true;
            slideTop = frame.top;
            slideBottom = frame.bottom;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        // Draw the exterior (i.e. outside the framing rect) darkened
        // 绘制四周的4个长方形

        paint.setColor(resultBitmap != null ? resultColor : maskColor);
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
                paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);

        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            /**
             * 如果结果图片不为空，所有解码成功了，那么就把图片绘制到中间那块区域
             */
            paint.setAlpha(OPAQUE);
            canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
        } else {
            /**
             * 如果不成功，就依旧绘制4条边框加一条中间的红线
             */
            // Draw a two pixel solid black border inside the framing rect
//            // 绘制两个像素边宽的黑色线框
//            paint.setColor(Color.GREEN);
//            canvas.drawRect(frame.left, frame.top, frame.right + 1,
//                    frame.top + 2, paint);
//            canvas.drawRect(frame.left, frame.top + 2, frame.left + 2,
//                    frame.bottom - 1, paint);
//            canvas.drawRect(frame.right - 1, frame.top, frame.right + 1,
//                    frame.bottom - 1, paint);
//            canvas.drawRect(frame.left, frame.bottom - 1, frame.right + 1,
//                    frame.bottom + 1, paint);


            //画扫描框边上的角，总共8个部分
            paint.setColor(Color.GREEN);
            canvas.drawRect(frame.left, frame.top, frame.left + ScreenRate,
                    frame.top + CORNER_WIDTH, paint);
            canvas.drawRect(frame.left, frame.top, frame.left + CORNER_WIDTH, frame.top
                    + ScreenRate, paint);
            canvas.drawRect(frame.right - ScreenRate, frame.top, frame.right,
                    frame.top + CORNER_WIDTH, paint);
            canvas.drawRect(frame.right - CORNER_WIDTH, frame.top, frame.right, frame.top
                    + ScreenRate, paint);
            canvas.drawRect(frame.left, frame.bottom - CORNER_WIDTH, frame.left
                    + ScreenRate, frame.bottom, paint);
            canvas.drawRect(frame.left, frame.bottom - ScreenRate,
                    frame.left + CORNER_WIDTH, frame.bottom, paint);
            canvas.drawRect(frame.right - ScreenRate, frame.bottom - CORNER_WIDTH,
                    frame.right, frame.bottom, paint);
            canvas.drawRect(frame.right - CORNER_WIDTH, frame.bottom - ScreenRate,
                    frame.right, frame.bottom, paint);

            // Draw a red "laser scanner" line through the middle to show
            // decoding is active
            // 绘制一条中间的红线
//            paint.setColor(laserColor);
//            paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
//            scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.length;
//            int middle = frame.height() / 2 + frame.top;
//            canvas.drawRect(frame.left + 2, middle - 1, frame.right - 1,
//                    middle + 2, paint);

            //绘制中间的线,每次刷新界面，中间的线往下移动SPEEN_DISTANCE
            slideTop += SPEEN_DISTANCE;
            if (slideTop >= frame.bottom) {
                slideTop = frame.top;
            }
            canvas.drawRect(frame.left + MIDDLE_LINE_PADDING, slideTop - MIDDLE_LINE_WIDTH / 2, frame.right - MIDDLE_LINE_PADDING, slideTop + MIDDLE_LINE_WIDTH / 2, paint);


            //画扫描框下面的字
            paint.setColor(Color.WHITE);
            paint.setTextSize(TAUtils.sp2px(context, TEXT_SIZE));
            paint.setAlpha(0x40);
            paint.setTypeface(Typeface.create("System", Typeface.BOLD));
            String string = getResources().getString(R.string.scan_text);
            float measureText = paint.measureText(string, 0, string.length());
            canvas.drawText(string, ((1024 / 2) - (measureText / 2)), (float) (frame.bottom + (float) TEXT_PADDING_TOP * density), paint);

            /**
             * 再绘制可能是结果的点
             */
            Rect previewFrame = CameraManager.get().getFramingRectInPreview();
            float scaleX = frame.width() / (float) previewFrame.width();
            float scaleY = frame.height() / (float) previewFrame.height();
            Collection<ResultPoint> currentPossible = possibleResultPoints;
            Collection<ResultPoint> currentLast = lastPossibleResultPoints;
            if (currentPossible.isEmpty()) {
                /**
                 * 如果没有可能的点，情况上次可能的点
                 */
                lastPossibleResultPoints = null;
            } else {
                possibleResultPoints = new HashSet<ResultPoint>(5);
                lastPossibleResultPoints = currentPossible;
                paint.setAlpha(OPAQUE);
                paint.setColor(resultPointColor);
                /**
                 * 这里绘制可能是结果的点，就是相机中可能是二维码的地方
                 */
                for (ResultPoint point : currentPossible) {
                    //canvas.drawCircle(frame.left + point.getX(), frame.top
                    //+ point.getY(), 6.0f, paint);
                    canvas.drawCircle(frame.left + (int) (point.getX() * scaleX), frame.top
                            + (int) (point.getY() * scaleY), 3.0f, paint);
                }
            }
            if (currentLast != null) {
                /**
                 * 这里是绘制上一次可能出现的点，其实是绘制上次点结束的样子，就是半径小一倍，透明度小一倍
                 */
                paint.setAlpha(OPAQUE / 2);
                paint.setColor(resultPointColor);
                for (ResultPoint point : currentLast) {
                    canvas.drawCircle(frame.left + point.getX(), frame.top
                            + point.getY(), 3.0f, paint);
                }
            }

            // Request another update at the animation interval, but only
            // repaint the laser line,
            // not the entire viewfinder mask.
            /**
             * 当我们获得结果的时候，我们只需要更新框框内部的内容
             */
            postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
                    frame.right, frame.bottom);
        }
    }

    /**
     * 这个方法用来清空图片，就是扫描好了，现在要重现扫描，那么就要将先前的图片撤掉，然后就会绘制那个框框和红线
     * 在activity中调用
     */
    public void drawViewfinder() {
        resultBitmap = null;
        invalidate();
    }

    /**
     * Draw a bitmap with the result points highlighted instead of the live
     * scanning display.
     *
     * @param barcode An image of the decoded barcode.
     *                当resultBitmap不为空的时候，view会将它绘制到界面上，让后更新界面，就是我们看到的结果
     */
    public void drawResultBitmap(Bitmap barcode) {
        resultBitmap = barcode;
        invalidate();
    }

    /**
     * 该方法在ViewfinderResultPointCallback中使用到，用来添加可能的点
     */
    public void addPossibleResultPoint(ResultPoint point) {
        possibleResultPoints.add(point);
    }

}
