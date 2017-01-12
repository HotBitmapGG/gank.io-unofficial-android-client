package com.hotbitmapgg.gank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.gank.model.Gank;
import com.hotbitmapgg.gank.widget.recyclehelper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.gank.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class GankAdapter extends AbsRecyclerViewAdapter {

  private List<Gank.GankInfo> gankInfos = new ArrayList<>();


  public GankAdapter(RecyclerView recyclerView, List<Gank.GankInfo> gankInfos) {

    super(recyclerView);
    this.gankInfos = gankInfos;
  }


  @Override
  public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    bindContext(parent.getContext());
    return new ItemViewHolder(
        LayoutInflater.from(getContext()).inflate(R.layout.card_item_gank, parent, false));
  }


  @Override
  public void onBindViewHolder(ClickableViewHolder holder, int position) {

    if (holder instanceof ItemViewHolder) {
      ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      Gank.GankInfo gankInfo = gankInfos.get(position);
      itemViewHolder.mDesc.setText(gankInfo.desc);
      itemViewHolder.mWho.setText(gankInfo.who);
      String date = gankInfo.publishedAt.replace('T', ' ').replace('Z', ' ');
      String time = DateUtils.friendlyTime(date);
      itemViewHolder.mTime.setText(time);
      if (gankInfo.type.equals("福利")) {
        itemViewHolder.mDesc.setVisibility(View.GONE);
        itemViewHolder.mImage.setVisibility(View.VISIBLE);
        Glide.with(getContext())
            .load(gankInfo.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .placeholder(R.drawable.placeholder_image)
            .into(itemViewHolder.mImage);
      } else {
        itemViewHolder.mDesc.setVisibility(View.VISIBLE);
        itemViewHolder.mImage.setVisibility(View.GONE);
      }
    }

    super.onBindViewHolder(holder, position);
  }


  @Override
  public int getItemCount() {

    return gankInfos == null ? 0 : gankInfos.size();
  }


  public class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

    public TextView mDesc;

    public TextView mWho;

    public TextView mTime;

    public ImageView mImage;


    public ItemViewHolder(View itemView) {

      super(itemView);
      mDesc = $(R.id.item_desc);
      mWho = $(R.id.item_who);
      mTime = $(R.id.item_time);
      mImage = $(R.id.item_image);
    }
  }
}
