package com.hotbitmapgg.studyproject.hcc.rxjava_operator;


import java.util.ArrayList;
import java.util.List;


public class DataUtils
{


    /**
     * Rxjava介绍相关
     *
     * @return
     */
    public List<Operator> getIntroduceList()
    {
        List<Operator> alloperatorses = new ArrayList<>();

        alloperatorses.add(new Operator(1, "ReactiveX", "什么是Rx，Rx的理念和优势", CommonString.SPLASH_INDEX_URL, OperatorsUrl.INTRODUCE, 1));
        alloperatorses.add(new Operator(2, "RxJava入门指南", "RxJava入门指南", CommonString.SPLASH_INDEX_URL, OperatorsUrl.GUIDE_OPEN, 1));
        alloperatorses.add(new Operator(3, "RxJava使用示例", "RxJava使用示例", CommonString.SPLASH_INDEX_URL, OperatorsUrl.SAMPLE_FIRST, 1));
        alloperatorses.add(new Operator(4, "Observables", "简要介绍Observable的观察者模型", CommonString.OBSERVABLES, OperatorsUrl.OBSERVABLES, 1));
        alloperatorses.add(new Operator(5, "Single", "一种特殊的只发射单个值的Observable", CommonString.SPLASH_INDEX_URL, OperatorsUrl.SINGLE, 1));
        alloperatorses.add(new Operator(6, "Subject", "Observable和Observer的复合体，也是二者的桥梁", CommonString.SUBJECT, OperatorsUrl.SUBJECT, 1));
        alloperatorses.add(new Operator(7, "Scheduler", "介绍了各种异步任务调度和默认调度器", CommonString.SPLASH_INDEX_URL, OperatorsUrl.SCHEDULE, 1));

        return alloperatorses;
    }


    /**
     * Create 创建操作
     *
     * @return
     */
    public List<Operator> getCreatingList()
    {
        List<Operator> alloperatorses = new ArrayList<>();

        alloperatorses.add(new Operator(1, "just()", "将一个或多个对象转换成发射这个或这些对象的一个Observable", CommonString.JUST, OperatorsUrl.JUST, 2));
        alloperatorses.add(new Operator(2, "from()", "将一个Iterable, 一个Future, 或者一个数组转换成一个Observable", CommonString.FROM, OperatorsUrl.FROM, 2));
        alloperatorses.add(new Operator(3, "repeat()", "创建一个重复发射指定数据或数据序列的Observable", CommonString.REPEAT, OperatorsUrl.REPEAT, 2));
        alloperatorses.add(new Operator(4, "repeatWhen()", "创建一个重复发射指定数据或数据序列的Observable，它依赖于另一个Observable发射的数据", CommonString.REPEAT_WHEN, OperatorsUrl.REPEAT, 2));
        alloperatorses.add(new Operator(5, "create()", "使用一个函数从头创建一个Observable", CommonString.CREATE, OperatorsUrl.CREATE, 2));
        alloperatorses.add(new Operator(6, "defer()", "只有当订阅者订阅才创建Observable；为每个订阅创建一个新的Observable", CommonString.DEFER, OperatorsUrl.DEFER, 2));
        alloperatorses.add(new Operator(7, "range()", "创建一个发射指定范围的整数序列的Observable", CommonString.RANGE, OperatorsUrl.DEFER, 2));
        alloperatorses.add(new Operator(8, "interval()", "创建一个按照给定的时间间隔发射整数序列的Observable", CommonString.INTERVAL, OperatorsUrl.INTERVAL, 2));
        alloperatorses.add(new Operator(9, "timer()", "创建一个按照给定的时间间隔发射整数序列的Observable", CommonString.TIMER, OperatorsUrl.TIMER, 2));
        alloperatorses.add(new Operator(10, "empty()", "创建一个什么都不做直接通知完成的Observable", CommonString.EMPTY, OperatorsUrl.EMPTY, 2));
        alloperatorses.add(new Operator(11, "error()", "创建一个什么都不做直接通知错误的Observable", CommonString.EMPTY, OperatorsUrl.EMPTY, 2));
        alloperatorses.add(new Operator(12, "never()", "创建一个不发射任何数据的Observable", CommonString.EMPTY, OperatorsUrl.EMPTY, 2));


        return alloperatorses;
    }

