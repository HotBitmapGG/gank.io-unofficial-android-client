package com.hotbitmapgg.studyproject.hcc.rxdemo;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Rxbus
 * 如果项目中已使用Rxjava
 * 可以使用Rxbus这种思想来替换掉EventBus 或者 Otto
 * 这类事件总线的开源库,  毕竟65535方法数还是很容易达到的
 */
public class RxBus
{

    private static Map<Object,Subject> subjectList = new HashMap<>();

    private RxBus()
    {

    }

    private static class RxBusHolder
    {

        private static final RxBus instance = new RxBus();
    }

    public static synchronized RxBus getInstance()
    {

        return RxBusHolder.instance;
    }

    public synchronized <T> Observable<T> register(@NonNull Object object)
    {

        Subject<T,T> subject = PublishSubject.create();
        subjectList.put(object, subject);
        return subject;
    }

    public synchronized void unregister(Object object)
    {

        subjectList.remove(object);
    }

    public void post(@NonNull Object content)
    {

        synchronized (this)
        {
            for (Subject subject : subjectList.values())
            {
                if (subject != null)
                {
                    subject.onNext(content);
                }
            }
        }
    }
}
