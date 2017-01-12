package com.hotbitmapgg.gank.notes.learning_notes;

import com.hotbitmapgg.gank.utils.LogUtil;
import com.hotbitmapgg.gank.config.ConstantUtil;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hcc on 16/7/30 14:43
 * 100332338@qq.com
 * <p/>
 * 来自Github：https://github.com/GeniusVJR/LearningNotes 安卓面试笔记整理
 */
public class LearningNotesContents {

  /**
   * 第一部分 安卓相关知识
   */

  //安卓相关知识  url
  private String ANDROID_PART_1
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%9F%BA%E7%A1%80%E7%9F%A5%E8%AF%86.md";

  private String ANDROID_PART_2
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md";

  private String ANDROID_PART_3
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Handler%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E5%88%86%E6%9E%90%E5%8F%8A%E8%A7%A3%E5%86%B3.md";

  private String ANDROID_PART_4
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/%E7%BA%BF%E7%A8%8B%E9%80%9A%E4%BF%A1%E5%9F%BA%E7%A1%80%E6%B5%81%E7%A8%8B%E5%88%86%E6%9E%90.md";

  private String ANDROID_PART_5
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96.md";

  private String ANDROID_PART_6
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Listview%E8%AF%A6%E8%A7%A3.md";

  private String ANDROID_PART_7
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Recyclerview%E5%92%8CListview%E7%9A%84%E5%BC%82%E5%90%8C.md";

  private String ANDROID_PART_8
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Asynctask%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90.md";

  private String ANDROID_PART_9
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/%E6%8F%92%E4%BB%B6%E5%8C%96%E6%8A%80%E6%9C%AF%E5%AD%A6%E4%B9%A0.md";

  private String ANDROID_PART_10
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%8E%A7%E4%BB%B6.md";

  private String ANDROID_PART_11 = "http://www.jianshu.com/p/e99b5e8bd67b";

  private String ANDROID_PART_12
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/ANR%E9%97%AE%E9%A2%98.md";

  private String ANDROID_PART_13
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Art%E5%92%8CDalvik%E5%8C%BA%E5%88%AB.md";

  private String ANDROID_PART_14
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%85%B3%E4%BA%8Eoom%E7%9A%84%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88.md";

  private String ANDROID_PART_15
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Fragment.md";

  private String ANDROID_PART_16 = "https://github.com/xxv/android-lifecycle";

  private String ANDROID_PART_17
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/SurfaceView.md";

  private String ANDROID_PART_18
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%87%A0%E7%A7%8D%E8%BF%9B%E7%A8%8B.md";

  private String ANDROID_PART_19
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/APP%E5%90%AF%E5%8A%A8%E8%BF%87%E7%A8%8B.md";

  private String ANDROID_PART_20
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%9B%BE%E7%89%87%E4%B8%AD%E7%9A%84%E4%B8%89%E7%BA%A7%E7%BC%93%E5%AD%98.md";

  private String ANDROID_PART_21
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Bitmap%E7%9A%84%E5%88%86%E6%9E%90%E4%B8%8E%E4%BD%BF%E7%94%A8.md";

  private String ANDROID_PART_22
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/%E7%83%AD%E4%BF%AE%E5%A4%8D%E6%8A%80%E6%9C%AF.md";

  private String ANDROID_PART_23
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/AIDL.md";

  private String ANDROID_PART_24
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Binder%E6%9C%BA%E5%88%B6.md";

  private String ANDROID_PART_25
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Zygote%E5%92%8CSystem%E8%BF%9B%E7%A8%8B%E7%9A%84%E5%90%AF%E5%8A%A8%E8%BF%87%E7%A8%8B.md";

  private String ANDROID_PART_26
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/MVC%2CMVP%2CMVVM%E7%9A%84%E5%8C%BA%E5%88%AB.md";

  private String ANDROID_PART_27
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/MVP.md";

  private String ANDROID_PART_28
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%BC%80%E6%9C%BA%E8%BF%87%E7%A8%8B.md";

  private String ANDROID_PART_29 = "http://www.jianshu.com/p/c1a3a881a144";

  private String ANDROID_PART_30 = "http://frodoking.github.io/2015/10/10/android-glide/";

  private String ANDROID_PART_31
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/EventBus%E7%94%A8%E6%B3%95%E8%AF%A6%E8%A7%A3.md";

  private String ANDROID_PART_32 = "http://p.codekk.com/blogs/detail/54cfab086c4761e5001b2538";

  private String ANDROID_PART_33 = "http://www.open-open.com/lib/view/open1438065400878.html";

  private String ANDROID_PART_34
      = "http://www.jcodecraeer.com/a/anzhuokaifa/developer/2015/0606/3005.html";

  private String ANDROID_PART_35 = "http://gank.io/post/560e15be2dca930e00da1083";

  private String ANDROID_PART_36 = "http://wuxiaolong.me/categories/Gradle/";

  private String ANDROID_PART_37
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Git%E6%93%8D%E4%BD%9C.md";

  //安卓相关知识  title
  private String ANDROID_TITLE_1 = "安卓基础知识";

  private String ANDROID_TITLE_2 = "安卓内存泄露总结";

  private String ANDROID_TITLE_3 = "Handler内存泄露分析以及解决方案";

  private String ANDROID_TITLE_4 = "Handler,Looper,Message,MessageQueue的基础流程分析";

