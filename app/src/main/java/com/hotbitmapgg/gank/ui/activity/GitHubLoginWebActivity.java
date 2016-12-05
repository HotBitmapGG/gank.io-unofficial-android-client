package com.hotbitmapgg.gank.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hotbitmapgg.gank.widget.CircleProgressView;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.gank.base.RxBaseActivity;
import com.hotbitmapgg.gank.config.ConstantUtil;
import com.hotbitmapgg.gank.model.GitHubUserInfo;
import com.hotbitmapgg.gank.network.RetrofitHelper;
import com.hotbitmapgg.gank.rx.RxBus;
import com.hotbitmapgg.gank.utils.ACache;
import com.hotbitmapgg.gank.utils.JsHandler;
import com.hotbitmapgg.gank.utils.LogUtil;
import com.hotbitmapgg.gank.utils.UrlUtil;
import com.hotbitmapgg.gank.widget.web.CommonWebChromeClient;
import com.hotbitmapgg.gank.widget.web.CommonWebView;
import com.hotbitmapgg.gank.widget.web.GitHubLoginWebViewClient;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * GitHub三方登录授权跳转网页界面
 * <p/>
 * Tips:
 * 因为授权三方登录需要页面跳转获取登录所需要的Token参数
 * 所以在这里先打开网页进行登录在获取参数去拉取用户信息
 */
public class GitHubLoginWebActivity extends RxBaseActivity
{

    @Bind(R.id.circle_progress)
    CircleProgressView mCircleProgressView;

    @Bind(R.id.progress_layout)
    LinearLayout mLoadLayout;

    @Bind(R.id.progress_bar)
    ProgressBar mBar;

    @Bind(R.id.web_view)
    CommonWebView mCommonWebView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private static final String KEY_URL = "key_url";

    private String url;


    @Override
    public int getLayoutId()
    {

        return R.layout.activity_github_login_web;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        Intent intent = getIntent();
        if (intent != null)
            parseIntent(intent);

        initWebSetting();

        hideProgress();
        mCommonWebView.setWebChromeClient(new CommonWebChromeClient(mBar, mCircleProgressView));
        mCommonWebView.setWebViewClient(new GitHubLoginWebViewClient(GitHubLoginWebActivity.this));
        mCommonWebView.loadUrl(url);
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("GitHub授权登录");
        mToolbar.setNavigationIcon(R.drawable.ic_clear);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                finish();
            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent)
    {

        super.onNewIntent(intent);
        parseIntent(intent);
    }

    private void parseIntent(Intent intent)
    {

        url = intent.getStringExtra(KEY_URL);

        if (TextUtils.isEmpty(url))
        {
            finish();
        }
        // Github授权登录回调url后解析地址拿到code参数
        if (url.startsWith(ConstantUtil.AUTHORIZATION_CALLBACK_URL))
        {
            Map<String,String> stringStringMap = UrlUtil.URLRequest(url);
            LogUtil.all(stringStringMap.toString());
            String code = stringStringMap.get("code");
            if (!TextUtils.isEmpty(code))
                showProgress();

            RetrofitHelper.getGithubLoginApi()
                    .getLoginToken(ConstantUtil.GITHUB_CLIENT_ID, ConstantUtil.GITHUB_CLIENT_SECRET,
                    code, ConstantUtil.AUTHORIZATION_CALLBACK_URL)
                    .compose(this.<ResponseBody>bindToLifecycle())
                    .map(new Func1<ResponseBody,String>()
                    {

                        @Override
                        public String call(ResponseBody responseBody)
                        {

                            try
                            {
                                String string = responseBody.string();
                                String token = UrlUtil.getTokenByUrl(string);
                                LogUtil.all(token);

                                return token;
                            } catch (IOException e)
                            {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    })
                    .filter(new Func1<String,Boolean>()
                    {

                        @Override
                        public Boolean call(String s)
                        {

                            return !TextUtils.isEmpty(s);
                        }
                    })
                    .flatMap(new Func1<String,Observable<GitHubUserInfo>>()
                    {

                        @Override
                        public Observable<GitHubUserInfo> call(String s)
                        {


                            return RetrofitHelper.getGithubApi().getGithubLoginUserInfo(s);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<GitHubUserInfo>()
                    {

                        @Override
                        public void call(GitHubUserInfo gitHubUserInfo)
                        {

                            LogUtil.all(gitHubUserInfo.name + gitHubUserInfo.bio);
                            ACache.get(GitHubLoginWebActivity.this)
                                    .put(ConstantUtil.CACHE_USER_KEY, gitHubUserInfo);
                            hideProgress();
                            Toast.makeText(GitHubLoginWebActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            RxBus.getInstance().post(ConstantUtil.CODE_SUCCESS);
                            GitHubLoginWebActivity.this.finish();
                        }
                    }, new Action1<Throwable>()
                    {

                        @Override
                        public void call(Throwable throwable)
                        {

                            LogUtil.all(throwable.getMessage() + "登录失败");
                            hideProgress();
                            Toast.makeText(GitHubLoginWebActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else
        {
            mCommonWebView.loadUrl(url);
        }
    }

    private void initWebSetting()
    {

        JsHandler jsHandler = new JsHandler(this, mCommonWebView);
        mCommonWebView.addJavascriptInterface(jsHandler, "JsHandler");
    }

    public static void luancher(Activity activity, String url)
    {

        Intent intent = new Intent();
        intent.setClass(activity, GitHubLoginWebActivity.class);
        intent.putExtra(KEY_URL, url);
        activity.startActivity(intent);
    }


    public void showProgress()
    {

        mCircleProgressView.post(new Runnable()
        {

            @Override
            public void run()
            {
                mCircleProgressView.setVisibility(View.VISIBLE);
                mCircleProgressView.spin();
                mLoadLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    public void hideProgress()
    {

        mCircleProgressView.setVisibility(View.GONE);
        mCircleProgressView.stopSpinning();
        mLoadLayout.setVisibility(View.GONE);
    }
}
