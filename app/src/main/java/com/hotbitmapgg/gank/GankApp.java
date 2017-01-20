package com.hotbitmapgg.gank;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import android.app.Application;
import android.content.Context;

public class GankApp extends Application {

  public static Context mAppContext;


  @Override
  public void onCreate() {

    super.onCreate();
    mAppContext = this;
    initRealm();
  }


  /**
   * 配置Realm数据库
   */
  private void initRealm() {
    RealmConfiguration configuration = new RealmConfiguration
        .Builder(this)
        .deleteRealmIfMigrationNeeded()
        .schemaVersion(7).migration((realm, oldVersion, newVersion) -> {

        }).build();

    Realm.setDefaultConfiguration(configuration);
  }


  public static Context getContext() {

    return mAppContext;
  }
}
