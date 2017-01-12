package com.hotbitmapgg.gank.notes.effective_java;

import java.util.ArrayList;
import java.util.List;

/**
 * EffectiveJava读书笔记
 * <p/>
 * 数据来自CSND博客,作者:chjttony
 * 1.避免创建不必要的对象
 * 2.内存泄漏
 * 3.覆盖equals方法的通用约定
 * 4.泛型
 * 5.枚举
 * 6.方法重载
 * 7.异常
 * 8.线程安全级别和延迟初始化
 * 9.线程并发
 * 10.序列化
 * <p/>
 * 原地址:http://blog.csdn.net/chjttony/article/category/1311991
 */
public class EffectiveJavaContents {

  private List<EffectiveJavaNote> notes = new ArrayList<>();

  private List<String> chapters = new ArrayList<>();

  private List<String> urls = new ArrayList<>();

  /**
   * 笔记地址
   */
  public final static String NOTES_ONE_URL
      = "http://blog.csdn.net/chjttony/article/details/7484899";

  public final static String NOTES_TWO_URL
      = "http://blog.csdn.net/chjttony/article/details/7484902";

  public final static String NOTES_THREE_URL
      = "http://blog.csdn.net/chjttony/article/details/8433070";

  public final static String NOTES_FOUR_URL
      = "http://blog.csdn.net/chjttony/article/details/8455323";

  public final static String NOTES_FIVE_URL
      = "http://blog.csdn.net/chjttony/article/details/8465426";

  public final static String NOTES_SIX_URL
      = "http://blog.csdn.net/chjttony/article/details/8472603";

  public final static String NOTES_SEVEN_URL
      = "http://blog.csdn.net/chjttony/article/details/8494547";

  public final static String NOTES_EIGHT_URL
      = "http://blog.csdn.net/chjttony/article/details/8516876";

  public final static String NOTES_NINE_URL
      = "http://blog.csdn.net/chjttony/article/details/8529814";

  public final static String NOTES_TEN_URL
      = "http://blog.csdn.net/chjttony/article/details/8556476";

  /**
   * 笔记对应的章节名称
   */

  public final static String NOTES_ONE_CHAPTER = "避免创建不必要的对象";

  public final static String NOTES_TWO_CHAPTER = "内存泄漏";

  public final static String NOTES_THREE_CHAPTER = "覆盖equals方法的通用约定";

  public final static String NOTES_FOUR_CHAPTER = "泛型";

  public final static String NOTES_FIVE_CHAPTER = "枚举";

  public final static String NOTES_SIX_CHAPTER = "方法重载";

  public final static String NOTES_SEVEN_CHAPTER = "异常";

  public final static String NOTES_EIGHT_CHAPTER = "线程安全级别和延迟初始化";

  public final static String NOTES_NINE_CHAPTER = "线程并发";

  public final static String NOTES_TEN_CHAPTER = "序列化";


  public List<EffectiveJavaNote> fillData() {

    //添加章节目录
    chapters.add(NOTES_ONE_CHAPTER);
    chapters.add(NOTES_TWO_CHAPTER);
    chapters.add(NOTES_THREE_CHAPTER);
    chapters.add(NOTES_FOUR_CHAPTER);
    chapters.add(NOTES_FIVE_CHAPTER);
    chapters.add(NOTES_SIX_CHAPTER);
    chapters.add(NOTES_SEVEN_CHAPTER);
    chapters.add(NOTES_EIGHT_CHAPTER);
    chapters.add(NOTES_NINE_CHAPTER);
    chapters.add(NOTES_TEN_CHAPTER);

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

    // 添加到数据集中
    EffectiveJavaNote mNote;
    int size = chapters.size();
    for (int i = 0; i < size; i++) {
      mNote = new EffectiveJavaNote();
      mNote.chapter = chapters.get(i);
      mNote.url = urls.get(i);

      notes.add(mNote);
    }

    return notes;
  }
}
