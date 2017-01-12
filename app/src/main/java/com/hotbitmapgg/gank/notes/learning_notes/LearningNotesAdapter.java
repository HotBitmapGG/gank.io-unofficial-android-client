package com.hotbitmapgg.gank.notes.learning_notes;

import com.hotbitmapgg.gank.widget.recyclehelper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.R;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hcc on 16/7/31 11:45
 * 100332338@qq.com
 */
public class LearningNotesAdapter extends AbsRecyclerViewAdapter {

  private List<LearningNotes> notes = new ArrayList<>();


  public LearningNotesAdapter(RecyclerView recyclerView, List<LearningNotes> notes) {

    super(recyclerView);
    this.notes = notes;
  }


  @Override
  public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    bindContext(parent.getContext());
    return new ItemViewHolder(
        LayoutInflater.from(getContext()).inflate(R.layout.item_learning_notes, parent, false));
  }


  @Override
  public void onBindViewHolder(ClickableViewHolder holder, int position) {

    if (holder instanceof ItemViewHolder) {
      ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      itemViewHolder.mChapter.setText(notes.get(position).getChapter());
    }
    super.onBindViewHolder(holder, position);
  }


  @Override
  public int getItemCount() {

    return notes.size();
  }


  private class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

    public TextView mChapter;


    public ItemViewHolder(View itemView) {

      super(itemView);
      mChapter = $(R.id.tv_chapter);
    }
  }
}
