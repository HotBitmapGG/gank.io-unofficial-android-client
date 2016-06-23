package com.hotbitmapgg.studyproject.hcc.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;


/**
 * View滑动的几种方法
 * <p/>
 * 1.layout方法:
 * 在View进行绘制时，会调用onLayout()方法来设置显示的位置，
 * 因此，我们可以通过修改View的left、top、right、bottom四个属性来控制View的坐标。
 * 要控制View随手指滑动，因此需要在onTouchEvent()事件中进行滑动控制
 * <p/>
 * 2.offsetLeftAndRight()和offsetTopAndBottom()
 * <p/>
 * 3.LayoutParams
 * <p/>
 * 4.scrollTo与scrollBy
 * <p/>
 * 5.Scroller辅助类
 */
public class DragView extends View
{

    int lastX;

    int lastY;

    int defultWidth = dp2px(200);

    int defultHeight = dp2px(200);

    Scroller mScroller;

    public DragView(Context context)
    {
        this(context, null);
    }

    public DragView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        setBackgroundColor(Color.RED);
        mScroller = new Scroller(getContext());
    }

    /**
     * easureSpec.EXACTLY：表示确定大小，
     * MeasureSpec.AT_MOST：表示最大大小，
     * MeasureSpec.UNSPECIFIED：不确定。
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST)
        {
            widthSize = defultWidth;
            heightSize = defultHeight;
        }


        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //触摸事件按下的点
                lastX = x;
                lastY = y;
                break;


            case MotionEvent.ACTION_MOVE:

                //触摸事件移动时的点
                int offsetX = x - lastX;
                int offsetY = y - lastY;

//                layout(getLeft() + offsetX,
//                        getTop() + offsetY,
//                        getRight() + offsetX,
//                        getBottom() + offsetY);

//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);


                //使用ScrollBy进行滑动 这里需要使用与平常相反的坐标
                int scrollX = lastX - x;
                int scrollY = lastY - y;
                //调用ViewGroup的ScrollBy进行滑动
                ((View) getParent()).scrollBy(scrollX, scrollY);


                break;

            case MotionEvent.ACTION_UP:
                LogUtil.all("UP");
                View mViewGroup = (View) getParent();
                mScroller.startScroll(mViewGroup.getScrollX(), mViewGroup.getScrollY()
                        , -mViewGroup.getScrollX(), -mViewGroup.getScrollY());

                invalidate();

                break;
        }


        return true;
    }

    @Override
    public void computeScroll()
    {
        if (mScroller.computeScrollOffset())
        {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }

        super.computeScroll();
    }

    public int dp2px(int dp)
    {
        float density = getContext().getResources().getDisplayMetrics().density;
        int px = (int) ((dp * density) + 0.5f);

        return px;
    }
}
