package com.hotbitmapgg.studyproject.hcc.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;


public class CustomProgressBar extends View
{

    private int mCenterX;

    private int mCenterY;

    private Paint mPaint;

    private int mRadius0 = 87;

    private int mRadius1 = 97;

    private int mRadius = 113;

    private int mRadius2 = 130;

    private int mRadius3 = 150;

    private RectF mOval = new RectF();

    private RectF mOval2 = new RectF();

    private RectF mOval3 = new RectF();

    private float mCurrentAngle = 270;

    private float mStartAngle = 135;

    private float mEndAngle = 270;

    /**
     * @param context
     * @param attrs
     */
    public CustomProgressBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Style.STROKE);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas)
    {
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;
        mOval.set(mCenterX - mRadius, mCenterY - mRadius, mCenterX + mRadius, mCenterY + mRadius);
        mOval2.set(mCenterX - mRadius2, mCenterY - mRadius2, mCenterX + mRadius2, mCenterY + mRadius2);
        mOval3.set(mCenterX - mRadius3, mCenterY - mRadius3, mCenterX + mRadius3, mCenterY + mRadius3);

        mPaint.setStrokeWidth(2);
        mPaint.setAlpha(26);
        canvas.drawArc(mOval3, mStartAngle, mEndAngle, false, mPaint);

        mPaint.setAlpha(51);
        canvas.drawArc(mOval2, mStartAngle, mEndAngle, false, mPaint);

        mPaint.setAlpha(153);
        drawLines(canvas);

        mPaint.setStrokeWidth(20);
        mPaint.setAlpha(102);
        canvas.drawArc(mOval, mStartAngle, mEndAngle, false, mPaint);


        mPaint.setAlpha(255);
        canvas.drawArc(mOval, mStartAngle, mCurrentAngle, false, mPaint);
    }

    private void drawLines(Canvas canvas)
    {
        for (int angle = 0; angle < 360; angle += 3)
        {
            double d = 2 * Math.PI * angle / 360;
            LogUtil.all( "d:" + d);
            float x0 = (float) (mRadius0 * Math.cos(d) + mCenterX);
            LogUtil.all( "x0:" + x0);
            float y0 = (float) (mRadius0 * Math.sin(d) + mCenterY);
            LogUtil.all( "y0:" + y0);
            float x1 = (float) (mRadius1 * Math.cos(d) + mCenterX);
            LogUtil.all( "x1:" + x1);
            float y1 = (float) (mRadius1 * Math.sin(d) + mCenterY);
            LogUtil.all( "y1:" + y1);
            canvas.drawLine(x0, y0, x1, y1, mPaint);
        }
    }

    public void setPercentage(float percent)
    {
        // mCurrentAngle = mStartAngle + percent * (mEndAngle - mStartAngle);
        mCurrentAngle = percent * mEndAngle;
        invalidate();
    }

    public float getPercentage()
    {
        return 0.0f;
    }
}