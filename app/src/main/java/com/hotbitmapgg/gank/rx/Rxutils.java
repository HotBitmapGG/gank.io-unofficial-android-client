package com.hotbitmapgg.gank.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hcc on 2017/1/3 15:12
 * 100332338@qq.com
 * LeisureRead
 *
 * @HotBitmapGG Rxjava工具类
 */

public class Rxutils {

  /**
   * rxjava线程切换工具类
   */
  public static <T> Observable.Transformer<T, T> normalSchedulers() {
    return tObservable -> tObservable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