    /**
     * TransFrom 变换操作
     *
     * @return
     */
    public List<Operator> getTransformList()
    {
        List<Operator> alloperatorses = new ArrayList<>();

        alloperatorses.add(new Operator(1, "map()", "对序列的每一项都应用一个函数来变换Observable发射的数据序列", CommonString.MAP, OperatorsUrl.MAP, 3));
        alloperatorses.add(new Operator(2, "flatMap()", "将Observable发射的数据集合变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable", CommonString.FLATMAP, OperatorsUrl.FLATMAP, 3));
        alloperatorses.add(new Operator(3, "concatMap()", "将Observable发射的数据集合变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable", CommonString.CONTACTMAP, OperatorsUrl.CONTACTMAP, 3));
        alloperatorses.add(new Operator(4, "switchMap()", "将Observable发射的数据集合变换为Observables集合，然后只发射这些Observables最近发射的数据", CommonString.SWITCHMAP, OperatorsUrl.SWITCHMAP, 3));
        alloperatorses.add(new Operator(5, "scan()", "对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值", CommonString.SCAN, OperatorsUrl.SCAN, 3));
        alloperatorses.add(new Operator(6, "groupBy()", "将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据", CommonString.GROUPBY, OperatorsUrl.GROUPBY, 3));
        alloperatorses.add(new Operator(7, "buffer()", "它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个", CommonString.BUFFER, OperatorsUrl.BUFFER, 3));
        alloperatorses.add(new Operator(8, "window()", "定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项", CommonString.WINDOW, OperatorsUrl.WINDOW, 3));
        alloperatorses.add(new Operator(9, "cast()", "在发射之前强制将Observable发射的所有数据转换为指定类型", CommonString.CAST, OperatorsUrl.CAST, 3));

        return alloperatorses;
    }

