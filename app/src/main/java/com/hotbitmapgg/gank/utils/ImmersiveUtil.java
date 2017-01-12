package com.hotbitmapgg.gank.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;

/**
 * 控件NavBar显示隐藏的工具类
 */
public class ImmersiveUtil {

  public static void enter(Activity activity) {

    if (Build.VERSION.SDK_INT >= 19) {
      ImmersiveUtil19.enter(activity.getWindow().getDecorView());
    }
  }


  public static void exit(Activity activity) {

    if (Build.VERSION.SDK_INT >= 19) {
      ImmersiveUtil19.exit(activity.getWindow().getDecorView());
    }
  }


  @TargetApi(19)
  private static class ImmersiveUtil19 {

    private static final int FLAG_IMMERSIVE = View.SYSTEM_UI_FLAG_IMMERSIVE
        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        | View.SYSTEM_UI_FLAG_FULLSCREEN;


    public static void enter(View decor) {

      SystemUiVisibilityUtil.addFlags(decor, FLAG_IMMERSIVE);
    }


    public static void exit(View decor) {

      SystemUiVisibilityUtil.clearFlags(decor, FLAG_IMMERSIVE);
    }
  }
}
