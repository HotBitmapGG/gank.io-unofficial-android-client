package com.hotbitmapgg.studyproject.rank.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.GsonBuilder;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.rank.adapter.GitHubFollowInfoAdapter;
import com.hotbitmapgg.studyproject.rank.base.RxBaseFragment;
import com.hotbitmapgg.studyproject.rank.model.GitHubFollowerInfo;
import com.hotbitmapgg.studyproject.rank.model.GitHubUserInfo;
import com.hotbitmapgg.studyproject.rank.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.rank.widget.recyclehelper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.rank.ui.activity.WebActivity;
import com.hotbitmapgg.studyproject.rank.utils.LogUtil;

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
 * 关注用户的人界面
 */
public class GitHubFollowersFragment extends RxBaseFragment
{

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    private List<GitHubFollowerInfo> followerInfos = new ArrayList<>();

    private static final String KEY = "info";

    public static GitHubFollowersFragment newInstance(GitHubUserInfo userInfo)
    {

        GitHubFollowersFragment mFollowersFragment = new GitHubFollowersFragment();
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
                .getGitHubFollowers(mUserInfo.login)
                .compose(this.<ResponseBody>bindToLifecycle())
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

                        LogUtil.all(gitHubFollowerInfos.size() + "##");
                        finishGet();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        LogUtil.all("加载关注用户的人数据失败");
                    }
                });
    }

    private void finishGet()
    {

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GitHubFollowInfoAdapter mAdapter = new GitHubFollowInfoAdapter(mRecyclerView, followerInfos);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                GitHubFollowerInfo gitHubFollowerInfo = followerInfos.get(position);
                WebActivity.start(getActivity(), gitHubFollowerInfo.htlmUrl, gitHubFollowerInfo.login);
            }
        });
    }
}
