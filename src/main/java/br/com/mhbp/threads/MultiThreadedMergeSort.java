package br.com.mhbp.threads;

public class MultiThreadedMergeSort {


    private static int SIZE = 25;
    private int[] input = new int[SIZE];
    private int[] scratch = new int[SIZE];

   void mergesort(int left, int right, int[] array) {
        if (left != right) {
            int mid = left + (right - left)/2;
            Thread t1 = new Thread(() -> {
                mergesort(left, mid, array);
            });

            Thread t2 = new Thread(() -> {
                mergesort( mid + 1, right, array);
            });

            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            }catch (Exception e) { }

            int i = left;
            int j = mid + 1;
            int k;
            //merge
            for (k = left; k <= right; k++) {
                scratch[k] = array[k];
            }

            k = left;
            while (k <= right) {

                if (i <= mid && j <= right) {
                    array[k] = Math.min(scratch[i], scratch[j]);

                    if (array[k] == scratch[i]) i++;
                    else j++;


                } else  if (i <= mid && j > right)
                    array[k] = scratch[i++];
                 else {
                    array[k] = scratch[j++];
                }
                k++;
            }
        }
    }
}
