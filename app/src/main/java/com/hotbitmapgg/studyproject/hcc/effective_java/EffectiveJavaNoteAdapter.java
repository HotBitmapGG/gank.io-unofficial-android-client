package com.hotbitmapgg.studyproject.hcc.effective_java;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcc on 16/6/5 15:17
 * 100332338@qq.com
 */
public class EffectiveJavaNoteAdapter extends AbsRecyclerViewAdapter
{

    private List<EffectiveJavaNote> notes = new ArrayList<>();


    public EffectiveJavaNoteAdapter(RecyclerView recyclerView, List<EffectiveJavaNote> notes)
    {

        super(recyclerView);
        this.notes = notes;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_effective_java, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position)
    {

        if (holder instanceof ItemViewHolder)
        {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mChapter.setText(notes.get(position).chapter);
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount()
    {

        return notes.size();
    }

    private class ItemViewHolder extends ClickableViewHolder
    {

        public TextView mChapter;

        public ItemViewHolder(View itemView)
        {

            super(itemView);
            mChapter = $(R.id.tv_chapter);
        }
    }
}
