package com.hotbitmapgg.gank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hotbitmapgg.gank.utils.DateUtils;
import com.hotbitmapgg.gank.widget.CircleImageView;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.gank.model.GitHubStarInfo;
import com.hotbitmapgg.gank.widget.recyclehelper.AbsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class GitHubStarredAdapter extends AbsRecyclerViewAdapter {

  private List<GitHubStarInfo> starredInfos = new ArrayList<>();


  public GitHubStarredAdapter(RecyclerView recyclerView, List<GitHubStarInfo> starredInfos) {

    super(recyclerView);
    this.starredInfos = starredInfos;
  }


  @Override
  public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    bindContext(parent.getContext());
    return new ItemViewHolder(
        LayoutInflater.from(getContext()).inflate(R.layout.item_github_starred, parent, false));
  }


  @Override
  public void onBindViewHolder(ClickableViewHolder holder, int position) {

    if (holder instanceof ItemViewHolder) {
      ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      GitHubStarInfo starredInfo = starredInfos.get(position);
      Glide.with(getContext())
          .load(starredInfo.owner.avatarUrl)
          .dontAnimate()
          .placeholder(R.drawable.ic_slide_menu_avatar_no_login)
          .into(itemViewHolder.mAvatar);

      itemViewHolder.mStarName.setText(starredInfo.name);
      itemViewHolder.mStarDesc.setText(starredInfo.description);
      String date = starredInfo.createTime.replace('T', ' ').replace('Z', ' ');
      String time = DateUtils.friendlyTime(date);
      itemViewHolder.mCreateTime.setText(time);
      itemViewHolder.mUserName.setText(starredInfo.owner.login);
      itemViewHolder.mStarNum.setText(starredInfo.stargazersCount);
      itemViewHolder.mWatchNum.setText(starredInfo.watchersCount);
      itemViewHolder.mForkNum.setText(starredInfo.forksCount);
    }
    super.onBindViewHolder(holder, position);
  }


  @Override
  public int getItemCount() {

    return starredInfos.size();
  }


  public class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

    public TextView mStarName;

    public TextView mStarDesc;

    public CircleImageView mAvatar;

    public TextView mUserName;

    public TextView mCreateTime;

    public TextView mStarNum;

    public TextView mWatchNum;

    public TextView mForkNum;


    public ItemViewHolder(View itemView) {

      super(itemView);
      mStarName = $(R.id.item_tv_name);
      mStarDesc = $(R.id.item_tv_desc);
      mAvatar = $(R.id.item_iv_avatar);
      mUserName = $(R.id.item_tv_user_name);
      mCreateTime = $(R.id.item_tv_create_time);
      mStarNum = $(R.id.item_tv_star_num);
      mWatchNum = $(R.id.item_tv_watch_num);
      mForkNum = $(R.id.item_tv_fork_num);
    }
  }
}