    /**
     * Filter 过滤操作
     *
     * @return
     */
    public List<Operator> getFilterList()
    {
        List<Operator> alloperatorses = new ArrayList<>();

        alloperatorses.add(new Operator(1, "filter()", "过滤数据", CommonString.FILTER, OperatorsUrl.FILTER, 4));
        alloperatorses.add(new Operator(2, "takeLast()", "只发射最后的N项数据", CommonString.TAKE_LAST, OperatorsUrl.TAKE_LAST, 4));
        alloperatorses.add(new Operator(3, "last()", "只发射最后的一项数据", CommonString.LAST, OperatorsUrl.LAST, 4));
        alloperatorses.add(new Operator(4, "lastOrDefault()", "只发射最后的一项数据，如果Observable为空就发射默认值", CommonString.LAST_OR_DEFAULT, OperatorsUrl.LAST_OR_DEFAULT, 4));
        alloperatorses.add(new Operator(5, "takeLastBuffer()", "将最后的N项数据当做单个数据发射", CommonString.TAKE_LAST_BUFFER, OperatorsUrl.TAKE_LAST_BUFFER, 4));
        alloperatorses.add(new Operator(6, "skip()", "跳过开始的N项数据", CommonString.SKIP, OperatorsUrl.SKIP, 4));
        alloperatorses.add(new Operator(7, "skipLast()", "跳过最后的N项数据", CommonString.SKIP_LAST, OperatorsUrl.SKIP_LAST, 4));
        alloperatorses.add(new Operator(8, "take()", "只发射开始的N项数据", CommonString.TAKE, OperatorsUrl.TAKE, 4));
        alloperatorses.add(new Operator(9, "first() and takeFirst()", "只发射第一项数据，或者满足某种条件的第一项数据", CommonString.FIRST, OperatorsUrl.FIRST, 4));
        alloperatorses.add(new Operator(10, "firstOrDefault()", "只发射第一项数据，如果Observable为空就发射默认值", CommonString.FIRST_DEFAULT, OperatorsUrl.FIRST_DEFAULT, 4));
        alloperatorses.add(new Operator(11, "elementAt()", "发射第N项数据", CommonString.ELEMENT_AT, OperatorsUrl.ELEMENT_AT, 4));
        alloperatorses.add(new Operator(12, "elementAtOrDefault()", "发射第N项数据，如果Observable数据少于N项就发射默认值", CommonString.ELEMENT_DEFAULT, OperatorsUrl.ELEMENT_DEFAULT, 4));
        alloperatorses.add(new Operator(13, "sample() or throttleLast()", "定期发射Observable最近的数据", CommonString.SAMPLE, OperatorsUrl.SAMPLE, 4));
        alloperatorses.add(new Operator(14, "throttleFirst()", "定期发射Observable发射的第一项数据", CommonString.THROLFIRST, OperatorsUrl.THROLFIRST, 4));
        alloperatorses.add(new Operator(15, "throttleWithTimeout() or debounce()", "只有当Observable在指定的时间后还没有发射数据时，才发射一个数据", CommonString.DEBOUND, OperatorsUrl.DEBOUND, 4));
        alloperatorses.add(new Operator(16, "timeout()", "如果在一个指定的时间段后还没发射数据，就发射一个异常", CommonString.TIMEOUT, OperatorsUrl.TIMEOUT, 4));
        alloperatorses.add(new Operator(17, "distinct()", "过滤掉重复数据", CommonString.DISTINCT, OperatorsUrl.DISTINCT, 4));
        alloperatorses.add(new Operator(18, "distinctUntilChanged()", "过滤掉连续重复的数据", CommonString.UNTILCHANGED, OperatorsUrl.UNTILCHANGED, 4));
        alloperatorses.add(new Operator(19, "ofType()", "只发射指定类型的数据", CommonString.OF_TYPE, OperatorsUrl.OF_TYPE, 4));
        alloperatorses.add(new Operator(20, "ignoreElements()", "丢弃所有的正常数据，只发射错误或完成通知", CommonString.IGNORE_ELEMENT, OperatorsUrl.IGNORE_ELEMENT, 4));

        return alloperatorses;
    }


    /**
     * ComBin 结合操作
     *
     * @return
     */
    public List<Operator> getCombinList()
    {
        List<Operator> alloperatorses = new ArrayList<>();

        alloperatorses.add(new Operator(1, "startWith()", "在数据序列的开头增加一项数据", CommonString.STARTWITH, OperatorsUrl.STARTWITH, 5));
        alloperatorses.add(new Operator(2, "merge()", "将多个Observable合并为一个", CommonString.MERGE, OperatorsUrl.MERGE, 5));
        alloperatorses.add(new Operator(3, "mergeDelayError()", "合并多个Observables，让没有错误的Observable都完成后再发射错误通知", CommonString.MERGEDELAY, OperatorsUrl.MERGEDELAY, 5));
        alloperatorses.add(new Operator(4, "zip()", "使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果", CommonString.ZIP, OperatorsUrl.ZIP, 5));
        alloperatorses.add(new Operator(5, "and(), then(), and when()", "(rxjava-joins)通过模式和计划组合多个Observables发射的数据集合", CommonString.AND, OperatorsUrl.AND, 5));
        alloperatorses.add(new Operator(6, "combineLatest()", "当两个Observables中的任何一个发射了一个数据时，通过一个指定的函数组合每个Observable发射的最新数据（一共两个数据），然后发射这个函数的结果", CommonString.COMBINLASTED, OperatorsUrl.COMBINLASTED, 5));
        alloperatorses.add(new Operator(7, "join() and groupJoin()", "无论何时，如果一个Observable发射了一个数据项，只要在另一个Observable发射的数据项定义的时间窗口内，就将两个Observable发射的数据合并发射", CommonString.JOIN, OperatorsUrl.JOIN, 5));
        alloperatorses.add(new Operator(8, "switchOnNext()", "将一个发射Observables的Observable转换成另一个Observable，后者发射这些Observables最近发射的数据", CommonString.SWITHONNEXT, OperatorsUrl.SWITHONNEXT, 5));

        return alloperatorses;
    }


