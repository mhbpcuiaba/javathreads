package br.com.mhbp.threads.threadpool;

import java.util.Random;
import java.util.concurrent.*;

public class ExecutorCompletionSericeExample {
    ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<Integer> cs = new ExecutorCompletionService<>(es);


        int numberTasks = 100;
        for (int i = 0; i < numberTasks; i++) {
            cs.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return new Random().nextInt();
                }
            });
        }


        for (int i = 0; i < numberTasks; i++) {
            Future<Integer> poll = cs.poll();
            if (poll!= null)
                System.out.println(poll.get());
        }

        es.shutdown();
    }
}
