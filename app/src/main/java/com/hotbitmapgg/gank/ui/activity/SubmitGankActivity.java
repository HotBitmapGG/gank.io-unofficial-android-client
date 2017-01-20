package com.hotbitmapgg.gank.ui.activity;

import butterknife.Bind;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.hotbitmapgg.gank.adapter.GankTypeSelectAdapter;
import com.hotbitmapgg.gank.base.RxBaseActivity;
import com.hotbitmapgg.gank.model.GankPostBoby;
import com.hotbitmapgg.gank.model.GankPostResult;
import com.hotbitmapgg.gank.network.RetrofitHelper;
import com.hotbitmapgg.gank.utils.LogUtil;
import com.hotbitmapgg.gank.utils.SnackbarUtil;
import com.hotbitmapgg.gank.widget.LoadingDialog;
import com.hotbitmapgg.studyproject.R;
import com.jakewharton.rxbinding.view.RxMenuItem;
import com.jakewharton.rxbinding.widget.RxTextView;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * 干货提交界面
 * <p/>
 * url 	想要提交的网页地址
 * desc 	对干货内容的描述 	单独的文字描述
 * who 	提交者 ID 	干货提交者的网络 ID
 * type 	干货类型 	可选参数: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
 * debug 	当前提交为测试数据 	如果想要测试数据是否合法, 请设置 debug 为 true! 可选参数: true | false
 */
public class SubmitGankActivity extends RxBaseActivity {

  @Bind(R.id.toolbar)
  Toolbar mToolBar;

  @Bind(R.id.ed_title)
  TextInputEditText mEdTitle;

  @Bind(R.id.select_type)
  TextView mSelectType;

  @Bind(R.id.ed_url)
  TextInputEditText mEdUrl;

  @Bind(R.id.ed_who)
  TextInputEditText mEdWho;

  private LoadingDialog loadingDialog;

  private String url;

  private String title;

  private String who;

  private String type;

  private String isDebug = "false";

  private BottomSheetDialog mBottomSheetDialog;

  private List<String> types = Arrays.asList("Android", "iOS", "休息视频", "福利", "拓展资源", "前端", "瞎推荐",
      "App");


  @Override
  public int getLayoutId() {

    return R.layout.activity_submit_gank;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {

    loadingDialog = LoadingDialog.newInstance();

    RxTextView.textChanges(mEdUrl)
        .map(CharSequence::toString)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(s -> {

          if (s.contains("http")) {
            url = s;
          }
        });

    RxTextView.textChanges(mEdTitle)
        .map(CharSequence::toString)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(s -> {

          if (!TextUtils.isEmpty(s)) {
            title = s;
          }
        });

    RxTextView.textChanges(mEdWho)
        .map(CharSequence::toString)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(s -> {

          if (!TextUtils.isEmpty(s)) {
            who = s;
          }
        });
  }


  @Override
  public void initToolBar() {

    mToolBar.setTitle("提交干货");
    mToolBar.setNavigationIcon(R.drawable.back);
    mToolBar.setNavigationOnClickListener(v -> onBackPressed());

    mToolBar.inflateMenu(R.menu.menu_post);

    RxMenuItem.clicks(mToolBar.getMenu().findItem(R.id.action_post))
        .subscribe(aVoid -> {
          submitGank();
        });
  }


  @Override public void loadData() {

  }


  public void submitGank() {

    if (TextUtils.isEmpty(url) || TextUtils.isEmpty(type)
        || TextUtils.isEmpty(title) || TextUtils.isEmpty(who)
        || TextUtils.isEmpty(isDebug)) {
      SnackbarUtil.showMessage(mToolBar, "请填写完整信息和正确的填写Url地址后在进行提交");
      return;
    }

    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

      @Override
      public void call(Subscriber<? super String> subscriber) {

        GankPostBoby mBoby = new GankPostBoby();
        mBoby.setUrl(url);
        mBoby.setType(type);
        mBoby.setTitle(title);
        mBoby.setName(who);
        mBoby.setIsdebug(isDebug);

        String result = RetrofitHelper.getPostGankResult(mBoby);
        subscriber.onNext(result);
      }
    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    observable
        .compose(this.bindToLifecycle())
        .doOnSubscribe(() -> loadingDialog.show(getFragmentManager(), "loadDialogFragment"))
        .map(s -> new Gson().fromJson(s, GankPostResult.class))
        .delay(2000, TimeUnit.MILLISECONDS)
        .subscribe(gankPostResult -> {

          loadingDialog.dismiss();
          SnackbarUtil.showMessage(mToolBar, gankPostResult.msg);
        }, throwable -> {

          LogUtil.all(throwable.getMessage());
        });
  }


  @OnClick(R.id.select_type)
  void startSelectGankType() {

    mBottomSheetDialog = new BottomSheetDialog(SubmitGankActivity.this);
    mBottomSheetDialog.setContentView(R.layout.layout_gank_type_bottom);
    RecyclerView mRecyclerView = (RecyclerView) mBottomSheetDialog.findViewById(R.id.recycle);

    assert mRecyclerView != null;
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setNestedScrollingEnabled(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    GankTypeSelectAdapter mAdapter = new GankTypeSelectAdapter(mRecyclerView, types);
    mRecyclerView.setAdapter(mAdapter);
    mAdapter.setOnItemClickListener((position, holder) -> {

      type = types.get(position);
      mSelectType.setText(type);
      mBottomSheetDialog.dismiss();
    });

    mBottomSheetDialog.show();
  }


  @Override
  protected void onDestroy() {

    super.onDestroy();

    if (mBottomSheetDialog != null) {
      if (mBottomSheetDialog.isShowing()) {
        mBottomSheetDialog.dismiss();
      }
    }
  }
}
