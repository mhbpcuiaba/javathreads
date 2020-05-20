package br.com.mhbp.threads.futures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Demonstration {

    static ExecutorService pool = Executors.newFixedThreadPool(2);
    FutureTask ft;
    public static void main(String[] args) throws Exception {
        IntStream generate = IntStream.generate(() -> {
            return ThreadLocalRandom.current().nextInt();
        });


//        int[] array = ThreadLocalRandom.current().ints().limit(1_000_000).toArray();
        List<Integer> integers = Collections.nCopies(10000, 3);
        System.out.println("sum " + sumParallel(integers));


    }

    private static Integer sumParallelIn2Threads(List<Integer> array) throws Exception {

        int t1Max = array.size()/2;
        int t2Max = array.size() ;
        Callable<Integer> c1 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {


                int count = 0;
                for (int i = 0; i < t1Max; i++) {
                    count += array.get(i);
                }
                return count;
            }
        };
        Callable<Integer> c2 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {


                int count = 0;
                for (int i = t1Max; i < t2Max; i++) {
                    count += array.get(i);
                }
                return count;
            }
        };

        return c1.call() + c2.call();
    }

    static class MyCallable implements  Callable<Integer> {
        final int start, end;
        final List<Integer> array;

        MyCallable(int start, int end, List<Integer> array) {
            this.start = start;
            this.end = end;
            this.array = array;
        }

        @Override
        public Integer call() throws Exception {

            int count = 0;
            for (int i = start; i < end; i++) {
                count += array.get(i);
            }
            return count;
        }
    }
    private static Integer sumParallel(List<Integer> array) throws Exception {

        int chunk = 50;
        int total = array.size();
        int start = 0;
        int end =  chunk;
        ArrayList<Future> tasks = new ArrayList<>();

        while (total > start) {

            MyCallable task = new MyCallable(start, start + chunk, array);
            Future<Integer> f = pool.submit(task);
            tasks.add(f);

            start += chunk;
        }


        int sum = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Future<Integer> future = tasks.get(i);
            Integer integer = future.get();
            sum += integer;

        }
        return sum;
    }
}
