package br.com.mhbp.threads.training.concurrency_vs_parallelism;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Parallelism is about doing lot of things at once. - Rob Pike
 *
 *
 * So in java are tools to enable Parallelism are:
 *
 *      Threads
 *      ThreadPool
 *          i.   ExecutorService
 *          ii.  ForkJoinPool
 *          iii. Custom ThreadPools (eg: Web Servers)
 *
 *      Requires more than 1 CPU core
 */


public class ParallelismCode {


    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        Object user = new Object();
        Object user2 = new Object();

        es.submit(() ->  processTax(user) );
        es.submit(() ->  processTax(user2) );
    }

    public static void main2(String[] args) {

        Object user1 = new Object();


        // if the machine where we run the code has more than one core than we will have parallelism
        //task 1 -> thread 1
        new Thread(() -> {
            processTax(user1);
        }).start();

        Object user2 = new Object();

        //task 2 -> thread 2
        new Thread(() -> {
            processTax(user2);
        }).start();


        //task 3 -> current thread
        heavyCalculations();
    }

    private static void heavyCalculations() {
    }

    private static void processTax(Object user1) {

    }
}
