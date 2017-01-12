package com.hotbitmapgg.gank.notes.android_develop_art_explore;

import java.util.ArrayList;
import java.util.List;

/**
 * 安卓开发艺术探索读书笔记目录
 * 第1章 Activity的生命周期和启动模式
 * 第2章 IPC机制
 * 第3章 View事件体系
 * 第4章 View的工作原理
 * 第5章 理解RemoteViews
 * 第6章 Android的Drawable（未完成）
 * 第7章 动画深入分析
 * 第8章 理解Window和WindowManager（未学完）
 * 第9章 四大组件的工作过程（未学完）
 * 第10章 Android的消息机制
 * 第11章 线程与线程池
 * 第12章 Bitmap的加载和Cache（未完成）
 * 第13、14、15章 综合技术、JNI与NDK编程、Android性能优化（未完成）
 * <p/>
 * 文／HuDP（简书作者）
 * 原文链接：http://www.jianshu.com/p/eb3247fac29a
 * 著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。
 */
public class AndroidDevelopNoteContents {

  private List<AndroidDevelopNote> notes = new ArrayList<>();

  private List<String> names = new ArrayList<>();

  private List<String> urls = new ArrayList<>();

  /**
   * 笔记地址
   */
  public final static String NOTES_ONE_URL = "http://www.jianshu.com/p/ccf080affc59";

  public final static String NOTES_TWO_URL = "http://www.jianshu.com/p/2ab103e32456";

  public final static String NOTES_THREE_URL = "http://www.jianshu.com/p/7d2c88ca24fc";

  public final static String NOTES_FOUR_URL = "http://www.jianshu.com/p/75dc9e4b67ae";

  public final static String NOTES_FIVE_URL = "http://www.jianshu.com/p/23041852bd85";

  public final static String NOTES_SIX_URL = "http://www.jianshu.com/p/b27fb1007191";

  public final static String NOTES_SEVEN_URL = "http://www.jianshu.com/p/68c45fa0668d";

  public final static String NOTES_EIGHT_URL = "";

  public final static String NOTES_NINE_URL = "";

  public final static String NOTES_TEN_URL = "http://www.jianshu.com/p/48464c1d5788";

  public final static String NOTES_ELEVEN_URL = "http://www.jianshu.com/p/8265dba04f34";

  public final static String NOTES_TWELVE_URL = "http://www.jianshu.com/p/99c19709cd61";

  public final static String NOTES_THIRTEEN_URL = "http://www.jianshu.com/p/d71acdab6db3";

  public final static String NOTES_FIFTEEN_URL = "http://www.jianshu.com/p/6daa89cd91b8";

  /**
   * 笔记对应的章节名称
   */

  public final static String NOTES_ONE_CHAPTER = "Activity的生命周期和启动模式";

  public final static String NOTES_TWO_CHAPTER = "IPC通信机制";

  public final static String NOTES_THREE_CHAPTER = "View事件体系";

  public final static String NOTES_FOUR_CHAPTER = "View的工作原理";

  public final static String NOTES_FIVE_CHAPTER = "理解RemoteViews";

  public final static String NOTES_SIX_CHAPTER = "Android的Drawable";

  public final static String NOTES_SEVEN_CHAPTER = "动画深入分析";

  public final static String NOTES_EIGHT_CHAPTER = "理解Window和WindowManager（未学完）";

  public final static String NOTES_NINE_CHAPTER = "四大组件的工作过程（未学完）";

  public final static String NOTES_TEN_CHAPTER = "Android的消息机制";

  public final static String NOTES_ELEVEN_CHAPTER = "线程与线程池";

  public final static String NOTES_TWELVE_CHAPTER = "Bitmap的加载和Cache";

  public final static String NOTES_THIRTEEN_CHAPTER = "综合技术、JNI与NDK编程";

  public final static String NOTES_FIFTEEN_CHAPTER = "Android性能优化";


  public List<AndroidDevelopNote> fillData() {

    //添加章节目录
    names.add(NOTES_ONE_CHAPTER);
    names.add(NOTES_TWO_CHAPTER);
    names.add(NOTES_THREE_CHAPTER);
    names.add(NOTES_FOUR_CHAPTER);
    names.add(NOTES_FIVE_CHAPTER);
    names.add(NOTES_SIX_CHAPTER);
    names.add(NOTES_SEVEN_CHAPTER);
    names.add(NOTES_EIGHT_CHAPTER);
    names.add(NOTES_NINE_CHAPTER);
    names.add(NOTES_TEN_CHAPTER);
    names.add(NOTES_ELEVEN_CHAPTER);
    names.add(NOTES_TWELVE_CHAPTER);
    names.add(NOTES_THIRTEEN_CHAPTER);
    names.add(NOTES_FIFTEEN_CHAPTER);

    //添加URl
    urls.add(NOTES_ONE_URL);
    urls.add(NOTES_TWO_URL);
    urls.add(NOTES_THREE_URL);
    urls.add(NOTES_FOUR_URL);
    urls.add(NOTES_FIVE_URL);
    urls.add(NOTES_SIX_URL);
    urls.add(NOTES_SEVEN_URL);
    urls.add(NOTES_EIGHT_URL);
    urls.add(NOTES_NINE_URL);
    urls.add(NOTES_TEN_URL);
    urls.add(NOTES_ELEVEN_URL);
    urls.add(NOTES_TWELVE_URL);
    urls.add(NOTES_THIRTEEN_URL);
    urls.add(NOTES_FIFTEEN_URL);

    // 添加到数据集中
    AndroidDevelopNote mNote;
    int size = names.size();
    for (int i = 0; i < size; i++) {
      mNote = new AndroidDevelopNote();
      mNote.name = names.get(i);
      mNote.url = urls.get(i);

      notes.add(mNote);
    }

    return notes;
  }
}
