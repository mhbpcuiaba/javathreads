package br.com.mhbp.threads.training;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

public class ScatterGatherProblem {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(4);
        CountDownLatch latch = new CountDownLatch(3);
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());
        es.submit(new Task(prices, latch));
        es.submit(new Task(prices, latch));
        es.submit(new Task(prices, latch));

        latch.await(3, TimeUnit.MINUTES);

        System.out.println(prices);
    }


    public static void main_V2(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());

        CompletableFuture<Void> task1 = CompletableFuture.runAsync(new TaskwithouLatch(prices));
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(new TaskwithouLatch(prices));
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(new TaskwithouLatch(prices));

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
        allTasks.get(3, TimeUnit.SECONDS);

        System.out.println(prices);
    }
}

class Task implements Runnable {


    private Set<Integer> prices;
    private CountDownLatch latch;

    Task(Set<Integer> prices, CountDownLatch latch) {
        this.prices = prices;
        this.latch = latch;
    }

    @Override
    public void run() {
        int price = new Random().nextInt(); //simulate http call
        prices.add(price);
        latch.countDown();
    }
}

class TaskwithouLatch implements Runnable {


    private Set<Integer> prices;

    TaskwithouLatch(Set<Integer> prices) {
        this.prices = prices;
    }

    @Override
    public void run() {
        int price = new Random().nextInt(); //simulate http call
        prices.add(price);
    }
}


