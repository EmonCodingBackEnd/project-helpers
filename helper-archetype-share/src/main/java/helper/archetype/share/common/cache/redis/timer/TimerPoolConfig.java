/*
 * 文件名称：TimerPoolConfig.java
 * 系统名称：[系统名称]
 * 模块名称：特殊任务线程池配置
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180515 10:38
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180515-01         Rushing0711     M201805151038 新建文件
 ********************************************************************************/
package helper.archetype.share.common.cache.redis.timer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 特殊任务线程池配置.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180613 15:36</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "timerpool")
public class TimerPoolConfig {

    private Delay delay;

    private Schedule schedule;

    private Async async;

    @Data
    public static class Delay {
        private String threadNamePrefix;

        private int corePoolSize;

        private int maxPoolSize;

        private int queueCapacity;

        private int keeyAliveSecond;

        private String delayTaskQueueDaemonThreadName;
    }

    @Data
    public static class Schedule {

        private String threadNamePrefix;

        private int poolSize;

        private int awaitTerminationSeconds;
    }

    @Data
    public static class Async {
        private String threadNamePrefix;

        private int corePoolSize;

        private int maxPoolSize;

        private int queueCapacity;

        private int keeyAliveSecond;
    }
}
