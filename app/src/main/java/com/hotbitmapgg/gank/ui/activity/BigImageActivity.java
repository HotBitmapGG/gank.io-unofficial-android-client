package com.hotbitmapgg.gank.ui.activity;

import butterknife.Bind;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.gank.base.RxBaseActivity;
import com.hotbitmapgg.gank.config.ConstantUtil;
import com.hotbitmapgg.gank.utils.GlideDownloadImageUtil;
import com.hotbitmapgg.gank.utils.ImmersiveUtil;
import com.hotbitmapgg.studyproject.R;
import com.jakewharton.rxbinding.view.RxMenuItem;
import com.tbruyelle.rxpermissions.RxPermissions;
import java.io.File;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by hcc on 16/6/12.
 * <p/>
 * 福利大图浏览界面
 * Tips:系统NavBar和StatusBar状态栏隐藏方法:
 * <p/>
 * this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
 * WindowManager.LayoutParams.FLAG_FULLSCREEN);
 * mAppBarLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
 * <p/>
 * 针对6.0的权限问题进行处理
 * ToolBar单独使用的一些技巧,不与ActionBar进行一起使用
 * 使用mToolBar.inflateMenu(R.menu.xxx)即可
 */
public class BigImageActivity extends RxBaseActivity {

  @Bind(R.id.full_pic)
  ImageView mImageView;

  @Bind(R.id.app_bar_layout)
  AppBarLayout mAppBarLayout;

  @Bind(R.id.toolbar)
  Toolbar mToolBar;

  private String url;

  private String title;

  private boolean isHide = false;


  @Override
  public int getLayoutId() {

    return R.layout.activity_big_image;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {

    Intent intent = getIntent();
    if (intent != null) {
      url = intent.getStringExtra(ConstantUtil.EXTRA_URL);
      title = intent.getStringExtra(ConstantUtil.EXTRA_TITLE);
    }

    loadData();
  }


  @Override
  public void initToolBar() {

    mToolBar.setTitle(title);
    mToolBar.setNavigationIcon(R.drawable.back);
    mToolBar.setNavigationOnClickListener(v -> onBackPressed());
    mToolBar.inflateMenu(R.menu.menu_meizi);

    mAppBarLayout.setAlpha(0.5f);
    mToolBar.setBackgroundResource(R.color.black_90);
    mAppBarLayout.setBackgroundResource(R.color.black_90);

    saveImage();
    shareImage();
  }


  @Override public void loadData() {

    ViewCompat.setTransitionName(mImageView, ConstantUtil.TRANSIT_PIC);

    Glide.with(this)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(mImageView);

    mImageView.setOnClickListener(v -> {

      //隐藏ToolBar
      hideOrShowToolbar();
    });

    mImageView.setOnLongClickListener(v -> {

      new AlertDialog.Builder(BigImageActivity.this)
          .setMessage("是否保存到本地?")
          .setNegativeButton("取消", (dialog, which) -> dialog.cancel())
          .setPositiveButton("确定", (dialog, which) -> {

            saveImageToGallery();
            dialog.dismiss();
          })
          .show();

      return true;
    });
  }


  public static Intent launch(Activity activity, String url, String title) {

    Intent intent = new Intent(activity, BigImageActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra(ConstantUtil.EXTRA_URL, url);
    intent.putExtra(ConstantUtil.EXTRA_TITLE, title);
    return intent;
  }


  private void saveImageToGallery() {
    Observable.just(ConstantUtil.APP_NAME)
        .compose(bindToLifecycle())
        .compose(RxPermissions.getInstance(BigImageActivity.this)
            .ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        .observeOn(Schedulers.io())
        .filter(aBoolean -> aBoolean)
        .flatMap(new Func1<Boolean, Observable<Uri>>() {

          @Override
          public Observable<Uri> call(Boolean aBoolean) {

            return GlideDownloadImageUtil.saveImageToLocal(BigImageActivity.this, url, title,
                ConstantUtil.PIC_TYPE_JPG);
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .retry()
        .subscribe(uri -> {

          File appDir = new File(Environment.getExternalStorageDirectory(),
              ConstantUtil.FILE_DIR);
          String msg = String.format("图片已保存至 %s 文件夹", appDir.getAbsolutePath());
          Toast.makeText(BigImageActivity.this, msg, Toast.LENGTH_SHORT).show();
        }, throwable -> {

          Toast.makeText(BigImageActivity.this, "保存失败,请重试", Toast.LENGTH_SHORT).show();
        });
  }


  /**
   * 保存图片到本地
   */
  private void saveImage() {

    RxMenuItem.clicks(mToolBar.getMenu().findItem(R.id.action_fuli_save))
        .compose(bindToLifecycle())
        .compose(RxPermissions.getInstance(BigImageActivity.this)
            .ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        .observeOn(Schedulers.io())
        .filter(aBoolean -> aBoolean)
        .flatMap(new Func1<Boolean, Observable<Uri>>() {

          @Override
          public Observable<Uri> call(Boolean aBoolean) {

            return GlideDownloadImageUtil.saveImageToLocal(BigImageActivity.this, url, title,
                ConstantUtil.PIC_TYPE_JPG);
          }
        })
        .map(uri -> String.format("图片已保存至 %s 文件夹",
            new File(Environment.getExternalStorageDirectory(), ConstantUtil.FILE_DIR)
                .getAbsolutePath()))
        .observeOn(AndroidSchedulers.mainThread())
        .retry()
        .subscribe(s -> {

          Toast.makeText(BigImageActivity.this, s, Toast.LENGTH_SHORT).show();
        }, throwable -> {

          Toast.makeText(BigImageActivity.this, "保存失败,请重试", Toast.LENGTH_SHORT).show();
        });
  }


  /**
   * 分享图片
   */
  public void shareImage() {

    RxMenuItem.clicks(mToolBar.getMenu().findItem(R.id.action_fuli_share))
        .compose(bindToLifecycle())
        .compose(RxPermissions.getInstance(BigImageActivity.this)
            .ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        .observeOn(Schedulers.io())
        .filter(aBoolean -> aBoolean)
        .flatMap(new Func1<Boolean, Observable<Uri>>() {

          @Override
          public Observable<Uri> call(Boolean aBoolean) {

            return GlideDownloadImageUtil.saveImageToLocal(BigImageActivity.this, url, title,
                ConstantUtil.PIC_TYPE_JPG);
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .retry()
        .subscribe(this::share, throwable -> {

          Toast.makeText(BigImageActivity.this, "分享失败,请重试", Toast.LENGTH_SHORT).show();
        });
  }


  /**
   * 分享图片
   */
  private void share(Uri uri) {

    Intent shareIntent = new Intent();
    shareIntent.setAction(Intent.ACTION_SEND);
    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
    shareIntent.setType("image/jpeg");
    startActivity(Intent.createChooser(shareIntent, title));
  }


  protected void hideOrShowToolbar() {

    if (isHide) {
      //显示
      ImmersiveUtil.exit(this);
      mAppBarLayout.animate()
          .translationY(0)
          .setInterpolator(new DecelerateInterpolator(2))
          .start();
      isHide = false;
    } else {
      //隐藏
      ImmersiveUtil.enter(this);
      mAppBarLayout.animate()
          .translationY(-mAppBarLayout.getHeight())
          .setInterpolator(new DecelerateInterpolator(2))
          .start();
      isHide = true;
    }
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();
  }
}
