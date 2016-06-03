package com.hotbitmapgg.studyproject.hcc;

import android.app.Application;
import android.content.Context;

public class StudyApp extends Application
{

    public static Context mAppContext;

    @Override
    public void onCreate()
    {

        super.onCreate();
        mAppContext = this;
//        RealmConfiguration configuration = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().schemaVersion(4).migration(new RealmMigration()
//        {
//
//            @Override
//            public void migrate(DynamicRealm realm, long oldVersion, long newVersion)
//            {
//
//            }
//        }).build();
//
//        Realm.setDefaultConfiguration(configuration);
    }


    public static Context getContext()
    {

        return mAppContext;
    }
}
