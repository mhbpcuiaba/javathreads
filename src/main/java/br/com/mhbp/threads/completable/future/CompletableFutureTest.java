package br.com.mhbp.threads.completable.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {

    public static void main(String[] args) {

        try {
            System.out.println(" ## Main thread ---" + Thread.currentThread().getName());
            String result = calculateAsync().get();
            System.out.println(result);
            System.out.println("_________________________________________");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static CompletableFuture<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        List<String> list = new ArrayList<>();
        list.add("KK");
        list.add("PK");
        list.add("SK");

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {

            try {
                System.out.println(Thread.currentThread().getName() + " has just started ");
                Thread.sleep(20);
                String strJoined = String.join(",", list);
                completableFuture.complete(strJoined);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " has just finished ");
            }
            return null;
        });

        executorService.shutdown();
        return completableFuture;
    }


    static void ex2() {


        CompletableFuture.runAsync(() -> {
            System.out.println("task1 is completed");
        });

        System.out.println("----------------------");


        CompletableFuture.runAsync(() -> {
            System.out.println("task2 is completed");
        }, Executors.newFixedThreadPool(2));


        CompletableFuture.supplyAsync(() -> "it is a supplier!");
    }


    static void ex3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> " Hey").thenApply(s -> s + " you!");
        String s = stringCompletableFuture.get();
        System.out.println(s);
    }

    static void ex4() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> " Hey")
                .thenCompose( s -> CompletableFuture.supplyAsync( () -> s + " you 2"));

        System.out.println(stringCompletableFuture.get());


        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> " Hey")
                .thenCombine(CompletableFuture.supplyAsync(() -> " you 2"), (s1, s2) -> s1 + s2);


        System.out.println(stringCompletableFuture1.get());
    }
}
