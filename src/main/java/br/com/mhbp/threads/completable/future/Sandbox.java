package br.com.mhbp.threads.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sandbox {

    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Executed! in " + Thread.currentThread().getName());
        Runnable task2 = () -> System.out.println("Executed! in " + Thread.currentThread().getName());
        Runnable task3 = () -> System.out.println("Executed! in " + Thread.currentThread().getName());

        CompletableFuture<Void> cf = CompletableFuture.runAsync(task);
        cf.thenAccept(System.out::println);

        System.out.println("main thread " + Thread.currentThread().getName());
        cf.join();

        ExecutorService es = Executors.newSingleThreadExecutor();
        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(task2, es);

        cf2.join();

        CompletableFuture<Runnable> ecf = CompletableFuture.supplyAsync(() -> task3, es);
        ecf.complete(() -> System.out.println("fim..."));

        cf.thenAccept(System.out::println);
        ecf.join();
        es.shutdown();
    System.out.println("game over");

    }
}
