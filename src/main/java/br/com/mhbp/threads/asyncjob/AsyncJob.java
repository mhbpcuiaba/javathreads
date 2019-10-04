package br.com.mhbp.threads.asyncjob;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
thread-safe channel from one thread to another. very popular mechanism in Go language
* */
public class AsyncJob implements Callable<Long> {

    @Override
    public Long call() throws Exception {
        Long sum = 0L;
        final Integer n = 1000;
        for(Long i = 0L; i < n; i++) {
            sum += i;
        }
        return sum;
    }
}

class Main {

    public static void main(String[] args) {
        final Integer poolSize = 10;
        final Integer n = 20_000;

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        List<Future<Long>> jobList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Callable<Long> job = new AsyncJob();
            Future<Long> pendingJob = executor.submit(job);
            jobList.add(pendingJob);
        }

        Long sum = 0L;

        for(Future<Long> result: jobList) {

            try {
                sum += result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        System.out.println("List size: " + jobList.size());
        System.out.println("Sum: " + sum);
    }
}