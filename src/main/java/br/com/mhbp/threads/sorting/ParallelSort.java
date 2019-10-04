package br.com.mhbp.threads.sorting;

import java.util.Arrays;
import java.util.Random;

public class ParallelSort {

    private int[] array;

    public static void main(String[] args) {
        new ParallelSort().doIt();
    }

    private void doIt() {
        final int size = 1024 * 1024 * 4;

        init(size);
        long serial = sortArray(array, false);

        init(size);
        long parallel = sortArray(array, true);

        log("Serial sort of array of size" + size);
        log("Elapsed time: " + serial);

        log("Parallel sort of array of size" + size);
        log("Elapsed time: " + parallel);

    }

    private void log(String s) {
        System.out.println(s);
    }
//www.open-mpi.org
    private long sortArray(int[] array, boolean parallel) {
        long start =  System.currentTimeMillis();
        if (parallel) {
            Arrays.parallelSort(array);
        } else {
            Arrays.sort(array);
        }

        long stop = System.currentTimeMillis();
        return  stop -start;
    }

    private void init(int size) {
        array = new int [size];
        Arrays.parallelSetAll(array, i -> new Random().nextInt());
    }
}
