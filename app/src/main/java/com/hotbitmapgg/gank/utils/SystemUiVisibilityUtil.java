package com.hotbitmapgg.gank.utils;

import android.view.View;

/**
 * 系统NavBar工具类
 */
public class SystemUiVisibilityUtil {

  public static void addFlags(View view, int flags) {

    view.setSystemUiVisibility(view.getSystemUiVisibility() | flags);
  }


  public static void clearFlags(View view, int flags) {

    view.setSystemUiVisibility(view.getSystemUiVisibility() & ~flags);
  }


  public static boolean hasFlags(View view, int flags) {

    return (view.getSystemUiVisibility() & flags) == flags;
  }
}
