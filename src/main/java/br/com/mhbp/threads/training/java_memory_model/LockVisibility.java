package br.com.mhbp.threads.training.java_memory_model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 1 Java thread == 1 OS thread
 *
 * CPU intesive operation => Runtime.getRuntime().availableProcessors()
 *      Ideal pool size == CPU Core count, How many other applications are running on the same CPU
 *
 * IO intensive operation  => Runtime.getRuntime().availableProcessors() * Constant(10?) or Executoers.newFixedThreadPool(100)
 *      Ideal pool size == Higher, exact number will depend on rate of task submissions and average task wait time.
 *          Too many threads will increase memory consumption too.
 *
 *
 *          Types of Basic Pool
 *
     *          FixedThreadPool
     *          CachedThreadPool
     *          ScheduledThreadPool
     *          SingleThreadedExecutor
 *
 *
 *          Types of Advanced Pool
 *
 *
 *          Pool size changes
 *          Queue types
 *          Task rejections
 *          Life cycle methods
 *
 *
 *
 *
 *
 *          public ThreadPoolExecutor(
               int corePoolSize,
               int maximumPoolSize,
               long keepAliveTime,
               TimeUnit unit,
               BlockingQueue<Runnable> workQueue,
               ThreadFactory threadFactory,
               RejectedExecutionHandler handler
 *          )
 *

 LinkedBlockingQueue is unlimited size

 ArrayBlockingQueue is limited size

        Pool                           | Queue Type                       | why?
----------------------------------------------------------------------------------
FixedThreadPool & SingleThreadExecutor | LinkedBlockingQueue              | Threads are limited, thus unbounded queue to store all tasks. Note: Sine queue can never become full, new threads are never created.

CachedThreadPool                       | SynchronousQueue                 | Threads are unbounded, thus no need to store the tasks. Synchronous queue is a queue wit single slot

ScheduledThreadPool                    | DelayedWorkQueue                 | Special queue that deals with schedules/time-delays

Custom                                 | ArrayBlockingQueue               | Bounded queue to store the tasks. If the queue gets full, new thread is created ( as long as cont is less than maxPoolSize
 *
 *
 *
 *
 *
 *
 * Thread Pool Policy
 *
 *
 *
 *  AbortPolicy             Submitting new tasks throws RejectedExecutionException
 *
 *  DiscardPolicy           Submitting new tasks silently discards it
 *
 *  DiscardOldestPolicy     Submitting new tasks drops existing oldest tasl, and new task is added to the queue
 *
 *  CallerRunsPolicy        Submiting new tasks will execute the task on the caller thread itself. This can create feedback loop where caller thread is busy executing the task and cannot submit bnew tasks at fast pace
 *
 *
 *
 *
 *  Thread pool life cycles method
 *
 *  Executor service = Executors.newFixedThradPool(10)
 *
 *  service.execute(new Task());
 *
 *  service.shutdown(); -> will throw RejectionExecutionException in case service.execute(..)
 *
 *  service.isShutdwn() -> will return true, since shutdown has begun
 *
 *  service.isTerminated() -> will return true if all tasks are completed, including queue ones
 *
 *  service.awaiTermination(10, TimeUnit.Seconds) -> block until all tasks are completed or if timeout occurs
 *
 * List<Runnable> runnables = service.shutdownNow() -> will initiate shutdown and return all queued tasks
 *
 *
 *
 *
 * Scheduling overhead, data locality when we have too much threads
 *
 *
 * Problem
 *
 * Expensive Threads & Blocking IO Ops == Limited Scalability
 *
 * Solution
 *
 * Non-blocking IO & Asynchronous API
 *
 *


 for (Integer id: employeesIds) {

    CompletableFuture.supplyAsync( () -> fetchEmployee( id ))
                     .thenApplyAsync( employee -> fetchTaxRate(employee))
                     .thenApplyAsync( taxRate -> calculateTax(taxRate))
                     .thenAcceptAsync( taxValue -> sendEmail(taxValue));

 }



Java NIO

    * Buffer, Channels, Selectors
    * Low-level API for asynchronous / non-blocking IO
    * Applicable for Files, Sockets
    * Listener based (callbacks)


 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class LockVisibility {

    int a = 0, b = 0, c = 0, x = 0;
    Lock lock = new ReentrantLock();


    public void writerThread_V2() {

        try {
            lock.lock();
            a = 1;
            b = 1;
            c = 1;
            x = 1;
        }finally {
            lock.unlock();
        }
    }

    public void readerThread_V2() {

        try {
            lock.lock();
            int r2 = x;
            int d1 = a;
            int d2 = b;
            int d3 = c;
        } finally {
            lock.unlock();
        }

    }
}
