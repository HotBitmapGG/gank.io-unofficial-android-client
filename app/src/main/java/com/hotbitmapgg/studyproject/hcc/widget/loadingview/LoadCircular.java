package com.hotbitmapgg.studyproject.hcc.widget.loadingview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class LoadCircular extends View
{

    private Paint mPaint;

    private float width = 0f;

    private float maxRadius = 0f;

    private int startAngle = 0;

    public LoadCircular(Context context)
    {

        this(context, null);
    }

    public LoadCircular(Context context, AttributeSet attrs)
    {

        this(context, attrs, 0);
    }

    public LoadCircular(Context context, AttributeSet attrs, int defStyleAttr)
    {

        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getMeasuredWidth() > getHeight())
        {
            width = getMeasuredHeight();
        } else
        {
            width = getMeasuredWidth();
        }

        maxRadius = width / 30f;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        for (int i = 0; i < 9; i++)
        {
            float x1 = (float) ((width / 2f - maxRadius) * Math.cos(startAngle + 45f * i * Math.PI / 180f));
            float y1 = (float) ((width / 2f - maxRadius) * Math.sin(startAngle + 45f * i * Math.PI / 180f));

            canvas.drawCircle(width / 2f - x1, width / 2f - y1, maxRadius, mPaint);
        }

        canvas.drawCircle(width / 2f, width / 2f, width / 2f - maxRadius * 6, mPaint);


        super.onDraw(canvas);
    }
}
