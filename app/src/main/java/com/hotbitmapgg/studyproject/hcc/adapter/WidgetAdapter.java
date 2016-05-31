package com.hotbitmapgg.studyproject.hcc.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class WidgetAdapter extends AbsRecyclerViewAdapter
{

    private List<String> names = new ArrayList<>();

    public WidgetAdapter(RecyclerView recyclerView, List<String> names)
    {

        super(recyclerView);
        this.names = names;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_widget, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position)
    {

        if (holder instanceof ItemViewHolder)
        {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mName.setText(names.get(position));
        }

        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount()
    {

        return names.size();
    }


    private class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder
    {

        public TextView mName;

        public ItemViewHolder(View itemView)
        {

            super(itemView);
            mName = $(R.id.item_name);
        }
    }
}
