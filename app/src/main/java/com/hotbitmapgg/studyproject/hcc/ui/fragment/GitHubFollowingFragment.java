package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.GsonBuilder;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.GitHubFollowInfoAdapter;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.model.GitHubFollowerInfo;
import com.hotbitmapgg.studyproject.hcc.model.GitHubUserInfo;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 用户关注的人界面
 */
public class GitHubFollowingFragment extends LazyFragment
{

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    private static final String KEY = "info";

    private List<GitHubFollowerInfo> followerInfos = new ArrayList<>();

    public static GitHubFollowingFragment newInstance(GitHubUserInfo userInfo)
    {

        GitHubFollowingFragment mFollowersFragment = new GitHubFollowingFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, userInfo);
        mFollowersFragment.setArguments(bundle);

        return mFollowersFragment;
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_follow_info;
    }

    @Override
    public void initViews()
    {

        GitHubUserInfo mUserInfo = (GitHubUserInfo) getArguments().getSerializable(KEY);
        RetrofitHelper.getGithubApi()
                .getGitHubFolloweing(mUserInfo.name)
                .map(new Func1<ResponseBody,List<GitHubFollowerInfo>>()
                {

                    @Override
                    public List<GitHubFollowerInfo> call(ResponseBody responseBody)
                    {

                        try
                        {
                            GitHubFollowerInfo[] gitHubFollowerInfos = new GsonBuilder()
                                    .create()
                                    .fromJson(responseBody.string(), GitHubFollowerInfo[].class);

                            int length = gitHubFollowerInfos.length;
                            for (int i = 0; i < length; i++)
                            {
                                followerInfos.add(gitHubFollowerInfos[i]);
                            }

                            return followerInfos;
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                            return null;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<GitHubFollowerInfo>>()
                {

                    @Override
                    public void call(List<GitHubFollowerInfo> gitHubFollowerInfos)
                    {

                        finishGet();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        LogUtil.all("加载用户关注的人数据失败");
                    }
                });
    }

    private void finishGet()
    {

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new GitHubFollowInfoAdapter(mRecyclerView, followerInfos));
    }
}
