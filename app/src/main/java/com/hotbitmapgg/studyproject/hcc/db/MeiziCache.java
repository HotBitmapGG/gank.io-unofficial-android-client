package com.hotbitmapgg.studyproject.hcc.db;

/**
 * Created by hcc on 16/6/2 19:35
 * 100332338@qq.com
 */
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


//    public void putHuaBanMeizi(Context context, int type, List<HuaBanMeiziInfo> infos)
//    {
//
//        Realm realm = Realm.getInstance(context);
//        realm.beginTransaction();
//        for (HuaBanMeiziInfo info : infos)
//        {
//            LogUtil.all("111");
//            info.setType(type);
//            realm.copyFromRealm(info);
//        }
//
//        realm.commitTransaction();
//        realm.close();
//    }
}
