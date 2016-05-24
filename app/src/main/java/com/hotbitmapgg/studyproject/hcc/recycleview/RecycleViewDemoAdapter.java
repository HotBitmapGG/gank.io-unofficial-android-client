package com.hotbitmapgg.studyproject.hcc.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotbitmapgg.studyproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RecycleViewDemoAdapter extends AbsRecyclerViewAdapter implements MyItemTouchHelper.ItemTouchAdapter
{

    private List<String> datas = new ArrayList<>();

    public RecycleViewDemoAdapter(RecyclerView recyclerView, List<String> datas)
    {
        super(recyclerView);
        this.datas = datas;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        bindContext(parent.getContext());
        return new RxjavaDemoViewHolder_1(LayoutInflater.from(getContext()).inflate(R.layout.item_test_1, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position)
    {
        if (holder instanceof RxjavaDemoViewHolder_1)
        {
            RxjavaDemoViewHolder_1 viewHolder_1 = (RxjavaDemoViewHolder_1) holder;
            viewHolder_1.mTextView.setText(datas.get(position));
        }

        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount()
    {
        return datas.size();
    }

    @Override
    public void onMove(int fromPosition, int toPosition)
    {
        if (fromPosition < toPosition)
        {

            for (int i = fromPosition; i < toPosition; i++)
            {
                Collections.swap(datas, i, i + 1);
            }
        }
        else
        {
            for (int i = fromPosition; i > toPosition; i--)
            {
                Collections.swap(datas, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onSwiped(int position)
    {
        datas.remove(position);
        notifyItemRemoved(position);
    }


    private class RxjavaDemoViewHolder_1 extends AbsRecyclerViewAdapter.ClickableViewHolder
    {

        public TextView mTextView;

        public RxjavaDemoViewHolder_1(View itemView)
        {
            super(itemView);
            mTextView = $(R.id.item_text);
        }


    }

}
