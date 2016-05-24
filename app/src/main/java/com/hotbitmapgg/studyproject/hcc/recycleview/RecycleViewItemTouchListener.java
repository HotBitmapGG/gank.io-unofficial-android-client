package com.hotbitmapgg.studyproject.hcc.recycleview;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class RecycleViewItemTouchListener implements RecyclerView.OnItemTouchListener
{
    private GestureDetectorCompat mGestureDetector;

    private RecyclerView mRcycleView;

    public RecycleViewItemTouchListener(RecyclerView mRcycleView)
    {
        this.mRcycleView = mRcycleView;
        mGestureDetector = new GestureDetectorCompat(mRcycleView.getContext(),
                new ItemTouchHelperGestureLisener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
    {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e)
    {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept)
    {

    }


    /**
     * 监听手执触摸事件回调
     * <p/>
     * 单击事件 和 长按事件
     */
    private class ItemTouchHelperGestureLisener extends GestureDetector.SimpleOnGestureListener
    {


        @Override
        public boolean onSingleTapUp(MotionEvent e)
        {
            View childViewUnder = mRcycleView.findChildViewUnder(e.getX(), e.getY());
            if (childViewUnder != null)
            {
                RecyclerView.ViewHolder viewHolder = mRcycleView.getChildViewHolder(childViewUnder);
                onItemClick(viewHolder);
            }

            return true;
        }

        @Override
        public void onLongPress(MotionEvent e)
        {
            View childViewUnder = mRcycleView.findChildViewUnder(e.getX(), e.getY());
            if (childViewUnder != null)
            {
                RecyclerView.ViewHolder viewHolder = mRcycleView.getChildViewHolder(childViewUnder);
                onLongClick(viewHolder);
            }

            super.onLongPress(e);
        }


    }


    public void onLongClick(RecyclerView.ViewHolder vh)
    {
    }

    public void onItemClick(RecyclerView.ViewHolder vh)
    {
    }
}
