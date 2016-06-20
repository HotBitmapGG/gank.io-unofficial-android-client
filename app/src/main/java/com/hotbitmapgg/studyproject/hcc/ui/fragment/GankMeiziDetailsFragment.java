package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.config.ConstantUtil;
import com.hotbitmapgg.studyproject.hcc.utils.GlideDownloadImageUtil;
import com.hotbitmapgg.studyproject.hcc.widget.image.PhotoImageView;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;

import butterknife.Bind;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class GankMeiziDetailsFragment extends LazyFragment implements RequestListener<String,GlideDrawable>
{

    @Bind(R.id.meizi)
    PhotoImageView mImageView;

    @Bind(R.id.meizi_title)
    TextView mTitle;

    private static final String EXTRA_URL = "extra_url";

    private static final String EXTRA_TITLE = "extra_title";

    private String url;

    private String title;

    private CompositeSubscription mCompositeSubscription;

    public static GankMeiziDetailsFragment newInstance(String url , String title)
    {

        GankMeiziDetailsFragment mDoubanMeiziDetailsFragment = new GankMeiziDetailsFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(EXTRA_URL, url);
        mBundle.putString(EXTRA_TITLE , title);
        mDoubanMeiziDetailsFragment.setArguments(mBundle);

        return mDoubanMeiziDetailsFragment;
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_meizi_details;
    }

    @Override
    public void initViews()
    {

        mCompositeSubscription = new CompositeSubscription();

        url = getArguments().getString(EXTRA_URL);
        title = getArguments().getString(EXTRA_TITLE);
        Glide.with(this).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade(0)
                .listener(this)
                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);

        mTitle.setText(title);


        mImageView.setOnLongClickListener(new View.OnLongClickListener()
        {

            @Override
            public boolean onLongClick(View v)
            {

                new AlertDialog.Builder(getActivity())
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


        Subscription s = GlideDownloadImageUtil.saveImageToLocal(getActivity(), url, title)
                .compose(RxPermissions.getInstance(getActivity()).ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .observeOn(Schedulers.io())
                .filter(new Func1<Boolean,Boolean>()
                {

                    @Override
                    public Boolean call(Boolean aBoolean)
                    {

                        return aBoolean;
                    }
                })
                .flatMap(new Func1<Boolean,Observable<Uri>>()
                {

                    @Override
                    public Observable<Uri> call(Boolean aBoolean)
                    {

                        return GlideDownloadImageUtil.saveImageToLocal(getActivity(), url, title);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>()
                {

                    @Override
                    public void call(Uri uri)
                    {

                        File appDir = new File(Environment.getExternalStorageDirectory(), ConstantUtil.FILE_DIR);
                        String msg = String.format("图片已保存至 %s 文件夹", appDir.getAbsolutePath());
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        Toast.makeText(getActivity(), "保存失败,请重试", Toast.LENGTH_SHORT).show();
                    }
                });

        mCompositeSubscription.add(s);
    }

    @Override
    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource)
    {

        return false;
    }

    @Override
    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource)
    {

        mImageView.setImageDrawable(resource);
        return false;
    }

    @Override
    public void onDestroy()
    {

        super.onDestroy();

        if(mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed())
        {
            mCompositeSubscription.unsubscribe();
        }
    }
}
