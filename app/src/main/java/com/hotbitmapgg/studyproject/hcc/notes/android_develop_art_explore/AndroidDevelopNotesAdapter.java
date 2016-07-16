package com.hotbitmapgg.studyproject.hcc.notes.android_develop_art_explore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.widget.recyclehelper.AbsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class AndroidDevelopNotesAdapter extends AbsRecyclerViewAdapter
{

    private List<AndroidDevelopNote> notes = new ArrayList<>();


    public AndroidDevelopNotesAdapter(RecyclerView recyclerView, List<AndroidDevelopNote> notes)
    {

        super(recyclerView);
        this.notes = notes;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_android_develop_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position)
    {

        if (holder instanceof ItemViewHolder)
        {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mChapter.setText(notes.get(position).name);
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount()
    {

        return notes.size();
    }

    private class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder
    {

        public TextView mChapter;

        public ItemViewHolder(View itemView)
        {

            super(itemView);
            mChapter = $(R.id.tv_chapter);
        }
    }
}
