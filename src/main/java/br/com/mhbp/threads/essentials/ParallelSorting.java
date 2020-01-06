package br.com.mhbp.threads.essentials;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class ParallelSorting {

    public static void main(String[] args) {
        int[] numbers = ThreadLocalRandom.current().ints(100_000_000).toArray();
        testingSorting(numbers);

        Arrays.stream(numbers).parallel().sorted();
    }

    private static void testingSorting(int[] numbers) {
        int[] numbersSeq = numbers.clone();
        int[] numbersPar = numbers.clone();

        sort(numbersSeq, Arrays::sort);
        sort(numbersSeq, Arrays::parallelSort);
    }


    private static void sort(int[] numbersSeq, Consumer<int[]> sortAlgorithm) {
        long time = System.currentTimeMillis();
        try {
            sortAlgorithm.accept(numbersSeq);
        } finally {
            time = System.currentTimeMillis() - time;
            System.out.println("time = "+ time + " ms");
        }
    }

}
