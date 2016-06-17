package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.model.GithubUser;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;
import com.hotbitmapgg.studyproject.hcc.widget.CircleImageView;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class GithubUserInfoActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.user_avatar)
    CircleImageView mUserAvatar;

    @Bind(R.id.user_name)
    TextView mUserName;

    @Bind(R.id.user_github_url)
    TextView mGithubUrl;

    public static final String KEY = "who";

    private String name;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_github_user_info;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        Intent intent = getIntent();
        if (intent != null)
        {
            name = intent.getStringExtra(KEY);
        }

        RetrofitHelper.getGithubUserApi()
                .getGithubUserInfo(name)
                .filter(new Func1<GithubUser,Boolean>()
                {

                    @Override
                    public Boolean call(GithubUser githubUser)
                    {

                        return !githubUser.isIncompleteResults;
                    }
                })
                .map(new Func1<GithubUser,GithubUser.UserInfo>()
                {

                    @Override
                    public GithubUser.UserInfo call(GithubUser githubUser)
                    {

                        return githubUser.items.get(0);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GithubUser.UserInfo>()
                {

                    @Override
                    public void call(GithubUser.UserInfo userInfo)
                    {

                        Glide.with(GithubUserInfoActivity.this)
                                .load(userInfo.avatarUrl)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(mUserAvatar);

                        mUserName.setText(userInfo.userName);
                        mGithubUrl.setText(userInfo.githubUrl);
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        LogUtil.all("查询失败");
                    }
                });
    }

    @Override
    public void initToolBar()
    {

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void luancher(Activity activity, String who)
    {

        Intent mIntent = new Intent(activity, GithubUserInfoActivity.class);
        mIntent.putExtra(KEY, who);
        activity.startActivity(mIntent);
    }
}
