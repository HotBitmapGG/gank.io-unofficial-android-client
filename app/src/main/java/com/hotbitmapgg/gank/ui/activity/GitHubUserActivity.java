package com.hotbitmapgg.gank.ui.activity;

import butterknife.Bind;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.flyco.tablayout.SlidingTabLayout;
import com.hotbitmapgg.gank.base.RxBaseActivity;
import com.hotbitmapgg.gank.config.ConstantUtil;
import com.hotbitmapgg.gank.model.GitHubUserInfo;
import com.hotbitmapgg.gank.rx.RxBus;
import com.hotbitmapgg.gank.ui.fragment.GitHubFollowersFragment;
import com.hotbitmapgg.gank.ui.fragment.GitHubFollowingFragment;
import com.hotbitmapgg.gank.ui.fragment.GitHubUserDetailsFragment;
import com.hotbitmapgg.gank.utils.ACache;
import com.hotbitmapgg.gank.widget.CircleImageView;
import com.hotbitmapgg.studyproject.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Github登录用户详情界面
 */
public class GitHubUserActivity extends RxBaseActivity {

  @Bind(R.id.toolbar)
  Toolbar mToolbar;

  @Bind(R.id.collapsing_toolbar)
  CollapsingToolbarLayout mCollapsingToolbarLayout;

  @Bind(R.id.user_info_avatar)
  CircleImageView mUserInfoAvatar;

  @Bind(R.id.user_info_name)
  TextView mUsername;

  @Bind(R.id.sliding_tabs)
  SlidingTabLayout mSlidingTabLayout;

  @Bind(R.id.view_pager)
  ViewPager mViewPager;

  private GitHubUserInfo mUserInfo;

  private List<Fragment> fragments = new ArrayList<>();

  private List<String> titles = Arrays.asList("个人主页", "关注我的", "我关注的");


  @Override
  public int getLayoutId() {

    return R.layout.activity_github_user;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {
    loadData();
  }


  private void initFragments() {

    GitHubUserDetailsFragment mGitHubUserDetailsFragment = GitHubUserDetailsFragment.newInstance(
        mUserInfo);
    GitHubFollowersFragment mGitHubFollowersFragment = GitHubFollowersFragment.newInstance(
        mUserInfo);
    GitHubFollowingFragment mGitHubFollowingFragment = GitHubFollowingFragment.newInstance(
        mUserInfo);
    fragments.add(mGitHubUserDetailsFragment);
    fragments.add(mGitHubFollowersFragment);
    fragments.add(mGitHubFollowingFragment);
    mViewPager.setAdapter(new UserInfoPagerAdapter(getSupportFragmentManager(), fragments, titles));
    mViewPager.setOffscreenPageLimit(fragments.size());
    mSlidingTabLayout.setViewPager(mViewPager);
  }


  private void setUserInfo() {

    Glide.with(this)
        .load(mUserInfo.avatarUrl)
        .asBitmap()
        .placeholder(R.drawable.ic_slide_menu_avatar_no_login)
        .into(new BitmapImageViewTarget(mUserInfoAvatar) {

          @Override
          protected void setResource(Bitmap resource) {

            mUserInfoAvatar.setImageDrawable(RoundedBitmapDrawableFactory
                .create(GitHubUserActivity.this.getResources(), resource));
          }
        });
    mUsername.setText(mUserInfo.name);
  }


  @Override
  public void initToolBar() {

    setSupportActionBar(mToolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    mCollapsingToolbarLayout.setTitle(mUserInfo.login);
  }


  @Override public void loadData() {
    mUserInfo = (GitHubUserInfo) ACache.get(GitHubUserActivity.this)
        .getAsObject(ConstantUtil.CACHE_USER_KEY);

    initFragments();
    setUserInfo();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menu_user_info, menu);
    return true;
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    } else if (item.getItemId() == R.id.action_info_share) {
      share();
    } else if (item.getItemId() == R.id.action_logout) {
      ACache.get(GitHubUserActivity.this).clear();
      RxBus.getInstance().post(ConstantUtil.CODE_LOGOUT);
      GitHubUserActivity.this.finish();
    }
    return super.onOptionsItemSelected(item);
  }


  private void share() {

    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
    intent.putExtra(Intent.EXTRA_TEXT, "来自「Gank.io」的分享:" + mUserInfo.htmlUrl);
    startActivity(Intent.createChooser(intent, mUserInfo.name));
  }


  private static class UserInfoPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    private List<String> titles;


    UserInfoPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {

      super(fm);
      this.fragments = fragments;
      this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {

      return fragments.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {

      return titles.get(position);
    }


    @Override
    public int getCount() {

      return fragments.size();
    }
  }
}