    /**
     * Error 错误处理
     *
     * @return
     */
    public List<Operator> getErrorList()
    {
        List<Operator> alloperatorses = new ArrayList<>();

        alloperatorses.add(new Operator(1, "onErrorResumeNext()", "指示Observable在遇到错误时发射一个数据序列", CommonString.EMPTY, OperatorsUrl.ERROR, 6));
        alloperatorses.add(new Operator(2, "onErrorReturn()", "指示Observable在遇到错误时发射一个特定的数据", CommonString.EMPTY, OperatorsUrl.ERROR, 6));
        alloperatorses.add(new Operator(3, "onExceptionResumeNext()", "instructs an Observable to continue emitting items after it encounters an exception (but not another variety of throwable)指示Observable遇到错误时继续发射数据", CommonString.EMPTY, OperatorsUrl.ERROR, 6));
        alloperatorses.add(new Operator(4, "retry()", "指示Observable遇到错误时重试", CommonString.RETRY, OperatorsUrl.RETRY, 6));
        alloperatorses.add(new Operator(5, "retryWhen()", "指示Observable遇到错误时，将错误传递给另一个Observable来决定是否要重新给订阅这个Observable", CommonString.RETRYWHEN, OperatorsUrl.RETRY, 6));

        return alloperatorses;
    }


    /**
     * Utility 辅助操作
     *
     * @return
     */
    public List<Operator> getUtilityList()
    {
        List<Operator> alloperatorses = new ArrayList<>();

        alloperatorses.add(new Operator(1, "Materialize()", "将Observable转换成一个通知列表convert an Observable into a list of Notifications", CommonString.Materialize, OperatorsUrl.Materialize, 7));
        alloperatorses.add(new Operator(2, "Dematerialize()", "将上面的结果逆转回一个Observable", CommonString.Dematerialize, OperatorsUrl.Dematerialize, 7));
        alloperatorses.add(new Operator(3, "Timestamp()", "给Observable发射的每个数据项添加一个时间戳", CommonString.Timestamp, OperatorsUrl.Timestamp, 7));
        alloperatorses.add(new Operator(4, "Serialize()", "强制Observable按次序发射数据并且要求功能是完好的", CommonString.Serialize, OperatorsUrl.Serialize, 7));
        alloperatorses.add(new Operator(5, "ObserveOn()", "指定观察者观察Observable的调度器", CommonString.ObserveOn, OperatorsUrl.ObserveOn, 7));
        alloperatorses.add(new Operator(6, "SubscribeOn()", "指定Observable执行任务的调度器", CommonString.SubscribeOn, OperatorsUrl.SubscribeOn, 7));
        alloperatorses.add(new Operator(7, "doOnEach()", "注册一个动作，对Observable发射的每个数据项使用", CommonString.doOnEach, OperatorsUrl.doOnEach, 7));
        alloperatorses.add(new Operator(8, "doOnSubscribe()", "注册一个动作，在观察者订阅时使用", CommonString.doOnSubscribe, OperatorsUrl.doOnSubscribe, 7));
        alloperatorses.add(new Operator(9, "doOnUnsubscribe()", "注册一个动作，在观察者取消订阅时使用", CommonString.doOnUnsubscribe, OperatorsUrl.doOnUnsubscribe, 7));
        alloperatorses.add(new Operator(10, "doOnCompleted()", "注册一个动作，对正常完成的Observable使用", CommonString.doOnCompleted, OperatorsUrl.doOnCompleted, 7));
        alloperatorses.add(new Operator(11, "doOnError()", "注册一个动作，对Observable发射的每个数据项使用", CommonString.doOnError, OperatorsUrl.doOnError, 7));
        alloperatorses.add(new Operator(12, "doOnTerminate()", "注册一个动作，对完成的Observable使用，无论是否发生错误", CommonString.doOnTerminate, OperatorsUrl.doOnTerminate, 7));
        alloperatorses.add(new Operator(13, "finallyDo()", "注册一个动作，在Observable完成时使用", CommonString.finallyDo, OperatorsUrl.finallyDo, 7));
        alloperatorses.add(new Operator(14, "Delay()", "延时发射Observable的结果", CommonString.Delay, OperatorsUrl.Delay, 7));
        alloperatorses.add(new Operator(15, "delaySubscription()", "延时处理订阅请求", CommonString.delaySubscription, OperatorsUrl.delaySubscription, 7));
        alloperatorses.add(new Operator(16, "TimeInterval()", "定期发射数据", CommonString.TimeInterval, OperatorsUrl.TimeInterval, 7));
        alloperatorses.add(new Operator(17, "Using()", " 创建一个只在Observable生命周期存在的资源", CommonString.Using, OperatorsUrl.Using, 7));
        alloperatorses.add(new Operator(18, "single()", " 强制返回单个数据，否则抛出异常", CommonString.First, OperatorsUrl.First, 7));
        alloperatorses.add(new Operator(19, "toFuture(), toIterable(), toList()", "将Observable转换为其它对象或数据结构", CommonString.To, OperatorsUrl.To, 7));

        return alloperatorses;
    }