  private String ANDROID_TITLE_5 = "安卓性能优化";

  private String ANDROID_TITLE_6 = "ListView详解";

  private String ANDROID_TITLE_7 = "RecycleView与ListView的异同";

  private String ANDROID_TITLE_8 = "AsyncTask源码分析";

  private String ANDROID_TITLE_9 = "插件化技术";

  private String ANDROID_TITLE_10 = "自定义控件";

  private String ANDROID_TITLE_11 = "事件分发机制";

  private String ANDROID_TITLE_12 = "ANR问题";

  private String ANDROID_TITLE_13 = "Art和Dalvik的区别";

  private String ANDROID_TITLE_14 = "安卓关于OOM的解决方案";

  private String ANDROID_TITLE_15 = "Fragment";

  private String ANDROID_TITLE_16 = "Activity&Fragment";

  private String ANDROID_TITLE_17 = "SurfaceView";

  private String ANDROID_TITLE_18 = "安卓进程相关";

  private String ANDROID_TITLE_19 = "App启动过程";

  private String ANDROID_TITLE_20 = "图片三级缓存";

  private String ANDROID_TITLE_21 = "Bitmap分析以及使用";

  private String ANDROID_TITLE_22 = "热修复的原理";

  private String ANDROID_TITLE_23 = "AIDL";

  private String ANDROID_TITLE_24 = "Binder机制";

  private String ANDROID_TITLE_25 = "Zygote和System进程启动过程";

  private String ANDROID_TITLE_26 = "安卓中的MVC,MVP,MVVM";

  private String ANDROID_TITLE_27 = "MVP";

  private String ANDROID_TITLE_28 = "安卓开机过程";

  private String ANDROID_TITLE_29 = "Retrofit源码分析";

  private String ANDROID_TITLE_30 = "Glide源码分析";

  private String ANDROID_TITLE_31 = "EventBus用法详解";

  private String ANDROID_TITLE_32 = "EventBus源码分析";

  private String ANDROID_TITLE_33 = "安卓ORM框架之GreenDao使用心得";

  private String ANDROID_TITLE_34 = "DataBinding数据绑定用户指南";

  private String ANDROID_TITLE_35 = "RxJava";

  private String ANDROID_TITLE_36 = "Gradle";

  private String ANDROID_TITLE_37 = "Git操作";


