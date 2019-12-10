#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * 文件名称：DelayQueueDaemonThread.java
 * 系统名称：[系统名称]
 * 模块名称：延时队列守护线程
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180515 13:36
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180515-01         Rushing0711     M201805151336 新建文件
 ********************************************************************************/
package ${package}.cache.redis.timer.delay;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列守护线程.
 *
 * <p>创建时间: <font style="color:${symbol_pound}00FFFF">20180515 14:11</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public abstract class DelayQueueSupport {

    /** 创建一个最初为空的新 DelayQueue */
    private static DelayQueue<DelayedItem> delayedItems = new DelayQueue<>();

    public static DelayQueue<DelayedItem> getDelayedItems() {
        return delayedItems;
    }

    /**
     * 添加任务到延时队列.
     *
     * <p>创建时间: <font style="color:${symbol_pound}00FFFF">20180515 14:06</font><br>
     * [请在此输入功能详述]
     *
     * @param delayTask - 延时任务，继承Thread类或实现Runnable接口的类
     * @param timeout - 延时时间，单位毫秒
     * @author Rushing0711
     * @since 1.0.0
     */
    public static void put(DelayTask delayTask, long timeout) {
        long nanoTime = TimeUnit.NANOSECONDS.convert(timeout, TimeUnit.MILLISECONDS);
        // 创建一个任务
        DelayedItem delayedItem = new DelayedItem(delayTask, nanoTime);
        // 阻塞式将任务放在延时的队列中
        delayedItems.put(delayedItem);
    }
}