    /**
     * Conditional 条件和布尔操作
     *
     * @return
     */
    public List<Operator> getConditionalList()
    {
        List<Operator> alloperatorses = new ArrayList<>();

        alloperatorses.add(new Operator(1, "amb()", "给定多个Observable，只让第一个发射数据的Observable发射全部数据", CommonString.amb, OperatorsUrl.amb, 8));
        alloperatorses.add(new Operator(2, "defaultIfEmpty()", "发射来自原始Observable的数据，如果原始Observable没有发射数据，就发射一个默认数据", CommonString.defaultIfEmpty, OperatorsUrl.amb, 8));
        alloperatorses.add(new Operator(3, "doWhile()", "发射原始Observable的数据序列，然后重复发射这个序列直到不满足这个条件为止", CommonString.doWhile, OperatorsUrl.amb, 8));
        alloperatorses.add(new Operator(4, "ifThen()", "只有当某个条件为真时才发射原始Observable的数据序列，否则发射一个空的或默认的序列", CommonString.ifThen, OperatorsUrl.amb, 8));
        alloperatorses.add(new Operator(5, "skipUntil()", "丢弃原始Observable发射的数据，直到第二个Observable发射了一个数据，然后发射原始Observable的剩余数据", CommonString.skipUntil, OperatorsUrl.amb, 8));
        alloperatorses.add(new Operator(6, "skipWhile()", "丢弃原始Observable发射的数据，直到一个特定的条件为假，然后发射原始Observable剩余的数据", CommonString.skipWhile, OperatorsUrl.amb, 8));
        alloperatorses.add(new Operator(7, "switchCase()", "基于一个计算结果，发射一个指定Observable的数据序列", CommonString.switchCase, OperatorsUrl.amb, 8));
        alloperatorses.add(new Operator(8, "takeUntil()", "发射来自原始Observable的数据，直到第二个Observable发射了一个数据或一个通知", CommonString.takeUntil, OperatorsUrl.amb, 8));
        alloperatorses.add(new Operator(9, "takeWhile(),takeWhileWithIndex()", "射原始Observable的数据，直到一个特定的条件为真，然后跳过剩余的数据", CommonString.takeWhile, OperatorsUrl.amb, 8));
        alloperatorses.add(new Operator(10, "whileDo()", "if a condition is true, emit the source Observable's sequence and then repeat the sequence as long as the condition remains true如果满足一个条件，发射原始Observable的数据，然后重复发射直到不满足这个条件为止", CommonString.takeWhile, CommonString.GITHUB_URL, 8));

        alloperatorses.add(new Operator(11, "all()", "判断是否所有的数据项都满足某个条件", CommonString.all, OperatorsUrl.BOOLEAN_ALL, 8));
        alloperatorses.add(new Operator(12, "contains()", "判断Observable是否会发射一个指定的值", CommonString.contains, OperatorsUrl.BOOLEAN_ALL, 8));
        alloperatorses.add(new Operator(13, "exists(),isEmpty()", "判断Observable是否发射了一个值", CommonString.exists, OperatorsUrl.BOOLEAN_ALL, 8));
        alloperatorses.add(new Operator(14, "sequenceEqual()", " test the equality of the sequences emitted by two Observables判断两个Observables发射的序列是否相等", CommonString.sequenceEqual, OperatorsUrl.BOOLEAN_ALL, 8));

        return alloperatorses;

    }


