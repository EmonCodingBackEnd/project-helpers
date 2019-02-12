package com.netflix.hystrix;

import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;

public abstract class CustomExecuteCommand<T> extends HystrixCommand<T> {

    public CustomExecuteCommand(
            String groupKey,
            String commandKey,
            String poolKey,
            int poolSize,
            int executionTimeoutInMilliseconds,
            boolean executionTimeoutEnabled,
            HystrixCommandExecutionHook executionHook) {
        super(
                HystrixCommandGroupKey.Factory.asKey(groupKey),
                HystrixCommandKey.Factory.asKey(commandKey),
                HystrixThreadPoolKey.Factory.asKey(poolKey),
                null,
                null,
                HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(executionTimeoutInMilliseconds)
                        .withExecutionTimeoutEnabled(executionTimeoutEnabled),
                HystrixThreadPoolProperties.Setter()
                        .withCoreSize(poolSize)
                        .withMaxQueueSize(10000)
                        .withKeepAliveTimeMinutes(5),
                null,
                null,
                null,
                null,
                executionHook);
    }
}
