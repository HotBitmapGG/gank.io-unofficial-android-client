package com.hotbitmapgg.gank.ui.fragment;

import butterknife.Bind;
import com.google.gson.GsonBuilder;
import com.hotbitmapgg.gank.adapter.GitHubStarredAdapter;
import com.hotbitmapgg.gank.base.RxBaseFragment;
import com.hotbitmapgg.gank.config.ConstantUtil;
import com.hotbitmapgg.gank.model.GitHubStarInfo;
import com.hotbitmapgg.gank.model.GitHubUserInfo;
import com.hotbitmapgg.gank.network.RetrofitHelper;
import com.hotbitmapgg.gank.ui.activity.WebActivity;
import com.hotbitmapgg.gank.utils.LogUtil;
import com.hotbitmapgg.gank.widget.CircleProgressView;
import com.hotbitmapgg.studyproject.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * GitHub用户详情信息
 */
public class GitHubUserDetailsFragment extends RxBaseFragment {

  @Bind(R.id.user_info_bio)
  TextView mUserBio;

  @Bind(R.id.user_info_blog)
  TextView mUserBlog;

  @Bind(R.id.user_info_company)
  TextView mUserCompany;

  @Bind(R.id.user_info_email)
  TextView mUserEmail;

  @Bind(R.id.user_info_location)
  TextView mUserLocation;

  @Bind(R.id.user_info_followers)
  TextView mUserFollowers;

  @Bind(R.id.user_info_following)
  TextView mUserFollowing;

  @Bind(R.id.recycle)
  RecyclerView mRecyclerView;

  @Bind(R.id.user_info_layout)
  LinearLayout mLayout;

  @Bind(R.id.circle_progress)
  CircleProgressView mCircleProgressView;

  private List<GitHubStarInfo> stars = new ArrayList<>();

  private GitHubUserInfo mUserInfo;


  public static GitHubUserDetailsFragment newInstance(GitHubUserInfo userInfo) {

    GitHubUserDetailsFragment mFragment = new GitHubUserDetailsFragment();
    Bundle mBundle = new Bundle();
    mBundle.putSerializable(ConstantUtil.KEY, userInfo);
    mFragment.setArguments(mBundle);

    return mFragment;
  }


  @Override
  public int getLayoutId() {

    return R.layout.fragment_github_user_details;
  }


  @SuppressLint("SetTextI18n") @Override
  public void initViews() {

    mUserInfo = (GitHubUserInfo) getArguments().getSerializable(ConstantUtil.KEY);

    if (mUserInfo != null) {
      mUserBio.setText(mUserInfo.bio);
      mUserBlog.setText(mUserInfo.blog);
      mUserCompany.setText(mUserInfo.company);
      mUserEmail.setText(mUserInfo.email);
      mUserLocation.setText(mUserInfo.location);
      mUserFollowers.setText("Following :" + mUserInfo.followers);
      mUserFollowing.setText("Followers :" + mUserInfo.following);
    }

    loadData();
  }


  @Override public void loadData() {
    RetrofitHelper.getGithubApi()
        .getGitHubStarred(mUserInfo.login)
        .compose(this.bindToLifecycle())
        .doOnSubscribe(this::showProgress)
        .map(responseBody -> {

          try {
            GitHubStarInfo[] gitHubStarInfos = new GsonBuilder()
                .create()
                .fromJson(responseBody.string(), GitHubStarInfo[].class);

            int length = gitHubStarInfos.length;
            stars.addAll(Arrays.asList(gitHubStarInfos).subList(0, length));

            return stars;
          } catch (IOException e) {
            e.printStackTrace();
            return null;
          }
        })
        .delay(2000, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(gitHubStarInfos -> {

          finishTask();
        }, throwable -> {

          LogUtil.all("加载Star数据失败" + throwable.getMessage());
        });
  }


  private void finishTask() {

    mRecyclerView.setHasFixedSize(false);
    mRecyclerView.setNestedScrollingEnabled(false);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    GitHubStarredAdapter mAdapter = new GitHubStarredAdapter(mRecyclerView, stars);
    mRecyclerView.setAdapter(mAdapter);
    hideProgress();

    mAdapter.setOnItemClickListener((position, holder) -> {

      GitHubStarInfo gitHubStarInfo = stars.get(position);
      WebActivity.launch(getActivity(), gitHubStarInfo.htmlUrl, gitHubStarInfo.fullName);
    });
  }


  public void showProgress() {

    mCircleProgressView.setVisibility(View.VISIBLE);
    mCircleProgressView.spin();
    mLayout.setVisibility(View.GONE);
  }


  public void hideProgress() {

    mCircleProgressView.setVisibility(View.GONE);
    mCircleProgressView.stopSpinning();
    mLayout.setVisibility(View.VISIBLE);
  }
}
