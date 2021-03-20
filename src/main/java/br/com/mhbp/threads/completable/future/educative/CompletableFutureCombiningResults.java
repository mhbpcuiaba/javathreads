package br.com.mhbp.threads.completable.future.educative;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class CompletableFutureCombiningResults {


    public static void allOf(String args[]) {

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 50);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 40);
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 30);

        CompletableFuture<Void> finalFuture = CompletableFuture.allOf(future1, future2, future3);

        try {
            finalFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Code that should be executed after all the futures complete.");
    }

    public static void join(String args[]) {

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 50);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 40);
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 30);

        Optional<Integer> maxElement = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join) // This will return the stream of results of all futures.
                .max(Integer::compareTo);

        System.out.println("The max element is " + maxElement);
    }

    public static void anyOf(String args[]) {

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 50);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 40);
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 30);

        //The first completed future will be returned.
        CompletableFuture<Object> firstCompletedFuture = CompletableFuture.anyOf(future1, future2, future3);

        try {
            System.out.println("The first completed future value is " + firstCompletedFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Code that should be executed after any of the futures complete.");
    }
}