    /**
     * Math 算术和聚合操作
     *
     * @return
     */
    public List<Operator> getMathList()
    {
        List<Operator> alloperatorses = new ArrayList<>();

        alloperatorses.add(new Operator(1, "rxjava-math模块", "模块的操作符", CommonString.MATH, OperatorsUrl.MATH, 9));
        alloperatorses.add(new Operator(2, "concat()", "顺序连接多个Observables", CommonString.concat, OperatorsUrl.concat, 9));
        alloperatorses.add(new Operator(3, "count() and countLong()", "计算数据项的个数并发射结果", CommonString.count, OperatorsUrl.count, 9));
        alloperatorses.add(new Operator(4, "reduce()", "对序列使用reduce()函数并发射对吼的结果", CommonString.reduce, OperatorsUrl.reduce, 9));
        alloperatorses.add(new Operator(5, "collect()", "将原始Observable发射的数据放到一个单一的可变的数据结构中，然后返回一个发射这个数据结构的Observable", CommonString.collect, OperatorsUrl.collect, 9));
        alloperatorses.add(new Operator(6, "toList()", "收集原始Observable发射的所有数据到一个列表，然后返回这个列表", CommonString.toList, OperatorsUrl.toList, 9));
        alloperatorses.add(new Operator(7, "toSortedList()", "收集原始Observable发射的所有数据到一个有序列表，然后返回这个列表", CommonString.toSortedList, OperatorsUrl.toSortedList, 9));
        alloperatorses.add(new Operator(8, "toMap()", "将序列数据转换为一个Map，Map的key是根据一个函数计算的", CommonString.toMap, OperatorsUrl.toMap, 9));
        alloperatorses.add(new Operator(9, "toMultiMap()", "将序列数据转换为一个列表，同时也是一个Map，Map的key是根据一个函数计算的", CommonString.toMultiMap, OperatorsUrl.toMultiMap, 9));

        return alloperatorses;
    }


