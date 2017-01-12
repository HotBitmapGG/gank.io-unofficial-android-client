package com.hotbitmapgg.gank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.gank.widget.recyclehelper.AbsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class GankTypeSelectAdapter extends AbsRecyclerViewAdapter {

  private List<String> types = new ArrayList<>();


  public GankTypeSelectAdapter(RecyclerView recyclerView, List<String> types) {

    super(recyclerView);
    this.types = types;
  }


  @Override
  public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    bindContext(parent.getContext());
    return new ItemViewHolder(
        LayoutInflater.from(getContext()).inflate(R.layout.item_gank_type, parent, false));
  }


  @Override
  public void onBindViewHolder(ClickableViewHolder holder, int position) {

    if (holder instanceof ItemViewHolder) {
      ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      itemViewHolder.mType.setText(types.get(position));
    }

    super.onBindViewHolder(holder, position);
  }


  @Override
  public int getItemCount() {

    return types.size();
  }


  private class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

    public TextView mType;


    public ItemViewHolder(View itemView) {

      super(itemView);
      mType = $(R.id.item_type);
    }
  }
}
