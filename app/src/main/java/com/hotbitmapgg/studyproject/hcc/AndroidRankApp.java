package com.hotbitmapgg.studyproject.hcc;

import android.app.Application;
import android.content.Context;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

public class AndroidRankApp extends Application
{

    public static Context mAppContext;

    @Override
    public void onCreate()
    {

        super.onCreate();
        mAppContext = this;
        // 配置Realm数据库
        RealmConfiguration configuration = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().schemaVersion(6).migration(new RealmMigration()
        {

            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion)
            {

            }
        }).build();

        Realm.setDefaultConfiguration(configuration);
    }


    public static Context getContext()
    {

        return mAppContext;
    }
}
