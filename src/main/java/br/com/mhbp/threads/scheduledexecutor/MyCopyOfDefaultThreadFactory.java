package br.com.mhbp.threads.scheduledexecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCopyOfDefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final String namePrefix;

    public MyCopyOfDefaultThreadFactory(ThreadGroup group, String namePrefix) {
        this.group = group;
        this.namePrefix = namePrefix;
        poolNumber.getAndIncrement();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(group, runnable, "Threadpool - " + poolNumber.get() + " - " + namePrefix + "_thread " + threadNumber.getAndIncrement() + " --- ");
        thread.setDaemon(true);
//        thread.setUncaughtExceptionHandler(???);
//        thread.setContextClassLoader();???
        thread.setPriority(2);
        return thread;
    }
}
