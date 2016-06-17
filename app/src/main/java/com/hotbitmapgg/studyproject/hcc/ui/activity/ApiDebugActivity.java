package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;

import butterknife.Bind;

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
//        RetrofitHelper.getGithubUserApi()
//                .getGithubUserInfo("HotBitmapGG")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<GithubUser>()
//                {
//
//                    @Override
//                    public void call(GithubUser githubUser)
//                    {
//
//                        LogUtil.all(githubUser.items.get(0).userName);
//                    }
//                }, new Action1<Throwable>()
//                {
//
//                    @Override
//                    public void call(Throwable throwable)
//                    {
//
//                        LogUtil.all("接口调用失败");
//                    }
//                });
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
