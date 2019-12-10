package helper.archetype.share.common.cache.redis.timer.delay;

import helper.archetype.share.common.cache.redis.AppRedisKey;
import helper.archetype.share.common.cache.redis.timer.TimerPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RDelayedQueueSupport {

    @Autowired
    private TimerPoolConfig timerPoolConfig;
    private ThreadPoolTaskExecutor delayQueueExecutor;

    private static RBlockingQueue<DelayTask> blockingFairQueue;
    private static RDelayedQueue<DelayTask> delayedQueue;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private void setRedissonClient(RedissonClient redissonClient) {
        blockingFairQueue =
                redissonClient.getBlockingQueue(AppRedisKey.DELAY_QUEUE.getKey(profile));
        delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
    }

    public static void put(DelayTask delayTask, long timeout, TimeUnit timeUnit) {
        log.info("【延时任务队列】任务已加入延迟队列,taskId={}", delayTask.getTaskId());
        delayedQueue.offer(delayTask, timeout, timeUnit);
    }

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

        Thread daemonThread = new Thread(this::execute);
        daemonThread.setDaemon(true);
        daemonThread.setName(timerPoolConfig.getDelay().getDelayTaskQueueDaemonThreadName());
        daemonThread.start();
    }

    private void execute() {
        log.info("【延时任务队列守护线程】开启,thread={}", Thread.currentThread().getId());
        while (true) {
            // 阻塞式获取
            try {
                DelayTask task = blockingFairQueue.take();
                if (task == null) {
                    continue;
                }
                log.info("【延时任务队列】任务已提取并加入线程池,taskId={}", task.getTaskId());
                delayQueueExecutor.execute(task);
            } catch (InterruptedException e) {
                log.error("【延时任务队列守护线程】异常", e);
                break;
            }
        }
        log.info("【延时任务队列守护线程】关闭,thread={}", Thread.currentThread().getId());
    }
}
