package com.hotbitmapgg.androidrank.ui.activity;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.flyco.tablayout.SlidingTabLayout;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.androidrank.base.RxBaseActivity;
import com.hotbitmapgg.androidrank.config.ConstantUtil;
import com.hotbitmapgg.androidrank.model.GitHubUserInfo;
import com.hotbitmapgg.androidrank.rx.RxBus;
import com.hotbitmapgg.androidrank.ui.fragment.GitHubFollowersFragment;
import com.hotbitmapgg.androidrank.ui.fragment.GitHubFollowingFragment;
import com.hotbitmapgg.androidrank.ui.fragment.GitHubUserDetailsFragment;
import com.hotbitmapgg.androidrank.utils.ACache;
import com.hotbitmapgg.androidrank.widget.CircleImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * Github登录用户详情界面
 */
public class GitHubUserInfoActivity extends RxBaseActivity
{

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

    private GitHubUserDetailsFragment mGitHubUserDetailsFragment;

    private List<String> titles = Arrays.asList("个人主页", "关注我的", "我关注的");

    private GitHubFollowersFragment mGitHubFollowersFragment;

    private GitHubFollowingFragment mGitHubFollowingFragment;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_github_user_info;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {


        mUserInfo = (GitHubUserInfo) ACache.get(GitHubUserInfoActivity.this)
                .getAsObject(ConstantUtil.CACHE_USER_KEY);

        initFragments();
        setUserInfo();
    }

    private void initFragments()
    {

        mGitHubUserDetailsFragment = GitHubUserDetailsFragment.newInstance(mUserInfo);
        mGitHubFollowersFragment = GitHubFollowersFragment.newInstance(mUserInfo);
        mGitHubFollowingFragment = GitHubFollowingFragment.newInstance(mUserInfo);
        fragments.add(mGitHubUserDetailsFragment);
        fragments.add(mGitHubFollowersFragment);
        fragments.add(mGitHubFollowingFragment);
        mViewPager.setAdapter(new UserInfoPagerAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(fragments.size());
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    private void setUserInfo()
    {

        Glide.with(this)
                .load(mUserInfo.avatarUrl)
                .asBitmap()
                .placeholder(R.drawable.ic_slide_menu_avatar_no_login)
                .into(new BitmapImageViewTarget(mUserInfoAvatar)
                {

                    @Override
                    protected void setResource(Bitmap resource)
                    {

                        mUserInfoAvatar.setImageDrawable(RoundedBitmapDrawableFactory
                                .create(GitHubUserInfoActivity.this.getResources(), resource));
                    }
                });
        mUsername.setText(mUserInfo.name);
    }

    @Override
    public void initToolBar()
    {

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        mCollapsingToolbarLayout.setTitle(mUserInfo.login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_user_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        } else if (item.getItemId() == R.id.action_info_share)
        {
            share();
        } else if (item.getItemId() == R.id.action_logout)
        {
            ACache.get(GitHubUserInfoActivity.this).clear();
            RxBus.getInstance().post(ConstantUtil.CODE_LOGOUT);
            GitHubUserInfoActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void share()
    {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「AndroidRank」的分享:" + mUserInfo.htmlUrl);
        startActivity(Intent.createChooser(intent, mUserInfo.name));
    }


    private class UserInfoPagerAdapter extends FragmentStatePagerAdapter
    {


        public UserInfoPagerAdapter(FragmentManager fm)
        {

            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {

            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {

            return titles.get(position);
        }

        @Override
        public int getCount()
        {

            return fragments.size();
        }
    }
}
