package br.com.mhbp.threads.pcd;

import java.util.Stack;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class RuntimePCD {

    private static final ThreadLocal<Stack<BaseTask>> threadLocalTaskStack = new ThreadLocal<Stack<BaseTask>>() {
        protected Stack<BaseTask> initialValue(){
            return new Stack<>();
        }
    };

    private static ForkJoinPool taskPool;


    public static void resizeWorkerThreads(int numOfWorkers) throws InterruptedException {
        taskPool.shutdown();
        boolean terminated = taskPool.awaitTermination(10L, TimeUnit.SECONDS);
        assert  terminated;
        SystemPropertyPCD.numWorkers.set(numOfWorkers);
        taskPool = new ForkJoinPool(numOfWorkers);
    }

    static {
        taskPool = new ForkJoinPool(ConfigurationPCD.readIntProperty(SystemPropertyPCD.numWorkers));
    }
}
