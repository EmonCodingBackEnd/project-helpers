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

import ${package}.cache.redis.timer.TimerPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;

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
@Component
@Slf4j
public class DelayQueueDaemonThread {

    @Autowired TimerPoolConfig timerPoolConfig;

    private ThreadPoolTaskExecutor delayQueueExecutor;

    /** 初始化守护线程 */
    @PostConstruct
    private void init() {
        log.info(
                "【延时任务线程池配置】threadNamePrefix={},corePoolSize={},maxPoolSize={},queueCapacity={},keeyAliveSecond={}",
                timerPoolConfig.getDelay().getThreadNamePrefix(),
                timerPoolConfig.getDelay().getCorePoolSize(),
                timerPoolConfig.getDelay().getMaxPoolSize(),
                timerPoolConfig.getDelay().getQueueCapacity(),
                timerPoolConfig.getDelay().getKeeyAliveSecond());
        delayQueueExecutor = new ThreadPoolTaskExecutor();
        delayQueueExecutor.setThreadNamePrefix(timerPoolConfig.getDelay().getThreadNamePrefix());
        delayQueueExecutor.setCorePoolSize(timerPoolConfig.getDelay().getCorePoolSize());
        delayQueueExecutor.setMaxPoolSize(timerPoolConfig.getDelay().getMaxPoolSize());
        delayQueueExecutor.setQueueCapacity(timerPoolConfig.getDelay().getQueueCapacity());
        delayQueueExecutor.setKeepAliveSeconds(timerPoolConfig.getDelay().getKeeyAliveSecond());
        /*
         * Rejected-policy：当pool已经达到max size的时候，如何处理新任务。
         * CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行。
         */
        delayQueueExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        delayQueueExecutor.initialize();

        Thread daemonThread = new Thread(() -> execute());
        daemonThread.setDaemon(true);
        daemonThread.setName(timerPoolConfig.getDelay().getDelayTaskQueueDaemonThreadName());
        daemonThread.start();
    }

    private void execute() {
        log.info("【延时任务队列守护线程】开启,thread={}", Thread.currentThread().getId());
        while (true) {
            // 阻塞式获取
            DelayedItem item;
            try {
                item = DelayQueueSupport.getDelayedItems().take();
                if (item != null) {
                    DelayTask task = item.getTask();
                    if (task == null) {
                        continue;
                    }
                    log.info("【延时任务队列】任务已提取并加入线程池,taskId={}", task.getTaskId());
                    delayQueueExecutor.execute(task);
                }
            } catch (InterruptedException e) {
                log.error("【延时任务队列守护线程】异常", e);
                break;
            }
        }
        log.info("【延时任务队列守护线程】关闭,thread={}", Thread.currentThread().getId());
    }
}
