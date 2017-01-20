package com.hotbitmapgg.gank.ui.activity;

import butterknife.Bind;
import com.hotbitmapgg.gank.base.RxBaseActivity;
import com.hotbitmapgg.gank.config.ConstantUtil;
import com.hotbitmapgg.gank.model.GitHubUserInfo;
import com.hotbitmapgg.gank.network.RetrofitHelper;
import com.hotbitmapgg.gank.rx.RxBus;
import com.hotbitmapgg.gank.utils.ACache;
import com.hotbitmapgg.gank.utils.JsHandler;
import com.hotbitmapgg.gank.utils.LogUtil;
import com.hotbitmapgg.gank.utils.UrlUtil;
import com.hotbitmapgg.gank.widget.CircleProgressView;
import com.hotbitmapgg.gank.widget.web.CommonWebChromeClient;
import com.hotbitmapgg.gank.widget.web.CommonWebView;
import com.hotbitmapgg.gank.widget.web.GitHubLoginWebViewClient;
import com.hotbitmapgg.studyproject.R;
import java.io.IOException;
import java.util.Map;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * GitHub三方登录授权跳转网页界面
 * <p/>
 * Tips:
 * 因为授权三方登录需要页面跳转获取登录所需要的Token参数
 * 所以在这里先打开网页进行登录在获取参数去拉取用户信息
 */
public class LoginGitHubActivity extends RxBaseActivity {

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

  private String url;


  @Override
  public int getLayoutId() {

    return R.layout.activity_login_github;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {

    Intent intent = getIntent();
    if (intent != null) {
      parseIntent(intent);
    }

    loadData();
  }


  @Override
  public void initToolBar() {

    mToolbar.setTitle("GitHub授权登录");
    mToolbar.setNavigationIcon(R.drawable.ic_clear);
    mToolbar.setNavigationOnClickListener(v -> onBackPressed());
  }


  @Override public void loadData() {

    initWebSetting();
    hideProgress();
    mCommonWebView.setWebChromeClient(new CommonWebChromeClient(mBar, mCircleProgressView));
    mCommonWebView.setWebViewClient(new GitHubLoginWebViewClient(LoginGitHubActivity.this));
    mCommonWebView.loadUrl(url);
  }


  @Override
  protected void onNewIntent(Intent intent) {

    super.onNewIntent(intent);
    parseIntent(intent);
  }


  private void parseIntent(Intent intent) {

    url = intent.getStringExtra(ConstantUtil.KEY_URL);

    if (TextUtils.isEmpty(url)) {
      finish();
    }
    // Github授权登录回调url后解析地址拿到code参数
    if (url.startsWith(ConstantUtil.AUTHORIZATION_CALLBACK_URL)) {
      Map<String, String> stringStringMap = UrlUtil.URLRequest(url);
      LogUtil.all(stringStringMap.toString());
      String code = stringStringMap.get("code");
      if (!TextUtils.isEmpty(code)) {
        showProgress();
      }

      RetrofitHelper.getGithubLoginApi()
          .getLoginToken(ConstantUtil.GITHUB_CLIENT_ID, ConstantUtil.GITHUB_CLIENT_SECRET,
              code, ConstantUtil.AUTHORIZATION_CALLBACK_URL)
          .compose(this.bindToLifecycle())
          .map(responseBody -> {

            try {
              String string = responseBody.string();
              String token = UrlUtil.getTokenByUrl(string);
              LogUtil.all(token);

              return token;
            } catch (IOException e) {
              e.printStackTrace();
              return null;
            }
          })
          .filter(s -> !TextUtils.isEmpty(s))
          .flatMap(new Func1<String, Observable<GitHubUserInfo>>() {

            @Override
            public Observable<GitHubUserInfo> call(String s) {

              return RetrofitHelper.getGithubApi().getGithubLoginUserInfo(s);
            }
          })
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(gitHubUserInfo -> {

            LogUtil.all(gitHubUserInfo.name + gitHubUserInfo.bio);
            ACache.get(LoginGitHubActivity.this)
                .put(ConstantUtil.CACHE_USER_KEY, gitHubUserInfo);
            hideProgress();
            Toast.makeText(LoginGitHubActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            RxBus.getInstance().post(ConstantUtil.CODE_SUCCESS);
            LoginGitHubActivity.this.finish();
          }, throwable -> {
            hideProgress();
            Toast.makeText(LoginGitHubActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
          });
    } else {
      mCommonWebView.loadUrl(url);
    }
  }


  private void initWebSetting() {

    JsHandler jsHandler = new JsHandler(this, mCommonWebView);
    mCommonWebView.addJavascriptInterface(jsHandler, "JsHandler");
  }


  public static void launch(Activity activity, String url) {

    Intent intent = new Intent();
    intent.setClass(activity, LoginGitHubActivity.class);
    intent.putExtra(ConstantUtil.KEY_URL, url);
    activity.startActivity(intent);
  }


  public void showProgress() {

    mCircleProgressView.post(() -> {
      mCircleProgressView.setVisibility(View.VISIBLE);
      mCircleProgressView.spin();
      mLoadLayout.setVisibility(View.VISIBLE);
    });
  }


  public void hideProgress() {

    mCircleProgressView.setVisibility(View.GONE);
    mCircleProgressView.stopSpinning();
    mLoadLayout.setVisibility(View.GONE);
  }
}
