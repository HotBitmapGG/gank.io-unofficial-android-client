

package com.hotbitmapgg.gank.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotbitmapgg.gank.model.Gank;
import com.hotbitmapgg.gank.utils.StringStyleUtils;
import com.hotbitmapgg.gank.widget.recyclehelper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.R;

import java.util.List;

public class GankListAdapter extends AbsRecyclerViewAdapter {

  private List<Gank.GankInfo> mGankList;


  public GankListAdapter(RecyclerView recyclerView, List<Gank.GankInfo> gankList) {

    super(recyclerView);
    this.mGankList = gankList;
  }


  @Override
  public void onBindViewHolder(ClickableViewHolder holder, int position) {

    if (holder instanceof ItemViewHolder) {
      ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      Gank.GankInfo gankInfo = mGankList.get(position);
      if (position == 0) {
        showCategory(itemViewHolder);
      } else {
        boolean theCategoryOfLastEqualsToThis = mGankList.get(
            position - 1).type.equals(mGankList.get(position).type);
        if (!theCategoryOfLastEqualsToThis) {
          showCategory(itemViewHolder);
        } else {
          hideCategory(itemViewHolder);
        }
      }
      itemViewHolder.category.setText(gankInfo.type);
      SpannableStringBuilder builder = new SpannableStringBuilder(gankInfo.desc).append(
          StringStyleUtils.format(itemViewHolder.gank.getContext(), " (via. " +
              gankInfo.who +
              ")", R.style.ViaTextAppearance));
      CharSequence gankText = builder.subSequence(0, builder.length());

      itemViewHolder.gank.setText(gankText);
      showItemAnim(itemViewHolder.gank, position);
    }

    super.onBindViewHolder(holder, position);
  }


  @Override
  public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    bindContext(parent.getContext());
    return new ItemViewHolder(
        LayoutInflater.from(getContext()).inflate(R.layout.item_gank_day, parent, false));
  }


  @Override
  public int getItemCount() {

    return mGankList.size();
  }


  private void showCategory(ItemViewHolder holder) {

    if (!isVisibleOf(holder.category)) holder.category.setVisibility(View.VISIBLE);
  }


  private void hideCategory(ItemViewHolder holder) {

    if (isVisibleOf(holder.category)) holder.category.setVisibility(View.GONE);
  }


  private boolean isVisibleOf(View view) {

    return view.getVisibility() == View.VISIBLE;
  }


  private class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

    public TextView category;

    public TextView gank;


    public ItemViewHolder(View itemView) {

      super(itemView);
      category = $(R.id.tv_category);
      gank = $(R.id.tv_title);
    }
  }
}