    /**
     * Async 异步操作
     *
     * @return
     */
    public List<Operator> getAsyncList()
    {
        List<Operator> alloperatorses = new ArrayList<>();

        alloperatorses.add(new Operator(1, "start()", "创建一个Observable，它发射一个函数的返回值", CommonString.start, OperatorsUrl.start, 10));
        alloperatorses.add(new Operator(2, "toAsync() or asyncAction() or asyncFunc()", " 将一个函数或者Action转换为已Observable，它执行这个函数并发射函数的返回值", CommonString.toAsync, OperatorsUrl.start, 10));
        alloperatorses.add(new Operator(3, "startFuture()", "将一个返回Future的函数转换为一个Observable，它发射Future的返回值", CommonString.startFuture, OperatorsUrl.start, 10));
        alloperatorses.add(new Operator(4, "deferFuture()", "将一个返回Observable的Future转换为一个Observable，但是并不尝试获取这个Future返回的Observable，直到有订阅者订阅它", CommonString.deferFuture, OperatorsUrl.start, 10));
        alloperatorses.add(new Operator(5, "forEachFuture()", "传递Subscriber方法给一个Subscriber，但是同时表现得像一个Future一样阻塞直到它完成", CommonString.forEachFuture, OperatorsUrl.start, 10));
        alloperatorses.add(new Operator(6, "fromAction()", "将一个Action转换为Observable，当一个订阅者订阅时，它执行这个action并发射它的返回值", CommonString.fromAction, OperatorsUrl.start, 10));
        alloperatorses.add(new Operator(7, "fromCallable()", "将一个Callable转换为Observable，当一个订阅者订阅时，它执行这个Callable并发射Callable的返回值，或者发射异常", CommonString.fromCallable, OperatorsUrl.start, 10));
        alloperatorses.add(new Operator(8, "fromRunnable()", "convert a Runnable into an Observable that invokes the runable and emits its result when a Subscriber subscribes将一个Runnable转换为Observable，当一个订阅者订阅时，它执行这个Runnable并发射Runnable的返回值", CommonString.fromRunnable, OperatorsUrl.start, 10));
        alloperatorses.add(new Operator(9, "runAsync()", "返回一个StoppableObservable，它发射某个Scheduler上指定的Action生成的多个actions", CommonString.runAsync, OperatorsUrl.start, 10));

        return alloperatorses;
    }


    /**
     * Connect 连接操作
     *
     * @return
     */
    public List<Operator> getConnectList()
    {
        List<Operator> alloperatorses = new ArrayList<>();

        alloperatorses.add(new Operator(1, "ConnectableObservable.connect()", "指示一个可连接的Observable开始发射数据", CommonString.connect, OperatorsUrl.connect, 11));
        alloperatorses.add(new Operator(2, "ConnectableObservable.publish()", "将一个Observable转换为一个可连接的Observable", CommonString.publish, OperatorsUrl.publish, 11));
        alloperatorses.add(new Operator(3, "ConnectableObservable.replay()", "确保所有的订阅者看到相同的数据序列，即使它们在Observable开始发射数据之后才订阅", CommonString.replay, OperatorsUrl.replay, 11));
        alloperatorses.add(new Operator(4, "ConnectableObservable.refCount()", "让一个可连接的Observable表现得像一个普通的Observable", CommonString.refCount, OperatorsUrl.refCount, 11));

        return alloperatorses;
    }


    /**
     * Block阻塞操作
     */
    public List<Operator> getBlockList()
    {
        List<Operator> alloperatorses = new ArrayList<>();
        alloperatorses.add(new Operator(1, "forEach()", "对Observable发射的每一项数据调用一个方法，会阻塞直到Observable完成", CommonString.forEach, OperatorsUrl.forEach, 12));
        alloperatorses.add(new Operator(2, "first()", "阻塞直到Observable发射了一个数据，然后返回第一项数据", CommonString.first, OperatorsUrl.first, 12));
        alloperatorses.add(new Operator(3, "firstOrDefault()", "阻塞直到Observable发射了一个数据或者终止，返回第一项数据，或者返回默认值", CommonString.firstOrDefault, OperatorsUrl.firstOrDefault, 12));
        alloperatorses.add(new Operator(4, "last()", "阻塞直到Observable终止，然后返回最后一项数据", CommonString.last, OperatorsUrl.last, 12));
        alloperatorses.add(new Operator(5, "lastOrDefault()", " 阻塞直到Observable终止，然后返回最后一项的数据，或者返回默认值", CommonString.lastOrDefault, OperatorsUrl.lastOrDefault, 12));
        alloperatorses.add(new Operator(6, "mostRecent()", "返回一个总是返回Observable最近发射的数据的iterable", CommonString.mostRecent, OperatorsUrl.mostRecent, 12));
        alloperatorses.add(new Operator(7, "next()", "返回一个Iterable，会阻塞直到Observable发射了另一个值，然后返回那个值", CommonString.next, OperatorsUrl.next, 12));
        alloperatorses.add(new Operator(8, "single()", " 如果Observable终止时只发射了一个值，返回那个值，否则抛出异常", CommonString.single, OperatorsUrl.single, 12));
        alloperatorses.add(new Operator(9, "singleOrDefault()", "如果Observable终止时只发射了一个值，返回那个值，否则否好默认值", CommonString.singleOrDefault, OperatorsUrl.singleOrDefault, 12));
        alloperatorses.add(new Operator(10, "toFuture()", "将Observable转换为一个Future", CommonString.toFuture, OperatorsUrl.toFuture, 12));
        alloperatorses.add(new Operator(11, "toIterable()", "将一个发射数据序列的Observable转换为一个Iterable", CommonString.toIterable, OperatorsUrl.toIterable, 12));
        alloperatorses.add(new Operator(12, "getIterator()", " 将一个发射数据序列的Observable转换为一个Iterator", CommonString.getIterator, OperatorsUrl.getIterator, 12));

        return alloperatorses;

    }


