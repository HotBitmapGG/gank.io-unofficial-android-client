package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.utils.ImageUtil;

import java.io.File;

import butterknife.Bind;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


public class HuaBanMeiziDetailsActivity extends AbsBaseActivity
{


    @Bind(R.id.meizi)
    ImageView mImageView;

    @Bind(R.id.meizi_title)
    TextView mTitle;

    @Bind(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    private static final String EXTRA_URL = "extra_url";

    private static final String EXTRA_TITLE = "extra_title";

    private String url;

    private String title;

    private CompositeSubscription mCompositeSubscription;

    private boolean isHide = false;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_meizi_pic;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        mCompositeSubscription = new CompositeSubscription();

        Intent intent = getIntent();
        if (intent != null)
        {
            url = intent.getStringExtra(EXTRA_URL);
            title = intent.getStringExtra(EXTRA_TITLE);
        }

        Glide.with(this).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView);

        mTitle.setText(title);

        setUpPhotoAttacher();
    }

    @Override
    public void initToolBar()
    {

        mToolBar.setTitle(title);
        setSupportActionBar(mToolBar);
        ActionBar supportActionBar = getSupportActionBar();
        mToolBar.setNavigationOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                onBackPressed();
            }
        });
        if (supportActionBar != null)
        {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_meizi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int itemId = item.getItemId();
        switch (itemId)
        {
            case R.id.action_fuli_share:
                // 分享
                ImageUtil.saveImageAndGetPathObservable(HuaBanMeiziDetailsActivity.this, url, title)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Uri>()
                        {

                            @Override
                            public void call(Uri uri)
                            {

                                share(uri);
                            }
                        }, new Action1<Throwable>()
                        {

                            @Override
                            public void call(Throwable throwable)
                            {

                                Toast.makeText(HuaBanMeiziDetailsActivity.this, "保存失败,请重试", Toast.LENGTH_SHORT).show();
                            }
                        });
                return true;

            case R.id.action_fuli_save:
                //保存
                saveImageToGallery();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public static Intent LuanchActivity(Activity activity, String url, String title)
    {

        Intent intent = new Intent(activity, HuaBanMeiziDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        return intent;
    }

    private void setUpPhotoAttacher()
    {

        mImageView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                //隐藏ToolBar
                hideOrShowToolbar();
            }
        });

        mImageView.setOnLongClickListener(new View.OnLongClickListener()
        {

            @Override
            public boolean onLongClick(View v)
            {
                new AlertDialog.Builder(HuaBanMeiziDetailsActivity.this)
                        .setMessage("是否保存到本地?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                saveImageToGallery();
                                dialog.dismiss();
                            }
                        })
                        .show();


                return true;
            }
        });
    }


    private void saveImageToGallery()
    {

        Subscription s = ImageUtil.saveImageAndGetPathObservable(this, url, title)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>()
                {

                    @Override
                    public void call(Uri uri)
                    {

                        File appDir = new File(Environment.getExternalStorageDirectory(), "gank_io");
                        String msg = String.format("图片已保存至 %s 文件夹", appDir.getAbsolutePath());
                        Toast.makeText(HuaBanMeiziDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        Toast.makeText(HuaBanMeiziDetailsActivity.this, "保存失败,请重试", Toast.LENGTH_SHORT).show();
                    }
                });

        mCompositeSubscription.add(s);
    }

    /**
     * 分享图片
     *
     * @param uri
     */
    private void share(Uri uri)
    {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, title));
    }

    protected void hideOrShowToolbar()
    {

        mAppBarLayout.animate()
                .translationY(isHide ? 0 : -mAppBarLayout.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();

        isHide = !isHide;
    }


    @Override
    protected void onDestroy()
    {

        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed())
        {
            mCompositeSubscription.unsubscribe();
        }
        super.onDestroy();
    }
}
