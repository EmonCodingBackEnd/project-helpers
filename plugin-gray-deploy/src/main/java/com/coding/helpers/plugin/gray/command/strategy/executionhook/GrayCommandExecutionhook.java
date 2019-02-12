package com.coding.helpers.plugin.gray.command.strategy.executionhook;

import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContextCopy;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;

public class GrayCommandExecutionhook extends HystrixCommandExecutionHook {

    private HystrixRequestContext copy;

    @Override
    public <T> void onStart(HystrixInvokable<T> commandInstance) {
        copy = HystrixRequestContextCopy.get();
    }

    @Override
    public <T> void onThreadStart(HystrixInvokable<T> commandInstance) {
        if (!HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.initializeContext();
        }
        if (copy != null) {
            HystrixRequestContext.setContextOnCurrentThread(copy);
        }
    }

    @Override
    public <T> void onThreadComplete(HystrixInvokable<T> commandInstance) {
        if (HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.getContextForCurrentThread().shutdown();
        }
    }
}
