package com.hotbitmapgg.studyproject.hcc.recycleview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


public class MyItemTouchHelper extends ItemTouchHelper.Callback
{

    private ItemTouchAdapter mAdapter;

    private Drawable background = null;

    private int bkcolor = -1;


    public MyItemTouchHelper(ItemTouchAdapter adapter)
    {
        this.mAdapter = adapter;
    }

    /**
     * 是否允许Item进行拖拽
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled()
    {
        return false;
    }


    @Override
    public boolean isItemViewSwipeEnabled()
    {
        return false;
    }

    /**
     * 该方法是否去处理拖拽事件跟滑动事件
     * <p/>
     * 如果是列表 则只处理UP DOWN事件
     * 如果是网格 则要处理 UP DOWN LEFT RIGHT事件
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager)
        {

            final int dragFlag = ItemTouchHelper.UP |
                    ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT |
                    ItemTouchHelper.RIGHT;

            final int swipeFlag = 0;

            return makeMovementFlags(dragFlag, swipeFlag);
        }
        else
        {

            final int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

            //final int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;
            final int swipeFlag = 0;

            return makeMovementFlags(dragFlag, swipeFlag);
        }

    }

    /**
     * 当Item拖拽时回调该方法
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
    {

        //拖动的item
        int position = viewHolder.getAdapterPosition();
        int targetPosition = target.getAdapterPosition();
        mAdapter.onMove(position, targetPosition);

        return true;
    }

    /**
     * 当Item滑动时候回调该方法
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
    {
        int position = viewHolder.getAdapterPosition();
        mAdapter.onSwiped(position);
    }

    /**
     * 当手指按下Item时回调该方法
     *
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState)
    {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
        {
            if (background == null && bkcolor == -1)
            {
                Drawable drawable = viewHolder.itemView.getBackground();
                if (drawable == null)
                {
                    bkcolor = 0;
                }
                else
                {
                    background = drawable;
                }
            }

            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }

        super.onSelectedChanged(viewHolder, actionState);
    }


    /**
     * 手指离开Item时回调该方法
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
        viewHolder.itemView.setAlpha(0.1f);
        if (background != null)
            viewHolder.itemView.setBackgroundDrawable(background);
        if (bkcolor != -1)
            viewHolder.itemView.setBackgroundColor(bkcolor);

        if (onDragListener != null)
        {
            onDragListener.onFinishDrag();
        }
        super.clearView(recyclerView, viewHolder);
    }


    /**
     * 添加滑动子Item的动画 重绘子view
     *
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive)
    {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE)
        {
            //滑动的改变item的透明度
            final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        }
        else
        {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    private OnDragListener onDragListener;

    public MyItemTouchHelper setOnDragListener(OnDragListener onDragListener)
    {
        this.onDragListener = onDragListener;
        return this;
    }

    public interface OnDragListener
    {
        void onFinishDrag();
    }

    public interface ItemTouchAdapter
    {
        void onMove(int fromPosition, int toPosition);

        void onSwiped(int position);
    }
}
