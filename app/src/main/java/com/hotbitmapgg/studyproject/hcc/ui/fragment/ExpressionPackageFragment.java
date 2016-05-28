package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.ZhuangbiAdapter;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.model.ExpressionPackage;
import com.hotbitmapgg.studyproject.hcc.network.ExpressionPackageApi;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.utils.ImageUtil;
import com.hotbitmapgg.studyproject.hcc.utils.SnackbarUtil;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class ExpressionPackageFragment extends LazyFragment
{

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    @Bind(R.id.edit)
    EditText mQueryEdit;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String content;

    private InputMethodManager mInputManager;

    private CompositeSubscription mCompositeSubscription;

    private List<ExpressionPackage> datas = new ArrayList<>();

    public static ExpressionPackageFragment newInstance()
    {

        return new ExpressionPackageFragment();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_expression_package;
    }

    @Override
    public void initViews()
    {


        mCompositeSubscription = new CompositeSubscription();

        mInputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {

            @Override
            public void onRefresh()
            {

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        RxTextView.textChangeEvents(mQueryEdit)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>()
                {

                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent)
                    {

                        content = textViewTextChangeEvent.text().toString();
                    }
                });


        mQueryEdit.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {

                if(actionId == EditorInfo.IME_ACTION_SEARCH)
                {
                    //加载数据
                    showProgress();
                    //隐藏键盘
                    hideKeyBord();
                    //清空输入内容
                    mQueryEdit.setText("");
                }
                return false;
            }
        });
    }

    private void getZhuangBiList(String text)
    {

        ExpressionPackageApi expressionPackageApi = RetrofitHelper.getExpressionPackageApi();

        expressionPackageApi.search(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ExpressionPackage>>()
                {

                    @Override
                    public void call(List<ExpressionPackage> expressionPackages)
                    {

                        datas.clear();
                        if (expressionPackages != null && expressionPackages.size() > 0)
                        {

                            datas.addAll(expressionPackages);
                            finishGetZhuangBiList();
                        } else
                        {
                            hideSwipeRefreshLayout();
                            SnackbarUtil.showMessage(mRecyclerView , "查询失败,没有该关键字的表情包!");
                        }
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {


                        hideSwipeRefreshLayout();
                        SnackbarUtil.showMessage(mRecyclerView , "网络连接超时！");
                    }
                });
    }

    private void finishGetZhuangBiList()
    {

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ZhuangbiAdapter mAdapter = new ZhuangbiAdapter(mRecyclerView, datas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);

        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(final int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
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

                                ExpressionPackage expressionPackage = datas.get(position);
                                saveImageToGallery(expressionPackage);
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        mAdapter.setOnItemLongClickListener(new AbsRecyclerViewAdapter.OnItemLongClickListener()
        {

            @Override
            public boolean onItemLongClick(final int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                new AlertDialog.Builder(getActivity())
                        .setMessage("是否分享给好友?")
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

                                shareImage(datas.get(position));
                                dialog.dismiss();
                            }
                        })
                        .show();

                return true;
            }
        });
    }

    private void saveImageToGallery(ExpressionPackage expressionPackage)
    {

        Subscription s = ImageUtil.saveImageAndGetPathObservable(getActivity(), expressionPackage.image_url, expressionPackage.description)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>()
                {

                    @Override
                    public void call(Uri uri)
                    {

                        File appDir = new File(Environment.getExternalStorageDirectory(), "pic");
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


    public void shareImage(final ExpressionPackage expressionPackage)
    {

        Subscription subscribe = ImageUtil.saveImageAndGetPathObservable(getActivity(), expressionPackage.image_url, expressionPackage.description)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>()
                {

                    @Override
                    public void call(Uri uri)
                    {

                        share(uri, expressionPackage.description);
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        Toast.makeText(getActivity(), "保存失败,请重试", Toast.LENGTH_SHORT).show();
                    }
                });

        mCompositeSubscription.add(subscribe);
    }


    /**
     * 分享图片
     *
     * @param uri
     */
    private void share(Uri uri, String desc)
    {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, desc));
    }


    public void showProgress()
    {

        mSwipeRefreshLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {

            @Override
            public void onGlobalLayout()
            {

                mRecyclerView.setVisibility(View.GONE);
                mSwipeRefreshLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mSwipeRefreshLayout.setRefreshing(true);
                getZhuangBiList(content);
            }
        });
    }


    /**
     * 隐藏软键盘
     */
    public void hideKeyBord()
    {

        mInputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void hideSwipeRefreshLayout()
    {

        mSwipeRefreshLayout.post(new Runnable()
        {

            @Override
            public void run()
            {

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroy()
    {

        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed())
        {
            mCompositeSubscription.unsubscribe();
        }
        datas.clear();
        super.onDestroy();
    }
}