    /**
     * string 操作符
     */
    public List<Operator> getStringList()
    {
        List<Operator> alloperatorses = new ArrayList<>();
        alloperatorses.add(new Operator(1, "byLine()", "将一个字符串的Observable转换为一个行序列的Observable，这个Observable将原来的序列当做流处理，然后按换行符分割", CommonString.byLine, OperatorsUrl.byLine, 13));
        alloperatorses.add(new Operator(2, "decode()", "将一个多字节的字符流转换为一个Observable，它按字符边界发射字节数组", CommonString.decode, OperatorsUrl.decode, 13));
        alloperatorses.add(new Operator(3, "encode()", "对一个发射字符串的Observable执行变换操作，变换后的Observable发射一个在原始字符串中表示多字节字符边界的字节数组", CommonString.encode, OperatorsUrl.encode, 13));
        alloperatorses.add(new Operator(4, "from()", "将一个字符流或者Reader转换为一个发射字节数组或者字符串的Observable", CommonString.from_String, OperatorsUrl.from_String, 13));
        alloperatorses.add(new Operator(5, "join()", "将一个发射字符串序列的Observable转换为一个发射单个字符串的Observable，后者用一个指定的字符串连接所有的字符串", CommonString.join, OperatorsUrl.join, 13));
        alloperatorses.add(new Operator(6, "split()", "将一个发射字符串的Observable转换为另一个发射字符串的Observable，后者使用一个指定的正则表达式边界分割前者发射的所有字符串", CommonString.split, OperatorsUrl.split, 13));
        alloperatorses.add(new Operator(7, "stringConcat()", "将一个发射字符串序列的Observable转换为一个发射单个字符串的Observable，后者连接前者发射的所有字符串", CommonString.stringConcat, OperatorsUrl.stringConcat, 13));

        return alloperatorses;
    }


    /**
     * 其他
     */
    public List<Operator> getOthersList()
    {
        List<Operator> alloperatorses = new ArrayList<>();
        alloperatorses.add(new Operator(1, "实现自己的操作符", "实现自己的操作符", CommonString.SPLASH_INDEX_URL, OperatorsUrl.OWN_OPERATE, 14));
        alloperatorses.add(new Operator(2, "自定义插件", "自定义插件", CommonString.SPLASH_INDEX_URL, OperatorsUrl.OWN_CHAJIAN, 14));
        alloperatorses.add(new Operator(3, "Android模块", "Android模块", CommonString.SPLASH_INDEX_URL, OperatorsUrl.ANDROID_MODULE, 14));
        alloperatorses.add(new Operator(4, "错误处理", "错误处理", CommonString.SPLASH_INDEX_URL, OperatorsUrl.ERROR_HANDLE, 14));


        return alloperatorses;


    }


}
