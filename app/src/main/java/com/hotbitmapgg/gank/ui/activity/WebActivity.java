package com.hotbitmapgg.gank.ui.activity;

import butterknife.Bind;
import com.hotbitmapgg.gank.base.RxBaseActivity;
import com.hotbitmapgg.gank.config.ConstantUtil;
import com.hotbitmapgg.gank.utils.ClipboardUtils;
import com.hotbitmapgg.gank.utils.JsHandler;
import com.hotbitmapgg.gank.utils.SnackbarUtil;
import com.hotbitmapgg.gank.widget.CircleProgressView;
import com.hotbitmapgg.gank.widget.web.CommonWebChromeClient;
import com.hotbitmapgg.gank.widget.web.CommonWebView;
import com.hotbitmapgg.gank.widget.web.CommonWebViewClient;
import com.hotbitmapgg.studyproject.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

/**
 * gank.IO详情web页面
 */
public class WebActivity extends RxBaseActivity {

  @Bind(R.id.circle_progress)
  CircleProgressView mCircleProgressView;

  @Bind(R.id.progress_bar)
  ProgressBar mBar;

  @Bind(R.id.web_view)
  CommonWebView mCommonWebView;

  @Bind(R.id.toolbar)
  Toolbar mToolbar;

  private String url, title;


  @Override
  public int getLayoutId() {

    return R.layout.activity_web;
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

    mToolbar.setTitle(title);
    setSupportActionBar(mToolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }


  @Override public void loadData() {

    initWebSetting();
    hideProgress();
    mCommonWebView.setWebChromeClient(new CommonWebChromeClient(mBar, mCircleProgressView));
    mCommonWebView.setWebViewClient(new CommonWebViewClient(WebActivity.this));
    mCommonWebView.loadUrl(url);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menu_web, menu);
    return true;
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    int itemId = item.getItemId();
    switch (itemId) {
      case android.R.id.home:
        onBackPressed();
        return true;

      case R.id.action_share:
        share();
        return true;

      case R.id.action_copy:
        ClipboardUtils.setText(WebActivity.this, url);
        SnackbarUtil.showMessage(mCommonWebView, "已复制到剪贴板");
        return true;
    }

    return super.onOptionsItemSelected(item);
  }


  @Override
  protected void onNewIntent(Intent intent) {

    super.onNewIntent(intent);
    parseIntent(intent);
  }


  private void parseIntent(Intent intent) {

    url = intent.getStringExtra(ConstantUtil.KEY_URL);
    title = intent.getStringExtra(ConstantUtil.KEY_TITLE);

    if (TextUtils.isEmpty(url)) {
      finish();
    }
  }


  private void initWebSetting() {

    JsHandler jsHandler = new JsHandler(this, mCommonWebView);
    mCommonWebView.addJavascriptInterface(jsHandler, "JsHandler");
  }


  public static void launch(Activity activity, String url, String title) {

    Intent intent = new Intent();
    intent.setClass(activity, WebActivity.class);
    intent.putExtra(ConstantUtil.KEY_URL, url);
    intent.putExtra(ConstantUtil.KEY_TITLE, title);
    activity.startActivity(intent);
  }


  public void hideProgress() {

    mCircleProgressView.setVisibility(View.GONE);
    mCircleProgressView.stopSpinning();
  }


  private void share() {

    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
    intent.putExtra(Intent.EXTRA_TEXT, "来自「Gank.io」的分享:" + url);
    startActivity(Intent.createChooser(intent, title));
  }
}
