package com.hotbitmapgg.gank.model;

import java.util.List;

/**
 * Created by hcc on 16/7/6 15:38
 * 100332338@qq.com
 */
public class GankDayInfo {

  public Result results;

  public class Result {

    public List<Gank.GankInfo> Android;

    public List<Gank.GankInfo> iOS;

    public List<Gank.GankInfo> 休息视频;

    public List<Gank.GankInfo> 拓展资源;

    public List<Gank.GankInfo> 瞎推荐;

    public List<Gank.GankInfo> 福利;
  }
}
