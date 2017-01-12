package com.hotbitmapgg.gank.notes.ldtk_blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcc on 16/7/31 13:11
 * 100332338@qq.com
 * <p/>
 * ldtk的自定义控件相关博客
 * 地址:https://github.com/Idtk/Blog
 */
public class LdtkBlogContents {

  private List<LdtkBlog> ldtkBlogs = new ArrayList<>();

  private List<String> titles = new ArrayList<>();

  private List<String> urls = new ArrayList<>();

  public final static String LDTK_BLOG_TITLE_1 = "Android坐标系与View的绘制流程";

  public final static String LDTK_BLOG_TITLE_2 = "Canvas与ValueAnimator";

  public final static String LDTK_BLOG_TITLE_3 = "多行文本居中";

  public final static String LDTK_BLOG_TITLE_4 = "Path图形与逻辑运算";

  public final static String LDTK_BLOG_TITLE_5 = "PieChart";

  public final static String LDTK_BLOG_TITLE_6 = "贝塞尔曲线";

  public final static String LDTK_BLOG_TITLE_7 = "雷达图(蜘蛛网图)";

  public final static String LDTK_BLOG_TITLE_8 = "弹性滑动";

  public final static String LDTK_BLOG_URL_1
      = "https://github.com/Idtk/Blog/blob/master/Blog/1%E3%80%81CoordinateAndProcess.md";

  public final static String LDTK_BLOG_URL_2
      = "https://github.com/Idtk/Blog/blob/master/Blog/2%E3%80%81CanvasAndValueAnimator.md";

  public final static String LDTK_BLOG_URL_3
      = "https://github.com/Idtk/Blog/blob/master/Blog/3%E3%80%81Multi-lineTextCenter.md";

  public final static String LDTK_BLOG_URL_4
      = "https://github.com/Idtk/Blog/blob/master/Blog/4%E3%80%81PathFigureAndLogical.md";

  public final static String LDTK_BLOG_URL_5
      = "https://github.com/Idtk/Blog/blob/master/Blog/5%E3%80%81PieChart.md";

  public final static String LDTK_BLOG_URL_6
      = "https://github.com/Idtk/Blog/blob/master/Blog/6%E3%80%81Bezier.md";

  public final static String LDTK_BLOG_URL_7
      = "https://github.com/Idtk/Blog/blob/master/Blog/7%E3%80%81RadarChart.md";

  public final static String LDTK_BLOG_URL_8
      = "https://github.com/Idtk/Blog/blob/master/Blog/8%E3%80%81Scroll.md";


  public List<LdtkBlog> fillData() {

    titles.add(LDTK_BLOG_TITLE_1);
    titles.add(LDTK_BLOG_TITLE_2);
    titles.add(LDTK_BLOG_TITLE_3);
    titles.add(LDTK_BLOG_TITLE_4);
    titles.add(LDTK_BLOG_TITLE_5);
    titles.add(LDTK_BLOG_TITLE_6);
    titles.add(LDTK_BLOG_TITLE_7);
    titles.add(LDTK_BLOG_TITLE_8);

    urls.add(LDTK_BLOG_URL_1);
    urls.add(LDTK_BLOG_URL_2);
    urls.add(LDTK_BLOG_URL_3);
    urls.add(LDTK_BLOG_URL_4);
    urls.add(LDTK_BLOG_URL_5);
    urls.add(LDTK_BLOG_URL_6);
    urls.add(LDTK_BLOG_URL_7);
    urls.add(LDTK_BLOG_URL_8);

    int size = titles.size();
    LdtkBlog ldtkBlog;
    for (int i = 0; i < size; i++) {
      ldtkBlog = new LdtkBlog();
      ldtkBlog.title = titles.get(i);
      ldtkBlog.url = urls.get(i);

      ldtkBlogs.add(ldtkBlog);
    }

    return ldtkBlogs;
  }
}
