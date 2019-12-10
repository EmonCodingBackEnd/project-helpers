/*
 * 文件名称：AsyncConfig.java
 * 系统名称：[系统名称]
 * 模块名称：异步任务配置
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180515 10:37
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180515-01         Rushing0711     M201805151037 新建文件
 ********************************************************************************/
package helper.archetype.share.common.cache.redis.timer.schedule;

import helper.archetype.share.common.cache.redis.timer.TimerPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务配置.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180514 16:37</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Autowired TimerPoolConfig timerPoolConfig;

    @Override
    public Executor getAsyncExecutor() {
        log.info(
                "【异步任务线程池配置】threadNamePrefix={},corePoolSize={},maxPoolSize={},queueCapacity={},keeyAliveSecond={}",
                timerPoolConfig.getAsync().getThreadNamePrefix(),
                timerPoolConfig.getAsync().getCorePoolSize(),
                timerPoolConfig.getAsync().getMaxPoolSize(),
                timerPoolConfig.getAsync().getQueueCapacity(),
                timerPoolConfig.getAsync().getKeeyAliveSecond());
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(timerPoolConfig.getAsync().getThreadNamePrefix());
        executor.setCorePoolSize(timerPoolConfig.getAsync().getCorePoolSize());
        executor.setMaxPoolSize(timerPoolConfig.getAsync().getMaxPoolSize());
        executor.setQueueCapacity(timerPoolConfig.getAsync().getQueueCapacity());
        executor.setKeepAliveSeconds(timerPoolConfig.getAsync().getKeeyAliveSecond());
        /*
         * Rejected-policy：当pool已经达到max size的时候，如何处理新任务。
         * CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行。
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtHandler();
    }
}
