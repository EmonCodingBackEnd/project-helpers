package com.coding.helpers.plugin.gray.command;

import com.coding.helpers.plugin.gray.command.strategy.executionhook.GrayCommandExecutionhook;
import com.netflix.hystrix.CustomExecuteCommand;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;

public abstract class GrayCommand<T> extends CustomExecuteCommand<T> {

    protected GrayCommand(
            String groupKey,
            String commandKey,
            String poolKey,
            int poolSize,
            int executionTimeoutInMilliseconds,
            boolean executionTimeoutEnabled,
            HystrixCommandExecutionHook executionHook) {
        super(
                groupKey,
                commandKey,
                poolKey,
                poolSize,
                executionTimeoutInMilliseconds,
                executionTimeoutEnabled,
                executionHook);
    }

    /**
     * 构造器.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190208 21:00</font><br>
     * [请在此输入功能详述]
     *
     * @param groupKey - 命令分组
     * @param commandKey - 命令名称
     * @param poolKey - 线程池名
     * @param poolSize - 线程池大小
     * @param executionTimeoutInMilliseconds - 命令执行的超时时间
     * @param executionTimeoutEnabled - 是否打开超时中断命令执行
     * @author Rushing0711
     * @since 1.0.0
     */
    public GrayCommand(
            String groupKey,
            String commandKey,
            String poolKey,
            int poolSize,
            int executionTimeoutInMilliseconds,
            boolean executionTimeoutEnabled) {
        this(
                groupKey,
                commandKey,
                poolKey,
                poolSize,
                executionTimeoutInMilliseconds,
                executionTimeoutEnabled,
                new GrayCommandExecutionhook());
    }

    /**
     * 构造器.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190208 21:03</font><br>
     * 构造一个默认启用超时的命令
     *
     * @param groupKey - 命令分组
     * @param commandKey - 命令名称
     * @param poolKey - 线程池名
     * @param poolSize - 线程池大小
     * @param executionTimeoutInMilliseconds - 命令执行的超时时间
     * @author Rushing0711
     * @since 1.0.0
     */
    public GrayCommand(
            String groupKey,
            String commandKey,
            String poolKey,
            int poolSize,
            int executionTimeoutInMilliseconds) {
        this(groupKey, commandKey, poolKey, poolSize, executionTimeoutInMilliseconds, true);
    }

    /**
     * 构造器.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190208 21:04</font><br>
     * 构造一个默认关闭超时的命令
     *
     * @param groupKey - 命令分组
     * @param commandKey - 命令名称
     * @param poolKey - 线程池名
     * @param poolSize - 线程池大小
     * @author Rushing0711
     * @since 1.0.0
     */
    public GrayCommand(String groupKey, String commandKey, String poolKey, int poolSize) {
        this(groupKey, commandKey, poolKey, poolSize, 0, false);
    }
}
