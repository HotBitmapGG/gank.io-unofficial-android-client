package com.hotbitmapgg.studyproject.hcc.db;

import android.content.Context;

import com.hotbitmapgg.studyproject.hcc.model.DoubanMeizi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.realm.Realm;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class MeiziCache
{

    private static MeiziCache mCache;

    private MeiziCache()
    {

    }

    public static MeiziCache getInstance()
    {

        if (mCache == null)
        {
            synchronized (MeiziCache.class)
            {
                if (mCache == null)
                {
                    mCache = new MeiziCache();
                }
            }
        }

        return mCache;
    }

    public void putDoubanMeiziCache(Context context, int type, Response<ResponseBody> response)
    {

        try
        {

            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();

            String string = response.body().string();
            Document parse = Jsoup.parse(string);
            Elements elements = parse.select("div[class=thumbnail]>div[class=img_single]>a>img");
            DoubanMeizi meizi;
            for (Element e : elements)
            {
                String src = e.attr("src");
                String title = e.attr("title");

                meizi = new DoubanMeizi();
                meizi.setUrl(src);
                meizi.setTitle(title);
                meizi.setType(type);

                realm.copyToRealm(meizi);
            }

            realm.commitTransaction();
            realm.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
