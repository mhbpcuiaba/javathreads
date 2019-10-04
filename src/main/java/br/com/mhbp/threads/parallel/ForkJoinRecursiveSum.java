package br.com.mhbp.threads.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinRecursiveSum extends RecursiveTask<Integer> {

    public static final int SEQUENTIAL_THRESHOLD = 10;
    private static final ForkJoinPool pool = new ForkJoinPool();
    private int lowerIndex, highIndex;
    private int[] array;

    public ForkJoinRecursiveSum(int lowerIndex, int highIndex, int[] array) {
        this.lowerIndex = lowerIndex;
        this.highIndex = highIndex;
        this.array = array;
    }

    public static int sum(int[] arr) throws InterruptedException {
        return pool.invoke(new ForkJoinRecursiveSum( 0, arr.length ,arr));
    }
    @Override
    protected Integer compute() {

        if (highIndex - lowerIndex <= SEQUENTIAL_THRESHOLD) {
            int ans = 0;

            for (int i = lowerIndex; i < highIndex; i++) {
                ans += array[i];
            }
            return ans;
        } else {
            int mid = (highIndex + lowerIndex) / 2;
            ForkJoinRecursiveSum leftRecursiveSum = new ForkJoinRecursiveSum(0, mid, array);
            ForkJoinRecursiveSum rightRecursiveSum = new ForkJoinRecursiveSum(mid, array.length, array);
            leftRecursiveSum.fork();
            Integer rightSum = rightRecursiveSum.compute();
            Integer leftSum = leftRecursiveSum.join();
            return leftSum + rightSum;
        }


    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        System.out.println("sum: " + sum(arr));
    }
}
