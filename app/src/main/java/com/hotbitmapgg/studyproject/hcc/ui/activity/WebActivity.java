package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.utils.JsHandler;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;
import com.hotbitmapgg.studyproject.hcc.widget.CircleProgressView;
import com.hotbitmapgg.studyproject.hcc.widget.CommonWebView;
import com.hotbitmapgg.studyproject.hcc.widget.CommonWebViewClient;

import butterknife.Bind;

/**
 * gank.IO详情web页面
 */
public class WebActivity extends AbsBaseActivity
{

    @Bind(R.id.circle_progress)
    CircleProgressView mCircleProgressView;

    @Bind(R.id.web_view)
    CommonWebView mCommonWebView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private static final String KEY_URL = "key_url";

    private static final String KEY_TITLE = "key_title";

    private String url, title;


    @Override
    public int getLayoutId()
    {

        return R.layout.activity_web;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        Intent intent = getIntent();
        if (intent != null)
            parseIntent(intent);

        initWebSetting();

        CommonWebViewClient mCommonWebViewClient = new CommonWebViewClient(this);
        mCommonWebViewClient.setOnPageFinish(new CommonWebViewClient.OnPageFinish()
        {

            @Override
            public void isFinish()
            {

                LogUtil.all("页面加载完毕");
                hideProgress();
            }
        });
        mCommonWebView.setWebViewClient(mCommonWebViewClient);
        mCommonWebView.loadUrl(url);
    }

    @Override
    public void initToolBar()
    {

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int itemId = item.getItemId();
        if(itemId == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
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
        title = intent.getStringExtra(KEY_TITLE);

        if (TextUtils.isEmpty(url))
        {
            finish();
        }
    }

    private void initWebSetting()
    {

        JsHandler jsHandler = new JsHandler(this, mCommonWebView);
        mCommonWebView.addJavascriptInterface(jsHandler, "JsHandler");
    }

    public static boolean start(Activity activity, String url, String title)
    {

        Intent intent = new Intent();
        intent.setClass(activity, WebActivity.class);
        intent.putExtra(KEY_URL, url);
        intent.putExtra(KEY_TITLE, title);
        activity.startActivity(intent);

        return true;
    }

    public static boolean start(Activity activity, String url)
    {

        return start(activity, url, null);
    }


    public void hideProgress()
    {

        mCircleProgressView.setVisibility(View.GONE);
        mCircleProgressView.stopSpinning();
    }
}
