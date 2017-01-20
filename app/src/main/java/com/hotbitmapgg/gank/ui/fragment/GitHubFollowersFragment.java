package com.hotbitmapgg.gank.ui.fragment;

import butterknife.Bind;
import com.google.gson.GsonBuilder;
import com.hotbitmapgg.gank.adapter.GitHubFollowInfoAdapter;
import com.hotbitmapgg.gank.base.RxBaseFragment;
import com.hotbitmapgg.gank.config.ConstantUtil;
import com.hotbitmapgg.gank.model.GitHubFollowerInfo;
import com.hotbitmapgg.gank.model.GitHubUserInfo;
import com.hotbitmapgg.gank.network.RetrofitHelper;
import com.hotbitmapgg.gank.ui.activity.WebActivity;
import com.hotbitmapgg.gank.utils.LogUtil;
import com.hotbitmapgg.studyproject.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 关注用户的人界面
 */
public class GitHubFollowersFragment extends RxBaseFragment {

  @Bind(R.id.recycle)
  RecyclerView mRecyclerView;

  private List<GitHubFollowerInfo> followerInfos = new ArrayList<>();
  private GitHubUserInfo mUserInfo;


  public static GitHubFollowersFragment newInstance(GitHubUserInfo userInfo) {

    GitHubFollowersFragment mFollowersFragment = new GitHubFollowersFragment();
    Bundle bundle = new Bundle();
    bundle.putSerializable(ConstantUtil.KEY, userInfo);
    mFollowersFragment.setArguments(bundle);

    return mFollowersFragment;
  }


  @Override
  public int getLayoutId() {

    return R.layout.fragment_follow_info;
  }


  @Override
  public void initViews() {

    mUserInfo = (GitHubUserInfo) getArguments().getSerializable(ConstantUtil.KEY);
    loadData();
  }


  @Override public void loadData() {
    RetrofitHelper.getGithubApi()
        .getGitHubFollowers(mUserInfo.login)
        .compose(this.bindToLifecycle())
        .map(responseBody -> {

          try {
            GitHubFollowerInfo[] gitHubFollowerInfos = new GsonBuilder()
                .create()
                .fromJson(responseBody.string(), GitHubFollowerInfo[].class);

            int length = gitHubFollowerInfos.length;
            followerInfos.addAll(Arrays.asList(gitHubFollowerInfos).subList(0, length));

            return followerInfos;
          } catch (IOException e) {
            e.printStackTrace();
            return null;
          }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(gitHubFollowerInfos -> {
          finishTask();
        }, throwable -> {

          LogUtil.all("加载关注用户的人数据失败");
        });
  }


  private void finishTask() {

    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    GitHubFollowInfoAdapter mAdapter = new GitHubFollowInfoAdapter(mRecyclerView, followerInfos);
    mRecyclerView.setAdapter(mAdapter);

    mAdapter.setOnItemClickListener((position, holder) -> {

      GitHubFollowerInfo gitHubFollowerInfo = followerInfos.get(position);
      WebActivity.launch(getActivity(), gitHubFollowerInfo.htlmUrl, gitHubFollowerInfo.login);
    });
  }
}
