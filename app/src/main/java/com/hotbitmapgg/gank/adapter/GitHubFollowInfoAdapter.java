package com.hotbitmapgg.gank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hotbitmapgg.gank.widget.CircleImageView;
import com.hotbitmapgg.gank.widget.recyclehelper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.gank.model.GitHubFollowerInfo;

import java.util.ArrayList;
import java.util.List;

public class GitHubFollowInfoAdapter extends AbsRecyclerViewAdapter {

  private List<GitHubFollowerInfo> followerInfos = new ArrayList<>();


  public GitHubFollowInfoAdapter(RecyclerView recyclerView, List<GitHubFollowerInfo> followerInfos) {

    super(recyclerView);
    this.followerInfos = followerInfos;
  }


  @Override
  public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    bindContext(parent.getContext());
    return new ItemViewHolder(
        LayoutInflater.from(getContext()).inflate(R.layout.item_github_follow, parent, false));
  }


  @Override
  public void onBindViewHolder(ClickableViewHolder holder, int position) {

    if (holder instanceof ItemViewHolder) {
      ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      GitHubFollowerInfo gitHubFollowerInfo = followerInfos.get(position);
      Glide.with(getContext())
          .load(gitHubFollowerInfo.avatarUrl)
          .dontAnimate()
          .placeholder(R.drawable.ic_slide_menu_avatar_no_login)
          .into(itemViewHolder.mFollowAvatar);

      itemViewHolder.mFollowName.setText(gitHubFollowerInfo.login);
    }
    super.onBindViewHolder(holder, position);
  }


  @Override
  public int getItemCount() {

    return followerInfos.size();
  }


  public class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

    public CircleImageView mFollowAvatar;

    public TextView mFollowName;


    public ItemViewHolder(View itemView) {

      super(itemView);
      mFollowAvatar = $(R.id.item_follow_avatar);
      mFollowName = $(R.id.item_follow_name);
    }
  }
}
