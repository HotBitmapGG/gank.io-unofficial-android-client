package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.GitHubStarredAdapter;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.model.GitHubStarInfo;
import com.hotbitmapgg.studyproject.hcc.model.GitHubUserInfo;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.ui.activity.WebActivity;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;
import com.hotbitmapgg.studyproject.hcc.widget.CircleProgressView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * GitHub用户详情信息
 */
public class GitHubUserDetailsFragment extends LazyFragment
{

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

    private static final String KEY = "info";

    private GitHubUserInfo mUserInfo;

    public static GitHubUserDetailsFragment newInstance(GitHubUserInfo userInfo)
    {

        GitHubUserDetailsFragment mFragment = new GitHubUserDetailsFragment();
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(KEY, userInfo);
        mFragment.setArguments(mBundle);

        return mFragment;
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_github_user_details;
    }

    @Override
    public void initViews()
    {

        mUserInfo = (GitHubUserInfo) getArguments().getSerializable(KEY);

        mUserBio.setText(mUserInfo.bio);
        mUserBlog.setText(mUserInfo.blog);
        mUserCompany.setText(mUserInfo.company);
        mUserEmail.setText(mUserInfo.email);
        mUserLocation.setText(mUserInfo.location);
        mUserFollowers.setText(mUserInfo.followers + "");
        mUserFollowing.setText(mUserInfo.following + "");


        getGitHubUserStarred();
    }

    private void getGitHubUserStarred()
    {

        RetrofitHelper.getGithubApi()
                .getGitHubStarred(mUserInfo.login)
                .doOnSubscribe(new Action0()
                {

                    @Override
                    public void call()
                    {
                        showProgress();
                    }
                })
                .map(new Func1<ResponseBody,List<GitHubStarInfo>>()
                {

                    @Override
                    public List<GitHubStarInfo> call(ResponseBody responseBody)
                    {

                        try
                        {
                            GitHubStarInfo[] gitHubStarInfos = new GsonBuilder()
                                    .create()
                                    .fromJson(responseBody.string(), GitHubStarInfo[].class);

                            int length = gitHubStarInfos.length;
                            for (int i = 0; i < length; i++)
                            {
                                stars.add(gitHubStarInfos[i]);
                            }

                            return stars;
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                            return null;
                        }
                    }
                })
                .delay(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<GitHubStarInfo>>()
                {

                    @Override
                    public void call(List<GitHubStarInfo> gitHubStarInfos)
                    {

                        finishGet();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        LogUtil.all("加载Star数据失败" + throwable.getMessage());
                    }
                });
    }

    private void finishGet()
    {

        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GitHubStarredAdapter mAdapter = new GitHubStarredAdapter(mRecyclerView, stars);
        mRecyclerView.setAdapter(mAdapter);
        hideProgress();

        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                GitHubStarInfo gitHubStarInfo = stars.get(position);
                WebActivity.start(getActivity(),gitHubStarInfo.htmlUrl , gitHubStarInfo.fullName);
            }
        });

    }

    public void showProgress()
    {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mLayout.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        mCircleProgressView.setVisibility(View.GONE);
        mCircleProgressView.stopSpinning();
        mLayout.setVisibility(View.VISIBLE);
    }
}
