package com.hotbitmapgg.studyproject.hcc.base;

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
    }


    public static Context getContext()
    {
        return mAppContext;
    }

}
