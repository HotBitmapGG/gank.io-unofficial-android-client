package com.hotbitmapgg.studyproject.hcc.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 二阶贝塞尔曲线练习
 */
public class Bezaer extends View
{

    private Paint mPaint;

    private int centerX, centerY;

    private PointF start, end, center;

    public Bezaer(Context context)
    {
        this(context, null);
    }

    public Bezaer(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public Bezaer(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init()
    {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        center = new PointF(0, 0);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w / 2;
        centerY = h / 2;

        //初始化数据点跟控制点的位置
        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        center.x = centerX;
        center.y = centerY - 100;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //根据触摸位置更新控制点 并进行重绘
        float x = event.getX();
        float y = event.getY();

        center.x = x;
        center.y = y;

        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //绘制控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(center.x, center.y, mPaint);

        //绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, center.x, center.y, mPaint);
        canvas.drawLine(end.x, end.y, center.x, center.y, mPaint);

        //绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);

        //根据路径来绘制贝塞尔曲线的 这里是二阶贝塞尔曲线
        Path mPath = new Path();
        mPath.moveTo(start.x, start.y);
        mPath.quadTo(center.x, center.y, end.x, end.y);

        canvas.drawPath(mPath, mPaint);
    }
}
