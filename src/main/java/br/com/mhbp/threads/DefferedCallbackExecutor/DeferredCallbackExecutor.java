package br.com.mhbp.threads.DefferedCallbackExecutor;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackExecutor {


    public static void main(String[] args) throws InterruptedException {
        HashSet<Thread> allThreads = new HashSet<>();
        DeferredCallbackExecutor dce = new DeferredCallbackExecutor();

        Thread service = new Thread(() -> {
            try {
                dce.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.start();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    CallBack cb = new CallBack(1, "Hello this is " + Thread.currentThread().getName());
                    dce.registerCallback(cb);
                }
            });
            thread.setName("Thread_" + (i + 1));
            thread.start();
            allThreads.add(thread);
            Thread.sleep(1000);
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }





    private static Random random = new Random(System.currentTimeMillis());

    PriorityQueue<CallBack> queue =new PriorityQueue<CallBack>(new Comparator<CallBack>() {
        @Override
        public int compare(CallBack cb1, CallBack cb2) {
            return (int) (cb1.executedAt - cb2.executedAt);
        }
    });

    ReentrantLock lock = new ReentrantLock();
    Condition newCallbackArrived = lock.newCondition();

    public void start() throws InterruptedException {

        long sleepFor = 0;

        while (true) {
            lock.lock();
            try {

                while (queue.isEmpty()) newCallbackArrived.await();

                while (!queue.isEmpty()) {
                    sleepFor = findSleepDuration();

                    if ( sleepFor <= 0) break;

                    newCallbackArrived.await(sleepFor, TimeUnit.MILLISECONDS);
                }

                CallBack cb = queue.poll();

                System.out.println(
                        "Executed at " + System.currentTimeMillis() / 1000 + " required at " + cb.executedAt / 1000
                                + ": message:" + cb.message);

            }finally {
                lock.unlock();
            }
        }
    }

    public void registerCallback(CallBack cb) {
        lock.lock();
        try {
            queue.add(cb);
            newCallbackArrived.signal();

        }finally {
            lock.unlock();
        }
    }

    private long findSleepDuration() {
        long currentTime = System.currentTimeMillis();
        return queue.peek().executedAt - currentTime;
    }
}
