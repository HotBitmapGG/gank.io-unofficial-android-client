package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Api接口调用测试界面
 */
public class ApiDebugActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    public int getLayoutId()
    {

        return R.layout.activity_api_debug;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {


//        Uri uri = Uri.parse("https://github.com/login/oauth/authorize?client_id=3f0b872a51a4b0c45065&state=AndroidRank&redirect_uri=http://example.com/path");
//        Intent mIntent = new Intent(Intent.ACTION_VIEW , uri);
//        startActivity(mIntent);


        /** 根据用户名查询GithubUser信息*/
//        RetrofitHelper.getGithubApi()
//                .getGithubUserInfo(name)
//                .filter(new Func1<GitHubQueryUserInfo,Boolean>()
//                {
//
//                    @Override
//                    public Boolean call(GitHubQueryUserInfo githubUser)
//                    {
//
//                        return !githubUser.isIncompleteResults;
//                    }
//                })
//                .map(new Func1<GitHubQueryUserInfo,GitHubQueryUserInfo.UserInfo>()
//                {
//
//                    @Override
//                    public GitHubQueryUserInfo.UserInfo call(GitHubQueryUserInfo githubUser)
//                    {
//
//                        return githubUser.items.get(0);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<GitHubQueryUserInfo.UserInfo>()
//                {
//
//                    @Override
//                    public void call(GitHubQueryUserInfo.UserInfo userInfo)
//                    {
//
//                        Glide.with(GitHubUserInfoActivity.this)
//                                .load(userInfo.avatarUrl)
//                                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                .into(mUserAvatar);
//
//                        mUserName.setText(userInfo.userName);
//                        mGithubUrl.setText(userInfo.githubUrl);
//                    }
//                }, new Action1<Throwable>()
//                {
//
//                    @Override
//                    public void call(Throwable throwable)
//                    {
//
//                        LogUtil.all("查询失败");
//                    }
//                });

    }

    @OnClick(R.id.debug_btn)
    void startLogin()
    {
        //GitHubLoginWebActivity.luancher(ApiDebugActivity.this , ConstantUtil.GITHUB_LOGIN_URL);
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("Api接口调试界面");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
