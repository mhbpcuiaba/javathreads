package br.com.mhbp.threads.cancellation;

import java.math.BigInteger;
import java.util.concurrent.*;

public class PrimeProducerTaskExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        ArrayBlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(100);
        Future<?> taskFuture = pool.submit(new PrimeProducerTask(queue));
        TimeUnit.SECONDS.sleep(1);
        taskFuture.cancel(true);

        try {
            long time = System.currentTimeMillis();
            taskFuture.get();
            time = System.currentTimeMillis() - time;
            System.out.println("time = " + time + " as");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("queue.size: " + queue.size());
        pool.shutdown();
    }
}
