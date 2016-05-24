package com.hotbitmapgg.studyproject.hcc.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

/**
 * 震动工具类
 */
public class VibratorUtil
{

    public static void vibrator(Activity activity, long time)
    {
        Vibrator mVibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        mVibrator.vibrate(time);
    }
}