  /**
   * 保存安卓相关数据到数据库中
   */
  public void fillAndroidData() {

    Observable.create(new Observable.OnSubscribe<List<LearningNotes>>() {

      @Override
      public void call(Subscriber<? super List<LearningNotes>> subscriber) {

        List<LearningNotes> learningNotes = new ArrayList<>();

        List<String> chapters = new ArrayList<>();

        List<String> urls = new ArrayList<>();

        chapters.add(ANDROID_TITLE_1);
        chapters.add(ANDROID_TITLE_2);
        chapters.add(ANDROID_TITLE_3);
        chapters.add(ANDROID_TITLE_4);
        chapters.add(ANDROID_TITLE_5);
        chapters.add(ANDROID_TITLE_6);
        chapters.add(ANDROID_TITLE_7);
        chapters.add(ANDROID_TITLE_8);
        chapters.add(ANDROID_TITLE_9);
        chapters.add(ANDROID_TITLE_10);
        chapters.add(ANDROID_TITLE_11);
        chapters.add(ANDROID_TITLE_12);
        chapters.add(ANDROID_TITLE_13);
        chapters.add(ANDROID_TITLE_14);
        chapters.add(ANDROID_TITLE_15);
        chapters.add(ANDROID_TITLE_16);
        chapters.add(ANDROID_TITLE_17);
        chapters.add(ANDROID_TITLE_18);
        chapters.add(ANDROID_TITLE_19);
        chapters.add(ANDROID_TITLE_20);
        chapters.add(ANDROID_TITLE_21);
        chapters.add(ANDROID_TITLE_22);
        chapters.add(ANDROID_TITLE_23);
        chapters.add(ANDROID_TITLE_24);
        chapters.add(ANDROID_TITLE_25);
        chapters.add(ANDROID_TITLE_26);
        chapters.add(ANDROID_TITLE_27);
        chapters.add(ANDROID_TITLE_28);
        chapters.add(ANDROID_TITLE_29);
        chapters.add(ANDROID_TITLE_30);
        chapters.add(ANDROID_TITLE_31);
        chapters.add(ANDROID_TITLE_32);
        chapters.add(ANDROID_TITLE_33);
        chapters.add(ANDROID_TITLE_34);
        chapters.add(ANDROID_TITLE_35);
        chapters.add(ANDROID_TITLE_36);
        chapters.add(ANDROID_TITLE_37);

        urls.add(ANDROID_PART_1);
        urls.add(ANDROID_PART_2);
        urls.add(ANDROID_PART_3);
        urls.add(ANDROID_PART_4);
        urls.add(ANDROID_PART_5);
        urls.add(ANDROID_PART_6);
        urls.add(ANDROID_PART_7);
        urls.add(ANDROID_PART_8);
        urls.add(ANDROID_PART_9);
        urls.add(ANDROID_PART_10);
        urls.add(ANDROID_PART_11);
        urls.add(ANDROID_PART_12);
        urls.add(ANDROID_PART_13);
        urls.add(ANDROID_PART_14);
        urls.add(ANDROID_PART_15);
        urls.add(ANDROID_PART_16);
        urls.add(ANDROID_PART_17);
        urls.add(ANDROID_PART_18);
        urls.add(ANDROID_PART_19);
        urls.add(ANDROID_PART_20);
        urls.add(ANDROID_PART_21);
        urls.add(ANDROID_PART_22);
        urls.add(ANDROID_PART_23);
        urls.add(ANDROID_PART_24);
        urls.add(ANDROID_PART_25);
        urls.add(ANDROID_PART_26);
        urls.add(ANDROID_PART_27);
        urls.add(ANDROID_PART_28);
        urls.add(ANDROID_PART_29);
        urls.add(ANDROID_PART_30);
        urls.add(ANDROID_PART_31);
        urls.add(ANDROID_PART_32);
        urls.add(ANDROID_PART_33);
        urls.add(ANDROID_PART_34);
        urls.add(ANDROID_PART_35);
        urls.add(ANDROID_PART_36);
        urls.add(ANDROID_PART_37);

        LearningNotes learningNote;
        int size = chapters.size();
        for (int i = 0; i < size; i++) {
          learningNote = new LearningNotes();
          learningNote.setChapter(chapters.get(i));
          learningNote.setUrl(urls.get(i));
          learningNote.setType(ConstantUtil.NOTES_TYPE_ANDROID);

          learningNotes.add(learningNote);
        }

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(learningNotes);
        realm.commitTransaction();
        realm.close();

        subscriber.onNext(learningNotes);
      }
    })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<List<LearningNotes>>() {

          @Override
          public void call(List<LearningNotes> learningNotes) {

            LogUtil.all(learningNotes.size() + "@@");
          }
        }, new Action1<Throwable>() {

          @Override
          public void call(Throwable throwable) {

            LogUtil.all("数据存储失败" + throwable.getMessage());
          }
        }, new Action0() {

          @Override
          public void call() {

            LogUtil.all("数据存储完成");
          }
        });
  }


  /**
   * 第二部分 设计模式
   */

  //设计模式相关 url

  private final static String DESIGN_PATTERN_1
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/DesignPattern/%E5%B8%B8%E8%A7%81%E7%9A%84%E9%9D%A2%E5%90%91%E5%AF%B9%E8%B1%A1%E8%AE%BE%E8%AE%A1%E5%8E%9F%E5%88%99.md";

  private final static String DESIGN_PATTERN_2
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/DesignPattern/%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F.md";

  private final static String DESIGN_PATTERN_3
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/DesignPattern/Builder%E6%A8%A1%E5%BC%8F.md";

  private final static String DESIGN_PATTERN_4
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/DesignPattern/%E5%8E%9F%E5%9E%8B%E6%A8%A1%E5%BC%8F.md";

  private final static String DESIGN_PATTERN_5
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/DesignPattern/%E7%AE%80%E5%8D%95%E5%B7%A5%E5%8E%82.md";

  private final static String DESIGN_PATTERN_6
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/DesignPattern/%E7%AD%96%E7%95%A5%E6%A8%A1%E5%BC%8F.md";

  private final static String DESIGN_PATTERN_7
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/DesignPattern/%E8%B4%A3%E4%BB%BB%E9%93%BE%E6%A8%A1%E5%BC%8F.md";

  private final static String DESIGN_PATTERN_8
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/DesignPattern/%E8%A7%82%E5%AF%9F%E8%80%85%E6%A8%A1%E5%BC%8F.md";

  private final static String DESIGN_PATTERN_9
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/DesignPattern/%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F.md";

  private final static String DESIGN_PATTERN_10
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/DesignPattern/%E9%80%82%E9%85%8D%E5%99%A8%E6%A8%A1%E5%BC%8F.md";

  private final static String DESIGN_PATTERN_11
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/DesignPattern/%E5%A4%96%E8%A7%82%E6%A8%A1%E5%BC%8F.md";

  //设计模式相关 title
  private final static String DESIGN_PATTERN_TITLE_1 = "面向对象六大原则";

  private final static String DESIGN_PATTERN_TITLE_2 = "单例模式";

  private final static String DESIGN_PATTERN_TITLE_3 = "Builder模式";

  private final static String DESIGN_PATTERN_TITLE_4 = "原型模式";

  private final static String DESIGN_PATTERN_TITLE_5 = "简单工厂";

  private final static String DESIGN_PATTERN_TITLE_6 = "策略模式";

  private final static String DESIGN_PATTERN_TITLE_7 = "责任链模式";

  private final static String DESIGN_PATTERN_TITLE_8 = "观察者模式";

  private final static String DESIGN_PATTERN_TITLE_9 = "代理模式";

  private final static String DESIGN_PATTERN_TITLE_10 = "适配器模式";

  private final static String DESIGN_PATTERN_TITLE_11 = "外观模式";


  /**
   * 保存设计模式相关数据到数据库中
   */
  public void fillDesignPatternData() {

    Observable.create(new Observable.OnSubscribe<List<LearningNotes>>() {

      @Override
      public void call(Subscriber<? super List<LearningNotes>> subscriber) {

        List<LearningNotes> learningNotes = new ArrayList<>();

        List<String> chapters = new ArrayList<>();

        List<String> urls = new ArrayList<>();

        chapters.add(DESIGN_PATTERN_TITLE_1);
        chapters.add(DESIGN_PATTERN_TITLE_2);
        chapters.add(DESIGN_PATTERN_TITLE_3);
        chapters.add(DESIGN_PATTERN_TITLE_4);
        chapters.add(DESIGN_PATTERN_TITLE_5);
        chapters.add(DESIGN_PATTERN_TITLE_6);
        chapters.add(DESIGN_PATTERN_TITLE_7);
        chapters.add(DESIGN_PATTERN_TITLE_8);
        chapters.add(DESIGN_PATTERN_TITLE_9);
        chapters.add(DESIGN_PATTERN_TITLE_10);
        chapters.add(DESIGN_PATTERN_TITLE_11);

        urls.add(DESIGN_PATTERN_1);
        urls.add(DESIGN_PATTERN_2);
        urls.add(DESIGN_PATTERN_3);
        urls.add(DESIGN_PATTERN_4);
        urls.add(DESIGN_PATTERN_5);
        urls.add(DESIGN_PATTERN_6);
        urls.add(DESIGN_PATTERN_7);
        urls.add(DESIGN_PATTERN_8);
        urls.add(DESIGN_PATTERN_9);
        urls.add(DESIGN_PATTERN_10);
        urls.add(DESIGN_PATTERN_11);

        LearningNotes learningNote;
        int size = chapters.size();
        for (int i = 0; i < size; i++) {
          learningNote = new LearningNotes();
          learningNote.setChapter(chapters.get(i));
          learningNote.setUrl(urls.get(i));
          learningNote.setType(ConstantUtil.NOTES_TYPE_DESIGN_PATTERN);

          learningNotes.add(learningNote);
        }

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(learningNotes);
        realm.commitTransaction();
        realm.close();

        subscriber.onNext(learningNotes);
      }
    })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<List<LearningNotes>>() {

          @Override
          public void call(List<LearningNotes> learningNotes) {

            LogUtil.all(learningNotes.size() + "@@");
          }
        }, new Action1<Throwable>() {

          @Override
          public void call(Throwable throwable) {

            LogUtil.all("数据存储失败" + throwable.getMessage());
          }
        }, new Action0() {

          @Override
          public void call() {

            LogUtil.all("数据存储完成");
          }
        });
  }


  /**
   * 第三部分 java基础
   */

  // java基础
  private final static String JAVA_BASE_URL_1
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/Java%E5%9F%BA%E7%A1%80%E7%9F%A5%E8%AF%86.md";

  private final static String JAVA_BASE_URL_2
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/Java%E4%B8%AD%E7%9A%84%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F.md";

  private final static String JAVA_BASE_URL_3
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/String%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90.md";

  private final static String JAVA_BASE_URL_4
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/Java%E9%9B%86%E5%90%88%E6%A1%86%E6%9E%B6.md";

  private final static String JAVA_BASE_URL_5
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/ArrayList%E6%BA%90%E7%A0%81%E5%89%96%E6%9E%90.md";

  private final static String JAVA_BASE_URL_6
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/LinkedList%E6%BA%90%E7%A0%81%E5%89%96%E6%9E%90.md";

  private final static String JAVA_BASE_URL_7
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/Vector%E6%BA%90%E7%A0%81%E5%89%96%E6%9E%90.md";

  private final static String JAVA_BASE_URL_8
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/HashMap%E6%BA%90%E7%A0%81%E5%89%96%E6%9E%90.md";

  private final static String JAVA_BASE_URL_9
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/HashTable%E6%BA%90%E7%A0%81%E5%89%96%E6%9E%90.md";

  private final static String JAVA_BASE_URL_10
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/LinkedHashMap%E6%BA%90%E7%A0%81%E5%89%96%E6%9E%90.md";

  private final static String JAVA_BASE_TITLE_1 = "JAVA基础知识";

  private final static String JAVA_BASE_TITLE_2 = "JAVA中的内存泄露";

  private final static String JAVA_BASE_TITLE_3 = "String源码分析";

  private final static String JAVA_BASE_TITLE_4 = "JAVA集合框架";

  private final static String JAVA_BASE_TITLE_5 = "ArrayList源码分析";

  private final static String JAVA_BASE_TITLE_6 = "LinkedList源码分析";

  private final static String JAVA_BASE_TITLE_7 = "Vector源码分析";

  private final static String JAVA_BASE_TITLE_8 = "HashMap源码分析";

  private final static String JAVA_BASE_TITLE_9 = "HashTable源码分析";

  private final static String JAVA_BASE_TITLE_10 = "LinkedHashMap源码分析";

  //java虚拟机
  private final static String JVM_URL_1
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JVM/JVM.md";

  private final static String JVM_URL_2
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JVM/JVM%E7%B1%BB%E5%8A%A0%E8%BD%BD%E6%9C%BA%E5%88%B6.md";

  private final static String JVM_URL_3
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JVM/Java%E5%86%85%E5%AD%98%E5%8C%BA%E5%9F%9F%E4%B8%8E%E5%86%85%E5%AD%98%E6%BA%A2%E5%87%BA.md";

  private final static String JVM_URL_4
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JVM/%E5%9E%83%E5%9C%BE%E5%9B%9E%E6%94%B6%E7%AE%97%E6%B3%95.md";

  private final static String JVM_TITLE_1 = "JVM基础知识";

  private final static String JVM_TITLE_2 = "JVM类加载机制";

  private final static String JVM_TITLE_3 = "JVM内存区域与内存溢出";

  private final static String JVM_TITLE_4 = "垃圾回收算法";

  // java并发
  private final static String JAVA_CONCURRENT_URL_1
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaConcurrent/Java%E5%B9%B6%E5%8F%91%E5%9F%BA%E7%A1%80%E7%9F%A5%E8%AF%86.md";

  private final static String JAVA_CONCURRENT_URL_2
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaConcurrent/%E7%94%9F%E4%BA%A7%E8%80%85%E5%92%8C%E6%B6%88%E8%B4%B9%E8%80%85%E9%97%AE%E9%A2%98.md";

  private final static String JAVA_CONCURRENT_URL_3
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaConcurrent/Thread%E5%92%8CRunnable%E5%AE%9E%E7%8E%B0%E5%A4%9A%E7%BA%BF%E7%A8%8B%E7%9A%84%E5%8C%BA%E5%88%AB.md";

  private final static String JAVA_CONCURRENT_URL_4
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaConcurrent/%E7%BA%BF%E7%A8%8B%E4%B8%AD%E6%96%AD.md";

  private final static String JAVA_CONCURRENT_URL_5
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaConcurrent/%E5%AE%88%E6%8A%A4%E7%BA%BF%E7%A8%8B%E4%B8%8E%E9%98%BB%E5%A1%9E%E7%BA%BF%E7%A8%8B.md";

  private final static String JAVA_CONCURRENT_URL_6
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaConcurrent/%E5%AE%88%E6%8A%A4%E7%BA%BF%E7%A8%8B%E4%B8%8E%E9%98%BB%E5%A1%9E%E7%BA%BF%E7%A8%8B.md";

  private final static String JAVA_CONCURRENT_URL_7
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaConcurrent/Synchronized.md";

  private final static String JAVA_CONCURRENT_URL_8
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaConcurrent/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E7%8E%AF%E5%A2%83%E4%B8%AD%E5%AE%89%E5%85%A8%E4%BD%BF%E7%94%A8%E9%9B%86%E5%90%88API.md";

  private final static String JAVA_CONCURRENT_URL_9
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaConcurrent/%E5%AE%9E%E7%8E%B0%E5%86%85%E5%AD%98%E5%8F%AF%E8%A7%81%E7%9A%84%E4%B8%A4%E7%A7%8D%E6%96%B9%E6%B3%95%E6%AF%94%E8%BE%83%EF%BC%9A%E5%8A%A0%E9%94%81%E5%92%8Cvolatile%E5%8F%98%E9%87%8F.md";

  private final static String JAVA_CONCURRENT_URL_10
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaConcurrent/%E5%8F%AF%E9%87%8D%E5%85%A5%E5%86%85%E7%BD%AE%E9%94%81.md";

  private final static String JAVA_CONCURRENT_URL_11
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaConcurrent/NIO.md";

  private final static String JAVA_CONCURRENT_TITLE_1 = "JAVA并发知识基础";

  private final static String JAVA_CONCURRENT_TITLE_2 = "生产者消费者问题";

  private final static String JAVA_CONCURRENT_TITLE_3 = "Thread与Runnable实现多线程的区别";

  private final static String JAVA_CONCURRENT_TITLE_4 = "线程中断";

  private final static String JAVA_CONCURRENT_TITLE_5 = "守护线程与阻塞线程";

  private final static String JAVA_CONCURRENT_TITLE_6 = "synchronized";

  private final static String JAVA_CONCURRENT_TITLE_7 = "多线程环境中安全使用集合API";

  private final static String JAVA_CONCURRENT_TITLE_8 = "实现内存可见的两种方法:加锁与volatle变量";

  private final static String JAVA_CONCURRENT_TITLE_9 = "死锁";

  private final static String JAVA_CONCURRENT_TITLE_10 = "可重入内置锁";

  private final static String JAVA_CONCURRENT_TITLE_11 = "NIO";


  /**
   * 保存Java相关数据到数据库中
   */
  public void fillJavaData() {

    Observable.create(new Observable.OnSubscribe<List<LearningNotes>>() {

      @Override
      public void call(Subscriber<? super List<LearningNotes>> subscriber) {

        List<LearningNotes> learningNotes = new ArrayList<>();

        List<String> chapters = new ArrayList<>();

        List<String> urls = new ArrayList<>();

        chapters.add(JAVA_BASE_TITLE_1);
        chapters.add(JAVA_BASE_TITLE_2);
        chapters.add(JAVA_BASE_TITLE_3);
        chapters.add(JAVA_BASE_TITLE_4);
        chapters.add(JAVA_BASE_TITLE_5);
        chapters.add(JAVA_BASE_TITLE_6);
        chapters.add(JAVA_BASE_TITLE_7);
        chapters.add(JAVA_BASE_TITLE_8);
        chapters.add(JAVA_BASE_TITLE_9);
        chapters.add(JAVA_BASE_TITLE_10);

        chapters.add(JVM_TITLE_1);
        chapters.add(JVM_TITLE_2);
        chapters.add(JVM_TITLE_3);
        chapters.add(JVM_TITLE_4);

        chapters.add(JAVA_CONCURRENT_TITLE_1);
        chapters.add(JAVA_CONCURRENT_TITLE_2);
        chapters.add(JAVA_CONCURRENT_TITLE_3);
        chapters.add(JAVA_CONCURRENT_TITLE_4);
        chapters.add(JAVA_CONCURRENT_TITLE_5);
        chapters.add(JAVA_CONCURRENT_TITLE_6);
        chapters.add(JAVA_CONCURRENT_TITLE_7);
        chapters.add(JAVA_CONCURRENT_TITLE_8);
        chapters.add(JAVA_CONCURRENT_TITLE_9);
        chapters.add(JAVA_CONCURRENT_TITLE_10);
        chapters.add(JAVA_CONCURRENT_TITLE_11);

        urls.add(JAVA_BASE_URL_1);
        urls.add(JAVA_BASE_URL_2);
        urls.add(JAVA_BASE_URL_3);
        urls.add(JAVA_BASE_URL_4);
        urls.add(JAVA_BASE_URL_5);
        urls.add(JAVA_BASE_URL_6);
        urls.add(JAVA_BASE_URL_7);
        urls.add(JAVA_BASE_URL_8);
        urls.add(JAVA_BASE_URL_9);
        urls.add(JAVA_BASE_URL_10);

        urls.add(JVM_URL_1);
        urls.add(JVM_URL_2);
        urls.add(JVM_URL_3);
        urls.add(JVM_URL_4);

        urls.add(JAVA_CONCURRENT_URL_1);
        urls.add(JAVA_CONCURRENT_URL_2);
        urls.add(JAVA_CONCURRENT_URL_3);
        urls.add(JAVA_CONCURRENT_URL_4);
        urls.add(JAVA_CONCURRENT_URL_5);
        urls.add(JAVA_CONCURRENT_URL_6);
        urls.add(JAVA_CONCURRENT_URL_7);
        urls.add(JAVA_CONCURRENT_URL_8);
        urls.add(JAVA_CONCURRENT_URL_9);
        urls.add(JAVA_CONCURRENT_URL_10);
        urls.add(JAVA_CONCURRENT_URL_11);

        LearningNotes learningNote;
        int size = chapters.size();
        for (int i = 0; i < size; i++) {
          learningNote = new LearningNotes();
          learningNote.setChapter(chapters.get(i));
          learningNote.setUrl(urls.get(i));
          learningNote.setType(ConstantUtil.NOTES_TYPE_JAVA);

          learningNotes.add(learningNote);
        }

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(learningNotes);
        realm.commitTransaction();
        realm.close();

        subscriber.onNext(learningNotes);
      }
    })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<List<LearningNotes>>() {

          @Override
          public void call(List<LearningNotes> learningNotes) {

            LogUtil.all(learningNotes.size() + "@@");
          }
        }, new Action1<Throwable>() {

          @Override
          public void call(Throwable throwable) {

            LogUtil.all("数据存储失败" + throwable.getMessage());
          }
        }, new Action0() {

          @Override
          public void call() {

            LogUtil.all("数据存储完成");
          }
        });
  }


  /**
   * 第四部分 数据结构  算法
   */

  private final static String DATA_STRUCTURE_URL_1
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/DataStructure/%E6%95%B0%E7%BB%84.md";

  private final static String DATA_STRUCTURE_URL_2
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/DataStructure/%E6%A0%88%E5%92%8C%E9%98%9F%E5%88%97.md";

  private final static String DATA_STRUCTURE_URL_3
      = "https://github.com/anAngryAnt/LearningNotes/blob/master/Part3/Algorithm/Sort/%E9%80%89%E6%8B%A9%E6%8E%92%E5%BA%8F.md";

  private final static String DATA_STRUCTURE_URL_4
      = "https://github.com/anAngryAnt/LearningNotes/blob/master/Part3/Algorithm/Sort/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F.md";

  private final static String DATA_STRUCTURE_URL_5
      = "https://github.com/anAngryAnt/LearningNotes/blob/master/Part3/Algorithm/Sort/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F.md";

  private final static String DATA_STRUCTURE_URL_6
      = "https://github.com/anAngryAnt/LearningNotes/blob/master/Part3/Algorithm/Sort/%E5%BD%92%E5%B9%B6%E6%8E%92%E5%BA%8F.md";

  private final static String DATA_STRUCTURE_URL_7
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/Algorithm/Lookup/%E9%A1%BA%E5%BA%8F%E6%9F%A5%E6%89%BE.md";

  private final static String DATA_STRUCTURE_URL_8
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/Algorithm/Lookup/%E6%8A%98%E5%8D%8A%E6%9F%A5%E6%89%BE.md";

  private final static String DATA_STRUCTURE_URL_9
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/Algorithm/%E5%89%91%E6%8C%87Offer/1.%E4%B8%83%E7%A7%8D%E6%96%B9%E5%BC%8F%E5%AE%9E%E7%8E%B0singleton%E6%A8%A1%E5%BC%8F.md";

  private final static String DATA_STRUCTURE_URL_10
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/Algorithm/%E5%89%91%E6%8C%87Offer/%E9%9D%A2%E8%AF%95%E9%A2%986%EF%BC%9A%E9%87%8D%E5%BB%BA%E4%BA%8C%E5%8F%89%E6%A0%91.md";

  private final static String DATA_STRUCTURE_URL_11
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/Algorithm/%E5%89%91%E6%8C%87Offer/%E9%9D%A2%E8%AF%95%E9%A2%9811%EF%BC%9A%E6%95%B0%E5%80%BC%E7%9A%84%E6%95%B4%E6%95%B0%E6%AC%A1%E6%96%B9.md";

  private final static String DATA_STRUCTURE_URL_12
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/Algorithm/%E5%89%91%E6%8C%87Offer/%E9%9D%A2%E8%AF%95%E9%A2%9844%EF%BC%9A%E6%89%91%E5%85%8B%E7%89%8C%E7%9A%84%E9%A1%BA%E5%AD%90.md";

  private final static String DATA_STRUCTURE_URL_13
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/Algorithm/%E5%89%91%E6%8C%87Offer/%E9%9D%A2%E8%AF%95%E9%A2%9845%EF%BC%9A%E5%9C%86%E5%9C%88%E4%B8%AD%E6%9C%80%E5%90%8E%E5%89%A9%E4%B8%8B%E7%9A%84%E6%95%B0%E5%AD%97.md";

  private final static String DATA_STRUCTURE_URL_14
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/Algorithm/LeetCode/two-sum.md";

  private final static String DATA_STRUCTURE_URL_15
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/Algorithm/%E7%A8%8B%E5%BA%8F%E5%91%98%E4%BB%A3%E7%A0%81%E9%9D%A2%E8%AF%95%E6%8C%87%E5%8D%97(%E5%B7%A6%E7%A8%8B%E4%BA%91)/1.%E8%AE%BE%E8%AE%A1%E4%B8%80%E4%B8%AA%E6%9C%89getMin%E5%8A%9F%E8%83%BD%E7%9A%84%E6%A0%88.md";

  private final static String DATA_STRUCTURE_URL_16
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/Algorithm/%E7%A8%8B%E5%BA%8F%E5%91%98%E4%BB%A3%E7%A0%81%E9%9D%A2%E8%AF%95%E6%8C%87%E5%8D%97(%E5%B7%A6%E7%A8%8B%E4%BA%91)/2.%E7%94%B1%E4%B8%A4%E4%B8%AA%E6%A0%88%E7%BB%84%E6%88%90%E7%9A%84%E9%98%9F%E5%88%97.md";

  private final static String DATA_STRUCTURE_URL_17
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/Algorithm/%E7%A8%8B%E5%BA%8F%E5%91%98%E4%BB%A3%E7%A0%81%E9%9D%A2%E8%AF%95%E6%8C%87%E5%8D%97(%E5%B7%A6%E7%A8%8B%E4%BA%91)/3.%E5%A6%82%E4%BD%95%E4%BB%85%E7%94%A8%E9%80%92%E5%BD%92%E5%87%BD%E6%95%B0%E5%92%8C%E6%A0%88%E6%93%8D%E4%BD%9C%E9%80%86%E5%BA%8F%E4%B8%80%E4%B8%AA%E6%A0%88.md";

  private final static String DATA_STRUCTURE_TITLE_1 = "数组";

  private final static String DATA_STRUCTURE_TITLE_2 = "栈和队列";

  private final static String DATA_STRUCTURE_TITLE_3 = "选择排序";

  private final static String DATA_STRUCTURE_TITLE_4 = "冒泡排序";

  private final static String DATA_STRUCTURE_TITLE_5 = "快速排序";

  private final static String DATA_STRUCTURE_TITLE_6 = "归并排序";

  private final static String DATA_STRUCTURE_TITLE_7 = "顺序查找";

  private final static String DATA_STRUCTURE_TITLE_8 = "拆分查找";

  private final static String DATA_STRUCTURE_TITLE_9 = "实现Singleton模式";

  private final static String DATA_STRUCTURE_TITLE_10 = "重建二叉树";

  private final static String DATA_STRUCTURE_TITLE_11 = "数值整数次方";

  private final static String DATA_STRUCTURE_TITLE_12 = "扑克牌的顺子";

  private final static String DATA_STRUCTURE_TITLE_13 = "圆圈中最后剩下的数字";

  private final static String DATA_STRUCTURE_TITLE_14 = "two-sum";

  private final static String DATA_STRUCTURE_TITLE_15 = "设计一个有getMin功能的栈";

  private final static String DATA_STRUCTURE_TITLE_16 = "由两个栈组成的队列";

  private final static String DATA_STRUCTURE_TITLE_17 = "如何仅用递归函数和栈操作逆序一个栈";


  /**
   * 保存数据结构与算法相关数据到数据库中
   */
  public void fillDataStructrueData() {

    Observable.create(new Observable.OnSubscribe<List<LearningNotes>>() {

      @Override
      public void call(Subscriber<? super List<LearningNotes>> subscriber) {

        List<LearningNotes> learningNotes = new ArrayList<>();

        List<String> chapters = new ArrayList<>();

        List<String> urls = new ArrayList<>();

        chapters.add(DATA_STRUCTURE_TITLE_1);
        chapters.add(DATA_STRUCTURE_TITLE_2);
        chapters.add(DATA_STRUCTURE_TITLE_3);
        chapters.add(DATA_STRUCTURE_TITLE_4);
        chapters.add(DATA_STRUCTURE_TITLE_5);
        chapters.add(DATA_STRUCTURE_TITLE_6);
        chapters.add(DATA_STRUCTURE_TITLE_7);
        chapters.add(DATA_STRUCTURE_TITLE_8);
        chapters.add(DATA_STRUCTURE_TITLE_9);
        chapters.add(DATA_STRUCTURE_TITLE_10);
        chapters.add(DATA_STRUCTURE_TITLE_11);
        chapters.add(DATA_STRUCTURE_TITLE_12);
        chapters.add(DATA_STRUCTURE_TITLE_13);
        chapters.add(DATA_STRUCTURE_TITLE_14);
        chapters.add(DATA_STRUCTURE_TITLE_15);
        chapters.add(DATA_STRUCTURE_TITLE_16);
        chapters.add(DATA_STRUCTURE_TITLE_17);

        urls.add(DATA_STRUCTURE_URL_1);
        urls.add(DATA_STRUCTURE_URL_2);
        urls.add(DATA_STRUCTURE_URL_3);
        urls.add(DATA_STRUCTURE_URL_4);
        urls.add(DATA_STRUCTURE_URL_5);
        urls.add(DATA_STRUCTURE_URL_6);
        urls.add(DATA_STRUCTURE_URL_7);
        urls.add(DATA_STRUCTURE_URL_8);
        urls.add(DATA_STRUCTURE_URL_9);
        urls.add(DATA_STRUCTURE_URL_10);
        urls.add(DATA_STRUCTURE_URL_11);
        urls.add(DATA_STRUCTURE_URL_12);
        urls.add(DATA_STRUCTURE_URL_13);
        urls.add(DATA_STRUCTURE_URL_14);
        urls.add(DATA_STRUCTURE_URL_15);
        urls.add(DATA_STRUCTURE_URL_16);
        urls.add(DATA_STRUCTURE_URL_17);

        LearningNotes learningNote;
        int size = chapters.size();
        for (int i = 0; i < size; i++) {
          learningNote = new LearningNotes();
          learningNote.setChapter(chapters.get(i));
          learningNote.setUrl(urls.get(i));
          learningNote.setType(ConstantUtil.NOTES_TYPE_DATA_STRUCTURE);

          learningNotes.add(learningNote);
        }

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(learningNotes);
        realm.commitTransaction();
        realm.close();

        subscriber.onNext(learningNotes);
      }
    })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<List<LearningNotes>>() {

          @Override
          public void call(List<LearningNotes> learningNotes) {

            LogUtil.all(learningNotes.size() + "@@");
          }
        }, new Action1<Throwable>() {

          @Override
          public void call(Throwable throwable) {

            LogUtil.all("数据存储失败" + throwable.getMessage());
          }
        }, new Action0() {

          @Override
          public void call() {

            LogUtil.all("数据存储完成");
          }
        });
  }


  /**
   * 第五部分 网络相关
   */

  private final static String NETWORK_URL_1
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part4/Network/TCP%E4%B8%8EUDP.md";

  private final static String NETWORK_URL_2
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part4/Network/Http%E5%8D%8F%E8%AE%AE.md";

  private final static String NETWORK_URL_3
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part4/Network/Socket.md";

  private final static String NETWORK_URL_4
      = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part4/OperatingSystem/Linux%E7%B3%BB%E7%BB%9F%E7%9A%84IPC.md";

  private final static String NETWORK_TITLE_1 = "TCP/UDP";

  private final static String NETWORK_TITLE_2 = "HTTP";

  private final static String NETWORK_TITLE_3 = "Socket";

  private final static String NETWORK_TITLE_4 = "Linux系统的IPC";


  /**
   * 保存数据结构与算法相关数据到数据库中
   */
  public void fillNetworkData() {

    Observable.create(new Observable.OnSubscribe<List<LearningNotes>>() {

      @Override
      public void call(Subscriber<? super List<LearningNotes>> subscriber) {

        List<LearningNotes> learningNotes = new ArrayList<>();

        List<String> chapters = new ArrayList<>();

        List<String> urls = new ArrayList<>();

        chapters.add(NETWORK_TITLE_1);
        chapters.add(NETWORK_TITLE_2);
        chapters.add(NETWORK_TITLE_3);
        chapters.add(NETWORK_TITLE_4);

        urls.add(NETWORK_URL_1);
        urls.add(NETWORK_URL_2);
        urls.add(NETWORK_URL_3);
        urls.add(NETWORK_URL_4);

        LearningNotes learningNote;
        int size = chapters.size();
        for (int i = 0; i < size; i++) {
          learningNote = new LearningNotes();
          learningNote.setChapter(chapters.get(i));
          learningNote.setUrl(urls.get(i));
          learningNote.setType(ConstantUtil.NOTES_TYPE_NETWORK);

          learningNotes.add(learningNote);
        }

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(learningNotes);
        realm.commitTransaction();
        realm.close();

        subscriber.onNext(learningNotes);
      }
    })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<List<LearningNotes>>() {

          @Override
          public void call(List<LearningNotes> learningNotes) {

            LogUtil.all(learningNotes.size() + "@@");
          }
        }, new Action1<Throwable>() {

          @Override
          public void call(Throwable throwable) {

            LogUtil.all("数据存储失败" + throwable.getMessage());
          }
        }, new Action0() {

          @Override
          public void call() {

            LogUtil.all("数据存储完成");
          }
        });
  }
}
