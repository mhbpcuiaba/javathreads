package br.com.mhbp.threads.training.concurrency_vs_parallelism;


/**
 * Shared resource is to be accessed/updated OR
 *
 * Multiple tasks need to coordinate
 *
 * In java are tools to deal with concurrency
 *
 *      - Locks/ syncronized
 *      - Atomic classes
 *      - Concurrent data structures (conurrent hash map, blocking queue)
 *      - CompletableFuture
 *      - Semaphore / CountdownLatch / CyclicBarrier / Phaser
 *
 *
 *
 *
 * Concurrency + Parallelism
 *
 *  - Split the sequential flow into independent components
 *  - Use Threads/threadpools to parallelize(& thus speed up)
 *  - Whenever share resource is to be updated, use concurrency tools to manage state
 *  - Whenever independent components (running on threads) need to coordinate, use concurrency toools.
 *
 *
 */

public class ConcurrencyCode {

    static int ticketsAvailable = 10;

    public static void main(String[] args) throws InterruptedException {

        //task 1
        new Thread(() -> {
            if (ticketsAvailable > 0) { //=> ticketsAvailable is a shared variable
                bookTicket();
                ticketsAvailable--;
            }
        }).start();

        //task 2
        new Thread(() -> {
            if (ticketsAvailable > 0) { //=> ticketsAvailable is a shared variable
                bookTicket();
                ticketsAvailable--;
            }
        }).start();


        Thread.sleep(5000);
    }

    static void bookTicket() {

    }
}
