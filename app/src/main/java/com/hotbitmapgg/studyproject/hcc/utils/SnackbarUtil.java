package com.hotbitmapgg.studyproject.hcc.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by hcc on 16/5/27 08:44
 * 100332338@qq.com
 */
public class SnackbarUtil
{

    public static void showMessage(View view, String text)
    {

        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }
}
