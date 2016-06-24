package com.hotbitmapgg.studyproject.hcc.widget.loadingview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class LoadCircularCD extends View
{
    float width = 0f;

    float padding = 0f;

    private Paint mPaint;

    public LoadCircularCD(Context context)
    {
        this(context, null);
    }

    public LoadCircularCD(Context context, AttributeSet attrs)
    {
        this(context, null, 0);
    }

    public LoadCircularCD(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getMeasuredWidth() > getHeight())
        {
            width = getMeasuredHeight();
        }
        else
        {
            width = getMeasuredWidth();
        }

        padding = 5f;


    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        canvas.save();
        mPaint.setStrokeWidth(2);
        //绘制外层圆形
        canvas.drawCircle(width / 2, width / 2, width / 2 - padding, mPaint);
        mPaint.setStrokeWidth(3);
        //绘制内圈圆形
        canvas.drawCircle(width / 2, width / 2, padding, mPaint);

        //绘制左边两个圆弧
        mPaint.setStrokeWidth(3);
        RectF rectF1 = new RectF();
        rectF1.set(width / 2 - width / 3, width / 2 - width / 3,
                width / 2 + width / 3, width / 2 + width / 3);
        canvas.drawArc(rectF1, 0, 80, false, mPaint);
        canvas.drawArc(rectF1, 180, 80, false, mPaint);

        mPaint.setStrokeWidth(2);
        RectF rectF2 = new RectF();
        rectF2.set(width / 2 - width / 4, width / 2 - width / 4,
                width / 2 + width / 4, width / 2 + width / 4);
        canvas.drawArc(rectF2, 0, 80, false, mPaint);
        canvas.drawArc(rectF2, 180, 80, false, mPaint);


        super.onDraw(canvas);
    }
}
