package br.com.mhbp.threads.training.how_to_timeout_raw_thread;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class T1 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {

            try {
                Thread.sleep(1_000);
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }

                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("one more instructions");
                    Thread.sleep(1_000);

                }

            } catch (InterruptedException e) {
                System.out.println("interruped");
                return;
            }
        });

        t.start();

        TimeUnit.MINUTES.sleep(10);

        t.interrupt();
    }


    public static void main_V2(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(2);

        es.submit(() -> {

        });

        TimeUnit.MINUTES.sleep(10);

        es.shutdown(); // 1. No new tasks are accepted,
                      // 2. Previously submitted tasks are executed

        //tasksThatWereWaitingOnQueue
        List<Runnable>  l = es.shutdownNow(); // 1. No new tasks are accepted,
                                              // 2. Previously submitted tasks waiting in the queue are returned,
                                              // 3. Tasks being run by threads are attempted to stop, but there is no guarantee;
    }


    public static void main_V3(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<String> future = es.submit(() -> {

            return "hi " + new Random().nextInt();
        });

        TimeUnit.MINUTES.sleep(10);

        future.cancel(true); // does no guarantee, just it just attempts to stop
    }
}
