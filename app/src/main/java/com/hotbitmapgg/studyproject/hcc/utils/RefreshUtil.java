package com.hotbitmapgg.studyproject.hcc.utils;

import android.support.v4.widget.SwipeRefreshLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 针对SwipeRefreshLayout设置自动刷新
 */
public class RefreshUtil
{

    public static void setRefreshing(SwipeRefreshLayout refreshLayout,
                                     boolean refreshing, boolean notify)
    {
        Class<? extends SwipeRefreshLayout> refreshLayoutClass = refreshLayout.getClass();
        if (refreshLayoutClass != null)
        {
            try
            {
                Method setRefreshing = refreshLayoutClass.
                        getDeclaredMethod("setRefreshing", boolean.class, boolean.class);
                setRefreshing.setAccessible(true);
                setRefreshing.invoke(refreshLayout, refreshing, notify);
            } catch (NoSuchMethodException e)
            {
                e.printStackTrace();
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            } catch (InvocationTargetException e)
            {
                e.printStackTrace();
            }
        }
    }

}
